package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineScopeKt {
    public static final <R> Object a(Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        Object c2;
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation.getContext(), continuation);
        Object b2 = UndispatchedKt.b(scopeCoroutine, scopeCoroutine, function2);
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        if (b2 == c2) {
            DebugProbesKt.c(continuation);
        }
        return b2;
    }
}
