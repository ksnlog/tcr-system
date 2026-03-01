package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.Comparator;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
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
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class OffsetDateTime extends DefaultInterfaceTemporal implements TemporalAdjuster, Comparable<OffsetDateTime>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final OffsetDateTime f3072f = LocalDateTime.f3033g.A(ZoneOffset.f3110m);

    /* renamed from: g  reason: collision with root package name */
    public static final OffsetDateTime f3073g = LocalDateTime.f3034h.A(ZoneOffset.f3109l);

    /* renamed from: h  reason: collision with root package name */
    public static final TemporalQuery<OffsetDateTime> f3074h = new TemporalQuery<OffsetDateTime>() { // from class: org.threeten.bp.OffsetDateTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public OffsetDateTime a(TemporalAccessor temporalAccessor) {
            return OffsetDateTime.o(temporalAccessor);
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private static final Comparator<OffsetDateTime> f3075i = new Comparator<OffsetDateTime>() { // from class: org.threeten.bp.OffsetDateTime.2
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(OffsetDateTime offsetDateTime, OffsetDateTime offsetDateTime2) {
            int b2 = Jdk8Methods.b(offsetDateTime.toEpochSecond(), offsetDateTime2.toEpochSecond());
            if (b2 == 0) {
                return Jdk8Methods.b(offsetDateTime.p(), offsetDateTime2.p());
            }
            return b2;
        }
    };

    /* renamed from: d  reason: collision with root package name */
    private final LocalDateTime f3076d;

    /* renamed from: e  reason: collision with root package name */
    private final ZoneOffset f3077e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.OffsetDateTime$3  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3078a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3078a = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3078a[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private OffsetDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        this.f3076d = (LocalDateTime) Jdk8Methods.i(localDateTime, "dateTime");
        this.f3077e = (ZoneOffset) Jdk8Methods.i(zoneOffset, "offset");
    }

    public static OffsetDateTime o(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof OffsetDateTime) {
            return (OffsetDateTime) temporalAccessor;
        }
        try {
            ZoneOffset w2 = ZoneOffset.w(temporalAccessor);
            try {
                return s(LocalDateTime.D(temporalAccessor), w2);
            } catch (DateTimeException unused) {
                return t(Instant.o(temporalAccessor), w2);
            }
        } catch (DateTimeException unused2) {
            throw new DateTimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static OffsetDateTime s(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return new OffsetDateTime(localDateTime, zoneOffset);
    }

    public static OffsetDateTime t(Instant instant, ZoneId zoneId) {
        Jdk8Methods.i(instant, "instant");
        Jdk8Methods.i(zoneId, "zone");
        ZoneOffset a2 = zoneId.n().a(instant);
        return new OffsetDateTime(LocalDateTime.Q(instant.p(), instant.q(), a2), a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OffsetDateTime v(DataInput dataInput) {
        return s(LocalDateTime.Y(dataInput), ZoneOffset.C(dataInput));
    }

    private Object writeReplace() {
        return new Ser((byte) 69, this);
    }

    private OffsetDateTime z(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        if (this.f3076d == localDateTime && this.f3077e.equals(zoneOffset)) {
            return this;
        }
        return new OffsetDateTime(localDateTime, zoneOffset);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: A */
    public OffsetDateTime c(TemporalAdjuster temporalAdjuster) {
        if (!(temporalAdjuster instanceof LocalDate) && !(temporalAdjuster instanceof LocalTime) && !(temporalAdjuster instanceof LocalDateTime)) {
            if (temporalAdjuster instanceof Instant) {
                return t((Instant) temporalAdjuster, this.f3077e);
            }
            if (temporalAdjuster instanceof ZoneOffset) {
                return z(this.f3076d, (ZoneOffset) temporalAdjuster);
            }
            if (temporalAdjuster instanceof OffsetDateTime) {
                return (OffsetDateTime) temporalAdjuster;
            }
            return (OffsetDateTime) temporalAdjuster.j(this);
        }
        return z(this.f3076d.y(temporalAdjuster), this.f3077e);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: B */
    public OffsetDateTime e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i2 = AnonymousClass3.f3078a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return z(this.f3076d.z(temporalField, j2), this.f3077e);
                }
                return z(this.f3076d, ZoneOffset.A(chronoField.g(j2)));
            }
            return t(Instant.v(j2, p()), this.f3077e);
        }
        return (OffsetDateTime) temporalField.b(this, j2);
    }

    public OffsetDateTime C(ZoneOffset zoneOffset) {
        if (zoneOffset.equals(this.f3077e)) {
            return this;
        }
        return new OffsetDateTime(this.f3076d.W(zoneOffset.x() - this.f3077e.x()), zoneOffset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(DataOutput dataOutput) {
        this.f3076d.j0(dataOutput);
        this.f3077e.F(dataOutput);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField != ChronoField.INSTANT_SECONDS && temporalField != ChronoField.OFFSET_SECONDS) {
                return this.f3076d.a(temporalField);
            }
            return temporalField.f();
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.a()) {
            return (R) IsoChronology.f3167h;
        }
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery != TemporalQueries.d() && temporalQuery != TemporalQueries.f()) {
            if (temporalQuery == TemporalQueries.b()) {
                return (R) w();
            }
            if (temporalQuery == TemporalQueries.c()) {
                return (R) y();
            }
            if (temporalQuery == TemporalQueries.g()) {
                return null;
            }
            return (R) super.b(temporalQuery);
        }
        return (R) q();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.a(this));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OffsetDateTime)) {
            return false;
        }
        OffsetDateTime offsetDateTime = (OffsetDateTime) obj;
        if (this.f3076d.equals(offsetDateTime.f3076d) && this.f3077e.equals(offsetDateTime.f3077e)) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass3.f3078a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return this.f3076d.f(temporalField);
                }
                return q().x();
            }
            throw new DateTimeException("Field too large for an int: " + temporalField);
        }
        return super.f(temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass3.f3078a[((ChronoField) temporalField).ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return this.f3076d.h(temporalField);
                }
                return q().x();
            }
            return toEpochSecond();
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return this.f3076d.hashCode() ^ this.f3077e.hashCode();
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.EPOCH_DAY, w().w()).e(ChronoField.NANO_OF_DAY, y().K()).e(ChronoField.OFFSET_SECONDS, q().x());
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        OffsetDateTime o2 = o(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return this.f3076d.l(o2.C(this.f3077e).f3076d, temporalUnit);
        }
        return temporalUnit.a(this, o2);
    }

    @Override // java.lang.Comparable
    /* renamed from: n */
    public int compareTo(OffsetDateTime offsetDateTime) {
        if (q().equals(offsetDateTime.q())) {
            return x().compareTo(offsetDateTime.x());
        }
        int b2 = Jdk8Methods.b(toEpochSecond(), offsetDateTime.toEpochSecond());
        if (b2 == 0) {
            int t2 = y().t() - offsetDateTime.y().t();
            if (t2 == 0) {
                return x().compareTo(offsetDateTime.x());
            }
            return t2;
        }
        return b2;
    }

    public int p() {
        return this.f3076d.I();
    }

    public ZoneOffset q() {
        return this.f3077e;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    /* renamed from: r */
    public OffsetDateTime g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    public long toEpochSecond() {
        return this.f3076d.u(this.f3077e);
    }

    public String toString() {
        return this.f3076d.toString() + this.f3077e.toString();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: u */
    public OffsetDateTime i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return z(this.f3076d.t(j2, temporalUnit), this.f3077e);
        }
        return (OffsetDateTime) temporalUnit.b(this, j2);
    }

    public LocalDate w() {
        return this.f3076d.w();
    }

    public LocalDateTime x() {
        return this.f3076d;
    }

    public LocalTime y() {
        return this.f3076d.x();
    }
}
