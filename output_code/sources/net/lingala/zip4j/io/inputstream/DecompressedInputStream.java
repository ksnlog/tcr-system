package net.lingala.zip4j.io.inputstream;

import java.io.InputStream;
import java.io.PushbackInputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class DecompressedInputStream extends InputStream {

    /* renamed from: d  reason: collision with root package name */
    private CipherInputStream<?> f1454d;

    /* renamed from: e  reason: collision with root package name */
    protected byte[] f1455e = new byte[1];

    public DecompressedInputStream(CipherInputStream<?> cipherInputStream) {
        this.f1454d = cipherInputStream;
    }

    public void b(InputStream inputStream, int i2) {
        this.f1454d.c(inputStream, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] c() {
        return this.f1454d.k();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1454d.close();
    }

    public int e(PushbackInputStream pushbackInputStream) {
        return 0;
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.f1455e) == -1) {
            return -1;
        }
        return this.f1455e[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.f1454d.read(bArr, i2, i3);
    }
}
