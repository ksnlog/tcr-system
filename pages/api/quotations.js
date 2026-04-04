// pages/api/quotations.js
// GET  ?sfId=...              → list quotations for an SF
// POST { sfId, quotation }    → save a new quotation

import { Redis } from '@upstash/redis';

const redis = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});

export default async function handler(req, res) {

  // ── GET: list quotations for an SF ────────────────────────────────────────
  if (req.method === 'GET') {
    const { sfId } = req.query;
    if (!sfId) return res.status(400).json({ error: 'sfId required' });
    try {
      const data = (await redis.get(`quotations_${sfId}`)) || { quotations: [] };
      return res.json(data);
    } catch (e) {
      return res.status(500).json({ error: 'Failed to load quotations' });
    }
  }

  // ── POST: save a new quotation ────────────────────────────────────────────
  if (req.method === 'POST') {
    const { sfId, quotation } = req.body;
    if (!sfId || !quotation) return res.status(400).json({ error: 'sfId and quotation required' });

    const key = `quotations_${sfId}`;
    let data;
    try {
      data = (await redis.get(key)) || { quotations: [] };
    } catch (e) {
      data = { quotations: [] };
    }

    const saved = { ...quotation, id: Date.now().toString(), savedAt: Date.now() };
    data.quotations.unshift(saved);   // newest first

    // keep last 200 quotations per SF
    if (data.quotations.length > 200) data.quotations = data.quotations.slice(0, 200);

    await redis.set(key, data);
    return res.json({ quotation: saved });
  }

  res.status(405).json({ error: 'Method not allowed' });
}
