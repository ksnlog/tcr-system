package okhttp3;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Handshake {

    /* renamed from: a  reason: collision with root package name */
    private final TlsVersion f1930a;

    /* renamed from: b  reason: collision with root package name */
    private final CipherSuite f1931b;

    /* renamed from: c  reason: collision with root package name */
    private final List<Certificate> f1932c;

    /* renamed from: d  reason: collision with root package name */
    private final List<Certificate> f1933d;

    private Handshake(TlsVersion tlsVersion, CipherSuite cipherSuite, List<Certificate> list, List<Certificate> list2) {
        this.f1930a = tlsVersion;
        this.f1931b = cipherSuite;
        this.f1932c = list;
        this.f1933d = list2;
    }

    public static Handshake b(SSLSession sSLSession) {
        Certificate[] certificateArr;
        List emptyList;
        List emptyList2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite != null) {
            if (!"SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
                CipherSuite a2 = CipherSuite.a(cipherSuite);
                String protocol = sSLSession.getProtocol();
                if (protocol != null) {
                    if (!"NONE".equals(protocol)) {
                        TlsVersion a3 = TlsVersion.a(protocol);
                        try {
                            certificateArr = sSLSession.getPeerCertificates();
                        } catch (SSLPeerUnverifiedException unused) {
                            certificateArr = null;
                        }
                        if (certificateArr != null) {
                            emptyList = Util.t(certificateArr);
                        } else {
                            emptyList = Collections.emptyList();
                        }
                        Certificate[] localCertificates = sSLSession.getLocalCertificates();
                        if (localCertificates != null) {
                            emptyList2 = Util.t(localCertificates);
                        } else {
                            emptyList2 = Collections.emptyList();
                        }
                        return new Handshake(a3, a2, emptyList, emptyList2);
                    }
                    throw new IOException("tlsVersion == NONE");
                }
                throw new IllegalStateException("tlsVersion == null");
            }
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        throw new IllegalStateException("cipherSuite == null");
    }

    public CipherSuite a() {
        return this.f1931b;
    }

    public List<Certificate> c() {
        return this.f1932c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake handshake = (Handshake) obj;
        if (!this.f1930a.equals(handshake.f1930a) || !this.f1931b.equals(handshake.f1931b) || !this.f1932c.equals(handshake.f1932c) || !this.f1933d.equals(handshake.f1933d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((527 + this.f1930a.hashCode()) * 31) + this.f1931b.hashCode()) * 31) + this.f1932c.hashCode()) * 31) + this.f1933d.hashCode();
    }
}
