package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http2Connection implements Closeable {
    private static final ExecutorService C = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.F("OkHttp Http2Connection", true));
    final ReaderRunnable A;
    final Set<Integer> B;

    /* renamed from: d  reason: collision with root package name */
    final boolean f2275d;

    /* renamed from: e  reason: collision with root package name */
    final Listener f2276e;

    /* renamed from: g  reason: collision with root package name */
    final String f2278g;

    /* renamed from: h  reason: collision with root package name */
    int f2279h;

    /* renamed from: i  reason: collision with root package name */
    int f2280i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2281j;

    /* renamed from: k  reason: collision with root package name */
    private final ScheduledExecutorService f2282k;

    /* renamed from: l  reason: collision with root package name */
    private final ExecutorService f2283l;

    /* renamed from: m  reason: collision with root package name */
    final PushObserver f2284m;

    /* renamed from: v  reason: collision with root package name */
    long f2293v;

    /* renamed from: x  reason: collision with root package name */
    final Settings f2295x;

    /* renamed from: y  reason: collision with root package name */
    final Socket f2296y;

    /* renamed from: z  reason: collision with root package name */
    final Http2Writer f2297z;

    /* renamed from: f  reason: collision with root package name */
    final Map<Integer, Http2Stream> f2277f = new LinkedHashMap();

    /* renamed from: n  reason: collision with root package name */
    private long f2285n = 0;

    /* renamed from: o  reason: collision with root package name */
    private long f2286o = 0;

    /* renamed from: p  reason: collision with root package name */
    private long f2287p = 0;

    /* renamed from: q  reason: collision with root package name */
    private long f2288q = 0;

    /* renamed from: r  reason: collision with root package name */
    private long f2289r = 0;

    /* renamed from: s  reason: collision with root package name */
    private long f2290s = 0;

    /* renamed from: t  reason: collision with root package name */
    private long f2291t = 0;

    /* renamed from: u  reason: collision with root package name */
    long f2292u = 0;

    /* renamed from: w  reason: collision with root package name */
    Settings f2294w = new Settings();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Socket f2320a;

        /* renamed from: b  reason: collision with root package name */
        String f2321b;

        /* renamed from: c  reason: collision with root package name */
        BufferedSource f2322c;

        /* renamed from: d  reason: collision with root package name */
        BufferedSink f2323d;

        /* renamed from: e  reason: collision with root package name */
        Listener f2324e = Listener.f2329a;

        /* renamed from: f  reason: collision with root package name */
        PushObserver f2325f = PushObserver.f2389a;

        /* renamed from: g  reason: collision with root package name */
        boolean f2326g;

        /* renamed from: h  reason: collision with root package name */
        int f2327h;

        public Builder(boolean z2) {
            this.f2326g = z2;
        }

        public Http2Connection a() {
            return new Http2Connection(this);
        }

        public Builder b(Listener listener) {
            this.f2324e = listener;
            return this;
        }

        public Builder c(int i2) {
            this.f2327h = i2;
            return this;
        }

        public Builder d(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.f2320a = socket;
            this.f2321b = str;
            this.f2322c = bufferedSource;
            this.f2323d = bufferedSink;
            return this;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    final class IntervalPingRunnable extends NamedRunnable {
        IntervalPingRunnable() {
            super("OkHttp %s ping", Http2Connection.this.f2278g);
        }

        @Override // okhttp3.internal.NamedRunnable
        public void k() {
            boolean z2;
            synchronized (Http2Connection.this) {
                if (Http2Connection.this.f2286o < Http2Connection.this.f2285n) {
                    z2 = true;
                } else {
                    Http2Connection.m(Http2Connection.this);
                    z2 = false;
                }
            }
            if (z2) {
                Http2Connection.this.B();
            } else {
                Http2Connection.this.c0(false, 1, 0);
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class Listener {

        /* renamed from: a  reason: collision with root package name */
        public static final Listener f2329a = new Listener() { // from class: okhttp3.internal.http2.Http2Connection.Listener.1
            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void b(Http2Stream http2Stream) {
                http2Stream.f(ErrorCode.REFUSED_STREAM);
            }
        };

        public void a(Http2Connection http2Connection) {
        }

        public abstract void b(Http2Stream http2Stream);
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    final class PingRunnable extends NamedRunnable {

        /* renamed from: e  reason: collision with root package name */
        final boolean f2330e;

        /* renamed from: f  reason: collision with root package name */
        final int f2331f;

        /* renamed from: g  reason: collision with root package name */
        final int f2332g;

        PingRunnable(boolean z2, int i2, int i3) {
            super("OkHttp %s ping %08x%08x", Http2Connection.this.f2278g, Integer.valueOf(i2), Integer.valueOf(i3));
            this.f2330e = z2;
            this.f2331f = i2;
            this.f2332g = i3;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void k() {
            Http2Connection.this.c0(this.f2330e, this.f2331f, this.f2332g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class ReaderRunnable extends NamedRunnable implements Http2Reader.Handler {

        /* renamed from: e  reason: collision with root package name */
        final Http2Reader f2334e;

        ReaderRunnable(Http2Reader http2Reader) {
            super("OkHttp %s", Http2Connection.this.f2278g);
            this.f2334e = http2Reader;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void a() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void b(final boolean z2, final Settings settings) {
            try {
                Http2Connection.this.f2282k.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{Http2Connection.this.f2278g}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.2
                    @Override // okhttp3.internal.NamedRunnable
                    public void k() {
                        ReaderRunnable.this.l(z2, settings);
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void c(boolean z2, int i2, BufferedSource bufferedSource, int i3) {
            if (Http2Connection.this.U(i2)) {
                Http2Connection.this.O(i2, bufferedSource, i3, z2);
                return;
            }
            Http2Stream D = Http2Connection.this.D(i2);
            if (D == null) {
                Http2Connection.this.e0(i2, ErrorCode.PROTOCOL_ERROR);
                long j2 = i3;
                Http2Connection.this.a0(j2);
                bufferedSource.skip(j2);
                return;
            }
            D.o(bufferedSource, i3);
            if (z2) {
                D.p();
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void d(boolean z2, int i2, int i3) {
            if (z2) {
                synchronized (Http2Connection.this) {
                    try {
                        if (i2 == 1) {
                            Http2Connection.e(Http2Connection.this);
                        } else if (i2 == 2) {
                            Http2Connection.w(Http2Connection.this);
                        } else if (i2 == 3) {
                            Http2Connection.x(Http2Connection.this);
                            Http2Connection.this.notifyAll();
                        }
                    } finally {
                    }
                }
                return;
            }
            try {
                Http2Connection.this.f2282k.execute(new PingRunnable(true, i2, i3));
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void e(int i2, int i3, int i4, boolean z2) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void f(int i2, ErrorCode errorCode) {
            if (Http2Connection.this.U(i2)) {
                Http2Connection.this.T(i2, errorCode);
                return;
            }
            Http2Stream V = Http2Connection.this.V(i2);
            if (V != null) {
                V.r(errorCode);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void g(boolean z2, int i2, int i3, List<Header> list) {
            if (Http2Connection.this.U(i2)) {
                Http2Connection.this.R(i2, list, z2);
                return;
            }
            synchronized (Http2Connection.this) {
                Http2Stream D = Http2Connection.this.D(i2);
                if (D == null) {
                    if (Http2Connection.this.f2281j) {
                        return;
                    }
                    Http2Connection http2Connection = Http2Connection.this;
                    if (i2 <= http2Connection.f2279h) {
                        return;
                    }
                    if (i2 % 2 == http2Connection.f2280i % 2) {
                        return;
                    }
                    final Http2Stream http2Stream = new Http2Stream(i2, Http2Connection.this, false, z2, Util.G(list));
                    Http2Connection http2Connection2 = Http2Connection.this;
                    http2Connection2.f2279h = i2;
                    http2Connection2.f2277f.put(Integer.valueOf(i2), http2Stream);
                    Http2Connection.C.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{Http2Connection.this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.1
                        @Override // okhttp3.internal.NamedRunnable
                        public void k() {
                            try {
                                Http2Connection.this.f2276e.b(http2Stream);
                            } catch (IOException e2) {
                                Platform l2 = Platform.l();
                                l2.s(4, "Http2Connection.Listener failure for " + Http2Connection.this.f2278g, e2);
                                try {
                                    http2Stream.f(ErrorCode.PROTOCOL_ERROR);
                                } catch (IOException unused) {
                                }
                            }
                        }
                    });
                    return;
                }
                D.q(list);
                if (z2) {
                    D.p();
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void h(int i2, long j2) {
            if (i2 == 0) {
                synchronized (Http2Connection.this) {
                    Http2Connection http2Connection = Http2Connection.this;
                    http2Connection.f2293v += j2;
                    http2Connection.notifyAll();
                }
                return;
            }
            Http2Stream D = Http2Connection.this.D(i2);
            if (D != null) {
                synchronized (D) {
                    D.c(j2);
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void i(int i2, int i3, List<Header> list) {
            Http2Connection.this.S(i3, list);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void j(int i2, ErrorCode errorCode, ByteString byteString) {
            Http2Stream[] http2StreamArr;
            byteString.u();
            synchronized (Http2Connection.this) {
                http2StreamArr = (Http2Stream[]) Http2Connection.this.f2277f.values().toArray(new Http2Stream[Http2Connection.this.f2277f.size()]);
                Http2Connection.this.f2281j = true;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.i() > i2 && http2Stream.l()) {
                    http2Stream.r(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.V(http2Stream.i());
                }
            }
        }

        @Override // okhttp3.internal.NamedRunnable
        protected void k() {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    this.f2334e.e(this);
                    while (this.f2334e.c(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                    try {
                        try {
                            Http2Connection.this.z(errorCode, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                            Http2Connection.this.z(errorCode3, errorCode3);
                            Util.f(this.f2334e);
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            Http2Connection.this.z(errorCode, errorCode2);
                        } catch (IOException unused2) {
                        }
                        Util.f(this.f2334e);
                        throw th;
                    }
                } catch (IOException unused3) {
                }
            } catch (IOException unused4) {
                errorCode = errorCode2;
            } catch (Throwable th2) {
                th = th2;
                errorCode = errorCode2;
                Http2Connection.this.z(errorCode, errorCode2);
                Util.f(this.f2334e);
                throw th;
            }
            Util.f(this.f2334e);
        }

        void l(boolean z2, Settings settings) {
            Http2Stream[] http2StreamArr;
            long j2;
            synchronized (Http2Connection.this.f2297z) {
                synchronized (Http2Connection.this) {
                    int d2 = Http2Connection.this.f2295x.d();
                    if (z2) {
                        Http2Connection.this.f2295x.a();
                    }
                    Http2Connection.this.f2295x.h(settings);
                    int d3 = Http2Connection.this.f2295x.d();
                    http2StreamArr = null;
                    if (d3 != -1 && d3 != d2) {
                        j2 = d3 - d2;
                        if (!Http2Connection.this.f2277f.isEmpty()) {
                            http2StreamArr = (Http2Stream[]) Http2Connection.this.f2277f.values().toArray(new Http2Stream[Http2Connection.this.f2277f.size()]);
                        }
                    } else {
                        j2 = 0;
                    }
                }
                try {
                    Http2Connection http2Connection = Http2Connection.this;
                    http2Connection.f2297z.b(http2Connection.f2295x);
                } catch (IOException unused) {
                    Http2Connection.this.B();
                }
            }
            if (http2StreamArr != null) {
                for (Http2Stream http2Stream : http2StreamArr) {
                    synchronized (http2Stream) {
                        http2Stream.c(j2);
                    }
                }
            }
            Http2Connection.C.execute(new NamedRunnable("OkHttp %s settings", Http2Connection.this.f2278g) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.3
                @Override // okhttp3.internal.NamedRunnable
                public void k() {
                    Http2Connection http2Connection2 = Http2Connection.this;
                    http2Connection2.f2276e.a(http2Connection2);
                }
            });
        }
    }

    Http2Connection(Builder builder) {
        int i2;
        Settings settings = new Settings();
        this.f2295x = settings;
        this.B = new LinkedHashSet();
        this.f2284m = builder.f2325f;
        boolean z2 = builder.f2326g;
        this.f2275d = z2;
        this.f2276e = builder.f2324e;
        if (z2) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        this.f2280i = i2;
        if (z2) {
            this.f2280i = i2 + 2;
        }
        if (z2) {
            this.f2294w.i(7, 16777216);
        }
        String str = builder.f2321b;
        this.f2278g = str;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.F(Util.q("OkHttp %s Writer", str), false));
        this.f2282k = scheduledThreadPoolExecutor;
        if (builder.f2327h != 0) {
            IntervalPingRunnable intervalPingRunnable = new IntervalPingRunnable();
            int i3 = builder.f2327h;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(intervalPingRunnable, i3, i3, TimeUnit.MILLISECONDS);
        }
        this.f2283l = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.F(Util.q("OkHttp %s Push Observer", str), true));
        settings.i(7, 65535);
        settings.i(5, 16384);
        this.f2293v = settings.d();
        this.f2296y = builder.f2320a;
        this.f2297z = new Http2Writer(builder.f2323d, z2);
        this.A = new ReaderRunnable(new Http2Reader(builder.f2322c, z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        try {
            ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
            z(errorCode, errorCode);
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[Catch: all -> 0x0073, TryCatch #1 {, blocks: (B:4:0x0006, B:24:0x004d, B:28:0x005c, B:25:0x0053, B:27:0x0057, B:32:0x0065, B:33:0x006c, B:5:0x0007, B:7:0x000e, B:8:0x0013, B:10:0x0017, B:12:0x0029, B:14:0x0031, B:19:0x003b, B:21:0x0041, B:22:0x004a, B:34:0x006d, B:35:0x0072), top: B:42:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private okhttp3.internal.http2.Http2Stream I(int r11, java.util.List<okhttp3.internal.http2.Header> r12, boolean r13) {
        /*
            r10 = this;
            r6 = r13 ^ 1
            r4 = 0
            okhttp3.internal.http2.Http2Writer r7 = r10.f2297z
            monitor-enter(r7)
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L76
            int r0 = r10.f2280i     // Catch: java.lang.Throwable -> L73
            r1 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 <= r1) goto L13
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch: java.lang.Throwable -> L73
            r10.X(r0)     // Catch: java.lang.Throwable -> L73
        L13:
            boolean r0 = r10.f2281j     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L6d
            int r8 = r10.f2280i     // Catch: java.lang.Throwable -> L73
            int r0 = r8 + 2
            r10.f2280i = r0     // Catch: java.lang.Throwable -> L73
            okhttp3.internal.http2.Http2Stream r9 = new okhttp3.internal.http2.Http2Stream     // Catch: java.lang.Throwable -> L73
            r5 = 0
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L73
            if (r13 == 0) goto L3a
            long r0 = r10.f2293v     // Catch: java.lang.Throwable -> L73
            r2 = 0
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L3a
            long r0 = r9.f2354b     // Catch: java.lang.Throwable -> L73
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 != 0) goto L38
            goto L3a
        L38:
            r13 = 0
            goto L3b
        L3a:
            r13 = 1
        L3b:
            boolean r0 = r9.m()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L4a
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r0 = r10.f2277f     // Catch: java.lang.Throwable -> L73
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L73
            r0.put(r1, r9)     // Catch: java.lang.Throwable -> L73
        L4a:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            if (r11 != 0) goto L53
            okhttp3.internal.http2.Http2Writer r0 = r10.f2297z     // Catch: java.lang.Throwable -> L76
            r0.B(r6, r8, r11, r12)     // Catch: java.lang.Throwable -> L76
            goto L5c
        L53:
            boolean r0 = r10.f2275d     // Catch: java.lang.Throwable -> L76
            if (r0 != 0) goto L65
            okhttp3.internal.http2.Http2Writer r0 = r10.f2297z     // Catch: java.lang.Throwable -> L76
            r0.w(r11, r8, r12)     // Catch: java.lang.Throwable -> L76
        L5c:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            if (r13 == 0) goto L64
            okhttp3.internal.http2.Http2Writer r11 = r10.f2297z
            r11.flush()
        L64:
            return r9
        L65:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L76
            java.lang.String r12 = "client streams shouldn't have associated stream IDs"
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L76
            throw r11     // Catch: java.lang.Throwable -> L76
        L6d:
            okhttp3.internal.http2.ConnectionShutdownException r11 = new okhttp3.internal.http2.ConnectionShutdownException     // Catch: java.lang.Throwable -> L73
            r11.<init>()     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L73
        L73:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L76
        L76:
            r11 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.I(int, java.util.List, boolean):okhttp3.internal.http2.Http2Stream");
    }

    private synchronized void Q(NamedRunnable namedRunnable) {
        if (!this.f2281j) {
            this.f2283l.execute(namedRunnable);
        }
    }

    static /* synthetic */ long e(Http2Connection http2Connection) {
        long j2 = http2Connection.f2286o;
        http2Connection.f2286o = 1 + j2;
        return j2;
    }

    static /* synthetic */ long m(Http2Connection http2Connection) {
        long j2 = http2Connection.f2285n;
        http2Connection.f2285n = 1 + j2;
        return j2;
    }

    static /* synthetic */ long w(Http2Connection http2Connection) {
        long j2 = http2Connection.f2288q;
        http2Connection.f2288q = 1 + j2;
        return j2;
    }

    static /* synthetic */ long x(Http2Connection http2Connection) {
        long j2 = http2Connection.f2290s;
        http2Connection.f2290s = 1 + j2;
        return j2;
    }

    synchronized Http2Stream D(int i2) {
        return this.f2277f.get(Integer.valueOf(i2));
    }

    public synchronized boolean E(long j2) {
        if (this.f2281j) {
            return false;
        }
        if (this.f2288q < this.f2287p) {
            if (j2 >= this.f2291t) {
                return false;
            }
        }
        return true;
    }

    public synchronized int G() {
        return this.f2295x.e(Integer.MAX_VALUE);
    }

    public Http2Stream J(List<Header> list, boolean z2) {
        return I(0, list, z2);
    }

    void O(final int i2, BufferedSource bufferedSource, final int i3, final boolean z2) {
        final Buffer buffer = new Buffer();
        long j2 = i3;
        bufferedSource.F(j2);
        bufferedSource.A(buffer, j2);
        if (buffer.size() == j2) {
            Q(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.6
                @Override // okhttp3.internal.NamedRunnable
                public void k() {
                    try {
                        boolean d2 = Http2Connection.this.f2284m.d(i2, buffer, i3, z2);
                        if (d2) {
                            Http2Connection.this.f2297z.x(i2, ErrorCode.CANCEL);
                        }
                        if (d2 || z2) {
                            synchronized (Http2Connection.this) {
                                Http2Connection.this.B.remove(Integer.valueOf(i2));
                            }
                        }
                    } catch (IOException unused) {
                    }
                }
            });
            return;
        }
        throw new IOException(buffer.size() + " != " + i3);
    }

    void R(final int i2, final List<Header> list, final boolean z2) {
        try {
            Q(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.5
                @Override // okhttp3.internal.NamedRunnable
                public void k() {
                    boolean b2 = Http2Connection.this.f2284m.b(i2, list, z2);
                    if (b2) {
                        try {
                            Http2Connection.this.f2297z.x(i2, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (b2 || z2) {
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.B.remove(Integer.valueOf(i2));
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    void S(final int i2, final List<Header> list) {
        synchronized (this) {
            if (this.B.contains(Integer.valueOf(i2))) {
                e0(i2, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.B.add(Integer.valueOf(i2));
            try {
                Q(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.4
                    @Override // okhttp3.internal.NamedRunnable
                    public void k() {
                        if (Http2Connection.this.f2284m.a(i2, list)) {
                            try {
                                Http2Connection.this.f2297z.x(i2, ErrorCode.CANCEL);
                                synchronized (Http2Connection.this) {
                                    Http2Connection.this.B.remove(Integer.valueOf(i2));
                                }
                            } catch (IOException unused) {
                            }
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void T(final int i2, final ErrorCode errorCode) {
        Q(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.7
            @Override // okhttp3.internal.NamedRunnable
            public void k() {
                Http2Connection.this.f2284m.c(i2, errorCode);
                synchronized (Http2Connection.this) {
                    Http2Connection.this.B.remove(Integer.valueOf(i2));
                }
            }
        });
    }

    boolean U(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Http2Stream V(int i2) {
        Http2Stream remove;
        remove = this.f2277f.remove(Integer.valueOf(i2));
        notifyAll();
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void W() {
        synchronized (this) {
            long j2 = this.f2288q;
            long j3 = this.f2287p;
            if (j2 < j3) {
                return;
            }
            this.f2287p = j3 + 1;
            this.f2291t = System.nanoTime() + 1000000000;
            try {
                this.f2282k.execute(new NamedRunnable("OkHttp %s ping", this.f2278g) { // from class: okhttp3.internal.http2.Http2Connection.3
                    @Override // okhttp3.internal.NamedRunnable
                    public void k() {
                        Http2Connection.this.c0(false, 2, 0);
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void X(ErrorCode errorCode) {
        synchronized (this.f2297z) {
            synchronized (this) {
                if (this.f2281j) {
                    return;
                }
                this.f2281j = true;
                this.f2297z.n(this.f2279h, errorCode, Util.f2081a);
            }
        }
    }

    public void Y() {
        Z(true);
    }

    void Z(boolean z2) {
        if (z2) {
            this.f2297z.c();
            this.f2297z.z(this.f2294w);
            int d2 = this.f2294w.d();
            if (d2 != 65535) {
                this.f2297z.D(0, d2 - 65535);
            }
        }
        new Thread(this.A).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a0(long j2) {
        long j3 = this.f2292u + j2;
        this.f2292u = j3;
        if (j3 >= this.f2294w.d() / 2) {
            f0(0, this.f2292u);
            this.f2292u = 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        throw new java.io.IOException("stream closed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r3 = java.lang.Math.min((int) java.lang.Math.min(r12, r3), r8.f2297z.p());
        r6 = r3;
        r8.f2293v -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b0(int r9, boolean r10, okio.Buffer r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 != 0) goto Ld
            okhttp3.internal.http2.Http2Writer r12 = r8.f2297z
            r12.e(r10, r9, r11, r0)
            return
        Ld:
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L67
            monitor-enter(r8)
        L12:
            long r3 = r8.f2293v     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 > 0) goto L30
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r3 = r8.f2277f     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            boolean r3 = r3.containsKey(r4)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            if (r3 == 0) goto L28
            r8.wait()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            goto L12
        L28:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            throw r9     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
        L30:
            long r3 = java.lang.Math.min(r12, r3)     // Catch: java.lang.Throwable -> L56
            int r4 = (int) r3     // Catch: java.lang.Throwable -> L56
            okhttp3.internal.http2.Http2Writer r3 = r8.f2297z     // Catch: java.lang.Throwable -> L56
            int r3 = r3.p()     // Catch: java.lang.Throwable -> L56
            int r3 = java.lang.Math.min(r4, r3)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.f2293v     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r3     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.f2293v = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            okhttp3.internal.http2.Http2Writer r4 = r8.f2297z
            if (r10 == 0) goto L51
            int r5 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = 0
        L52:
            r4.e(r5, r9, r11, r3)
            goto Ld
        L56:
            r9 = move-exception
            goto L65
        L58:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L56
            r9.interrupt()     // Catch: java.lang.Throwable -> L56
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L65:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.b0(int, boolean, okio.Buffer, long):void");
    }

    void c0(boolean z2, int i2, int i3) {
        try {
            this.f2297z.r(z2, i2, i3);
        } catch (IOException unused) {
            B();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        z(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d0(int i2, ErrorCode errorCode) {
        this.f2297z.x(i2, errorCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e0(final int i2, final ErrorCode errorCode) {
        try {
            this.f2282k.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.1
                @Override // okhttp3.internal.NamedRunnable
                public void k() {
                    try {
                        Http2Connection.this.d0(i2, errorCode);
                    } catch (IOException unused) {
                        Http2Connection.this.B();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f0(final int i2, final long j2) {
        try {
            this.f2282k.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.f2278g, Integer.valueOf(i2)}) { // from class: okhttp3.internal.http2.Http2Connection.2
                @Override // okhttp3.internal.NamedRunnable
                public void k() {
                    try {
                        Http2Connection.this.f2297z.D(i2, j2);
                    } catch (IOException unused) {
                        Http2Connection.this.B();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    public void flush() {
        this.f2297z.flush();
    }

    void z(ErrorCode errorCode, ErrorCode errorCode2) {
        Http2Stream[] http2StreamArr = null;
        try {
            X(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (!this.f2277f.isEmpty()) {
                http2StreamArr = (Http2Stream[]) this.f2277f.values().toArray(new Http2Stream[this.f2277f.size()]);
                this.f2277f.clear();
            }
        }
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.f(errorCode2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        try {
            this.f2297z.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.f2296y.close();
        } catch (IOException e5) {
            e = e5;
        }
        this.f2282k.shutdown();
        this.f2283l.shutdown();
        if (e == null) {
            return;
        }
        throw e;
    }
}
