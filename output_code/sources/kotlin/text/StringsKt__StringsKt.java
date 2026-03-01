package kotlin.text;

import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    public static final int A(CharSequence charSequence, char[] chars, int i2, boolean z2) {
        int a2;
        boolean z3;
        char p2;
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(chars, "chars");
        if (!z2 && chars.length == 1 && (charSequence instanceof String)) {
            p2 = ArraysKt___ArraysKt.p(chars);
            return ((String) charSequence).indexOf(p2, i2);
        }
        a2 = RangesKt___RangesKt.a(i2, 0);
        IntIterator it = new IntRange(a2, t(charSequence)).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            char charAt = charSequence.charAt(nextInt);
            int length = chars.length;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    if (CharsKt__CharKt.d(chars[i3], charAt, z2)) {
                        z3 = true;
                        continue;
                        break;
                    }
                    i3++;
                } else {
                    z3 = false;
                    continue;
                    break;
                }
            }
            if (z3) {
                return nextInt;
            }
        }
        return -1;
    }

    public static final int B(CharSequence charSequence, char c2, int i2, boolean z2) {
        Intrinsics.f(charSequence, "<this>");
        if (!z2 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(c2, i2);
        }
        return D(charSequence, new char[]{c2}, i2, z2);
    }

    public static /* synthetic */ int C(CharSequence charSequence, char c2, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = t(charSequence);
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return B(charSequence, c2, i2, z2);
    }

    public static final int D(CharSequence charSequence, char[] chars, int i2, boolean z2) {
        int c2;
        char p2;
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(chars, "chars");
        if (!z2 && chars.length == 1 && (charSequence instanceof String)) {
            p2 = ArraysKt___ArraysKt.p(chars);
            return ((String) charSequence).lastIndexOf(p2, i2);
        }
        for (c2 = RangesKt___RangesKt.c(i2, t(charSequence)); -1 < c2; c2--) {
            char charAt = charSequence.charAt(c2);
            int length = chars.length;
            boolean z3 = false;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if (CharsKt__CharKt.d(chars[i3], charAt, z2)) {
                    z3 = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z3) {
                return c2;
            }
        }
        return -1;
    }

    public static final boolean E(CharSequence charSequence, int i2, CharSequence other, int i3, int i4, boolean z2) {
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(other, "other");
        if (i3 < 0 || i2 < 0 || i2 > charSequence.length() - i4 || i3 > other.length() - i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (!CharsKt__CharKt.d(charSequence.charAt(i2 + i5), other.charAt(i3 + i5), z2)) {
                return false;
            }
        }
        return true;
    }

    public static String F(String str, CharSequence suffix) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(suffix, "suffix");
        if (r(str, suffix, false, 2, null)) {
            String substring = str.substring(0, str.length() - suffix.length());
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }

    public static final void G(int i2) {
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2).toString());
    }

    public static final String H(String str, char c2, String missingDelimiterValue) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(missingDelimiterValue, "missingDelimiterValue");
        int y2 = y(str, c2, 0, false, 6, null);
        if (y2 != -1) {
            String substring = str.substring(y2 + 1, str.length());
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static final String I(String str, String delimiter, String missingDelimiterValue) {
        int z2;
        Intrinsics.f(str, "<this>");
        Intrinsics.f(delimiter, "delimiter");
        Intrinsics.f(missingDelimiterValue, "missingDelimiterValue");
        z2 = z(str, delimiter, 0, false, 6, null);
        if (z2 != -1) {
            String substring = str.substring(z2 + delimiter.length(), str.length());
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static /* synthetic */ String J(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return H(str, c2, str2);
    }

    public static /* synthetic */ String K(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return I(str, str2, str3);
    }

    public static final String L(String str, char c2, String missingDelimiterValue) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(missingDelimiterValue, "missingDelimiterValue");
        int C = C(str, c2, 0, false, 6, null);
        if (C != -1) {
            String substring = str.substring(C + 1, str.length());
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static /* synthetic */ String M(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return L(str, c2, str2);
    }

    public static final String N(String str, char c2, String missingDelimiterValue) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(missingDelimiterValue, "missingDelimiterValue");
        int y2 = y(str, c2, 0, false, 6, null);
        if (y2 != -1) {
            String substring = str.substring(0, y2);
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static final String O(String str, String delimiter, String missingDelimiterValue) {
        int z2;
        Intrinsics.f(str, "<this>");
        Intrinsics.f(delimiter, "delimiter");
        Intrinsics.f(missingDelimiterValue, "missingDelimiterValue");
        z2 = z(str, delimiter, 0, false, 6, null);
        if (z2 != -1) {
            String substring = str.substring(0, z2);
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static /* synthetic */ String P(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return N(str, c2, str2);
    }

    public static /* synthetic */ String Q(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return O(str, str2, str3);
    }

    public static CharSequence R(CharSequence charSequence) {
        int i2;
        Intrinsics.f(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i3 = 0;
        boolean z2 = false;
        while (i3 <= length) {
            if (!z2) {
                i2 = i3;
            } else {
                i2 = length;
            }
            boolean c2 = CharsKt__CharJVMKt.c(charSequence.charAt(i2));
            if (!z2) {
                if (!c2) {
                    z2 = true;
                } else {
                    i3++;
                }
            } else if (!c2) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i3, length + 1);
    }

    public static final boolean o(CharSequence charSequence, CharSequence other, boolean z2) {
        int z3;
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(other, "other");
        if (other instanceof String) {
            z3 = z(charSequence, (String) other, 0, z2, 2, null);
            if (z3 >= 0) {
                return true;
            }
        } else if (x(charSequence, other, 0, charSequence.length(), z2, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean p(CharSequence charSequence, CharSequence charSequence2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return o(charSequence, charSequence2, z2);
    }

    public static final boolean q(CharSequence charSequence, CharSequence suffix, boolean z2) {
        boolean g2;
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(suffix, "suffix");
        if (!z2 && (charSequence instanceof String) && (suffix instanceof String)) {
            g2 = StringsKt__StringsJVMKt.g((String) charSequence, (String) suffix, false, 2, null);
            return g2;
        }
        return E(charSequence, charSequence.length() - suffix.length(), suffix, 0, suffix.length(), z2);
    }

    public static /* synthetic */ boolean r(CharSequence charSequence, CharSequence charSequence2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return q(charSequence, charSequence2, z2);
    }

    public static final IntRange s(CharSequence charSequence) {
        Intrinsics.f(charSequence, "<this>");
        return new IntRange(0, charSequence.length() - 1);
    }

    public static final int t(CharSequence charSequence) {
        Intrinsics.f(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int u(CharSequence charSequence, char c2, int i2, boolean z2) {
        Intrinsics.f(charSequence, "<this>");
        if (!z2 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c2, i2);
        }
        return A(charSequence, new char[]{c2}, i2, z2);
    }

    public static final int v(CharSequence charSequence, String string, int i2, boolean z2) {
        Intrinsics.f(charSequence, "<this>");
        Intrinsics.f(string, "string");
        if (!z2 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(string, i2);
        }
        return x(charSequence, string, i2, charSequence.length(), z2, false, 16, null);
    }

    private static final int w(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z2, boolean z3) {
        int c2;
        int a2;
        IntProgression e2;
        int a3;
        int c3;
        if (!z3) {
            a3 = RangesKt___RangesKt.a(i2, 0);
            c3 = RangesKt___RangesKt.c(i3, charSequence.length());
            e2 = new IntRange(a3, c3);
        } else {
            c2 = RangesKt___RangesKt.c(i2, t(charSequence));
            a2 = RangesKt___RangesKt.a(i3, 0);
            e2 = RangesKt___RangesKt.e(c2, a2);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int a4 = e2.a();
            int b2 = e2.b();
            int c4 = e2.c();
            if ((c4 > 0 && a4 <= b2) || (c4 < 0 && b2 <= a4)) {
                while (!StringsKt__StringsJVMKt.j((String) charSequence2, 0, (String) charSequence, a4, charSequence2.length(), z2)) {
                    if (a4 != b2) {
                        a4 += c4;
                    } else {
                        return -1;
                    }
                }
                return a4;
            }
            return -1;
        }
        int a5 = e2.a();
        int b3 = e2.b();
        int c5 = e2.c();
        if ((c5 > 0 && a5 <= b3) || (c5 < 0 && b3 <= a5)) {
            while (!E(charSequence2, 0, charSequence, a5, charSequence2.length(), z2)) {
                if (a5 != b3) {
                    a5 += c5;
                } else {
                    return -1;
                }
            }
            return a5;
        }
        return -1;
    }

    static /* synthetic */ int x(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z2, boolean z3, int i4, Object obj) {
        return w(charSequence, charSequence2, i2, i3, z2, (i4 & 16) != 0 ? false : z3);
    }

    public static /* synthetic */ int y(CharSequence charSequence, char c2, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return u(charSequence, c2, i2, z2);
    }

    public static /* synthetic */ int z(CharSequence charSequence, String str, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return v(charSequence, str, i2, z2);
    }
}
