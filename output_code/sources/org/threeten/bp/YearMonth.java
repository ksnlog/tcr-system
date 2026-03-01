package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.SignStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class YearMonth extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<YearMonth>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final TemporalQuery<YearMonth> f3097f = new TemporalQuery<YearMonth>() { // from class: org.threeten.bp.YearMonth.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public YearMonth a(TemporalAccessor temporalAccessor) {
            return YearMonth.n(temporalAccessor);
        }
    };

    /* renamed from: g  reason: collision with root package name */
    private static final DateTimeFormatter f3098g = new DateTimeFormatterBuilder().l(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).e('-').k(ChronoField.MONTH_OF_YEAR, 2).s();

    /* renamed from: d  reason: collision with root package name */
    private final int f3099d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3100e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.YearMonth$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3101a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3102b;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3102b = iArr;
            try {
                iArr[ChronoUnit.MONTHS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3102b[ChronoUnit.YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3102b[ChronoUnit.DECADES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3102b[ChronoUnit.CENTURIES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3102b[ChronoUnit.MILLENNIA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3102b[ChronoUnit.ERAS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            f3101a = iArr2;
            try {
                iArr2[ChronoField.MONTH_OF_YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3101a[ChronoField.PROLEPTIC_MONTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3101a[ChronoField.YEAR_OF_ERA.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3101a[ChronoField.YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3101a[ChronoField.ERA.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private YearMonth(int i2, int i3) {
        this.f3099d = i2;
        this.f3100e = i3;
    }

    public static YearMonth n(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof YearMonth) {
            return (YearMonth) temporalAccessor;
        }
        try {
            if (!IsoChronology.f3167h.equals(Chronology.g(temporalAccessor))) {
                temporalAccessor = LocalDate.D(temporalAccessor);
            }
            return r(temporalAccessor.f(ChronoField.YEAR), temporalAccessor.f(ChronoField.MONTH_OF_YEAR));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain YearMonth from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    private long o() {
        return (this.f3099d * 12) + (this.f3100e - 1);
    }

    public static YearMonth r(int i2, int i3) {
        ChronoField.YEAR.h(i2);
        ChronoField.MONTH_OF_YEAR.h(i3);
        return new YearMonth(i2, i3);
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static YearMonth v(DataInput dataInput) {
        return r(dataInput.readInt(), dataInput.readByte());
    }

    private YearMonth w(int i2, int i3) {
        if (this.f3099d == i2 && this.f3100e == i3) {
            return this;
        }
        return new YearMonth(i2, i3);
    }

    private Object writeReplace() {
        return new Ser((byte) 68, this);
    }

    public YearMonth A(int i2) {
        ChronoField.YEAR.h(i2);
        return w(i2, this.f3100e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(DataOutput dataOutput) {
        dataOutput.writeInt(this.f3099d);
        dataOutput.writeByte(this.f3100e);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        long j2;
        if (temporalField == ChronoField.YEAR_OF_ERA) {
            if (p() <= 0) {
                j2 = 1000000000;
            } else {
                j2 = 999999999;
            }
            return ValueRange.i(1L, j2);
        }
        return super.a(temporalField);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) IsoChronology.f3167h;
        }
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.MONTHS;
        }
        if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return (R) super.b(temporalQuery);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.YEAR || temporalField == ChronoField.MONTH_OF_YEAR || temporalField == ChronoField.PROLEPTIC_MONTH || temporalField == ChronoField.YEAR_OF_ERA || temporalField == ChronoField.ERA) {
                return true;
            }
            return false;
        } else if (temporalField != null && temporalField.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof YearMonth)) {
            return false;
        }
        YearMonth yearMonth = (YearMonth) obj;
        if (this.f3099d == yearMonth.f3099d && this.f3100e == yearMonth.f3100e) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        return a(temporalField).a(h(temporalField), temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        int i2;
        if (temporalField instanceof ChronoField) {
            int i3 = AnonymousClass2.f3101a[((ChronoField) temporalField).ordinal()];
            int i4 = 1;
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        if (i3 != 4) {
                            if (i3 == 5) {
                                if (this.f3099d < 1) {
                                    i4 = 0;
                                }
                                return i4;
                            }
                            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                        }
                        i2 = this.f3099d;
                    } else {
                        int i5 = this.f3099d;
                        if (i5 < 1) {
                            i5 = 1 - i5;
                        }
                        return i5;
                    }
                } else {
                    return o();
                }
            } else {
                i2 = this.f3100e;
            }
            return i2;
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return this.f3099d ^ (this.f3100e << 27);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        if (Chronology.g(temporal).equals(IsoChronology.f3167h)) {
            return temporal.e(ChronoField.PROLEPTIC_MONTH, o());
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        YearMonth n2 = n(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long o2 = n2.o() - o();
            switch (AnonymousClass2.f3102b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return o2;
                case 2:
                    return o2 / 12;
                case 3:
                    return o2 / 120;
                case 4:
                    return o2 / 1200;
                case 5:
                    return o2 / 12000;
                case R$styleable.f1342r /* 6 */:
                    ChronoField chronoField = ChronoField.ERA;
                    return n2.h(chronoField) - h(chronoField);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.a(this, n2);
    }

    @Override // java.lang.Comparable
    /* renamed from: m */
    public int compareTo(YearMonth yearMonth) {
        int i2 = this.f3099d - yearMonth.f3099d;
        if (i2 == 0) {
            return this.f3100e - yearMonth.f3100e;
        }
        return i2;
    }

    public int p() {
        return this.f3099d;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: q */
    public YearMonth g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: s */
    public YearMonth i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3102b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return t(j2);
                case 2:
                    return u(j2);
                case 3:
                    return u(Jdk8Methods.l(j2, 10));
                case 4:
                    return u(Jdk8Methods.l(j2, 100));
                case 5:
                    return u(Jdk8Methods.l(j2, 1000));
                case R$styleable.f1342r /* 6 */:
                    ChronoField chronoField = ChronoField.ERA;
                    return e(chronoField, Jdk8Methods.k(h(chronoField), j2));
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (YearMonth) temporalUnit.b(this, j2);
    }

    public YearMonth t(long j2) {
        if (j2 == 0) {
            return this;
        }
        long j3 = (this.f3099d * 12) + (this.f3100e - 1) + j2;
        return w(ChronoField.YEAR.g(Jdk8Methods.e(j3, 12L)), Jdk8Methods.g(j3, 12) + 1);
    }

    public String toString() {
        String str;
        int abs = Math.abs(this.f3099d);
        StringBuilder sb = new StringBuilder(9);
        if (abs < 1000) {
            int i2 = this.f3099d;
            if (i2 < 0) {
                sb.append(i2 - 10000);
                sb.deleteCharAt(1);
            } else {
                sb.append(i2 + 10000);
                sb.deleteCharAt(0);
            }
        } else {
            sb.append(this.f3099d);
        }
        if (this.f3100e < 10) {
            str = "-0";
        } else {
            str = "-";
        }
        sb.append(str);
        sb.append(this.f3100e);
        return sb.toString();
    }

    public YearMonth u(long j2) {
        if (j2 == 0) {
            return this;
        }
        return w(ChronoField.YEAR.g(this.f3099d + j2), this.f3100e);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: x */
    public YearMonth c(TemporalAdjuster temporalAdjuster) {
        return (YearMonth) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: y */
    public YearMonth e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            int i2 = AnonymousClass2.f3101a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5) {
                                if (h(ChronoField.ERA) == j2) {
                                    return this;
                                }
                                return A(1 - this.f3099d);
                            }
                            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                        }
                        return A((int) j2);
                    }
                    if (this.f3099d < 1) {
                        j2 = 1 - j2;
                    }
                    return A((int) j2);
                }
                return t(j2 - h(ChronoField.PROLEPTIC_MONTH));
            }
            return z((int) j2);
        }
        return (YearMonth) temporalField.b(this, j2);
    }

    public YearMonth z(int i2) {
        ChronoField.MONTH_OF_YEAR.h(i2);
        return w(this.f3099d, i2);
    }
}
