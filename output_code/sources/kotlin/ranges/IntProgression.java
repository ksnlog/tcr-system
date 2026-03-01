package kotlin.ranges;

import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class IntProgression implements Iterable<Integer> {

    /* renamed from: g  reason: collision with root package name */
    public static final Companion f891g = new Companion(null);

    /* renamed from: d  reason: collision with root package name */
    private final int f892d;

    /* renamed from: e  reason: collision with root package name */
    private final int f893e;

    /* renamed from: f  reason: collision with root package name */
    private final int f894f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IntProgression a(int i2, int i3, int i4) {
            return new IntProgression(i2, i3, i4);
        }
    }

    public IntProgression(int i2, int i3, int i4) {
        if (i4 != 0) {
            if (i4 != Integer.MIN_VALUE) {
                this.f892d = i2;
                this.f893e = ProgressionUtilKt.b(i2, i3, i4);
                this.f894f = i4;
                return;
            }
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        throw new IllegalArgumentException("Step must be non-zero.");
    }

    public final int a() {
        return this.f892d;
    }

    public final int b() {
        return this.f893e;
    }

    public final int c() {
        return this.f894f;
    }

    @Override // java.lang.Iterable
    /* renamed from: d */
    public IntIterator iterator() {
        return new IntProgressionIterator(this.f892d, this.f893e, this.f894f);
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntProgression) {
            if (!isEmpty() || !((IntProgression) obj).isEmpty()) {
                IntProgression intProgression = (IntProgression) obj;
                if (this.f892d != intProgression.f892d || this.f893e != intProgression.f893e || this.f894f != intProgression.f894f) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f892d * 31) + this.f893e) * 31) + this.f894f;
    }

    public boolean isEmpty() {
        if (this.f894f > 0) {
            if (this.f892d > this.f893e) {
                return true;
            }
        } else if (this.f892d < this.f893e) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        if (this.f894f > 0) {
            sb = new StringBuilder();
            sb.append(this.f892d);
            sb.append("..");
            sb.append(this.f893e);
            sb.append(" step ");
            i2 = this.f894f;
        } else {
            sb = new StringBuilder();
            sb.append(this.f892d);
            sb.append(" downTo ");
            sb.append(this.f893e);
            sb.append(" step ");
            i2 = -this.f894f;
        }
        sb.append(i2);
        return sb.toString();
    }
}
