package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class CallableReference implements KCallable, Serializable {

    /* renamed from: j  reason: collision with root package name */
    public static final Object f863j = NoReceiver.f870d;

    /* renamed from: d  reason: collision with root package name */
    private transient KCallable f864d;

    /* renamed from: e  reason: collision with root package name */
    protected final Object f865e;

    /* renamed from: f  reason: collision with root package name */
    private final Class f866f;

    /* renamed from: g  reason: collision with root package name */
    private final String f867g;

    /* renamed from: h  reason: collision with root package name */
    private final String f868h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f869i;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class NoReceiver implements Serializable {

        /* renamed from: d  reason: collision with root package name */
        private static final NoReceiver f870d = new NoReceiver();

        private NoReceiver() {
        }
    }

    public CallableReference() {
        this(f863j);
    }

    public KCallable a() {
        KCallable kCallable = this.f864d;
        if (kCallable == null) {
            KCallable c2 = c();
            this.f864d = c2;
            return c2;
        }
        return kCallable;
    }

    protected abstract KCallable c();

    public Object d() {
        return this.f865e;
    }

    public KDeclarationContainer g() {
        Class cls = this.f866f;
        if (cls == null) {
            return null;
        }
        if (this.f869i) {
            return Reflection.c(cls);
        }
        return Reflection.b(cls);
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.f867g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KCallable h() {
        KCallable a2 = a();
        if (a2 != this) {
            return a2;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public String i() {
        return this.f868h;
    }

    protected CallableReference(Object obj) {
        this(obj, null, null, null, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CallableReference(Object obj, Class cls, String str, String str2, boolean z2) {
        this.f865e = obj;
        this.f866f = cls;
        this.f867g = str;
        this.f868h = str2;
        this.f869i = z2;
    }
}
