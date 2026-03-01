package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class WorkQueue {

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1219b = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1220c = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1221d = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1222e = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReferenceArray<Task> f1223a = new AtomicReferenceArray<>(128);
    private volatile int blockingTasksInBuffer;
    private volatile int consumerIndex;
    private volatile Object lastScheduledTask;
    private volatile int producerIndex;

    private final Task b(Task task) {
        if (d() == 127) {
            return task;
        }
        boolean z2 = true;
        if (task.f1207e.b() != 1) {
            z2 = false;
        }
        if (z2) {
            f1222e.incrementAndGet(this);
        }
        int i2 = f1220c.get(this) & 127;
        while (this.f1223a.get(i2) != null) {
            Thread.yield();
        }
        this.f1223a.lazySet(i2, task);
        f1220c.incrementAndGet(this);
        return null;
    }

    private final void c(Task task) {
        if (task != null) {
            boolean z2 = true;
            if (task.f1207e.b() != 1) {
                z2 = false;
            }
            if (z2) {
                f1222e.decrementAndGet(this);
            }
        }
    }

    private final int d() {
        return f1220c.get(this) - f1221d.get(this);
    }

    private final Task i() {
        Task andSet;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f1221d;
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 - f1220c.get(this) == 0) {
                return null;
            }
            int i3 = i2 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 + 1) && (andSet = this.f1223a.getAndSet(i3, null)) != null) {
                c(andSet);
                return andSet;
            }
        }
    }

    private final boolean j(GlobalQueue globalQueue) {
        Task i2 = i();
        if (i2 == null) {
            return false;
        }
        globalQueue.a(i2);
        return true;
    }

    private final Task k(boolean z2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Task task;
        do {
            atomicReferenceFieldUpdater = f1219b;
            task = (Task) atomicReferenceFieldUpdater.get(this);
            if (task != null) {
                boolean z3 = true;
                if (task.f1207e.b() != 1) {
                    z3 = false;
                }
                if (z3 == z2) {
                }
            }
            int i2 = f1221d.get(this);
            int i3 = f1220c.get(this);
            while (i2 != i3) {
                if (z2 && f1222e.get(this) == 0) {
                    return null;
                }
                i3--;
                Task m2 = m(i3, z2);
                if (m2 != null) {
                    return m2;
                }
            }
            return null;
        } while (!a.a(atomicReferenceFieldUpdater, this, task, (Object) null));
        return task;
    }

    private final Task l(int i2) {
        int i3 = f1221d.get(this);
        int i4 = f1220c.get(this);
        boolean z2 = true;
        if (i2 != 1) {
            z2 = false;
        }
        while (i3 != i4) {
            if (z2 && f1222e.get(this) == 0) {
                return null;
            }
            int i5 = i3 + 1;
            Task m2 = m(i3, z2);
            if (m2 == null) {
                i3 = i5;
            } else {
                return m2;
            }
        }
        return null;
    }

    private final Task m(int i2, boolean z2) {
        int i3 = i2 & 127;
        Task task = this.f1223a.get(i3);
        if (task != null) {
            boolean z3 = true;
            if (task.f1207e.b() != 1) {
                z3 = false;
            }
            if (z3 == z2 && w.a.a(this.f1223a, i3, task, null)) {
                if (z2) {
                    f1222e.decrementAndGet(this);
                }
                return task;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, kotlinx.coroutines.scheduling.Task, java.lang.Object] */
    private final long o(int i2, Ref$ObjectRef<Task> ref$ObjectRef) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ?? r1;
        boolean z2;
        do {
            atomicReferenceFieldUpdater = f1219b;
            r1 = (Task) atomicReferenceFieldUpdater.get(this);
            if (r1 == 0) {
                return -2L;
            }
            int i3 = 1;
            if (r1.f1207e.b() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                i3 = 2;
            }
            if ((i3 & i2) == 0) {
                return -2L;
            }
            long a2 = TasksKt.f1215f.a() - r1.f1206d;
            long j2 = TasksKt.f1211b;
            if (a2 < j2) {
                return j2 - a2;
            }
        } while (!a.a(atomicReferenceFieldUpdater, this, (Object) r1, (Object) null));
        ref$ObjectRef.f884d = r1;
        return -1L;
    }

    public final Task a(Task task, boolean z2) {
        if (z2) {
            return b(task);
        }
        Task task2 = (Task) f1219b.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return b(task2);
    }

    public final int e() {
        return f1219b.get(this) != null ? d() + 1 : d();
    }

    public final void f(GlobalQueue globalQueue) {
        Task task = (Task) f1219b.getAndSet(this, null);
        if (task != null) {
            globalQueue.a(task);
        }
        do {
        } while (j(globalQueue));
    }

    public final Task g() {
        Task task = (Task) f1219b.getAndSet(this, null);
        return task == null ? i() : task;
    }

    public final Task h() {
        return k(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long n(int i2, Ref$ObjectRef<Task> ref$ObjectRef) {
        T t2;
        if (i2 == 3) {
            t2 = i();
        } else {
            t2 = l(i2);
        }
        if (t2 != 0) {
            ref$ObjectRef.f884d = t2;
            return -1L;
        }
        return o(i2, ref$ObjectRef);
    }
}
