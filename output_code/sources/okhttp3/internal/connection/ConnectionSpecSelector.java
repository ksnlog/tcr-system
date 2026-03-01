package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConnectionSpecSelector {

    /* renamed from: a  reason: collision with root package name */
    private final List<ConnectionSpec> f2117a;

    /* renamed from: b  reason: collision with root package name */
    private int f2118b = 0;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2119c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2120d;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.f2117a = list;
    }

    private boolean c(SSLSocket sSLSocket) {
        for (int i2 = this.f2118b; i2 < this.f2117a.size(); i2++) {
            if (this.f2117a.get(i2).c(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public ConnectionSpec a(SSLSocket sSLSocket) {
        ConnectionSpec connectionSpec;
        int i2 = this.f2118b;
        int size = this.f2117a.size();
        while (true) {
            if (i2 < size) {
                connectionSpec = this.f2117a.get(i2);
                if (connectionSpec.c(sSLSocket)) {
                    this.f2118b = i2 + 1;
                    break;
                }
                i2++;
            } else {
                connectionSpec = null;
                break;
            }
        }
        if (connectionSpec != null) {
            this.f2119c = c(sSLSocket);
            Internal.f2079a.c(connectionSpec, sSLSocket, this.f2120d);
            return connectionSpec;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f2120d + ", modes=" + this.f2117a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public boolean b(IOException iOException) {
        this.f2120d = true;
        if (!this.f2119c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z2 = iOException instanceof SSLHandshakeException;
        if ((z2 && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if (z2 || (iOException instanceof SSLProtocolException) || (iOException instanceof SSLException)) {
            return true;
        }
        return false;
    }
}
