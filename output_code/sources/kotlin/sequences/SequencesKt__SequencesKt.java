package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
    public static <T> Sequence<T> c(final Iterator<? extends T> it) {
        Sequence<T> d2;
        Intrinsics.f(it, "<this>");
        d2 = d(new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                return it;
            }
        });
        return d2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Sequence<T> d(Sequence<? extends T> sequence) {
        Intrinsics.f(sequence, "<this>");
        if (!(sequence instanceof ConstrainedOnceSequence)) {
            return new ConstrainedOnceSequence(sequence);
        }
        return sequence;
    }
}
