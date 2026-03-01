package kotlin.ranges;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class IntRange extends IntProgression {

    /* renamed from: h  reason: collision with root package name */
    public static final Companion f899h = new Companion(null);

    /* renamed from: i  reason: collision with root package name */
    private static final IntRange f900i = new IntRange(1, 0);

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IntRange(int i2, int i3) {
        super(i2, i3, 1);
    }

    public boolean e(int i2) {
        return a() <= i2 && i2 <= b();
    }

    @Override // kotlin.ranges.IntProgression
    public boolean equals(Object obj) {
        if (obj instanceof IntRange) {
            if (!isEmpty() || !((IntRange) obj).isEmpty()) {
                IntRange intRange = (IntRange) obj;
                if (a() != intRange.a() || b() != intRange.b()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.IntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (a() * 31) + b();
    }

    @Override // kotlin.ranges.IntProgression
    public boolean isEmpty() {
        return a() > b();
    }

    @Override // kotlin.ranges.IntProgression
    public String toString() {
        return a() + ".." + b();
    }
}
