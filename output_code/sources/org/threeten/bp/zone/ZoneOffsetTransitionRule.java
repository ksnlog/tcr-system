package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAdjusters;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZoneOffsetTransitionRule implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final Month f3431d;

    /* renamed from: e  reason: collision with root package name */
    private final byte f3432e;

    /* renamed from: f  reason: collision with root package name */
    private final DayOfWeek f3433f;

    /* renamed from: g  reason: collision with root package name */
    private final LocalTime f3434g;

    /* renamed from: h  reason: collision with root package name */
    private final int f3435h;

    /* renamed from: i  reason: collision with root package name */
    private final TimeDefinition f3436i;

    /* renamed from: j  reason: collision with root package name */
    private final ZoneOffset f3437j;

    /* renamed from: k  reason: collision with root package name */
    private final ZoneOffset f3438k;

    /* renamed from: l  reason: collision with root package name */
    private final ZoneOffset f3439l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.zone.ZoneOffsetTransitionRule$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3440a;

        static {
            int[] iArr = new int[TimeDefinition.values().length];
            f3440a = iArr;
            try {
                iArr[TimeDefinition.UTC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3440a[TimeDefinition.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum TimeDefinition {
        UTC,
        WALL,
        STANDARD;

        public LocalDateTime a(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
            int i2 = AnonymousClass1.f3440a[ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return localDateTime;
                }
                return localDateTime.W(zoneOffset2.x() - zoneOffset.x());
            }
            return localDateTime.W(zoneOffset2.x() - ZoneOffset.f3108k.x());
        }
    }

    ZoneOffsetTransitionRule(Month month, int i2, DayOfWeek dayOfWeek, LocalTime localTime, int i3, TimeDefinition timeDefinition, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        this.f3431d = month;
        this.f3432e = (byte) i2;
        this.f3433f = dayOfWeek;
        this.f3434g = localTime;
        this.f3435h = i3;
        this.f3436i = timeDefinition;
        this.f3437j = zoneOffset;
        this.f3438k = zoneOffset2;
        this.f3439l = zoneOffset3;
    }

    private void a(StringBuilder sb, long j2) {
        if (j2 < 10) {
            sb.append(0);
        }
        sb.append(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneOffsetTransitionRule c(DataInput dataInput) {
        DayOfWeek m2;
        int i2;
        int i3;
        int x2;
        int x3;
        int readInt = dataInput.readInt();
        Month q2 = Month.q(readInt >>> 28);
        int i4 = ((264241152 & readInt) >>> 22) - 32;
        int i5 = (3670016 & readInt) >>> 19;
        if (i5 == 0) {
            m2 = null;
        } else {
            m2 = DayOfWeek.m(i5);
        }
        DayOfWeek dayOfWeek = m2;
        int i6 = (507904 & readInt) >>> 14;
        TimeDefinition timeDefinition = TimeDefinition.values()[(readInt & 12288) >>> 12];
        int i7 = (readInt & 4080) >>> 4;
        int i8 = (readInt & 12) >>> 2;
        int i9 = readInt & 3;
        if (i6 == 31) {
            i2 = dataInput.readInt();
        } else {
            i2 = i6 * 3600;
        }
        if (i7 == 255) {
            i3 = dataInput.readInt();
        } else {
            i3 = (i7 - 128) * 900;
        }
        ZoneOffset A = ZoneOffset.A(i3);
        if (i8 == 3) {
            x2 = dataInput.readInt();
        } else {
            x2 = A.x() + (i8 * 1800);
        }
        ZoneOffset A2 = ZoneOffset.A(x2);
        if (i9 == 3) {
            x3 = dataInput.readInt();
        } else {
            x3 = A.x() + (i9 * 1800);
        }
        ZoneOffset A3 = ZoneOffset.A(x3);
        if (i4 >= -28 && i4 <= 31 && i4 != 0) {
            return new ZoneOffsetTransitionRule(q2, i4, dayOfWeek, LocalTime.C(Jdk8Methods.f(i2, 86400)), Jdk8Methods.d(i2, 86400), timeDefinition, A, A2, A3);
        }
        throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    public ZoneOffsetTransition b(int i2) {
        LocalDate V;
        byte b2 = this.f3432e;
        if (b2 < 0) {
            Month month = this.f3431d;
            V = LocalDate.V(i2, month, month.n(IsoChronology.f3167h.u(i2)) + 1 + this.f3432e);
            DayOfWeek dayOfWeek = this.f3433f;
            if (dayOfWeek != null) {
                V = V.x(TemporalAdjusters.b(dayOfWeek));
            }
        } else {
            V = LocalDate.V(i2, this.f3431d, b2);
            DayOfWeek dayOfWeek2 = this.f3433f;
            if (dayOfWeek2 != null) {
                V = V.x(TemporalAdjusters.a(dayOfWeek2));
            }
        }
        return new ZoneOffsetTransition(this.f3436i.a(LocalDateTime.P(V.a0(this.f3435h), this.f3434g), this.f3437j, this.f3438k), this.f3438k, this.f3439l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(DataOutput dataOutput) {
        int i2;
        int i3;
        int i4;
        int i5;
        int value;
        int L = this.f3434g.L() + (this.f3435h * 86400);
        int x2 = this.f3437j.x();
        int x3 = this.f3438k.x() - x2;
        int x4 = this.f3439l.x() - x2;
        if (L % 3600 == 0 && L <= 86400) {
            if (L == 86400) {
                i2 = 24;
            } else {
                i2 = this.f3434g.r();
            }
        } else {
            i2 = 31;
        }
        if (x2 % 900 == 0) {
            i3 = (x2 / 900) + 128;
        } else {
            i3 = 255;
        }
        if (x3 != 0 && x3 != 1800 && x3 != 3600) {
            i4 = 3;
        } else {
            i4 = x3 / 1800;
        }
        if (x4 != 0 && x4 != 1800 && x4 != 3600) {
            i5 = 3;
        } else {
            i5 = x4 / 1800;
        }
        DayOfWeek dayOfWeek = this.f3433f;
        if (dayOfWeek == null) {
            value = 0;
        } else {
            value = dayOfWeek.getValue();
        }
        dataOutput.writeInt((this.f3431d.getValue() << 28) + ((this.f3432e + 32) << 22) + (value << 19) + (i2 << 14) + (this.f3436i.ordinal() << 12) + (i3 << 4) + (i4 << 2) + i5);
        if (i2 == 31) {
            dataOutput.writeInt(L);
        }
        if (i3 == 255) {
            dataOutput.writeInt(x2);
        }
        if (i4 == 3) {
            dataOutput.writeInt(this.f3438k.x());
        }
        if (i5 == 3) {
            dataOutput.writeInt(this.f3439l.x());
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransitionRule)) {
            return false;
        }
        ZoneOffsetTransitionRule zoneOffsetTransitionRule = (ZoneOffsetTransitionRule) obj;
        if (this.f3431d == zoneOffsetTransitionRule.f3431d && this.f3432e == zoneOffsetTransitionRule.f3432e && this.f3433f == zoneOffsetTransitionRule.f3433f && this.f3436i == zoneOffsetTransitionRule.f3436i && this.f3435h == zoneOffsetTransitionRule.f3435h && this.f3434g.equals(zoneOffsetTransitionRule.f3434g) && this.f3437j.equals(zoneOffsetTransitionRule.f3437j) && this.f3438k.equals(zoneOffsetTransitionRule.f3438k) && this.f3439l.equals(zoneOffsetTransitionRule.f3439l)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int ordinal;
        int L = ((this.f3434g.L() + this.f3435h) << 15) + (this.f3431d.ordinal() << 11) + ((this.f3432e + 32) << 5);
        DayOfWeek dayOfWeek = this.f3433f;
        if (dayOfWeek == null) {
            ordinal = 7;
        } else {
            ordinal = dayOfWeek.ordinal();
        }
        return ((((L + (ordinal << 2)) + this.f3436i.ordinal()) ^ this.f3437j.hashCode()) ^ this.f3438k.hashCode()) ^ this.f3439l.hashCode();
    }

    public String toString() {
        String str;
        long j2;
        StringBuilder sb = new StringBuilder();
        sb.append("TransitionRule[");
        if (this.f3438k.compareTo(this.f3439l) > 0) {
            str = "Gap ";
        } else {
            str = "Overlap ";
        }
        sb.append(str);
        sb.append(this.f3438k);
        sb.append(" to ");
        sb.append(this.f3439l);
        sb.append(", ");
        DayOfWeek dayOfWeek = this.f3433f;
        if (dayOfWeek != null) {
            byte b2 = this.f3432e;
            if (b2 == -1) {
                sb.append(dayOfWeek.name());
                sb.append(" on or before last day of ");
                sb.append(this.f3431d.name());
            } else if (b2 < 0) {
                sb.append(dayOfWeek.name());
                sb.append(" on or before last day minus ");
                sb.append((-this.f3432e) - 1);
                sb.append(" of ");
                sb.append(this.f3431d.name());
            } else {
                sb.append(dayOfWeek.name());
                sb.append(" on or after ");
                sb.append(this.f3431d.name());
                sb.append(' ');
                sb.append((int) this.f3432e);
            }
        } else {
            sb.append(this.f3431d.name());
            sb.append(' ');
            sb.append((int) this.f3432e);
        }
        sb.append(" at ");
        if (this.f3435h == 0) {
            sb.append(this.f3434g);
        } else {
            a(sb, Jdk8Methods.e((this.f3434g.L() / 60) + (this.f3435h * 24 * 60), 60L));
            sb.append(':');
            a(sb, Jdk8Methods.g(j2, 60));
        }
        sb.append(" ");
        sb.append(this.f3436i);
        sb.append(", standard offset ");
        sb.append(this.f3437j);
        sb.append(']');
        return sb.toString();
    }
}
