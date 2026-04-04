import { getAllInvoices, getInvoice } from '../../../lib/billing';

export default async function handler(req, res) {
  if (req.method === 'GET') {
    const { id } = req.query;
    if (id) {
      const invoice = await getInvoice(id);
      if (!invoice) return res.status(404).json({ error: 'Invoice not found' });
      return res.status(200).json(invoice);
    }
    const invoices = await getAllInvoices();
    return res.status(200).json(invoices);
  }
  return res.status(405).json({ error: 'Method not allowed' });
}
