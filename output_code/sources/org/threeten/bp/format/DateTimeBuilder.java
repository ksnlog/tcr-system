package org.threeten.bp.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DateTimeBuilder extends DefaultInterfaceTemporalAccessor implements Cloneable {

    /* renamed from: d  reason: collision with root package name */
    final Map<TemporalField, Long> f3207d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    Chronology f3208e;

    /* renamed from: f  reason: collision with root package name */
    ZoneId f3209f;

    /* renamed from: g  reason: collision with root package name */
    ChronoLocalDate f3210g;

    /* renamed from: h  reason: collision with root package name */
    LocalTime f3211h;

    /* renamed from: i  reason: collision with root package name */
    boolean f3212i;

    /* renamed from: j  reason: collision with root package name */
    Period f3213j;

    private boolean A(ResolverStyle resolverStyle) {
        int i2 = 0;
        loop0: while (i2 < 100) {
            for (Map.Entry<TemporalField, Long> entry : this.f3207d.entrySet()) {
                TemporalField key = entry.getKey();
                TemporalAccessor e2 = key.e(this.f3207d, this, resolverStyle);
                if (e2 != null) {
                    if (e2 instanceof ChronoZonedDateTime) {
                        ChronoZonedDateTime chronoZonedDateTime = (ChronoZonedDateTime) e2;
                        ZoneId zoneId = this.f3209f;
                        if (zoneId == null) {
                            this.f3209f = chronoZonedDateTime.q();
                        } else if (!zoneId.equals(chronoZonedDateTime.q())) {
                            throw new DateTimeException("ChronoZonedDateTime must use the effective parsed zone: " + this.f3209f);
                        }
                        e2 = chronoZonedDateTime.w();
                    }
                    if (e2 instanceof ChronoLocalDate) {
                        E(key, (ChronoLocalDate) e2);
                    } else if (e2 instanceof LocalTime) {
                        D(key, (LocalTime) e2);
                    } else if (e2 instanceof ChronoLocalDateTime) {
                        ChronoLocalDateTime chronoLocalDateTime = (ChronoLocalDateTime) e2;
                        E(key, chronoLocalDateTime.w());
                        D(key, chronoLocalDateTime.x());
                    } else {
                        throw new DateTimeException("Unknown type: " + e2.getClass().getName());
                    }
                } else if (!this.f3207d.containsKey(key)) {
                    break;
                }
                i2++;
            }
        }
        if (i2 != 100) {
            if (i2 <= 0) {
                return false;
            }
            return true;
        }
        throw new DateTimeException("Badly written field");
    }

    private void B() {
        if (this.f3211h == null) {
            if (this.f3207d.containsKey(ChronoField.INSTANT_SECONDS) || this.f3207d.containsKey(ChronoField.SECOND_OF_DAY) || this.f3207d.containsKey(ChronoField.SECOND_OF_MINUTE)) {
                Map<TemporalField, Long> map = this.f3207d;
                ChronoField chronoField = ChronoField.NANO_OF_SECOND;
                if (map.containsKey(chronoField)) {
                    long longValue = this.f3207d.get(chronoField).longValue();
                    this.f3207d.put(ChronoField.MICRO_OF_SECOND, Long.valueOf(longValue / 1000));
                    this.f3207d.put(ChronoField.MILLI_OF_SECOND, Long.valueOf(longValue / 1000000));
                    return;
                }
                this.f3207d.put(chronoField, 0L);
                this.f3207d.put(ChronoField.MICRO_OF_SECOND, 0L);
                this.f3207d.put(ChronoField.MILLI_OF_SECOND, 0L);
            }
        }
    }

    private void C() {
        if (this.f3210g != null && this.f3211h != null) {
            Long l2 = this.f3207d.get(ChronoField.OFFSET_SECONDS);
            if (l2 != null) {
                ChronoZonedDateTime<?> n2 = this.f3210g.n(this.f3211h).n(ZoneOffset.A(l2.intValue()));
                ChronoField chronoField = ChronoField.INSTANT_SECONDS;
                this.f3207d.put(chronoField, Long.valueOf(n2.h(chronoField)));
            } else if (this.f3209f != null) {
                ChronoZonedDateTime<?> n3 = this.f3210g.n(this.f3211h).n(this.f3209f);
                ChronoField chronoField2 = ChronoField.INSTANT_SECONDS;
                this.f3207d.put(chronoField2, Long.valueOf(n3.h(chronoField2)));
            }
        }
    }

    private void D(TemporalField temporalField, LocalTime localTime) {
        long K = localTime.K();
        Long put = this.f3207d.put(ChronoField.NANO_OF_DAY, Long.valueOf(K));
        if (put != null && put.longValue() != K) {
            throw new DateTimeException("Conflict found: " + LocalTime.B(put.longValue()) + " differs from " + localTime + " while resolving  " + temporalField);
        }
    }

    private void E(TemporalField temporalField, ChronoLocalDate chronoLocalDate) {
        if (this.f3208e.equals(chronoLocalDate.p())) {
            long w2 = chronoLocalDate.w();
            Long put = this.f3207d.put(ChronoField.EPOCH_DAY, Long.valueOf(w2));
            if (put != null && put.longValue() != w2) {
                throw new DateTimeException("Conflict found: " + LocalDate.W(put.longValue()) + " differs from " + LocalDate.W(w2) + " while resolving  " + temporalField);
            }
            return;
        }
        throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + this.f3208e);
    }

    private void F(ResolverStyle resolverStyle) {
        Map<TemporalField, Long> map = this.f3207d;
        ChronoField chronoField = ChronoField.HOUR_OF_DAY;
        Long l2 = map.get(chronoField);
        Map<TemporalField, Long> map2 = this.f3207d;
        ChronoField chronoField2 = ChronoField.MINUTE_OF_HOUR;
        Long l3 = map2.get(chronoField2);
        Map<TemporalField, Long> map3 = this.f3207d;
        ChronoField chronoField3 = ChronoField.SECOND_OF_MINUTE;
        Long l4 = map3.get(chronoField3);
        Map<TemporalField, Long> map4 = this.f3207d;
        ChronoField chronoField4 = ChronoField.NANO_OF_SECOND;
        Long l5 = map4.get(chronoField4);
        if (l2 == null) {
            return;
        }
        if (l3 == null && (l4 != null || l5 != null)) {
            return;
        }
        if (l3 != null && l4 == null && l5 != null) {
            return;
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (resolverStyle == ResolverStyle.SMART && l2.longValue() == 24 && ((l3 == null || l3.longValue() == 0) && ((l4 == null || l4.longValue() == 0) && (l5 == null || l5.longValue() == 0)))) {
                l2 = 0L;
                this.f3213j = Period.d(1);
            }
            int g2 = chronoField.g(l2.longValue());
            if (l3 != null) {
                int g3 = chronoField2.g(l3.longValue());
                if (l4 != null) {
                    int g4 = chronoField3.g(l4.longValue());
                    if (l5 != null) {
                        n(LocalTime.A(g2, g3, g4, chronoField4.g(l5.longValue())));
                    } else {
                        n(LocalTime.z(g2, g3, g4));
                    }
                } else if (l5 == null) {
                    n(LocalTime.y(g2, g3));
                }
            } else if (l4 == null && l5 == null) {
                n(LocalTime.y(g2, 0));
            }
        } else {
            long longValue = l2.longValue();
            if (l3 != null) {
                if (l4 != null) {
                    if (l5 == null) {
                        l5 = 0L;
                    }
                    long k2 = Jdk8Methods.k(Jdk8Methods.k(Jdk8Methods.k(Jdk8Methods.m(longValue, 3600000000000L), Jdk8Methods.m(l3.longValue(), 60000000000L)), Jdk8Methods.m(l4.longValue(), 1000000000L)), l5.longValue());
                    n(LocalTime.B(Jdk8Methods.h(k2, 86400000000000L)));
                    this.f3213j = Period.d((int) Jdk8Methods.e(k2, 86400000000000L));
                } else {
                    long k3 = Jdk8Methods.k(Jdk8Methods.m(longValue, 3600L), Jdk8Methods.m(l3.longValue(), 60L));
                    n(LocalTime.C(Jdk8Methods.h(k3, 86400L)));
                    this.f3213j = Period.d((int) Jdk8Methods.e(k3, 86400L));
                }
            } else {
                int p2 = Jdk8Methods.p(Jdk8Methods.e(longValue, 24L));
                n(LocalTime.y(Jdk8Methods.g(longValue, 24), 0));
                this.f3213j = Period.d(p2);
            }
        }
        this.f3207d.remove(chronoField);
        this.f3207d.remove(chronoField2);
        this.f3207d.remove(chronoField3);
        this.f3207d.remove(chronoField4);
    }

    private void q(LocalDate localDate) {
        if (localDate != null) {
            o(localDate);
            for (TemporalField temporalField : this.f3207d.keySet()) {
                if ((temporalField instanceof ChronoField) && temporalField.isDateBased()) {
                    try {
                        long h2 = localDate.h(temporalField);
                        Long l2 = this.f3207d.get(temporalField);
                        if (h2 != l2.longValue()) {
                            throw new DateTimeException("Conflict found: Field " + temporalField + " " + h2 + " differs from " + temporalField + " " + l2 + " derived from " + localDate);
                        }
                    } catch (DateTimeException unused) {
                    }
                }
            }
        }
    }

    private void r() {
        LocalTime localTime;
        if (this.f3207d.size() > 0) {
            ChronoLocalDate chronoLocalDate = this.f3210g;
            if (chronoLocalDate != null && (localTime = this.f3211h) != null) {
                s(chronoLocalDate.n(localTime));
            } else if (chronoLocalDate != null) {
                s(chronoLocalDate);
            } else {
                TemporalAccessor temporalAccessor = this.f3211h;
                if (temporalAccessor != null) {
                    s(temporalAccessor);
                }
            }
        }
    }

    private void s(TemporalAccessor temporalAccessor) {
        Iterator<Map.Entry<TemporalField, Long>> it = this.f3207d.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<TemporalField, Long> next = it.next();
            TemporalField key = next.getKey();
            long longValue = next.getValue().longValue();
            if (temporalAccessor.d(key)) {
                try {
                    long h2 = temporalAccessor.h(key);
                    if (h2 == longValue) {
                        it.remove();
                    } else {
                        throw new DateTimeException("Cross check failed: " + key + " " + h2 + " vs " + key + " " + longValue);
                    }
                } catch (RuntimeException unused) {
                }
            }
        }
    }

    private Long t(TemporalField temporalField) {
        return this.f3207d.get(temporalField);
    }

    private void u(ResolverStyle resolverStyle) {
        if (this.f3208e instanceof IsoChronology) {
            q(IsoChronology.f3167h.w(this.f3207d, resolverStyle));
            return;
        }
        Map<TemporalField, Long> map = this.f3207d;
        ChronoField chronoField = ChronoField.EPOCH_DAY;
        if (map.containsKey(chronoField)) {
            q(LocalDate.W(this.f3207d.remove(chronoField).longValue()));
        }
    }

    private void v() {
        if (this.f3207d.containsKey(ChronoField.INSTANT_SECONDS)) {
            ZoneId zoneId = this.f3209f;
            if (zoneId != null) {
                w(zoneId);
                return;
            }
            Long l2 = this.f3207d.get(ChronoField.OFFSET_SECONDS);
            if (l2 != null) {
                w(ZoneOffset.A(l2.intValue()));
            }
        }
    }

    private void w(ZoneId zoneId) {
        Map<TemporalField, Long> map = this.f3207d;
        ChronoField chronoField = ChronoField.INSTANT_SECONDS;
        ChronoZonedDateTime<?> q2 = this.f3208e.q(Instant.u(map.remove(chronoField).longValue()), zoneId);
        if (this.f3210g == null) {
            o(q2.v());
        } else {
            E(chronoField, q2.v());
        }
        m(ChronoField.SECOND_OF_DAY, q2.x().L());
    }

    private void x(ResolverStyle resolverStyle) {
        Map<TemporalField, Long> map = this.f3207d;
        ChronoField chronoField = ChronoField.CLOCK_HOUR_OF_DAY;
        long j2 = 0;
        if (map.containsKey(chronoField)) {
            long longValue = this.f3207d.remove(chronoField).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue != 0)) {
                chronoField.h(longValue);
            }
            ChronoField chronoField2 = ChronoField.HOUR_OF_DAY;
            if (longValue == 24) {
                longValue = 0;
            }
            m(chronoField2, longValue);
        }
        Map<TemporalField, Long> map2 = this.f3207d;
        ChronoField chronoField3 = ChronoField.CLOCK_HOUR_OF_AMPM;
        if (map2.containsKey(chronoField3)) {
            long longValue2 = this.f3207d.remove(chronoField3).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || longValue2 != 0)) {
                chronoField3.h(longValue2);
            }
            ChronoField chronoField4 = ChronoField.HOUR_OF_AMPM;
            if (longValue2 != 12) {
                j2 = longValue2;
            }
            m(chronoField4, j2);
        }
        ResolverStyle resolverStyle2 = ResolverStyle.LENIENT;
        if (resolverStyle != resolverStyle2) {
            Map<TemporalField, Long> map3 = this.f3207d;
            ChronoField chronoField5 = ChronoField.AMPM_OF_DAY;
            if (map3.containsKey(chronoField5)) {
                chronoField5.h(this.f3207d.get(chronoField5).longValue());
            }
            Map<TemporalField, Long> map4 = this.f3207d;
            ChronoField chronoField6 = ChronoField.HOUR_OF_AMPM;
            if (map4.containsKey(chronoField6)) {
                chronoField6.h(this.f3207d.get(chronoField6).longValue());
            }
        }
        Map<TemporalField, Long> map5 = this.f3207d;
        ChronoField chronoField7 = ChronoField.AMPM_OF_DAY;
        if (map5.containsKey(chronoField7)) {
            Map<TemporalField, Long> map6 = this.f3207d;
            ChronoField chronoField8 = ChronoField.HOUR_OF_AMPM;
            if (map6.containsKey(chronoField8)) {
                m(ChronoField.HOUR_OF_DAY, (this.f3207d.remove(chronoField7).longValue() * 12) + this.f3207d.remove(chronoField8).longValue());
            }
        }
        Map<TemporalField, Long> map7 = this.f3207d;
        ChronoField chronoField9 = ChronoField.NANO_OF_DAY;
        if (map7.containsKey(chronoField9)) {
            long longValue3 = this.f3207d.remove(chronoField9).longValue();
            if (resolverStyle != resolverStyle2) {
                chronoField9.h(longValue3);
            }
            m(ChronoField.SECOND_OF_DAY, longValue3 / 1000000000);
            m(ChronoField.NANO_OF_SECOND, longValue3 % 1000000000);
        }
        Map<TemporalField, Long> map8 = this.f3207d;
        ChronoField chronoField10 = ChronoField.MICRO_OF_DAY;
        if (map8.containsKey(chronoField10)) {
            long longValue4 = this.f3207d.remove(chronoField10).longValue();
            if (resolverStyle != resolverStyle2) {
                chronoField10.h(longValue4);
            }
            m(ChronoField.SECOND_OF_DAY, longValue4 / 1000000);
            m(ChronoField.MICRO_OF_SECOND, longValue4 % 1000000);
        }
        Map<TemporalField, Long> map9 = this.f3207d;
        ChronoField chronoField11 = ChronoField.MILLI_OF_DAY;
        if (map9.containsKey(chronoField11)) {
            long longValue5 = this.f3207d.remove(chronoField11).longValue();
            if (resolverStyle != resolverStyle2) {
                chronoField11.h(longValue5);
            }
            m(ChronoField.SECOND_OF_DAY, longValue5 / 1000);
            m(ChronoField.MILLI_OF_SECOND, longValue5 % 1000);
        }
        Map<TemporalField, Long> map10 = this.f3207d;
        ChronoField chronoField12 = ChronoField.SECOND_OF_DAY;
        if (map10.containsKey(chronoField12)) {
            long longValue6 = this.f3207d.remove(chronoField12).longValue();
            if (resolverStyle != resolverStyle2) {
                chronoField12.h(longValue6);
            }
            m(ChronoField.HOUR_OF_DAY, longValue6 / 3600);
            m(ChronoField.MINUTE_OF_HOUR, (longValue6 / 60) % 60);
            m(ChronoField.SECOND_OF_MINUTE, longValue6 % 60);
        }
        Map<TemporalField, Long> map11 = this.f3207d;
        ChronoField chronoField13 = ChronoField.MINUTE_OF_DAY;
        if (map11.containsKey(chronoField13)) {
            long longValue7 = this.f3207d.remove(chronoField13).longValue();
            if (resolverStyle != resolverStyle2) {
                chronoField13.h(longValue7);
            }
            m(ChronoField.HOUR_OF_DAY, longValue7 / 60);
            m(ChronoField.MINUTE_OF_HOUR, longValue7 % 60);
        }
        if (resolverStyle != resolverStyle2) {
            Map<TemporalField, Long> map12 = this.f3207d;
            ChronoField chronoField14 = ChronoField.MILLI_OF_SECOND;
            if (map12.containsKey(chronoField14)) {
                chronoField14.h(this.f3207d.get(chronoField14).longValue());
            }
            Map<TemporalField, Long> map13 = this.f3207d;
            ChronoField chronoField15 = ChronoField.MICRO_OF_SECOND;
            if (map13.containsKey(chronoField15)) {
                chronoField15.h(this.f3207d.get(chronoField15).longValue());
            }
        }
        Map<TemporalField, Long> map14 = this.f3207d;
        ChronoField chronoField16 = ChronoField.MILLI_OF_SECOND;
        if (map14.containsKey(chronoField16)) {
            Map<TemporalField, Long> map15 = this.f3207d;
            ChronoField chronoField17 = ChronoField.MICRO_OF_SECOND;
            if (map15.containsKey(chronoField17)) {
                m(chronoField17, (this.f3207d.remove(chronoField16).longValue() * 1000) + (this.f3207d.get(chronoField17).longValue() % 1000));
            }
        }
        Map<TemporalField, Long> map16 = this.f3207d;
        ChronoField chronoField18 = ChronoField.MICRO_OF_SECOND;
        if (map16.containsKey(chronoField18)) {
            Map<TemporalField, Long> map17 = this.f3207d;
            ChronoField chronoField19 = ChronoField.NANO_OF_SECOND;
            if (map17.containsKey(chronoField19)) {
                m(chronoField18, this.f3207d.get(chronoField19).longValue() / 1000);
                this.f3207d.remove(chronoField18);
            }
        }
        if (this.f3207d.containsKey(chronoField16)) {
            Map<TemporalField, Long> map18 = this.f3207d;
            ChronoField chronoField20 = ChronoField.NANO_OF_SECOND;
            if (map18.containsKey(chronoField20)) {
                m(chronoField16, this.f3207d.get(chronoField20).longValue() / 1000000);
                this.f3207d.remove(chronoField16);
            }
        }
        if (this.f3207d.containsKey(chronoField18)) {
            m(ChronoField.NANO_OF_SECOND, this.f3207d.remove(chronoField18).longValue() * 1000);
        } else if (this.f3207d.containsKey(chronoField16)) {
            m(ChronoField.NANO_OF_SECOND, this.f3207d.remove(chronoField16).longValue() * 1000000);
        }
    }

    private DateTimeBuilder y(TemporalField temporalField, long j2) {
        this.f3207d.put(temporalField, Long.valueOf(j2));
        return this;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.g()) {
            return (R) this.f3209f;
        }
        if (temporalQuery == TemporalQueries.a()) {
            return (R) this.f3208e;
        }
        if (temporalQuery == TemporalQueries.b()) {
            ChronoLocalDate chronoLocalDate = this.f3210g;
            if (chronoLocalDate == null) {
                return null;
            }
            return (R) LocalDate.D(chronoLocalDate);
        } else if (temporalQuery == TemporalQueries.c()) {
            return (R) this.f3211h;
        } else {
            if (temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.d()) {
                if (temporalQuery == TemporalQueries.e()) {
                    return null;
                }
                return temporalQuery.a(this);
            }
            return temporalQuery.a(this);
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        ChronoLocalDate chronoLocalDate;
        LocalTime localTime;
        if (temporalField == null) {
            return false;
        }
        if (!this.f3207d.containsKey(temporalField) && (((chronoLocalDate = this.f3210g) == null || !chronoLocalDate.d(temporalField)) && ((localTime = this.f3211h) == null || !localTime.d(temporalField)))) {
            return false;
        }
        return true;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        Jdk8Methods.i(temporalField, "field");
        Long t2 = t(temporalField);
        if (t2 == null) {
            ChronoLocalDate chronoLocalDate = this.f3210g;
            if (chronoLocalDate != null && chronoLocalDate.d(temporalField)) {
                return this.f3210g.h(temporalField);
            }
            LocalTime localTime = this.f3211h;
            if (localTime != null && localTime.d(temporalField)) {
                return this.f3211h.h(temporalField);
            }
            throw new DateTimeException("Field not found: " + temporalField);
        }
        return t2.longValue();
    }

    DateTimeBuilder m(TemporalField temporalField, long j2) {
        Jdk8Methods.i(temporalField, "field");
        Long t2 = t(temporalField);
        if (t2 != null && t2.longValue() != j2) {
            throw new DateTimeException("Conflict found: " + temporalField + " " + t2 + " differs from " + temporalField + " " + j2 + ": " + this);
        }
        return y(temporalField, j2);
    }

    void n(LocalTime localTime) {
        this.f3211h = localTime;
    }

    void o(ChronoLocalDate chronoLocalDate) {
        this.f3210g = chronoLocalDate;
    }

    public <R> R p(TemporalQuery<R> temporalQuery) {
        return temporalQuery.a(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("DateTimeBuilder[");
        if (this.f3207d.size() > 0) {
            sb.append("fields=");
            sb.append(this.f3207d);
        }
        sb.append(", ");
        sb.append(this.f3208e);
        sb.append(", ");
        sb.append(this.f3209f);
        sb.append(", ");
        sb.append(this.f3210g);
        sb.append(", ");
        sb.append(this.f3211h);
        sb.append(']');
        return sb.toString();
    }

    public DateTimeBuilder z(ResolverStyle resolverStyle, Set<TemporalField> set) {
        ChronoLocalDate chronoLocalDate;
        if (set != null) {
            this.f3207d.keySet().retainAll(set);
        }
        v();
        u(resolverStyle);
        x(resolverStyle);
        if (A(resolverStyle)) {
            v();
            u(resolverStyle);
            x(resolverStyle);
        }
        F(resolverStyle);
        r();
        Period period = this.f3213j;
        if (period != null && !period.c() && (chronoLocalDate = this.f3210g) != null && this.f3211h != null) {
            this.f3210g = chronoLocalDate.v(this.f3213j);
            this.f3213j = Period.f3085g;
        }
        B();
        C();
        return this;
    }
}
