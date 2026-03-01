package okhttp3;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class OkHttpClient implements Cloneable {
    static final List<Protocol> D = Util.t(Protocol.HTTP_2, Protocol.HTTP_1_1);
    static final List<ConnectionSpec> E = Util.t(ConnectionSpec.f1895h, ConnectionSpec.f1897j);
    final int A;
    final int B;
    final int C;

    /* renamed from: d  reason: collision with root package name */
    final Dispatcher f1960d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    final Proxy f1961e;

    /* renamed from: f  reason: collision with root package name */
    final List<Protocol> f1962f;

    /* renamed from: g  reason: collision with root package name */
    final List<ConnectionSpec> f1963g;

    /* renamed from: h  reason: collision with root package name */
    final List<Interceptor> f1964h;

    /* renamed from: i  reason: collision with root package name */
    final List<Interceptor> f1965i;

    /* renamed from: j  reason: collision with root package name */
    final EventListener.Factory f1966j;

    /* renamed from: k  reason: collision with root package name */
    final ProxySelector f1967k;

    /* renamed from: l  reason: collision with root package name */
    final CookieJar f1968l;

    /* renamed from: m  reason: collision with root package name */
    final SocketFactory f1969m;

    /* renamed from: n  reason: collision with root package name */
    final SSLSocketFactory f1970n;

    /* renamed from: o  reason: collision with root package name */
    final CertificateChainCleaner f1971o;

    /* renamed from: p  reason: collision with root package name */
    final HostnameVerifier f1972p;

    /* renamed from: q  reason: collision with root package name */
    final CertificatePinner f1973q;

    /* renamed from: r  reason: collision with root package name */
    final Authenticator f1974r;

    /* renamed from: s  reason: collision with root package name */
    final Authenticator f1975s;

    /* renamed from: t  reason: collision with root package name */
    final ConnectionPool f1976t;

    /* renamed from: u  reason: collision with root package name */
    final Dns f1977u;

    /* renamed from: v  reason: collision with root package name */
    final boolean f1978v;

    /* renamed from: w  reason: collision with root package name */
    final boolean f1979w;

    /* renamed from: x  reason: collision with root package name */
    final boolean f1980x;

    /* renamed from: y  reason: collision with root package name */
    final int f1981y;

    /* renamed from: z  reason: collision with root package name */
    final int f1982z;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        Proxy f1984b;

        /* renamed from: h  reason: collision with root package name */
        ProxySelector f1990h;

        /* renamed from: i  reason: collision with root package name */
        CookieJar f1991i;

        /* renamed from: j  reason: collision with root package name */
        SocketFactory f1992j;
        @Nullable

        /* renamed from: k  reason: collision with root package name */
        SSLSocketFactory f1993k;
        @Nullable

        /* renamed from: l  reason: collision with root package name */
        CertificateChainCleaner f1994l;

        /* renamed from: m  reason: collision with root package name */
        HostnameVerifier f1995m;

        /* renamed from: n  reason: collision with root package name */
        CertificatePinner f1996n;

        /* renamed from: o  reason: collision with root package name */
        Authenticator f1997o;

        /* renamed from: p  reason: collision with root package name */
        Authenticator f1998p;

        /* renamed from: q  reason: collision with root package name */
        ConnectionPool f1999q;

        /* renamed from: r  reason: collision with root package name */
        Dns f2000r;

        /* renamed from: s  reason: collision with root package name */
        boolean f2001s;

        /* renamed from: t  reason: collision with root package name */
        boolean f2002t;

        /* renamed from: u  reason: collision with root package name */
        boolean f2003u;

        /* renamed from: v  reason: collision with root package name */
        int f2004v;

        /* renamed from: w  reason: collision with root package name */
        int f2005w;

        /* renamed from: x  reason: collision with root package name */
        int f2006x;

        /* renamed from: y  reason: collision with root package name */
        int f2007y;

        /* renamed from: z  reason: collision with root package name */
        int f2008z;

        /* renamed from: e  reason: collision with root package name */
        final List<Interceptor> f1987e = new ArrayList();

        /* renamed from: f  reason: collision with root package name */
        final List<Interceptor> f1988f = new ArrayList();

        /* renamed from: a  reason: collision with root package name */
        Dispatcher f1983a = new Dispatcher();

        /* renamed from: c  reason: collision with root package name */
        List<Protocol> f1985c = OkHttpClient.D;

        /* renamed from: d  reason: collision with root package name */
        List<ConnectionSpec> f1986d = OkHttpClient.E;

        /* renamed from: g  reason: collision with root package name */
        EventListener.Factory f1989g = EventListener.k(EventListener.f1928a);

        public Builder() {
            ProxySelector proxySelector = ProxySelector.getDefault();
            this.f1990h = proxySelector;
            if (proxySelector == null) {
                this.f1990h = new NullProxySelector();
            }
            this.f1991i = CookieJar.f1919a;
            this.f1992j = SocketFactory.getDefault();
            this.f1995m = OkHostnameVerifier.f2431a;
            this.f1996n = CertificatePinner.f1841c;
            Authenticator authenticator = Authenticator.f1817a;
            this.f1997o = authenticator;
            this.f1998p = authenticator;
            this.f1999q = new ConnectionPool();
            this.f2000r = Dns.f1927a;
            this.f2001s = true;
            this.f2002t = true;
            this.f2003u = true;
            this.f2004v = 0;
            this.f2005w = 10000;
            this.f2006x = 10000;
            this.f2007y = 10000;
            this.f2008z = 0;
        }
    }

    static {
        Internal.f2079a = new Internal() { // from class: okhttp3.OkHttpClient.1
            @Override // okhttp3.internal.Internal
            public void a(Headers.Builder builder, String str) {
                builder.b(str);
            }

            @Override // okhttp3.internal.Internal
            public void b(Headers.Builder builder, String str, String str2) {
                builder.c(str, str2);
            }

            @Override // okhttp3.internal.Internal
            public void c(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z2) {
                connectionSpec.a(sSLSocket, z2);
            }

            @Override // okhttp3.internal.Internal
            public int d(Response.Builder builder) {
                return builder.f2056c;
            }

            @Override // okhttp3.internal.Internal
            public boolean e(ConnectionPool connectionPool, RealConnection realConnection) {
                return connectionPool.b(realConnection);
            }

            @Override // okhttp3.internal.Internal
            public Socket f(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation) {
                return connectionPool.c(address, streamAllocation);
            }

            @Override // okhttp3.internal.Internal
            public boolean g(Address address, Address address2) {
                return address.d(address2);
            }

            @Override // okhttp3.internal.Internal
            public RealConnection h(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route) {
                return connectionPool.d(address, streamAllocation, route);
            }

            @Override // okhttp3.internal.Internal
            public void i(ConnectionPool connectionPool, RealConnection realConnection) {
                connectionPool.f(realConnection);
            }

            @Override // okhttp3.internal.Internal
            public RouteDatabase j(ConnectionPool connectionPool) {
                return connectionPool.f1889e;
            }

            @Override // okhttp3.internal.Internal
            @Nullable
            public IOException k(Call call, @Nullable IOException iOException) {
                return ((RealCall) call).g(iOException);
            }
        };
    }

    public OkHttpClient() {
        this(new Builder());
    }

    private static SSLSocketFactory s(X509TrustManager x509TrustManager) {
        try {
            SSLContext m2 = Platform.l().m();
            m2.init(null, new TrustManager[]{x509TrustManager}, null);
            return m2.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            throw Util.b("No System TLS", e2);
        }
    }

    public SocketFactory A() {
        return this.f1969m;
    }

    public SSLSocketFactory B() {
        return this.f1970n;
    }

    public int C() {
        return this.B;
    }

    public Authenticator a() {
        return this.f1975s;
    }

    public int b() {
        return this.f1981y;
    }

    public CertificatePinner c() {
        return this.f1973q;
    }

    public int d() {
        return this.f1982z;
    }

    public ConnectionPool e() {
        return this.f1976t;
    }

    public List<ConnectionSpec> f() {
        return this.f1963g;
    }

    public CookieJar g() {
        return this.f1968l;
    }

    public Dispatcher h() {
        return this.f1960d;
    }

    public Dns i() {
        return this.f1977u;
    }

    public EventListener.Factory j() {
        return this.f1966j;
    }

    public boolean l() {
        return this.f1979w;
    }

    public boolean m() {
        return this.f1978v;
    }

    public HostnameVerifier n() {
        return this.f1972p;
    }

    public List<Interceptor> o() {
        return this.f1964h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalCache p() {
        return null;
    }

    public List<Interceptor> q() {
        return this.f1965i;
    }

    public Call r(Request request) {
        return RealCall.f(this, request, false);
    }

    public int t() {
        return this.C;
    }

    public List<Protocol> u() {
        return this.f1962f;
    }

    @Nullable
    public Proxy v() {
        return this.f1961e;
    }

    public Authenticator w() {
        return this.f1974r;
    }

    public ProxySelector x() {
        return this.f1967k;
    }

    public int y() {
        return this.A;
    }

    public boolean z() {
        return this.f1980x;
    }

    OkHttpClient(Builder builder) {
        boolean z2;
        this.f1960d = builder.f1983a;
        this.f1961e = builder.f1984b;
        this.f1962f = builder.f1985c;
        List<ConnectionSpec> list = builder.f1986d;
        this.f1963g = list;
        this.f1964h = Util.s(builder.f1987e);
        this.f1965i = Util.s(builder.f1988f);
        this.f1966j = builder.f1989g;
        this.f1967k = builder.f1990h;
        this.f1968l = builder.f1991i;
        this.f1969m = builder.f1992j;
        loop0: while (true) {
            z2 = false;
            for (ConnectionSpec connectionSpec : list) {
                z2 = (z2 || connectionSpec.d()) ? true : z2;
            }
        }
        SSLSocketFactory sSLSocketFactory = builder.f1993k;
        if (sSLSocketFactory == null && z2) {
            X509TrustManager B = Util.B();
            this.f1970n = s(B);
            this.f1971o = CertificateChainCleaner.b(B);
        } else {
            this.f1970n = sSLSocketFactory;
            this.f1971o = builder.f1994l;
        }
        if (this.f1970n != null) {
            Platform.l().f(this.f1970n);
        }
        this.f1972p = builder.f1995m;
        this.f1973q = builder.f1996n.f(this.f1971o);
        this.f1974r = builder.f1997o;
        this.f1975s = builder.f1998p;
        this.f1976t = builder.f1999q;
        this.f1977u = builder.f2000r;
        this.f1978v = builder.f2001s;
        this.f1979w = builder.f2002t;
        this.f1980x = builder.f2003u;
        this.f1981y = builder.f2004v;
        this.f1982z = builder.f2005w;
        this.A = builder.f2006x;
        this.B = builder.f2007y;
        this.C = builder.f2008z;
        if (!this.f1964h.contains(null)) {
            if (!this.f1965i.contains(null)) {
                return;
            }
            throw new IllegalStateException("Null network interceptor: " + this.f1965i);
        }
        throw new IllegalStateException("Null interceptor: " + this.f1964h);
    }
}
