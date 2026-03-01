package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    public static final <T> Collection<T> c(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        return new ArrayAsCollection(tArr, false);
    }

    public static final <T extends Comparable<? super T>> int d(List<? extends T> list, T t2, int i2, int i3) {
        int a2;
        Intrinsics.f(list, "<this>");
        k(list.size(), i2, i3);
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            a2 = ComparisonsKt__ComparisonsKt.a(list.get(i5), t2);
            if (a2 < 0) {
                i2 = i5 + 1;
            } else if (a2 > 0) {
                i4 = i5 - 1;
            } else {
                return i5;
            }
        }
        return -(i2 + 1);
    }

    public static /* synthetic */ int e(List list, Comparable comparable, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = list.size();
        }
        return d(list, comparable, i2, i3);
    }

    public static <T> List<T> f() {
        return EmptyList.f785d;
    }

    public static <T> int g(List<? extends T> list) {
        Intrinsics.f(list, "<this>");
        return list.size() - 1;
    }

    public static <T> List<T> h(T... elements) {
        List<T> f2;
        Intrinsics.f(elements, "elements");
        if (elements.length > 0) {
            return ArraysKt___ArraysJvmKt.c(elements);
        }
        f2 = f();
        return f2;
    }

    public static <T> List<T> i(T... elements) {
        Intrinsics.f(elements, "elements");
        if (elements.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new ArrayAsCollection(elements, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> j(List<? extends T> list) {
        List<T> f2;
        List<T> b2;
        Intrinsics.f(list, "<this>");
        int size = list.size();
        if (size != 0) {
            if (size == 1) {
                b2 = CollectionsKt__CollectionsJVMKt.b(list.get(0));
                return b2;
            }
            return list;
        }
        f2 = f();
        return f2;
    }

    private static final void k(int i2, int i3, int i4) {
        if (i3 <= i4) {
            if (i3 >= 0) {
                if (i4 <= i2) {
                    return;
                }
                throw new IndexOutOfBoundsException("toIndex (" + i4 + ") is greater than size (" + i2 + ").");
            }
            throw new IndexOutOfBoundsException("fromIndex (" + i3 + ") is less than zero.");
        }
        throw new IllegalArgumentException("fromIndex (" + i3 + ") is greater than toIndex (" + i4 + ").");
    }

    public static void l() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
