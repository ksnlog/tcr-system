package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DebugProbesKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Continuation<T> a(Continuation<? super T> completion) {
        Intrinsics.f(completion, "completion");
        return completion;
    }

    public static final void b(Continuation<?> frame) {
        Intrinsics.f(frame, "frame");
    }

    public static final void c(Continuation<?> frame) {
        Intrinsics.f(frame, "frame");
    }
}
