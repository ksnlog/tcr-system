package kotlin.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ContinuationKt {
    public static final <R, T> void a(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r2, Continuation<? super T> completion) {
        Continuation a2;
        Continuation b2;
        Intrinsics.f(function2, "<this>");
        Intrinsics.f(completion, "completion");
        a2 = IntrinsicsKt__IntrinsicsJvmKt.a(function2, r2, completion);
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(a2);
        Result.Companion companion = Result.f752d;
        b2.resumeWith(Result.a(Unit.f763a));
    }
}
