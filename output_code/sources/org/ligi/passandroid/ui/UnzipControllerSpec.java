package org.ligi.passandroid.ui;

import android.content.Context;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.Settings;
import org.ligi.passandroid.ui.UnzipPassController;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class UnzipControllerSpec {

    /* renamed from: a  reason: collision with root package name */
    private File f2882a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f2883b;

    /* renamed from: c  reason: collision with root package name */
    private final PassStore f2884c;

    /* renamed from: d  reason: collision with root package name */
    private final UnzipPassController.SuccessCallback f2885d;

    /* renamed from: e  reason: collision with root package name */
    private final UnzipPassController.FailCallback f2886e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2887f;

    public UnzipControllerSpec(File targetPath, Context context, PassStore passStore, UnzipPassController.SuccessCallback successCallback, UnzipPassController.FailCallback failCallback) {
        Intrinsics.f(targetPath, "targetPath");
        Intrinsics.f(context, "context");
        Intrinsics.f(passStore, "passStore");
        this.f2882a = targetPath;
        this.f2883b = context;
        this.f2884c = passStore;
        this.f2885d = successCallback;
        this.f2886e = failCallback;
    }

    public final Context a() {
        return this.f2883b;
    }

    public final UnzipPassController.FailCallback b() {
        return this.f2886e;
    }

    public final UnzipPassController.SuccessCallback c() {
        return this.f2885d;
    }

    public final boolean d() {
        return this.f2887f;
    }

    public final PassStore e() {
        return this.f2884c;
    }

    public final File f() {
        return this.f2882a;
    }

    public final void g(boolean z2) {
        this.f2887f = z2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UnzipControllerSpec(Context context, PassStore passStore, UnzipPassController.SuccessCallback successCallback, UnzipPassController.FailCallback failCallback, Settings settings) {
        this(settings.getPassesDir(), context, passStore, successCallback, failCallback);
        Intrinsics.f(context, "context");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(settings, "settings");
    }
}
