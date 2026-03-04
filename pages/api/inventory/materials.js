import { getMaterials, saveMaterials } from '../../../lib/inventory';
const MASTER_PWD = 'Project@1';
export default async function handler(req, res) {
  if (req.method === 'GET') {
    const materials = await getMaterials();
    return res.status(200).json(materials);
  }
  if (req.method === 'POST') {
    const { password, materials } = req.body;
    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
    await saveMaterials(materials);
    return res.status(200).json({ success: true });
  }
  return res.status(405).json({ error: 'Method not allowed' });
}
