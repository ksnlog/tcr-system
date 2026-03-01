package org.ligi.passandroid.model;

import com.squareup.moshi.Moshi;
import java.io.File;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidFileSystemPassStore$classifier$2 extends Lambda implements Function0<FileBackedPassClassifier> {
    final /* synthetic */ Settings $settings;
    final /* synthetic */ AndroidFileSystemPassStore this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidFileSystemPassStore$classifier$2(Settings settings, AndroidFileSystemPassStore androidFileSystemPassStore) {
        super(0);
        this.$settings = settings;
        this.this$0 = androidFileSystemPassStore;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final FileBackedPassClassifier mo2invoke() {
        Moshi moshi;
        File file = new File(this.$settings.getStateDir(), "classifier_state.json");
        AndroidFileSystemPassStore androidFileSystemPassStore = this.this$0;
        moshi = androidFileSystemPassStore.moshi;
        return new FileBackedPassClassifier(file, androidFileSystemPassStore, moshi);
    }
}
