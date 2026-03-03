import { Redis } from '@upstash/redis';
const redis = new Redis({
  url: process.env.KV_REST_API_URL,
  token: process.env.KV_REST_API_TOKEN,
});
const TTL = 30 * 60;

export async function saveToken(token, data) {
  await redis.set(`tcr:${token}`, { ...data, createdAt: Date.now() }, { ex: TTL });
  return true;
}

export async function getToken(token) {
  const data = await redis.get(`tcr:${token}`);
  return data || null;
}

export async function confirmToken(token) {
  const data = await redis.get(`tcr:${token}`);
  if (!data) return null;
  const confirmed = { ...data, confirmed: true, confirmedAt: new Date().toISOString() };
  await redis.set(`tcr:${token}`, confirmed, { ex: TTL });
  return confirmed;
}

export async function deleteToken(token) {
  await redis.del(`tcr:${token}`);
}
