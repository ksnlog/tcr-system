package okio;

import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _Base64Kt {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f2507a;

    /* renamed from: b  reason: collision with root package name */
    private static final byte[] f2508b;

    static {
        ByteString.Companion companion = ByteString.f2454g;
        f2507a = companion.b("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").g();
        f2508b = companion.b("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").g();
    }

    public static final String a(byte[] bArr, byte[] map) {
        Intrinsics.f(bArr, "<this>");
        Intrinsics.f(map, "map");
        byte[] bArr2 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            int i5 = i4 + 1;
            byte b3 = bArr[i4];
            int i6 = i5 + 1;
            byte b4 = bArr[i5];
            int i7 = i3 + 1;
            bArr2[i3] = map[(b2 & 255) >> 2];
            int i8 = i7 + 1;
            bArr2[i7] = map[((b2 & 3) << 4) | ((b3 & 255) >> 4)];
            int i9 = i8 + 1;
            bArr2[i8] = map[((b3 & 15) << 2) | ((b4 & 255) >> 6)];
            i3 = i9 + 1;
            bArr2[i9] = map[b4 & 63];
            i2 = i6;
        }
        int length2 = bArr.length - length;
        if (length2 != 1) {
            if (length2 == 2) {
                int i10 = i2 + 1;
                byte b5 = bArr[i2];
                byte b6 = bArr[i10];
                int i11 = i3 + 1;
                bArr2[i3] = map[(b5 & 255) >> 2];
                int i12 = i11 + 1;
                bArr2[i11] = map[((b5 & 3) << 4) | ((b6 & 255) >> 4)];
                bArr2[i12] = map[(b6 & 15) << 2];
                bArr2[i12 + 1] = 61;
            }
        } else {
            byte b7 = bArr[i2];
            int i13 = i3 + 1;
            bArr2[i3] = map[(b7 & 255) >> 2];
            int i14 = i13 + 1;
            bArr2[i13] = map[(b7 & 3) << 4];
            bArr2[i14] = 61;
            bArr2[i14 + 1] = 61;
        }
        return _JvmPlatformKt.b(bArr2);
    }

    public static /* synthetic */ String b(byte[] bArr, byte[] bArr2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr2 = f2507a;
        }
        return a(bArr, bArr2);
    }
}
