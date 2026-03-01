package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.HashMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HijrahChronology extends Chronology implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final HijrahChronology f3137h = new HijrahChronology();

    /* renamed from: i  reason: collision with root package name */
    private static final HashMap<String, String[]> f3138i;

    /* renamed from: j  reason: collision with root package name */
    private static final HashMap<String, String[]> f3139j;

    /* renamed from: k  reason: collision with root package name */
    private static final HashMap<String, String[]> f3140k;

    static {
        HashMap<String, String[]> hashMap = new HashMap<>();
        f3138i = hashMap;
        HashMap<String, String[]> hashMap2 = new HashMap<>();
        f3139j = hashMap2;
        HashMap<String, String[]> hashMap3 = new HashMap<>();
        f3140k = hashMap3;
        hashMap.put("en", new String[]{"BH", "HE"});
        hashMap2.put("en", new String[]{"B.H.", "H.E."});
        hashMap3.put("en", new String[]{"Before Hijrah", "Hijrah Era"});
    }

    private HijrahChronology() {
    }

    private Object readResolve() {
        return f3137h;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String h() {
        return "islamic-umalqura";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String i() {
        return "Hijrah-umalqura";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<HijrahDate> k(TemporalAccessor temporalAccessor) {
        return super.k(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> q(Instant instant, ZoneId zoneId) {
        return super.q(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> r(TemporalAccessor temporalAccessor) {
        return super.r(temporalAccessor);
    }

    public HijrahDate s(int i2, int i3, int i4) {
        return HijrahDate.f0(i2, i3, i4);
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: t */
    public HijrahDate b(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof HijrahDate) {
            return (HijrahDate) temporalAccessor;
        }
        return HijrahDate.h0(temporalAccessor.h(ChronoField.EPOCH_DAY));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: u */
    public HijrahEra f(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return HijrahEra.AH;
            }
            throw new DateTimeException("invalid Hijrah era");
        }
        return HijrahEra.BEFORE_AH;
    }

    public ValueRange v(ChronoField chronoField) {
        return chronoField.f();
    }
}
