package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JapaneseChronology extends Chronology implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    static final Locale f3171h = new Locale("ja", "JP", "JP");

    /* renamed from: i  reason: collision with root package name */
    public static final JapaneseChronology f3172i = new JapaneseChronology();

    /* renamed from: j  reason: collision with root package name */
    private static final Map<String, String[]> f3173j;

    /* renamed from: k  reason: collision with root package name */
    private static final Map<String, String[]> f3174k;

    /* renamed from: l  reason: collision with root package name */
    private static final Map<String, String[]> f3175l;

    /* renamed from: org.threeten.bp.chrono.JapaneseChronology$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3176a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3176a = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3176a[ChronoField.DAY_OF_WEEK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3176a[ChronoField.MICRO_OF_DAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3176a[ChronoField.MICRO_OF_SECOND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3176a[ChronoField.HOUR_OF_DAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3176a[ChronoField.HOUR_OF_AMPM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3176a[ChronoField.MINUTE_OF_DAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3176a[ChronoField.MINUTE_OF_HOUR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3176a[ChronoField.SECOND_OF_DAY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3176a[ChronoField.SECOND_OF_MINUTE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3176a[ChronoField.MILLI_OF_DAY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3176a[ChronoField.MILLI_OF_SECOND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3176a[ChronoField.NANO_OF_DAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3176a[ChronoField.NANO_OF_SECOND.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3176a[ChronoField.CLOCK_HOUR_OF_DAY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f3176a[ChronoField.CLOCK_HOUR_OF_AMPM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3176a[ChronoField.EPOCH_DAY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f3176a[ChronoField.PROLEPTIC_MONTH.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f3176a[ChronoField.ERA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f3176a[ChronoField.YEAR.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f3176a[ChronoField.YEAR_OF_ERA.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f3176a[ChronoField.MONTH_OF_YEAR.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f3176a[ChronoField.DAY_OF_YEAR.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f3173j = hashMap;
        HashMap hashMap2 = new HashMap();
        f3174k = hashMap2;
        HashMap hashMap3 = new HashMap();
        f3175l = hashMap3;
        hashMap.put("en", new String[]{"Unknown", "K", "M", "T", "S", "H"});
        hashMap.put("ja", new String[]{"Unknown", "K", "M", "T", "S", "H"});
        hashMap2.put("en", new String[]{"Unknown", "K", "M", "T", "S", "H"});
        hashMap2.put("ja", new String[]{"Unknown", "慶", "明", "大", "昭", "平"});
        hashMap3.put("en", new String[]{"Unknown", "Keio", "Meiji", "Taisho", "Showa", "Heisei"});
        hashMap3.put("ja", new String[]{"Unknown", "慶応", "明治", "大正", "昭和", "平成"});
    }

    private JapaneseChronology() {
    }

    private Object readResolve() {
        return f3172i;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String h() {
        return "japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String i() {
        return "Japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<JapaneseDate> k(TemporalAccessor temporalAccessor) {
        return super.k(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<JapaneseDate> q(Instant instant, ZoneId zoneId) {
        return super.q(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<JapaneseDate> r(TemporalAccessor temporalAccessor) {
        return super.r(temporalAccessor);
    }

    public JapaneseDate s(int i2, int i3, int i4) {
        return new JapaneseDate(LocalDate.U(i2, i3, i4));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: t */
    public JapaneseDate b(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof JapaneseDate) {
            return (JapaneseDate) temporalAccessor;
        }
        return new JapaneseDate(LocalDate.D(temporalAccessor));
    }

    @Override // org.threeten.bp.chrono.Chronology
    /* renamed from: u */
    public JapaneseEra f(int i2) {
        return JapaneseEra.o(i2);
    }

    public int v(Era era, int i2) {
        JapaneseEra japaneseEra;
        if (era instanceof JapaneseEra) {
            int M = (((JapaneseEra) era).r().M() + i2) - 1;
            ValueRange.i(1L, (japaneseEra.m().M() - japaneseEra.r().M()) + 1).b(i2, ChronoField.YEAR_OF_ERA);
            return M;
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    public ValueRange w(ChronoField chronoField) {
        int[] iArr = AnonymousClass1.f3176a;
        switch (iArr[chronoField.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case R$styleable.f1342r /* 6 */:
            case R$styleable.f1343s /* 7 */:
            case R$styleable.f1328d /* 8 */:
            case R$styleable.f1329e /* 9 */:
            case R$styleable.f1330f /* 10 */:
            case R$styleable.f1331g /* 11 */:
            case R$styleable.f1332h /* 12 */:
            case R$styleable.f1333i /* 13 */:
            case R$styleable.f1334j /* 14 */:
            case 15:
            case 16:
            case 17:
            case 18:
                return chronoField.f();
            default:
                Calendar calendar = Calendar.getInstance(f3171h);
                int i2 = 0;
                switch (iArr[chronoField.ordinal()]) {
                    case 19:
                        JapaneseEra[] s2 = JapaneseEra.s();
                        return ValueRange.i(s2[0].getValue(), s2[s2.length - 1].getValue());
                    case 20:
                        JapaneseEra[] s3 = JapaneseEra.s();
                        return ValueRange.i(JapaneseDate.f3177h.M(), s3[s3.length - 1].m().M());
                    case 21:
                        JapaneseEra[] s4 = JapaneseEra.s();
                        int M = (s4[s4.length - 1].m().M() - s4[s4.length - 1].r().M()) + 1;
                        int i3 = Integer.MAX_VALUE;
                        while (i2 < s4.length) {
                            i3 = Math.min(i3, (s4[i2].m().M() - s4[i2].r().M()) + 1);
                            i2++;
                        }
                        return ValueRange.k(1L, 6L, i3, M);
                    case 22:
                        return ValueRange.k(calendar.getMinimum(2) + 1, calendar.getGreatestMinimum(2) + 1, calendar.getLeastMaximum(2) + 1, calendar.getMaximum(2) + 1);
                    case 23:
                        JapaneseEra[] s5 = JapaneseEra.s();
                        int i4 = 366;
                        while (i2 < s5.length) {
                            i4 = Math.min(i4, (s5[i2].r().P() - s5[i2].r().I()) + 1);
                            i2++;
                        }
                        return ValueRange.j(1L, i4, 366L);
                    default:
                        throw new UnsupportedOperationException("Unimplementable field: " + chronoField);
                }
        }
    }
}
