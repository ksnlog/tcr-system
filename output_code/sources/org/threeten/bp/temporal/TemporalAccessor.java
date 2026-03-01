package org.threeten.bp.temporal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface TemporalAccessor {
    ValueRange a(TemporalField temporalField);

    <R> R b(TemporalQuery<R> temporalQuery);

    boolean d(TemporalField temporalField);

    int f(TemporalField temporalField);

    long h(TemporalField temporalField);
}
