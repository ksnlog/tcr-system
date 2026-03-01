package okio.internal;

import kotlin.jvm.internal.Intrinsics;
import okio.SegmentedByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _SegmentedByteStringKt {
    public static final int a(int[] iArr, int i2, int i3, int i4) {
        Intrinsics.f(iArr, "<this>");
        int i5 = i4 - 1;
        while (i3 <= i5) {
            int i6 = (i3 + i5) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i2) {
                i3 = i6 + 1;
            } else if (i7 > i2) {
                i5 = i6 - 1;
            } else {
                return i6;
            }
        }
        return (-i3) - 1;
    }

    public static final int b(SegmentedByteString segmentedByteString, int i2) {
        Intrinsics.f(segmentedByteString, "<this>");
        int a2 = a(segmentedByteString.A(), i2 + 1, 0, segmentedByteString.B().length);
        if (a2 < 0) {
            return a2 ^ (-1);
        }
        return a2;
    }
}
