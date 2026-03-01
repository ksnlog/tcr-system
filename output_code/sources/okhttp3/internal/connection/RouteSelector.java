package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RouteSelector {

    /* renamed from: a  reason: collision with root package name */
    private final Address f2138a;

    /* renamed from: b  reason: collision with root package name */
    private final RouteDatabase f2139b;

    /* renamed from: c  reason: collision with root package name */
    private final Call f2140c;

    /* renamed from: d  reason: collision with root package name */
    private final EventListener f2141d;

    /* renamed from: f  reason: collision with root package name */
    private int f2143f;

    /* renamed from: e  reason: collision with root package name */
    private List<Proxy> f2142e = Collections.emptyList();

    /* renamed from: g  reason: collision with root package name */
    private List<InetSocketAddress> f2144g = Collections.emptyList();

    /* renamed from: h  reason: collision with root package name */
    private final List<Route> f2145h = new ArrayList();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Selection {

        /* renamed from: a  reason: collision with root package name */
        private final List<Route> f2146a;

        /* renamed from: b  reason: collision with root package name */
        private int f2147b = 0;

        Selection(List<Route> list) {
            this.f2146a = list;
        }

        public List<Route> a() {
            return new ArrayList(this.f2146a);
        }

        public boolean b() {
            return this.f2147b < this.f2146a.size();
        }

        public Route c() {
            if (b()) {
                List<Route> list = this.f2146a;
                int i2 = this.f2147b;
                this.f2147b = i2 + 1;
                return list.get(i2);
            }
            throw new NoSuchElementException();
        }
    }

    public RouteSelector(Address address, RouteDatabase routeDatabase, Call call, EventListener eventListener) {
        this.f2138a = address;
        this.f2139b = routeDatabase;
        this.f2140c = call;
        this.f2141d = eventListener;
        h(address.l(), address.g());
    }

    static String b(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    private boolean d() {
        return this.f2143f < this.f2142e.size();
    }

    private Proxy f() {
        if (d()) {
            List<Proxy> list = this.f2142e;
            int i2 = this.f2143f;
            this.f2143f = i2 + 1;
            Proxy proxy = list.get(i2);
            g(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f2138a.l().l() + "; exhausted proxy configurations: " + this.f2142e);
    }

    private void g(Proxy proxy) {
        String l2;
        int w2;
        this.f2144g = new ArrayList();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                l2 = b(inetSocketAddress);
                w2 = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        } else {
            l2 = this.f2138a.l().l();
            w2 = this.f2138a.l().w();
        }
        if (w2 >= 1 && w2 <= 65535) {
            if (proxy.type() == Proxy.Type.SOCKS) {
                this.f2144g.add(InetSocketAddress.createUnresolved(l2, w2));
                return;
            }
            this.f2141d.j(this.f2140c, l2);
            List<InetAddress> a2 = this.f2138a.c().a(l2);
            if (!a2.isEmpty()) {
                this.f2141d.i(this.f2140c, l2, a2);
                int size = a2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f2144g.add(new InetSocketAddress(a2.get(i2), w2));
                }
                return;
            }
            throw new UnknownHostException(this.f2138a.c() + " returned no addresses for " + l2);
        }
        throw new SocketException("No route to " + l2 + ":" + w2 + "; port is out of range");
    }

    private void h(HttpUrl httpUrl, Proxy proxy) {
        List<Proxy> t2;
        if (proxy != null) {
            this.f2142e = Collections.singletonList(proxy);
        } else {
            List<Proxy> select = this.f2138a.i().select(httpUrl.B());
            if (select != null && !select.isEmpty()) {
                t2 = Util.s(select);
            } else {
                t2 = Util.t(Proxy.NO_PROXY);
            }
            this.f2142e = t2;
        }
        this.f2143f = 0;
    }

    public void a(Route route, IOException iOException) {
        if (route.b().type() != Proxy.Type.DIRECT && this.f2138a.i() != null) {
            this.f2138a.i().connectFailed(this.f2138a.l().B(), route.b().address(), iOException);
        }
        this.f2139b.b(route);
    }

    public boolean c() {
        return d() || !this.f2145h.isEmpty();
    }

    public Selection e() {
        if (c()) {
            ArrayList arrayList = new ArrayList();
            while (d()) {
                Proxy f2 = f();
                int size = this.f2144g.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Route route = new Route(this.f2138a, f2, this.f2144g.get(i2));
                    if (this.f2139b.c(route)) {
                        this.f2145h.add(route);
                    } else {
                        arrayList.add(route);
                    }
                }
                if (!arrayList.isEmpty()) {
                    break;
                }
            }
            if (arrayList.isEmpty()) {
                arrayList.addAll(this.f2145h);
                this.f2145h.clear();
            }
            return new Selection(arrayList);
        }
        throw new NoSuchElementException();
    }
}
