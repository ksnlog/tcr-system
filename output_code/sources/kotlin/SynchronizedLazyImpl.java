package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SynchronizedLazyImpl<T> implements Lazy<T>, Serializable {

    /* renamed from: d  reason: collision with root package name */
    private Function0<? extends T> f759d;

    /* renamed from: e  reason: collision with root package name */
    private volatile Object f760e;

    /* renamed from: f  reason: collision with root package name */
    private final Object f761f;

    public SynchronizedLazyImpl(Function0<? extends T> initializer, Object obj) {
        Intrinsics.f(initializer, "initializer");
        this.f759d = initializer;
        this.f760e = UNINITIALIZED_VALUE.f762a;
        this.f761f = obj == null ? this : obj;
    }

    public boolean a() {
        return this.f760e != UNINITIALIZED_VALUE.f762a;
    }

    @Override // kotlin.Lazy
    public T getValue() {
        T t2;
        T t3 = (T) this.f760e;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f762a;
        if (t3 != uninitialized_value) {
            return t3;
        }
        synchronized (this.f761f) {
            t2 = (T) this.f760e;
            if (t2 == uninitialized_value) {
                Function0<? extends T> function0 = this.f759d;
                Intrinsics.c(function0);
                t2 = function0.mo2invoke();
                this.f760e = t2;
                this.f759d = null;
            }
        }
        return t2;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(Function0 function0, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i2 & 2) != 0 ? null : obj);
    }
}
