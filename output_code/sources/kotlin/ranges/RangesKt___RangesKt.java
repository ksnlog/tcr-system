package kotlin.ranges;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class RangesKt___RangesKt extends RangesKt__RangesKt {
    public static int a(int i2, int i3) {
        return i2 < i3 ? i3 : i2;
    }

    public static long b(long j2, long j3) {
        return j2 < j3 ? j3 : j2;
    }

    public static int c(int i2, int i3) {
        return i2 > i3 ? i3 : i2;
    }

    public static long d(long j2, long j3) {
        return j2 > j3 ? j3 : j2;
    }

    public static IntProgression e(int i2, int i3) {
        return IntProgression.f891g.a(i2, i3, -1);
    }
}
