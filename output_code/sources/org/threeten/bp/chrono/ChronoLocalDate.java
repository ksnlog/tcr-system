package org.threeten.bp.chrono;

import java.util.Comparator;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ChronoLocalDate extends DefaultInterfaceTemporal implements TemporalAdjuster, Comparable<ChronoLocalDate> {

    /* renamed from: d  reason: collision with root package name */
    private static final Comparator<ChronoLocalDate> f3122d = new Comparator<ChronoLocalDate>() { // from class: org.threeten.bp.chrono.ChronoLocalDate.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            return Jdk8Methods.b(chronoLocalDate.w(), chronoLocalDate2.w());
        }
    };

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) p();
        }
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.DAYS;
        }
        if (temporalQuery == TemporalQueries.b()) {
            return (R) LocalDate.W(w());
        }
        if (temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return (R) super.b(temporalQuery);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isDateBased();
        }
        if (temporalField != null && temporalField.a(this)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ChronoLocalDate) && compareTo((ChronoLocalDate) obj) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long w2 = w();
        return p().hashCode() ^ ((int) (w2 ^ (w2 >>> 32)));
    }

    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.EPOCH_DAY, w());
    }

    public ChronoLocalDateTime<?> n(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.A(this, localTime);
    }

    @Override // java.lang.Comparable
    /* renamed from: o */
    public int compareTo(ChronoLocalDate chronoLocalDate) {
        int b2 = Jdk8Methods.b(w(), chronoLocalDate.w());
        if (b2 == 0) {
            return p().compareTo(chronoLocalDate.p());
        }
        return b2;
    }

    public abstract Chronology p();

    public Era q() {
        return p().f(f(ChronoField.ERA));
    }

    public boolean r(ChronoLocalDate chronoLocalDate) {
        return w() > chronoLocalDate.w();
    }

    public boolean s(ChronoLocalDate chronoLocalDate) {
        return w() < chronoLocalDate.w();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: t */
    public ChronoLocalDate g(long j2, TemporalUnit temporalUnit) {
        return p().c(super.g(j2, temporalUnit));
    }

    public String toString() {
        String str;
        long h2 = h(ChronoField.YEAR_OF_ERA);
        long h3 = h(ChronoField.MONTH_OF_YEAR);
        long h4 = h(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(p().toString());
        sb.append(" ");
        sb.append(q());
        sb.append(" ");
        sb.append(h2);
        String str2 = "-0";
        if (h3 >= 10) {
            str = "-";
        } else {
            str = str2;
        }
        sb.append(str);
        sb.append(h3);
        if (h4 >= 10) {
            str2 = "-";
        }
        sb.append(str2);
        sb.append(h4);
        return sb.toString();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: u */
    public abstract ChronoLocalDate i(long j2, TemporalUnit temporalUnit);

    public ChronoLocalDate v(TemporalAmount temporalAmount) {
        return p().c(super.m(temporalAmount));
    }

    public long w() {
        return h(ChronoField.EPOCH_DAY);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: x */
    public ChronoLocalDate c(TemporalAdjuster temporalAdjuster) {
        return p().c(super.c(temporalAdjuster));
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: y */
    public abstract ChronoLocalDate e(TemporalField temporalField, long j2);
}
