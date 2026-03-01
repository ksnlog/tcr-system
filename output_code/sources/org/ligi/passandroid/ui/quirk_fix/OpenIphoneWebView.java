package org.ligi.passandroid.ui.quirk_fix;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import androidx.core.content.ContextCompat;
import androidx.webkit.WebResourceErrorCompat;
import androidx.webkit.WebViewClientCompat;
import kotlin.jvm.internal.Intrinsics;
import net.steamcrafted.loadtoast.LoadToast;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class OpenIphoneWebView extends Activity {
    @Override // android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data == null) {
            return;
        }
        WebView webView = new WebView(this);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(String.valueOf(data));
        setContentView(webView);
        final LoadToast l2 = new LoadToast(this).k("Loading").j(ContextCompat.c(this, 2131099745)).l();
        webView.setWebViewClient(new WebViewClientCompat() { // from class: org.ligi.passandroid.ui.quirk_fix.OpenIphoneWebView$onCreate$1
            public void a(WebView view, WebResourceRequest request, WebResourceErrorCompat error) {
                Intrinsics.f(view, "view");
                Intrinsics.f(request, "request");
                Intrinsics.f(error, "error");
                LoadToast.this.i();
            }

            /* JADX WARN: Multi-variable type inference failed */
            public void onPageFinished(WebView view, String url) {
                Intrinsics.f(view, "view");
                Intrinsics.f(url, "url");
                super/*android.webkit.WebViewClient*/.onPageFinished(view, url);
                LoadToast.this.n();
            }
        });
    }
}
