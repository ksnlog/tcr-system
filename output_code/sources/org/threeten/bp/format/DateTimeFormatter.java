package org.threeten.bp.format;

import java.io.IOException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.DateTimeParseContext;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQuery;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DateTimeFormatter {

    /* renamed from: h  reason: collision with root package name */
    public static final DateTimeFormatter f3214h;

    /* renamed from: i  reason: collision with root package name */
    public static final DateTimeFormatter f3215i;

    /* renamed from: j  reason: collision with root package name */
    public static final DateTimeFormatter f3216j;

    /* renamed from: k  reason: collision with root package name */
    public static final DateTimeFormatter f3217k;

    /* renamed from: l  reason: collision with root package name */
    public static final DateTimeFormatter f3218l;

    /* renamed from: m  reason: collision with root package name */
    public static final DateTimeFormatter f3219m;

    /* renamed from: n  reason: collision with root package name */
    public static final DateTimeFormatter f3220n;

    /* renamed from: o  reason: collision with root package name */
    public static final DateTimeFormatter f3221o;

    /* renamed from: p  reason: collision with root package name */
    public static final DateTimeFormatter f3222p;

    /* renamed from: q  reason: collision with root package name */
    public static final DateTimeFormatter f3223q;

    /* renamed from: r  reason: collision with root package name */
    public static final DateTimeFormatter f3224r;

    /* renamed from: s  reason: collision with root package name */
    public static final DateTimeFormatter f3225s;

    /* renamed from: t  reason: collision with root package name */
    public static final DateTimeFormatter f3226t;

    /* renamed from: u  reason: collision with root package name */
    public static final DateTimeFormatter f3227u;

    /* renamed from: v  reason: collision with root package name */
    public static final DateTimeFormatter f3228v;

    /* renamed from: w  reason: collision with root package name */
    private static final TemporalQuery<Period> f3229w;

    /* renamed from: x  reason: collision with root package name */
    private static final TemporalQuery<Boolean> f3230x;

    /* renamed from: a  reason: collision with root package name */
    private final DateTimeFormatterBuilder.CompositePrinterParser f3231a;

    /* renamed from: b  reason: collision with root package name */
    private final Locale f3232b;

    /* renamed from: c  reason: collision with root package name */
    private final DecimalStyle f3233c;

    /* renamed from: d  reason: collision with root package name */
    private final ResolverStyle f3234d;

    /* renamed from: e  reason: collision with root package name */
    private final Set<TemporalField> f3235e;

    /* renamed from: f  reason: collision with root package name */
    private final Chronology f3236f;

    /* renamed from: g  reason: collision with root package name */
    private final ZoneId f3237g;

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        ChronoField chronoField = ChronoField.YEAR;
        SignStyle signStyle = SignStyle.EXCEEDS_PAD;
        DateTimeFormatterBuilder e2 = dateTimeFormatterBuilder.l(chronoField, 4, 10, signStyle).e('-');
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        DateTimeFormatterBuilder e3 = e2.k(chronoField2, 2).e('-');
        ChronoField chronoField3 = ChronoField.DAY_OF_MONTH;
        DateTimeFormatterBuilder k2 = e3.k(chronoField3, 2);
        ResolverStyle resolverStyle = ResolverStyle.STRICT;
        DateTimeFormatter u2 = k2.u(resolverStyle);
        IsoChronology isoChronology = IsoChronology.f3167h;
        DateTimeFormatter l2 = u2.l(isoChronology);
        f3214h = l2;
        f3215i = new DateTimeFormatterBuilder().p().a(l2).h().u(resolverStyle).l(isoChronology);
        f3216j = new DateTimeFormatterBuilder().p().a(l2).o().h().u(resolverStyle).l(isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = new DateTimeFormatterBuilder();
        ChronoField chronoField4 = ChronoField.HOUR_OF_DAY;
        DateTimeFormatterBuilder e4 = dateTimeFormatterBuilder2.k(chronoField4, 2).e(':');
        ChronoField chronoField5 = ChronoField.MINUTE_OF_HOUR;
        DateTimeFormatterBuilder e5 = e4.k(chronoField5, 2).o().e(':');
        ChronoField chronoField6 = ChronoField.SECOND_OF_MINUTE;
        DateTimeFormatter u3 = e5.k(chronoField6, 2).o().b(ChronoField.NANO_OF_SECOND, 0, 9, true).u(resolverStyle);
        f3217k = u3;
        f3218l = new DateTimeFormatterBuilder().p().a(u3).h().u(resolverStyle);
        f3219m = new DateTimeFormatterBuilder().p().a(u3).o().h().u(resolverStyle);
        DateTimeFormatter l3 = new DateTimeFormatterBuilder().p().a(l2).e('T').a(u3).u(resolverStyle).l(isoChronology);
        f3220n = l3;
        DateTimeFormatter l4 = new DateTimeFormatterBuilder().p().a(l3).h().u(resolverStyle).l(isoChronology);
        f3221o = l4;
        f3222p = new DateTimeFormatterBuilder().a(l4).o().e('[').q().m().e(']').u(resolverStyle).l(isoChronology);
        f3223q = new DateTimeFormatterBuilder().a(l3).o().h().o().e('[').q().m().e(']').u(resolverStyle).l(isoChronology);
        f3224r = new DateTimeFormatterBuilder().p().l(chronoField, 4, 10, signStyle).e('-').k(ChronoField.DAY_OF_YEAR, 3).o().h().u(resolverStyle).l(isoChronology);
        DateTimeFormatterBuilder e6 = new DateTimeFormatterBuilder().p().l(IsoFields.f3384d, 4, 10, signStyle).f("-W").k(IsoFields.f3383c, 2).e('-');
        ChronoField chronoField7 = ChronoField.DAY_OF_WEEK;
        f3225s = e6.k(chronoField7, 1).o().h().u(resolverStyle).l(isoChronology);
        f3226t = new DateTimeFormatterBuilder().p().c().u(resolverStyle);
        f3227u = new DateTimeFormatterBuilder().p().k(chronoField, 4).k(chronoField2, 2).k(chronoField3, 2).o().g("+HHMMss", "Z").u(resolverStyle).l(isoChronology);
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        f3228v = new DateTimeFormatterBuilder().p().r().o().i(chronoField7, hashMap).f(", ").n().l(chronoField3, 1, 2, SignStyle.NOT_NEGATIVE).e(' ').i(chronoField2, hashMap2).e(' ').k(chronoField, 4).e(' ').k(chronoField4, 2).e(':').k(chronoField5, 2).o().e(':').k(chronoField6, 2).n().e(' ').g("+HHMM", "GMT").u(ResolverStyle.SMART).l(isoChronology);
        f3229w = new TemporalQuery<Period>() { // from class: org.threeten.bp.format.DateTimeFormatter.1
            @Override // org.threeten.bp.temporal.TemporalQuery
            /* renamed from: b */
            public Period a(TemporalAccessor temporalAccessor) {
                if (temporalAccessor instanceof DateTimeBuilder) {
                    return ((DateTimeBuilder) temporalAccessor).f3213j;
                }
                return Period.f3085g;
            }
        };
        f3230x = new TemporalQuery<Boolean>() { // from class: org.threeten.bp.format.DateTimeFormatter.2
            @Override // org.threeten.bp.temporal.TemporalQuery
            /* renamed from: b */
            public Boolean a(TemporalAccessor temporalAccessor) {
                if (temporalAccessor instanceof DateTimeBuilder) {
                    return Boolean.valueOf(((DateTimeBuilder) temporalAccessor).f3212i);
                }
                return Boolean.FALSE;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser compositePrinterParser, Locale locale, DecimalStyle decimalStyle, ResolverStyle resolverStyle, Set<TemporalField> set, Chronology chronology, ZoneId zoneId) {
        this.f3231a = (DateTimeFormatterBuilder.CompositePrinterParser) Jdk8Methods.i(compositePrinterParser, "printerParser");
        this.f3232b = (Locale) Jdk8Methods.i(locale, "locale");
        this.f3233c = (DecimalStyle) Jdk8Methods.i(decimalStyle, "decimalStyle");
        this.f3234d = (ResolverStyle) Jdk8Methods.i(resolverStyle, "resolverStyle");
        this.f3235e = set;
        this.f3236f = chronology;
        this.f3237g = zoneId;
    }

    private DateTimeParseException a(CharSequence charSequence, RuntimeException runtimeException) {
        String charSequence2;
        if (charSequence.length() > 64) {
            charSequence2 = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            charSequence2 = charSequence.toString();
        }
        return new DateTimeParseException("Text '" + charSequence2 + "' could not be parsed: " + runtimeException.getMessage(), charSequence, 0, runtimeException);
    }

    private DateTimeBuilder i(CharSequence charSequence, ParsePosition parsePosition) {
        ParsePosition parsePosition2;
        String charSequence2;
        if (parsePosition != null) {
            parsePosition2 = parsePosition;
        } else {
            parsePosition2 = new ParsePosition(0);
        }
        DateTimeParseContext.Parsed j2 = j(charSequence, parsePosition2);
        if (j2 != null && parsePosition2.getErrorIndex() < 0 && (parsePosition != null || parsePosition2.getIndex() >= charSequence.length())) {
            return j2.n();
        }
        if (charSequence.length() > 64) {
            charSequence2 = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            charSequence2 = charSequence.toString();
        }
        if (parsePosition2.getErrorIndex() >= 0) {
            throw new DateTimeParseException("Text '" + charSequence2 + "' could not be parsed at index " + parsePosition2.getErrorIndex(), charSequence, parsePosition2.getErrorIndex());
        }
        throw new DateTimeParseException("Text '" + charSequence2 + "' could not be parsed, unparsed text found at index " + parsePosition2.getIndex(), charSequence, parsePosition2.getIndex());
    }

    private DateTimeParseContext.Parsed j(CharSequence charSequence, ParsePosition parsePosition) {
        Jdk8Methods.i(charSequence, "text");
        Jdk8Methods.i(parsePosition, "position");
        DateTimeParseContext dateTimeParseContext = new DateTimeParseContext(this);
        int b2 = this.f3231a.b(dateTimeParseContext, charSequence, parsePosition.getIndex());
        if (b2 < 0) {
            parsePosition.setErrorIndex(b2 ^ (-1));
            return null;
        }
        parsePosition.setIndex(b2);
        return dateTimeParseContext.t();
    }

    public String b(TemporalAccessor temporalAccessor) {
        StringBuilder sb = new StringBuilder(32);
        c(temporalAccessor, sb);
        return sb.toString();
    }

    public void c(TemporalAccessor temporalAccessor, Appendable appendable) {
        Jdk8Methods.i(temporalAccessor, "temporal");
        Jdk8Methods.i(appendable, "appendable");
        try {
            DateTimePrintContext dateTimePrintContext = new DateTimePrintContext(temporalAccessor, this);
            if (appendable instanceof StringBuilder) {
                this.f3231a.a(dateTimePrintContext, (StringBuilder) appendable);
                return;
            }
            StringBuilder sb = new StringBuilder(32);
            this.f3231a.a(dateTimePrintContext, sb);
            appendable.append(sb);
        } catch (IOException e2) {
            throw new DateTimeException(e2.getMessage(), e2);
        }
    }

    public Chronology d() {
        return this.f3236f;
    }

    public DecimalStyle e() {
        return this.f3233c;
    }

    public Locale f() {
        return this.f3232b;
    }

    public ZoneId g() {
        return this.f3237g;
    }

    public <T> T h(CharSequence charSequence, TemporalQuery<T> temporalQuery) {
        Jdk8Methods.i(charSequence, "text");
        Jdk8Methods.i(temporalQuery, "type");
        try {
            return (T) i(charSequence, null).z(this.f3234d, this.f3235e).p(temporalQuery);
        } catch (DateTimeParseException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            throw a(charSequence, e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeFormatterBuilder.CompositePrinterParser k(boolean z2) {
        return this.f3231a.c(z2);
    }

    public DateTimeFormatter l(Chronology chronology) {
        if (Jdk8Methods.c(this.f3236f, chronology)) {
            return this;
        }
        return new DateTimeFormatter(this.f3231a, this.f3232b, this.f3233c, this.f3234d, this.f3235e, chronology, this.f3237g);
    }

    public DateTimeFormatter m(ResolverStyle resolverStyle) {
        Jdk8Methods.i(resolverStyle, "resolverStyle");
        if (Jdk8Methods.c(this.f3234d, resolverStyle)) {
            return this;
        }
        return new DateTimeFormatter(this.f3231a, this.f3232b, this.f3233c, resolverStyle, this.f3235e, this.f3236f, this.f3237g);
    }

    public String toString() {
        String compositePrinterParser = this.f3231a.toString();
        if (!compositePrinterParser.startsWith("[")) {
            return compositePrinterParser.substring(1, compositePrinterParser.length() - 1);
        }
        return compositePrinterParser;
    }
}
