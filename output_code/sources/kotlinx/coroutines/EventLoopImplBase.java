package kotlinx.coroutines;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f967i = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f968j = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f969k = AtomicIntegerFieldUpdater.newUpdater(EventLoopImplBase.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class DelayedResumeTask extends DelayedTask {

        /* renamed from: f  reason: collision with root package name */
        private final CancellableContinuation<Unit> f970f;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(long j2, CancellableContinuation<? super Unit> cancellableContinuation) {
            super(j2);
            this.f970f = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f970f.d(EventLoopImplBase.this, Unit.f763a);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.f970f;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;

        /* renamed from: d  reason: collision with root package name */
        public long f972d;

        /* renamed from: e  reason: collision with root package name */
        private int f973e = -1;

        public DelayedTask(long j2) {
            this.f972d = j2;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void a() {
            Symbol symbol;
            DelayedTaskQueue delayedTaskQueue;
            Symbol symbol2;
            synchronized (this) {
                Object obj = this._heap;
                symbol = EventLoop_commonKt.f975a;
                if (obj == symbol) {
                    return;
                }
                if (obj instanceof DelayedTaskQueue) {
                    delayedTaskQueue = (DelayedTaskQueue) obj;
                } else {
                    delayedTaskQueue = null;
                }
                if (delayedTaskQueue != null) {
                    delayedTaskQueue.g(this);
                }
                symbol2 = EventLoop_commonKt.f975a;
                this._heap = symbol2;
                Unit unit = Unit.f763a;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void b(ThreadSafeHeap<?> threadSafeHeap) {
            Symbol symbol;
            boolean z2;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.f975a;
            if (obj != symbol) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                this._heap = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap<?> c() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // java.lang.Comparable
        /* renamed from: e */
        public int compareTo(DelayedTask delayedTask) {
            long j2 = this.f972d - delayedTask.f972d;
            if (j2 > 0) {
                return 1;
            }
            return j2 < 0 ? -1 : 0;
        }

        public final int f(long j2, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoopImplBase) {
            Symbol symbol;
            synchronized (this) {
                Object obj = this._heap;
                symbol = EventLoop_commonKt.f975a;
                if (obj == symbol) {
                    return 2;
                }
                synchronized (delayedTaskQueue) {
                    DelayedTask b2 = delayedTaskQueue.b();
                    if (eventLoopImplBase.k0()) {
                        return 1;
                    }
                    if (b2 == null) {
                        delayedTaskQueue.f974c = j2;
                    } else {
                        long j3 = b2.f972d;
                        if (j3 - j2 < 0) {
                            j2 = j3;
                        }
                        if (j2 - delayedTaskQueue.f974c > 0) {
                            delayedTaskQueue.f974c = j2;
                        }
                    }
                    long j4 = this.f972d;
                    long j5 = delayedTaskQueue.f974c;
                    if (j4 - j5 < 0) {
                        this.f972d = j5;
                    }
                    delayedTaskQueue.a(this);
                    return 0;
                }
            }
        }

        public final boolean g(long j2) {
            return j2 - this.f972d >= 0;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.f973e;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i2) {
            this.f973e = i2;
        }

        public String toString() {
            return "Delayed[nanos=" + this.f972d + ']';
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {

        /* renamed from: c  reason: collision with root package name */
        public long f974c;

        public DelayedTaskQueue(long j2) {
            this.f974c = j2;
        }
    }

    private final void g0() {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f967i;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f967i;
                symbol = EventLoop_commonKt.f976b;
                if (a.a(atomicReferenceFieldUpdater2, this, (Object) null, symbol)) {
                    return;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                ((LockFreeTaskQueueCore) obj).d();
                return;
            } else {
                symbol2 = EventLoop_commonKt.f976b;
                if (obj == symbol2) {
                    return;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                Intrinsics.d(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                lockFreeTaskQueueCore.a((Runnable) obj);
                if (a.a(f967i, this, obj, lockFreeTaskQueueCore)) {
                    return;
                }
            }
        }
    }

    private final Runnable h0() {
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f967i;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                return null;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                Object j2 = lockFreeTaskQueueCore.j();
                if (j2 != LockFreeTaskQueueCore.f1132h) {
                    return (Runnable) j2;
                }
                a.a(f967i, this, obj, lockFreeTaskQueueCore.i());
            } else {
                symbol = EventLoop_commonKt.f976b;
                if (obj == symbol) {
                    return null;
                }
                if (a.a(f967i, this, obj, (Object) null)) {
                    Intrinsics.d(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    return (Runnable) obj;
                }
            }
        }
    }

    private final boolean j0(Runnable runnable) {
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f967i;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (k0()) {
                return false;
            }
            if (obj == null) {
                if (a.a(f967i, this, (Object) null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int a2 = lockFreeTaskQueueCore.a(runnable);
                if (a2 == 0) {
                    return true;
                }
                if (a2 != 1) {
                    if (a2 == 2) {
                        return false;
                    }
                } else {
                    a.a(f967i, this, obj, lockFreeTaskQueueCore.i());
                }
            } else {
                symbol = EventLoop_commonKt.f976b;
                if (obj == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                Intrinsics.d(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                lockFreeTaskQueueCore2.a((Runnable) obj);
                lockFreeTaskQueueCore2.a(runnable);
                if (a.a(f967i, this, obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean k0() {
        if (f969k.get(this) != 0) {
            return true;
        }
        return false;
    }

    private final void n0() {
        DelayedTask i2;
        AbstractTimeSourceKt.a();
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) f968j.get(this);
            if (delayedTaskQueue != null && (i2 = delayedTaskQueue.i()) != null) {
                d0(nanoTime, i2);
            } else {
                return;
            }
        }
    }

    private final int q0(long j2, DelayedTask delayedTask) {
        if (k0()) {
            return 1;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f968j;
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) atomicReferenceFieldUpdater.get(this);
        if (delayedTaskQueue == null) {
            a.a(atomicReferenceFieldUpdater, this, (Object) null, new DelayedTaskQueue(j2));
            Object obj = atomicReferenceFieldUpdater.get(this);
            Intrinsics.c(obj);
            delayedTaskQueue = (DelayedTaskQueue) obj;
        }
        return delayedTask.f(j2, delayedTaskQueue, this);
    }

    private final void r0(boolean z2) {
        f969k.set(this, z2 ? 1 : 0);
    }

    private final boolean s0(DelayedTask delayedTask) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) f968j.get(this);
        return (delayedTaskQueue != null ? delayedTaskQueue.e() : null) == delayedTask;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void Q(CoroutineContext coroutineContext, Runnable runnable) {
        i0(runnable);
    }

    @Override // kotlinx.coroutines.EventLoop
    protected long W() {
        DelayedTask e2;
        long b2;
        Symbol symbol;
        if (super.W() == 0) {
            return 0L;
        }
        Object obj = f967i.get(this);
        if (obj != null) {
            if (obj instanceof LockFreeTaskQueueCore) {
                if (!((LockFreeTaskQueueCore) obj).g()) {
                    return 0L;
                }
            } else {
                symbol = EventLoop_commonKt.f976b;
                if (obj != symbol) {
                    return 0L;
                }
                return Long.MAX_VALUE;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) f968j.get(this);
        if (delayedTaskQueue == null || (e2 = delayedTaskQueue.e()) == null) {
            return Long.MAX_VALUE;
        }
        long j2 = e2.f972d;
        AbstractTimeSourceKt.a();
        b2 = RangesKt___RangesKt.b(j2 - System.nanoTime(), 0L);
        return b2;
    }

    public void i0(Runnable runnable) {
        if (j0(runnable)) {
            e0();
        } else {
            DefaultExecutor.f952l.i0(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean l0() {
        Symbol symbol;
        if (!a0()) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) f968j.get(this);
        if (delayedTaskQueue != null && !delayedTaskQueue.d()) {
            return false;
        }
        Object obj = f967i.get(this);
        if (obj != null) {
            if (obj instanceof LockFreeTaskQueueCore) {
                return ((LockFreeTaskQueueCore) obj).g();
            }
            symbol = EventLoop_commonKt.f976b;
            if (obj != symbol) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long m0() {
        /*
            r9 = this;
            boolean r0 = r9.b0()
            r1 = 0
            if (r0 == 0) goto L9
            return r1
        L9:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.EventLoopImplBase.f968j
            java.lang.Object r0 = r0.get(r9)
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r0 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r0
            if (r0 == 0) goto L49
            boolean r3 = r0.d()
            if (r3 != 0) goto L49
            kotlinx.coroutines.AbstractTimeSourceKt.a()
            long r3 = java.lang.System.nanoTime()
        L20:
            monitor-enter(r0)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r5 = r0.b()     // Catch: java.lang.Throwable -> L46
            r6 = 0
            if (r5 != 0) goto L2a
            monitor-exit(r0)
            goto L41
        L2a:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r5 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r5     // Catch: java.lang.Throwable -> L46
            boolean r7 = r5.g(r3)     // Catch: java.lang.Throwable -> L46
            r8 = 0
            if (r7 == 0) goto L38
            boolean r5 = r9.j0(r5)     // Catch: java.lang.Throwable -> L46
            goto L39
        L38:
            r5 = 0
        L39:
            if (r5 == 0) goto L40
            kotlinx.coroutines.internal.ThreadSafeHeapNode r5 = r0.h(r8)     // Catch: java.lang.Throwable -> L46
            r6 = r5
        L40:
            monitor-exit(r0)
        L41:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r6 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r6
            if (r6 != 0) goto L20
            goto L49
        L46:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L49:
            java.lang.Runnable r0 = r9.h0()
            if (r0 == 0) goto L53
            r0.run()
            return r1
        L53:
            long r0 = r9.W()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.m0():long");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void o0() {
        f967i.set(this, null);
        f968j.set(this, null);
    }

    @Override // kotlinx.coroutines.Delay
    public void p(long j2, CancellableContinuation<? super Unit> cancellableContinuation) {
        long c2 = EventLoop_commonKt.c(j2);
        if (c2 < 4611686018427387903L) {
            AbstractTimeSourceKt.a();
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(c2 + nanoTime, cancellableContinuation);
            p0(nanoTime, delayedResumeTask);
            CancellableContinuationKt.a(cancellableContinuation, delayedResumeTask);
        }
    }

    public final void p0(long j2, DelayedTask delayedTask) {
        int q0 = q0(j2, delayedTask);
        if (q0 != 0) {
            if (q0 != 1) {
                if (q0 != 2) {
                    throw new IllegalStateException("unexpected result".toString());
                }
                return;
            }
            d0(j2, delayedTask);
        } else if (s0(delayedTask)) {
            e0();
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.f1012a.b();
        r0(true);
        g0();
        do {
        } while (m0() <= 0);
        n0();
    }
}
