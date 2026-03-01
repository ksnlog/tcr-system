package net.lingala.zip4j.io.inputstream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.util.FileUtils;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class NumberedSplitRandomAccessFile extends RandomAccessFile {

    /* renamed from: d  reason: collision with root package name */
    private long f1461d;

    /* renamed from: e  reason: collision with root package name */
    private File[] f1462e;

    /* renamed from: f  reason: collision with root package name */
    private RandomAccessFile f1463f;

    /* renamed from: g  reason: collision with root package name */
    private byte[] f1464g;

    /* renamed from: h  reason: collision with root package name */
    private int f1465h;

    /* renamed from: i  reason: collision with root package name */
    private String f1466i;

    public NumberedSplitRandomAccessFile(File file, String str) {
        this(file, str, FileUtils.h(file));
    }

    private void b(File[] fileArr) {
        int i2 = 1;
        for (File file : fileArr) {
            String k2 = FileUtils.k(file);
            try {
                if (i2 == Integer.parseInt(k2)) {
                    i2++;
                } else {
                    throw new IOException("Split file number " + i2 + " does not exist");
                }
            } catch (NumberFormatException unused) {
                throw new IOException("Split file extension not in expected format. Found: " + k2 + " expected of format: .001, .002, etc");
            }
        }
    }

    private void e(int i2) {
        if (this.f1465h == i2) {
            return;
        }
        if (i2 <= this.f1462e.length - 1) {
            RandomAccessFile randomAccessFile = this.f1463f;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.f1463f = new RandomAccessFile(this.f1462e[i2], this.f1466i);
            this.f1465h = i2;
            return;
        }
        throw new IOException("split counter greater than number of split files");
    }

    public void c() {
        e(this.f1462e.length - 1);
    }

    @Override // java.io.RandomAccessFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        RandomAccessFile randomAccessFile = this.f1463f;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        super.close();
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() {
        return this.f1463f.getFilePointer();
    }

    public void k(long j2) {
        this.f1463f.seek(j2);
    }

    @Override // java.io.RandomAccessFile
    public long length() {
        return this.f1463f.length();
    }

    @Override // java.io.RandomAccessFile
    public int read() {
        if (read(this.f1464g) == -1) {
            return -1;
        }
        return this.f1464g[0] & 255;
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j2) {
        int i2 = (int) (j2 / this.f1461d);
        if (i2 != this.f1465h) {
            e(i2);
        }
        this.f1463f.seek(j2 - (i2 * this.f1461d));
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i2) {
        throw new UnsupportedOperationException();
    }

    public NumberedSplitRandomAccessFile(File file, String str, File[] fileArr) {
        super(file, str);
        this.f1464g = new byte[1];
        this.f1465h = 0;
        super.close();
        if (!RandomAccessFileMode.WRITE.a().equals(str)) {
            b(fileArr);
            this.f1463f = new RandomAccessFile(file, str);
            this.f1462e = fileArr;
            this.f1461d = file.length();
            this.f1466i = str;
            return;
        }
        throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i2, int i3) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i2, int i3) {
        int read = this.f1463f.read(bArr, i2, i3);
        if (read == -1) {
            int i4 = this.f1465h;
            if (i4 == this.f1462e.length - 1) {
                return -1;
            }
            e(i4 + 1);
            return read(bArr, i2, i3);
        }
        return read;
    }
}
