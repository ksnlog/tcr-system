// pages/api/master/send-otp.js
import { Redis } from '@upstash/redis';
import nodemailer from 'nodemailer';

const kv = new Redis({
  url: process.env.UPSTASH_REDIS_REST_URL,
  token: process.env.UPSTASH_REDIS_REST_TOKEN,
});

export default async function handler(req, res) {
  if (req.method !== 'POST') return res.status(405).json({ error: 'Method not allowed' });

  const masterEmail = process.env.MASTER_EMAIL;
  if (!masterEmail) return res.status(500).json({ error: 'MASTER_EMAIL env variable not configured' });

  if (!process.env.GMAIL_USER || !process.env.GMAIL_APP_PASSWORD) {
    return res.status(500).json({ error: 'Gmail credentials not configured' });
  }

  try {
    // Generate 6-digit OTP
    const otp = Math.floor(100000 + Math.random() * 900000).toString();

    // Save OTP in Redis with 10 min TTL
    await kv.set('master:otp', otp, { ex: 600 });

    // Send email via Gmail
    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: process.env.GMAIL_USER,
        pass: process.env.GMAIL_APP_PASSWORD, // Must be a Gmail App Password, NOT your Gmail login password
      },
    });

    // Verify transporter config before sending
    await transporter.verify();

    await transporter.sendMail({
      from: `"GENERAL HVAC TCR System" <${process.env.GMAIL_USER}>`,
      to: masterEmail,
      subject: 'Master Login OTP — GENERAL HVAC TCR System',
      html: `
        <div style="font-family:sans-serif;max-width:400px;margin:0 auto;padding:32px;background:#f9f9f9;border-radius:12px;">
          <h2 style="color:#E8001D;margin-bottom:8px;">🔐 Master Login OTP</h2>
          <p style="color:#444;">Your one-time password to access the TCR Inventory Master:</p>
          <div style="font-size:36px;font-weight:bold;letter-spacing:10px;color:#111;text-align:center;padding:24px;background:white;border-radius:10px;margin:20px 0;">${otp}</div>
          <p style="color:#888;font-size:13px;">This OTP expires in <strong>10 minutes</strong>. Do not share it with anyone.</p>
          <p style="color:#888;font-size:12px;">— GENERAL HVAC Solutions India Pvt Ltd</p>
        </div>
      `,
    });

    return res.status(200).json({ success: true });

  } catch (err) {
    console.error('send-otp error:', err.message);
    return res.status(500).json({ error: 'Failed to send OTP. ' + err.message });
  }
}
