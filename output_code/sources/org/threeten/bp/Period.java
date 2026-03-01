package org.threeten.bp;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.threeten.bp.chrono.ChronoPeriod;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Period extends ChronoPeriod implements Serializable {

    /* renamed from: g  reason: collision with root package name */
    public static final Period f3085g = new Period(0, 0, 0);

    /* renamed from: h  reason: collision with root package name */
    private static final Pattern f3086h = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);

    /* renamed from: d  reason: collision with root package name */
    private final int f3087d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3088e;

    /* renamed from: f  reason: collision with root package name */
    private final int f3089f;

    private Period(int i2, int i3, int i4) {
        this.f3087d = i2;
        this.f3088e = i3;
        this.f3089f = i4;
    }

    private static Period b(int i2, int i3, int i4) {
        if ((i2 | i3 | i4) == 0) {
            return f3085g;
        }
        return new Period(i2, i3, i4);
    }

    public static Period d(int i2) {
        return b(0, 0, i2);
    }

    private Object readResolve() {
        if ((this.f3087d | this.f3088e | this.f3089f) == 0) {
            return f3085g;
        }
        return this;
    }

    @Override // org.threeten.bp.temporal.TemporalAmount
    public Temporal a(Temporal temporal) {
        Jdk8Methods.i(temporal, "temporal");
        int i2 = this.f3087d;
        if (i2 != 0) {
            if (this.f3088e != 0) {
                temporal = temporal.i(e(), ChronoUnit.MONTHS);
            } else {
                temporal = temporal.i(i2, ChronoUnit.YEARS);
            }
        } else {
            int i3 = this.f3088e;
            if (i3 != 0) {
                temporal = temporal.i(i3, ChronoUnit.MONTHS);
            }
        }
        int i4 = this.f3089f;
        if (i4 != 0) {
            return temporal.i(i4, ChronoUnit.DAYS);
        }
        return temporal;
    }

    public boolean c() {
        return this == f3085g;
    }

    public long e() {
        return (this.f3087d * 12) + this.f3088e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        if (this.f3087d == period.f3087d && this.f3088e == period.f3088e && this.f3089f == period.f3089f) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f3087d + Integer.rotateLeft(this.f3088e, 8) + Integer.rotateLeft(this.f3089f, 16);
    }

    public String toString() {
        if (this == f3085g) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('P');
        int i2 = this.f3087d;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('Y');
        }
        int i3 = this.f3088e;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('M');
        }
        int i4 = this.f3089f;
        if (i4 != 0) {
            sb.append(i4);
            sb.append('D');
        }
        return sb.toString();
    }
}
