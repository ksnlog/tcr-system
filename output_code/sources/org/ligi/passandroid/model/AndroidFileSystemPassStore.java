package org.ligi.passandroid.model;

import android.content.Context;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.channels.ConflatedBroadcastChannel;
import okio.BufferedSink;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Sink;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.reader.AppleStylePassReader;
import org.ligi.passandroid.reader.PassReader;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidFileSystemPassStore implements PassStore, KoinComponent {
    private final Lazy classifier$delegate;
    private final Context context;
    private Pass currentPass;
    private final Moshi moshi;
    private final HashMap<String, Pass> passMap;
    private final File path;
    private final Lazy tracker$delegate;
    private final ConflatedBroadcastChannel<PassStoreUpdateEvent> updateChannel;

    public AndroidFileSystemPassStore(Context context, Settings settings, Moshi moshi) {
        Lazy a2;
        Lazy b2;
        Intrinsics.f(context, "context");
        Intrinsics.f(settings, "settings");
        Intrinsics.f(moshi, "moshi");
        this.context = context;
        this.moshi = moshi;
        this.updateChannel = new ConflatedBroadcastChannel<>();
        this.path = settings.getPassesDir();
        this.passMap = new HashMap<>();
        a2 = LazyKt__LazyJVMKt.a(KoinPlatformTools.f2602a.b(), new AndroidFileSystemPassStore$special$$inlined$inject$default$1(this, null, null));
        this.tracker$delegate = a2;
        b2 = LazyKt__LazyJVMKt.b(new AndroidFileSystemPassStore$classifier$2(settings, this));
        this.classifier$delegate = b2;
    }

    private final Tracker getTracker() {
        return (Tracker) this.tracker$delegate.getValue();
    }

    private final Pass readPass(String str) {
        boolean z2;
        File pathForID = getPathForID(str);
        String language = this.context.getResources().getConfiguration().locale.getLanguage();
        Pass pass = null;
        if (pathForID.exists() && pathForID.isDirectory()) {
            File file = new File(pathForID, "main.json");
            if (file.exists()) {
                z2 = false;
                try {
                    pass = (Pass) this.moshi.c(PassImpl.class).fromJson(Okio.b(Okio.i(file)));
                } catch (JsonDataException unused) {
                    getTracker().c("invalid main.json", false);
                }
            } else {
                z2 = true;
            }
            if (pass == null && new File(pathForID, "data.json").exists()) {
                pass = PassReader.f2681a.a(pathForID);
                new File(pathForID, "data.json").delete();
            }
            if (pass == null && new File(pathForID, "pass.json").exists()) {
                AppleStylePassReader appleStylePassReader = AppleStylePassReader.f2677a;
                Intrinsics.e(language, "language");
                pass = appleStylePassReader.g(pathForID, language, this.context, getTracker());
            }
            if (pass != null) {
                if (z2) {
                    save(pass);
                }
                mo0getPassMap().put(str, pass);
                notifyChange();
            }
        }
        return pass;
    }

    @Override // org.ligi.passandroid.model.PassStore
    public boolean deletePassWithId(String id) {
        boolean e2;
        Intrinsics.f(id, "id");
        e2 = FilesKt__UtilsKt.e(getPathForID(id));
        if (e2) {
            mo0getPassMap().remove(id);
            getClassifier().removePass(id);
            notifyChange();
        }
        return e2;
    }

    @Override // org.ligi.passandroid.model.PassStore
    public PassClassifier getClassifier() {
        return (PassClassifier) this.classifier$delegate.getValue();
    }

    @Override // org.ligi.passandroid.model.PassStore
    public Pass getCurrentPass() {
        return this.currentPass;
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }

    @Override // org.ligi.passandroid.model.PassStore
    public Pass getPassbookForId(String id) {
        Intrinsics.f(id, "id");
        Pass pass = mo0getPassMap().get(id);
        if (pass == null) {
            return readPass(id);
        }
        return pass;
    }

    @Override // org.ligi.passandroid.model.PassStore
    public File getPathForID(String id) {
        Intrinsics.f(id, "id");
        return new File(this.path, id);
    }

    @Override // org.ligi.passandroid.model.PassStore
    public void notifyChange() {
        BuildersKt__Builders_commonKt.b(GlobalScope.f979d, null, null, new AndroidFileSystemPassStore$notifyChange$1(this, null), 3, null);
    }

    @Override // org.ligi.passandroid.model.PassStore
    public void save(Pass pass) {
        Sink g2;
        Intrinsics.f(pass, "pass");
        JsonAdapter c2 = this.moshi.c(PassImpl.class);
        File pathForID = getPathForID(pass.getId());
        if (!pathForID.exists()) {
            pathForID.mkdirs();
        }
        g2 = Okio__JvmOkioKt.g(new File(pathForID, "main.json"), false, 1, null);
        BufferedSink a2 = Okio.a(g2);
        c2.toJson(a2, (BufferedSink) ((PassImpl) pass));
        a2.close();
        mo0getPassMap().put(pass.getId(), pass);
    }

    @Override // org.ligi.passandroid.model.PassStore
    public void setCurrentPass(Pass pass) {
        this.currentPass = pass;
    }

    @Override // org.ligi.passandroid.model.PassStore
    public void syncPassStoreWithClassifier(String defaultTopic) {
        int i2;
        Intrinsics.f(defaultTopic, "defaultTopic");
        Set<String> keySet = getClassifier().getTopicByIdMap().keySet();
        ArrayList<String> arrayList = new ArrayList();
        Iterator<T> it = keySet.iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (getPassbookForId((String) next) == null) {
                i2 = 1;
            }
            if (i2 != 0) {
                arrayList.add(next);
            }
        }
        for (String str : arrayList) {
            getClassifier().getTopicByIdMap().remove(str);
        }
        File[] listFiles = this.path.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            while (i2 < length) {
                File file = listFiles[i2];
                PassClassifier classifier = getClassifier();
                String name = file.getName();
                Intrinsics.e(name, "it.name");
                classifier.getTopic(name, defaultTopic);
                i2++;
            }
        }
    }

    @Override // org.ligi.passandroid.model.PassStore
    /* renamed from: getPassMap */
    public HashMap<String, Pass> mo0getPassMap() {
        return this.passMap;
    }

    @Override // org.ligi.passandroid.model.PassStore
    /* renamed from: getUpdateChannel  reason: collision with other method in class */
    public ConflatedBroadcastChannel<PassStoreUpdateEvent> mo1getUpdateChannel() {
        return this.updateChannel;
    }
}
