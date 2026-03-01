package org.ligi.passandroid.ui.edit;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ITFHelperKt {
    public static final String a() {
        int m2;
        String B;
        IntRange intRange = new IntRange(0, 11);
        m2 = CollectionsKt__IterablesKt.m(intRange, 10);
        ArrayList arrayList = new ArrayList(m2);
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            double random = Math.random();
            double d2 = 9;
            Double.isNaN(d2);
            arrayList.add(Integer.valueOf((int) (random * d2)));
        }
        B = CollectionsKt___CollectionsKt.B(arrayList, "", null, null, 0, null, null, 62, null);
        return B;
    }
}
