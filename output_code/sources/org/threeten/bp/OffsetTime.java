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
public final class OffsetTime extends DefaultInterfaceTemporalAccessor implements Temporal, TemporalAdjuster, Comparable<OffsetTime>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final OffsetTime f3079f = LocalTime.f3039h.m(ZoneOffset.f3110m);

    /* renamed from: g  reason: collision with root package name */
    public static final OffsetTime f3080g = LocalTime.f3040i.m(ZoneOffset.f3109l);

    /* renamed from: h  reason: collision with root package name */
    public static final TemporalQuery<OffsetTime> f3081h = new TemporalQuery<OffsetTime>() { // from class: org.threeten.bp.OffsetTime.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public OffsetTime a(TemporalAccessor temporalAccessor) {
            return OffsetTime.n(temporalAccessor);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    private final LocalTime f3082d;

    /* renamed from: e  reason: collision with root package name */
    private final ZoneOffset f3083e;

    /* renamed from: org.threeten.bp.OffsetTime$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3084a;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3084a = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3084a[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3084a[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3084a[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3084a[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3084a[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3084a[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private OffsetTime(LocalTime localTime, ZoneOffset zoneOffset) {
        this.f3082d = (LocalTime) Jdk8Methods.i(localTime, "time");
        this.f3083e = (ZoneOffset) Jdk8Methods.i(zoneOffset, "offset");
    }

    public static OffsetTime n(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof OffsetTime) {
            return (OffsetTime) temporalAccessor;
        }
        try {
            return new OffsetTime(LocalTime.p(temporalAccessor), ZoneOffset.w(temporalAccessor));
        } catch (DateTimeException unused) {
            throw new DateTimeException("Unable to obtain OffsetTime from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
        }
    }

    public static OffsetTime q(LocalTime localTime, ZoneOffset zoneOffset) {
        return new OffsetTime(localTime, zoneOffset);
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OffsetTime s(DataInput dataInput) {
        return q(LocalTime.J(dataInput), ZoneOffset.C(dataInput));
    }

    private long t() {
        return this.f3082d.K() - (this.f3083e.x() * 1000000000);
    }

    private OffsetTime u(LocalTime localTime, ZoneOffset zoneOffset) {
        if (this.f3082d == localTime && this.f3083e.equals(zoneOffset)) {
            return this;
        }
        return new OffsetTime(localTime, zoneOffset);
    }

    private Object writeReplace() {
        return new Ser((byte) 66, this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return temporalField.f();
            }
            return this.f3082d.a(temporalField);
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.e()) {
            return (R) ChronoUnit.NANOS;
        }
        if (temporalQuery != TemporalQueries.d() && temporalQuery != TemporalQueries.f()) {
            if (temporalQuery == TemporalQueries.c()) {
                return (R) this.f3082d;
            }
            if (temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.g()) {
                return (R) super.b(temporalQuery);
            }
            return null;
        }
        return (R) o();
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased() || temporalField == ChronoField.OFFSET_SECONDS) {
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
        if (!(obj instanceof OffsetTime)) {
            return false;
        }
        OffsetTime offsetTime = (OffsetTime) obj;
        if (this.f3082d.equals(offsetTime.f3082d) && this.f3083e.equals(offsetTime.f3083e)) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        return super.f(temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return o().x();
            }
            return this.f3082d.h(temporalField);
        }
        return temporalField.c(this);
    }

    public int hashCode() {
        return this.f3082d.hashCode() ^ this.f3083e.hashCode();
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.NANO_OF_DAY, this.f3082d.K()).e(ChronoField.OFFSET_SECONDS, o().x());
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        OffsetTime n2 = n(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            long t2 = n2.t() - t();
            switch (AnonymousClass2.f3084a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return t2;
                case 2:
                    return t2 / 1000;
                case 3:
                    return t2 / 1000000;
                case 4:
                    return t2 / 1000000000;
                case 5:
                    return t2 / 60000000000L;
                case R$styleable.f1342r /* 6 */:
                    return t2 / 3600000000000L;
                case R$styleable.f1343s /* 7 */:
                    return t2 / 43200000000000L;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return temporalUnit.a(this, n2);
    }

    @Override // java.lang.Comparable
    /* renamed from: m */
    public int compareTo(OffsetTime offsetTime) {
        if (this.f3083e.equals(offsetTime.f3083e)) {
            return this.f3082d.compareTo(offsetTime.f3082d);
        }
        int b2 = Jdk8Methods.b(t(), offsetTime.t());
        if (b2 == 0) {
            return this.f3082d.compareTo(offsetTime.f3082d);
        }
        return b2;
    }

    public ZoneOffset o() {
        return this.f3083e;
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: p */
    public OffsetTime g(long j2, TemporalUnit temporalUnit) {
        return j2 == Long.MIN_VALUE ? i(Long.MAX_VALUE, temporalUnit).i(1L, temporalUnit) : i(-j2, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: r */
    public OffsetTime i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return u(this.f3082d.i(j2, temporalUnit), this.f3083e);
        }
        return (OffsetTime) temporalUnit.b(this, j2);
    }

    public String toString() {
        return this.f3082d.toString() + this.f3083e.toString();
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: v */
    public OffsetTime c(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalTime) {
            return u((LocalTime) temporalAdjuster, this.f3083e);
        }
        if (temporalAdjuster instanceof ZoneOffset) {
            return u(this.f3082d, (ZoneOffset) temporalAdjuster);
        }
        if (temporalAdjuster instanceof OffsetTime) {
            return (OffsetTime) temporalAdjuster;
        }
        return (OffsetTime) temporalAdjuster.j(this);
    }

    @Override // org.threeten.bp.temporal.Temporal
    /* renamed from: w */
    public OffsetTime e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return u(this.f3082d, ZoneOffset.A(((ChronoField) temporalField).g(j2)));
            }
            return u(this.f3082d.e(temporalField, j2), this.f3083e);
        }
        return (OffsetTime) temporalField.b(this, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(DataOutput dataOutput) {
        this.f3082d.T(dataOutput);
        this.f3083e.F(dataOutput);
    }
}
