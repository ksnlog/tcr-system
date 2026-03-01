package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.format.DateTimeFormatter;
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
public final class Instant extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<Instant>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final Instant f3017f = new Instant(0, 0);

    /* renamed from: g  reason: collision with root package name */
    public static final Instant f3018g = v(-31557014167219200L, 0);

    /* renamed from: h  reason: collision with root package name */
    public static final Instant f3019h = v(31556889864403199L, 999999999);

    /* renamed from: i  reason: collision with root package name */
    public static final TemporalQuery<Instant> f3020i = new TemporalQuery<Instant>() { // from class: org.threeten.bp.Instant.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public Instant a(TemporalAccessor temporalAccessor) {
            return Instant.o(temporalAccessor);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    private final long f3021d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3022e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.Instant$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3023a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3024b;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3024b = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3024b[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3024b[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3024b[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3024b[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3024b[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3024b[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3024b[ChronoUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            f3023a = iArr2;
            try {
                iArr2[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3023a[ChronoField.MICRO_OF_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3023a[ChronoField.MILLI_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3023a[ChronoField.INSTANT_SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private Instant(long j2, int i2) {
        this.f3021d = j2;
        this.f3022e = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Instant B(DataInput dataInput) {
        return v(dataInput.readLong(), dataInput.readInt());
    }

    private long C(Instant instant) {
        long o2 = Jdk8Methods.o(instant.f3021d, this.f3021d);
        long j2 = instant.f3022e - this.f3022e;
        if (o2 > 0 && j2 < 0) {
            return o2 - 1;
        }
        if (o2 < 0 && j2 > 0) {
            return o2 + 1;
        }
        return o2;
    }

    private static Instant n(long j2, int i2) {
        if ((i2 | j2) == 0) {
            return f3017f;
        }
        if (j2 >= -31557014167219200L && j2 <= 31556889864403199L) {
            return new Instant(j2, i2);
        }
        throw new DateTimeException("Instant exceeds minimum or maximum instant");
    }

    public static Instant o(TemporalAccessor temporalAccessor) {
        try {
            return v(temporalAccessor.h(ChronoField.INSTANT_SECONDS), temporalAccessor.f(ChronoField.NANO_OF_SECOND));
        } catch (DateTimeException e2) {
            throw new DateTimeException("Unable to obtain Instant from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName(), e2);
        }
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private long s(Instant instant) {
        return Jdk8Methods.k(Jdk8Methods.l(Jdk8Methods.o(instant.f3021d, this.f3021d), 1000000000), instant.f3022e - this.f3022e);
    }

    public static Instant t(long j2) {
        return n(Jdk8Methods.e(j2, 1000L), Jdk8Methods.g(j2, 1000) * 1000000);
    }

    public static Instant u(long j2) {
        return n(j2, 0);
    }

    public static Instant v(long j2, long j3) {
        return n(Jdk8Methods.k(j2, Jdk8Methods.e(j3, 1000000000L)), Jdk8Methods.g(j3, 1000000000));
    }

    private Instant w(long j2, long j3) {
        if ((j2 | j3) == 0) {
            return this;
        }
        return v(Jdk8Methods.k(Jdk8Methods.k(this.f3021d, j2), j3 / 1000000000), this.f3022e + (j3 % 1000000000));
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    public Instant A(long j2) {
        return w(j2, 0L);
    }

    public long D() {
        long j2 = this.f3021d;
        if (j2 >= 0) {
            return Jdk8Methods.k(Jdk8Methods.m(j2, 1000L), this.f3022e / 1000000);
        }
        return Jdk8Methods.o(Jdk8Methods.m(j2 + 1, 1000L), 1000 - (this.f3022e / 1000000));
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: E */
    public Instant c(TemporalAdjuster temporalAdjuster) {
        return (Instant) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: F */
    public Instant e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            int i2 = AnonymousClass2.f3023a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            if (j2 != this.f3021d) {
                                return n(j2, this.f3022e);
                            }
                            return this;
                        }
                        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                    }
                    int i3 = ((int) j2) * 1000000;
                    if (i3 != this.f3022e) {
                        return n(this.f3021d, i3);
                    }
                    return this;
                }
                int i4 = ((int) j2) * 1000;
                if (i4 != this.f3022e) {
                    return n(this.f3021d, i4);
                }
                return this;
            } else if (j2 != this.f3022e) {
                return n(this.f3021d, (int) j2);
            } else {
                return this;
            }
        }
        return (Instant) temporalField.b(this, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G(DataOutput dataOutput) {
        dataOutput.writeLong(this.f3021d);
        dataOutput.writeInt(this.f3022e);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        return super.a(temporalField);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.d()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.NANO_OF_SECOND || temporalField == ChronoField.MICRO_OF_SECOND || temporalField == ChronoField.MILLI_OF_SECOND) {
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
        if (!(obj instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) obj;
        if (this.f3021d == instant.f3021d && this.f3022e == instant.f3022e) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3023a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return this.f3022e / 1000000;
                    }
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                }
                return this.f3022e / 1000;
            }
            return this.f3022e;
        }
        return a(temporalField).a(temporalField.c(this), temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        int i2;
        if (temporalField instanceof ChronoField) {
            int i3 = AnonymousClass2.f3023a[((ChronoField) temporalField).ordinal()];
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        if (i3 == 4) {
                            return this.f3021d;
                        }
                        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                    }
                    i2 = this.f3022e / 1000000;
                } else {
                    i2 = this.f3022e / 1000;
                }
            } else {
                i2 = this.f3022e;
            }
            return i2;
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        long j2 = this.f3021d;
        return ((int) (j2 ^ (j2 >>> 32))) + (this.f3022e * 51);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.INSTANT_SECONDS, this.f3021d).e(ChronoField.NANO_OF_SECOND, this.f3022e);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        Instant o2 = o(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3024b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return s(o2);
                case 2:
                    return s(o2) / 1000;
                case 3:
                    return Jdk8Methods.o(o2.D(), D());
                case 4:
                    return C(o2);
                case 5:
                    return C(o2) / 60;
                case R$styleable.f1342r /* 6 */:
                    return C(o2) / 3600;
                case R$styleable.f1343s /* 7 */:
                    return C(o2) / 43200;
                case R$styleable.f1328d /* 8 */:
                    return C(o2) / 86400;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.a(this, o2);
    }

    @Override // java.lang.Comparable
    /* renamed from: m */
    public int compareTo(Instant instant) {
        int b2 = Jdk8Methods.b(this.f3021d, instant.f3021d);
        if (b2 != 0) {
            return b2;
        }
        return this.f3022e - instant.f3022e;
    }

    public long p() {
        return this.f3021d;
    }

    public int q() {
        return this.f3022e;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: r */
    public Instant g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    public String toString() {
        return DateTimeFormatter.f3226t.b(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: x */
    public Instant i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3024b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return z(j2);
                case 2:
                    return w(j2 / 1000000, (j2 % 1000000) * 1000);
                case 3:
                    return y(j2);
                case 4:
                    return A(j2);
                case 5:
                    return A(Jdk8Methods.l(j2, 60));
                case R$styleable.f1342r /* 6 */:
                    return A(Jdk8Methods.l(j2, 3600));
                case R$styleable.f1343s /* 7 */:
                    return A(Jdk8Methods.l(j2, 43200));
                case R$styleable.f1328d /* 8 */:
                    return A(Jdk8Methods.l(j2, 86400));
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (Instant) temporalUnit.b(this, j2);
    }

    public Instant y(long j2) {
        return w(j2 / 1000, (j2 % 1000) * 1000000);
    }

    public Instant z(long j2) {
        return w(0L, j2);
    }
}
