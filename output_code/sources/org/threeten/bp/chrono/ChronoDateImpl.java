package org.threeten.bp.chrono;

import java.io.Serializable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ChronoDateImpl<D extends ChronoLocalDate> extends ChronoLocalDate implements Serializable {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.ChronoDateImpl$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3121a;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            f3121a = iArr;
            try {
                iArr[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3121a[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3121a[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3121a[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3121a[ChronoUnit.DECADES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3121a[ChronoUnit.CENTURIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3121a[ChronoUnit.MILLENNIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    abstract ChronoDateImpl<D> A(long j2);

    abstract ChronoDateImpl<D> B(long j2);

    abstract ChronoDateImpl<D> C(long j2);

    @Override // org.threeten.bp.temporal.Temporal
    public long l(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDate b2 = p().b(temporal);
        if (temporalUnit instanceof ChronoUnit) {
            return LocalDate.D(this).l(b2, temporalUnit);
        }
        return temporalUnit.a(this, b2);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public ChronoLocalDateTime<?> n(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.A(this, localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: z */
    public ChronoDateImpl<D> u(long j2, TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            switch (AnonymousClass1.f3121a[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return A(j2);
                case 2:
                    return A(Jdk8Methods.l(j2, 7));
                case 3:
                    return B(j2);
                case 4:
                    return C(j2);
                case 5:
                    return C(Jdk8Methods.l(j2, 10));
                case R$styleable.f1342r /* 6 */:
                    return C(Jdk8Methods.l(j2, 100));
                case R$styleable.f1343s /* 7 */:
                    return C(Jdk8Methods.l(j2, 1000));
                default:
                    throw new DateTimeException(temporalUnit + " not valid for chronology " + p().i());
            }
        }
        return (ChronoDateImpl) p().c(temporalUnit.b(this, j2));
    }
}
