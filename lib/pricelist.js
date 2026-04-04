import { Redis } from '@upstash/redis';
const redis = new Redis({ url: process.env.KV_REST_API_URL, token: process.env.KV_REST_API_TOKEN });

const KEY = 'inv:priceList';

export async function getPriceList() {
  try {
    const data = await redis.get(KEY);
    return data || [];
  } catch(e) { return []; }
}

export async function savePriceList(items) {
  await redis.set(KEY, items);
}

export async function upsertPriceListItem(item) {
  const list = await getPriceList();
  const idx = list.findIndex(x => x.materialCode === item.materialCode);
  if (idx >= 0) list[idx] = { ...list[idx], ...item };
  else list.push(item);
  await savePriceList(list);
  return list;
}

export async function bulkUpsertPriceList(items) {
  const list = await getPriceList();
  items.forEach(item => {
    const idx = list.findIndex(x => x.materialCode === item.materialCode);
    if (idx >= 0) list[idx] = { ...list[idx], ...item };
    else list.push(item);
  });
  await savePriceList(list);
  return list;
}
