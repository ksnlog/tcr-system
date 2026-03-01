package okio;

import java.security.MessageDigest;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._SegmentedByteStringKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SegmentedByteString extends ByteString {

    /* renamed from: i  reason: collision with root package name */
    private final transient byte[][] f2499i;

    /* renamed from: j  reason: collision with root package name */
    private final transient int[] f2500j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(byte[][] segments, int[] directory) {
        super(ByteString.f2455h.g());
        Intrinsics.f(segments, "segments");
        Intrinsics.f(directory, "directory");
        this.f2499i = segments;
        this.f2500j = directory;
    }

    private final ByteString C() {
        return new ByteString(x());
    }

    public final int[] A() {
        return this.f2500j;
    }

    public final byte[][] B() {
        return this.f2499i;
    }

    @Override // okio.ByteString
    public String a() {
        return C().a();
    }

    @Override // okio.ByteString
    public ByteString d(String algorithm) {
        Intrinsics.f(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        int length = B().length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = A()[length + i2];
            int i5 = A()[i2];
            messageDigest.update(B()[i2], i4, i5 - i3);
            i2++;
            i3 = i5;
        }
        byte[] digestBytes = messageDigest.digest();
        Intrinsics.e(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.u() == u() && o(0, byteString, 0, u())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int h2 = h();
        if (h2 == 0) {
            int length = B().length;
            int i2 = 0;
            int i3 = 1;
            int i4 = 0;
            while (i2 < length) {
                int i5 = A()[length + i2];
                int i6 = A()[i2];
                byte[] bArr = B()[i2];
                int i7 = (i6 - i4) + i5;
                while (i5 < i7) {
                    i3 = (i3 * 31) + bArr[i5];
                    i5++;
                }
                i2++;
                i4 = i6;
            }
            q(i3);
            return i3;
        }
        return h2;
    }

    @Override // okio.ByteString
    public int i() {
        return A()[B().length - 1];
    }

    @Override // okio.ByteString
    public String k() {
        return C().k();
    }

    @Override // okio.ByteString
    public byte[] l() {
        return x();
    }

    @Override // okio.ByteString
    public byte m(int i2) {
        int i3;
        _UtilKt.b(A()[B().length - 1], i2, 1L);
        int b2 = _SegmentedByteStringKt.b(this, i2);
        if (b2 == 0) {
            i3 = 0;
        } else {
            i3 = A()[b2 - 1];
        }
        return B()[b2][(i2 - i3) + A()[B().length + b2]];
    }

    @Override // okio.ByteString
    public boolean o(int i2, ByteString other, int i3, int i4) {
        int i5;
        Intrinsics.f(other, "other");
        if (i2 < 0 || i2 > u() - i4) {
            return false;
        }
        int i6 = i4 + i2;
        int b2 = _SegmentedByteStringKt.b(this, i2);
        while (i2 < i6) {
            if (b2 == 0) {
                i5 = 0;
            } else {
                i5 = A()[b2 - 1];
            }
            int i7 = A()[B().length + b2];
            int min = Math.min(i6, (A()[b2] - i5) + i5) - i2;
            if (!other.p(i3, B()[b2], i7 + (i2 - i5), min)) {
                return false;
            }
            i3 += min;
            i2 += min;
            b2++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean p(int i2, byte[] other, int i3, int i4) {
        int i5;
        Intrinsics.f(other, "other");
        if (i2 < 0 || i2 > u() - i4 || i3 < 0 || i3 > other.length - i4) {
            return false;
        }
        int i6 = i4 + i2;
        int b2 = _SegmentedByteStringKt.b(this, i2);
        while (i2 < i6) {
            if (b2 == 0) {
                i5 = 0;
            } else {
                i5 = A()[b2 - 1];
            }
            int i7 = A()[B().length + b2];
            int min = Math.min(i6, (A()[b2] - i5) + i5) - i2;
            if (!_UtilKt.a(B()[b2], i7 + (i2 - i5), other, i3, min)) {
                return false;
            }
            i3 += min;
            i2 += min;
            b2++;
        }
        return true;
    }

    @Override // okio.ByteString
    public String toString() {
        return C().toString();
    }

    @Override // okio.ByteString
    public ByteString w() {
        return C().w();
    }

    @Override // okio.ByteString
    public byte[] x() {
        byte[] bArr = new byte[u()];
        int length = B().length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = A()[length + i2];
            int i6 = A()[i2];
            int i7 = i6 - i3;
            ArraysKt___ArraysJvmKt.d(B()[i2], bArr, i4, i5, i5 + i7);
            i4 += i7;
            i2++;
            i3 = i6;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public void z(Buffer buffer, int i2, int i3) {
        int i4;
        Intrinsics.f(buffer, "buffer");
        int i5 = i2 + i3;
        int b2 = _SegmentedByteStringKt.b(this, i2);
        while (i2 < i5) {
            if (b2 == 0) {
                i4 = 0;
            } else {
                i4 = A()[b2 - 1];
            }
            int i6 = A()[B().length + b2];
            int min = Math.min(i5, (A()[b2] - i4) + i4) - i2;
            int i7 = i6 + (i2 - i4);
            Segment segment = new Segment(B()[b2], i7, i7 + min, true, false);
            Segment segment2 = buffer.f2445d;
            if (segment2 == null) {
                segment.f2493g = segment;
                segment.f2492f = segment;
                buffer.f2445d = segment;
            } else {
                Intrinsics.c(segment2);
                Segment segment3 = segment2.f2493g;
                Intrinsics.c(segment3);
                segment3.c(segment);
            }
            i2 += min;
            b2++;
        }
        buffer.J(buffer.size() + i3);
    }
}
