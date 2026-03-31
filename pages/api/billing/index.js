const MASTER_PWD = 'Project@1';

export default async function handler(req, res) {
  if (req.method === 'POST') {
    const { password, invoice } = req.body;
    if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
    if (!invoice) return res.status(400).json({ error: 'Invoice data required' });
    try {
      const { saveInvoice } = await import('../../lib/billing');
      const saved = await saveInvoice(invoice);
      return res.status(200).json({ success: true, invoice: saved });
    } catch(e) {
      return res.status(500).json({ error: 'Failed to save invoice: ' + e.message });
    }
  }
  return res.status(405).json({ error: 'Method not allowed' });
}
