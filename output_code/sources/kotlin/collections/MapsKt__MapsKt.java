package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    public static <K, V> Map<K, V> d() {
        EmptyMap emptyMap = EmptyMap.f786d;
        Intrinsics.d(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static <K, V> Map<K, V> e(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.f(pairs, "pairs");
        if (pairs.length > 0) {
            return l(pairs, new LinkedHashMap(MapsKt.a(pairs.length)));
        }
        return MapsKt.d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<K, V> f(Map<K, ? extends V> map) {
        Intrinsics.f(map, "<this>");
        int size = map.size();
        if (size != 0) {
            if (size == 1) {
                return MapsKt__MapsJVMKt.c(map);
            }
            return map;
        }
        return MapsKt.d();
    }

    public static final <K, V> void g(Map<? super K, ? super V> map, Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.f(map, "<this>");
        Intrinsics.f(pairs, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairs) {
            map.put((K) pair.a(), (V) pair.b());
        }
    }

    public static final <K, V> void h(Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.f(map, "<this>");
        Intrinsics.f(pairs, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairs) {
            map.put((K) pair.a(), (V) pair.b());
        }
    }

    public static <K, V> Map<K, V> i(Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Pair<? extends K, ? extends V> next;
        Intrinsics.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    return j(iterable, new LinkedHashMap(MapsKt.a(collection.size())));
                }
                if (iterable instanceof List) {
                    next = (Pair<? extends K, ? extends V>) ((List) iterable).get(0);
                } else {
                    next = iterable.iterator().next();
                }
                return MapsKt__MapsJVMKt.b(next);
            }
            return MapsKt.d();
        }
        return f(j(iterable, new LinkedHashMap()));
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M j(Iterable<? extends Pair<? extends K, ? extends V>> iterable, M destination) {
        Intrinsics.f(iterable, "<this>");
        Intrinsics.f(destination, "destination");
        g(destination, iterable);
        return destination;
    }

    public static <K, V> Map<K, V> k(Map<? extends K, ? extends V> map) {
        Intrinsics.f(map, "<this>");
        int size = map.size();
        if (size != 0) {
            if (size != 1) {
                return m(map);
            }
            return MapsKt__MapsJVMKt.c(map);
        }
        return MapsKt.d();
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M l(Pair<? extends K, ? extends V>[] pairArr, M destination) {
        Intrinsics.f(pairArr, "<this>");
        Intrinsics.f(destination, "destination");
        h(destination, pairArr);
        return destination;
    }

    public static final <K, V> Map<K, V> m(Map<? extends K, ? extends V> map) {
        Intrinsics.f(map, "<this>");
        return new LinkedHashMap(map);
    }
}
