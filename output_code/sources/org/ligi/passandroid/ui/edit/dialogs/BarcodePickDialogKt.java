package org.ligi.passandroid.ui.edit.dialogs;

import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.ActivityExtensionsKt;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.edit.BarcodeEditController;
import org.ligi.passandroid.ui.edit.dialogs.BarcodePickDialogKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BarcodePickDialogKt {
    public static final void b(AppCompatActivity context, final Function0<Unit> refreshCallback, final Pass pass, BarCode barCode) {
        Intrinsics.f(context, "context");
        Intrinsics.f(refreshCallback, "refreshCallback");
        Intrinsics.f(pass, "pass");
        Intrinsics.f(barCode, "barCode");
        View view = ActivityExtensionsKt.c(context, 2131492899, null, 2, null);
        Intrinsics.e(view, "view");
        final BarcodeEditController barcodeEditController = new BarcodeEditController(view, context, barCode);
        new AlertDialog.Builder(context).y(view).v(2131755095).m(17039360, (DialogInterface.OnClickListener) null).r(17039370, new DialogInterface.OnClickListener() { // from class: g0.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                BarcodePickDialogKt.c(Pass.this, barcodeEditController, refreshCallback, dialogInterface, i2);
            }
        }).z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Pass pass, BarcodeEditController barcodeEditController, Function0 refreshCallback, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(pass, "$pass");
        Intrinsics.f(barcodeEditController, "$barcodeEditController");
        Intrinsics.f(refreshCallback, "$refreshCallback");
        pass.setBarCode(barcodeEditController.k());
        refreshCallback.mo2invoke();
    }
}
