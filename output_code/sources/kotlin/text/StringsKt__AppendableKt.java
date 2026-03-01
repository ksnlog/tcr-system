package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StringsKt__AppendableKt {
    public static <T> void a(Appendable appendable, T t2, Function1<? super T, ? extends CharSequence> function1) {
        boolean z2;
        Intrinsics.f(appendable, "<this>");
        if (function1 != null) {
            appendable.append(function1.f(t2));
            return;
        }
        if (t2 == null) {
            z2 = true;
        } else {
            z2 = t2 instanceof CharSequence;
        }
        if (z2) {
            appendable.append((CharSequence) t2);
        } else if (t2 instanceof Character) {
            appendable.append(((Character) t2).charValue());
        } else {
            appendable.append(String.valueOf(t2));
        }
    }
}
