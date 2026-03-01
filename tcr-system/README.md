# TCR Confirmation System — GENERAL HVAC
## Secure Customer Approval via WhatsApp · Deploy Free on Vercel

---

## How It Works

```
Technician fills form → taps "Send to Customer via WhatsApp"
       ↓
Server stores TCR data with a one-time UUID token (30 min TTL)
       ↓
WhatsApp opens with pre-filled message → technician sends to customer
       ↓
Customer opens link on THEIR OWN PHONE → sees read-only charge summary
       ↓
Customer taps "Confirm & Approve" independently
       ↓
Server marks token as confirmed
       ↓
Technician's screen auto-updates to "Customer Approved ✅"
Customer's phone generates watermarked approved PDF
```

### Why Technicians Cannot Hack It
- The confirmation URL is a **one-time UUID** — impossible to guess
- **Token stored server-side** — not accessible from the form
- Technician's form enters **locked "Pending" state** after submission
- Link goes directly to **customer's WhatsApp** — technician cannot intercept
- Token **expires in 30 minutes** — cannot be reused
- Customer confirms **on their own device** — technician's phone never touches the approval

---

## Deploy in 5 Steps

### Prerequisites
- [GitHub account](https://github.com) (free)
- [Vercel account](https://vercel.com) (free) — sign in with GitHub

---

### Step 1 — Upload to GitHub

1. Go to [github.com/new](https://github.com/new)
2. Repository name: `tcr-system`
3. Set to **Private**, click **Create repository**
4. On your computer, open a terminal in the `tcr-system` folder:

```bash
git init
git add .
git commit -m "Initial TCR system"
git remote add origin https://github.com/YOUR_USERNAME/tcr-system.git
git push -u origin main
```

---

### Step 2 — Deploy on Vercel

1. Go to [vercel.com/new](https://vercel.com/new)
2. Click **Import** next to your `tcr-system` repo
3. **Framework Preset**: Next.js (auto-detected)
4. Click **Deploy**
5. Wait ~2 minutes → Your app is live at `https://tcr-system.vercel.app`

---

### Step 3 — Set Environment Variable

1. In Vercel Dashboard → your project → **Settings** → **Environment Variables**
2. Add:

| Name | Value |
|------|-------|
| `NEXT_PUBLIC_BASE_URL` | `https://your-actual-url.vercel.app` |

3. Click **Save** → Go to **Deployments** → **Redeploy** (pick latest)

---

### Step 4 — Upgrade Storage (Recommended for Production)

The default in-memory store resets on each deployment. For production:

1. Vercel Dashboard → **Storage** → **Create Database** → **KV**
2. Name it `tcr-store`, click **Create**
3. Click **Connect Project** → select your project → **Connect**
4. Vercel auto-adds the KV env vars

5. In `lib/store.js`:
   - Comment out the entire **IN-MEMORY STORE** block
   - Uncomment the entire **VERCEL KV STORE** block

6. Run `npm install @vercel/kv`
7. Commit & push → Vercel auto-redeploys

---

### Step 5 — Enable Automated WhatsApp Sending (Optional)

Currently the app opens WhatsApp on the technician's phone with a pre-filled message — they tap Send.

For **fully automated** sending (customer gets message without technician involvement):

#### Option A: CallMeBot (100% Free, easiest)
Each customer sends this message to **+34 644 82 81 73** on WhatsApp once:
```
I allow callmebot to send me messages
```
They receive an API key. Store it per customer.

In `lib/whatsapp.js`, uncomment the **CallMeBot** block.

#### Option B: Meta WhatsApp Cloud API (Free: 1000 conversations/month)
Best for production. Requires Meta Business verification (~3 days).
1. [developers.facebook.com](https://developers.facebook.com) → Create App → WhatsApp
2. Get Phone Number ID + Access Token
3. Add to Vercel env vars:
   - `WHATSAPP_PHONE_NUMBER_ID`
   - `WHATSAPP_ACCESS_TOKEN`
4. Uncomment **Option D** in `lib/whatsapp.js`

---

## File Structure

```
tcr-system/
├── pages/
│   ├── index.js              ← Technician form (fill TCR, send to customer)
│   ├── _app.js
│   ├── confirm/
│   │   └── [token].js        ← Customer confirmation page (opens on customer's phone)
│   └── api/
│       ├── submit.js         ← POST: technician submits → returns WA link
│       ├── confirm.js        ← POST: customer confirms → marks token confirmed
│       └── status.js         ← GET:  technician polls → knows when approved
├── lib/
│   ├── store.js              ← Token store (in-memory or Vercel KV)
│   └── whatsapp.js           ← WA message builder + API options
├── .env.example              ← Copy to .env.local for local dev
├── package.json
└── next.config.js
```

---

## Local Development

```bash
cd tcr-system
npm install
cp .env.example .env.local
# Edit .env.local: set NEXT_PUBLIC_BASE_URL=http://localhost:3000
npm run dev
```

Open [http://localhost:3000](http://localhost:3000)

---

## Security Summary

| Threat | Protection |
|--------|-----------|
| Technician self-approves | Form locked in "Pending" state after submit — cannot approve |
| Technician guesses token | UUID v4 = 2^122 possibilities — practically unguessable |
| Token reuse | One-time use — confirmed tokens cannot be re-confirmed |
| Delayed approval after expiry | 30-minute TTL — expired tokens return 404 |
| Man-in-middle on WA | Link goes to customer's registered number — technician cannot intercept WhatsApp |
| Customer coerced | "Do not confirm under pressure" warning on customer page |

---

## Support
Built for GENERAL HVAC Solutions India Pvt Ltd
Authorized O General – Fujitsu Installation Partner
