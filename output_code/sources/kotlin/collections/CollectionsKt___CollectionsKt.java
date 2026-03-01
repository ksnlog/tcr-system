package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__AppendableKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CollectionsKt___CollectionsKt extends CollectionsKt___CollectionsJvmKt {
    public static final <T> String A(Iterable<? extends T> iterable, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(separator, "separator");
        Intrinsics.f(prefix, "prefix");
        Intrinsics.f(postfix, "postfix");
        Intrinsics.f(truncated, "truncated");
        String sb = ((StringBuilder) z(iterable, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.e(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    public static /* synthetic */ String B(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        CharSequence charSequence5;
        int i4;
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence6 = "";
        if ((i3 & 2) != 0) {
            charSequence5 = charSequence6;
        } else {
            charSequence5 = charSequence2;
        }
        if ((i3 & 4) == 0) {
            charSequence6 = charSequence3;
        }
        if ((i3 & 8) != 0) {
            i4 = -1;
        } else {
            i4 = i2;
        }
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return A(iterable, charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public static <T> List<T> C(Collection<? extends T> collection, Iterable<? extends T> elements) {
        Intrinsics.f(collection, "<this>");
        Intrinsics.f(elements, "elements");
        if (elements instanceof Collection) {
            Collection collection2 = (Collection) elements;
            ArrayList arrayList = new ArrayList(collection.size() + collection2.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        CollectionsKt__MutableCollectionsKt.o(arrayList2, elements);
        return arrayList2;
    }

    public static <T> List<T> D(Collection<? extends T> collection, T t2) {
        Intrinsics.f(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(t2);
        return arrayList;
    }

    public static <T> List<T> E(Collection<? extends T> collection, T[] elements) {
        Intrinsics.f(collection, "<this>");
        Intrinsics.f(elements, "elements");
        ArrayList arrayList = new ArrayList(collection.size() + elements.length);
        arrayList.addAll(collection);
        CollectionsKt__MutableCollectionsKt.p(arrayList, elements);
        return arrayList;
    }

    public static <T> List<T> F(Iterable<? extends T> iterable, int i2) {
        boolean z2;
        List<T> j2;
        List<T> b2;
        List<T> H;
        List<T> f2;
        Intrinsics.f(iterable, "<this>");
        int i3 = 0;
        if (i2 >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i2 == 0) {
                f2 = CollectionsKt__CollectionsKt.f();
                return f2;
            }
            if (iterable instanceof Collection) {
                if (i2 >= ((Collection) iterable).size()) {
                    H = H(iterable);
                    return H;
                } else if (i2 == 1) {
                    b2 = CollectionsKt__CollectionsJVMKt.b(u(iterable));
                    return b2;
                }
            }
            ArrayList arrayList = new ArrayList(i2);
            for (T t2 : iterable) {
                arrayList.add(t2);
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            j2 = CollectionsKt__CollectionsKt.j(arrayList);
            return j2;
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    public static final <T, C extends Collection<? super T>> C G(Iterable<? extends T> iterable, C destination) {
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(destination, "destination");
        for (T t2 : iterable) {
            destination.add(t2);
        }
        return destination;
    }

    public static <T> List<T> H(Iterable<? extends T> iterable) {
        List<T> j2;
        List<T> f2;
        Object next;
        List<T> b2;
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    return J(collection);
                }
                if (iterable instanceof List) {
                    next = ((List) iterable).get(0);
                } else {
                    next = iterable.iterator().next();
                }
                b2 = CollectionsKt__CollectionsJVMKt.b(next);
                return b2;
            }
            f2 = CollectionsKt__CollectionsKt.f();
            return f2;
        }
        j2 = CollectionsKt__CollectionsKt.j(I(iterable));
        return j2;
    }

    public static final <T> List<T> I(Iterable<? extends T> iterable) {
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return J((Collection) iterable);
        }
        return (List) G(iterable, new ArrayList());
    }

    public static final <T> List<T> J(Collection<? extends T> collection) {
        Intrinsics.f(collection, "<this>");
        return new ArrayList(collection);
    }

    public static <T> Set<T> K(Iterable<? extends T> iterable) {
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        return (Set) G(iterable, new LinkedHashSet());
    }

    public static <T> Set<T> L(Iterable<? extends T> iterable) {
        Set<T> b2;
        Object next;
        Set<T> a2;
        int a3;
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    a3 = MapsKt__MapsJVMKt.a(collection.size());
                    return (Set) G(iterable, new LinkedHashSet(a3));
                }
                if (iterable instanceof List) {
                    next = ((List) iterable).get(0);
                } else {
                    next = iterable.iterator().next();
                }
                a2 = SetsKt__SetsJVMKt.a(next);
                return a2;
            }
            b2 = SetsKt__SetsKt.b();
            return b2;
        }
        return SetsKt__SetsKt.c((Set) G(iterable, new LinkedHashSet()));
    }

    public static <T, R> List<Pair<T, R>> M(Iterable<? extends T> iterable, Iterable<? extends R> other) {
        int m2;
        int m3;
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(other, "other");
        Iterator<? extends T> it = iterable.iterator();
        Iterator<? extends R> it2 = other.iterator();
        m2 = CollectionsKt__IterablesKt.m(iterable, 10);
        m3 = CollectionsKt__IterablesKt.m(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(m2, m3));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(TuplesKt.a(it.next(), it2.next()));
        }
        return arrayList;
    }

    public static <T> boolean q(Iterable<? extends T> iterable, T t2) {
        int y2;
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(t2);
        }
        y2 = y(iterable, t2);
        if (y2 >= 0) {
            return true;
        }
        return false;
    }

    public static <T> T r(Iterable<? extends T> iterable, int i2) {
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) x((List) iterable, i2);
        }
        if (i2 < 0) {
            return null;
        }
        int i3 = 0;
        for (T t2 : iterable) {
            int i4 = i3 + 1;
            if (i2 == i3) {
                return t2;
            }
            i3 = i4;
        }
        return null;
    }

    public static <T> List<T> s(Iterable<? extends T> iterable) {
        Intrinsics.f(iterable, "<this>");
        return (List) t(iterable, new ArrayList());
    }

    public static final <C extends Collection<? super T>, T> C t(Iterable<? extends T> iterable, C destination) {
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(destination, "destination");
        for (T t2 : iterable) {
            if (t2 != null) {
                destination.add(t2);
            }
        }
        return destination;
    }

    public static final <T> T u(Iterable<? extends T> iterable) {
        Object v2;
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof List) {
            v2 = v((List) iterable);
            return (T) v2;
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static <T> T v(List<? extends T> list) {
        Intrinsics.f(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static <T> T w(List<? extends T> list) {
        Intrinsics.f(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final <T> T x(List<? extends T> list, int i2) {
        int g2;
        Intrinsics.f(list, "<this>");
        if (i2 >= 0) {
            g2 = CollectionsKt__CollectionsKt.g(list);
            if (i2 <= g2) {
                return list.get(i2);
            }
        }
        return null;
    }

    public static <T> int y(Iterable<? extends T> iterable, T t2) {
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(t2);
        }
        int i2 = 0;
        for (T t3 : iterable) {
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.l();
            }
            if (Intrinsics.a(t2, t3)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static final <T, A extends Appendable> A z(Iterable<? extends T> iterable, A buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(buffer, "buffer");
        Intrinsics.f(separator, "separator");
        Intrinsics.f(prefix, "prefix");
        Intrinsics.f(postfix, "postfix");
        Intrinsics.f(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (T t2 : iterable) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            StringsKt__AppendableKt.a(buffer, t2, function1);
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }
}
