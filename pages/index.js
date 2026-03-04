import Head from 'next/head';
import { useState, useEffect, useRef } from 'react';

const additionalItems = () => [
  {no:"2.1", desc:"Copper pipe With Insulation - Supply & Labour, Upto 2 Ton",       rate:850,  unit:"Ft", group:"low",  qty:0},
  {no:"2.2", desc:"Copper pipe With Insulation - Supply & Labour, 2.5/3/4 Ton",      rate:950,  unit:"Ft", group:"high", qty:0},
  {no:"3",   desc:"Supply and laying of Electrical 3/4 Core Cable",                  rate:120,  unit:"Ft", group:"all",  qty:0},
  {no:"4",   desc:"ODU Stand Supply and Fixing / Only Fixing",                        rate:750,  unit:"No.", group:"all",  qty:0},
  {no:"5",   desc:"Drain Pipe supply and fixing",                                     rate:100,  unit:"Ft", group:"all",  qty:0},
  {no:"6",   desc:"Dismantling of OLD / Existing Split AC Unit",                      rate:750,  unit:"No.", group:"all",  qty:0},
];

const actualItems = () => [
  {no:"7", desc:"Wall Chiseling / Chipping",          actual:0},
  {no:"8", desc:"Beam / Concrete Wall Core Drilling", actual:0},
  {no:"9", desc:"Miscellaneous (Please specify)",     actual:0},
];

const tonOptions = [
  {v:"1.0",    l:"1.0 Ton"},
  {v:"1.5",    l:"1.5 Ton"},
  {v:"2.0",    l:"2.0 Ton"},
  {v:"2.0+",   l:"Above 2 Ton"},
  {v:"mix",    l:"Multiple Mix"},
  {v:"window", l:"Window AC"},
];

const fmtINR = n => Number(n||0).toLocaleString('en-IN');

export default function Home() {
  // ── Form fields ──
  const [f, setF] = useState({
    custName:"", mobile:"", callNo:"", serviceDate:"", techName:"", ssdName:"",
    address:"", tonnage:"", unitCount:1, gstOn:false, gstNumber:""
  });
  const [units, setUnits] = useState([{model:"",serial:""}]);
  const [addItems, setAddItems] = useState(additionalItems());
  const [actItems, setActItems] = useState(actualItems());

  // ── App state ──
  const [screen, setScreen] = useState("form");   // form | pending | done
  const [token, setToken] = useState("");
  const [waLink, setWaLink] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const [err, setErr] = useState("");
  const [remaining, setRemaining] = useState(1800);
  const [doneData, setDoneData] = useState(null);
  const pollRef = useRef(null);
  const timerRef = useRef(null);

  const field = (k, v) => setF(p => ({...p, [k]: v}));

  // Unit count change
  useEffect(() => {
    const n = parseInt(f.unitCount) || 1;
    setUnits(prev => {
      const next = [...prev];
      while (next.length < n) next.push({model:"",serial:""});
      return next.slice(0, n);
    });
  }, [f.unitCount]);

  // ── Derived totals ──
  function calcTotals() {
    const ton = f.tonnage;
    let sub = 0;
    addItems.forEach(it => {
      if (isDisabled(it, ton)) return;
      const rate = (it.no === "4" && (ton==="2.0+"||ton==="2.0")) ? 1200 : it.rate;
      sub += rate * (parseFloat(it.qty)||0);
    });
    actItems.forEach(it => sub += parseFloat(it.actual)||0);
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
    const ton = f.tonnage;
    return (it.no==="4" && (ton==="2.0+"||ton==="2.0")) ? 1200 : it.rate;
  }

  function updateAddItem(i, key, val) {
    setAddItems(prev => { const n=[...prev]; n[i]={...n[i],[key]:val}; return n; });
  }
  function updateActItem(i, val) {
    setActItems(prev => { const n=[...prev]; n[i]={...n[i],actual:parseFloat(val)||0}; return n; });
  }
  function updateUnit(i, key, val) {
    setUnits(prev => { const n=[...prev]; n[i]={...n[i],[key]:val}; return n; });
  }

  const { sub, gst, total } = calcTotals();

  // ── Submit ──
  async function handleSubmit() {
    setErr("");
    if (!f.custName)                         return setErr("Customer Name required");
    if (!/^\d{10}$/.test(f.mobile))           return setErr("Valid 10-digit mobile required");
    if (!f.callNo)                           return setErr("Call / Job No. required");
    if (!f.serviceDate)                      return setErr("Service Date required");
    if (!f.techName)                         return setErr("Technician Name required");
    if (!f.tonnage)                          return setErr("Please select AC Tonnage");
    if (f.gstOn && f.gstNumber.length < 15) return setErr("Valid 15-char GST Number required");

    const payload = {
      ...f,
      units,
      additionalItems: addItems.map(it => ({...it, rate: effectiveRate(it)})),
      actualItems: actItems,
      sub, gst, total,
    };

    setSubmitting(true);
    try {
      const res  = await fetch("/api/submit", {
        method:"POST", headers:{"Content-Type":"application/json"},
        body: JSON.stringify(payload),
      });
      const json = await res.json();
      if (!res.ok) { setErr(json.error || "Submit failed"); setSubmitting(false); return; }

      setToken(json.token);
      setWaLink(json.waLink);
      setScreen("pending");
      setRemaining(1800);
      startPolling(json.token);
    } catch(e) {
      setErr("Network error. Check connection.");
    }
    setSubmitting(false);
  }

  // ── Polling ──
  function startPolling(tok) {
    pollRef.current = setInterval(async () => {
      try {
        const res  = await fetch("/api/status?token="+tok);
        const json = await res.json();
        if (json.status === "confirmed") {
          clearInterval(pollRef.current);
          clearInterval(timerRef.current);
          setDoneData(json.fullData);
          setScreen("done");
        }
        if (json.status === "expired") {
          clearInterval(pollRef.current);
          clearInterval(timerRef.current);
          setScreen("form");
          setErr("Confirmation link expired. Please resubmit.");
        }
        if (json.remaining !== undefined) setRemaining(json.remaining);
      } catch(e) {}
    }, 4000);

    timerRef.current = setInterval(() => {
      setRemaining(r => {
        if (r <= 1) { clearInterval(timerRef.current); return 0; }
        return r - 1;
      });
    }, 1000);
  }

  useEffect(() => () => {
    clearInterval(pollRef.current);
    clearInterval(timerRef.current);
  }, []);

  function fmtTime(s) {
    const m = Math.floor(s/60), sec = s%60;
    return m+":" + String(sec).padStart(2,"0");
  }

  function reset() {
    clearInterval(pollRef.current);
    clearInterval(timerRef.current);
    setScreen("form");
    setF({custName:"",mobile:"",callNo:"",serviceDate:"",techName:"",ssdName:"",address:"",tonnage:"",unitCount:1,gstOn:false,gstNumber:""});
    setUnits([{model:"",serial:""}]);
    setAddItems(additionalItems());
    setActItems(actualItems());
    setErr("");
    setToken("");
    setWaLink("");
    setDoneData(null);
    setRemaining(1800);
  }

  return (
    <>
      <Head>
        <title>TCR - GENERAL HVAC</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=DM+Mono:wght@400;500&display=swap" rel="stylesheet"/>
      </Head>

      <style jsx global>{`
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:'Poppins',sans-serif;background:#E5DDD5;min-height:100vh;padding:10px 8px 40px;color:#111827}
        :root{--brand:#E8001D;--green:#16A34A;--green2:#15803D;--dark:#111827;--mid:#374151;--muted:#6B7280;--light:#F3F4F6;--border:#E5E7EB}
        .wrap{max-width:480px;margin:0 auto}

        /* Header */
        .hdr{background:linear-gradient(135deg,#E8001D,#9B0013);border-radius:14px 14px 0 0;padding:14px 16px;color:white;position:relative;overflow:hidden}
        .hdr::after,.hdr::before{content:'';position:absolute;background:rgba(255,255,255,.05);border-radius:50%}
        .hdr::after{right:-15px;top:-15px;width:90px;height:90px}
        .hdr::before{right:35px;bottom:-30px;width:120px;height:120px}
        .ht{display:flex;align-items:center;gap:10px;position:relative;z-index:1}
        .logo{width:40px;height:40px;background:#25D366;flex-shrink:0;overflow:hidden;display:flex;align-items:center;justify-content:center}
        .logo img{width:100%;height:100%;object-fit:contain}
        .hco{font-size:12px;font-weight:700}
        .hsub{font-size:9px;color:rgba(255,255,255,.6);margin-top:1px}
        .hssd{font-size:9px;color:rgba(255,255,255,.75);margin-top:2px;font-style:italic;min-height:14px}
        .hsubtitle{font-size:10.5px;color:rgba(255,255,255,.7);margin-top:9px;letter-spacing:.4px;text-transform:uppercase;position:relative;z-index:1}
        .hbadge{margin-top:6px;display:inline-flex;align-items:center;gap:5px;background:rgba(255,255,255,.18);padding:3px 10px;border-radius:20px;font-size:10px;font-weight:600;position:relative;z-index:1}
        .wa-bubble{background:white;border-radius:0 0 14px 14px;overflow:hidden;box-shadow:0 4px 20px rgba(0,0,0,.12)}
        .wa-inner{background:#ECE5DD;padding:8px 10px}
        .wa-msg{background:white;border-radius:3px 14px 14px 14px;padding:14px 14px 10px;box-shadow:0 1px 2px rgba(0,0,0,.08)}
        .wa-ts{font-size:10px;color:#9CA3AF;text-align:right;margin-top:5px;display:flex;justify-content:flex-end;align-items:center;gap:3px}

        /* Sections */
        .sec{padding:12px 14px;border-bottom:1px solid var(--border)}
        .sec-h{display:flex;align-items:center;gap:6px;font-size:12px;font-weight:600;color:var(--dark);margin-bottom:10px}
        .sec-h svg{color:var(--brand);flex-shrink:0}
        .fg{display:grid;grid-template-columns:1fr 1fr;gap:8px;margin-bottom:6px}
        .fg.one{grid-template-columns:1fr}
        .field label{display:block;font-size:9.5px;font-weight:600;color:var(--muted);text-transform:uppercase;letter-spacing:.5px;margin-bottom:3px}
        .field input,.field textarea,.field select{width:100%;padding:8px 10px;border:1.5px solid var(--border);border-radius:8px;font-family:'Poppins',sans-serif;font-size:12px;color:var(--dark);outline:none;background:white;transition:border-color .15s}
        .field input:focus,.field textarea:focus,.field select:focus{border-color:#93C5FD}
        .field textarea{resize:none;height:52px;font-size:11.5px}

        /* Tonnage */
        .ton-grid{display:grid;grid-template-columns:repeat(3,1fr);gap:6px}
        .ton-opt{padding:8px 4px;border:1.5px solid var(--border);border-radius:9px;text-align:center;font-size:11px;font-weight:500;color:var(--muted);cursor:pointer;background:white;transition:all .15s;user-select:none}
        .ton-opt:hover{border-color:#93C5FD;color:var(--dark)}
        .ton-opt.sel{border-color:#E8001D;background:#FFF5F5;color:#E8001D;font-weight:600}

        /* Unit fields */
        .unit-block{background:var(--light);border-radius:9px;padding:9px 10px;margin-bottom:6px}
        .unit-label{font-size:10px;font-weight:600;color:var(--brand);margin-bottom:6px}

        /* Charge table */
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

        /* GST */
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
        .gst-num input:focus{border-color:#93C5FD}

        /* Totals */
        .tots{padding:10px 14px;background:#FAFAFA;border-top:2px solid var(--border)}
        .tr{display:flex;justify-content:space-between;padding:3px 0;font-size:11.5px;color:var(--mid);border-bottom:1px dashed var(--border)}
        .tr:last-of-type{border-bottom:none}
        .tra{font-family:'DM Mono',monospace;font-weight:600;color:var(--dark)}
        .grand{background:linear-gradient(135deg,#111827,#1F2937);border-radius:10px;padding:11px 13px;margin-top:9px;display:flex;justify-content:space-between;align-items:center}
        .gl{font-size:11px;color:rgba(255,255,255,.6)}
        .gl small{display:block;font-size:9px;color:rgba(255,255,255,.3);margin-top:1px}
        .ga{font-family:'DM Mono',monospace;font-size:20px;font-weight:700;color:white}
        .ga span{font-size:12px;color:rgba(255,255,255,.5)}

        /* Submit */
        .submit-wrap{padding:12px 14px}
        .err{background:#FFF0F0;border:1px solid #FECACA;border-radius:8px;padding:8px 11px;font-size:11.5px;color:#DC2626;margin-bottom:10px}
        .btn-submit{width:100%;padding:14px;background:linear-gradient(135deg,#25D366,#128C7E);color:white;border:none;border-radius:12px;font-family:'Poppins',sans-serif;font-size:14px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:8px;box-shadow:0 4px 14px rgba(37,211,102,.3);transition:all .2s}
        .btn-submit:disabled{background:#9CA3AF;box-shadow:none;cursor:not-allowed}
        .btn-amt{font-family:'DM Mono',monospace;background:rgba(255,255,255,.2);padding:3px 8px;border-radius:20px;font-size:12px}

        /* PENDING screen */
        .pending-wrap{padding:24px 16px;text-align:center}
        .pulse-ring{width:80px;height:80px;border-radius:50%;background:#DCFCE7;display:flex;align-items:center;justify-content:center;margin:0 auto 16px;font-size:38px;animation:pulse 2s ease-in-out infinite}
        @keyframes pulse{0%,100%{box-shadow:0 0 0 0 rgba(22,163,74,.3)}50%{box-shadow:0 0 0 16px rgba(22,163,74,0)}}
        .ptitle{font-size:17px;font-weight:700;color:var(--dark);margin-bottom:6px}
        .pmsg{font-size:12px;color:var(--muted);line-height:1.6;margin-bottom:16px}
        .timer{font-family:'DM Mono',monospace;font-size:13px;color:var(--muted);background:var(--light);padding:5px 14px;border-radius:20px;display:inline-block;margin-bottom:18px}
        .wa-btn{width:100%;padding:13px;background:linear-gradient(135deg,#25D366,#128C7E);color:white;border:none;border-radius:11px;font-family:'Poppins',sans-serif;font-size:13.5px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:8px;margin-bottom:8px}
        .wa-btn-sm{width:100%;padding:10px;background:none;border:1.5px solid #25D366;color:#128C7E;border-radius:11px;font-family:'Poppins',sans-serif;font-size:12.5px;font-weight:600;cursor:pointer;margin-bottom:10px}
        .back-btn{background:none;border:1.5px solid var(--border);border-radius:10px;padding:9px;width:100%;font-family:'Poppins',sans-serif;font-size:12.5px;color:var(--muted);cursor:pointer;margin-top:4px}
        .polling-dots::after{content:'...';animation:dots 1.5s steps(3,end) infinite}
        @keyframes dots{0%{content:'.'}33%{content:'..'}66%{content:'...'}}
        .info-card{background:#F0FDF4;border:1px solid #BBF7D0;border-radius:10px;padding:11px 13px;text-align:left;margin-bottom:10px;font-size:11px;color:#166534}

        /* DONE screen */
        .done-wrap{padding:20px 16px 28px;text-align:center}
        .done-icon{width:70px;height:70px;background:#DCFCE7;border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 12px;font-size:34px}
        .ref-box{font-family:'DM Mono',monospace;font-size:11.5px;background:var(--light);padding:5px 12px;border-radius:8px;color:var(--dark);display:inline-block;margin:8px 0}
        .btn-new{width:100%;padding:12px;background:linear-gradient(135deg,#E8001D,#9B0013);color:white;border:none;border-radius:10px;font-family:'Poppins',sans-serif;font-size:13px;font-weight:600;cursor:pointer;margin-top:10px}
      `}</style>

      <div className="wrap">
        {/* ── HEADER ── */}
        <div className="hdr">
          <div className="ht">
            
            <div>
              <div className="hco">GENERAL HVAC Solutions India Pvt Ltd</div>
              <div className="hsub">Authorized Service Partner - TCR cum Customer Confirmation</div>
              <div className="hssd" id="ssdDisp">{f.ssdName ? `📍 ${f.ssdName}` : ""}</div>
            </div>
          </div>
        </div>

        <div className="wa-bubble">
          <div className="wa-inner">
            <div className="wa-msg">

              {/* ════════════════ FORM SCREEN ════════════════ */}
              {screen === "form" && <>

                {/* ① Customer Details */}
                <div className="sec">
                  <div className="sec-h">
                    <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/></svg>
                    Customer & Service Details
                  </div>
                  <div className="fg">
                    <div className="field"><label>Customer Name *</label><input value={f.custName} onChange={e=>field("custName",e.target.value)} placeholder="Full name"/></div>
                    <div className="field"><label>Mobile Number *</label><input type="tel" maxLength={10} value={f.mobile} onChange={e=>field("mobile",e.target.value)} placeholder="10-digit"/></div>
                  </div>
                  <div className="fg">
                    <div className="field"><label>Call / Job No. *</label><input value={f.callNo} onChange={e=>field("callNo",e.target.value)} placeholder="e.g. KOL110126000001"/></div>
                    <div className="field"><label>Service Date</label><input type="date" value={f.serviceDate} onChange={e=>field("serviceDate",e.target.value)}/></div>
                  </div>
                  <div className="fg">
                    <div className="field"><label>Technician Name *</label><input value={f.techName} onChange={e=>field("techName",e.target.value)} placeholder="Technician name"/></div>
                    <div className="field"><label>SSD / SF Name</label><input value={f.ssdName} onChange={e=>field("ssdName",e.target.value)} placeholder="SSD or SF name"/></div>
                  </div>
                  <div className="fg one">
                    <div className="field"><label>Installation Address</label><textarea value={f.address} onChange={e=>field("address",e.target.value)} placeholder="Site address (optional)"/></div>
                  </div>
                </div>

                {/* ② Model Details */}
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
                      </div>
                    </div>
                  ))}
                </div>

                {/* ③ Standard Installation */}
                <div className="sec">
                  <div className="sec-h">
                    <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/></svg>
                    Installation Charges
                  </div>
                  <table className="ct" style={{marginBottom:10}}>
                    <thead><tr><th>#</th><th>Description</th><th style={{textAlign:"center"}}>Rate</th><th style={{textAlign:"center"}}>Qty (Ft)</th><th style={{textAlign:"right"}}>Amount</th></tr></thead>
                    <tbody>
                      <tr>
                        <td className="no">1</td>
                        <td className="dc">Standard Installation as per Scope of Work</td>
                        <td style={{textAlign:"center",color:"#16A34A",fontWeight:600,fontSize:10.5}}>Free</td>
                        <td style={{textAlign:"center"}}>—</td>
                        <td className="ra" style={{color:"#16A34A"}}>₹0</td>
                      </tr>
                      {addItems.map((it,i) => {
                        const dis = isDisabled(it, f.tonnage);
                        const rate = effectiveRate(it);
                        const amt = rate * (parseFloat(it.qty)||0);
                        return (
                          <tr key={it.no} className={dis?"disabled":""}>
                            <td className="no">{it.no}</td>
                            <td className="dc">{it.desc}</td>
                            <td style={{textAlign:"center",fontFamily:"DM Mono,monospace",fontSize:10}}>
                              ₹{rate}/{it.unit}
                              {it.no==="4"&&(f.tonnage==="2.0+"||f.tonnage==="2.0")&&<span style={{fontSize:9,background:"#FEF3C7",color:"#92400E",padding:"0 4px",borderRadius:4,marginLeft:3}}>2T+</span>}
                            </td>
                            <td style={{textAlign:"center"}}>
                              <input type="number" min="0" value={it.qty||""} disabled={dis}
                                onChange={e=>updateAddItem(i,"qty",parseFloat(e.target.value)||0)}
                                placeholder="0"/>
                            </td>
                            <td className="ra">{amt>0?`₹${fmtINR(amt)}`:"—"}</td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </table>

                  <div style={{fontSize:"9.5px",fontWeight:700,color:"#92400E",textTransform:"uppercase",letterSpacing:".5px",marginBottom:6,paddingLeft:2}}>Customer Scope - Actuals</div>
                  <table className="ct">
                    <thead><tr><th>#</th><th>Description</th><th colSpan={2} style={{textAlign:"center"}}>Actual Amount (₹)</th><th style={{textAlign:"right"}}>Amount</th></tr></thead>
                    <tbody>
                      {actItems.map((it,i) => (
                        <tr key={it.no}>
                          <td className="no">{it.no}</td>
                          <td className="dc">{it.desc}</td>
                          <td colSpan={2} style={{textAlign:"center"}}>
                            <input type="number" min="0" value={it.actual||""} onChange={e=>updateActItem(i,e.target.value)} placeholder="0" style={{width:80}}/>
                          </td>
                          <td className="ra">{it.actual>0?`₹${fmtINR(it.actual)}`:"—"}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>

                {/* GST Toggle */}
                <div className="gst-row">
                  <label className="gst-toggle">
                    <input type="checkbox" checked={f.gstOn} onChange={e=>field("gstOn",e.target.checked)}/>
                    <span className="gst-slider"/>
                  </label>
                  <span className="gst-lbl">Apply GST @ 18%</span>
                </div>
                {f.gstOn && (
                  <div className="gst-num">
                    <div className="field"><label>GST Number *</label>
                      <input value={f.gstNumber} maxLength={15} onChange={e=>field("gstNumber",e.target.value.toUpperCase())} placeholder="15-character GST number"/>
                    </div>
                  </div>
                )}

                {/* Totals */}
                <div className="tots">
                  <div className="tr"><span>Sub Total</span><span className="tra">₹{fmtINR(sub)}</span></div>
                  {f.gstOn && <div className="tr"><span>GST @ 18%</span><span className="tra">₹{fmtINR(gst)}</span></div>}
                  <div className="grand">
                    <div className="gl">Total Payable<small>{f.gstOn?"Incl. GST":"Excl. GST"}</small></div>
                    <div className="ga"><span>₹</span>{fmtINR(total)}</div>
                  </div>
                </div>

                {/* Submit */}
                <div className="submit-wrap">
                  {err && <div className="err">⚠️ {err}</div>}
                  <button className="btn-submit" disabled={submitting} onClick={handleSubmit}>
                    {submitting
                      ? <><span style={{width:17,height:17,border:"2px solid rgba(255,255,255,.4)",borderTopColor:"white",borderRadius:"50%",animation:"spin .8s linear infinite",display:"inline-block"}}/>Sending…</>
                      : <>
                          <svg width="17" height="17" viewBox="0 0 24 24" fill="white"><path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/></svg>
                          Send to Customer via WhatsApp
                          <span className="btn-amt">₹{fmtINR(total)}</span>
                        </>
                    }
                  </button>
                </div>

              </>}

              {/* ════════════════ PENDING SCREEN ════════════════ */}
              {screen === "pending" && (
                <div className="pending-wrap">
                  <div className="pulse-ring">📲</div>
                  <div className="ptitle">Awaiting Customer Confirmation</div>
                  <div className="pmsg">
                    A secure confirmation link has been sent to the customer&apos;s WhatsApp.<br/>
                    <strong>Do not touch the customer&apos;s phone.</strong><br/>
                    The customer must confirm on their own device.
                  </div>
                  <div className="timer">⏱ Expires in {fmtTime(remaining)}</div>

                  <div className="info-card">
                    <strong>🔒 Security Note:</strong><br/>
                    This form is now locked. The confirmation link is unique and one-time — only the customer can approve it on their own device. You cannot approve on their behalf.
                  </div>

                  <button className="wa-btn" onClick={()=>window.open(waLink,"_blank")}>
                    <svg width="17" height="17" viewBox="0 0 24 24" fill="white"><path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/></svg>
                    Open WhatsApp → Send to Customer
                  </button>
                  <button className="wa-btn-sm" onClick={()=>window.open(waLink,"_blank")}>Resend Link</button>
                  <p style={{fontSize:10.5,color:"var(--muted)",marginBottom:12}} className="polling-dots">Waiting for customer approval</p>
                  <button className="back-btn" onClick={reset}>✕ Cancel & Start New TCR</button>
                </div>
              )}

              {/* ════════════════ DONE SCREEN ════════════════ */}
              {screen === "done" && (
                <div className="done-wrap">
                  <div className="done-icon">✅</div>
                  <div style={{fontSize:17,fontWeight:700,color:"var(--dark)",marginBottom:6}}>Customer Approved!</div>
                  <p style={{fontSize:12.5,color:"var(--muted)",lineHeight:1.6}}>
                    The customer confirmed on their own device.<br/>
                    The approved PDF with watermark has been generated on the customer&apos;s phone.
                  </p>
                  <div className="ref-box">JOB: {doneData?.callNo || f.callNo}</div>
                  <p style={{fontSize:11,color:"#9CA3AF",marginBottom:14}}>Customer: {doneData?.custName} · ₹{fmtINR(doneData?.total)}</p>
                  <div style={{background:"#F0FDF4",border:"1px solid #BBF7D0",borderRadius:10,padding:"11px 13px",fontSize:11.5,color:"#166534",textAlign:"left",marginBottom:14}}>
                    ✅ Confirmation recorded at {doneData ? new Date().toLocaleString("en-IN") : "—"}<br/>
                    🔒 Approved independently by customer on their own device<br/>
                    📄 Approved PDF available on customer&apos;s phone
                  </div>
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
    </>
  );
}
