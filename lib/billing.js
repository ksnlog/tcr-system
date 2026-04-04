import { Redis } from '@upstash/redis';
import { v4 as uuidv4 } from 'uuid';
const redis = new Redis({ url: process.env.KV_REST_API_URL, token: process.env.KV_REST_API_TOKEN });

const INVOICES_KEY = 'inv:invoices';
const INVOICE_PREFIX = 'inv:invoice:';

export async function getAllInvoices() {
  try {
    const ids = await redis.lrange(INVOICES_KEY, 0, -1) || [];
    if (ids.length === 0) return [];
    const invoices = await Promise.all(ids.map(id => redis.get(INVOICE_PREFIX + id)));
    return invoices.filter(Boolean).reverse();
  } catch(e) { return []; }
}

export async function getInvoice(id) {
  try {
    return await redis.get(INVOICE_PREFIX + id);
  } catch(e) { return null; }
}

export async function saveInvoice(invoice) {
  const id = invoice.id || uuidv4();
  const invoiceWithId = { ...invoice, id };
  await redis.set(INVOICE_PREFIX + id, invoiceWithId);
  await redis.lpush(INVOICES_KEY, id);
  return invoiceWithId;
}

export async function updateInvoice(id, updates) {
  const existing = await getInvoice(id);
  if (!existing) return null;
  const updated = { ...existing, ...updates, id };
  await redis.set(INVOICE_PREFIX + id, updated);
  return updated;
}
