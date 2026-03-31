export default async function handler(req, res) {
  if (req.method !== 'GET') return res.status(405).json({ error: 'Method not allowed' });
  try {
    const response = await fetch('https://services.gst.gov.in/services/captchavld.htm', {
      headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36' }
    });
    const text = await response.text();
    const match = text.match(/value="([^"]+)"/);
    const sessionId = match ? match[1] : '';
    const captchaResponse = await fetch('https://services.gst.gov.in/services/captchavld.htm?captchaSuggestion=' + sessionId, {
      headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36' }
    });
    const captchaText = await captchaResponse.text();
    return res.status(200).json({ sessionId, captcha: captchaText });
  } catch(e) {
    return res.status(500).json({ error: 'Failed to fetch captcha: ' + e.message });
  }
}
