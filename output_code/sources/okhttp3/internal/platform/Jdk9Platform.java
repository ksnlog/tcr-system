package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Jdk9Platform extends Platform {

    /* renamed from: c  reason: collision with root package name */
    final Method f2406c;

    /* renamed from: d  reason: collision with root package name */
    final Method f2407d;

    Jdk9Platform(Method method, Method method2) {
        this.f2406c = method;
        this.f2407d = method2;
    }

    public static Jdk9Platform u() {
        try {
            return new Jdk9Platform(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void g(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> b2 = Platform.b(list);
            this.f2406c.invoke(sSLParameters, b2.toArray(new String[b2.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw Util.b("unable to set ssl parameters", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    @Nullable
    public String n(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f2407d.invoke(sSLSocket, new Object[0]);
            if (str != null) {
                if (!str.equals("")) {
                    return str;
                }
            }
            return null;
        } catch (IllegalAccessException e2) {
            throw Util.b("failed to get ALPN selected protocol", e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof UnsupportedOperationException) {
                return null;
            }
            throw Util.b("failed to get ALPN selected protocol", e3);
        }
    }
}
