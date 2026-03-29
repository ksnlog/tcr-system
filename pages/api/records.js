/**
 * POST /api/records  — save an approved TCR record permanently
 * GET  /api/records  — fetch all records (or filter by ?techId=)
 */
import { Redis } from '@upstash/redis';

const redis = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});

export default async function handler(req, res) {

  // ── SAVE a new approved record ──────────────────────────────────────
  if (req.method === 'POST') {
    try {
      const data = { ...req.body, confirmedAt: new Date().toISOString() };
      const key = `tcr:record:${data.callNo}`;

      // Avoid duplicates
      const existing = await redis.get(key);
      if (!existing) {
        await redis.set(key, data);                           // permanent — no TTL
        await redis.lpush('tcr:records:index', data.callNo); // global index
        if (data.techName) {
          await redis.lpush(`tcr:records:tech:${data.techName}`, data.callNo); // per-tech index
        }
      }
      return res.status(200).json({ ok: true });
    } catch (e) {
      console.error('Records save error:', e);
      return res.status(500).json({ error: 'Failed to save record' });
    }
  }

  // ── FETCH records ───────────────────────────────────────────────────
  if (req.method === 'GET') {
    try {
      const { techId } = req.query;
      const indexKey = techId ? `tcr:records:tech:${techId}` : 'tcr:records:index';
      const callNos = await redis.lrange(indexKey, 0, -1);

      if (!callNos || callNos.length === 0) return res.status(200).json([]);

      // Fetch all records in parallel
      const records = await Promise.all(
        callNos.map(cn => redis.get(`tcr:record:${cn}`))
      );

      // Filter nulls, sort newest first
      const sorted = records
        .filter(Boolean)
        .sort((a, b) => new Date(b.confirmedAt || 0) - new Date(a.confirmedAt || 0));

      return res.status(200).json(sorted);
    } catch (e) {
      console.error('Records fetch error:', e);
      return res.status(500).json({ error: 'Failed to fetch records' });
    }
  }

  res.status(405).json({ error: 'Method not allowed' });
}
