package org.ligi.passandroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.InputStreamWithSource;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.ui.UnzipPassController;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnzipPassDialog$show$AlertDialogUpdater implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    private final Function1<String, Unit> f2899d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ InputStreamWithSource f2900e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Activity f2901f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ PassStore f2902g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ ProgressDialog f2903h;

    /* JADX WARN: Multi-variable type inference failed */
    public UnzipPassDialog$show$AlertDialogUpdater(InputStreamWithSource ins, Activity activity, PassStore passStore, ProgressDialog progressDialog, Function1<? super String, Unit> call_after_finish) {
        Intrinsics.f(ins, "$ins");
        Intrinsics.f(activity, "$activity");
        Intrinsics.f(passStore, "$passStore");
        Intrinsics.f(call_after_finish, "call_after_finish");
        this.f2900e = ins;
        this.f2901f = activity;
        this.f2902g = passStore;
        this.f2903h = progressDialog;
        this.f2899d = call_after_finish;
    }

    @Override // java.lang.Runnable
    public void run() {
        InputStreamWithSource inputStreamWithSource = this.f2900e;
        Activity activity = this.f2901f;
        UnzipPassController.f2888d.e(new UnzipPassController.InputStreamUnzipControllerSpec(inputStreamWithSource, activity, this.f2902g, new UnzipPassDialog$show$AlertDialogUpdater$run$spec$1(activity, this.f2903h, this), new UnzipPassDialog$show$AlertDialogUpdater$run$spec$2(this.f2901f, this.f2903h)));
    }
}
