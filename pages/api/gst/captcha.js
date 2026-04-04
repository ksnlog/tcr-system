export default async function handler(req, res) {
  /**
   * GST Captcha API - DEPRECATED
   * ----------------------------
   * Moving to "Option B" — simple JSON API (no captcha needed).
   * Returning success to avoid breaking frontend calls.
   */
  return res.status(200).json({ 
    success: true, 
    message: 'Captcha not required for this verification method.',
    sessionId: 'session_na',
    captcha: '' // Empty captcha = frontend hides the box
  });
}
