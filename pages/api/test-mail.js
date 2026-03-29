export default async function handler(req, res) {
  return res.status(200).json({
    gmail_user: process.env.GMAIL_USER,
    pass_length: process.env.GMAIL_APP_PASSWORD?.length,
    pass_preview: process.env.GMAIL_APP_PASSWORD?.slice(0,4) + '...' + process.env.GMAIL_APP_PASSWORD?.slice(-4),
  });
}
```

---

### Step 6 — Save/Commit the file
- Scroll down to the bottom of the page
- You'll see **"Commit new file"** section
- Just click the green **"Commit new file"** button

---

### Step 7 — Wait 30–60 seconds for Vercel to redeploy
Then visit:
```
https://your-vercel-site.com/api/test-mail
