package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http2 {

    /* renamed from: a  reason: collision with root package name */
    static final ByteString f2261a = ByteString.e("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f2262b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* renamed from: c  reason: collision with root package name */
    static final String[] f2263c = new String[64];

    /* renamed from: d  reason: collision with root package name */
    static final String[] f2264d = new String[256];

    static {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = f2264d;
            if (i3 >= strArr.length) {
                break;
            }
            strArr[i3] = Util.q("%8s", Integer.toBinaryString(i3)).replace(' ', '0');
            i3++;
        }
        String[] strArr2 = f2263c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        strArr2[1 | 8] = strArr2[1] + "|PADDED";
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i4 = 0; i4 < 3; i4++) {
            int i5 = iArr2[i4];
            int i6 = iArr[0];
            String[] strArr3 = f2263c;
            int i7 = i6 | i5;
            strArr3[i7] = strArr3[i6] + '|' + strArr3[i5];
            strArr3[i7 | 8] = strArr3[i6] + '|' + strArr3[i5] + "|PADDED";
        }
        while (true) {
            String[] strArr4 = f2263c;
            if (i2 < strArr4.length) {
                if (strArr4[i2] == null) {
                    strArr4[i2] = f2264d[i2];
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private Http2() {
    }

    static String a(byte b2, byte b3) {
        String str;
        if (b3 == 0) {
            return "";
        }
        if (b2 != 2 && b2 != 3) {
            if (b2 != 4 && b2 != 6) {
                if (b2 != 7 && b2 != 8) {
                    String[] strArr = f2263c;
                    if (b3 < strArr.length) {
                        str = strArr[b3];
                    } else {
                        str = f2264d[b3];
                    }
                    if (b2 == 5 && (b3 & 4) != 0) {
                        return str.replace("HEADERS", "PUSH_PROMISE");
                    }
                    if (b2 == 0 && (b3 & 32) != 0) {
                        return str.replace("PRIORITY", "COMPRESSED");
                    }
                    return str;
                }
            } else if (b3 == 1) {
                return "ACK";
            } else {
                return f2264d[b3];
            }
        }
        return f2264d[b3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(boolean z2, int i2, int i3, byte b2, byte b3) {
        String q2;
        String str;
        String[] strArr = f2262b;
        if (b2 < strArr.length) {
            q2 = strArr[b2];
        } else {
            q2 = Util.q("0x%02x", Byte.valueOf(b2));
        }
        String a2 = a(b2, b3);
        Object[] objArr = new Object[5];
        if (z2) {
            str = "<<";
        } else {
            str = ">>";
        }
        objArr[0] = str;
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(i3);
        objArr[3] = q2;
        objArr[4] = a2;
        return Util.q("%s 0x%08x %5d %-13s %s", objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.q(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IOException d(String str, Object... objArr) {
        throw new IOException(Util.q(str, objArr));
    }
}
