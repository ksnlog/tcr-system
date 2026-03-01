package okhttp3.internal.http;

import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CallServerInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f2164a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class CountingSink extends ForwardingSink {

        /* renamed from: e  reason: collision with root package name */
        long f2165e;

        CountingSink(Sink sink) {
            super(sink);
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void f(Buffer buffer, long j2) {
            super.f(buffer, j2);
            this.f2165e += j2;
        }
    }

    public CallServerInterceptor(boolean z2) {
        this.f2164a = z2;
    }

    @Override // okhttp3.Interceptor
    public Response a(Interceptor.Chain chain) {
        Response c2;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpCodec i2 = realInterceptorChain.i();
        StreamAllocation k2 = realInterceptorChain.k();
        RealConnection realConnection = (RealConnection) realInterceptorChain.g();
        Request e2 = realInterceptorChain.e();
        long currentTimeMillis = System.currentTimeMillis();
        realInterceptorChain.h().o(realInterceptorChain.f());
        i2.b(e2);
        realInterceptorChain.h().n(realInterceptorChain.f(), e2);
        Response.Builder builder = null;
        if (HttpMethod.a(e2.f()) && e2.a() != null) {
            if ("100-continue".equalsIgnoreCase(e2.c("Expect"))) {
                i2.d();
                realInterceptorChain.h().s(realInterceptorChain.f());
                builder = i2.f(true);
            }
            if (builder == null) {
                realInterceptorChain.h().m(realInterceptorChain.f());
                CountingSink countingSink = new CountingSink(i2.e(e2, e2.a().a()));
                BufferedSink a2 = Okio.a(countingSink);
                e2.a().e(a2);
                a2.close();
                realInterceptorChain.h().l(realInterceptorChain.f(), countingSink.f2165e);
            } else if (!realConnection.n()) {
                k2.j();
            }
        }
        i2.a();
        if (builder == null) {
            realInterceptorChain.h().s(realInterceptorChain.f());
            builder = i2.f(false);
        }
        Response c3 = builder.p(e2).h(k2.d().k()).q(currentTimeMillis).o(System.currentTimeMillis()).c();
        int e3 = c3.e();
        if (e3 == 100) {
            c3 = i2.f(false).p(e2).h(k2.d().k()).q(currentTimeMillis).o(System.currentTimeMillis()).c();
            e3 = c3.e();
        }
        realInterceptorChain.h().r(realInterceptorChain.f(), c3);
        if (this.f2164a && e3 == 101) {
            c2 = c3.p().b(Util.f2083c).c();
        } else {
            c2 = c3.p().b(i2.c(c3)).c();
        }
        if ("close".equalsIgnoreCase(c2.x().c("Connection")) || "close".equalsIgnoreCase(c2.m("Connection"))) {
            k2.j();
        }
        if ((e3 != 204 && e3 != 205) || c2.b().e() <= 0) {
            return c2;
        }
        throw new ProtocolException("HTTP " + e3 + " had non-zero Content-Length: " + c2.b().e());
    }
}
