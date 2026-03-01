package org.ligi.passandroid.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.ui.edit.ImageEditHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassEditActivity$pickWithPermissionCheck$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassEditActivity f2775d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ int f2776e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassEditActivity$pickWithPermissionCheck$1(PassEditActivity passEditActivity, int i2) {
        super(0);
        this.f2775d = passEditActivity;
        this.f2776e = i2;
    }

    public final void a() {
        ImageEditHelper v0;
        v0 = this.f2775d.v0();
        v0.c(this.f2776e);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
