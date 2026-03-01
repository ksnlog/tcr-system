package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConnectionPool {

    /* renamed from: g  reason: collision with root package name */
    private static final Executor f1884g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.F("OkHttp ConnectionPool", true));

    /* renamed from: a  reason: collision with root package name */
    private final int f1885a;

    /* renamed from: b  reason: collision with root package name */
    private final long f1886b;

    /* renamed from: c  reason: collision with root package name */
    private final Runnable f1887c;

    /* renamed from: d  reason: collision with root package name */
    private final Deque<RealConnection> f1888d;

    /* renamed from: e  reason: collision with root package name */
    final RouteDatabase f1889e;

    /* renamed from: f  reason: collision with root package name */
    boolean f1890f;

    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    private int e(RealConnection realConnection, long j2) {
        List<Reference<StreamAllocation>> list = realConnection.f2133n;
        int i2 = 0;
        while (i2 < list.size()) {
            Reference<StreamAllocation> reference = list.get(i2);
            if (reference.get() != null) {
                i2++;
            } else {
                Platform.l().t("A connection to " + realConnection.p().a().l() + " was leaked. Did you forget to close a response body?", ((StreamAllocation.StreamAllocationReference) reference).f2162a);
                list.remove(i2);
                realConnection.f2130k = true;
                if (list.isEmpty()) {
                    realConnection.f2134o = j2 - this.f1886b;
                    return 0;
                }
            }
        }
        return list.size();
    }

    long a(long j2) {
        synchronized (this) {
            RealConnection realConnection = null;
            long j3 = Long.MIN_VALUE;
            int i2 = 0;
            int i3 = 0;
            for (RealConnection realConnection2 : this.f1888d) {
                if (e(realConnection2, j2) > 0) {
                    i3++;
                } else {
                    i2++;
                    long j4 = j2 - realConnection2.f2134o;
                    if (j4 > j3) {
                        realConnection = realConnection2;
                        j3 = j4;
                    }
                }
            }
            long j5 = this.f1886b;
            if (j3 < j5 && i2 <= this.f1885a) {
                if (i2 > 0) {
                    return j5 - j3;
                } else if (i3 > 0) {
                    return j5;
                } else {
                    this.f1890f = false;
                    return -1L;
                }
            }
            this.f1888d.remove(realConnection);
            Util.g(realConnection.q());
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(RealConnection realConnection) {
        if (!realConnection.f2130k && this.f1885a != 0) {
            notifyAll();
            return false;
        }
        this.f1888d.remove(realConnection);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Socket c(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.f1888d) {
            if (realConnection.l(address, null) && realConnection.n() && realConnection != streamAllocation.d()) {
                return streamAllocation.m(realConnection);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RealConnection d(Address address, StreamAllocation streamAllocation, Route route) {
        for (RealConnection realConnection : this.f1888d) {
            if (realConnection.l(address, route)) {
                streamAllocation.a(realConnection, true);
                return realConnection;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(RealConnection realConnection) {
        if (!this.f1890f) {
            this.f1890f = true;
            f1884g.execute(this.f1887c);
        }
        this.f1888d.add(realConnection);
    }

    public ConnectionPool(int i2, long j2, TimeUnit timeUnit) {
        this.f1887c = new Runnable() { // from class: okhttp3.ConnectionPool.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    long a2 = ConnectionPool.this.a(System.nanoTime());
                    if (a2 == -1) {
                        return;
                    }
                    if (a2 > 0) {
                        long j3 = a2 / 1000000;
                        long j4 = a2 - (1000000 * j3);
                        synchronized (ConnectionPool.this) {
                            try {
                                ConnectionPool.this.wait(j3, (int) j4);
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                }
            }
        };
        this.f1888d = new ArrayDeque();
        this.f1889e = new RouteDatabase();
        this.f1885a = i2;
        this.f1886b = timeUnit.toNanos(j2);
        if (j2 > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j2);
    }
}
