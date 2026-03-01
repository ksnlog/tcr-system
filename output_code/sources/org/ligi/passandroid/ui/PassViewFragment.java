package org.ligi.passandroid.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.ContextExtensionsKt;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.PassViewFragment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassViewFragment extends Fragment {

    /* renamed from: g0  reason: collision with root package name */
    private final Lazy f2857g0;

    /* renamed from: h0  reason: collision with root package name */
    private final Lazy f2858h0;

    /* renamed from: i0  reason: collision with root package name */
    public Pass f2859i0;

    /* JADX WARN: Multi-variable type inference failed */
    public PassViewFragment() {
        Lazy b2;
        Lazy a2;
        b2 = LazyKt__LazyJVMKt.b(new PassViewFragment$passViewHelper$2(this));
        this.f2857g0 = b2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new PassViewFragment$special$$inlined$inject$default$1(this, null, null));
        this.f2858h0 = a2;
    }

    private final PassViewHelper W1() {
        return (PassViewHelper) this.f2857g0.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void X1(TextView textView, TextView textView2, View view) {
        if (textView.getVisibility() == 0) {
            textView.setVisibility(8);
            textView2.setText(2131755179);
            return;
        }
        textView.setVisibility(0);
        textView2.setText(2131755134);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Y1(PassViewFragment this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        FragmentActivity m2 = this$0.m();
        if (m2 != null) {
            ContextExtensionsKt.a(m2, FullscreenBarcodeActivity.class);
        }
    }

    private final void Z1(final ImageView imageView, final String str, Pass pass) {
        Bitmap bitmap = pass.getBitmap(V1(), str);
        if (bitmap != null && bitmap.getWidth() > 300) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: e0.f0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PassViewFragment.a2(imageView, str, this, view);
                }
            });
        }
        W1().e(imageView, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a2(ImageView view, String name, PassViewFragment this$0, View view2) {
        Intrinsics.f(view, "$view");
        Intrinsics.f(name, "$name");
        Intrinsics.f(this$0, "this$0");
        Intent intent = new Intent(view.getContext(), TouchImageActivity.class);
        intent.putExtra("IMAGE", name);
        this$0.M1(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x00f3, code lost:
        if (org.ligi.passandroid.maps.PassbookMapsFacade.a(r5) == false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void M0() {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.ui.PassViewFragment.M0():void");
    }

    public void Q0(View view, Bundle bundle) {
        Intrinsics.f(view, "view");
        super.Q0(view, bundle);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(2131296622);
        linearLayout.addView(F().inflate(2131492999, (ViewGroup) linearLayout, false));
    }

    public final Pass U1() {
        Pass pass = this.f2859i0;
        if (pass != null) {
            return pass;
        }
        Intrinsics.p("pass");
        return null;
    }

    public final PassStore V1() {
        return (PassStore) this.f2858h0.getValue();
    }

    public final void b2(Pass pass) {
        Intrinsics.f(pass, "<set-?>");
        this.f2859i0 = pass;
    }

    public View v0(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Pass currentPass;
        Intrinsics.f(inflater, "inflater");
        View rootView = inflater.inflate(2131492896, viewGroup, false);
        Bundle s2 = s();
        if (s2 != null) {
            if (!s2.containsKey("uuid")) {
                s2 = null;
            }
            if (s2 != null) {
                String string = s2.getString("uuid");
                if (string != null) {
                    currentPass = V1().getPassbookForId(string);
                    if (currentPass == null) {
                        currentPass = V1().getCurrentPass();
                        Intrinsics.c(currentPass);
                    }
                } else {
                    currentPass = V1().getCurrentPass();
                    Intrinsics.c(currentPass);
                }
                b2(currentPass);
            }
        }
        Intrinsics.e(rootView, "rootView");
        return rootView;
    }
}
