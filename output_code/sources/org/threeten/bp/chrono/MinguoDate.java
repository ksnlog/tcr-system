package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MinguoDate extends ChronoDateImpl<MinguoDate> {

    /* renamed from: e  reason: collision with root package name */
    private final LocalDate f3193e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.MinguoDate$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3194a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3194a = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3194a[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3194a[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3194a[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3194a[ChronoField.PROLEPTIC_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3194a[ChronoField.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3194a[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MinguoDate(LocalDate localDate) {
        Jdk8Methods.i(localDate, "date");
        this.f3193e = localDate;
    }

    private long F() {
        return ((G() * 12) + this.f3193e.K()) - 1;
    }

    private int G() {
        return this.f3193e.M() - 1911;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate N(DataInput dataInput) {
        return MinguoChronology.f3191h.s(dataInput.readInt(), dataInput.readByte(), dataInput.readByte());
    }

    private MinguoDate O(LocalDate localDate) {
        return localDate.equals(this.f3193e) ? this : new MinguoDate(localDate);
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: D */
    public MinguoChronology p() {
        return MinguoChronology.f3191h;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: E */
    public MinguoEra q() {
        return (MinguoEra) super.q();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: H */
    public MinguoDate t(long j2, TemporalUnit temporalUnit) {
        return (MinguoDate) super.g(j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: I */
    public MinguoDate z(long j2, TemporalUnit temporalUnit) {
        return (MinguoDate) super.u(j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: J */
    public MinguoDate v(TemporalAmount temporalAmount) {
        return (MinguoDate) super.v(temporalAmount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: K */
    public MinguoDate A(long j2) {
        return O(this.f3193e.a0(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: L */
    public MinguoDate B(long j2) {
        return O(this.f3193e.b0(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: M */
    public MinguoDate C(long j2) {
        return O(this.f3193e.d0(j2));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: P */
    public MinguoDate x(TemporalAdjuster temporalAdjuster) {
        return (MinguoDate) super.c(temporalAdjuster);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r2 != 7) goto L13;
     */
    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: Q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.threeten.bp.chrono.MinguoDate y(org.threeten.bp.temporal.TemporalField r8, long r9) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof org.threeten.bp.temporal.ChronoField
            if (r0 == 0) goto L94
            r0 = r8
            org.threeten.bp.temporal.ChronoField r0 = (org.threeten.bp.temporal.ChronoField) r0
            long r1 = r7.h(r0)
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 != 0) goto L10
            return r7
        L10:
            int[] r1 = org.threeten.bp.chrono.MinguoDate.AnonymousClass1.f3194a
            int r2 = r0.ordinal()
            r2 = r1[r2]
            r3 = 7
            r4 = 6
            r5 = 4
            if (r2 == r5) goto L3a
            r6 = 5
            if (r2 == r6) goto L25
            if (r2 == r4) goto L3a
            if (r2 == r3) goto L3a
            goto L53
        L25:
            org.threeten.bp.chrono.MinguoChronology r8 = r7.p()
            org.threeten.bp.temporal.ValueRange r8 = r8.v(r0)
            r8.b(r9, r0)
            long r0 = r7.F()
            long r9 = r9 - r0
            org.threeten.bp.chrono.MinguoDate r8 = r7.B(r9)
            return r8
        L3a:
            org.threeten.bp.chrono.MinguoChronology r2 = r7.p()
            org.threeten.bp.temporal.ValueRange r2 = r2.v(r0)
            int r2 = r2.a(r9, r0)
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            if (r0 == r5) goto L7d
            if (r0 == r4) goto L70
            if (r0 == r3) goto L5e
        L53:
            org.threeten.bp.LocalDate r0 = r7.f3193e
            org.threeten.bp.LocalDate r8 = r0.y(r8, r9)
            org.threeten.bp.chrono.MinguoDate r8 = r7.O(r8)
            return r8
        L5e:
            org.threeten.bp.LocalDate r8 = r7.f3193e
            int r9 = r7.G()
            int r1 = r1 - r9
            int r1 = r1 + 1911
            org.threeten.bp.LocalDate r8 = r8.l0(r1)
            org.threeten.bp.chrono.MinguoDate r8 = r7.O(r8)
            return r8
        L70:
            org.threeten.bp.LocalDate r8 = r7.f3193e
            int r2 = r2 + 1911
            org.threeten.bp.LocalDate r8 = r8.l0(r2)
            org.threeten.bp.chrono.MinguoDate r8 = r7.O(r8)
            return r8
        L7d:
            org.threeten.bp.LocalDate r8 = r7.f3193e
            int r9 = r7.G()
            if (r9 < r1) goto L88
            int r2 = r2 + 1911
            goto L8b
        L88:
            int r1 = r1 - r2
            int r2 = r1 + 1911
        L8b:
            org.threeten.bp.LocalDate r8 = r8.l0(r2)
            org.threeten.bp.chrono.MinguoDate r8 = r7.O(r8)
            return r8
        L94:
            org.threeten.bp.temporal.Temporal r8 = r8.b(r7, r9)
            org.threeten.bp.chrono.MinguoDate r8 = (org.threeten.bp.chrono.MinguoDate) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.MinguoDate.y(org.threeten.bp.temporal.TemporalField, long):org.threeten.bp.chrono.MinguoDate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R(DataOutput dataOutput) {
        dataOutput.writeInt(f(ChronoField.YEAR));
        dataOutput.writeByte(f(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(f(ChronoField.DAY_OF_MONTH));
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        long c2;
        if (temporalField instanceof ChronoField) {
            if (d(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i2 = AnonymousClass1.f3194a[chronoField.ordinal()];
                if (i2 != 1 && i2 != 2 && i2 != 3) {
                    if (i2 != 4) {
                        return p().v(chronoField);
                    }
                    ValueRange f2 = ChronoField.YEAR.f();
                    if (G() <= 0) {
                        c2 = (-f2.d()) + 1 + 1911;
                    } else {
                        c2 = f2.c() - 1911;
                    }
                    return ValueRange.i(1L, c2);
                }
                return this.f3193e.a(temporalField);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.d(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MinguoDate) {
            return this.f3193e.equals(((MinguoDate) obj).f3193e);
        }
        return false;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i2 = AnonymousClass1.f3194a[((ChronoField) temporalField).ordinal()];
            int i3 = 1;
            if (i2 != 4) {
                if (i2 != 5) {
                    if (i2 != 6) {
                        if (i2 != 7) {
                            return this.f3193e.h(temporalField);
                        }
                        if (G() < 1) {
                            i3 = 0;
                        }
                        return i3;
                    }
                    return G();
                }
                return F();
            }
            int G = G();
            if (G < 1) {
                G = 1 - G;
            }
            return G;
        }
        return temporalField.c(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        return p().i().hashCode() ^ this.f3193e.hashCode();
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long l(Temporal temporal, TemporalUnit temporalUnit) {
        return super.l(temporal, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<MinguoDate> n(LocalTime localTime) {
        return super.n(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long w() {
        return this.f3193e.w();
    }
}
