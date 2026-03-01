package okhttp3.internal.cache;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CacheInterceptor implements Interceptor {
    public CacheInterceptor(InternalCache internalCache) {
    }

    private static Headers b(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int g2 = headers.g();
        for (int i2 = 0; i2 < g2; i2++) {
            String e2 = headers.e(i2);
            String h2 = headers.h(i2);
            if ((!"Warning".equalsIgnoreCase(e2) || !h2.startsWith("1")) && (c(e2) || !d(e2) || headers2.c(e2) == null)) {
                Internal.f2079a.b(builder, e2, h2);
            }
        }
        int g3 = headers2.g();
        for (int i3 = 0; i3 < g3; i3++) {
            String e3 = headers2.e(i3);
            if (!c(e3) && d(e3)) {
                Internal.f2079a.b(builder, e3, headers2.h(i3));
            }
        }
        return builder.d();
    }

    static boolean c(String str) {
        if (!"Content-Length".equalsIgnoreCase(str) && !"Content-Encoding".equalsIgnoreCase(str) && !"Content-Type".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    static boolean d(String str) {
        if (!"Connection".equalsIgnoreCase(str) && !"Keep-Alive".equalsIgnoreCase(str) && !"Proxy-Authenticate".equalsIgnoreCase(str) && !"Proxy-Authorization".equalsIgnoreCase(str) && !"TE".equalsIgnoreCase(str) && !"Trailers".equalsIgnoreCase(str) && !"Transfer-Encoding".equalsIgnoreCase(str) && !"Upgrade".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    private static Response e(Response response) {
        if (response != null && response.b() != null) {
            return response.p().b(null).c();
        }
        return response;
    }

    @Override // okhttp3.Interceptor
    public Response a(Interceptor.Chain chain) {
        CacheStrategy c2 = new CacheStrategy.Factory(System.currentTimeMillis(), chain.e(), null).c();
        Request request = c2.f2102a;
        Response response = c2.f2103b;
        if (request == null && response == null) {
            return new Response.Builder().p(chain.e()).n(Protocol.HTTP_1_1).g(504).k("Unsatisfiable Request (only-if-cached)").b(Util.f2083c).q(-1L).o(System.currentTimeMillis()).c();
        }
        if (request == null) {
            return response.p().d(e(response)).c();
        }
        Response d2 = chain.d(request);
        if (response != null) {
            if (d2.e() != 304) {
                Util.f(response.b());
            } else {
                response.p().j(b(response.o(), d2.o())).q(d2.z()).o(d2.w()).d(e(response)).l(e(d2)).c();
                d2.b().close();
                throw null;
            }
        }
        return d2.p().d(e(response)).l(e(d2)).c();
    }
}
