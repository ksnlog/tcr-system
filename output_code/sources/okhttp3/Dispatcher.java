package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.RealCall;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Dispatcher {
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private Runnable f1922c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    private ExecutorService f1923d;

    /* renamed from: a  reason: collision with root package name */
    private int f1920a = 64;

    /* renamed from: b  reason: collision with root package name */
    private int f1921b = 5;

    /* renamed from: e  reason: collision with root package name */
    private final Deque<RealCall.AsyncCall> f1924e = new ArrayDeque();

    /* renamed from: f  reason: collision with root package name */
    private final Deque<RealCall.AsyncCall> f1925f = new ArrayDeque();

    /* renamed from: g  reason: collision with root package name */
    private final Deque<RealCall> f1926g = new ArrayDeque();

    private <T> void c(Deque<T> deque, T t2) {
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t2)) {
                runnable = this.f1922c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (!f() && runnable != null) {
            runnable.run();
        }
    }

    private boolean f() {
        int i2;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealCall.AsyncCall> it = this.f1924e.iterator();
            while (it.hasNext()) {
                RealCall.AsyncCall next = it.next();
                if (this.f1925f.size() >= this.f1920a) {
                    break;
                } else if (h(next) < this.f1921b) {
                    it.remove();
                    arrayList.add(next);
                    this.f1925f.add(next);
                }
            }
            if (g() > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        int size = arrayList.size();
        for (i2 = 0; i2 < size; i2++) {
            ((RealCall.AsyncCall) arrayList.get(i2)).l(b());
        }
        return z2;
    }

    private int h(RealCall.AsyncCall asyncCall) {
        int i2 = 0;
        for (RealCall.AsyncCall asyncCall2 : this.f1925f) {
            if (!asyncCall2.m().f2022i && asyncCall2.n().equals(asyncCall.n())) {
                i2++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(RealCall realCall) {
        this.f1926g.add(realCall);
    }

    public synchronized ExecutorService b() {
        if (this.f1923d == null) {
            this.f1923d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.F("OkHttp Dispatcher", false));
        }
        return this.f1923d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(RealCall.AsyncCall asyncCall) {
        c(this.f1925f, asyncCall);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(RealCall realCall) {
        c(this.f1926g, realCall);
    }

    public synchronized int g() {
        return this.f1925f.size() + this.f1926g.size();
    }
}
