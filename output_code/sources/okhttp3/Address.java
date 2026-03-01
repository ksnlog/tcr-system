package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Address {

    /* renamed from: a  reason: collision with root package name */
    final HttpUrl f1806a;

    /* renamed from: b  reason: collision with root package name */
    final Dns f1807b;

    /* renamed from: c  reason: collision with root package name */
    final SocketFactory f1808c;

    /* renamed from: d  reason: collision with root package name */
    final Authenticator f1809d;

    /* renamed from: e  reason: collision with root package name */
    final List<Protocol> f1810e;

    /* renamed from: f  reason: collision with root package name */
    final List<ConnectionSpec> f1811f;

    /* renamed from: g  reason: collision with root package name */
    final ProxySelector f1812g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    final Proxy f1813h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    final SSLSocketFactory f1814i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    final HostnameVerifier f1815j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    final CertificatePinner f1816k;

    public Address(String str, int i2, Dns dns, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, Authenticator authenticator, @Nullable Proxy proxy, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector) {
        this.f1806a = new HttpUrl.Builder().p(sSLSocketFactory != null ? "https" : "http").e(str).k(i2).a();
        if (dns != null) {
            this.f1807b = dns;
            if (socketFactory != null) {
                this.f1808c = socketFactory;
                if (authenticator != null) {
                    this.f1809d = authenticator;
                    if (list != null) {
                        this.f1810e = Util.s(list);
                        if (list2 != null) {
                            this.f1811f = Util.s(list2);
                            if (proxySelector != null) {
                                this.f1812g = proxySelector;
                                this.f1813h = proxy;
                                this.f1814i = sSLSocketFactory;
                                this.f1815j = hostnameVerifier;
                                this.f1816k = certificatePinner;
                                return;
                            }
                            throw new NullPointerException("proxySelector == null");
                        }
                        throw new NullPointerException("connectionSpecs == null");
                    }
                    throw new NullPointerException("protocols == null");
                }
                throw new NullPointerException("proxyAuthenticator == null");
            }
            throw new NullPointerException("socketFactory == null");
        }
        throw new NullPointerException("dns == null");
    }

    @Nullable
    public CertificatePinner a() {
        return this.f1816k;
    }

    public List<ConnectionSpec> b() {
        return this.f1811f;
    }

    public Dns c() {
        return this.f1807b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(Address address) {
        if (this.f1807b.equals(address.f1807b) && this.f1809d.equals(address.f1809d) && this.f1810e.equals(address.f1810e) && this.f1811f.equals(address.f1811f) && this.f1812g.equals(address.f1812g) && Util.p(this.f1813h, address.f1813h) && Util.p(this.f1814i, address.f1814i) && Util.p(this.f1815j, address.f1815j) && Util.p(this.f1816k, address.f1816k) && l().w() == address.l().w()) {
            return true;
        }
        return false;
    }

    @Nullable
    public HostnameVerifier e() {
        return this.f1815j;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Address) {
            Address address = (Address) obj;
            if (this.f1806a.equals(address.f1806a) && d(address)) {
                return true;
            }
        }
        return false;
    }

    public List<Protocol> f() {
        return this.f1810e;
    }

    @Nullable
    public Proxy g() {
        return this.f1813h;
    }

    public Authenticator h() {
        return this.f1809d;
    }

    public int hashCode() {
        int i2;
        int i3;
        int i4;
        int hashCode = (((((((((((527 + this.f1806a.hashCode()) * 31) + this.f1807b.hashCode()) * 31) + this.f1809d.hashCode()) * 31) + this.f1810e.hashCode()) * 31) + this.f1811f.hashCode()) * 31) + this.f1812g.hashCode()) * 31;
        Proxy proxy = this.f1813h;
        int i5 = 0;
        if (proxy != null) {
            i2 = proxy.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (hashCode + i2) * 31;
        SSLSocketFactory sSLSocketFactory = this.f1814i;
        if (sSLSocketFactory != null) {
            i3 = sSLSocketFactory.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        HostnameVerifier hostnameVerifier = this.f1815j;
        if (hostnameVerifier != null) {
            i4 = hostnameVerifier.hashCode();
        } else {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        CertificatePinner certificatePinner = this.f1816k;
        if (certificatePinner != null) {
            i5 = certificatePinner.hashCode();
        }
        return i8 + i5;
    }

    public ProxySelector i() {
        return this.f1812g;
    }

    public SocketFactory j() {
        return this.f1808c;
    }

    @Nullable
    public SSLSocketFactory k() {
        return this.f1814i;
    }

    public HttpUrl l() {
        return this.f1806a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f1806a.l());
        sb.append(":");
        sb.append(this.f1806a.w());
        if (this.f1813h != null) {
            sb.append(", proxy=");
            sb.append(this.f1813h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f1812g);
        }
        sb.append("}");
        return sb.toString();
    }
}
