package org.threeten.bp.chrono;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChronoLocalDateTimeImpl<D extends ChronoLocalDate> extends ChronoLocalDateTime<D> implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final D f3124e;

    /* renamed from: f  reason: collision with root package name */
    private final LocalTime f3125f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.ChronoLocalDateTimeImpl$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3126a;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3126a = iArr;
            try {
                iArr[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3126a[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3126a[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3126a[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3126a[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3126a[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3126a[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private ChronoLocalDateTimeImpl(D d2, LocalTime localTime) {
        Jdk8Methods.i(d2, "date");
        Jdk8Methods.i(localTime, "time");
        this.f3124e = d2;
        this.f3125f = localTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <R extends ChronoLocalDate> ChronoLocalDateTimeImpl<R> A(R r2, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl<>(r2, localTime);
    }

    private ChronoLocalDateTimeImpl<D> C(long j2) {
        return J(this.f3124e.i(j2, ChronoUnit.DAYS), this.f3125f);
    }

    private ChronoLocalDateTimeImpl<D> D(long j2) {
        return H(this.f3124e, j2, 0L, 0L, 0L);
    }

    private ChronoLocalDateTimeImpl<D> E(long j2) {
        return H(this.f3124e, 0L, j2, 0L, 0L);
    }

    private ChronoLocalDateTimeImpl<D> F(long j2) {
        return H(this.f3124e, 0L, 0L, 0L, j2);
    }

    private ChronoLocalDateTimeImpl<D> H(D d2, long j2, long j3, long j4, long j5) {
        LocalTime B;
        if ((j2 | j3 | j4 | j5) == 0) {
            return J(d2, this.f3125f);
        }
        long K = this.f3125f.K();
        long j6 = (j5 % 86400000000000L) + ((j4 % 86400) * 1000000000) + ((j3 % 1440) * 60000000000L) + ((j2 % 24) * 3600000000000L) + K;
        long e2 = (j5 / 86400000000000L) + (j4 / 86400) + (j3 / 1440) + (j2 / 24) + Jdk8Methods.e(j6, 86400000000000L);
        long h2 = Jdk8Methods.h(j6, 86400000000000L);
        if (h2 == K) {
            B = this.f3125f;
        } else {
            B = LocalTime.B(h2);
        }
        return J(d2.i(e2, ChronoUnit.DAYS), B);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDateTime<?> I(ObjectInput objectInput) {
        return ((ChronoLocalDate) objectInput.readObject()).n((LocalTime) objectInput.readObject());
    }

    private ChronoLocalDateTimeImpl<D> J(Temporal temporal, LocalTime localTime) {
        D d2 = this.f3124e;
        if (d2 == temporal && this.f3125f == localTime) {
            return this;
        }
        return new ChronoLocalDateTimeImpl<>(d2.p().c(temporal), localTime);
    }

    private Object writeReplace() {
        return new Ser((byte) 12, this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: B */
    public ChronoLocalDateTimeImpl<D> t(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass1.f3126a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return F(j2);
                case 2:
                    return C(j2 / 86400000000L).F((j2 % 86400000000L) * 1000);
                case 3:
                    return C(j2 / 86400000).F((j2 % 86400000) * 1000000);
                case 4:
                    return G(j2);
                case 5:
                    return E(j2);
                case R$styleable.f1342r /* 6 */:
                    return D(j2);
                case R$styleable.f1343s /* 7 */:
                    return C(j2 / 256).D((j2 % 256) * 12);
                default:
                    return J(this.f3124e.i(j2, temporalUnit), this.f3125f);
            }
        }
        return this.f3124e.p().d(temporalUnit.b(this, j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChronoLocalDateTimeImpl<D> G(long j2) {
        return H(this.f3124e, 0L, 0L, j2, 0L);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: K */
    public ChronoLocalDateTimeImpl<D> y(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof ChronoLocalDate) {
            return J((ChronoLocalDate) temporalAdjuster, this.f3125f);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return J(this.f3124e, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof ChronoLocalDateTimeImpl) {
            return this.f3124e.p().d((ChronoLocalDateTimeImpl) temporalAdjuster);
        }
        return this.f3124e.p().d((ChronoLocalDateTimeImpl) temporalAdjuster.j(this));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    /* renamed from: L */
    public ChronoLocalDateTimeImpl<D> z(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return J(this.f3124e, this.f3125f.e(temporalField, j2));
            }
            return J(this.f3124e.e(temporalField, j2), this.f3125f);
        }
        return this.f3124e.p().d(temporalField.b(this, j2));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3125f.a(temporalField);
            }
            return this.f3124e.a(temporalField);
        }
        return temporalField.d(this);
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

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3125f.f(temporalField);
            }
            return this.f3124e.f(temporalField);
        }
        return a(temporalField).a(h(temporalField), temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isTimeBased()) {
                return this.f3125f.h(temporalField);
            }
            return this.f3124e.h(temporalField);
        }
        return temporalField.c(this);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [org.threeten.bp.chrono.ChronoLocalDate] */
    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDateTime<?> k2 = w().p().k(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
            if (chronoUnit.c()) {
                ChronoField chronoField = ChronoField.EPOCH_DAY;
                long h2 = k2.h(chronoField) - this.f3124e.h(chronoField);
                switch (AnonymousClass1.f3126a[chronoUnit.ordinal()]) {
                    case 1:
                        h2 = Jdk8Methods.m(h2, 86400000000000L);
                        break;
                    case 2:
                        h2 = Jdk8Methods.m(h2, 86400000000L);
                        break;
                    case 3:
                        h2 = Jdk8Methods.m(h2, 86400000L);
                        break;
                    case 4:
                        h2 = Jdk8Methods.l(h2, 86400);
                        break;
                    case 5:
                        h2 = Jdk8Methods.l(h2, 1440);
                        break;
                    case R$styleable.f1342r /* 6 */:
                        h2 = Jdk8Methods.l(h2, 24);
                        break;
                    case R$styleable.f1343s /* 7 */:
                        h2 = Jdk8Methods.l(h2, 2);
                        break;
                }
                return Jdk8Methods.k(h2, this.f3125f.l(k2.x(), temporalUnit));
            }
            ?? w2 = k2.w();
            ChronoLocalDate chronoLocalDate = w2;
            if (k2.x().w(this.f3125f)) {
                chronoLocalDate = w2.g(1L, ChronoUnit.DAYS);
            }
            return this.f3124e.l(chronoLocalDate, temporalUnit);
        }
        return temporalUnit.a(this, k2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public ChronoZonedDateTime<D> n(ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.D(this, zoneId, null);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public D w() {
        return this.f3124e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.f3124e);
        objectOutput.writeObject(this.f3125f);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDateTime
    public LocalTime x() {
        return this.f3125f;
    }
}
