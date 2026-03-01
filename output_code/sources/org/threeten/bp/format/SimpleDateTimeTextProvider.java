package org.threeten.bp.format;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SimpleDateTimeTextProvider extends DateTimeTextProvider {

    /* renamed from: b  reason: collision with root package name */
    private static final Comparator<Map.Entry<String, Long>> f3329b = new Comparator<Map.Entry<String, Long>>() { // from class: org.threeten.bp.format.SimpleDateTimeTextProvider.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getKey().length() - entry.getKey().length();
        }
    };

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class LocaleStore {

        /* renamed from: a  reason: collision with root package name */
        private final Map<TextStyle, Map<Long, String>> f3330a;

        /* renamed from: b  reason: collision with root package name */
        private final Map<TextStyle, List<Map.Entry<String, Long>>> f3331b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LocaleStore(Map<TextStyle, Map<Long, String>> map) {
            this.f3330a = map;
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (TextStyle textStyle : map.keySet()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry<Long, String> entry : map.get(textStyle).entrySet()) {
                    hashMap2.put(entry.getValue(), SimpleDateTimeTextProvider.e(entry.getValue(), entry.getKey()));
                }
                ArrayList arrayList2 = new ArrayList(hashMap2.values());
                Collections.sort(arrayList2, SimpleDateTimeTextProvider.f3329b);
                hashMap.put(textStyle, arrayList2);
                arrayList.addAll(arrayList2);
                hashMap.put(null, arrayList);
            }
            Collections.sort(arrayList, SimpleDateTimeTextProvider.f3329b);
            this.f3331b = hashMap;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String a(long j2, TextStyle textStyle) {
            Map<Long, String> map = this.f3330a.get(textStyle);
            if (map != null) {
                return map.get(Long.valueOf(j2));
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Iterator<Map.Entry<String, Long>> b(TextStyle textStyle) {
            List<Map.Entry<String, Long>> list = this.f3331b.get(textStyle);
            if (list != null) {
                return list.iterator();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <A, B> Map.Entry<A, B> e(A a2, B b2) {
        return new AbstractMap.SimpleImmutableEntry(a2, b2);
    }
}
