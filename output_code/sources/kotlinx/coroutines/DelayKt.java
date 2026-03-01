package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DelayKt {
    public static final Object a(long j2, Continuation<? super Unit> continuation) {
        Continuation b2;
        Object c2;
        Object c3;
        if (j2 <= 0) {
            return Unit.f763a;
        }
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(b2, 1);
        cancellableContinuationImpl.z();
        if (j2 < Long.MAX_VALUE) {
            b(cancellableContinuationImpl.getContext()).p(j2, cancellableContinuationImpl);
        }
        Object w2 = cancellableContinuationImpl.w();
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        if (w2 == c2) {
            DebugProbesKt.c(continuation);
        }
        c3 = IntrinsicsKt__IntrinsicsKt.c();
        if (w2 == c3) {
            return w2;
        }
        return Unit.f763a;
    }

    public static final Delay b(CoroutineContext coroutineContext) {
        CoroutineContext.Element b2 = coroutineContext.b(ContinuationInterceptor.f799a);
        Delay delay = b2 instanceof Delay ? (Delay) b2 : null;
        return delay == null ? DefaultExecutorKt.a() : delay;
    }
}
