export default async function handler(req, res) {
  return res.status(200).json({
    gmail_user: process.env.GMAIL_USER,
    pass_length: process.env.GMAIL_APP_PASSWORD?.length,
    pass_preview: process.env.GMAIL_APP_PASSWORD?.slice(0,4) + '...' + process.env.GMAIL_APP_PASSWORD?.slice(-4),
  });
}
