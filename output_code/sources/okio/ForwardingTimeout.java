package okio;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ForwardingTimeout extends Timeout {

    /* renamed from: f  reason: collision with root package name */
    private Timeout f2461f;

    public ForwardingTimeout(Timeout delegate) {
        Intrinsics.f(delegate, "delegate");
        this.f2461f = delegate;
    }

    @Override // okio.Timeout
    public Timeout a() {
        return this.f2461f.a();
    }

    @Override // okio.Timeout
    public Timeout b() {
        return this.f2461f.b();
    }

    @Override // okio.Timeout
    public long c() {
        return this.f2461f.c();
    }

    @Override // okio.Timeout
    public Timeout d(long j2) {
        return this.f2461f.d(j2);
    }

    @Override // okio.Timeout
    public boolean e() {
        return this.f2461f.e();
    }

    @Override // okio.Timeout
    public void f() {
        this.f2461f.f();
    }

    @Override // okio.Timeout
    public Timeout g(long j2, TimeUnit unit) {
        Intrinsics.f(unit, "unit");
        return this.f2461f.g(j2, unit);
    }

    public final Timeout i() {
        return this.f2461f;
    }

    public final ForwardingTimeout j(Timeout delegate) {
        Intrinsics.f(delegate, "delegate");
        this.f2461f = delegate;
        return this;
    }
}
