package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnsafeLazyImpl<T> implements Lazy<T>, Serializable {

    /* renamed from: d  reason: collision with root package name */
    private Function0<? extends T> f764d;

    /* renamed from: e  reason: collision with root package name */
    private Object f765e;

    public UnsafeLazyImpl(Function0<? extends T> initializer) {
        Intrinsics.f(initializer, "initializer");
        this.f764d = initializer;
        this.f765e = UNINITIALIZED_VALUE.f762a;
    }

    public boolean a() {
        return this.f765e != UNINITIALIZED_VALUE.f762a;
    }

    @Override // kotlin.Lazy
    public T getValue() {
        if (this.f765e == UNINITIALIZED_VALUE.f762a) {
            Function0<? extends T> function0 = this.f764d;
            Intrinsics.c(function0);
            this.f765e = function0.mo2invoke();
            this.f764d = null;
        }
        return (T) this.f765e;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
