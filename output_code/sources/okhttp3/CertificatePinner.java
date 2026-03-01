package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CertificatePinner {

    /* renamed from: c  reason: collision with root package name */
    public static final CertificatePinner f1841c = new Builder().a();

    /* renamed from: a  reason: collision with root package name */
    private final Set<Pin> f1842a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    private final CertificateChainCleaner f1843b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final List<Pin> f1844a = new ArrayList();

        public CertificatePinner a() {
            return new CertificatePinner(new LinkedHashSet(this.f1844a), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Pin {

        /* renamed from: a  reason: collision with root package name */
        final String f1845a;

        /* renamed from: b  reason: collision with root package name */
        final String f1846b;

        /* renamed from: c  reason: collision with root package name */
        final String f1847c;

        /* renamed from: d  reason: collision with root package name */
        final ByteString f1848d;

        boolean a(String str) {
            if (this.f1845a.startsWith("*.")) {
                int indexOf = str.indexOf(46);
                if ((str.length() - indexOf) - 1 == this.f1846b.length()) {
                    String str2 = this.f1846b;
                    if (str.regionMatches(false, indexOf + 1, str2, 0, str2.length())) {
                        return true;
                    }
                }
                return false;
            }
            return str.equals(this.f1846b);
        }

        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin pin = (Pin) obj;
                if (this.f1845a.equals(pin.f1845a) && this.f1847c.equals(pin.f1847c) && this.f1848d.equals(pin.f1848d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f1845a.hashCode()) * 31) + this.f1847c.hashCode()) * 31) + this.f1848d.hashCode();
        }

        public String toString() {
            return this.f1847c + this.f1848d.a();
        }
    }

    CertificatePinner(Set<Pin> set, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.f1842a = set;
        this.f1843b = certificateChainCleaner;
    }

    public static String c(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + e((X509Certificate) certificate).a();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static ByteString d(X509Certificate x509Certificate) {
        return ByteString.n(x509Certificate.getPublicKey().getEncoded()).s();
    }

    static ByteString e(X509Certificate x509Certificate) {
        return ByteString.n(x509Certificate.getPublicKey().getEncoded()).t();
    }

    public void a(String str, List<Certificate> list) {
        List<Pin> b2 = b(str);
        if (b2.isEmpty()) {
            return;
        }
        CertificateChainCleaner certificateChainCleaner = this.f1843b;
        if (certificateChainCleaner != null) {
            list = certificateChainCleaner.a(list, str);
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i2);
            int size2 = b2.size();
            ByteString byteString = null;
            ByteString byteString2 = null;
            for (int i3 = 0; i3 < size2; i3++) {
                Pin pin = b2.get(i3);
                if (pin.f1847c.equals("sha256/")) {
                    if (byteString == null) {
                        byteString = e(x509Certificate);
                    }
                    if (pin.f1848d.equals(byteString)) {
                        return;
                    }
                } else if (pin.f1847c.equals("sha1/")) {
                    if (byteString2 == null) {
                        byteString2 = d(x509Certificate);
                    }
                    if (pin.f1848d.equals(byteString2)) {
                        return;
                    }
                } else {
                    throw new AssertionError("unsupported hashAlgorithm: " + pin.f1847c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i4 = 0; i4 < size3; i4++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i4);
            sb.append("\n    ");
            sb.append(c(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = b2.size();
        for (int i5 = 0; i5 < size4; i5++) {
            sb.append("\n    ");
            sb.append(b2.get(i5));
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    List<Pin> b(String str) {
        List<Pin> emptyList = Collections.emptyList();
        for (Pin pin : this.f1842a) {
            if (pin.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                emptyList.add(pin);
            }
        }
        return emptyList;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (Util.p(this.f1843b, certificatePinner.f1843b) && this.f1842a.equals(certificatePinner.f1842a)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CertificatePinner f(@Nullable CertificateChainCleaner certificateChainCleaner) {
        if (Util.p(this.f1843b, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.f1842a, certificateChainCleaner);
    }

    public int hashCode() {
        int i2;
        CertificateChainCleaner certificateChainCleaner = this.f1843b;
        if (certificateChainCleaner != null) {
            i2 = certificateChainCleaner.hashCode();
        } else {
            i2 = 0;
        }
        return (i2 * 31) + this.f1842a.hashCode();
    }
}
