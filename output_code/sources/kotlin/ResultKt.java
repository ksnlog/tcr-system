package kotlin;

import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ResultKt {
    public static final Object a(Throwable exception) {
        Intrinsics.f(exception, "exception");
        return new Result.Failure(exception);
    }

    public static final void b(Object obj) {
        if (!(obj instanceof Result.Failure)) {
            return;
        }
        throw ((Result.Failure) obj).f753d;
    }
}
