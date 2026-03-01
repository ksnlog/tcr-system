package okio;

import java.io.Closeable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Source extends Closeable {
    long A(Buffer buffer, long j2);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    Timeout d();
}
