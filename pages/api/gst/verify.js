export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });
  const { sessionId, gstin, captcha } = req.body;
  if (!sessionId || !gstin || !captcha) return res.status(400).json({ error: 'Missing sessionId, gstin or captcha' });
  try {
    const response = await fetch('https://services.gst.gov.in/services/searchtp', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
      },
      body: new URLSearchParams({
        captcha: captcha,
        scope: ' taxpayerDet',
        'gstin': gstin,
        'searchBtn': 'Go'
      }).toString()
    });
    const text = await response.text();
    if (text.includes('Invalid Captcha') || text.includes('incorrect')) {
      return res.status(400).json({ error: 'Invalid captcha. Please try again.' });
    }
    const tradeName = extractField(text, ['Trade Name', 'trade_nm', 'tradeNam']) || '';
    const legalName = extractField(text, ['Legal Name', 'legal_nm', 'lgnm']) || '';
    const address = extractField(text, ['Address', 'adr', 'pradr']) || '';
    const status = extractField(text, ['Status', 'sts']) || '';
    const registrationDate = extractField(text, ['Registration Date', 'rgdt']) || '';
    return res.status(200).json({ gstin, tradeName, legalName, address, status, registrationDate });
  } catch(e) {
    return res.status(500).json({ error: 'Verification failed: ' + e.message });
  }
}

function extractField(html, keys) {
  for (const k of keys) {
    const re = new RegExp('<' + k + '[^>]*>([^<]+)<\\/' + k + '>', 'i');
    const m = html.match(re);
    if (m && m[1]) return m[1].trim();
    const re2 = new RegExp(k + '[=:][\\s"\']*([^"\'<\\n,]+)', 'i');
    const m2 = html.match(re2);
    if (m2 && m2[1]) return m2[1].trim();
  }
  return '';
}
