package org.ligi.passandroid.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.EditTextExtensionsKt;
import org.ligi.passandroid.databinding.EditFieldsBinding;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassField;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.ui.edit.FieldsEditFragment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FieldsEditFragment extends Fragment {
    public static final Companion j0 = new Companion(null);

    /* renamed from: g0  reason: collision with root package name */
    private final Lazy f2936g0;

    /* renamed from: h0  reason: collision with root package name */
    private boolean f2937h0;

    /* renamed from: i0  reason: collision with root package name */
    private LayoutInflater f2938i0;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FieldsEditFragment a(boolean z2) {
            FieldsEditFragment fieldsEditFragment = new FieldsEditFragment();
            fieldsEditFragment.A1(BundleKt.a(new Pair[]{TuplesKt.a("KEY", Boolean.valueOf(z2))}));
            return fieldsEditFragment;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class FieldView {

        /* renamed from: a  reason: collision with root package name */
        private final ViewGroup f2942a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ FieldsEditFragment f2943b;

        public FieldView(FieldsEditFragment fieldsEditFragment, ViewGroup container) {
            Intrinsics.f(container, "container");
            this.f2943b = fieldsEditFragment;
            this.f2942a = container;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void c(List fields, PassField passField, View view) {
            Intrinsics.f(fields, "$fields");
            Intrinsics.f(passField, "$passField");
            ViewParent parent = view.getParent();
            Intrinsics.d(parent, "null cannot be cast to non-null type android.view.View");
            ((View) parent).setVisibility(8);
            fields.remove(passField);
        }

        public final void b(final PassField passField, final List<PassField> fields) {
            Intrinsics.f(passField, "passField");
            Intrinsics.f(fields, "fields");
            TextInputEditText apply$lambda$0 = this.f2942a.findViewById(2131296502);
            apply$lambda$0.setText(passField.getLabel());
            Intrinsics.e(apply$lambda$0, "apply$lambda$0");
            EditTextExtensionsKt.a(apply$lambda$0, new FieldsEditFragment$FieldView$apply$1$1(passField));
            TextInputEditText apply$lambda$1 = this.f2942a.findViewById(2131296799);
            apply$lambda$1.setText(passField.getValue());
            Intrinsics.e(apply$lambda$1, "apply$lambda$1");
            EditTextExtensionsKt.a(apply$lambda$1, new FieldsEditFragment$FieldView$apply$2$1(passField));
            this.f2942a.findViewById(2131296402).setOnClickListener(new View.OnClickListener() { // from class: f0.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FieldsEditFragment.FieldView.c(fields, passField, view);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldsEditFragment() {
        Lazy a2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new FieldsEditFragment$special$$inlined$inject$default$1(this, null, null));
        this.f2936g0 = a2;
    }

    private final void S1(PassField passField, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = this.f2938i0;
        if (layoutInflater == null) {
            Intrinsics.p("inflater");
            layoutInflater = null;
        }
        View inflate = layoutInflater.inflate(2131492922, viewGroup, false);
        Intrinsics.d(inflate, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup2 = (ViewGroup) inflate;
        new FieldView(this, viewGroup2).b(passField, T1().getFields());
        viewGroup.addView(viewGroup2);
    }

    private final PassImpl T1() {
        Pass currentPass = U1().getCurrentPass();
        Intrinsics.d(currentPass, "null cannot be cast to non-null type org.ligi.passandroid.model.pass.PassImpl");
        return (PassImpl) currentPass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void V1(FieldsEditFragment this$0, EditFieldsBinding inflate, View view) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(inflate, "$inflate");
        PassField passField = new PassField(null, null, null, this$0.f2937h0, null, 16, null);
        LinearLayout linearLayout = inflate.f2652c;
        Intrinsics.e(linearLayout, "inflate.fieldsContainer");
        this$0.S1(passField, linearLayout);
        this$0.T1().getFields().add(passField);
    }

    public final PassStore U1() {
        return (PassStore) this.f2936g0.getValue();
    }

    public View v0(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z2;
        Intrinsics.f(inflater, "inflater");
        this.f2938i0 = inflater;
        final EditFieldsBinding c2 = EditFieldsBinding.c(F(), viewGroup, false);
        Intrinsics.e(c2, "inflate(layoutInflater, container, false)");
        Bundle s2 = s();
        if (s2 != null) {
            this.f2937h0 = s2.getBoolean("KEY");
        }
        if (this.f2937h0) {
            c2.f2651b.setText(2131755036);
        } else {
            c2.f2651b.setText(2131755039);
        }
        List<PassField> fields = T1().getFields();
        ArrayList<PassField> arrayList = new ArrayList();
        for (Object obj : fields) {
            if (((PassField) obj).getHide() == this.f2937h0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                arrayList.add(obj);
            }
        }
        for (PassField passField : arrayList) {
            LinearLayout linearLayout = c2.f2652c;
            Intrinsics.e(linearLayout, "inflate.fieldsContainer");
            S1(passField, linearLayout);
        }
        c2.f2651b.setOnClickListener(new View.OnClickListener() { // from class: f0.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FieldsEditFragment.V1(FieldsEditFragment.this, c2, view);
            }
        });
        return c2.b();
    }
}
