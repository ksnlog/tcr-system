package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class JdkWithJettyBootPlatform extends Platform {

    /* renamed from: c  reason: collision with root package name */
    private final Method f2408c;

    /* renamed from: d  reason: collision with root package name */
    private final Method f2409d;

    /* renamed from: e  reason: collision with root package name */
    private final Method f2410e;

    /* renamed from: f  reason: collision with root package name */
    private final Class<?> f2411f;

    /* renamed from: g  reason: collision with root package name */
    private final Class<?> f2412g;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class JettyNegoProvider implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final List<String> f2413a;

        /* renamed from: b  reason: collision with root package name */
        boolean f2414b;

        /* renamed from: c  reason: collision with root package name */
        String f2415c;

        JettyNegoProvider(List<String> list) {
            this.f2413a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.f2082b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.TRUE;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f2414b = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.f2413a;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1) {
                    Object obj2 = objArr[0];
                    if (obj2 instanceof List) {
                        List list = (List) obj2;
                        int size = list.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            if (this.f2413a.contains(list.get(i2))) {
                                String str = (String) list.get(i2);
                                this.f2415c = str;
                                return str;
                            }
                        }
                        String str2 = this.f2413a.get(0);
                        this.f2415c = str2;
                        return str2;
                    }
                }
                if ((name.equals("protocolSelected") || name.equals("selected")) && objArr.length == 1) {
                    this.f2415c = (String) objArr[0];
                    return null;
                }
                return method.invoke(this, objArr);
            }
        }
    }

    JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f2408c = method;
        this.f2409d = method2;
        this.f2410e = method3;
        this.f2411f = cls;
        this.f2412g = cls2;
    }

    public static Platform u() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider");
            return new JdkWithJettyBootPlatform(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), cls3, Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void a(SSLSocket sSLSocket) {
        try {
            this.f2410e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw Util.b("unable to remove alpn", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public void g(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            this.f2408c.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.f2411f, this.f2412g}, new JettyNegoProvider(Platform.b(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw Util.b("unable to set alpn", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    @Nullable
    public String n(SSLSocket sSLSocket) {
        try {
            JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.f2409d.invoke(null, sSLSocket));
            boolean z2 = jettyNegoProvider.f2414b;
            if (!z2 && jettyNegoProvider.f2415c == null) {
                Platform.l().s(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            } else if (z2) {
                return null;
            } else {
                return jettyNegoProvider.f2415c;
            }
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw Util.b("unable to get selected protocol", e2);
        }
    }
}
