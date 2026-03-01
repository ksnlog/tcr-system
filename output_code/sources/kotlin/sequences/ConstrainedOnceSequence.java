package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConstrainedOnceSequence<T> implements Sequence<T> {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReference<Sequence<T>> f901a;

    public ConstrainedOnceSequence(Sequence<? extends T> sequence) {
        Intrinsics.f(sequence, "sequence");
        this.f901a = new AtomicReference<>(sequence);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        Sequence<T> andSet = this.f901a.getAndSet(null);
        if (andSet != null) {
            return andSet.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
