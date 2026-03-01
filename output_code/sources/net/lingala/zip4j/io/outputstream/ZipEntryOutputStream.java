package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class ZipEntryOutputStream extends OutputStream {

    /* renamed from: e  reason: collision with root package name */
    private OutputStream f1504e;

    /* renamed from: d  reason: collision with root package name */
    private long f1503d = 0;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1505f = false;

    public ZipEntryOutputStream(OutputStream outputStream) {
        this.f1504e = outputStream;
    }

    public void b() {
        this.f1505f = true;
    }

    public long c() {
        return this.f1503d;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
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
        if (!this.f1505f) {
            this.f1504e.write(bArr, i2, i3);
            this.f1503d += i3;
            return;
        }
        throw new IllegalStateException("ZipEntryOutputStream is closed");
    }
}
