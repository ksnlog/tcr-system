package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Options;
import okio.Segment;
import okio._JvmPlatformKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _BufferKt {

    /* renamed from: a */
    private static final byte[] f2511a = _JvmPlatformKt.a("0123456789abcdef");

    public static final byte[] a() {
        return f2511a;
    }

    public static final boolean b(Segment segment, int i2, byte[] bytes, int i3, int i4) {
        Intrinsics.f(segment, "segment");
        Intrinsics.f(bytes, "bytes");
        int i5 = segment.f2489c;
        byte[] bArr = segment.f2487a;
        while (i3 < i4) {
            if (i2 == i5) {
                segment = segment.f2492f;
                Intrinsics.c(segment);
                byte[] bArr2 = segment.f2487a;
                bArr = bArr2;
                i2 = segment.f2488b;
                i5 = segment.f2489c;
            }
            if (bArr[i2] != bytes[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    public static final String c(Buffer buffer, long j2) {
        Intrinsics.f(buffer, "<this>");
        if (j2 > 0) {
            long j3 = j2 - 1;
            if (buffer.o(j3) == 13) {
                String g2 = buffer.g(j3);
                buffer.skip(2L);
                return g2;
            }
        }
        String g3 = buffer.g(j2);
        buffer.skip(1L);
        return g3;
    }

    public static final int d(Buffer buffer, Options options, boolean z2) {
        int i2;
        int i3;
        boolean z3;
        int i4;
        int i5;
        Segment segment;
        Intrinsics.f(buffer, "<this>");
        Intrinsics.f(options, "options");
        Segment segment2 = buffer.f2445d;
        if (segment2 == null) {
            if (z2) {
                return -2;
            }
            return -1;
        }
        byte[] bArr = segment2.f2487a;
        int i6 = segment2.f2488b;
        int i7 = segment2.f2489c;
        int[] e2 = options.e();
        Segment segment3 = segment2;
        int i8 = 0;
        int i9 = -1;
        loop0: while (true) {
            int i10 = i8 + 1;
            int i11 = e2[i8];
            int i12 = i10 + 1;
            int i13 = e2[i10];
            if (i13 != -1) {
                i9 = i13;
            }
            if (segment3 == null) {
                break;
            }
            if (i11 < 0) {
                int i14 = i12 + (i11 * (-1));
                while (true) {
                    int i15 = i6 + 1;
                    int i16 = i12 + 1;
                    if ((bArr[i6] & 255) != e2[i12]) {
                        return i9;
                    }
                    if (i16 == i14) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (i15 == i7) {
                        Intrinsics.c(segment3);
                        Segment segment4 = segment3.f2492f;
                        Intrinsics.c(segment4);
                        i5 = segment4.f2488b;
                        byte[] bArr2 = segment4.f2487a;
                        i4 = segment4.f2489c;
                        if (segment4 == segment2) {
                            if (!z3) {
                                break loop0;
                            }
                            bArr = bArr2;
                            segment = null;
                        } else {
                            segment = segment4;
                            bArr = bArr2;
                        }
                    } else {
                        Segment segment5 = segment3;
                        i4 = i7;
                        i5 = i15;
                        segment = segment5;
                    }
                    if (z3) {
                        i3 = e2[i16];
                        i2 = i5;
                        i7 = i4;
                        segment3 = segment;
                        break;
                    }
                    i6 = i5;
                    i7 = i4;
                    i12 = i16;
                    segment3 = segment;
                }
            } else {
                i2 = i6 + 1;
                int i17 = bArr[i6] & 255;
                int i18 = i12 + i11;
                while (i12 != i18) {
                    if (i17 == e2[i12]) {
                        i3 = e2[i12 + i11];
                        if (i2 == i7) {
                            segment3 = segment3.f2492f;
                            Intrinsics.c(segment3);
                            i2 = segment3.f2488b;
                            bArr = segment3.f2487a;
                            i7 = segment3.f2489c;
                            if (segment3 == segment2) {
                                segment3 = null;
                            }
                        }
                    } else {
                        i12++;
                    }
                }
                return i9;
            }
            if (i3 >= 0) {
                return i3;
            }
            i8 = -i3;
            i6 = i2;
        }
        if (z2) {
            return -2;
        }
        return i9;
    }

    public static /* synthetic */ int e(Buffer buffer, Options options, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return d(buffer, options, z2);
    }
}
