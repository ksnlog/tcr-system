package org.ligi.passandroid.ui;

import android.app.Activity;
import android.view.WindowManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.kaxt.WindowManagerExtensionsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassViewHelper$windowWidth$2 extends Lambda implements Function0<Integer> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassViewHelper f2868d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassViewHelper$windowWidth$2(PassViewHelper passViewHelper) {
        super(0);
        this.f2868d = passViewHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final Integer mo2invoke() {
        Activity activity;
        activity = this.f2868d.f2864a;
        WindowManager windowManager = activity.getWindowManager();
        Intrinsics.e(windowManager, "context.windowManager");
        return Integer.valueOf(WindowManagerExtensionsKt.a(windowManager).x);
    }
}
