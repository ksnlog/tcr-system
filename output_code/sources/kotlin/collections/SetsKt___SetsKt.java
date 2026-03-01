package kotlin.collections;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SetsKt___SetsKt extends SetsKt__SetsKt {
    public static <T> Set<T> d(Set<? extends T> set, T t2) {
        int a2;
        Intrinsics.f(set, "<this>");
        a2 = MapsKt__MapsJVMKt.a(set.size() + 1);
        LinkedHashSet linkedHashSet = new LinkedHashSet(a2);
        linkedHashSet.addAll(set);
        linkedHashSet.add(t2);
        return linkedHashSet;
    }
}
