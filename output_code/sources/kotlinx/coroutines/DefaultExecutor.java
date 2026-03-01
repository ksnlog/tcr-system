package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.EventLoopImplBase;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* renamed from: l  reason: collision with root package name */
    public static final DefaultExecutor f952l;

    /* renamed from: m  reason: collision with root package name */
    private static final long f953m;

    static {
        Long l2;
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        f952l = defaultExecutor;
        EventLoop.Y(defaultExecutor, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l2 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l2 = 1000L;
        }
        f953m = timeUnit.toNanos(l2.longValue());
    }

    private DefaultExecutor() {
    }

    private final synchronized void t0() {
        if (!w0()) {
            return;
        }
        debugStatus = 3;
        o0();
        Intrinsics.d(this, "null cannot be cast to non-null type java.lang.Object");
        notifyAll();
    }

    private final synchronized Thread u0() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    private final boolean v0() {
        return debugStatus == 4;
    }

    private final boolean w0() {
        int i2 = debugStatus;
        return i2 == 2 || i2 == 3;
    }

    private final synchronized boolean x0() {
        if (w0()) {
            return false;
        }
        debugStatus = 1;
        Intrinsics.d(this, "null cannot be cast to non-null type java.lang.Object");
        notifyAll();
        return true;
    }

    private final void y0() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread c0() {
        Thread thread = _thread;
        return thread == null ? u0() : thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected void d0(long j2, EventLoopImplBase.DelayedTask delayedTask) {
        y0();
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public void i0(Runnable runnable) {
        if (v0()) {
            y0();
        }
        super.i0(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean l0;
        ThreadLocalEventLoop.f1012a.c(this);
        AbstractTimeSourceKt.a();
        try {
            if (!x0()) {
                if (!l0) {
                    return;
                }
                return;
            }
            long j2 = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long m0 = m0();
                if (m0 == Long.MAX_VALUE) {
                    AbstractTimeSourceKt.a();
                    long nanoTime = System.nanoTime();
                    if (j2 == Long.MAX_VALUE) {
                        j2 = f953m + nanoTime;
                    }
                    long j3 = j2 - nanoTime;
                    if (j3 <= 0) {
                        _thread = null;
                        t0();
                        AbstractTimeSourceKt.a();
                        if (!l0()) {
                            c0();
                            return;
                        }
                        return;
                    }
                    m0 = RangesKt___RangesKt.d(m0, j3);
                } else {
                    j2 = Long.MAX_VALUE;
                }
                if (m0 > 0) {
                    if (w0()) {
                        _thread = null;
                        t0();
                        AbstractTimeSourceKt.a();
                        if (!l0()) {
                            c0();
                            return;
                        }
                        return;
                    }
                    AbstractTimeSourceKt.a();
                    LockSupport.parkNanos(this, m0);
                }
            }
        } finally {
            _thread = null;
            t0();
            AbstractTimeSourceKt.a();
            if (!l0()) {
                c0();
            }
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
