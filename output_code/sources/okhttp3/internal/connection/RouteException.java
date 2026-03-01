package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RouteException extends RuntimeException {

    /* renamed from: d  reason: collision with root package name */
    private IOException f2136d;

    /* renamed from: e  reason: collision with root package name */
    private IOException f2137e;

    public RouteException(IOException iOException) {
        super(iOException);
        this.f2136d = iOException;
        this.f2137e = iOException;
    }

    public void a(IOException iOException) {
        Util.a(this.f2136d, iOException);
        this.f2137e = iOException;
    }

    public IOException b() {
        return this.f2136d;
    }

    public IOException c() {
        return this.f2137e;
    }
}
