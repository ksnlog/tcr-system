package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Buffer;
import okio.internal._ByteStringKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _UtilKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Buffer.UnsafeCursor f2509a = new Buffer.UnsafeCursor();

    /* renamed from: b  reason: collision with root package name */
    private static final int f2510b = -1234567890;

    public static final boolean a(byte[] a2, int i2, byte[] b2, int i3, int i4) {
        Intrinsics.f(a2, "a");
        Intrinsics.f(b2, "b");
        for (int i5 = 0; i5 < i4; i5++) {
            if (a2[i5 + i2] != b2[i5 + i3]) {
                return false;
            }
        }
        return true;
    }

    public static final void b(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException("size=" + j2 + " offset=" + j3 + " byteCount=" + j4);
        }
    }

    public static final int c(ByteString byteString, int i2) {
        Intrinsics.f(byteString, "<this>");
        if (i2 == f2510b) {
            return byteString.u();
        }
        return i2;
    }

    public static final int d(int i2) {
        return ((i2 & 255) << 24) | (((-16777216) & i2) >>> 24) | ((16711680 & i2) >>> 8) | ((65280 & i2) << 8);
    }

    public static final short e(short s2) {
        int i2 = s2 & 65535;
        return (short) (((i2 & 255) << 8) | ((65280 & i2) >>> 8));
    }

    public static final String f(byte b2) {
        String d2;
        d2 = StringsKt__StringsJVMKt.d(new char[]{_ByteStringKt.f()[(b2 >> 4) & 15], _ByteStringKt.f()[b2 & 15]});
        return d2;
    }

    public static final String g(int i2) {
        String e2;
        if (i2 == 0) {
            return "0";
        }
        int i3 = 0;
        char[] cArr = {_ByteStringKt.f()[(i2 >> 28) & 15], _ByteStringKt.f()[(i2 >> 24) & 15], _ByteStringKt.f()[(i2 >> 20) & 15], _ByteStringKt.f()[(i2 >> 16) & 15], _ByteStringKt.f()[(i2 >> 12) & 15], _ByteStringKt.f()[(i2 >> 8) & 15], _ByteStringKt.f()[(i2 >> 4) & 15], _ByteStringKt.f()[i2 & 15]};
        while (i3 < 8 && cArr[i3] == '0') {
            i3++;
        }
        e2 = StringsKt__StringsJVMKt.e(cArr, i3, 8);
        return e2;
    }
}
