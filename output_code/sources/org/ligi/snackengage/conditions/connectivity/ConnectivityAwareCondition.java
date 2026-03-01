package org.ligi.snackengage.conditions.connectivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.content.ContextCompat;
import org.ligi.snackengage.conditions.SnackCondition;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class ConnectivityAwareCondition implements SnackCondition {

    /* renamed from: a  reason: collision with root package name */
    protected NetworkInfo f2969a;

    /* renamed from: b  reason: collision with root package name */
    protected ConnectivityManager f2970b;

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressLint({"MissingPermission"})
    public void b(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.h(context, ConnectivityManager.class);
            this.f2970b = connectivityManager;
            this.f2969a = connectivityManager.getActiveNetworkInfo();
        }
    }

    public boolean c() {
        return this.f2970b != null;
    }
}
