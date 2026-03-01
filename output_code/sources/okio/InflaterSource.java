package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InflaterSource implements Source {

    /* renamed from: d  reason: collision with root package name */
    private final BufferedSource f2467d;

    /* renamed from: e  reason: collision with root package name */
    private final Inflater f2468e;

    /* renamed from: f  reason: collision with root package name */
    private int f2469f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2470g;

    public InflaterSource(BufferedSource source, Inflater inflater) {
        Intrinsics.f(source, "source");
        Intrinsics.f(inflater, "inflater");
        this.f2467d = source;
        this.f2468e = inflater;
    }

    private final void e() {
        int i2 = this.f2469f;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.f2468e.getRemaining();
        this.f2469f -= remaining;
        this.f2467d.skip(remaining);
    }

    @Override // okio.Source
    public long A(Buffer sink, long j2) {
        Intrinsics.f(sink, "sink");
        do {
            long b2 = b(sink, j2);
            if (b2 > 0) {
                return b2;
            }
            if (this.f2468e.finished() || this.f2468e.needsDictionary()) {
                return -1L;
            }
        } while (!this.f2467d.u());
        throw new EOFException("source exhausted prematurely");
    }

    public final long b(Buffer sink, long j2) {
        boolean z2;
        Intrinsics.f(sink, "sink");
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (!this.f2470g) {
                if (j2 == 0) {
                    return 0L;
                }
                try {
                    Segment R = sink.R(1);
                    int min = (int) Math.min(j2, 8192 - R.f2489c);
                    c();
                    int inflate = this.f2468e.inflate(R.f2487a, R.f2489c, min);
                    e();
                    if (inflate > 0) {
                        R.f2489c += inflate;
                        long j3 = inflate;
                        sink.J(sink.size() + j3);
                        return j3;
                    }
                    if (R.f2488b == R.f2489c) {
                        sink.f2445d = R.b();
                        SegmentPool.b(R);
                    }
                    return 0L;
                } catch (DataFormatException e2) {
                    throw new IOException(e2);
                }
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    public final boolean c() {
        if (!this.f2468e.needsInput()) {
            return false;
        }
        if (this.f2467d.u()) {
            return true;
        }
        Segment segment = this.f2467d.t().f2445d;
        Intrinsics.c(segment);
        int i2 = segment.f2489c;
        int i3 = segment.f2488b;
        int i4 = i2 - i3;
        this.f2469f = i4;
        this.f2468e.setInput(segment.f2487a, i3, i4);
        return false;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f2470g) {
            return;
        }
        this.f2468e.end();
        this.f2470g = true;
        this.f2467d.close();
    }

    @Override // okio.Source
    public Timeout d() {
        return this.f2467d.d();
    }
}
