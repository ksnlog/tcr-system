package okio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Okio {
    public static final BufferedSink a(Sink sink) {
        return Okio__OkioKt.a(sink);
    }

    public static final BufferedSource b(Source source) {
        return Okio__OkioKt.b(source);
    }

    public static final boolean c(AssertionError assertionError) {
        return Okio__JvmOkioKt.b(assertionError);
    }

    public static final Sink d(File file) {
        return Okio__JvmOkioKt.c(file);
    }

    public static final Sink e(File file, boolean z2) {
        return Okio__JvmOkioKt.d(file, z2);
    }

    public static final Sink f(OutputStream outputStream) {
        return Okio__JvmOkioKt.e(outputStream);
    }

    public static final Sink g(Socket socket) {
        return Okio__JvmOkioKt.f(socket);
    }

    public static final Source i(File file) {
        return Okio__JvmOkioKt.h(file);
    }

    public static final Source j(InputStream inputStream) {
        return Okio__JvmOkioKt.i(inputStream);
    }

    public static final Source k(Socket socket) {
        return Okio__JvmOkioKt.j(socket);
    }
}
