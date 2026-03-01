package kotlin;

import androidx.concurrent.futures.a;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SafePublicationLazyImpl<T> implements Lazy<T>, Serializable {

    /* renamed from: g  reason: collision with root package name */
    public static final Companion f754g = new Companion(null);

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater<SafePublicationLazyImpl<?>, Object> f755h = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "e");

    /* renamed from: d  reason: collision with root package name */
    private volatile Function0<? extends T> f756d;

    /* renamed from: e  reason: collision with root package name */
    private volatile Object f757e;

    /* renamed from: f  reason: collision with root package name */
    private final Object f758f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SafePublicationLazyImpl(Function0<? extends T> initializer) {
        Intrinsics.f(initializer, "initializer");
        this.f756d = initializer;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f762a;
        this.f757e = uninitialized_value;
        this.f758f = uninitialized_value;
    }

    public boolean a() {
        return this.f757e != UNINITIALIZED_VALUE.f762a;
    }

    @Override // kotlin.Lazy
    public T getValue() {
        T t2 = (T) this.f757e;
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.f762a;
        if (t2 != uninitialized_value) {
            return t2;
        }
        Function0<? extends T> function0 = this.f756d;
        if (function0 != null) {
            T mo2invoke = function0.mo2invoke();
            if (a.a(f755h, this, uninitialized_value, mo2invoke)) {
                this.f756d = null;
                return mo2invoke;
            }
        }
        return (T) this.f757e;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
