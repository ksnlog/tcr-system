package org.ligi.passandroid.ui;

import android.app.Activity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.databinding.PassListBinding;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassListActivity$drawerToggle$2 extends Lambda implements Function0<ActionBarDrawerToggle> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassListActivity f2807d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassListActivity$drawerToggle$2(PassListActivity passListActivity) {
        super(0);
        this.f2807d = passListActivity;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.ligi.passandroid.ui.PassListActivity, android.app.Activity] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final ActionBarDrawerToggle mo2invoke() {
        PassListBinding passListBinding;
        ?? r1 = this.f2807d;
        passListBinding = ((PassListActivity) r1).H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        return new ActionBarDrawerToggle((Activity) r1, passListBinding.f2658c, 2131755094, 2131755093);
    }
}
