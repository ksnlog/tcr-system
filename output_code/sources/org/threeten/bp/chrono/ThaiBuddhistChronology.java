package org.threeten.bp.chrono;

import java.io.Serializable;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ThaiBuddhistChronology extends Chronology implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final ThaiBuddhistChronology f3200h = new ThaiBuddhistChronology();

    /* renamed from: org.threeten.bp.chrono.ThaiBuddhistChronology$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3201a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3201a = iArr;
            try {
                iArr[ChronoField.PROLEPTIC_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3201a[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3201a[ChronoField.YEAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ThaiBuddhistChronology() {
    }

    private Object readResolve() {
        return f3200h;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String h() {
        return "buddhist";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String i() {
        return "ThaiBuddhist";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<ThaiBuddhistDate> k(TemporalAccessor temporalAccessor) {
        return super.k(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<ThaiBuddhistDate> q(Instant instant, ZoneId zoneId) {
        return super.q(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<ThaiBuddhistDate> r(TemporalAccessor temporalAccessor) {
        return super.r(temporalAccessor);
    }

    public ThaiBuddhistDate s(int i2, int i3, int i4) {
        return new ThaiBuddhistDate(LocalDate.U(i2 - 543, i3, i4));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: t */
    public ThaiBuddhistDate b(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate) temporalAccessor;
        }
        return new ThaiBuddhistDate(LocalDate.D(temporalAccessor));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: u */
    public ThaiBuddhistEra f(int i2) {
        return ThaiBuddhistEra.k(i2);
    }

    public ValueRange v(ChronoField chronoField) {
        int i2 = AnonymousClass1.f3201a[chronoField.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return chronoField.f();
                }
                ValueRange f2 = ChronoField.YEAR.f();
                return ValueRange.i(f2.d() + 543, f2.c() + 543);
            }
            ValueRange f3 = ChronoField.YEAR.f();
            return ValueRange.j(1L, 1 + (-(f3.d() + 543)), f3.c() + 543);
        }
        ValueRange f4 = ChronoField.PROLEPTIC_MONTH.f();
        return ValueRange.i(f4.d() + 6516, f4.c() + 6516);
    }
}
