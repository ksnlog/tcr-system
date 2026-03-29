export default async function handler(req, res) {
  return res.status(200).json({
    gmail_user: process.env.GMAIL_USER,
    pass_length: process.env.GMAIL_APP_PASSWORD?.length,
    pass_preview: process.env.GMAIL_APP_PASSWORD?.slice(0,4) + '...' + process.env.GMAIL_APP_PASSWORD?.slice(-4),
  });
}
```

---

### Step 4 — Commit
Scroll down → click green **"Commit changes"** button.

---

### Step 5 — Wait for Vercel to redeploy (30–60 sec)
Then visit:
```
https://your-project-name.vercel.app/api/test-mail
