package org.ligi.passandroid.ui;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassClassifier;
import org.ligi.passandroid.ui.PassListFragment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassTopicFragmentPagerAdapter extends FragmentStatePagerAdapter {

    /* renamed from: j  reason: collision with root package name */
    private final PassClassifier f2844j;

    /* renamed from: k  reason: collision with root package name */
    private String[] f2845k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassTopicFragmentPagerAdapter(PassClassifier passClassifier, FragmentManager fragmentManager) {
        super(fragmentManager);
        Intrinsics.f(passClassifier, "passClassifier");
        Intrinsics.f(fragmentManager, "fragmentManager");
        this.f2844j = passClassifier;
        i();
    }

    public int c() {
        String[] strArr = this.f2845k;
        if (strArr == null) {
            Intrinsics.p("topics");
            strArr = null;
        }
        return strArr.length;
    }

    public int d(Object object) {
        Intrinsics.f(object, "object");
        return -2;
    }

    public void i() {
        this.f2845k = (String[]) this.f2844j.getTopics().toArray(new String[0]);
        super/*androidx.viewpager.widget.PagerAdapter*/.i();
    }

    /* renamed from: r */
    public PassListFragment q(int i2) {
        PassListFragment.Companion companion = PassListFragment.k0;
        String[] strArr = this.f2845k;
        if (strArr == null) {
            Intrinsics.p("topics");
            strArr = null;
        }
        return companion.a(strArr[i2]);
    }

    /* renamed from: s */
    public String e(int i2) {
        String[] strArr = this.f2845k;
        if (strArr == null) {
            Intrinsics.p("topics");
            strArr = null;
        }
        return strArr[i2];
    }
}
