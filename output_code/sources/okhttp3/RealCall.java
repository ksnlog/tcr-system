package okhttp3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealCall implements Call {

    /* renamed from: d  reason: collision with root package name */
    final OkHttpClient f2017d;

    /* renamed from: e  reason: collision with root package name */
    final RetryAndFollowUpInterceptor f2018e;

    /* renamed from: f  reason: collision with root package name */
    final AsyncTimeout f2019f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private EventListener f2020g;

    /* renamed from: h  reason: collision with root package name */
    final Request f2021h;

    /* renamed from: i  reason: collision with root package name */
    final boolean f2022i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2023j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class AsyncCall extends NamedRunnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ RealCall f2025e;

        @Override // okhttp3.internal.NamedRunnable
        protected void k() {
            Throwable th;
            boolean z2;
            this.f2025e.f2019f.v();
            try {
                try {
                    this.f2025e.e();
                    try {
                        throw null;
                    } catch (Throwable th2) {
                        th = th2;
                        z2 = true;
                        this.f2025e.b();
                        if (!z2) {
                            new IOException("canceled due to " + th);
                            throw null;
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    this.f2025e.f2017d.h().d(this);
                    throw th3;
                }
            } catch (IOException e2) {
                this.f2025e.f2020g.b(this.f2025e, this.f2025e.g(e2));
                throw null;
            } catch (Throwable th4) {
                th = th4;
                z2 = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void l(ExecutorService executorService) {
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e2) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e2);
                    this.f2025e.f2020g.b(this.f2025e, interruptedIOException);
                    throw null;
                }
            } catch (Throwable th) {
                this.f2025e.f2017d.h().d(this);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RealCall m() {
            return this.f2025e;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String n() {
            return this.f2025e.f2021h.h().l();
        }
    }

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z2) {
        this.f2017d = okHttpClient;
        this.f2021h = request;
        this.f2022i = z2;
        this.f2018e = new RetryAndFollowUpInterceptor(okHttpClient, z2);
        AsyncTimeout asyncTimeout = new AsyncTimeout() { // from class: okhttp3.RealCall.1
            @Override // okio.AsyncTimeout
            protected void B() {
                RealCall.this.b();
            }
        };
        this.f2019f = asyncTimeout;
        asyncTimeout.g(okHttpClient.b(), TimeUnit.MILLISECONDS);
    }

    private void c() {
        this.f2018e.k(Platform.l().o("response.body().close()"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RealCall f(OkHttpClient okHttpClient, Request request, boolean z2) {
        RealCall realCall = new RealCall(okHttpClient, request, z2);
        realCall.f2020g = okHttpClient.j().a(realCall);
        return realCall;
    }

    public void b() {
        this.f2018e.b();
    }

    /* renamed from: d */
    public RealCall clone() {
        return f(this.f2017d, this.f2021h, this.f2022i);
    }

    Response e() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f2017d.o());
        arrayList.add(this.f2018e);
        arrayList.add(new BridgeInterceptor(this.f2017d.g()));
        this.f2017d.p();
        arrayList.add(new CacheInterceptor(null));
        arrayList.add(new ConnectInterceptor(this.f2017d));
        if (!this.f2022i) {
            arrayList.addAll(this.f2017d.q());
        }
        arrayList.add(new CallServerInterceptor(this.f2022i));
        Response d2 = new RealInterceptorChain(arrayList, null, null, null, 0, this.f2021h, this, this.f2020g, this.f2017d.d(), this.f2017d.y(), this.f2017d.C()).d(this.f2021h);
        if (!this.f2018e.e()) {
            return d2;
        }
        Util.f(d2);
        throw new IOException("Canceled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public IOException g(@Nullable IOException iOException) {
        if (!this.f2019f.w()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    @Override // okhttp3.Call
    public Response k() {
        synchronized (this) {
            if (!this.f2023j) {
                this.f2023j = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        c();
        this.f2019f.v();
        this.f2020g.c(this);
        try {
            try {
                this.f2017d.h().a(this);
                Response e2 = e();
                if (e2 != null) {
                    return e2;
                }
                throw new IOException("Canceled");
            } catch (IOException e3) {
                IOException g2 = g(e3);
                this.f2020g.b(this, g2);
                throw g2;
            }
        } finally {
            this.f2017d.h().e(this);
        }
    }
}
