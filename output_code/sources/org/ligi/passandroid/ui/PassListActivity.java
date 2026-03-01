package org.ligi.passandroid.ui;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import net.steamcrafted.loadtoast.R$drawable;
import org.ligi.kaxt.ContextExtensionsKt;
import org.ligi.passandroid.databinding.PassListBinding;
import org.ligi.passandroid.functions.PassTemplatesKt;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.PassStoreProjection;
import org.ligi.passandroid.model.State;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.PassListActivity;
import org.ligi.snackengage.SnackEngage;
import org.ligi.snackengage.snacks.DefaultRateSnack;
import org.ligi.tracedroid.TraceDroid;
import org.ligi.tracedroid.sending.TraceDroidEmailSenderKt;
import permissions.dispatcher.ktx.ActivityExtensionsKt;
import permissions.dispatcher.ktx.PermissionsRequester;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassListActivity extends PassAndroidActivity {
    private PassListBinding H;
    private final Lazy I;
    private final Lazy J;

    public PassListActivity() {
        Lazy b2;
        Lazy b3;
        b2 = LazyKt__LazyJVMKt.b(new PassListActivity$drawerToggle$2(this));
        this.I = b2;
        b3 = LazyKt__LazyJVMKt.b(new PassListActivity$adapter$2(this));
        this.J = b3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void B0(PassListActivity this$0, View view) {
        String e2;
        Intrinsics.f(this$0, "this$0");
        PassStore n0 = this$0.n0();
        Resources resources = this$0.getResources();
        Intrinsics.e(resources, "resources");
        Pass a2 = PassTemplatesKt.a(n0, resources);
        PassListBinding passListBinding = this$0.H;
        PassListBinding passListBinding2 = null;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        passListBinding.f2664i.m();
        ContextExtensionsKt.a(this$0, PassEditActivity.class);
        PassListBinding passListBinding3 = this$0.H;
        if (passListBinding3 == null) {
            Intrinsics.p("binding");
            passListBinding3 = null;
        }
        if (passListBinding3.f2666k.getSelectedTabPosition() < 0) {
            e2 = this$0.getString(2131755312);
        } else {
            PassTopicFragmentPagerAdapter y0 = this$0.y0();
            PassListBinding passListBinding4 = this$0.H;
            if (passListBinding4 == null) {
                Intrinsics.p("binding");
            } else {
                passListBinding2 = passListBinding4;
            }
            e2 = y0.e(passListBinding2.f2666k.getSelectedTabPosition());
        }
        Intrinsics.e(e2, "if (binding.tabLayout.se…abPosition)\n            }");
        this$0.n0().getClassifier().moveToTopic(a2, e2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void C0(PassListActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        this$0.H0();
        PassListBinding passListBinding = this$0.H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        passListBinding.f2664i.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void D0(PassListActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        ContextExtensionsKt.c(this$0, "http://espass.it/examples");
        PassListBinding passListBinding = this$0.H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        passListBinding.f2664i.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void E0(PassListActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        this$0.A0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void F0(PassListActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this$0, "this$0");
        PassStore n0 = this$0.n0();
        String string = this$0.getString(2131755313);
        Intrinsics.e(string, "getString(R.string.topic_trash)");
        for (Pass pass : new PassStoreProjection(n0, string, null).getPassList()) {
            this$0.n0().deletePassWithId(pass.getId());
        }
    }

    private final void I0() {
        if (!x0()) {
            PassListBinding passListBinding = this.H;
            PassListBinding passListBinding2 = null;
            if (passListBinding == null) {
                Intrinsics.p("binding");
                passListBinding = null;
            }
            TabLayout tabLayout = passListBinding.f2666k;
            PassListBinding passListBinding3 = this.H;
            if (passListBinding3 == null) {
                Intrinsics.p("binding");
            } else {
                passListBinding2 = passListBinding3;
            }
            tabLayout.setupWithViewPager(passListBinding2.f2668m);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public final void J0() {
        PassListBinding passListBinding = this.H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        Snackbar.n0(passListBinding.f2664i, "no permission to scan", -2).X();
    }

    private final boolean x0() {
        int c2 = y0().c();
        PassListBinding passListBinding = this.H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        if (c2 != passListBinding.f2666k.getTabCount()) {
            return false;
        }
        int c3 = y0().c();
        for (int i2 = 0; i2 < c3; i2++) {
            PassListBinding passListBinding2 = this.H;
            if (passListBinding2 == null) {
                Intrinsics.p("binding");
                passListBinding2 = null;
            }
            TabLayout.Tab A = passListBinding2.f2666k.A(i2);
            if (A == null || !Intrinsics.a(y0().e(i2), A.i())) {
                return false;
            }
        }
        return true;
    }

    private final PassTopicFragmentPagerAdapter y0() {
        return (PassTopicFragmentPagerAdapter) this.J.getValue();
    }

    private final ActionBarDrawerToggle z0() {
        return (ActionBarDrawerToggle) this.I.getValue();
    }

    @TargetApi(19)
    public final void A0() {
        try {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            startActivityForResult(intent, 1000);
        } catch (ActivityNotFoundException unused) {
            PassListBinding passListBinding = this.H;
            if (passListBinding == null) {
                Intrinsics.p("binding");
                passListBinding = null;
            }
            Snackbar.n0(passListBinding.f2664i, "Unavailable", 0).X();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void G0() {
        int i2;
        y0().i();
        I0();
        invalidateOptionsMenu();
        boolean isEmpty = n0().getClassifier().getTopicByIdMap().isEmpty();
        PassListBinding passListBinding = this.H;
        PassListBinding passListBinding2 = null;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        TextView textView = passListBinding.f2659d;
        int i3 = 8;
        if (isEmpty) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        Set<String> topics = n0().getClassifier().getTopics();
        boolean z2 = true;
        if (!(topics instanceof Collection) || !topics.isEmpty()) {
            Iterator<T> it = topics.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!Intrinsics.a((String) it.next(), getString(2131755312))) {
                        z2 = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        PassListBinding passListBinding3 = this.H;
        if (passListBinding3 == null) {
            Intrinsics.p("binding");
        } else {
            passListBinding2 = passListBinding3;
        }
        TabLayout tabLayout = passListBinding2.f2666k;
        if (!z2) {
            i3 = 0;
        }
        tabLayout.setVisibility(i3);
    }

    @TargetApi(16)
    public final void H0() {
        PermissionsRequester a2;
        a2 = ActivityExtensionsKt.a(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, (r13 & 2) != 0 ? null : null, (r13 & 4) != 0 ? null : new PassListActivity$scan$1(this), (r13 & 8) != 0 ? null : new PassListActivity$scan$2(this), new PassListActivity$scan$3(this));
        a2.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super/*androidx.fragment.app.FragmentActivity*/.onActivityResult(i2, i3, intent);
        if (i2 == 1000 && i3 == -1 && intent != null) {
            Intent intent2 = new Intent((Context) this, (Class<?>) PassImportActivity.class);
            intent2.setData(intent.getData());
            startActivity(intent2);
        }
    }

    public void onBackPressed() {
        PassListBinding passListBinding = this.H;
        PassListBinding passListBinding2 = null;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        if (passListBinding.f2658c.C(8388611)) {
            PassListBinding passListBinding3 = this.H;
            if (passListBinding3 == null) {
                Intrinsics.p("binding");
            } else {
                passListBinding2 = passListBinding3;
            }
            passListBinding2.f2658c.d(8388611);
            return;
        }
        PassListBinding passListBinding4 = this.H;
        if (passListBinding4 == null) {
            Intrinsics.p("binding");
            passListBinding4 = null;
        }
        if (passListBinding4.f2664i.u()) {
            PassListBinding passListBinding5 = this.H;
            if (passListBinding5 == null) {
                Intrinsics.p("binding");
            } else {
                passListBinding2 = passListBinding5;
            }
            passListBinding2.f2664i.m();
            return;
        }
        super/*androidx.activity.ComponentActivity*/.onBackPressed();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.f(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        z0().f(newConfig);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        boolean z2;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        PassListBinding c2 = PassListBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        this.H = c2;
        if (c2 == null) {
            Intrinsics.p("binding");
            c2 = null;
        }
        setContentView(c2.b());
        PassListBinding passListBinding = this.H;
        if (passListBinding == null) {
            Intrinsics.p("binding");
            passListBinding = null;
        }
        k0(passListBinding.f2667l);
        if (TraceDroid.f2981c.d().length == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            p0().b("ui_event", "send", "stacktraces", null);
            if (o0().doTraceDroidEmailSend()) {
                TraceDroidEmailSenderKt.a("ligi+passandroid@ligi.de", this);
            }
        } else {
            p0().b("ui_event", "processFile", "updatenotice", null);
            PassListBinding passListBinding2 = this.H;
            if (passListBinding2 == null) {
                Intrinsics.p("binding");
                passListBinding2 = null;
            }
            SnackEngage.b(passListBinding2.f2664i).b(new DefaultRateSnack().l(-2)).a().a();
        }
        PassListBinding passListBinding3 = this.H;
        if (passListBinding3 == null) {
            Intrinsics.p("binding");
            passListBinding3 = null;
        }
        passListBinding3.f2658c.a(z0());
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.s(true);
        }
        PassListBinding passListBinding4 = this.H;
        if (passListBinding4 == null) {
            Intrinsics.p("binding");
            passListBinding4 = null;
        }
        passListBinding4.f2668m.setAdapter(y0());
        if (y0().c() > 0) {
            PassListBinding passListBinding5 = this.H;
            if (passListBinding5 == null) {
                Intrinsics.p("binding");
                passListBinding5 = null;
            }
            passListBinding5.f2668m.setCurrentItem(State.INSTANCE.getLastSelectedTab());
        }
        PassListBinding passListBinding6 = this.H;
        if (passListBinding6 == null) {
            Intrinsics.p("binding");
            passListBinding6 = null;
        }
        passListBinding6.f2668m.c(new ViewPager.OnPageChangeListener() { // from class: org.ligi.passandroid.ui.PassListActivity$onCreate$1
            public void a(int i2, float f2, int i3) {
            }

            public void b(int i2) {
            }

            public void c(int i2) {
                State.INSTANCE.setLastSelectedTab(i2);
                PassListActivity.this.invalidateOptionsMenu();
            }
        });
        PassStore n0 = n0();
        String string = getString(2131755312);
        Intrinsics.e(string, "getString(R.string.topic_new)");
        n0.syncPassStoreWithClassifier(string);
        G0();
        PassListBinding passListBinding7 = this.H;
        if (passListBinding7 == null) {
            Intrinsics.p("binding");
            passListBinding7 = null;
        }
        passListBinding7.f2660e.setOnClickListener(new View.OnClickListener() { // from class: e0.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassListActivity.B0(PassListActivity.this, view);
            }
        });
        PassListBinding passListBinding8 = this.H;
        if (passListBinding8 == null) {
            Intrinsics.p("binding");
            passListBinding8 = null;
        }
        passListBinding8.f2663h.setOnClickListener(new View.OnClickListener() { // from class: e0.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassListActivity.C0(PassListActivity.this, view);
            }
        });
        PassListBinding passListBinding9 = this.H;
        if (passListBinding9 == null) {
            Intrinsics.p("binding");
            passListBinding9 = null;
        }
        passListBinding9.f2661f.setOnClickListener(new View.OnClickListener() { // from class: e0.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassListActivity.D0(PassListActivity.this, view);
            }
        });
        if (Build.VERSION.SDK_INT >= 19) {
            PassListBinding passListBinding10 = this.H;
            if (passListBinding10 == null) {
                Intrinsics.p("binding");
                passListBinding10 = null;
            }
            passListBinding10.f2662g.setOnClickListener(new View.OnClickListener() { // from class: e0.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PassListActivity.E0(PassListActivity.this, view);
                }
            });
            PassListBinding passListBinding11 = this.H;
            if (passListBinding11 == null) {
                Intrinsics.p("binding");
                passListBinding11 = null;
            }
            passListBinding11.f2662g.setVisibility(0);
        }
        BuildersKt__Builders_commonKt.b(LifecycleOwnerKt.a(this), null, null, new PassListActivity$onCreate$6(this, null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.f(menu, "menu");
        getMenuInflater().inflate(2131623936, menu);
        return super/*android.app.Activity*/.onCreateOptionsMenu(menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        int itemId = item.getItemId();
        if (itemId != 2131296548) {
            if (itemId != 2131296550) {
                if (z0().g(item) || super/*android.app.Activity*/.onOptionsItemSelected(item)) {
                    return true;
                }
                return false;
            }
            ContextExtensionsKt.a(this, HelpActivity.class);
            return true;
        }
        new AlertDialog.Builder(this).k(getString(2131755099)).f((int) R$drawable.f1804a).w(getString(2131755100)).r(2131755101, new DialogInterface.OnClickListener() { // from class: e0.t
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                PassListActivity.F0(PassListActivity.this, dialogInterface, i2);
            }
        }).m(17039360, (DialogInterface.OnClickListener) null).z();
        return true;
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        z0().k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z2;
        Intrinsics.f(menu, "menu");
        MenuItem findItem = menu.findItem(2131296548);
        if (y0().c() > 0) {
            PassTopicFragmentPagerAdapter y0 = y0();
            PassListBinding passListBinding = this.H;
            if (passListBinding == null) {
                Intrinsics.p("binding");
                passListBinding = null;
            }
            if (Intrinsics.a(y0.e(passListBinding.f2668m.getCurrentItem()), getString(2131755313))) {
                z2 = true;
                findItem.setVisible(z2);
                return true;
            }
        }
        z2 = false;
        findItem.setVisible(z2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.ligi.passandroid.ui.PassAndroidActivity
    public void onResume() {
        super.onResume();
        y0().i();
        G0();
    }
}
