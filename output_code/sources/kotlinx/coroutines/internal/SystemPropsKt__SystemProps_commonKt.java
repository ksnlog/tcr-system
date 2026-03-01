package kotlinx.coroutines.internal;

import kotlin.text.StringsKt__StringNumberConversionsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final int a(String str, int i2, int i3, int i4) {
        return (int) SystemPropsKt.c(str, i2, i3, i4);
    }

    public static final long b(String str, long j2, long j3, long j4) {
        Long b2;
        String d2 = SystemPropsKt.d(str);
        if (d2 == null) {
            return j2;
        }
        b2 = StringsKt__StringNumberConversionsKt.b(d2);
        if (b2 != null) {
            long longValue = b2.longValue();
            boolean z2 = false;
            if (j3 <= longValue && longValue <= j4) {
                z2 = true;
            }
            if (z2) {
                return longValue;
            }
            throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + longValue + '\'').toString());
        }
        throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + d2 + '\'').toString());
    }

    public static final String c(String str, String str2) {
        String d2 = SystemPropsKt.d(str);
        return d2 == null ? str2 : d2;
    }

    public static final boolean d(String str, boolean z2) {
        String d2 = SystemPropsKt.d(str);
        return d2 != null ? Boolean.parseBoolean(d2) : z2;
    }

    public static /* synthetic */ int e(String str, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return SystemPropsKt.b(str, i2, i3, i4);
    }

    public static /* synthetic */ long f(String str, long j2, long j3, long j4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j3 = 1;
        }
        long j5 = j3;
        if ((i2 & 8) != 0) {
            j4 = Long.MAX_VALUE;
        }
        return SystemPropsKt.c(str, j2, j5, j4);
    }
}
