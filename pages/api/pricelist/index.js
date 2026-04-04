import { getPriceList } from '../../../lib/pricelist';

export default async function handler(req, res) {
  if (req.method !== 'GET') return res.status(405).json({ error: 'Method not allowed' });
  const list = await getPriceList();
  const msl = list.reduce((sum, item) => sum + (parseFloat(item.dp) || 0), 0);
  return res.status(200).json({ items: list, msl });
}
