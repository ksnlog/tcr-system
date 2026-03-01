package org.ligi.passandroid.ui.edit;

import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.UPCEANWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EANHelperKt {
    public static final String a() {
        int m2;
        String B;
        int m3;
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
        IntRange intRange2 = new IntRange(0, 9);
        m3 = CollectionsKt__IterablesKt.m(intRange2, 10);
        ArrayList<String> arrayList2 = new ArrayList(m3);
        Iterator<Integer> it2 = intRange2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(B + ((IntIterator) it2).nextInt());
        }
        for (String str : arrayList2) {
            if (d(str)) {
                return str;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final String b() {
        int m2;
        String B;
        int m3;
        IntRange intRange = new IntRange(0, 6);
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
        IntRange intRange2 = new IntRange(0, 9);
        m3 = CollectionsKt__IterablesKt.m(intRange2, 10);
        ArrayList<String> arrayList2 = new ArrayList(m3);
        Iterator<Integer> it2 = intRange2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(B + ((IntIterator) it2).nextInt());
        }
        for (String str : arrayList2) {
            if (e(str)) {
                return str;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final boolean c(String payload, UPCEANWriter writer) {
        Intrinsics.f(payload, "payload");
        Intrinsics.f(writer, "writer");
        try {
            writer.c(payload);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static final boolean d(String payload) {
        Intrinsics.f(payload, "payload");
        return c(payload, new EAN13Writer());
    }

    public static final boolean e(String payload) {
        Intrinsics.f(payload, "payload");
        return c(payload, new EAN8Writer());
    }
}
