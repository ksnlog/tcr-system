package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractList;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    public static String d(char[] cArr) {
        Intrinsics.f(cArr, "<this>");
        return new String(cArr);
    }

    public static String e(char[] cArr, int i2, int i3) {
        Intrinsics.f(cArr, "<this>");
        AbstractList.f770d.a(i2, i3, cArr.length);
        return new String(cArr, i2, i3 - i2);
    }

    public static final boolean f(String str, String suffix, boolean z2) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(suffix, "suffix");
        if (!z2) {
            return str.endsWith(suffix);
        }
        return j(str, str.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    public static /* synthetic */ boolean g(String str, String str2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return f(str, str2, z2);
    }

    public static boolean h(String str, String str2, boolean z2) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            return false;
        } else if (!z2) {
            return str.equals(str2);
        } else {
            return str.equalsIgnoreCase(str2);
        }
    }

    public static boolean i(CharSequence charSequence) {
        boolean z2;
        Intrinsics.f(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return true;
        }
        IntRange s2 = StringsKt__StringsKt.s(charSequence);
        if (!(s2 instanceof Collection) || !((Collection) s2).isEmpty()) {
            Iterator<Integer> it = s2.iterator();
            while (it.hasNext()) {
                if (!CharsKt__CharJVMKt.c(charSequence.charAt(((IntIterator) it).nextInt()))) {
                    z2 = false;
                    break;
                }
            }
        }
        z2 = true;
        if (z2) {
            return true;
        }
        return false;
    }

    public static final boolean j(String str, int i2, String other, int i3, int i4, boolean z2) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(other, "other");
        if (!z2) {
            return str.regionMatches(i2, other, i3, i4);
        }
        return str.regionMatches(z2, i2, other, i3, i4);
    }

    public static final String k(String str, String oldValue, String newValue, boolean z2) {
        int a2;
        Intrinsics.f(str, "<this>");
        Intrinsics.f(oldValue, "oldValue");
        Intrinsics.f(newValue, "newValue");
        int i2 = 0;
        int v2 = StringsKt__StringsKt.v(str, oldValue, 0, z2);
        if (v2 < 0) {
            return str;
        }
        int length = oldValue.length();
        a2 = RangesKt___RangesKt.a(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 >= 0) {
            StringBuilder sb = new StringBuilder(length2);
            do {
                sb.append((CharSequence) str, i2, v2);
                sb.append(newValue);
                i2 = v2 + length;
                if (v2 >= str.length()) {
                    break;
                }
                v2 = StringsKt__StringsKt.v(str, oldValue, v2 + a2, z2);
            } while (v2 > 0);
            sb.append((CharSequence) str, i2, str.length());
            String sb2 = sb.toString();
            Intrinsics.e(sb2, "stringBuilder.append(this, i, length).toString()");
            return sb2;
        }
        throw new OutOfMemoryError();
    }

    public static /* synthetic */ String l(String str, String str2, String str3, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return k(str, str2, str3, z2);
    }

    public static final boolean m(String str, String prefix, boolean z2) {
        Intrinsics.f(str, "<this>");
        Intrinsics.f(prefix, "prefix");
        if (!z2) {
            return str.startsWith(prefix);
        }
        return j(str, 0, prefix, 0, prefix.length(), z2);
    }

    public static /* synthetic */ boolean n(String str, String str2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return m(str, str2, z2);
    }
}
