package org.threeten.bp.temporal;

import java.util.Map;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class IsoFields {

    /* renamed from: a  reason: collision with root package name */
    public static final TemporalField f3381a = Field.DAY_OF_QUARTER;

    /* renamed from: b  reason: collision with root package name */
    public static final TemporalField f3382b = Field.QUARTER_OF_YEAR;

    /* renamed from: c  reason: collision with root package name */
    public static final TemporalField f3383c = Field.WEEK_OF_WEEK_BASED_YEAR;

    /* renamed from: d  reason: collision with root package name */
    public static final TemporalField f3384d = Field.WEEK_BASED_YEAR;

    /* renamed from: e  reason: collision with root package name */
    public static final TemporalUnit f3385e = Unit.WEEK_BASED_YEARS;

    /* renamed from: f  reason: collision with root package name */
    public static final TemporalUnit f3386f = Unit.QUARTER_YEARS;

    /* renamed from: org.threeten.bp.temporal.IsoFields$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3387a;

        static {
            int[] iArr = new int[Unit.values().length];
            f3387a = iArr;
            try {
                iArr[Unit.WEEK_BASED_YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3387a[Unit.QUARTER_YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum Field implements TemporalField {
        DAY_OF_QUARTER { // from class: org.threeten.bp.temporal.IsoFields.Field.1
            @Override // org.threeten.bp.temporal.TemporalField
            public boolean a(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(ChronoField.DAY_OF_YEAR) && temporalAccessor.d(ChronoField.MONTH_OF_YEAR) && temporalAccessor.d(ChronoField.YEAR) && Field.q(temporalAccessor)) {
                    return true;
                }
                return false;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R b(R r2, long j2) {
                long c2 = c(r2);
                f().b(j2, this);
                ChronoField chronoField = ChronoField.DAY_OF_YEAR;
                return (R) r2.e(chronoField, r2.h(chronoField) + (j2 - c2));
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long c(TemporalAccessor temporalAccessor) {
                int i2;
                if (temporalAccessor.d(this)) {
                    int f2 = temporalAccessor.f(ChronoField.DAY_OF_YEAR);
                    int f3 = temporalAccessor.f(ChronoField.MONTH_OF_YEAR);
                    long h2 = temporalAccessor.h(ChronoField.YEAR);
                    int[] iArr = Field.f3392h;
                    int i3 = (f3 - 1) / 3;
                    if (IsoChronology.f3167h.u(h2)) {
                        i2 = 4;
                    } else {
                        i2 = 0;
                    }
                    return f2 - iArr[i3 + i2];
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange d(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(this)) {
                    long h2 = temporalAccessor.h(Field.QUARTER_OF_YEAR);
                    if (h2 == 1) {
                        if (IsoChronology.f3167h.u(temporalAccessor.h(ChronoField.YEAR))) {
                            return ValueRange.i(1L, 91L);
                        }
                        return ValueRange.i(1L, 90L);
                    } else if (h2 == 2) {
                        return ValueRange.i(1L, 91L);
                    } else {
                        if (h2 != 3 && h2 != 4) {
                            return f();
                        }
                        return ValueRange.i(1L, 92L);
                    }
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.bp.temporal.TemporalField
            public TemporalAccessor e(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                LocalDate a02;
                ChronoField chronoField = ChronoField.YEAR;
                Long l2 = map.get(chronoField);
                TemporalField temporalField = Field.QUARTER_OF_YEAR;
                Long l3 = map.get(temporalField);
                if (l2 != null && l3 != null) {
                    int g2 = chronoField.g(l2.longValue());
                    long longValue = map.get(Field.DAY_OF_QUARTER).longValue();
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        a02 = LocalDate.U(g2, 1, 1).b0(Jdk8Methods.l(Jdk8Methods.o(l3.longValue(), 1L), 3)).a0(Jdk8Methods.o(longValue, 1L));
                    } else {
                        int a2 = temporalField.f().a(l3.longValue(), temporalField);
                        if (resolverStyle == ResolverStyle.STRICT) {
                            int i2 = 91;
                            if (a2 == 1) {
                                if (!IsoChronology.f3167h.u(g2)) {
                                    i2 = 90;
                                }
                            } else if (a2 != 2) {
                                i2 = 92;
                            }
                            ValueRange.i(1L, i2).b(longValue, this);
                        } else {
                            f().b(longValue, this);
                        }
                        a02 = LocalDate.U(g2, ((a2 - 1) * 3) + 1, 1).a0(longValue - 1);
                    }
                    map.remove(this);
                    map.remove(chronoField);
                    map.remove(temporalField);
                    return a02;
                }
                return null;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange f() {
                return ValueRange.j(1L, 90L, 92L);
            }

            @Override // java.lang.Enum
            public String toString() {
                return "DayOfQuarter";
            }
        },
        QUARTER_OF_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.2
            @Override // org.threeten.bp.temporal.TemporalField
            public boolean a(TemporalAccessor temporalAccessor) {
                return temporalAccessor.d(ChronoField.MONTH_OF_YEAR) && Field.q(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R b(R r2, long j2) {
                long c2 = c(r2);
                f().b(j2, this);
                ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
                return (R) r2.e(chronoField, r2.h(chronoField) + ((j2 - c2) * 3));
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long c(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(this)) {
                    return (temporalAccessor.h(ChronoField.MONTH_OF_YEAR) + 2) / 3;
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange d(TemporalAccessor temporalAccessor) {
                return f();
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange f() {
                return ValueRange.i(1L, 4L);
            }

            @Override // java.lang.Enum
            public String toString() {
                return "QuarterOfYear";
            }
        },
        WEEK_OF_WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.3
            @Override // org.threeten.bp.temporal.TemporalField
            public boolean a(TemporalAccessor temporalAccessor) {
                return temporalAccessor.d(ChronoField.EPOCH_DAY) && Field.q(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R b(R r2, long j2) {
                f().b(j2, this);
                return (R) r2.i(Jdk8Methods.o(j2, c(r2)), ChronoUnit.WEEKS);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long c(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(this)) {
                    return Field.m(LocalDate.D(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange d(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(this)) {
                    return Field.p(LocalDate.D(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.IsoFields.Field, org.threeten.bp.temporal.TemporalField
            public TemporalAccessor e(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                TemporalField temporalField;
                LocalDate y2;
                long j2;
                TemporalField temporalField2 = Field.WEEK_BASED_YEAR;
                Long l2 = map.get(temporalField2);
                ChronoField chronoField = ChronoField.DAY_OF_WEEK;
                Long l3 = map.get(chronoField);
                if (l2 != null && l3 != null) {
                    int a2 = temporalField2.f().a(l2.longValue(), temporalField2);
                    long longValue = map.get(Field.WEEK_OF_WEEK_BASED_YEAR).longValue();
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long longValue2 = l3.longValue();
                        if (longValue2 > 7) {
                            long j3 = longValue2 - 1;
                            j2 = j3 / 7;
                            longValue2 = (j3 % 7) + 1;
                        } else if (longValue2 < 1) {
                            j2 = (longValue2 / 7) - 1;
                            longValue2 = (longValue2 % 7) + 7;
                        } else {
                            j2 = 0;
                        }
                        temporalField = temporalField2;
                        y2 = LocalDate.U(a2, 1, 4).c0(longValue - 1).c0(j2).y(chronoField, longValue2);
                    } else {
                        temporalField = temporalField2;
                        int g2 = chronoField.g(l3.longValue());
                        if (resolverStyle == ResolverStyle.STRICT) {
                            Field.p(LocalDate.U(a2, 1, 4)).b(longValue, this);
                        } else {
                            f().b(longValue, this);
                        }
                        y2 = LocalDate.U(a2, 1, 4).c0(longValue - 1).y(chronoField, g2);
                    }
                    map.remove(this);
                    map.remove(temporalField);
                    map.remove(chronoField);
                    return y2;
                }
                return null;
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange f() {
                return ValueRange.j(1L, 52L, 53L);
            }

            @Override // java.lang.Enum
            public String toString() {
                return "WeekOfWeekBasedYear";
            }
        },
        WEEK_BASED_YEAR { // from class: org.threeten.bp.temporal.IsoFields.Field.4
            @Override // org.threeten.bp.temporal.TemporalField
            public boolean a(TemporalAccessor temporalAccessor) {
                return temporalAccessor.d(ChronoField.EPOCH_DAY) && Field.q(temporalAccessor);
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public <R extends Temporal> R b(R r2, long j2) {
                if (a(r2)) {
                    int a2 = f().a(j2, Field.WEEK_BASED_YEAR);
                    LocalDate D = LocalDate.D(r2);
                    ChronoField chronoField = ChronoField.DAY_OF_WEEK;
                    int f2 = D.f(chronoField);
                    int m2 = Field.m(D);
                    if (m2 == 53 && Field.o(a2) == 52) {
                        m2 = 52;
                    }
                    LocalDate U = LocalDate.U(a2, 1, 4);
                    return (R) r2.c(U.a0((f2 - U.f(chronoField)) + ((m2 - 1) * 7)));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public long c(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.d(this)) {
                    return Field.n(LocalDate.D(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange d(TemporalAccessor temporalAccessor) {
                return ChronoField.YEAR.f();
            }

            @Override // org.threeten.bp.temporal.TemporalField
            public ValueRange f() {
                return ChronoField.YEAR.f();
            }

            @Override // java.lang.Enum
            public String toString() {
                return "WeekBasedYear";
            }
        };
        

        /* renamed from: h  reason: collision with root package name */
        private static final int[] f3392h = {0, 90, 181, 273, 0, 91, 182, 274};

        /* JADX INFO: Access modifiers changed from: private */
        public static int m(LocalDate localDate) {
            boolean z2;
            int ordinal = localDate.H().ordinal();
            int I = localDate.I() - 1;
            int i2 = (3 - ordinal) + I;
            int i3 = (i2 - ((i2 / 7) * 7)) - 3;
            if (i3 < -3) {
                i3 += 7;
            }
            if (I < i3) {
                return (int) p(localDate.j0(180).S(1L)).c();
            }
            int i4 = ((I - i3) / 7) + 1;
            if (i4 == 53) {
                if (i3 != -3 && (i3 != -2 || !localDate.N())) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    return 1;
                }
            }
            return i4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int n(LocalDate localDate) {
            int M = localDate.M();
            int I = localDate.I();
            if (I <= 3) {
                if (I - localDate.H().ordinal() < -2) {
                    return M - 1;
                }
                return M;
            } else if (I >= 363) {
                if (((I - 363) - (localDate.N() ? 1 : 0)) - localDate.H().ordinal() >= 0) {
                    return M + 1;
                }
                return M;
            } else {
                return M;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int o(int i2) {
            LocalDate U = LocalDate.U(i2, 1, 1);
            if (U.H() != DayOfWeek.THURSDAY) {
                if (U.H() != DayOfWeek.WEDNESDAY || !U.N()) {
                    return 52;
                }
                return 53;
            }
            return 53;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ValueRange p(LocalDate localDate) {
            return ValueRange.i(1L, o(n(localDate)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean q(TemporalAccessor temporalAccessor) {
            return Chronology.g(temporalAccessor).equals(IsoChronology.f3167h);
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalAccessor e(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            return null;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        /* synthetic */ Field(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.l(31556952)),
        QUARTER_YEARS("QuarterYears", Duration.l(7889238));
        

        /* renamed from: d  reason: collision with root package name */
        private final String f3397d;

        /* renamed from: e  reason: collision with root package name */
        private final Duration f3398e;

        Unit(String str, Duration duration) {
            this.f3397d = str;
            this.f3398e = duration;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public long a(Temporal temporal, Temporal temporal2) {
            int i2 = AnonymousClass1.f3387a[ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return temporal.l(temporal2, ChronoUnit.MONTHS) / 3;
                }
                throw new IllegalStateException("Unreachable");
            }
            TemporalField temporalField = IsoFields.f3384d;
            return Jdk8Methods.o(temporal2.h(temporalField), temporal.h(temporalField));
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public <R extends Temporal> R b(R r2, long j2) {
            int i2 = AnonymousClass1.f3387a[ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return (R) r2.i(j2 / 256, ChronoUnit.YEARS).i((j2 % 256) * 3, ChronoUnit.MONTHS);
                }
                throw new IllegalStateException("Unreachable");
            }
            TemporalField temporalField = IsoFields.f3384d;
            return (R) r2.e(temporalField, Jdk8Methods.k(r2.f(temporalField), j2));
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public Duration getDuration() {
            return this.f3398e;
        }

        @Override // org.threeten.bp.temporal.TemporalUnit
        public boolean isDateBased() {
            return true;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f3397d;
        }
    }
}
