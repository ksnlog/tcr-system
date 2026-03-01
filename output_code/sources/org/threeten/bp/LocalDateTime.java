package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.chrono.ChronoLocalDateTime;
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
public final class LocalDateTime extends ChronoLocalDateTime<LocalDate> implements Serializable {

    /* renamed from: g  reason: collision with root package name */
    public static final LocalDateTime f3033g = P(LocalDate.f3025h, LocalTime.f3039h);

    /* renamed from: h  reason: collision with root package name */
    public static final LocalDateTime f3034h = P(LocalDate.f3026i, LocalTime.f3040i);

    /* renamed from: i  reason: collision with root package name */
    public static final TemporalQuery<LocalDateTime> f3035i = new TemporalQuery<LocalDateTime>() { // from class: org.threeten.bp.LocalDateTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public LocalDateTime a(TemporalAccessor temporalAccessor) {
            return LocalDateTime.D(temporalAccessor);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private final LocalDate f3036e;

    /* renamed from: f  reason: collision with root package name */
    private final LocalTime f3037f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.LocalDateTime$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3038a;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3038a = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3038a[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3038a[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3038a[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3038a[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3038a[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3038a[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private LocalDateTime(LocalDate localDate, LocalTime localTime) {
        this.f3036e = localDate;
        this.f3037f = localTime;
    }

    private int C(LocalDateTime localDateTime) {
        int A = this.f3036e.A(localDateTime.w());
        if (A == 0) {
            return this.f3037f.compareTo(localDateTime.x());
        }
        return A;
    }

    public static LocalDateTime D(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        }
        if (temporalAccessor instanceof ZonedDateTime) {
            return ((ZonedDateTime) temporalAccessor).w();
        }
        try {
            return new LocalDateTime(LocalDate.D(temporalAccessor), LocalTime.p(temporalAccessor));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static LocalDateTime M() {
        return N(Clock.c());
    }

    public static LocalDateTime N(Clock clock) {
        Jdk8Methods.i(clock, "clock");
        Instant b2 = clock.b();
        return Q(b2.p(), b2.q(), clock.a().n().a(b2));
    }

    public static LocalDateTime O(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new LocalDateTime(LocalDate.U(i2, i3, i4), LocalTime.A(i5, i6, i7, i8));
    }

    public static LocalDateTime P(LocalDate localDate, LocalTime localTime) {
        Jdk8Methods.i(localDate, "date");
        Jdk8Methods.i(localTime, "time");
        return new LocalDateTime(localDate, localTime);
    }

    public static LocalDateTime Q(long j2, int i2, ZoneOffset zoneOffset) {
        Jdk8Methods.i(zoneOffset, "offset");
        long x2 = j2 + zoneOffset.x();
        return new LocalDateTime(LocalDate.W(Jdk8Methods.e(x2, 86400L)), LocalTime.D(Jdk8Methods.g(x2, 86400), i2));
    }

    private LocalDateTime X(LocalDate localDate, long j2, long j3, long j4, long j5, int i2) {
        LocalTime B;
        if ((j2 | j3 | j4 | j5) == 0) {
            return b0(localDate, this.f3037f);
        }
        long j6 = i2;
        long K = this.f3037f.K();
        long j7 = (((j5 % 86400000000000L) + ((j4 % 86400) * 1000000000) + ((j3 % 1440) * 60000000000L) + ((j2 % 24) * 3600000000000L)) * j6) + K;
        long e2 = (((j5 / 86400000000000L) + (j4 / 86400) + (j3 / 1440) + (j2 / 24)) * j6) + Jdk8Methods.e(j7, 86400000000000L);
        long h2 = Jdk8Methods.h(j7, 86400000000000L);
        if (h2 == K) {
            B = this.f3037f;
        } else {
            B = LocalTime.B(h2);
        }
        return b0(localDate.a0(e2), B);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalDateTime Y(DataInput dataInput) {
        return P(LocalDate.e0(dataInput), LocalTime.J(dataInput));
    }

    private LocalDateTime b0(LocalDate localDate, LocalTime localTime) {
        if (this.f3036e == localDate && this.f3037f == localTime) {
            return this;
        }
        return new LocalDateTime(localDate, localTime);
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }

    public OffsetDateTime A(ZoneOffset zoneOffset) {
        return OffsetDateTime.s(this, zoneOffset);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: B */
    public ZonedDateTime n(ZoneId zoneId) {
        return ZonedDateTime.O(this, zoneId);
    }

    public int E() {
        return this.f3036e.G();
    }

    public int F() {
        return this.f3037f.r();
    }

    public int G() {
        return this.f3037f.s();
    }

    public Month H() {
        return this.f3036e.J();
    }

    public int I() {
        return this.f3037f.t();
    }

    public int J() {
        return this.f3037f.u();
    }

    public int K() {
        return this.f3036e.M();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: L */
    public LocalDateTime s(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? t(Long.MAX_VALUE, temporalUnit).t(1L, temporalUnit) : t(-j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: R */
    public LocalDateTime t(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass2.f3038a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return V(j2);
                case 2:
                    return S(j2 / 86400000000L).V((j2 % 86400000000L) * 1000);
                case 3:
                    return S(j2 / 86400000).V((j2 % 86400000) * 1000000);
                case 4:
                    return W(j2);
                case 5:
                    return U(j2);
                case R$styleable.f1342r /* 6 */:
                    return T(j2);
                case R$styleable.f1343s /* 7 */:
                    return S(j2 / 256).T((j2 % 256) * 12);
                default:
                    return b0(this.f3036e.u(j2, temporalUnit), this.f3037f);
            }
        }
        return (LocalDateTime) temporalUnit.b(this, j2);
    }

    public LocalDateTime S(long j2) {
        return b0(this.f3036e.a0(j2), this.f3037f);
    }

    public LocalDateTime T(long j2) {
        return X(this.f3036e, j2, 0L, 0L, 0L, 1);
    }

    public LocalDateTime U(long j2) {
        return X(this.f3036e, 0L, j2, 0L, 0L, 1);
    }

    public LocalDateTime V(long j2) {
        return X(this.f3036e, 0L, 0L, 0L, j2, 1);
    }

    public LocalDateTime W(long j2) {
        return X(this.f3036e, 0L, 0L, j2, 0L, 1);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: Z */
    public LocalDate w() {
        return this.f3036e;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3037f.a(temporalField);
            }
            return this.f3036e.a(temporalField);
        }
        return temporalField.d(this);
    }

    public LocalDateTime a0(TemporalUnit temporalUnit) {
        return b0(this.f3036e, this.f3037f.M(temporalUnit));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.b()) {
            return (R) w();
        }
        return (R) super.b(temporalQuery);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: c0 */
    public LocalDateTime y(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return b0((LocalDate) temporalAdjuster, this.f3037f);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return b0(this.f3036e, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof LocalDateTime) {
            return (LocalDateTime) temporalAdjuster;
        }
        return (LocalDateTime) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isDateBased() || temporalField.isTimeBased()) {
                return true;
            }
            return false;
        } else if (temporalField != null && temporalField.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: d0 */
    public LocalDateTime z(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return b0(this.f3036e, this.f3037f.e(temporalField, j2));
            }
            return b0(this.f3036e.y(temporalField, j2), this.f3037f);
        }
        return (LocalDateTime) temporalField.b(this, j2);
    }

    public LocalDateTime e0(int i2) {
        return b0(this.f3036e.i0(i2), this.f3037f);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalDateTime)) {
            return false;
        }
        LocalDateTime localDateTime = (LocalDateTime) obj;
        if (this.f3036e.equals(localDateTime.f3036e) && this.f3037f.equals(localDateTime.f3037f)) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3037f.f(temporalField);
            }
            return this.f3036e.f(temporalField);
        }
        return super.f(temporalField);
    }

    public LocalDateTime f0(int i2) {
        return b0(this.f3036e, this.f3037f.P(i2));
    }

    public LocalDateTime g0(int i2) {
        return b0(this.f3036e, this.f3037f.Q(i2));
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3037f.h(temporalField);
            }
            return this.f3036e.h(temporalField);
        }
        return temporalField.c(this);
    }

    public LocalDateTime h0(int i2) {
        return b0(this.f3036e.k0(i2), this.f3037f);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public int hashCode() {
        return this.f3036e.hashCode() ^ this.f3037f.hashCode();
    }

    public LocalDateTime i0(int i2) {
        return b0(this.f3036e.l0(i2), this.f3037f);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return super.j(temporal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j0(DataOutput dataOutput) {
        this.f3036e.m0(dataOutput);
        this.f3037f.T(dataOutput);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        LocalDateTime D = D(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            if (chronoUnit.c()) {
                long C = this.f3036e.C(D.f3036e);
                long K = D.f3037f.K() - this.f3037f.K();
                if (C > 0 && K < 0) {
                    C--;
                    K += 86400000000000L;
                } else if (C < 0 && K > 0) {
                    C++;
                    K -= 86400000000000L;
                }
                switch (AnonymousClass2.f3038a[chronoUnit.ordinal()]) {
                    case 1:
                        return Jdk8Methods.k(Jdk8Methods.m(C, 86400000000000L), K);
                    case 2:
                        return Jdk8Methods.k(Jdk8Methods.m(C, 86400000000L), K / 1000);
                    case 3:
                        return Jdk8Methods.k(Jdk8Methods.m(C, 86400000L), K / 1000000);
                    case 4:
                        return Jdk8Methods.k(Jdk8Methods.l(C, 86400), K / 1000000000);
                    case 5:
                        return Jdk8Methods.k(Jdk8Methods.l(C, 1440), K / 60000000000L);
                    case R$styleable.f1342r /* 6 */:
                        return Jdk8Methods.k(Jdk8Methods.l(C, 24), K / 3600000000000L);
                    case R$styleable.f1343s /* 7 */:
                        return Jdk8Methods.k(Jdk8Methods.l(C, 2), K / 43200000000000L);
                    default:
                        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
                }
            }
            LocalDate localDate = D.f3036e;
            if (localDate.r(this.f3036e) && D.f3037f.w(this.f3037f)) {
                localDate = localDate.R(1L);
            } else if (localDate.s(this.f3036e) && D.f3037f.v(this.f3037f)) {
                localDate = localDate.a0(1L);
            }
            return this.f3036e.l(localDate, temporalUnit);
        }
        return temporalUnit.a(this, D);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime, java.lang.Comparable
    /* renamed from: o */
    public int compareTo(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return C((LocalDateTime) chronoLocalDateTime);
        }
        return super.compareTo(chronoLocalDateTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public boolean q(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            if (C((LocalDateTime) chronoLocalDateTime) > 0) {
                return true;
            }
            return false;
        }
        return super.q(chronoLocalDateTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public boolean r(ChronoLocalDateTime<?> chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            if (C((LocalDateTime) chronoLocalDateTime) < 0) {
                return true;
            }
            return false;
        }
        return super.r(chronoLocalDateTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public String toString() {
        return this.f3036e.toString() + 'T' + this.f3037f.toString();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public LocalTime x() {
        return this.f3037f;
    }
}
