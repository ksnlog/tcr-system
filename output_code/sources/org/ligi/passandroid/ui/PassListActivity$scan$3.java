package org.ligi.passandroid.ui;

import android.content.Context;
import android.content.Intent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.scan.PassScanActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassListActivity$scan$3 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassListActivity f2812d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassListActivity$scan$3(PassListActivity passListActivity) {
        super(0);
        this.f2812d = passListActivity;
    }

    public final void a() {
        this.f2812d.startActivity(new Intent((Context) this.f2812d, (Class<?>) PassScanActivity.class));
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
