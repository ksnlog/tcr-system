package net.lingala.zip4j.io.outputstream;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SplitOutputStream extends OutputStream implements OutputStreamWithSplitZipSupport {

    /* renamed from: d  reason: collision with root package name */
    private RandomAccessFile f1497d;

    /* renamed from: e  reason: collision with root package name */
    private long f1498e;

    /* renamed from: f  reason: collision with root package name */
    private File f1499f;

    /* renamed from: g  reason: collision with root package name */
    private int f1500g;

    /* renamed from: h  reason: collision with root package name */
    private long f1501h;

    /* renamed from: i  reason: collision with root package name */
    private RawIO f1502i;

    public SplitOutputStream(File file) {
        this(file, -1L);
    }

    private boolean m(int i2) {
        long j2 = this.f1498e;
        if (j2 < 65536 || this.f1501h + i2 <= j2) {
            return true;
        }
        return false;
    }

    private boolean n(byte[] bArr) {
        HeaderSignature[] values;
        int d2 = this.f1502i.d(bArr);
        for (HeaderSignature headerSignature : HeaderSignature.values()) {
            if (headerSignature != HeaderSignature.SPLIT_ZIP && headerSignature.a() == d2) {
                return true;
            }
        }
        return false;
    }

    private void w() {
        String str;
        String r2 = FileUtils.r(this.f1499f.getName());
        String absolutePath = this.f1499f.getAbsolutePath();
        if (this.f1499f.getParent() == null) {
            str = "";
        } else {
            str = this.f1499f.getParent() + System.getProperty("file.separator");
        }
        String str2 = ".z0" + (this.f1500g + 1);
        if (this.f1500g >= 9) {
            str2 = ".z" + (this.f1500g + 1);
        }
        File file = new File(str + r2 + str2);
        this.f1497d.close();
        if (!file.exists()) {
            if (this.f1499f.renameTo(file)) {
                this.f1499f = new File(absolutePath);
                this.f1497d = new RandomAccessFile(this.f1499f, RandomAccessFileMode.WRITE.a());
                this.f1500g++;
                return;
            }
            throw new IOException("cannot rename newly created split file");
        }
        throw new IOException("split file: " + file.getName() + " already exists in the current directory, cannot rename this file");
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public int b() {
        return this.f1500g;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public long c() {
        return this.f1497d.getFilePointer();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1497d.close();
    }

    public boolean e(int i2) {
        if (i2 >= 0) {
            if (!m(i2)) {
                try {
                    w();
                    this.f1501h = 0L;
                    return true;
                } catch (IOException e2) {
                    throw new ZipException(e2);
                }
            }
            return false;
        }
        throw new ZipException("negative buffersize for checkBufferSizeAndStartNextSplitFile");
    }

    public long k() {
        return this.f1498e;
    }

    public boolean o() {
        return this.f1498e != -1;
    }

    public void p(long j2) {
        this.f1497d.seek(j2);
    }

    public int r(int i2) {
        return this.f1497d.skipBytes(i2);
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2});
    }

    public SplitOutputStream(File file, long j2) {
        this.f1502i = new RawIO();
        if (j2 >= 0 && j2 < 65536) {
            throw new ZipException("split length less than minimum allowed split length of 65536 Bytes");
        }
        this.f1497d = new RandomAccessFile(file, RandomAccessFileMode.WRITE.a());
        this.f1498e = j2;
        this.f1499f = file;
        this.f1500g = 0;
        this.f1501h = 0L;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        if (i3 <= 0) {
            return;
        }
        long j2 = this.f1498e;
        if (j2 == -1) {
            this.f1497d.write(bArr, i2, i3);
            this.f1501h += i3;
            return;
        }
        long j3 = this.f1501h;
        if (j3 >= j2) {
            w();
            this.f1497d.write(bArr, i2, i3);
            this.f1501h = i3;
            return;
        }
        long j4 = i3;
        if (j3 + j4 > j2) {
            if (n(bArr)) {
                w();
                this.f1497d.write(bArr, i2, i3);
                this.f1501h = j4;
                return;
            }
            this.f1497d.write(bArr, i2, (int) (this.f1498e - this.f1501h));
            w();
            RandomAccessFile randomAccessFile = this.f1497d;
            long j5 = this.f1498e;
            long j6 = this.f1501h;
            randomAccessFile.write(bArr, i2 + ((int) (j5 - j6)), (int) (j4 - (j5 - j6)));
            this.f1501h = j4 - (this.f1498e - this.f1501h);
            return;
        }
        this.f1497d.write(bArr, i2, i3);
        this.f1501h += j4;
    }
}
