package org.threeten.bp.temporal;

import java.util.Map;
import org.threeten.bp.format.ResolverStyle;
/* JADX WARN: Init of enum A can be incorrect */
/* JADX WARN: Init of enum B can be incorrect */
/* JADX WARN: Init of enum C can be incorrect */
/* JADX WARN: Init of enum D can be incorrect */
/* JADX WARN: Init of enum E can be incorrect */
/* JADX WARN: Init of enum F can be incorrect */
/* JADX WARN: Init of enum G can be incorrect */
/* JADX WARN: Init of enum H can be incorrect */
/* JADX WARN: Init of enum I can be incorrect */
/* JADX WARN: Init of enum J can be incorrect */
/* JADX WARN: Init of enum K can be incorrect */
/* JADX WARN: Init of enum h can be incorrect */
/* JADX WARN: Init of enum i can be incorrect */
/* JADX WARN: Init of enum j can be incorrect */
/* JADX WARN: Init of enum k can be incorrect */
/* JADX WARN: Init of enum l can be incorrect */
/* JADX WARN: Init of enum m can be incorrect */
/* JADX WARN: Init of enum n can be incorrect */
/* JADX WARN: Init of enum o can be incorrect */
/* JADX WARN: Init of enum p can be incorrect */
/* JADX WARN: Init of enum q can be incorrect */
/* JADX WARN: Init of enum r can be incorrect */
/* JADX WARN: Init of enum s can be incorrect */
/* JADX WARN: Init of enum t can be incorrect */
/* JADX WARN: Init of enum u can be incorrect */
/* JADX WARN: Init of enum v can be incorrect */
/* JADX WARN: Init of enum w can be incorrect */
/* JADX WARN: Init of enum x can be incorrect */
/* JADX WARN: Init of enum y can be incorrect */
/* JADX WARN: Init of enum z can be incorrect */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum ChronoField implements TemporalField {
    NANO_OF_SECOND("NanoOfSecond", r12, r20, ValueRange.i(0, 999999999)),
    NANO_OF_DAY("NanoOfDay", r12, r3, ValueRange.i(0, 86399999999999L)),
    MICRO_OF_SECOND("MicroOfSecond", r25, r20, ValueRange.i(0, 999999)),
    MICRO_OF_DAY("MicroOfDay", r25, r3, ValueRange.i(0, 86399999999L)),
    MILLI_OF_SECOND("MilliOfSecond", r25, r20, ValueRange.i(0, 999)),
    MILLI_OF_DAY("MilliOfDay", r25, r3, ValueRange.i(0, 86399999)),
    SECOND_OF_MINUTE("SecondOfMinute", r20, r12, ValueRange.i(0, 59)),
    SECOND_OF_DAY("SecondOfDay", r20, r3, ValueRange.i(0, 86399)),
    MINUTE_OF_HOUR("MinuteOfHour", r12, r29, ValueRange.i(0, 59)),
    MINUTE_OF_DAY("MinuteOfDay", r12, r3, ValueRange.i(0, 1439)),
    HOUR_OF_AMPM("HourOfAmPm", r29, r30, ValueRange.i(0, 11)),
    CLOCK_HOUR_OF_AMPM("ClockHourOfAmPm", r29, r30, ValueRange.i(1, 12)),
    HOUR_OF_DAY("HourOfDay", r29, r3, ValueRange.i(0, 23)),
    CLOCK_HOUR_OF_DAY("ClockHourOfDay", r29, r3, ValueRange.i(1, 24)),
    AMPM_OF_DAY("AmPmOfDay", r30, r3, ValueRange.i(0, 1)),
    DAY_OF_WEEK("DayOfWeek", r3, r13, ValueRange.i(1, 7)),
    ALIGNED_DAY_OF_WEEK_IN_MONTH("AlignedDayOfWeekInMonth", r3, r13, ValueRange.i(1, 7)),
    ALIGNED_DAY_OF_WEEK_IN_YEAR("AlignedDayOfWeekInYear", r3, r13, ValueRange.i(1, 7)),
    DAY_OF_MONTH("DayOfMonth", r3, r14, ValueRange.j(1, 28, 31)),
    DAY_OF_YEAR("DayOfYear", r3, r15, ValueRange.j(1, 365, 366)),
    EPOCH_DAY("EpochDay", r3, r46, ValueRange.i(-365243219162L, 365241780471L)),
    ALIGNED_WEEK_OF_MONTH("AlignedWeekOfMonth", r13, r14, ValueRange.j(1, 4, 5)),
    ALIGNED_WEEK_OF_YEAR("AlignedWeekOfYear", r13, r15, ValueRange.i(1, 53)),
    MONTH_OF_YEAR("MonthOfYear", r14, r15, ValueRange.i(1, 12)),
    PROLEPTIC_MONTH("ProlepticMonth", r14, r46, ValueRange.i(-11999999988L, 11999999999L)),
    YEAR_OF_ERA("YearOfEra", r15, r46, ValueRange.j(1, 999999999, 1000000000)),
    YEAR("Year", r15, r46, ValueRange.i(-999999999, 999999999)),
    ERA("Era", ChronoUnit.ERAS, r46, ValueRange.i(0, 1)),
    INSTANT_SECONDS("InstantSeconds", r20, r46, ValueRange.i(Long.MIN_VALUE, Long.MAX_VALUE)),
    OFFSET_SECONDS("OffsetSeconds", r20, r46, ValueRange.i(-64800, 64800));
    

    /* renamed from: d  reason: collision with root package name */
    private final String f3358d;

    /* renamed from: e  reason: collision with root package name */
    private final TemporalUnit f3359e;

    /* renamed from: f  reason: collision with root package name */
    private final TemporalUnit f3360f;

    /* renamed from: g  reason: collision with root package name */
    private final ValueRange f3361g;

    static {
        ChronoUnit chronoUnit = ChronoUnit.NANOS;
        ChronoUnit chronoUnit2 = ChronoUnit.SECONDS;
        ChronoUnit chronoUnit3 = ChronoUnit.DAYS;
        ChronoUnit chronoUnit4 = ChronoUnit.MICROS;
        ChronoUnit chronoUnit5 = ChronoUnit.MILLIS;
        ChronoUnit chronoUnit6 = ChronoUnit.MINUTES;
        ChronoUnit chronoUnit7 = ChronoUnit.HOURS;
        ChronoUnit chronoUnit8 = ChronoUnit.HALF_DAYS;
        ChronoUnit chronoUnit9 = ChronoUnit.WEEKS;
        ChronoUnit chronoUnit10 = ChronoUnit.MONTHS;
        ChronoUnit chronoUnit11 = ChronoUnit.YEARS;
        ChronoUnit chronoUnit12 = ChronoUnit.FOREVER;
    }

    ChronoField(String str, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
        this.f3358d = str;
        this.f3359e = temporalUnit;
        this.f3360f = temporalUnit2;
        this.f3361g = valueRange;
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public boolean a(TemporalAccessor temporalAccessor) {
        return temporalAccessor.d(this);
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public <R extends Temporal> R b(R r2, long j2) {
        return (R) r2.e(this, j2);
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public long c(TemporalAccessor temporalAccessor) {
        return temporalAccessor.h(this);
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public ValueRange d(TemporalAccessor temporalAccessor) {
        return temporalAccessor.a(this);
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public TemporalAccessor e(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public ValueRange f() {
        return this.f3361g;
    }

    public int g(long j2) {
        return f().a(j2, this);
    }

    public long h(long j2) {
        return f().b(j2, this);
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public boolean isDateBased() {
        return ordinal() >= DAY_OF_WEEK.ordinal() && ordinal() <= ERA.ordinal();
    }

    @Override // org.threeten.bp.temporal.TemporalField
    public boolean isTimeBased() {
        return ordinal() < DAY_OF_WEEK.ordinal();
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f3358d;
    }
}
