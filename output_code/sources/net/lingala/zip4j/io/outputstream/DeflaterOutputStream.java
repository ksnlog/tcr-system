package net.lingala.zip4j.io.outputstream;

import java.util.zip.Deflater;
import net.lingala.zip4j.model.enums.CompressionLevel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class DeflaterOutputStream extends CompressedOutputStream {

    /* renamed from: e  reason: collision with root package name */
    private byte[] f1495e;

    /* renamed from: f  reason: collision with root package name */
    protected Deflater f1496f;

    public DeflaterOutputStream(CipherOutputStream<?> cipherOutputStream, CompressionLevel compressionLevel, int i2) {
        super(cipherOutputStream);
        this.f1496f = new Deflater(compressionLevel.a(), true);
        this.f1495e = new byte[i2];
    }

    private void e() {
        Deflater deflater = this.f1496f;
        byte[] bArr = this.f1495e;
        int deflate = deflater.deflate(bArr, 0, bArr.length);
        if (deflate > 0) {
            super.write(this.f1495e, 0, deflate);
        }
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream
    public void b() {
        if (!this.f1496f.finished()) {
            this.f1496f.finish();
            while (!this.f1496f.finished()) {
                e();
            }
        }
        this.f1496f.end();
        super.b();
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // net.lingala.zip4j.io.outputstream.CompressedOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f1496f.setInput(bArr, i2, i3);
        while (!this.f1496f.needsInput()) {
            e();
        }
    }
}
