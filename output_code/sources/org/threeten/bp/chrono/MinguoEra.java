package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum MinguoEra implements Era {
    BEFORE_ROC,
    ROC;

    public static MinguoEra k(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return ROC;
            }
            throw new DateTimeException("Invalid era: " + i2);
        }
        return BEFORE_ROC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MinguoEra m(DataInput dataInput) {
        return k(dataInput.readByte());
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
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
            return (R) ChronoUnit.ERAS;
        }
        if (temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d() && temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.ERA) {
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
        if (temporalField == ChronoField.ERA) {
            return getValue();
        }
        return a(temporalField).a(h(temporalField), temporalField);
    }

    @Override // org.threeten.bp.chrono.Era
    public int getValue() {
        return ordinal();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return getValue();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.c(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.ERA, getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(DataOutput dataOutput) {
        dataOutput.writeByte(getValue());
    }
}
