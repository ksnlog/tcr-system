package org.threeten.bp.temporal;

import org.threeten.bp.Duration;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface TemporalUnit {
    long a(Temporal temporal, Temporal temporal2);

    <R extends Temporal> R b(R r2, long j2);

    Duration getDuration();

    boolean isDateBased();
}
