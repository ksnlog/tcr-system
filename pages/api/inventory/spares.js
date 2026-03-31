// pages/api/inventory/spares.js
// Manages SF-scoped spare parts catalog: material code, description, DP, MRP, stock, usage count

import { getStore } from '../../../lib/store';

export default async function handler(req, res) {
  const store = getStore();

  // ── GET: fetch all spares for an SF ────────────────────────────────────────
  if (req.method === 'GET') {
    const { sfId } = req.query;
    if (!sfId) return res.status(400).json({ error: 'sfId required' });
    try {
      const data = (await store.get(`spares_${sfId}`)) || { items: [] };
      return res.json(data);
    } catch (e) {
      return res.status(500).json({ error: 'Failed to load spares' });
    }
  }

  // ── POST: mutate spare catalog ──────────────────────────────────────────────
  if (req.method === 'POST') {
    const { sfId, action, ...payload } = req.body;
    if (!sfId) return res.status(400).json({ error: 'sfId required' });
    if (!action) return res.status(400).json({ error: 'action required' });

    const key = `spares_${sfId}`;
    let data;
    try {
      data = (await store.get(key)) || { items: [] };
    } catch (e) {
      data = { items: [] };
    }

    // ── addItem ──
    if (action === 'addItem') {
      const { materialCode, description, dp, mrp, stock } = payload;
      if (!materialCode?.trim() || !description?.trim())
        return res.status(400).json({ error: 'Material code and description are required' });
      const code = materialCode.trim().toUpperCase();
      if (data.items.find(i => i.materialCode === code))
        return res.status(400).json({ error: 'Material code already exists' });
      data.items.push({
        materialCode: code,
        description: description.trim(),
        dp: parseFloat(dp) || 0,
        mrp: parseFloat(mrp) || 0,
        stock: parseFloat(stock) || 0,
        usageCount: 0,
        totalQtySold: 0,
        createdAt: Date.now(),
      });
      await store.set(key, data);
      return res.json(data);
    }

    // ── updateStock ──
    if (action === 'updateStock') {
      const { materialCode, qty, operation } = payload;
      const item = data.items.find(i => i.materialCode === materialCode);
      if (!item) return res.status(404).json({ error: 'Material not found' });
      const q = parseFloat(qty) || 0;
      if (operation === 'add') item.stock = Math.max(0, (item.stock || 0) + q);
      else if (operation === 'subtract') item.stock = Math.max(0, (item.stock || 0) - q);
      else item.stock = Math.max(0, q); // 'set'
      await store.set(key, data);
      return res.json(data);
    }

    // ── recordUsage: called after invoice is generated ──
    if (action === 'recordUsage') {
      const { items: usedItems } = payload; // [{materialCode, qty}]
      if (!Array.isArray(usedItems)) return res.status(400).json({ error: 'items array required' });
      usedItems.forEach(u => {
        const item = data.items.find(i => i.materialCode === u.materialCode);
        if (item) {
          item.usageCount = (item.usageCount || 0) + 1;           // invoice count
          item.totalQtySold = (item.totalQtySold || 0) + (parseFloat(u.qty) || 0);
          item.stock = Math.max(0, (item.stock || 0) - (parseFloat(u.qty) || 0));
        }
      });
      await store.set(key, data);
      return res.json(data);
    }

    // ── updateItem: edit description / dp / mrp ──
    if (action === 'updateItem') {
      const { materialCode, description, dp, mrp } = payload;
      const item = data.items.find(i => i.materialCode === materialCode);
      if (!item) return res.status(404).json({ error: 'Material not found' });
      if (description !== undefined) item.description = description.trim();
      if (dp !== undefined) item.dp = parseFloat(dp) || 0;
      if (mrp !== undefined) item.mrp = parseFloat(mrp) || 0;
      await store.set(key, data);
      return res.json(data);
    }

    // ── deleteItem ──
    if (action === 'deleteItem') {
      const { materialCode } = payload;
      data.items = data.items.filter(i => i.materialCode !== materialCode);
      await store.set(key, data);
      return res.json(data);
    }

    // ── bulkUpload ──
    if (action === 'bulkUpload') {
      const { items } = payload;
      if (!Array.isArray(items)) return res.status(400).json({ error: 'items array required' });
      let added = 0, updated = 0;
      items.forEach(row => {
        const code = String(row.materialCode || row['Material Code'] || '').trim().toUpperCase();
        const desc = String(row.description || row['Description'] || '').trim();
        const dp = parseFloat(row.dp || row['DP'] || 0) || 0;
        const mrp = parseFloat(row.mrp || row['MRP'] || 0) || 0;
        if (!code) return;
        const idx = data.items.findIndex(i => i.materialCode === code);
        if (idx >= 0) {
          data.items[idx] = { ...data.items[idx], description: desc || data.items[idx].description, dp, mrp };
          updated++;
        } else {
          data.items.push({ materialCode: code, description: desc, dp, mrp, stock: 0, usageCount: 0, totalQtySold: 0, createdAt: Date.now() });
          added++;
        }
      });
      await store.set(key, data);
      return res.json({ ...data, message: `Added ${added}, updated ${updated} items` });
    }

    return res.status(400).json({ error: 'Unknown action' });
  }

  res.status(405).json({ error: 'Method not allowed' });
}
