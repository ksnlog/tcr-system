package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http1Codec implements HttpCodec {

    /* renamed from: a  reason: collision with root package name */
    final OkHttpClient f2194a;

    /* renamed from: b  reason: collision with root package name */
    final StreamAllocation f2195b;

    /* renamed from: c  reason: collision with root package name */
    final BufferedSource f2196c;

    /* renamed from: d  reason: collision with root package name */
    final BufferedSink f2197d;

    /* renamed from: e  reason: collision with root package name */
    int f2198e = 0;

    /* renamed from: f  reason: collision with root package name */
    private long f2199f = 262144;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public abstract class AbstractSource implements Source {

        /* renamed from: d  reason: collision with root package name */
        protected final ForwardingTimeout f2200d;

        /* renamed from: e  reason: collision with root package name */
        protected boolean f2201e;

        /* renamed from: f  reason: collision with root package name */
        protected long f2202f;

        private AbstractSource() {
            this.f2200d = new ForwardingTimeout(Http1Codec.this.f2196c.d());
            this.f2202f = 0L;
        }

        @Override // okio.Source
        public long A(Buffer buffer, long j2) {
            try {
                long A = Http1Codec.this.f2196c.A(buffer, j2);
                if (A > 0) {
                    this.f2202f += A;
                }
                return A;
            } catch (IOException e2) {
                b(false, e2);
                throw e2;
            }
        }

        protected final void b(boolean z2, IOException iOException) {
            Http1Codec http1Codec = Http1Codec.this;
            int i2 = http1Codec.f2198e;
            if (i2 == 6) {
                return;
            }
            if (i2 == 5) {
                http1Codec.g(this.f2200d);
                Http1Codec http1Codec2 = Http1Codec.this;
                http1Codec2.f2198e = 6;
                StreamAllocation streamAllocation = http1Codec2.f2195b;
                if (streamAllocation != null) {
                    streamAllocation.r(!z2, http1Codec2, this.f2202f, iOException);
                    return;
                }
                return;
            }
            throw new IllegalStateException("state: " + Http1Codec.this.f2198e);
        }

        @Override // okio.Source
        public Timeout d() {
            return this.f2200d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class ChunkedSink implements Sink {

        /* renamed from: d  reason: collision with root package name */
        private final ForwardingTimeout f2204d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f2205e;

        ChunkedSink() {
            this.f2204d = new ForwardingTimeout(Http1Codec.this.f2197d.d());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.f2205e) {
                return;
            }
            this.f2205e = true;
            Http1Codec.this.f2197d.H("0\r\n\r\n");
            Http1Codec.this.g(this.f2204d);
            Http1Codec.this.f2198e = 3;
        }

        @Override // okio.Sink
        public Timeout d() {
            return this.f2204d;
        }

        @Override // okio.Sink
        public void f(Buffer buffer, long j2) {
            if (!this.f2205e) {
                if (j2 == 0) {
                    return;
                }
                Http1Codec.this.f2197d.i(j2);
                Http1Codec.this.f2197d.H("\r\n");
                Http1Codec.this.f2197d.f(buffer, j2);
                Http1Codec.this.f2197d.H("\r\n");
                return;
            }
            throw new IllegalStateException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            if (this.f2205e) {
                return;
            }
            Http1Codec.this.f2197d.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class ChunkedSource extends AbstractSource {

        /* renamed from: h  reason: collision with root package name */
        private final HttpUrl f2207h;

        /* renamed from: i  reason: collision with root package name */
        private long f2208i;

        /* renamed from: j  reason: collision with root package name */
        private boolean f2209j;

        ChunkedSource(HttpUrl httpUrl) {
            super();
            this.f2208i = -1L;
            this.f2209j = true;
            this.f2207h = httpUrl;
        }

        private void c() {
            if (this.f2208i != -1) {
                Http1Codec.this.f2196c.q();
            }
            try {
                this.f2208i = Http1Codec.this.f2196c.L();
                String trim = Http1Codec.this.f2196c.q().trim();
                if (this.f2208i >= 0 && (trim.isEmpty() || trim.startsWith(";"))) {
                    if (this.f2208i == 0) {
                        this.f2209j = false;
                        HttpHeaders.e(Http1Codec.this.f2194a.g(), this.f2207h, Http1Codec.this.n());
                        b(true, null);
                        return;
                    }
                    return;
                }
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f2208i + trim + "\"");
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long A(Buffer buffer, long j2) {
            if (j2 >= 0) {
                if (!this.f2201e) {
                    if (!this.f2209j) {
                        return -1L;
                    }
                    long j3 = this.f2208i;
                    if (j3 == 0 || j3 == -1) {
                        c();
                        if (!this.f2209j) {
                            return -1L;
                        }
                    }
                    long A = super.A(buffer, Math.min(j2, this.f2208i));
                    if (A != -1) {
                        this.f2208i -= A;
                        return A;
                    }
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    b(false, protocolException);
                    throw protocolException;
                }
                throw new IllegalStateException("closed");
            }
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2201e) {
                return;
            }
            if (this.f2209j && !Util.o(this, 100, TimeUnit.MILLISECONDS)) {
                b(false, null);
            }
            this.f2201e = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class FixedLengthSink implements Sink {

        /* renamed from: d  reason: collision with root package name */
        private final ForwardingTimeout f2211d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f2212e;

        /* renamed from: f  reason: collision with root package name */
        private long f2213f;

        FixedLengthSink(long j2) {
            this.f2211d = new ForwardingTimeout(Http1Codec.this.f2197d.d());
            this.f2213f = j2;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2212e) {
                return;
            }
            this.f2212e = true;
            if (this.f2213f <= 0) {
                Http1Codec.this.g(this.f2211d);
                Http1Codec.this.f2198e = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }

        @Override // okio.Sink
        public Timeout d() {
            return this.f2211d;
        }

        @Override // okio.Sink
        public void f(Buffer buffer, long j2) {
            if (!this.f2212e) {
                Util.e(buffer.size(), 0L, j2);
                if (j2 <= this.f2213f) {
                    Http1Codec.this.f2197d.f(buffer, j2);
                    this.f2213f -= j2;
                    return;
                }
                throw new ProtocolException("expected " + this.f2213f + " bytes but received " + j2);
            }
            throw new IllegalStateException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.f2212e) {
                return;
            }
            Http1Codec.this.f2197d.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class FixedLengthSource extends AbstractSource {

        /* renamed from: h  reason: collision with root package name */
        private long f2215h;

        FixedLengthSource(long j2) {
            super();
            this.f2215h = j2;
            if (j2 == 0) {
                b(true, null);
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long A(Buffer buffer, long j2) {
            if (j2 >= 0) {
                if (!this.f2201e) {
                    long j3 = this.f2215h;
                    if (j3 == 0) {
                        return -1L;
                    }
                    long A = super.A(buffer, Math.min(j3, j2));
                    if (A != -1) {
                        long j4 = this.f2215h - A;
                        this.f2215h = j4;
                        if (j4 == 0) {
                            b(true, null);
                        }
                        return A;
                    }
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    b(false, protocolException);
                    throw protocolException;
                }
                throw new IllegalStateException("closed");
            }
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2201e) {
                return;
            }
            if (this.f2215h != 0 && !Util.o(this, 100, TimeUnit.MILLISECONDS)) {
                b(false, null);
            }
            this.f2201e = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class UnknownLengthSource extends AbstractSource {

        /* renamed from: h  reason: collision with root package name */
        private boolean f2217h;

        UnknownLengthSource() {
            super();
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long A(Buffer buffer, long j2) {
            if (j2 >= 0) {
                if (!this.f2201e) {
                    if (this.f2217h) {
                        return -1L;
                    }
                    long A = super.A(buffer, j2);
                    if (A == -1) {
                        this.f2217h = true;
                        b(true, null);
                        return -1L;
                    }
                    return A;
                }
                throw new IllegalStateException("closed");
            }
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2201e) {
                return;
            }
            if (!this.f2217h) {
                b(false, null);
            }
            this.f2201e = true;
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.f2194a = okHttpClient;
        this.f2195b = streamAllocation;
        this.f2196c = bufferedSource;
        this.f2197d = bufferedSink;
    }

    private String m() {
        String C = this.f2196c.C(this.f2199f);
        this.f2199f -= C.length();
        return C;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void a() {
        this.f2197d.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void b(Request request) {
        o(request.d(), RequestLine.a(request, this.f2195b.d().p().b().type()));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody c(Response response) {
        StreamAllocation streamAllocation = this.f2195b;
        streamAllocation.f2153f.q(streamAllocation.f2152e);
        String m2 = response.m("Content-Type");
        if (!HttpHeaders.c(response)) {
            return new RealResponseBody(m2, 0L, Okio.b(k(0L)));
        }
        if ("chunked".equalsIgnoreCase(response.m("Transfer-Encoding"))) {
            return new RealResponseBody(m2, -1L, Okio.b(i(response.x().h())));
        }
        long b2 = HttpHeaders.b(response);
        if (b2 != -1) {
            return new RealResponseBody(m2, b2, Okio.b(k(b2)));
        }
        return new RealResponseBody(m2, -1L, Okio.b(l()));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        RealConnection d2 = this.f2195b.d();
        if (d2 != null) {
            d2.c();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void d() {
        this.f2197d.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink e(Request request, long j2) {
        if ("chunked".equalsIgnoreCase(request.c("Transfer-Encoding"))) {
            return h();
        }
        if (j2 != -1) {
            return j(j2);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder f(boolean z2) {
        int i2 = this.f2198e;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException("state: " + this.f2198e);
        }
        try {
            StatusLine a2 = StatusLine.a(m());
            Response.Builder j2 = new Response.Builder().n(a2.f2191a).g(a2.f2192b).k(a2.f2193c).j(n());
            if (z2 && a2.f2192b == 100) {
                return null;
            }
            if (a2.f2192b == 100) {
                this.f2198e = 3;
                return j2;
            }
            this.f2198e = 4;
            return j2;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f2195b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    void g(ForwardingTimeout forwardingTimeout) {
        Timeout i2 = forwardingTimeout.i();
        forwardingTimeout.j(Timeout.f2503e);
        i2.a();
        i2.b();
    }

    public Sink h() {
        if (this.f2198e == 1) {
            this.f2198e = 2;
            return new ChunkedSink();
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }

    public Source i(HttpUrl httpUrl) {
        if (this.f2198e == 4) {
            this.f2198e = 5;
            return new ChunkedSource(httpUrl);
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }

    public Sink j(long j2) {
        if (this.f2198e == 1) {
            this.f2198e = 2;
            return new FixedLengthSink(j2);
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }

    public Source k(long j2) {
        if (this.f2198e == 4) {
            this.f2198e = 5;
            return new FixedLengthSource(j2);
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }

    public Source l() {
        if (this.f2198e == 4) {
            StreamAllocation streamAllocation = this.f2195b;
            if (streamAllocation != null) {
                this.f2198e = 5;
                streamAllocation.j();
                return new UnknownLengthSource();
            }
            throw new IllegalStateException("streamAllocation == null");
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }

    public Headers n() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String m2 = m();
            if (m2.length() != 0) {
                Internal.f2079a.a(builder, m2);
            } else {
                return builder.d();
            }
        }
    }

    public void o(Headers headers, String str) {
        if (this.f2198e == 0) {
            this.f2197d.H(str).H("\r\n");
            int g2 = headers.g();
            for (int i2 = 0; i2 < g2; i2++) {
                this.f2197d.H(headers.e(i2)).H(": ").H(headers.h(i2)).H("\r\n");
            }
            this.f2197d.H("\r\n");
            this.f2198e = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.f2198e);
    }
}
