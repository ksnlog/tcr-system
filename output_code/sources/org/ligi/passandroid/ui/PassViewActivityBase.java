package org.ligi.passandroid.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.snackbar.Snackbar;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.InputStreamWithSource;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.State;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.PassViewActivityBase;
import org.ligi.passandroid.ui.UnzipPassController;
import permissions.dispatcher.ktx.ActivityExtensionsKt;
import permissions.dispatcher.ktx.PermissionsRequester;
@SuppressLint({"Registered"})
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PassViewActivityBase extends PassAndroidActivity {
    public static final Companion J = new Companion(null);
    public Pass H;
    private boolean I;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean a(Pass pass) {
            return ((pass != null ? pass.getWebServiceURL() : null) == null || pass.getPassIdent() == null || pass.getSerial() == null) ? false : true;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class MyUnzipFailCallback implements UnzipPassController.FailCallback {

        /* renamed from: a  reason: collision with root package name */
        private final Dialog f2850a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ PassViewActivityBase f2851b;

        public MyUnzipFailCallback(PassViewActivityBase passViewActivityBase, Dialog dlg) {
            Intrinsics.f(dlg, "dlg");
            this.f2851b = passViewActivityBase;
            this.f2850a = dlg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void c(PassViewActivityBase this$0, MyUnzipFailCallback this$1, String reason) {
            Intrinsics.f(this$0, "this$0");
            Intrinsics.f(this$1, "this$1");
            Intrinsics.f(reason, "$reason");
            if (!this$0.isFinishing()) {
                this$1.f2850a.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(this$0);
                builder.k("Could not update pass :( " + reason + ')').r(17039370, (DialogInterface.OnClickListener) null).z();
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.ligi.passandroid.ui.PassViewActivityBase, android.app.Activity] */
        @Override // org.ligi.passandroid.ui.UnzipPassController.FailCallback
        public void a(final String reason) {
            Intrinsics.f(reason, "reason");
            final ?? r0 = this.f2851b;
            r0.runOnUiThread(new Runnable() { // from class: e0.a0
                @Override // java.lang.Runnable
                public final void run() {
                    PassViewActivityBase.MyUnzipFailCallback.c(PassViewActivityBase.this, this, reason);
                }
            });
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class MyUnzipSuccessCallback implements UnzipPassController.SuccessCallback {

        /* renamed from: a  reason: collision with root package name */
        private final Dialog f2852a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ PassViewActivityBase f2853b;

        public MyUnzipSuccessCallback(PassViewActivityBase passViewActivityBase, Dialog dlg) {
            Intrinsics.f(dlg, "dlg");
            this.f2853b = passViewActivityBase;
            this.f2852a = dlg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void c(PassViewActivityBase this$0, MyUnzipSuccessCallback this$1, String uuid) {
            Intrinsics.f(this$0, "this$0");
            Intrinsics.f(this$1, "this$1");
            Intrinsics.f(uuid, "$uuid");
            if (this$0.isFinishing()) {
                return;
            }
            this$1.f2852a.dismiss();
            if (!Intrinsics.a(this$0.s0().getId(), uuid)) {
                this$0.n0().deletePassWithId(this$0.s0().getId());
            }
            this$0.n0().setCurrentPass(this$0.n0().getPassbookForId(uuid));
            Pass currentPass = this$0.n0().getCurrentPass();
            Intrinsics.c(currentPass);
            this$0.u0(currentPass);
            this$0.t0();
            Snackbar.m0(this$0.getWindow().getDecorView(), 2131755264, 0).X();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.ligi.passandroid.ui.PassViewActivityBase, android.app.Activity] */
        @Override // org.ligi.passandroid.ui.UnzipPassController.SuccessCallback
        public void a(final String uuid) {
            Intrinsics.f(uuid, "uuid");
            final ?? r0 = this.f2853b;
            r0.runOnUiThread(new Runnable() { // from class: e0.b0
                @Override // java.lang.Runnable
                public final void run() {
                    PassViewActivityBase.MyUnzipSuccessCallback.c(PassViewActivityBase.this, this, uuid);
                }
            });
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class UpdateAsync implements Runnable {

        /* renamed from: d  reason: collision with root package name */
        private ProgressDialog f2854d;

        public UpdateAsync() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void b(UpdateAsync this$0, PassViewActivityBase this$1) {
            Intrinsics.f(this$0, "this$0");
            Intrinsics.f(this$1, "this$1");
            ProgressDialog progressDialog = new ProgressDialog(this$1);
            this$0.f2854d = progressDialog;
            progressDialog.setMessage(this$1.getString(2131755092));
            ProgressDialog progressDialog2 = this$0.f2854d;
            if (progressDialog2 == null) {
                Intrinsics.p("dlg");
                progressDialog2 = null;
            }
            progressDialog2.show();
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [org.ligi.passandroid.ui.PassViewActivityBase, android.app.Activity] */
        /* JADX WARN: Type inference failed for: r5v1, types: [android.content.Context, org.ligi.passandroid.ui.PassAndroidActivity, org.ligi.passandroid.ui.PassViewActivityBase] */
        @Override // java.lang.Runnable
        public void run() {
            Pass s0 = PassViewActivityBase.this.s0();
            final ?? r1 = PassViewActivityBase.this;
            r1.runOnUiThread(new Runnable() { // from class: e0.c0
                @Override // java.lang.Runnable
                public final void run() {
                    PassViewActivityBase.UpdateAsync.b(PassViewActivityBase.UpdateAsync.this, r1);
                }
            });
            OkHttpClient okHttpClient = new OkHttpClient();
            String str = s0.getWebServiceURL() + "/v1/passes/" + s0.getPassIdent() + '/' + s0.getSerial();
            Request.Builder g2 = new Request.Builder().g(str);
            g2.a("Authorization", "ApplePass " + s0.getAuthToken());
            try {
                Response k2 = okHttpClient.r(g2.b()).k();
                Intrinsics.e(k2, "client.newCall(request).execute()");
                ResponseBody b2 = k2.b();
                if (b2 != null) {
                    InputStream b3 = b2.b();
                    Intrinsics.e(b3, "body.byteStream()");
                    InputStreamWithSource inputStreamWithSource = new InputStreamWithSource(str, b3);
                    ?? r5 = PassViewActivityBase.this;
                    PassStore n0 = r5.n0();
                    PassViewActivityBase passViewActivityBase = PassViewActivityBase.this;
                    ProgressDialog progressDialog = this.f2854d;
                    ProgressDialog progressDialog2 = null;
                    if (progressDialog == null) {
                        Intrinsics.p("dlg");
                        progressDialog = null;
                    }
                    MyUnzipSuccessCallback myUnzipSuccessCallback = new MyUnzipSuccessCallback(passViewActivityBase, progressDialog);
                    PassViewActivityBase passViewActivityBase2 = PassViewActivityBase.this;
                    ProgressDialog progressDialog3 = this.f2854d;
                    if (progressDialog3 == null) {
                        Intrinsics.p("dlg");
                    } else {
                        progressDialog2 = progressDialog3;
                    }
                    UnzipPassController.InputStreamUnzipControllerSpec inputStreamUnzipControllerSpec = new UnzipPassController.InputStreamUnzipControllerSpec(inputStreamWithSource, r5, n0, myUnzipSuccessCallback, new MyUnzipFailCallback(passViewActivityBase2, progressDialog2));
                    inputStreamUnzipControllerSpec.g(true);
                    UnzipPassController.f2888d.e(inputStreamUnzipControllerSpec);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private final void r0() {
        PermissionsRequester a2;
        a2 = ActivityExtensionsKt.a(this, new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, (r13 & 2) != 0 ? null : null, (r13 & 4) != 0 ? null : null, (r13 & 8) != 0 ? null : null, new PassViewActivityBase$createShortcut$1(this));
        a2.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void v0() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.screenBrightness = 1.0f;
        window.setAttributes(attributes);
        this.I = true;
        invalidateOptionsMenu();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void w0() {
        String stringExtra = getIntent().getStringExtra("uuid");
        if (stringExtra != null) {
            n0().setCurrentPass(n0().getPassbookForId(stringExtra));
        }
        if (n0().getCurrentPass() == null) {
            n0().setCurrentPass(n0().getPassbookForId(State.INSTANCE.getLastSelectedPassUUID()));
        }
        if (n0().getCurrentPass() == null) {
            Tracker p0 = p0();
            p0.c("pass not present in " + this, false);
            finish();
            return;
        }
        Pass currentPass = n0().getCurrentPass();
        Intrinsics.c(currentPass);
        u0(currentPass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate(Bundle bundle) {
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        try {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
            Field declaredField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            declaredField.setAccessible(true);
            declaredField.setBoolean(viewConfiguration, false);
        } catch (Exception unused) {
        }
        w0();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.f(menu, "menu");
        getMenuInflater().inflate(2131623937, menu);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        if (new PassMenuOptions(this, s0()).e(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            case 2131296493:
                r0();
                return true;
            case 2131296552:
                v0();
                return true;
            case 2131296557:
                new Thread(new UpdateAsync()).start();
                return true;
            default:
                return super/*android.app.Activity*/.onOptionsItemSelected(item);
        }
    }

    protected void onPause() {
        super/*androidx.fragment.app.FragmentActivity*/.onPause();
        State.INSTANCE.setLastSelectedPassUUID(s0().getId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.f(menu, "menu");
        boolean onPrepareOptionsMenu = super/*android.app.Activity*/.onPrepareOptionsMenu(menu);
        boolean z2 = true;
        menu.findItem(2131296552).setVisible(!this.I);
        MenuItem findItem = menu.findItem(2131296554);
        if (Build.VERSION.SDK_INT < 19) {
            z2 = false;
        }
        findItem.setVisible(z2);
        return onPrepareOptionsMenu;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.ligi.passandroid.ui.PassAndroidActivity
    public void onResume() {
        super.onResume();
        q0();
        if (o0().isAutomaticLightEnabled()) {
            v0();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void q0() {
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.w(true);
        }
        ActionBar b03 = b0();
        if (b03 != null) {
            b03.s(true);
        }
    }

    public final Pass s0() {
        Pass pass = this.H;
        if (pass != null) {
            return pass;
        }
        Intrinsics.p("currentPass");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t0() {
    }

    public final void u0(Pass pass) {
        Intrinsics.f(pass, "<set-?>");
        this.H = pass;
    }
}
