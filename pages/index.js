import Head from 'next/head';
import { useState, useEffect, useRef } from 'react';

const additionalItems = () => [
  {no:"2.1", desc:"Copper pipe With Insulation - Supply & Labour, Upto 2 Ton",       rate:259,  unit:"Ft", group:"low",  qty:0},
  {no:"2.2", desc:"Copper pipe With Insulation - Supply & Labour, 2.5/3/4 Ton",      rate:290,  unit:"Ft", group:"high", qty:0},
  {no:"3",   desc:"Supply and laying of Electrical 3/4 Core Cable",                  rate:37,   unit:"Ft", group:"all",  qty:0},
  {no:"4",   desc:"ODU Stand Supply and Fixing / Only Fixing",                        rate:750,  unit:"No.", group:"all",  qty:0},
  {no:"5",   desc:"Drain Pipe supply and fixing",                                     rate:30,   unit:"Ft", group:"all",  qty:0},
  {no:"6",   desc:"Dismantling of OLD / Existing Split AC Unit",                      rate:750,  unit:"No.", group:"all",  qty:0},
];
const actCatalog = [
  {desc:"Wall Chiseling / Chipping",          unit:"Ft",   rate:0},
  {desc:"Beam / Concrete Wall Core Drilling", unit:"No.",  rate:0},
  {desc:"Nitrogen flushing",                  unit:"Unit", rate:600},
  {desc:"Miscellaneous (Please specify)",     unit:"No.",  rate:0},
  {desc:"Wrapping Tape",                      unit:"Ft",   rate:24},
  {desc:"Rubber Pad",                         unit:"Unit", rate:200},
  {desc:"Plug Top",                           unit:"Unit", rate:150},
];
const actualItems = () => [];
const tonOptions = [
  {v:"1.0",    l:"1.0 Ton"},
  {v:"1.5",    l:"1.5 Ton"},
  {v:"2.0",    l:"2.0 Ton"},
  {v:"2.0+",   l:"Above 2 Ton"},
  {v:"mix",    l:"Multiple Mix"},
  {v:"window", l:"Window AC"},
];
const fmtINR = n => Number(n||0).toLocaleString('en-IN');
const numToWords = n => {
  if (!n) return 'Zero Rupees Only';
  const a=['','One','Two','Three','Four','Five','Six','Seven','Eight','Nine','Ten','Eleven','Twelve','Thirteen','Fourteen','Fifteen','Sixteen','Seventeen','Eighteen','Nineteen'];
  const b=['','Ten','Twenty','Thirty','Forty','Fifty','Sixty','Seventy','Eighty','Ninety'];
  const k=(n,l)=>{let s='';n=Math.floor(n);if(n>100){s=a[Math.floor(n/100)]+' Hundred ';n%=100;}if(n>19)s+=b[Math.floor(n/10)]+(n%10?' '+a[n%10]:'');else s+=a[n];return s.trim();};
  if(n>=10000000)return k(Math.floor(n/10000000))+' Crore '+k(Math.floor((n%10000000)/100000))+' Lakh '+k(Math.floor((n%100000)/1000))+' Thousand '+k(n%1000)+(l?' '+l:'');
  if(n>=100000)return k(Math.floor(n/100000))+' Lakh '+k(Math.floor((n%100000)/1000))+' Thousand '+k(n%1000)+(l?' '+l:'');
  if(n>=1000)return k(Math.floor(n/1000))+' Thousand '+k(n%1000)+(l?' '+l:'');
  return k(n)+(l?' '+l:'');
};

export default function App() {
  const [mainTab, setMainTab] = useState('tcr');

  // ── TCR State ──────────────────────────────────────────────────────────────
  const [authed, setAuthed] = useState(false);
  const [sessionTech, setSessionTech] = useState(null); // {techId, techName, sfId, sfName}
  const [techIdInput, setTechIdInput] = useState('');
  const [pwdInput, setPwdInput] = useState('');
  const [pwdErr, setPwdErr] = useState('');
  const [loginLoading, setLoginLoading] = useState(false);
  const [f, setF] = useState({
    custName:"", mobile:"", callNo:"", serviceDate:"", techName:"", ssdName:"",
    address:"", tonnage:"", unitCount:1, gstOn:false, gstNumber:""
  });
  const [units, setUnits] = useState([{model:"",serial:"",pipeSize:""}]);
  const [techList, setTechList] = useState([]);
  const [addItems, setAddItems] = useState(additionalItems());
  const [actItems, setActItems] = useState(actualItems());
  const [actSelected, setActSelected] = useState([]);
  const [custStand, setCustStand] = useState(false);
  const [custMaterials, setCustMaterials] = useState(false);
  const [miscDesc, setMiscDesc] = useState('');
  const [screen, setScreen] = useState("form");
  const [token, setToken] = useState("");
  const [waLink, setWaLink] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const [err, setErr] = useState("");
  const [remaining, setRemaining] = useState(1800);
  const [doneData, setDoneData] = useState(null);
  const pollRef = useRef(null);
  const timerRef = useRef(null);
  const pdfBlobRef = useRef(null);
  const gpsRef = useRef(null);

  // ── Master State ───────────────────────────────────────────────────────────
  const [masterAuthed, setMasterAuthed] = useState(false);
  const [masterPwd, setMasterPwd] = useState('');
  const [masterPwdErr, setMasterPwdErr] = useState('');
  const [masterTab, setMasterTab] = useState('sfs');
  const [techs, setTechs] = useState([]);
  const [materials, setMaterials] = useState([]);
  const [allStock, setAllStock] = useState({});
  const [masterLoading, setMasterLoading] = useState(false);
  const [masterMsg, setMasterMsg] = useState('');
  const [masterLoginStep, setMasterLoginStep] = useState('password'); // 'password' | 'otp' | 'newpwd'
  const [masterOtp, setMasterOtp] = useState('');
  const [masterNewPwd, setMasterNewPwd] = useState('');
  const [masterNewPwd2, setMasterNewPwd2] = useState('');
  const [masterOtpSending, setMasterOtpSending] = useState(false);
  const [newTechId, setNewTechId] = useState('');
  const [newTechName, setNewTechName] = useState('');
  const [selTech, setSelTech] = useState('');
  const [selMat, setSelMat] = useState('');
  const [addQty, setAddQty] = useState('');
  const [addCost, setAddCost] = useState('');
  const [editMats, setEditMats] = useState([]);

  // ── SF Management State (Master) ──────────────────────────────────────────
  const [sfs, setSfs] = useState([]);
  const [sfLoading, setSfLoading] = useState(false);
  const [newSfId, setNewSfId] = useState('');
  const [newSfName, setNewSfName] = useState('');
  const [newSfPwd, setNewSfPwd] = useState('');
  const [selSfForTech, setSelSfForTech] = useState('');
  const [newSfTechId, setNewSfTechId] = useState('');
  const [newSfTechName, setNewSfTechName] = useState('');
  const [newSfTechPwd, setNewSfTechPwd] = useState('');
  const [expandedSf, setExpandedSf] = useState(null);
   const [sfSpares, setSfSpares]           = useState([]);
  const [sparesLoading, setSparesLoading] = useState(false);
  const [sparesSearch, setSparesSearch]   = useState('');
  const [showAddSpare, setShowAddSpare]   = useState(false);
  const [newSpare, setNewSpare]           = useState({ materialCode:'', description:'', dp:'', mrp:'', stock:'' });
  const [spareMsg, setSpareMsg]           = useState('');
  const [stockAdjItem, setStockAdjItem]   = useState(null);   // materialCode being adjusted
  const [stockAdjQty, setStockAdjQty]     = useState('');
  const [stockAdjOp, setStockAdjOp]       = useState('add');  // 'add' | 'subtract' | 'set'
  const [bulkSparesUploading, setBulkSparesUploading] = useState(false);
 
  // ── Invoice State ─────────────────────────────────────────────────────────
  const [showInvoice, setShowInvoice]         = useState(false);
  const [invoiceItems, setInvoiceItems]       = useState([]);
  const [invoiceCust, setInvoiceCust]         = useState({ name:'', mobile:'', address:'', gstin:'' });
  const [invoiceNo, setInvoiceNo]             = useState('');
  const [invoiceTaxType, setInvoiceTaxType]   = useState('intra'); // 'intra'=CGST+SGST | 'inter'=IGST
  const [invoiceSearchQ, setInvoiceSearchQ]   = useState('');
  const [invoiceHistory, setInvoiceHistory]   = useState([]);
  const [showInvHistory, setShowInvHistory]   = useState(false);
  const [invHistLoading, setInvHistLoading]   = useState(false);
  const [gstSession, setGstSession] = useState('');
  const [gstCaptcha, setGstCaptcha] = useState('');
  const [gstCaptchaInput, setGstCaptchaInput] = useState('');
  const [gstVerifying, setGstVerifying] = useState(false);
  const [gstError, setGstError] = useState('');
  const [gstSuccess, setGstSuccess] = useState(false);

  // ── Tech (My Stock) State ─────────────────────────────────────────────────
  const [myTechId, setMyTechId] = useState('');
  const [techAuthed, setTechAuthed] = useState(false);
  const [techData, setTechData] = useState(null);
  const [techErr, setTechErr] = useState('');
  const [techLoading, setTechLoading] = useState(false);

  // ── Service Centre Tab State ───────────────────────────────────────────────
  const [sfTabAuthed, setSfTabAuthed] = useState(false);
  const [sfTabSession, setSfTabSession] = useState(null); // {sfId, sfName, gst, address, contact, email}
  const [sfTabIdInput, setSfTabIdInput] = useState('');
  const [sfTabPwdInput, setSfTabPwdInput] = useState('');
  const [sfTabErr, setSfTabErr] = useState('');
  const [sfTabLoading, setSfTabLoading] = useState(false);
  const [sfTabDataLoading, setSfTabDataLoading] = useState(false);
  const [sfSubTab, setSfSubTab] = useState('stock');
  const [sfTabTechs, setSfTabTechs] = useState([]);
  const [sfTabMaterials, setSfTabMaterials] = useState([]);
  const [sfTabEditMats, setSfTabEditMats] = useState([]);
  const [sfTabStock, setSfTabStock] = useState({});
  const [sfTabMsg, setSfTabMsg] = useState('');
  const [sfTabNewTechId, setSfTabNewTechId] = useState('');
  const [sfTabNewTechName, setSfTabNewTechName] = useState('');
  const [sfTabNewTechPwd, setSfTabNewTechPwd] = useState('');
  // Master — SF profile editing
  const [editingSfId, setEditingSfId] = useState(null);
  const [sfProfileDraft, setSfProfileDraft] = useState({});

  const field = (k, v) => setF(p => ({...p, [k]: v}));

  // Fetch SF-scoped tech list after login
  useEffect(() => {
    if (sessionTech?.sfId) {
      fetch('/api/inventory/technicians?sfId=' + sessionTech.sfId)
        .then(r => r.json())
        .then(d => { if (Array.isArray(d)) setTechList(d); });
    }
  }, [sessionTech]);

  useEffect(() => {
    const n = parseInt(f.unitCount) || 1;
    setUnits(prev => {
      const next = [...prev];
      while (next.length < n) next.push({model:"",serial:"",pipeSize:""});
      return next.slice(0, n);
    });
  }, [f.unitCount]);

  useEffect(() => {
    if (masterAuthed) { loadMasterAll(); loadSfs(); }
  }, [masterAuthed]);

  useEffect(() => { if(screen === "pending" && waLink) { window.open(waLink, "_blank"); } }, [screen, waLink]);
  useEffect(() => () => { clearInterval(pollRef.current); clearInterval(timerRef.current); }, []);

  // ── TCR Functions ──────────────────────────────────────────────────────────
  function calcTotals() {
    const ton = f.tonnage; let sub = 0;
    addItems.forEach(it => {
      if (isDisabled(it, ton)) return;
      const rate = effectiveRate(it);
      sub += rate * (parseFloat(it.qty)||0);
    });
    actItems.forEach(it => {
      let rate = it.rate;
      if (custMaterials && it.desc === "Wrapping Tape") rate = 10;
      const qty = parseFloat(it.actual)||0;
      sub += rate>0 ? qty*rate : qty;
    });
    const gst = f.gstOn ? Math.round(sub * 0.18) : 0;
    return { sub, gst, total: sub + gst };
  }

  function isDisabled(it, ton) {
    if (it.group === "all") return false;
    if (!ton || ton==="mix" || ton==="window") return false;
    const isHigh = ton==="2.0+" || ton==="2.0";
    if (it.group==="high" && !isHigh) return true;
    if (it.group==="low"  &&  isHigh) return true;
    return false;
  }

  function effectiveRate(it) {
    if (it.no==="4") {
      if (custStand) return 250;
      return (f.tonnage==="2.0+"||f.tonnage==="2.0") ? 1200 : 750;
    }
    if (custMaterials) {
      if (it.no==="2.1" || it.no==="2.2") return 70;
      if (it.no==="3") return 10;
      if (it.no==="5") return 10;
    }
    return it.rate;
  }

  function updateAddItem(i, key, val) { setAddItems(prev => { const n=[...prev]; n[i]={...n[i],[key]:val}; return n; }); }
  function updateActItem(i, val) { setActItems(prev => { const n=[...prev]; n[i]={...n[i],actual:parseFloat(val)||0}; return n; }); }
  function updateUnit(i, key, val) { setUnits(prev => { const n=[...prev]; n[i]={...n[i],[key]:val}; return n; }); }

  // NEW: Tech login via API
  async function handleLogin() {
    if (!techIdInput.trim() || !pwdInput) return setPwdErr('Enter Tech ID and Password');
    setLoginLoading(true); setPwdErr('');
    try {
      const res = await fetch('/api/auth/tech', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ techId: techIdInput.trim().toUpperCase(), password: pwdInput })
      });
      const json = await res.json();
      if (!res.ok) { setPwdErr(json.error || 'Invalid credentials'); setLoginLoading(false); return; }
      setSessionTech(json); // {techId, techName, sfId, sfName}
      setF(p => ({ ...p, techName: json.techId, ssdName: json.sfName }));
      setAuthed(true);
    } catch(e) { setPwdErr('Network error'); }
    setLoginLoading(false);
  }

  async function handleSubmit() {
    setErr("");
    if (!f.custName)                         return setErr("Customer Name required");
    if (!/^\d{10}$/.test(f.mobile))           return setErr("Valid 10-digit mobile required");
    if (!f.callNo)                            return setErr("Call / Job No. required");
    if (!f.serviceDate)                       return setErr("Service Date required");
    if (!f.techName)                          return setErr("Technician Name required");
    if (!f.tonnage)                           return setErr("Please select AC Tonnage");
    if (f.gstOn && f.gstNumber.length < 15)  return setErr("Valid 15-char GST Number required");
    const { sub, gst, total } = calcTotals();
    const payload = {
      ...f,
      sfId: sessionTech?.sfId,              // ← SF scope
      units,
      additionalItems: addItems.map(it => ({...it, rate: effectiveRate(it)})),
      actualItems: actItems,
      sub, gst, total
    };
    gpsRef.current = null;
    if (navigator.geolocation) {
      gpsRef.current = await new Promise(resolve => {
        navigator.geolocation.getCurrentPosition(
          p => resolve({ lat: p.coords.latitude, lng: p.coords.longitude, acc: p.coords.accuracy }),
          () => resolve(null),
          { timeout: 5000, maximumAge: 60000 }
        );
      });
    }
    setSubmitting(true);
    try {
      const res = await fetch("/api/submit", { method:"POST", headers:{"Content-Type":"application/json"}, body: JSON.stringify(payload) });
      const json = await res.json();
      if (!res.ok) { setErr(json.error || "Submit failed"); setSubmitting(false); return; }
      setToken(json.token); setWaLink(json.waLink); setScreen("pending");
      try {
        const deductItems = [];
        const pipeMap = {"1/4 & 1/2":"copper_pair_14_12","3/8 & 5/8":"copper_pair_38_58","1/2 & 5/8":"copper_pair_12_58"};
        const pipeQty = addItems.find(it=>it.no==="2.1"||it.no==="2.2");
        units.forEach(u => {
          if (!u.pipeSize) return;
          const matId = pipeMap[u.pipeSize];
          if (matId && pipeQty && pipeQty.qty>0) {
            const ex = deductItems.find(d=>d.materialId===matId);
            if (ex) ex.qty += parseFloat(pipeQty.qty)||0;
            else deductItems.push({materialId:matId, qty:parseFloat(pipeQty.qty)||0});
          }
        });
        const matMap = {"3":"elec_cable","4":"odu_stand","5":"drain_pipe","6":"dismantling"};
        addItems.forEach(it => { if (matMap[it.no]&&it.qty>0) deductItems.push({materialId:matMap[it.no],qty:parseFloat(it.qty)||0}); });
        const actMap = {"Wrapping Tape":"wrapping_tape","Rubber Pad":"rubber_pad","Plug Top":"plug_top"};
        actItems.forEach(it => { const mid=actMap[it.desc]; if(mid&&it.actual>0) deductItems.push({materialId:mid,qty:parseFloat(it.actual)||0}); });
        if (deductItems.length>0 && f.techName) await fetch('/api/inventory/deduct',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({techId:f.techName,items:deductItems,jobNo:f.callNo})});
      } catch(e) { console.log('Deduct error:',e); }
      setRemaining(1800); startPolling(json.token);
    } catch(e) { setErr("Network error. Check connection."); }
    setSubmitting(false);
  }

  function startPolling(tok) {
    pollRef.current = setInterval(async () => {
      try {
        const res = await fetch("/api/status?token="+tok);
        const json = await res.json();
        if (json.status === "confirmed") {
          clearInterval(pollRef.current); clearInterval(timerRef.current);
          setDoneData(json.fullData); setScreen("done");
          buildTechPDF(json.fullData, tok);
          try { await fetch('/api/records', { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(json.fullData) }); } catch(e) { console.log('Record save error:',e); }
        }
        if (json.status === "expired") { clearInterval(pollRef.current); clearInterval(timerRef.current); setScreen("form"); setErr("Confirmation link expired. Please resubmit."); }
        if (json.remaining !== undefined) setRemaining(json.remaining);
      } catch(e) {}
    }, 4000);
    timerRef.current = setInterval(() => { setRemaining(r => { if (r <= 1) { clearInterval(timerRef.current); return 0; } return r - 1; }); }, 1000);
  }

  async function buildTechPDF(d, tok) {
    try {
      const { jsPDF } = await import('jspdf');
      await import('jspdf-autotable');
      const gpsCoords = gpsRef.current;
      const doc = new jsPDF({ unit:'mm', format:'a4', compress:true });
      const W=210, M=14; let y=0;
      doc.setFillColor(232,0,29); doc.rect(0,0,W,30,'F');
      doc.setTextColor(255,255,255); doc.setFont('helvetica','bold'); doc.setFontSize(11);
      doc.text('GENERAL HVAC Solutions India Pvt Ltd', M+34, 13);
      doc.setFont('helvetica','normal'); doc.setFontSize(7.5);
      doc.text('Authorized Service Partner - TCR cum Customer Confirmation', M+34, 19);
      doc.setFontSize(9); doc.setFont('helvetica','bold');
      doc.text('AC INSTALLATION - TCR CHARGE CONFIRMATION', M+34, 26);
      doc.setFontSize(7); doc.setFont('helvetica','normal');
      doc.text('Job No: '+d.callNo, W-M, 10, {align:'right'});
      doc.text('Date: '+d.serviceDate, W-M, 16, {align:'right'});
      y=36;
      doc.saveGraphicsState();
      doc.setGState(new doc.GState({opacity:0.07}));
      doc.setTextColor(22,163,74); doc.setFont('helvetica','bold'); doc.setFontSize(62);
      doc.text('APPROVED', W/2, 170, {align:'center', angle:45});
      doc.restoreGraphicsState();
      const sH=(t,c)=>{ doc.setFillColor(...c); doc.rect(M,y,W-M*2,6.5,'F'); doc.setTextColor(255,255,255); doc.setFont('helvetica','bold'); doc.setFontSize(8); doc.text(t.toUpperCase(),M+3,y+4.5); y+=8; };
      sH('Customer & Service Details',[40,40,40]);
      doc.setFillColor(250,250,250); doc.setDrawColor(230,230,230); doc.rect(M,y,W-M*2,28,'FD');
      [['CUSTOMER',d.custName],['MOBILE',d.mobile],['JOB NO.',d.callNo],['DATE',d.serviceDate],['TECHNICIAN',d.techName],['SSD/SF',d.ssdName||'--']].forEach(([k,v],i)=>{
        const cx=M+(i%2)*((W-M*2)/2)+3, cy=y+5+(Math.floor(i/2)*9);
        doc.setFont('helvetica','normal'); doc.setFontSize(7); doc.setTextColor(130,130,130); doc.text(k,cx,cy);
        doc.setFont('helvetica','bold'); doc.setFontSize(8); doc.setTextColor(30,30,30); doc.text(String(v||'--'),cx,cy+5);
      });
      y+=32;
      sH('Installation Charges',[40,40,40]);
      const rows=[['1','Standard Installation (Free)','--','--','Rs.0']];
      (d.additionalItems||[]).filter(i=>i.qty>0).forEach(i=>rows.push([i.no,i.desc,'Rs.'+i.rate+'/Ft',i.qty+' Ft','Rs.'+(i.rate*i.qty).toLocaleString('en-IN')]));
      (d.actualItems||[]).filter(i=>i.actual>0).forEach(i=>{ const amt=i.rate>0?i.rate*i.actual:i.actual; rows.push([i.no,i.desc,i.unit||'Actual',i.actual,'Rs.'+Number(amt).toLocaleString('en-IN')]); });
      doc.autoTable({ startY:y, margin:{left:M,right:M}, head:[['#','Description','Rate','Qty','Amount']], body:rows, styles:{fontSize:8,cellPadding:2.5,textColor:[50,50,50]}, headStyles:{fillColor:[30,30,30],textColor:[255,255,255],fontStyle:'bold',fontSize:8}, alternateRowStyles:{fillColor:[248,248,248]}, columnStyles:{0:{cellWidth:10},1:{cellWidth:95},2:{cellWidth:28},3:{cellWidth:20},4:{cellWidth:25,halign:'right'}}, theme:'grid' });
      y=doc.lastAutoTable.finalY+6;
      const bx=W-M-70, bw=70, fR=n=>'Rs.'+Number(n).toLocaleString('en-IN');
      doc.setFillColor(248,248,248); doc.setDrawColor(220,220,220); doc.roundedRect(bx,y,bw,d.gstOn?25:18,2,2,'FD');
      doc.setFontSize(8); doc.setTextColor(80,80,80); doc.setFont('helvetica','normal');
      doc.text('Sub Total',bx+3,y+6); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30); doc.text(fR(d.sub),bx+bw-3,y+6,{align:'right'});
      if(d.gstOn){ doc.setFont('helvetica','normal'); doc.setTextColor(80,80,80); doc.text('GST @ 18%',bx+3,y+13); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30); doc.text(fR(d.gst),bx+bw-3,y+13,{align:'right'}); doc.setDrawColor(200,200,200); doc.line(bx+3,y+15,bx+bw-3,y+15); doc.setFontSize(10); doc.setTextColor(232,0,29); doc.text('TOTAL',bx+3,y+22); doc.text(fR(d.total),bx+bw-3,y+22,{align:'right'}); y+=29; }
      else { doc.setDrawColor(200,200,200); doc.line(bx+3,y+11,bx+bw-3,y+11); doc.setFontSize(10); doc.setFont('helvetica','bold'); doc.setTextColor(232,0,29); doc.text('TOTAL',bx+3,y+16); doc.text(fR(d.total),bx+bw-3,y+16,{align:'right'}); y+=22; }
      y+=6;
      sH('Customer Confirmation - Digitally Approved',[22,163,74]);
      doc.setFillColor(240,253,244); doc.setDrawColor(187,247,208); doc.rect(M,y,W-M*2,32,'FD');
      doc.setFillColor(22,163,74); doc.roundedRect(M+3,y+4,54,8,2,2,'F');
      doc.setTextColor(255,255,255); doc.setFont('helvetica','bold'); doc.setFontSize(7.5);
      doc.text('APPROVED BY CUSTOMER', M+30, y+9, {align:'center'});
      doc.setFontSize(7); doc.setFont('helvetica','normal'); doc.setTextColor(70,70,70);
      doc.text('Confirmed via unique one-time secure link on customer own device', M+3, y+17);
      doc.text('Confirmed at: '+new Date().toLocaleString('en-IN'), M+3, y+23);
      doc.text('Token Ref: '+String(tok).slice(0,16)+'...', M+3, y+29);
      y+=36;
      {
        const mapsUrl = gpsCoords ? 'https://maps.google.com/?q='+gpsCoords.lat+','+gpsCoords.lng : null;
        if (y > 245) { doc.addPage(); y = 20; }
        sH('Technician GPS Location',[29,78,216]);
        doc.setFillColor(239,246,255); doc.setDrawColor(147,197,253); doc.rect(M,y,W-M*2,11,'FD');
        doc.setFont('helvetica','bold'); doc.setFontSize(7.5); doc.setTextColor(29,78,216);
        doc.text('GPS LOCATION:', M+3, y+7);
        doc.setFont('helvetica','normal'); doc.setTextColor(50,50,50);
        if (gpsCoords) {
          doc.text(gpsCoords.lat.toFixed(6)+', '+gpsCoords.lng.toFixed(6)+'  (Acc: +/-'+Math.round(gpsCoords.acc)+'m)', M+32, y+7);
          doc.setTextColor(37,99,235);
          doc.text('[View on Maps]', W-M-30, y+7);
          doc.link(W-M-30, y+2, 30, 7, {url: mapsUrl});
        } else {
          doc.setTextColor(150,150,150);
          doc.text('Location not available (permission denied or GPS unavailable)', M+32, y+7);
        }
        y += 15;
      }
      doc.setFillColor(30,30,30); doc.rect(0,287,W,10,'F');
      doc.setTextColor(160,160,160); doc.setFont('helvetica','normal'); doc.setFontSize(7);
      doc.text('GENERAL HVAC Solutions India Pvt Ltd  |  Approved TCR  |  Job: '+d.callNo, W/2, 293, {align:'center'});
      pdfBlobRef.current = doc.output('blob');
    } catch(e) { console.log('PDF error:',e); }
  }

  function downloadTechPDF() {
    if (!pdfBlobRef.current) return;
    const url = URL.createObjectURL(pdfBlobRef.current);
    const a = document.createElement('a'); a.href=url; a.download='TCR_APPROVED_'+(doneData&&doneData.callNo?doneData.callNo:'receipt')+'.pdf'; a.click(); URL.revokeObjectURL(url);
  }

  function fmtTime(s) { const m = Math.floor(s/60), sec = s%60; return m+":"+String(sec).padStart(2,"0"); }

  function reset() {
    clearInterval(pollRef.current); clearInterval(timerRef.current);
    setScreen("form");
    // Re-populate session fields so they stay locked after reset
    setF({
      custName:"", mobile:"", callNo:"", serviceDate:"",
      techName: sessionTech?.techId || "",
      ssdName: sessionTech?.sfName || "",
      address:"", tonnage:"", unitCount:1, gstOn:false, gstNumber:""
    });
    setUnits([{model:"",serial:"",pipeSize:""}]);
    setAddItems(additionalItems()); setActItems(actualItems()); setActSelected([]);
    setErr(""); setToken(""); setWaLink(""); setDoneData(null); setRemaining(1800);
    pdfBlobRef.current=null; gpsRef.current=null;
    setCustStand(false); setCustMaterials(false); setMiscDesc('');
  }

  function fullLogout() {
    reset();
    setAuthed(false);
    setSessionTech(null);
    setTechIdInput('');
    setPwdInput('');
    setPwdErr('');
    setTechList([]);
    setF({custName:"",mobile:"",callNo:"",serviceDate:"",techName:"",ssdName:"",address:"",tonnage:"",unitCount:1,gstOn:false,gstNumber:""});
  }

  async function downloadExcel(records, filename) {
    if (!records || records.length === 0) return alert('No records found.');
    const XLSX = await import('xlsx');
    const rows = records.map(r => {
      const getAdd = no => (r.additionalItems||[]).find(i=>i.no===no);
      const getAct = desc => (r.actualItems||[]).find(i=>i.desc===desc);
      const itemQty = no => { const it=getAdd(no); return it&&it.qty>0 ? parseFloat(it.qty)||0 : 0; };
      const itemAmt = no => { const it=getAdd(no); return it&&it.qty>0 ? (parseFloat(it.rate)||0)*(parseFloat(it.qty)||0) : 0; };
      const actQty = desc => { const it=getAct(desc); return it&&it.actual>0 ? parseFloat(it.actual)||0 : 0; };
      const actAmt = desc => { const it=getAct(desc); if(!it||!it.actual) return 0; return it.rate>0 ? it.rate*it.actual : it.actual; };
      const copperQty = itemQty('2.1')||itemQty('2.2');
      const copperAmt = itemAmt('2.1')||itemAmt('2.2');
      return {
        'Job No.'                    : r.callNo||'',
        'Service Date'               : r.serviceDate||'',
        'Confirmed At'               : r.confirmedAt ? new Date(r.confirmedAt).toLocaleString('en-IN') : '',
        'Customer Name'              : r.custName||'',
        'Mobile'                     : r.mobile||'',
        'Technician'                 : r.techName||'',
        'SSD / SF'                   : r.ssdName||'',
        'Tonnage'                    : r.tonnage||'',
        'Units'                      : r.unitCount||1,
        'GST Applied'                : r.gstOn?'Yes':'No',
        'GST Number'                 : r.gstNumber||'',
        'Copper Pipe Qty (Ft)'       : copperQty,
        'Copper Pipe Amt (Rs.)'      : copperAmt,
        'Electrical Cable Qty (Ft)'  : itemQty('3'),
        'Electrical Cable Amt (Rs.)' : itemAmt('3'),
        'ODU Stand Qty'              : itemQty('4'),
        'ODU Stand Amt (Rs.)'        : itemAmt('4'),
        'Drain Pipe Qty (Ft)'        : itemQty('5'),
        'Drain Pipe Amt (Rs.)'       : itemAmt('5'),
        'Dismantling Qty'            : itemQty('6'),
        'Dismantling Amt (Rs.)'      : itemAmt('6'),
        'Wrapping Tape (Ft)'         : actQty('Wrapping Tape'),
        'Wrapping Tape Amt (Rs.)'    : actAmt('Wrapping Tape'),
        'Rubber Pad (Qty)'           : actQty('Rubber Pad'),
        'Rubber Pad Amt (Rs.)'       : actAmt('Rubber Pad'),
        'Plug Top (Qty)'             : actQty('Plug Top'),
        'Plug Top Amt (Rs.)'         : actAmt('Plug Top'),
        'Nitrogen Flushing (Qty)'    : actQty('Nitrogen flushing'),
        'Nitrogen Flushing Amt (Rs.)': actAmt('Nitrogen flushing'),
        'Miscellaneous'              : actQty('Miscellaneous (Please specify)'),
        'Sub Total (Rs.)'            : Number(r.sub)||0,
        'GST Amt (Rs.)'              : Number(r.gst)||0,
        'Total (Rs.)'                : Number(r.total)||0,
      };
    });
    const ws = XLSX.utils.json_to_sheet(rows);
    ws['!cols'] = Object.keys(rows[0]).map(k=>({wch:Math.max(k.length,14)}));
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'TCR Records');
    XLSX.writeFile(wb, filename);
  }

  const { sub, gst, total } = calcTotals();

  // ── Master Functions ───────────────────────────────────────────────────────
  async function loadMasterAll() {
    setMasterLoading(true);
    try {
      const res = await fetch('/api/inventory/stock?password='+'Project@1');
      const json = await res.json();
      setTechs(json.techs||[]); setMaterials(json.materials||[]); setAllStock(json.allStock||{}); setEditMats(json.materials||[]);
    } catch(e) { setMasterMsg('Error loading data'); }
    setMasterLoading(false);
  }
  async function addTechnician() {
    if (!newTechId||!newTechName) return setMasterMsg('Fill both ID and Name');
    const res = await fetch('/api/inventory/technicians',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:'Project@1',action:'add',techId:newTechId.toUpperCase(),techName:newTechName})});
    const json = await res.json(); if (json.error) return setMasterMsg(json.error);
    setTechs(json.techs); setNewTechId(''); setNewTechName(''); setMasterMsg('Technician added to stock!'); setTimeout(()=>setMasterMsg(''),3000);
  }
  async function removeTechnician(tid) {
    if (!confirm('Remove '+tid+' from stock tracking?')) return;
    const res = await fetch('/api/inventory/technicians',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:'Project@1',action:'remove',techId:tid})});
    const json = await res.json(); setTechs(json.techs); setMasterMsg('Removed'); setTimeout(()=>setMasterMsg(''),3000);
  }
  async function addStockSubmit() {
    if (!selTech||!selMat||!addQty) return setMasterMsg('Fill all fields');
    const res = await fetch('/api/inventory/stock',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:'Project@1',techId:selTech,materialId:selMat,qty:addQty,costPrice:addCost})});
    const json = await res.json(); if (json.error) return setMasterMsg(json.error);
    const r2 = await fetch('/api/inventory/stock?password='+'Project@1'); const j2 = await r2.json(); setAllStock(j2.allStock||{});
    setSelTech(''); setSelMat(''); setAddQty(''); setAddCost(''); setMasterMsg('Stock added!'); setTimeout(()=>setMasterMsg(''),3000);
  }
  async function saveMaterialPrices() {
    const res = await fetch('/api/inventory/materials',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({password:'Project@1',materials:editMats})});
    const json = await res.json(); if (json.error) return setMasterMsg(json.error);
    setMaterials(editMats); setMasterMsg('Prices saved!'); setTimeout(()=>setMasterMsg(''),3000);
  }
  function getStockVal(tid) { const s=allStock[tid]||{}; let v=0; materials.forEach(m=>{v+=(s[m.id]||0)*m.costPrice;}); return v; }

  // ── SF Management Functions ────────────────────────────────────────────────
  async function doMasterLogin() {
    if (!masterPwd.trim()) return setMasterPwdErr('Enter your password');
    const res = await fetch('/api/master/login', {
      method: 'POST', headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ password: masterPwd })
    });
    const json = await res.json();
    if (!res.ok) return setMasterPwdErr(json.error || 'Incorrect password');
    setMasterAuthed(true); setMasterPwdErr(''); setMasterPwd('');
  }

  async function doSendOtp() {
    setMasterOtpSending(true); setMasterPwdErr('');
    const res = await fetch('/api/master/send-otp', { method: 'POST' });
    const json = await res.json();
    setMasterOtpSending(false);
    if (!res.ok) return setMasterPwdErr(json.error || 'Failed to send OTP');
    setMasterLoginStep('otp');
  }

  async function doVerifyOtp() {
    if (!masterOtp.trim()) return setMasterPwdErr('Enter the OTP');
    if (!masterNewPwd) return setMasterPwdErr('Enter a new password');
    if (masterNewPwd !== masterNewPwd2) return setMasterPwdErr('Passwords do not match');
    const res = await fetch('/api/master/verify-otp', {
      method: 'POST', headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ otp: masterOtp, newPassword: masterNewPwd })
    });
    const json = await res.json();
    if (!res.ok) return setMasterPwdErr(json.error || 'Failed to verify OTP');
    setMasterLoginStep('done');
  }

  async function loadSfs() {
    setSfLoading(true);
    try {
      const res = await fetch('/api/admin/sfs?password=' + 'Project@1');
      const json = await res.json();
      setSfs(json.sfs || []);
    } catch(e) { setMasterMsg('Error loading service centres'); }
    setSfLoading(false);
  }

  async function createSf() {
    if (!newSfId.trim() || !newSfName.trim() || !newSfPwd.trim()) return setMasterMsg('Fill SF ID, Name and Password');
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', action: 'createSf', sfId: newSfId.trim().toUpperCase(), sfName: newSfName.trim(), sfPassword: newSfPwd.trim() })
    });
    const json = await res.json();
    if (json.error) return setMasterMsg(json.error);
    setSfs(json.sfs); setNewSfId(''); setNewSfName(''); setNewSfPwd('');
    setMasterMsg('Service Centre created!'); setTimeout(() => setMasterMsg(''), 3000);
  }

  async function removeSf(sfId) {
    if (!confirm('Remove SF ' + sfId + ' and ALL its technician accounts?')) return;
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', action: 'removeSf', sfId })
    });
    const json = await res.json();
    setSfs(json.sfs); setMasterMsg('SF removed'); setTimeout(() => setMasterMsg(''), 3000);
  }

  async function addTechToSf() {
    if (!selSfForTech || !newSfTechId.trim() || !newSfTechName.trim() || !newSfTechPwd.trim())
      return setMasterMsg('Fill all technician fields');
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        password: 'Project@1', action: 'addTech',
        sfId: selSfForTech,
        techId: newSfTechId.trim().toUpperCase(),
        techName: newSfTechName.trim(),
        techPassword: newSfTechPwd.trim()
      })
    });
    const json = await res.json();
    if (json.error) return setMasterMsg(json.error);
    setSfs(json.sfs);
    setNewSfTechId(''); setNewSfTechName(''); setNewSfTechPwd('');
    // Refresh stock tech list too
    loadMasterAll();
    setMasterMsg('Technician added!'); setTimeout(() => setMasterMsg(''), 3000);
  }

  async function removeTechFromSf(sfId, techId) {
    if (!confirm('Remove technician ' + techId + ' from ' + sfId + '?')) return;
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', action: 'removeTech', sfId, techId })
    });
    const json = await res.json();
    setSfs(json.sfs); setMasterMsg('Technician removed'); setTimeout(() => setMasterMsg(''), 3000);
  }

  // ── Tech (My Stock) Functions ──────────────────────────────────────────────
  async function techLogin() {
    if (!myTechId.trim()) return setTechErr('Enter your Technician ID');
    setTechLoading(true); setTechErr('');
    try {
      const res = await fetch('/api/inventory/stock?techId='+myTechId.trim().toUpperCase());
      const json = await res.json();
      if (json.error) { setTechErr('Invalid Technician ID'); setTechLoading(false); return; }
      setTechData(json); setTechAuthed(true);
    } catch(e) { setTechErr('Network error'); }
    setTechLoading(false);
  }
  async function techRefresh() {
    setTechLoading(true);
    const res = await fetch('/api/inventory/stock?techId='+myTechId.trim().toUpperCase());
    const json = await res.json(); setTechData(json); setTechLoading(false);
  }

  // ── Service Centre Tab Functions ───────────────────────────────────────────
  async function sfTabLogin() {
    if (!sfTabIdInput.trim() || !sfTabPwdInput.trim()) return setSfTabErr('Enter SF ID and password');
    setSfTabLoading(true); setSfTabErr('');
    try {
      const res = await fetch('/api/admin/sfs', {
        method: 'POST', headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ action: 'sfLogin', sfId: sfTabIdInput.trim().toUpperCase(), sfPassword: sfTabPwdInput.trim() })
      });
      const json = await res.json();
      if (!res.ok) { setSfTabErr(json.error || 'Invalid SF ID or password'); setSfTabLoading(false); return; }
      setSfTabSession({
        sfId: json.sfId, sfName: json.sfName,
        gst: json.gst || '', address: json.address || '',
        contact: json.contact || '', email: json.email || '',
      });
      setSfTabAuthed(true);
      loadSfTabData(json.sfId);
    } catch(e) { setSfTabErr('Network error'); }
    setSfTabLoading(false);
  }

  async function updateSfProfile(sfId, draft) {
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', action: 'updateSf', sfId, ...draft })
    });
    const json = await res.json();
    if (json.error) return setMasterMsg(json.error);
    setSfs(json.sfs);
    setEditingSfId(null); setSfProfileDraft({});
    setMasterMsg('Profile updated!'); setTimeout(() => setMasterMsg(''), 3000);
  }

  async function loadSfTabData(sfId) {
    setSfTabDataLoading(true);
    try {
      // Load techs scoped to this SF
      const r1 = await fetch('/api/inventory/technicians?sfId=' + sfId);
      const d1 = await r1.json();
      const techArr = Array.isArray(d1) ? d1 : [];
      setSfTabTechs(techArr);
      // Load materials & prices
      const r2 = await fetch('/api/inventory/stock?password=Project@1');
      const d2 = await r2.json();
      setSfTabMaterials(d2.materials || []);
      setSfTabEditMats(d2.materials || []);
      // Load stock for each tech in this SF
      const stockObj = {};
      await Promise.all(techArr.map(async t => {
        try {
          const r = await fetch('/api/inventory/stock?techId=' + t.id);
          const d = await r.json();
          if (d.stock) stockObj[t.id] = d.stock;
        } catch(e) {}
      }));
      setSfTabStock(stockObj);
    } catch(e) { setSfTabMsg('Error loading data'); }
    setSfTabDataLoading(false);
  }

  async function sfTabAddTech() {
    if (!sfTabNewTechId.trim() || !sfTabNewTechName.trim() || !sfTabNewTechPwd.trim())
      return setSfTabMsg('Fill Tech ID, Name and Password');
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        password: 'Project@1', action: 'addTech',
        sfId: sfTabSession.sfId,
        techId: sfTabNewTechId.trim().toUpperCase(),
        techName: sfTabNewTechName.trim(),
        techPassword: sfTabNewTechPwd.trim()
      })
    });
    const json = await res.json();
    if (json.error) return setSfTabMsg(json.error);
    setSfTabNewTechId(''); setSfTabNewTechName(''); setSfTabNewTechPwd('');
    setSfTabMsg('Technician added!'); setTimeout(() => setSfTabMsg(''), 3000);
    loadSfTabData(sfTabSession.sfId);
  }

  async function sfTabRemoveTech(techId) {
    if (!confirm('Remove technician ' + techId + ' from ' + sfTabSession.sfId + '?')) return;
    const res = await fetch('/api/admin/sfs', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', action: 'removeTech', sfId: sfTabSession.sfId, techId })
    });
    const json = await res.json();
    if (json.error) return setSfTabMsg(json.error);
    setSfTabMsg('Technician removed'); setTimeout(() => setSfTabMsg(''), 3000);
    loadSfTabData(sfTabSession.sfId);
  }

  async function sfTabSaveMaterialPrices() {
    const res = await fetch('/api/inventory/materials', {
      method: 'POST', headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ password: 'Project@1', materials: sfTabEditMats })
    });
    const json = await res.json();
    if (json.error) return setSfTabMsg(json.error);
    setSfTabMaterials(sfTabEditMats);
    setSfTabMsg('Prices saved!'); setTimeout(() => setSfTabMsg(''), 3000);
  }

  function sfTabLogout() {
    setSfTabAuthed(false); setSfTabSession(null);
    setSfTabIdInput(''); setSfTabPwdInput(''); setSfTabErr('');
    setSfTabTechs([]); setSfTabMaterials([]); setSfTabEditMats([]);
    setSfTabStock({}); setSfTabMsg(''); setSfSubTab('stock');
    setSfTabNewTechId(''); setSfTabNewTechName(''); setSfTabNewTechPwd('');
  }

  function sfTabGetStockVal(tid) {
    const s = sfTabStock[tid] || {}; let v = 0;
    sfTabMaterials.forEach(m => { v += (s[m.id] || 0) * m.costPrice; });
    return v;
  }
 function consumptionLabel(usageCount) {
    if (usageCount >= 20) return { label:'🔥 High',  color:'#DC2626', bg:'#FEF2F2', border:'#FECACA' };
    if (usageCount  >= 8) return { label:'🔶 Mid',   color:'#D97706', bg:'#FFFBEB', border:'#FDE68A' };
    if (usageCount  >= 1) return { label:'🔵 Low',   color:'#1D4ED8', bg:'#EFF6FF', border:'#BFDBFE' };
    return                       { label:'⬜ New',   color:'#6B7280', bg:'#F9FAFB', border:'#E5E7EB' };
  }
 
  async function loadSpares(sfId) {
    setSparesLoading(true);
    try {
      const r = await fetch('/api/inventory/spares?sfId=' + sfId);
      const d = await r.json();
      setSfSpares(d.items || []);
    } catch(e) { setSpareMsg('Error loading spares'); }
    setSparesLoading(false);
  }
 
  async function addSpare() {
    const { materialCode, description, dp, mrp, stock } = newSpare;
    if (!materialCode.trim() || !description.trim()) return setSpareMsg('Material code and description are required');
    setSparesLoading(true);
    try {
      const r = await fetch('/api/inventory/spares', {
        method:'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ sfId: sfTabSession.sfId, action:'addItem', materialCode, description, dp, mrp, stock })
      });
      const d = await r.json();
      if (d.error) { setSpareMsg(d.error); setSparesLoading(false); return; }
      setSfSpares(d.items || []);
      setNewSpare({ materialCode:'', description:'', dp:'', mrp:'', stock:'' });
      setShowAddSpare(false);
      setSpareMsg('✅ Spare added!'); setTimeout(()=>setSpareMsg(''), 3000);
    } catch(e) { setSpareMsg('Network error'); }
    setSparesLoading(false);
  }
 
  async function confirmStockAdj() {
    if (!stockAdjItem || !stockAdjQty) return;
    try {
      const r = await fetch('/api/inventory/spares', {
        method:'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ sfId: sfTabSession.sfId, action:'updateStock', materialCode: stockAdjItem, qty: stockAdjQty, operation: stockAdjOp })
      });
      const d = await r.json();
      if (d.error) return setSpareMsg(d.error);
      setSfSpares(d.items || []);
      setStockAdjItem(null); setStockAdjQty(''); setStockAdjOp('add');
      setSpareMsg('✅ Stock updated!'); setTimeout(()=>setSpareMsg(''), 2000);
    } catch(e) { setSpareMsg('Network error'); }
  }
 
  async function deleteSpare(materialCode) {
    if (!confirm('Delete ' + materialCode + ' from spare catalog?')) return;
    try {
      const r = await fetch('/api/inventory/spares', {
        method:'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ sfId: sfTabSession.sfId, action:'deleteItem', materialCode })
      });
      const d = await r.json();
      setSfSpares(d.items || []);
    } catch(e) { setSpareMsg('Error deleting'); }
  }
  async function handleBulkSparesUpload(e) {
    const file = e.target.files[0]; if (!file) return;
    setBulkSparesUploading(true); setSpareMsg('');
    try {
      const buffer = await file.arrayBuffer();
      const workbook = await import('xlsx').then(m => m.default || m);
      const wb = workbook.read(buffer, { type: 'array' });
      const ws = wb.Sheets[wb.SheetNames[0]];
      const items = workbook.utils.sheet_to_json(ws);
      const r = await fetch('/api/inventory/spares', {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ sfId: sfTabSession.sfId, action: 'bulkUpload', items })
      });
      const d = await r.json();
      if (d.error) return setSpareMsg(d.error);
      setSfSpares(d.items || []);
      setSpareMsg('✅ ' + (d.message || 'Bulk upload done!'));
      setTimeout(() => setSpareMsg(''), 4000);
    } catch(err) { setSpareMsg('Error: ' + err.message); }
    setBulkSparesUploading(false); e.target.value = '';
  }
  // ── SF Spare Inventory / Invoice Functions ────────────────────────────────
  function addInvoiceItem(spare) {
    setInvoiceItems(prev => {
      if (prev.find(i => i.materialCode === spare.materialCode)) return prev;
      return [...prev, { materialCode: spare.materialCode, description: spare.description, qty: 1, rate: spare.mrp, discount: 0 }];
    });
    setInvoiceSearchQ('');
  }
  function updateInvoiceLine(code, key, val) {
    setInvoiceItems(prev => prev.map(i => i.materialCode === code ? { ...i, [key]: parseFloat(val) || 0 } : i));
  }
  function removeInvoiceLine(code) {
    setInvoiceItems(prev => prev.filter(i => i.materialCode !== code));
  }
  function calcInvoiceTotals() {
    const subtotal = invoiceItems.reduce((s, i) => {
      const lineTotal = (parseFloat(i.rate) || 0) * (parseFloat(i.qty) || 0);
      const disc = Math.min(parseFloat(i.discount) || 0, lineTotal);
      return s + lineTotal - disc;
    }, 0);
    const taxAmt = Math.round(subtotal * 0.18);
    return { subtotal, taxAmt, total: subtotal + taxAmt };
  }
  async function loadInvoiceHistory() {
    if (!sfTabSession?.sfId) return;
    setInvHistLoading(true);
    try {
      const r = await fetch('/api/invoices?sfId=' + sfTabSession.sfId);
      const d = await r.json();
      setInvoiceHistory(d.invoices || []);
    } catch(e) {}
    setInvHistLoading(false);
  }
  async function generateInvoicePdf() {
    if (!invoiceCust.name) return alert('Customer name is required');
    if (invoiceItems.length === 0) return alert('Add at least one item');
    const { subtotal, taxAmt, total } = calcInvoiceTotals();
    const invNo = invoiceNo || ('INV-' + (sfTabSession?.sfId || '') + '-' + Date.now());
    const invoiceData = {
      invoiceNo: invNo, date: new Date().toLocaleDateString('en-IN'),
      customer: invoiceCust, sfName: sfTabSession?.sfName, sfId: sfTabSession?.sfId,
      taxType: invoiceTaxType, items: invoiceItems, subtotal, taxAmt, total,
    };
    try {
      await fetch('/api/invoices', { method:'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ sfId: sfTabSession.sfId, invoice: invoiceData }) });
      await fetch('/api/inventory/spares', { method:'POST', headers:{'Content-Type':'application/json'},
        body: JSON.stringify({ sfId: sfTabSession.sfId, action:'recordUsage',
          items: invoiceItems.map(i => ({ materialCode: i.materialCode, qty: i.qty })) }) });
      setSfSpares(prev => {
        const updated = [...prev];
        invoiceItems.forEach(ii => {
          const idx = updated.findIndex(s => s.materialCode === ii.materialCode);
          if (idx > -1) updated[idx] = { ...updated[idx],
            stock: Math.max(0, (updated[idx].stock||0) - (parseFloat(ii.qty)||0)) };
        });
        return updated;
      });
    } catch(e) { console.log('Invoice save error:', e); }
    try {
      const { jsPDF } = await import('jspdf');
      await import('jspdf-autotable');
      const doc = new jsPDF({ unit:'mm', format:'a4' });
      const W=210, M=14; let y=0;
      doc.setFillColor(29,78,216); doc.rect(0,0,W,28,'F');
      doc.setTextColor(255,255,255); doc.setFont('helvetica','bold'); doc.setFontSize(13);
      doc.text('TAX INVOICE', M, 13);
      doc.setFontSize(8); doc.setFont('helvetica','normal');
      doc.text(sfTabSession?.sfName||'', M, 20);
      if (sfTabSession?.gst) doc.text('GSTIN: '+sfTabSession.gst, M, 25);
      doc.text('Invoice No: '+invNo, W-M, 13, {align:'right'});
      doc.text('Date: '+invoiceData.date, W-M, 20, {align:'right'});
      y=36;
      doc.setTextColor(30,30,30); doc.setFont('helvetica','bold'); doc.setFontSize(9);
      doc.text('Bill To:', M, y); y+=5;
      doc.setFont('helvetica','normal'); doc.setFontSize(8);
      doc.text(invoiceCust.name, M, y); y+=4;
      if (invoiceCust.mobile)  { doc.text('Mobile: '+invoiceCust.mobile, M, y); y+=4; }
      if (invoiceCust.gstin)   { doc.text('GSTIN: '+invoiceCust.gstin, M, y); y+=4; }
      if (invoiceCust.address) { doc.text(invoiceCust.address, M, y); y+=4; }
      y+=4;
      const rows = invoiceItems.map(i => {
        const lt=(i.rate||0)*(i.qty||0), disc=Math.min(i.discount||0,lt);
        return [i.materialCode, i.description, i.qty,
          'Rs.'+fmtINR(i.rate), i.discount?'-Rs.'+fmtINR(disc):'--', 'Rs.'+fmtINR(lt-disc)];
      });
      doc.autoTable({ startY:y, margin:{left:M,right:M},
        head:[['Code','Description','Qty','Rate','Disc.','Amount']],
        body:rows, styles:{fontSize:8,cellPadding:2.5},
        headStyles:{fillColor:[30,30,30],textColor:[255,255,255]}, theme:'grid' });
      y=doc.lastAutoTable.finalY+8;
      const bx=W-M-65;
      doc.setFontSize(8); doc.setTextColor(80,80,80); doc.setFont('helvetica','normal');
      doc.text('Sub Total',bx,y); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30);
      doc.text('Rs.'+fmtINR(subtotal),W-M,y,{align:'right'}); y+=6;
      if (invoiceTaxType==='intra') {
        doc.setFont('helvetica','normal'); doc.setTextColor(80,80,80);
        doc.text('CGST @ 9%',bx,y); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30);
        doc.text('Rs.'+fmtINR(Math.round(taxAmt/2)),W-M,y,{align:'right'}); y+=6;
        doc.setFont('helvetica','normal'); doc.setTextColor(80,80,80);
        doc.text('SGST @ 9%',bx,y); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30);
        doc.text('Rs.'+fmtINR(Math.round(taxAmt/2)),W-M,y,{align:'right'}); y+=6;
      } else {
        doc.setFont('helvetica','normal'); doc.setTextColor(80,80,80);
        doc.text('IGST @ 18%',bx,y); doc.setFont('helvetica','bold'); doc.setTextColor(30,30,30);
        doc.text('Rs.'+fmtINR(taxAmt),W-M,y,{align:'right'}); y+=6;
      }
      doc.setDrawColor(200,200,200); doc.line(bx,y,W-M,y); y+=4;
      doc.setFontSize(10); doc.setFont('helvetica','bold'); doc.setTextColor(29,78,216);
      doc.text('TOTAL',bx,y); doc.text('Rs.'+fmtINR(total),W-M,y,{align:'right'});
      doc.save('Invoice_'+invNo+'.pdf');
    } catch(e) { console.log('PDF error:',e); }
    setShowInvoice(false); setInvoiceItems([]);
    setInvoiceCust({name:'',mobile:'',address:'',gstin:''});
    setInvoiceNo(''); setInvoiceSearchQ('');
    setSpareMsg('Invoice generated & downloaded!'); setTimeout(()=>setSpareMsg(''),3000);
  }
  async function fetchGstCaptcha() {
    setGstError(''); setGstSuccess(false);
    try {
      const res = await fetch('/api/gst/captcha');
      const json = await res.json();
      setGstSession(json.sessionId || '');
      setGstCaptcha(json.captcha || '');
    } catch(e) { setGstError('Could not load captcha. Try again.'); }
  }
  async function verifyGst() {
    if (!invoiceCust.gstin || invoiceCust.gstin.length < 15) return setGstError('Enter valid 15-digit GST number');
    if (!gstCaptchaInput) return setGstError('Enter captcha code');
    setGstVerifying(true); setGstError('');
    try {
      const res = await fetch('/api/gst/verify', {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ sessionId: gstSession, gstin: invoiceCust.gstin, captcha: gstCaptchaInput })
      });
      const json = await res.json();
      if (!res.ok) return setGstError(json.error || 'Verification failed');
      setInvoiceCust(p => ({ ...p, name: json.tradeName || json.legalName || p.name }));
      setGstSuccess(true);
    } catch(e) { setGstError('Verification failed. Try again.'); }
    setGstVerifying(false);
  }

  // ── Render ─────────────────────────────────────────────────────────────────
  return (
    <>
      <Head>
        <title>TCR - GENERAL HVAC</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=DM+Mono:wght@400;500&display=swap" rel="stylesheet"/>
      </Head>

      <style jsx global>{`
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:'Poppins',sans-serif;background:#E5DDD5;min-height:100vh;padding:0 0 66px 0;color:#111827}
        :root{--brand:#E8001D;--green:#16A34A;--green2:#15803D;--dark:#111827;--mid:#374151;--muted:#6B7280;--light:#F3F4F6;--border:#E5E7EB}
        .tab-bar{position:fixed;bottom:0;left:0;right:0;background:white;border-top:1px solid #E5E7EB;display:flex;z-index:100;box-shadow:0 -2px 10px rgba(0,0,0,.08)}
        .tab-btn{flex:1;padding:10px 4px 8px;border:none;background:none;font-family:'Poppins',sans-serif;font-size:10px;font-weight:500;color:#9CA3AF;cursor:pointer;display:flex;flex-direction:column;align-items:center;gap:2px}
        .tab-btn.act{color:#E8001D;font-weight:700}
        .tab-icon{font-size:18px}
        .wrap{max-width:480px;margin:0 auto;padding:10px 8px 10px}
        .hdr{background:linear-gradient(135deg,#E8001D,#9B0013);border-radius:14px 14px 0 0;padding:14px 16px;color:white;position:relative;overflow:hidden}
        .hdr::after,.hdr::before{content:'';position:absolute;background:rgba(255,255,255,.05);border-radius:50%}
        .hdr::after{right:-15px;top:-15px;width:90px;height:90px}
        .hdr::before{right:35px;bottom:-30px;width:120px;height:120px}
        .ht{display:flex;align-items:center;gap:10px;position:relative;z-index:1}
        .hco{font-size:12px;font-weight:700}.hsub{font-size:9px;color:rgba(255,255,255,.6);margin-top:1px}
        .hssd{font-size:9px;color:rgba(255,255,255,.75);margin-top:2px;font-style:italic;min-height:14px}
        .wa-bubble{background:white;border-radius:0 0 14px 14px;overflow:hidden;box-shadow:0 4px 20px rgba(0,0,0,.12)}
        .wa-inner{background:#ECE5DD;padding:8px 10px}
        .wa-msg{background:white;border-radius:3px 14px 14px 14px;padding:14px 14px 10px;box-shadow:0 1px 2px rgba(0,0,0,.08)}
        .wa-ts{font-size:10px;color:#9CA3AF;text-align:right;margin-top:5px;display:flex;justify-content:flex-end;align-items:center;gap:3px}
        .sec{padding:12px 14px;border-bottom:1px solid var(--border)}
        .sec-h{display:flex;align-items:center;gap:6px;font-size:12px;font-weight:600;color:var(--dark);margin-bottom:10px}
        .sec-h svg{color:var(--brand);flex-shrink:0}
        .fg{display:grid;grid-template-columns:1fr 1fr;gap:8px;margin-bottom:6px}
        .fg.one{grid-template-columns:1fr}
        .field label{display:block;font-size:9.5px;font-weight:600;color:var(--muted);text-transform:uppercase;letter-spacing:.5px;margin-bottom:3px}
        .field input,.field textarea,.field select{width:100%;padding:8px 10px;border:1.5px solid var(--border);border-radius:8px;font-family:'Poppins',sans-serif;font-size:12px;color:var(--dark);outline:none;background:white;transition:border-color .15s}
        .field input:focus,.field textarea:focus,.field select:focus{border-color:#93C5FD}
        .field textarea{resize:none;height:52px;font-size:11.5px}
        .ton-grid{display:grid;grid-template-columns:repeat(3,1fr);gap:6px}
        .ton-opt{padding:8px 4px;border:1.5px solid var(--border);border-radius:9px;text-align:center;font-size:11px;font-weight:500;color:var(--muted);cursor:pointer;background:white;transition:all .15s;user-select:none}
        .ton-opt:hover{border-color:#93C5FD;color:var(--dark)}
        .ton-opt.sel{border-color:#E8001D;background:#FFF5F5;color:#E8001D;font-weight:600}
        .unit-block{background:var(--light);border-radius:9px;padding:9px 10px;margin-bottom:6px}
        .unit-label{font-size:10px;font-weight:600;color:var(--brand);margin-bottom:6px}
        .ct{width:100%;border-collapse:collapse;font-size:11px}
        .ct thead th{padding:6px 8px;background:var(--dark);color:white;font-size:10px;font-weight:500;text-align:left}
        .ct thead th:nth-child(3),.ct thead th:nth-child(4){text-align:center}
        .ct thead th:last-child{text-align:right}
        .ct tbody tr{border-bottom:1px solid var(--border)}
        .ct tbody tr.disabled{opacity:.35}
        .ct tbody tr.disabled input{pointer-events:none;background:var(--light)}
        .ct td{padding:5px 8px;color:var(--mid);vertical-align:middle}
        .ct td.dc{font-size:10.5px;line-height:1.35;color:var(--mid)}
        .ct td.no{font-family:'DM Mono',monospace;font-size:9.5px;color:var(--muted);width:24px}
        .ct td.ra{text-align:right;font-family:'DM Mono',monospace;font-size:10.5px;font-weight:600;color:var(--dark);width:68px}
        .ct input[type=number]{width:56px;padding:4px 6px;border:1.5px solid var(--border);border-radius:6px;font-family:'DM Mono',monospace;font-size:12px;font-weight:600;text-align:center;color:var(--dark);outline:none}
        .ct input[type=number]:focus{border-color:#93C5FD}
        .gst-row{display:flex;align-items:center;gap:10px;padding:9px 12px;background:#F0FDF4;border:1px solid #BBF7D0;border-radius:9px;margin:8px 14px}
        .gst-toggle{position:relative;display:inline-block;width:38px;height:22px;flex-shrink:0}
        .gst-toggle input{opacity:0;width:0;height:0}
        .gst-slider{position:absolute;inset:0;background:#9CA3AF;border-radius:11px;cursor:pointer;transition:.2s}
        .gst-slider:before{content:'';position:absolute;height:16px;width:16px;left:3px;bottom:3px;background:white;border-radius:50%;transition:.2s}
        input:checked + .gst-slider{background:#16A34A}
        input:checked + .gst-slider:before{transform:translateX(16px)}
        .gst-lbl{font-size:12px;font-weight:500;color:var(--dark)}
        .gst-num{margin-top:6px;padding:0 14px 10px}
        .gst-num input{width:100%;padding:8px 10px;border:1.5px solid var(--border);border-radius:8px;font-family:'DM Mono',monospace;font-size:13px;letter-spacing:2px;color:var(--dark);outline:none;text-transform:uppercase}
        .tots{padding:10px 14px;background:#FAFAFA;border-top:2px solid var(--border)}
        .tr{display:flex;justify-content:space-between;padding:3px 0;font-size:11.5px;color:var(--mid);border-bottom:1px dashed var(--border)}
        .tr:last-of-type{border-bottom:none}
        .tra{font-family:'DM Mono',monospace;font-weight:600;color:var(--dark)}
        .grand{background:linear-gradient(135deg,#111827,#1F2937);border-radius:10px;padding:11px 13px;margin-top:9px;display:flex;justify-content:space-between;align-items:center}
        .gl{font-size:11px;color:rgba(255,255,255,.6)}.gl small{display:block;font-size:9px;color:rgba(255,255,255,.3);margin-top:1px}
        .ga{font-family:'DM Mono',monospace;font-size:20px;font-weight:700;color:white}.ga span{font-size:12px;color:rgba(255,255,255,.5)}
        .submit-wrap{padding:12px 14px}
        .err{background:#FFF0F0;border:1px solid #FECACA;border-radius:8px;padding:8px 11px;font-size:11.5px;color:#DC2626;margin-bottom:10px}
        .btn-submit{width:100%;padding:14px;background:linear-gradient(135deg,#25D366,#128C7E);color:white;border:none;border-radius:12px;font-family:'Poppins',sans-serif;font-size:14px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:8px;box-shadow:0 4px 14px rgba(37,211,102,.3);transition:all .2s}
        .btn-submit:disabled{background:#9CA3AF;box-shadow:none;cursor:not-allowed}
        .btn-amt{font-family:'DM Mono',monospace;background:rgba(255,255,255,.2);padding:3px 8px;border-radius:20px;font-size:12px}
        .pending-wrap{padding:24px 16px;text-align:center}
        .pulse-ring{width:80px;height:80px;border-radius:50%;background:#DCFCE7;display:flex;align-items:center;justify-content:center;margin:0 auto 16px;font-size:38px;animation:pulse 2s ease-in-out infinite}
        @keyframes pulse{0%,100%{box-shadow:0 0 0 0 rgba(22,163,74,.3)}50%{box-shadow:0 0 0 16px rgba(22,163,74,0)}}
        .ptitle{font-size:17px;font-weight:700;color:var(--dark);margin-bottom:6px}
        .pmsg{font-size:12px;color:var(--muted);line-height:1.6;margin-bottom:16px}
        .timer{font-family:'DM Mono',monospace;font-size:13px;color:var(--muted);background:var(--light);padding:5px 14px;border-radius:20px;display:inline-block;margin-bottom:18px}
        .wa-btn{width:100%;padding:13px;background:linear-gradient(135deg,#25D366,#128C7E);color:white;border:none;border-radius:11px;font-family:'Poppins',sans-serif;font-size:13.5px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:8px;margin-bottom:8px}
        .wa-btn-sm{width:100%;padding:10px;background:none;border:1.5px solid #25D366;color:#128C7E;border-radius:11px;font-family:'Poppins',sans-serif;font-size:12.5px;font-weight:600;cursor:pointer;margin-bottom:10px}
        .back-btn{background:none;border:1.5px solid var(--border);border-radius:10px;padding:9px;width:100%;font-family:'Poppins',sans-serif;font-size:12.5px;color:var(--muted);cursor:pointer;margin-top:4px}
        
        
        .info-card{background:#F0FDF4;border:1px solid #BBF7D0;border-radius:10px;padding:11px 13px;text-align:left;margin-bottom:10px;font-size:11px;color:#166534}
        .done-wrap{padding:20px 16px 28px;text-align:center}
        .done-icon{width:70px;height:70px;background:#DCFCE7;border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 12px;font-size:34px}
        .ref-box{font-family:'DM Mono',monospace;font-size:11.5px;background:var(--light);padding:5px 12px;border-radius:8px;color:var(--dark);display:inline-block;margin:8px 0}
        .btn-new{width:100%;padding:12px;background:linear-gradient(135deg,#E8001D,#9B0013);color:white;border:none;border-radius:10px;font-family:'Poppins',sans-serif;font-size:13px;font-weight:600;cursor:pointer;margin-top:8px}
        .btn-dl{width:100%;padding:12px;background:linear-gradient(135deg,#1D4ED8,#1E40AF);color:white;border:none;border-radius:10px;font-family:'Poppins',sans-serif;font-size:13px;font-weight:600;cursor:pointer;margin-top:10px;display:flex;align-items:center;justify-content:center;gap:7px}
        .locked-field{padding:8px 10px;background:#F9FAFB;border:1.5px solid #E5E7EB;border-radius:8px;font-size:12px;color:#111827;font-weight:500;display:flex;align-items:center;gap:5px}
        @keyframes spin{to{transform:rotate(360deg)}}
      `}</style>

      {/* Bottom Tab Bar */}
      <div className="tab-bar">
        <button className={"tab-btn"+(mainTab==='tcr'?' act':'')} onClick={()=>setMainTab('tcr')}>
          <span className="tab-icon">📋</span>TCR
        </button>
        <button className={"tab-btn"+(mainTab==='master'?' act':'')} onClick={()=>setMainTab('master')}>
          <span className="tab-icon">📦</span>Master
        </button>
        <button className={"tab-btn"+(mainTab==='sf'?' act':'')} onClick={()=>setMainTab('sf')}>
          <span className="tab-icon">🏢</span>SF
        </button>
        <button className={"tab-btn"+(mainTab==='inventory'?' act':'')} onClick={()=>setMainTab('inventory')}>
          <span className="tab-icon">🔧</span>My Stock
        </button>
      </div>

      {/* ═══════════════════════════════════════════════════════ TCR TAB ═══ */}
      {mainTab==='tcr' && (
        <div className="wrap">
          <div className="hdr">
            <div className="ht">
              <div>
                <div className="hco">GENERAL HVAC Solutions India Pvt Ltd</div>
                <div className="hsub">Authorized Service Partner - TCR cum Customer Confirmation</div>
                <div className="hssd" id="ssdDisp">
                  {sessionTech ? `📍 ${sessionTech.sfName}` : ""}
                </div>
              </div>
              {authed && (
                <button onClick={fullLogout}
                  style={{marginLeft:'auto',background:'rgba(255,255,255,.15)',border:'1px solid rgba(255,255,255,.3)',color:'white',padding:'4px 10px',borderRadius:8,fontSize:10,cursor:'pointer',flexShrink:0,position:'relative',zIndex:1}}>
                  Logout
                </button>
              )}
            </div>
          </div>

          <div className="wa-bubble">
            <div className="wa-inner">
              <div className="wa-msg">

                {/* ─── Login Screen ─── */}
                {!authed && (
                  <div style={{padding:"40px 20px",textAlign:"center"}}>
                    <div style={{fontSize:32,marginBottom:12}}>🔐</div>
                    <div style={{fontSize:16,fontWeight:700,marginBottom:6}}>Technician Login</div>
                    <div style={{fontSize:12,color:"#6B7280",marginBottom:20}}>Enter your Tech ID and password</div>
                    <input
                      value={techIdInput}
                      onChange={e=>setTechIdInput(e.target.value.toUpperCase())}
                      onKeyDown={e=>e.key==='Enter'&&handleLogin()}
                      placeholder="Tech ID  (e.g. TECH001)"
                      style={{width:"100%",padding:"10px 14px",border:"1.5px solid #E5E7EB",borderRadius:10,fontSize:13,marginBottom:8,outline:"none",textAlign:"center",letterSpacing:2,fontFamily:"monospace"}}
                    />
                    <input
                      type="password"
                      value={pwdInput}
                      onChange={e=>setPwdInput(e.target.value)}
                      onKeyDown={e=>e.key==='Enter'&&handleLogin()}
                      placeholder="Password"
                      style={{width:"100%",padding:"10px 14px",border:"1.5px solid #E5E7EB",borderRadius:10,fontSize:14,marginBottom:8,outline:"none",textAlign:"center",letterSpacing:2}}
                    />
                    {pwdErr && <div style={{color:"#DC2626",fontSize:12,marginBottom:8}}>{pwdErr}</div>}
                    <button onClick={handleLogin} disabled={loginLoading}
                      style={{width:"100%",padding:"12px",background:"linear-gradient(135deg,#E8001D,#9B0013)",color:"white",border:"none",borderRadius:10,fontSize:14,fontWeight:600,cursor:"pointer",opacity:loginLoading?.6:1}}>
                      {loginLoading ? 'Verifying…' : 'Login'}
                    </button>
                  </div>
                )}

                {/* ─── TCR Form ─── */}
                {authed && screen === "form" && <>
                  <div className="sec">
                    <div className="sec-h">
                      <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/></svg>
                      Customer &amp; Service Details
                    </div>
                    <div className="fg">
                      <div className="field"><label>Customer Name *</label><input value={f.custName} onChange={e=>field("custName",e.target.value)} placeholder="Full name"/></div>
                      <div className="field"><label>Mobile Number *</label><input type="tel" maxLength={10} value={f.mobile} onChange={e=>field("mobile",e.target.value)} placeholder="10-digit"/></div>
                    </div>
                    <div className="fg">
                      <div className="field"><label>Call / Job No. *</label><input value={f.callNo} onChange={e=>field("callNo",e.target.value)} placeholder="e.g. KOL110126000001"/></div>
                      <div className="field"><label>Service Date</label><input type="date" value={f.serviceDate} onChange={e=>field("serviceDate",e.target.value)}/></div>
                    </div>
                    {/* Technician & SF — locked from session */}
                    <div className="fg">
                      <div className="field">
                        <label>Technician</label>
                        <div className="locked-field">
                          🔧 <span style={{fontWeight:600}}>{sessionTech?.techName}</span>
                          <span style={{fontSize:10,color:"#6B7280",fontFamily:"monospace",marginLeft:2}}>({sessionTech?.techId})</span>
                        </div>
                      </div>
                      <div className="field">
                        <label>Service Centre</label>
                        <div className="locked-field">
                          🏢 <span style={{fontWeight:600}}>{sessionTech?.sfName}</span>
                        </div>
                      </div>
                    </div>
                    <div className="fg one">
                      <div className="field"><label>Installation Address</label><textarea value={f.address} onChange={e=>field("address",e.target.value)} placeholder="Site address (optional)"/></div>
                    </div>
                  </div>

                  <div className="sec">
                    <div className="sec-h">
                      <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/></svg>
                      Model Details
                    </div>
                    <div style={{marginBottom:10}}>
                      <div style={{fontSize:"9.5px",fontWeight:600,color:"var(--muted)",textTransform:"uppercase",letterSpacing:".5px",marginBottom:6}}>AC Tonnage / Type *</div>
                      <div className="ton-grid">
                        {tonOptions.map(o => (
                          <div key={o.v} className={"ton-opt"+(f.tonnage===o.v?" sel":"")} onClick={()=>field("tonnage",o.v)}>{o.l}</div>
                        ))}
                      </div>
                    </div>
                    <div className="fg" style={{marginBottom:8}}>
                      <div className="field"><label>No. of Units</label>
                        <select value={f.unitCount} onChange={e=>field("unitCount",parseInt(e.target.value))}>
                          {Array.from({length:20},(_,i)=><option key={i+1} value={i+1}>{i+1} Unit{i>0?"s":""}</option>)}
                        </select>
                      </div>
                    </div>
                    {units.map((u,i) => (
                      <div className="unit-block" key={i}>
                        <div className="unit-label">Unit {i+1}</div>
                        <div className="fg">
                          <div className="field"><label>Model No.</label><input value={u.model} onChange={e=>updateUnit(i,"model",e.target.value)} placeholder="Model number"/></div>
                          <div className="field"><label>Serial No.</label><input value={u.serial} onChange={e=>updateUnit(i,"serial",e.target.value)} placeholder="Serial number"/></div>
                          <div className="field" style={{gridColumn:"1/-1"}}><label>Pipe Size (Pair) *</label>
                            <div style={{display:"flex",gap:6,flexWrap:"wrap",marginTop:3}}>
                              {["1/4 & 1/2","3/8 & 5/8","1/2 & 5/8"].map(ps=>(
                                <div key={ps} onClick={()=>updateUnit(i,"pipeSize",ps)}
                                  style={{padding:"5px 12px",borderRadius:20,fontSize:11,fontWeight:500,cursor:"pointer",userSelect:"none",
                                    background:u.pipeSize===ps?"#DBEAFE":"white",
                                    border:u.pipeSize===ps?"1.5px solid #3B82F6":"1.5px solid #E5E7EB",
                                    color:u.pipeSize===ps?"#1D4ED8":"#6B7280"}}>
                                  {u.pipeSize===ps?"✓ ":""}{ps}
                                </div>
                              ))}
                            </div>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>

                  <div className="sec">
                    <div className="sec-h">
                      <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/></svg>
                      Installation Charges
                    </div>
                    <div style={{display:'flex',alignItems:'center',gap:10,padding:'8px 10px',background:'#FFFBEB',border:'1px solid #FDE68A',borderRadius:9,marginBottom:10}}>
                      <label style={{position:'relative',display:'inline-block',width:38,height:22,flexShrink:0}}>
                        <input type="checkbox" checked={custStand} onChange={e=>setCustStand(e.target.checked)} style={{opacity:0,width:0,height:0}}/>
                        <span style={{position:'absolute',inset:0,background:custStand?'#16A34A':'#9CA3AF',borderRadius:11,cursor:'pointer',transition:'.2s'}}></span>
                        <span style={{position:'absolute',height:16,width:16,left:custStand?19:3,bottom:3,background:'white',borderRadius:'50%',transition:'.2s'}}></span>
                      </label>
                      <span style={{fontSize:12,fontWeight:500,color:'#92400E'}}>Customer supplies ODU Stand <strong>(Fixing only @ ₹250)</strong></span>
                    </div>
                    <div style={{display:'flex',alignItems:'center',gap:10,padding:'8px 10px',background:'#FFF7ED',border:'1px solid #FED7AA',borderRadius:9,marginBottom:10}}>
                      <label style={{position:'relative',display:'inline-block',width:38,height:22,flexShrink:0}}>
                        <input type="checkbox" checked={custMaterials} onChange={e=>setCustMaterials(e.target.checked)} style={{opacity:0,width:0,height:0}}/>
                        <span style={{position:'absolute',inset:0,background:custMaterials?'#16A34A':'#9CA3AF',borderRadius:11,cursor:'pointer',transition:'.2s'}}></span>
                        <span style={{position:'absolute',height:16,width:16,left:custMaterials?19:3,bottom:3,background:'white',borderRadius:'50%',transition:'.2s'}}></span>
                      </label>
                      <span style={{fontSize:12,fontWeight:500,color:'#9A3412'}}>Customer supplies all materials <strong>(Laying charges only)</strong></span>
                    </div>
                    <table className="ct" style={{marginBottom:10}}>
                      <thead><tr><th>#</th><th>Description</th><th style={{textAlign:"center"}}>Rate</th><th style={{textAlign:"center"}}>Qty (Ft)</th><th style={{textAlign:"right"}}>Amount</th></tr></thead>
                      <tbody>
                        <tr>
                          <td className="no">1</td><td className="dc">Standard Installation as per Scope of Work</td>
                          <td style={{textAlign:"center",color:"#16A34A",fontWeight:600,fontSize:10.5}}>Free</td>
                          <td style={{textAlign:"center"}}>—</td><td className="ra" style={{color:"#16A34A"}}>₹0</td>
                        </tr>
                        {addItems.map((it,i) => {
                          const dis = isDisabled(it, f.tonnage); const rate = effectiveRate(it); const amt = rate * (parseFloat(it.qty)||0);
                          const isCustMat = custMaterials && (it.no==="2.1" || it.no==="2.2" || it.no==="3" || it.no==="5");
                          return (
                            <tr key={it.no} className={dis?"disabled":""}>
                              <td className="no">{it.no}</td><td className="dc">{it.desc}</td>
                              <td style={{textAlign:"center",fontFamily:"DM Mono,monospace",fontSize:10}}>
                                ₹{rate}/{it.unit}
                                {it.no==="4"&&(f.tonnage==="2.0+"||f.tonnage==="2.0")&&<span style={{fontSize:9,background:"#FEF3C7",color:"#92400E",padding:"0 4px",borderRadius:4,marginLeft:3}}>2T+</span>}
                                {isCustMat&&<span style={{fontSize:9,background:"#DBEAFE",color:"#1E40AF",padding:"0 4px",borderRadius:4,marginLeft:3}}>Laying</span>}
                              </td>
                              <td style={{textAlign:"center"}}><input type="number" min="0" value={it.qty||""} disabled={dis} onChange={e=>updateAddItem(i,"qty",parseFloat(e.target.value)||0)} placeholder="0"/></td>
                              <td className="ra">{amt>0?`₹${fmtINR(amt)}`:"—"}</td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                    <div style={{fontSize:"9.5px",fontWeight:700,color:"#92400E",textTransform:"uppercase",letterSpacing:".5px",marginBottom:6,paddingLeft:2}}>Customer Scope - Actuals</div>
                    <div style={{display:"flex",flexWrap:"wrap",gap:6,marginBottom:8,maxHeight:90,overflowY:"auto",padding:6,background:"#FFFBEB",borderRadius:8,border:"1px solid #FDE68A"}}>
                      {actCatalog.map((cat,i) => {
                        const sel = actSelected.includes(i);
                        return (
                          <div key={i} onClick={()=>{
                            if(sel){ setActSelected(p=>p.filter(x=>x!==i)); setActItems(p=>p.filter(it=>it.desc!==cat.desc)); }
                            else { setActSelected(p=>[...p,i]); setActItems(p=>[...p,{no:String(10+i),desc:cat.desc,unit:cat.unit,rate:cat.rate,actual:0}]); }
                          }} style={{padding:"4px 10px",borderRadius:20,fontSize:11,fontWeight:500,cursor:"pointer",userSelect:"none",background:sel?"#FEF3C7":"white",border:sel?"1.5px solid #F59E0B":"1.5px solid #E5E7EB",color:sel?"#92400E":"#6B7280"}}>
                            {sel?"✓ ":""}{cat.desc}{cat.rate>0?" (Rs."+cat.rate+"/"+cat.unit+")":""}
                          </div>
                        );
                      })}
                    </div>
                    {actItems.length>0 && (
                      <table className="ct">
                        <thead><tr><th>#</th><th>Description</th><th style={{textAlign:"center"}}>Qty</th><th style={{textAlign:"right"}}>Amount</th></tr></thead>
                        <tbody>
                          {actItems.map((it,i)=>(
                            <>
                              <tr key={i}>
                                <td className="no">{it.no}</td>
                                <td className="dc">
                                  {it.desc}
                                  {custMaterials && it.desc === "Wrapping Tape" ? <small> Rs.10/Ft</small> : (it.rate>0?<small> Rs.{it.rate}/{it.unit}</small>:null)}
                                </td>
                                <td style={{textAlign:"center"}}><input type="number" min="0" value={it.actual||""} onChange={e=>updateActItem(i,e.target.value)} placeholder="0" style={{width:60}}/></td>
                                <td className="ra">{it.actual>0?(
                                  (custMaterials && it.desc === "Wrapping Tape") ? "Rs."+String((it.actual*10).toLocaleString("en-IN")) :
                                  (it.rate>0?"Rs."+String((it.actual*it.rate).toLocaleString("en-IN")):"Rs."+String(Number(it.actual).toLocaleString("en-IN")))
                                ):"—"}</td>
                              </tr>
                              {it.desc === "Miscellaneous (Please specify)" && (
                                <tr key={i+"_misc"}>
                                  <td colSpan="4" style={{padding:'6px 8px',background:'#FEFCE8'}}>
                                    <input
                                      type="text"
                                      value={miscDesc}
                                      onChange={e=>setMiscDesc(e.target.value)}
                                      placeholder="Specify the work/item..."
                                      style={{width:'100%',padding:'6px 8px',border:'1.5px solid #FDE047',borderRadius:6,fontSize:11,outline:'none'}}
                                    />
                                  </td>
                                </tr>
                              )}
                            </>
                          ))}
                        </tbody>
                      </table>
                    )}
                  </div>

                  <div className="gst-row">
                    <label className="gst-toggle"><input type="checkbox" checked={f.gstOn} onChange={e=>field("gstOn",e.target.checked)}/><span className="gst-slider"/></label>
                    <span className="gst-lbl">Apply GST @ 18%</span>
                  </div>
                  {f.gstOn && (<div className="gst-num"><div className="field"><label>GST Number *</label><input value={f.gstNumber} maxLength={15} onChange={e=>field("gstNumber",e.target.value.toUpperCase())} placeholder="15-character GST number"/></div></div>)}
                  <div className="tots">
                    <div className="tr"><span>Sub Total</span><span className="tra">₹{fmtINR(sub)}</span></div>
                    {f.gstOn && <div className="tr"><span>GST @ 18%</span><span className="tra">₹{fmtINR(gst)}</span></div>}
                    <div className="grand"><div className="gl">Total Payable<small>{f.gstOn?"Incl. GST":"Excl. GST"}</small></div><div className="ga"><span>₹</span>{fmtINR(total)}</div></div>
                  </div>
                  <div className="submit-wrap">
                    {err && <div className="err">⚠️ {err}</div>}
                    <button className="btn-submit" disabled={submitting} onClick={handleSubmit}>
                      {submitting
                        ? <><span style={{width:17,height:17,border:"2px solid rgba(255,255,255,.4)",borderTopColor:"white",borderRadius:"50%",animation:"spin .8s linear infinite",display:"inline-block"}}/>Sending…</>
                        : <><svg width="17" height="17" viewBox="0 0 24 24" fill="white"><path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/></svg>
                            Send to Customer via WhatsApp<span className="btn-amt">₹{fmtINR(total)}</span></>
                      }
                    </button>
                  </div>
                </>}

                {/* ─── Pending Screen ─── */}
                {authed && screen === "pending" && (
                  <div className="pending-wrap">
                    <div className="pulse-ring">📲</div>
                    <div className="ptitle">Awaiting Customer Confirmation</div>
                    <div className="pmsg">A secure confirmation link has been sent to the customer&apos;s WhatsApp.<br/><strong>Do not touch the customer&apos;s phone.</strong><br/>The customer must confirm on their own device.</div>
                    <div className="timer">⏱ Expires in {fmtTime(remaining)}</div>
                    <div className="info-card"><strong>🔒 Security Note:</strong><br/>This form is now locked. The confirmation link is unique and one-time.</div>
                    <div style={{background:'#DCF8C6',border:'1px solid #25D366',borderRadius:11,padding:'14px 16px',marginBottom:8,textAlign:'center'}}>
                      <div style={{fontSize:22,marginBottom:4}}>📲</div>
                      <div style={{fontSize:13,fontWeight:700,color:'#166534'}}>Link sent to customer&apos;s WhatsApp</div>
                      <div style={{fontSize:11,color:'#166534',marginTop:3,marginBottom:10}}>Ask the customer to open the link and confirm on their own device.</div>
                      <button onClick={()=>window.open(waLink,"_blank")} style={{padding:'8px 20px',background:'#25D366',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>↺ Resend to Customer</button>
                    </div>
                    <p style={{fontSize:10.5,color:"var(--muted)",marginBottom:12}} className="">Waiting for customer approval...</p>
                    <button className="back-btn" onClick={reset}>✕ Cancel &amp; Start New TCR</button>
                  </div>
                )}

                {/* ─── Done Screen ─── */}
                {authed && screen === "done" && (
                  <div className="done-wrap">
                    <div className="done-icon">✅</div>
                    <div style={{fontSize:17,fontWeight:700,color:"var(--dark)",marginBottom:6}}>Customer Approved!</div>
                    <p style={{fontSize:12.5,color:"var(--muted)",lineHeight:1.6}}>The customer confirmed on their own device.</p>
                    <div className="ref-box">JOB: {doneData?.callNo || f.callNo}</div>
                    <p style={{fontSize:11,color:"#9CA3AF",marginBottom:14}}>Customer: {doneData?.custName} · ₹{fmtINR(doneData?.total)}</p>
                    <div style={{background:"#F0FDF4",border:"1px solid #BBF7D0",borderRadius:10,padding:"11px 13px",fontSize:11.5,color:"#166534",textAlign:"left",marginBottom:14}}>
                      ✅ Confirmed at {doneData ? new Date().toLocaleString("en-IN") : "—"}<br/>
                      🔒 Approved independently by customer<br/>
                      📄 Approved PDF ready below
                    </div>
                    <button className="btn-dl" onClick={downloadTechPDF}>
                      <svg width="15" height="15" fill="none" stroke="white" strokeWidth="2" viewBox="0 0 24 24"><path d="M12 10v6m0 0l-3-3m3 3l3-3M3 17v3a1 1 0 001 1h16a1 1 0 001-1v-3"/></svg>
                      Download Approved PDF
                    </button>
                    <button className="btn-new" onClick={reset}>＋ Start New TCR</button>
                  </div>
                )}
              </div>

              <div className="wa-ts">
                {new Date().toLocaleTimeString("en-IN",{hour:"2-digit",minute:"2-digit"})}
                <svg width="14" height="10" viewBox="0 0 16 11" fill="#53BDEB"><path d="M15.01.227l-1.36-1.227-7.75 8.61L2.36 4.06 1 5.288l4.55 4.485zM11.45 1L6.01 7.06 4.64 5.53 3.28 6.76l2.73 2.74 6.8-7.58z"/></svg>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* ══════════════════════════════════════════════════════ MASTER TAB ═══ */}
      {mainTab==='master' && (
        <div style={{minHeight:'100vh',background:'#F3F4F6',padding:'12px 8px 20px'}}>
          <div style={{maxWidth:720,margin:'0 auto'}}>
            {!masterAuthed ? (
              <div style={{display:'flex',alignItems:'center',justifyContent:'center',padding:40}}>
                <div style={{background:'white',borderRadius:16,padding:32,width:'100%',maxWidth:380,boxShadow:'0 4px 20px rgba(0,0,0,.1)',textAlign:'center'}}>
                  <div style={{fontSize:36,marginBottom:12}}>📦</div>
                  <div style={{fontSize:18,fontWeight:700,marginBottom:4}}>Inventory Master</div>
                  <div style={{fontSize:12,color:'#6B7280',marginBottom:24}}>GENERAL HVAC Solutions</div>

                  {masterLoginStep === 'password' && <>
                    <input type="password" value={masterPwd} onChange={e=>setMasterPwd(e.target.value)}
                      onKeyDown={e=>{ if(e.key==='Enter') doMasterLogin(); }}
                      placeholder="Master password"
                      style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center'}}/>
                    {masterPwdErr&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{masterPwdErr}</div>}
                    <button onClick={doMasterLogin}
                      style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer',marginBottom:12}}>
                      Login
                    </button>
                    <button onClick={doSendOtp} disabled={masterOtpSending}
                      style={{width:'100%',padding:10,background:'none',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:13,color:'#6B7280',cursor:'pointer'}}>
                      {masterOtpSending ? 'Sending OTP...' : '🔑 Forgot password? Reset via Email'}
                    </button>
                  </>}

                  {masterLoginStep === 'otp' && <>
                    <div style={{fontSize:13,color:'#6B7280',marginBottom:16}}>OTP sent to your registered email. Enter it below.</div>
                    <input type="text" value={masterOtp} onChange={e=>setMasterOtp(e.target.value)}
                      placeholder="6-digit OTP" maxLength={6}
                      style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:20,marginBottom:8,outline:'none',textAlign:'center',letterSpacing:8}}/>
                    {masterPwdErr&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{masterPwdErr}</div>}
                    <input type="password" value={masterNewPwd} onChange={e=>setMasterNewPwd(e.target.value)}
                      placeholder="New password"
                      style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center'}}/>
                    <input type="password" value={masterNewPwd2} onChange={e=>setMasterNewPwd2(e.target.value)}
                      placeholder="Confirm new password"
                      style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center'}}/>
                    <button onClick={doVerifyOtp}
                      style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer',marginBottom:8}}>
                      Set New Password
                    </button>
                    <button onClick={()=>{setMasterLoginStep('password');setMasterPwdErr('');}}
                      style={{width:'100%',padding:10,background:'none',border:'none',fontSize:12,color:'#9CA3AF',cursor:'pointer'}}>
                      ← Back
                    </button>
                  </>}

                  {masterLoginStep === 'done' && <>
                    <div style={{fontSize:40,marginBottom:12}}>✅</div>
                    <div style={{fontSize:15,fontWeight:600,marginBottom:8}}>Password updated!</div>
                    <div style={{fontSize:13,color:'#6B7280',marginBottom:20}}>You can now log in with your new password.</div>
                    <button onClick={()=>{setMasterLoginStep('password');setMasterOtp('');setMasterNewPwd('');setMasterNewPwd2('');setMasterPwdErr('');}}
                      style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer'}}>
                      Go to Login
                    </button>
                  </>}

                </div>
              </div>
            ) : (
              <>
                <div style={{background:'linear-gradient(135deg,#E8001D,#9B0013)',borderRadius:14,padding:'14px 16px',color:'white',marginBottom:12}}>
                  <div style={{fontSize:14,fontWeight:700}}>📦 Inventory Master Dashboard</div>
                  <div style={{fontSize:10,color:'rgba(255,255,255,.6)',marginTop:2}}>GENERAL HVAC Solutions India Pvt Ltd</div>
                </div>
                {masterMsg&&<div style={{background:'#F0FDF4',border:'1px solid #BBF7D0',borderRadius:8,padding:'8px 12px',fontSize:12,color:'#166534',marginBottom:10}}>{masterMsg}</div>}

                {/* Sub-tabs */}
                <div style={{display:'flex',gap:5,marginBottom:12,overflowX:'auto',paddingBottom:2}}>
                  {[
                    ['sfs',        '🏢 SFs'],
                    ['stock',      '📊 Stock'],
                    ['technicians','👷 Techs'],
                    ['materials',  '🏷️ Prices'],
                    ['records',    '📁 Records'],
                  ].map(([k,l])=>(
                    <button key={k} onClick={()=>setMasterTab(k)}
                      style={{flexShrink:0,padding:'9px 12px',border:'none',borderRadius:10,fontFamily:'inherit',fontSize:11,fontWeight:600,cursor:'pointer',
                        background: masterTab===k ? 'white':'rgba(255,255,255,.5)',
                        color:      masterTab===k ? '#1D4ED8':'#6B7280',
                        boxShadow:  masterTab===k ? '0 2px 8px rgba(0,0,0,.1)':'none'
                      }}>
                      {l}
                    </button>
                  ))}
                </div>

                {masterLoading&&<div style={{textAlign:'center',padding:40,color:'#6B7280'}}>Loading...</div>}

                {/* ─── SERVICE CENTRES TAB (NEW) ─── */}
                {!masterLoading && masterTab==='sfs' && (
                  <div>
                    {/* Create SF */}
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Create Service Centre</div>
                      <div style={{display:'grid',gridTemplateColumns:'1fr 1fr 1fr',gap:8,marginBottom:8}}>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>SF ID</label>
                          <input value={newSfId} onChange={e=>setNewSfId(e.target.value)} placeholder="e.g. SF001"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>SF Name</label>
                          <input value={newSfName} onChange={e=>setNewSfName(e.target.value)} placeholder="Centre name"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Password</label>
                          <input value={newSfPwd} onChange={e=>setNewSfPwd(e.target.value)} placeholder="SF password"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                      </div>
                      <button onClick={createSf} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#1D4ED8,#1E40AF)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>
                        Create Service Centre
                      </button>
                    </div>

                    {/* Add Technician to SF */}
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>👷 Add Technician to SF</div>
                      <div style={{marginBottom:8}}>
                        <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Service Centre</label>
                        <select value={selSfForTech} onChange={e=>setSelSfForTech(e.target.value)}
                          style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}>
                          <option value="">Select SF...</option>
                          {sfs.map(s=><option key={s.id} value={s.id}>{s.id} — {s.name}</option>)}
                        </select>
                      </div>
                      <div style={{display:'grid',gridTemplateColumns:'1fr 1fr 1fr',gap:8,marginBottom:8}}>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Tech ID</label>
                          <input value={newSfTechId} onChange={e=>setNewSfTechId(e.target.value)} placeholder="e.g. TECH001"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Full Name</label>
                          <input value={newSfTechName} onChange={e=>setNewSfTechName(e.target.value)} placeholder="Name"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Password</label>
                          <input value={newSfTechPwd} onChange={e=>setNewSfTechPwd(e.target.value)} placeholder="Password"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                      </div>
                      <button onClick={addTechToSf} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>
                        Add Technician
                      </button>
                    </div>

                    {/* SF List */}
                    {sfLoading && <div style={{textAlign:'center',padding:20,color:'#6B7280'}}>Loading SFs…</div>}
                    {!sfLoading && sfs.length===0 && (
                      <div style={{background:'white',borderRadius:12,padding:24,textAlign:'center',color:'#9CA3AF',fontSize:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                        No service centres yet. Create one above.
                      </div>
                    )}
                    {sfs.map(sf => (
                      <div key={sf.id} style={{background:'white',borderRadius:12,marginBottom:10,boxShadow:'0 2px 8px rgba(0,0,0,.06)',overflow:'hidden'}}>
                        {/* Card header */}
                        <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',padding:'12px 14px',cursor:'pointer',background:expandedSf===sf.id?'#EFF6FF':'white'}}
                          onClick={()=>{
                            const opening = expandedSf !== sf.id;
                            setExpandedSf(opening ? sf.id : null);
                            if (opening) { setEditingSfId(null); setSfProfileDraft({}); }
                          }}>
                          <div>
                            <div style={{fontSize:13,fontWeight:700}}>{sf.name}</div>
                            <div style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>
                              ID: {sf.id} &nbsp;·&nbsp; {sf.techs?.length||0} technician{sf.techs?.length!==1?'s':''}
                              {sf.contact && <span> &nbsp;·&nbsp; 📞 {sf.contact}</span>}
                            </div>
                          </div>
                          <div style={{display:'flex',gap:8,alignItems:'center'}}>
                            <span style={{fontSize:11,color:'#6B7280'}}>{expandedSf===sf.id?'▲':'▼'}</span>
                            <button onClick={e=>{e.stopPropagation();removeSf(sf.id);}}
                              style={{padding:'3px 8px',background:'#FEF2F2',color:'#DC2626',border:'1px solid #FECACA',borderRadius:6,fontSize:10,cursor:'pointer'}}>Remove</button>
                          </div>
                        </div>

                        {expandedSf===sf.id && (
                          <div style={{borderTop:'1px solid #F3F4F6',padding:'12px 14px'}}>

                            {/* ── Profile section ── */}
                            <div style={{marginBottom:12}}>
                              <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',marginBottom:8}}>
                                <div style={{fontSize:11,fontWeight:700,color:'#1D4ED8'}}>🏢 SF Profile Details</div>
                                {editingSfId !== sf.id
                                  ? <button onClick={()=>{ setEditingSfId(sf.id); setSfProfileDraft({ sfName:sf.name, gst:sf.gst||'', address:sf.address||'', contact:sf.contact||'', email:sf.email||'' }); }}
                                      style={{padding:'4px 10px',background:'#EFF6FF',color:'#1D4ED8',border:'1px solid #BFDBFE',borderRadius:6,fontSize:11,cursor:'pointer',fontWeight:600}}>
                                      ✏️ Edit
                                    </button>
                                  : <div style={{display:'flex',gap:6}}>
                                      <button onClick={()=>updateSfProfile(sf.id, sfProfileDraft)}
                                        style={{padding:'4px 10px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:6,fontSize:11,cursor:'pointer',fontWeight:600}}>
                                        Save
                                      </button>
                                      <button onClick={()=>{ setEditingSfId(null); setSfProfileDraft({}); }}
                                        style={{padding:'4px 10px',background:'#F3F4F6',color:'#6B7280',border:'none',borderRadius:6,fontSize:11,cursor:'pointer'}}>
                                        Cancel
                                      </button>
                                    </div>
                                }
                              </div>

                              {editingSfId !== sf.id ? (
                                /* ── Read-only view ── */
                                <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:6}}>
                                  {[
                                    ['SF Name',   sf.name],
                                    ['SF ID',     sf.id],
                                    ['GST No.',   sf.gst     || '—'],
                                    ['Contact',   sf.contact || '—'],
                                    ['Email',     sf.email   || '—'],
                                    ['Address',   sf.address || '—'],
                                  ].map(([label, val])=>(
                                    <div key={label} style={{background:'#F9FAFB',borderRadius:7,padding:'6px 9px'}}>
                                      <div style={{fontSize:9,fontWeight:700,color:'#9CA3AF',textTransform:'uppercase',marginBottom:2}}>{label}</div>
                                      <div style={{fontSize:11,fontWeight:600,color:'#111827',wordBreak:'break-all'}}>{val}</div>
                                    </div>
                                  ))}
                                </div>
                              ) : (
                                /* ── Edit form ── */
                                <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8}}>
                                  {[
                                    ['SF Name',    'sfName',  'text',  'Centre name'],
                                    ['GST Number', 'gst',     'text',  '15-char GST'],
                                    ['Contact No.','contact', 'tel',   '10-digit mobile'],
                                    ['Email ID',   'email',   'email', 'Email address'],
                                  ].map(([label, key, type, ph])=>(
                                    <div key={key}>
                                      <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>{label}</label>
                                      <input type={type} value={sfProfileDraft[key]||''} placeholder={ph}
                                        onChange={e=>setSfProfileDraft(p=>({...p,[key]:e.target.value}))}
                                        style={{width:'100%',padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:12,outline:'none'}}/>
                                    </div>
                                  ))}
                                  <div style={{gridColumn:'1/-1'}}>
                                    <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Address</label>
                                    <textarea value={sfProfileDraft.address||''} placeholder="Full address"
                                      onChange={e=>setSfProfileDraft(p=>({...p,address:e.target.value}))}
                                      rows={2}
                                      style={{width:'100%',padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:12,outline:'none',resize:'none',fontFamily:'inherit'}}/>
                                  </div>
                                </div>
                              )}
                            </div>

                            {/* ── Technician list ── */}
                            <div style={{borderTop:'1px solid #F3F4F6',paddingTop:10}}>
                              <div style={{fontSize:11,fontWeight:700,color:'#374151',marginBottom:6}}>👷 Technicians</div>
                              {(!sf.techs || sf.techs.length===0) && (
                                <div style={{fontSize:11,color:'#9CA3AF',textAlign:'center',padding:8}}>No technicians in this SF yet</div>
                              )}
                              {sf.techs?.map(t=>(
                                <div key={t.id} style={{display:'flex',justifyContent:'space-between',alignItems:'center',padding:'7px 0',borderBottom:'1px solid #F9FAFB'}}>
                                  <div>
                                    <span style={{fontSize:12,fontWeight:600}}>{t.name}</span>
                                    <span style={{fontSize:10,color:'#6B7280',fontFamily:'monospace',marginLeft:6}}>({t.id})</span>
                                  </div>
                                  <button onClick={()=>removeTechFromSf(sf.id,t.id)}
                                    style={{padding:'3px 8px',background:'#FEF2F2',color:'#DC2626',border:'1px solid #FECACA',borderRadius:6,fontSize:10,cursor:'pointer'}}>Remove</button>
                                </div>
                              ))}
                            </div>

                          </div>
                        )}
                      </div>
                    ))}
                  </div>
                )}

                {/* ─── STOCK TAB ─── */}
                {!masterLoading&&masterTab==='stock'&&(
                  <div>
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Add Stock to Technician</div>
                      <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:8}}>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Technician</label>
                          <select value={selTech} onChange={e=>setSelTech(e.target.value)} style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}>
                            <option value="">Select...</option>{techs.map(t=><option key={t.id} value={t.id}>{t.id} - {t.name}</option>)}
                          </select></div>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Material</label>
                          <select value={selMat} onChange={e=>setSelMat(e.target.value)} style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}>
                            <option value="">Select...</option>{materials.map(m=><option key={m.id} value={m.id}>{m.name}{m.sub?' ('+m.sub+')':''}</option>)}
                          </select></div>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Qty</label>
                          <input type="number" value={addQty} onChange={e=>setAddQty(e.target.value)} placeholder="0" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Cost Price (Rs.)</label>
                          <input type="number" value={addCost} onChange={e=>setAddCost(e.target.value)} placeholder="0" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                      </div>
                      <button onClick={addStockSubmit} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>Add Stock</button>
                    </div>
                    {techs.map(t=>{
                      const s=allStock[t.id]||{}; const tv=getStockVal(t.id); const zi=materials.filter(m=>(s[m.id]||0)===0);
                      return (
                        <div key={t.id} style={{background:'white',borderRadius:12,padding:14,marginBottom:10,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                          <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',marginBottom:8}}>
                            <div><div style={{fontSize:13,fontWeight:700}}>{t.name}</div><div style={{fontSize:10,color:'#6B7280'}}>ID: {t.id}</div></div>
                            <div style={{textAlign:'right'}}><div style={{fontSize:13,fontWeight:700,color:'#E8001D'}}>Rs.{fmtINR(tv)}</div><div style={{fontSize:9,color:'#6B7280'}}>Stock Value</div></div>
                          </div>
                          {zi.length>0&&<div style={{background:'#FEF2F2',border:'1px solid #FECACA',borderRadius:6,padding:'5px 8px',fontSize:10,color:'#DC2626',marginBottom:8}}>🔴 Zero Stock: {zi.map(m=>m.name+(m.sub?' ('+m.sub+')':'')).join(', ')}</div>}
                          <table style={{width:'100%',borderCollapse:'collapse',fontSize:11}}>
                            <thead><tr style={{background:'#F9FAFB'}}><th style={{padding:'5px 8px',textAlign:'left',fontWeight:600,fontSize:10,color:'#6B7280'}}>Material</th><th style={{padding:'5px 8px',textAlign:'center',fontWeight:600,fontSize:10,color:'#6B7280'}}>Stock</th><th style={{padding:'5px 8px',textAlign:'right',fontWeight:600,fontSize:10,color:'#6B7280'}}>Value</th></tr></thead>
                            <tbody>
                              {materials.map(m=>{ const qty=s[m.id]||0; const val=qty*m.costPrice; const zero=qty===0; return(
                                <tr key={m.id} style={{borderTop:'1px solid #F3F4F6',background:zero?'#FFF5F5':'white'}}>
                                  <td style={{padding:'5px 8px',color:zero?'#DC2626':'#111827'}}>{m.name}{m.sub?<span style={{fontSize:9,color:'#6B7280',marginLeft:4}}>({m.sub})</span>:null}</td>
                                  <td style={{padding:'5px 8px',textAlign:'center',fontFamily:'monospace',fontWeight:600,color:zero?'#DC2626':'#111827'}}>{zero?'⚠️ 0':qty} {m.unit}</td>
                                  <td style={{padding:'5px 8px',textAlign:'right',fontFamily:'monospace',color:'#6B7280'}}>Rs.{fmtINR(val)}</td>
                                </tr>);})}
                            </tbody>
                          </table>
                        </div>
                      );
                    })}
                  </div>
                )}

                {/* ─── TECHNICIANS TAB (stock tracking list) ─── */}
                {!masterLoading&&masterTab==='technicians'&&(
                  <div>
                    <div style={{background:'#EFF6FF',border:'1px solid #BFDBFE',borderRadius:10,padding:'10px 12px',marginBottom:12,fontSize:11,color:'#1E40AF'}}>
                      ℹ️ This list is for <strong>stock tracking</strong>. To create technician <strong>login accounts</strong>, use the 🏢 SFs tab.
                    </div>
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Add to Stock Tracking</div>
                      <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:8}}>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Tech ID</label>
                          <input value={newTechId} onChange={e=>setNewTechId(e.target.value)} placeholder="e.g. TECH001" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                        <div><label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Full Name</label>
                          <input value={newTechName} onChange={e=>setNewTechName(e.target.value)} placeholder="Technician name" style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/></div>
                      </div>
                      <button onClick={addTechnician} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>Add to Stock Tracking</button>
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

                {/* ─── MATERIALS / PRICES TAB ─── */}
                {!masterLoading&&masterTab==='materials'&&(
                  <div style={{background:'white',borderRadius:12,padding:14,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                    <div style={{fontSize:12,fontWeight:700,marginBottom:4}}>🏷️ Material Prices</div>
                    <div style={{fontSize:11,color:'#6B7280',marginBottom:12}}>Set cost price and selling rate</div>
                    <div style={{display:'grid',gridTemplateColumns:'1fr 80px 80px',gap:8,marginBottom:6}}>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase'}}>Material</div>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Cost</div>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Rate</div>
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

                {/* ─── RECORDS TAB ─── */}
                {!masterLoading&&masterTab==='records'&&(
                  <div>
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:4}}>📁 TCR Records</div>
                      <div style={{fontSize:11,color:'#6B7280',marginBottom:14}}>Download approved TCR data as Excel</div>
                      <button onClick={async()=>{
                        const r=await fetch('/api/records');
                        const d=await r.json();
                        downloadExcel(d,'TCR_All_Records.xlsx');
                      }} style={{width:'100%',padding:'11px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:9,fontSize:12,fontWeight:600,cursor:'pointer',marginBottom:10,display:'flex',alignItems:'center',justifyContent:'center',gap:7}}>
                        <svg width="15" height="15" fill="none" stroke="white" strokeWidth="2" viewBox="0 0 24 24"><path d="M12 10v6m0 0l-3-3m3 3l3-3M3 17v3a1 1 0 001 1h16a1 1 0 001-1v-3"/></svg>
                        Download All TCRs (Excel)
                      </button>
                      <div style={{fontSize:11,fontWeight:600,color:'#374151',marginBottom:8,marginTop:4}}>Download by Technician</div>
                      {techs.map(t=>(
                        <button key={t.id} onClick={async()=>{
                          const r=await fetch('/api/records?techId='+t.id);
                          const d=await r.json();
                          downloadExcel(d,'TCR_'+t.id+'_'+t.name.replace(/\s+/g,'_')+'.xlsx');
                        }} style={{width:'100%',padding:'9px 12px',background:'#F3F4F6',border:'1px solid #E5E7EB',borderRadius:8,fontSize:12,fontWeight:500,cursor:'pointer',marginBottom:6,display:'flex',alignItems:'center',justifyContent:'space-between',color:'#111827'}}>
                          <span>🔧 {t.name} <span style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>({t.id})</span></span>
                          <span style={{fontSize:11,color:'#16A34A',fontWeight:600}}>⬇ Excel</span>
                        </button>
                      ))}
                      {techs.length===0&&<div style={{color:'#9CA3AF',fontSize:12,textAlign:'center',padding:16}}>No technicians added yet</div>}
                    </div>
                  </div>
                )}

              </>
            )}
          </div>
        </div>
      )}

      {/* ══════════════════════════════════════ SERVICE CENTRE TAB ═══ */}
      {mainTab==='sf' && (
        <div style={{minHeight:'100vh',background:'#F3F4F6',padding:'12px 8px 20px'}}>
          <div style={{maxWidth:720,margin:'0 auto'}}>
            {!sfTabAuthed ? (
              /* ── SF Login ── */
              <div style={{display:'flex',alignItems:'center',justifyContent:'center',padding:40}}>
                <div style={{background:'white',borderRadius:16,padding:32,width:'100%',maxWidth:380,boxShadow:'0 4px 20px rgba(0,0,0,.1)',textAlign:'center'}}>
                  <div style={{fontSize:36,marginBottom:12}}>🏢</div>
                  <div style={{fontSize:18,fontWeight:700,marginBottom:4}}>Service Centre Login</div>
                  <div style={{fontSize:12,color:'#6B7280',marginBottom:24}}>Enter your SF ID and password</div>
                  <input
                    value={sfTabIdInput}
                    onChange={e=>setSfTabIdInput(e.target.value.toUpperCase())}
                    onKeyDown={e=>e.key==='Enter'&&sfTabLogin()}
                    placeholder="SF ID  (e.g. SF001)"
                    style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:13,marginBottom:8,outline:'none',textAlign:'center',letterSpacing:2,fontFamily:'monospace'}}
                  />
                  <input
                    type="password"
                    value={sfTabPwdInput}
                    onChange={e=>setSfTabPwdInput(e.target.value)}
                    onKeyDown={e=>e.key==='Enter'&&sfTabLogin()}
                    placeholder="Password"
                    style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center'}}
                  />
                  {sfTabErr&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{sfTabErr}</div>}
                  <button onClick={sfTabLogin} disabled={sfTabLoading}
                    style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer',opacity:sfTabLoading?.6:1}}>
                    {sfTabLoading ? 'Verifying…' : 'Login'}
                  </button>
                </div>
              </div>
            ) : (
              /* ── SF Dashboard ── */
              <>
                {/* Header */}
                <div style={{background:'linear-gradient(135deg,#1D4ED8,#1E40AF)',borderRadius:14,padding:'14px 16px',color:'white',marginBottom:12,display:'flex',justifyContent:'space-between',alignItems:'center'}}>
                  <div>
                    <div style={{fontSize:14,fontWeight:700}}>🏢 {sfTabSession?.sfName}</div>
                    <div style={{fontSize:10,color:'rgba(255,255,255,.6)',marginTop:2,fontFamily:'monospace'}}>ID: {sfTabSession?.sfId}</div>
                  </div>
                  <button onClick={sfTabLogout} style={{background:'rgba(255,255,255,.15)',border:'1px solid rgba(255,255,255,.3)',color:'white',padding:'5px 12px',borderRadius:8,fontSize:11,cursor:'pointer'}}>
                    Logout
                  </button>
                </div>

                {/* Profile Details Card */}
                <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                  <div style={{fontSize:11,fontWeight:700,color:'#1D4ED8',marginBottom:10}}>🏢 Service Centre Details</div>
                  <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:6}}>
                    {[
                      ['SF Name',   sfTabSession?.sfName],
                      ['SF ID',     sfTabSession?.sfId],
                      ['GST No.',   sfTabSession?.gst     || '—'],
                      ['Contact',   sfTabSession?.contact || '—'],
                      ['Email',     sfTabSession?.email   || '—'],
                    ].map(([label,val])=>(
                      <div key={label} style={{background:'#F0F7FF',borderRadius:7,padding:'7px 10px'}}>
                        <div style={{fontSize:9,fontWeight:700,color:'#93C5FD',textTransform:'uppercase',marginBottom:2}}>{label}</div>
                        <div style={{fontSize:12,fontWeight:600,color:'#1E3A8A',wordBreak:'break-all'}}>{val}</div>
                      </div>
                    ))}
                    {sfTabSession?.address && (
                      <div style={{gridColumn:'1/-1',background:'#F0F7FF',borderRadius:7,padding:'7px 10px'}}>
                        <div style={{fontSize:9,fontWeight:700,color:'#93C5FD',textTransform:'uppercase',marginBottom:2}}>Address</div>
                        <div style={{fontSize:12,fontWeight:600,color:'#1E3A8A'}}>{sfTabSession.address}</div>
                      </div>
                    )}
                    {!sfTabSession?.address && (
                      <div style={{gridColumn:'1/-1',background:'#F0F7FF',borderRadius:7,padding:'7px 10px'}}>
                        <div style={{fontSize:9,fontWeight:700,color:'#93C5FD',textTransform:'uppercase',marginBottom:2}}>Address</div>
                        <div style={{fontSize:12,fontWeight:600,color:'#1E3A8A'}}>—</div>
                      </div>
                    )}
                  </div>
                  <div style={{marginTop:8,fontSize:10,color:'#9CA3AF'}}>Contact your Master admin to update these details.</div>
                </div>

                {sfTabMsg&&<div style={{background:'#F0FDF4',border:'1px solid #BBF7D0',borderRadius:8,padding:'8px 12px',fontSize:12,color:'#166534',marginBottom:10}}>{sfTabMsg}</div>}

                {/* Sub-tabs */}
                <div style={{display:'flex',gap:5,marginBottom:12,overflowX:'auto',paddingBottom:2}}>
                  {[['stock','📊 Stock'],['technicians','👷 Techs'],['materials','🏷️ Prices'],['records','📁 Records'],['spares','🔩 Spares']].map(([k,l])=>(
                    <button key={k} onClick={()=>{
                      setSfSubTab(k);
                      if (k==='spares' && sfSpares.length===0 && sfTabSession?.sfId) loadSpares(sfTabSession.sfId);
                    }}
                      style={{flexShrink:0,padding:'9px 12px',border:'none',borderRadius:10,fontFamily:'inherit',fontSize:11,fontWeight:600,cursor:'pointer',
                        background: sfSubTab===k?'white':'rgba(255,255,255,.5)',
                        color:      sfSubTab===k?(k==='spares'?'#7C3AED':'#1D4ED8'):'#6B7280',
                        boxShadow:  sfSubTab===k?'0 2px 8px rgba(0,0,0,.1)':'none'
                      }}>{l}</button>
                  ))}
                </div>

                {sfTabDataLoading&&<div style={{textAlign:'center',padding:40,color:'#6B7280'}}>Loading…</div>}

                {/* ─── STOCK SUB-TAB ─── */}
                {!sfTabDataLoading&&sfSubTab==='stock'&&(
                  <div>
                    <div style={{background:'white',borderRadius:12,overflow:'hidden',boxShadow:'0 2px 8px rgba(0,0,0,.06)',marginBottom:12}}>
                      <div style={{background:'#1E40AF',padding:'8px 14px',display:'grid',gridTemplateColumns:'1fr 80px 80px 80px'}}>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.7)'}}>TECHNICIAN</div>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.7)',textAlign:'center'}}>ITEMS</div>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.7)',textAlign:'center'}}>ZERO</div>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.7)',textAlign:'right'}}>VALUE</div>
                      </div>
                      {sfTabTechs.length===0&&<div style={{padding:20,textAlign:'center',color:'#9CA3AF',fontSize:12}}>No technicians in this SF yet</div>}
                      {sfTabTechs.map(t=>{
                        const s=sfTabStock[t.id]||{};
                        const zeroCount=(sfTabMaterials||[]).filter(m=>(s[m.id]||0)===0).length;
                        const val=sfTabGetStockVal(t.id);
                        return(
                          <div key={t.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 80px 80px',padding:'10px 14px',borderBottom:'1px solid #F3F4F6',alignItems:'center'}}>
                            <div>
                              <div style={{fontSize:12,fontWeight:600,color:'#111827'}}>{t.name}</div>
                              <div style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>{t.id}</div>
                            </div>
                            <div style={{textAlign:'center',fontSize:12,fontWeight:600,color:'#374151'}}>{sfTabMaterials.length}</div>
                            <div style={{textAlign:'center'}}>
                              {zeroCount>0
                                ?<span style={{fontSize:11,fontWeight:700,color:'#DC2626'}}>⚠️ {zeroCount}</span>
                                :<span style={{fontSize:11,fontWeight:700,color:'#16A34A'}}>✓</span>}
                            </div>
                            <div style={{textAlign:'right',fontFamily:'monospace',fontSize:11,fontWeight:600,color:'#1D4ED8'}}>₹{fmtINR(val)}</div>
                          </div>
                        );
                      })}
                    </div>

                    {/* Per-tech stock detail cards */}
                    {sfTabTechs.map(t=>{
                      const s=sfTabStock[t.id]||{};
                      const zi=(sfTabMaterials||[]).filter(m=>(s[m.id]||0)===0);
                      return(
                        <div key={t.id} style={{background:'white',borderRadius:12,marginBottom:10,boxShadow:'0 2px 8px rgba(0,0,0,.06)',overflow:'hidden'}}>
                          <div style={{background:'#1E3A8A',padding:'8px 14px',display:'flex',justifyContent:'space-between',alignItems:'center'}}>
                            <div style={{fontSize:12,fontWeight:700,color:'white'}}>🔧 {t.name}</div>
                            <div style={{fontSize:10,color:'rgba(255,255,255,.6)',fontFamily:'monospace'}}>{t.id}</div>
                          </div>
                          {zi.length>0&&(
                            <div style={{background:'#FEF2F2',padding:'6px 12px',borderBottom:'1px solid #FECACA'}}>
                              <div style={{fontSize:11,color:'#DC2626',fontWeight:600}}>⚠️ Zero stock: {zi.map(m=>m.name+(m.sub?' ('+m.sub+')':'')).join(', ')}</div>
                            </div>
                          )}
                          <div style={{display:'grid',gridTemplateColumns:'1fr 80px 60px',padding:'6px 12px',background:'#F9FAFB',borderBottom:'1px solid #F3F4F6'}}>
                            <div style={{fontSize:9,fontWeight:700,color:'#6B7280',textTransform:'uppercase'}}>Material</div>
                            <div style={{fontSize:9,fontWeight:700,color:'#6B7280',textTransform:'uppercase',textAlign:'center'}}>Stock</div>
                            <div style={{fontSize:9,fontWeight:700,color:'#6B7280',textTransform:'uppercase',textAlign:'right'}}>Unit</div>
                          </div>
                          {(sfTabMaterials||[]).map(m=>{
                            const qty=(s[m.id]||0); const zero=qty===0;
                            return(
                              <div key={m.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 60px',padding:'8px 12px',borderBottom:'1px solid #F9FAFB',background:zero?'#FFF5F5':'white',alignItems:'center'}}>
                                <div>
                                  <div style={{fontSize:11,fontWeight:500,color:zero?'#DC2626':'#111827'}}>{m.name}</div>
                                  {m.sub&&<div style={{fontSize:10,color:'#6B7280'}}>{m.sub}</div>}
                                </div>
                                <div style={{textAlign:'center',fontFamily:'monospace',fontSize:13,fontWeight:700,color:zero?'#DC2626':'#16A34A'}}>{zero?'⚠️ 0':qty}</div>
                                <div style={{textAlign:'right',fontSize:11,color:'#6B7280'}}>{m.unit}</div>
                              </div>
                            );
                          })}
                        </div>
                      );
                    })}
                    <button onClick={()=>loadSfTabData(sfTabSession.sfId)} style={{width:'100%',padding:'10px',background:'#F3F4F6',border:'1.5px solid #E5E7EB',borderRadius:9,fontSize:12,fontWeight:600,color:'#374151',cursor:'pointer',marginTop:4}}>
                      ↻ Refresh Stock
                    </button>
                  </div>
                )}

                {/* ─── TECHNICIANS SUB-TAB ─── */}
                {!sfTabDataLoading&&sfSubTab==='technicians'&&(
                  <div>
                    {/* Add technician card */}
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:10}}>➕ Add Technician to Your SF</div>
                      <div style={{display:'grid',gridTemplateColumns:'1fr 1fr 1fr',gap:8,marginBottom:8}}>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Tech ID</label>
                          <input value={sfTabNewTechId} onChange={e=>setSfTabNewTechId(e.target.value)} placeholder="e.g. TECH001"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Full Name</label>
                          <input value={sfTabNewTechName} onChange={e=>setSfTabNewTechName(e.target.value)} placeholder="Name"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                        <div>
                          <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Password</label>
                          <input value={sfTabNewTechPwd} onChange={e=>setSfTabNewTechPwd(e.target.value)} placeholder="Password"
                            style={{width:'100%',padding:'7px 8px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:12,outline:'none'}}/>
                        </div>
                      </div>
                      <button onClick={sfTabAddTech} style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#1D4ED8,#1E40AF)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer'}}>
                        Add Technician
                      </button>
                    </div>

                    {/* Technician list */}
                    <div style={{background:'white',borderRadius:12,overflow:'hidden',boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{background:'#1E40AF',padding:'8px 14px'}}>
                        <div style={{fontSize:11,fontWeight:700,color:'white'}}>👷 Your Technicians ({sfTabTechs.length})</div>
                      </div>
                      {sfTabTechs.length===0&&<div style={{padding:24,textAlign:'center',color:'#9CA3AF',fontSize:12}}>No technicians added yet</div>}
                      {sfTabTechs.map(t=>(
                        <div key={t.id} style={{display:'flex',justifyContent:'space-between',alignItems:'center',padding:'11px 14px',borderBottom:'1px solid #F3F4F6'}}>
                          <div>
                            <div style={{fontSize:12,fontWeight:600,color:'#111827'}}>{t.name}</div>
                            <div style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>{t.id}</div>
                          </div>
                          <button onClick={()=>sfTabRemoveTech(t.id)}
                            style={{padding:'4px 10px',background:'#FEF2F2',color:'#DC2626',border:'1px solid #FECACA',borderRadius:6,fontSize:11,cursor:'pointer',fontWeight:600}}>
                            Remove
                          </button>
                        </div>
                      ))}
                    </div>
                  </div>
                )}

                {/* ─── MATERIAL PRICES SUB-TAB ─── */}
                {!sfTabDataLoading&&sfSubTab==='materials'&&(
                  <div style={{background:'white',borderRadius:12,padding:14,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                    <div style={{fontSize:12,fontWeight:700,marginBottom:4}}>🏷️ Material Prices</div>
                    <div style={{fontSize:11,color:'#6B7280',marginBottom:12}}>Set cost price and selling rate for each material</div>
                    <div style={{display:'grid',gridTemplateColumns:'1fr 80px 80px',gap:8,marginBottom:6,padding:'0 0 4px'}}>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase'}}>Material</div>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Cost</div>
                      <div style={{fontSize:9,color:'#6B7280',fontWeight:600,textTransform:'uppercase',textAlign:'center'}}>Rate</div>
                    </div>
                    {sfTabEditMats.map((m,i)=>(
                      <div key={m.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 80px',gap:8,alignItems:'center',padding:'7px 0',borderBottom:'1px solid #F3F4F6'}}>
                        <div style={{fontSize:12,fontWeight:500}}>{m.name}{m.sub?<span style={{fontSize:10,color:'#6B7280'}}> ({m.sub})</span>:null}<span style={{fontSize:9,color:'#9CA3AF',marginLeft:4}}>{m.unit}</span></div>
                        <input type="number" value={m.costPrice} placeholder="Cost" onChange={e=>{const n=[...sfTabEditMats];n[i]={...n[i],costPrice:parseFloat(e.target.value)||0};setSfTabEditMats(n);}} style={{padding:'5px 6px',border:'1.5px solid #E5E7EB',borderRadius:6,fontSize:11,outline:'none',textAlign:'center'}}/>
                        <input type="number" value={m.sellingRate} placeholder="Rate" onChange={e=>{const n=[...sfTabEditMats];n[i]={...n[i],sellingRate:parseFloat(e.target.value)||0};setSfTabEditMats(n);}} style={{padding:'5px 6px',border:'1.5px solid #E5E7EB',borderRadius:6,fontSize:11,outline:'none',textAlign:'center'}}/>
                      </div>
                    ))}
                    <button onClick={sfTabSaveMaterialPrices} style={{width:'100%',padding:'10px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:600,cursor:'pointer',marginTop:12}}>
                      Save Prices
                    </button>
                  </div>
                )}

                {/* ─── RECORDS SUB-TAB ─── */}
                {!sfTabDataLoading&&sfSubTab==='records'&&(
                  <div>
                    <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{fontSize:12,fontWeight:700,marginBottom:4}}>📁 TCR Records — {sfTabSession?.sfName}</div>
                      <div style={{fontSize:11,color:'#6B7280',marginBottom:14}}>Download approved TCR data as Excel</div>
                      <div style={{fontSize:11,fontWeight:600,color:'#374151',marginBottom:8}}>Download by Technician</div>
                      {sfTabTechs.length===0&&<div style={{color:'#9CA3AF',fontSize:12,textAlign:'center',padding:16}}>No technicians in this SF yet</div>}
                      {sfTabTechs.map(t=>(
                        <button key={t.id} onClick={async()=>{
                          const r=await fetch('/api/records?techId='+t.id);
                          const d=await r.json();
                          downloadExcel(d,'TCR_'+t.id+'_'+t.name.replace(/\s+/g,'_')+'.xlsx');
                        }} style={{width:'100%',padding:'9px 12px',background:'#F3F4F6',border:'1px solid #E5E7EB',borderRadius:8,fontSize:12,fontWeight:500,cursor:'pointer',marginBottom:6,display:'flex',alignItems:'center',justifyContent:'space-between',color:'#111827'}}>
                          <span>🔧 {t.name} <span style={{fontSize:10,color:'#6B7280',fontFamily:'monospace'}}>({t.id})</span></span>
                          <span style={{fontSize:11,color:'#16A34A',fontWeight:600}}>⬇ Excel</span>
                        </button>
                      ))}
                    </div>
                  </div>
                )}
                {/* ─── SPARES SUB-TAB ─── */}
                {!sfTabDataLoading && sfSubTab==='spares' && (
                  <div>
                    {spareMsg && (
                      <div style={{background: spareMsg.startsWith('✅')?'#F0FDF4':'#FFF0F0',
                        border:'1px solid '+(spareMsg.startsWith('✅')?'#BBF7D0':'#FECACA'),
                        borderRadius:8,padding:'8px 12px',fontSize:12,
                        color: spareMsg.startsWith('✅')?'#166534':'#DC2626',marginBottom:10}}>
                        {spareMsg}
                      </div>
                    )}
 
                    {/* ── Invoice builder overlay ── */}
                    {showInvoice ? (
                      <div style={{background:'white',borderRadius:14,padding:16,boxShadow:'0 4px 20px rgba(0,0,0,.12)'}}>
                        {/* Invoice header */}
                        <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',marginBottom:14}}>
                          <div>
                            <div style={{fontSize:14,fontWeight:700,color:'#1D4ED8'}}>🧾 New Tax Invoice</div>
                            <div style={{fontSize:10,color:'#6B7280',marginTop:1}}>{sfTabSession?.sfName}</div>
                          </div>
                          <button onClick={()=>{setShowInvoice(false);setInvoiceItems([]);setInvoiceCust({name:'',mobile:'',address:'',gstin:''});setInvoiceNo('');}}
                            style={{background:'#F3F4F6',border:'none',padding:'5px 10px',borderRadius:7,fontSize:11,cursor:'pointer',color:'#374151'}}>
                            ✕ Cancel
                          </button>
                        </div>
 
                        {/* Invoice No + Tax type */}
                        <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:12}}>
                          <div>
                            <label style={{fontSize:9,fontWeight:700,color:'#7C3AED',textTransform:'uppercase',display:'block',marginBottom:3}}>Invoice No. (optional)</label>
                            <input value={invoiceNo} onChange={e=>setInvoiceNo(e.target.value)}
                              placeholder={'INV-' + (sfTabSession?.sfId||'') + '-XXXXXX'}
                              style={{width:'100%',padding:'7px 9px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:11,outline:'none',fontFamily:'monospace'}}/>
                          </div>
                          <div>
                            <label style={{fontSize:9,fontWeight:700,color:'#7C3AED',textTransform:'uppercase',display:'block',marginBottom:3}}>GST Type</label>
                            <select value={invoiceTaxType} onChange={e=>setInvoiceTaxType(e.target.value)}
                              style={{width:'100%',padding:'7px 9px',border:'1.5px solid #E5E7EB',borderRadius:8,fontSize:11,outline:'none',background:'white'}}>
                              <option value="intra">Intra-State (CGST + SGST)</option>
                              <option value="inter">Inter-State (IGST)</option>
                            </select>
                          </div>
                        </div>
 
                        {/* Customer details */}
                        <div style={{background:'#F0F7FF',borderRadius:10,padding:12,marginBottom:12}}>
                          <div style={{fontSize:11,fontWeight:700,color:'#1E3A8A',marginBottom:8}}>👤 Customer Details</div>
                          <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:7,marginBottom:7}}>
                            <div>
                              <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:2}}>Name *</label>
                              <input value={invoiceCust.name} onChange={e=>setInvoiceCust(p=>({...p,name:e.target.value}))}
                                placeholder="Customer name"
                                style={{width:'100%',padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:11,outline:'none'}}/>
                            </div>
                            <div>
                              <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:2}}>Mobile</label>
                              <input value={invoiceCust.mobile} onChange={e=>setInvoiceCust(p=>({...p,mobile:e.target.value}))}
                                placeholder="10-digit mobile"
                                style={{width:'100%',padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:11,outline:'none',fontFamily:'monospace'}}/>
                            </div>
                          </div>
                          <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:7}}>
                            <div>
                              <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:2}}>GSTIN (optional)</label>
                              <div style={{display:'flex',gap:4}}>
                                <input value={invoiceCust.gstin} onChange={e=>{setInvoiceCust(p=>({...p,gstin:e.target.value.toUpperCase()})); setGstSuccess(false);}}
                                  placeholder="15-char GSTIN"
                                  style={{flex:1,padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:11,outline:'none',fontFamily:'monospace',letterSpacing:1}}/>
                                {gstSuccess ? (
                                  <span style={{padding:'6px 8px',background:'#DCFCE7',borderRadius:7,fontSize:11,color:'#16A34A',fontWeight:700,whiteSpace:'nowrap'}}>✓ Verified</span>
                                ) : (
                                  <button onClick={()=>fetchGstCaptcha()} style={{padding:'7px 8px',background:'#7C3AED',color:'white',border:'none',borderRadius:7,fontSize:10,fontWeight:700,cursor:'pointer',whiteSpace:'nowrap'}}>Verify</button>
                                )}
                              </div>
                              {gstCaptcha && !gstSuccess && (
                                <div style={{marginTop:5,background:'#FFFBEB',border:'1px solid #FDE68A',borderRadius:7,padding:8}}>
                                  <div style={{fontSize:9,fontWeight:600,color:'#92400E',marginBottom:4}}>Enter captcha to verify GST</div>
                                  <div style={{display:'flex',gap:4,flexWrap:'wrap',alignItems:'center'}}>
                                    <img src={gstCaptcha.startsWith('data:') ? gstCaptcha : 'data:image/png;base64,' + gstCaptcha} alt="captcha" style={{height:36,borderRadius:4,border:'1px solid #E5E7EB'}}/>
                                    <input value={gstCaptchaInput} onChange={e=>setGstCaptchaInput(e.target.value.toUpperCase())} placeholder="Code" maxLength={6}
                                      style={{padding:'5px 6px',border:'1px solid #D1D5DB',borderRadius:5,fontSize:12,outline:'none',fontFamily:'monospace',width:70,letterSpacing:3}}/>
                                    <button onClick={verifyGst} disabled={gstVerifying} style={{padding:'5px 8px',background:'#16A34A',color:'white',border:'none',borderRadius:5,fontSize:10,fontWeight:700,cursor:'pointer'}}>{gstVerifying?'...':'Verify'}</button>
                                    <button onClick={fetchGstCaptcha} style={{padding:'5px 8px',background:'#F3F4F6',border:'1px solid #E5E7EB',borderRadius:5,fontSize:10,cursor:'pointer'}}>↻</button>
                                  </div>
                                  {gstError && <div style={{fontSize:9,color:'#DC2626',marginTop:3}}>{gstError}</div>}
                                </div>
                              )}
                            </div>
                            <div>
                              <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:2}}>Address</label>
                              <input value={invoiceCust.address} onChange={e=>setInvoiceCust(p=>({...p,address:e.target.value}))}
                                placeholder="Billing address"
                                style={{width:'100%',padding:'7px 8px',border:'1.5px solid #BFDBFE',borderRadius:7,fontSize:11,outline:'none'}}/>
                            </div>
                          </div>
                        </div>
 
                        {/* Item search / add */}
                        <div style={{marginBottom:10}}>
                          <label style={{fontSize:9,fontWeight:700,color:'#7C3AED',textTransform:'uppercase',display:'block',marginBottom:4}}>🔍 Add Spare to Invoice</label>
                          <input value={invoiceSearchQ} onChange={e=>setInvoiceSearchQ(e.target.value)}
                            placeholder="Search by code or description…"
                            style={{width:'100%',padding:'8px 10px',border:'1.5px solid #DDD6FE',borderRadius:8,fontSize:12,outline:'none'}}/>
                          {invoiceSearchQ.trim() && (
                            <div style={{border:'1px solid #E5E7EB',borderRadius:8,marginTop:4,maxHeight:160,overflowY:'auto',boxShadow:'0 4px 12px rgba(0,0,0,.08)'}}>
                              {sfSpares.filter(s =>
                                s.materialCode.toLowerCase().includes(invoiceSearchQ.toLowerCase()) ||
                                s.description.toLowerCase().includes(invoiceSearchQ.toLowerCase())
                              ).map(s=>(
                                <div key={s.materialCode} onClick={()=>addInvoiceItem(s)}
                                  style={{padding:'9px 12px',borderBottom:'1px solid #F3F4F6',cursor:'pointer',background:'white',display:'flex',justifyContent:'space-between',alignItems:'center'}}
                                  onMouseEnter={e=>e.currentTarget.style.background='#F0F7FF'}
                                  onMouseLeave={e=>e.currentTarget.style.background='white'}>
                                  <div>
                                    <span style={{fontFamily:'monospace',fontSize:10,color:'#7C3AED',marginRight:6}}>{s.materialCode}</span>
                                    <span style={{fontSize:11,color:'#111827'}}>{s.description}</span>
                                  </div>
                                  <span style={{fontSize:11,fontWeight:600,color:'#1D4ED8',fontFamily:'monospace'}}>₹{fmtINR(s.mrp)}</span>
                                </div>
                              ))}
                              {sfSpares.filter(s =>
                                s.materialCode.toLowerCase().includes(invoiceSearchQ.toLowerCase()) ||
                                s.description.toLowerCase().includes(invoiceSearchQ.toLowerCase())
                              ).length === 0 && (
                                <div style={{padding:12,textAlign:'center',color:'#9CA3AF',fontSize:11}}>No matching spare parts</div>
                              )}
                            </div>
                          )}
                        </div>
 
                        {/* Line items table */}
                        {invoiceItems.length > 0 && (
                          <div style={{marginBottom:12}}>
                            <div style={{fontSize:11,fontWeight:700,color:'#374151',marginBottom:6}}>📋 Invoice Items</div>
                            <div style={{background:'#1E3A8A',borderRadius:'8px 8px 0 0',display:'grid',gridTemplateColumns:'1fr 50px 60px 50px 20px',gap:4,padding:'6px 8px'}}>
                              {['Description','Qty','Rate','Disc.',''].map((h,i)=>(
                                <div key={i} style={{fontSize:9,fontWeight:600,color:'rgba(255,255,255,.7)',textAlign:i===1||i===2||i===3?'center':'left'}}>{h}</div>
                              ))}
                            </div>
                            {invoiceItems.map(item=>(
                              <div key={item.materialCode}
                                style={{display:'grid',gridTemplateColumns:'1fr 50px 60px 50px 20px',gap:4,padding:'7px 8px',borderBottom:'1px solid #F3F4F6',background:'white',alignItems:'center'}}>
                                <div>
                                  <div style={{fontSize:10,fontFamily:'monospace',color:'#7C3AED'}}>{item.materialCode}</div>
                                  <div style={{fontSize:11,color:'#111827',lineHeight:1.3}}>{item.description}</div>
                                </div>
                                <input type="number" min="1" value={item.qty}
                                  onChange={e=>updateInvoiceLine(item.materialCode,'qty',e.target.value)}
                                  style={{width:'100%',padding:'4px 4px',border:'1.5px solid #E5E7EB',borderRadius:5,fontSize:11,textAlign:'center',outline:'none',fontFamily:'monospace'}}/>
                                <input type="number" min="0" value={item.rate}
                                  onChange={e=>updateInvoiceLine(item.materialCode,'rate',e.target.value)}
                                  style={{width:'100%',padding:'4px 4px',border:'1.5px solid #E5E7EB',borderRadius:5,fontSize:11,textAlign:'center',outline:'none',fontFamily:'monospace'}}/>
                                <input type="number" min="0" value={item.discount}
                                  onChange={e=>updateInvoiceLine(item.materialCode,'discount',e.target.value)}
                                  placeholder="0"
                                  style={{width:'100%',padding:'4px 4px',border:'1.5px solid #E5E7EB',borderRadius:5,fontSize:11,textAlign:'center',outline:'none',fontFamily:'monospace'}}/>
                                <button onClick={()=>removeInvoiceLine(item.materialCode)}
                                  style={{background:'none',border:'none',color:'#DC2626',fontSize:14,cursor:'pointer',padding:0,lineHeight:1}}>×</button>
                              </div>
                            ))}
 
                            {/* Totals summary */}
                            {(()=>{
                              const {subtotal,taxAmt,total}=calcInvoiceTotals();
                              return (
                                <div style={{background:'#F8FAFF',border:'1px solid #DBEAFE',borderRadius:'0 0 8px 8px',padding:'10px 12px'}}>
                                  <div style={{display:'flex',justifyContent:'space-between',fontSize:11,color:'#374151',marginBottom:3}}>
                                    <span>Sub Total</span><span style={{fontFamily:'monospace',fontWeight:600}}>₹{fmtINR(subtotal)}</span>
                                  </div>
                                  {invoiceTaxType==='intra' ? (
                                    <>
                                      <div style={{display:'flex',justifyContent:'space-between',fontSize:11,color:'#374151',marginBottom:3}}>
                                        <span>CGST @ 9%</span><span style={{fontFamily:'monospace',fontWeight:600}}>₹{fmtINR(Math.round(taxAmt/2))}</span>
                                      </div>
                                      <div style={{display:'flex',justifyContent:'space-between',fontSize:11,color:'#374151',marginBottom:6}}>
                                        <span>SGST @ 9%</span><span style={{fontFamily:'monospace',fontWeight:600}}>₹{fmtINR(Math.round(taxAmt/2))}</span>
                                      </div>
                                    </>
                                  ) : (
                                    <div style={{display:'flex',justifyContent:'space-between',fontSize:11,color:'#374151',marginBottom:6}}>
                                      <span>IGST @ 18%</span><span style={{fontFamily:'monospace',fontWeight:600}}>₹{fmtINR(taxAmt)}</span>
                                    </div>
                                  )}
                                  <div style={{display:'flex',justifyContent:'space-between',fontSize:14,fontWeight:700,color:'#1D4ED8',borderTop:'1px solid #BFDBFE',paddingTop:6}}>
                                    <span>TOTAL</span><span style={{fontFamily:'monospace'}}>₹{fmtINR(total)}</span>
                                  </div>
                                </div>
                              );
                            })()}
                          </div>
                        )}
 
                        {/* Generate button */}
                        <button onClick={generateInvoicePdf}
                          style={{width:'100%',padding:'12px',background:'linear-gradient(135deg,#7C3AED,#5B21B6)',color:'white',border:'none',borderRadius:10,fontSize:13,fontWeight:700,cursor:'pointer',display:'flex',alignItems:'center',justifyContent:'center',gap:8}}>
                          <svg width="16" height="16" fill="none" stroke="white" strokeWidth="2" viewBox="0 0 24 24"><path d="M12 10v6m0 0l-3-3m3 3l3-3M3 17v3a1 1 0 001 1h16a1 1 0 001-1v-3"/></svg>
                          Generate & Download Tax Invoice PDF
                        </button>
                      </div>
 
                    ) : (
                      /* ── Spare Catalog View ── */
                      <>
                        {/* Action bar */}
                        <div style={{display:'flex',gap:8,marginBottom:12}}>
                          <div style={{flex:1,position:'relative'}}>
                            <input value={sparesSearch} onChange={e=>setSparesSearch(e.target.value)}
                              placeholder="🔍  Search material code or description…"
                              style={{width:'100%',padding:'10px 12px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:12,outline:'none',background:'white'}}/>
                            {sparesSearch && (
                              <button onClick={()=>setSparesSearch('')}
                                style={{position:'absolute',right:10,top:'50%',transform:'translateY(-50%)',background:'none',border:'none',cursor:'pointer',color:'#9CA3AF',fontSize:14}}>×</button>
                            )}
                          </div>
                          <button onClick={()=>setShowAddSpare(p=>!p)}
                            style={{flexShrink:0,padding:'10px 12px',background:'linear-gradient(135deg,#7C3AED,#5B21B6)',color:'white',border:'none',borderRadius:10,fontSize:11,fontWeight:700,cursor:'pointer'}}>
                            {showAddSpare ? '✕ Close' : '+ Add'}
                          </button>
                          <label style={{flexShrink:0,padding:'10px 12px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:10,fontSize:11,fontWeight:700,cursor:'pointer',whiteSpace:'nowrap'}}>
                            {bulkSparesUploading ? 'Uploading...' : '📤 Bulk Upload'}
                            <input type="file" accept=".csv,.xlsx,.xls" onChange={handleBulkSparesUpload} style={{display:'none'}} disabled={bulkSparesUploading}/>
                          </label>
                        </div>
 
                        {/* Add spare form */}
                        {showAddSpare && (
                          <div style={{background:'white',borderRadius:12,padding:14,marginBottom:12,boxShadow:'0 2px 10px rgba(124,58,237,.12)',border:'1.5px solid #DDD6FE'}}>
                            <div style={{fontSize:12,fontWeight:700,color:'#7C3AED',marginBottom:10}}>➕ Add New Spare Part</div>
                            <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:8}}>
                              <div>
                                <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Material Code *</label>
                                <input value={newSpare.materialCode} onChange={e=>setNewSpare(p=>({...p,materialCode:e.target.value.toUpperCase()}))}
                                  placeholder="e.g. AC-COMP-001"
                                  style={{width:'100%',padding:'7px 8px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:12,outline:'none',fontFamily:'monospace',letterSpacing:1}}/>
                              </div>
                              <div>
                                <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Stock Qty</label>
                                <input type="number" value={newSpare.stock} onChange={e=>setNewSpare(p=>({...p,stock:e.target.value}))}
                                  placeholder="Opening stock"
                                  style={{width:'100%',padding:'7px 8px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:12,outline:'none',fontFamily:'monospace'}}/>
                              </div>
                            </div>
                            <div style={{marginBottom:8}}>
                              <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>Description *</label>
                              <input value={newSpare.description} onChange={e=>setNewSpare(p=>({...p,description:e.target.value}))}
                                placeholder="e.g. Rotary Compressor 1.5T R22"
                                style={{width:'100%',padding:'7px 8px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:12,outline:'none'}}/>
                            </div>
                            <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginBottom:10}}>
                              <div>
                                <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>DP (Dealer Price) ₹</label>
                                <input type="number" value={newSpare.dp} onChange={e=>setNewSpare(p=>({...p,dp:e.target.value}))}
                                  placeholder="0.00"
                                  style={{width:'100%',padding:'7px 8px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:12,outline:'none',fontFamily:'monospace'}}/>
                              </div>
                              <div>
                                <label style={{fontSize:9,fontWeight:600,color:'#6B7280',textTransform:'uppercase',display:'block',marginBottom:3}}>MRP ₹</label>
                                <input type="number" value={newSpare.mrp} onChange={e=>setNewSpare(p=>({...p,mrp:e.target.value}))}
                                  placeholder="0.00"
                                  style={{width:'100%',padding:'7px 8px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:12,outline:'none',fontFamily:'monospace'}}/>
                              </div>
                            </div>
                            <button onClick={addSpare} disabled={sparesLoading}
                              style={{width:'100%',padding:'9px',background:'linear-gradient(135deg,#7C3AED,#5B21B6)',color:'white',border:'none',borderRadius:8,fontSize:12,fontWeight:700,cursor:'pointer',opacity:sparesLoading?.6:1}}>
                              {sparesLoading ? 'Saving…' : 'Save Spare Part'}
                            </button>
                          </div>
                        )}
 
                        {/* Stats bar */}
                        {sfSpares.length > 0 && (
                          <div style={{display:'grid',gridTemplateColumns:'repeat(4,1fr)',gap:6,marginBottom:12}}>
                            {[
                              ['Total Parts', sfSpares.length, '#7C3AED', '#F3EEFF'],
                              ['Zero Stock',  sfSpares.filter(s=>s.stock===0).length, '#DC2626', '#FFF0F0'],
                              ['🔥 High',     sfSpares.filter(s=>s.usageCount>=20).length, '#DC2626', '#FEF2F2'],
                              ['🔶 Mid',      sfSpares.filter(s=>s.usageCount>=8&&s.usageCount<20).length, '#D97706', '#FFFBEB'],
                            ].map(([label,count,color,bg])=>(
                              <div key={label} style={{background:bg,borderRadius:8,padding:'8px 6px',textAlign:'center'}}>
                                <div style={{fontSize:16,fontWeight:700,color,fontFamily:'monospace'}}>{count}</div>
                                <div style={{fontSize:9,fontWeight:600,color,marginTop:1}}>{label}</div>
                              </div>
                            ))}
                          </div>
                        )}
 
                        {/* Spare list */}
                        {sparesLoading && <div style={{textAlign:'center',padding:32,color:'#9CA3AF'}}>Loading spares…</div>}
                        {!sparesLoading && sfSpares.length === 0 && (
                          <div style={{background:'white',borderRadius:12,padding:32,textAlign:'center',boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                            <div style={{fontSize:36,marginBottom:8}}>🔩</div>
                            <div style={{fontSize:13,fontWeight:600,color:'#374151',marginBottom:4}}>No spare parts yet</div>
                            <div style={{fontSize:11,color:'#9CA3AF'}}>Click "+ Add" to upload your first spare part</div>
                          </div>
                        )}
 
                        {!sparesLoading && sfSpares.filter(s =>
                          !sparesSearch.trim() ||
                          s.materialCode.toLowerCase().includes(sparesSearch.toLowerCase()) ||
                          s.description.toLowerCase().includes(sparesSearch.toLowerCase())
                        ).map(s => {
                          const cLabel = consumptionLabel(s.usageCount || 0);
                          const isAdj  = stockAdjItem === s.materialCode;
                          const zero   = s.stock === 0;
                          return (
                            <div key={s.materialCode}
                              style={{background:'white',borderRadius:11,marginBottom:8,boxShadow:'0 2px 8px rgba(0,0,0,.06)',overflow:'hidden',
                                border: zero ? '1.5px solid #FECACA' : '1.5px solid transparent'}}>
 
                              {/* Main row */}
                              <div style={{padding:'10px 12px',display:'flex',alignItems:'flex-start',gap:10}}>
                                <div style={{flex:1,minWidth:0}}>
                                  {/* Code + consumption badge */}
                                  <div style={{display:'flex',alignItems:'center',gap:6,marginBottom:3,flexWrap:'wrap'}}>
                                    <span style={{fontFamily:'monospace',fontSize:10,fontWeight:700,color:'#7C3AED',background:'#F3EEFF',padding:'2px 6px',borderRadius:4}}>
                                      {s.materialCode}
                                    </span>
                                    <span style={{fontSize:9,fontWeight:700,padding:'2px 7px',borderRadius:20,
                                      background: cLabel.bg, color: cLabel.color, border:'1px solid '+cLabel.border}}>
                                      {cLabel.label}
                                    </span>
                                    {zero && <span style={{fontSize:9,fontWeight:700,padding:'2px 7px',borderRadius:20,background:'#FEF2F2',color:'#DC2626',border:'1px solid #FECACA'}}>⚠️ No Stock</span>}
                                  </div>
                                  <div style={{fontSize:12,fontWeight:600,color:'#111827',marginBottom:4,lineHeight:1.35}}>{s.description}</div>
                                  {/* Price row */}
                                  <div style={{display:'flex',gap:10,flexWrap:'wrap'}}>
                                    <span style={{fontSize:10,color:'#6B7280'}}>DP: <b style={{color:'#374151',fontFamily:'monospace'}}>₹{fmtINR(s.dp)}</b></span>
                                    <span style={{fontSize:10,color:'#6B7280'}}>MRP: <b style={{color:'#1D4ED8',fontFamily:'monospace'}}>₹{fmtINR(s.mrp)}</b></span>
                                    <span style={{fontSize:10,color:'#6B7280'}}>Used: <b style={{color:'#374151'}}>{s.usageCount||0} time{(s.usageCount||0)!==1?'s':''}</b></span>
                                  </div>
                                </div>
                                {/* Stock qty */}
                                <div style={{textAlign:'center',minWidth:56,flexShrink:0}}>
                                  <div style={{fontSize:20,fontWeight:700,fontFamily:'monospace',color:zero?'#DC2626':'#16A34A',lineHeight:1}}>
                                    {zero ? '0' : s.stock}
                                  </div>
                                  <div style={{fontSize:9,color:'#9CA3AF',marginTop:1}}>in stock</div>
                                </div>
                              </div>
 
                              {/* Stock adjustment panel (inline) */}
                              {isAdj && (
                                <div style={{background:'#F8F5FF',borderTop:'1px solid #EDE9FE',padding:'10px 12px'}}>
                                  <div style={{fontSize:10,fontWeight:700,color:'#7C3AED',marginBottom:7}}>Adjust Stock for {s.materialCode}</div>
                                  <div style={{display:'flex',gap:6,marginBottom:8}}>
                                    {[['add','➕ Add'],['subtract','➖ Remove'],['set','📌 Set']].map(([op,lbl])=>(
                                      <button key={op} onClick={()=>setStockAdjOp(op)}
                                        style={{flex:1,padding:'5px 4px',border:'1.5px solid '+(stockAdjOp===op?'#7C3AED':'#E5E7EB'),
                                          borderRadius:7,fontSize:10,fontWeight:600,cursor:'pointer',
                                          background:stockAdjOp===op?'#7C3AED':'white',color:stockAdjOp===op?'white':'#374151'}}>
                                        {lbl}
                                      </button>
                                    ))}
                                  </div>
                                  <div style={{display:'flex',gap:7}}>
                                    <input type="number" min="0" value={stockAdjQty} onChange={e=>setStockAdjQty(e.target.value)}
                                      placeholder="Quantity"
                                      style={{flex:1,padding:'7px 10px',border:'1.5px solid #DDD6FE',borderRadius:7,fontSize:13,outline:'none',fontFamily:'monospace'}}/>
                                    <button onClick={confirmStockAdj}
                                      style={{padding:'7px 14px',background:'linear-gradient(135deg,#7C3AED,#5B21B6)',color:'white',border:'none',borderRadius:7,fontSize:12,fontWeight:700,cursor:'pointer'}}>
                                      Save
                                    </button>
                                    <button onClick={()=>{setStockAdjItem(null);setStockAdjQty('');}}
                                      style={{padding:'7px 10px',background:'#F3F4F6',border:'none',borderRadius:7,fontSize:12,cursor:'pointer',color:'#374151'}}>
                                      ✕
                                    </button>
                                  </div>
                                </div>
                              )}
 
                              {/* Action buttons */}
                              <div style={{borderTop:'1px solid #F3F4F6',display:'flex'}}>
                                <button onClick={()=>{setStockAdjItem(isAdj?null:s.materialCode);setStockAdjQty('');setStockAdjOp('add');}}
                                  style={{flex:1,padding:'7px 4px',background:'none',border:'none',fontSize:10,fontWeight:600,color:'#7C3AED',cursor:'pointer',borderRight:'1px solid #F3F4F6'}}>
                                  📦 Stock
                                </button>
                                <button onClick={()=>{addInvoiceItem(s);setShowInvoice(true);}}
                                  style={{flex:1,padding:'7px 4px',background:'none',border:'none',fontSize:10,fontWeight:600,color:'#1D4ED8',cursor:'pointer',borderRight:'1px solid #F3F4F6'}}>
                                  🧾 Invoice
                                </button>
                                <button onClick={()=>deleteSpare(s.materialCode)}
                                  style={{flex:1,padding:'7px 4px',background:'none',border:'none',fontSize:10,fontWeight:600,color:'#DC2626',cursor:'pointer'}}>
                                  🗑️ Delete
                                </button>
                              </div>
                            </div>
                          );
                        })}
 
                        {/* Bottom actions */}
                        <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:8,marginTop:12}}>
                          <button onClick={()=>loadSpares(sfTabSession.sfId)} disabled={sparesLoading}
                            style={{padding:'10px',background:'#F3F4F6',border:'1.5px solid #E5E7EB',borderRadius:9,fontSize:11,fontWeight:600,color:'#374151',cursor:'pointer'}}>
                            ↻ Refresh
                          </button>
                          <button onClick={()=>setShowInvoice(true)}
                            style={{padding:'10px',background:'linear-gradient(135deg,#7C3AED,#5B21B6)',color:'white',border:'none',borderRadius:9,fontSize:11,fontWeight:700,cursor:'pointer'}}>
                            🧾 New Invoice
                          </button>
                        </div>
 
                        {/* Invoice history */}
                        <div style={{marginTop:10}}>
                          <button onClick={async()=>{setShowInvHistory(p=>!p);if(!showInvHistory)await loadInvoiceHistory();}}
                            style={{width:'100%',padding:'9px',background:'white',border:'1.5px solid #E5E7EB',borderRadius:9,fontSize:11,fontWeight:600,color:'#374151',cursor:'pointer'}}>
                            {showInvHistory ? '▲ Hide' : '📋 Show'} Invoice History
                          </button>
                          {showInvHistory && (
                            <div style={{background:'white',borderRadius:10,marginTop:6,boxShadow:'0 2px 8px rgba(0,0,0,.06)',overflow:'hidden'}}>
                              {invHistLoading && <div style={{padding:20,textAlign:'center',color:'#9CA3AF',fontSize:12}}>Loading…</div>}
                              {!invHistLoading && invoiceHistory.length===0 && (
                                <div style={{padding:20,textAlign:'center',color:'#9CA3AF',fontSize:12}}>No invoices generated yet</div>
                              )}
                              {!invHistLoading && invoiceHistory.map(inv=>(
                                <div key={inv.id} style={{padding:'10px 14px',borderBottom:'1px solid #F3F4F6',display:'flex',justifyContent:'space-between',alignItems:'center'}}>
                                  <div>
                                    <div style={{fontSize:11,fontWeight:700,fontFamily:'monospace',color:'#7C3AED'}}>{inv.invoiceNo}</div>
                                    <div style={{fontSize:10,color:'#374151',marginTop:1}}>{inv.customer?.name}</div>
                                    <div style={{fontSize:9,color:'#9CA3AF'}}>{inv.date}</div>
                                  </div>
                                  <div style={{textAlign:'right'}}>
                                    <div style={{fontSize:12,fontWeight:700,fontFamily:'monospace',color:'#1D4ED8'}}>₹{fmtINR(inv.total)}</div>
                                    <div style={{fontSize:9,color:'#9CA3AF'}}>{inv.items?.length} item{inv.items?.length!==1?'s':''}</div>
                                  </div>
                                </div>
                              ))}
                            </div>
                          )}
                        </div>
                      </>
                    )}
                  </div>
                )}

              </>
            )}
          </div>
        </div>
      )}

      {/* ══════════════════════════════════════════════════ MY STOCK TAB ═══ */}
      {mainTab==='inventory' && (
        <div style={{minHeight:'100vh',background:'#F3F4F6',padding:'12px 8px 20px'}}>
          <div style={{maxWidth:480,margin:'0 auto'}}>
            {!techAuthed ? (
              <div style={{display:'flex',alignItems:'center',justifyContent:'center',padding:40}}>
                <div style={{background:'white',borderRadius:16,padding:32,width:'100%',maxWidth:360,boxShadow:'0 4px 20px rgba(0,0,0,.1)',textAlign:'center'}}>
                  <div style={{fontSize:36,marginBottom:12}}>🔧</div>
                  <div style={{fontSize:18,fontWeight:700,marginBottom:4}}>My Inventory</div>
                  <div style={{fontSize:12,color:'#6B7280',marginBottom:24}}>Enter your Technician ID</div>
                  <input value={myTechId} onChange={e=>setMyTechId(e.target.value.toUpperCase())} onKeyDown={e=>e.key==='Enter'&&techLogin()} placeholder="e.g. TECH001"
                    style={{width:'100%',padding:'10px 14px',border:'1.5px solid #E5E7EB',borderRadius:10,fontSize:14,marginBottom:8,outline:'none',textAlign:'center',letterSpacing:2,fontFamily:'monospace'}}/>
                  {techErr&&<div style={{color:'#DC2626',fontSize:12,marginBottom:8}}>{techErr}</div>}
                  <button onClick={techLogin} disabled={techLoading} style={{width:'100%',padding:12,background:'linear-gradient(135deg,#E8001D,#9B0013)',color:'white',border:'none',borderRadius:10,fontSize:14,fontWeight:600,cursor:'pointer'}}>
                    {techLoading?'Loading...':'View My Stock'}
                  </button>
                </div>
              </div>
            ) : (
              <>
                <div style={{background:'linear-gradient(135deg,#E8001D,#9B0013)',borderRadius:14,padding:'14px 16px',color:'white',marginBottom:12}}>
                  <div style={{display:'flex',justifyContent:'space-between',alignItems:'center'}}>
                    <div>
                      <div style={{fontSize:14,fontWeight:700}}>🔧 {techData?.tech?.name}</div>
                      <div style={{fontSize:10,color:'rgba(255,255,255,.6)',marginTop:2,fontFamily:'monospace'}}>ID: {techData?.tech?.id}</div>
                    </div>
                    <button onClick={techRefresh} style={{background:'rgba(255,255,255,.2)',border:'none',color:'white',padding:'6px 12px',borderRadius:8,fontSize:11,cursor:'pointer'}}>{techLoading?'...':'↻ Refresh'}</button>
                  </div>
                </div>
                {(()=>{
                  const {stock,materials:mats}=techData||{};
                  const zi=(mats||[]).filter(m=>((stock||{})[m.id]||0)===0);
                  return (<>
                    {zi.length>0&&(
                      <div style={{background:'#FEF2F2',border:'1px solid #FECACA',borderRadius:10,padding:'10px 12px',marginBottom:10}}>
                        <div style={{fontSize:12,fontWeight:700,color:'#DC2626',marginBottom:4}}>⚠️ Zero Stock Alert</div>
                        {zi.map(m=><div key={m.id} style={{fontSize:11,color:'#DC2626'}}>• {m.name}{m.sub?' ('+m.sub+')':''} — 0 {m.unit}</div>)}
                      </div>
                    )}
                    <div style={{background:'white',borderRadius:12,overflow:'hidden',boxShadow:'0 2px 8px rgba(0,0,0,.06)'}}>
                      <div style={{background:'#111827',padding:'8px 14px',display:'grid',gridTemplateColumns:'1fr 80px 70px'}}>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)'}}>MATERIAL</div>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)',textAlign:'center'}}>STOCK</div>
                        <div style={{fontSize:10,fontWeight:600,color:'rgba(255,255,255,.6)',textAlign:'right'}}>UNIT</div>
                      </div>
                      {(mats||[]).map(m=>{const qty=((stock||{})[m.id]||0);const zero=qty===0;return(
                        <div key={m.id} style={{display:'grid',gridTemplateColumns:'1fr 80px 70px',padding:'10px 14px',borderBottom:'1px solid #F3F4F6',background:zero?'#FFF5F5':'white',alignItems:'center'}}>
                          <div><div style={{fontSize:12,fontWeight:500,color:zero?'#DC2626':'#111827'}}>{m.name}</div>{m.sub&&<div style={{fontSize:10,color:'#6B7280'}}>{m.sub}</div>}</div>
                          <div style={{textAlign:'center',fontFamily:'monospace',fontSize:14,fontWeight:700,color:zero?'#DC2626':'#16A34A'}}>{zero?'⚠️ 0':qty}</div>
                          <div style={{textAlign:'right',fontSize:11,color:'#6B7280'}}>{m.unit}</div>
                        </div>
                      );})}
                    </div>
                    <div style={{textAlign:'center',marginTop:16,fontSize:10,color:'#9CA3AF'}}>Stock deducts automatically when TCR is submitted</div>
                    <button onClick={async()=>{
                      const r=await fetch('/api/records?techId='+myTechId.trim().toUpperCase());
                      const d=await r.json();
                      downloadExcel(d,'TCR_'+myTechId.trim().toUpperCase()+'.xlsx');
                    }} style={{width:'100%',marginTop:12,padding:'11px',background:'linear-gradient(135deg,#16A34A,#15803D)',color:'white',border:'none',borderRadius:9,fontSize:12,fontWeight:600,cursor:'pointer',display:'flex',alignItems:'center',justifyContent:'center',gap:7}}>
                      <svg width="15" height="15" fill="none" stroke="white" strokeWidth="2" viewBox="0 0 24 24"><path d="M12 10v6m0 0l-3-3m3 3l3-3M3 17v3a1 1 0 001 1h16a1 1 0 001-1v-3"/></svg>
                      Download My TCR Records (Excel)
      
                    </button>
                  </>);
                })()}
              </>
            )}
          </div>
        </div>
      )}
    </>
  );
}
