package org.ligi.passandroid.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.comparator.PassSortOrder;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassStoreProjection {
    private List<? extends Pass> passList;
    private final PassSortOrder passSortOrder;
    private final PassStore passStore;
    private final String topic;

    public PassStoreProjection(PassStore passStore, String topic, PassSortOrder passSortOrder) {
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(topic, "topic");
        this.passStore = passStore;
        this.topic = topic;
        this.passSortOrder = passSortOrder;
        this.passList = new ArrayList();
        refresh();
    }

    public final List<Pass> getPassList() {
        return this.passList;
    }

    public final void refresh() {
        List<Pass> passListByTopic = this.passStore.getClassifier().getPassListByTopic(this.topic);
        PassSortOrder passSortOrder = this.passSortOrder;
        if (passSortOrder != null) {
            Collections.sort(passListByTopic, passSortOrder.toComparator());
        }
        this.passList = passListByTopic;
    }

    public /* synthetic */ PassStoreProjection(PassStore passStore, String str, PassSortOrder passSortOrder, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(passStore, str, (i2 & 4) != 0 ? null : passSortOrder);
    }
}
