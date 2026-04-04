/**
 * POST /api/submit
 * ----------------
 * Called by the technician's form when they tap "Send to Customer".
 * 
 * 1. Validates the payload
 * 2. Generates a one-time UUID token
 * 3. Stores the TCR data against that token (TTL: 30 min)
 * 4. Returns the WhatsApp deep link for the technician to open → send to customer
 * 
 * The technician's form then enters "PENDING" state.
 * They CANNOT approve — only the customer can via their own phone.
 */

import { v4 as uuidv4 } from 'uuid';
import { saveToken } from '../../lib/store';
import { buildWADeepLink } from '../../lib/whatsapp';

export default async function handler(req, res) {
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  try {
    const data = req.body;

    // ── Validate required fields ──
    const required = ['custName', 'mobile', 'callNo', 'serviceDate', 'techName', 'tonnage'];
    for (const field of required) {
      if (!data[field]) {
        return res.status(400).json({ error: `Missing required field: ${field}` });
      }
    }

    if (!/^\d{10}$/.test(data.mobile)) {
      return res.status(400).json({ error: 'Invalid mobile number' });
    }

    // ── Generate one-time token ──
    const token = uuidv4();

    // ── Build the confirmation URL (customer opens this on their phone) ──
    const baseUrl = process.env.NEXT_PUBLIC_BASE_URL || `https://${req.headers.host}`;
    const confirmUrl = `${baseUrl}/confirm/${token}`;

    // ── Store token + TCR data ──
    await saveToken(token, {
      ...data,
      confirmed: false,
      submittedAt: new Date().toISOString(),
      confirmUrl,
    });

    // ── Build WhatsApp deep link (technician opens this, taps Send) ──
    const waLink = buildWADeepLink(data.mobile, confirmUrl, data);

    return res.status(200).json({
      success: true,
      token,
      confirmUrl,
      waLink,
      message: 'TCR submitted. Open the WhatsApp link to send confirmation to customer.',
    });

  } catch (err) {
    console.error('Submit error:', err);
    return res.status(500).json({ error: 'Server error. Please try again.' });
  }
}
