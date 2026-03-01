package kotlin.jvm.internal;

import java.util.Iterator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ArrayIteratorKt {
    public static final <T> Iterator<T> a(T[] array) {
        Intrinsics.f(array, "array");
        return new ArrayIterator(array);
    }
}
