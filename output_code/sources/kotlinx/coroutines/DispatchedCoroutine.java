package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f956h = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");
    private volatile int _decision;

    public DispatchedCoroutine(CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        super(coroutineContext, continuation);
    }

    private final boolean C0() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f956h;
        do {
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f956h.compareAndSet(this, 0, 2));
        return true;
    }

    private final boolean D0() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f956h;
        do {
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f956h.compareAndSet(this, 0, 1));
        return true;
    }

    public final Object B0() {
        Object c2;
        if (D0()) {
            c2 = IntrinsicsKt__IntrinsicsKt.c();
            return c2;
        }
        Object h2 = JobSupportKt.h(T());
        if (!(h2 instanceof CompletedExceptionally)) {
            return h2;
        }
        throw ((CompletedExceptionally) h2).f935a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public void q(Object obj) {
        x0(obj);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void x0(Object obj) {
        Continuation b2;
        if (C0()) {
            return;
        }
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(this.f1148g);
        DispatchedContinuationKt.c(b2, CompletionStateKt.a(obj, this.f1148g), null, 2, null);
    }
}
