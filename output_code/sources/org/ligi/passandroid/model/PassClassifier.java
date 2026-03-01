package org.ligi.passandroid.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PassClassifier {
    private final PassStore passStore;
    private final Map<String, String> topicByIdMap;

    public PassClassifier(Map<String, String> topicByIdMap, PassStore passStore) {
        Intrinsics.f(topicByIdMap, "topicByIdMap");
        Intrinsics.f(passStore, "passStore");
        this.topicByIdMap = topicByIdMap;
        this.passStore = passStore;
    }

    public final List<Pass> getPassListByTopic(String topic) {
        List<Pass> s2;
        Intrinsics.f(topic, "topic");
        Map<String, String> map = this.topicByIdMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Intrinsics.a(entry.getValue(), topic)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            arrayList.add(this.passStore.getPassbookForId((String) entry2.getKey()));
        }
        s2 = CollectionsKt___CollectionsKt.s(arrayList);
        return s2;
    }

    public final String getTopic(Pass pass, String str) {
        Intrinsics.f(pass, "pass");
        Intrinsics.f(str, "default");
        return getTopic(pass.getId(), str);
    }

    public final Map<String, String> getTopicByIdMap() {
        return this.topicByIdMap;
    }

    public final String getTopicWithOffset(Pass pass, int i2) {
        int y2;
        Object r2;
        Intrinsics.f(pass, "pass");
        y2 = CollectionsKt___CollectionsKt.y(getTopics(), getTopic(pass, ""));
        r2 = CollectionsKt___CollectionsKt.r(getTopics(), y2 + i2);
        return (String) r2;
    }

    public final Set<String> getTopics() {
        Set<String> L;
        L = CollectionsKt___CollectionsKt.L(this.topicByIdMap.values());
        return L;
    }

    public final void moveToTopic(Pass pass, String newTopic) {
        Intrinsics.f(pass, "pass");
        Intrinsics.f(newTopic, "newTopic");
        this.topicByIdMap.put(pass.getId(), newTopic);
        processDataChange();
    }

    public void processDataChange() {
        boolean z2;
        Map<String, String> map = this.topicByIdMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().length() == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<String> arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            arrayList.add((String) entry2.getValue());
        }
        for (String str : arrayList) {
            this.topicByIdMap.remove(str);
        }
        this.passStore.notifyChange();
    }

    public final void removePass(String id) {
        Intrinsics.f(id, "id");
        this.topicByIdMap.remove(id);
        processDataChange();
    }

    public final String getTopic(String id, String str) {
        Intrinsics.f(id, "id");
        Intrinsics.f(str, "default");
        String str2 = this.topicByIdMap.get(id);
        if (str2 != null) {
            return str2;
        }
        if (str.length() > 0) {
            this.topicByIdMap.put(id, str);
            processDataChange();
        }
        return str;
    }
}
