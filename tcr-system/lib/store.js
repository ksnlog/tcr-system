/**
 * TOKEN STORE
 * -----------
 * Stores pending TCR confirmations keyed by a one-time UUID token.
 * 
 * FREE TIER SETUP:
 * - Default: In-memory (works on single Vercel instance, resets on redeploy)
 * - Recommended: Vercel KV (free tier: 30k requests/month)
 *   1. Go to Vercel Dashboard → Storage → Create KV Database
 *   2. Link to your project → env vars auto-populate
 *   3. Uncomment the KV section below
 */

// ─── IN-MEMORY STORE (default, no setup needed) ─────────────────────────────
const store = new Map();

export async function saveToken(token, data) {
  store.set(token, { ...data, createdAt: Date.now() });
  // Auto-expire after 30 minutes
  setTimeout(() => store.delete(token), 30 * 60 * 1000);
  return true;
}

export async function getToken(token) {
  const data = store.get(token);
  if (!data) return null;
  // Check expiry (30 min)
  if (Date.now() - data.createdAt > 30 * 60 * 1000) {
    store.delete(token);
    return null;
  }
  return data;
}

export async function confirmToken(token, location) {
  const data = store.get(token);
  if (!data) return null;
  const confirmed = { ...data, confirmed: true, confirmedAt: new Date().toISOString(), location: location || null };
  store.set(token, confirmed);
  return confirmed;
}

export async function deleteToken(token) {
  store.delete(token);
}


// ─── VERCEL KV STORE (recommended for production) ───────────────────────────
// Uncomment this block and comment out the in-memory block above
// Install: npm install @vercel/kv
/*
import { kv } from '@vercel/kv';
const TTL = 30 * 60; // 30 minutes in seconds

export async function saveToken(token, data) {
  await kv.set(`tcr:${token}`, { ...data, createdAt: Date.now() }, { ex: TTL });
  return true;
}

export async function getToken(token) {
  const data = await kv.get(`tcr:${token}`);
  return data || null;
}

export async function confirmToken(token) {
  const data = await kv.get(`tcr:${token}`);
  if (!data) return null;
  const confirmed = { ...data, confirmed: true, confirmedAt: new Date().toISOString() };
  await kv.set(`tcr:${token}`, confirmed, { ex: TTL });
  return confirmed;
}

export async function deleteToken(token) {
  await kv.del(`tcr:${token}`);
}
*/
