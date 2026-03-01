package org.threeten.bp.format;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DecimalStyle {

    /* renamed from: e  reason: collision with root package name */
    public static final DecimalStyle f3313e = new DecimalStyle('0', '+', '-', '.');

    /* renamed from: f  reason: collision with root package name */
    private static final ConcurrentMap<Locale, DecimalStyle> f3314f = new ConcurrentHashMap(16, 0.75f, 2);

    /* renamed from: a  reason: collision with root package name */
    private final char f3315a;

    /* renamed from: b  reason: collision with root package name */
    private final char f3316b;

    /* renamed from: c  reason: collision with root package name */
    private final char f3317c;

    /* renamed from: d  reason: collision with root package name */
    private final char f3318d;

    private DecimalStyle(char c2, char c3, char c4, char c5) {
        this.f3315a = c2;
        this.f3316b = c3;
        this.f3317c = c4;
        this.f3318d = c5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(String str) {
        char c2 = this.f3315a;
        if (c2 == '0') {
            return str;
        }
        int i2 = c2 - '0';
        char[] charArray = str.toCharArray();
        for (int i3 = 0; i3 < charArray.length; i3++) {
            charArray[i3] = (char) (charArray[i3] + i2);
        }
        return new String(charArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(char c2) {
        int i2 = c2 - this.f3315a;
        if (i2 < 0 || i2 > 9) {
            return -1;
        }
        return i2;
    }

    public char c() {
        return this.f3318d;
    }

    public char d() {
        return this.f3317c;
    }

    public char e() {
        return this.f3316b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalStyle)) {
            return false;
        }
        DecimalStyle decimalStyle = (DecimalStyle) obj;
        if (this.f3315a == decimalStyle.f3315a && this.f3316b == decimalStyle.f3316b && this.f3317c == decimalStyle.f3317c && this.f3318d == decimalStyle.f3318d) {
            return true;
        }
        return false;
    }

    public char f() {
        return this.f3315a;
    }

    public int hashCode() {
        return this.f3315a + this.f3316b + this.f3317c + this.f3318d;
    }

    public String toString() {
        return "DecimalStyle[" + this.f3315a + this.f3316b + this.f3317c + this.f3318d + "]";
    }
}
