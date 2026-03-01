package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static <T> boolean o(Collection<? super T> collection, Iterable<? extends T> elements) {
        Intrinsics.f(collection, "<this>");
        Intrinsics.f(elements, "elements");
        if (elements instanceof Collection) {
            return collection.addAll((Collection) elements);
        }
        Iterator<? extends T> it = elements.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (collection.add((T) it.next())) {
                z2 = true;
            }
        }
        return z2;
    }

    public static final <T> boolean p(Collection<? super T> collection, T[] elements) {
        Intrinsics.f(collection, "<this>");
        Intrinsics.f(elements, "elements");
        return collection.addAll(ArraysKt___ArraysJvmKt.c(elements));
    }
}
