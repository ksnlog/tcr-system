package kotlin.collections;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    public static <T> Set<T> b() {
        return EmptySet.f787d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Set<T> c(Set<? extends T> set) {
        Intrinsics.f(set, "<this>");
        int size = set.size();
        if (size != 0) {
            if (size == 1) {
                return SetsKt.a(set.iterator().next());
            }
            return set;
        }
        return SetsKt.b();
    }
}
