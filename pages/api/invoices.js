// pages/api/invoices.js
// Stores tax invoices generated from the SF Spare Inventory tab

import { Redis } from '@upstash/redis';

const redis = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});

export default async function handler(req, res) {
  const store = redis;

  // ── GET: list invoices for an SF ───────────────────────────────────────────
  if (req.method === 'GET') {
    const { sfId } = req.query;
    if (!sfId) return res.status(400).json({ error: 'sfId required' });
    try {
      const data = (await store.get(`invoices_${sfId}`)) || { invoices: [] };
      return res.json(data);
    } catch (e) {
      return res.status(500).json({ error: 'Failed to load invoices' });
    }
  }

  // ── POST: save a new invoice ────────────────────────────────────────────────
  if (req.method === 'POST') {
    const { sfId, invoice } = req.body;
    if (!sfId || !invoice) return res.status(400).json({ error: 'sfId and invoice required' });
    const key = `invoices_${sfId}`;
    let data;
    try {
      data = (await store.get(key)) || { invoices: [] };
    } catch (e) {
      data = { invoices: [] };
    }
    const saved = { ...invoice, id: Date.now().toString(), savedAt: Date.now() };
    data.invoices.unshift(saved);   // newest first
    // keep last 200 invoices per SF
    if (data.invoices.length > 200) data.invoices = data.invoices.slice(0, 200);
    await store.set(key, data);
    return res.json({ invoice: saved });
  }

  // ── PUT: update an invoice (e.g. payment status) ───────────────────────────
  if (req.method === 'PUT') {
    const { sfId, id, updates } = req.body;
    if (!sfId || !id) return res.status(400).json({ error: 'sfId and id required' });
    const key = `invoices_${sfId}`;
    try {
      let data = (await store.get(key)) || { invoices: [] };
      const idx = data.invoices.findIndex(inv => inv.id === id);
      if (idx !== -1) {
        data.invoices[idx] = { ...data.invoices[idx], ...updates };
        await store.set(key, data);
        return res.json({ success: true, invoice: data.invoices[idx] });
      }
      return res.status(404).json({ error: 'Invoice not found' });
    } catch (e) {
      return res.status(500).json({ error: 'Failed to update invoice' });
    }
  }

  res.status(405).json({ error: 'Method not allowed' });
}
