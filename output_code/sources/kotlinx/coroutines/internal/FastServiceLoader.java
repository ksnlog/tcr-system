package kotlinx.coroutines.internal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FastServiceLoader {

    /* renamed from: a  reason: collision with root package name */
    public static final FastServiceLoader f1112a = new FastServiceLoader();

    private FastServiceLoader() {
    }

    private final <S> S a(String str, ClassLoader classLoader, Class<S> cls) {
        Class<?> cls2 = Class.forName(str, false, classLoader);
        if (cls.isAssignableFrom(cls2)) {
            return cls.cast(cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        throw new IllegalArgumentException(("Expected service of class " + cls + ", but found " + cls2).toString());
    }

    private final <S> List<S> b(Class<S> cls, ClassLoader classLoader) {
        List<S> H;
        try {
            return d(cls, classLoader);
        } catch (Throwable unused) {
            H = CollectionsKt___CollectionsKt.H(ServiceLoader.load(cls, classLoader));
            return H;
        }
    }

    private final List<String> e(URL url) {
        boolean n2;
        String K;
        String P;
        String K2;
        String url2 = url.toString();
        n2 = StringsKt__StringsJVMKt.n(url2, "jar", false, 2, null);
        if (n2) {
            K = StringsKt__StringsKt.K(url2, "jar:file:", null, 2, null);
            P = StringsKt__StringsKt.P(K, '!', null, 2, null);
            K2 = StringsKt__StringsKt.K(url2, "!/", null, 2, null);
            JarFile jarFile = new JarFile(P, false);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(new ZipEntry(K2)), "UTF-8"));
                List<String> f2 = f1112a.f(bufferedReader);
                CloseableKt.a(bufferedReader, null);
                jarFile.close();
                return f2;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        jarFile.close();
                        throw th2;
                    } catch (Throwable th3) {
                        ExceptionsKt__ExceptionsKt.a(th, th3);
                        throw th;
                    }
                }
            }
        }
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url.openStream()));
        try {
            List<String> f3 = f1112a.f(bufferedReader2);
            CloseableKt.a(bufferedReader2, null);
            return f3;
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                CloseableKt.a(bufferedReader2, th4);
                throw th5;
            }
        }
    }

    private final List<String> f(BufferedReader bufferedReader) {
        List<String> H;
        String Q;
        CharSequence R;
        boolean z2;
        boolean z3;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                H = CollectionsKt___CollectionsKt.H(linkedHashSet);
                return H;
            }
            Q = StringsKt__StringsKt.Q(readLine, "#", null, 2, null);
            R = StringsKt__StringsKt.R(Q);
            String obj = R.toString();
            boolean z4 = false;
            int i2 = 0;
            while (true) {
                if (i2 < obj.length()) {
                    char charAt = obj.charAt(i2);
                    if (charAt != '.' && !Character.isJavaIdentifierPart(charAt)) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (!z3) {
                        z2 = false;
                        break;
                    }
                    i2++;
                } else {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                if (obj.length() > 0) {
                    z4 = true;
                }
                if (z4) {
                    linkedHashSet.add(obj);
                }
            } else {
                throw new IllegalArgumentException(("Illegal service provider class name: " + obj).toString());
            }
        }
    }

    public final List<MainDispatcherFactory> c() {
        MainDispatcherFactory mainDispatcherFactory;
        if (!FastServiceLoaderKt.a()) {
            return b(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
        try {
            ArrayList arrayList = new ArrayList(2);
            MainDispatcherFactory mainDispatcherFactory2 = null;
            try {
                mainDispatcherFactory = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused) {
                mainDispatcherFactory = null;
            }
            if (mainDispatcherFactory != null) {
                arrayList.add(mainDispatcherFactory);
            }
            try {
                mainDispatcherFactory2 = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused2) {
            }
            if (mainDispatcherFactory2 != null) {
                arrayList.add(mainDispatcherFactory2);
                return arrayList;
            }
            return arrayList;
        } catch (Throwable unused3) {
            return b(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
    }

    public final <S> List<S> d(Class<S> cls, ClassLoader classLoader) {
        Set<String> L;
        int m2;
        ArrayList<URL> list = Collections.list(classLoader.getResources("META-INF/services/" + cls.getName()));
        Intrinsics.e(list, "list(this)");
        ArrayList arrayList = new ArrayList();
        for (URL url : list) {
            CollectionsKt__MutableCollectionsKt.o(arrayList, f1112a.e(url));
        }
        L = CollectionsKt___CollectionsKt.L(arrayList);
        if (!L.isEmpty()) {
            m2 = CollectionsKt__IterablesKt.m(L, 10);
            ArrayList arrayList2 = new ArrayList(m2);
            for (String str : L) {
                arrayList2.add(f1112a.a(str, classLoader, cls));
            }
            return arrayList2;
        }
        throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
    }
}
