package org.threeten.bp.temporal;

import org.threeten.bp.Duration;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.k(1)),
    MICROS("Micros", Duration.k(1000)),
    MILLIS("Millis", Duration.k(1000000)),
    SECONDS("Seconds", Duration.l(1)),
    MINUTES("Minutes", Duration.l(60)),
    HOURS("Hours", Duration.l(3600)),
    HALF_DAYS("HalfDays", Duration.l(43200)),
    DAYS("Days", Duration.l(86400)),
    WEEKS("Weeks", Duration.l(604800)),
    MONTHS("Months", Duration.l(2629746)),
    YEARS("Years", Duration.l(31556952)),
    DECADES("Decades", Duration.l(315569520)),
    CENTURIES("Centuries", Duration.l(3155695200L)),
    MILLENNIA("Millennia", Duration.l(31556952000L)),
    ERAS("Eras", Duration.l(31556952000000000L)),
    FOREVER("Forever", Duration.m(Long.MAX_VALUE, 999999999));
    

    /* renamed from: d  reason: collision with root package name */
    private final String f3379d;

    /* renamed from: e  reason: collision with root package name */
    private final Duration f3380e;

    ChronoUnit(String str, Duration duration) {
        this.f3379d = str;
        this.f3380e = duration;
    }

    @Override // org.threeten.bp.temporal.TemporalUnit
    public long a(Temporal temporal, Temporal temporal2) {
        return temporal.l(temporal2, this);
    }

    @Override // org.threeten.bp.temporal.TemporalUnit
    public <R extends Temporal> R b(R r2, long j2) {
        return (R) r2.i(j2, this);
    }

    public boolean c() {
        return compareTo(DAYS) < 0;
    }

    @Override // org.threeten.bp.temporal.TemporalUnit
    public Duration getDuration() {
        return this.f3380e;
    }

    @Override // org.threeten.bp.temporal.TemporalUnit
    public boolean isDateBased() {
        return compareTo(DAYS) >= 0 && this != FOREVER;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f3379d;
    }
}
