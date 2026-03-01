package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.model.ZipParameters;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CipherOutputStream<T extends Encrypter> extends OutputStream {

    /* renamed from: d  reason: collision with root package name */
    private ZipEntryOutputStream f1490d;

    /* renamed from: e  reason: collision with root package name */
    private T f1491e;

    public CipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        this.f1490d = zipEntryOutputStream;
        this.f1491e = k(zipEntryOutputStream, zipParameters, cArr, z2);
    }

    public void b() {
        this.f1490d.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T c() {
        return this.f1491e;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1490d.close();
    }

    public long e() {
        return this.f1490d.c();
    }

    protected abstract T k(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z2);

    public void m(byte[] bArr) {
        this.f1490d.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f1490d.write(i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f1490d.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f1491e.a(bArr, i2, i3);
        this.f1490d.write(bArr, i2, i3);
    }
}
