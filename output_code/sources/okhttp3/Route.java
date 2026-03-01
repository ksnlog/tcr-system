package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Route {

    /* renamed from: a  reason: collision with root package name */
    final Address f2069a;

    /* renamed from: b  reason: collision with root package name */
    final Proxy f2070b;

    /* renamed from: c  reason: collision with root package name */
    final InetSocketAddress f2071c;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (address != null) {
            if (proxy != null) {
                if (inetSocketAddress != null) {
                    this.f2069a = address;
                    this.f2070b = proxy;
                    this.f2071c = inetSocketAddress;
                    return;
                }
                throw new NullPointerException("inetSocketAddress == null");
            }
            throw new NullPointerException("proxy == null");
        }
        throw new NullPointerException("address == null");
    }

    public Address a() {
        return this.f2069a;
    }

    public Proxy b() {
        return this.f2070b;
    }

    public boolean c() {
        return this.f2069a.f1814i != null && this.f2070b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f2071c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Route) {
            Route route = (Route) obj;
            if (route.f2069a.equals(this.f2069a) && route.f2070b.equals(this.f2070b) && route.f2071c.equals(this.f2071c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f2069a.hashCode()) * 31) + this.f2070b.hashCode()) * 31) + this.f2071c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f2071c + "}";
    }
}
