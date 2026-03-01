package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.ObjectInputStream;
import java.util.Calendar;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JapaneseDate extends ChronoDateImpl<JapaneseDate> {

    /* renamed from: h  reason: collision with root package name */
    static final LocalDate f3177h = LocalDate.U(1873, 1, 1);

    /* renamed from: e  reason: collision with root package name */
    private final LocalDate f3178e;

    /* renamed from: f  reason: collision with root package name */
    private transient JapaneseEra f3179f;

    /* renamed from: g  reason: collision with root package name */
    private transient int f3180g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.JapaneseDate$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3181a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3181a = iArr;
            try {
                iArr[ChronoField.DAY_OF_YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3181a[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3181a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3181a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3181a[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3181a[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3181a[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JapaneseDate(LocalDate localDate) {
        if (!localDate.s(f3177h)) {
            JapaneseEra n2 = JapaneseEra.n(localDate);
            this.f3179f = n2;
            this.f3180g = localDate.M() - (n2.r().M() - 1);
            this.f3178e = localDate;
            return;
        }
        throw new DateTimeException("Minimum supported date is January 1st Meiji 6");
    }

    private ValueRange D(int i2) {
        Calendar calendar = Calendar.getInstance(JapaneseChronology.f3171h);
        calendar.set(0, this.f3179f.getValue() + 2);
        calendar.set(this.f3180g, this.f3178e.K() - 1, this.f3178e.G());
        return ValueRange.i(calendar.getActualMinimum(i2), calendar.getActualMaximum(i2));
    }

    private long F() {
        int I;
        if (this.f3180g == 1) {
            I = (this.f3178e.I() - this.f3179f.r().I()) + 1;
        } else {
            I = this.f3178e.I();
        }
        return I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate N(DataInput dataInput) {
        return JapaneseChronology.f3172i.s(dataInput.readInt(), dataInput.readByte(), dataInput.readByte());
    }

    private JapaneseDate O(LocalDate localDate) {
        return localDate.equals(this.f3178e) ? this : new JapaneseDate(localDate);
    }

    private JapaneseDate R(int i2) {
        return S(q(), i2);
    }

    private JapaneseDate S(JapaneseEra japaneseEra, int i2) {
        return O(this.f3178e.l0(JapaneseChronology.f3172i.v(japaneseEra, i2)));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        JapaneseEra n2 = JapaneseEra.n(this.f3178e);
        this.f3179f = n2;
        this.f3180g = this.f3178e.M() - (n2.r().M() - 1);
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: E */
    public JapaneseChronology p() {
        return JapaneseChronology.f3172i;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: G */
    public JapaneseEra q() {
        return this.f3179f;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: H */
    public JapaneseDate t(long j2, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.g(j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: I */
    public JapaneseDate z(long j2, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.u(j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: J */
    public JapaneseDate v(TemporalAmount temporalAmount) {
        return (JapaneseDate) super.v(temporalAmount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: K */
    public JapaneseDate A(long j2) {
        return O(this.f3178e.a0(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: L */
    public JapaneseDate B(long j2) {
        return O(this.f3178e.b0(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: M */
    public JapaneseDate C(long j2) {
        return O(this.f3178e.d0(j2));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: P */
    public JapaneseDate x(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.c(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: Q */
    public JapaneseDate y(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            if (h(chronoField) == j2) {
                return this;
            }
            int[] iArr = AnonymousClass1.f3181a;
            int i2 = iArr[chronoField.ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 7) {
                int a2 = p().w(chronoField).a(j2, chronoField);
                int i3 = iArr[chronoField.ordinal()];
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 == 7) {
                            return S(JapaneseEra.o(a2), this.f3180g);
                        }
                    } else {
                        return R(a2);
                    }
                } else {
                    return O(this.f3178e.a0(a2 - F()));
                }
            }
            return O(this.f3178e.y(temporalField, j2));
        }
        return (JapaneseDate) temporalField.b(this, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T(DataOutput dataOutput) {
        dataOutput.writeInt(f(ChronoField.YEAR));
        dataOutput.writeByte(f(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(f(ChronoField.DAY_OF_MONTH));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (d(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i2 = AnonymousClass1.f3181a[chronoField.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        return p().w(chronoField);
                    }
                    return D(1);
                }
                return D(6);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField != ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH && temporalField != ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR && temporalField != ChronoField.ALIGNED_WEEK_OF_MONTH && temporalField != ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return super.d(temporalField);
        }
        return false;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JapaneseDate) {
            return this.f3178e.equals(((JapaneseDate) obj).f3178e);
        }
        return false;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            switch (AnonymousClass1.f3181a[((ChronoField) temporalField).ordinal()]) {
                case 1:
                    return F();
                case 2:
                    return this.f3180g;
                case 3:
                case 4:
                case 5:
                case R$styleable.f1342r /* 6 */:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                case R$styleable.f1343s /* 7 */:
                    return this.f3179f.getValue();
                default:
                    return this.f3178e.h(temporalField);
            }
        }
        return temporalField.c(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        return p().i().hashCode() ^ this.f3178e.hashCode();
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long l(Temporal temporal, TemporalUnit temporalUnit) {
        return super.l(temporal, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<JapaneseDate> n(LocalTime localTime) {
        return super.n(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long w() {
        return this.f3178e.w();
    }
}
