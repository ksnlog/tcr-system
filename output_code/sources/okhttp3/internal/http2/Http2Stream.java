package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http2Stream {

    /* renamed from: a  reason: collision with root package name */
    long f2353a = 0;

    /* renamed from: b  reason: collision with root package name */
    long f2354b;

    /* renamed from: c  reason: collision with root package name */
    final int f2355c;

    /* renamed from: d  reason: collision with root package name */
    final Http2Connection f2356d;

    /* renamed from: e  reason: collision with root package name */
    private final Deque<Headers> f2357e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2358f;

    /* renamed from: g  reason: collision with root package name */
    private final FramingSource f2359g;

    /* renamed from: h  reason: collision with root package name */
    final FramingSink f2360h;

    /* renamed from: i  reason: collision with root package name */
    final StreamTimeout f2361i;

    /* renamed from: j  reason: collision with root package name */
    final StreamTimeout f2362j;

    /* renamed from: k  reason: collision with root package name */
    ErrorCode f2363k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class FramingSink implements Sink {

        /* renamed from: d  reason: collision with root package name */
        private final Buffer f2364d = new Buffer();

        /* renamed from: e  reason: collision with root package name */
        boolean f2365e;

        /* renamed from: f  reason: collision with root package name */
        boolean f2366f;

        FramingSink() {
        }

        private void b(boolean z2) {
            Http2Stream http2Stream;
            long min;
            Http2Stream http2Stream2;
            boolean z3;
            synchronized (Http2Stream.this) {
                Http2Stream.this.f2362j.v();
                while (true) {
                    http2Stream = Http2Stream.this;
                    if (http2Stream.f2354b > 0 || this.f2366f || this.f2365e || http2Stream.f2363k != null) {
                        break;
                    }
                    http2Stream.t();
                }
                http2Stream.f2362j.C();
                Http2Stream.this.e();
                min = Math.min(Http2Stream.this.f2354b, this.f2364d.size());
                http2Stream2 = Http2Stream.this;
                http2Stream2.f2354b -= min;
            }
            http2Stream2.f2362j.v();
            try {
                Http2Stream http2Stream3 = Http2Stream.this;
                Http2Connection http2Connection = http2Stream3.f2356d;
                int i2 = http2Stream3.f2355c;
                if (z2 && min == this.f2364d.size()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                http2Connection.b0(i2, z3, this.f2364d, min);
            } finally {
                Http2Stream.this.f2362j.C();
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            synchronized (Http2Stream.this) {
                if (this.f2365e) {
                    return;
                }
                if (!Http2Stream.this.f2360h.f2366f) {
                    if (this.f2364d.size() > 0) {
                        while (this.f2364d.size() > 0) {
                            b(true);
                        }
                    } else {
                        Http2Stream http2Stream = Http2Stream.this;
                        http2Stream.f2356d.b0(http2Stream.f2355c, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.f2365e = true;
                }
                Http2Stream.this.f2356d.flush();
                Http2Stream.this.d();
            }
        }

        @Override // okio.Sink
        public Timeout d() {
            return Http2Stream.this.f2362j;
        }

        @Override // okio.Sink
        public void f(Buffer buffer, long j2) {
            this.f2364d.f(buffer, j2);
            while (this.f2364d.size() >= 16384) {
                b(false);
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            synchronized (Http2Stream.this) {
                Http2Stream.this.e();
            }
            while (this.f2364d.size() > 0) {
                b(false);
                Http2Stream.this.f2356d.flush();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class FramingSource implements Source {

        /* renamed from: d  reason: collision with root package name */
        private final Buffer f2368d = new Buffer();

        /* renamed from: e  reason: collision with root package name */
        private final Buffer f2369e = new Buffer();

        /* renamed from: f  reason: collision with root package name */
        private final long f2370f;

        /* renamed from: g  reason: collision with root package name */
        boolean f2371g;

        /* renamed from: h  reason: collision with root package name */
        boolean f2372h;

        FramingSource(long j2) {
            this.f2370f = j2;
        }

        private void c(long j2) {
            Http2Stream.this.f2356d.a0(j2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
            r12 = -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0086, code lost:
            r11.f2373i.f2361i.C();
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long A(okio.Buffer r12, long r13) {
            /*
                r11 = this;
                r0 = 0
                int r2 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
                if (r2 < 0) goto Lb2
            L6:
                okhttp3.internal.http2.Http2Stream r2 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r2)
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> Laf
                okhttp3.internal.http2.Http2Stream$StreamTimeout r3 = r3.f2361i     // Catch: java.lang.Throwable -> Laf
                r3.v()     // Catch: java.lang.Throwable -> Laf
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.ErrorCode r4 = r3.f2363k     // Catch: java.lang.Throwable -> L2c
                if (r4 == 0) goto L17
                goto L18
            L17:
                r4 = 0
            L18:
                boolean r5 = r11.f2371g     // Catch: java.lang.Throwable -> L2c
                if (r5 != 0) goto L9f
                java.util.Deque r3 = okhttp3.internal.http2.Http2Stream.a(r3)     // Catch: java.lang.Throwable -> L2c
                boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L2c
                if (r3 != 0) goto L2f
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Http2Stream.b(r3)     // Catch: java.lang.Throwable -> L2c
                goto L2f
            L2c:
                r12 = move-exception
                goto La7
            L2f:
                okio.Buffer r3 = r11.f2369e     // Catch: java.lang.Throwable -> L2c
                long r5 = r3.size()     // Catch: java.lang.Throwable -> L2c
                r7 = -1
                int r3 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r3 <= 0) goto L71
                okio.Buffer r3 = r11.f2369e     // Catch: java.lang.Throwable -> L2c
                long r5 = r3.size()     // Catch: java.lang.Throwable -> L2c
                long r13 = java.lang.Math.min(r13, r5)     // Catch: java.lang.Throwable -> L2c
                long r12 = r3.A(r12, r13)     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Http2Stream r14 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                long r5 = r14.f2353a     // Catch: java.lang.Throwable -> L2c
                long r5 = r5 + r12
                r14.f2353a = r5     // Catch: java.lang.Throwable -> L2c
                if (r4 != 0) goto L86
                okhttp3.internal.http2.Http2Connection r14 = r14.f2356d     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Settings r14 = r14.f2294w     // Catch: java.lang.Throwable -> L2c
                int r14 = r14.d()     // Catch: java.lang.Throwable -> L2c
                int r14 = r14 / 2
                long r9 = (long) r14     // Catch: java.lang.Throwable -> L2c
                int r14 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
                if (r14 < 0) goto L86
                okhttp3.internal.http2.Http2Stream r14 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Http2Connection r3 = r14.f2356d     // Catch: java.lang.Throwable -> L2c
                int r5 = r14.f2355c     // Catch: java.lang.Throwable -> L2c
                long r9 = r14.f2353a     // Catch: java.lang.Throwable -> L2c
                r3.f0(r5, r9)     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Http2Stream r14 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                r14.f2353a = r0     // Catch: java.lang.Throwable -> L2c
                goto L86
            L71:
                boolean r3 = r11.f2372h     // Catch: java.lang.Throwable -> L2c
                if (r3 != 0) goto L85
                if (r4 != 0) goto L85
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> L2c
                r3.t()     // Catch: java.lang.Throwable -> L2c
                okhttp3.internal.http2.Http2Stream r3 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> Laf
                okhttp3.internal.http2.Http2Stream$StreamTimeout r3 = r3.f2361i     // Catch: java.lang.Throwable -> Laf
                r3.C()     // Catch: java.lang.Throwable -> Laf
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Laf
                goto L6
            L85:
                r12 = r7
            L86:
                okhttp3.internal.http2.Http2Stream r14 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> Laf
                okhttp3.internal.http2.Http2Stream$StreamTimeout r14 = r14.f2361i     // Catch: java.lang.Throwable -> Laf
                r14.C()     // Catch: java.lang.Throwable -> Laf
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Laf
                int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
                if (r14 == 0) goto L96
                r11.c(r12)
                return r12
            L96:
                if (r4 != 0) goto L99
                return r7
            L99:
                okhttp3.internal.http2.StreamResetException r12 = new okhttp3.internal.http2.StreamResetException
                r12.<init>(r4)
                throw r12
            L9f:
                java.io.IOException r12 = new java.io.IOException     // Catch: java.lang.Throwable -> L2c
                java.lang.String r13 = "stream closed"
                r12.<init>(r13)     // Catch: java.lang.Throwable -> L2c
                throw r12     // Catch: java.lang.Throwable -> L2c
            La7:
                okhttp3.internal.http2.Http2Stream r13 = okhttp3.internal.http2.Http2Stream.this     // Catch: java.lang.Throwable -> Laf
                okhttp3.internal.http2.Http2Stream$StreamTimeout r13 = r13.f2361i     // Catch: java.lang.Throwable -> Laf
                r13.C()     // Catch: java.lang.Throwable -> Laf
                throw r12     // Catch: java.lang.Throwable -> Laf
            Laf:
                r12 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Laf
                throw r12
            Lb2:
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "byteCount < 0: "
                r0.append(r1)
                r0.append(r13)
                java.lang.String r13 = r0.toString()
                r12.<init>(r13)
                goto Lca
            Lc9:
                throw r12
            Lca:
                goto Lc9
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.A(okio.Buffer, long):long");
        }

        void b(BufferedSource bufferedSource, long j2) {
            boolean z2;
            boolean z3;
            boolean z4;
            long j3;
            while (j2 > 0) {
                synchronized (Http2Stream.this) {
                    z2 = this.f2372h;
                    z3 = true;
                    if (this.f2369e.size() + j2 > this.f2370f) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                }
                if (z4) {
                    bufferedSource.skip(j2);
                    Http2Stream.this.h(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z2) {
                    bufferedSource.skip(j2);
                    return;
                } else {
                    long A = bufferedSource.A(this.f2368d, j2);
                    if (A != -1) {
                        j2 -= A;
                        synchronized (Http2Stream.this) {
                            if (this.f2371g) {
                                j3 = this.f2368d.size();
                                this.f2368d.b();
                            } else {
                                if (this.f2369e.size() != 0) {
                                    z3 = false;
                                }
                                this.f2369e.V(this.f2368d);
                                if (z3) {
                                    Http2Stream.this.notifyAll();
                                }
                                j3 = 0;
                            }
                        }
                        if (j3 > 0) {
                            c(j3);
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long size;
            synchronized (Http2Stream.this) {
                this.f2371g = true;
                size = this.f2369e.size();
                this.f2369e.b();
                if (!Http2Stream.this.f2357e.isEmpty()) {
                    Http2Stream.this.getClass();
                }
                Http2Stream.this.notifyAll();
            }
            if (size > 0) {
                c(size);
            }
            Http2Stream.this.d();
        }

        @Override // okio.Source
        public Timeout d() {
            return Http2Stream.this.f2361i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override // okio.AsyncTimeout
        protected void B() {
            Http2Stream.this.h(ErrorCode.CANCEL);
            Http2Stream.this.f2356d.W();
        }

        public void C() {
            if (!w()) {
                return;
            }
            throw x(null);
        }

        @Override // okio.AsyncTimeout
        protected IOException x(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Stream(int i2, Http2Connection http2Connection, boolean z2, boolean z3, @Nullable Headers headers) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f2357e = arrayDeque;
        this.f2361i = new StreamTimeout();
        this.f2362j = new StreamTimeout();
        this.f2363k = null;
        if (http2Connection != null) {
            this.f2355c = i2;
            this.f2356d = http2Connection;
            this.f2354b = http2Connection.f2295x.d();
            FramingSource framingSource = new FramingSource(http2Connection.f2294w.d());
            this.f2359g = framingSource;
            FramingSink framingSink = new FramingSink();
            this.f2360h = framingSink;
            framingSource.f2372h = z3;
            framingSink.f2366f = z2;
            if (headers != null) {
                arrayDeque.add(headers);
            }
            if (l() && headers != null) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            if (!l() && headers == null) {
                throw new IllegalStateException("remotely-initiated streams should have headers");
            }
            return;
        }
        throw new NullPointerException("connection == null");
    }

    private boolean g(ErrorCode errorCode) {
        synchronized (this) {
            if (this.f2363k != null) {
                return false;
            }
            if (this.f2359g.f2372h && this.f2360h.f2366f) {
                return false;
            }
            this.f2363k = errorCode;
            notifyAll();
            this.f2356d.V(this.f2355c);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(long j2) {
        this.f2354b += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    void d() {
        boolean z2;
        boolean m2;
        synchronized (this) {
            FramingSource framingSource = this.f2359g;
            if (!framingSource.f2372h && framingSource.f2371g) {
                FramingSink framingSink = this.f2360h;
                if (framingSink.f2366f || framingSink.f2365e) {
                    z2 = true;
                    m2 = m();
                }
            }
            z2 = false;
            m2 = m();
        }
        if (z2) {
            f(ErrorCode.CANCEL);
        } else if (!m2) {
            this.f2356d.V(this.f2355c);
        }
    }

    void e() {
        FramingSink framingSink = this.f2360h;
        if (!framingSink.f2365e) {
            if (!framingSink.f2366f) {
                if (this.f2363k == null) {
                    return;
                }
                throw new StreamResetException(this.f2363k);
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    public void f(ErrorCode errorCode) {
        if (!g(errorCode)) {
            return;
        }
        this.f2356d.d0(this.f2355c, errorCode);
    }

    public void h(ErrorCode errorCode) {
        if (!g(errorCode)) {
            return;
        }
        this.f2356d.e0(this.f2355c, errorCode);
    }

    public int i() {
        return this.f2355c;
    }

    public Sink j() {
        synchronized (this) {
            if (!this.f2358f && !l()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f2360h;
    }

    public Source k() {
        return this.f2359g;
    }

    public boolean l() {
        boolean z2;
        if ((this.f2355c & 1) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.f2356d.f2275d == z2) {
            return true;
        }
        return false;
    }

    public synchronized boolean m() {
        if (this.f2363k != null) {
            return false;
        }
        FramingSource framingSource = this.f2359g;
        if (framingSource.f2372h || framingSource.f2371g) {
            FramingSink framingSink = this.f2360h;
            if (framingSink.f2366f || framingSink.f2365e) {
                if (this.f2358f) {
                    return false;
                }
            }
        }
        return true;
    }

    public Timeout n() {
        return this.f2361i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(BufferedSource bufferedSource, int i2) {
        this.f2359g.b(bufferedSource, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        boolean m2;
        synchronized (this) {
            this.f2359g.f2372h = true;
            m2 = m();
            notifyAll();
        }
        if (!m2) {
            this.f2356d.V(this.f2355c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(List<Header> list) {
        boolean m2;
        synchronized (this) {
            this.f2358f = true;
            this.f2357e.add(Util.G(list));
            m2 = m();
            notifyAll();
        }
        if (!m2) {
            this.f2356d.V(this.f2355c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void r(ErrorCode errorCode) {
        if (this.f2363k == null) {
            this.f2363k = errorCode;
            notifyAll();
        }
    }

    public synchronized Headers s() {
        this.f2361i.v();
        while (this.f2357e.isEmpty() && this.f2363k == null) {
            t();
        }
        this.f2361i.C();
        if (!this.f2357e.isEmpty()) {
        } else {
            throw new StreamResetException(this.f2363k);
        }
        return this.f2357e.removeFirst();
    }

    void t() {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public Timeout u() {
        return this.f2362j;
    }
}
