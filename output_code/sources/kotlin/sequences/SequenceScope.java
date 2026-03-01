package kotlin.sequences;

import java.util.Iterator;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class SequenceScope<T> {
    public abstract Object b(T t2, Continuation<? super Unit> continuation);

    public abstract Object c(Iterator<? extends T> it, Continuation<? super Unit> continuation);

    public final Object e(Sequence<? extends T> sequence, Continuation<? super Unit> continuation) {
        Object c2;
        Object c3 = c(sequence.iterator(), continuation);
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        return c3 == c2 ? c3 : Unit.f763a;
    }
}
