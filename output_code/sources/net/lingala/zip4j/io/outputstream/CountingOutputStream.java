package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CountingOutputStream extends OutputStream implements OutputStreamWithSplitZipSupport {

    /* renamed from: d  reason: collision with root package name */
    private OutputStream f1493d;

    /* renamed from: e  reason: collision with root package name */
    private long f1494e = 0;

    public CountingOutputStream(OutputStream outputStream) {
        this.f1493d = outputStream;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public int b() {
        if (o()) {
            return ((SplitOutputStream) this.f1493d).b();
        }
        return 0;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public long c() {
        OutputStream outputStream = this.f1493d;
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).c();
        }
        return this.f1494e;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1493d.close();
    }

    public boolean e(int i2) {
        if (!o()) {
            return false;
        }
        return ((SplitOutputStream) this.f1493d).e(i2);
    }

    public long k() {
        OutputStream outputStream = this.f1493d;
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).c();
        }
        return this.f1494e;
    }

    public long m() {
        OutputStream outputStream = this.f1493d;
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).c();
        }
        return this.f1494e;
    }

    public long n() {
        if (o()) {
            return ((SplitOutputStream) this.f1493d).k();
        }
        return 0L;
    }

    public boolean o() {
        OutputStream outputStream = this.f1493d;
        if ((outputStream instanceof SplitOutputStream) && ((SplitOutputStream) outputStream).o()) {
            return true;
        }
        return false;
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
        this.f1493d.write(bArr, i2, i3);
        this.f1494e += i3;
    }
}
