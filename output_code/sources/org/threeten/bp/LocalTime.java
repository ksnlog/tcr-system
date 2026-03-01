package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
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
public final class LocalTime extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<LocalTime>, Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final LocalTime f3039h;

    /* renamed from: i  reason: collision with root package name */
    public static final LocalTime f3040i;

    /* renamed from: j  reason: collision with root package name */
    public static final LocalTime f3041j;

    /* renamed from: k  reason: collision with root package name */
    public static final LocalTime f3042k;

    /* renamed from: l  reason: collision with root package name */
    public static final TemporalQuery<LocalTime> f3043l = new TemporalQuery<LocalTime>() { // from class: org.threeten.bp.LocalTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public LocalTime a(TemporalAccessor temporalAccessor) {
            return LocalTime.p(temporalAccessor);
        }
    };

    /* renamed from: m  reason: collision with root package name */
    private static final LocalTime[] f3044m = new LocalTime[24];

    /* renamed from: d  reason: collision with root package name */
    private final byte f3045d;

    /* renamed from: e  reason: collision with root package name */
    private final byte f3046e;

    /* renamed from: f  reason: collision with root package name */
    private final byte f3047f;

    /* renamed from: g  reason: collision with root package name */
    private final int f3048g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.LocalTime$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3049a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3050b;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3050b = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3050b[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3050b[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3050b[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3050b[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3050b[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3050b[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[ChronoField.values().length];
            f3049a = iArr2;
            try {
                iArr2[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3049a[ChronoField.NANO_OF_DAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3049a[ChronoField.MICRO_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3049a[ChronoField.MICRO_OF_DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3049a[ChronoField.MILLI_OF_SECOND.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3049a[ChronoField.MILLI_OF_DAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3049a[ChronoField.SECOND_OF_MINUTE.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3049a[ChronoField.SECOND_OF_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f3049a[ChronoField.MINUTE_OF_HOUR.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3049a[ChronoField.MINUTE_OF_DAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f3049a[ChronoField.HOUR_OF_AMPM.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f3049a[ChronoField.CLOCK_HOUR_OF_AMPM.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f3049a[ChronoField.HOUR_OF_DAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f3049a[ChronoField.CLOCK_HOUR_OF_DAY.ordinal()] = 14;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f3049a[ChronoField.AMPM_OF_DAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    static {
        int i2 = 0;
        while (true) {
            LocalTime[] localTimeArr = f3044m;
            if (i2 < localTimeArr.length) {
                localTimeArr[i2] = new LocalTime(i2, 0, 0, 0);
                i2++;
            } else {
                LocalTime localTime = localTimeArr[0];
                f3041j = localTime;
                f3042k = localTimeArr[12];
                f3039h = localTime;
                f3040i = new LocalTime(23, 59, 59, 999999999);
                return;
            }
        }
    }

    private LocalTime(int i2, int i3, int i4, int i5) {
        this.f3045d = (byte) i2;
        this.f3046e = (byte) i3;
        this.f3047f = (byte) i4;
        this.f3048g = i5;
    }

    public static LocalTime A(int i2, int i3, int i4, int i5) {
        ChronoField.HOUR_OF_DAY.h(i2);
        ChronoField.MINUTE_OF_HOUR.h(i3);
        ChronoField.SECOND_OF_MINUTE.h(i4);
        ChronoField.NANO_OF_SECOND.h(i5);
        return o(i2, i3, i4, i5);
    }

    public static LocalTime B(long j2) {
        ChronoField.NANO_OF_DAY.h(j2);
        int i2 = (int) (j2 / 3600000000000L);
        long j3 = j2 - (i2 * 3600000000000L);
        int i3 = (int) (j3 / 60000000000L);
        long j4 = j3 - (i3 * 60000000000L);
        int i4 = (int) (j4 / 1000000000);
        return o(i2, i3, i4, (int) (j4 - (i4 * 1000000000)));
    }

    public static LocalTime C(long j2) {
        ChronoField.SECOND_OF_DAY.h(j2);
        int i2 = (int) (j2 / 3600);
        long j3 = j2 - (i2 * 3600);
        int i3 = (int) (j3 / 60);
        return o(i2, i3, (int) (j3 - (i3 * 60)), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalTime D(long j2, int i2) {
        ChronoField.SECOND_OF_DAY.h(j2);
        ChronoField.NANO_OF_SECOND.h(i2);
        int i3 = (int) (j2 / 3600);
        long j3 = j2 - (i3 * 3600);
        int i4 = (int) (j3 / 60);
        return o(i3, i4, (int) (j3 - (i4 * 60)), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2 */
    public static LocalTime J(DataInput dataInput) {
        byte b2;
        int i2;
        byte b3;
        int readByte = dataInput.readByte();
        byte b4 = 0;
        if (readByte < 0) {
            readByte ^= -1;
            b3 = 0;
        } else {
            byte readByte2 = dataInput.readByte();
            if (readByte2 < 0) {
                int i3 = readByte2 ^ (-1);
                i2 = 0;
                b4 = i3 == 1 ? 1 : 0;
                b2 = 0;
            } else {
                byte readByte3 = dataInput.readByte();
                if (readByte3 < 0) {
                    b4 = readByte2;
                    b3 = readByte3 ^ (-1);
                } else {
                    int readInt = dataInput.readInt();
                    b2 = readByte3;
                    i2 = readInt;
                    b4 = readByte2;
                }
            }
            int i4 = b2 == 1 ? 1 : 0;
            int i5 = b2 == 1 ? 1 : 0;
            return A(readByte, b4, i4, i2);
        }
        i2 = 0;
        b2 = b3;
        int i42 = b2 == 1 ? 1 : 0;
        int i52 = b2 == 1 ? 1 : 0;
        return A(readByte, b4, i42, i2);
    }

    private static LocalTime o(int i2, int i3, int i4, int i5) {
        if ((i3 | i4 | i5) == 0) {
            return f3044m[i2];
        }
        return new LocalTime(i2, i3, i4, i5);
    }

    public static LocalTime p(TemporalAccessor temporalAccessor) {
        LocalTime localTime = (LocalTime) temporalAccessor.b(TemporalQueries.c());
        if (localTime != null) {
            return localTime;
        }
        throw new DateTimeException("Unable to obtain LocalTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    private int q(TemporalField temporalField) {
        switch (AnonymousClass2.f3049a[((ChronoField) temporalField).ordinal()]) {
            case 1:
                return this.f3048g;
            case 2:
                throw new DateTimeException("Field too large for an int: " + temporalField);
            case 3:
                return this.f3048g / 1000;
            case 4:
                throw new DateTimeException("Field too large for an int: " + temporalField);
            case 5:
                return this.f3048g / 1000000;
            case R$styleable.f1342r /* 6 */:
                return (int) (K() / 1000000);
            case R$styleable.f1343s /* 7 */:
                return this.f3047f;
            case R$styleable.f1328d /* 8 */:
                return L();
            case R$styleable.f1329e /* 9 */:
                return this.f3046e;
            case R$styleable.f1330f /* 10 */:
                return (this.f3045d * 60) + this.f3046e;
            case R$styleable.f1331g /* 11 */:
                return this.f3045d % 12;
            case R$styleable.f1332h /* 12 */:
                int i2 = this.f3045d % 12;
                if (i2 % 12 == 0) {
                    return 12;
                }
                return i2;
            case R$styleable.f1333i /* 13 */:
                return this.f3045d;
            case R$styleable.f1334j /* 14 */:
                byte b2 = this.f3045d;
                if (b2 == 0) {
                    return 24;
                }
                return b2;
            case 15:
                return this.f3045d / 12;
            default:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    public static LocalTime y(int i2, int i3) {
        ChronoField.HOUR_OF_DAY.h(i2);
        if (i3 == 0) {
            return f3044m[i2];
        }
        ChronoField.MINUTE_OF_HOUR.h(i3);
        return new LocalTime(i2, i3, 0, 0);
    }

    public static LocalTime z(int i2, int i3, int i4) {
        ChronoField.HOUR_OF_DAY.h(i2);
        if ((i3 | i4) == 0) {
            return f3044m[i2];
        }
        ChronoField.MINUTE_OF_HOUR.h(i3);
        ChronoField.SECOND_OF_MINUTE.h(i4);
        return new LocalTime(i2, i3, i4, 0);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: E */
    public LocalTime i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3050b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return H(j2);
                case 2:
                    return H((j2 % 86400000000L) * 1000);
                case 3:
                    return H((j2 % 86400000) * 1000000);
                case 4:
                    return I(j2);
                case 5:
                    return G(j2);
                case R$styleable.f1342r /* 6 */:
                    return F(j2);
                case R$styleable.f1343s /* 7 */:
                    return F((j2 % 2) * 12);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return (LocalTime) temporalUnit.b(this, j2);
    }

    public LocalTime F(long j2) {
        if (j2 == 0) {
            return this;
        }
        return o(((((int) (j2 % 24)) + this.f3045d) + 24) % 24, this.f3046e, this.f3047f, this.f3048g);
    }

    public LocalTime G(long j2) {
        if (j2 == 0) {
            return this;
        }
        int i2 = (this.f3045d * 60) + this.f3046e;
        int i3 = ((((int) (j2 % 1440)) + i2) + 1440) % 1440;
        if (i2 == i3) {
            return this;
        }
        return o(i3 / 60, i3 % 60, this.f3047f, this.f3048g);
    }

    public LocalTime H(long j2) {
        if (j2 == 0) {
            return this;
        }
        long K = K();
        long j3 = (((j2 % 86400000000000L) + K) + 86400000000000L) % 86400000000000L;
        if (K == j3) {
            return this;
        }
        return o((int) (j3 / 3600000000000L), (int) ((j3 / 60000000000L) % 60), (int) ((j3 / 1000000000) % 60), (int) (j3 % 1000000000));
    }

    public LocalTime I(long j2) {
        if (j2 == 0) {
            return this;
        }
        int i2 = (this.f3045d * 3600) + (this.f3046e * 60) + this.f3047f;
        int i3 = ((((int) (j2 % 86400)) + i2) + 86400) % 86400;
        if (i2 == i3) {
            return this;
        }
        return o(i3 / 3600, (i3 / 60) % 60, i3 % 60, this.f3048g);
    }

    public long K() {
        return (this.f3045d * 3600000000000L) + (this.f3046e * 60000000000L) + (this.f3047f * 1000000000) + this.f3048g;
    }

    public int L() {
        return (this.f3045d * 3600) + (this.f3046e * 60) + this.f3047f;
    }

    public LocalTime M(TemporalUnit temporalUnit) {
        if (temporalUnit == ChronoUnit.NANOS) {
            return this;
        }
        Duration duration = temporalUnit.getDuration();
        if (duration.g() <= 86400) {
            long o2 = duration.o();
            if (86400000000000L % o2 == 0) {
                return B((K() / o2) * o2);
            }
            throw new DateTimeException("Unit must divide into a standard day without remainder");
        }
        throw new DateTimeException("Unit is too large to be used for truncation");
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: N */
    public LocalTime c(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalTime) {
            return (LocalTime) temporalAdjuster;
        }
        return (LocalTime) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: O */
    public LocalTime e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            switch (AnonymousClass2.f3049a[chronoField.ordinal()]) {
                case 1:
                    return R((int) j2);
                case 2:
                    return B(j2);
                case 3:
                    return R(((int) j2) * 1000);
                case 4:
                    return B(j2 * 1000);
                case 5:
                    return R(((int) j2) * 1000000);
                case R$styleable.f1342r /* 6 */:
                    return B(j2 * 1000000);
                case R$styleable.f1343s /* 7 */:
                    return S((int) j2);
                case R$styleable.f1328d /* 8 */:
                    return I(j2 - L());
                case R$styleable.f1329e /* 9 */:
                    return Q((int) j2);
                case R$styleable.f1330f /* 10 */:
                    return G(j2 - ((this.f3045d * 60) + this.f3046e));
                case R$styleable.f1331g /* 11 */:
                    return F(j2 - (this.f3045d % 12));
                case R$styleable.f1332h /* 12 */:
                    if (j2 == 12) {
                        j2 = 0;
                    }
                    return F(j2 - (this.f3045d % 12));
                case R$styleable.f1333i /* 13 */:
                    return P((int) j2);
                case R$styleable.f1334j /* 14 */:
                    if (j2 == 24) {
                        j2 = 0;
                    }
                    return P((int) j2);
                case 15:
                    return F((j2 - (this.f3045d / 12)) * 12);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
        }
        return (LocalTime) temporalField.b(this, j2);
    }

    public LocalTime P(int i2) {
        if (this.f3045d == i2) {
            return this;
        }
        ChronoField.HOUR_OF_DAY.h(i2);
        return o(i2, this.f3046e, this.f3047f, this.f3048g);
    }

    public LocalTime Q(int i2) {
        if (this.f3046e == i2) {
            return this;
        }
        ChronoField.MINUTE_OF_HOUR.h(i2);
        return o(this.f3045d, i2, this.f3047f, this.f3048g);
    }

    public LocalTime R(int i2) {
        if (this.f3048g == i2) {
            return this;
        }
        ChronoField.NANO_OF_SECOND.h(i2);
        return o(this.f3045d, this.f3046e, this.f3047f, i2);
    }

    public LocalTime S(int i2) {
        if (this.f3047f == i2) {
            return this;
        }
        ChronoField.SECOND_OF_MINUTE.h(i2);
        return o(this.f3045d, this.f3046e, i2, this.f3048g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T(DataOutput dataOutput) {
        if (this.f3048g == 0) {
            if (this.f3047f == 0) {
                if (this.f3046e == 0) {
                    dataOutput.writeByte(this.f3045d ^ (-1));
                    return;
                }
                dataOutput.writeByte(this.f3045d);
                dataOutput.writeByte(this.f3046e ^ (-1));
                return;
            }
            dataOutput.writeByte(this.f3045d);
            dataOutput.writeByte(this.f3046e);
            dataOutput.writeByte(this.f3047f ^ (-1));
            return;
        }
        dataOutput.writeByte(this.f3045d);
        dataOutput.writeByte(this.f3046e);
        dataOutput.writeByte(this.f3047f);
        dataOutput.writeInt(this.f3048g);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        return super.a(temporalField);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.c()) {
            return this;
        }
        if (temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.f() && temporalQuery != TemporalQueries.d() && temporalQuery != TemporalQueries.b()) {
            return temporalQuery.a(this);
        }
        return null;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased();
        }
        if (temporalField != null && temporalField.a(this)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalTime)) {
            return false;
        }
        LocalTime localTime = (LocalTime) obj;
        if (this.f3045d == localTime.f3045d && this.f3046e == localTime.f3046e && this.f3047f == localTime.f3047f && this.f3048g == localTime.f3048g) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return q(temporalField);
        }
        return super.f(temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.NANO_OF_DAY) {
                return K();
            }
            if (temporalField == ChronoField.MICRO_OF_DAY) {
                return K() / 1000;
            }
            return q(temporalField);
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        long K = K();
        return (int) (K ^ (K >>> 32));
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.NANO_OF_DAY, K());
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        LocalTime p2 = p(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long K = p2.K() - K();
            switch (AnonymousClass2.f3050b[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return K;
                case 2:
                    return K / 1000;
                case 3:
                    return K / 1000000;
                case 4:
                    return K / 1000000000;
                case 5:
                    return K / 60000000000L;
                case R$styleable.f1342r /* 6 */:
                    return K / 3600000000000L;
                case R$styleable.f1343s /* 7 */:
                    return K / 43200000000000L;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.a(this, p2);
    }

    public OffsetTime m(ZoneOffset zoneOffset) {
        return OffsetTime.q(this, zoneOffset);
    }

    @Override // java.lang.Comparable
    /* renamed from: n */
    public int compareTo(LocalTime localTime) {
        int a2 = Jdk8Methods.a(this.f3045d, localTime.f3045d);
        if (a2 == 0) {
            int a3 = Jdk8Methods.a(this.f3046e, localTime.f3046e);
            if (a3 == 0) {
                int a4 = Jdk8Methods.a(this.f3047f, localTime.f3047f);
                if (a4 == 0) {
                    return Jdk8Methods.a(this.f3048g, localTime.f3048g);
                }
                return a4;
            }
            return a3;
        }
        return a2;
    }

    public int r() {
        return this.f3045d;
    }

    public int s() {
        return this.f3046e;
    }

    public int t() {
        return this.f3048g;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder(18);
        byte b2 = this.f3045d;
        byte b3 = this.f3046e;
        byte b4 = this.f3047f;
        int i2 = this.f3048g;
        if (b2 < 10) {
            str = "0";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append((int) b2);
        String str3 = ":0";
        if (b3 >= 10) {
            str2 = ":";
        } else {
            str2 = str3;
        }
        sb.append(str2);
        sb.append((int) b3);
        if (b4 > 0 || i2 > 0) {
            if (b4 >= 10) {
                str3 = ":";
            }
            sb.append(str3);
            sb.append((int) b4);
            if (i2 > 0) {
                sb.append('.');
                if (i2 % 1000000 == 0) {
                    sb.append(Integer.toString((i2 / 1000000) + 1000).substring(1));
                } else if (i2 % 1000 == 0) {
                    sb.append(Integer.toString((i2 / 1000) + 1000000).substring(1));
                } else {
                    sb.append(Integer.toString(i2 + 1000000000).substring(1));
                }
            }
        }
        return sb.toString();
    }

    public int u() {
        return this.f3047f;
    }

    public boolean v(LocalTime localTime) {
        return compareTo(localTime) > 0;
    }

    public boolean w(LocalTime localTime) {
        return compareTo(localTime) < 0;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: x */
    public LocalTime g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }
}
