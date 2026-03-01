package org.threeten.bp.chrono;

import java.util.Comparator;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ChronoLocalDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements TemporalAdjuster, Comparable<ChronoLocalDateTime<?>> {

    /* renamed from: d  reason: collision with root package name */
    private static final Comparator<ChronoLocalDateTime<?>> f3123d = new Comparator<ChronoLocalDateTime<?>>() { // from class: org.threeten.bp.chrono.ChronoLocalDateTime.1
        /* JADX WARN: Type inference failed for: r0v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
        /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(ChronoLocalDateTime<?> chronoLocalDateTime, ChronoLocalDateTime<?> chronoLocalDateTime2) {
            int b2 = Jdk8Methods.b(chronoLocalDateTime.w().w(), chronoLocalDateTime2.w().w());
            if (b2 == 0) {
                return Jdk8Methods.b(chronoLocalDateTime.x().K(), chronoLocalDateTime2.x().K());
            }
            return b2;
        }
    };

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) p();
        }
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.b()) {
            return (R) LocalDate.W(w().w());
        }
        if (temporalQuery == TemporalQueries.c()) {
            return (R) x();
        }
        if (temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return (R) super.b(temporalQuery);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ChronoLocalDateTime) && compareTo((ChronoLocalDateTime) obj) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return w().hashCode() ^ x().hashCode();
    }

    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.EPOCH_DAY, w().w()).e(ChronoField.NANO_OF_DAY, x().K());
    }

    public abstract ChronoZonedDateTime<D> n(ZoneId zoneId);

    @Override // java.lang.Comparable
    /* renamed from: o */
    public int compareTo(ChronoLocalDateTime<?> chronoLocalDateTime) {
        int compareTo = w().compareTo(chronoLocalDateTime.w());
        if (compareTo == 0) {
            int compareTo2 = x().compareTo(chronoLocalDateTime.x());
            if (compareTo2 == 0) {
                return p().compareTo(chronoLocalDateTime.p());
            }
            return compareTo2;
        }
        return compareTo;
    }

    public Chronology p() {
        return w().p();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    public boolean q(ChronoLocalDateTime<?> chronoLocalDateTime) {
        long w2 = w().w();
        long w3 = chronoLocalDateTime.w().w();
        if (w2 <= w3 && (w2 != w3 || x().K() <= chronoLocalDateTime.x().K())) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    public boolean r(ChronoLocalDateTime<?> chronoLocalDateTime) {
        long w2 = w().w();
        long w3 = chronoLocalDateTime.w().w();
        if (w2 >= w3 && (w2 != w3 || x().K() >= chronoLocalDateTime.x().K())) {
            return false;
        }
        return true;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: s */
    public ChronoLocalDateTime<D> g(long j2, TemporalUnit temporalUnit) {
        return w().p().d(super.g(j2, temporalUnit));
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: t */
    public abstract ChronoLocalDateTime<D> i(long j2, TemporalUnit temporalUnit);

    public String toString() {
        return w().toString() + 'T' + x().toString();
    }

    public long u(ZoneOffset zoneOffset) {
        Jdk8Methods.i(zoneOffset, "offset");
        return ((w().w() * 86400) + x().L()) - zoneOffset.x();
    }

    public Instant v(ZoneOffset zoneOffset) {
        return Instant.v(u(zoneOffset), x().t());
    }

    public abstract D w();

    public abstract LocalTime x();

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: y */
    public ChronoLocalDateTime<D> c(TemporalAdjuster temporalAdjuster) {
        return w().p().d(super.c(temporalAdjuster));
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: z */
    public abstract ChronoLocalDateTime<D> e(TemporalField temporalField, long j2);
}
