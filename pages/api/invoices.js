// pages/api/invoices.js
// Stores tax invoices generated from the SF Spare Inventory tab

import { getStore } from '../../lib/store';

export default async function handler(req, res) {
  const store = getStore();

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

  res.status(405).json({ error: 'Method not allowed' });
}
