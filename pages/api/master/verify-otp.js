// pages/api/master/verify-otp.js
import { Redis } from '@upstash/redis';
import bcrypt from 'bcryptjs';

const kv = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});

export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });

  const { otp, newPassword } = req.body;
  if (!otp || !newPassword) return res.status(400).json({ error: 'OTP and new password required' });
  if (newPassword.length < 6) return res.status(400).json({ error: 'Password must be at least 6 characters' });

  const savedOtp = await kv.get('master:otp');
  if (!savedOtp) return res.status(400).json({ error: 'OTP expired. Please request a new one.' });
  if (savedOtp !== otp) return res.status(400).json({ error: 'Incorrect OTP. Try again.' });

  // Hash and save new password
  const hashed = await bcrypt.hash(newPassword, 10);
  await kv.set('master:password', hashed);
  await kv.del('master:otp'); // OTP used — delete it

  return res.status(200).json({ success: true });
}
