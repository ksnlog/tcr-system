package net.lingala.zip4j.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import net.lingala.zip4j.exception.ZipException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class RawIO {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1720a = new byte[2];

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f1721b = new byte[4];

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f1722c = new byte[8];

    private void a(InputStream inputStream, byte[] bArr, int i2) {
        if (Zip4jUtil.m(inputStream, bArr, 0, i2) == i2) {
            return;
        }
        throw new ZipException("Could not fill buffer");
    }

    private void n(byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
    }

    public int b(InputStream inputStream) {
        a(inputStream, this.f1721b, 4);
        return d(this.f1721b);
    }

    public int c(RandomAccessFile randomAccessFile) {
        randomAccessFile.readFully(this.f1721b);
        return d(this.f1721b);
    }

    public int d(byte[] bArr) {
        return e(bArr, 0);
    }

    public int e(byte[] bArr, int i2) {
        return ((((bArr[i2 + 3] & 255) << 8) | (bArr[i2 + 2] & 255)) << 16) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8);
    }

    public long f(InputStream inputStream) {
        byte[] bArr = this.f1722c;
        a(inputStream, bArr, bArr.length);
        return j(this.f1722c, 0);
    }

    public long g(InputStream inputStream, int i2) {
        n(this.f1722c);
        a(inputStream, this.f1722c, i2);
        return j(this.f1722c, 0);
    }

    public long h(RandomAccessFile randomAccessFile) {
        randomAccessFile.readFully(this.f1722c);
        return j(this.f1722c, 0);
    }

    public long i(RandomAccessFile randomAccessFile, int i2) {
        n(this.f1722c);
        randomAccessFile.readFully(this.f1722c, 0, i2);
        return j(this.f1722c, 0);
    }

    public long j(byte[] bArr, int i2) {
        if (bArr.length - i2 < 8) {
            n(this.f1722c);
        }
        System.arraycopy(bArr, i2, this.f1722c, 0, Math.min(bArr.length - i2, 8));
        byte[] bArr2 = this.f1722c;
        return (bArr2[0] & 255) | (((((((((((((((bArr2[7] & 255) | 0) << 8) | (bArr2[6] & 255)) << 8) | (bArr2[5] & 255)) << 8) | (bArr2[4] & 255)) << 8) | (bArr2[3] & 255)) << 8) | (bArr2[2] & 255)) << 8) | (bArr2[1] & 255)) << 8);
    }

    public int k(InputStream inputStream) {
        byte[] bArr = this.f1720a;
        a(inputStream, bArr, bArr.length);
        return m(this.f1720a, 0);
    }

    public int l(RandomAccessFile randomAccessFile) {
        randomAccessFile.readFully(this.f1720a);
        return m(this.f1720a, 0);
    }

    public int m(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
    }

    public void o(OutputStream outputStream, int i2) {
        p(this.f1721b, 0, i2);
        outputStream.write(this.f1721b);
    }

    public void p(byte[] bArr, int i2, int i3) {
        bArr[i2 + 3] = (byte) (i3 >>> 24);
        bArr[i2 + 2] = (byte) (i3 >>> 16);
        bArr[i2 + 1] = (byte) (i3 >>> 8);
        bArr[i2] = (byte) (i3 & 255);
    }

    public void q(OutputStream outputStream, long j2) {
        r(this.f1722c, 0, j2);
        outputStream.write(this.f1722c);
    }

    public void r(byte[] bArr, int i2, long j2) {
        bArr[i2 + 7] = (byte) (j2 >>> 56);
        bArr[i2 + 6] = (byte) (j2 >>> 48);
        bArr[i2 + 5] = (byte) (j2 >>> 40);
        bArr[i2 + 4] = (byte) (j2 >>> 32);
        bArr[i2 + 3] = (byte) (j2 >>> 24);
        bArr[i2 + 2] = (byte) (j2 >>> 16);
        bArr[i2 + 1] = (byte) (j2 >>> 8);
        bArr[i2] = (byte) (j2 & 255);
    }

    public void s(OutputStream outputStream, int i2) {
        t(this.f1720a, 0, i2);
        outputStream.write(this.f1720a);
    }

    public void t(byte[] bArr, int i2, int i3) {
        bArr[i2 + 1] = (byte) (i3 >>> 8);
        bArr[i2] = (byte) (i3 & 255);
    }
}
