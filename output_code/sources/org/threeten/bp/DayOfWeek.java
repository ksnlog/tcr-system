package org.threeten.bp;

import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum DayOfWeek implements TemporalAccessor, TemporalAdjuster {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
    

    /* renamed from: k  reason: collision with root package name */
    public static final TemporalQuery<DayOfWeek> f3009k = new TemporalQuery<DayOfWeek>() { // from class: org.threeten.bp.DayOfWeek.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public DayOfWeek a(TemporalAccessor temporalAccessor) {
            return DayOfWeek.k(temporalAccessor);
        }
    };

    /* renamed from: l  reason: collision with root package name */
    private static final DayOfWeek[] f3010l = values();

    public static DayOfWeek k(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof DayOfWeek) {
            return (DayOfWeek) temporalAccessor;
        }
        try {
            return m(temporalAccessor.f(ChronoField.DAY_OF_WEEK));
        } catch (DateTimeException e2) {
            throw new DateTimeException("Unable to obtain DayOfWeek from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName(), e2);
        }
    }

    public static DayOfWeek m(int i2) {
        if (i2 >= 1 && i2 <= 7) {
            return f3010l[i2 - 1];
        }
        throw new DateTimeException("Invalid value for DayOfWeek: " + i2);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField == ChronoField.DAY_OF_WEEK) {
            return temporalField.f();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.d(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.DAYS;
        }
        if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.DAY_OF_WEEK) {
                return true;
            }
            return false;
        } else if (temporalField != null && temporalField.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField == ChronoField.DAY_OF_WEEK) {
            return getValue();
        }
        return a(temporalField).a(h(temporalField), temporalField);
    }

    public int getValue() {
        return ordinal() + 1;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField == ChronoField.DAY_OF_WEEK) {
            return getValue();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.c(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.DAY_OF_WEEK, getValue());
    }
}
