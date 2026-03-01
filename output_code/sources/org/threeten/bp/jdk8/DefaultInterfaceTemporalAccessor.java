package org.threeten.bp.jdk8;

import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class DefaultInterfaceTemporalAccessor implements TemporalAccessor {
    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (d(temporalField)) {
                return temporalField.f();
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.e()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        return a(temporalField).a(h(temporalField), temporalField);
    }
}
