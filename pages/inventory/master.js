import { useState, useEffect } from 'react';
const MASTER_PWD = 'Project@1';
export default function MasterInventory() {
  const [authed, setAuthed] = useState(false);
  const [pwd, setPwd] = useState('');
  const [pwdErr, setPwdErr] = useState('');
  const [tab, setTab] = useState('stock');
  const [techs, setTechs] = useState([]);
  const [materials, setMaterials] = useState([]);
  const [allStock, setAllStock] = useState({});
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState('');
  const [newTechId, setNewTechId] = useState('');
  const [newTechName, setNewTechName] = useState('');
  const [selTech, setSelTech] = useState('');
  const [selMat, setSelMat] = useState('');
  const [addQty, setAddQty] = useState('');
  const [addCost, setAddCost] = useState('');
  const [editMats, setEditMats] = useState([]);
  useEffect(() => { if (authed) loadAll(); }, [authed]);
  async function loadAll() {
    setLoading(true);
    try {
      const res = await fetch('/api/inventory/stock?password='+MASTER_PWD);
      const json = await res.json();
      setTechs(json.techs||[]); setMaterials(json.materials||[]); setAllStock(json.allStock||{}); setEditMats(json.materials||[]);
    } catch(e) { setMsg('Error loading data'); }
    setLoading(false);
  }
  function login() { if (pwd===MASTER_PWD) { setAuthed(true); setPwdErr(''); } else setPwdErr('Incorrect password'); }
  async function addTechnician() {
    if (!newTechId||!newTechName) return setMsg('Fill both ID and Name');
    const res = await fetch('/api/inventory/technicians',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:MASTER_PWD,action:'add',techId:newTechId.toUpperCase(),techName:newTechName})});
    const json = await res.json();
    if (json.error) return setMsg(json.error);
    setTechs(json.techs); setNewTechId(''); setNewTechName('');
    setMsg('Technician added!'); setTimeout(()=>setMsg(''),3000);
  }
  async function removeTechnician(techId) {
    if (!confirm('Remove '+techId+'?')) return;
    const res = await fetch('/api/inventory/technicians',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:MASTER_PWD,action:'remove',techId})});
    const json = await res.json();
    setTechs(json.techs); setMsg('Removed'); setTimeout(()=>setMsg(''),3000);
  }
  async function addStockSubmit() {
    if (!selTech||!selMat||!addQty) return setMsg('Fill all fields');
    const res = await fetch('/api/inventory/stock',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:MASTER_PWD,techId:selTech,materialId:selMat,qty:addQty,costPrice:addCost})});
    const json = await res.json();
    if (json.error) return setMsg(json.error);
    const r2 = await fetch('/api/inventory/stock?password='+MASTER_PWD);
    const j2 = await r2.json();
    setAllStock(j2.allStock||{});
    setSelTech(''); setSelMat(''); setAddQty(''); setAddCost('');
    setMsg('Stock added!'); setTimeout(()=>setMsg(''),3000);
  }
  async function saveMaterialPrices() {
    const res = await fetch('/api/inventory/materials',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:MASTER_PWD,materials:editMats})});
    const json = await res.json();
    if (json.error) return setMsg(json.error);
    setMaterials(editMats); setMsg('Prices saved!'); setTimeout(()=>setMsg(''),3000);
  }
  function getStockVal(techId) {
    const stock=allStock[techId]||{};
    let val=0; materials.forEach(m=>{ val+=(stock[m.id]||0)*m.costPrice; }); return val;
  }
  const fmt = n => Number(n||0).toLocaleString('en-IN');
  if (!authed) return (
    <div style={{minHeight:'100vh',background:'#F3F4F6',display:'flex',alignItems:'center',justifyContent:'center',padding:16}}>
      <div style={{background:'white',borderRadius:16,padding:32,width:'100%',maxWidth:360,boxShadow:'0 4px 20px rgba(0,0,0,.1)',textAlign:'center'}}>
        <div style={{fontSize:36,marginBottom:12}}>📦</div>
        <div style={{fontSize:18,fontWeight:700,marginBottom:4}}>Inventory Master</div>
        <div style={{fontSize:12,color:'#6B7280',marginBottom:24}}>GENERAL HVAC Solutions</div>
        <input type="password" value={pwd} onChange={e=>setPwd(e.target.value)} onKeyDown={e=>e.key==='Enter'&&login()} placeholder="Master password" style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center'}}/>
        {pwdErr&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{pwdErr}</div>}
        <button onClick={login} style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer'}}>Login</button>
      </div>
    </div>
  );
  return (
    <div style={{minHeight:'100vh',background:'#F3F4F6',padding:'12px 8px 40px'}}>
      <div style={{maxWidth:720,margin:'0 auto'}}>
        <div style={{background:'linear-gradient(135deg,#E8001D,#9B0013)',borderRadius:14,padding:'14px 16px',color:'white',marginBottom:12}}>
          <div style={{fontSize:14,fontWeight:700}}>📦 Inventory Master Dashboard</div>
          <div style={{fontSize:10,color:'rgba(255,255,255,.6)',marginTop:2}}>GENERAL HVAC Solutions India Pvt Ltd</div>
        </div>
        {msg&&<div style={{background:'#F0FDF4',border:'1px solid #BBF7D0',borderRadius:8,padding:'8px 12px',fontSize:12,color:'#166534',marginBottom:10}}>{msg}</div>}
        <div style={{display:'flex',gap:6,marginBottom:12}}>
          {[['stock','📊 Stock'],['technicians','👷 Technicians'],['materials','🏷️ Prices']].map(([k,l])=>(
            <button key={k} onClick={()=>setTab(k)} style={{flex:1,padding:'9px 4px',border:'none',borderRadius:10,fontFamily:'inherit',fontSize:12,fontWeight:600,cursor:'pointer',background:tab===k?'white':'rgba(255,255,255,.5)',color:tab===k?'#E8001D':'#6B7280',boxShadow:tab===k?'0 2px 8px rgba(0,0,0,.1)':'none'}}>{l}</button>
          ))}
        </div>
        {loading&&<div style={{textAlign:'center',padding:40,color:'#6B7280'}}>Loading...</div>}
        {!loading&&tab==='stock'&&(
          <div>
            <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
              <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Add Stock to Technician</div>
              <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:8}}>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Technician</label>
                  <select value={selTech} onChange={e=>setSelTech(e.target.value)} style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}>
                    <option value="">Select...</option>
                    {techs.map(t=><option key={t.id} value={t.id}>{t.id} - {t.name}</option>)}
                  </select></div>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Material</label>
                  <select value={selMat} onChange={e=>setSelMat(e.target.value)} style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}>
                    <option value="">Select...</option>
                    {materials.map(m=><option key={m.id} value={m.id}>{m.name}{m.sub?' ('+m.sub+')':''}</option>)}
                  </select></div>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Qty</label>
                  <input type="number" value={addQty} onChange={e=>setAddQty(e.target.value)} placeholder="0" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Cost Price (Rs.)</label>
                  <input type="number" value={addCost} onChange={e=>setAddCost(e.target.value)} placeholder="0" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
              </div>
              <button onClick={addStockSubmit} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>Add Stock</button>
            </div>
            {techs.map(t=>{
              const stock=allStock[t.id]||{};
              const totalVal=getStockVal(t.id);
              const zeroItems=materials.filter(m=>(stock[m.id]||0)===0);
              return (
                <div key={t.id} style={{background:'white',borderRadius:12,padding:14,marginBottom:10,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                  <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',marginBottom:8}}>
                    <div><div style={{fontSize:13,fontWeight:700}}>{t.name}</div><div style={{fontSize:10,color:'#6B7280'}}>ID: {t.id}</div></div>
                    <div style={{textAlign:'right'}}><div style={{fontSize:13,fontWeight:700,color:'#E8001D'}}>Rs.{fmt(totalVal)}</div><div style={{fontSize:9,color:'#6B7280'}}>Stock Value</div></div>
                  </div>
                  {zeroItems.length>0&&<div style={{background:'#FEF2F2',border:'1px solid #FECACA',borderRadius:6,padding:'5px 8px',fontSize:10,color:'#DC2626',marginBottom:8}}>🔴 Zero Stock: {zeroItems.map(m=>m.name+(m.sub?' ('+m.sub+')':'')).join(', ')}</div>}
                  <table style={{width:'100%',borderCollapse:'collapse',fontSize:11}}>
                    <thead><tr style={{background:'#F9FAFB'}}><th style={{padding:'5px 8px',textAlign:'left',fontWeight:600,fontSize:10,color:'#6B7280'}}>Material</th><th style={{padding:'5px 8px',textAlign:'center',fontWeight:600,fontSize:10,color:'#6B7280'}}>Stock</th><th style={{padding:'5px 8px',textAlign:'right',fontWeight:600,fontSize:10,color:'#6B7280'}}>Value</th></tr></thead>
                    <tbody>
                      {materials.map(m=>{
                        const qty=stock[m.id]||0; const val=qty*m.costPrice; const zero=qty===0;
                        return (<tr key={m.id} style={{borderTop:'1px solid #F3F4F6',background:zero?'#FFF5F5':'white'}}>
                          <td style={{padding:'5px 8px',color:zero?'#DC2626':'#111827'}}>{m.name}{m.sub?<span style={{fontSize:9,color:'#6B7280',marginLeft:4}}>({m.sub})</span>:null}</td>
                          <td style={{padding:'5px 8px',textAlign:'center',fontFamily:'monospace',fontWeight:600,color:zero?'#DC2626':'#111827'}}>{zero?'⚠️ 0':qty} {m.unit}</td>
                          <td style={{padding:'5px 8px',textAlign:'right',fontFamily:'monospace',color:'#6B7280'}}>Rs.{fmt(val)}</td>
                        </tr>);
                      })}
                    </tbody>
                  </table>
                </div>
              );
            })}
          </div>
        )}
        {!loading&&tab==='technicians'&&(
          <div>
            <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
              <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Add Technician</div>
              <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:8}}>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Tech ID</label>
                  <input value={newTechId} onChange={e=>setNewTechId(e.target.value)} placeholder="e.g. TECH001" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Full Name</label>
                  <input value={newTechName} onChange={e=>setNewTechName(e.target.value)} placeholder="Technician name" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
              </div>
              <button onClick={addTechnician} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>Add Technician</button>
            </div>
            <div style={{background:'white',borderRadius:12,padding:14,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
              <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>👷 All Technicians ({techs.length})</div>
              {techs.length===0&&<div style={{color:'#9CA3AF',fontSize:12,textAlign:'center',padding:20}}>No technicians added yet</div>}
              {techs.map(t=>(
                <div key={t.id} style={{display:'flex',justifyContent:'space-between',alignItems:'center',padding:'9px 0',borderBottom:'1px solid #F3F4F6'}}>
                  <div><div style={{fontSize:13,fontWeight:600}}>{t.name}</div><div style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>ID: {t.id}</div></div>
                  <button onClick={()=>removeTechnician(t.id)} style={{padding:'4px 10px',background:'#FEF2F2',color:'#DC2626',border:'1px solid #FECACA',borderRadius:6,fontSize:11,cursor:'pointer'}}>Remove</button>
                </div>
              ))}
            </div>
          </div>
        )}
        {!loading&&tab==='materials'&&(
          <div style={{background:'white',borderRadius:12,padding:14,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
            <div style={{fontSize:12,fontWeight:700,marginBottom:4}}>🏷️ Material Prices</div>
            <div style={{fontSize:11,color:'#6B7280',marginBottom:12}}>Set cost price (what you pay) and selling rate (what customer pays)</div>
            <div style={{display:'grid',gridTemplateColumns:'1fr 80px 80px',gap:8,marginBottom:6}}>
              <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase'}}>Material</div>
              <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Cost Price</div>
              <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Sell Rate</div>
            </div>
            {editMats.map((m,i)=>(
              <div key={m.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 80px',gap:8,alignItems:'center',padding:'7px 0',borderBottom:'1px solid #F3F4F6'}}>
                <div style={{fontSize:12,fontWeight:500}}>{m.name}{m.sub?<span style={{fontSize:10,color:'#6B7280'}}> ({m.sub})</span>:null}<span style={{fontSize:9,color:'#9CA3AF',marginLeft:4}}>{m.unit}</span></div>
                <input type="number" value={m.costPrice} placeholder="Cost" onChange={e=>{const n=[...editMats];n[i]={...n[i],costPrice:parseFloat(e.target.value)||0};setEditMats(n);}} style={{padding:'5px 6px',border:'1.5px solid #E5E7EB',borderRadius:6,fontSize:11,outline:'none',textAlign:'center'}}/>
                <input type="number" value={m.sellingRate} placeholder="Rate" onChange={e=>{const n=[...editMats];n[i]={...n[i],sellingRate:parseFloat(e.target.value)||0};setEditMats(n);}} style={{padding:'5px 6px',border:'1.5px solid #E5E7EB',borderRadius:6,fontSize:11,outline:'none',textAlign:'center'}}/>
              </div>
            ))}
            <button onClick={saveMaterialPrices} style={{width:'100%',padding:'10px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer',marginTop:12}}>Save Prices</button>
          </div>
        )}
      </div>
    </div>
  );
}
