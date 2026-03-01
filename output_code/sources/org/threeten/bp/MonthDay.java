package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MonthDay extends DefaultInterfaceTemporalAccessor implements TemporalAdjuster, Comparable<MonthDay>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final TemporalQuery<MonthDay> f3067f = new TemporalQuery<MonthDay>() { // from class: org.threeten.bp.MonthDay.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public MonthDay a(TemporalAccessor temporalAccessor) {
            return MonthDay.n(temporalAccessor);
        }
    };

    /* renamed from: g  reason: collision with root package name */
    private static final DateTimeFormatter f3068g = new DateTimeFormatterBuilder().f("--").k(ChronoField.MONTH_OF_YEAR, 2).e('-').k(ChronoField.DAY_OF_MONTH, 2).s();

    /* renamed from: d  reason: collision with root package name */
    private final int f3069d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3070e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.MonthDay$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3071a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3071a = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3071a[ChronoField.MONTH_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MonthDay(int i2, int i3) {
        this.f3069d = i2;
        this.f3070e = i3;
    }

    public static MonthDay n(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof MonthDay) {
            return (MonthDay) temporalAccessor;
        }
        try {
            if (!IsoChronology.f3167h.equals(Chronology.g(temporalAccessor))) {
                temporalAccessor = LocalDate.D(temporalAccessor);
            }
            return p(temporalAccessor.f(ChronoField.MONTH_OF_YEAR), temporalAccessor.f(ChronoField.DAY_OF_MONTH));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain MonthDay from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static MonthDay p(int i2, int i3) {
        return q(Month.q(i2), i3);
    }

    public static MonthDay q(Month month, int i2) {
        Jdk8Methods.i(month, "month");
        ChronoField.DAY_OF_MONTH.h(i2);
        if (i2 <= month.o()) {
            return new MonthDay(month.getValue(), i2);
        }
        throw new DateTimeException("Illegal value for DayOfMonth field, value " + i2 + " is not valid for month " + month.name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MonthDay r(DataInput dataInput) {
        return p(dataInput.readByte(), dataInput.readByte());
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 64, this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            return temporalField.f();
        }
        if (temporalField == ChronoField.DAY_OF_MONTH) {
            return ValueRange.j(1L, o().p(), o().o());
        }
        return super.a(temporalField);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) IsoChronology.f3167h;
        }
        return (R) super.b(temporalQuery);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.MONTH_OF_YEAR || temporalField == ChronoField.DAY_OF_MONTH) {
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
        if (!(obj instanceof MonthDay)) {
            return false;
        }
        MonthDay monthDay = (MonthDay) obj;
        if (this.f3069d == monthDay.f3069d && this.f3070e == monthDay.f3070e) {
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
            int i3 = AnonymousClass2.f3071a[((ChronoField) temporalField).ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    i2 = this.f3069d;
                } else {
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                }
            } else {
                i2 = this.f3070e;
            }
            return i2;
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return (this.f3069d << 6) + this.f3070e;
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        if (Chronology.g(temporal).equals(IsoChronology.f3167h)) {
            Temporal e2 = temporal.e(ChronoField.MONTH_OF_YEAR, this.f3069d);
            ChronoField chronoField = ChronoField.DAY_OF_MONTH;
            return e2.e(chronoField, Math.min(e2.a(chronoField).c(), this.f3070e));
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    @Override // java.lang.Comparable
    /* renamed from: m */
    public int compareTo(MonthDay monthDay) {
        int i2 = this.f3069d - monthDay.f3069d;
        if (i2 == 0) {
            return this.f3070e - monthDay.f3070e;
        }
        return i2;
    }

    public Month o() {
        return Month.q(this.f3069d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(DataOutput dataOutput) {
        dataOutput.writeByte(this.f3069d);
        dataOutput.writeByte(this.f3070e);
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        if (this.f3069d < 10) {
            str = "0";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(this.f3069d);
        if (this.f3070e < 10) {
            str2 = "-0";
        } else {
            str2 = "-";
        }
        sb.append(str2);
        sb.append(this.f3070e);
        return sb.toString();
    }
}
