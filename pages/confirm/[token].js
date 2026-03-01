import Head from 'next/head';
import { useEffect, useState, useRef } from 'react';
import { useRouter } from 'next/router';

const tonLabels = { '1.0':'1.0 Ton','1.5':'1.5 Ton','2.0':'2.0 Ton','2.0+':'Above 2 Ton','mix':'Multiple Mix','window':'Window AC' };
const fmtINR = n => `₹${Number(n||0).toLocaleString('en-IN')}`;

export default function ConfirmPage() {
  const router = useRouter();
  const { token } = router.query;
  const [screen, setScreen] = useState('loading');
  const [data, setData] = useState(null);
  const [consent, setConsent] = useState(false);
  const [confirming, setConfirming] = useState(false);
  const [confirmResult, setConfirmResult] = useState(null);
  const pdfBlobRef = useRef(null);

  useEffect(() => {
    if (!token) return;
    fetch(`/api/status?token=${token}`)
      .then(r => r.json())
      .then(json => {
        if (json.status === 'expired') { setScreen('expired'); return; }
        if (json.status === 'confirmed') { setScreen('already'); return; }
        if (json.fullData) { setData(json.fullData); setScreen('review'); }
        else setScreen('expired');
      })
      .catch(() => setScreen('expired'));
  }, [token]);

  async function handleConfirm() {
    setConfirming(true);
    try {
      const res = await fetch('/api/confirm', {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ token }),
      });
      const json = await res.json();
      if (!res.ok) { alert(json.message || 'Error. Please try again.'); setConfirming(false); return; }
      setConfirmResult(json);
      await buildPDF(data, json);
      setScreen('success');
    } catch(e) { alert('Network error. Try again.'); setConfirming(false); }
  }

  async function buildPDF(d, cr) {
    const { jsPDF } = await import('jspdf');
    await import('jspdf-autotable');
    const doc = new jsPDF({ unit:'mm', format:'a4', compress:true });
    const W=210, M=14; let y=0;
    doc.setFillColor(232,0,29); doc.rect(0,0,W,30,'F');
    doc.setFillColor(37,211,102); doc.rect(M,5,30,20,'F');
    doc.setTextColor(255,255,255);
    doc.setFont('helvetica','bold'); doc.setFontSize(11);
    doc.text('GENERAL HVAC Solutions India Pvt Ltd', M+34, 13);
    doc.setFont('helvetica','normal'); doc.setFontSize(7.5);
    doc.text('Authorized O General - Fujitsu Installation Partner', M+34, 19);
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
    (d.actualItems||[]).filter(i=>i.actual>0).forEach(i=>rows.push([i.no,i.desc,'Actual','--','Rs.'+Number(i.actual).toLocaleString('en-IN')]));
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
    doc.text('Confirmed at: '+new Date(cr.confirmedAt).toLocaleString('en-IN'), M+3, y+23);
    doc.text('Token Ref: '+String(token).slice(0,16)+'...', M+3, y+29);
    doc.setTextColor(60,60,60); doc.setFont('helvetica','italic'); doc.setFontSize(7.5);
    const conf='I, '+d.custName+', confirm I reviewed all charges and approve payment of Rs.'+Number(d.total).toLocaleString('en-IN')+' for installation work. Confirmed on my own device.';
    doc.text(doc.splitTextToSize(conf,82),M+90,y+10);
    y+=36;
    doc.setFillColor(30,30,30); doc.rect(0,287,W,10,'F');
    doc.setTextColor(160,160,160); doc.setFont('helvetica','normal'); doc.setFontSize(7);
    doc.text('GENERAL HVAC Solutions India Pvt Ltd  ·  Approved TCR  ·  Job: '+d.callNo, W/2, 293, {align:'center'});
    pdfBlobRef.current = doc.output('blob');
  }

  function downloadPDF() {
    if (!pdfBlobRef.current) return;
    const url=URL.createObjectURL(pdfBlobRef.current);
    const a=document.createElement('a'); a.href=url; a.download='TCR_APPROVED_'+(data?.callNo||'receipt')+'.pdf'; a.click(); URL.revokeObjectURL(url);
  }

  function shareWA() {
    if (!data) return;
    const msg=encodeURIComponent('✅ *TCR Approved*\n*GENERAL HVAC Solutions*\n\nJob: '+data.callNo+'\nCustomer: '+data.custName+'\nTotal: '+fmtINR(data.total)+'\nConfirmed: '+new Date(confirmResult?.confirmedAt).toLocaleString('en-IN')+'\n\n_Approved via secure link on customer own device._');
    window.open('https://wa.me/?text='+msg,'_blank');
  }

  const sub=Number(data?.sub||0), gst=Number(data?.gst||0), total=Number(data?.total||0);

  return (
    <>
      <Head>
        <title>Confirm Charges - GENERAL HVAC</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&family=DM+Mono:wght@400;500&display=swap" rel="stylesheet"/>
      </Head>
      <style jsx global>{`
        *{box-sizing:border-box;margin:0;padding:0} body{font-family:'Poppins',sans-serif;background:#E5DDD5;min-height:100vh;padding:10px 8px 40px}
        .wrap{max-width:460px;margin:0 auto} .card{background:white;border-radius:0 0 14px 14px;box-shadow:0 4px 20px rgba(0,0,0,.1);overflow:hidden}
        .hdr{background:linear-gradient(135deg,#E8001D,#9B0013);border-radius:14px 14px 0 0;padding:13px 15px;color:white;position:relative;overflow:hidden}
        .hdr::after{content:'';position:absolute;right:-20px;top:-20px;width:90px;height:90px;background:rgba(255,255,255,.06);border-radius:50%}
        .ht{display:flex;align-items:center;gap:9px;position:relative;z-index:1}
        .logo{width:36px;height:36px;background:#25D366;display:flex;align-items:center;justify-content:center;flex-shrink:0;font-size:7.5px;font-weight:800;color:white;text-align:center;line-height:1.2}
        .hco{font-size:11.5px;font-weight:700} .hsub{font-size:9px;color:rgba(255,255,255,.6);margin-top:1px}
        .hbadge{margin-top:7px;display:inline-flex;align-items:center;gap:4px;background:rgba(255,255,255,.18);padding:3px 9px;border-radius:20px;font-size:10px;font-weight:600;position:relative;z-index:1}
        .loading{padding:48px 20px;text-align:center} .spin{width:40px;height:40px;border:3px solid #E5E7EB;border-top-color:#E8001D;border-radius:50%;animation:sp .8s linear infinite;margin:0 auto 12px} @keyframes sp{to{transform:rotate(360deg)}}
        .stt{padding:24px 20px 30px;text-align:center} .se{font-size:48px;margin-bottom:10px} .stitle{font-size:17px;font-weight:700;color:#111827;margin-bottom:6px} .smsg{font-size:12px;color:#6B7280;line-height:1.6}
        .sec{padding:11px 14px;border-bottom:1px solid #E5E7EB} .shead{font-size:9px;font-weight:700;text-transform:uppercase;letter-spacing:.7px;color:#E8001D;margin-bottom:7px}
        .ig{display:grid;grid-template-columns:1fr 1fr;gap:5px} .ii{background:#F3F4F6;border-radius:7px;padding:6px 8px} .ii.f{grid-column:1/-1}
        .ik{font-size:8.5px;color:#6B7280;text-transform:uppercase;letter-spacing:.3px} .iv{font-size:11.5px;font-weight:600;color:#111827;margin-top:1px}
        .ct{width:100%;border-collapse:collapse;font-size:10.5px} .ct thead tr{background:#111827;color:white} .ct thead th{padding:5px 7px;text-align:left;font-size:9.5px;font-weight:500} .ct thead th:last-child{text-align:right}
        .ct tbody tr{border-bottom:1px solid #E5E7EB} .ct td{padding:5px 7px;color:#374151;vertical-align:top} .ct td.dc{font-size:10px;line-height:1.35}
        .ct td:last-child{text-align:right;font-family:'DM Mono',monospace;font-weight:600;color:#111827;white-space:nowrap;width:60px} .ct td:first-child{width:22px;font-family:'DM Mono',monospace;font-size:9px;color:#9CA3AF}
        .fb{display:inline-block;background:#DCFCE7;color:#16A34A;padding:1px 6px;border-radius:20px;font-size:9px;font-weight:600}
        .tots{padding:10px 14px;background:#FAFAFA;border-top:2px solid #E5E7EB}
        .tr{display:flex;justify-content:space-between;padding:3px 0;font-size:11.5px;color:#374151;border-bottom:1px dashed #E5E7EB} .tr:last-of-type{border-bottom:none} .tra{font-family:'DM Mono',monospace;font-weight:600;color:#111827}
        .grand{background:linear-gradient(135deg,#111827,#1F2937);border-radius:9px;padding:10px 12px;margin-top:8px;display:flex;justify-content:space-between;align-items:center}
        .gl{font-size:10.5px;color:rgba(255,255,255,.6)} .gl small{display:block;font-size:8.5px;color:rgba(255,255,255,.3);margin-top:1px}
        .ga{font-family:'DM Mono',monospace;font-size:20px;font-weight:700;color:white} .ga span{font-size:12px;color:rgba(255,255,255,.5)}
        .cw{padding:13px 14px}
        .cons{display:flex;align-items:flex-start;gap:7px;padding:8px 10px;background:#F3F4F6;border-radius:8px;margin-bottom:10px;cursor:pointer}
        .cons input{width:15px;height:15px;accent-color:#16A34A;flex-shrink:0;margin-top:2px;cursor:pointer} .ctxt{font-size:10.5px;color:#374151;line-height:1.5} .ctxt strong{color:#111827}
        .bcf{width:100%;padding:13px;background:linear-gradient(135deg,#16A34A,#15803D);color:white;border:none;border-radius:11px;font-family:'Poppins',sans-serif;font-size:13.5px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:7px;transition:all .2s;box-shadow:0 4px 14px rgba(22,163,74,.28)}
        .bcf:disabled{background:#9CA3AF;box-shadow:none;cursor:not-allowed} .ca{font-family:'DM Mono',monospace;background:rgba(255,255,255,.2);padding:2px 7px;border-radius:20px;font-size:11.5px}
        .notice{background:#FFFBEB;border:1px solid #FDE68A;border-radius:8px;padding:8px 10px;font-size:10px;color:#92400E;line-height:1.5;margin-top:10px}
        .sw{padding:16px 14px 26px;text-align:center}
        .si{width:64px;height:64px;background:#DCFCE7;border-radius:50%;display:flex;align-items:center;justify-content:center;margin:0 auto 10px;font-size:32px}
        .ref{font-family:'DM Mono',monospace;font-size:11px;background:#F3F4F6;padding:5px 10px;border-radius:7px;color:#111827;display:inline-block;margin:8px 0 4px}
        .bdl{width:100%;padding:11px;margin-bottom:6px;background:linear-gradient(135deg,#E8001D,#9B0013);color:white;border:none;border-radius:9px;font-family:'Poppins',sans-serif;font-size:12.5px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:6px}
        .bwa{width:100%;padding:11px;background:linear-gradient(135deg,#25D366,#128C7E);color:white;border:none;border-radius:9px;font-family:'Poppins',sans-serif;font-size:12.5px;font-weight:600;cursor:pointer;display:flex;align-items:center;justify-content:center;gap:6px}
      `}</style>

      <div className="wrap">
        <div className="hdr">
          <div className="ht">
            <div className="logo">O<br/>GEN</div>
            <div><div className="hco">GENERAL HVAC Solutions India Pvt Ltd</div><div className="hsub">Authorized O General – Fujitsu Installation Partner</div></div>
          </div>
          <div className="hbadge">🔒 TCR – Secure Customer Confirmation</div>
        </div>
        <div className="card">
          {screen==='loading'&&<div className="loading"><div className="spin"/><p style={{fontSize:12,color:'#6B7280'}}>Loading charge summary…</p></div>}
          {screen==='expired'&&<div className="stt"><div className="se">⏰</div><div className="stitle">Link Expired</div><div className="smsg">This link has expired (30 min limit).<br/>Ask your technician to resend.</div></div>}
          {screen==='already'&&<div className="stt"><div className="se">✅</div><div className="stitle">Already Confirmed</div><div className="smsg">This TCR has already been confirmed. Thank you!</div></div>}
          {screen==='review'&&data&&<>
            <div className="sec">
              <div className="shead">📋 Service Details</div>
              <div className="ig">
                <div className="ii"><div className="ik">Customer</div><div className="iv">{data.custName}</div></div>
                <div className="ii"><div className="ik">Job No.</div><div className="iv">{data.callNo}</div></div>
                <div className="ii"><div className="ik">Date</div><div className="iv">{data.serviceDate}</div></div>
                <div className="ii"><div className="ik">Technician</div><div className="iv">{data.techName}</div></div>
                <div className="ii f"><div className="ik">AC · Units</div><div className="iv">{tonLabels[data.tonnage]||data.tonnage} · {data.unitCount} Unit(s)</div></div>
              </div>
            </div>
            <div className="sec" style={{padding:0}}>
              <div style={{padding:'7px 14px 4px',background:'#FFF5F5',borderBottom:'1px solid #FECACA'}}><div className="shead" style={{marginBottom:0,color:'#E8001D'}}>💰 Charges</div></div>
              <table className="ct">
                <thead><tr><th>#</th><th>Description</th><th>Qty</th><th>Amount</th></tr></thead>
                <tbody>
                  <tr><td>1</td><td className="dc">Standard Installation</td><td>—</td><td><span className="fb">₹0</span></td></tr>
                  {(data.additionalItems||[]).filter(i=>i.qty>0).map(i=><tr key={i.no}><td>{i.no}</td><td className="dc">{i.desc}</td><td style={{textAlign:'center',fontFamily:'DM Mono,monospace',fontSize:10}}>{i.qty} Ft</td><td>{fmtINR(i.rate*i.qty)}</td></tr>)}
                  {(data.actualItems||[]).filter(i=>i.actual>0).map(i=><tr key={i.no}><td>{i.no}</td><td className="dc">{i.desc}</td><td style={{textAlign:'center'}}>Actual</td><td>{fmtINR(i.actual)}</td></tr>)}
                </tbody>
              </table>
            </div>
            <div className="tots">
              <div className="tr"><span>Sub Total</span><span className="tra">{fmtINR(sub)}</span></div>
              {data.gstOn&&gst>0&&<div className="tr"><span>GST @ 18%</span><span className="tra">{fmtINR(gst)}</span></div>}
              <div className="grand"><div className="gl">Total Payable<small>{data.gstOn?'Incl. GST':'Excl. GST'}</small></div><div className="ga"><span>₹</span>{total.toLocaleString('en-IN')}</div></div>
            </div>
            <div className="cw">
              <div style={{fontSize:13,fontWeight:600,color:'#111827',marginBottom:3}}>✅ Your Approval</div>
              <div style={{fontSize:11,color:'#6B7280',marginBottom:10,lineHeight:1.5}}>Review carefully. Your confirmation is secure and independent.</div>
              <label className="cons" htmlFor="cb">
                <input type="checkbox" id="cb" checked={consent} onChange={e=>setConsent(e.target.checked)}/>
                <div className="ctxt">I, <strong>{data.custName}</strong>, have reviewed all charges and agree to pay <strong>{fmtINR(total)}</strong> for the installation work. This is my own independent decision.</div>
              </label>
              <button className="bcf" disabled={!consent||confirming} onClick={handleConfirm}>
                {confirming?<><span style={{width:16,height:16,border:'2px solid rgba(255,255,255,.4)',borderTopColor:'white',borderRadius:'50%',animation:'sp .8s linear infinite',display:'inline-block'}}/>Confirming…</>:<><svg width="17" height="17" fill="none" stroke="currentColor" strokeWidth="2.5" viewBox="0 0 24 24"><path d="M5 13l4 4L19 7"/></svg>Confirm & Approve<span className="ca">{fmtINR(total)}</span></>}
              </button>
              <div className="notice"><strong>⚠️ Important:</strong> Only confirm if you have physically verified the work. Do not confirm under any pressure. Call GENERAL HVAC support if unsure.</div>
            </div>
          </>}
          {screen==='success'&&<div className="sw">
            <div className="si">✅</div>
            <div style={{fontSize:17,fontWeight:700,color:'#111827',marginBottom:6}}>Approved!</div>
            <p style={{fontSize:12,color:'#6B7280',lineHeight:1.6,margin:'4px 0'}}>Confirmed. Download your approved PDF.</p>
            <div className="ref">REF: {data?.callNo}</div>
            <p style={{fontSize:10.5,color:'#9CA3AF',marginBottom:14}}>At: {confirmResult?new Date(confirmResult.confirmedAt).toLocaleString('en-IN'):'—'}</p>
            <button className="bdl" onClick={downloadPDF}><svg width="15" height="15" fill="none" stroke="white" strokeWidth="2" viewBox="0 0 24 24"><path d="M12 10v6m0 0l-3-3m3 3l3-3M3 17v3a1 1 0 001 1h16a1 1 0 001-1v-3"/></svg>Download Approved PDF</button>
            <button className="bwa" onClick={shareWA}><svg width="16" height="16" viewBox="0 0 24 24" fill="white"><path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/></svg>Share on WhatsApp</button>
          </div>}
        </div>
      </div>
    </>
  );
}
