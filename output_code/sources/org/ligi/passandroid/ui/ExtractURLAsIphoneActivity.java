package org.ligi.passandroid.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.ligi.kaxt.DialogExtensionsKt;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.ui.quirk_fix.OpenIphoneWebView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ExtractURLAsIphoneActivity extends PassAndroidActivity {
    private final Lazy H;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class DownloadExtractAndStartImportTask extends AsyncTask<Void, Void, String> {
        public DownloadExtractAndStartImportTask() {
        }

        private final String b(String str) {
            Object obj;
            String[] strArr = {"href=\"(.*\\.pkpass.*?)\"", "window.location = '(.*\\.pkpass.*?)'"};
            ArrayList arrayList = new ArrayList(2);
            for (int i2 = 0; i2 < 2; i2++) {
                arrayList.add(Pattern.compile(strArr[i2]).matcher(str));
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    obj = it.next();
                    if (((Matcher) obj).find()) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            Matcher matcher = (Matcher) obj;
            if (matcher == null) {
                return null;
            }
            return matcher.group(1);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(Void... params) {
            Uri uri;
            boolean n2;
            String str;
            String str2;
            Uri data;
            Uri data2;
            Intrinsics.f(params, "params");
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                Request.Builder builder = new Request.Builder();
                Intent intent = ExtractURLAsIphoneActivity.this.getIntent();
                if (intent != null) {
                    uri = intent.getData();
                } else {
                    uri = null;
                }
                Request.Builder h2 = builder.h(new URI(String.valueOf(uri)).toURL());
                h2.c("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
                ResponseBody b2 = okHttpClient.r(h2.b()).k().b();
                if (b2 != null) {
                    String bodyString = b2.p();
                    b2.close();
                    Intrinsics.e(bodyString, "bodyString");
                    String b3 = b(bodyString);
                    if (b3 == null) {
                        return null;
                    }
                    n2 = StringsKt__StringsJVMKt.n(b3, "http", false, 2, null);
                    if (!n2) {
                        StringBuilder sb = new StringBuilder();
                        Intent intent2 = ExtractURLAsIphoneActivity.this.getIntent();
                        if (intent2 != null && (data2 = intent2.getData()) != null) {
                            str = data2.getScheme();
                        } else {
                            str = null;
                        }
                        sb.append(str);
                        sb.append("://");
                        Intent intent3 = ExtractURLAsIphoneActivity.this.getIntent();
                        if (intent3 != null && (data = intent3.getData()) != null) {
                            str2 = data.getHost();
                        } else {
                            str2 = null;
                        }
                        sb.append(str2);
                        sb.append('/');
                        sb.append(b3);
                        return sb.toString();
                    }
                    return b3;
                }
            } catch (IOException e2) {
                ExtractURLAsIphoneActivity.this.p0().a("ExtractURLAsIphoneActivity", e2, false);
            } catch (URISyntaxException e3) {
                ExtractURLAsIphoneActivity.this.p0().a("ExtractURLAsIphoneActivity", e3, false);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        public void onPostExecute(String str) {
            String str2;
            Uri data;
            super.onPostExecute(str);
            if (str == null) {
                Intent intent = new Intent((Context) ExtractURLAsIphoneActivity.this, (Class<?>) OpenIphoneWebView.class);
                intent.setData(ExtractURLAsIphoneActivity.this.getIntent().getData());
                ExtractURLAsIphoneActivity.this.startActivity(intent);
                ExtractURLAsIphoneActivity.this.r0();
                return;
            }
            Tracker p0 = ExtractURLAsIphoneActivity.this.p0();
            Intent intent2 = ExtractURLAsIphoneActivity.this.getIntent();
            if (intent2 != null && (data = intent2.getData()) != null) {
                str2 = data.getHost();
            } else {
                str2 = null;
            }
            p0.b("quirk_fix", "unpack_success", str2, null);
            Intent intent3 = new Intent((Context) ExtractURLAsIphoneActivity.this, (Class<?>) PassImportActivity.class);
            Uri parse = Uri.parse(str);
            Intrinsics.e(parse, "parse(this)");
            intent3.setData(parse);
            ExtractURLAsIphoneActivity.this.startActivity(intent3);
            ExtractURLAsIphoneActivity.this.r0();
        }
    }

    public ExtractURLAsIphoneActivity() {
        Lazy b2;
        b2 = LazyKt__LazyJVMKt.b(new ExtractURLAsIphoneActivity$progressDialog$2(this));
        this.H = b2;
    }

    private final ProgressDialog q0() {
        return (ProgressDialog) this.H.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        Uri uri;
        String str;
        Uri data;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            uri = intent.getData();
        } else {
            uri = null;
        }
        if (uri != null) {
            q0().show();
            Tracker p0 = p0();
            Intent intent2 = getIntent();
            if (intent2 != null && (data = intent2.getData()) != null) {
                str = data.getHost();
            } else {
                str = null;
            }
            p0.b("quirk_fix", "unpack_attempt", str, null);
            new DownloadExtractAndStartImportTask().execute(new Void[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r0() {
        DialogExtensionsKt.a(q0());
        finish();
    }
}
