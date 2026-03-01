package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
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
public final class Year extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<Year>, Serializable {

    /* renamed from: e  reason: collision with root package name */
    public static final TemporalQuery<Year> f3092e = new TemporalQuery<Year>() { // from class: org.threeten.bp.Year.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public Year a(TemporalAccessor temporalAccessor) {
            return Year.n(temporalAccessor);
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private static final DateTimeFormatter f3093f = new DateTimeFormatterBuilder().l(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).s();

    /* renamed from: d  reason: collision with root package name */
    private final int f3094d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.Year$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3095a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3096b;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3096b = iArr;
            try {
                iArr[ChronoUnit.YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3096b[ChronoUnit.DECADES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3096b[ChronoUnit.CENTURIES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3096b[ChronoUnit.MILLENNIA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3096b[ChronoUnit.ERAS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            f3095a = iArr2;
            try {
                iArr2[ChronoField.YEAR_OF_ERA.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3095a[ChronoField.YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3095a[ChronoField.ERA.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private Year(int i2) {
        this.f3094d = i2;
    }

    public static Year n(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Year) {
            return (Year) temporalAccessor;
        }
        try {
            if (!IsoChronology.f3167h.equals(Chronology.g(temporalAccessor))) {
                temporalAccessor = LocalDate.D(temporalAccessor);
            }
            return q(temporalAccessor.f(ChronoField.YEAR));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain Year from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static boolean o(long j2) {
        return (3 & j2) == 0 && (j2 % 100 != 0 || j2 % 400 == 0);
    }

    public static Year q(int i2) {
        ChronoField.YEAR.h(i2);
        return new Year(i2);
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Year t(DataInput dataInput) {
        return q(dataInput.readInt());
    }

    private Object writeReplace() {
        return new Ser((byte) 67, this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        long j2;
        if (temporalField == ChronoField.YEAR_OF_ERA) {
            if (this.f3094d <= 0) {
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
            return (R) ChronoUnit.YEARS;
        }
        if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.d()) {
            return (R) super.b(temporalQuery);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.YEAR || temporalField == ChronoField.YEAR_OF_ERA || temporalField == ChronoField.ERA) {
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
        if ((obj instanceof Year) && this.f3094d == ((Year) obj).f3094d) {
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
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3095a[((ChronoField) temporalField).ordinal()];
            int i3 = 1;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (this.f3094d < 1) {
                            i3 = 0;
                        }
                        return i3;
                    }
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                }
                return this.f3094d;
            }
            int i4 = this.f3094d;
            if (i4 < 1) {
                i4 = 1 - i4;
            }
            return i4;
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return this.f3094d;
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        if (Chronology.g(temporal).equals(IsoChronology.f3167h)) {
            return temporal.e(ChronoField.YEAR, this.f3094d);
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        Year n2 = n(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long j2 = n2.f3094d - this.f3094d;
            int i2 = AnonymousClass2.f3096b[((ChronoUnit) temporalUnit).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5) {
                                ChronoField chronoField = ChronoField.ERA;
                                return n2.h(chronoField) - h(chronoField);
                            }
                            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
                        }
                        return j2 / 1000;
                    }
                    return j2 / 100;
                }
                return j2 / 10;
            }
            return j2;
        }
        return temporalUnit.a(this, n2);
    }

    @Override // java.lang.Comparable
    /* renamed from: m */
    public int compareTo(Year year) {
        return this.f3094d - year.f3094d;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: p */
    public Year g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: r */
    public Year i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            int i2 = AnonymousClass2.f3096b[((ChronoUnit) temporalUnit).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5) {
                                ChronoField chronoField = ChronoField.ERA;
                                return e(chronoField, Jdk8Methods.k(h(chronoField), j2));
                            }
                            throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
                        }
                        return s(Jdk8Methods.l(j2, 1000));
                    }
                    return s(Jdk8Methods.l(j2, 100));
                }
                return s(Jdk8Methods.l(j2, 10));
            }
            return s(j2);
        }
        return (Year) temporalUnit.b(this, j2);
    }

    public Year s(long j2) {
        return j2 == 0 ? this : q(ChronoField.YEAR.g(this.f3094d + j2));
    }

    public String toString() {
        return Integer.toString(this.f3094d);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: u */
    public Year c(TemporalAdjuster temporalAdjuster) {
        return (Year) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: v */
    public Year e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            int i2 = AnonymousClass2.f3095a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (h(ChronoField.ERA) == j2) {
                            return this;
                        }
                        return q(1 - this.f3094d);
                    }
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                }
                return q((int) j2);
            }
            if (this.f3094d < 1) {
                j2 = 1 - j2;
            }
            return q((int) j2);
        }
        return (Year) temporalField.b(this, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(DataOutput dataOutput) {
        dataOutput.writeInt(this.f3094d);
    }
}
