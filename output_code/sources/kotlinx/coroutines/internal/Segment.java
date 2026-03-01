package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.NotCompleted;
import kotlinx.coroutines.internal.Segment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> implements NotCompleted {

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1149g = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
    private volatile int cleanedAndPointers;

    /* renamed from: f  reason: collision with root package name */
    public final long f1150f;

    public Segment(long j2, S s2, int i2) {
        super(s2);
        this.f1150f = j2;
        this.cleanedAndPointers = i2 << 16;
    }

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean h() {
        if (f1149g.get(this) == n() && !i()) {
            return true;
        }
        return false;
    }

    public final boolean m() {
        if (f1149g.addAndGet(this, -65536) == n() && !i()) {
            return true;
        }
        return false;
    }

    public abstract int n();

    public abstract void o(int i2, Throwable th, CoroutineContext coroutineContext);

    public final void p() {
        if (f1149g.incrementAndGet(this) == n()) {
            k();
        }
    }

    public final boolean q() {
        int i2;
        boolean z2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f1149g;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 == n() && !i()) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!z2) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
