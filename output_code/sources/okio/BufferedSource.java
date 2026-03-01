package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    String C(long j2);

    void F(long j2);

    boolean K(long j2, ByteString byteString);

    long L();

    String M(Charset charset);

    InputStream N();

    int P(Options options);

    Buffer a();

    String g(long j2);

    ByteString j(long j2);

    boolean l(long j2);

    String q();

    byte readByte();

    void readFully(byte[] bArr);

    int readInt();

    short readShort();

    long s(ByteString byteString);

    void skip(long j2);

    Buffer t();

    boolean u();

    byte[] v(long j2);

    long y(ByteString byteString);
}
