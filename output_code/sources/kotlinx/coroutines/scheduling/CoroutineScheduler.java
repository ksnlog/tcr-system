package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.random.Random;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {

    /* renamed from: k  reason: collision with root package name */
    public static final Companion f1169k = new Companion(null);

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1170l = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");

    /* renamed from: m  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1171m = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");

    /* renamed from: n  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1172n = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");

    /* renamed from: o  reason: collision with root package name */
    public static final Symbol f1173o = new Symbol("NOT_IN_STACK");
    private volatile int _isTerminated;
    private volatile long controlState;

    /* renamed from: d  reason: collision with root package name */
    public final int f1174d;

    /* renamed from: e  reason: collision with root package name */
    public final int f1175e;

    /* renamed from: f  reason: collision with root package name */
    public final long f1176f;

    /* renamed from: g  reason: collision with root package name */
    public final String f1177g;

    /* renamed from: h  reason: collision with root package name */
    public final GlobalQueue f1178h;

    /* renamed from: i  reason: collision with root package name */
    public final GlobalQueue f1179i;

    /* renamed from: j  reason: collision with root package name */
    public final ResizableAtomicArray<Worker> f1180j;
    private volatile long parkedWorkersStack;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1181a;

        static {
            int[] iArr = new int[WorkerState.values().length];
            try {
                iArr[WorkerState.PARKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WorkerState.BLOCKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WorkerState.DORMANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[WorkerState.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            f1181a = iArr;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i2, int i3, long j2, String str) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.f1174d = i2;
        this.f1175e = i3;
        this.f1176f = j2;
        this.f1177g = str;
        boolean z5 = true;
        if (i2 >= 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i3 >= i2) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                if (i3 <= 2097150) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (z4) {
                    if (j2 <= 0 ? false : z5) {
                        this.f1178h = new GlobalQueue();
                        this.f1179i = new GlobalQueue();
                        this.f1180j = new ResizableAtomicArray<>((i2 + 1) * 2);
                        this.controlState = i2 << 42;
                        this._isTerminated = 0;
                        return;
                    }
                    throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
                }
                throw new IllegalArgumentException(("Max pool size " + i3 + " should not exceed maximal supported number of threads 2097150").toString());
            }
            throw new IllegalArgumentException(("Max pool size " + i3 + " should be greater than or equals to core pool size " + i2).toString());
        }
        throw new IllegalArgumentException(("Core pool size " + i2 + " should be at least 1").toString());
    }

    private final void D(long j2, boolean z2) {
        if (z2 || O() || I(j2)) {
            return;
        }
        O();
    }

    private final Task G(Worker worker, Task task, boolean z2) {
        if (worker == null) {
            return task;
        }
        if (worker.f1185f == WorkerState.TERMINATED) {
            return task;
        }
        if (task.f1207e.b() == 0 && worker.f1185f == WorkerState.BLOCKING) {
            return task;
        }
        worker.f1189j = true;
        return worker.f1183d.a(task, z2);
    }

    private final boolean I(long j2) {
        int a2;
        a2 = RangesKt___RangesKt.a(((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21)), 0);
        if (a2 < this.f1174d) {
            int e2 = e();
            if (e2 == 1 && this.f1174d > 1) {
                e();
            }
            if (e2 > 0) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ boolean J(CoroutineScheduler coroutineScheduler, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = f1171m.get(coroutineScheduler);
        }
        return coroutineScheduler.I(j2);
    }

    private final boolean O() {
        Worker r2;
        do {
            r2 = r();
            if (r2 == null) {
                return false;
            }
        } while (!Worker.j().compareAndSet(r2, -1, 0));
        LockSupport.unpark(r2);
        return true;
    }

    private final boolean c(Task task) {
        boolean z2 = true;
        if (task.f1207e.b() != 1) {
            z2 = false;
        }
        if (z2) {
            return this.f1179i.a(task);
        }
        return this.f1178h.a(task);
    }

    private final int e() {
        int a2;
        boolean z2;
        synchronized (this.f1180j) {
            if (isTerminated()) {
                return -1;
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater = f1171m;
            long j2 = atomicLongFieldUpdater.get(this);
            int i2 = (int) (j2 & 2097151);
            boolean z3 = false;
            a2 = RangesKt___RangesKt.a(i2 - ((int) ((j2 & 4398044413952L) >> 21)), 0);
            if (a2 >= this.f1174d) {
                return 0;
            }
            if (i2 >= this.f1175e) {
                return 0;
            }
            int i3 = ((int) (f1171m.get(this) & 2097151)) + 1;
            if (i3 > 0 && this.f1180j.b(i3) == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                Worker worker = new Worker(this, i3);
                this.f1180j.c(i3, worker);
                if (i3 == ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    z3 = true;
                }
                if (z3) {
                    int i4 = a2 + 1;
                    worker.start();
                    return i4;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final Worker m() {
        Worker worker;
        Thread currentThread = Thread.currentThread();
        if (currentThread instanceof Worker) {
            worker = (Worker) currentThread;
        } else {
            worker = null;
        }
        if (worker == null || !Intrinsics.a(CoroutineScheduler.this, this)) {
            return null;
        }
        return worker;
    }

    public static /* synthetic */ void o(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            taskContext = TasksKt.f1216g;
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        coroutineScheduler.n(runnable, taskContext, z2);
    }

    private final int p(Worker worker) {
        Object i2 = worker.i();
        while (i2 != f1173o) {
            if (i2 == null) {
                return 0;
            }
            Worker worker2 = (Worker) i2;
            int h2 = worker2.h();
            if (h2 != 0) {
                return h2;
            }
            i2 = worker2.i();
        }
        return -1;
    }

    private final Worker r() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1170l;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            Worker b2 = this.f1180j.b((int) (2097151 & j2));
            if (b2 == null) {
                return null;
            }
            long j3 = (2097152 + j2) & (-2097152);
            int p2 = p(b2);
            if (p2 >= 0 && f1170l.compareAndSet(this, j2, p2 | j3)) {
                b2.r(f1173o);
                return b2;
            }
        }
    }

    public final void B(long j2) {
        int i2;
        Task d2;
        if (!f1172n.compareAndSet(this, 0, 1)) {
            return;
        }
        Worker m2 = m();
        synchronized (this.f1180j) {
            i2 = (int) (f1171m.get(this) & 2097151);
        }
        if (1 <= i2) {
            int i3 = 1;
            while (true) {
                Worker b2 = this.f1180j.b(i3);
                Intrinsics.c(b2);
                Worker worker = b2;
                if (worker != m2) {
                    while (worker.isAlive()) {
                        LockSupport.unpark(worker);
                        worker.join(j2);
                    }
                    worker.f1183d.f(this.f1179i);
                }
                if (i3 == i2) {
                    break;
                }
                i3++;
            }
        }
        this.f1179i.b();
        this.f1178h.b();
        while (true) {
            if (m2 != null) {
                d2 = m2.g(true);
                if (d2 != null) {
                    continue;
                    z(d2);
                }
            }
            d2 = this.f1178h.d();
            if (d2 == null && (d2 = this.f1179i.d()) == null) {
                break;
            }
            z(d2);
        }
        if (m2 != null) {
            m2.u(WorkerState.TERMINATED);
        }
        f1170l.set(this, 0L);
        f1171m.set(this, 0L);
    }

    public final void E() {
        if (O() || J(this, 0L, 1, null)) {
            return;
        }
        O();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        B(10000L);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        o(this, runnable, null, false, 6, null);
    }

    public final boolean isTerminated() {
        if (f1172n.get(this) != 0) {
            return true;
        }
        return false;
    }

    public final Task k(Runnable runnable, TaskContext taskContext) {
        long a2 = TasksKt.f1215f.a();
        if (runnable instanceof Task) {
            Task task = (Task) runnable;
            task.f1206d = a2;
            task.f1207e = taskContext;
            return task;
        }
        return new TaskImpl(runnable, a2, taskContext);
    }

    public final void n(Runnable runnable, TaskContext taskContext, boolean z2) {
        boolean z3;
        long j2;
        AbstractTimeSourceKt.a();
        Task k2 = k(runnable, taskContext);
        boolean z4 = false;
        if (k2.f1207e.b() == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            j2 = f1171m.addAndGet(this, 2097152L);
        } else {
            j2 = 0;
        }
        Worker m2 = m();
        Task G = G(m2, k2, z2);
        if (G != null && !c(G)) {
            throw new RejectedExecutionException(this.f1177g + " was terminated");
        }
        if (z2 && m2 != null) {
            z4 = true;
        }
        if (z3) {
            D(j2, z4);
        } else if (z4) {
        } else {
            E();
        }
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        int a2 = this.f1180j.a();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 1; i7 < a2; i7++) {
            Worker b2 = this.f1180j.b(i7);
            if (b2 != null) {
                int e2 = b2.f1183d.e();
                int i8 = WhenMappings.f1181a[b2.f1185f.ordinal()];
                if (i8 != 1) {
                    if (i8 != 2) {
                        if (i8 != 3) {
                            if (i8 != 4) {
                                if (i8 == 5) {
                                    i6++;
                                }
                            } else {
                                i5++;
                                if (e2 > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(e2);
                                    sb.append('d');
                                    arrayList.add(sb.toString());
                                }
                            }
                        } else {
                            i2++;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(e2);
                            sb2.append('c');
                            arrayList.add(sb2.toString());
                        }
                    } else {
                        i3++;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(e2);
                        sb3.append('b');
                        arrayList.add(sb3.toString());
                    }
                } else {
                    i4++;
                }
            }
        }
        long j2 = f1171m.get(this);
        return this.f1177g + '@' + DebugStringsKt.b(this) + "[Pool Size {core = " + this.f1174d + ", max = " + this.f1175e + "}, Worker States {CPU = " + i2 + ", blocking = " + i3 + ", parked = " + i4 + ", dormant = " + i5 + ", terminated = " + i6 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.f1178h.c() + ", global blocking queue size = " + this.f1179i.c() + ", Control State {created workers= " + ((int) (2097151 & j2)) + ", blocking tasks = " + ((int) ((4398044413952L & j2) >> 21)) + ", CPUs acquired = " + (this.f1174d - ((int) ((9223367638808264704L & j2) >> 42))) + "}]";
    }

    public final boolean w(Worker worker) {
        long j2;
        int h2;
        if (worker.i() != f1173o) {
            return false;
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1170l;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            h2 = worker.h();
            worker.r(this.f1180j.b((int) (2097151 & j2)));
        } while (!f1170l.compareAndSet(this, j2, ((2097152 + j2) & (-2097152)) | h2));
        return true;
    }

    public final void x(Worker worker, int i2, int i3) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1170l;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            int i4 = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & (-2097152);
            if (i4 == i2) {
                if (i3 == 0) {
                    i4 = p(worker);
                } else {
                    i4 = i3;
                }
            }
            if (i4 >= 0 && f1170l.compareAndSet(this, j2, j3 | i4)) {
                return;
            }
        }
    }

    public final void z(Task task) {
        try {
            task.run();
        } finally {
            try {
            } finally {
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class Worker extends Thread {

        /* renamed from: l  reason: collision with root package name */
        private static final AtomicIntegerFieldUpdater f1182l = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");

        /* renamed from: d  reason: collision with root package name */
        public final WorkQueue f1183d;

        /* renamed from: e  reason: collision with root package name */
        private final Ref$ObjectRef<Task> f1184e;

        /* renamed from: f  reason: collision with root package name */
        public WorkerState f1185f;

        /* renamed from: g  reason: collision with root package name */
        private long f1186g;

        /* renamed from: h  reason: collision with root package name */
        private long f1187h;

        /* renamed from: i  reason: collision with root package name */
        private int f1188i;
        private volatile int indexInArray;

        /* renamed from: j  reason: collision with root package name */
        public boolean f1189j;
        private volatile Object nextParkedWorker;
        private volatile int workerCtl;

        private Worker() {
            setDaemon(true);
            this.f1183d = new WorkQueue();
            this.f1184e = new Ref$ObjectRef<>();
            this.f1185f = WorkerState.DORMANT;
            this.nextParkedWorker = CoroutineScheduler.f1173o;
            this.f1188i = Random.f889d.b();
        }

        private final void b(int i2) {
            if (i2 == 0) {
                return;
            }
            CoroutineScheduler.f1171m.addAndGet(CoroutineScheduler.this, -2097152L);
            if (this.f1185f != WorkerState.TERMINATED) {
                this.f1185f = WorkerState.DORMANT;
            }
        }

        private final void c(int i2) {
            if (i2 != 0 && u(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.E();
            }
        }

        private final void d(Task task) {
            int b2 = task.f1207e.b();
            k(b2);
            c(b2);
            CoroutineScheduler.this.z(task);
            b(b2);
        }

        private final Task e(boolean z2) {
            boolean z3;
            Task o2;
            Task o3;
            if (z2) {
                if (m(CoroutineScheduler.this.f1174d * 2) == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3 && (o3 = o()) != null) {
                    return o3;
                }
                Task g2 = this.f1183d.g();
                if (g2 != null) {
                    return g2;
                }
                if (!z3 && (o2 = o()) != null) {
                    return o2;
                }
            } else {
                Task o4 = o();
                if (o4 != null) {
                    return o4;
                }
            }
            return v(3);
        }

        private final Task f() {
            Task h2 = this.f1183d.h();
            if (h2 == null) {
                Task d2 = CoroutineScheduler.this.f1179i.d();
                if (d2 == null) {
                    return v(1);
                }
                return d2;
            }
            return h2;
        }

        public static final AtomicIntegerFieldUpdater j() {
            return f1182l;
        }

        private final void k(int i2) {
            this.f1186g = 0L;
            if (this.f1185f == WorkerState.PARKING) {
                this.f1185f = WorkerState.BLOCKING;
            }
        }

        private final boolean l() {
            return this.nextParkedWorker != CoroutineScheduler.f1173o;
        }

        private final void n() {
            if (this.f1186g == 0) {
                this.f1186g = System.nanoTime() + CoroutineScheduler.this.f1176f;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.f1176f);
            if (System.nanoTime() - this.f1186g >= 0) {
                this.f1186g = 0L;
                w();
            }
        }

        private final Task o() {
            if (m(2) == 0) {
                Task d2 = CoroutineScheduler.this.f1178h.d();
                if (d2 != null) {
                    return d2;
                }
                return CoroutineScheduler.this.f1179i.d();
            }
            Task d3 = CoroutineScheduler.this.f1179i.d();
            if (d3 != null) {
                return d3;
            }
            return CoroutineScheduler.this.f1178h.d();
        }

        private final void p() {
            loop0: while (true) {
                boolean z2 = false;
                while (!CoroutineScheduler.this.isTerminated() && this.f1185f != WorkerState.TERMINATED) {
                    Task g2 = g(this.f1189j);
                    if (g2 != null) {
                        this.f1187h = 0L;
                        d(g2);
                    } else {
                        this.f1189j = false;
                        if (this.f1187h != 0) {
                            if (!z2) {
                                z2 = true;
                            } else {
                                u(WorkerState.PARKING);
                                Thread.interrupted();
                                LockSupport.parkNanos(this.f1187h);
                                this.f1187h = 0L;
                            }
                        } else {
                            t();
                        }
                    }
                }
            }
            u(WorkerState.TERMINATED);
        }

        private final boolean s() {
            boolean z2;
            if (this.f1185f == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            AtomicLongFieldUpdater atomicLongFieldUpdater = CoroutineScheduler.f1171m;
            while (true) {
                long j2 = atomicLongFieldUpdater.get(coroutineScheduler);
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    z2 = false;
                    break;
                }
                if (CoroutineScheduler.f1171m.compareAndSet(coroutineScheduler, j2, j2 - 4398046511104L)) {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                this.f1185f = WorkerState.CPU_ACQUIRED;
                return true;
            }
            return false;
        }

        private final void t() {
            if (!l()) {
                CoroutineScheduler.this.w(this);
                return;
            }
            f1182l.set(this, -1);
            while (l() && f1182l.get(this) == -1 && !CoroutineScheduler.this.isTerminated() && this.f1185f != WorkerState.TERMINATED) {
                u(WorkerState.PARKING);
                Thread.interrupted();
                n();
            }
        }

        private final Task v(int i2) {
            int i3 = (int) (CoroutineScheduler.f1171m.get(CoroutineScheduler.this) & 2097151);
            if (i3 < 2) {
                return null;
            }
            int m2 = m(i3);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j2 = Long.MAX_VALUE;
            for (int i4 = 0; i4 < i3; i4++) {
                m2++;
                if (m2 > i3) {
                    m2 = 1;
                }
                Worker b2 = coroutineScheduler.f1180j.b(m2);
                if (b2 != null && b2 != this) {
                    long n2 = b2.f1183d.n(i2, this.f1184e);
                    if (n2 == -1) {
                        Ref$ObjectRef<Task> ref$ObjectRef = this.f1184e;
                        Task task = ref$ObjectRef.f884d;
                        ref$ObjectRef.f884d = null;
                        return task;
                    } else if (n2 > 0) {
                        j2 = Math.min(j2, n2);
                    }
                }
            }
            if (j2 == Long.MAX_VALUE) {
                j2 = 0;
            }
            this.f1187h = j2;
            return null;
        }

        private final void w() {
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            synchronized (coroutineScheduler.f1180j) {
                if (coroutineScheduler.isTerminated()) {
                    return;
                }
                if (((int) (CoroutineScheduler.f1171m.get(coroutineScheduler) & 2097151)) <= coroutineScheduler.f1174d) {
                    return;
                }
                if (!f1182l.compareAndSet(this, -1, 1)) {
                    return;
                }
                int i2 = this.indexInArray;
                q(0);
                coroutineScheduler.x(this, i2, 0);
                int andDecrement = (int) (2097151 & CoroutineScheduler.f1171m.getAndDecrement(coroutineScheduler));
                if (andDecrement != i2) {
                    Worker b2 = coroutineScheduler.f1180j.b(andDecrement);
                    Intrinsics.c(b2);
                    Worker worker = b2;
                    coroutineScheduler.f1180j.c(i2, worker);
                    worker.q(i2);
                    coroutineScheduler.x(worker, andDecrement, i2);
                }
                coroutineScheduler.f1180j.c(andDecrement, null);
                Unit unit = Unit.f763a;
                this.f1185f = WorkerState.TERMINATED;
            }
        }

        public final Task g(boolean z2) {
            if (s()) {
                return e(z2);
            }
            return f();
        }

        public final int h() {
            return this.indexInArray;
        }

        public final Object i() {
            return this.nextParkedWorker;
        }

        public final int m(int i2) {
            int i3 = this.f1188i;
            int i4 = i3 ^ (i3 << 13);
            int i5 = i4 ^ (i4 >> 17);
            int i6 = i5 ^ (i5 << 5);
            this.f1188i = i6;
            int i7 = i2 - 1;
            if ((i7 & i2) == 0) {
                return i6 & i7;
            }
            return (i6 & Integer.MAX_VALUE) % i2;
        }

        public final void q(int i2) {
            String valueOf;
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.f1177g);
            sb.append("-worker-");
            if (i2 == 0) {
                valueOf = "TERMINATED";
            } else {
                valueOf = String.valueOf(i2);
            }
            sb.append(valueOf);
            setName(sb.toString());
            this.indexInArray = i2;
        }

        public final void r(Object obj) {
            this.nextParkedWorker = obj;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            p();
        }

        public final boolean u(WorkerState workerState) {
            boolean z2;
            WorkerState workerState2 = this.f1185f;
            if (workerState2 == WorkerState.CPU_ACQUIRED) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                CoroutineScheduler.f1171m.addAndGet(CoroutineScheduler.this, 4398046511104L);
            }
            if (workerState2 != workerState) {
                this.f1185f = workerState;
            }
            return z2;
        }

        public Worker(CoroutineScheduler coroutineScheduler, int i2) {
            this();
            q(i2);
        }
    }
}
