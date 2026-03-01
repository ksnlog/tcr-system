package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConnectionSpec {

    /* renamed from: e  reason: collision with root package name */
    private static final CipherSuite[] f1892e;

    /* renamed from: f  reason: collision with root package name */
    private static final CipherSuite[] f1893f;

    /* renamed from: g  reason: collision with root package name */
    public static final ConnectionSpec f1894g;

    /* renamed from: h  reason: collision with root package name */
    public static final ConnectionSpec f1895h;

    /* renamed from: i  reason: collision with root package name */
    public static final ConnectionSpec f1896i;

    /* renamed from: j  reason: collision with root package name */
    public static final ConnectionSpec f1897j;

    /* renamed from: a  reason: collision with root package name */
    final boolean f1898a;

    /* renamed from: b  reason: collision with root package name */
    final boolean f1899b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    final String[] f1900c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    final String[] f1901d;

    static {
        CipherSuite cipherSuite = CipherSuite.n1;
        CipherSuite cipherSuite2 = CipherSuite.o1;
        CipherSuite cipherSuite3 = CipherSuite.p1;
        CipherSuite cipherSuite4 = CipherSuite.q1;
        CipherSuite cipherSuite5 = CipherSuite.r1;
        CipherSuite cipherSuite6 = CipherSuite.Z0;
        CipherSuite cipherSuite7 = CipherSuite.d1;
        CipherSuite cipherSuite8 = CipherSuite.a1;
        CipherSuite cipherSuite9 = CipherSuite.e1;
        CipherSuite cipherSuite10 = CipherSuite.k1;
        CipherSuite cipherSuite11 = CipherSuite.j1;
        CipherSuite[] cipherSuiteArr = {cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9, cipherSuite10, cipherSuite11};
        f1892e = cipherSuiteArr;
        CipherSuite[] cipherSuiteArr2 = {cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9, cipherSuite10, cipherSuite11, CipherSuite.K0, CipherSuite.L0, CipherSuite.f1865i0, CipherSuite.j0, CipherSuite.G, CipherSuite.K, CipherSuite.f1867k};
        f1893f = cipherSuiteArr2;
        Builder c2 = new Builder(true).c(cipherSuiteArr);
        TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        f1894g = c2.f(tlsVersion, tlsVersion2).d(true).a();
        Builder c3 = new Builder(true).c(cipherSuiteArr2);
        TlsVersion tlsVersion3 = TlsVersion.TLS_1_0;
        f1895h = c3.f(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, tlsVersion3).d(true).a();
        f1896i = new Builder(true).c(cipherSuiteArr2).f(tlsVersion3).d(true).a();
        f1897j = new Builder(false).a();
    }

    ConnectionSpec(Builder builder) {
        this.f1898a = builder.f1902a;
        this.f1900c = builder.f1903b;
        this.f1901d = builder.f1904c;
        this.f1899b = builder.f1905d;
    }

    private ConnectionSpec e(SSLSocket sSLSocket, boolean z2) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        if (this.f1900c != null) {
            enabledCipherSuites = Util.y(CipherSuite.f1850b, sSLSocket.getEnabledCipherSuites(), this.f1900c);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        if (this.f1901d != null) {
            enabledProtocols = Util.y(Util.f2097q, sSLSocket.getEnabledProtocols(), this.f1901d);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int v2 = Util.v(CipherSuite.f1850b, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z2 && v2 != -1) {
            enabledCipherSuites = Util.h(enabledCipherSuites, supportedCipherSuites[v2]);
        }
        return new Builder(this).b(enabledCipherSuites).e(enabledProtocols).a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(SSLSocket sSLSocket, boolean z2) {
        ConnectionSpec e2 = e(sSLSocket, z2);
        String[] strArr = e2.f1901d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = e2.f1900c;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    @Nullable
    public List<CipherSuite> b() {
        String[] strArr = this.f1900c;
        if (strArr != null) {
            return CipherSuite.b(strArr);
        }
        return null;
    }

    public boolean c(SSLSocket sSLSocket) {
        if (!this.f1898a) {
            return false;
        }
        String[] strArr = this.f1901d;
        if (strArr != null && !Util.A(Util.f2097q, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f1900c;
        if (strArr2 != null && !Util.A(CipherSuite.f1850b, strArr2, sSLSocket.getEnabledCipherSuites())) {
            return false;
        }
        return true;
    }

    public boolean d() {
        return this.f1898a;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec connectionSpec = (ConnectionSpec) obj;
        boolean z2 = this.f1898a;
        if (z2 != connectionSpec.f1898a) {
            return false;
        }
        if (z2 && (!Arrays.equals(this.f1900c, connectionSpec.f1900c) || !Arrays.equals(this.f1901d, connectionSpec.f1901d) || this.f1899b != connectionSpec.f1899b)) {
            return false;
        }
        return true;
    }

    public boolean f() {
        return this.f1899b;
    }

    @Nullable
    public List<TlsVersion> g() {
        String[] strArr = this.f1901d;
        if (strArr != null) {
            return TlsVersion.b(strArr);
        }
        return null;
    }

    public int hashCode() {
        if (this.f1898a) {
            return ((((527 + Arrays.hashCode(this.f1900c)) * 31) + Arrays.hashCode(this.f1901d)) * 31) + (!this.f1899b ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        String str;
        if (!this.f1898a) {
            return "ConnectionSpec()";
        }
        String str2 = "[all enabled]";
        if (this.f1900c != null) {
            str = b().toString();
        } else {
            str = str2;
        }
        if (this.f1901d != null) {
            str2 = g().toString();
        }
        return "ConnectionSpec(cipherSuites=" + str + ", tlsVersions=" + str2 + ", supportsTlsExtensions=" + this.f1899b + ")";
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        boolean f1902a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        String[] f1903b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        String[] f1904c;

        /* renamed from: d  reason: collision with root package name */
        boolean f1905d;

        Builder(boolean z2) {
            this.f1902a = z2;
        }

        public ConnectionSpec a() {
            return new ConnectionSpec(this);
        }

        public Builder b(String... strArr) {
            if (this.f1902a) {
                if (strArr.length != 0) {
                    this.f1903b = (String[]) strArr.clone();
                    return this;
                }
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder c(CipherSuite... cipherSuiteArr) {
            if (this.f1902a) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i2 = 0; i2 < cipherSuiteArr.length; i2++) {
                    strArr[i2] = cipherSuiteArr[i2].f1883a;
                }
                return b(strArr);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder d(boolean z2) {
            if (this.f1902a) {
                this.f1905d = z2;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public Builder e(String... strArr) {
            if (this.f1902a) {
                if (strArr.length != 0) {
                    this.f1904c = (String[]) strArr.clone();
                    return this;
                }
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder f(TlsVersion... tlsVersionArr) {
            if (this.f1902a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i2 = 0; i2 < tlsVersionArr.length; i2++) {
                    strArr[i2] = tlsVersionArr[i2].f2078d;
                }
                return e(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.f1902a = connectionSpec.f1898a;
            this.f1903b = connectionSpec.f1900c;
            this.f1904c = connectionSpec.f1901d;
            this.f1905d = connectionSpec.f1899b;
        }
    }
}
