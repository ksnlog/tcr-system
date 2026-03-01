package org.ligi.passandroid.model;

import java.io.File;
import java.util.Map;
import kotlinx.coroutines.channels.BroadcastChannel;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface PassStore {
    boolean deletePassWithId(String str);

    PassClassifier getClassifier();

    Pass getCurrentPass();

    /* renamed from: getPassMap */
    Map<String, Pass> mo0getPassMap();

    Pass getPassbookForId(String str);

    File getPathForID(String str);

    /* renamed from: getUpdateChannel */
    BroadcastChannel<PassStoreUpdateEvent> mo1getUpdateChannel();

    void notifyChange();

    void save(Pass pass);

    void setCurrentPass(Pass pass);

    void syncPassStoreWithClassifier(String str);
}
