package org.ligi.passandroid.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassEditActivity$passViewHelper$2 extends Lambda implements Function0<PassViewHelper> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassEditActivity f2774d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassEditActivity$passViewHelper$2(PassEditActivity passEditActivity) {
        super(0);
        this.f2774d = passEditActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final PassViewHelper mo2invoke() {
        return new PassViewHelper(this.f2774d);
    }
}
