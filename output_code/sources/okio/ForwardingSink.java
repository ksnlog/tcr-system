package okio;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ForwardingSink implements Sink {

    /* renamed from: d  reason: collision with root package name */
    private final Sink f2459d;

    public ForwardingSink(Sink delegate) {
        Intrinsics.f(delegate, "delegate");
        this.f2459d = delegate;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2459d.close();
    }

    @Override // okio.Sink
    public Timeout d() {
        return this.f2459d.d();
    }

    @Override // okio.Sink
    public void f(Buffer source, long j2) {
        Intrinsics.f(source, "source");
        this.f2459d.f(source, j2);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f2459d.flush();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f2459d + ')';
    }
}
