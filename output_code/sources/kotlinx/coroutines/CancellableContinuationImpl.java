package kotlinx.coroutines;

import androidx.concurrent.futures.a;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame, Waiter {

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f920i = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decisionAndIndex");

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f921j = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f922k = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* renamed from: g  reason: collision with root package name */
    private final Continuation<T> f923g;

    /* renamed from: h  reason: collision with root package name */
    private final CoroutineContext f924h;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(Continuation<? super T> continuation, int i2) {
        super(i2);
        this.f923g = continuation;
        this.f924h = continuation.getContext();
        this._decisionAndIndex = 536870911;
        this._state = Active.f918d;
    }

    private final DisposableHandle A() {
        Job job = (Job) getContext().b(Job.f986c);
        if (job == null) {
            return null;
        }
        DisposableHandle d2 = Job.DefaultImpls.d(job, true, false, new ChildContinuation(this), 2, null);
        a.a(f922k, this, (Object) null, d2);
        return d2;
    }

    private final void B(Object obj) {
        boolean z2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof Active) {
                if (a.a(f921j, this, obj2, obj)) {
                    return;
                }
            } else {
                if (obj2 instanceof CancelHandler) {
                    z2 = true;
                } else {
                    z2 = obj2 instanceof Segment;
                }
                if (z2) {
                    F(obj, obj2);
                } else {
                    boolean z3 = obj2 instanceof CompletedExceptionally;
                    if (z3) {
                        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj2;
                        if (!completedExceptionally.b()) {
                            F(obj, obj2);
                        }
                        if (obj2 instanceof CancelledContinuation) {
                            Throwable th = null;
                            if (!z3) {
                                completedExceptionally = null;
                            }
                            if (completedExceptionally != null) {
                                th = completedExceptionally.f935a;
                            }
                            if (obj instanceof CancelHandler) {
                                m((CancelHandler) obj, th);
                                return;
                            }
                            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                            o((Segment) obj, th);
                            return;
                        }
                        return;
                    } else if (obj2 instanceof CompletedContinuation) {
                        CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                        if (completedContinuation.f930b != null) {
                            F(obj, obj2);
                        }
                        if (obj instanceof Segment) {
                            return;
                        }
                        Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        CancelHandler cancelHandler = (CancelHandler) obj;
                        if (completedContinuation.c()) {
                            m(cancelHandler, completedContinuation.f933e);
                            return;
                        }
                        if (a.a(f921j, this, obj2, CompletedContinuation.b(completedContinuation, null, cancelHandler, null, null, null, 29, null))) {
                            return;
                        }
                    } else if (obj instanceof Segment) {
                        return;
                    } else {
                        Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        if (a.a(f921j, this, obj2, new CompletedContinuation(obj2, (CancelHandler) obj, null, null, null, 28, null))) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private final boolean D() {
        if (DispatchedTaskKt.c(this.f957f)) {
            Continuation<T> continuation = this.f923g;
            Intrinsics.d(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).o()) {
                return true;
            }
        }
        return false;
    }

    private final CancelHandler E(Function1<? super Throwable, Unit> function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void F(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    private final void L(Object obj, int i2, Function1<? super Throwable, Unit> function1) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        do {
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
            } else {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation.c()) {
                        if (function1 != null) {
                            n(function1, cancelledContinuation.f935a);
                            return;
                        }
                        return;
                    }
                }
                l(obj);
                throw new KotlinNothingValueException();
            }
        } while (!a.a(f921j, this, obj2, N((NotCompleted) obj2, obj, i2, function1, null)));
        s();
        t(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void M(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i2, Function1 function1, int i3, Object obj2) {
        if (obj2 == null) {
            if ((i3 & 4) != 0) {
                function1 = null;
            }
            cancellableContinuationImpl.L(obj, i2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
    }

    private final Object N(NotCompleted notCompleted, Object obj, int i2, Function1<? super Throwable, Unit> function1, Object obj2) {
        CancelHandler cancelHandler;
        if (!(obj instanceof CompletedExceptionally)) {
            if (DispatchedTaskKt.b(i2) || obj2 != null) {
                if (function1 != null || (notCompleted instanceof CancelHandler) || obj2 != null) {
                    if (notCompleted instanceof CancelHandler) {
                        cancelHandler = (CancelHandler) notCompleted;
                    } else {
                        cancelHandler = null;
                    }
                    return new CompletedContinuation(obj, cancelHandler, function1, obj2, null, 16, null);
                }
                return obj;
            }
            return obj;
        }
        return obj;
    }

    private final boolean O() {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f920i;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f920i.compareAndSet(this, i2, 1073741824 + (536870911 & i2)));
        return true;
    }

    private final Symbol P(Object obj, Object obj2, Function1<? super Throwable, Unit> function1) {
        Object obj3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        do {
            obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj3 instanceof NotCompleted) {
            } else if (!(obj3 instanceof CompletedContinuation) || obj2 == null || ((CompletedContinuation) obj3).f932d != obj2) {
                return null;
            } else {
                return CancellableContinuationImplKt.f925a;
            }
        } while (!a.a(f921j, this, obj3, N((NotCompleted) obj3, obj, this.f957f, function1, obj2)));
        s();
        return CancellableContinuationImplKt.f925a;
    }

    private final boolean Q() {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f920i;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f920i.compareAndSet(this, i2, 536870912 + (536870911 & i2)));
        return true;
    }

    private final Void l(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void o(Segment<?> segment, Throwable th) {
        boolean z2;
        int i2 = f920i.get(this) & 536870911;
        if (i2 != 536870911) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            try {
                segment.o(i2, th, getContext());
                return;
            } catch (Throwable th2) {
                CoroutineContext context = getContext();
                CoroutineExceptionHandlerKt.a(context, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
                return;
            }
        }
        throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
    }

    private final boolean q(Throwable th) {
        if (!D()) {
            return false;
        }
        Continuation<T> continuation = this.f923g;
        Intrinsics.d(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return ((DispatchedContinuation) continuation).p(th);
    }

    private final void s() {
        if (!D()) {
            r();
        }
    }

    private final void t(int i2) {
        if (O()) {
            return;
        }
        DispatchedTaskKt.a(this, i2);
    }

    private final DisposableHandle v() {
        return (DisposableHandle) f922k.get(this);
    }

    private final String y() {
        Object x2 = x();
        if (x2 instanceof NotCompleted) {
            return "Active";
        }
        if (x2 instanceof CancelledContinuation) {
            return "Cancelled";
        }
        return "Completed";
    }

    public boolean C() {
        return !(x() instanceof NotCompleted);
    }

    protected String G() {
        return "CancellableContinuation";
    }

    public final void H(Throwable th) {
        if (q(th)) {
            return;
        }
        p(th);
        s();
    }

    public final void I() {
        DispatchedContinuation dispatchedContinuation;
        Throwable r2;
        Continuation<T> continuation = this.f923g;
        if (continuation instanceof DispatchedContinuation) {
            dispatchedContinuation = (DispatchedContinuation) continuation;
        } else {
            dispatchedContinuation = null;
        }
        if (dispatchedContinuation != null && (r2 = dispatchedContinuation.r(this)) != null) {
            r();
            p(r2);
        }
    }

    public final boolean J() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if ((obj instanceof CompletedContinuation) && ((CompletedContinuation) obj).f932d != null) {
            r();
            return false;
        }
        f920i.set(this, 536870911);
        atomicReferenceFieldUpdater.set(this, Active.f918d);
        return true;
    }

    public void K(T t2, Function1<? super Throwable, Unit> function1) {
        L(t2, this.f957f, function1);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object a(T t2, Object obj, Function1<? super Throwable, Unit> function1) {
        return P(t2, obj, function1);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void b(Object obj, Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof NotCompleted)) {
                if (obj2 instanceof CompletedExceptionally) {
                    return;
                }
                if (obj2 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                    if (!completedContinuation.c()) {
                        if (a.a(f921j, this, obj2, CompletedContinuation.b(completedContinuation, null, null, null, null, th, 15, null))) {
                            completedContinuation.d(this, th);
                            return;
                        }
                    } else {
                        throw new IllegalStateException("Must be called at most once".toString());
                    }
                } else if (a.a(f921j, this, obj2, new CompletedContinuation(obj2, null, null, null, th, 14, null))) {
                    return;
                }
            } else {
                throw new IllegalStateException("Not completed".toString());
            }
        }
    }

    @Override // kotlinx.coroutines.Waiter
    public void c(Segment<?> segment, int i2) {
        int i3;
        boolean z2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f920i;
        do {
            i3 = atomicIntegerFieldUpdater.get(this);
            if ((i3 & 536870911) == 536870911) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, ((i3 >> 29) << 29) + i2));
        B(segment);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void d(CoroutineDispatcher coroutineDispatcher, T t2) {
        DispatchedContinuation dispatchedContinuation;
        int i2;
        Continuation<T> continuation = this.f923g;
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (continuation instanceof DispatchedContinuation) {
            dispatchedContinuation = (DispatchedContinuation) continuation;
        } else {
            dispatchedContinuation = null;
        }
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.f1106g;
        }
        if (coroutineDispatcher2 == coroutineDispatcher) {
            i2 = 4;
        } else {
            i2 = this.f957f;
        }
        M(this, t2, i2, null, 4, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> e() {
        return this.f923g;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable f(Object obj) {
        Throwable f2 = super.f(obj);
        if (f2 != null) {
            return f2;
        }
        return null;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void g(Function1<? super Throwable, Unit> function1) {
        B(E(function1));
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.f923g;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f924h;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void h(Object obj) {
        t(this.f957f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T i(Object obj) {
        return obj instanceof CompletedContinuation ? (T) ((CompletedContinuation) obj).f929a : obj;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object k() {
        return x();
    }

    public final void m(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.d(th);
        } catch (Throwable th2) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.a(context, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void n(Function1<? super Throwable, Unit> function1, Throwable th) {
        try {
            function1.f(th);
        } catch (Throwable th2) {
            CoroutineContext context = getContext();
            CoroutineExceptionHandlerKt.a(context, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public boolean p(Throwable th) {
        Object obj;
        boolean z2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f921j;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            z2 = false;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            if ((obj instanceof CancelHandler) || (obj instanceof Segment)) {
                z2 = true;
            }
        } while (!a.a(f921j, this, obj, new CancelledContinuation(this, th, z2)));
        NotCompleted notCompleted = (NotCompleted) obj;
        if (notCompleted instanceof CancelHandler) {
            m((CancelHandler) obj, th);
        } else if (notCompleted instanceof Segment) {
            o((Segment) obj, th);
        }
        s();
        t(this.f957f);
        return true;
    }

    public final void r() {
        DisposableHandle v2 = v();
        if (v2 == null) {
            return;
        }
        v2.a();
        f922k.set(this, NonDisposableHandle.f1011d);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        M(this, CompletionStateKt.c(obj, this), this.f957f, null, 4, null);
    }

    public String toString() {
        return G() + '(' + DebugStringsKt.c(this.f923g) + "){" + y() + "}@" + DebugStringsKt.b(this);
    }

    public Throwable u(Job job) {
        return job.z();
    }

    public final Object w() {
        Job job;
        Object c2;
        boolean D = D();
        if (Q()) {
            if (v() == null) {
                A();
            }
            if (D) {
                I();
            }
            c2 = IntrinsicsKt__IntrinsicsKt.c();
            return c2;
        }
        if (D) {
            I();
        }
        Object x2 = x();
        if (!(x2 instanceof CompletedExceptionally)) {
            if (DispatchedTaskKt.b(this.f957f) && (job = (Job) getContext().b(Job.f986c)) != null && !job.c()) {
                CancellationException z2 = job.z();
                b(x2, z2);
                throw z2;
            }
            return i(x2);
        }
        throw ((CompletedExceptionally) x2).f935a;
    }

    public final Object x() {
        return f921j.get(this);
    }

    public void z() {
        DisposableHandle A = A();
        if (A != null && C()) {
            A.a();
            f922k.set(this, NonDisposableHandle.f1011d);
        }
    }
}
