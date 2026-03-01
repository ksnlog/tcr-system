package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DispatchedContinuationKt {

    /* renamed from: a */
    private static final Symbol f1110a = new Symbol("UNDEFINED");

    /* renamed from: b */
    public static final Symbol f1111b = new Symbol("REUSABLE_CLAIMED");

    public static final <T> void b(Continuation<? super T> continuation, Object obj, Function1<? super Throwable, Unit> function1) {
        boolean z2;
        UndispatchedCoroutine<?> undispatchedCoroutine;
        if (continuation instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Object b2 = CompletionStateKt.b(obj, function1);
            if (dispatchedContinuation.f1106g.R(dispatchedContinuation.getContext())) {
                dispatchedContinuation.f1108i = b2;
                dispatchedContinuation.f957f = 1;
                dispatchedContinuation.f1106g.Q(dispatchedContinuation.getContext(), dispatchedContinuation);
                return;
            }
            EventLoop a2 = ThreadLocalEventLoop.f1012a.a();
            if (a2.Z()) {
                dispatchedContinuation.f1108i = b2;
                dispatchedContinuation.f957f = 1;
                a2.V(dispatchedContinuation);
                return;
            }
            a2.X(true);
            try {
                Job job = (Job) dispatchedContinuation.getContext().b(Job.f986c);
                if (job != null && !job.c()) {
                    CancellationException z3 = job.z();
                    dispatchedContinuation.b(b2, z3);
                    Result.Companion companion = Result.f752d;
                    dispatchedContinuation.resumeWith(Result.a(ResultKt.a(z3)));
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    Continuation<T> continuation2 = dispatchedContinuation.f1107h;
                    Object obj2 = dispatchedContinuation.f1109j;
                    CoroutineContext context = continuation2.getContext();
                    Object c2 = ThreadContextKt.c(context, obj2);
                    if (c2 != ThreadContextKt.f1156a) {
                        undispatchedCoroutine = CoroutineContextKt.g(continuation2, context, c2);
                    } else {
                        undispatchedCoroutine = null;
                    }
                    dispatchedContinuation.f1107h.resumeWith(obj);
                    Unit unit = Unit.f763a;
                    if (undispatchedCoroutine == null || undispatchedCoroutine.B0()) {
                        ThreadContextKt.a(context, c2);
                    }
                }
                do {
                } while (a2.b0());
            } finally {
                try {
                    return;
                } finally {
                }
            }
            return;
        }
        continuation.resumeWith(obj);
    }

    public static /* synthetic */ void c(Continuation continuation, Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        b(continuation, obj, function1);
    }
}
