import { getStock, addStock, getTechnicians, getMaterials } from '../../../lib/inventory';
const MASTER_PWD = 'Project@1';
export default async function handler(req, res) {
  if (req.method === 'GET') {
    const { techId, password } = req.query;
    if (password === MASTER_PWD) {
      const techs = await getTechnicians();
      const materials = await getMaterials();
      const allStock = {};
      for (const t of techs) { allStock[t.id] = await getStock(t.id); }
      return res.status(200).json({ techs, materials, allStock });
    }
    if (techId) {
      const techs = await getTechnicians();
      const tech = techs.find(t => t.id === techId);
      if (!tech) return res.status(404).json({ error: 'Technician not found' });
      const stock = await getStock(techId);
      const materials = await getMaterials();
      return res.status(200).json({ tech, stock, materials });
    }
    return res.status(400).json({ error: 'techId or master password required' });
  }
  if (req.method === 'POST') {
    const { password, techId, materialId, qty, costPrice } = req.body;
    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
    if (!techId || !materialId || !qty) return res.status(400).json({ error: 'techId, materialId, qty required' });
    await addStock(techId, materialId, parseFloat(qty), parseFloat(costPrice||0));
    const stock = await getStock(techId);
    return res.status(200).json({ success: true, stock });
  }
  return res.status(405).json({ error: 'Method not allowed' });
}
