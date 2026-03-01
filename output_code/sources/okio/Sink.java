package okio;

import java.io.Closeable;
import java.io.Flushable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Sink extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    Timeout d();

    void f(Buffer buffer, long j2);

    void flush();
}
