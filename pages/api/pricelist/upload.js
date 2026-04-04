import { getPriceList, bulkUpsertPriceList } from '../../../lib/pricelist';
const MASTER_PWD = 'Project@1';
import * as XLSX from 'xlsx';

export const config = { api: { bodyParser: { sizeLimit: '50mb' } } };

export default async function handler(req, res) {
  if (req.method === 'GET') {
    const list = await getPriceList();
    return res.status(200).json(list);
  }
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });
  const { password } = req.body;
  if (password !== MASTER_PWD) return res.status(401).json({ error: 'Unauthorized' });
  try {
    let rows = [];
    if (req.body.csv) {
      const workbook = XLSX.read(req.body.csv, { type: 'base64' });
      const sheet = workbook.Sheets[workbook.SheetNames[0]];
      rows = XLSX.utils.sheet_to_json(sheet);
    } else if (req.body.data) {
      rows = req.body.data;
    }
    const items = rows.map(row => {
      const mc = String(row['Material Code'] || row['MaterialCode'] || row['materialCode'] || '').trim();
      const desc = String(row['Description'] || row['description'] || '').trim();
      const dp = parseFloat(row['DP'] || row['dp'] || 0) || 0;
      const mrp = parseFloat(row['MRP'] || row['mrp'] || 0) || 0;
      const unit = String(row['Unit'] || row['unit'] || 'No.').trim();
      const hsn = String(row['HSN'] || row['hsn'] || '').trim();
      if (!mc) return null;
      return { materialCode: mc, description: desc, dp, mrp, unit, hsn };
    }).filter(Boolean);
    const updated = await bulkUpsertPriceList(items);
    return res.status(200).json({ success: true, count: items.length, total: updated.length });
  } catch(e) {
    return res.status(500).json({ error: 'Upload failed: ' + e.message });
  }
}
