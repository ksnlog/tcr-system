package org.ligi.passandroid.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.ActivityExtensionsKt;
import org.ligi.passandroid.model.PassStoreProjection;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassType;
import org.ligi.passandroid.ui.PassViewActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassViewActivity extends PassViewActivityBase {
    private CollectionPagerAdapter K;
    private ViewPager2 L;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class CollectionPagerAdapter extends FragmentStateAdapter {

        /* renamed from: o  reason: collision with root package name */
        private PassStoreProjection f2846o;

        /* renamed from: p  reason: collision with root package name */
        final /* synthetic */ PassViewActivity f2847p;

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public /* synthetic */ class WhenMappings {

            /* renamed from: a  reason: collision with root package name */
            public static final /* synthetic */ int[] f2848a;

            static {
                int[] iArr = new int[PassType.values().length];
                try {
                    iArr[PassType.PKBOARDING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                f2848a = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CollectionPagerAdapter(PassViewActivity passViewActivity, FragmentActivity fa, PassStoreProjection passStoreProjection) {
            super(fa);
            Intrinsics.f(fa, "fa");
            Intrinsics.f(passStoreProjection, "passStoreProjection");
            this.f2847p = passViewActivity;
            this.f2846o = passStoreProjection;
        }

        public Fragment E(int i2) {
            Fragment passViewFragment;
            Pass W = W(i2);
            if (WhenMappings.f2848a[W.getType().ordinal()] == 1) {
                passViewFragment = new PassViewPKFragment();
            } else {
                passViewFragment = new PassViewFragment();
            }
            passViewFragment.A1(BundleKt.a(new Pair[]{TuplesKt.a("uuid", W.getId())}));
            return passViewFragment;
        }

        public final Pass W(int i2) {
            return this.f2846o.getPassList().get(i2);
        }

        public final int X(Pass pass) {
            Intrinsics.f(pass, "pass");
            return this.f2846o.getPassList().indexOf(pass);
        }

        public final void Y() {
            this.f2846o.refresh();
        }

        public int j() {
            return this.f2846o.getPassList().size();
        }
    }

    protected void Z() {
        super/*androidx.fragment.app.FragmentActivity*/.Z();
        k0(findViewById(2131296778));
        q0();
        t0();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(524288);
        }
        ActivityExtensionsKt.a(this);
        setContentView(2131492894);
        this.K = new CollectionPagerAdapter(this, this, new PassStoreProjection(n0(), n0().getClassifier().getTopic(s0(), ""), o0().getSortOrder()));
        ViewPager2 findViewById = findViewById(2131296616);
        Intrinsics.e(findViewById, "findViewById(R.id.pager)");
        ViewPager2 viewPager2 = findViewById;
        this.L = viewPager2;
        ViewPager2 viewPager22 = null;
        if (viewPager2 == null) {
            Intrinsics.p("viewPager");
            viewPager2 = null;
        }
        RecyclerView.Adapter adapter = this.K;
        if (adapter == null) {
            Intrinsics.p("pagerAdapter");
            adapter = null;
        }
        viewPager2.setAdapter(adapter);
        ViewPager2 viewPager23 = this.L;
        if (viewPager23 == null) {
            Intrinsics.p("viewPager");
            viewPager23 = null;
        }
        CollectionPagerAdapter collectionPagerAdapter = this.K;
        if (collectionPagerAdapter == null) {
            Intrinsics.p("pagerAdapter");
            collectionPagerAdapter = null;
        }
        viewPager23.setCurrentItem(collectionPagerAdapter.X(s0()));
        ViewPager2 viewPager24 = this.L;
        if (viewPager24 == null) {
            Intrinsics.p("viewPager");
        } else {
            viewPager22 = viewPager24;
        }
        viewPager22.g(new ViewPager2.OnPageChangeCallback() { // from class: org.ligi.passandroid.ui.PassViewActivity$onCreate$1
            public void c(int i2) {
                PassViewActivity.CollectionPagerAdapter collectionPagerAdapter2;
                PassViewActivity passViewActivity = PassViewActivity.this;
                collectionPagerAdapter2 = passViewActivity.K;
                if (collectionPagerAdapter2 == null) {
                    Intrinsics.p("pagerAdapter");
                    collectionPagerAdapter2 = null;
                }
                passViewActivity.u0(collectionPagerAdapter2.W(i2));
                PassViewActivity.this.n0().setCurrentPass(PassViewActivity.this.s0());
            }
        });
    }

    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.f(menu, "menu");
        getMenuInflater().inflate(2131623938, menu);
        getMenuInflater().inflate(2131623941, menu);
        getMenuInflater().inflate(2131623940, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        if (item.getItemId() == 16908332) {
            Intent a2 = NavUtils.a(this);
            if (a2 != null) {
                if (NavUtils.f(this, a2)) {
                    TaskStackBuilder.e(this).b(a2).f();
                    finish();
                } else {
                    NavUtils.e(this, a2);
                }
                return true;
            }
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.f(menu, "menu");
        menu.findItem(2131296553).setVisible(!s0().getLocations().isEmpty());
        menu.findItem(2131296557).setVisible(PassViewActivityBase.J.a(s0()));
        menu.findItem(2131296493).setVisible(ShortcutManagerCompat.a(this));
        return super.onPrepareOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    public void t0() {
        super.t0();
        CollectionPagerAdapter collectionPagerAdapter = this.K;
        if (collectionPagerAdapter == null) {
            Intrinsics.p("pagerAdapter");
            collectionPagerAdapter = null;
        }
        collectionPagerAdapter.Y();
    }
}
