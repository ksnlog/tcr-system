package org.ligi.passandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.ligi.passandroid.databinding.PassRecyclerBinding;
import org.ligi.passandroid.functions.MoveHelperKt;
import org.ligi.passandroid.model.PassClassifier;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.PassStoreProjection;
import org.ligi.passandroid.model.Settings;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassListFragment extends Fragment {
    public static final Companion k0 = new Companion(null);

    /* renamed from: g0  reason: collision with root package name */
    private PassStoreProjection f2813g0;

    /* renamed from: h0  reason: collision with root package name */
    private PassAdapter f2814h0;

    /* renamed from: i0  reason: collision with root package name */
    private final Lazy f2815i0;
    private final Lazy j0;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PassListFragment a(String topic) {
            Intrinsics.f(topic, "topic");
            PassListFragment passListFragment = new PassListFragment();
            passListFragment.A1(BundleKt.a(new Pair[]{TuplesKt.a("topic", topic)}));
            return passListFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PassListFragment() {
        Lazy a2;
        Lazy a3;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        a2 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassListFragment$special$$inlined$inject$default$1(this, null, null));
        this.f2815i0 = a2;
        a3 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassListFragment$special$$inlined$inject$default$2(this, null, null));
        this.j0 = a3;
    }

    public final PassStore T1() {
        return (PassStore) this.f2815i0.getValue();
    }

    public final Settings U1() {
        return (Settings) this.j0.getValue();
    }

    public final void V1(int i2, int i3) {
        int i4;
        PassStoreProjection passStoreProjection = this.f2813g0;
        if (passStoreProjection == null) {
            Intrinsics.p("passStoreProjection");
            passStoreProjection = null;
        }
        Pass pass = passStoreProjection.getPassList().get(i2);
        PassClassifier classifier = T1().getClassifier();
        if (i3 == 4) {
            i4 = -1;
        } else {
            i4 = 1;
        }
        String topicWithOffset = classifier.getTopicWithOffset(pass, i4);
        FragmentActivity m2 = m();
        if (m2 != null) {
            if (topicWithOffset != null) {
                MoveHelperKt.b(T1().getClassifier(), pass, topicWithOffset, m2);
            } else {
                new MoveToNewTopicUI(m2, T1(), pass).h();
            }
        }
    }

    public View v0(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        Intrinsics.f(inflater, "inflater");
        PassRecyclerBinding c2 = PassRecyclerBinding.c(F(), viewGroup, false);
        Intrinsics.e(c2, "inflate(layoutInflater, container, false)");
        PassStore T1 = T1();
        Bundle s2 = s();
        if (s2 != null) {
            str = s2.getString("topic");
        } else {
            str = null;
        }
        Intrinsics.c(str);
        this.f2813g0 = new PassStoreProjection(T1, str, U1().getSortOrder());
        AppCompatActivity m2 = m();
        Intrinsics.d(m2, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        AppCompatActivity appCompatActivity = m2;
        PassStoreProjection passStoreProjection = this.f2813g0;
        if (passStoreProjection == null) {
            Intrinsics.p("passStoreProjection");
            passStoreProjection = null;
        }
        PassAdapter passAdapter = new PassAdapter(appCompatActivity, passStoreProjection);
        this.f2814h0 = passAdapter;
        c2.f2670b.setAdapter(passAdapter);
        c2.f2670b.setLayoutManager(new LinearLayoutManager(m()));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback() { // from class: org.ligi.passandroid.ui.PassListFragment$onCreateView$simpleItemTouchCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0, 12);
            }

            public void B(RecyclerView.ViewHolder viewHolder, int i2) {
                Intrinsics.f(viewHolder, "viewHolder");
                PassListFragment.this.V1(viewHolder.k(), i2);
            }

            public boolean y(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Intrinsics.f(recyclerView, "recyclerView");
                Intrinsics.f(viewHolder, "viewHolder");
                Intrinsics.f(target, "target");
                return false;
            }
        }).m(c2.f2670b);
        BuildersKt__Builders_commonKt.b(LifecycleOwnerKt.a(this), null, null, new PassListFragment$onCreateView$1(this, null), 3, null);
        return c2.b();
    }
}
