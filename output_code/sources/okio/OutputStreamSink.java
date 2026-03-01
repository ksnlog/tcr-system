package okio;

import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class OutputStreamSink implements Sink {

    /* renamed from: d  reason: collision with root package name */
    private final OutputStream f2477d;

    /* renamed from: e  reason: collision with root package name */
    private final Timeout f2478e;

    public OutputStreamSink(OutputStream out, Timeout timeout) {
        Intrinsics.f(out, "out");
        Intrinsics.f(timeout, "timeout");
        this.f2477d = out;
        this.f2478e = timeout;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2477d.close();
    }

    @Override // okio.Sink
    public Timeout d() {
        return this.f2478e;
    }

    @Override // okio.Sink
    public void f(Buffer source, long j2) {
        Intrinsics.f(source, "source");
        _UtilKt.b(source.size(), 0L, j2);
        while (j2 > 0) {
            this.f2478e.f();
            Segment segment = source.f2445d;
            Intrinsics.c(segment);
            int min = (int) Math.min(j2, segment.f2489c - segment.f2488b);
            this.f2477d.write(segment.f2487a, segment.f2488b, min);
            segment.f2488b += min;
            long j3 = min;
            j2 -= j3;
            source.J(source.size() - j3);
            if (segment.f2488b == segment.f2489c) {
                source.f2445d = segment.b();
                SegmentPool.b(segment);
            }
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f2477d.flush();
    }

    public String toString() {
        return "sink(" + this.f2477d + ')';
    }
}
