package net.lingala.zip4j.headers;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.NumberedSplitRandomAccessFile;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.AbstractFileHeader;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.DataDescriptor;
import net.lingala.zip4j.model.DigitalSignature;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.Zip64ExtendedInfo;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class HeaderReader {

    /* renamed from: a  reason: collision with root package name */
    private ZipModel f1409a;

    /* renamed from: b  reason: collision with root package name */
    private final RawIO f1410b = new RawIO();

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f1411c = new byte[4];

    private long a(ZipModel zipModel) {
        if (zipModel.i()) {
            return zipModel.f().h();
        }
        return zipModel.b().h();
    }

    private long c(RandomAccessFile randomAccessFile) {
        long length = randomAccessFile.length();
        if (length >= 22) {
            long j2 = length - 22;
            w(randomAccessFile, j2);
            if (this.f1410b.c(randomAccessFile) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.a()) {
                return j2;
            }
            return d(randomAccessFile);
        }
        throw new ZipException("Zip file size less than size of zip headers. Probably not a zip file.");
    }

    private long d(RandomAccessFile randomAccessFile) {
        long length = randomAccessFile.length() - 22;
        long j2 = 65536;
        if (randomAccessFile.length() < 65536) {
            j2 = randomAccessFile.length();
        }
        while (j2 > 0 && length > 0) {
            length--;
            w(randomAccessFile, length);
            if (this.f1410b.c(randomAccessFile) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.a()) {
                return length;
            }
            j2--;
        }
        throw new ZipException("Zip headers not found. Probably not a zip file");
    }

    private List<ExtraDataRecord> e(byte[] bArr, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < i2) {
            ExtraDataRecord extraDataRecord = new ExtraDataRecord();
            extraDataRecord.g(this.f1410b.m(bArr, i3));
            int i4 = i3 + 2;
            int m2 = this.f1410b.m(bArr, i4);
            extraDataRecord.h(m2);
            int i5 = i4 + 2;
            if (m2 > 0) {
                byte[] bArr2 = new byte[m2];
                System.arraycopy(bArr, i5, bArr2, 0, m2);
                extraDataRecord.f(bArr2);
            }
            i3 = i5 + m2;
            arrayList.add(extraDataRecord);
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        return arrayList;
    }

    private AESExtraDataRecord f(List<ExtraDataRecord> list, RawIO rawIO) {
        if (list == null) {
            return null;
        }
        for (ExtraDataRecord extraDataRecord : list) {
            if (extraDataRecord != null) {
                long d2 = extraDataRecord.d();
                HeaderSignature headerSignature = HeaderSignature.AES_EXTRA_DATA_RECORD;
                if (d2 == headerSignature.a()) {
                    byte[] c2 = extraDataRecord.c();
                    if (c2 != null && c2.length == 7) {
                        AESExtraDataRecord aESExtraDataRecord = new AESExtraDataRecord();
                        aESExtraDataRecord.b(headerSignature);
                        aESExtraDataRecord.k(extraDataRecord.e());
                        byte[] c3 = extraDataRecord.c();
                        aESExtraDataRecord.i(AesVersion.a(rawIO.m(c3, 0)));
                        byte[] bArr = new byte[2];
                        System.arraycopy(c3, 2, bArr, 0, 2);
                        aESExtraDataRecord.l(new String(bArr));
                        aESExtraDataRecord.h(AesKeyStrength.a(c3[4] & 255));
                        aESExtraDataRecord.j(CompressionMethod.b(rawIO.m(c3, 5)));
                        return aESExtraDataRecord;
                    }
                    throw new ZipException("corrupt AES extra data records");
                }
            }
        }
        return null;
    }

    private void g(AbstractFileHeader abstractFileHeader, RawIO rawIO) {
        AESExtraDataRecord f2;
        if (abstractFileHeader.h() != null && abstractFileHeader.h().size() > 0 && (f2 = f(abstractFileHeader.h(), rawIO)) != null) {
            abstractFileHeader.u(f2);
            abstractFileHeader.B(EncryptionMethod.AES);
        }
    }

    private CentralDirectory i(RandomAccessFile randomAccessFile, RawIO rawIO, Charset charset) {
        CentralDirectory centralDirectory = new CentralDirectory();
        ArrayList arrayList = new ArrayList();
        long e2 = HeaderUtil.e(this.f1409a);
        long a2 = a(this.f1409a);
        randomAccessFile.seek(e2);
        int i2 = 2;
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[4];
        int i3 = 0;
        int i4 = 0;
        while (i4 < a2) {
            FileHeader fileHeader = new FileHeader();
            byte[] bArr3 = bArr2;
            long c2 = rawIO.c(randomAccessFile);
            HeaderSignature headerSignature = HeaderSignature.CENTRAL_DIRECTORY;
            if (c2 == headerSignature.a()) {
                fileHeader.b(headerSignature);
                fileHeader.Y(rawIO.l(randomAccessFile));
                fileHeader.K(rawIO.l(randomAccessFile));
                byte[] bArr4 = new byte[i2];
                randomAccessFile.readFully(bArr4);
                fileHeader.A(BitUtils.a(bArr4[i3], i3));
                fileHeader.y(BitUtils.a(bArr4[i3], 3));
                fileHeader.G(BitUtils.a(bArr4[1], 3));
                fileHeader.H((byte[]) bArr4.clone());
                fileHeader.w(CompressionMethod.b(rawIO.l(randomAccessFile)));
                fileHeader.I(rawIO.c(randomAccessFile));
                randomAccessFile.readFully(bArr3);
                byte[] bArr5 = bArr;
                fileHeader.x(rawIO.j(bArr3, i3));
                fileHeader.v(rawIO.i(randomAccessFile, 4));
                fileHeader.J(rawIO.i(randomAccessFile, 4));
                int l2 = rawIO.l(randomAccessFile);
                fileHeader.F(l2);
                fileHeader.D(rawIO.l(randomAccessFile));
                int l3 = rawIO.l(randomAccessFile);
                fileHeader.V(l3);
                fileHeader.S(rawIO.l(randomAccessFile));
                randomAccessFile.readFully(bArr5);
                fileHeader.W((byte[]) bArr5.clone());
                randomAccessFile.readFully(bArr3);
                fileHeader.T((byte[]) bArr3.clone());
                randomAccessFile.readFully(bArr3);
                long j2 = a2;
                fileHeader.X(rawIO.j(bArr3, 0));
                if (l2 > 0) {
                    byte[] bArr6 = new byte[l2];
                    randomAccessFile.readFully(bArr6);
                    fileHeader.E(HeaderUtil.a(bArr6, fileHeader.t(), charset));
                    fileHeader.z(b(fileHeader.O(), fileHeader.j()));
                    o(randomAccessFile, fileHeader);
                    t(fileHeader, rawIO);
                    g(fileHeader, rawIO);
                    if (l3 > 0) {
                        byte[] bArr7 = new byte[l3];
                        randomAccessFile.readFully(bArr7);
                        fileHeader.U(HeaderUtil.a(bArr7, fileHeader.t(), charset));
                    }
                    if (fileHeader.s()) {
                        if (fileHeader.c() != null) {
                            fileHeader.B(EncryptionMethod.AES);
                        } else {
                            fileHeader.B(EncryptionMethod.ZIP_STANDARD);
                        }
                    }
                    arrayList.add(fileHeader);
                    i4++;
                    bArr = bArr5;
                    bArr2 = bArr3;
                    a2 = j2;
                    i2 = 2;
                    i3 = 0;
                } else {
                    throw new ZipException("Invalid entry name in file header");
                }
            } else {
                throw new ZipException("Expected central directory entry not found (#" + (i4 + 1) + ")");
            }
        }
        centralDirectory.b(arrayList);
        DigitalSignature digitalSignature = new DigitalSignature();
        HeaderSignature headerSignature2 = HeaderSignature.DIGITAL_SIGNATURE;
        if (rawIO.c(randomAccessFile) == headerSignature2.a()) {
            digitalSignature.b(headerSignature2);
            digitalSignature.e(rawIO.l(randomAccessFile));
            if (digitalSignature.c() > 0) {
                byte[] bArr8 = new byte[digitalSignature.c()];
                randomAccessFile.readFully(bArr8);
                digitalSignature.d(new String(bArr8));
            }
        }
        return centralDirectory;
    }

    private EndOfCentralDirectoryRecord k(RandomAccessFile randomAccessFile, RawIO rawIO, Zip4jConfig zip4jConfig) {
        long c2 = c(randomAccessFile);
        w(randomAccessFile, 4 + c2);
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = new EndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.b(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
        endOfCentralDirectoryRecord.k(rawIO.l(randomAccessFile));
        endOfCentralDirectoryRecord.l(rawIO.l(randomAccessFile));
        endOfCentralDirectoryRecord.q(rawIO.l(randomAccessFile));
        endOfCentralDirectoryRecord.p(rawIO.l(randomAccessFile));
        endOfCentralDirectoryRecord.o(rawIO.c(randomAccessFile));
        endOfCentralDirectoryRecord.m(c2);
        randomAccessFile.readFully(this.f1411c);
        boolean z2 = false;
        endOfCentralDirectoryRecord.n(rawIO.j(this.f1411c, 0));
        endOfCentralDirectoryRecord.j(v(randomAccessFile, rawIO.l(randomAccessFile), zip4jConfig.b()));
        ZipModel zipModel = this.f1409a;
        if (endOfCentralDirectoryRecord.d() > 0) {
            z2 = true;
        }
        zipModel.m(z2);
        return endOfCentralDirectoryRecord;
    }

    private List<ExtraDataRecord> l(InputStream inputStream, int i2) {
        if (i2 < 4) {
            if (i2 > 0) {
                inputStream.skip(i2);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i2];
        Zip4jUtil.l(inputStream, bArr);
        try {
            return e(bArr, i2);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<ExtraDataRecord> m(RandomAccessFile randomAccessFile, int i2) {
        if (i2 < 4) {
            if (i2 > 0) {
                randomAccessFile.skipBytes(i2);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i2];
        randomAccessFile.read(bArr);
        try {
            return e(bArr, i2);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private void n(InputStream inputStream, LocalFileHeader localFileHeader) {
        int i2 = localFileHeader.i();
        if (i2 <= 0) {
            return;
        }
        localFileHeader.C(l(inputStream, i2));
    }

    private void o(RandomAccessFile randomAccessFile, FileHeader fileHeader) {
        int i2 = fileHeader.i();
        if (i2 <= 0) {
            return;
        }
        fileHeader.C(m(randomAccessFile, i2));
    }

    private Zip64EndOfCentralDirectoryRecord q(RandomAccessFile randomAccessFile, RawIO rawIO) {
        if (this.f1409a.e() != null) {
            long d2 = this.f1409a.e().d();
            if (d2 >= 0) {
                randomAccessFile.seek(d2);
                Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = new Zip64EndOfCentralDirectoryRecord();
                long c2 = rawIO.c(randomAccessFile);
                HeaderSignature headerSignature = HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD;
                if (c2 == headerSignature.a()) {
                    zip64EndOfCentralDirectoryRecord.b(headerSignature);
                    zip64EndOfCentralDirectoryRecord.q(rawIO.h(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.t(rawIO.l(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.u(rawIO.l(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.m(rawIO.c(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.n(rawIO.c(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.s(rawIO.h(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.r(rawIO.h(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.p(rawIO.h(randomAccessFile));
                    zip64EndOfCentralDirectoryRecord.o(rawIO.h(randomAccessFile));
                    long g2 = zip64EndOfCentralDirectoryRecord.g() - 44;
                    if (g2 > 0) {
                        byte[] bArr = new byte[(int) g2];
                        randomAccessFile.readFully(bArr);
                        zip64EndOfCentralDirectoryRecord.l(bArr);
                    }
                    return zip64EndOfCentralDirectoryRecord;
                }
                throw new ZipException("invalid signature for zip64 end of central directory record");
            }
            throw new ZipException("invalid offset for start of end of central directory record");
        }
        throw new ZipException("invalid zip64 end of central directory locator");
    }

    private Zip64EndOfCentralDirectoryLocator r(RandomAccessFile randomAccessFile, RawIO rawIO, long j2) {
        Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = new Zip64EndOfCentralDirectoryLocator();
        x(randomAccessFile, j2);
        HeaderSignature headerSignature = HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR;
        if (rawIO.c(randomAccessFile) == headerSignature.a()) {
            this.f1409a.q(true);
            zip64EndOfCentralDirectoryLocator.b(headerSignature);
            zip64EndOfCentralDirectoryLocator.f(rawIO.c(randomAccessFile));
            zip64EndOfCentralDirectoryLocator.g(rawIO.h(randomAccessFile));
            zip64EndOfCentralDirectoryLocator.h(rawIO.c(randomAccessFile));
            return zip64EndOfCentralDirectoryLocator;
        }
        this.f1409a.q(false);
        return null;
    }

    private Zip64ExtendedInfo s(List<ExtraDataRecord> list, RawIO rawIO, long j2, long j3, long j4, int i2) {
        for (ExtraDataRecord extraDataRecord : list) {
            if (extraDataRecord != null && HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.a() == extraDataRecord.d()) {
                Zip64ExtendedInfo zip64ExtendedInfo = new Zip64ExtendedInfo();
                byte[] c2 = extraDataRecord.c();
                if (extraDataRecord.e() <= 0) {
                    return null;
                }
                int i3 = 0;
                if (extraDataRecord.e() > 0 && j2 == 4294967295L) {
                    zip64ExtendedInfo.j(rawIO.j(c2, 0));
                    i3 = 8;
                }
                if (i3 < extraDataRecord.e() && j3 == 4294967295L) {
                    zip64ExtendedInfo.g(rawIO.j(c2, i3));
                    i3 += 8;
                }
                if (i3 < extraDataRecord.e() && j4 == 4294967295L) {
                    zip64ExtendedInfo.i(rawIO.j(c2, i3));
                    i3 += 8;
                }
                if (i3 < extraDataRecord.e() && i2 == 65535) {
                    zip64ExtendedInfo.h(rawIO.e(c2, i3));
                }
                return zip64ExtendedInfo;
            }
        }
        return null;
    }

    private void t(FileHeader fileHeader, RawIO rawIO) {
        Zip64ExtendedInfo s2;
        if (fileHeader.h() == null || fileHeader.h().size() <= 0 || (s2 = s(fileHeader.h(), rawIO, fileHeader.n(), fileHeader.d(), fileHeader.Q(), fileHeader.N())) == null) {
            return;
        }
        fileHeader.L(s2);
        if (s2.f() != -1) {
            fileHeader.J(s2.f());
        }
        if (s2.c() != -1) {
            fileHeader.v(s2.c());
        }
        if (s2.e() != -1) {
            fileHeader.X(s2.e());
        }
        if (s2.d() != -1) {
            fileHeader.S(s2.d());
        }
    }

    private void u(LocalFileHeader localFileHeader, RawIO rawIO) {
        Zip64ExtendedInfo s2;
        if (localFileHeader != null) {
            if (localFileHeader.h() == null || localFileHeader.h().size() <= 0 || (s2 = s(localFileHeader.h(), rawIO, localFileHeader.n(), localFileHeader.d(), 0L, 0)) == null) {
                return;
            }
            localFileHeader.L(s2);
            if (s2.f() != -1) {
                localFileHeader.J(s2.f());
            }
            if (s2.c() != -1) {
                localFileHeader.v(s2.c());
                return;
            }
            return;
        }
        throw new ZipException("file header is null in reading Zip64 Extended Info");
    }

    private String v(RandomAccessFile randomAccessFile, int i2, Charset charset) {
        if (i2 <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i2];
            randomAccessFile.readFully(bArr);
            if (charset == null) {
                charset = InternalZipConstants.f1719c;
            }
            return HeaderUtil.a(bArr, false, charset);
        } catch (IOException unused) {
            return null;
        }
    }

    private void w(RandomAccessFile randomAccessFile, long j2) {
        if (randomAccessFile instanceof NumberedSplitRandomAccessFile) {
            ((NumberedSplitRandomAccessFile) randomAccessFile).k(j2);
        } else {
            randomAccessFile.seek(j2);
        }
    }

    private void x(RandomAccessFile randomAccessFile, long j2) {
        w(randomAccessFile, (((j2 - 4) - 8) - 4) - 4);
    }

    public boolean b(byte[] bArr, String str) {
        byte b2 = bArr[0];
        if (b2 != 0 && BitUtils.a(b2, 4)) {
            return true;
        }
        byte b3 = bArr[3];
        if (b3 != 0 && BitUtils.a(b3, 6)) {
            return true;
        }
        if (str == null) {
            return false;
        }
        if (!str.endsWith("/") && !str.endsWith("\\")) {
            return false;
        }
        return true;
    }

    public ZipModel h(RandomAccessFile randomAccessFile, Zip4jConfig zip4jConfig) {
        if (randomAccessFile.length() == 0) {
            return new ZipModel();
        }
        if (randomAccessFile.length() >= 22) {
            ZipModel zipModel = new ZipModel();
            this.f1409a = zipModel;
            try {
                zipModel.l(k(randomAccessFile, this.f1410b, zip4jConfig));
                if (this.f1409a.b().h() == 0) {
                    return this.f1409a;
                }
                ZipModel zipModel2 = this.f1409a;
                zipModel2.o(r(randomAccessFile, this.f1410b, zipModel2.b().f()));
                if (this.f1409a.i()) {
                    this.f1409a.p(q(randomAccessFile, this.f1410b));
                    if (this.f1409a.f() != null && this.f1409a.f().c() > 0) {
                        this.f1409a.m(true);
                    } else {
                        this.f1409a.m(false);
                    }
                }
                this.f1409a.j(i(randomAccessFile, this.f1410b, zip4jConfig.b()));
                return this.f1409a;
            } catch (ZipException e2) {
                throw e2;
            } catch (IOException e3) {
                e3.printStackTrace();
                throw new ZipException("Zip headers not found. Probably not a zip file or a corrupted zip file", e3);
            }
        }
        throw new ZipException("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
    }

    public DataDescriptor j(InputStream inputStream, boolean z2) {
        DataDescriptor dataDescriptor = new DataDescriptor();
        byte[] bArr = new byte[4];
        Zip4jUtil.l(inputStream, bArr);
        long j2 = this.f1410b.j(bArr, 0);
        HeaderSignature headerSignature = HeaderSignature.EXTRA_DATA_RECORD;
        if (j2 == headerSignature.a()) {
            dataDescriptor.b(headerSignature);
            Zip4jUtil.l(inputStream, bArr);
            dataDescriptor.g(this.f1410b.j(bArr, 0));
        } else {
            dataDescriptor.g(j2);
        }
        if (z2) {
            dataDescriptor.f(this.f1410b.f(inputStream));
            dataDescriptor.h(this.f1410b.f(inputStream));
        } else {
            dataDescriptor.f(this.f1410b.b(inputStream));
            dataDescriptor.h(this.f1410b.b(inputStream));
        }
        return dataDescriptor;
    }

    public LocalFileHeader p(InputStream inputStream, Charset charset) {
        LocalFileHeader localFileHeader = new LocalFileHeader();
        byte[] bArr = new byte[4];
        int b2 = this.f1410b.b(inputStream);
        if (b2 == HeaderSignature.TEMPORARY_SPANNING_MARKER.a()) {
            b2 = this.f1410b.b(inputStream);
        }
        HeaderSignature headerSignature = HeaderSignature.LOCAL_FILE_HEADER;
        if (b2 != headerSignature.a()) {
            return null;
        }
        localFileHeader.b(headerSignature);
        localFileHeader.K(this.f1410b.k(inputStream));
        byte[] bArr2 = new byte[2];
        if (Zip4jUtil.l(inputStream, bArr2) == 2) {
            localFileHeader.A(BitUtils.a(bArr2[0], 0));
            localFileHeader.y(BitUtils.a(bArr2[0], 3));
            boolean z2 = true;
            localFileHeader.G(BitUtils.a(bArr2[1], 3));
            localFileHeader.H((byte[]) bArr2.clone());
            localFileHeader.w(CompressionMethod.b(this.f1410b.k(inputStream)));
            localFileHeader.I(this.f1410b.b(inputStream));
            Zip4jUtil.l(inputStream, bArr);
            localFileHeader.x(this.f1410b.j(bArr, 0));
            localFileHeader.v(this.f1410b.g(inputStream, 4));
            localFileHeader.J(this.f1410b.g(inputStream, 4));
            int k2 = this.f1410b.k(inputStream);
            localFileHeader.F(k2);
            localFileHeader.D(this.f1410b.k(inputStream));
            if (k2 > 0) {
                byte[] bArr3 = new byte[k2];
                Zip4jUtil.l(inputStream, bArr3);
                String a2 = HeaderUtil.a(bArr3, localFileHeader.t(), charset);
                localFileHeader.E(a2);
                if (!a2.endsWith("/") && !a2.endsWith("\\")) {
                    z2 = false;
                }
                localFileHeader.z(z2);
                n(inputStream, localFileHeader);
                u(localFileHeader, this.f1410b);
                g(localFileHeader, this.f1410b);
                if (localFileHeader.s() && localFileHeader.g() != EncryptionMethod.AES) {
                    if (BitUtils.a(localFileHeader.l()[0], 6)) {
                        localFileHeader.B(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG);
                    } else {
                        localFileHeader.B(EncryptionMethod.ZIP_STANDARD);
                    }
                }
                return localFileHeader;
            }
            throw new ZipException("Invalid entry name in local file header");
        }
        throw new ZipException("Could not read enough bytes for generalPurposeFlags");
    }
}
