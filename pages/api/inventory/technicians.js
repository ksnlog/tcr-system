// pages/api/inventory/technicians.js
// GET  ?sfId=SF001              → returns technicians for that SF (used by TCR form after login)
// GET  (no sfId)                → returns global technician list (used by Master stock tab)
// POST { password, action, techId, techName } → add/remove from global list (Master stock tab)

import { kv } from '@vercel/kv';

const MASTER_PWD = 'Project@1';

export default async function handler(req, res) {
  if (req.method === 'GET') {
    const { sfId } = req.query;

    if (sfId) {
      // Return SF-scoped tech list (for TCR form dropdown) — no auth needed, just names+ids
      try {
        const techs = await kv.get(`sf:${sfId}:technicians`) || [];
        return res.status(200).json(techs.map(({ id, name }) => ({ id, name })));
      } catch (e) {
        return res.status(500).json({ error: 'Server error' });
      }
    } else {
      // Return global technician list (for stock management)
      try {
        const techs = await kv.get('technicians') || [];
        return res.status(200).json(techs);
      } catch (e) {
        return res.status(500).json({ error: 'Server error' });
      }
    }
  }

  if (req.method === 'POST') {
    const { password, action, techId, techName } = req.body || {};
    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });

    try {
      let techs = await kv.get('technicians') || [];

      if (action === 'add') {
        if (!techId || !techName) return res.status(400).json({ error: 'techId and techName required' });
        if (techs.find(t => t.id === techId)) return res.status(400).json({ error: 'Tech ID already exists' });
        techs.push({ id: techId, name: techName });
        await kv.set('technicians', techs);
        return res.status(200).json({ techs });
      }

      if (action === 'remove') {
        techs = techs.filter(t => t.id !== techId);
        await kv.set('technicians', techs);
        return res.status(200).json({ techs });
      }

      return res.status(400).json({ error: 'Unknown action' });
    } catch (e) {
      return res.status(500).json({ error: 'Server error' });
    }
  }

  return res.status(405).json({ error: 'Method not allowed' });
}
