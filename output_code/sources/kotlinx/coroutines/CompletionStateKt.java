package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CompletionStateKt {
    public static final <T> Object a(Object obj, Continuation<? super T> continuation) {
        if (obj instanceof CompletedExceptionally) {
            Result.Companion companion = Result.f752d;
            return Result.a(ResultKt.a(((CompletedExceptionally) obj).f935a));
        }
        return Result.a(obj);
    }

    public static final <T> Object b(Object obj, Function1<? super Throwable, Unit> function1) {
        Throwable b2 = Result.b(obj);
        if (b2 == null) {
            if (function1 != null) {
                return new CompletedWithCancellation(obj, function1);
            }
            return obj;
        }
        return new CompletedExceptionally(b2, false, 2, null);
    }

    public static final <T> Object c(Object obj, CancellableContinuation<?> cancellableContinuation) {
        Throwable b2 = Result.b(obj);
        if (b2 != null) {
            return new CompletedExceptionally(b2, false, 2, null);
        }
        return obj;
    }

    public static /* synthetic */ Object d(Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            function1 = null;
        }
        return b(obj, function1);
    }
}
