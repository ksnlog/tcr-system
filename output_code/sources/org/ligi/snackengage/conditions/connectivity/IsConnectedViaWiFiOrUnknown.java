package org.ligi.snackengage.conditions.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class IsConnectedViaWiFiOrUnknown extends IsConnectedUnMeteredOrUnknown {
    @Override // org.ligi.snackengage.conditions.connectivity.IsConnectedUnMeteredOrUnknown
    protected boolean d(ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
        return networkInfo.getType() == 1;
    }
}
