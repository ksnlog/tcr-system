// pages/api/admin/sfs.js
// GET  ?password=...            → list all SFs with their technicians
// POST { password, action, ... } → createSf | removeSf | addTech | removeTech

import { Redis } from '@upstash/redis';
const kv = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});

const MASTER_PWD = 'Project@1';

export default async function handler(req, res) {
  // ─── GET: list all SFs ────────────────────────────────────────────────────
  if (req.method === 'GET') {
    if (req.query.password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
    try {
      const sfMeta = await kv.get('admin:sfs') || [];
      // Hydrate each SF with its technician list (without exposing passwords)
      const sfs = await Promise.all(sfMeta.map(async (sf) => {
        const techs = await kv.get(`sf:${sf.id}:technicians`) || [];
        return {
          ...sf,
          techs: techs.map(({ id, name }) => ({ id, name })), // strip passwords
        };
      }));
      return res.status(200).json({ sfs });
    } catch (e) {
      return res.status(500).json({ error: 'Server error' });
    }
  }

  // ─── POST: mutations ───────────────────────────────────────────────────────
  if (req.method === 'POST') {
    const { password, action, ...data } = req.body || {};

    // ── sfLogin — uses SF's own password, NOT master password ──
    if (action === 'sfLogin') {
      const { sfId, sfPassword } = data;
      if (!sfId || !sfPassword) return res.status(400).json({ error: 'SF ID and password required' });
      try {
        const sfs = await kv.get('admin:sfs') || [];
        const sf = sfs.find(s => s.id === sfId.toUpperCase());
        if (!sf || sf.password !== sfPassword) return res.status(401).json({ error: 'Invalid SF ID or password' });
        return res.status(200).json({ sfId: sf.id, sfName: sf.name });
      } catch (e) {
        return res.status(500).json({ error: 'Server error' });
      }
    }

    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });

    try {
      // ── createSf ──
      if (action === 'createSf') {
        const { sfId, sfName, sfPassword } = data;
        if (!sfId || !sfName || !sfPassword) return res.status(400).json({ error: 'sfId, sfName and sfPassword required' });

        let sfs = await kv.get('admin:sfs') || [];
        if (sfs.find(s => s.id === sfId)) return res.status(400).json({ error: `SF ID ${sfId} already exists` });

        sfs.push({ id: sfId, name: sfName, password: sfPassword, createdAt: new Date().toISOString() });
        await kv.set('admin:sfs', sfs);
        await kv.set(`sf:${sfId}:technicians`, []); // empty tech list

        const hydrated = await hydrateSfs(sfs);
        return res.status(200).json({ sfs: hydrated });
      }

      // ── removeSf ──
      if (action === 'removeSf') {
        const { sfId } = data;
        let sfs = await kv.get('admin:sfs') || [];
        sfs = sfs.filter(s => s.id !== sfId);
        await kv.set('admin:sfs', sfs);
        await kv.del(`sf:${sfId}:technicians`);

        const hydrated = await hydrateSfs(sfs);
        return res.status(200).json({ sfs: hydrated });
      }

      // ── addTech ──
      if (action === 'addTech') {
        const { sfId, techId, techName, techPassword } = data;
        if (!sfId || !techId || !techName || !techPassword)
          return res.status(400).json({ error: 'sfId, techId, techName and techPassword required' });

        // Check SF exists
        const sfs = await kv.get('admin:sfs') || [];
        if (!sfs.find(s => s.id === sfId)) return res.status(400).json({ error: `SF ${sfId} not found` });

        // Add to SF's technician list
        let sfTechs = await kv.get(`sf:${sfId}:technicians`) || [];
        if (sfTechs.find(t => t.id === techId)) return res.status(400).json({ error: `Tech ID ${techId} already exists in this SF` });
        sfTechs.push({ id: techId, name: techName, password: techPassword });
        await kv.set(`sf:${sfId}:technicians`, sfTechs);

        // Also add to global technicians list (for stock tracking compatibility)
        let globalTechs = await kv.get('technicians') || [];
        if (!globalTechs.find(t => t.id === techId)) {
          globalTechs.push({ id: techId, name: techName });
          await kv.set('technicians', globalTechs);
        }

        const hydrated = await hydrateSfs(sfs);
        return res.status(200).json({ sfs: hydrated });
      }

      // ── removeTech ──
      if (action === 'removeTech') {
        const { sfId, techId } = data;
        let sfTechs = await kv.get(`sf:${sfId}:technicians`) || [];
        sfTechs = sfTechs.filter(t => t.id !== techId);
        await kv.set(`sf:${sfId}:technicians`, sfTechs);

        // Also remove from global technicians list
        let globalTechs = await kv.get('technicians') || [];
        globalTechs = globalTechs.filter(t => t.id !== techId);
        await kv.set('technicians', globalTechs);

        const sfs = await kv.get('admin:sfs') || [];
        const hydrated = await hydrateSfs(sfs);
        return res.status(200).json({ sfs: hydrated });
      }

      return res.status(400).json({ error: 'Unknown action' });

    } catch (e) {
      console.error('SF admin error:', e);
      return res.status(500).json({ error: 'Server error' });
    }
  }

  return res.status(405).json({ error: 'Method not allowed' });
}

// Helper: hydrate SF list with tech counts (no passwords)
async function hydrateSfs(sfMeta) {
  return Promise.all(sfMeta.map(async (sf) => {
    const techs = await kv.get(`sf:${sf.id}:technicians`) || [];
    return { ...sf, techs: techs.map(({ id, name }) => ({ id, name })) };
  }));
}
