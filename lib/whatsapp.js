/**
 * WHATSAPP SENDER
 * ---------------
 * Sends the confirmation link to the customer via WhatsApp.
 *
 * FREE OPTIONS (choose one):
 *
 * OPTION A — WhatsApp wa.me deep link (zero cost, zero setup)
 *   Opens WhatsApp on the technician's phone with a pre-filled message.
 *   Technician taps Send. Simple, free, no API needed.
 *   ⚠️ Slight trust gap: technician physically sends — but they can't
 *      intercept the confirmation (link goes to customer's number only).
 *
 * OPTION B — CallMeBot (free WhatsApp API, ~5 msgs/min)
 *   Fully automated. Customer gets message without technician involvement.
 *   Setup: customer sends "I allow callmebot to send me messages" to
 *          +34 644 82 81 73 on WhatsApp to get their API key.
 *   Limitation: each customer needs to opt-in once.
 *
 * OPTION C — Twilio WhatsApp Sandbox (free dev tier)
 *   Best for testing. Production requires WhatsApp Business approval.
 *
 * OPTION D — Meta WhatsApp Cloud API (free tier: 1000 conversations/month)
 *   Best for production. Requires Meta Business verification.
 */

export function buildWADeepLink(customerMobile, confirmUrl, data) {
  const msg =
    `🔔 *GENERAL HVAC Solutions*\n` +
    `Installation Charge Confirmation\n\n` +
    `Dear Customer,\n` +
    `Your technician has raised a TCR for AC installation work at your premises.\n\n` +
    `📋 *Job No:* ${data.callNo}\n` +
    `🗓 *Date:* ${data.serviceDate}\n` +
    `👷 *Technician:* ${data.techName}\n` +
    `❄️ *AC Type:* ${data.tonnage} | ${data.unitCount} Unit(s)\n` +
    `💰 *Total Amount:* ₹${Number(data.total).toLocaleString('en-IN')}\n\n` +
    `✅ *Please review and confirm here:*\n` +
    `${confirmUrl}\n\n` +
    `⏰ This link expires in 30 minutes.\n` +
    `_Do NOT share this link with anyone._`;

  const encoded = encodeURIComponent(msg);
  // Format Indian mobile: remove leading 0, add country code 91
  const formatted = '91' + customerMobile.replace(/^0/, '').slice(-10);
  return `https://wa.me/${formatted}?text=${encoded}`;
}


// ─── OPTION B: CallMeBot (uncomment to use) ─────────────────────────────────
/*
export async function sendViaCallMeBot(customerMobile, apiKey, message) {
  const encoded = encodeURIComponent(message);
  const url = `https://api.callmebot.com/whatsapp.php?phone=91${customerMobile}&text=${encoded}&apikey=${apiKey}`;
  const res = await fetch(url);
  return res.ok;
}
*/


// ─── OPTION C: Twilio (uncomment to use) ─────────────────────────────────────
/*
export async function sendViaTwilio(customerMobile, message) {
  const accountSid = process.env.TWILIO_ACCOUNT_SID;
  const authToken  = process.env.TWILIO_AUTH_TOKEN;
  const from       = 'whatsapp:+14155238886'; // Twilio sandbox number
  const to         = `whatsapp:+91${customerMobile}`;

  const res = await fetch(`https://api.twilio.com/2010-04-01/Accounts/${accountSid}/Messages.json`, {
    method: 'POST',
    headers: {
      'Authorization': 'Basic ' + Buffer.from(`${accountSid}:${authToken}`).toString('base64'),
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({ From: from, To: to, Body: message }),
  });
  return res.ok;
}
*/


// ─── OPTION D: Meta WhatsApp Cloud API (uncomment to use) ────────────────────
/*
export async function sendViaMetaAPI(customerMobile, confirmUrl, data) {
  const res = await fetch(
    `https://graph.facebook.com/v19.0/${process.env.WHATSAPP_PHONE_NUMBER_ID}/messages`,
    {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${process.env.WHATSAPP_ACCESS_TOKEN}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        messaging_product: 'whatsapp',
        to: '91' + customerMobile,
        type: 'template',
        template: {
          name: 'tcr_confirmation', // your approved template name
          language: { code: 'en_IN' },
          components: [{
            type: 'body',
            parameters: [
              { type: 'text', text: data.callNo },
              { type: 'text', text: `Rs.${data.total}` },
              { type: 'text', text: confirmUrl },
            ]
          }]
        }
      }),
    }
  );
  return res.ok;
}
*/
