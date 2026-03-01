package org.ligi.passandroid.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassEditActivity$refreshCallback$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassEditActivity f2777d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassEditActivity$refreshCallback$1(PassEditActivity passEditActivity) {
        super(0);
        this.f2777d = passEditActivity;
    }

    public final void a() {
        PassImpl passImpl;
        PassEditActivity passEditActivity = this.f2777d;
        passImpl = passEditActivity.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        passEditActivity.E0(passImpl);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
