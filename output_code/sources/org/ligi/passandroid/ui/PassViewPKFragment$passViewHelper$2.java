package org.ligi.passandroid.ui;

import androidx.fragment.app.FragmentActivity;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassViewPKFragment$passViewHelper$2 extends Lambda implements Function0<PassViewHelper> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassViewPKFragment f2875d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassViewPKFragment$passViewHelper$2(PassViewPKFragment passViewPKFragment) {
        super(0);
        this.f2875d = passViewPKFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final PassViewHelper mo2invoke() {
        FragmentActivity s1 = this.f2875d.s1();
        Intrinsics.e(s1, "requireActivity()");
        return new PassViewHelper(s1);
    }
}
