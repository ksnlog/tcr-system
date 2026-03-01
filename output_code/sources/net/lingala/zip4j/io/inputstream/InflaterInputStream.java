package net.lingala.zip4j.io.inputstream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class InflaterInputStream extends DecompressedInputStream {

    /* renamed from: f  reason: collision with root package name */
    private Inflater f1456f;

    /* renamed from: g  reason: collision with root package name */
    private byte[] f1457g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f1458h;

    /* renamed from: i  reason: collision with root package name */
    private int f1459i;

    public InflaterInputStream(CipherInputStream<?> cipherInputStream, int i2) {
        super(cipherInputStream);
        this.f1458h = new byte[1];
        this.f1456f = new Inflater(true);
        this.f1457g = new byte[i2];
    }

    private void k() {
        byte[] bArr = this.f1457g;
        int read = super.read(bArr, 0, bArr.length);
        this.f1459i = read;
        if (read != -1) {
            this.f1456f.setInput(this.f1457g, 0, read);
            return;
        }
        throw new EOFException("Unexpected end of input stream");
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream
    public void b(InputStream inputStream, int i2) {
        Inflater inflater = this.f1456f;
        if (inflater != null) {
            inflater.end();
            this.f1456f = null;
        }
        super.b(inputStream, i2);
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Inflater inflater = this.f1456f;
        if (inflater != null) {
            inflater.end();
        }
        super.close();
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream
    public int e(PushbackInputStream pushbackInputStream) {
        int remaining = this.f1456f.getRemaining();
        if (remaining > 0) {
            pushbackInputStream.unread(c(), this.f1459i - remaining, remaining);
        }
        return remaining;
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read() {
        if (read(this.f1458h) == -1) {
            return -1;
        }
        return this.f1458h[0];
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        while (true) {
            try {
                int inflate = this.f1456f.inflate(bArr, i2, i3);
                if (inflate != 0) {
                    return inflate;
                }
                if (!this.f1456f.finished() && !this.f1456f.needsDictionary()) {
                    if (this.f1456f.needsInput()) {
                        k();
                    }
                }
                return -1;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        }
    }
}
