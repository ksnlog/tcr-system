import { deductStock, getTechnicians } from '../../../lib/inventory';
export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });
  const { techId, items, jobNo } = req.body;
  if (!techId || !items || !jobNo) return res.status(400).json({ error: 'techId, items, jobNo required' });
  const techs = await getTechnicians();
  const tech = techs.find(t => t.id === techId);
  if (!tech) return res.status(404).json({ error: 'Technician not found' });
  const results = [];
  for (const item of items) {
    const remaining = await deductStock(techId, item.materialId, item.qty, jobNo);
    results.push({ materialId: item.materialId, deducted: item.qty, remaining });
  }
  return res.status(200).json({ success: true, results });
}
