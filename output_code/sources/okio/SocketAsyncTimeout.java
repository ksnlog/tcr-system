package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SocketAsyncTimeout extends AsyncTimeout {

    /* renamed from: o  reason: collision with root package name */
    private final Socket f2501o;

    public SocketAsyncTimeout(Socket socket) {
        Intrinsics.f(socket, "socket");
        this.f2501o = socket;
    }

    @Override // okio.AsyncTimeout
    protected void B() {
        Logger logger;
        Logger logger2;
        try {
            this.f2501o.close();
        } catch (AssertionError e2) {
            if (Okio.c(e2)) {
                logger2 = Okio__JvmOkioKt.f2473a;
                Level level = Level.WARNING;
                logger2.log(level, "Failed to close timed out socket " + this.f2501o, (Throwable) e2);
                return;
            }
            throw e2;
        } catch (Exception e3) {
            logger = Okio__JvmOkioKt.f2473a;
            Level level2 = Level.WARNING;
            logger.log(level2, "Failed to close timed out socket " + this.f2501o, (Throwable) e3);
        }
    }

    @Override // okio.AsyncTimeout
    protected IOException x(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }
}
