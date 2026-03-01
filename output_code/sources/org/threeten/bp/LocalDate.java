package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Era;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LocalDate extends ChronoLocalDate implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final LocalDate f3025h = U(-999999999, 1, 1);

    /* renamed from: i  reason: collision with root package name */
    public static final LocalDate f3026i = U(999999999, 12, 31);

    /* renamed from: j  reason: collision with root package name */
    public static final TemporalQuery<LocalDate> f3027j = new TemporalQuery<LocalDate>() { // from class: org.threeten.bp.LocalDate.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public LocalDate a(TemporalAccessor temporalAccessor) {
            return LocalDate.D(temporalAccessor);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private final int f3028e;

    /* renamed from: f  reason: collision with root package name */
    private final short f3029f;

    /* renamed from: g  reason: collision with root package name */
    private final short f3030g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.LocalDate$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3031a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3032b;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3032b = iArr;
            try {
                iArr[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3032b[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3032b[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3032b[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3032b[ChronoUnit.DECADES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3032b[ChronoUnit.CENTURIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3032b[ChronoUnit.MILLENNIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3032b[ChronoUnit.ERAS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            f3031a = iArr2;
            try {
                iArr2[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3031a[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3031a[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3031a[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3031a[ChronoField.DAY_OF_WEEK.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3031a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3031a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f3031a[ChronoField.EPOCH_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3031a[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f3031a[ChronoField.MONTH_OF_YEAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f3031a[ChronoField.PROLEPTIC_MONTH.ordinal()] = 11;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f3031a[ChronoField.YEAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f3031a[ChronoField.ERA.ordinal()] = 13;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    private LocalDate(int i2, int i3, int i4) {
        this.f3028e = i2;
        this.f3029f = (short) i3;
        this.f3030g = (short) i4;
    }

    private static LocalDate B(int i2, Month month, int i3) {
        if (i3 > 28 && i3 > month.n(IsoChronology.f3167h.u(i2))) {
            if (i3 == 29) {
                throw new DateTimeException("Invalid date 'February 29' as '" + i2 + "' is not a leap year");
            }
            throw new DateTimeException("Invalid date '" + month.name() + " " + i3 + "'");
        }
        return new LocalDate(i2, month.getValue(), i3);
    }

    public static LocalDate D(TemporalAccessor temporalAccessor) {
        LocalDate localDate = (LocalDate) temporalAccessor.b(TemporalQueries.b());
        if (localDate != null) {
            return localDate;
        }
        throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    private int E(TemporalField temporalField) {
        switch (AnonymousClass2.f3031a[((ChronoField) temporalField).ordinal()]) {
            case 1:
                return this.f3030g;
            case 2:
                return I();
            case 3:
                return ((this.f3030g - 1) / 7) + 1;
            case 4:
                int i2 = this.f3028e;
                if (i2 < 1) {
                    return 1 - i2;
                }
                return i2;
            case 5:
                return H().getValue();
            case R$styleable.f1342r /* 6 */:
                return ((this.f3030g - 1) % 7) + 1;
            case R$styleable.f1343s /* 7 */:
                return ((I() - 1) % 7) + 1;
            case R$styleable.f1328d /* 8 */:
                throw new DateTimeException("Field too large for an int: " + temporalField);
            case R$styleable.f1329e /* 9 */:
                return ((I() - 1) / 7) + 1;
            case R$styleable.f1330f /* 10 */:
                return this.f3029f;
            case R$styleable.f1331g /* 11 */:
                throw new DateTimeException("Field too large for an int: " + temporalField);
            case R$styleable.f1332h /* 12 */:
                return this.f3028e;
            case R$styleable.f1333i /* 13 */:
                if (this.f3028e >= 1) {
                    return 1;
                }
                return 0;
            default:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
    }

    private long L() {
        return (this.f3028e * 12) + (this.f3029f - 1);
    }

    private long T(LocalDate localDate) {
        return (((localDate.L() * 32) + localDate.G()) - ((L() * 32) + G())) / 32;
    }

    public static LocalDate U(int i2, int i3, int i4) {
        ChronoField.YEAR.h(i2);
        ChronoField.MONTH_OF_YEAR.h(i3);
        ChronoField.DAY_OF_MONTH.h(i4);
        return B(i2, Month.q(i3), i4);
    }

    public static LocalDate V(int i2, Month month, int i3) {
        ChronoField.YEAR.h(i2);
        Jdk8Methods.i(month, "month");
        ChronoField.DAY_OF_MONTH.h(i3);
        return B(i2, month, i3);
    }

    public static LocalDate W(long j2) {
        long j3;
        ChronoField.EPOCH_DAY.h(j2);
        long j4 = (j2 + 719528) - 60;
        if (j4 < 0) {
            long j5 = ((j4 + 1) / 146097) - 1;
            j3 = j5 * 400;
            j4 += (-j5) * 146097;
        } else {
            j3 = 0;
        }
        long j6 = ((j4 * 400) + 591) / 146097;
        long j7 = j4 - ((((j6 * 365) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        if (j7 < 0) {
            j6--;
            j7 = j4 - ((((365 * j6) + (j6 / 4)) - (j6 / 100)) + (j6 / 400));
        }
        int i2 = (int) j7;
        int i3 = ((i2 * 5) + 2) / 153;
        return new LocalDate(ChronoField.YEAR.g(j6 + j3 + (i3 / 10)), ((i3 + 2) % 12) + 1, (i2 - (((i3 * 306) + 5) / 10)) + 1);
    }

    public static LocalDate X(int i2, int i3) {
        long j2 = i2;
        ChronoField.YEAR.h(j2);
        ChronoField.DAY_OF_YEAR.h(i3);
        boolean u2 = IsoChronology.f3167h.u(j2);
        if (i3 == 366 && !u2) {
            throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + i2 + "' is not a leap year");
        }
        Month q2 = Month.q(((i3 - 1) / 31) + 1);
        if (i3 > (q2.k(u2) + q2.n(u2)) - 1) {
            q2 = q2.r(1L);
        }
        return B(i2, q2, (i3 - q2.k(u2)) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalDate e0(DataInput dataInput) {
        return U(dataInput.readInt(), dataInput.readByte(), dataInput.readByte());
    }

    private static LocalDate f0(int i2, int i3, int i4) {
        int i5;
        if (i3 != 2) {
            if (i3 == 4 || i3 == 6 || i3 == 9 || i3 == 11) {
                i4 = Math.min(i4, 30);
            }
        } else {
            if (IsoChronology.f3167h.u(i2)) {
                i5 = 29;
            } else {
                i5 = 28;
            }
            i4 = Math.min(i4, i5);
        }
        return U(i2, i3, i4);
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int A(LocalDate localDate) {
        int i2 = this.f3028e - localDate.f3028e;
        if (i2 == 0) {
            int i3 = this.f3029f - localDate.f3029f;
            if (i3 == 0) {
                return this.f3030g - localDate.f3030g;
            }
            return i3;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long C(LocalDate localDate) {
        return localDate.w() - w();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: F */
    public IsoChronology p() {
        return IsoChronology.f3167h;
    }

    public int G() {
        return this.f3030g;
    }

    public DayOfWeek H() {
        return DayOfWeek.m(Jdk8Methods.g(w() + 3, 7) + 1);
    }

    public int I() {
        return (J().k(N()) + this.f3030g) - 1;
    }

    public Month J() {
        return Month.q(this.f3029f);
    }

    public int K() {
        return this.f3029f;
    }

    public int M() {
        return this.f3028e;
    }

    public boolean N() {
        return IsoChronology.f3167h.u(this.f3028e);
    }

    public int O() {
        short s2 = this.f3029f;
        if (s2 != 2) {
            if (s2 != 4 && s2 != 6 && s2 != 9 && s2 != 11) {
                return 31;
            }
            return 30;
        } else if (N()) {
            return 29;
        } else {
            return 28;
        }
    }

    public int P() {
        return N() ? 366 : 365;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: Q */
    public LocalDate t(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? u(Long.MAX_VALUE, temporalUnit).u(1L, temporalUnit) : u(-j2, temporalUnit);
    }

    public LocalDate R(long j2) {
        return j2 == Long.MIN_VALUE ? a0(Long.MAX_VALUE).a0(1L) : a0(-j2);
    }

    public LocalDate S(long j2) {
        return j2 == Long.MIN_VALUE ? d0(Long.MAX_VALUE).d0(1L) : d0(-j2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: Y */
    public LocalDate u(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3032b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return a0(j2);
                case 2:
                    return c0(j2);
                case 3:
                    return b0(j2);
                case 4:
                    return d0(j2);
                case 5:
                    return d0(Jdk8Methods.l(j2, 10));
                case R$styleable.f1342r /* 6 */:
                    return d0(Jdk8Methods.l(j2, 100));
                case R$styleable.f1343s /* 7 */:
                    return d0(Jdk8Methods.l(j2, 1000));
                case R$styleable.f1328d /* 8 */:
                    ChronoField chronoField = ChronoField.ERA;
                    return y(chronoField, Jdk8Methods.k(h(chronoField), j2));
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (LocalDate) temporalUnit.b(this, j2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: Z */
    public LocalDate v(TemporalAmount temporalAmount) {
        return (LocalDate) temporalAmount.a(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        long j2;
        long j3;
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (chronoField.isDateBased()) {
                int i2 = AnonymousClass2.f3031a[chronoField.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                return temporalField.f();
                            }
                            if (M() <= 0) {
                                j3 = 1000000000;
                            } else {
                                j3 = 999999999;
                            }
                            return ValueRange.i(1L, j3);
                        }
                        if (J() == Month.FEBRUARY && !N()) {
                            j2 = 4;
                        } else {
                            j2 = 5;
                        }
                        return ValueRange.i(1L, j2);
                    }
                    return ValueRange.i(1L, P());
                }
                return ValueRange.i(1L, O());
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.d(this);
    }

    public LocalDate a0(long j2) {
        if (j2 == 0) {
            return this;
        }
        return W(Jdk8Methods.k(w(), j2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.b()) {
            return this;
        }
        return (R) super.b(temporalQuery);
    }

    public LocalDate b0(long j2) {
        if (j2 == 0) {
            return this;
        }
        long j3 = (this.f3028e * 12) + (this.f3029f - 1) + j2;
        return f0(ChronoField.YEAR.g(Jdk8Methods.e(j3, 12L)), Jdk8Methods.g(j3, 12) + 1, this.f3030g);
    }

    public LocalDate c0(long j2) {
        return a0(Jdk8Methods.l(j2, 7));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        return super.d(temporalField);
    }

    public LocalDate d0(long j2) {
        if (j2 == 0) {
            return this;
        }
        return f0(ChronoField.YEAR.g(this.f3028e + j2), this.f3029f, this.f3030g);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof LocalDate) && A((LocalDate) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return E(temporalField);
        }
        return super.f(temporalField);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: g0 */
    public LocalDate x(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return (LocalDate) temporalAdjuster;
        }
        return (LocalDate) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.EPOCH_DAY) {
                return w();
            }
            if (temporalField == ChronoField.PROLEPTIC_MONTH) {
                return L();
            }
            return E(temporalField);
        }
        return temporalField.c(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: h0 */
    public LocalDate y(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            switch (AnonymousClass2.f3031a[chronoField.ordinal()]) {
                case 1:
                    return i0((int) j2);
                case 2:
                    return j0((int) j2);
                case 3:
                    return c0(j2 - h(ChronoField.ALIGNED_WEEK_OF_MONTH));
                case 4:
                    if (this.f3028e < 1) {
                        j2 = 1 - j2;
                    }
                    return l0((int) j2);
                case 5:
                    return a0(j2 - H().getValue());
                case R$styleable.f1342r /* 6 */:
                    return a0(j2 - h(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case R$styleable.f1343s /* 7 */:
                    return a0(j2 - h(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case R$styleable.f1328d /* 8 */:
                    return W(j2);
                case R$styleable.f1329e /* 9 */:
                    return c0(j2 - h(ChronoField.ALIGNED_WEEK_OF_YEAR));
                case R$styleable.f1330f /* 10 */:
                    return k0((int) j2);
                case R$styleable.f1331g /* 11 */:
                    return b0(j2 - h(ChronoField.PROLEPTIC_MONTH));
                case R$styleable.f1332h /* 12 */:
                    return l0((int) j2);
                case R$styleable.f1333i /* 13 */:
                    if (h(ChronoField.ERA) == j2) {
                        return this;
                    }
                    return l0(1 - this.f3028e);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
        }
        return (LocalDate) temporalField.b(this, j2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        int i2 = this.f3028e;
        return (((i2 << 11) + (this.f3029f << 6)) + this.f3030g) ^ (i2 & (-2048));
    }

    public LocalDate i0(int i2) {
        if (this.f3030g == i2) {
            return this;
        }
        return U(this.f3028e, this.f3029f, i2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return super.j(temporal);
    }

    public LocalDate j0(int i2) {
        if (I() == i2) {
            return this;
        }
        return X(this.f3028e, i2);
    }

    public LocalDate k0(int i2) {
        if (this.f3029f == i2) {
            return this;
        }
        ChronoField.MONTH_OF_YEAR.h(i2);
        return f0(this.f3028e, i2, this.f3030g);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        LocalDate D = D(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3032b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return C(D);
                case 2:
                    return C(D) / 7;
                case 3:
                    return T(D);
                case 4:
                    return T(D) / 12;
                case 5:
                    return T(D) / 120;
                case R$styleable.f1342r /* 6 */:
                    return T(D) / 1200;
                case R$styleable.f1343s /* 7 */:
                    return T(D) / 12000;
                case R$styleable.f1328d /* 8 */:
                    ChronoField chronoField = ChronoField.ERA;
                    return D.h(chronoField) - h(chronoField);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.a(this, D);
    }

    public LocalDate l0(int i2) {
        if (this.f3028e == i2) {
            return this;
        }
        ChronoField.YEAR.h(i2);
        return f0(i2, this.f3029f, this.f3030g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m0(DataOutput dataOutput) {
        dataOutput.writeInt(this.f3028e);
        dataOutput.writeByte(this.f3029f);
        dataOutput.writeByte(this.f3030g);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, java.lang.Comparable
    /* renamed from: o */
    public int compareTo(ChronoLocalDate chronoLocalDate) {
        if (chronoLocalDate instanceof LocalDate) {
            return A((LocalDate) chronoLocalDate);
        }
        return super.compareTo(chronoLocalDate);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public Era q() {
        return super.q();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean r(ChronoLocalDate chronoLocalDate) {
        if (chronoLocalDate instanceof LocalDate) {
            if (A((LocalDate) chronoLocalDate) > 0) {
                return true;
            }
            return false;
        }
        return super.r(chronoLocalDate);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean s(ChronoLocalDate chronoLocalDate) {
        if (chronoLocalDate instanceof LocalDate) {
            if (A((LocalDate) chronoLocalDate) < 0) {
                return true;
            }
            return false;
        }
        return super.s(chronoLocalDate);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public String toString() {
        String str;
        int i2 = this.f3028e;
        short s2 = this.f3029f;
        short s3 = this.f3030g;
        int abs = Math.abs(i2);
        StringBuilder sb = new StringBuilder(10);
        if (abs < 1000) {
            if (i2 < 0) {
                sb.append(i2 - 10000);
                sb.deleteCharAt(1);
            } else {
                sb.append(i2 + 10000);
                sb.deleteCharAt(0);
            }
        } else {
            if (i2 > 9999) {
                sb.append('+');
            }
            sb.append(i2);
        }
        String str2 = "-0";
        if (s2 >= 10) {
            str = "-";
        } else {
            str = str2;
        }
        sb.append(str);
        sb.append((int) s2);
        if (s3 >= 10) {
            str2 = "-";
        }
        sb.append(str2);
        sb.append((int) s3);
        return sb.toString();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long w() {
        long j2;
        long j3 = this.f3028e;
        long j4 = this.f3029f;
        long j5 = (365 * j3) + 0;
        if (j3 >= 0) {
            j2 = j5 + (((3 + j3) / 4) - ((99 + j3) / 100)) + ((j3 + 399) / 400);
        } else {
            j2 = j5 - (((j3 / (-4)) - (j3 / (-100))) + (j3 / (-400)));
        }
        long j6 = j2 + (((367 * j4) - 362) / 12) + (this.f3030g - 1);
        if (j4 > 2) {
            j6--;
            if (!N()) {
                j6--;
            }
        }
        return j6 - 719528;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: z */
    public LocalDateTime n(LocalTime localTime) {
        return LocalDateTime.P(this, localTime);
    }
}
