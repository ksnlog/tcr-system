package org.threeten.bp.temporal;

import java.util.Map;
import org.threeten.bp.format.ResolverStyle;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface TemporalField {
    boolean a(TemporalAccessor temporalAccessor);

    <R extends Temporal> R b(R r2, long j2);

    long c(TemporalAccessor temporalAccessor);

    ValueRange d(TemporalAccessor temporalAccessor);

    TemporalAccessor e(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle);

    ValueRange f();

    boolean isDateBased();

    boolean isTimeBased();
}
