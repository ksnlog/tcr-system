package net.lingala.zip4j.io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderReader;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.DataDescriptor;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.PasswordCallback;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipInputStream extends InputStream {

    /* renamed from: d  reason: collision with root package name */
    private PushbackInputStream f1471d;

    /* renamed from: e  reason: collision with root package name */
    private DecompressedInputStream f1472e;

    /* renamed from: f  reason: collision with root package name */
    private HeaderReader f1473f;

    /* renamed from: g  reason: collision with root package name */
    private char[] f1474g;

    /* renamed from: h  reason: collision with root package name */
    private LocalFileHeader f1475h;

    /* renamed from: i  reason: collision with root package name */
    private CRC32 f1476i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f1477j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f1478k;

    /* renamed from: l  reason: collision with root package name */
    private Zip4jConfig f1479l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f1480m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f1481n;

    public ZipInputStream(InputStream inputStream, char[] cArr, Zip4jConfig zip4jConfig) {
        this(inputStream, cArr, null, zip4jConfig);
    }

    private void B() {
        if (this.f1475h.q() && !this.f1478k) {
            DataDescriptor j2 = this.f1473f.j(this.f1471d, c(this.f1475h.h()));
            this.f1475h.v(j2.c());
            this.f1475h.J(j2.e());
            this.f1475h.x(j2.d());
        }
    }

    private void D() {
        if (this.f1477j == null) {
            this.f1477j = new byte[512];
        }
        do {
        } while (read(this.f1477j) != -1);
        this.f1481n = true;
    }

    private void E() {
        this.f1475h = null;
        this.f1476i.reset();
    }

    private void G() {
        if ((this.f1475h.g() != EncryptionMethod.AES || !this.f1475h.c().d().equals(AesVersion.TWO)) && this.f1475h.f() != this.f1476i.getValue()) {
            ZipException.Type type = ZipException.Type.CHECKSUM_MISMATCH;
            if (x(this.f1475h)) {
                type = ZipException.Type.WRONG_PASSWORD;
            }
            throw new ZipException("Reached end of entry, but crc verification failed for " + this.f1475h.j(), type);
        }
    }

    private void I(LocalFileHeader localFileHeader) {
        if (!z(localFileHeader.j()) && localFileHeader.e() == CompressionMethod.STORE && localFileHeader.n() < 0) {
            throw new IOException("Invalid local file header for: " + localFileHeader.j() + ". Uncompressed size has to be set for entry of compression type store which is not a directory");
        }
    }

    private void b() {
        if (!this.f1480m) {
            return;
        }
        throw new IOException("Stream closed");
    }

    private boolean c(List<ExtraDataRecord> list) {
        if (list == null) {
            return false;
        }
        Iterator<ExtraDataRecord> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().d() == HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.a()) {
                return true;
            }
        }
        return false;
    }

    private void e() {
        this.f1472e.b(this.f1471d, this.f1472e.e(this.f1471d));
        B();
        G();
        E();
        this.f1481n = true;
    }

    private int k(AESExtraDataRecord aESExtraDataRecord) {
        if (aESExtraDataRecord != null && aESExtraDataRecord.c() != null) {
            return aESExtraDataRecord.c().e() + 12;
        }
        throw new ZipException("AesExtraDataRecord not found or invalid for Aes encrypted entry");
    }

    private long m(LocalFileHeader localFileHeader) {
        if (Zip4jUtil.i(localFileHeader).equals(CompressionMethod.STORE)) {
            return localFileHeader.n();
        }
        if (localFileHeader.q() && !this.f1478k) {
            return -1L;
        }
        return localFileHeader.d() - n(localFileHeader);
    }

    private int n(LocalFileHeader localFileHeader) {
        if (!localFileHeader.s()) {
            return 0;
        }
        if (localFileHeader.g().equals(EncryptionMethod.AES)) {
            return k(localFileHeader.c());
        }
        if (!localFileHeader.g().equals(EncryptionMethod.ZIP_STANDARD)) {
            return 0;
        }
        return 12;
    }

    private CipherInputStream<?> p(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader) {
        if (!localFileHeader.s()) {
            return new NoCipherInputStream(zipEntryInputStream, localFileHeader, this.f1474g, this.f1479l.a());
        }
        if (localFileHeader.g() == EncryptionMethod.AES) {
            return new AesCipherInputStream(zipEntryInputStream, localFileHeader, this.f1474g, this.f1479l.a(), this.f1479l.c());
        }
        if (localFileHeader.g() == EncryptionMethod.ZIP_STANDARD) {
            return new ZipStandardCipherInputStream(zipEntryInputStream, localFileHeader, this.f1474g, this.f1479l.a(), this.f1479l.c());
        }
        throw new ZipException(String.format("Entry [%s] Strong Encryption not supported", localFileHeader.j()), ZipException.Type.UNSUPPORTED_ENCRYPTION);
    }

    private DecompressedInputStream r(CipherInputStream<?> cipherInputStream, LocalFileHeader localFileHeader) {
        if (Zip4jUtil.i(localFileHeader) == CompressionMethod.DEFLATE) {
            return new InflaterInputStream(cipherInputStream, this.f1479l.a());
        }
        return new StoreInputStream(cipherInputStream);
    }

    private DecompressedInputStream w(LocalFileHeader localFileHeader) {
        return r(p(new ZipEntryInputStream(this.f1471d, m(localFileHeader)), localFileHeader), localFileHeader);
    }

    private boolean x(LocalFileHeader localFileHeader) {
        return localFileHeader.s() && EncryptionMethod.ZIP_STANDARD.equals(localFileHeader.g());
    }

    private boolean z(String str) {
        if (!str.endsWith("/") && !str.endsWith("\\")) {
            return false;
        }
        return true;
    }

    @Override // java.io.InputStream
    public int available() {
        b();
        return !this.f1481n ? 1 : 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f1480m) {
            return;
        }
        DecompressedInputStream decompressedInputStream = this.f1472e;
        if (decompressedInputStream != null) {
            decompressedInputStream.close();
        }
        this.f1480m = true;
    }

    public LocalFileHeader o(FileHeader fileHeader, boolean z2) {
        if (this.f1475h != null && z2) {
            D();
        }
        LocalFileHeader p2 = this.f1473f.p(this.f1471d, this.f1479l.b());
        this.f1475h = p2;
        if (p2 == null) {
            return null;
        }
        if (p2.s()) {
            char[] cArr = this.f1474g;
        }
        I(this.f1475h);
        this.f1476i.reset();
        if (fileHeader != null) {
            this.f1475h.x(fileHeader.f());
            this.f1475h.v(fileHeader.d());
            this.f1475h.J(fileHeader.n());
            this.f1475h.z(fileHeader.r());
            this.f1478k = true;
        } else {
            this.f1478k = false;
        }
        this.f1472e = w(this.f1475h);
        this.f1481n = false;
        return this.f1475h;
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    private ZipInputStream(InputStream inputStream, char[] cArr, PasswordCallback passwordCallback, Zip4jConfig zip4jConfig) {
        this.f1473f = new HeaderReader();
        this.f1476i = new CRC32();
        this.f1478k = false;
        this.f1480m = false;
        this.f1481n = false;
        if (zip4jConfig.a() >= 512) {
            this.f1471d = new PushbackInputStream(inputStream, zip4jConfig.a());
            this.f1474g = cArr;
            this.f1479l = zip4jConfig;
            return;
        }
        throw new IllegalArgumentException("Buffer size cannot be less than 512 bytes");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (!this.f1480m) {
            if (i3 < 0) {
                throw new IllegalArgumentException("Negative read length");
            }
            if (i3 == 0) {
                return 0;
            }
            if (this.f1475h == null) {
                return -1;
            }
            try {
                int read = this.f1472e.read(bArr, i2, i3);
                if (read == -1) {
                    e();
                } else {
                    this.f1476i.update(bArr, i2, read);
                }
                return read;
            } catch (IOException e2) {
                if (x(this.f1475h)) {
                    throw new ZipException(e2.getMessage(), e2.getCause(), ZipException.Type.WRONG_PASSWORD);
                }
                throw e2;
            }
        }
        throw new IOException("Stream closed");
    }
}
