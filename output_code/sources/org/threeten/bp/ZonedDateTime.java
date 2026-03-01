package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.List;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
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
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneRules;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZonedDateTime extends ChronoZonedDateTime<LocalDate> implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public static final TemporalQuery<ZonedDateTime> f3116h = new TemporalQuery<ZonedDateTime>() { // from class: org.threeten.bp.ZonedDateTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZonedDateTime a(TemporalAccessor temporalAccessor) {
            return ZonedDateTime.D(temporalAccessor);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private final LocalDateTime f3117e;

    /* renamed from: f  reason: collision with root package name */
    private final ZoneOffset f3118f;

    /* renamed from: g  reason: collision with root package name */
    private final ZoneId f3119g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.ZonedDateTime$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3120a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3120a = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3120a[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ZonedDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        this.f3117e = localDateTime;
        this.f3118f = zoneOffset;
        this.f3119g = zoneId;
    }

    private static ZonedDateTime C(long j2, int i2, ZoneId zoneId) {
        ZoneOffset a2 = zoneId.n().a(Instant.v(j2, i2));
        return new ZonedDateTime(LocalDateTime.Q(j2, i2, a2), a2, zoneId);
    }

    public static ZonedDateTime D(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ZonedDateTime) {
            return (ZonedDateTime) temporalAccessor;
        }
        try {
            ZoneId k2 = ZoneId.k(temporalAccessor);
            ChronoField chronoField = ChronoField.INSTANT_SECONDS;
            if (temporalAccessor.d(chronoField)) {
                try {
                    return C(temporalAccessor.h(chronoField), temporalAccessor.f(ChronoField.NANO_OF_SECOND), k2);
                } catch (DateTimeException unused) {
                }
            }
            return O(LocalDateTime.D(temporalAccessor), k2);
        } catch (DateTimeException unused2) {
            throw new DateTimeException("Unable to obtain ZonedDateTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static ZonedDateTime M() {
        return N(Clock.c());
    }

    public static ZonedDateTime N(Clock clock) {
        Jdk8Methods.i(clock, "clock");
        return P(clock.b(), clock.a());
    }

    public static ZonedDateTime O(LocalDateTime localDateTime, ZoneId zoneId) {
        return S(localDateTime, zoneId, null);
    }

    public static ZonedDateTime P(Instant instant, ZoneId zoneId) {
        Jdk8Methods.i(instant, "instant");
        Jdk8Methods.i(zoneId, "zone");
        return C(instant.p(), instant.q(), zoneId);
    }

    public static ZonedDateTime Q(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        Jdk8Methods.i(localDateTime, "localDateTime");
        Jdk8Methods.i(zoneOffset, "offset");
        Jdk8Methods.i(zoneId, "zone");
        return C(localDateTime.u(zoneOffset), localDateTime.I(), zoneId);
    }

    private static ZonedDateTime R(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        Jdk8Methods.i(localDateTime, "localDateTime");
        Jdk8Methods.i(zoneOffset, "offset");
        Jdk8Methods.i(zoneId, "zone");
        if ((zoneId instanceof ZoneOffset) && !zoneOffset.equals(zoneId)) {
            throw new IllegalArgumentException("ZoneId must match ZoneOffset");
        }
        return new ZonedDateTime(localDateTime, zoneOffset, zoneId);
    }

    public static ZonedDateTime S(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        Jdk8Methods.i(localDateTime, "localDateTime");
        Jdk8Methods.i(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ZonedDateTime(localDateTime, (ZoneOffset) zoneId, zoneId);
        }
        ZoneRules n2 = zoneId.n();
        List<ZoneOffset> c2 = n2.c(localDateTime);
        if (c2.size() == 1) {
            zoneOffset = c2.get(0);
        } else if (c2.size() == 0) {
            ZoneOffsetTransition b2 = n2.b(localDateTime);
            localDateTime = localDateTime.W(b2.d().g());
            zoneOffset = b2.g();
        } else if (zoneOffset == null || !c2.contains(zoneOffset)) {
            zoneOffset = (ZoneOffset) Jdk8Methods.i(c2.get(0), "offset");
        }
        return new ZonedDateTime(localDateTime, zoneOffset, zoneId);
    }

    public static ZonedDateTime T(CharSequence charSequence) {
        return U(charSequence, DateTimeFormatter.f3222p);
    }

    public static ZonedDateTime U(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.i(dateTimeFormatter, "formatter");
        return (ZonedDateTime) dateTimeFormatter.h(charSequence, f3116h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZonedDateTime Y(DataInput dataInput) {
        return R(LocalDateTime.Y(dataInput), ZoneOffset.C(dataInput), (ZoneId) Ser.a(dataInput));
    }

    private ZonedDateTime Z(LocalDateTime localDateTime) {
        return Q(localDateTime, this.f3118f, this.f3119g);
    }

    private ZonedDateTime a0(LocalDateTime localDateTime) {
        return S(localDateTime, this.f3119g, this.f3118f);
    }

    private ZonedDateTime b0(ZoneOffset zoneOffset) {
        if (!zoneOffset.equals(this.f3118f) && this.f3119g.n().e(this.f3117e, zoneOffset)) {
            return new ZonedDateTime(this.f3117e, zoneOffset, this.f3119g);
        }
        return this;
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    public int E() {
        return this.f3117e.E();
    }

    public int F() {
        return this.f3117e.F();
    }

    public int G() {
        return this.f3117e.G();
    }

    public Month H() {
        return this.f3117e.H();
    }

    public int I() {
        return this.f3117e.I();
    }

    public int J() {
        return this.f3117e.K();
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: K */
    public ZonedDateTime t(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? u(Long.MAX_VALUE, temporalUnit).u(1L, temporalUnit) : u(-j2, temporalUnit);
    }

    public ZonedDateTime L(long j2) {
        return j2 == Long.MIN_VALUE ? W(Long.MAX_VALUE).W(1L) : W(-j2);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: V */
    public ZonedDateTime u(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            if (temporalUnit.isDateBased()) {
                return a0(this.f3117e.t(j2, temporalUnit));
            }
            return Z(this.f3117e.t(j2, temporalUnit));
        }
        return (ZonedDateTime) temporalUnit.b(this, j2);
    }

    public ZonedDateTime W(long j2) {
        return Z(this.f3117e.T(j2));
    }

    public ZonedDateTime X(long j2) {
        return Z(this.f3117e.U(j2));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField != ChronoField.INSTANT_SECONDS && temporalField != ChronoField.OFFSET_SECONDS) {
                return this.f3117e.a(temporalField);
            }
            return temporalField.f();
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.b()) {
            return (R) v();
        }
        return (R) super.b(temporalQuery);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: c0 */
    public LocalDate v() {
        return this.f3117e.w();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.a(this));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: d0 */
    public LocalDateTime w() {
        return this.f3117e;
    }

    public OffsetDateTime e0() {
        return OffsetDateTime.s(this.f3117e, this.f3118f);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZonedDateTime)) {
            return false;
        }
        ZonedDateTime zonedDateTime = (ZonedDateTime) obj;
        if (this.f3117e.equals(zonedDateTime.f3117e) && this.f3118f.equals(zonedDateTime.f3118f) && this.f3119g.equals(zonedDateTime.f3119g)) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3120a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return this.f3117e.f(temporalField);
                }
                return p().x();
            }
            throw new DateTimeException("Field too large for an int: " + temporalField);
        }
        return super.f(temporalField);
    }

    public ZonedDateTime f0(TemporalUnit temporalUnit) {
        return a0(this.f3117e.a0(temporalUnit));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: g0 */
    public ZonedDateTime y(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return a0(LocalDateTime.P((LocalDate) temporalAdjuster, this.f3117e.x()));
        }
        if (temporalAdjuster instanceof LocalTime) {
            return a0(LocalDateTime.P(this.f3117e.w(), (LocalTime) temporalAdjuster));
        }
        if (temporalAdjuster instanceof LocalDateTime) {
            return a0((LocalDateTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof Instant) {
            Instant instant = (Instant) temporalAdjuster;
            return C(instant.p(), instant.q(), this.f3119g);
        } else if (temporalAdjuster instanceof ZoneOffset) {
            return b0((ZoneOffset) temporalAdjuster);
        } else {
            return (ZonedDateTime) temporalAdjuster.j(this);
        }
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass2.f3120a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return this.f3117e.h(temporalField);
                }
                return p().x();
            }
            return toEpochSecond();
        }
        return temporalField.c(this);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: h0 */
    public ZonedDateTime z(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i2 = AnonymousClass2.f3120a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return a0(this.f3117e.z(temporalField, j2));
                }
                return b0(ZoneOffset.A(chronoField.g(j2)));
            }
            return C(j2, I(), this.f3119g);
        }
        return (ZonedDateTime) temporalField.b(this, j2);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public int hashCode() {
        return (this.f3117e.hashCode() ^ this.f3118f.hashCode()) ^ Integer.rotateLeft(this.f3119g.hashCode(), 3);
    }

    public ZonedDateTime i0(int i2) {
        return a0(this.f3117e.e0(i2));
    }

    public ZonedDateTime j0(int i2) {
        return a0(this.f3117e.f0(i2));
    }

    public ZonedDateTime k0(int i2) {
        return a0(this.f3117e.g0(i2));
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        ZonedDateTime D = D(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ZonedDateTime A = D.A(this.f3119g);
            if (temporalUnit.isDateBased()) {
                return this.f3117e.l(A.f3117e, temporalUnit);
            }
            return e0().l(A.e0(), temporalUnit);
        }
        return temporalUnit.a(this, D);
    }

    public ZonedDateTime l0(int i2) {
        return a0(this.f3117e.h0(i2));
    }

    public ZonedDateTime m0(int i2) {
        return a0(this.f3117e.i0(i2));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: n0 */
    public ZonedDateTime A(ZoneId zoneId) {
        Jdk8Methods.i(zoneId, "zone");
        if (this.f3119g.equals(zoneId)) {
            return this;
        }
        return C(this.f3117e.u(this.f3118f), this.f3117e.I(), zoneId);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public String o(DateTimeFormatter dateTimeFormatter) {
        return super.o(dateTimeFormatter);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    /* renamed from: o0 */
    public ZonedDateTime B(ZoneId zoneId) {
        Jdk8Methods.i(zoneId, "zone");
        if (this.f3119g.equals(zoneId)) {
            return this;
        }
        return S(this.f3117e, zoneId, this.f3118f);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ZoneOffset p() {
        return this.f3118f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p0(DataOutput dataOutput) {
        this.f3117e.j0(dataOutput);
        this.f3118f.F(dataOutput);
        this.f3119g.t(dataOutput);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ZoneId q() {
        return this.f3119g;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public String toString() {
        String str = this.f3117e.toString() + this.f3118f.toString();
        if (this.f3118f != this.f3119g) {
            return str + '[' + this.f3119g.toString() + ']';
        }
        return str;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public LocalTime x() {
        return this.f3117e.x();
    }
}
