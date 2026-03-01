package okhttp3.internal.platform;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AndroidPlatform extends Platform {

    /* renamed from: c  reason: collision with root package name */
    private final Class<?> f2393c;

    /* renamed from: d  reason: collision with root package name */
    private final OptionalMethod<Socket> f2394d;

    /* renamed from: e  reason: collision with root package name */
    private final OptionalMethod<Socket> f2395e;

    /* renamed from: f  reason: collision with root package name */
    private final OptionalMethod<Socket> f2396f;

    /* renamed from: g  reason: collision with root package name */
    private final OptionalMethod<Socket> f2397g;

    /* renamed from: h  reason: collision with root package name */
    private final CloseGuard f2398h = CloseGuard.b();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class AndroidCertificateChainCleaner extends CertificateChainCleaner {

        /* renamed from: a  reason: collision with root package name */
        private final Object f2399a;

        /* renamed from: b  reason: collision with root package name */
        private final Method f2400b;

        AndroidCertificateChainCleaner(Object obj, Method method) {
            this.f2399a = obj;
            this.f2400b = method;
        }

        @Override // okhttp3.internal.tls.CertificateChainCleaner
        public List<Certificate> a(List<Certificate> list, String str) {
            try {
                return (List) this.f2400b.invoke(this.f2399a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof AndroidCertificateChainCleaner;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class AndroidTrustRootIndex implements TrustRootIndex {

        /* renamed from: a  reason: collision with root package name */
        private final X509TrustManager f2401a;

        /* renamed from: b  reason: collision with root package name */
        private final Method f2402b;

        AndroidTrustRootIndex(X509TrustManager x509TrustManager, Method method) {
            this.f2402b = method;
            this.f2401a = x509TrustManager;
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f2402b.invoke(this.f2401a, x509Certificate);
                if (trustAnchor == null) {
                    return null;
                }
                return trustAnchor.getTrustedCert();
            } catch (IllegalAccessException e2) {
                throw Util.b("unable to get issues and signature", e2);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AndroidTrustRootIndex)) {
                return false;
            }
            AndroidTrustRootIndex androidTrustRootIndex = (AndroidTrustRootIndex) obj;
            if (this.f2401a.equals(androidTrustRootIndex.f2401a) && this.f2402b.equals(androidTrustRootIndex.f2402b)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f2401a.hashCode() + (this.f2402b.hashCode() * 31);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class CloseGuard {

        /* renamed from: a  reason: collision with root package name */
        private final Method f2403a;

        /* renamed from: b  reason: collision with root package name */
        private final Method f2404b;

        /* renamed from: c  reason: collision with root package name */
        private final Method f2405c;

        CloseGuard(Method method, Method method2, Method method3) {
            this.f2403a = method;
            this.f2404b = method2;
            this.f2405c = method3;
        }

        static CloseGuard b() {
            Method method;
            Method method2;
            Method method3;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                method = cls.getMethod("get", new Class[0]);
                method3 = cls.getMethod("open", String.class);
                method2 = cls.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception unused) {
                method = null;
                method2 = null;
                method3 = null;
            }
            return new CloseGuard(method, method3, method2);
        }

        Object a(String str) {
            Method method = this.f2403a;
            if (method != null) {
                try {
                    Object invoke = method.invoke(null, new Object[0]);
                    this.f2404b.invoke(invoke, str);
                    return invoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        boolean c(Object obj) {
            if (obj != null) {
                try {
                    this.f2405c.invoke(obj, new Object[0]);
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidPlatform(Class<?> cls, OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4) {
        this.f2393c = cls;
        this.f2394d = optionalMethod;
        this.f2395e = optionalMethod2;
        this.f2396f = optionalMethod3;
        this.f2397g = optionalMethod4;
    }

    private boolean u(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.q(str);
        }
    }

    private boolean v(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return u(str, cls, obj);
        }
    }

    public static Platform w() {
        Class<?> cls;
        OptionalMethod optionalMethod;
        OptionalMethod optionalMethod2;
        if (!Platform.p()) {
            return null;
        }
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            OptionalMethod optionalMethod3 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod optionalMethod4 = new OptionalMethod(null, "setHostname", String.class);
            if (y()) {
                OptionalMethod optionalMethod5 = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                optionalMethod2 = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
                optionalMethod = optionalMethod5;
            } else {
                optionalMethod = null;
                optionalMethod2 = null;
            }
            return new AndroidPlatform(cls2, optionalMethod3, optionalMethod4, optionalMethod, optionalMethod2);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (NoClassDefFoundError unused) {
            return 0;
        }
    }

    private static boolean y() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public CertificateChainCleaner c(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new AndroidCertificateChainCleaner(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public TrustRootIndex d(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new AndroidTrustRootIndex(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.d(x509TrustManager);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void g(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f2394d.e(sSLSocket, Boolean.TRUE);
            this.f2395e.e(sSLSocket, str);
        }
        OptionalMethod<Socket> optionalMethod = this.f2397g;
        if (optionalMethod != null && optionalMethod.g(sSLSocket)) {
            this.f2397g.f(sSLSocket, Platform.e(list));
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i2) {
        try {
            socket.connect(inetSocketAddress, i2);
        } catch (AssertionError e2) {
            if (Util.z(e2)) {
                throw new IOException(e2);
            }
            throw e2;
        } catch (ClassCastException e3) {
            if (Build.VERSION.SDK_INT == 26) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e3);
                throw iOException;
            }
            throw e3;
        } catch (SecurityException e4) {
            IOException iOException2 = new IOException("Exception in connect");
            iOException2.initCause(e4);
            throw iOException2;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public SSLContext m() {
        boolean z2 = true;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 16 || i2 >= 22) {
                z2 = false;
            }
        } catch (NoClassDefFoundError unused) {
        }
        if (z2) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused2) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    @Nullable
    public String n(SSLSocket sSLSocket) {
        byte[] bArr;
        OptionalMethod<Socket> optionalMethod = this.f2396f;
        if (optionalMethod == null || !optionalMethod.g(sSLSocket) || (bArr = (byte[]) this.f2396f.f(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, Util.f2090j);
    }

    @Override // okhttp3.internal.platform.Platform
    public Object o(String str) {
        return this.f2398h.a(str);
    }

    @Override // okhttp3.internal.platform.Platform
    public boolean q(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return super.q(str);
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return v(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.q(str);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw Util.b("unable to determine cleartext support", e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw Util.b("unable to determine cleartext support", e);
        } catch (InvocationTargetException e4) {
            e = e4;
            throw Util.b("unable to determine cleartext support", e);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void s(int i2, String str, @Nullable Throwable th) {
        int min;
        int i3 = 5;
        if (i2 != 5) {
            i3 = 3;
        }
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int length = str.length();
        int i4 = 0;
        while (i4 < length) {
            int indexOf = str.indexOf(10, i4);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i4 + 4000);
                Log.println(i3, "OkHttp", str.substring(i4, min));
                if (min >= indexOf) {
                    break;
                }
                i4 = min;
            }
            i4 = min + 1;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void t(String str, Object obj) {
        if (!this.f2398h.c(obj)) {
            s(5, str, null);
        }
    }
}
