package net.lingala.zip4j.io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.FileHeaderFactory;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipOutputStream extends OutputStream {

    /* renamed from: d  reason: collision with root package name */
    private CountingOutputStream f1506d;

    /* renamed from: e  reason: collision with root package name */
    private char[] f1507e;

    /* renamed from: f  reason: collision with root package name */
    private ZipModel f1508f;

    /* renamed from: g  reason: collision with root package name */
    private CompressedOutputStream f1509g;

    /* renamed from: h  reason: collision with root package name */
    private FileHeader f1510h;

    /* renamed from: i  reason: collision with root package name */
    private LocalFileHeader f1511i;

    /* renamed from: o  reason: collision with root package name */
    private Zip4jConfig f1517o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f1518p;

    /* renamed from: j  reason: collision with root package name */
    private FileHeaderFactory f1512j = new FileHeaderFactory();

    /* renamed from: k  reason: collision with root package name */
    private HeaderWriter f1513k = new HeaderWriter();

    /* renamed from: l  reason: collision with root package name */
    private CRC32 f1514l = new CRC32();

    /* renamed from: m  reason: collision with root package name */
    private RawIO f1515m = new RawIO();

    /* renamed from: n  reason: collision with root package name */
    private long f1516n = 0;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1519q = true;

    public ZipOutputStream(OutputStream outputStream, char[] cArr, Zip4jConfig zip4jConfig, ZipModel zipModel) {
        if (zip4jConfig.a() >= 512) {
            CountingOutputStream countingOutputStream = new CountingOutputStream(outputStream);
            this.f1506d = countingOutputStream;
            this.f1507e = cArr;
            this.f1517o = zip4jConfig;
            this.f1508f = p(zipModel, countingOutputStream);
            this.f1518p = false;
            B();
            return;
        }
        throw new IllegalArgumentException("Buffer size cannot be less than 512 bytes");
    }

    private void B() {
        if (!this.f1506d.o()) {
            return;
        }
        this.f1515m.o(this.f1506d, (int) HeaderSignature.SPLIT_ZIP.a());
    }

    private ZipParameters b(ZipParameters zipParameters) {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        if (FileUtils.x(zipParameters.k())) {
            zipParameters2.F(false);
            zipParameters2.w(CompressionMethod.STORE);
            zipParameters2.y(false);
            zipParameters2.B(0L);
        }
        if (zipParameters.l() <= 0) {
            zipParameters2.E(System.currentTimeMillis());
        }
        return zipParameters2;
    }

    private void e() {
        if (!this.f1518p) {
            return;
        }
        throw new IOException("Stream is closed");
    }

    private void k(ZipParameters zipParameters) {
        FileHeader d2 = this.f1512j.d(zipParameters, this.f1506d.o(), this.f1506d.b(), this.f1517o.b(), this.f1515m);
        this.f1510h = d2;
        d2.X(this.f1506d.m());
        LocalFileHeader f2 = this.f1512j.f(this.f1510h);
        this.f1511i = f2;
        this.f1513k.p(this.f1508f, f2, this.f1506d, this.f1517o.b());
    }

    private CipherOutputStream<?> m(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters) {
        if (!zipParameters.o()) {
            return new NoCipherOutputStream(zipEntryOutputStream, zipParameters, null);
        }
        char[] cArr = this.f1507e;
        if (cArr != null && cArr.length != 0) {
            if (zipParameters.f() == EncryptionMethod.AES) {
                return new AesCipherOutputStream(zipEntryOutputStream, zipParameters, this.f1507e, this.f1517o.c());
            }
            if (zipParameters.f() == EncryptionMethod.ZIP_STANDARD) {
                return new ZipStandardCipherOutputStream(zipEntryOutputStream, zipParameters, this.f1507e, this.f1517o.c());
            }
            EncryptionMethod f2 = zipParameters.f();
            EncryptionMethod encryptionMethod = EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG;
            if (f2 == encryptionMethod) {
                throw new ZipException(encryptionMethod + " encryption method is not supported");
            }
            throw new ZipException("Invalid encryption method");
        }
        throw new ZipException("password not set");
    }

    private CompressedOutputStream n(CipherOutputStream<?> cipherOutputStream, ZipParameters zipParameters) {
        if (zipParameters.d() == CompressionMethod.DEFLATE) {
            return new DeflaterOutputStream(cipherOutputStream, zipParameters.c(), this.f1517o.a());
        }
        return new StoreOutputStream(cipherOutputStream);
    }

    private CompressedOutputStream o(ZipParameters zipParameters) {
        return n(m(new ZipEntryOutputStream(this.f1506d), zipParameters), zipParameters);
    }

    private ZipModel p(ZipModel zipModel, CountingOutputStream countingOutputStream) {
        if (zipModel == null) {
            zipModel = new ZipModel();
        }
        if (countingOutputStream.o()) {
            zipModel.m(true);
            zipModel.n(countingOutputStream.n());
        }
        return zipModel;
    }

    private void w() {
        this.f1516n = 0L;
        this.f1514l.reset();
        this.f1509g.close();
    }

    private void x(ZipParameters zipParameters) {
        if (!Zip4jUtil.k(zipParameters.k())) {
            if (zipParameters.d() == CompressionMethod.STORE && zipParameters.h() < 0 && !FileUtils.x(zipParameters.k()) && zipParameters.u()) {
                throw new IllegalArgumentException("uncompressed size should be set for zip entries of compression type store");
            }
            return;
        }
        throw new IllegalArgumentException("fileNameInZip is null or empty");
    }

    private boolean z(FileHeader fileHeader) {
        boolean z2;
        if (fileHeader.s() && fileHeader.g().equals(EncryptionMethod.AES)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return true;
        }
        return fileHeader.c().d().equals(AesVersion.ONE);
    }

    public FileHeader c() {
        this.f1509g.b();
        long c2 = this.f1509g.c();
        this.f1510h.v(c2);
        this.f1511i.v(c2);
        this.f1510h.J(this.f1516n);
        this.f1511i.J(this.f1516n);
        if (z(this.f1510h)) {
            this.f1510h.x(this.f1514l.getValue());
            this.f1511i.x(this.f1514l.getValue());
        }
        this.f1508f.c().add(this.f1511i);
        this.f1508f.a().a().add(this.f1510h);
        if (this.f1511i.q()) {
            this.f1513k.n(this.f1511i, this.f1506d);
        }
        w();
        this.f1519q = true;
        return this.f1510h;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.f1519q) {
            c();
        }
        this.f1508f.b().n(this.f1506d.k());
        this.f1513k.d(this.f1508f, this.f1506d, this.f1517o.b());
        this.f1506d.close();
        this.f1518p = true;
    }

    public void r(ZipParameters zipParameters) {
        x(zipParameters);
        ZipParameters b2 = b(zipParameters);
        k(b2);
        this.f1509g = o(b2);
        this.f1519q = false;
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
        e();
        this.f1514l.update(bArr, i2, i3);
        this.f1509g.write(bArr, i2, i3);
        this.f1516n += i3;
    }
}
