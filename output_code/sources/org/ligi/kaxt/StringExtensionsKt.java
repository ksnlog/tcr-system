package org.ligi.kaxt;

import android.graphics.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StringExtensionsKt {
    public static final int a(String str, int i2) {
        boolean n2;
        boolean n3;
        if (str != null) {
            n2 = StringsKt__StringsJVMKt.n(str, "rgb", false, 2, null);
            if (n2) {
                return b(str, i2);
            }
            n3 = StringsKt__StringsJVMKt.n(str, "#", false, 2, null);
            if (n3) {
                try {
                    return Color.parseColor(str);
                } catch (Exception unused) {
                    return i2;
                }
            }
            return i2;
        }
        return i2;
    }

    private static final int b(String str, int i2) {
        Matcher matcher = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)").matcher(str);
        if (matcher.matches()) {
            Integer valueOf = Integer.valueOf(matcher.group(1));
            if (valueOf == null) {
                Intrinsics.m();
            }
            int intValue = (valueOf.intValue() << 16) | (-16777216);
            Integer valueOf2 = Integer.valueOf(matcher.group(2));
            if (valueOf2 == null) {
                Intrinsics.m();
            }
            int intValue2 = intValue | (valueOf2.intValue() << 8);
            Integer valueOf3 = Integer.valueOf(matcher.group(3));
            if (valueOf3 == null) {
                Intrinsics.m();
            }
            return valueOf3.intValue() | intValue2;
        }
        return i2;
    }
}
