package kotlin.collections;

import java.util.Collections;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SetsKt__SetsJVMKt {
    public static <T> Set<T> a(T t2) {
        Set<T> singleton = Collections.singleton(t2);
        Intrinsics.e(singleton, "singleton(element)");
        return singleton;
    }
}
