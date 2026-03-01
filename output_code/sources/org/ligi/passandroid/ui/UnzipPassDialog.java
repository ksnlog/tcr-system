package org.ligi.passandroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.InputStreamWithSource;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.ui.UnzipPassDialog;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnzipPassDialog {

    /* renamed from: a */
    public static final UnzipPassDialog f2898a = new UnzipPassDialog();

    private UnzipPassDialog() {
    }

    public static final /* synthetic */ void b(UnzipPassDialog unzipPassDialog, Activity activity, String str, String str2) {
        unzipPassDialog.d(activity, str, str2);
    }

    public static final /* synthetic */ boolean c(UnzipPassDialog unzipPassDialog, Activity activity, ProgressDialog progressDialog) {
        return unzipPassDialog.f(activity, progressDialog);
    }

    public final void d(final Activity activity, String str, String str2) {
        new AlertDialog.Builder(activity).w(str).k(str2).r(17039370, new DialogInterface.OnClickListener() { // from class: e0.n0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                UnzipPassDialog.e(activity, dialogInterface, i2);
            }
        }).z();
    }

    public static final void e(Activity activity, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(activity, "$activity");
        activity.finish();
    }

    public final boolean f(Activity activity, ProgressDialog progressDialog) {
        if (!activity.isFinishing() && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
                return true;
            } catch (IllegalArgumentException unused) {
            }
        }
        return false;
    }

    public final void g(InputStreamWithSource ins, Activity activity, PassStore passStore, Function1<? super String, Unit> callAfterFinishOnUIThread) {
        Intrinsics.f(ins, "ins");
        Intrinsics.f(activity, "activity");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(callAfterFinishOnUIThread, "callAfterFinishOnUIThread");
        if (activity.isFinishing()) {
            return;
        }
        ProgressDialog show = ProgressDialog.show(activity, activity.getString(2131755319), activity.getString(2131755318), true);
        show.setCancelable(false);
        new Thread(new UnzipPassDialog$show$AlertDialogUpdater(ins, activity, passStore, show, callAfterFinishOnUIThread)).start();
    }
}
