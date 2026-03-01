package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CipherSuite {

    /* renamed from: a  reason: collision with root package name */
    final String f1883a;

    /* renamed from: b  reason: collision with root package name */
    static final Comparator<String> f1850b = new Comparator<String>() { // from class: okhttp3.CipherSuite.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(String str, String str2) {
            int min = Math.min(str.length(), str2.length());
            for (int i2 = 4; i2 < min; i2++) {
                char charAt = str.charAt(i2);
                char charAt2 = str2.charAt(i2);
                if (charAt != charAt2) {
                    if (charAt < charAt2) {
                        return -1;
                    }
                    return 1;
                }
            }
            int length = str.length();
            int length2 = str2.length();
            if (length != length2) {
                if (length < length2) {
                    return -1;
                }
                return 1;
            }
            return 0;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static final Map<String, CipherSuite> f1852c = new LinkedHashMap();

    /* renamed from: d  reason: collision with root package name */
    public static final CipherSuite f1854d = c("SSL_RSA_WITH_NULL_MD5", 1);

    /* renamed from: e  reason: collision with root package name */
    public static final CipherSuite f1856e = c("SSL_RSA_WITH_NULL_SHA", 2);

    /* renamed from: f  reason: collision with root package name */
    public static final CipherSuite f1858f = c("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);

    /* renamed from: g  reason: collision with root package name */
    public static final CipherSuite f1860g = c("SSL_RSA_WITH_RC4_128_MD5", 4);

    /* renamed from: h  reason: collision with root package name */
    public static final CipherSuite f1862h = c("SSL_RSA_WITH_RC4_128_SHA", 5);

    /* renamed from: i  reason: collision with root package name */
    public static final CipherSuite f1864i = c("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);

    /* renamed from: j  reason: collision with root package name */
    public static final CipherSuite f1866j = c("SSL_RSA_WITH_DES_CBC_SHA", 9);

    /* renamed from: k  reason: collision with root package name */
    public static final CipherSuite f1867k = c("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);

    /* renamed from: l  reason: collision with root package name */
    public static final CipherSuite f1868l = c("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);

    /* renamed from: m  reason: collision with root package name */
    public static final CipherSuite f1869m = c("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);

    /* renamed from: n  reason: collision with root package name */
    public static final CipherSuite f1870n = c("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);

    /* renamed from: o  reason: collision with root package name */
    public static final CipherSuite f1871o = c("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);

    /* renamed from: p  reason: collision with root package name */
    public static final CipherSuite f1872p = c("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);

    /* renamed from: q  reason: collision with root package name */
    public static final CipherSuite f1873q = c("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);

    /* renamed from: r  reason: collision with root package name */
    public static final CipherSuite f1874r = c("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);

    /* renamed from: s  reason: collision with root package name */
    public static final CipherSuite f1875s = c("SSL_DH_anon_WITH_RC4_128_MD5", 24);

    /* renamed from: t  reason: collision with root package name */
    public static final CipherSuite f1876t = c("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);

    /* renamed from: u  reason: collision with root package name */
    public static final CipherSuite f1877u = c("SSL_DH_anon_WITH_DES_CBC_SHA", 26);

    /* renamed from: v  reason: collision with root package name */
    public static final CipherSuite f1878v = c("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);

    /* renamed from: w  reason: collision with root package name */
    public static final CipherSuite f1879w = c("TLS_KRB5_WITH_DES_CBC_SHA", 30);

    /* renamed from: x  reason: collision with root package name */
    public static final CipherSuite f1880x = c("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);

    /* renamed from: y  reason: collision with root package name */
    public static final CipherSuite f1881y = c("TLS_KRB5_WITH_RC4_128_SHA", 32);

    /* renamed from: z  reason: collision with root package name */
    public static final CipherSuite f1882z = c("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final CipherSuite A = c("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final CipherSuite B = c("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final CipherSuite C = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final CipherSuite D = c("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final CipherSuite E = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final CipherSuite F = c("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final CipherSuite G = c("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final CipherSuite H = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final CipherSuite I = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final CipherSuite J = c("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final CipherSuite K = c("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final CipherSuite L = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final CipherSuite M = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final CipherSuite N = c("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final CipherSuite O = c("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final CipherSuite P = c("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final CipherSuite Q = c("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final CipherSuite R = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final CipherSuite S = c("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final CipherSuite T = c("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final CipherSuite U = c("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final CipherSuite V = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final CipherSuite W = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final CipherSuite X = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final CipherSuite Y = c("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final CipherSuite Z = c("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);

    /* renamed from: a0  reason: collision with root package name */
    public static final CipherSuite f1849a0 = c("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);

    /* renamed from: b0  reason: collision with root package name */
    public static final CipherSuite f1851b0 = c("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);

    /* renamed from: c0  reason: collision with root package name */
    public static final CipherSuite f1853c0 = c("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);

    /* renamed from: d0  reason: collision with root package name */
    public static final CipherSuite f1855d0 = c("TLS_PSK_WITH_RC4_128_SHA", 138);

    /* renamed from: e0  reason: collision with root package name */
    public static final CipherSuite f1857e0 = c("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);

    /* renamed from: f0  reason: collision with root package name */
    public static final CipherSuite f1859f0 = c("TLS_PSK_WITH_AES_128_CBC_SHA", 140);

    /* renamed from: g0  reason: collision with root package name */
    public static final CipherSuite f1861g0 = c("TLS_PSK_WITH_AES_256_CBC_SHA", 141);

    /* renamed from: h0  reason: collision with root package name */
    public static final CipherSuite f1863h0 = c("TLS_RSA_WITH_SEED_CBC_SHA", 150);

    /* renamed from: i0  reason: collision with root package name */
    public static final CipherSuite f1865i0 = c("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    public static final CipherSuite j0 = c("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    public static final CipherSuite k0 = c("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final CipherSuite l0 = c("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    public static final CipherSuite m0 = c("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
    public static final CipherSuite n0 = c("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
    public static final CipherSuite o0 = c("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
    public static final CipherSuite p0 = c("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
    public static final CipherSuite q0 = c("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final CipherSuite r0 = c("TLS_FALLBACK_SCSV", 22016);
    public static final CipherSuite s0 = c("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    public static final CipherSuite t0 = c("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    public static final CipherSuite u0 = c("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    public static final CipherSuite v0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    public static final CipherSuite w0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    public static final CipherSuite x0 = c("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    public static final CipherSuite y0 = c("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    public static final CipherSuite z0 = c("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    public static final CipherSuite A0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    public static final CipherSuite B0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    public static final CipherSuite C0 = c("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    public static final CipherSuite D0 = c("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    public static final CipherSuite E0 = c("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    public static final CipherSuite F0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    public static final CipherSuite G0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    public static final CipherSuite H0 = c("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    public static final CipherSuite I0 = c("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    public static final CipherSuite J0 = c("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    public static final CipherSuite K0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    public static final CipherSuite L0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    public static final CipherSuite M0 = c("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    public static final CipherSuite N0 = c("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    public static final CipherSuite O0 = c("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    public static final CipherSuite P0 = c("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    public static final CipherSuite Q0 = c("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    public static final CipherSuite R0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    public static final CipherSuite S0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    public static final CipherSuite T0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    public static final CipherSuite U0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    public static final CipherSuite V0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    public static final CipherSuite W0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    public static final CipherSuite X0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    public static final CipherSuite Y0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    public static final CipherSuite Z0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    public static final CipherSuite a1 = c("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    public static final CipherSuite b1 = c("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    public static final CipherSuite c1 = c("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    public static final CipherSuite d1 = c("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    public static final CipherSuite e1 = c("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    public static final CipherSuite f1 = c("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    public static final CipherSuite g1 = c("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    public static final CipherSuite h1 = c("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    public static final CipherSuite i1 = c("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    public static final CipherSuite j1 = c("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final CipherSuite k1 = c("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    public static final CipherSuite l1 = c("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
    public static final CipherSuite m1 = c("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
    public static final CipherSuite n1 = c("TLS_AES_128_GCM_SHA256", 4865);
    public static final CipherSuite o1 = c("TLS_AES_256_GCM_SHA384", 4866);
    public static final CipherSuite p1 = c("TLS_CHACHA20_POLY1305_SHA256", 4867);
    public static final CipherSuite q1 = c("TLS_AES_128_CCM_SHA256", 4868);
    public static final CipherSuite r1 = c("TLS_AES_256_CCM_8_SHA256", 4869);

    private CipherSuite(String str) {
        str.getClass();
        this.f1883a = str;
    }

    public static synchronized CipherSuite a(String str) {
        CipherSuite cipherSuite;
        synchronized (CipherSuite.class) {
            Map<String, CipherSuite> map = f1852c;
            cipherSuite = map.get(str);
            if (cipherSuite == null) {
                cipherSuite = map.get(d(str));
                if (cipherSuite == null) {
                    cipherSuite = new CipherSuite(str);
                }
                map.put(str, cipherSuite);
            }
        }
        return cipherSuite;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<CipherSuite> b(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static CipherSuite c(String str, int i2) {
        CipherSuite cipherSuite = new CipherSuite(str);
        f1852c.put(str, cipherSuite);
        return cipherSuite;
    }

    private static String d(String str) {
        if (str.startsWith("TLS_")) {
            return "SSL_" + str.substring(4);
        } else if (str.startsWith("SSL_")) {
            return "TLS_" + str.substring(4);
        } else {
            return str;
        }
    }

    public String toString() {
        return this.f1883a;
    }
}
