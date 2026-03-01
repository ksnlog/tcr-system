package org.threeten.bp.jdk8;

import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class DefaultInterfaceTemporal extends DefaultInterfaceTemporalAccessor implements Temporal {
    public Temporal c(TemporalAdjuster temporalAdjuster) {
        return temporalAdjuster.j(this);
    }

    public Temporal g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    public Temporal m(TemporalAmount temporalAmount) {
        return temporalAmount.a(this);
    }
}
