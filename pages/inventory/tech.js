import { useState } from 'react';
export default function TechInventory() {
  const [techId, setTechId] = useState('');
  const [authed, setAuthed] = useState(false);
  const [data, setData] = useState(null);
  const [err, setErr] = useState('');
  const [loading, setLoading] = useState(false);
  async function login() {
    if (!techId.trim()) return setErr('Enter your Technician ID');
    setLoading(true); setErr('');
    try {
      const res = await fetch('/api/inventory/stock?techId='+techId.trim().toUpperCase());
      const json = await res.json();
      if (json.error) { setErr('Invalid Technician ID'); setLoading(false); return; }
      setData(json); setAuthed(true);
    } catch(e) { setErr('Network error'); }
    setLoading(false);
  }
  async function refresh() {
    setLoading(true);
    const res = await fetch('/api/inventory/stock?techId='+techId.trim().toUpperCase());
    const json = await res.json();
    setData(json); setLoading(false);
  }
  const fmt = n => Number(n||0).toLocaleString('en-IN');
  if (!authed) return (
    <div style={{minHeight:'100vh',background:'#F3F4F6',display:'flex',alignItems:'center',justifyContent:'center',padding:16}}>
      <div style={{background:'white',borderRadius:16,padding:32,width:'100%',maxWidth:360,boxShadow:'0 4px 20px rgba(0,0,0,.1)',textAlign:'center'}}>
        <div style={{fontSize:36,marginBottom:12}}>🔧</div>
        <div style={{fontSize:18,fontWeight:700,marginBottom:4}}>My Inventory</div>
        <div style={{fontSize:12,color:'#6B7280',marginBottom:24}}>Enter your Technician ID</div>
        <input value={techId} onChange={e=>setTechId(e.target.value.toUpperCase())} onKeyDown={e=>e.key==='Enter'&&login()} placeholder="e.g. TECH001"
          style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center',letterSpacing:2,fontFamily:'monospace'}}/>
        {err&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{err}</div>}
        <button onClick={login} disabled={loading} style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer'}}>
          {loading?'Loading...':'View My Stock'}
        </button>
      </div>
    </div>
  );
  const { tech, stock, materials } = data||{};
  const zeroItems=(materials||[]).filter(m=>(stock[m.id]||0)===0);
  return (
    <div style={{minHeight:'100vh',background:'#F3F4F6',padding:'12px 8px 40px'}}>
      <div style={{maxWidth:480,margin:'0 auto'}}>
        <div style={{background:'linear-gradient(135deg,#E8001D,#9B0013)',borderRadius:14,padding:'14px 16px',color:'white',marginBottom:12}}>
          <div style={{display:'flex',justifyContent:'space-between',alignItems:'center'}}>
            <div>
              <div style={{fontSize:14,fontWeight:700}}>🔧 {tech?.name}</div>
              <div style={{fontSize:10,color:'rgba(255,255,255,.6)',marginTop:2,fontFamily:'monospace'}}>ID: {tech?.id}</div>
            </div>
            <button onClick={refresh} style={{background:'rgba(255,255,255,.2)',border:'none',color:'white',padding:'6px 12px',borderRadius:8,fontSize:11,cursor:'pointer'}}>
              {loading?'...':'↻ Refresh'}
            </button>
          </div>
        </div>
        {zeroItems.length>0&&(
          <div style={{background:'#FEF2F2',border:'1px solid #FECACA',borderRadius:10,padding:'10px 12px',marginBottom:10}}>
            <div style={{fontSize:12,fontWeight:700,color:'#DC2626',marginBottom:4}}>⚠️ Zero Stock Alert</div>
            {zeroItems.map(m=><div key={m.id} style={{fontSize:11,color:'#DC2626'}}>• {m.name}{m.sub?' ('+m.sub+')':''} — 0 {m.unit}</div>)}
          </div>
        )}
        <div style={{background:'white',borderRadius:12,overflow:'hidden',boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
          <div style={{background:'#111827',padding:'8px 14px',display:'grid',gridTemplateColumns:'1fr 80px 70px'}}>
            <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)'}}>MATERIAL</div>
            <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)',textAlign:'center'}}>STOCK</div>
            <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)',textAlign:'right'}}>UNIT</div>
          </div>
          {(materials||[]).map(m=>{
            const qty=(stock||{})[m.id]||0; const zero=qty===0;
            return (
              <div key={m.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 70px',padding:'10px 14px',borderBottom:'1px solid #F3F4F6',background:zero?'#FFF5F5':'white',alignItems:'center'}}>
                <div>
                  <div style={{fontSize:12,fontWeight:500,color:zero?'#DC2626':'#111827'}}>{m.name}</div>
                  {m.sub&&<div style={{fontSize:10,color:'#6B7280'}}>{m.sub}</div>}
                </div>
                <div style={{textAlign:'center',fontFamily:'monospace',fontSize:14,fontWeight:700,color:zero?'#DC2626':'#16A34A'}}>{zero?'⚠️ 0':qty}</div>
                <div style={{textAlign:'right',fontSize:11,color:'#6B7280'}}>{m.unit}</div>
              </div>
            );
          })}
        </div>
        <div style={{textAlign:'center',marginTop:16,fontSize:10,color:'#9CA3AF'}}>Stock deducts automatically when TCR is submitted</div>
      </div>
    </div>
  );
}
