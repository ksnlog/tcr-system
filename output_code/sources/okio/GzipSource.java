package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class GzipSource implements Source {

    /* renamed from: d  reason: collision with root package name */
    private byte f2462d;

    /* renamed from: e  reason: collision with root package name */
    private final RealBufferedSource f2463e;

    /* renamed from: f  reason: collision with root package name */
    private final Inflater f2464f;

    /* renamed from: g  reason: collision with root package name */
    private final InflaterSource f2465g;

    /* renamed from: h  reason: collision with root package name */
    private final CRC32 f2466h;

    public GzipSource(Source source) {
        Intrinsics.f(source, "source");
        RealBufferedSource realBufferedSource = new RealBufferedSource(source);
        this.f2463e = realBufferedSource;
        Inflater inflater = new Inflater(true);
        this.f2464f = inflater;
        this.f2465g = new InflaterSource(realBufferedSource, inflater);
        this.f2466h = new CRC32();
    }

    private final void b(String str, int i2, int i3) {
        if (i3 == i2) {
            return;
        }
        String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i3), Integer.valueOf(i2)}, 3));
        Intrinsics.e(format, "format(this, *args)");
        throw new IOException(format);
    }

    private final void c() {
        boolean z2;
        boolean z3;
        boolean z4;
        this.f2463e.F(10L);
        byte o2 = this.f2463e.f2483e.o(3L);
        boolean z5 = true;
        if (((o2 >> 1) & 1) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            k(this.f2463e.f2483e, 0L, 10L);
        }
        b("ID1ID2", 8075, this.f2463e.readShort());
        this.f2463e.skip(8L);
        if (((o2 >> 2) & 1) == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            this.f2463e.F(2L);
            if (z2) {
                k(this.f2463e.f2483e, 0L, 2L);
            }
            long E = this.f2463e.f2483e.E();
            this.f2463e.F(E);
            if (z2) {
                k(this.f2463e.f2483e, 0L, E);
            }
            this.f2463e.skip(E);
        }
        if (((o2 >> 3) & 1) == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            long b2 = this.f2463e.b((byte) 0);
            if (b2 != -1) {
                if (z2) {
                    k(this.f2463e.f2483e, 0L, b2 + 1);
                }
                this.f2463e.skip(b2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((o2 >> 4) & 1) != 1) {
            z5 = false;
        }
        if (z5) {
            long b3 = this.f2463e.b((byte) 0);
            if (b3 != -1) {
                if (z2) {
                    k(this.f2463e.f2483e, 0L, b3 + 1);
                }
                this.f2463e.skip(b3 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (z2) {
            b("FHCRC", this.f2463e.o(), (short) this.f2466h.getValue());
            this.f2466h.reset();
        }
    }

    private final void e() {
        b("CRC", this.f2463e.n(), (int) this.f2466h.getValue());
        b("ISIZE", this.f2463e.n(), (int) this.f2464f.getBytesWritten());
    }

    private final void k(Buffer buffer, long j2, long j3) {
        int i2;
        Segment segment = buffer.f2445d;
        Intrinsics.c(segment);
        while (true) {
            int i3 = segment.f2489c;
            int i4 = segment.f2488b;
            if (j2 < i3 - i4) {
                break;
            }
            j2 -= i3 - i4;
            segment = segment.f2492f;
            Intrinsics.c(segment);
        }
        while (j3 > 0) {
            int min = (int) Math.min(segment.f2489c - i2, j3);
            this.f2466h.update(segment.f2487a, (int) (segment.f2488b + j2), min);
            j3 -= min;
            segment = segment.f2492f;
            Intrinsics.c(segment);
            j2 = 0;
        }
    }

    @Override // okio.Source
    public long A(Buffer sink, long j2) {
        boolean z2;
        Intrinsics.f(sink, "sink");
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (j2 == 0) {
                return 0L;
            }
            if (this.f2462d == 0) {
                c();
                this.f2462d = (byte) 1;
            }
            if (this.f2462d == 1) {
                long size = sink.size();
                long A = this.f2465g.A(sink, j2);
                if (A != -1) {
                    k(sink, size, A);
                    return A;
                }
                this.f2462d = (byte) 2;
            }
            if (this.f2462d == 2) {
                e();
                this.f2462d = (byte) 3;
                if (!this.f2463e.u()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1L;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2465g.close();
    }

    @Override // okio.Source
    public Timeout d() {
        return this.f2463e.d();
    }
}
