package net.lingala.zip4j.io.inputstream;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class ZipEntryInputStream extends InputStream {

    /* renamed from: d  reason: collision with root package name */
    private InputStream f1467d;

    /* renamed from: e  reason: collision with root package name */
    private long f1468e = 0;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f1469f = new byte[1];

    /* renamed from: g  reason: collision with root package name */
    private long f1470g;

    public ZipEntryInputStream(InputStream inputStream, long j2) {
        this.f1467d = inputStream;
        this.f1470g = j2;
    }

    private int c(byte[] bArr, int i2) {
        int length = bArr.length - i2;
        int i3 = 0;
        for (int i4 = 0; i2 < bArr.length && i3 != -1 && i4 < 15; i4++) {
            i3 += this.f1467d.read(bArr, i2, length);
            if (i3 > 0) {
                i2 += i3;
                length -= i3;
            }
        }
        return i2;
    }

    public int b(byte[] bArr) {
        int read = this.f1467d.read(bArr);
        if (read != -1) {
            if (read != bArr.length && (read = c(bArr, read)) != bArr.length) {
                throw new IOException("Cannot read fully into byte buffer");
            }
            return read;
        }
        throw new IOException("Unexpected EOF reached when trying to read stream");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1467d.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.f1469f) == -1) {
            return -1;
        }
        return this.f1469f[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        long j2 = this.f1470g;
        if (j2 != -1) {
            long j3 = this.f1468e;
            if (j3 >= j2) {
                return -1;
            }
            if (i3 > j2 - j3) {
                i3 = (int) (j2 - j3);
            }
        }
        int read = this.f1467d.read(bArr, i2, i3);
        if (read > 0) {
            this.f1468e += read;
        }
        return read;
    }
}
