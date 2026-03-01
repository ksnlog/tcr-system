package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Timeout {

    /* renamed from: d  reason: collision with root package name */
    public static final Companion f2502d = new Companion(null);

    /* renamed from: e  reason: collision with root package name */
    public static final Timeout f2503e = new Timeout() { // from class: okio.Timeout$Companion$NONE$1
        @Override // okio.Timeout
        public Timeout d(long j2) {
            return this;
        }

        @Override // okio.Timeout
        public void f() {
        }

        @Override // okio.Timeout
        public Timeout g(long j2, TimeUnit unit) {
            Intrinsics.f(unit, "unit");
            return this;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private boolean f2504a;

    /* renamed from: b  reason: collision with root package name */
    private long f2505b;

    /* renamed from: c  reason: collision with root package name */
    private long f2506c;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Timeout a() {
        this.f2504a = false;
        return this;
    }

    public Timeout b() {
        this.f2506c = 0L;
        return this;
    }

    public long c() {
        if (this.f2504a) {
            return this.f2505b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    public Timeout d(long j2) {
        this.f2504a = true;
        this.f2505b = j2;
        return this;
    }

    public boolean e() {
        return this.f2504a;
    }

    public void f() {
        if (!Thread.currentThread().isInterrupted()) {
            if (this.f2504a && this.f2505b - System.nanoTime() <= 0) {
                throw new InterruptedIOException("deadline reached");
            }
            return;
        }
        throw new InterruptedIOException("interrupted");
    }

    public Timeout g(long j2, TimeUnit unit) {
        boolean z2;
        Intrinsics.f(unit, "unit");
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            this.f2506c = unit.toNanos(j2);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j2).toString());
    }

    public long h() {
        return this.f2506c;
    }
}
