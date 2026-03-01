package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {
    public static final <T, C extends Collection<? super T>> C e(Sequence<? extends T> sequence, C destination) {
        Intrinsics.f(sequence, "<this>");
        Intrinsics.f(destination, "destination");
        for (T t2 : sequence) {
            destination.add(t2);
        }
        return destination;
    }

    public static <T> List<T> f(Sequence<? extends T> sequence) {
        Intrinsics.f(sequence, "<this>");
        return CollectionsKt.j(g(sequence));
    }

    public static final <T> List<T> g(Sequence<? extends T> sequence) {
        Intrinsics.f(sequence, "<this>");
        return (List) e(sequence, new ArrayList());
    }
}
