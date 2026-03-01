package org.ligi.passandroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.ui.PassExportTaskAndShare;
import org.ligi.passandroid.ui.PassExporter;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PassExportTaskAndShare implements KoinComponent {

    /* renamed from: d  reason: collision with root package name */
    private final Activity f2778d;

    /* renamed from: e  reason: collision with root package name */
    private final File f2779e;

    /* renamed from: f  reason: collision with root package name */
    private final Lazy f2780f;

    public PassExportTaskAndShare(Activity activity, File inputPath) {
        Lazy a2;
        Intrinsics.f(activity, "activity");
        Intrinsics.f(inputPath, "inputPath");
        this.f2778d = activity;
        this.f2779e = inputPath;
        a2 = LazyKt__LazyJVMKt.a(KoinPlatformTools.f2602a.b(), new PassExportTaskAndShare$special$$inlined$inject$default$1(this, null, null));
        this.f2780f = a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(final PassExporter passExporter, Handler handler, final PassExportTaskAndShare this$0, final ProgressDialog progressDialog, final File file) {
        Intrinsics.f(passExporter, "$passExporter");
        Intrinsics.f(handler, "$handler");
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(progressDialog, "$progressDialog");
        Intrinsics.f(file, "$file");
        passExporter.b();
        handler.post(new Runnable() { // from class: e0.s
            @Override // java.lang.Runnable
            public final void run() {
                PassExportTaskAndShare.f(PassExportTaskAndShare.this, progressDialog, passExporter, file);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f(PassExportTaskAndShare this$0, ProgressDialog progressDialog, PassExporter passExporter, File file) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(progressDialog, "$progressDialog");
        Intrinsics.f(passExporter, "$passExporter");
        Intrinsics.f(file, "$file");
        if (!this$0.f2778d.isFinishing() && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (passExporter.c() != null) {
            Tracker g2 = this$0.g();
            Exception c2 = passExporter.c();
            Intrinsics.c(c2);
            g2.a("passExporterException", c2, false);
            Activity activity = this$0.f2778d;
            Toast.makeText(activity, "could not export pass: " + passExporter.c(), 1).show();
            return;
        }
        Activity activity2 = this$0.f2778d;
        Uri f2 = FileProvider.f(activity2, activity2.getString(2131755045), file);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", this$0.f2778d.getString(2131755265));
        intent.putExtra("android.intent.extra.STREAM", f2);
        intent.setType("application/vnd.espass-espass");
        Activity activity3 = this$0.f2778d;
        activity3.startActivity(Intent.createChooser(intent, activity3.getString(2131755266)));
    }

    public final void d() {
        final File file = new File(this.f2778d.getFilesDir(), "share/share.espass");
        final PassExporter passExporter = new PassExporter(this.f2779e, file);
        final ProgressDialog progressDialog = new ProgressDialog(this.f2778d);
        progressDialog.setTitle(2131755289);
        progressDialog.setMessage(this.f2778d.getString(2131755275));
        progressDialog.show();
        final Handler handler = new Handler();
        new Thread(new Runnable() { // from class: e0.r
            @Override // java.lang.Runnable
            public final void run() {
                PassExportTaskAndShare.e(PassExporter.this, handler, this, progressDialog, file);
            }
        }).start();
    }

    public final Tracker g() {
        return (Tracker) this.f2780f.getValue();
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }
}
