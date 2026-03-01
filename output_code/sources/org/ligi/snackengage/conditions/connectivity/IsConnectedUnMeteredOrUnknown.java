package org.ligi.snackengage.conditions.connectivity;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.ligi.snackengage.SnackContext;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class IsConnectedUnMeteredOrUnknown extends ConnectivityAwareCondition {
    @Override // org.ligi.snackengage.conditions.SnackCondition
    public boolean a(SnackContext snackContext, Snack snack) {
        NetworkInfo networkInfo;
        b(snackContext.a());
        if (c() && ((networkInfo = this.f2969a) == null || !networkInfo.isConnectedOrConnecting() || !d(this.f2970b, this.f2969a))) {
            return false;
        }
        return true;
    }

    @Override // org.ligi.snackengage.conditions.connectivity.ConnectivityAwareCondition
    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    @SuppressLint({"MissingPermission"})
    protected boolean d(ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
        throw null;
    }
}
