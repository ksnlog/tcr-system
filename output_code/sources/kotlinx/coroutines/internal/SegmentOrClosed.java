package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SegmentOrClosed<S extends Segment<S>> {
    public static <S extends Segment<S>> Object a(Object obj) {
        return obj;
    }

    public static final S b(Object obj) {
        if (obj != ConcurrentLinkedListKt.f1100a) {
            Intrinsics.d(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
            return (S) obj;
        }
        throw new IllegalStateException("Does not contain segment".toString());
    }

    public static final boolean c(Object obj) {
        return obj == ConcurrentLinkedListKt.f1100a;
    }
}
