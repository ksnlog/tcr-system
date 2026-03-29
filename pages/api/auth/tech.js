// pages/api/auth/tech.js
// POST { techId, password }
// Searches all SFs for a matching technician → returns { techId, techName, sfId, sfName }

import { kv } from '@vercel/kv';

export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });

  const { techId, password } = req.body || {};
  if (!techId || !password) return res.status(400).json({ error: 'Tech ID and password required' });

  try {
    // Get all SF records
    const sfs = await kv.get('admin:sfs') || [];

    for (const sf of sfs) {
      // Get this SF's technician list
      const techs = await kv.get(`sf:${sf.id}:technicians`) || [];
      const tech = techs.find(t => t.id === techId && t.password === password);
      if (tech) {
        return res.status(200).json({
          techId: tech.id,
          techName: tech.name,
          sfId: sf.id,
          sfName: sf.name,
        });
      }
    }

    return res.status(401).json({ error: 'Invalid Tech ID or password' });
  } catch (e) {
    console.error('Auth error:', e);
    return res.status(500).json({ error: 'Server error' });
  }
}
