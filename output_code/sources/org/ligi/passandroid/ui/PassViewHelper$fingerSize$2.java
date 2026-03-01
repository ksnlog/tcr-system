package org.ligi.passandroid.ui;

import android.app.Activity;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassViewHelper$fingerSize$2 extends Lambda implements Function0<Integer> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassViewHelper f2867d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassViewHelper$fingerSize$2(PassViewHelper passViewHelper) {
        super(0);
        this.f2867d = passViewHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final Integer mo2invoke() {
        Activity activity;
        activity = this.f2867d.f2864a;
        return Integer.valueOf(activity.getResources().getDimensionPixelSize(2131165353));
    }
}
