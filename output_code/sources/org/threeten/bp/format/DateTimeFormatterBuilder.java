package org.threeten.bp.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.zone.ZoneRulesProvider;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DateTimeFormatterBuilder {

    /* renamed from: h  reason: collision with root package name */
    private static final TemporalQuery<ZoneId> f3238h = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneId a(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.b(TemporalQueries.g());
            if (zoneId == null || (zoneId instanceof ZoneOffset)) {
                return null;
            }
            return zoneId;
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private static final Map<Character, TemporalField> f3239i;

    /* renamed from: j  reason: collision with root package name */
    static final Comparator<String> f3240j;

    /* renamed from: a  reason: collision with root package name */
    private DateTimeFormatterBuilder f3241a;

    /* renamed from: b  reason: collision with root package name */
    private final DateTimeFormatterBuilder f3242b;

    /* renamed from: c  reason: collision with root package name */
    private final List<DateTimePrinterParser> f3243c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f3244d;

    /* renamed from: e  reason: collision with root package name */
    private int f3245e;

    /* renamed from: f  reason: collision with root package name */
    private char f3246f;

    /* renamed from: g  reason: collision with root package name */
    private int f3247g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.format.DateTimeFormatterBuilder$4  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3250a;

        static {
            int[] iArr = new int[SignStyle.values().length];
            f3250a = iArr;
            try {
                iArr[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3250a[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3250a[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3250a[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class CharLiteralPrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final char f3251d;

        CharLiteralPrinterParser(char c2) {
            this.f3251d = c2;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.f3251d);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            if (i2 == charSequence.length()) {
                return i2 ^ (-1);
            }
            if (!dateTimeParseContext.b(this.f3251d, charSequence.charAt(i2))) {
                return i2 ^ (-1);
            }
            return i2 + 1;
        }

        public String toString() {
            if (this.f3251d == '\'') {
                return "''";
            }
            return "'" + this.f3251d + "'";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class CompositePrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final DateTimePrinterParser[] f3252d;

        /* renamed from: e  reason: collision with root package name */
        private final boolean f3253e;

        CompositePrinterParser(List<DateTimePrinterParser> list, boolean z2) {
            this((DateTimePrinterParser[]) list.toArray(new DateTimePrinterParser[list.size()]), z2);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (this.f3253e) {
                dateTimePrintContext.h();
            }
            try {
                for (DateTimePrinterParser dateTimePrinterParser : this.f3252d) {
                    if (!dateTimePrinterParser.a(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                if (this.f3253e) {
                    dateTimePrintContext.b();
                }
                return true;
            } finally {
                if (this.f3253e) {
                    dateTimePrintContext.b();
                }
            }
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            if (this.f3253e) {
                dateTimeParseContext.r();
                int i3 = i2;
                for (DateTimePrinterParser dateTimePrinterParser : this.f3252d) {
                    i3 = dateTimePrinterParser.b(dateTimeParseContext, charSequence, i3);
                    if (i3 < 0) {
                        dateTimeParseContext.f(false);
                        return i2;
                    }
                }
                dateTimeParseContext.f(true);
                return i3;
            }
            for (DateTimePrinterParser dateTimePrinterParser2 : this.f3252d) {
                i2 = dateTimePrinterParser2.b(dateTimeParseContext, charSequence, i2);
                if (i2 < 0) {
                    break;
                }
            }
            return i2;
        }

        public CompositePrinterParser c(boolean z2) {
            if (z2 == this.f3253e) {
                return this;
            }
            return new CompositePrinterParser(this.f3252d, z2);
        }

        public String toString() {
            String str;
            String str2;
            StringBuilder sb = new StringBuilder();
            if (this.f3252d != null) {
                if (this.f3253e) {
                    str = "[";
                } else {
                    str = "(";
                }
                sb.append(str);
                for (DateTimePrinterParser dateTimePrinterParser : this.f3252d) {
                    sb.append(dateTimePrinterParser);
                }
                if (this.f3253e) {
                    str2 = "]";
                } else {
                    str2 = ")";
                }
                sb.append(str2);
            }
            return sb.toString();
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z2) {
            this.f3252d = dateTimePrinterParserArr;
            this.f3253e = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface DateTimePrinterParser {
        boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb);

        int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class FractionPrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final TemporalField f3254d;

        /* renamed from: e  reason: collision with root package name */
        private final int f3255e;

        /* renamed from: f  reason: collision with root package name */
        private final int f3256f;

        /* renamed from: g  reason: collision with root package name */
        private final boolean f3257g;

        FractionPrinterParser(TemporalField temporalField, int i2, int i3, boolean z2) {
            Jdk8Methods.i(temporalField, "field");
            if (temporalField.f().e()) {
                if (i2 >= 0 && i2 <= 9) {
                    if (i3 >= 1 && i3 <= 9) {
                        if (i3 >= i2) {
                            this.f3254d = temporalField;
                            this.f3255e = i2;
                            this.f3256f = i3;
                            this.f3257g = z2;
                            return;
                        }
                        throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + i3 + " < " + i2);
                    }
                    throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + i3);
                }
                throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + i2);
            }
            throw new IllegalArgumentException("Field must have a fixed set of values: " + temporalField);
        }

        private long c(BigDecimal bigDecimal) {
            ValueRange f2 = this.f3254d.f();
            BigDecimal valueOf = BigDecimal.valueOf(f2.d());
            return bigDecimal.multiply(BigDecimal.valueOf(f2.c()).subtract(valueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(valueOf).longValueExact();
        }

        private BigDecimal d(long j2) {
            ValueRange f2 = this.f3254d.f();
            f2.b(j2, this.f3254d);
            BigDecimal valueOf = BigDecimal.valueOf(f2.d());
            BigDecimal divide = BigDecimal.valueOf(j2).subtract(valueOf).divide(BigDecimal.valueOf(f2.c()).subtract(valueOf).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            if (divide.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            }
            return divide.stripTrailingZeros();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long f2 = dateTimePrintContext.f(this.f3254d);
            if (f2 == null) {
                return false;
            }
            DecimalStyle d2 = dateTimePrintContext.d();
            BigDecimal d3 = d(f2.longValue());
            if (d3.scale() == 0) {
                if (this.f3255e > 0) {
                    if (this.f3257g) {
                        sb.append(d2.c());
                    }
                    for (int i2 = 0; i2 < this.f3255e; i2++) {
                        sb.append(d2.f());
                    }
                    return true;
                }
                return true;
            }
            String a2 = d2.a(d3.setScale(Math.min(Math.max(d3.scale(), this.f3255e), this.f3256f), RoundingMode.FLOOR).toPlainString().substring(2));
            if (this.f3257g) {
                sb.append(d2.c());
            }
            sb.append(a2);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            int i3;
            int i4;
            int i5;
            int i6 = 0;
            if (dateTimeParseContext.l()) {
                i3 = this.f3255e;
            } else {
                i3 = 0;
            }
            if (dateTimeParseContext.l()) {
                i4 = this.f3256f;
            } else {
                i4 = 9;
            }
            int length = charSequence.length();
            if (i2 == length) {
                if (i3 > 0) {
                    return i2 ^ (-1);
                }
                return i2;
            }
            if (this.f3257g) {
                if (charSequence.charAt(i2) != dateTimeParseContext.j().c()) {
                    if (i3 > 0) {
                        return i2 ^ (-1);
                    }
                    return i2;
                }
                i2++;
            }
            int i7 = i2;
            int i8 = i3 + i7;
            if (i8 > length) {
                return i7 ^ (-1);
            }
            int min = Math.min(i4 + i7, length);
            int i9 = i7;
            while (true) {
                if (i9 < min) {
                    int i10 = i9 + 1;
                    int b2 = dateTimeParseContext.j().b(charSequence.charAt(i9));
                    if (b2 < 0) {
                        if (i10 < i8) {
                            return i7 ^ (-1);
                        }
                        i5 = i10 - 1;
                    } else {
                        i6 = (i6 * 10) + b2;
                        i9 = i10;
                    }
                } else {
                    i5 = i9;
                    break;
                }
            }
            return dateTimeParseContext.o(this.f3254d, c(new BigDecimal(i6).movePointLeft(i5 - i7)), i7, i5);
        }

        public String toString() {
            String str;
            if (this.f3257g) {
                str = ",DecimalPoint";
            } else {
                str = "";
            }
            return "Fraction(" + this.f3254d + "," + this.f3255e + "," + this.f3256f + str + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class InstantPrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final int f3258d;

        InstantPrinterParser(int i2) {
            this.f3258d = i2;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long f2 = dateTimePrintContext.f(ChronoField.INSTANT_SECONDS);
            Long l2 = 0L;
            TemporalAccessor e2 = dateTimePrintContext.e();
            ChronoField chronoField = ChronoField.NANO_OF_SECOND;
            if (e2.d(chronoField)) {
                l2 = Long.valueOf(dateTimePrintContext.e().h(chronoField));
            }
            int i2 = 0;
            if (f2 == null) {
                return false;
            }
            long longValue = f2.longValue();
            int g2 = chronoField.g(l2.longValue());
            if (longValue >= -62167219200L) {
                long j2 = (longValue - 315569520000L) + 62167219200L;
                long e3 = Jdk8Methods.e(j2, 315569520000L) + 1;
                LocalDateTime Q = LocalDateTime.Q(Jdk8Methods.h(j2, 315569520000L) - 62167219200L, 0, ZoneOffset.f3108k);
                if (e3 > 0) {
                    sb.append('+');
                    sb.append(e3);
                }
                sb.append(Q);
                if (Q.J() == 0) {
                    sb.append(":00");
                }
            } else {
                long j3 = longValue + 62167219200L;
                long j4 = j3 / 315569520000L;
                long j5 = j3 % 315569520000L;
                LocalDateTime Q2 = LocalDateTime.Q(j5 - 62167219200L, 0, ZoneOffset.f3108k);
                int length = sb.length();
                sb.append(Q2);
                if (Q2.J() == 0) {
                    sb.append(":00");
                }
                if (j4 < 0) {
                    if (Q2.K() == -10000) {
                        sb.replace(length, length + 2, Long.toString(j4 - 1));
                    } else if (j5 == 0) {
                        sb.insert(length, j4);
                    } else {
                        sb.insert(length + 1, Math.abs(j4));
                    }
                }
            }
            int i3 = this.f3258d;
            if (i3 == -2) {
                if (g2 != 0) {
                    sb.append('.');
                    if (g2 % 1000000 == 0) {
                        sb.append(Integer.toString((g2 / 1000000) + 1000).substring(1));
                    } else if (g2 % 1000 == 0) {
                        sb.append(Integer.toString((g2 / 1000) + 1000000).substring(1));
                    } else {
                        sb.append(Integer.toString(g2 + 1000000000).substring(1));
                    }
                }
            } else if (i3 > 0 || (i3 == -1 && g2 > 0)) {
                sb.append('.');
                int i4 = 100000000;
                while (true) {
                    int i5 = this.f3258d;
                    if ((i5 != -1 || g2 <= 0) && i2 >= i5) {
                        break;
                    }
                    int i6 = g2 / i4;
                    sb.append((char) (i6 + 48));
                    g2 -= i6 * i4;
                    i4 /= 10;
                    i2++;
                }
            }
            sb.append('Z');
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            DateTimeParseContext d2 = dateTimeParseContext.d();
            int i8 = this.f3258d;
            int i9 = 0;
            if (i8 < 0) {
                i3 = 0;
            } else {
                i3 = i8;
            }
            if (i8 < 0) {
                i8 = 9;
            }
            DateTimeFormatterBuilder e2 = new DateTimeFormatterBuilder().a(DateTimeFormatter.f3214h).e('T');
            ChronoField chronoField = ChronoField.HOUR_OF_DAY;
            DateTimeFormatterBuilder e3 = e2.k(chronoField, 2).e(':');
            ChronoField chronoField2 = ChronoField.MINUTE_OF_HOUR;
            DateTimeFormatterBuilder e4 = e3.k(chronoField2, 2).e(':');
            ChronoField chronoField3 = ChronoField.SECOND_OF_MINUTE;
            DateTimeFormatterBuilder k2 = e4.k(chronoField3, 2);
            ChronoField chronoField4 = ChronoField.NANO_OF_SECOND;
            int b2 = k2.b(chronoField4, i3, i8, true).e('Z').s().k(false).b(d2, charSequence, i2);
            if (b2 < 0) {
                return b2;
            }
            long longValue = d2.i(ChronoField.YEAR).longValue();
            int intValue = d2.i(ChronoField.MONTH_OF_YEAR).intValue();
            int intValue2 = d2.i(ChronoField.DAY_OF_MONTH).intValue();
            int intValue3 = d2.i(chronoField).intValue();
            int intValue4 = d2.i(chronoField2).intValue();
            Long i10 = d2.i(chronoField3);
            Long i11 = d2.i(chronoField4);
            if (i10 != null) {
                i4 = i10.intValue();
            } else {
                i4 = 0;
            }
            if (i11 != null) {
                i5 = i11.intValue();
            } else {
                i5 = 0;
            }
            int i12 = ((int) longValue) % 10000;
            if (intValue3 == 24 && intValue4 == 0 && i4 == 0 && i5 == 0) {
                i7 = i4;
                i9 = 1;
                i6 = 0;
            } else if (intValue3 == 23 && intValue4 == 59 && i4 == 60) {
                dateTimeParseContext.p();
                i6 = intValue3;
                i7 = 59;
            } else {
                i6 = intValue3;
                i7 = i4;
            }
            try {
                return dateTimeParseContext.o(chronoField4, i5, i2, dateTimeParseContext.o(ChronoField.INSTANT_SECONDS, Jdk8Methods.m(longValue / 10000, 315569520000L) + LocalDateTime.O(i12, intValue, intValue2, i6, intValue4, i7, 0).S(i9).u(ZoneOffset.f3108k), i2, b2));
            } catch (RuntimeException unused) {
                return i2 ^ (-1);
            }
        }

        public String toString() {
            return "Instant()";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class OffsetIdPrinterParser implements DateTimePrinterParser {

        /* renamed from: f  reason: collision with root package name */
        static final String[] f3265f = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};

        /* renamed from: g  reason: collision with root package name */
        static final OffsetIdPrinterParser f3266g = new OffsetIdPrinterParser("Z", "+HH:MM:ss");

        /* renamed from: h  reason: collision with root package name */
        static final OffsetIdPrinterParser f3267h = new OffsetIdPrinterParser("0", "+HH:MM:ss");

        /* renamed from: d  reason: collision with root package name */
        private final String f3268d;

        /* renamed from: e  reason: collision with root package name */
        private final int f3269e;

        OffsetIdPrinterParser(String str, String str2) {
            Jdk8Methods.i(str, "noOffsetText");
            Jdk8Methods.i(str2, "pattern");
            this.f3268d = str;
            this.f3269e = c(str2);
        }

        private int c(String str) {
            int i2 = 0;
            while (true) {
                String[] strArr = f3265f;
                if (i2 < strArr.length) {
                    if (strArr[i2].equals(str)) {
                        return i2;
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException("Invalid zone offset pattern: " + str);
                }
            }
        }

        private boolean d(int[] iArr, int i2, CharSequence charSequence, boolean z2) {
            int i3;
            int i4 = this.f3269e;
            if ((i4 + 3) / 2 < i2) {
                return false;
            }
            int i5 = iArr[0];
            if (i4 % 2 == 0 && i2 > 1) {
                int i6 = i5 + 1;
                if (i6 <= charSequence.length() && charSequence.charAt(i5) == ':') {
                    i5 = i6;
                } else {
                    return z2;
                }
            }
            if (i5 + 2 > charSequence.length()) {
                return z2;
            }
            int i7 = i5 + 1;
            char charAt = charSequence.charAt(i5);
            int i8 = i7 + 1;
            char charAt2 = charSequence.charAt(i7);
            if (charAt >= '0' && charAt <= '9' && charAt2 >= '0' && charAt2 <= '9' && (i3 = ((charAt - '0') * 10) + (charAt2 - '0')) >= 0 && i3 <= 59) {
                iArr[i2] = i3;
                iArr[0] = i8;
                return false;
            }
            return z2;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            String str;
            String str2;
            Long f2 = dateTimePrintContext.f(ChronoField.OFFSET_SECONDS);
            if (f2 == null) {
                return false;
            }
            int p2 = Jdk8Methods.p(f2.longValue());
            if (p2 == 0) {
                sb.append(this.f3268d);
            } else {
                int abs = Math.abs((p2 / 3600) % 100);
                int abs2 = Math.abs((p2 / 60) % 60);
                int abs3 = Math.abs(p2 % 60);
                int length = sb.length();
                if (p2 < 0) {
                    str = "-";
                } else {
                    str = "+";
                }
                sb.append(str);
                sb.append((char) ((abs / 10) + 48));
                sb.append((char) ((abs % 10) + 48));
                int i2 = this.f3269e;
                if (i2 >= 3 || (i2 >= 1 && abs2 > 0)) {
                    String str3 = ":";
                    if (i2 % 2 != 0) {
                        str2 = "";
                    } else {
                        str2 = str3;
                    }
                    sb.append(str2);
                    sb.append((char) ((abs2 / 10) + 48));
                    sb.append((char) ((abs2 % 10) + 48));
                    abs += abs2;
                    int i3 = this.f3269e;
                    if (i3 >= 7 || (i3 >= 5 && abs3 > 0)) {
                        if (i3 % 2 != 0) {
                            str3 = "";
                        }
                        sb.append(str3);
                        sb.append((char) ((abs3 / 10) + 48));
                        sb.append((char) ((abs3 % 10) + 48));
                        abs += abs3;
                    }
                }
                if (abs == 0) {
                    sb.setLength(length);
                    sb.append(this.f3268d);
                }
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x007f  */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int b(org.threeten.bp.format.DateTimeParseContext r15, java.lang.CharSequence r16, int r17) {
            /*
                r14 = this;
                r0 = r14
                r7 = r16
                r8 = r17
                int r1 = r16.length()
                java.lang.String r2 = r0.f3268d
                int r9 = r2.length()
                r10 = -1
                if (r9 != 0) goto L22
                if (r8 != r1) goto L44
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                r1 = r15
                r5 = r17
                r6 = r17
                int r1 = r1.o(r2, r3, r5, r6)
                return r1
            L22:
                if (r8 != r1) goto L27
                r1 = r8 ^ (-1)
                return r1
            L27:
                java.lang.String r4 = r0.f3268d
                r5 = 0
                r1 = r15
                r2 = r16
                r3 = r17
                r6 = r9
                boolean r1 = r1.s(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L44
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r15
                r5 = r17
                int r1 = r1.o(r2, r3, r5, r6)
                return r1
            L44:
                char r1 = r16.charAt(r17)
                r2 = 43
                r3 = 45
                if (r1 == r2) goto L50
                if (r1 != r3) goto La3
            L50:
                r2 = 1
                if (r1 != r3) goto L55
                r1 = -1
                goto L56
            L55:
                r1 = 1
            L56:
                r3 = 4
                int[] r3 = new int[r3]
                int r4 = r8 + 1
                r5 = 0
                r3[r5] = r4
                boolean r4 = r14.d(r3, r2, r7, r2)
                r6 = 2
                r11 = 3
                if (r4 != 0) goto L7c
                int r4 = r0.f3269e
                if (r4 < r11) goto L6c
                r4 = 1
                goto L6d
            L6c:
                r4 = 0
            L6d:
                boolean r4 = r14.d(r3, r6, r7, r4)
                if (r4 != 0) goto L7c
                boolean r4 = r14.d(r3, r11, r7, r5)
                if (r4 == 0) goto L7a
                goto L7c
            L7a:
                r4 = 0
                goto L7d
            L7c:
                r4 = 1
            L7d:
                if (r4 != 0) goto La3
                long r9 = (long) r1
                r1 = r3[r2]
                long r1 = (long) r1
                r12 = 3600(0xe10, double:1.7786E-320)
                long r1 = r1 * r12
                r4 = r3[r6]
                long r6 = (long) r4
                r12 = 60
                long r6 = r6 * r12
                long r1 = r1 + r6
                r4 = r3[r11]
                long r6 = (long) r4
                long r1 = r1 + r6
                long r6 = r9 * r1
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r9 = r3[r5]
                r1 = r15
                r3 = r6
                r5 = r17
                r6 = r9
                int r1 = r1.o(r2, r3, r5, r6)
                return r1
            La3:
                if (r9 != 0) goto Lb3
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r15
                r5 = r17
                int r1 = r1.o(r2, r3, r5, r6)
                return r1
            Lb3:
                r1 = r8 ^ (-1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.OffsetIdPrinterParser.b(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        public String toString() {
            String replace = this.f3268d.replace("'", "''");
            return "Offset(" + f3265f[this.f3269e] + ",'" + replace + "')";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class PadPrinterParserDecorator implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final DateTimePrinterParser f3270d;

        /* renamed from: e  reason: collision with root package name */
        private final int f3271e;

        /* renamed from: f  reason: collision with root package name */
        private final char f3272f;

        PadPrinterParserDecorator(DateTimePrinterParser dateTimePrinterParser, int i2, char c2) {
            this.f3270d = dateTimePrinterParser;
            this.f3271e = i2;
            this.f3272f = c2;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (!this.f3270d.a(dateTimePrintContext, sb)) {
                return false;
            }
            int length2 = sb.length() - length;
            if (length2 <= this.f3271e) {
                for (int i2 = 0; i2 < this.f3271e - length2; i2++) {
                    sb.insert(length, this.f3272f);
                }
                return true;
            }
            throw new DateTimeException("Cannot print as output of " + length2 + " characters exceeds pad width of " + this.f3271e);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            boolean l2 = dateTimeParseContext.l();
            boolean k2 = dateTimeParseContext.k();
            if (i2 <= charSequence.length()) {
                if (i2 == charSequence.length()) {
                    return i2 ^ (-1);
                }
                int i3 = this.f3271e + i2;
                if (i3 > charSequence.length()) {
                    if (l2) {
                        return i2 ^ (-1);
                    }
                    i3 = charSequence.length();
                }
                int i4 = i2;
                while (i4 < i3) {
                    if (k2) {
                        if (charSequence.charAt(i4) != this.f3272f) {
                            break;
                        }
                        i4++;
                    } else if (!dateTimeParseContext.b(charSequence.charAt(i4), this.f3272f)) {
                        break;
                    } else {
                        i4++;
                    }
                }
                int b2 = this.f3270d.b(dateTimeParseContext, charSequence.subSequence(0, i3), i4);
                if (b2 != i3 && l2) {
                    return (i2 + i4) ^ (-1);
                }
                return b2;
            }
            throw new IndexOutOfBoundsException();
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("Pad(");
            sb.append(this.f3270d);
            sb.append(",");
            sb.append(this.f3271e);
            if (this.f3272f == ' ') {
                str = ")";
            } else {
                str = ",'" + this.f3272f + "')";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            dateTimeParseContext.q(false);
                        }
                    } else {
                        dateTimeParseContext.q(true);
                    }
                } else {
                    dateTimeParseContext.m(false);
                }
            } else {
                dateTimeParseContext.m(true);
            }
            return i2;
        }

        @Override // java.lang.Enum
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            return "ParseStrict(false)";
                        }
                        throw new IllegalStateException("Unreachable");
                    }
                    return "ParseStrict(true)";
                }
                return "ParseCaseSensitive(false)";
            }
            return "ParseCaseSensitive(true)";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class StringLiteralPrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final String f3278d;

        StringLiteralPrinterParser(String str) {
            this.f3278d = str;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.f3278d);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            if (i2 <= charSequence.length() && i2 >= 0) {
                String str = this.f3278d;
                if (!dateTimeParseContext.s(charSequence, i2, str, 0, str.length())) {
                    return i2 ^ (-1);
                }
                return i2 + this.f3278d.length();
            }
            throw new IndexOutOfBoundsException();
        }

        public String toString() {
            String replace = this.f3278d.replace("'", "''");
            return "'" + replace + "'";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class TextPrinterParser implements DateTimePrinterParser {

        /* renamed from: d  reason: collision with root package name */
        private final TemporalField f3279d;

        /* renamed from: e  reason: collision with root package name */
        private final TextStyle f3280e;

        /* renamed from: f  reason: collision with root package name */
        private final DateTimeTextProvider f3281f;

        /* renamed from: g  reason: collision with root package name */
        private volatile NumberPrinterParser f3282g;

        TextPrinterParser(TemporalField temporalField, TextStyle textStyle, DateTimeTextProvider dateTimeTextProvider) {
            this.f3279d = temporalField;
            this.f3280e = textStyle;
            this.f3281f = dateTimeTextProvider;
        }

        private NumberPrinterParser c() {
            if (this.f3282g == null) {
                this.f3282g = new NumberPrinterParser(this.f3279d, 1, 19, SignStyle.NORMAL);
            }
            return this.f3282g;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long f2 = dateTimePrintContext.f(this.f3279d);
            if (f2 == null) {
                return false;
            }
            String a2 = this.f3281f.a(this.f3279d, f2.longValue(), this.f3280e, dateTimePrintContext.c());
            if (a2 == null) {
                return c().a(dateTimePrintContext, sb);
            }
            sb.append(a2);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            TextStyle textStyle;
            int length = charSequence.length();
            if (i2 >= 0 && i2 <= length) {
                if (dateTimeParseContext.l()) {
                    textStyle = this.f3280e;
                } else {
                    textStyle = null;
                }
                Iterator<Map.Entry<String, Long>> b2 = this.f3281f.b(this.f3279d, textStyle, dateTimeParseContext.h());
                if (b2 != null) {
                    while (b2.hasNext()) {
                        Map.Entry<String, Long> next = b2.next();
                        String key = next.getKey();
                        if (dateTimeParseContext.s(key, 0, charSequence, i2, key.length())) {
                            return dateTimeParseContext.o(this.f3279d, next.getValue().longValue(), i2, i2 + key.length());
                        }
                    }
                    if (dateTimeParseContext.l()) {
                        return i2 ^ (-1);
                    }
                }
                return c().b(dateTimeParseContext, charSequence, i2);
            }
            throw new IndexOutOfBoundsException();
        }

        public String toString() {
            if (this.f3280e == TextStyle.FULL) {
                return "Text(" + this.f3279d + ")";
            }
            return "Text(" + this.f3279d + "," + this.f3280e + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class ZoneIdPrinterParser implements DateTimePrinterParser {

        /* renamed from: f  reason: collision with root package name */
        private static volatile Map.Entry<Integer, SubstringTree> f3283f;

        /* renamed from: d  reason: collision with root package name */
        private final TemporalQuery<ZoneId> f3284d;

        /* renamed from: e  reason: collision with root package name */
        private final String f3285e;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public static final class SubstringTree {

            /* renamed from: a  reason: collision with root package name */
            final int f3286a;

            /* renamed from: b  reason: collision with root package name */
            private final Map<CharSequence, SubstringTree> f3287b;

            /* renamed from: c  reason: collision with root package name */
            private final Map<String, SubstringTree> f3288c;

            /* JADX INFO: Access modifiers changed from: private */
            public void c(String str) {
                int length = str.length();
                int i2 = this.f3286a;
                if (length == i2) {
                    this.f3287b.put(str, null);
                    this.f3288c.put(str.toLowerCase(Locale.ENGLISH), null);
                } else if (length > i2) {
                    String substring = str.substring(0, i2);
                    SubstringTree substringTree = this.f3287b.get(substring);
                    if (substringTree == null) {
                        substringTree = new SubstringTree(length);
                        this.f3287b.put(substring, substringTree);
                        this.f3288c.put(substring.toLowerCase(Locale.ENGLISH), substringTree);
                    }
                    substringTree.c(str);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public SubstringTree d(CharSequence charSequence, boolean z2) {
                if (z2) {
                    return this.f3287b.get(charSequence);
                }
                return this.f3288c.get(charSequence.toString().toLowerCase(Locale.ENGLISH));
            }

            private SubstringTree(int i2) {
                this.f3287b = new HashMap();
                this.f3288c = new HashMap();
                this.f3286a = i2;
            }
        }

        ZoneIdPrinterParser(TemporalQuery<ZoneId> temporalQuery, String str) {
            this.f3284d = temporalQuery;
            this.f3285e = str;
        }

        private ZoneId c(Set<String> set, String str, boolean z2) {
            if (str == null) {
                return null;
            }
            if (z2) {
                if (!set.contains(str)) {
                    return null;
                }
                return ZoneId.p(str);
            }
            for (String str2 : set) {
                if (str2.equalsIgnoreCase(str)) {
                    return ZoneId.p(str2);
                }
            }
            return null;
        }

        private int d(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2, int i3) {
            String upperCase = charSequence.subSequence(i2, i3).toString().toUpperCase();
            DateTimeParseContext d2 = dateTimeParseContext.d();
            if (i3 < charSequence.length() && dateTimeParseContext.b(charSequence.charAt(i3), 'Z')) {
                dateTimeParseContext.n(ZoneId.r(upperCase, ZoneOffset.f3108k));
                return i3;
            }
            int b2 = OffsetIdPrinterParser.f3266g.b(d2, charSequence, i3);
            if (b2 < 0) {
                dateTimeParseContext.n(ZoneId.r(upperCase, ZoneOffset.f3108k));
                return i3;
            }
            dateTimeParseContext.n(ZoneId.r(upperCase, ZoneOffset.A((int) d2.i(ChronoField.OFFSET_SECONDS).longValue())));
            return b2;
        }

        private static SubstringTree e(Set<String> set) {
            ArrayList<String> arrayList = new ArrayList(set);
            Collections.sort(arrayList, DateTimeFormatterBuilder.f3240j);
            SubstringTree substringTree = new SubstringTree(((String) arrayList.get(0)).length());
            for (String str : arrayList) {
                substringTree.c(str);
            }
            return substringTree;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.g(this.f3284d);
            if (zoneId == null) {
                return false;
            }
            sb.append(zoneId.m());
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int b(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i2) {
            int i3;
            int length = charSequence.length();
            if (i2 <= length) {
                if (i2 == length) {
                    return i2 ^ (-1);
                }
                char charAt = charSequence.charAt(i2);
                if (charAt != '+' && charAt != '-') {
                    int i4 = i2 + 2;
                    if (length >= i4) {
                        char charAt2 = charSequence.charAt(i2 + 1);
                        if (dateTimeParseContext.b(charAt, 'U') && dateTimeParseContext.b(charAt2, 'T')) {
                            int i5 = i2 + 3;
                            if (length >= i5 && dateTimeParseContext.b(charSequence.charAt(i4), 'C')) {
                                return d(dateTimeParseContext, charSequence, i2, i5);
                            }
                            return d(dateTimeParseContext, charSequence, i2, i4);
                        } else if (dateTimeParseContext.b(charAt, 'G') && length >= (i3 = i2 + 3) && dateTimeParseContext.b(charAt2, 'M') && dateTimeParseContext.b(charSequence.charAt(i4), 'T')) {
                            return d(dateTimeParseContext, charSequence, i2, i3);
                        }
                    }
                    Set<String> a2 = ZoneRulesProvider.a();
                    int size = a2.size();
                    Map.Entry<Integer, SubstringTree> entry = f3283f;
                    if (entry == null || entry.getKey().intValue() != size) {
                        synchronized (this) {
                            entry = f3283f;
                            if (entry == null || entry.getKey().intValue() != size) {
                                entry = new AbstractMap.SimpleImmutableEntry<>(Integer.valueOf(size), e(a2));
                                f3283f = entry;
                            }
                        }
                    }
                    SubstringTree value = entry.getValue();
                    String str = null;
                    String str2 = null;
                    while (value != null) {
                        int i6 = value.f3286a + i2;
                        if (i6 > length) {
                            break;
                        }
                        String charSequence2 = charSequence.subSequence(i2, i6).toString();
                        value = value.d(charSequence2, dateTimeParseContext.k());
                        str2 = str;
                        str = charSequence2;
                    }
                    ZoneId c2 = c(a2, str, dateTimeParseContext.k());
                    if (c2 == null) {
                        c2 = c(a2, str2, dateTimeParseContext.k());
                        if (c2 == null) {
                            if (dateTimeParseContext.b(charAt, 'Z')) {
                                dateTimeParseContext.n(ZoneOffset.f3108k);
                                return i2 + 1;
                            }
                            return i2 ^ (-1);
                        }
                        str = str2;
                    }
                    dateTimeParseContext.n(c2);
                    return i2 + str.length();
                }
                DateTimeParseContext d2 = dateTimeParseContext.d();
                int b2 = OffsetIdPrinterParser.f3266g.b(d2, charSequence, i2);
                if (b2 < 0) {
                    return b2;
                }
                dateTimeParseContext.n(ZoneOffset.A((int) d2.i(ChronoField.OFFSET_SECONDS).longValue()));
                return b2;
            }
            throw new IndexOutOfBoundsException();
        }

        public String toString() {
            return this.f3285e;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f3239i = hashMap;
        hashMap.put('G', ChronoField.ERA);
        hashMap.put('y', ChronoField.YEAR_OF_ERA);
        hashMap.put('u', ChronoField.YEAR);
        TemporalField temporalField = IsoFields.f3382b;
        hashMap.put('Q', temporalField);
        hashMap.put('q', temporalField);
        ChronoField chronoField = ChronoField.MONTH_OF_YEAR;
        hashMap.put('M', chronoField);
        hashMap.put('L', chronoField);
        hashMap.put('D', ChronoField.DAY_OF_YEAR);
        hashMap.put('d', ChronoField.DAY_OF_MONTH);
        hashMap.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        ChronoField chronoField2 = ChronoField.DAY_OF_WEEK;
        hashMap.put('E', chronoField2);
        hashMap.put('c', chronoField2);
        hashMap.put('e', chronoField2);
        hashMap.put('a', ChronoField.AMPM_OF_DAY);
        hashMap.put('H', ChronoField.HOUR_OF_DAY);
        hashMap.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        hashMap.put('K', ChronoField.HOUR_OF_AMPM);
        hashMap.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        hashMap.put('m', ChronoField.MINUTE_OF_HOUR);
        hashMap.put('s', ChronoField.SECOND_OF_MINUTE);
        ChronoField chronoField3 = ChronoField.NANO_OF_SECOND;
        hashMap.put('S', chronoField3);
        hashMap.put('A', ChronoField.MILLI_OF_DAY);
        hashMap.put('n', chronoField3);
        hashMap.put('N', ChronoField.NANO_OF_DAY);
        f3240j = new Comparator<String>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.3
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.length() == str2.length() ? str.compareTo(str2) : str.length() - str2.length();
            }
        };
    }

    public DateTimeFormatterBuilder() {
        this.f3241a = this;
        this.f3243c = new ArrayList();
        this.f3247g = -1;
        this.f3242b = null;
        this.f3244d = false;
    }

    private int d(DateTimePrinterParser dateTimePrinterParser) {
        Jdk8Methods.i(dateTimePrinterParser, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f3241a;
        int i2 = dateTimeFormatterBuilder.f3245e;
        if (i2 > 0) {
            if (dateTimePrinterParser != null) {
                dateTimePrinterParser = new PadPrinterParserDecorator(dateTimePrinterParser, i2, dateTimeFormatterBuilder.f3246f);
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.f3241a;
            dateTimeFormatterBuilder2.f3245e = 0;
            dateTimeFormatterBuilder2.f3246f = (char) 0;
        }
        this.f3241a.f3243c.add(dateTimePrinterParser);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = this.f3241a;
        dateTimeFormatterBuilder3.f3247g = -1;
        return dateTimeFormatterBuilder3.f3243c.size() - 1;
    }

    private DateTimeFormatterBuilder j(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser f2;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f3241a;
        int i2 = dateTimeFormatterBuilder.f3247g;
        if (i2 >= 0 && (dateTimeFormatterBuilder.f3243c.get(i2) instanceof NumberPrinterParser)) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.f3241a;
            int i3 = dateTimeFormatterBuilder2.f3247g;
            NumberPrinterParser numberPrinterParser2 = (NumberPrinterParser) dateTimeFormatterBuilder2.f3243c.get(i3);
            int i4 = numberPrinterParser.f3261e;
            int i5 = numberPrinterParser.f3262f;
            if (i4 == i5 && numberPrinterParser.f3263g == SignStyle.NOT_NEGATIVE) {
                f2 = numberPrinterParser2.g(i5);
                d(numberPrinterParser.f());
                this.f3241a.f3247g = i3;
            } else {
                f2 = numberPrinterParser2.f();
                this.f3241a.f3247g = d(numberPrinterParser);
            }
            this.f3241a.f3243c.set(i3, f2);
        } else {
            this.f3241a.f3247g = d(numberPrinterParser);
        }
        return this;
    }

    public DateTimeFormatterBuilder a(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.i(dateTimeFormatter, "formatter");
        d(dateTimeFormatter.k(false));
        return this;
    }

    public DateTimeFormatterBuilder b(TemporalField temporalField, int i2, int i3, boolean z2) {
        d(new FractionPrinterParser(temporalField, i2, i3, z2));
        return this;
    }

    public DateTimeFormatterBuilder c() {
        d(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder e(char c2) {
        d(new CharLiteralPrinterParser(c2));
        return this;
    }

    public DateTimeFormatterBuilder f(String str) {
        Jdk8Methods.i(str, "literal");
        if (str.length() > 0) {
            if (str.length() == 1) {
                d(new CharLiteralPrinterParser(str.charAt(0)));
            } else {
                d(new StringLiteralPrinterParser(str));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder g(String str, String str2) {
        d(new OffsetIdPrinterParser(str2, str));
        return this;
    }

    public DateTimeFormatterBuilder h() {
        d(OffsetIdPrinterParser.f3266g);
        return this;
    }

    public DateTimeFormatterBuilder i(TemporalField temporalField, Map<Long, String> map) {
        Jdk8Methods.i(temporalField, "field");
        Jdk8Methods.i(map, "textLookup");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        TextStyle textStyle = TextStyle.FULL;
        final SimpleDateTimeTextProvider.LocaleStore localeStore = new SimpleDateTimeTextProvider.LocaleStore(Collections.singletonMap(textStyle, linkedHashMap));
        d(new TextPrinterParser(temporalField, textStyle, new DateTimeTextProvider() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.2
            @Override // org.threeten.bp.format.DateTimeTextProvider
            public String a(TemporalField temporalField2, long j2, TextStyle textStyle2, Locale locale) {
                return localeStore.a(j2, textStyle2);
            }

            @Override // org.threeten.bp.format.DateTimeTextProvider
            public Iterator<Map.Entry<String, Long>> b(TemporalField temporalField2, TextStyle textStyle2, Locale locale) {
                return localeStore.b(textStyle2);
            }
        }));
        return this;
    }

    public DateTimeFormatterBuilder k(TemporalField temporalField, int i2) {
        Jdk8Methods.i(temporalField, "field");
        if (i2 >= 1 && i2 <= 19) {
            j(new NumberPrinterParser(temporalField, i2, i2, SignStyle.NOT_NEGATIVE));
            return this;
        }
        throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i2);
    }

    public DateTimeFormatterBuilder l(TemporalField temporalField, int i2, int i3, SignStyle signStyle) {
        if (i2 == i3 && signStyle == SignStyle.NOT_NEGATIVE) {
            return k(temporalField, i3);
        }
        Jdk8Methods.i(temporalField, "field");
        Jdk8Methods.i(signStyle, "signStyle");
        if (i2 >= 1 && i2 <= 19) {
            if (i3 >= 1 && i3 <= 19) {
                if (i3 >= i2) {
                    j(new NumberPrinterParser(temporalField, i2, i3, signStyle));
                    return this;
                }
                throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i3 + " < " + i2);
            }
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i3);
        }
        throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i2);
    }

    public DateTimeFormatterBuilder m() {
        d(new ZoneIdPrinterParser(f3238h, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder n() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f3241a;
        if (dateTimeFormatterBuilder.f3242b != null) {
            if (dateTimeFormatterBuilder.f3243c.size() > 0) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.f3241a;
                CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.f3243c, dateTimeFormatterBuilder2.f3244d);
                this.f3241a = this.f3241a.f3242b;
                d(compositePrinterParser);
            } else {
                this.f3241a = this.f3241a.f3242b;
            }
            return this;
        }
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    public DateTimeFormatterBuilder o() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f3241a;
        dateTimeFormatterBuilder.f3247g = -1;
        this.f3241a = new DateTimeFormatterBuilder(dateTimeFormatterBuilder, true);
        return this;
    }

    public DateTimeFormatterBuilder p() {
        d(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder q() {
        d(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder r() {
        d(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatter s() {
        return t(Locale.getDefault());
    }

    public DateTimeFormatter t(Locale locale) {
        Jdk8Methods.i(locale, "locale");
        while (this.f3241a.f3242b != null) {
            n();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.f3243c, false), locale, DecimalStyle.f3313e, ResolverStyle.SMART, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeFormatter u(ResolverStyle resolverStyle) {
        return s().m(resolverStyle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class NumberPrinterParser implements DateTimePrinterParser {

        /* renamed from: i  reason: collision with root package name */
        static final int[] f3259i = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

        /* renamed from: d  reason: collision with root package name */
        final TemporalField f3260d;

        /* renamed from: e  reason: collision with root package name */
        final int f3261e;

        /* renamed from: f  reason: collision with root package name */
        final int f3262f;

        /* renamed from: g  reason: collision with root package name */
        final SignStyle f3263g;

        /* renamed from: h  reason: collision with root package name */
        final int f3264h;

        NumberPrinterParser(TemporalField temporalField, int i2, int i3, SignStyle signStyle) {
            this.f3260d = temporalField;
            this.f3261e = i2;
            this.f3262f = i3;
            this.f3263g = signStyle;
            this.f3264h = 0;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean a(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            String l2;
            Long f2 = dateTimePrintContext.f(this.f3260d);
            if (f2 == null) {
                return false;
            }
            long c2 = c(dateTimePrintContext, f2.longValue());
            DecimalStyle d2 = dateTimePrintContext.d();
            if (c2 == Long.MIN_VALUE) {
                l2 = "9223372036854775808";
            } else {
                l2 = Long.toString(Math.abs(c2));
            }
            if (l2.length() <= this.f3262f) {
                String a2 = d2.a(l2);
                if (c2 >= 0) {
                    int i2 = AnonymousClass4.f3250a[this.f3263g.ordinal()];
                    if (i2 != 1) {
                        if (i2 == 2) {
                            sb.append(d2.e());
                        }
                    } else {
                        int i3 = this.f3261e;
                        if (i3 < 19 && c2 >= f3259i[i3]) {
                            sb.append(d2.e());
                        }
                    }
                } else {
                    int i4 = AnonymousClass4.f3250a[this.f3263g.ordinal()];
                    if (i4 != 1 && i4 != 2 && i4 != 3) {
                        if (i4 == 4) {
                            throw new DateTimeException("Field " + this.f3260d + " cannot be printed as the value " + c2 + " cannot be negative according to the SignStyle");
                        }
                    } else {
                        sb.append(d2.d());
                    }
                }
                for (int i5 = 0; i5 < this.f3261e - a2.length(); i5++) {
                    sb.append(d2.f());
                }
                sb.append(a2);
                return true;
            }
            throw new DateTimeException("Field " + this.f3260d + " cannot be printed as the value " + c2 + " exceeds the maximum print width of " + this.f3262f);
        }

        /* JADX WARN: Code restructure failed: missing block: B:100:0x0152, code lost:
            if (r2 == false) goto L90;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x0156, code lost:
            if (r0 > r19.f3261e) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:104:0x015c, code lost:
            return (r7 - 1) ^ (-1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:106:0x015f, code lost:
            if (r0 <= r19.f3261e) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:108:0x0163, code lost:
            return r7 ^ (-1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:109:0x0164, code lost:
            r2 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:110:0x0165, code lost:
            if (r13 == null) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x016d, code lost:
            if (r13.bitLength() <= 63) goto L69;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x016f, code lost:
            r13 = r13.divide(java.math.BigInteger.TEN);
            r5 = r5 - 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:115:0x0184, code lost:
            return e(r20, r13.longValue(), r7, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:117:0x018e, code lost:
            return e(r20, r2, r7, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x010f, code lost:
            r5 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0114, code lost:
            if (r0 == false) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0116, code lost:
            if (r13 == null) goto L73;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x011e, code lost:
            if (r13.equals(java.math.BigInteger.ZERO) == false) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0124, code lost:
            if (r20.l() == false) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x012a, code lost:
            return (r7 - 1) ^ (-1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x012b, code lost:
            r13 = r13.negate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0135, code lost:
            if (r14 != 0) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x013b, code lost:
            if (r20.l() == false) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0140, code lost:
            return (r7 - 1) ^ (-1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0141, code lost:
            r2 = -r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0148, code lost:
            if (r19.f3263g != org.threeten.bp.format.SignStyle.EXCEEDS_PAD) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x014e, code lost:
            if (r20.l() == false) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x0150, code lost:
            r0 = r5 - r7;
         */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int b(org.threeten.bp.format.DateTimeParseContext r20, java.lang.CharSequence r21, int r22) {
            /*
                Method dump skipped, instructions count: 399
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.b(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        long c(DateTimePrintContext dateTimePrintContext, long j2) {
            return j2;
        }

        boolean d(DateTimeParseContext dateTimeParseContext) {
            int i2 = this.f3264h;
            return i2 == -1 || (i2 > 0 && this.f3261e == this.f3262f && this.f3263g == SignStyle.NOT_NEGATIVE);
        }

        int e(DateTimeParseContext dateTimeParseContext, long j2, int i2, int i3) {
            return dateTimeParseContext.o(this.f3260d, j2, i2, i3);
        }

        NumberPrinterParser f() {
            if (this.f3264h == -1) {
                return this;
            }
            return new NumberPrinterParser(this.f3260d, this.f3261e, this.f3262f, this.f3263g, -1);
        }

        NumberPrinterParser g(int i2) {
            return new NumberPrinterParser(this.f3260d, this.f3261e, this.f3262f, this.f3263g, this.f3264h + i2);
        }

        public String toString() {
            int i2 = this.f3261e;
            if (i2 == 1 && this.f3262f == 19 && this.f3263g == SignStyle.NORMAL) {
                return "Value(" + this.f3260d + ")";
            } else if (i2 == this.f3262f && this.f3263g == SignStyle.NOT_NEGATIVE) {
                return "Value(" + this.f3260d + "," + this.f3261e + ")";
            } else {
                return "Value(" + this.f3260d + "," + this.f3261e + "," + this.f3262f + "," + this.f3263g + ")";
            }
        }

        private NumberPrinterParser(TemporalField temporalField, int i2, int i3, SignStyle signStyle, int i4) {
            this.f3260d = temporalField;
            this.f3261e = i2;
            this.f3262f = i3;
            this.f3263g = signStyle;
            this.f3264h = i4;
        }
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder, boolean z2) {
        this.f3241a = this;
        this.f3243c = new ArrayList();
        this.f3247g = -1;
        this.f3242b = dateTimeFormatterBuilder;
        this.f3244d = z2;
    }
}
