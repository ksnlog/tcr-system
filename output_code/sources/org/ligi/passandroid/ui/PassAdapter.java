package org.ligi.passandroid.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.kaxt.ContextExtensionsKt;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.PassStoreProjection;
import org.ligi.passandroid.model.Settings;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.PassAdapter;
import org.ligi.passandroid.ui.pass_view_holder.CondensedPassViewHolder;
import org.ligi.passandroid.ui.pass_view_holder.PassViewHolder;
import org.ligi.passandroid.ui.pass_view_holder.VerbosePassViewHolder;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassAdapter extends RecyclerView.Adapter<PassViewHolder> implements KoinComponent {

    /* renamed from: g  reason: collision with root package name */
    private final AppCompatActivity f2749g;

    /* renamed from: h  reason: collision with root package name */
    private final PassStoreProjection f2750h;

    /* renamed from: i  reason: collision with root package name */
    private final Lazy f2751i;

    /* renamed from: j  reason: collision with root package name */
    private final Lazy f2752j;

    /* renamed from: k  reason: collision with root package name */
    private final List<Pass> f2753k;

    public PassAdapter(AppCompatActivity passListActivity, PassStoreProjection passStoreProjection) {
        Lazy a2;
        Lazy a3;
        Intrinsics.f(passListActivity, "passListActivity");
        Intrinsics.f(passStoreProjection, "passStoreProjection");
        this.f2749g = passListActivity;
        this.f2750h = passStoreProjection;
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.f2602a;
        a2 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new PassAdapter$special$$inlined$inject$default$1(this, null, null));
        this.f2751i = a2;
        a3 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new PassAdapter$special$$inlined$inject$default$2(this, null, null));
        this.f2752j = a3;
        this.f2753k = passStoreProjection.getPassList();
    }

    private final PassStore E() {
        return (PassStore) this.f2751i.getValue();
    }

    private final Settings F() {
        return (Settings) this.f2752j.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void H(PassAdapter this$0, Pass pass, View view) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(pass, "$pass");
        this$0.E().setCurrentPass(pass);
        ContextExtensionsKt.a(this$0.f2749g, PassViewActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean I(CardView root, View view) {
        Intrinsics.f(root, "$root");
        Snackbar.m0(root, 2131755274, 0).X();
        return true;
    }

    /* renamed from: G */
    public void r(PassViewHolder viewHolder, int i2) {
        Intrinsics.f(viewHolder, "viewHolder");
        final Pass pass = this.f2750h.getPassList().get(i2);
        viewHolder.Q(pass, E(), this.f2749g);
        final CardView V = viewHolder.V();
        V.setOnClickListener(new View.OnClickListener() { // from class: e0.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassAdapter.H(PassAdapter.this, pass, view);
            }
        });
        V.setOnLongClickListener(new View.OnLongClickListener() { // from class: e0.l
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean I;
                I = PassAdapter.I(V, view);
                return I;
            }
        });
    }

    /* renamed from: J */
    public PassViewHolder t(ViewGroup viewGroup, int i2) {
        Intrinsics.f(viewGroup, "viewGroup");
        CardView inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2131492997, viewGroup, false);
        Intrinsics.d(inflate, "null cannot be cast to non-null type androidx.cardview.widget.CardView");
        CardView cardView = inflate;
        if (F().isCondensedModeEnabled()) {
            return new CondensedPassViewHolder(cardView);
        }
        return new VerbosePassViewHolder(cardView);
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }

    public int j() {
        return this.f2753k.size();
    }

    public long k(int i2) {
        return i2;
    }
}
