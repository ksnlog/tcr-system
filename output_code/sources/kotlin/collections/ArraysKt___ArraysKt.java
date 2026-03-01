package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final <T> boolean j(T[] tArr, T t2) {
        Intrinsics.f(tArr, "<this>");
        if (n(tArr, t2) >= 0) {
            return true;
        }
        return false;
    }

    public static <T> List<T> k(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        return (List) l(tArr, new ArrayList());
    }

    public static final <C extends Collection<? super T>, T> C l(T[] tArr, C destination) {
        Intrinsics.f(tArr, "<this>");
        Intrinsics.f(destination, "destination");
        for (T t2 : tArr) {
            if (t2 != null) {
                destination.add(t2);
            }
        }
        return destination;
    }

    public static final <T> int m(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        return tArr.length - 1;
    }

    public static final <T> int n(T[] tArr, T t2) {
        Intrinsics.f(tArr, "<this>");
        int i2 = 0;
        if (t2 == null) {
            int length = tArr.length;
            while (i2 < length) {
                if (tArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i2 < length2) {
            if (Intrinsics.a(t2, tArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static <T> T o(T[] tArr) {
        boolean z2;
        Intrinsics.f(tArr, "<this>");
        if (tArr.length == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return tArr[m(tArr)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static char p(char[] cArr) {
        Intrinsics.f(cArr, "<this>");
        int length = cArr.length;
        if (length != 0) {
            if (length == 1) {
                return cArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static <T> T q(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    public static <T> List<T> r(T[] tArr) {
        List<T> f2;
        List<T> b2;
        List<T> s2;
        Intrinsics.f(tArr, "<this>");
        int length = tArr.length;
        if (length != 0) {
            if (length != 1) {
                s2 = s(tArr);
                return s2;
            }
            b2 = CollectionsKt__CollectionsJVMKt.b(tArr[0]);
            return b2;
        }
        f2 = CollectionsKt__CollectionsKt.f();
        return f2;
    }

    public static <T> List<T> s(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        return new ArrayList(CollectionsKt__CollectionsKt.c(tArr));
    }
}
