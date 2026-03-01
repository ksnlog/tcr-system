package net.lingala.zip4j.io.inputstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipStandardSplitFileInputStream extends SplitFileInputStream {

    /* renamed from: d  reason: collision with root package name */
    protected RandomAccessFile f1482d;

    /* renamed from: e  reason: collision with root package name */
    protected File f1483e;

    /* renamed from: f  reason: collision with root package name */
    private int f1484f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1485g;

    /* renamed from: h  reason: collision with root package name */
    private int f1486h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f1487i = new byte[1];

    public ZipStandardSplitFileInputStream(File file, boolean z2, int i2) {
        this.f1486h = 0;
        this.f1482d = new RandomAccessFile(file, RandomAccessFileMode.READ.a());
        this.f1483e = file;
        this.f1485g = z2;
        this.f1484f = i2;
        if (z2) {
            this.f1486h = i2;
        }
    }

    @Override // net.lingala.zip4j.io.inputstream.SplitFileInputStream
    public void b(FileHeader fileHeader) {
        if (this.f1485g && this.f1486h != fileHeader.N()) {
            e(fileHeader.N());
            this.f1486h = fileHeader.N();
        }
        this.f1482d.seek(fileHeader.Q());
    }

    protected File c(int i2) {
        String str;
        if (i2 == this.f1484f) {
            return this.f1483e;
        }
        String canonicalPath = this.f1483e.getCanonicalPath();
        if (i2 >= 9) {
            str = ".z";
        } else {
            str = ".z0";
        }
        return new File(canonicalPath.substring(0, canonicalPath.lastIndexOf(".")) + str + (i2 + 1));
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        RandomAccessFile randomAccessFile = this.f1482d;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    protected void e(int i2) {
        File c2 = c(i2);
        if (c2.exists()) {
            this.f1482d.close();
            this.f1482d = new RandomAccessFile(c2, RandomAccessFileMode.READ.a());
            return;
        }
        throw new FileNotFoundException("zip split file does not exist: " + c2);
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.f1487i) == -1) {
            return -1;
        }
        return this.f1487i[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = this.f1482d.read(bArr, i2, i3);
        if ((read != i3 || read == -1) && this.f1485g) {
            e(this.f1486h + 1);
            this.f1486h++;
            if (read < 0) {
                read = 0;
            }
            int read2 = this.f1482d.read(bArr, read, i3 - read);
            return read2 > 0 ? read + read2 : read;
        }
        return read;
    }
}
