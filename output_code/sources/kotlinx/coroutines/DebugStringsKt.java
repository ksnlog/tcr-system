package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DebugStringsKt {
    public static final String a(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String b(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String c(Continuation<?> continuation) {
        String a2;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Result.Companion companion = Result.f752d;
            a2 = Result.a(continuation + '@' + b(continuation));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.f752d;
            a2 = Result.a(ResultKt.a(th));
        }
        if (Result.b(a2) != null) {
            a2 = continuation.getClass().getName() + '@' + b(continuation);
        }
        return (String) a2;
    }
}
