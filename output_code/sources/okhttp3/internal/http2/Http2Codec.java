package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http2Codec implements HttpCodec {

    /* renamed from: f  reason: collision with root package name */
    private static final List<String> f2265f = Util.t("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* renamed from: g  reason: collision with root package name */
    private static final List<String> f2266g = Util.t("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* renamed from: a  reason: collision with root package name */
    private final Interceptor.Chain f2267a;

    /* renamed from: b  reason: collision with root package name */
    final StreamAllocation f2268b;

    /* renamed from: c  reason: collision with root package name */
    private final Http2Connection f2269c;

    /* renamed from: d  reason: collision with root package name */
    private Http2Stream f2270d;

    /* renamed from: e  reason: collision with root package name */
    private final Protocol f2271e;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    class StreamFinishingSource extends ForwardingSource {

        /* renamed from: e  reason: collision with root package name */
        boolean f2272e;

        /* renamed from: f  reason: collision with root package name */
        long f2273f;

        StreamFinishingSource(Source source) {
            super(source);
            this.f2272e = false;
            this.f2273f = 0L;
        }

        private void c(IOException iOException) {
            if (this.f2272e) {
                return;
            }
            this.f2272e = true;
            Http2Codec http2Codec = Http2Codec.this;
            http2Codec.f2268b.r(false, http2Codec, this.f2273f, iOException);
        }

        @Override // okio.Source
        public long A(Buffer buffer, long j2) {
            try {
                long A = b().A(buffer, j2);
                if (A > 0) {
                    this.f2273f += A;
                }
                return A;
            } catch (IOException e2) {
                c(e2);
                throw e2;
            }
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            super.close();
            c(null);
        }
    }

    public Http2Codec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.f2267a = chain;
        this.f2268b = streamAllocation;
        this.f2269c = http2Connection;
        List<Protocol> u2 = okHttpClient.u();
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.f2271e = !u2.contains(protocol) ? Protocol.HTTP_2 : protocol;
    }

    public static List<Header> g(Request request) {
        Headers d2 = request.d();
        ArrayList arrayList = new ArrayList(d2.g() + 4);
        arrayList.add(new Header(Header.f2234f, request.f()));
        arrayList.add(new Header(Header.f2235g, RequestLine.c(request.h())));
        String c2 = request.c("Host");
        if (c2 != null) {
            arrayList.add(new Header(Header.f2237i, c2));
        }
        arrayList.add(new Header(Header.f2236h, request.h().A()));
        int g2 = d2.g();
        for (int i2 = 0; i2 < g2; i2++) {
            ByteString e2 = ByteString.e(d2.e(i2).toLowerCase(Locale.US));
            if (!f2265f.contains(e2.y())) {
                arrayList.add(new Header(e2, d2.h(i2)));
            }
        }
        return arrayList;
    }

    public static Response.Builder h(Headers headers, Protocol protocol) {
        Headers.Builder builder = new Headers.Builder();
        int g2 = headers.g();
        StatusLine statusLine = null;
        for (int i2 = 0; i2 < g2; i2++) {
            String e2 = headers.e(i2);
            String h2 = headers.h(i2);
            if (e2.equals(":status")) {
                statusLine = StatusLine.a("HTTP/1.1 " + h2);
            } else if (!f2266g.contains(e2)) {
                Internal.f2079a.b(builder, e2, h2);
            }
        }
        if (statusLine != null) {
            return new Response.Builder().n(protocol).g(statusLine.f2192b).k(statusLine.f2193c).j(builder.d());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void a() {
        this.f2270d.j().close();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void b(Request request) {
        boolean z2;
        if (this.f2270d != null) {
            return;
        }
        if (request.a() != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Http2Stream J = this.f2269c.J(g(request), z2);
        this.f2270d = J;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        J.n().g(this.f2267a.b(), timeUnit);
        this.f2270d.u().g(this.f2267a.c(), timeUnit);
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody c(Response response) {
        StreamAllocation streamAllocation = this.f2268b;
        streamAllocation.f2153f.q(streamAllocation.f2152e);
        return new RealResponseBody(response.m("Content-Type"), HttpHeaders.b(response), Okio.b(new StreamFinishingSource(this.f2270d.k())));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        Http2Stream http2Stream = this.f2270d;
        if (http2Stream != null) {
            http2Stream.h(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void d() {
        this.f2269c.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink e(Request request, long j2) {
        return this.f2270d.j();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder f(boolean z2) {
        Response.Builder h2 = h(this.f2270d.s(), this.f2271e);
        if (z2 && Internal.f2079a.d(h2) == 100) {
            return null;
        }
        return h2;
    }
}
