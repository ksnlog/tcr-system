package org.ligi.passandroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.ui.UnzipPassController;
import org.ligi.passandroid.ui.UnzipPassDialog$show$AlertDialogUpdater$run$spec$1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnzipPassDialog$show$AlertDialogUpdater$run$spec$1 implements UnzipPassController.SuccessCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f2904a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ProgressDialog f2905b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ UnzipPassDialog$show$AlertDialogUpdater f2906c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnzipPassDialog$show$AlertDialogUpdater$run$spec$1(Activity activity, ProgressDialog progressDialog, UnzipPassDialog$show$AlertDialogUpdater unzipPassDialog$show$AlertDialogUpdater) {
        this.f2904a = activity;
        this.f2905b = progressDialog;
        this.f2906c = unzipPassDialog$show$AlertDialogUpdater;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Activity activity, ProgressDialog dialog, UnzipPassDialog$show$AlertDialogUpdater this$0, String uuid) {
        boolean f2;
        Function1 function1;
        Intrinsics.f(activity, "$activity");
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(uuid, "$uuid");
        UnzipPassDialog unzipPassDialog = UnzipPassDialog.f2898a;
        Intrinsics.e(dialog, "dialog");
        f2 = unzipPassDialog.f(activity, dialog);
        if (!f2) {
            return;
        }
        function1 = this$0.f2899d;
        function1.f(uuid);
    }

    @Override // org.ligi.passandroid.ui.UnzipPassController.SuccessCallback
    public void a(final String uuid) {
        Intrinsics.f(uuid, "uuid");
        final Activity activity = this.f2904a;
        final ProgressDialog progressDialog = this.f2905b;
        final UnzipPassDialog$show$AlertDialogUpdater unzipPassDialog$show$AlertDialogUpdater = this.f2906c;
        activity.runOnUiThread(new Runnable() { // from class: e0.o0
            @Override // java.lang.Runnable
            public final void run() {
                UnzipPassDialog$show$AlertDialogUpdater$run$spec$1.c(activity, progressDialog, unzipPassDialog$show$AlertDialogUpdater, uuid);
            }
        });
    }
}
