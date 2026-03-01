package net.lingala.zip4j.io.inputstream;

import java.io.File;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class NumberedSplitFileInputStream extends SplitFileInputStream {

    /* renamed from: d  reason: collision with root package name */
    private RandomAccessFile f1460d;

    public NumberedSplitFileInputStream(File file) {
        this.f1460d = new NumberedSplitRandomAccessFile(file, RandomAccessFileMode.READ.a());
    }

    @Override // net.lingala.zip4j.io.inputstream.SplitFileInputStream
    public void b(FileHeader fileHeader) {
        this.f1460d.seek(fileHeader.Q());
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        RandomAccessFile randomAccessFile = this.f1460d;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    @Override // java.io.InputStream
    public int read() {
        return this.f1460d.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.f1460d.read(bArr, i2, i3);
    }
}
