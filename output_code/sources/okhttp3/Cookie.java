package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Cookie {

    /* renamed from: j  reason: collision with root package name */
    private static final Pattern f1906j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: k  reason: collision with root package name */
    private static final Pattern f1907k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: l  reason: collision with root package name */
    private static final Pattern f1908l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: m  reason: collision with root package name */
    private static final Pattern f1909m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: a  reason: collision with root package name */
    private final String f1910a;

    /* renamed from: b  reason: collision with root package name */
    private final String f1911b;

    /* renamed from: c  reason: collision with root package name */
    private final long f1912c;

    /* renamed from: d  reason: collision with root package name */
    private final String f1913d;

    /* renamed from: e  reason: collision with root package name */
    private final String f1914e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f1915f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f1916g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f1917h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f1918i;

    private Cookie(String str, String str2, long j2, String str3, String str4, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.f1910a = str;
        this.f1911b = str2;
        this.f1912c = j2;
        this.f1913d = str3;
        this.f1914e = str4;
        this.f1915f = z2;
        this.f1916g = z3;
        this.f1918i = z4;
        this.f1917h = z5;
    }

    private static int a(String str, int i2, int i3, boolean z2) {
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (((charAt < ' ' && charAt != '\t') || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z2)) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static boolean b(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.I(str)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0128  */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static okhttp3.Cookie d(long r23, okhttp3.HttpUrl r25, java.lang.String r26) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.d(long, okhttp3.HttpUrl, java.lang.String):okhttp3.Cookie");
    }

    @Nullable
    public static Cookie e(HttpUrl httpUrl, String str) {
        return d(System.currentTimeMillis(), httpUrl, str);
    }

    public static List<Cookie> f(HttpUrl httpUrl, Headers headers) {
        List<String> i2 = headers.i("Set-Cookie");
        int size = i2.size();
        ArrayList arrayList = null;
        for (int i3 = 0; i3 < size; i3++) {
            Cookie e2 = e(httpUrl, i2.get(i3));
            if (e2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(e2);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    private static String g(String str) {
        if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            String d2 = Util.d(str);
            if (d2 != null) {
                return d2;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static long h(String str, int i2, int i3) {
        int a2 = a(str, i2, i3, false);
        Matcher matcher = f1909m.matcher(str);
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        while (a2 < i3) {
            int a3 = a(str, a2 + 1, i3, true);
            matcher.region(a2, a3);
            if (i5 == -1 && matcher.usePattern(f1909m).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
                i8 = Integer.parseInt(matcher.group(2));
                i9 = Integer.parseInt(matcher.group(3));
            } else if (i6 == -1 && matcher.usePattern(f1908l).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else {
                if (i7 == -1) {
                    Pattern pattern = f1907k;
                    if (matcher.usePattern(pattern).matches()) {
                        i7 = pattern.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                    }
                }
                if (i4 == -1 && matcher.usePattern(f1906j).matches()) {
                    i4 = Integer.parseInt(matcher.group(1));
                }
            }
            a2 = a(str, a3 + 1, i3, false);
        }
        if (i4 >= 70 && i4 <= 99) {
            i4 += 1900;
        }
        if (i4 >= 0 && i4 <= 69) {
            i4 += 2000;
        }
        if (i4 >= 1601) {
            if (i7 != -1) {
                if (i6 >= 1 && i6 <= 31) {
                    if (i5 >= 0 && i5 <= 23) {
                        if (i8 >= 0 && i8 <= 59) {
                            if (i9 >= 0 && i9 <= 59) {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.f2096p);
                                gregorianCalendar.setLenient(false);
                                gregorianCalendar.set(1, i4);
                                gregorianCalendar.set(2, i7 - 1);
                                gregorianCalendar.set(5, i6);
                                gregorianCalendar.set(11, i5);
                                gregorianCalendar.set(12, i8);
                                gregorianCalendar.set(13, i9);
                                gregorianCalendar.set(14, 0);
                                return gregorianCalendar.getTimeInMillis();
                            }
                            throw new IllegalArgumentException();
                        }
                        throw new IllegalArgumentException();
                    }
                    throw new IllegalArgumentException();
                }
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static long i(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e2) {
            if (str.matches("-?\\d+")) {
                if (str.startsWith("-")) {
                    return Long.MIN_VALUE;
                }
                return Long.MAX_VALUE;
            }
            throw e2;
        }
    }

    public String c() {
        return this.f1910a;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (!cookie.f1910a.equals(this.f1910a) || !cookie.f1911b.equals(this.f1911b) || !cookie.f1913d.equals(this.f1913d) || !cookie.f1914e.equals(this.f1914e) || cookie.f1912c != this.f1912c || cookie.f1915f != this.f1915f || cookie.f1916g != this.f1916g || cookie.f1917h != this.f1917h || cookie.f1918i != this.f1918i) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j2 = this.f1912c;
        return ((((((((((((((((527 + this.f1910a.hashCode()) * 31) + this.f1911b.hashCode()) * 31) + this.f1913d.hashCode()) * 31) + this.f1914e.hashCode()) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (!this.f1915f ? 1 : 0)) * 31) + (!this.f1916g ? 1 : 0)) * 31) + (!this.f1917h ? 1 : 0)) * 31) + (!this.f1918i ? 1 : 0);
    }

    String j(boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1910a);
        sb.append('=');
        sb.append(this.f1911b);
        if (this.f1917h) {
            if (this.f1912c == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(HttpDate.a(new Date(this.f1912c)));
            }
        }
        if (!this.f1918i) {
            sb.append("; domain=");
            if (z2) {
                sb.append(".");
            }
            sb.append(this.f1913d);
        }
        sb.append("; path=");
        sb.append(this.f1914e);
        if (this.f1915f) {
            sb.append("; secure");
        }
        if (this.f1916g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public String k() {
        return this.f1911b;
    }

    public String toString() {
        return j(false);
    }
}
