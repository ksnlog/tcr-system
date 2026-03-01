package org.ligi.passandroid.ui;

import android.app.ProgressDialog;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ExtractURLAsIphoneActivity$progressDialog$2 extends Lambda implements Function0<ProgressDialog> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ExtractURLAsIphoneActivity f2740d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtractURLAsIphoneActivity$progressDialog$2(ExtractURLAsIphoneActivity extractURLAsIphoneActivity) {
        super(0);
        this.f2740d = extractURLAsIphoneActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final ProgressDialog mo2invoke() {
        return new ProgressDialog(this.f2740d);
    }
}
