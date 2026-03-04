import { Redis } from '@upstash/redis';
const redis = new Redis({ url: process.env.KV_REST_API_URL, token: process.env.KV_REST_API_TOKEN });

export const DEFAULT_MATERIALS = [
  { id:'copper_pair_14_12', name:'Copper Pipe', sub:'1/4 & 1/2', unit:'Ft', costPrice:0, sellingRate:259 },
  { id:'copper_pair_38_58', name:'Copper Pipe', sub:'3/8 & 5/8', unit:'Ft', costPrice:0, sellingRate:290 },
  { id:'copper_pair_12_58', name:'Copper Pipe', sub:'1/2 & 5/8', unit:'Ft', costPrice:0, sellingRate:290 },
  { id:'elec_cable',        name:'Electrical Cable', sub:null, unit:'Ft',   costPrice:0, sellingRate:37  },
  { id:'drain_pipe',        name:'Drain Pipe',        sub:null, unit:'Ft',   costPrice:0, sellingRate:30  },
  { id:'odu_stand',         name:'ODU Stand',         sub:null, unit:'No.',  costPrice:0, sellingRate:750 },
  { id:'dismantling',       name:'Dismantling',       sub:null, unit:'No.',  costPrice:0, sellingRate:750 },
  { id:'wrapping_tape',     name:'Wrapping Tape',     sub:null, unit:'Ft',   costPrice:0, sellingRate:24  },
  { id:'rubber_pad',        name:'Rubber Pad',        sub:null, unit:'Unit', costPrice:0, sellingRate:200 },
  { id:'plug_top',          name:'Plug Top',          sub:null, unit:'Unit', costPrice:0, sellingRate:150 },
];

export async function getMaterials() { try { return await redis.get('inv:materials') || DEFAULT_MATERIALS; } catch(e) { return DEFAULT_MATERIALS; } }
export async function saveMaterials(m) { await redis.set('inv:materials', m); }
export async function getTechnicians() { try { return await redis.get('inv:technicians') || []; } catch(e) { return []; } }
export async function saveTechnicians(t) { await redis.set('inv:technicians', t); }
export async function getStock(techId) { try { return await redis.get('inv:stock:'+techId) || {}; } catch(e) { return {}; } }
export async function setStock(techId, stock) { await redis.set('inv:stock:'+techId, stock); }
export async function addStock(techId, materialId, qty, costPrice) {
  const stock = await getStock(techId);
  stock[materialId] = (stock[materialId] || 0) + qty;
  await setStock(techId, stock);
  await logHistory(techId, { type:'add', materialId, qty, costPrice, at: new Date().toISOString() });
}
export async function deductStock(techId, materialId, qty, jobNo) {
  const stock = await getStock(techId);
  stock[materialId] = Math.max(0, (stock[materialId]||0) - qty);
  await setStock(techId, stock);
  await logHistory(techId, { type:'deduct', materialId, qty, jobNo, at: new Date().toISOString() });
  return stock[materialId];
}
async function logHistory(techId, event) {
  try {
    const h = await redis.get('inv:history:'+techId) || [];
    h.unshift(event);
    await redis.set('inv:history:'+techId, h.slice(0,200));
  } catch(e) {}
}
export async function getHistory(techId) { try { return await redis.get('inv:history:'+techId) || []; } catch(e) { return []; } }
