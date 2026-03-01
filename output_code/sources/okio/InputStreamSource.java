package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class InputStreamSource implements Source {

    /* renamed from: d  reason: collision with root package name */
    private final InputStream f2471d;

    /* renamed from: e  reason: collision with root package name */
    private final Timeout f2472e;

    public InputStreamSource(InputStream input, Timeout timeout) {
        Intrinsics.f(input, "input");
        Intrinsics.f(timeout, "timeout");
        this.f2471d = input;
        this.f2472e = timeout;
    }

    @Override // okio.Source
    public long A(Buffer sink, long j2) {
        boolean z2;
        Intrinsics.f(sink, "sink");
        if (j2 == 0) {
            return 0L;
        }
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            try {
                this.f2472e.f();
                Segment R = sink.R(1);
                int read = this.f2471d.read(R.f2487a, R.f2489c, (int) Math.min(j2, 8192 - R.f2489c));
                if (read == -1) {
                    if (R.f2488b == R.f2489c) {
                        sink.f2445d = R.b();
                        SegmentPool.b(R);
                        return -1L;
                    }
                    return -1L;
                }
                R.f2489c += read;
                long j3 = read;
                sink.J(sink.size() + j3);
                return j3;
            } catch (AssertionError e2) {
                if (Okio.c(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2471d.close();
    }

    @Override // okio.Source
    public Timeout d() {
        return this.f2472e;
    }

    public String toString() {
        return "source(" + this.f2471d + ')';
    }
}
