package org.threeten.bp.temporal;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.Chronology;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TemporalQueries {

    /* renamed from: a  reason: collision with root package name */
    static final TemporalQuery<ZoneId> f3401a = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.temporal.TemporalQueries.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneId a(TemporalAccessor temporalAccessor) {
            return (ZoneId) temporalAccessor.b(this);
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static final TemporalQuery<Chronology> f3402b = new TemporalQuery<Chronology>() { // from class: org.threeten.bp.temporal.TemporalQueries.2
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public Chronology a(TemporalAccessor temporalAccessor) {
            return (Chronology) temporalAccessor.b(this);
        }
    };

    /* renamed from: c  reason: collision with root package name */
    static final TemporalQuery<TemporalUnit> f3403c = new TemporalQuery<TemporalUnit>() { // from class: org.threeten.bp.temporal.TemporalQueries.3
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public TemporalUnit a(TemporalAccessor temporalAccessor) {
            return (TemporalUnit) temporalAccessor.b(this);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    static final TemporalQuery<ZoneId> f3404d = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.temporal.TemporalQueries.4
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneId a(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.b(TemporalQueries.f3401a);
            if (zoneId == null) {
                return (ZoneId) temporalAccessor.b(TemporalQueries.f3405e);
            }
            return zoneId;
        }
    };

    /* renamed from: e  reason: collision with root package name */
    static final TemporalQuery<ZoneOffset> f3405e = new TemporalQuery<ZoneOffset>() { // from class: org.threeten.bp.temporal.TemporalQueries.5
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneOffset a(TemporalAccessor temporalAccessor) {
            ChronoField chronoField = ChronoField.OFFSET_SECONDS;
            if (temporalAccessor.d(chronoField)) {
                return ZoneOffset.A(temporalAccessor.f(chronoField));
            }
            return null;
        }
    };

    /* renamed from: f  reason: collision with root package name */
    static final TemporalQuery<LocalDate> f3406f = new TemporalQuery<LocalDate>() { // from class: org.threeten.bp.temporal.TemporalQueries.6
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public LocalDate a(TemporalAccessor temporalAccessor) {
            ChronoField chronoField = ChronoField.EPOCH_DAY;
            if (temporalAccessor.d(chronoField)) {
                return LocalDate.W(temporalAccessor.h(chronoField));
            }
            return null;
        }
    };

    /* renamed from: g  reason: collision with root package name */
    static final TemporalQuery<LocalTime> f3407g = new TemporalQuery<LocalTime>() { // from class: org.threeten.bp.temporal.TemporalQueries.7
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public LocalTime a(TemporalAccessor temporalAccessor) {
            ChronoField chronoField = ChronoField.NANO_OF_DAY;
            if (temporalAccessor.d(chronoField)) {
                return LocalTime.B(temporalAccessor.h(chronoField));
            }
            return null;
        }
    };

    public static final TemporalQuery<Chronology> a() {
        return f3402b;
    }

    public static final TemporalQuery<LocalDate> b() {
        return f3406f;
    }

    public static final TemporalQuery<LocalTime> c() {
        return f3407g;
    }

    public static final TemporalQuery<ZoneOffset> d() {
        return f3405e;
    }

    public static final TemporalQuery<TemporalUnit> e() {
        return f3403c;
    }

    public static final TemporalQuery<ZoneId> f() {
        return f3404d;
    }

    public static final TemporalQuery<ZoneId> g() {
        return f3401a;
    }
}
