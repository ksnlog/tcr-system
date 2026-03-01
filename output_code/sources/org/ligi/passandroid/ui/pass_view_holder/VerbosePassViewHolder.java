package org.ligi.passandroid.ui.pass_view_holder;

import android.app.Activity;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class VerbosePassViewHolder extends PassViewHolder {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerbosePassViewHolder(CardView view) {
        super(view);
        Intrinsics.f(view, "view");
    }

    @Override // org.ligi.passandroid.ui.pass_view_holder.PassViewHolder
    public void Q(Pass pass, PassStore passStore, Activity activity) {
        boolean z2;
        Intrinsics.f(pass, "pass");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(activity, "activity");
        super.Q(pass, passStore, activity);
        String U = U(pass);
        if (U == null) {
            U = S(pass);
        }
        TextView textView = (TextView) V().findViewById(2131296396);
        if (U != null) {
            if (U.length() > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                textView.setText(U);
                textView.setVisibility(0);
                return;
            }
        }
        textView.setVisibility(8);
    }
}
