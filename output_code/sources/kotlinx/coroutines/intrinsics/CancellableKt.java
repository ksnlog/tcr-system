package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CancellableKt {
    private static final void a(Continuation<?> continuation, Throwable th) {
        Result.Companion companion = Result.f752d;
        continuation.resumeWith(Result.a(ResultKt.a(th)));
        throw th;
    }

    public static final void b(Continuation<? super Unit> continuation, Continuation<?> continuation2) {
        Continuation b2;
        try {
            b2 = IntrinsicsKt__IntrinsicsJvmKt.b(continuation);
            Result.Companion companion = Result.f752d;
            DispatchedContinuationKt.c(b2, Result.a(Unit.f763a), null, 2, null);
        } catch (Throwable th) {
            a(continuation2, th);
        }
    }

    public static final <R, T> void c(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1) {
        Continuation a2;
        Continuation b2;
        try {
            a2 = IntrinsicsKt__IntrinsicsJvmKt.a(function2, r2, continuation);
            b2 = IntrinsicsKt__IntrinsicsJvmKt.b(a2);
            Result.Companion companion = Result.f752d;
            DispatchedContinuationKt.b(b2, Result.a(Unit.f763a), function1);
        } catch (Throwable th) {
            a(continuation, th);
        }
    }

    public static /* synthetic */ void d(Function2 function2, Object obj, Continuation continuation, Function1 function1, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        c(function2, obj, continuation, function1);
    }
}
