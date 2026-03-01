package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StreamAllocation {

    /* renamed from: a  reason: collision with root package name */
    public final Address f2148a;

    /* renamed from: b  reason: collision with root package name */
    private RouteSelector.Selection f2149b;

    /* renamed from: c  reason: collision with root package name */
    private Route f2150c;

    /* renamed from: d  reason: collision with root package name */
    private final ConnectionPool f2151d;

    /* renamed from: e  reason: collision with root package name */
    public final Call f2152e;

    /* renamed from: f  reason: collision with root package name */
    public final EventListener f2153f;

    /* renamed from: g  reason: collision with root package name */
    private final Object f2154g;

    /* renamed from: h  reason: collision with root package name */
    private final RouteSelector f2155h;

    /* renamed from: i  reason: collision with root package name */
    private int f2156i;

    /* renamed from: j  reason: collision with root package name */
    private RealConnection f2157j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f2158k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f2159l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f2160m;

    /* renamed from: n  reason: collision with root package name */
    private HttpCodec f2161n;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {

        /* renamed from: a  reason: collision with root package name */
        public final Object f2162a;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.f2162a = obj;
        }
    }

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call, EventListener eventListener, Object obj) {
        this.f2151d = connectionPool;
        this.f2148a = address;
        this.f2152e = call;
        this.f2153f = eventListener;
        this.f2155h = new RouteSelector(address, p(), call, eventListener);
        this.f2154g = obj;
    }

    private Socket e(boolean z2, boolean z3, boolean z4) {
        Socket socket;
        if (z4) {
            this.f2161n = null;
        }
        if (z3) {
            this.f2159l = true;
        }
        RealConnection realConnection = this.f2157j;
        if (realConnection == null) {
            return null;
        }
        if (z2) {
            realConnection.f2130k = true;
        }
        if (this.f2161n != null) {
            return null;
        }
        if (!this.f2159l && !realConnection.f2130k) {
            return null;
        }
        l(realConnection);
        if (this.f2157j.f2133n.isEmpty()) {
            this.f2157j.f2134o = System.nanoTime();
            if (Internal.f2079a.e(this.f2151d, this.f2157j)) {
                socket = this.f2157j.q();
                this.f2157j = null;
                return socket;
            }
        }
        socket = null;
        this.f2157j = null;
        return socket;
    }

    private RealConnection f(int i2, int i3, int i4, int i5, boolean z2) {
        RealConnection realConnection;
        Socket n2;
        RealConnection realConnection2;
        Socket socket;
        Route route;
        boolean z3;
        boolean z4;
        RouteSelector.Selection selection;
        synchronized (this.f2151d) {
            if (!this.f2159l) {
                if (this.f2161n == null) {
                    if (!this.f2160m) {
                        realConnection = this.f2157j;
                        n2 = n();
                        realConnection2 = this.f2157j;
                        socket = null;
                        if (realConnection2 != null) {
                            realConnection = null;
                        } else {
                            realConnection2 = null;
                        }
                        if (!this.f2158k) {
                            realConnection = null;
                        }
                        if (realConnection2 == null) {
                            Internal.f2079a.h(this.f2151d, this.f2148a, this, null);
                            RealConnection realConnection3 = this.f2157j;
                            if (realConnection3 != null) {
                                realConnection2 = realConnection3;
                                z3 = true;
                                route = null;
                            } else {
                                route = this.f2150c;
                            }
                        } else {
                            route = null;
                        }
                        z3 = false;
                    } else {
                        throw new IOException("Canceled");
                    }
                } else {
                    throw new IllegalStateException("codec != null");
                }
            } else {
                throw new IllegalStateException("released");
            }
        }
        Util.g(n2);
        if (realConnection != null) {
            this.f2153f.h(this.f2152e, realConnection);
        }
        if (z3) {
            this.f2153f.g(this.f2152e, realConnection2);
        }
        if (realConnection2 != null) {
            this.f2150c = this.f2157j.p();
            return realConnection2;
        }
        if (route == null && ((selection = this.f2149b) == null || !selection.b())) {
            this.f2149b = this.f2155h.e();
            z4 = true;
        } else {
            z4 = false;
        }
        synchronized (this.f2151d) {
            if (!this.f2160m) {
                if (z4) {
                    List<Route> a2 = this.f2149b.a();
                    int size = a2.size();
                    int i6 = 0;
                    while (true) {
                        if (i6 >= size) {
                            break;
                        }
                        Route route2 = a2.get(i6);
                        Internal.f2079a.h(this.f2151d, this.f2148a, this, route2);
                        RealConnection realConnection4 = this.f2157j;
                        if (realConnection4 != null) {
                            this.f2150c = route2;
                            realConnection2 = realConnection4;
                            z3 = true;
                            break;
                        }
                        i6++;
                    }
                }
                if (!z3) {
                    if (route == null) {
                        route = this.f2149b.c();
                    }
                    this.f2150c = route;
                    this.f2156i = 0;
                    realConnection2 = new RealConnection(this.f2151d, route);
                    a(realConnection2, false);
                }
            } else {
                throw new IOException("Canceled");
            }
        }
        if (z3) {
            this.f2153f.g(this.f2152e, realConnection2);
            return realConnection2;
        }
        realConnection2.d(i2, i3, i4, i5, z2, this.f2152e, this.f2153f);
        p().a(realConnection2.p());
        synchronized (this.f2151d) {
            this.f2158k = true;
            Internal.f2079a.i(this.f2151d, realConnection2);
            if (realConnection2.n()) {
                socket = Internal.f2079a.f(this.f2151d, this.f2148a, this);
                realConnection2 = this.f2157j;
            }
        }
        Util.g(socket);
        this.f2153f.g(this.f2152e, realConnection2);
        return realConnection2;
    }

    private RealConnection g(int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        while (true) {
            RealConnection f2 = f(i2, i3, i4, i5, z2);
            synchronized (this.f2151d) {
                if (f2.f2131l == 0 && !f2.n()) {
                    return f2;
                }
                if (!f2.m(z3)) {
                    j();
                } else {
                    return f2;
                }
            }
        }
    }

    private void l(RealConnection realConnection) {
        int size = realConnection.f2133n.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (realConnection.f2133n.get(i2).get() == this) {
                realConnection.f2133n.remove(i2);
                return;
            }
        }
        throw new IllegalStateException();
    }

    private Socket n() {
        RealConnection realConnection = this.f2157j;
        if (realConnection != null && realConnection.f2130k) {
            return e(false, false, true);
        }
        return null;
    }

    private RouteDatabase p() {
        return Internal.f2079a.j(this.f2151d);
    }

    public void a(RealConnection realConnection, boolean z2) {
        if (this.f2157j == null) {
            this.f2157j = realConnection;
            this.f2158k = z2;
            realConnection.f2133n.add(new StreamAllocationReference(this, this.f2154g));
            return;
        }
        throw new IllegalStateException();
    }

    public void b() {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.f2151d) {
            this.f2160m = true;
            httpCodec = this.f2161n;
            realConnection = this.f2157j;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.c();
        }
    }

    public HttpCodec c() {
        HttpCodec httpCodec;
        synchronized (this.f2151d) {
            httpCodec = this.f2161n;
        }
        return httpCodec;
    }

    public synchronized RealConnection d() {
        return this.f2157j;
    }

    public boolean h() {
        RouteSelector.Selection selection;
        if (this.f2150c == null && (((selection = this.f2149b) == null || !selection.b()) && !this.f2155h.c())) {
            return false;
        }
        return true;
    }

    public HttpCodec i(OkHttpClient okHttpClient, Interceptor.Chain chain, boolean z2) {
        try {
            HttpCodec o2 = g(chain.a(), chain.b(), chain.c(), okHttpClient.t(), okHttpClient.z(), z2).o(okHttpClient, chain, this);
            synchronized (this.f2151d) {
                this.f2161n = o2;
            }
            return o2;
        } catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    public void j() {
        RealConnection realConnection;
        Socket e2;
        synchronized (this.f2151d) {
            realConnection = this.f2157j;
            e2 = e(true, false, false);
            if (this.f2157j != null) {
                realConnection = null;
            }
        }
        Util.g(e2);
        if (realConnection != null) {
            this.f2153f.h(this.f2152e, realConnection);
        }
    }

    public void k() {
        RealConnection realConnection;
        Socket e2;
        synchronized (this.f2151d) {
            realConnection = this.f2157j;
            e2 = e(false, true, false);
            if (this.f2157j != null) {
                realConnection = null;
            }
        }
        Util.g(e2);
        if (realConnection != null) {
            Internal.f2079a.k(this.f2152e, null);
            this.f2153f.h(this.f2152e, realConnection);
            this.f2153f.a(this.f2152e);
        }
    }

    public Socket m(RealConnection realConnection) {
        if (this.f2161n == null && this.f2157j.f2133n.size() == 1) {
            Socket e2 = e(true, false, false);
            this.f2157j = realConnection;
            realConnection.f2133n.add(this.f2157j.f2133n.get(0));
            return e2;
        }
        throw new IllegalStateException();
    }

    public Route o() {
        return this.f2150c;
    }

    public void q(IOException iOException) {
        RealConnection realConnection;
        boolean z2;
        Socket e2;
        synchronized (this.f2151d) {
            realConnection = null;
            if (iOException instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException) iOException).f2392d;
                if (errorCode == ErrorCode.REFUSED_STREAM) {
                    int i2 = this.f2156i + 1;
                    this.f2156i = i2;
                    if (i2 > 1) {
                        this.f2150c = null;
                        z2 = true;
                    }
                    z2 = false;
                } else {
                    if (errorCode != ErrorCode.CANCEL) {
                        this.f2150c = null;
                        z2 = true;
                    }
                    z2 = false;
                }
            } else {
                RealConnection realConnection2 = this.f2157j;
                if (realConnection2 != null && (!realConnection2.n() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.f2157j.f2131l == 0) {
                        Route route = this.f2150c;
                        if (route != null && iOException != null) {
                            this.f2155h.a(route, iOException);
                        }
                        this.f2150c = null;
                    }
                    z2 = true;
                }
                z2 = false;
            }
            RealConnection realConnection3 = this.f2157j;
            e2 = e(z2, false, true);
            if (this.f2157j == null && this.f2158k) {
                realConnection = realConnection3;
            }
        }
        Util.g(e2);
        if (realConnection != null) {
            this.f2153f.h(this.f2152e, realConnection);
        }
    }

    public void r(boolean z2, HttpCodec httpCodec, long j2, IOException iOException) {
        RealConnection realConnection;
        Socket e2;
        boolean z3;
        this.f2153f.p(this.f2152e, j2);
        synchronized (this.f2151d) {
            if (httpCodec != null) {
                if (httpCodec == this.f2161n) {
                    if (!z2) {
                        this.f2157j.f2131l++;
                    }
                    realConnection = this.f2157j;
                    e2 = e(z2, false, true);
                    if (this.f2157j != null) {
                        realConnection = null;
                    }
                    z3 = this.f2159l;
                }
            }
            throw new IllegalStateException("expected " + this.f2161n + " but was " + httpCodec);
        }
        Util.g(e2);
        if (realConnection != null) {
            this.f2153f.h(this.f2152e, realConnection);
        }
        if (iOException != null) {
            this.f2153f.b(this.f2152e, Internal.f2079a.k(this.f2152e, iOException));
        } else if (z3) {
            Internal.f2079a.k(this.f2152e, null);
            this.f2153f.a(this.f2152e);
        }
    }

    public String toString() {
        RealConnection d2 = d();
        if (d2 != null) {
            return d2.toString();
        }
        return this.f2148a.toString();
    }
}
