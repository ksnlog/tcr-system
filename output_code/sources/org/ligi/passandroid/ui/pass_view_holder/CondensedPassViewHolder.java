package org.ligi.passandroid.ui.pass_view_holder;

import androidx.cardview.widget.CardView;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CondensedPassViewHolder extends PassViewHolder {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CondensedPassViewHolder(CardView view) {
        super(view);
        Intrinsics.f(view, "view");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    @Override // org.ligi.passandroid.ui.pass_view_holder.PassViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void Q(org.ligi.passandroid.model.pass.Pass r3, org.ligi.passandroid.model.PassStore r4, android.app.Activity r5) {
        /*
            r2 = this;
            java.lang.String r0 = "pass"
            kotlin.jvm.internal.Intrinsics.f(r3, r0)
            java.lang.String r0 = "passStore"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.f(r5, r0)
            super.Q(r3, r4, r5)
            java.lang.String r4 = r2.S(r3)
            androidx.cardview.widget.CardView r5 = r2.V()
            r0 = 2131296396(0x7f09008c, float:1.8210707E38)
            android.view.View r5 = r5.findViewById(r0)
            android.widget.TextView r5 = (android.widget.TextView) r5
            r0 = 0
            if (r4 == 0) goto L2f
            boolean r1 = kotlin.text.StringsKt.i(r4)
            if (r1 == 0) goto L2d
            goto L2f
        L2d:
            r1 = 0
            goto L30
        L2f:
            r1 = 1
        L30:
            if (r1 == 0) goto L38
            r4 = 8
            r5.setVisibility(r4)
            goto L3e
        L38:
            r5.setText(r4)
            r5.setVisibility(r0)
        L3e:
            androidx.cardview.widget.CardView r4 = r2.V()
            r5 = 2131296773(0x7f090205, float:1.8211472E38)
            android.view.View r4 = r4.findViewById(r5)
            android.widget.TextView r4 = (android.widget.TextView) r4
            java.lang.String r3 = r2.U(r3)
            r4.setText(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.ui.pass_view_holder.CondensedPassViewHolder.Q(org.ligi.passandroid.model.pass.Pass, org.ligi.passandroid.model.PassStore, android.app.Activity):void");
    }
}
