package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConcurrentLinkedListKt {

    /* renamed from: a */
    private static final Symbol f1100a = new Symbol("CLOSED");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    public static final <N extends ConcurrentLinkedListNode<N>> N b(N n2) {
        while (true) {
            Object f2 = n2.f();
            if (f2 == f1100a) {
                return n2;
            }
            ?? r0 = (ConcurrentLinkedListNode) f2;
            if (r0 == 0) {
                if (n2.j()) {
                    return n2;
                }
            } else {
                n2 = r0;
            }
        }
    }

    public static final <S extends Segment<S>> Object c(S s2, long j2, Function2<? super Long, ? super S, ? extends S> function2) {
        while (true) {
            if (s2.f1150f >= j2 && !s2.h()) {
                return SegmentOrClosed.a(s2);
            }
            Object f2 = s2.f();
            if (f2 == f1100a) {
                return SegmentOrClosed.a(f1100a);
            }
            S s3 = (S) ((ConcurrentLinkedListNode) f2);
            if (s3 == null) {
                s3 = function2.invoke(Long.valueOf(s2.f1150f + 1), s2);
                if (s2.l(s3)) {
                    if (s2.h()) {
                        s2.k();
                    }
                }
            }
            s2 = s3;
        }
    }
}
