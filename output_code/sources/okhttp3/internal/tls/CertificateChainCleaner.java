package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.platform.Platform;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class CertificateChainCleaner {
    public static CertificateChainCleaner b(X509TrustManager x509TrustManager) {
        return Platform.l().c(x509TrustManager);
    }

    public abstract List<Certificate> a(List<Certificate> list, String str);
}
