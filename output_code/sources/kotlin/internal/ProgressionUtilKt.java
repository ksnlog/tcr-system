package kotlin.internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ProgressionUtilKt {
    private static final int a(int i2, int i3, int i4) {
        return c(c(i2, i4) - c(i3, i4), i4);
    }

    public static final int b(int i2, int i3, int i4) {
        if (i4 > 0) {
            if (i2 < i3) {
                return i3 - a(i3, i2, i4);
            }
            return i3;
        } else if (i4 < 0) {
            if (i2 > i3) {
                return i3 + a(i2, i3, -i4);
            }
            return i3;
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    private static final int c(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 >= 0 ? i4 : i4 + i3;
    }
}
