package org.ligi.passandroid.ui.edit.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.databinding.EditColorBinding;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.edit.dialogs.ColorPickDialogKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ColorPickDialogKt {
    public static final void b(Context context, final Pass pass, final Function0<Unit> refreshCallback) {
        Intrinsics.f(context, "context");
        Intrinsics.f(pass, "pass");
        Intrinsics.f(refreshCallback, "refreshCallback");
        final EditColorBinding c2 = EditColorBinding.c(LayoutInflater.from(context));
        Intrinsics.e(c2, "inflate(LayoutInflater.from(context))");
        c2.f2649b.setColor(pass.getAccentColor());
        c2.f2649b.setOldCenterColor(pass.getAccentColor());
        new AlertDialog.Builder(context).y(c2.b()).r(17039370, new DialogInterface.OnClickListener() { // from class: g0.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ColorPickDialogKt.c(Pass.this, c2, refreshCallback, dialogInterface, i2);
            }
        }).m(17039360, (DialogInterface.OnClickListener) null).v(2131755081).z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Pass pass, EditColorBinding inflate, Function0 refreshCallback, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(pass, "$pass");
        Intrinsics.f(inflate, "$inflate");
        Intrinsics.f(refreshCallback, "$refreshCallback");
        pass.setAccentColor(inflate.f2649b.getColor());
        refreshCallback.mo2invoke();
    }
}
