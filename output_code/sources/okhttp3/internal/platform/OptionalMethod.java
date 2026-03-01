package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class OptionalMethod<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f2416a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2417b;

    /* renamed from: c  reason: collision with root package name */
    private final Class[] f2418c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OptionalMethod(Class<?> cls, String str, Class... clsArr) {
        this.f2416a = cls;
        this.f2417b = str;
        this.f2418c = clsArr;
    }

    private Method a(Class<?> cls) {
        Class<?> cls2;
        String str = this.f2417b;
        if (str == null) {
            return null;
        }
        Method b2 = b(cls, str, this.f2418c);
        if (b2 != null && (cls2 = this.f2416a) != null && !cls2.isAssignableFrom(b2.getReturnType())) {
            return null;
        }
        return b2;
    }

    private static Method b(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) == 0) {
                    return null;
                }
            } catch (NoSuchMethodException unused) {
            }
            return method;
        } catch (NoSuchMethodException unused2) {
            return null;
        }
    }

    public Object c(T t2, Object... objArr) {
        Method a2 = a(t2.getClass());
        if (a2 != null) {
            try {
                return a2.invoke(t2, objArr);
            } catch (IllegalAccessException e2) {
                AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + a2);
                assertionError.initCause(e2);
                throw assertionError;
            }
        }
        throw new AssertionError("Method " + this.f2417b + " not supported for object " + t2);
    }

    public Object d(T t2, Object... objArr) {
        Method a2 = a(t2.getClass());
        if (a2 == null) {
            return null;
        }
        try {
            return a2.invoke(t2, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public Object e(T t2, Object... objArr) {
        try {
            return d(t2, objArr);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object f(T t2, Object... objArr) {
        try {
            return c(t2, objArr);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public boolean g(T t2) {
        return a(t2.getClass()) != null;
    }
}
