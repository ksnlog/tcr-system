package org.ligi.passandroid.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NavUtils;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import net.steamcrafted.loadtoast.R$drawable;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.kaxt.ContextExtensionsKt;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.maps.PassbookMapsFacade;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.printing.PrintHelperKt;
import org.ligi.passandroid.ui.PassMenuOptions;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassMenuOptions implements KoinComponent {

    /* renamed from: d  reason: collision with root package name */
    private final Activity f2826d;

    /* renamed from: e  reason: collision with root package name */
    private final Pass f2827e;

    /* renamed from: f  reason: collision with root package name */
    private final Lazy f2828f;

    /* renamed from: g  reason: collision with root package name */
    private final Lazy f2829g;

    /* renamed from: h  reason: collision with root package name */
    private final Lazy f2830h;

    public PassMenuOptions(Activity activity, Pass pass) {
        Lazy a2;
        Lazy a3;
        Lazy a4;
        Intrinsics.f(activity, "activity");
        Intrinsics.f(pass, "pass");
        this.f2826d = activity;
        this.f2827e = pass;
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.f2602a;
        a2 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new PassMenuOptions$special$$inlined$inject$default$1(this, null, null));
        this.f2828f = a2;
        a3 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new PassMenuOptions$special$$inlined$inject$default$2(this, null, null));
        this.f2829g = a3;
        a4 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new PassMenuOptions$special$$inlined$inject$default$3(this, null, null));
        this.f2830h = a4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f(CheckBox checkBox, String str, PassMenuOptions this$0, DialogInterface dialogInterface, int i2) {
        String l2;
        Intrinsics.f(this$0, "this$0");
        if (checkBox.isChecked()) {
            Intrinsics.c(str);
            l2 = StringsKt__StringsJVMKt.l(str, "file://", "", false, 4, null);
            new File(l2).delete();
        }
        this$0.c().deletePassWithId(this$0.f2827e.getId());
        if (this$0.f2826d instanceof PassViewActivityBase) {
            NavUtils.e(this$0.f2826d, new Intent(this$0.f2826d, PassListActivity.class));
        }
    }

    public final PassStore c() {
        return (PassStore) this.f2828f.getValue();
    }

    public final Tracker d() {
        return (Tracker) this.f2829g.getValue();
    }

    public final boolean e(MenuItem item) {
        boolean n2;
        Intrinsics.f(item, "item");
        switch (item.getItemId()) {
            case 2131296546:
                d().b("ui_action", "delete", "delete", null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this.f2826d);
                builder.k(this.f2826d.getString(2131755090));
                builder.w(this.f2826d.getString(2131755091));
                builder.f((int) R$drawable.f1804a);
                View inflate = LayoutInflater.from(this.f2826d).inflate(2131492903, (ViewGroup) null);
                final String source = this.f2827e.getSource(c());
                final CheckBox checkBox = (CheckBox) inflate.findViewById(2131296712);
                if (source != null) {
                    n2 = StringsKt__StringsJVMKt.n(source, "file://", false, 2, null);
                    if (n2) {
                        checkBox.setText(this.f2826d.getString(2131755089));
                        builder.y(inflate);
                    }
                }
                builder.s(this.f2826d.getString(2131755088), new DialogInterface.OnClickListener() { // from class: e0.y
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        PassMenuOptions.f(checkBox, source, this, dialogInterface, i2);
                    }
                });
                builder.m(17039369, (DialogInterface.OnClickListener) null);
                builder.z();
                return true;
            case 2131296547:
                d().b("ui_action", "share", "shared", null);
                c().setCurrentPass(this.f2827e);
                ContextExtensionsKt.a(this.f2826d, PassEditActivity.class);
                return true;
            case 2131296553:
                PassbookMapsFacade.b(this.f2826d);
                return true;
            case 2131296554:
                PrintHelperKt.a(this.f2826d, this.f2827e);
                return true;
            case 2131296556:
                d().b("ui_action", "share", "shared", null);
                new PassExportTaskAndShare(this.f2826d, c().getPathForID(this.f2827e.getId())).d();
                return true;
            default:
                return false;
        }
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }
}
