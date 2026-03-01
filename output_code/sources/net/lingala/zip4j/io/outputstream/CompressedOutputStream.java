package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CompressedOutputStream extends OutputStream {

    /* renamed from: d  reason: collision with root package name */
    private CipherOutputStream<?> f1492d;

    public CompressedOutputStream(CipherOutputStream<?> cipherOutputStream) {
        this.f1492d = cipherOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        this.f1492d.b();
    }

    public long c() {
        return this.f1492d.e();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1492d.close();
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f1492d.write(bArr, i2, i3);
    }
}
