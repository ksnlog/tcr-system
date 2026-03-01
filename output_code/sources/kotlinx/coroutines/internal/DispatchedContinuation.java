package kotlinx.coroutines.internal;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1105k = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;

    /* renamed from: g  reason: collision with root package name */
    public final CoroutineDispatcher f1106g;

    /* renamed from: h  reason: collision with root package name */
    public final Continuation<T> f1107h;

    /* renamed from: i  reason: collision with root package name */
    public Object f1108i;

    /* renamed from: j  reason: collision with root package name */
    public final Object f1109j;

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation<? super T> continuation) {
        super(-1);
        this.f1106g = coroutineDispatcher;
        this.f1107h = continuation;
        this.f1108i = DispatchedContinuationKt.a();
        this.f1109j = ThreadContextKt.b(getContext());
    }

    private final CancellableContinuationImpl<?> n() {
        Object obj = f1105k.get(this);
        if (obj instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) obj;
        }
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void b(Object obj, Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).f937b.f(th);
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> e() {
        return this;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.f1107h;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f1107h.getContext();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object k() {
        Object obj = this.f1108i;
        this.f1108i = DispatchedContinuationKt.a();
        return obj;
    }

    public final void l() {
        do {
        } while (f1105k.get(this) == DispatchedContinuationKt.f1111b);
    }

    public final CancellableContinuationImpl<T> m() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1105k;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                f1105k.set(this, DispatchedContinuationKt.f1111b);
                return null;
            } else if (obj instanceof CancellableContinuationImpl) {
                if (a.a(f1105k, this, obj, DispatchedContinuationKt.f1111b)) {
                    return (CancellableContinuationImpl) obj;
                }
            } else if (obj != DispatchedContinuationKt.f1111b && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
    }

    public final boolean o() {
        if (f1105k.get(this) != null) {
            return true;
        }
        return false;
    }

    public final boolean p(Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1105k;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            Symbol symbol = DispatchedContinuationKt.f1111b;
            if (Intrinsics.a(obj, symbol)) {
                if (a.a(f1105k, this, symbol, th)) {
                    return true;
                }
            } else if (obj instanceof Throwable) {
                return true;
            } else {
                if (a.a(f1105k, this, obj, (Object) null)) {
                    return false;
                }
            }
        }
    }

    public final void q() {
        l();
        CancellableContinuationImpl<?> n2 = n();
        if (n2 != null) {
            n2.r();
        }
    }

    public final Throwable r(CancellableContinuation<?> cancellableContinuation) {
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1105k;
        do {
            Object obj = atomicReferenceFieldUpdater.get(this);
            symbol = DispatchedContinuationKt.f1111b;
            if (obj != symbol) {
                if (obj instanceof Throwable) {
                    if (a.a(f1105k, this, obj, (Object) null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!a.a(f1105k, this, symbol, cancellableContinuation));
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        CoroutineContext context = this.f1107h.getContext();
        Object d2 = CompletionStateKt.d(obj, null, 1, null);
        if (this.f1106g.R(context)) {
            this.f1108i = d2;
            this.f957f = 0;
            this.f1106g.Q(context, this);
            return;
        }
        EventLoop a2 = ThreadLocalEventLoop.f1012a.a();
        if (a2.Z()) {
            this.f1108i = d2;
            this.f957f = 0;
            a2.V(this);
            return;
        }
        a2.X(true);
        try {
            CoroutineContext context2 = getContext();
            Object c2 = ThreadContextKt.c(context2, this.f1109j);
            this.f1107h.resumeWith(obj);
            Unit unit = Unit.f763a;
            ThreadContextKt.a(context2, c2);
            do {
            } while (a2.b0());
        } finally {
            try {
            } finally {
            }
        }
    }

    public String toString() {
        return "DispatchedContinuation[" + this.f1106g + ", " + DebugStringsKt.c(this.f1107h) + ']';
    }
}
