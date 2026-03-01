package org.threeten.bp.chrono;

import java.io.Serializable;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MinguoChronology extends Chronology implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final MinguoChronology f3191h = new MinguoChronology();

    /* renamed from: org.threeten.bp.chrono.MinguoChronology$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3192a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3192a = iArr;
            try {
                iArr[ChronoField.PROLEPTIC_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3192a[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3192a[ChronoField.YEAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private MinguoChronology() {
    }

    private Object readResolve() {
        return f3191h;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String h() {
        return "roc";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String i() {
        return "Minguo";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<MinguoDate> k(TemporalAccessor temporalAccessor) {
        return super.k(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<MinguoDate> q(Instant instant, ZoneId zoneId) {
        return super.q(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<MinguoDate> r(TemporalAccessor temporalAccessor) {
        return super.r(temporalAccessor);
    }

    public MinguoDate s(int i2, int i3, int i4) {
        return new MinguoDate(LocalDate.U(i2 + 1911, i3, i4));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: t */
    public MinguoDate b(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof MinguoDate) {
            return (MinguoDate) temporalAccessor;
        }
        return new MinguoDate(LocalDate.D(temporalAccessor));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: u */
    public MinguoEra f(int i2) {
        return MinguoEra.k(i2);
    }

    public ValueRange v(ChronoField chronoField) {
        int i2 = AnonymousClass1.f3192a[chronoField.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return chronoField.f();
                }
                ValueRange f2 = ChronoField.YEAR.f();
                return ValueRange.i(f2.d() - 1911, f2.c() - 1911);
            }
            ValueRange f3 = ChronoField.YEAR.f();
            return ValueRange.j(1L, f3.c() - 1911, (-f3.d()) + 1 + 1911);
        }
        ValueRange f4 = ChronoField.PROLEPTIC_MONTH.f();
        return ValueRange.i(f4.d() - 22932, f4.c() - 22932);
    }
}
