package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CollectionsKt__CollectionsJVMKt {
    public static final <T> Object[] a(T[] tArr, boolean z2) {
        Intrinsics.f(tArr, "<this>");
        if (!z2 || !Intrinsics.a(tArr.getClass(), Object[].class)) {
            Object[] copyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
            Intrinsics.e(copyOf, "copyOf(this, this.size, Array<Any?>::class.java)");
            return copyOf;
        }
        return tArr;
    }

    public static <T> List<T> b(T t2) {
        List<T> singletonList = Collections.singletonList(t2);
        Intrinsics.e(singletonList, "singletonList(element)");
        return singletonList;
    }
}
