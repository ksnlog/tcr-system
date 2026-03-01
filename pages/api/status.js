/**
 * GET /api/status?token=xxx
 * -------------------------
 * Polled by the technician's form every 5 seconds after submission.
 * Returns whether the customer has confirmed yet.
 * 
 * Returns full TCR data only after confirmation — so even if a technician
 * intercepts the poll response before confirmation, there's nothing useful.
 */

import { getToken } from '../../lib/store';

export default async function handler(req, res) {
  if (req.method !== 'GET') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  const { token } = req.query;

  if (!token) {
    return res.status(400).json({ error: 'Token required' });
  }

  try {
    const data = await getToken(token);

    if (!data) {
      return res.status(404).json({ status: 'expired' });
    }

    if (data.confirmed) {
      return res.status(200).json({
        status: 'confirmed',
        confirmedAt: data.confirmedAt,
        callNo: data.callNo,
        custName: data.custName,
        total: data.total,
        // Full data for PDF generation on technician side
        fullData: data,
      });
    }

    // Still pending
    const elapsed = Math.floor((Date.now() - data.createdAt) / 1000);
    const remaining = Math.max(0, 1800 - elapsed);

    return res.status(200).json({
      status: 'pending',
      remaining, // seconds until expiry
    });

  } catch (err) {
    console.error('Status error:', err);
    return res.status(500).json({ error: 'Server error' });
  }
}
