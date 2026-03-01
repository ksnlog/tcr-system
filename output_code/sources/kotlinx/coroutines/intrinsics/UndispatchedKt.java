package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UndispatchedKt {
    public static final <R, T> void a(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, Continuation<? super T> continuation) {
        Object c2;
        Continuation a2 = DebugProbesKt.a(continuation);
        try {
            CoroutineContext context = continuation.getContext();
            Object c3 = ThreadContextKt.c(context, null);
            Object invoke = ((Function2) TypeIntrinsics.a(function2, 2)).invoke(r2, a2);
            ThreadContextKt.a(context, c3);
            c2 = IntrinsicsKt__IntrinsicsKt.c();
            if (invoke != c2) {
                a2.resumeWith(Result.a(invoke));
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.f752d;
            a2.resumeWith(Result.a(ResultKt.a(th)));
        }
    }

    public static final <T, R> Object b(ScopeCoroutine<? super T> scopeCoroutine, R r2, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object completedExceptionally;
        Object c2;
        Object c3;
        Object c4;
        try {
            completedExceptionally = ((Function2) TypeIntrinsics.a(function2, 2)).invoke(r2, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        if (completedExceptionally == c2) {
            c4 = IntrinsicsKt__IntrinsicsKt.c();
            return c4;
        }
        Object a02 = scopeCoroutine.a0(completedExceptionally);
        if (a02 == JobSupportKt.f1004b) {
            c3 = IntrinsicsKt__IntrinsicsKt.c();
            return c3;
        } else if (!(a02 instanceof CompletedExceptionally)) {
            return JobSupportKt.h(a02);
        } else {
            throw ((CompletedExceptionally) a02).f935a;
        }
    }
}
