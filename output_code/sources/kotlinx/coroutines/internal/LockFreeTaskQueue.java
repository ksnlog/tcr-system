package kotlinx.coroutines.internal;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LockFreeTaskQueue<E> {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1128a = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur");
    private volatile Object _cur;

    public LockFreeTaskQueue(boolean z2) {
        this._cur = new LockFreeTaskQueueCore(8, z2);
    }

    public final boolean a(E e2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1128a;
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicReferenceFieldUpdater.get(this);
            int a2 = lockFreeTaskQueueCore.a(e2);
            if (a2 == 0) {
                return true;
            }
            if (a2 != 1) {
                if (a2 == 2) {
                    return false;
                }
            } else {
                a.a(f1128a, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
            }
        }
    }

    public final void b() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1128a;
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicReferenceFieldUpdater.get(this);
            if (lockFreeTaskQueueCore.d()) {
                return;
            }
            a.a(f1128a, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
        }
    }

    public final int c() {
        return ((LockFreeTaskQueueCore) f1128a.get(this)).f();
    }

    public final E d() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1128a;
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicReferenceFieldUpdater.get(this);
            E e2 = (E) lockFreeTaskQueueCore.j();
            if (e2 != LockFreeTaskQueueCore.f1132h) {
                return e2;
            }
            a.a(f1128a, this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.i());
        }
    }
}
