package okio;

import java.nio.channels.WritableByteChannel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    BufferedSink H(String str);

    Buffer a();

    @Override // okio.Sink, java.io.Flushable
    void flush();

    BufferedSink h(String str, int i2, int i3);

    BufferedSink i(long j2);

    BufferedSink write(byte[] bArr);

    BufferedSink write(byte[] bArr, int i2, int i3);

    BufferedSink writeByte(int i2);

    BufferedSink writeInt(int i2);

    BufferedSink writeShort(int i2);
}
