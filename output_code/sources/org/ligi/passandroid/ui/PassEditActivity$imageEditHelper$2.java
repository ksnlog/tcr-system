package org.ligi.passandroid.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.ui.edit.ImageEditHelper;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassEditActivity$imageEditHelper$2 extends Lambda implements Function0<ImageEditHelper> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassEditActivity f2772d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassEditActivity$imageEditHelper$2(PassEditActivity passEditActivity) {
        super(0);
        this.f2772d = passEditActivity;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.ligi.passandroid.ui.PassEditActivity, android.app.Activity] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final ImageEditHelper mo2invoke() {
        ?? r1 = this.f2772d;
        return new ImageEditHelper(r1, r1.w0());
    }
}
