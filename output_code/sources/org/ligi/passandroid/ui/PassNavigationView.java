package org.ligi.passandroid.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.ui.PassNavigationView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassNavigationView extends NavigationView implements KoinComponent {
    private final Lazy A;
    private final Lazy B;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Lazy a2;
        Lazy b2;
        Intrinsics.f(context, "context");
        Intrinsics.f(attrs, "attrs");
        a2 = LazyKt__LazyJVMKt.a(KoinPlatformTools.f2602a.b(), new PassNavigationView$special$$inlined$inject$default$1(this, null, null));
        this.A = a2;
        b2 = LazyKt__LazyJVMKt.b(new PassNavigationView$marketUrl$2(context));
        this.B = b2;
    }

    private final String getMarketUrl() {
        return (String) this.B.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Intent q(int i2) {
        switch (i2) {
            case 2131296545:
                return r("https://play.google.com/apps/testing/org.ligi.passandroid");
            case 2131296549:
                return r("https://github.com/ligi/PassAndroid");
            case 2131296551:
                return r("https://transifex.com/projects/p/passandroid");
            case 2131296555:
                return new Intent(getContext(), PreferenceActivity.class);
            case 2131296556:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", getMarketUrl());
                intent.setType("text/plain");
                return intent;
            default:
                return null;
        }
    }

    private final Intent r(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri parse = Uri.parse(str);
        Intrinsics.e(parse, "parse(this)");
        intent.setData(parse);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean s(PassNavigationView this$0, MenuItem item) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(item, "item");
        Intent q2 = this$0.q(item.getItemId());
        if (q2 != null) {
            this$0.getContext().startActivity(q2);
            return true;
        }
        return false;
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }

    public final PassStore getPassStore() {
        return (PassStore) this.A.getValue();
    }

    @SuppressLint({"RestrictedApi"})
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // from class: e0.z
            public final boolean a(MenuItem menuItem) {
                boolean s2;
                s2 = PassNavigationView.s(PassNavigationView.this, menuItem);
                return s2;
            }
        });
        t();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void t() {
        int size = getPassStore().mo0getPassMap().size();
        TextView textView = (TextView) h(0).findViewById(2131296625);
        textView.setText(getContext().getString(2131755267, Integer.valueOf(size)));
        textView.setText(getContext().getString(2131755074, Integer.valueOf(getPassStore().getClassifier().getTopics().size())));
    }
}
