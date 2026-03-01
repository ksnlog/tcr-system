package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Regex implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    public static final Companion f915e = new Companion(null);

    /* renamed from: d  reason: collision with root package name */
    private final Pattern f916d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Regex(Pattern nativePattern) {
        Intrinsics.f(nativePattern, "nativePattern");
        this.f916d = nativePattern;
    }

    public final boolean a(CharSequence input) {
        Intrinsics.f(input, "input");
        return this.f916d.matcher(input).matches();
    }

    public final String b(CharSequence input, String replacement) {
        Intrinsics.f(input, "input");
        Intrinsics.f(replacement, "replacement");
        String replaceAll = this.f916d.matcher(input).replaceAll(replacement);
        Intrinsics.e(replaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return replaceAll;
    }

    public final List<String> c(CharSequence input, int i2) {
        List<String> b2;
        Intrinsics.f(input, "input");
        StringsKt__StringsKt.G(i2);
        Matcher matcher = this.f916d.matcher(input);
        if (i2 != 1 && matcher.find()) {
            int i3 = 10;
            if (i2 > 0) {
                i3 = RangesKt___RangesKt.c(i2, 10);
            }
            ArrayList arrayList = new ArrayList(i3);
            int i4 = i2 - 1;
            int i5 = 0;
            do {
                arrayList.add(input.subSequence(i5, matcher.start()).toString());
                i5 = matcher.end();
                if (i4 >= 0 && arrayList.size() == i4) {
                    break;
                }
            } while (matcher.find());
            arrayList.add(input.subSequence(i5, input.length()).toString());
            return arrayList;
        }
        b2 = CollectionsKt__CollectionsJVMKt.b(input.toString());
        return b2;
    }

    public String toString() {
        String pattern = this.f916d.toString();
        Intrinsics.e(pattern, "nativePattern.toString()");
        return pattern;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Regex(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.f(r2, r0)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.lang.String r0 = "compile(pattern)"
            kotlin.jvm.internal.Intrinsics.e(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex.<init>(java.lang.String):void");
    }
}
