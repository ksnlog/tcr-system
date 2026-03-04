import { getTechnicians, saveTechnicians } from '../../../lib/inventory';
const MASTER_PWD = 'Project@1';
export default async function handler(req, res) {
  if (req.method === 'GET') {
    const techs = await getTechnicians();
    return res.status(200).json(techs);
  }
  if (req.method === 'POST') {
    const { password, action, techId, techName } = req.body;
    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
    const techs = await getTechnicians();
    if (action === 'add') {
      if (!techId || !techName) return res.status(400).json({ error: 'techId and techName required' });
      if (techs.find(t => t.id === techId)) return res.status(400).json({ error: 'Tech ID already exists' });
      techs.push({ id: techId, name: techName, active: true });
      await saveTechnicians(techs);
      return res.status(200).json({ success: true, techs });
    }
    if (action === 'remove') {
      const updated = techs.filter(t => t.id !== techId);
      await saveTechnicians(updated);
      return res.status(200).json({ success: true, techs: updated });
    }
    return res.status(400).json({ error: 'Invalid action' });
  }
  return res.status(405).json({ error: 'Method not allowed' });
}
