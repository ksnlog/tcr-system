package org.threeten.bp.format;

import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DateTimePrintContext {

    /* renamed from: a  reason: collision with root package name */
    private TemporalAccessor f3304a;

    /* renamed from: b  reason: collision with root package name */
    private Locale f3305b;

    /* renamed from: c  reason: collision with root package name */
    private DecimalStyle f3306c;

    /* renamed from: d  reason: collision with root package name */
    private int f3307d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        this.f3304a = a(temporalAccessor, dateTimeFormatter);
        this.f3305b = dateTimeFormatter.f();
        this.f3306c = dateTimeFormatter.e();
    }

    private static TemporalAccessor a(final TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        final Chronology chronology;
        ChronoField[] values;
        Chronology d2 = dateTimeFormatter.d();
        ZoneId g2 = dateTimeFormatter.g();
        if (d2 == null && g2 == null) {
            return temporalAccessor;
        }
        Chronology chronology2 = (Chronology) temporalAccessor.b(TemporalQueries.a());
        final ZoneId zoneId = (ZoneId) temporalAccessor.b(TemporalQueries.g());
        final ChronoLocalDate chronoLocalDate = null;
        if (Jdk8Methods.c(chronology2, d2)) {
            d2 = null;
        }
        if (Jdk8Methods.c(zoneId, g2)) {
            g2 = null;
        }
        if (d2 == null && g2 == null) {
            return temporalAccessor;
        }
        if (d2 != null) {
            chronology = d2;
        } else {
            chronology = chronology2;
        }
        if (g2 != null) {
            zoneId = g2;
        }
        if (g2 != null) {
            if (temporalAccessor.d(ChronoField.INSTANT_SECONDS)) {
                if (chronology == null) {
                    chronology = IsoChronology.f3167h;
                }
                return chronology.q(Instant.o(temporalAccessor), g2);
            }
            ZoneId o2 = g2.o();
            ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.b(TemporalQueries.d());
            if ((o2 instanceof ZoneOffset) && zoneOffset != null && !o2.equals(zoneOffset)) {
                throw new DateTimeException("Invalid override zone for temporal: " + g2 + " " + temporalAccessor);
            }
        }
        if (d2 != null) {
            if (temporalAccessor.d(ChronoField.EPOCH_DAY)) {
                chronoLocalDate = chronology.b(temporalAccessor);
            } else if (d2 != IsoChronology.f3167h || chronology2 != null) {
                for (ChronoField chronoField : ChronoField.values()) {
                    if (chronoField.isDateBased() && temporalAccessor.d(chronoField)) {
                        throw new DateTimeException("Invalid override chronology for temporal: " + d2 + " " + temporalAccessor);
                    }
                }
            }
        }
        return new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.format.DateTimePrintContext.1
            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public ValueRange a(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.a(temporalField);
                }
                return temporalAccessor.a(temporalField);
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public <R> R b(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.a()) {
                    return (R) chronology;
                }
                if (temporalQuery == TemporalQueries.g()) {
                    return (R) zoneId;
                }
                if (temporalQuery == TemporalQueries.e()) {
                    return (R) temporalAccessor.b(temporalQuery);
                }
                return temporalQuery.a(this);
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public boolean d(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.d(temporalField);
                }
                return temporalAccessor.d(temporalField);
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public long h(TemporalField temporalField) {
                if (ChronoLocalDate.this != null && temporalField.isDateBased()) {
                    return ChronoLocalDate.this.h(temporalField);
                }
                return temporalAccessor.h(temporalField);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f3307d--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Locale c() {
        return this.f3305b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecimalStyle d() {
        return this.f3306c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TemporalAccessor e() {
        return this.f3304a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Long f(TemporalField temporalField) {
        try {
            return Long.valueOf(this.f3304a.h(temporalField));
        } catch (DateTimeException e2) {
            if (this.f3307d > 0) {
                return null;
            }
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <R> R g(TemporalQuery<R> temporalQuery) {
        R r2 = (R) this.f3304a.b(temporalQuery);
        if (r2 == null && this.f3307d == 0) {
            throw new DateTimeException("Unable to extract value: " + this.f3304a.getClass());
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        this.f3307d++;
    }

    public String toString() {
        return this.f3304a.toString();
    }
}
