package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AsyncTimeout extends Timeout {

    /* renamed from: i  reason: collision with root package name */
    public static final Companion f2432i = new Companion(null);

    /* renamed from: j  reason: collision with root package name */
    private static final ReentrantLock f2433j;

    /* renamed from: k  reason: collision with root package name */
    private static final Condition f2434k;

    /* renamed from: l  reason: collision with root package name */
    private static final long f2435l;

    /* renamed from: m  reason: collision with root package name */
    private static final long f2436m;

    /* renamed from: n  reason: collision with root package name */
    private static AsyncTimeout f2437n;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2438f;

    /* renamed from: g  reason: collision with root package name */
    private AsyncTimeout f2439g;

    /* renamed from: h  reason: collision with root package name */
    private long f2440h;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean d(AsyncTimeout asyncTimeout) {
            ReentrantLock f2 = AsyncTimeout.f2432i.f();
            f2.lock();
            try {
                if (!asyncTimeout.f2438f) {
                    return false;
                }
                asyncTimeout.f2438f = false;
                for (AsyncTimeout asyncTimeout2 = AsyncTimeout.f2437n; asyncTimeout2 != null; asyncTimeout2 = asyncTimeout2.f2439g) {
                    if (asyncTimeout2.f2439g == asyncTimeout) {
                        asyncTimeout2.f2439g = asyncTimeout.f2439g;
                        asyncTimeout.f2439g = null;
                        return false;
                    }
                }
                f2.unlock();
                return true;
            } finally {
                f2.unlock();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void g(AsyncTimeout asyncTimeout, long j2, boolean z2) {
            ReentrantLock f2 = AsyncTimeout.f2432i.f();
            f2.lock();
            try {
                if (!asyncTimeout.f2438f) {
                    asyncTimeout.f2438f = true;
                    if (AsyncTimeout.f2437n == null) {
                        AsyncTimeout.f2437n = new AsyncTimeout();
                        new Watchdog().start();
                    }
                    long nanoTime = System.nanoTime();
                    if (j2 != 0 && z2) {
                        asyncTimeout.f2440h = Math.min(j2, asyncTimeout.c() - nanoTime) + nanoTime;
                    } else if (j2 != 0) {
                        asyncTimeout.f2440h = j2 + nanoTime;
                    } else if (z2) {
                        asyncTimeout.f2440h = asyncTimeout.c();
                    } else {
                        throw new AssertionError();
                    }
                    long y2 = asyncTimeout.y(nanoTime);
                    AsyncTimeout asyncTimeout2 = AsyncTimeout.f2437n;
                    Intrinsics.c(asyncTimeout2);
                    while (asyncTimeout2.f2439g != null) {
                        AsyncTimeout asyncTimeout3 = asyncTimeout2.f2439g;
                        Intrinsics.c(asyncTimeout3);
                        if (y2 < asyncTimeout3.y(nanoTime)) {
                            break;
                        }
                        asyncTimeout2 = asyncTimeout2.f2439g;
                        Intrinsics.c(asyncTimeout2);
                    }
                    asyncTimeout.f2439g = asyncTimeout2.f2439g;
                    asyncTimeout2.f2439g = asyncTimeout;
                    if (asyncTimeout2 == AsyncTimeout.f2437n) {
                        AsyncTimeout.f2432i.e().signal();
                    }
                    Unit unit = Unit.f763a;
                    return;
                }
                throw new IllegalStateException("Unbalanced enter/exit".toString());
            } finally {
                f2.unlock();
            }
        }

        public final AsyncTimeout c() {
            AsyncTimeout asyncTimeout = AsyncTimeout.f2437n;
            Intrinsics.c(asyncTimeout);
            AsyncTimeout asyncTimeout2 = asyncTimeout.f2439g;
            if (asyncTimeout2 == null) {
                long nanoTime = System.nanoTime();
                e().await(AsyncTimeout.f2435l, TimeUnit.MILLISECONDS);
                AsyncTimeout asyncTimeout3 = AsyncTimeout.f2437n;
                Intrinsics.c(asyncTimeout3);
                if (asyncTimeout3.f2439g != null || System.nanoTime() - nanoTime < AsyncTimeout.f2436m) {
                    return null;
                }
                return AsyncTimeout.f2437n;
            }
            long y2 = asyncTimeout2.y(System.nanoTime());
            if (y2 > 0) {
                e().await(y2, TimeUnit.NANOSECONDS);
                return null;
            }
            AsyncTimeout asyncTimeout4 = AsyncTimeout.f2437n;
            Intrinsics.c(asyncTimeout4);
            asyncTimeout4.f2439g = asyncTimeout2.f2439g;
            asyncTimeout2.f2439g = null;
            return asyncTimeout2;
        }

        public final Condition e() {
            return AsyncTimeout.f2434k;
        }

        public final ReentrantLock f() {
            return AsyncTimeout.f2433j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ReentrantLock f2;
            AsyncTimeout c2;
            while (true) {
                try {
                    Companion companion = AsyncTimeout.f2432i;
                    f2 = companion.f();
                    f2.lock();
                    c2 = companion.c();
                } catch (InterruptedException unused) {
                }
                if (c2 == AsyncTimeout.f2437n) {
                    AsyncTimeout.f2437n = null;
                    f2.unlock();
                    return;
                }
                Unit unit = Unit.f763a;
                f2.unlock();
                if (c2 != null) {
                    c2.B();
                }
            }
        }
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f2433j = reentrantLock;
        Condition newCondition = reentrantLock.newCondition();
        Intrinsics.e(newCondition, "lock.newCondition()");
        f2434k = newCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f2435l = millis;
        f2436m = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long y(long j2) {
        return this.f2440h - j2;
    }

    public final Source A(final Source source) {
        Intrinsics.f(source, "source");
        return new Source() { // from class: okio.AsyncTimeout$source$1
            @Override // okio.Source
            public long A(Buffer sink, long j2) {
                Intrinsics.f(sink, "sink");
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.v();
                try {
                    long A = source2.A(sink, j2);
                    if (!asyncTimeout.w()) {
                        return A;
                    }
                    throw asyncTimeout.p(null);
                } catch (IOException e2) {
                    if (!asyncTimeout.w()) {
                        throw e2;
                    }
                    throw asyncTimeout.p(e2);
                } finally {
                    asyncTimeout.w();
                }
            }

            @Override // okio.Source
            /* renamed from: b */
            public AsyncTimeout d() {
                return AsyncTimeout.this;
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.v();
                try {
                    source2.close();
                    Unit unit = Unit.f763a;
                    if (!asyncTimeout.w()) {
                        return;
                    }
                    throw asyncTimeout.p(null);
                } catch (IOException e2) {
                    if (!asyncTimeout.w()) {
                        throw e2;
                    }
                    throw asyncTimeout.p(e2);
                } finally {
                    asyncTimeout.w();
                }
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ')';
            }
        };
    }

    protected void B() {
    }

    public final IOException p(IOException iOException) {
        return x(iOException);
    }

    public final void v() {
        long h2 = h();
        boolean e2 = e();
        if (h2 == 0 && !e2) {
            return;
        }
        f2432i.g(this, h2, e2);
    }

    public final boolean w() {
        return f2432i.d(this);
    }

    protected IOException x(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final Sink z(final Sink sink) {
        Intrinsics.f(sink, "sink");
        return new Sink() { // from class: okio.AsyncTimeout$sink$1
            @Override // okio.Sink
            /* renamed from: b */
            public AsyncTimeout d() {
                return AsyncTimeout.this;
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.v();
                try {
                    sink2.close();
                    Unit unit = Unit.f763a;
                    if (!asyncTimeout.w()) {
                        return;
                    }
                    throw asyncTimeout.p(null);
                } catch (IOException e2) {
                    if (!asyncTimeout.w()) {
                        throw e2;
                    }
                    throw asyncTimeout.p(e2);
                } finally {
                    asyncTimeout.w();
                }
            }

            @Override // okio.Sink
            public void f(Buffer source, long j2) {
                Intrinsics.f(source, "source");
                _UtilKt.b(source.size(), 0L, j2);
                while (true) {
                    long j3 = 0;
                    if (j2 > 0) {
                        Segment segment = source.f2445d;
                        Intrinsics.c(segment);
                        while (true) {
                            if (j3 >= 65536) {
                                break;
                            }
                            j3 += segment.f2489c - segment.f2488b;
                            if (j3 >= j2) {
                                j3 = j2;
                                break;
                            } else {
                                segment = segment.f2492f;
                                Intrinsics.c(segment);
                            }
                        }
                        AsyncTimeout asyncTimeout = AsyncTimeout.this;
                        Sink sink2 = sink;
                        asyncTimeout.v();
                        try {
                            sink2.f(source, j3);
                            Unit unit = Unit.f763a;
                            if (!asyncTimeout.w()) {
                                j2 -= j3;
                            } else {
                                throw asyncTimeout.p(null);
                            }
                        } catch (IOException e2) {
                            if (!asyncTimeout.w()) {
                                throw e2;
                            }
                            throw asyncTimeout.p(e2);
                        } finally {
                            asyncTimeout.w();
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.v();
                try {
                    sink2.flush();
                    Unit unit = Unit.f763a;
                    if (!asyncTimeout.w()) {
                        return;
                    }
                    throw asyncTimeout.p(null);
                } catch (IOException e2) {
                    if (!asyncTimeout.w()) {
                        throw e2;
                    }
                    throw asyncTimeout.p(e2);
                } finally {
                    asyncTimeout.w();
                }
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ')';
            }
        };
    }
}
