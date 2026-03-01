package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {

    /* renamed from: b  reason: collision with root package name */
    private final ConnectionPool f2121b;

    /* renamed from: c  reason: collision with root package name */
    private final Route f2122c;

    /* renamed from: d  reason: collision with root package name */
    private Socket f2123d;

    /* renamed from: e  reason: collision with root package name */
    private Socket f2124e;

    /* renamed from: f  reason: collision with root package name */
    private Handshake f2125f;

    /* renamed from: g  reason: collision with root package name */
    private Protocol f2126g;

    /* renamed from: h  reason: collision with root package name */
    private Http2Connection f2127h;

    /* renamed from: i  reason: collision with root package name */
    private BufferedSource f2128i;

    /* renamed from: j  reason: collision with root package name */
    private BufferedSink f2129j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f2130k;

    /* renamed from: l  reason: collision with root package name */
    public int f2131l;

    /* renamed from: m  reason: collision with root package name */
    public int f2132m = 1;

    /* renamed from: n  reason: collision with root package name */
    public final List<Reference<StreamAllocation>> f2133n = new ArrayList();

    /* renamed from: o  reason: collision with root package name */
    public long f2134o = Long.MAX_VALUE;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.f2121b = connectionPool;
        this.f2122c = route;
    }

    private void e(int i2, int i3, Call call, EventListener eventListener) {
        Socket createSocket;
        Proxy b2 = this.f2122c.b();
        Address a2 = this.f2122c.a();
        if (b2.type() != Proxy.Type.DIRECT && b2.type() != Proxy.Type.HTTP) {
            createSocket = new Socket(b2);
        } else {
            createSocket = a2.j().createSocket();
        }
        this.f2123d = createSocket;
        eventListener.f(call, this.f2122c.d(), b2);
        this.f2123d.setSoTimeout(i3);
        try {
            Platform.l().h(this.f2123d, this.f2122c.d(), i2);
            try {
                this.f2128i = Okio.b(Okio.k(this.f2123d));
                this.f2129j = Okio.a(Okio.g(this.f2123d));
            } catch (NullPointerException e2) {
                if (!"throw with null exception".equals(e2.getMessage())) {
                    return;
                }
                throw new IOException(e2);
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f2122c.d());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    private void f(ConnectionSpecSelector connectionSpecSelector) {
        SSLSocket sSLSocket;
        Protocol protocol;
        Address a2 = this.f2122c.a();
        SSLSocket sSLSocket2 = null;
        String str = null;
        try {
            try {
                sSLSocket = (SSLSocket) a2.k().createSocket(this.f2123d, a2.l().l(), a2.l().w(), true);
            } catch (AssertionError e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            ConnectionSpec a3 = connectionSpecSelector.a(sSLSocket);
            if (a3.f()) {
                Platform.l().g(sSLSocket, a2.l().l(), a2.f());
            }
            sSLSocket.startHandshake();
            SSLSession session = sSLSocket.getSession();
            Handshake b2 = Handshake.b(session);
            if (!a2.e().verify(a2.l().l(), session)) {
                List<Certificate> c2 = b2.c();
                if (!c2.isEmpty()) {
                    X509Certificate x509Certificate = (X509Certificate) c2.get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + a2.l().l() + " not verified:\n    certificate: " + CertificatePinner.c(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.a(x509Certificate));
                }
                throw new SSLPeerUnverifiedException("Hostname " + a2.l().l() + " not verified (no certificates)");
            }
            a2.a().a(a2.l().l(), b2.c());
            if (a3.f()) {
                str = Platform.l().n(sSLSocket);
            }
            this.f2124e = sSLSocket;
            this.f2128i = Okio.b(Okio.k(sSLSocket));
            this.f2129j = Okio.a(Okio.g(this.f2124e));
            this.f2125f = b2;
            if (str != null) {
                protocol = Protocol.a(str);
            } else {
                protocol = Protocol.HTTP_1_1;
            }
            this.f2126g = protocol;
            Platform.l().a(sSLSocket);
        } catch (AssertionError e3) {
            e = e3;
            if (Util.z(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            sSLSocket2 = sSLSocket;
            if (sSLSocket2 != null) {
                Platform.l().a(sSLSocket2);
            }
            Util.g(sSLSocket2);
            throw th;
        }
    }

    private void g(int i2, int i3, int i4, Call call, EventListener eventListener) {
        Request i5 = i();
        HttpUrl h2 = i5.h();
        for (int i6 = 0; i6 < 21; i6++) {
            e(i2, i3, call, eventListener);
            i5 = h(i3, i4, i5, h2);
            if (i5 != null) {
                Util.g(this.f2123d);
                this.f2123d = null;
                this.f2129j = null;
                this.f2128i = null;
                eventListener.d(call, this.f2122c.d(), this.f2122c.b(), null);
            } else {
                return;
            }
        }
    }

    private Request h(int i2, int i3, Request request, HttpUrl httpUrl) {
        String str = "CONNECT " + Util.r(httpUrl, true) + " HTTP/1.1";
        while (true) {
            Http1Codec http1Codec = new Http1Codec(null, null, this.f2128i, this.f2129j);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.f2128i.d().g(i2, timeUnit);
            this.f2129j.d().g(i3, timeUnit);
            http1Codec.o(request.d(), str);
            http1Codec.a();
            Response c2 = http1Codec.f(false).p(request).c();
            long b2 = HttpHeaders.b(c2);
            if (b2 == -1) {
                b2 = 0;
            }
            Source k2 = http1Codec.k(b2);
            Util.C(k2, Integer.MAX_VALUE, timeUnit);
            k2.close();
            int e2 = c2.e();
            if (e2 != 200) {
                if (e2 == 407) {
                    Request a2 = this.f2122c.a().h().a(this.f2122c, c2);
                    if (a2 != null) {
                        if ("close".equalsIgnoreCase(c2.m("Connection"))) {
                            return a2;
                        }
                        request = a2;
                    } else {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + c2.e());
                }
            } else if (this.f2128i.a().u() && this.f2129j.a().u()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        }
    }

    private Request i() {
        Request b2 = new Request.Builder().i(this.f2122c.a().l()).e("CONNECT", null).c("Host", Util.r(this.f2122c.a().l(), true)).c("Proxy-Connection", "Keep-Alive").c("User-Agent", Version.a()).b();
        Request a2 = this.f2122c.a().h().a(this.f2122c, new Response.Builder().p(b2).n(Protocol.HTTP_1_1).g(407).k("Preemptive Authenticate").b(Util.f2083c).q(-1L).o(-1L).i("Proxy-Authenticate", "OkHttp-Preemptive").c());
        if (a2 != null) {
            return a2;
        }
        return b2;
    }

    private void j(ConnectionSpecSelector connectionSpecSelector, int i2, Call call, EventListener eventListener) {
        if (this.f2122c.a().k() == null) {
            List<Protocol> f2 = this.f2122c.a().f();
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (f2.contains(protocol)) {
                this.f2124e = this.f2123d;
                this.f2126g = protocol;
                r(i2);
                return;
            }
            this.f2124e = this.f2123d;
            this.f2126g = Protocol.HTTP_1_1;
            return;
        }
        eventListener.u(call);
        f(connectionSpecSelector);
        eventListener.t(call, this.f2125f);
        if (this.f2126g == Protocol.HTTP_2) {
            r(i2);
        }
    }

    private void r(int i2) {
        this.f2124e.setSoTimeout(0);
        Http2Connection a2 = new Http2Connection.Builder(true).d(this.f2124e, this.f2122c.a().l().l(), this.f2128i, this.f2129j).b(this).c(i2).a();
        this.f2127h = a2;
        a2.Y();
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void a(Http2Connection http2Connection) {
        synchronized (this.f2121b) {
            this.f2132m = http2Connection.G();
        }
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void b(Http2Stream http2Stream) {
        http2Stream.f(ErrorCode.REFUSED_STREAM);
    }

    public void c() {
        Util.g(this.f2123d);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0090 A[Catch: IOException -> 0x00f9, TRY_LEAVE, TryCatch #0 {IOException -> 0x00f9, blocks: (B:18:0x0088, B:20:0x0090), top: B:67:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0142 A[EDGE_INSN: B:77:0x0142->B:61:0x0142 ?: BREAK  ] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(int r17, int r18, int r19, int r20, boolean r21, okhttp3.Call r22, okhttp3.EventListener r23) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.d(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    public Handshake k() {
        return this.f2125f;
    }

    public boolean l(Address address, @Nullable Route route) {
        if (this.f2133n.size() >= this.f2132m || this.f2130k || !Internal.f2079a.g(this.f2122c.a(), address)) {
            return false;
        }
        if (address.l().l().equals(p().a().l().l())) {
            return true;
        }
        if (this.f2127h == null || route == null || route.b().type() != Proxy.Type.DIRECT || this.f2122c.b().type() != Proxy.Type.DIRECT || !this.f2122c.d().equals(route.d()) || route.a().e() != OkHostnameVerifier.f2431a || !s(address.l())) {
            return false;
        }
        try {
            address.a().a(address.l().l(), k().c());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean m(boolean z2) {
        if (this.f2124e.isClosed() || this.f2124e.isInputShutdown() || this.f2124e.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.f2127h;
        if (http2Connection != null) {
            return http2Connection.E(System.nanoTime());
        }
        if (z2) {
            try {
                int soTimeout = this.f2124e.getSoTimeout();
                try {
                    this.f2124e.setSoTimeout(1);
                    if (this.f2128i.u()) {
                        return false;
                    }
                    return true;
                } finally {
                    this.f2124e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean n() {
        return this.f2127h != null;
    }

    public HttpCodec o(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation) {
        if (this.f2127h != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.f2127h);
        }
        this.f2124e.setSoTimeout(chain.b());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.f2128i.d().g(chain.b(), timeUnit);
        this.f2129j.d().g(chain.c(), timeUnit);
        return new Http1Codec(okHttpClient, streamAllocation, this.f2128i, this.f2129j);
    }

    public Route p() {
        return this.f2122c;
    }

    public Socket q() {
        return this.f2124e;
    }

    public boolean s(HttpUrl httpUrl) {
        if (httpUrl.w() != this.f2122c.a().l().w()) {
            return false;
        }
        if (httpUrl.l().equals(this.f2122c.a().l().l())) {
            return true;
        }
        if (this.f2125f == null || !OkHostnameVerifier.f2431a.c(httpUrl.l(), (X509Certificate) this.f2125f.c().get(0))) {
            return false;
        }
        return true;
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f2122c.a().l().l());
        sb.append(":");
        sb.append(this.f2122c.a().l().w());
        sb.append(", proxy=");
        sb.append(this.f2122c.b());
        sb.append(" hostAddress=");
        sb.append(this.f2122c.d());
        sb.append(" cipherSuite=");
        Handshake handshake = this.f2125f;
        if (handshake != null) {
            obj = handshake.a();
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f2126g);
        sb.append('}');
        return sb.toString();
    }
}
