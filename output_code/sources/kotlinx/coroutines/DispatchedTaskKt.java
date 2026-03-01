package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DispatchedTaskKt {
    public static final <T> void a(DispatchedTask<? super T> dispatchedTask, int i2) {
        boolean z2;
        Continuation<? super T> e2 = dispatchedTask.e();
        if (i2 == 4) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2 && (e2 instanceof DispatchedContinuation) && b(i2) == b(dispatchedTask.f957f)) {
            CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) e2).f1106g;
            CoroutineContext context = e2.getContext();
            if (coroutineDispatcher.R(context)) {
                coroutineDispatcher.Q(context, dispatchedTask);
                return;
            } else {
                e(dispatchedTask);
                return;
            }
        }
        d(dispatchedTask, e2, z2);
    }

    public static final boolean b(int i2) {
        return i2 == 1 || i2 == 2;
    }

    public static final boolean c(int i2) {
        return i2 == 2;
    }

    public static final <T> void d(DispatchedTask<? super T> dispatchedTask, Continuation<? super T> continuation, boolean z2) {
        Object i2;
        UndispatchedCoroutine<?> undispatchedCoroutine;
        boolean B0;
        Object k2 = dispatchedTask.k();
        Throwable f2 = dispatchedTask.f(k2);
        if (f2 != null) {
            Result.Companion companion = Result.f752d;
            i2 = ResultKt.a(f2);
        } else {
            Result.Companion companion2 = Result.f752d;
            i2 = dispatchedTask.i(k2);
        }
        Object a2 = Result.a(i2);
        if (z2) {
            Intrinsics.d(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Continuation<T> continuation2 = dispatchedContinuation.f1107h;
            Object obj = dispatchedContinuation.f1109j;
            CoroutineContext context = continuation2.getContext();
            Object c2 = ThreadContextKt.c(context, obj);
            if (c2 != ThreadContextKt.f1156a) {
                undispatchedCoroutine = CoroutineContextKt.g(continuation2, context, c2);
            } else {
                undispatchedCoroutine = null;
            }
            try {
                dispatchedContinuation.f1107h.resumeWith(a2);
                Unit unit = Unit.f763a;
                if (undispatchedCoroutine != null) {
                    if (!B0) {
                        return;
                    }
                }
                return;
            } finally {
                if (undispatchedCoroutine == null || undispatchedCoroutine.B0()) {
                    ThreadContextKt.a(context, c2);
                }
            }
        }
        continuation.resumeWith(a2);
    }

    private static final void e(DispatchedTask<?> dispatchedTask) {
        EventLoop a2 = ThreadLocalEventLoop.f1012a.a();
        if (a2.Z()) {
            a2.V(dispatchedTask);
            return;
        }
        a2.X(true);
        try {
            d(dispatchedTask, dispatchedTask.e(), true);
            do {
            } while (a2.b0());
        } finally {
            try {
            } finally {
            }
        }
    }
}
