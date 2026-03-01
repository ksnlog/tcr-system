package org.ligi.passandroid.model;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class AndroidSettings$sharedPreferences$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ AndroidSettings this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidSettings$sharedPreferences$2(AndroidSettings androidSettings) {
        super(0);
        this.this$0 = androidSettings;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final SharedPreferences mo2invoke() {
        return PreferenceManager.b(this.this$0.getContext());
    }
}
