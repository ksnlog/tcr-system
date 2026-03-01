package org.threeten.bp.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DateTimeParseContext {

    /* renamed from: a  reason: collision with root package name */
    private Locale f3289a;

    /* renamed from: b  reason: collision with root package name */
    private DecimalStyle f3290b;

    /* renamed from: c  reason: collision with root package name */
    private Chronology f3291c;

    /* renamed from: d  reason: collision with root package name */
    private ZoneId f3292d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f3293e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f3294f;

    /* renamed from: g  reason: collision with root package name */
    private final ArrayList<Parsed> f3295g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class Parsed extends DefaultInterfaceTemporalAccessor {

        /* renamed from: d  reason: collision with root package name */
        Chronology f3296d;

        /* renamed from: e  reason: collision with root package name */
        ZoneId f3297e;

        /* renamed from: f  reason: collision with root package name */
        final Map<TemporalField, Long> f3298f;

        /* renamed from: g  reason: collision with root package name */
        boolean f3299g;

        /* renamed from: h  reason: collision with root package name */
        Period f3300h;

        @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
        public <R> R b(TemporalQuery<R> temporalQuery) {
            if (temporalQuery == TemporalQueries.a()) {
                return (R) this.f3296d;
            }
            if (temporalQuery != TemporalQueries.g() && temporalQuery != TemporalQueries.f()) {
                return (R) super.b(temporalQuery);
            }
            return (R) this.f3297e;
        }

        @Override // org.threeten.bp.temporal.TemporalAccessor
        public boolean d(TemporalField temporalField) {
            return this.f3298f.containsKey(temporalField);
        }

        @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
        public int f(TemporalField temporalField) {
            if (this.f3298f.containsKey(temporalField)) {
                return Jdk8Methods.p(this.f3298f.get(temporalField).longValue());
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }

        @Override // org.threeten.bp.temporal.TemporalAccessor
        public long h(TemporalField temporalField) {
            if (this.f3298f.containsKey(temporalField)) {
                return this.f3298f.get(temporalField).longValue();
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }

        protected Parsed m() {
            Parsed parsed = new Parsed();
            parsed.f3296d = this.f3296d;
            parsed.f3297e = this.f3297e;
            parsed.f3298f.putAll(this.f3298f);
            parsed.f3299g = this.f3299g;
            return parsed;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public DateTimeBuilder n() {
            DateTimeBuilder dateTimeBuilder = new DateTimeBuilder();
            dateTimeBuilder.f3207d.putAll(this.f3298f);
            dateTimeBuilder.f3208e = DateTimeParseContext.this.g();
            ZoneId zoneId = this.f3297e;
            if (zoneId != null) {
                dateTimeBuilder.f3209f = zoneId;
            } else {
                dateTimeBuilder.f3209f = DateTimeParseContext.this.f3292d;
            }
            dateTimeBuilder.f3212i = this.f3299g;
            dateTimeBuilder.f3213j = this.f3300h;
            return dateTimeBuilder;
        }

        public String toString() {
            return this.f3298f.toString() + "," + this.f3296d + "," + this.f3297e;
        }

        private Parsed() {
            this.f3296d = null;
            this.f3297e = null;
            this.f3298f = new HashMap();
            this.f3300h = Period.f3085g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeParseContext(DateTimeFormatter dateTimeFormatter) {
        this.f3293e = true;
        this.f3294f = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.f3295g = arrayList;
        this.f3289a = dateTimeFormatter.f();
        this.f3290b = dateTimeFormatter.e();
        this.f3291c = dateTimeFormatter.d();
        this.f3292d = dateTimeFormatter.g();
        arrayList.add(new Parsed());
    }

    static boolean c(char c2, char c3) {
        if (c2 != c3 && Character.toUpperCase(c2) != Character.toUpperCase(c3) && Character.toLowerCase(c2) != Character.toLowerCase(c3)) {
            return false;
        }
        return true;
    }

    private Parsed e() {
        ArrayList<Parsed> arrayList = this.f3295g;
        return arrayList.get(arrayList.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(char c2, char c3) {
        if (k()) {
            if (c2 == c3) {
                return true;
            }
            return false;
        }
        return c(c2, c3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeParseContext d() {
        return new DateTimeParseContext(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(boolean z2) {
        if (z2) {
            ArrayList<Parsed> arrayList = this.f3295g;
            arrayList.remove(arrayList.size() - 2);
            return;
        }
        ArrayList<Parsed> arrayList2 = this.f3295g;
        arrayList2.remove(arrayList2.size() - 1);
    }

    Chronology g() {
        Chronology chronology = e().f3296d;
        if (chronology == null) {
            Chronology chronology2 = this.f3291c;
            if (chronology2 == null) {
                return IsoChronology.f3167h;
            }
            return chronology2;
        }
        return chronology;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Locale h() {
        return this.f3289a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Long i(TemporalField temporalField) {
        return e().f3298f.get(temporalField);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecimalStyle j() {
        return this.f3290b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.f3293e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l() {
        return this.f3294f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(boolean z2) {
        this.f3293e = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(ZoneId zoneId) {
        Jdk8Methods.i(zoneId, "zone");
        e().f3297e = zoneId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int o(TemporalField temporalField, long j2, int i2, int i3) {
        Jdk8Methods.i(temporalField, "field");
        Long put = e().f3298f.put(temporalField, Long.valueOf(j2));
        if (put != null && put.longValue() != j2) {
            return i2 ^ (-1);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        e().f3299g = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(boolean z2) {
        this.f3294f = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r() {
        this.f3295g.add(e().m());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean s(CharSequence charSequence, int i2, CharSequence charSequence2, int i3, int i4) {
        if (i2 + i4 > charSequence.length() || i3 + i4 > charSequence2.length()) {
            return false;
        }
        if (k()) {
            for (int i5 = 0; i5 < i4; i5++) {
                if (charSequence.charAt(i2 + i5) != charSequence2.charAt(i3 + i5)) {
                    return false;
                }
            }
            return true;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            char charAt = charSequence.charAt(i2 + i6);
            char charAt2 = charSequence2.charAt(i3 + i6);
            if (charAt != charAt2 && Character.toUpperCase(charAt) != Character.toUpperCase(charAt2) && Character.toLowerCase(charAt) != Character.toLowerCase(charAt2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Parsed t() {
        return e();
    }

    public String toString() {
        return e().toString();
    }

    DateTimeParseContext(DateTimeParseContext dateTimeParseContext) {
        this.f3293e = true;
        this.f3294f = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.f3295g = arrayList;
        this.f3289a = dateTimeParseContext.f3289a;
        this.f3290b = dateTimeParseContext.f3290b;
        this.f3291c = dateTimeParseContext.f3291c;
        this.f3292d = dateTimeParseContext.f3292d;
        this.f3293e = dateTimeParseContext.f3293e;
        this.f3294f = dateTimeParseContext.f3294f;
        arrayList.add(new Parsed());
    }
}
