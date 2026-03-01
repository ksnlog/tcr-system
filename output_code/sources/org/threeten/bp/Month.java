package org.threeten.bp;

import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum Month implements TemporalAccessor, TemporalAdjuster {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;
    

    /* renamed from: p  reason: collision with root package name */
    public static final TemporalQuery<Month> f3063p = new TemporalQuery<Month>() { // from class: org.threeten.bp.Month.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public Month a(TemporalAccessor temporalAccessor) {
            return Month.m(temporalAccessor);
        }
    };

    /* renamed from: q  reason: collision with root package name */
    private static final Month[] f3064q = values();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.Month$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3066a;

        static {
            int[] iArr = new int[Month.values().length];
            f3066a = iArr;
            try {
                iArr[Month.FEBRUARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3066a[Month.APRIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3066a[Month.JUNE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3066a[Month.SEPTEMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3066a[Month.NOVEMBER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3066a[Month.JANUARY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3066a[Month.MARCH.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3066a[Month.MAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3066a[Month.JULY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3066a[Month.AUGUST.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3066a[Month.OCTOBER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3066a[Month.DECEMBER.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public static Month m(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Month) {
            return (Month) temporalAccessor;
        }
        try {
            if (!IsoChronology.f3167h.equals(Chronology.g(temporalAccessor))) {
                temporalAccessor = LocalDate.D(temporalAccessor);
            }
            return q(temporalAccessor.f(ChronoField.MONTH_OF_YEAR));
        } catch (DateTimeException e2) {
            throw new DateTimeException("Unable to obtain Month from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName(), e2);
        }
    }

    public static Month q(int i2) {
        if (i2 >= 1 && i2 <= 12) {
            return f3064q[i2 - 1];
        }
        throw new DateTimeException("Invalid value for MonthOfYear: " + i2);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            return temporalField.f();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.d(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) IsoChronology.f3167h;
        }
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.MONTHS;
        }
        if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.MONTH_OF_YEAR) {
                return true;
            }
            return false;
        } else if (temporalField != null && temporalField.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            return getValue();
        }
        return a(temporalField).a(h(temporalField), temporalField);
    }

    public int getValue() {
        return ordinal() + 1;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            return getValue();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.c(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        if (Chronology.g(temporal).equals(IsoChronology.f3167h)) {
            return temporal.e(ChronoField.MONTH_OF_YEAR, getValue());
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int k(boolean z2) {
        switch (AnonymousClass2.f3066a[ordinal()]) {
            case 1:
                return 32;
            case 2:
                return (z2 ? 1 : 0) + 91;
            case 3:
                return z2 + 152;
            case 4:
                return z2 + 244;
            case 5:
                return z2 + 305;
            case R$styleable.f1342r /* 6 */:
                return 1;
            case R$styleable.f1343s /* 7 */:
                return z2 + 60;
            case R$styleable.f1328d /* 8 */:
                return z2 + 121;
            case R$styleable.f1329e /* 9 */:
                return z2 + 182;
            case R$styleable.f1330f /* 10 */:
                return z2 + 213;
            case R$styleable.f1331g /* 11 */:
                return z2 + 274;
            default:
                return z2 + 335;
        }
    }

    public int n(boolean z2) {
        int i2 = AnonymousClass2.f3066a[ordinal()];
        return i2 != 1 ? (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) ? 30 : 31 : z2 ? 29 : 28;
    }

    public int o() {
        int i2 = AnonymousClass2.f3066a[ordinal()];
        if (i2 != 1) {
            return (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) ? 30 : 31;
        }
        return 29;
    }

    public int p() {
        int i2 = AnonymousClass2.f3066a[ordinal()];
        if (i2 != 1) {
            return (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) ? 30 : 31;
        }
        return 28;
    }

    public Month r(long j2) {
        return f3064q[(ordinal() + (((int) (j2 % 12)) + 12)) % 12];
    }
}
