package okhttp3.internal.platform;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Platform {

    /* renamed from: a  reason: collision with root package name */
    private static final Platform f2419a = k();

    /* renamed from: b  reason: collision with root package name */
    private static final Logger f2420b = Logger.getLogger(OkHttpClient.class.getName());

    public static List<String> b(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] e(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.H(protocol.toString());
            }
        }
        return buffer.z();
    }

    private static Platform i() {
        Platform w2 = Android10Platform.w();
        if (w2 != null) {
            return w2;
        }
        Platform w3 = AndroidPlatform.w();
        if (w3 != null) {
            return w3;
        }
        throw new NullPointerException("No platform found on Android");
    }

    private static Platform j() {
        ConscryptPlatform u2;
        if (r() && (u2 = ConscryptPlatform.u()) != null) {
            return u2;
        }
        Jdk9Platform u3 = Jdk9Platform.u();
        if (u3 != null) {
            return u3;
        }
        Platform u4 = JdkWithJettyBootPlatform.u();
        if (u4 != null) {
            return u4;
        }
        return new Platform();
    }

    private static Platform k() {
        if (p()) {
            return i();
        }
        return j();
    }

    public static Platform l() {
        return f2419a;
    }

    public static boolean p() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    public static boolean r() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public void a(SSLSocket sSLSocket) {
    }

    public CertificateChainCleaner c(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(d(x509TrustManager));
    }

    public TrustRootIndex d(X509TrustManager x509TrustManager) {
        return new BasicTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }

    public void f(SSLSocketFactory sSLSocketFactory) {
    }

    public void g(SSLSocket sSLSocket, @Nullable String str, List<Protocol> list) {
    }

    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i2) {
        socket.connect(inetSocketAddress, i2);
    }

    public SSLContext m() {
        if ("1.7".equals(System.getProperty("java.specification.version"))) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    @Nullable
    public String n(SSLSocket sSLSocket) {
        return null;
    }

    public Object o(String str) {
        if (f2420b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean q(String str) {
        return true;
    }

    public void s(int i2, String str, @Nullable Throwable th) {
        Level level;
        if (i2 == 5) {
            level = Level.WARNING;
        } else {
            level = Level.INFO;
        }
        f2420b.log(level, str, th);
    }

    public void t(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        s(5, str, (Throwable) obj);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
