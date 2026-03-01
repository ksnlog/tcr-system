package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._BufferKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealBufferedSource implements BufferedSource {

    /* renamed from: d  reason: collision with root package name */
    public final Source f2482d;

    /* renamed from: e  reason: collision with root package name */
    public final Buffer f2483e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f2484f;

    public RealBufferedSource(Source source) {
        Intrinsics.f(source, "source");
        this.f2482d = source;
        this.f2483e = new Buffer();
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
            if (true ^ this.f2484f) {
                if (this.f2483e.size() == 0 && this.f2482d.A(this.f2483e, 8192L) == -1) {
                    return -1L;
                }
                return this.f2483e.A(sink, Math.min(j2, this.f2483e.size()));
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    @Override // okio.BufferedSource
    public String C(long j2) {
        boolean z2;
        long j3;
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (j2 == Long.MAX_VALUE) {
                j3 = Long.MAX_VALUE;
            } else {
                j3 = j2 + 1;
            }
            long c2 = c((byte) 10, 0L, j3);
            if (c2 != -1) {
                return _BufferKt.c(this.f2483e, c2);
            }
            if (j3 < Long.MAX_VALUE && l(j3) && this.f2483e.o(j3 - 1) == 13 && l(1 + j3) && this.f2483e.o(j3) == 10) {
                return _BufferKt.c(this.f2483e, j3);
            }
            Buffer buffer = new Buffer();
            Buffer buffer2 = this.f2483e;
            buffer2.n(buffer, 0L, Math.min(32, buffer2.size()));
            throw new EOFException("\\n not found: limit=" + Math.min(this.f2483e.size(), j2) + " content=" + buffer.B().k() + (char) 8230);
        }
        throw new IllegalArgumentException(("limit < 0: " + j2).toString());
    }

    @Override // okio.BufferedSource
    public void F(long j2) {
        if (l(j2)) {
            return;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public boolean K(long j2, ByteString bytes) {
        Intrinsics.f(bytes, "bytes");
        return m(j2, bytes, 0, bytes.u());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0031, code lost:
        if (r0 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0034, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("Expected leading [0-9a-fA-F] character but was 0x");
        r3 = kotlin.text.CharsKt__CharJVMKt.a(16);
        r3 = kotlin.text.CharsKt__CharJVMKt.a(r3);
        r2 = java.lang.Integer.toString(r2, r3);
        kotlin.jvm.internal.Intrinsics.e(r2, "toString(this, checkRadix(radix))");
        r1.append(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
        throw new java.lang.NumberFormatException(r1.toString());
     */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long L() {
        /*
            r5 = this;
            r0 = 1
            r5.F(r0)
            r0 = 0
        L6:
            int r1 = r0 + 1
            long r2 = (long) r1
            boolean r2 = r5.l(r2)
            if (r2 == 0) goto L5e
            okio.Buffer r2 = r5.f2483e
            long r3 = (long) r0
            byte r2 = r2.o(r3)
            r3 = 48
            if (r2 < r3) goto L1e
            r3 = 57
            if (r2 <= r3) goto L2f
        L1e:
            r3 = 97
            if (r2 < r3) goto L26
            r3 = 102(0x66, float:1.43E-43)
            if (r2 <= r3) goto L2f
        L26:
            r3 = 65
            if (r2 < r3) goto L31
            r3 = 70
            if (r2 <= r3) goto L2f
            goto L31
        L2f:
            r0 = r1
            goto L6
        L31:
            if (r0 == 0) goto L34
            goto L5e
        L34:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.append(r3)
            r3 = 16
            int r3 = kotlin.text.CharsKt.a(r3)
            int r3 = kotlin.text.CharsKt.a(r3)
            java.lang.String r2 = java.lang.Integer.toString(r2, r3)
            java.lang.String r3 = "toString(this, checkRadix(radix))"
            kotlin.jvm.internal.Intrinsics.e(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L5e:
            okio.Buffer r0 = r5.f2483e
            long r0 = r0.L()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.L():long");
    }

    @Override // okio.BufferedSource
    public String M(Charset charset) {
        Intrinsics.f(charset, "charset");
        this.f2483e.V(this.f2482d);
        return this.f2483e.M(charset);
    }

    @Override // okio.BufferedSource
    public InputStream N() {
        return new InputStream() { // from class: okio.RealBufferedSource$inputStream$1
            @Override // java.io.InputStream
            public int available() {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.f2484f) {
                    return (int) Math.min(realBufferedSource.f2483e.size(), Integer.MAX_VALUE);
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.f2484f) {
                    if (realBufferedSource.f2483e.size() == 0) {
                        RealBufferedSource realBufferedSource2 = RealBufferedSource.this;
                        if (realBufferedSource2.f2482d.A(realBufferedSource2.f2483e, 8192L) == -1) {
                            return -1;
                        }
                    }
                    return RealBufferedSource.this.f2483e.readByte() & 255;
                }
                throw new IOException("closed");
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] data, int i2, int i3) {
                Intrinsics.f(data, "data");
                if (!RealBufferedSource.this.f2484f) {
                    _UtilKt.b(data.length, i2, i3);
                    if (RealBufferedSource.this.f2483e.size() == 0) {
                        RealBufferedSource realBufferedSource = RealBufferedSource.this;
                        if (realBufferedSource.f2482d.A(realBufferedSource.f2483e, 8192L) == -1) {
                            return -1;
                        }
                    }
                    return RealBufferedSource.this.f2483e.read(data, i2, i3);
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // okio.BufferedSource
    public int P(Options options) {
        Intrinsics.f(options, "options");
        if (!this.f2484f) {
            while (true) {
                int d2 = _BufferKt.d(this.f2483e, options, true);
                if (d2 != -2) {
                    if (d2 != -1) {
                        this.f2483e.skip(options.d()[d2].u());
                        return d2;
                    }
                } else if (this.f2482d.A(this.f2483e, 8192L) == -1) {
                    break;
                }
            }
            return -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer a() {
        return this.f2483e;
    }

    public long b(byte b2) {
        return c(b2, 0L, Long.MAX_VALUE);
    }

    public long c(byte b2, long j2, long j3) {
        boolean z2 = true;
        if (!this.f2484f) {
            if (0 > j2 || j2 > j3) {
                z2 = false;
            }
            if (z2) {
                while (j2 < j3) {
                    long p2 = this.f2483e.p(b2, j2, j3);
                    if (p2 != -1) {
                        return p2;
                    }
                    long size = this.f2483e.size();
                    if (size >= j3 || this.f2482d.A(this.f2483e, 8192L) == -1) {
                        return -1L;
                    }
                    j2 = Math.max(j2, size);
                }
                return -1L;
            }
            throw new IllegalArgumentException(("fromIndex=" + j2 + " toIndex=" + j3).toString());
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.f2484f) {
            this.f2484f = true;
            this.f2482d.close();
            this.f2483e.b();
        }
    }

    @Override // okio.Source
    public Timeout d() {
        return this.f2482d.d();
    }

    public long e(ByteString bytes, long j2) {
        Intrinsics.f(bytes, "bytes");
        if (!this.f2484f) {
            while (true) {
                long r2 = this.f2483e.r(bytes, j2);
                if (r2 == -1) {
                    long size = this.f2483e.size();
                    if (this.f2482d.A(this.f2483e, 8192L) == -1) {
                        return -1L;
                    }
                    j2 = Math.max(j2, (size - bytes.u()) + 1);
                } else {
                    return r2;
                }
            }
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    @Override // okio.BufferedSource
    public String g(long j2) {
        F(j2);
        return this.f2483e.g(j2);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2484f;
    }

    @Override // okio.BufferedSource
    public ByteString j(long j2) {
        F(j2);
        return this.f2483e.j(j2);
    }

    public long k(ByteString targetBytes, long j2) {
        Intrinsics.f(targetBytes, "targetBytes");
        if (!this.f2484f) {
            while (true) {
                long w2 = this.f2483e.w(targetBytes, j2);
                if (w2 == -1) {
                    long size = this.f2483e.size();
                    if (this.f2482d.A(this.f2483e, 8192L) == -1) {
                        return -1L;
                    }
                    j2 = Math.max(j2, size);
                } else {
                    return w2;
                }
            }
        } else {
            throw new IllegalStateException("closed".toString());
        }
    }

    @Override // okio.BufferedSource
    public boolean l(long j2) {
        boolean z2;
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (!this.f2484f) {
                while (this.f2483e.size() < j2) {
                    if (this.f2482d.A(this.f2483e, 8192L) == -1) {
                        return false;
                    }
                }
                return true;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    public boolean m(long j2, ByteString bytes, int i2, int i3) {
        Intrinsics.f(bytes, "bytes");
        if (!this.f2484f) {
            if (j2 >= 0 && i2 >= 0 && i3 >= 0 && bytes.u() - i2 >= i3) {
                for (int i4 = 0; i4 < i3; i4++) {
                    long j3 = i4 + j2;
                    if (l(1 + j3) && this.f2483e.o(j3) == bytes.f(i2 + i4)) {
                    }
                }
                return true;
            }
            return false;
        }
        throw new IllegalStateException("closed".toString());
    }

    public int n() {
        F(4L);
        return this.f2483e.D();
    }

    public short o() {
        F(2L);
        return this.f2483e.E();
    }

    @Override // okio.BufferedSource
    public String q() {
        return C(Long.MAX_VALUE);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) {
        Intrinsics.f(sink, "sink");
        if (this.f2483e.size() == 0 && this.f2482d.A(this.f2483e, 8192L) == -1) {
            return -1;
        }
        return this.f2483e.read(sink);
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        F(1L);
        return this.f2483e.readByte();
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) {
        Intrinsics.f(sink, "sink");
        try {
            F(sink.length);
            this.f2483e.readFully(sink);
        } catch (EOFException e2) {
            int i2 = 0;
            while (this.f2483e.size() > 0) {
                Buffer buffer = this.f2483e;
                int read = buffer.read(sink, i2, (int) buffer.size());
                if (read != -1) {
                    i2 += read;
                } else {
                    throw new AssertionError();
                }
            }
            throw e2;
        }
    }

    @Override // okio.BufferedSource
    public int readInt() {
        F(4L);
        return this.f2483e.readInt();
    }

    @Override // okio.BufferedSource
    public short readShort() {
        F(2L);
        return this.f2483e.readShort();
    }

    @Override // okio.BufferedSource
    public long s(ByteString bytes) {
        Intrinsics.f(bytes, "bytes");
        return e(bytes, 0L);
    }

    @Override // okio.BufferedSource
    public void skip(long j2) {
        if (!this.f2484f) {
            while (j2 > 0) {
                if (this.f2483e.size() == 0 && this.f2482d.A(this.f2483e, 8192L) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j2, this.f2483e.size());
                this.f2483e.skip(min);
                j2 -= min;
            }
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public Buffer t() {
        return this.f2483e;
    }

    public String toString() {
        return "buffer(" + this.f2482d + ')';
    }

    @Override // okio.BufferedSource
    public boolean u() {
        if (!this.f2484f) {
            if (this.f2483e.u() && this.f2482d.A(this.f2483e, 8192L) == -1) {
                return true;
            }
            return false;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSource
    public byte[] v(long j2) {
        F(j2);
        return this.f2483e.v(j2);
    }

    @Override // okio.BufferedSource
    public long y(ByteString targetBytes) {
        Intrinsics.f(targetBytes, "targetBytes");
        return k(targetBytes, 0L);
    }
}
