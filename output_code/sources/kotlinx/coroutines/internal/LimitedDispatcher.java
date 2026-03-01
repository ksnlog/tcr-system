package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LimitedDispatcher extends CoroutineDispatcher implements Delay {

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1114k = AtomicIntegerFieldUpdater.newUpdater(LimitedDispatcher.class, "runningWorkers");

    /* renamed from: f  reason: collision with root package name */
    private final CoroutineDispatcher f1115f;

    /* renamed from: g  reason: collision with root package name */
    private final int f1116g;

    /* renamed from: h  reason: collision with root package name */
    private final /* synthetic */ Delay f1117h;

    /* renamed from: i  reason: collision with root package name */
    private final LockFreeTaskQueue<Runnable> f1118i;

    /* renamed from: j  reason: collision with root package name */
    private final Object f1119j;
    private volatile int runningWorkers;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class Worker implements Runnable {

        /* renamed from: d  reason: collision with root package name */
        private Runnable f1120d;

        public Worker(Runnable runnable) {
            this.f1120d = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = 0;
            while (true) {
                try {
                    this.f1120d.run();
                } catch (Throwable th) {
                    CoroutineExceptionHandlerKt.a(EmptyCoroutineContext.f802d, th);
                }
                Runnable V = LimitedDispatcher.this.V();
                if (V == null) {
                    return;
                }
                this.f1120d = V;
                i2++;
                if (i2 >= 16 && LimitedDispatcher.this.f1115f.R(LimitedDispatcher.this)) {
                    LimitedDispatcher.this.f1115f.Q(LimitedDispatcher.this, this);
                    return;
                }
            }
        }
    }

    public LimitedDispatcher(CoroutineDispatcher coroutineDispatcher, int i2) {
        Delay delay;
        this.f1115f = coroutineDispatcher;
        this.f1116g = i2;
        if (coroutineDispatcher instanceof Delay) {
            delay = (Delay) coroutineDispatcher;
        } else {
            delay = null;
        }
        this.f1117h = delay == null ? DefaultExecutorKt.a() : delay;
        this.f1118i = new LockFreeTaskQueue<>(false);
        this.f1119j = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Runnable V() {
        while (true) {
            Runnable d2 = this.f1118i.d();
            if (d2 == null) {
                synchronized (this.f1119j) {
                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f1114k;
                    atomicIntegerFieldUpdater.decrementAndGet(this);
                    if (this.f1118i.c() == 0) {
                        return null;
                    }
                    atomicIntegerFieldUpdater.incrementAndGet(this);
                }
            } else {
                return d2;
            }
        }
    }

    private final boolean W() {
        synchronized (this.f1119j) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f1114k;
            if (atomicIntegerFieldUpdater.get(this) >= this.f1116g) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable V;
        this.f1118i.a(runnable);
        if (f1114k.get(this) < this.f1116g && W() && (V = V()) != null) {
            this.f1115f.Q(this, new Worker(V));
        }
    }

    @Override // kotlinx.coroutines.Delay
    public void p(long j2, CancellableContinuation<? super Unit> cancellableContinuation) {
        this.f1117h.p(j2, cancellableContinuation);
    }
}
