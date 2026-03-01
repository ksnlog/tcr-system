package org.threeten.bp.temporal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Temporal extends TemporalAccessor {
    Temporal c(TemporalAdjuster temporalAdjuster);

    Temporal e(TemporalField temporalField, long j2);

    Temporal g(long j2, TemporalUnit temporalUnit);

    Temporal i(long j2, TemporalUnit temporalUnit);

    long l(Temporal temporal, TemporalUnit temporalUnit);
}
