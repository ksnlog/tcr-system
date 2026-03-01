package org.ligi.passandroid.ui;

import androidx.fragment.app.FragmentManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.model.PassClassifier;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassListActivity$adapter$2 extends Lambda implements Function0<PassTopicFragmentPagerAdapter> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassListActivity f2806d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassListActivity$adapter$2(PassListActivity passListActivity) {
        super(0);
        this.f2806d = passListActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final PassTopicFragmentPagerAdapter mo2invoke() {
        PassClassifier classifier = this.f2806d.n0().getClassifier();
        FragmentManager supportFragmentManager = this.f2806d.Q();
        Intrinsics.e(supportFragmentManager, "supportFragmentManager");
        return new PassTopicFragmentPagerAdapter(classifier, supportFragmentManager);
    }
}
