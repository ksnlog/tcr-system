package org.ligi.snackengage.snacks;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.google.android.material.snackbar.Snackbar;
import org.ligi.snackengage.R$string;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractOpenURLSnack extends BaseSnack {
    @Override // org.ligi.snackengage.snacks.BaseSnack
    public void e() {
        super.e();
        try {
            Intent intent = new Intent("android.intent.action.VIEW", m());
            intent.addFlags(268435456);
            this.f2971a.a().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Snackbar.m0(this.f2971a.b(), R$string.f2959a, 0).X();
        }
    }

    protected abstract Uri m();
}
