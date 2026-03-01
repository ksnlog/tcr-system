package net.lingala.zip4j.io.inputstream;

import java.io.InputStream;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CipherInputStream<T extends Decrypter> extends InputStream {

    /* renamed from: d  reason: collision with root package name */
    private ZipEntryInputStream f1449d;

    /* renamed from: e  reason: collision with root package name */
    private T f1450e;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f1451f;

    /* renamed from: g  reason: collision with root package name */
    private byte[] f1452g = new byte[1];

    /* renamed from: h  reason: collision with root package name */
    private LocalFileHeader f1453h;

    public CipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i2, boolean z2) {
        this.f1449d = zipEntryInputStream;
        this.f1450e = m(localFileHeader, cArr, z2);
        this.f1453h = localFileHeader;
        if (Zip4jUtil.i(localFileHeader).equals(CompressionMethod.DEFLATE)) {
            this.f1451f = new byte[i2];
        }
    }

    private void b(byte[] bArr, int i2) {
        byte[] bArr2 = this.f1451f;
        if (bArr2 != null) {
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(InputStream inputStream, int i2) {
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1449d.close();
    }

    public T e() {
        return this.f1450e;
    }

    public byte[] k() {
        return this.f1451f;
    }

    protected abstract T m(LocalFileHeader localFileHeader, char[] cArr, boolean z2);

    /* JADX INFO: Access modifiers changed from: protected */
    public int n(byte[] bArr) {
        return this.f1449d.b(bArr);
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.f1452g) == -1) {
            return -1;
        }
        return this.f1452g[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int m2 = Zip4jUtil.m(this.f1449d, bArr, i2, i3);
        if (m2 > 0) {
            b(bArr, m2);
            this.f1450e.a(bArr, i2, m2);
        }
        return m2;
    }
}
