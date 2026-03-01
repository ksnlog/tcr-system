package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty0;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Reflection {

    /* renamed from: a  reason: collision with root package name */
    private static final ReflectionFactory f885a;

    /* renamed from: b  reason: collision with root package name */
    private static final KClass[] f886b;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        f885a = reflectionFactory;
        f886b = new KClass[0];
    }

    public static KFunction a(FunctionReference functionReference) {
        return f885a.a(functionReference);
    }

    public static KClass b(Class cls) {
        return f885a.b(cls);
    }

    public static KDeclarationContainer c(Class cls) {
        return f885a.c(cls, "");
    }

    public static KMutableProperty1 d(MutablePropertyReference1 mutablePropertyReference1) {
        return f885a.d(mutablePropertyReference1);
    }

    public static KProperty0 e(PropertyReference0 propertyReference0) {
        return f885a.e(propertyReference0);
    }

    public static String f(FunctionBase functionBase) {
        return f885a.f(functionBase);
    }

    public static String g(Lambda lambda) {
        return f885a.g(lambda);
    }
}
