package org.ligi.passandroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.ui.UnzipPassController;
import org.ligi.passandroid.ui.UnzipPassDialog$show$AlertDialogUpdater$run$spec$2;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnzipPassDialog$show$AlertDialogUpdater$run$spec$2 implements UnzipPassController.FailCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f2907a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ProgressDialog f2908b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnzipPassDialog$show$AlertDialogUpdater$run$spec$2(Activity activity, ProgressDialog progressDialog) {
        this.f2907a = activity;
        this.f2908b = progressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Activity activity, ProgressDialog dialog, String reason) {
        Intrinsics.f(activity, "$activity");
        Intrinsics.f(reason, "$reason");
        UnzipPassDialog unzipPassDialog = UnzipPassDialog.f2898a;
        Intrinsics.e(dialog, "dialog");
        if (!UnzipPassDialog.c(unzipPassDialog, activity, dialog)) {
            return;
        }
        String string = activity.getString(2131755131);
        Intrinsics.e(string, "activity.getString(R.str…g.invalid_passbook_title)");
        UnzipPassDialog.b(unzipPassDialog, activity, string, reason);
    }

    @Override // org.ligi.passandroid.ui.UnzipPassController.FailCallback
    public void a(final String reason) {
        Intrinsics.f(reason, "reason");
        final Activity activity = this.f2907a;
        final ProgressDialog progressDialog = this.f2908b;
        activity.runOnUiThread(new Runnable() { // from class: e0.p0
            @Override // java.lang.Runnable
            public final void run() {
                UnzipPassDialog$show$AlertDialogUpdater$run$spec$2.c(activity, progressDialog, reason);
            }
        });
    }
}
