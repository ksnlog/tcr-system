package kotlin.text;

import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static String S(String str, int i2) {
        boolean z2;
        int a2;
        Intrinsics.f(str, "<this>");
        if (i2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            a2 = RangesKt___RangesKt.a(str.length() - i2, 0);
            return U(str, a2);
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }

    public static char T(CharSequence charSequence) {
        boolean z2;
        Intrinsics.f(charSequence, "<this>");
        if (charSequence.length() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return charSequence.charAt(StringsKt__StringsKt.t(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final String U(String str, int i2) {
        boolean z2;
        int c2;
        Intrinsics.f(str, "<this>");
        if (i2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            c2 = RangesKt___RangesKt.c(i2, str.length());
            String substring = str.substring(0, c2);
            Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }
}
