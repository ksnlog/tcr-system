package org.ligi.passandroid.ui;

import android.text.Editable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassEditActivity$onCreate$2 extends Lambda implements Function1<Editable, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassEditActivity f2773d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassEditActivity$onCreate$2(PassEditActivity passEditActivity) {
        super(1);
        this.f2773d = passEditActivity;
    }

    public final void a(Editable it) {
        PassImpl passImpl;
        Intrinsics.f(it, "it");
        passImpl = this.f2773d.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        passImpl.setDescription(String.valueOf(it));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Editable editable) {
        a(editable);
        return Unit.f763a;
    }
}
