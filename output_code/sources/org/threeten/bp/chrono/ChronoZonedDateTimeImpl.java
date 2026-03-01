package org.threeten.bp.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.zone.ZoneOffsetTransition;
import org.threeten.bp.zone.ZoneRules;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChronoZonedDateTimeImpl<D extends ChronoLocalDate> extends ChronoZonedDateTime<D> implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final ChronoLocalDateTimeImpl<D> f3129e;

    /* renamed from: f  reason: collision with root package name */
    private final ZoneOffset f3130f;

    /* renamed from: g  reason: collision with root package name */
    private final ZoneId f3131g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.ChronoZonedDateTimeImpl$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3132a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3132a = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3132a[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ChronoZonedDateTimeImpl(ChronoLocalDateTimeImpl<D> chronoLocalDateTimeImpl, ZoneOffset zoneOffset, ZoneId zoneId) {
        this.f3129e = (ChronoLocalDateTimeImpl) Jdk8Methods.i(chronoLocalDateTimeImpl, "dateTime");
        this.f3130f = (ZoneOffset) Jdk8Methods.i(zoneOffset, "offset");
        this.f3131g = (ZoneId) Jdk8Methods.i(zoneId, "zone");
    }

    private ChronoZonedDateTimeImpl<D> C(Instant instant, ZoneId zoneId) {
        return E(v().p(), instant, zoneId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <R extends ChronoLocalDate> ChronoZonedDateTime<R> D(ChronoLocalDateTimeImpl<R> chronoLocalDateTimeImpl, ZoneId zoneId, ZoneOffset zoneOffset) {
        Jdk8Methods.i(chronoLocalDateTimeImpl, "localDateTime");
        Jdk8Methods.i(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ChronoZonedDateTimeImpl(chronoLocalDateTimeImpl, (ZoneOffset) zoneId, zoneId);
        }
        ZoneRules n2 = zoneId.n();
        LocalDateTime D = LocalDateTime.D(chronoLocalDateTimeImpl);
        List<ZoneOffset> c2 = n2.c(D);
        if (c2.size() == 1) {
            zoneOffset = c2.get(0);
        } else if (c2.size() == 0) {
            ZoneOffsetTransition b2 = n2.b(D);
            chronoLocalDateTimeImpl = chronoLocalDateTimeImpl.G(b2.d().g());
            zoneOffset = b2.g();
        } else if (zoneOffset == null || !c2.contains(zoneOffset)) {
            zoneOffset = c2.get(0);
        }
        Jdk8Methods.i(zoneOffset, "offset");
        return new ChronoZonedDateTimeImpl(chronoLocalDateTimeImpl, zoneOffset, zoneId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <R extends ChronoLocalDate> ChronoZonedDateTimeImpl<R> E(Chronology chronology, Instant instant, ZoneId zoneId) {
        ZoneOffset a2 = zoneId.n().a(instant);
        Jdk8Methods.i(a2, "offset");
        return new ChronoZonedDateTimeImpl<>((ChronoLocalDateTimeImpl) chronology.k(LocalDateTime.Q(instant.p(), instant.q(), a2)), a2, zoneId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoZonedDateTime<?> F(ObjectInput objectInput) {
        return (ChronoZonedDateTime<D>) ((ChronoLocalDateTime) objectInput.readObject()).n((ZoneOffset) objectInput.readObject()).B((ZoneId) objectInput.readObject());
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 13, this);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> A(ZoneId zoneId) {
        Jdk8Methods.i(zoneId, "zone");
        if (this.f3131g.equals(zoneId)) {
            return this;
        }
        return C(this.f3129e.v(this.f3130f), zoneId);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> B(ZoneId zoneId) {
        return D(this.f3129e, zoneId, this.f3130f);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.a(this));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public int hashCode() {
        return (w().hashCode() ^ p().hashCode()) ^ Integer.rotateLeft(q().hashCode(), 3);
    }

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoZonedDateTime<?> r2 = v().p().r(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return this.f3129e.l(r2.A(this.f3130f).w(), temporalUnit);
        }
        return temporalUnit.a(this, r2);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ZoneOffset p() {
        return this.f3130f;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ZoneId q() {
        return this.f3131g;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public String toString() {
        String str = w().toString() + p().toString();
        if (p() != q()) {
            return str + '[' + q().toString() + ']';
        }
        return str;
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.temporal.Temporal
    /* renamed from: u */
    public ChronoZonedDateTime<D> i(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return c(this.f3129e.t(j2, temporalUnit));
        }
        return v().p().e(temporalUnit.b(this, j2));
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime
    public ChronoLocalDateTime<D> w() {
        return this.f3129e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.f3129e);
        objectOutput.writeObject(this.f3130f);
        objectOutput.writeObject(this.f3131g);
    }

    @Override // org.threeten.bp.chrono.ChronoZonedDateTime, org.threeten.bp.temporal.Temporal
    /* renamed from: z */
    public ChronoZonedDateTime<D> e(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i2 = AnonymousClass1.f3132a[chronoField.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return D(this.f3129e.z(temporalField, j2), this.f3131g, this.f3130f);
                }
                return C(this.f3129e.v(ZoneOffset.A(chronoField.g(j2))), this.f3131g);
            }
            return i(j2 - toEpochSecond(), ChronoUnit.SECONDS);
        }
        return v().p().e(temporalField.b(this, j2));
    }
}
