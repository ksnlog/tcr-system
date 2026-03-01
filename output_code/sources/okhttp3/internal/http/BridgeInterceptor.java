package okhttp3.internal.http;

import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BridgeInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private final CookieJar f2163a;

    public BridgeInterceptor(CookieJar cookieJar) {
        this.f2163a = cookieJar;
    }

    private String b(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i2);
            sb.append(cookie.c());
            sb.append('=');
            sb.append(cookie.k());
        }
        return sb.toString();
    }

    @Override // okhttp3.Interceptor
    public Response a(Interceptor.Chain chain) {
        Request e2 = chain.e();
        Request.Builder g2 = e2.g();
        RequestBody a2 = e2.a();
        if (a2 != null) {
            MediaType b2 = a2.b();
            if (b2 != null) {
                g2.c("Content-Type", b2.toString());
            }
            long a3 = a2.a();
            if (a3 != -1) {
                g2.c("Content-Length", Long.toString(a3));
                g2.f("Transfer-Encoding");
            } else {
                g2.c("Transfer-Encoding", "chunked");
                g2.f("Content-Length");
            }
        }
        boolean z2 = false;
        if (e2.c("Host") == null) {
            g2.c("Host", Util.r(e2.h(), false));
        }
        if (e2.c("Connection") == null) {
            g2.c("Connection", "Keep-Alive");
        }
        if (e2.c("Accept-Encoding") == null && e2.c("Range") == null) {
            g2.c("Accept-Encoding", "gzip");
            z2 = true;
        }
        List<Cookie> a4 = this.f2163a.a(e2.h());
        if (!a4.isEmpty()) {
            g2.c("Cookie", b(a4));
        }
        if (e2.c("User-Agent") == null) {
            g2.c("User-Agent", Version.a());
        }
        Response d2 = chain.d(g2.b());
        HttpHeaders.e(this.f2163a, e2.h(), d2.o());
        Response.Builder p2 = d2.p().p(e2);
        if (z2 && "gzip".equalsIgnoreCase(d2.m("Content-Encoding")) && HttpHeaders.c(d2)) {
            GzipSource gzipSource = new GzipSource(d2.b().o());
            p2.j(d2.o().f().e("Content-Encoding").e("Content-Length").d());
            p2.b(new RealResponseBody(d2.m("Content-Type"), -1L, Okio.b(gzipSource)));
        }
        return p2.c();
    }
}
