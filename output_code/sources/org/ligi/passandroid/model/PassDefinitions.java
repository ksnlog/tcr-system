package org.ligi.passandroid.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.ranges.RangesKt___RangesKt;
import org.ligi.passandroid.model.pass.PassType;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassDefinitions {
    public static final PassDefinitions INSTANCE = new PassDefinitions();
    private static final Map<String, PassType> NAME_TO_TYPE;
    private static final Map<PassType, String> TYPE_TO_NAME;

    static {
        Map<PassType, String> e2;
        int m2;
        int a2;
        int a3;
        e2 = MapsKt__MapsKt.e(TuplesKt.a(PassType.COUPON, "coupon"), TuplesKt.a(PassType.EVENT, "eventTicket"), TuplesKt.a(PassType.PKBOARDING, "boardingPass"), TuplesKt.a(PassType.GENERIC, "generic"), TuplesKt.a(PassType.LOYALTY, "storeCard"));
        TYPE_TO_NAME = e2;
        Set<Map.Entry<PassType, String>> entrySet = e2.entrySet();
        m2 = CollectionsKt__IterablesKt.m(entrySet, 10);
        a2 = MapsKt__MapsJVMKt.a(m2);
        a3 = RangesKt___RangesKt.a(a2, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(a3);
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Pair a4 = TuplesKt.a(entry.getValue(), entry.getKey());
            linkedHashMap.put(a4.c(), a4.d());
        }
        NAME_TO_TYPE = linkedHashMap;
    }

    private PassDefinitions() {
    }

    public final Map<String, PassType> getNAME_TO_TYPE() {
        return NAME_TO_TYPE;
    }

    public final Map<PassType, String> getTYPE_TO_NAME() {
        return TYPE_TO_NAME;
    }
}
