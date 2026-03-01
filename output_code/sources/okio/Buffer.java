package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.internal._BufferKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    /* renamed from: d  reason: collision with root package name */
    public Segment f2445d;

    /* renamed from: e  reason: collision with root package name */
    private long f2446e;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class UnsafeCursor implements Closeable {

        /* renamed from: d  reason: collision with root package name */
        public Buffer f2447d;

        /* renamed from: e  reason: collision with root package name */
        private Segment f2448e;

        /* renamed from: g  reason: collision with root package name */
        public byte[] f2450g;

        /* renamed from: f  reason: collision with root package name */
        public long f2449f = -1;

        /* renamed from: h  reason: collision with root package name */
        public int f2451h = -1;

        /* renamed from: i  reason: collision with root package name */
        public int f2452i = -1;

        public final void b(Segment segment) {
            this.f2448e = segment;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            boolean z2;
            if (this.f2447d != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                this.f2447d = null;
                b(null);
                this.f2449f = -1L;
                this.f2450g = null;
                this.f2451h = -1;
                this.f2452i = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer".toString());
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
            if (size() == 0) {
                return -1L;
            }
            if (j2 > size()) {
                j2 = size();
            }
            sink.f(this, j2);
            return j2;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j2).toString());
    }

    public ByteString B() {
        return j(size());
    }

    @Override // okio.BufferedSource
    public String C(long j2) {
        boolean z2;
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            long j3 = Long.MAX_VALUE;
            if (j2 != Long.MAX_VALUE) {
                j3 = j2 + 1;
            }
            long p2 = p((byte) 10, 0L, j3);
            if (p2 != -1) {
                return _BufferKt.c(this, p2);
            }
            if (j3 < size() && o(j3 - 1) == 13 && o(j3) == 10) {
                return _BufferKt.c(this, j3);
            }
            Buffer buffer = new Buffer();
            n(buffer, 0L, Math.min(32, size()));
            throw new EOFException("\\n not found: limit=" + Math.min(size(), j2) + " content=" + buffer.B().k() + (char) 8230);
        }
        throw new IllegalArgumentException(("limit < 0: " + j2).toString());
    }

    public int D() {
        return _UtilKt.d(readInt());
    }

    public short E() {
        return _UtilKt.e(readShort());
    }

    @Override // okio.BufferedSource
    public void F(long j2) {
        if (this.f2446e >= j2) {
            return;
        }
        throw new EOFException();
    }

    public String G(long j2, Charset charset) {
        boolean z2;
        Intrinsics.f(charset, "charset");
        if (j2 >= 0 && j2 <= 2147483647L) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (this.f2446e >= j2) {
                if (j2 == 0) {
                    return "";
                }
                Segment segment = this.f2445d;
                Intrinsics.c(segment);
                int i2 = segment.f2488b;
                if (i2 + j2 > segment.f2489c) {
                    return new String(v(j2), charset);
                }
                int i3 = (int) j2;
                String str = new String(segment.f2487a, i2, i3, charset);
                int i4 = segment.f2488b + i3;
                segment.f2488b = i4;
                this.f2446e -= j2;
                if (i4 == segment.f2489c) {
                    this.f2445d = segment.b();
                    SegmentPool.b(segment);
                }
                return str;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(("byteCount: " + j2).toString());
    }

    public String I() {
        return G(this.f2446e, Charsets.f909b);
    }

    public final void J(long j2) {
        this.f2446e = j2;
    }

    @Override // okio.BufferedSource
    public boolean K(long j2, ByteString bytes) {
        Intrinsics.f(bytes, "bytes");
        return x(j2, bytes, 0, bytes.u());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00aa A[EDGE_INSN: B:44:0x00aa->B:38:0x00aa ?: BREAK  , SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long L() {
        /*
            r15 = this;
            long r0 = r15.size()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto Lb4
            r0 = 0
            r4 = r2
            r1 = 0
        Ld:
            okio.Segment r6 = r15.f2445d
            kotlin.jvm.internal.Intrinsics.c(r6)
            byte[] r7 = r6.f2487a
            int r8 = r6.f2488b
            int r9 = r6.f2489c
        L18:
            if (r8 >= r9) goto L96
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L27
            r11 = 57
            if (r10 > r11) goto L27
            int r11 = r10 + (-48)
            goto L3f
        L27:
            r11 = 97
            if (r10 < r11) goto L34
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L34
            int r11 = r10 + (-97)
        L31:
            int r11 = r11 + 10
            goto L3f
        L34:
            r11 = 65
            if (r10 < r11) goto L77
            r11 = 70
            if (r10 > r11) goto L77
            int r11 = r10 + (-65)
            goto L31
        L3f:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 != 0) goto L4f
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L18
        L4f:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.i(r4)
            okio.Buffer r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.I()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L77:
            if (r0 == 0) goto L7b
            r1 = 1
            goto L96
        L7b:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.append(r2)
            java.lang.String r2 = okio._UtilKt.f(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L96:
            if (r8 != r9) goto La2
            okio.Segment r7 = r6.b()
            r15.f2445d = r7
            okio.SegmentPool.b(r6)
            goto La4
        La2:
            r6.f2488b = r8
        La4:
            if (r1 != 0) goto Laa
            okio.Segment r6 = r15.f2445d
            if (r6 != 0) goto Ld
        Laa:
            long r1 = r15.size()
            long r6 = (long) r0
            long r1 = r1 - r6
            r15.J(r1)
            return r4
        Lb4:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            goto Lbb
        Lba:
            throw r0
        Lbb:
            goto Lba
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.L():long");
    }

    @Override // okio.BufferedSource
    public String M(Charset charset) {
        Intrinsics.f(charset, "charset");
        return G(this.f2446e, charset);
    }

    @Override // okio.BufferedSource
    public InputStream N() {
        return new InputStream() { // from class: okio.Buffer$inputStream$1
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size(), Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size() > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] sink, int i2, int i3) {
                Intrinsics.f(sink, "sink");
                return Buffer.this.read(sink, i2, i3);
            }
        };
    }

    public final ByteString O() {
        boolean z2;
        if (size() <= 2147483647L) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return Q((int) size());
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()).toString());
    }

    @Override // okio.BufferedSource
    public int P(Options options) {
        Intrinsics.f(options, "options");
        int e2 = _BufferKt.e(this, options, false, 2, null);
        if (e2 == -1) {
            return -1;
        }
        skip(options.d()[e2].u());
        return e2;
    }

    public final ByteString Q(int i2) {
        if (i2 == 0) {
            return ByteString.f2455h;
        }
        _UtilKt.b(size(), 0L, i2);
        Segment segment = this.f2445d;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Intrinsics.c(segment);
            int i6 = segment.f2489c;
            int i7 = segment.f2488b;
            if (i6 != i7) {
                i4 += i6 - i7;
                i5++;
                segment = segment.f2492f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i5];
        int[] iArr = new int[i5 * 2];
        Segment segment2 = this.f2445d;
        int i8 = 0;
        while (i3 < i2) {
            Intrinsics.c(segment2);
            bArr[i8] = segment2.f2487a;
            i3 += segment2.f2489c - segment2.f2488b;
            iArr[i8] = Math.min(i3, i2);
            iArr[i8 + i5] = segment2.f2488b;
            segment2.f2490d = true;
            i8++;
            segment2 = segment2.f2492f;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    public final Segment R(int i2) {
        boolean z2 = true;
        if (i2 < 1 || i2 > 8192) {
            z2 = false;
        }
        if (z2) {
            Segment segment = this.f2445d;
            if (segment == null) {
                Segment c2 = SegmentPool.c();
                this.f2445d = c2;
                c2.f2493g = c2;
                c2.f2492f = c2;
                return c2;
            }
            Intrinsics.c(segment);
            Segment segment2 = segment.f2493g;
            Intrinsics.c(segment2);
            if (segment2.f2489c + i2 <= 8192 && segment2.f2491e) {
                return segment2;
            }
            return segment2.c(SegmentPool.c());
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    public Buffer S(ByteString byteString) {
        Intrinsics.f(byteString, "byteString");
        byteString.z(this, 0, byteString.u());
        return this;
    }

    @Override // okio.BufferedSink
    /* renamed from: T */
    public Buffer write(byte[] source) {
        Intrinsics.f(source, "source");
        return write(source, 0, source.length);
    }

    @Override // okio.BufferedSink
    /* renamed from: U */
    public Buffer write(byte[] source, int i2, int i3) {
        Intrinsics.f(source, "source");
        long j2 = i3;
        _UtilKt.b(source.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment R = R(1);
            int min = Math.min(i4 - i2, 8192 - R.f2489c);
            int i5 = i2 + min;
            ArraysKt___ArraysJvmKt.d(source, R.f2487a, R.f2489c, i2, i5);
            R.f2489c += min;
            i2 = i5;
        }
        J(size() + j2);
        return this;
    }

    public long V(Source source) {
        Intrinsics.f(source, "source");
        long j2 = 0;
        while (true) {
            long A = source.A(this, 8192L);
            if (A != -1) {
                j2 += A;
            } else {
                return j2;
            }
        }
    }

    @Override // okio.BufferedSink
    /* renamed from: W */
    public Buffer writeByte(int i2) {
        Segment R = R(1);
        byte[] bArr = R.f2487a;
        int i3 = R.f2489c;
        R.f2489c = i3 + 1;
        bArr[i3] = (byte) i2;
        J(size() + 1);
        return this;
    }

    @Override // okio.BufferedSink
    /* renamed from: X */
    public Buffer i(long j2) {
        if (j2 == 0) {
            return writeByte(48);
        }
        long j3 = (j2 >>> 1) | j2;
        long j4 = j3 | (j3 >>> 2);
        long j5 = j4 | (j4 >>> 4);
        long j6 = j5 | (j5 >>> 8);
        long j7 = j6 | (j6 >>> 16);
        long j8 = j7 | (j7 >>> 32);
        long j9 = j8 - ((j8 >>> 1) & 6148914691236517205L);
        long j10 = ((j9 >>> 2) & 3689348814741910323L) + (j9 & 3689348814741910323L);
        long j11 = ((j10 >>> 4) + j10) & 1085102592571150095L;
        long j12 = j11 + (j11 >>> 8);
        long j13 = j12 + (j12 >>> 16);
        int i2 = (int) ((((j13 & 63) + ((j13 >>> 32) & 63)) + 3) / 4);
        Segment R = R(i2);
        byte[] bArr = R.f2487a;
        int i3 = R.f2489c;
        for (int i4 = (i3 + i2) - 1; i4 >= i3; i4--) {
            bArr[i4] = _BufferKt.a()[(int) (15 & j2)];
            j2 >>>= 4;
        }
        R.f2489c += i2;
        J(size() + i2);
        return this;
    }

    @Override // okio.BufferedSink
    /* renamed from: Y */
    public Buffer writeInt(int i2) {
        Segment R = R(4);
        byte[] bArr = R.f2487a;
        int i3 = R.f2489c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        R.f2489c = i6 + 1;
        J(size() + 4);
        return this;
    }

    @Override // okio.BufferedSink
    /* renamed from: Z */
    public Buffer writeShort(int i2) {
        Segment R = R(2);
        byte[] bArr = R.f2487a;
        int i3 = R.f2489c;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        R.f2489c = i4 + 1;
        J(size() + 2);
        return this;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer a() {
        return this;
    }

    public Buffer a0(String string, int i2, int i3, Charset charset) {
        boolean z2;
        boolean z3;
        Intrinsics.f(string, "string");
        Intrinsics.f(charset, "charset");
        boolean z4 = true;
        if (i2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i3 >= i2) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                if (i3 > string.length()) {
                    z4 = false;
                }
                if (z4) {
                    if (Intrinsics.a(charset, Charsets.f909b)) {
                        return h(string, i2, i3);
                    }
                    String substring = string.substring(i2, i3);
                    Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    byte[] bytes = substring.getBytes(charset);
                    Intrinsics.e(bytes, "this as java.lang.String).getBytes(charset)");
                    return write(bytes, 0, bytes.length);
                }
                throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()).toString());
            }
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2).toString());
        }
        throw new IllegalArgumentException(("beginIndex < 0: " + i2).toString());
    }

    public final void b() {
        skip(size());
    }

    @Override // okio.BufferedSink
    /* renamed from: b0 */
    public Buffer H(String string) {
        Intrinsics.f(string, "string");
        return h(string, 0, string.length());
    }

    /* renamed from: c */
    public Buffer clone() {
        return m();
    }

    @Override // okio.BufferedSink
    /* renamed from: c0 */
    public Buffer h(String string, int i2, int i3) {
        boolean z2;
        boolean z3;
        boolean z4;
        char charAt;
        char c2;
        boolean z5;
        Intrinsics.f(string, "string");
        if (i2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i3 >= i2) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                if (i3 <= string.length()) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (z4) {
                    while (i2 < i3) {
                        char charAt2 = string.charAt(i2);
                        if (charAt2 < 128) {
                            Segment R = R(1);
                            byte[] bArr = R.f2487a;
                            int i4 = R.f2489c - i2;
                            int min = Math.min(i3, 8192 - i4);
                            int i5 = i2 + 1;
                            bArr[i2 + i4] = (byte) charAt2;
                            while (true) {
                                i2 = i5;
                                if (i2 >= min || (charAt = string.charAt(i2)) >= 128) {
                                    break;
                                }
                                i5 = i2 + 1;
                                bArr[i2 + i4] = (byte) charAt;
                            }
                            int i6 = R.f2489c;
                            int i7 = (i4 + i2) - i6;
                            R.f2489c = i6 + i7;
                            J(size() + i7);
                        } else {
                            if (charAt2 < 2048) {
                                Segment R2 = R(2);
                                byte[] bArr2 = R2.f2487a;
                                int i8 = R2.f2489c;
                                bArr2[i8] = (byte) ((charAt2 >> 6) | 192);
                                bArr2[i8 + 1] = (byte) ((charAt2 & '?') | 128);
                                R2.f2489c = i8 + 2;
                                J(size() + 2);
                            } else if (charAt2 >= 55296 && charAt2 <= 57343) {
                                int i9 = i2 + 1;
                                if (i9 < i3) {
                                    c2 = string.charAt(i9);
                                } else {
                                    c2 = 0;
                                }
                                if (charAt2 <= 56319) {
                                    if (56320 <= c2 && c2 < 57344) {
                                        z5 = true;
                                    } else {
                                        z5 = false;
                                    }
                                    if (z5) {
                                        int i10 = (((charAt2 & 1023) << 10) | (c2 & 1023)) + 65536;
                                        Segment R3 = R(4);
                                        byte[] bArr3 = R3.f2487a;
                                        int i11 = R3.f2489c;
                                        bArr3[i11] = (byte) ((i10 >> 18) | 240);
                                        bArr3[i11 + 1] = (byte) (((i10 >> 12) & 63) | 128);
                                        bArr3[i11 + 2] = (byte) (((i10 >> 6) & 63) | 128);
                                        bArr3[i11 + 3] = (byte) ((i10 & 63) | 128);
                                        R3.f2489c = i11 + 4;
                                        J(size() + 4);
                                        i2 += 2;
                                    }
                                }
                                writeByte(63);
                                i2 = i9;
                            } else {
                                Segment R4 = R(3);
                                byte[] bArr4 = R4.f2487a;
                                int i12 = R4.f2489c;
                                bArr4[i12] = (byte) ((charAt2 >> '\f') | 224);
                                bArr4[i12 + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                                bArr4[i12 + 2] = (byte) ((charAt2 & '?') | 128);
                                R4.f2489c = i12 + 3;
                                J(size() + 3);
                            }
                            i2++;
                        }
                    }
                    return this;
                }
                throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()).toString());
            }
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2).toString());
        }
        throw new IllegalArgumentException(("beginIndex < 0: " + i2).toString());
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.Source
    public Timeout d() {
        return Timeout.f2503e;
    }

    public Buffer d0(int i2) {
        if (i2 < 128) {
            writeByte(i2);
        } else if (i2 < 2048) {
            Segment R = R(2);
            byte[] bArr = R.f2487a;
            int i3 = R.f2489c;
            bArr[i3] = (byte) ((i2 >> 6) | 192);
            bArr[i3 + 1] = (byte) ((i2 & 63) | 128);
            R.f2489c = i3 + 2;
            J(size() + 2);
        } else {
            boolean z2 = false;
            if (55296 <= i2 && i2 < 57344) {
                z2 = true;
            }
            if (z2) {
                writeByte(63);
            } else if (i2 < 65536) {
                Segment R2 = R(3);
                byte[] bArr2 = R2.f2487a;
                int i4 = R2.f2489c;
                bArr2[i4] = (byte) ((i2 >> 12) | 224);
                bArr2[i4 + 1] = (byte) (((i2 >> 6) & 63) | 128);
                bArr2[i4 + 2] = (byte) ((i2 & 63) | 128);
                R2.f2489c = i4 + 3;
                J(size() + 3);
            } else if (i2 <= 1114111) {
                Segment R3 = R(4);
                byte[] bArr3 = R3.f2487a;
                int i5 = R3.f2489c;
                bArr3[i5] = (byte) ((i2 >> 18) | 240);
                bArr3[i5 + 1] = (byte) (((i2 >> 12) & 63) | 128);
                bArr3[i5 + 2] = (byte) (((i2 >> 6) & 63) | 128);
                bArr3[i5 + 3] = (byte) ((i2 & 63) | 128);
                R3.f2489c = i5 + 4;
                J(size() + 4);
            } else {
                throw new IllegalArgumentException("Unexpected code point: 0x" + _UtilKt.g(i2));
            }
        }
        return this;
    }

    public final long e() {
        long size = size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = this.f2445d;
        Intrinsics.c(segment);
        Segment segment2 = segment.f2493g;
        Intrinsics.c(segment2);
        int i2 = segment2.f2489c;
        if (i2 < 8192 && segment2.f2491e) {
            size -= i2 - segment2.f2488b;
        }
        return size;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Buffer) {
            Buffer buffer = (Buffer) obj;
            if (size() == buffer.size()) {
                if (size() == 0) {
                    return true;
                }
                Segment segment = this.f2445d;
                Intrinsics.c(segment);
                Segment segment2 = buffer.f2445d;
                Intrinsics.c(segment2);
                int i2 = segment.f2488b;
                int i3 = segment2.f2488b;
                long j2 = 0;
                while (j2 < size()) {
                    long min = Math.min(segment.f2489c - i2, segment2.f2489c - i3);
                    long j3 = 0;
                    while (j3 < min) {
                        int i4 = i2 + 1;
                        int i5 = i3 + 1;
                        if (segment.f2487a[i2] == segment2.f2487a[i3]) {
                            j3++;
                            i2 = i4;
                            i3 = i5;
                        }
                    }
                    if (i2 == segment.f2489c) {
                        segment = segment.f2492f;
                        Intrinsics.c(segment);
                        i2 = segment.f2488b;
                    }
                    if (i3 == segment2.f2489c) {
                        segment2 = segment2.f2492f;
                        Intrinsics.c(segment2);
                        i3 = segment2.f2488b;
                    }
                    j2 += min;
                }
                return true;
            }
        }
        return false;
    }

    @Override // okio.Sink
    public void f(Buffer source, long j2) {
        boolean z2;
        Segment segment;
        Segment segment2;
        int i2;
        Intrinsics.f(source, "source");
        if (source != this) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            _UtilKt.b(source.size(), 0L, j2);
            while (j2 > 0) {
                Segment segment3 = source.f2445d;
                Intrinsics.c(segment3);
                int i3 = segment3.f2489c;
                Intrinsics.c(source.f2445d);
                if (j2 < i3 - segment.f2488b) {
                    Segment segment4 = this.f2445d;
                    if (segment4 != null) {
                        Intrinsics.c(segment4);
                        segment2 = segment4.f2493g;
                    } else {
                        segment2 = null;
                    }
                    if (segment2 != null && segment2.f2491e) {
                        long j3 = segment2.f2489c + j2;
                        if (segment2.f2490d) {
                            i2 = 0;
                        } else {
                            i2 = segment2.f2488b;
                        }
                        if (j3 - i2 <= 8192) {
                            Segment segment5 = source.f2445d;
                            Intrinsics.c(segment5);
                            segment5.f(segment2, (int) j2);
                            source.J(source.size() - j2);
                            J(size() + j2);
                            return;
                        }
                    }
                    Segment segment6 = source.f2445d;
                    Intrinsics.c(segment6);
                    source.f2445d = segment6.e((int) j2);
                }
                Segment segment7 = source.f2445d;
                Intrinsics.c(segment7);
                long j4 = segment7.f2489c - segment7.f2488b;
                source.f2445d = segment7.b();
                Segment segment8 = this.f2445d;
                if (segment8 == null) {
                    this.f2445d = segment7;
                    segment7.f2493g = segment7;
                    segment7.f2492f = segment7;
                } else {
                    Intrinsics.c(segment8);
                    Segment segment9 = segment8.f2493g;
                    Intrinsics.c(segment9);
                    segment9.c(segment7).a();
                }
                source.J(source.size() - j4);
                J(size() + j4);
                j2 -= j4;
            }
            return;
        }
        throw new IllegalArgumentException("source == this".toString());
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource
    public String g(long j2) {
        return G(j2, Charsets.f909b);
    }

    public int hashCode() {
        Segment segment = this.f2445d;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.f2489c;
            for (int i4 = segment.f2488b; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.f2487a[i4];
            }
            segment = segment.f2492f;
            Intrinsics.c(segment);
        } while (segment != this.f2445d);
        return i2;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.BufferedSource
    public ByteString j(long j2) {
        boolean z2;
        if (j2 >= 0 && j2 <= 2147483647L) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (size() >= j2) {
                if (j2 >= 4096) {
                    ByteString Q = Q((int) j2);
                    skip(j2);
                    return Q;
                }
                return new ByteString(v(j2));
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(("byteCount: " + j2).toString());
    }

    @Override // okio.BufferedSource
    public boolean l(long j2) {
        return this.f2446e >= j2;
    }

    public final Buffer m() {
        Buffer buffer = new Buffer();
        if (size() != 0) {
            Segment segment = this.f2445d;
            Intrinsics.c(segment);
            Segment d2 = segment.d();
            buffer.f2445d = d2;
            d2.f2493g = d2;
            d2.f2492f = d2;
            for (Segment segment2 = segment.f2492f; segment2 != segment; segment2 = segment2.f2492f) {
                Segment segment3 = d2.f2493g;
                Intrinsics.c(segment3);
                Intrinsics.c(segment2);
                segment3.c(segment2.d());
            }
            buffer.J(size());
        }
        return buffer;
    }

    public final Buffer n(Buffer out, long j2, long j3) {
        Intrinsics.f(out, "out");
        _UtilKt.b(size(), j2, j3);
        if (j3 != 0) {
            out.J(out.size() + j3);
            Segment segment = this.f2445d;
            while (true) {
                Intrinsics.c(segment);
                int i2 = segment.f2489c;
                int i3 = segment.f2488b;
                if (j2 < i2 - i3) {
                    break;
                }
                j2 -= i2 - i3;
                segment = segment.f2492f;
            }
            while (j3 > 0) {
                Intrinsics.c(segment);
                Segment d2 = segment.d();
                int i4 = d2.f2488b + ((int) j2);
                d2.f2488b = i4;
                d2.f2489c = Math.min(i4 + ((int) j3), d2.f2489c);
                Segment segment2 = out.f2445d;
                if (segment2 == null) {
                    d2.f2493g = d2;
                    d2.f2492f = d2;
                    out.f2445d = d2;
                } else {
                    Intrinsics.c(segment2);
                    Segment segment3 = segment2.f2493g;
                    Intrinsics.c(segment3);
                    segment3.c(d2);
                }
                j3 -= d2.f2489c - d2.f2488b;
                segment = segment.f2492f;
                j2 = 0;
            }
        }
        return this;
    }

    public final byte o(long j2) {
        _UtilKt.b(size(), j2, 1L);
        Segment segment = this.f2445d;
        if (segment != null) {
            if (size() - j2 < j2) {
                long size = size();
                while (size > j2) {
                    segment = segment.f2493g;
                    Intrinsics.c(segment);
                    size -= segment.f2489c - segment.f2488b;
                }
                Intrinsics.c(segment);
                return segment.f2487a[(int) ((segment.f2488b + j2) - size)];
            }
            long j3 = 0;
            while (true) {
                long j4 = (segment.f2489c - segment.f2488b) + j3;
                if (j4 <= j2) {
                    segment = segment.f2492f;
                    Intrinsics.c(segment);
                    j3 = j4;
                } else {
                    Intrinsics.c(segment);
                    return segment.f2487a[(int) ((segment.f2488b + j2) - j3)];
                }
            }
        } else {
            Intrinsics.c(null);
            throw null;
        }
    }

    public long p(byte b2, long j2, long j3) {
        Segment segment;
        int i2;
        boolean z2 = false;
        long j4 = 0;
        if (0 <= j2 && j2 <= j3) {
            z2 = true;
        }
        if (z2) {
            if (j3 > size()) {
                j3 = size();
            }
            if (j2 == j3 || (segment = this.f2445d) == null) {
                return -1L;
            }
            if (size() - j2 < j2) {
                j4 = size();
                while (j4 > j2) {
                    segment = segment.f2493g;
                    Intrinsics.c(segment);
                    j4 -= segment.f2489c - segment.f2488b;
                }
                while (j4 < j3) {
                    byte[] bArr = segment.f2487a;
                    int min = (int) Math.min(segment.f2489c, (segment.f2488b + j3) - j4);
                    i2 = (int) ((segment.f2488b + j2) - j4);
                    while (i2 < min) {
                        if (bArr[i2] != b2) {
                            i2++;
                        }
                    }
                    j4 += segment.f2489c - segment.f2488b;
                    segment = segment.f2492f;
                    Intrinsics.c(segment);
                    j2 = j4;
                }
                return -1L;
            }
            while (true) {
                long j5 = (segment.f2489c - segment.f2488b) + j4;
                if (j5 > j2) {
                    break;
                }
                segment = segment.f2492f;
                Intrinsics.c(segment);
                j4 = j5;
            }
            while (j4 < j3) {
                byte[] bArr2 = segment.f2487a;
                int min2 = (int) Math.min(segment.f2489c, (segment.f2488b + j3) - j4);
                i2 = (int) ((segment.f2488b + j2) - j4);
                while (i2 < min2) {
                    if (bArr2[i2] != b2) {
                        i2++;
                    }
                }
                j4 += segment.f2489c - segment.f2488b;
                segment = segment.f2492f;
                Intrinsics.c(segment);
                j2 = j4;
            }
            return -1L;
            return (i2 - segment.f2488b) + j4;
        }
        throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + j2 + " toIndex=" + j3).toString());
    }

    @Override // okio.BufferedSource
    public String q() {
        return C(Long.MAX_VALUE);
    }

    public long r(ByteString bytes, long j2) {
        boolean z2;
        boolean z3;
        long j3 = j2;
        Intrinsics.f(bytes, "bytes");
        if (bytes.u() > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            long j4 = 0;
            if (j3 >= 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                Segment segment = this.f2445d;
                if (segment != null) {
                    if (size() - j3 < j3) {
                        long size = size();
                        while (size > j3) {
                            segment = segment.f2493g;
                            Intrinsics.c(segment);
                            size -= segment.f2489c - segment.f2488b;
                        }
                        byte[] l2 = bytes.l();
                        byte b2 = l2[0];
                        int u2 = bytes.u();
                        long size2 = (size() - u2) + 1;
                        while (size < size2) {
                            byte[] bArr = segment.f2487a;
                            long j5 = size;
                            int min = (int) Math.min(segment.f2489c, (segment.f2488b + size2) - size);
                            for (int i2 = (int) ((segment.f2488b + j3) - j5); i2 < min; i2++) {
                                if (bArr[i2] == b2 && _BufferKt.b(segment, i2 + 1, l2, 1, u2)) {
                                    return (i2 - segment.f2488b) + j5;
                                }
                            }
                            j3 = j5 + (segment.f2489c - segment.f2488b);
                            segment = segment.f2492f;
                            Intrinsics.c(segment);
                            size = j3;
                        }
                    } else {
                        while (true) {
                            long j6 = (segment.f2489c - segment.f2488b) + j4;
                            if (j6 > j3) {
                                break;
                            }
                            segment = segment.f2492f;
                            Intrinsics.c(segment);
                            j4 = j6;
                        }
                        byte[] l3 = bytes.l();
                        byte b3 = l3[0];
                        int u3 = bytes.u();
                        long size3 = (size() - u3) + 1;
                        while (j4 < size3) {
                            byte[] bArr2 = segment.f2487a;
                            long j7 = size3;
                            int min2 = (int) Math.min(segment.f2489c, (segment.f2488b + size3) - j4);
                            for (int i3 = (int) ((segment.f2488b + j3) - j4); i3 < min2; i3++) {
                                if (bArr2[i3] == b3 && _BufferKt.b(segment, i3 + 1, l3, 1, u3)) {
                                    return (i3 - segment.f2488b) + j4;
                                }
                            }
                            j4 += segment.f2489c - segment.f2488b;
                            segment = segment.f2492f;
                            Intrinsics.c(segment);
                            j3 = j4;
                            size3 = j7;
                        }
                    }
                }
                return -1L;
            }
            throw new IllegalArgumentException(("fromIndex < 0: " + j3).toString());
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) {
        Intrinsics.f(sink, "sink");
        Segment segment = this.f2445d;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(sink.remaining(), segment.f2489c - segment.f2488b);
        sink.put(segment.f2487a, segment.f2488b, min);
        int i2 = segment.f2488b + min;
        segment.f2488b = i2;
        this.f2446e -= min;
        if (i2 == segment.f2489c) {
            this.f2445d = segment.b();
            SegmentPool.b(segment);
        }
        return min;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (size() != 0) {
            Segment segment = this.f2445d;
            Intrinsics.c(segment);
            int i2 = segment.f2488b;
            int i3 = segment.f2489c;
            int i4 = i2 + 1;
            byte b2 = segment.f2487a[i2];
            J(size() - 1);
            if (i4 == i3) {
                this.f2445d = segment.b();
                SegmentPool.b(segment);
            } else {
                segment.f2488b = i4;
            }
            return b2;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) {
        Intrinsics.f(sink, "sink");
        int i2 = 0;
        while (i2 < sink.length) {
            int read = read(sink, i2, sink.length - i2);
            if (read != -1) {
                i2 += read;
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (size() >= 4) {
            Segment segment = this.f2445d;
            Intrinsics.c(segment);
            int i2 = segment.f2488b;
            int i3 = segment.f2489c;
            if (i3 - i2 < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.f2487a;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
            int i7 = i5 + 1;
            int i8 = i6 | ((bArr[i5] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | (bArr[i7] & 255);
            J(size() - 4);
            if (i9 == i3) {
                this.f2445d = segment.b();
                SegmentPool.b(segment);
            } else {
                segment.f2488b = i9;
            }
            return i10;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (size() >= 2) {
            Segment segment = this.f2445d;
            Intrinsics.c(segment);
            int i2 = segment.f2488b;
            int i3 = segment.f2489c;
            if (i3 - i2 < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.f2487a;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
            J(size() - 2);
            if (i5 == i3) {
                this.f2445d = segment.b();
                SegmentPool.b(segment);
            } else {
                segment.f2488b = i5;
            }
            return (short) i6;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public long s(ByteString bytes) {
        Intrinsics.f(bytes, "bytes");
        return r(bytes, 0L);
    }

    public final long size() {
        return this.f2446e;
    }

    @Override // okio.BufferedSource
    public void skip(long j2) {
        while (j2 > 0) {
            Segment segment = this.f2445d;
            if (segment != null) {
                int min = (int) Math.min(j2, segment.f2489c - segment.f2488b);
                long j3 = min;
                J(size() - j3);
                j2 -= j3;
                int i2 = segment.f2488b + min;
                segment.f2488b = i2;
                if (i2 == segment.f2489c) {
                    this.f2445d = segment.b();
                    SegmentPool.b(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.BufferedSource
    public Buffer t() {
        return this;
    }

    public String toString() {
        return O().toString();
    }

    @Override // okio.BufferedSource
    public boolean u() {
        return this.f2446e == 0;
    }

    @Override // okio.BufferedSource
    public byte[] v(long j2) {
        boolean z2;
        if (j2 >= 0 && j2 <= 2147483647L) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (size() >= j2) {
                byte[] bArr = new byte[(int) j2];
                readFully(bArr);
                return bArr;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(("byteCount: " + j2).toString());
    }

    public long w(ByteString targetBytes, long j2) {
        boolean z2;
        int i2;
        int i3;
        Intrinsics.f(targetBytes, "targetBytes");
        long j3 = 0;
        if (j2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            Segment segment = this.f2445d;
            if (segment == null) {
                return -1L;
            }
            if (size() - j2 < j2) {
                j3 = size();
                while (j3 > j2) {
                    segment = segment.f2493g;
                    Intrinsics.c(segment);
                    j3 -= segment.f2489c - segment.f2488b;
                }
                if (targetBytes.u() == 2) {
                    byte f2 = targetBytes.f(0);
                    byte f3 = targetBytes.f(1);
                    while (j3 < size()) {
                        byte[] bArr = segment.f2487a;
                        i2 = (int) ((segment.f2488b + j2) - j3);
                        int i4 = segment.f2489c;
                        while (i2 < i4) {
                            byte b2 = bArr[i2];
                            if (b2 != f2 && b2 != f3) {
                                i2++;
                            }
                            i3 = segment.f2488b;
                        }
                        j3 += segment.f2489c - segment.f2488b;
                        segment = segment.f2492f;
                        Intrinsics.c(segment);
                        j2 = j3;
                    }
                    return -1L;
                }
                byte[] l2 = targetBytes.l();
                while (j3 < size()) {
                    byte[] bArr2 = segment.f2487a;
                    i2 = (int) ((segment.f2488b + j2) - j3);
                    int i5 = segment.f2489c;
                    while (i2 < i5) {
                        byte b3 = bArr2[i2];
                        for (byte b4 : l2) {
                            if (b3 == b4) {
                                i3 = segment.f2488b;
                            }
                        }
                        i2++;
                    }
                    j3 += segment.f2489c - segment.f2488b;
                    segment = segment.f2492f;
                    Intrinsics.c(segment);
                    j2 = j3;
                }
                return -1L;
            }
            while (true) {
                long j4 = (segment.f2489c - segment.f2488b) + j3;
                if (j4 > j2) {
                    break;
                }
                segment = segment.f2492f;
                Intrinsics.c(segment);
                j3 = j4;
            }
            if (targetBytes.u() == 2) {
                byte f4 = targetBytes.f(0);
                byte f5 = targetBytes.f(1);
                while (j3 < size()) {
                    byte[] bArr3 = segment.f2487a;
                    i2 = (int) ((segment.f2488b + j2) - j3);
                    int i6 = segment.f2489c;
                    while (i2 < i6) {
                        byte b5 = bArr3[i2];
                        if (b5 != f4 && b5 != f5) {
                            i2++;
                        }
                        i3 = segment.f2488b;
                    }
                    j3 += segment.f2489c - segment.f2488b;
                    segment = segment.f2492f;
                    Intrinsics.c(segment);
                    j2 = j3;
                }
                return -1L;
            }
            byte[] l3 = targetBytes.l();
            while (j3 < size()) {
                byte[] bArr4 = segment.f2487a;
                i2 = (int) ((segment.f2488b + j2) - j3);
                int i7 = segment.f2489c;
                while (i2 < i7) {
                    byte b6 = bArr4[i2];
                    for (byte b7 : l3) {
                        if (b6 == b7) {
                            i3 = segment.f2488b;
                        }
                    }
                    i2++;
                }
                j3 += segment.f2489c - segment.f2488b;
                segment = segment.f2492f;
                Intrinsics.c(segment);
                j2 = j3;
            }
            return -1L;
            return (i2 - i3) + j3;
        }
        throw new IllegalArgumentException(("fromIndex < 0: " + j2).toString());
    }

    public boolean x(long j2, ByteString bytes, int i2, int i3) {
        Intrinsics.f(bytes, "bytes");
        if (j2 < 0 || i2 < 0 || i3 < 0 || size() - j2 < i3 || bytes.u() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (o(i4 + j2) != bytes.f(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public long y(ByteString targetBytes) {
        Intrinsics.f(targetBytes, "targetBytes");
        return w(targetBytes, 0L);
    }

    public byte[] z() {
        return v(size());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) {
        Intrinsics.f(source, "source");
        int remaining = source.remaining();
        int i2 = remaining;
        while (i2 > 0) {
            Segment R = R(1);
            int min = Math.min(i2, 8192 - R.f2489c);
            source.get(R.f2487a, R.f2489c, min);
            i2 -= min;
            R.f2489c += min;
        }
        this.f2446e += remaining;
        return remaining;
    }

    public int read(byte[] sink, int i2, int i3) {
        Intrinsics.f(sink, "sink");
        _UtilKt.b(sink.length, i2, i3);
        Segment segment = this.f2445d;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.f2489c - segment.f2488b);
        byte[] bArr = segment.f2487a;
        int i4 = segment.f2488b;
        ArraysKt___ArraysJvmKt.d(bArr, sink, i2, i4, i4 + min);
        segment.f2488b += min;
        J(size() - min);
        if (segment.f2488b == segment.f2489c) {
            this.f2445d = segment.b();
            SegmentPool.b(segment);
        }
        return min;
    }
}
