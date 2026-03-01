package z;

import android.net.ssl.SSLSockets;
import javax.net.ssl.SSLSocket;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class d {
    public static /* bridge */ /* synthetic */ void a(SSLSocket sSLSocket, boolean z2) {
        SSLSockets.setUseSessionTickets(sSLSocket, z2);
    }
}
