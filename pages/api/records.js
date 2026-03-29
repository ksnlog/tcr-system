import fs from 'fs';
import path from 'path';

const DATA_DIR = path.join(process.cwd(), 'data');
const FILE = path.join(DATA_DIR, 'tcr_records.json');

function readRecords() {
  try {
    if (!fs.existsSync(DATA_DIR)) fs.mkdirSync(DATA_DIR, { recursive: true });
    if (!fs.existsSync(FILE)) return [];
    return JSON.parse(fs.readFileSync(FILE, 'utf8'));
  } catch (e) { return []; }
}

function writeRecords(records) {
  fs.writeFileSync(FILE, JSON.stringify(records, null, 2));
}

export default function handler(req, res) {
  if (req.method === 'POST') {
    try {
      const records = readRecords();
      const record = { ...req.body, confirmedAt: new Date().toISOString() };
      // Avoid duplicates by Job No.
      const already = records.find(r => r.callNo === record.callNo);
      if (!already) records.push(record);
      writeRecords(records);
      return res.json({ ok: true });
    } catch (e) { return res.status(500).json({ error: 'Failed to save record' }); }
  }

  if (req.method === 'GET') {
    try {
      const records = readRecords();
      const { techId } = req.query;
      if (techId) return res.json(records.filter(r => r.techName === techId));
      return res.json(records);
    } catch (e) { return res.status(500).json({ error: 'Failed to read records' }); }
  }

  res.status(405).json({ error: 'Method not allowed' });
}
