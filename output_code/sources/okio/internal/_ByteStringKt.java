package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _ByteStringKt {

    /* renamed from: a */
    private static final char[] f2512a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX WARN: Removed duplicated region for block: B:553:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:556:0x007a A[EDGE_INSN: B:556:0x007a->B:350:0x007a ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0159 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:576:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:581:0x01fc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int c(byte[] r19, int r20) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._ByteStringKt.c(byte[], int):int");
    }

    public static final void d(ByteString byteString, Buffer buffer, int i2, int i3) {
        Intrinsics.f(byteString, "<this>");
        Intrinsics.f(buffer, "buffer");
        buffer.write(byteString.g(), i2, i3);
    }

    public static final int e(char c2) {
        boolean z2 = true;
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (!('a' <= c2 && c2 < 'g')) {
            c3 = 'A';
            if ('A' > c2 || c2 >= 'G') {
                z2 = false;
            }
            if (!z2) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c2);
            }
        }
        return (c2 - c3) + 10;
    }

    public static final char[] f() {
        return f2512a;
    }
}
