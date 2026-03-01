package okhttp3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HttpUrl {

    /* renamed from: j  reason: collision with root package name */
    private static final char[] f1936j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a  reason: collision with root package name */
    final String f1937a;

    /* renamed from: b  reason: collision with root package name */
    private final String f1938b;

    /* renamed from: c  reason: collision with root package name */
    private final String f1939c;

    /* renamed from: d  reason: collision with root package name */
    final String f1940d;

    /* renamed from: e  reason: collision with root package name */
    final int f1941e;

    /* renamed from: f  reason: collision with root package name */
    private final List<String> f1942f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private final List<String> f1943g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    private final String f1944h;

    /* renamed from: i  reason: collision with root package name */
    private final String f1945i;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        String f1946a;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        String f1949d;

        /* renamed from: f  reason: collision with root package name */
        final List<String> f1951f;
        @Nullable

        /* renamed from: g  reason: collision with root package name */
        List<String> f1952g;
        @Nullable

        /* renamed from: h  reason: collision with root package name */
        String f1953h;

        /* renamed from: b  reason: collision with root package name */
        String f1947b = "";

        /* renamed from: c  reason: collision with root package name */
        String f1948c = "";

        /* renamed from: e  reason: collision with root package name */
        int f1950e = -1;

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.f1951f = arrayList;
            arrayList.add("");
        }

        private static String b(String str, int i2, int i3) {
            return Util.d(HttpUrl.r(str, i2, i3, false));
        }

        private boolean f(String str) {
            if (!str.equals(".") && !str.equalsIgnoreCase("%2e")) {
                return false;
            }
            return true;
        }

        private boolean g(String str) {
            if (!str.equals("..") && !str.equalsIgnoreCase("%2e.") && !str.equalsIgnoreCase(".%2e") && !str.equalsIgnoreCase("%2e%2e")) {
                return false;
            }
            return true;
        }

        private static int i(String str, int i2, int i3) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(HttpUrl.a(str, i2, i3, "", false, false, false, true, null));
            } catch (NumberFormatException unused) {
            }
            if (parseInt <= 0 || parseInt > 65535) {
                return -1;
            }
            return parseInt;
        }

        private void j() {
            List<String> list = this.f1951f;
            if (list.remove(list.size() - 1).isEmpty() && !this.f1951f.isEmpty()) {
                List<String> list2 = this.f1951f;
                list2.set(list2.size() - 1, "");
                return;
            }
            this.f1951f.add("");
        }

        private static int l(String str, int i2, int i3) {
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != ':') {
                    if (charAt == '[') {
                        do {
                            i2++;
                            if (i2 < i3) {
                            }
                        } while (str.charAt(i2) != ']');
                    }
                    i2++;
                } else {
                    return i2;
                }
            }
            return i3;
        }

        private void m(String str, int i2, int i3, boolean z2, boolean z3) {
            String a2 = HttpUrl.a(str, i2, i3, " \"<>^`{}|/\\?#", z3, false, false, true, null);
            if (f(a2)) {
                return;
            }
            if (g(a2)) {
                j();
                return;
            }
            List<String> list = this.f1951f;
            if (list.get(list.size() - 1).isEmpty()) {
                List<String> list2 = this.f1951f;
                list2.set(list2.size() - 1, a2);
            } else {
                this.f1951f.add(a2);
            }
            if (z2) {
                this.f1951f.add("");
            }
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0044 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0041 -> B:11:0x0029). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void o(java.lang.String r11, int r12, int r13) {
            /*
                r10 = this;
                if (r12 != r13) goto L3
                return
            L3:
                char r0 = r11.charAt(r12)
                r1 = 47
                java.lang.String r2 = ""
                r3 = 1
                if (r0 == r1) goto L1e
                r1 = 92
                if (r0 != r1) goto L13
                goto L1e
            L13:
                java.util.List<java.lang.String> r0 = r10.f1951f
                int r1 = r0.size()
                int r1 = r1 - r3
                r0.set(r1, r2)
                goto L29
            L1e:
                java.util.List<java.lang.String> r0 = r10.f1951f
                r0.clear()
                java.util.List<java.lang.String> r0 = r10.f1951f
                r0.add(r2)
                goto L41
            L29:
                r6 = r12
                if (r6 >= r13) goto L44
                java.lang.String r12 = "/\\"
                int r12 = okhttp3.internal.Util.n(r11, r6, r13, r12)
                if (r12 >= r13) goto L36
                r0 = 1
                goto L37
            L36:
                r0 = 0
            L37:
                r9 = 1
                r4 = r10
                r5 = r11
                r7 = r12
                r8 = r0
                r4.m(r5, r6, r7, r8, r9)
                if (r0 == 0) goto L29
            L41:
                int r12 = r12 + 1
                goto L29
            L44:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.o(java.lang.String, int, int):void");
        }

        private static int q(String str, int i2, int i3) {
            if (i3 - i2 < 2) {
                return -1;
            }
            char charAt = str.charAt(i2);
            if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                while (true) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < 'a' || charAt2 > 'z') {
                        if (charAt2 < 'A' || charAt2 > 'Z') {
                            if (charAt2 < '0' || charAt2 > '9') {
                                if (charAt2 != '+' && charAt2 != '-' && charAt2 != '.') {
                                    if (charAt2 == ':') {
                                        return i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private static int r(String str, int i2, int i3) {
            int i4 = 0;
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i4++;
                i2++;
            }
            return i4;
        }

        public HttpUrl a() {
            if (this.f1946a != null) {
                if (this.f1949d != null) {
                    return new HttpUrl(this);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        int c() {
            int i2 = this.f1950e;
            return i2 != -1 ? i2 : HttpUrl.d(this.f1946a);
        }

        public Builder d(@Nullable String str) {
            List<String> list;
            if (str != null) {
                list = HttpUrl.y(HttpUrl.b(str, " \"'<>#", true, false, true, true));
            } else {
                list = null;
            }
            this.f1952g = list;
            return this;
        }

        public Builder e(String str) {
            if (str != null) {
                String b2 = b(str, 0, str.length());
                if (b2 != null) {
                    this.f1949d = b2;
                    return this;
                }
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            throw new NullPointerException("host == null");
        }

        Builder h(@Nullable HttpUrl httpUrl, String str) {
            int q2;
            int n2;
            char c2;
            int i2;
            int D = Util.D(str, 0, str.length());
            int E = Util.E(str, D, str.length());
            if (q(str, D, E) != -1) {
                if (str.regionMatches(true, D, "https:", 0, 6)) {
                    this.f1946a = "https";
                    D += 6;
                } else if (str.regionMatches(true, D, "http:", 0, 5)) {
                    this.f1946a = "http";
                    D += 5;
                } else {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, q2) + "'");
                }
            } else if (httpUrl != null) {
                this.f1946a = httpUrl.f1937a;
            } else {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            int r2 = r(str, D, E);
            char c3 = '?';
            char c4 = '#';
            if (r2 < 2 && httpUrl != null && httpUrl.f1937a.equals(this.f1946a)) {
                this.f1947b = httpUrl.j();
                this.f1948c = httpUrl.f();
                this.f1949d = httpUrl.f1940d;
                this.f1950e = httpUrl.f1941e;
                this.f1951f.clear();
                this.f1951f.addAll(httpUrl.h());
                if (D == E || str.charAt(D) == '#') {
                    d(httpUrl.i());
                }
            } else {
                int i3 = D + r2;
                boolean z2 = false;
                boolean z3 = false;
                while (true) {
                    n2 = Util.n(str, i3, E, "@/\\?#");
                    if (n2 != E) {
                        c2 = str.charAt(n2);
                    } else {
                        c2 = 65535;
                    }
                    if (c2 == 65535 || c2 == c4 || c2 == '/' || c2 == '\\' || c2 == c3) {
                        break;
                    }
                    if (c2 == '@') {
                        if (!z2) {
                            int m2 = Util.m(str, i3, n2, ':');
                            i2 = n2;
                            String a2 = HttpUrl.a(str, i3, m2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z3) {
                                a2 = this.f1947b + "%40" + a2;
                            }
                            this.f1947b = a2;
                            if (m2 != i2) {
                                this.f1948c = HttpUrl.a(str, m2 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z2 = true;
                            }
                            z3 = true;
                        } else {
                            i2 = n2;
                            this.f1948c += "%40" + HttpUrl.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        }
                        i3 = i2 + 1;
                    }
                    c3 = '?';
                    c4 = '#';
                }
                int l2 = l(str, i3, n2);
                int i4 = l2 + 1;
                if (i4 < n2) {
                    this.f1949d = b(str, i3, l2);
                    int i5 = i(str, i4, n2);
                    this.f1950e = i5;
                    if (i5 == -1) {
                        throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i4, n2) + '\"');
                    }
                } else {
                    this.f1949d = b(str, i3, l2);
                    this.f1950e = HttpUrl.d(this.f1946a);
                }
                if (this.f1949d != null) {
                    D = n2;
                } else {
                    throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i3, l2) + '\"');
                }
            }
            int n3 = Util.n(str, D, E, "?#");
            o(str, D, n3);
            if (n3 < E && str.charAt(n3) == '?') {
                int m3 = Util.m(str, n3, E, '#');
                this.f1952g = HttpUrl.y(HttpUrl.a(str, n3 + 1, m3, " \"'<>#", true, false, true, true, null));
                n3 = m3;
            }
            if (n3 < E && str.charAt(n3) == '#') {
                this.f1953h = HttpUrl.a(str, 1 + n3, E, "", true, false, false, false, null);
            }
            return this;
        }

        public Builder k(int i2) {
            if (i2 > 0 && i2 <= 65535) {
                this.f1950e = i2;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i2);
        }

        Builder n() {
            int size = this.f1951f.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1951f.set(i2, HttpUrl.b(this.f1951f.get(i2), "[]", true, true, false, true));
            }
            List<String> list = this.f1952g;
            if (list != null) {
                int size2 = list.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    String str = this.f1952g.get(i3);
                    if (str != null) {
                        this.f1952g.set(i3, HttpUrl.b(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.f1953h;
            if (str2 != null) {
                this.f1953h = HttpUrl.b(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public Builder p(String str) {
            if (str != null) {
                if (str.equalsIgnoreCase("http")) {
                    this.f1946a = "http";
                } else if (str.equalsIgnoreCase("https")) {
                    this.f1946a = "https";
                } else {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                return this;
            }
            throw new NullPointerException("scheme == null");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = this.f1946a;
            if (str != null) {
                sb.append(str);
                sb.append("://");
            } else {
                sb.append("//");
            }
            if (!this.f1947b.isEmpty() || !this.f1948c.isEmpty()) {
                sb.append(this.f1947b);
                if (!this.f1948c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f1948c);
                }
                sb.append('@');
            }
            String str2 = this.f1949d;
            if (str2 != null) {
                if (str2.indexOf(58) != -1) {
                    sb.append('[');
                    sb.append(this.f1949d);
                    sb.append(']');
                } else {
                    sb.append(this.f1949d);
                }
            }
            if (this.f1950e != -1 || this.f1946a != null) {
                int c2 = c();
                String str3 = this.f1946a;
                if (str3 == null || c2 != HttpUrl.d(str3)) {
                    sb.append(':');
                    sb.append(c2);
                }
            }
            HttpUrl.q(sb, this.f1951f);
            if (this.f1952g != null) {
                sb.append('?');
                HttpUrl.n(sb, this.f1952g);
            }
            if (this.f1953h != null) {
                sb.append('#');
                sb.append(this.f1953h);
            }
            return sb.toString();
        }
    }

    HttpUrl(Builder builder) {
        List<String> list;
        this.f1937a = builder.f1946a;
        this.f1938b = s(builder.f1947b, false);
        this.f1939c = s(builder.f1948c, false);
        this.f1940d = builder.f1949d;
        this.f1941e = builder.c();
        this.f1942f = t(builder.f1951f, false);
        List<String> list2 = builder.f1952g;
        String str = null;
        if (list2 != null) {
            list = t(list2, true);
        } else {
            list = null;
        }
        this.f1943g = list;
        String str2 = builder.f1953h;
        this.f1944h = str2 != null ? s(str2, false) : str;
        this.f1945i = builder.toString();
    }

    static String a(String str, int i2, int i3, String str2, boolean z2, boolean z3, boolean z4, boolean z5, Charset charset) {
        int i4 = i2;
        while (i4 < i3) {
            int codePointAt = str.codePointAt(i4);
            if (codePointAt >= 32 && codePointAt != 127 && (codePointAt < 128 || !z5)) {
                if (str2.indexOf(codePointAt) == -1 && ((codePointAt != 37 || (z2 && (!z3 || v(str, i4, i3)))) && (codePointAt != 43 || !z4))) {
                    i4 += Character.charCount(codePointAt);
                }
            }
            Buffer buffer = new Buffer();
            buffer.h(str, i2, i4);
            c(buffer, str, i4, i3, str2, z2, z3, z4, z5, charset);
            return buffer.I();
        }
        return str.substring(i2, i3);
    }

    static String b(String str, String str2, boolean z2, boolean z3, boolean z4, boolean z5) {
        return a(str, 0, str.length(), str2, z2, z3, z4, z5, null);
    }

    static void c(Buffer buffer, String str, int i2, int i3, String str2, boolean z2, boolean z3, boolean z4, boolean z5, Charset charset) {
        String str3;
        Buffer buffer2 = null;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (!z2 || (codePointAt != 9 && codePointAt != 10 && codePointAt != 12 && codePointAt != 13)) {
                if (codePointAt == 43 && z4) {
                    if (z2) {
                        str3 = "+";
                    } else {
                        str3 = "%2B";
                    }
                    buffer.H(str3);
                } else if (codePointAt >= 32 && codePointAt != 127 && ((codePointAt < 128 || !z5) && str2.indexOf(codePointAt) == -1 && (codePointAt != 37 || (z2 && (!z3 || v(str, i2, i3)))))) {
                    buffer.d0(codePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    if (charset != null && !charset.equals(Util.f2090j)) {
                        buffer2.a0(str, i2, Character.charCount(codePointAt) + i2, charset);
                    } else {
                        buffer2.d0(codePointAt);
                    }
                    while (!buffer2.u()) {
                        int readByte = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        char[] cArr = f1936j;
                        buffer.writeByte(cArr[(readByte >> 4) & 15]);
                        buffer.writeByte(cArr[readByte & 15]);
                    }
                }
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    public static int d(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public static HttpUrl k(String str) {
        return new Builder().h(null, str).a();
    }

    static void n(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    static void q(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static String r(String str, int i2, int i3, boolean z2) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = str.charAt(i4);
            if (charAt == '%' || (charAt == '+' && z2)) {
                Buffer buffer = new Buffer();
                buffer.h(str, i2, i4);
                u(buffer, str, i4, i3, z2);
                return buffer.I();
            }
        }
        return str.substring(i2, i3);
    }

    static String s(String str, boolean z2) {
        return r(str, 0, str.length(), z2);
    }

    private List<String> t(List<String> list, boolean z2) {
        String str;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = list.get(i2);
            if (str2 != null) {
                str = s(str2, z2);
            } else {
                str = null;
            }
            arrayList.add(str);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static void u(Buffer buffer, String str, int i2, int i3, boolean z2) {
        int i4;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt == 37 && (i4 = i2 + 2) < i3) {
                int j2 = Util.j(str.charAt(i2 + 1));
                int j3 = Util.j(str.charAt(i4));
                if (j2 != -1 && j3 != -1) {
                    buffer.writeByte((j2 << 4) + j3);
                    i2 = i4;
                }
                buffer.d0(codePointAt);
            } else {
                if (codePointAt == 43 && z2) {
                    buffer.writeByte(32);
                }
                buffer.d0(codePointAt);
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    static boolean v(String str, int i2, int i3) {
        int i4 = i2 + 2;
        if (i4 < i3 && str.charAt(i2) == '%' && Util.j(str.charAt(i2 + 1)) != -1 && Util.j(str.charAt(i4)) != -1) {
            return true;
        }
        return false;
    }

    static List<String> y(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 != -1 && indexOf2 <= indexOf) {
                arrayList.add(str.substring(i2, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            } else {
                arrayList.add(str.substring(i2, indexOf));
                arrayList.add(null);
            }
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    public String A() {
        return this.f1937a;
    }

    public URI B() {
        String builder = o().n().toString();
        try {
            return new URI(builder);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(builder.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    @Nullable
    public String e() {
        if (this.f1944h == null) {
            return null;
        }
        return this.f1945i.substring(this.f1945i.indexOf(35) + 1);
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).f1945i.equals(this.f1945i);
    }

    public String f() {
        if (this.f1939c.isEmpty()) {
            return "";
        }
        int indexOf = this.f1945i.indexOf(64);
        return this.f1945i.substring(this.f1945i.indexOf(58, this.f1937a.length() + 3) + 1, indexOf);
    }

    public String g() {
        int indexOf = this.f1945i.indexOf(47, this.f1937a.length() + 3);
        String str = this.f1945i;
        return this.f1945i.substring(indexOf, Util.n(str, indexOf, str.length(), "?#"));
    }

    public List<String> h() {
        int indexOf = this.f1945i.indexOf(47, this.f1937a.length() + 3);
        String str = this.f1945i;
        int n2 = Util.n(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < n2) {
            int i2 = indexOf + 1;
            int m2 = Util.m(this.f1945i, i2, n2, '/');
            arrayList.add(this.f1945i.substring(i2, m2));
            indexOf = m2;
        }
        return arrayList;
    }

    public int hashCode() {
        return this.f1945i.hashCode();
    }

    @Nullable
    public String i() {
        if (this.f1943g == null) {
            return null;
        }
        int indexOf = this.f1945i.indexOf(63) + 1;
        String str = this.f1945i;
        return this.f1945i.substring(indexOf, Util.m(str, indexOf, str.length(), '#'));
    }

    public String j() {
        if (this.f1938b.isEmpty()) {
            return "";
        }
        int length = this.f1937a.length() + 3;
        String str = this.f1945i;
        return this.f1945i.substring(length, Util.n(str, length, str.length(), ":@"));
    }

    public String l() {
        return this.f1940d;
    }

    public boolean m() {
        return this.f1937a.equals("https");
    }

    public Builder o() {
        int i2;
        Builder builder = new Builder();
        builder.f1946a = this.f1937a;
        builder.f1947b = j();
        builder.f1948c = f();
        builder.f1949d = this.f1940d;
        if (this.f1941e != d(this.f1937a)) {
            i2 = this.f1941e;
        } else {
            i2 = -1;
        }
        builder.f1950e = i2;
        builder.f1951f.clear();
        builder.f1951f.addAll(h());
        builder.d(i());
        builder.f1953h = e();
        return builder;
    }

    @Nullable
    public Builder p(String str) {
        try {
            return new Builder().h(this, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public String toString() {
        return this.f1945i;
    }

    public int w() {
        return this.f1941e;
    }

    @Nullable
    public String x() {
        if (this.f1943g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        n(sb, this.f1943g);
        return sb.toString();
    }

    @Nullable
    public HttpUrl z(String str) {
        Builder p2 = p(str);
        if (p2 != null) {
            return p2.a();
        }
        return null;
    }
}
