/**
 * POST /api/confirm
 * -----------------
 * Called when the customer taps "Confirm & Approve" on their phone.
 * 
 * Security guarantees:
 * - Token is one-time UUID — cannot be guessed
 * - Token expires after 30 minutes
 * - Once confirmed, cannot be confirmed again
 * - No authentication is required from the technician side
 * - The technician's form polls /api/status to see when approved
 */

import { getToken, confirmToken } from '../../lib/store';

export default async function handler(req, res) {
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  const { token } = req.body;

  if (!token) {
    return res.status(400).json({ error: 'Token required' });
  }

  try {
    const data = await getToken(token);

    if (!data) {
      return res.status(404).json({
        error: 'expired',
        message: 'This confirmation link has expired or is invalid. Please ask the technician to resend.',
      });
    }

    if (data.confirmed) {
      return res.status(409).json({
        error: 'already_confirmed',
        message: 'This TCR has already been confirmed.',
        confirmedAt: data.confirmedAt,
      });
    }

    // ── Mark as confirmed ──
    const confirmed = await confirmToken(token);

    return res.status(200).json({
      success: true,
      confirmedAt: confirmed.confirmedAt,
      callNo: confirmed.callNo,
      custName: confirmed.custName,
      total: confirmed.total,
    });

  } catch (err) {
    console.error('Confirm error:', err);
    return res.status(500).json({ error: 'Server error' });
  }
}
