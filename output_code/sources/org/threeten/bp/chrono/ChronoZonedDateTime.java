package org.threeten.bp.chrono;

import java.util.Comparator;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ChronoZonedDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements Comparable<ChronoZonedDateTime<?>> {

    /* renamed from: d  reason: collision with root package name */
    private static Comparator<ChronoZonedDateTime<?>> f3127d = new Comparator<ChronoZonedDateTime<?>>() { // from class: org.threeten.bp.chrono.ChronoZonedDateTime.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(ChronoZonedDateTime<?> chronoZonedDateTime, ChronoZonedDateTime<?> chronoZonedDateTime2) {
            int b2 = Jdk8Methods.b(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
            if (b2 == 0) {
                return Jdk8Methods.b(chronoZonedDateTime.x().K(), chronoZonedDateTime2.x().K());
            }
            return b2;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.ChronoZonedDateTime$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3128a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3128a = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3128a[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public abstract ChronoZonedDateTime<D> A(ZoneId zoneId);

    public abstract ChronoZonedDateTime<D> B(ZoneId zoneId);

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField != ChronoField.INSTANT_SECONDS && temporalField != ChronoField.OFFSET_SECONDS) {
                return w().a(temporalField);
            }
            return temporalField.f();
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.f()) {
            if (temporalQuery == TemporalQueries.a()) {
                return (R) v().p();
            }
            if (temporalQuery == TemporalQueries.e()) {
                return (R) ChronoUnit.NANOS;
            }
            if (temporalQuery == TemporalQueries.d()) {
                return (R) p();
            }
            if (temporalQuery == TemporalQueries.b()) {
                return (R) LocalDate.W(v().w());
            }
            if (temporalQuery == TemporalQueries.c()) {
                return (R) x();
            }
            return (R) super.b(temporalQuery);
        }
        return (R) q();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3128a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return w().f(temporalField);
                }
                return p().x();
            }
            throw new UnsupportedTemporalTypeException("Field too large for an int: " + temporalField);
        }
        return super.f(temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3128a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return w().h(temporalField);
                }
                return p().x();
            }
            return toEpochSecond();
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return (w().hashCode() ^ p().hashCode()) ^ Integer.rotateLeft(q().hashCode(), 3);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    @Override // java.lang.Comparable
    /* renamed from: n */
    public int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int b2 = Jdk8Methods.b(toEpochSecond(), chronoZonedDateTime.toEpochSecond());
        if (b2 == 0) {
            int t2 = x().t() - chronoZonedDateTime.x().t();
            if (t2 == 0) {
                int compareTo = w().compareTo(chronoZonedDateTime.w());
                if (compareTo == 0) {
                    int compareTo2 = q().m().compareTo(chronoZonedDateTime.q().m());
                    if (compareTo2 == 0) {
                        return v().p().compareTo(chronoZonedDateTime.v().p());
                    }
                    return compareTo2;
                }
                return compareTo;
            }
            return t2;
        }
        return b2;
    }

    public String o(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.i(dateTimeFormatter, "formatter");
        return dateTimeFormatter.b(this);
    }

    public abstract ZoneOffset p();

    public abstract ZoneId q();

    public boolean r(ChronoZonedDateTime<?> chronoZonedDateTime) {
        long epochSecond = toEpochSecond();
        long epochSecond2 = chronoZonedDateTime.toEpochSecond();
        if (epochSecond <= epochSecond2 && (epochSecond != epochSecond2 || x().t() <= chronoZonedDateTime.x().t())) {
            return false;
        }
        return true;
    }

    public boolean s(ChronoZonedDateTime<?> chronoZonedDateTime) {
        long epochSecond = toEpochSecond();
        long epochSecond2 = chronoZonedDateTime.toEpochSecond();
        if (epochSecond >= epochSecond2 && (epochSecond != epochSecond2 || x().t() >= chronoZonedDateTime.x().t())) {
            return false;
        }
        return true;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: t */
    public ChronoZonedDateTime<D> g(long j2, TemporalUnit temporalUnit) {
        return v().p().e(super.g(j2, temporalUnit));
    }

    public long toEpochSecond() {
        return ((v().w() * 86400) + x().L()) - p().x();
    }

    public String toString() {
        String str = w().toString() + p().toString();
        if (p() != q()) {
            return str + '[' + q().toString() + ']';
        }
        return str;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: u */
    public abstract ChronoZonedDateTime<D> i(long j2, TemporalUnit temporalUnit);

    public D v() {
        return w().w();
    }

    public abstract ChronoLocalDateTime<D> w();

    public LocalTime x() {
        return w().x();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: y */
    public ChronoZonedDateTime<D> c(TemporalAdjuster temporalAdjuster) {
        return v().p().e(super.c(temporalAdjuster));
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: z */
    public abstract ChronoZonedDateTime<D> e(TemporalField temporalField, long j2);
}
