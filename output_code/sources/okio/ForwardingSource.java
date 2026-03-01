package okio;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ForwardingSource implements Source {

    /* renamed from: d  reason: collision with root package name */
    private final Source f2460d;

    public ForwardingSource(Source delegate) {
        Intrinsics.f(delegate, "delegate");
        this.f2460d = delegate;
    }

    public final Source b() {
        return this.f2460d;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2460d.close();
    }

    @Override // okio.Source
    public Timeout d() {
        return this.f2460d.d();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f2460d + ')';
    }
}
