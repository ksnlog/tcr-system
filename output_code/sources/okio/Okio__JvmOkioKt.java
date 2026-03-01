package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class Okio__JvmOkioKt {

    /* renamed from: a */
    private static final Logger f2473a = Logger.getLogger("okio.Okio");

    public static final boolean b(AssertionError assertionError) {
        boolean z2;
        Intrinsics.f(assertionError, "<this>");
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        if (message != null) {
            z2 = StringsKt__StringsKt.p(message, "getsockname failed", false, 2, null);
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        return true;
    }

    public static final Sink c(File file) {
        Sink g2;
        Intrinsics.f(file, "<this>");
        g2 = g(file, false, 1, null);
        return g2;
    }

    public static final Sink d(File file, boolean z2) {
        Intrinsics.f(file, "<this>");
        return Okio.f(new FileOutputStream(file, z2));
    }

    public static final Sink e(OutputStream outputStream) {
        Intrinsics.f(outputStream, "<this>");
        return new OutputStreamSink(outputStream, new Timeout());
    }

    public static final Sink f(Socket socket) {
        Intrinsics.f(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        OutputStream outputStream = socket.getOutputStream();
        Intrinsics.e(outputStream, "getOutputStream()");
        return socketAsyncTimeout.z(new OutputStreamSink(outputStream, socketAsyncTimeout));
    }

    public static /* synthetic */ Sink g(File file, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return Okio.e(file, z2);
    }

    public static final Source h(File file) {
        Intrinsics.f(file, "<this>");
        return new InputStreamSource(new FileInputStream(file), Timeout.f2503e);
    }

    public static final Source i(InputStream inputStream) {
        Intrinsics.f(inputStream, "<this>");
        return new InputStreamSource(inputStream, new Timeout());
    }

    public static final Source j(Socket socket) {
        Intrinsics.f(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        InputStream inputStream = socket.getInputStream();
        Intrinsics.e(inputStream, "getInputStream()");
        return socketAsyncTimeout.A(new InputStreamSource(inputStream, socketAsyncTimeout));
    }
}
