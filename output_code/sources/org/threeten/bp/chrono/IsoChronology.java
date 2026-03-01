package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Map;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class IsoChronology extends Chronology implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final IsoChronology f3167h = new IsoChronology();

    private IsoChronology() {
    }

    private Object readResolve() {
        return f3167h;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String h() {
        return "iso8601";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String i() {
        return "ISO";
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: s */
    public LocalDate b(TemporalAccessor temporalAccessor) {
        return LocalDate.D(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: t */
    public IsoEra f(int i2) {
        return IsoEra.k(i2);
    }

    public boolean u(long j2) {
        return (3 & j2) == 0 && (j2 % 100 != 0 || j2 % 400 == 0);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: v */
    public LocalDateTime k(TemporalAccessor temporalAccessor) {
        return LocalDateTime.D(temporalAccessor);
    }

    public LocalDate w(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        long longValue;
        long o2;
        ChronoField chronoField = ChronoField.EPOCH_DAY;
        if (map.containsKey(chronoField)) {
            return LocalDate.W(map.remove(chronoField).longValue());
        }
        ChronoField chronoField2 = ChronoField.PROLEPTIC_MONTH;
        Long remove = map.remove(chronoField2);
        if (remove != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                chronoField2.h(remove.longValue());
            }
            o(map, ChronoField.MONTH_OF_YEAR, Jdk8Methods.g(remove.longValue(), 12) + 1);
            o(map, ChronoField.YEAR, Jdk8Methods.e(remove.longValue(), 12L));
        }
        ChronoField chronoField3 = ChronoField.YEAR_OF_ERA;
        Long remove2 = map.remove(chronoField3);
        if (remove2 != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                chronoField3.h(remove2.longValue());
            }
            Long remove3 = map.remove(ChronoField.ERA);
            if (remove3 == null) {
                ChronoField chronoField4 = ChronoField.YEAR;
                Long l2 = map.get(chronoField4);
                if (resolverStyle == ResolverStyle.STRICT) {
                    if (l2 != null) {
                        if (l2.longValue() > 0) {
                            o2 = remove2.longValue();
                        } else {
                            o2 = Jdk8Methods.o(1L, remove2.longValue());
                        }
                        o(map, chronoField4, o2);
                    } else {
                        map.put(chronoField3, remove2);
                    }
                } else {
                    if (l2 != null && l2.longValue() <= 0) {
                        longValue = Jdk8Methods.o(1L, remove2.longValue());
                    } else {
                        longValue = remove2.longValue();
                    }
                    o(map, chronoField4, longValue);
                }
            } else if (remove3.longValue() == 1) {
                o(map, ChronoField.YEAR, remove2.longValue());
            } else if (remove3.longValue() == 0) {
                o(map, ChronoField.YEAR, Jdk8Methods.o(1L, remove2.longValue()));
            } else {
                throw new DateTimeException("Invalid value for era: " + remove3);
            }
        } else {
            ChronoField chronoField5 = ChronoField.ERA;
            if (map.containsKey(chronoField5)) {
                chronoField5.h(map.get(chronoField5).longValue());
            }
        }
        ChronoField chronoField6 = ChronoField.YEAR;
        if (map.containsKey(chronoField6)) {
            ChronoField chronoField7 = ChronoField.MONTH_OF_YEAR;
            if (map.containsKey(chronoField7)) {
                ChronoField chronoField8 = ChronoField.DAY_OF_MONTH;
                if (map.containsKey(chronoField8)) {
                    int g2 = chronoField6.g(map.remove(chronoField6).longValue());
                    int p2 = Jdk8Methods.p(map.remove(chronoField7).longValue());
                    int p3 = Jdk8Methods.p(map.remove(chronoField8).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return LocalDate.U(g2, 1, 1).b0(Jdk8Methods.n(p2, 1)).a0(Jdk8Methods.n(p3, 1));
                    } else if (resolverStyle == ResolverStyle.SMART) {
                        chronoField8.h(p3);
                        if (p2 != 4 && p2 != 6 && p2 != 9 && p2 != 11) {
                            if (p2 == 2) {
                                p3 = Math.min(p3, Month.FEBRUARY.n(Year.o(g2)));
                            }
                        } else {
                            p3 = Math.min(p3, 30);
                        }
                        return LocalDate.U(g2, p2, p3);
                    } else {
                        return LocalDate.U(g2, p2, p3);
                    }
                }
                ChronoField chronoField9 = ChronoField.ALIGNED_WEEK_OF_MONTH;
                if (map.containsKey(chronoField9)) {
                    ChronoField chronoField10 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                    if (map.containsKey(chronoField10)) {
                        int g3 = chronoField6.g(map.remove(chronoField6).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long o3 = Jdk8Methods.o(map.remove(chronoField7).longValue(), 1L);
                            long o4 = Jdk8Methods.o(map.remove(chronoField9).longValue(), 1L);
                            return LocalDate.U(g3, 1, 1).b0(o3).c0(o4).a0(Jdk8Methods.o(map.remove(chronoField10).longValue(), 1L));
                        }
                        int g4 = chronoField7.g(map.remove(chronoField7).longValue());
                        LocalDate a02 = LocalDate.U(g3, g4, 1).a0(((chronoField9.g(map.remove(chronoField9).longValue()) - 1) * 7) + (chronoField10.g(map.remove(chronoField10).longValue()) - 1));
                        if (resolverStyle == ResolverStyle.STRICT && a02.f(chronoField7) != g4) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return a02;
                    }
                    ChronoField chronoField11 = ChronoField.DAY_OF_WEEK;
                    if (map.containsKey(chronoField11)) {
                        int g5 = chronoField6.g(map.remove(chronoField6).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            long o5 = Jdk8Methods.o(map.remove(chronoField7).longValue(), 1L);
                            long o6 = Jdk8Methods.o(map.remove(chronoField9).longValue(), 1L);
                            return LocalDate.U(g5, 1, 1).b0(o5).c0(o6).a0(Jdk8Methods.o(map.remove(chronoField11).longValue(), 1L));
                        }
                        int g6 = chronoField7.g(map.remove(chronoField7).longValue());
                        LocalDate x2 = LocalDate.U(g5, g6, 1).c0(chronoField9.g(map.remove(chronoField9).longValue()) - 1).x(TemporalAdjusters.a(DayOfWeek.m(chronoField11.g(map.remove(chronoField11).longValue()))));
                        if (resolverStyle == ResolverStyle.STRICT && x2.f(chronoField7) != g6) {
                            throw new DateTimeException("Strict mode rejected date parsed to a different month");
                        }
                        return x2;
                    }
                }
            }
            ChronoField chronoField12 = ChronoField.DAY_OF_YEAR;
            if (map.containsKey(chronoField12)) {
                int g7 = chronoField6.g(map.remove(chronoField6).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.X(g7, 1).a0(Jdk8Methods.o(map.remove(chronoField12).longValue(), 1L));
                }
                return LocalDate.X(g7, chronoField12.g(map.remove(chronoField12).longValue()));
            }
            ChronoField chronoField13 = ChronoField.ALIGNED_WEEK_OF_YEAR;
            if (map.containsKey(chronoField13)) {
                ChronoField chronoField14 = ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
                if (map.containsKey(chronoField14)) {
                    int g8 = chronoField6.g(map.remove(chronoField6).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long o7 = Jdk8Methods.o(map.remove(chronoField13).longValue(), 1L);
                        return LocalDate.U(g8, 1, 1).c0(o7).a0(Jdk8Methods.o(map.remove(chronoField14).longValue(), 1L));
                    }
                    LocalDate a03 = LocalDate.U(g8, 1, 1).a0(((chronoField13.g(map.remove(chronoField13).longValue()) - 1) * 7) + (chronoField14.g(map.remove(chronoField14).longValue()) - 1));
                    if (resolverStyle == ResolverStyle.STRICT && a03.f(chronoField6) != g8) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    }
                    return a03;
                }
                ChronoField chronoField15 = ChronoField.DAY_OF_WEEK;
                if (map.containsKey(chronoField15)) {
                    int g9 = chronoField6.g(map.remove(chronoField6).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long o8 = Jdk8Methods.o(map.remove(chronoField13).longValue(), 1L);
                        return LocalDate.U(g9, 1, 1).c0(o8).a0(Jdk8Methods.o(map.remove(chronoField15).longValue(), 1L));
                    }
                    LocalDate x3 = LocalDate.U(g9, 1, 1).c0(chronoField13.g(map.remove(chronoField13).longValue()) - 1).x(TemporalAdjusters.a(DayOfWeek.m(chronoField15.g(map.remove(chronoField15).longValue()))));
                    if (resolverStyle == ResolverStyle.STRICT && x3.f(chronoField6) != g9) {
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    return x3;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: x */
    public ZonedDateTime q(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.P(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: y */
    public ZonedDateTime r(TemporalAccessor temporalAccessor) {
        return ZonedDateTime.D(temporalAccessor);
    }
}
