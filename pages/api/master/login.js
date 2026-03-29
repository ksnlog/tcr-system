// pages/api/master/login.js
import { Redis } from '@upstash/redis';
import bcrypt from 'bcryptjs';

const kv = new Redis({
  url: process.env.UPSTASH_REDIS_REST_URL,
  token: process.env.UPSTASH_REDIS_REST_TOKEN,
});

const LEGACY_PWD = 'Project@1'; // accepted only if no password set yet in DB

export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });

  const { password } = req.body;
  if (!password) return res.status(400).json({ error: 'Password required' });

  const hashed = await kv.get('master:password');

  // If no password set yet, accept legacy hardcoded one
  if (!hashed) {
    if (password === LEGACY_PWD) return res.status(200).json({ success: true });
    return res.status(401).json({ error: 'Incorrect password' });
  }

  const match = await bcrypt.compare(password, hashed);
  if (!match) return res.status(401).json({ error: 'Incorrect password' });

  return res.status(200).json({ success: true });
}
