package org.ligi.snackengage.snacks;

import android.content.Context;
import android.net.Uri;
import org.ligi.snackengage.R$string;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class RateSnack extends AbstractOpenURLSnack {
    @Override // org.ligi.snackengage.snacks.BaseSnack
    public String f() {
        return h(R$string.f2960b);
    }

    @Override // org.ligi.snackengage.snacks.BaseSnack
    public String g() {
        return "RATE_SNACK";
    }

    @Override // org.ligi.snackengage.snacks.BaseSnack
    public String i() {
        return h(R$string.f2961c);
    }

    @Override // org.ligi.snackengage.snacks.AbstractOpenURLSnack
    public Uri m() {
        return n(this.f2971a.a());
    }

    public Uri n(Context context) {
        return Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName());
    }
}
