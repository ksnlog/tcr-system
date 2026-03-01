package net.lingala.zip4j.headers;

import androidx.core.graphics.h;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.outputstream.CountingOutputStream;
import net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport;
import net.lingala.zip4j.io.outputstream.SplitOutputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class HeaderWriter {

    /* renamed from: a  reason: collision with root package name */
    private final RawIO f1426a = new RawIO();

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f1427b = new byte[8];

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f1428c = new byte[4];

    private Zip64EndOfCentralDirectoryRecord a(ZipModel zipModel, int i2, long j2) {
        long j3;
        Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = new Zip64EndOfCentralDirectoryRecord();
        zip64EndOfCentralDirectoryRecord.b(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        zip64EndOfCentralDirectoryRecord.q(44L);
        if (zipModel.a() != null && zipModel.a().a() != null && zipModel.a().a().size() > 0) {
            FileHeader fileHeader = zipModel.a().a().get(0);
            zip64EndOfCentralDirectoryRecord.t(fileHeader.R());
            zip64EndOfCentralDirectoryRecord.u(fileHeader.o());
        }
        zip64EndOfCentralDirectoryRecord.m(zipModel.b().d());
        zip64EndOfCentralDirectoryRecord.n(zipModel.b().e());
        long size = zipModel.a().a().size();
        if (zipModel.h()) {
            j3 = c(zipModel.a().a(), zipModel.b().d());
        } else {
            j3 = size;
        }
        zip64EndOfCentralDirectoryRecord.s(j3);
        zip64EndOfCentralDirectoryRecord.r(size);
        zip64EndOfCentralDirectoryRecord.p(i2);
        zip64EndOfCentralDirectoryRecord.o(j2);
        return zip64EndOfCentralDirectoryRecord;
    }

    private int b(FileHeader fileHeader, boolean z2) {
        int i2;
        if (z2) {
            i2 = 32;
        } else {
            i2 = 0;
        }
        if (fileHeader.c() != null) {
            i2 += 11;
        }
        if (fileHeader.h() != null) {
            for (ExtraDataRecord extraDataRecord : fileHeader.h()) {
                if (extraDataRecord.d() != HeaderSignature.AES_EXTRA_DATA_RECORD.a() && extraDataRecord.d() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.a()) {
                    i2 += extraDataRecord.e() + 4;
                }
            }
        }
        return i2;
    }

    private long c(List<FileHeader> list, int i2) {
        if (list != null) {
            int i3 = 0;
            for (FileHeader fileHeader : list) {
                if (fileHeader.N() == i2) {
                    i3++;
                }
            }
            return i3;
        }
        throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
    }

    private int e(OutputStream outputStream) {
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).b();
        }
        return ((CountingOutputStream) outputStream).b();
    }

    private long f(ZipModel zipModel) {
        if (zipModel.i() && zipModel.f() != null && zipModel.f().e() != -1) {
            return zipModel.f().e();
        }
        return zipModel.b().g();
    }

    private boolean g(OutputStream outputStream) {
        if (outputStream instanceof SplitOutputStream) {
            return ((SplitOutputStream) outputStream).o();
        }
        if (outputStream instanceof CountingOutputStream) {
            return ((CountingOutputStream) outputStream).o();
        }
        return false;
    }

    private boolean h(FileHeader fileHeader) {
        if (fileHeader.d() < 4294967295L && fileHeader.n() < 4294967295L && fileHeader.Q() < 4294967295L && fileHeader.N() < 65535) {
            return false;
        }
        return true;
    }

    private void i(ZipModel zipModel, OutputStream outputStream) {
        int i2;
        if (outputStream instanceof OutputStreamWithSplitZipSupport) {
            OutputStreamWithSplitZipSupport outputStreamWithSplitZipSupport = (OutputStreamWithSplitZipSupport) outputStream;
            zipModel.b().n(outputStreamWithSplitZipSupport.c());
            i2 = outputStreamWithSplitZipSupport.b();
        } else {
            i2 = 0;
        }
        if (zipModel.i()) {
            if (zipModel.f() == null) {
                zipModel.p(new Zip64EndOfCentralDirectoryRecord());
            }
            if (zipModel.e() == null) {
                zipModel.o(new Zip64EndOfCentralDirectoryLocator());
            }
            zipModel.f().o(zipModel.b().g());
            zipModel.e().f(i2);
            zipModel.e().h(i2 + 1);
        }
        zipModel.b().k(i2);
        zipModel.b().l(i2);
    }

    private void j(SplitOutputStream splitOutputStream, FileHeader fileHeader) {
        if (fileHeader.n() >= 4294967295L) {
            this.f1426a.r(this.f1427b, 0, 4294967295L);
            splitOutputStream.write(this.f1427b, 0, 4);
            splitOutputStream.write(this.f1427b, 0, 4);
            int k2 = fileHeader.k() + 4 + 2 + 2;
            if (splitOutputStream.r(k2) == k2) {
                this.f1426a.q(splitOutputStream, fileHeader.n());
                this.f1426a.q(splitOutputStream, fileHeader.d());
                return;
            }
            throw new ZipException("Unable to skip " + k2 + " bytes to update LFH");
        }
        this.f1426a.r(this.f1427b, 0, fileHeader.d());
        splitOutputStream.write(this.f1427b, 0, 4);
        this.f1426a.r(this.f1427b, 0, fileHeader.n());
        splitOutputStream.write(this.f1427b, 0, 4);
    }

    private void l(ZipModel zipModel, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) {
        if (zipModel.a() != null && zipModel.a().a() != null && zipModel.a().a().size() > 0) {
            for (FileHeader fileHeader : zipModel.a().a()) {
                o(zipModel, fileHeader, byteArrayOutputStream, rawIO, charset);
            }
        }
    }

    private void m(ZipModel zipModel, int i2, long j2, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) {
        long j3;
        byte[] bArr = new byte[8];
        rawIO.o(byteArrayOutputStream, (int) HeaderSignature.END_OF_CENTRAL_DIRECTORY.a());
        rawIO.s(byteArrayOutputStream, zipModel.b().d());
        rawIO.s(byteArrayOutputStream, zipModel.b().e());
        long size = zipModel.a().a().size();
        if (zipModel.h()) {
            j3 = c(zipModel.a().a(), zipModel.b().d());
        } else {
            j3 = size;
        }
        if (j3 > 65535) {
            j3 = 65535;
        }
        rawIO.s(byteArrayOutputStream, (int) j3);
        if (size > 65535) {
            size = 65535;
        }
        rawIO.s(byteArrayOutputStream, (int) size);
        rawIO.o(byteArrayOutputStream, i2);
        if (j2 > 4294967295L) {
            rawIO.r(bArr, 0, 4294967295L);
            byteArrayOutputStream.write(bArr, 0, 4);
        } else {
            rawIO.r(bArr, 0, j2);
            byteArrayOutputStream.write(bArr, 0, 4);
        }
        String c2 = zipModel.b().c();
        if (Zip4jUtil.j(c2)) {
            byte[] b2 = HeaderUtil.b(c2, charset);
            rawIO.s(byteArrayOutputStream, b2.length);
            byteArrayOutputStream.write(b2);
            return;
        }
        rawIO.s(byteArrayOutputStream, 0);
    }

    private void o(ZipModel zipModel, FileHeader fileHeader, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO, Charset charset) {
        byte[] bArr;
        if (fileHeader != null) {
            try {
                byte[] bArr2 = {0, 0};
                boolean h2 = h(fileHeader);
                rawIO.o(byteArrayOutputStream, (int) fileHeader.a().a());
                rawIO.s(byteArrayOutputStream, fileHeader.R());
                rawIO.s(byteArrayOutputStream, fileHeader.o());
                byteArrayOutputStream.write(fileHeader.l());
                rawIO.s(byteArrayOutputStream, fileHeader.e().a());
                rawIO.r(this.f1427b, 0, fileHeader.m());
                byteArrayOutputStream.write(this.f1427b, 0, 4);
                rawIO.r(this.f1427b, 0, fileHeader.f());
                byteArrayOutputStream.write(this.f1427b, 0, 4);
                if (h2) {
                    rawIO.r(this.f1427b, 0, 4294967295L);
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                    zipModel.q(true);
                    bArr = bArr2;
                } else {
                    bArr = bArr2;
                    rawIO.r(this.f1427b, 0, fileHeader.d());
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                    rawIO.r(this.f1427b, 0, fileHeader.n());
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                }
                byte[] bArr3 = new byte[0];
                if (Zip4jUtil.j(fileHeader.j())) {
                    bArr3 = HeaderUtil.b(fileHeader.j(), charset);
                }
                rawIO.s(byteArrayOutputStream, bArr3.length);
                byte[] bArr4 = new byte[4];
                if (h2) {
                    rawIO.r(this.f1427b, 0, 4294967295L);
                    System.arraycopy(this.f1427b, 0, bArr4, 0, 4);
                } else {
                    rawIO.r(this.f1427b, 0, fileHeader.Q());
                    System.arraycopy(this.f1427b, 0, bArr4, 0, 4);
                }
                rawIO.s(byteArrayOutputStream, b(fileHeader, h2));
                String P = fileHeader.P();
                byte[] bArr5 = new byte[0];
                if (Zip4jUtil.j(P)) {
                    bArr5 = HeaderUtil.b(P, charset);
                }
                rawIO.s(byteArrayOutputStream, bArr5.length);
                if (h2) {
                    rawIO.p(this.f1428c, 0, 65535);
                    byteArrayOutputStream.write(this.f1428c, 0, 2);
                } else {
                    rawIO.s(byteArrayOutputStream, fileHeader.N());
                }
                byteArrayOutputStream.write(bArr);
                byteArrayOutputStream.write(fileHeader.O());
                byteArrayOutputStream.write(bArr4);
                if (bArr3.length > 0) {
                    byteArrayOutputStream.write(bArr3);
                }
                if (h2) {
                    zipModel.q(true);
                    rawIO.s(byteArrayOutputStream, (int) HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.a());
                    rawIO.s(byteArrayOutputStream, 28);
                    rawIO.q(byteArrayOutputStream, fileHeader.n());
                    rawIO.q(byteArrayOutputStream, fileHeader.d());
                    rawIO.q(byteArrayOutputStream, fileHeader.Q());
                    rawIO.o(byteArrayOutputStream, fileHeader.N());
                }
                if (fileHeader.c() != null) {
                    AESExtraDataRecord c2 = fileHeader.c();
                    rawIO.s(byteArrayOutputStream, (int) c2.a().a());
                    rawIO.s(byteArrayOutputStream, c2.f());
                    rawIO.s(byteArrayOutputStream, c2.d().b());
                    byteArrayOutputStream.write(HeaderUtil.b(c2.g(), charset));
                    byteArrayOutputStream.write(new byte[]{(byte) c2.c().d()});
                    rawIO.s(byteArrayOutputStream, c2.e().a());
                }
                q(fileHeader, byteArrayOutputStream);
                if (bArr5.length > 0) {
                    byteArrayOutputStream.write(bArr5);
                    return;
                }
                return;
            } catch (Exception e2) {
                throw new ZipException(e2);
            }
        }
        throw new ZipException("input parameters is null, cannot write local file header");
    }

    private void q(FileHeader fileHeader, OutputStream outputStream) {
        if (fileHeader.h() != null && fileHeader.h().size() != 0) {
            for (ExtraDataRecord extraDataRecord : fileHeader.h()) {
                if (extraDataRecord.d() != HeaderSignature.AES_EXTRA_DATA_RECORD.a() && extraDataRecord.d() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.a()) {
                    this.f1426a.s(outputStream, (int) extraDataRecord.d());
                    this.f1426a.s(outputStream, extraDataRecord.e());
                    if (extraDataRecord.e() > 0 && extraDataRecord.c() != null) {
                        outputStream.write(extraDataRecord.c());
                    }
                }
            }
        }
    }

    private void r(Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO) {
        rawIO.o(byteArrayOutputStream, (int) HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.a());
        rawIO.o(byteArrayOutputStream, zip64EndOfCentralDirectoryLocator.c());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryLocator.d());
        rawIO.o(byteArrayOutputStream, zip64EndOfCentralDirectoryLocator.e());
    }

    private void s(Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord, ByteArrayOutputStream byteArrayOutputStream, RawIO rawIO) {
        rawIO.o(byteArrayOutputStream, (int) zip64EndOfCentralDirectoryRecord.a().a());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.g());
        rawIO.s(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.j());
        rawIO.s(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.k());
        rawIO.o(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.c());
        rawIO.o(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.d());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.i());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.h());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.f());
        rawIO.q(byteArrayOutputStream, zip64EndOfCentralDirectoryRecord.e());
    }

    private void t(ZipModel zipModel, OutputStream outputStream, byte[] bArr, Charset charset) {
        if (bArr != null) {
            if ((outputStream instanceof CountingOutputStream) && ((CountingOutputStream) outputStream).e(bArr.length)) {
                d(zipModel, outputStream, charset);
                return;
            } else {
                outputStream.write(bArr);
                return;
            }
        }
        throw new ZipException("invalid buff to write as zip headers");
    }

    public void d(ZipModel zipModel, OutputStream outputStream, Charset charset) {
        if (zipModel != null && outputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                i(zipModel, outputStream);
                long f2 = f(zipModel);
                l(zipModel, byteArrayOutputStream, this.f1426a, charset);
                int size = byteArrayOutputStream.size();
                if (zipModel.i() || f2 >= 4294967295L || zipModel.a().a().size() >= 65535) {
                    if (zipModel.f() == null) {
                        zipModel.p(new Zip64EndOfCentralDirectoryRecord());
                    }
                    if (zipModel.e() == null) {
                        zipModel.o(new Zip64EndOfCentralDirectoryLocator());
                    }
                    zipModel.e().g(size + f2);
                    if (g(outputStream)) {
                        int e2 = e(outputStream);
                        zipModel.e().f(e2);
                        zipModel.e().h(e2 + 1);
                    } else {
                        zipModel.e().f(0);
                        zipModel.e().h(1);
                    }
                    Zip64EndOfCentralDirectoryRecord a2 = a(zipModel, size, f2);
                    zipModel.p(a2);
                    s(a2, byteArrayOutputStream, this.f1426a);
                    r(zipModel.e(), byteArrayOutputStream, this.f1426a);
                }
                m(zipModel, size, f2, byteArrayOutputStream, this.f1426a, charset);
                t(zipModel, outputStream, byteArrayOutputStream.toByteArray(), charset);
                byteArrayOutputStream.close();
                return;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    h.a(th, th2);
                }
                throw th;
            }
        }
        throw new ZipException("input parameters is null, cannot finalize zip file");
    }

    public void k(FileHeader fileHeader, ZipModel zipModel, SplitOutputStream splitOutputStream) {
        SplitOutputStream splitOutputStream2;
        boolean z2;
        String str;
        String str2;
        if (fileHeader != null && zipModel != null) {
            if (fileHeader.N() != splitOutputStream.b()) {
                String parent = zipModel.g().getParent();
                String r2 = FileUtils.r(zipModel.g().getName());
                if (parent != null) {
                    str = parent + System.getProperty("file.separator");
                } else {
                    str = "";
                }
                z2 = true;
                if (fileHeader.N() < 9) {
                    str2 = str + r2 + ".z0" + (fileHeader.N() + 1);
                } else {
                    str2 = str + r2 + ".z" + (fileHeader.N() + 1);
                }
                splitOutputStream2 = new SplitOutputStream(new File(str2));
            } else {
                splitOutputStream2 = splitOutputStream;
                z2 = false;
            }
            long c2 = splitOutputStream2.c();
            splitOutputStream2.p(fileHeader.Q() + 14);
            this.f1426a.r(this.f1427b, 0, fileHeader.f());
            splitOutputStream2.write(this.f1427b, 0, 4);
            j(splitOutputStream2, fileHeader);
            if (z2) {
                splitOutputStream2.close();
                return;
            } else {
                splitOutputStream.p(c2);
                return;
            }
        }
        throw new ZipException("invalid input parameters, cannot update local file header");
    }

    public void n(LocalFileHeader localFileHeader, OutputStream outputStream) {
        if (localFileHeader != null && outputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                this.f1426a.o(byteArrayOutputStream, (int) HeaderSignature.EXTRA_DATA_RECORD.a());
                this.f1426a.r(this.f1427b, 0, localFileHeader.f());
                byteArrayOutputStream.write(this.f1427b, 0, 4);
                if (localFileHeader.M()) {
                    this.f1426a.q(byteArrayOutputStream, localFileHeader.d());
                    this.f1426a.q(byteArrayOutputStream, localFileHeader.n());
                } else {
                    this.f1426a.r(this.f1427b, 0, localFileHeader.d());
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                    this.f1426a.r(this.f1427b, 0, localFileHeader.n());
                    byteArrayOutputStream.write(this.f1427b, 0, 4);
                }
                outputStream.write(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();
                return;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    h.a(th, th2);
                }
                throw th;
            }
        }
        throw new ZipException("input parameters is null, cannot write extended local header");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006e A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0086 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b5 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00cf A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d9 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00de A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0109 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:11:0x006e, B:13:0x00a9, B:15:0x00b5, B:16:0x00bd, B:20:0x00c9, B:22:0x00cf, B:23:0x00d1, B:25:0x00d9, B:27:0x00de, B:28:0x0103, B:30:0x0109, B:31:0x0159, B:12:0x0086), top: B:40:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void p(net.lingala.zip4j.model.ZipModel r10, net.lingala.zip4j.model.LocalFileHeader r11, java.io.OutputStream r12, java.nio.charset.Charset r13) {
        /*
            Method dump skipped, instructions count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.headers.HeaderWriter.p(net.lingala.zip4j.model.ZipModel, net.lingala.zip4j.model.LocalFileHeader, java.io.OutputStream, java.nio.charset.Charset):void");
    }
}
