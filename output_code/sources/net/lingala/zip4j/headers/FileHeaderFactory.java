package net.lingala.zip4j.headers;

import java.nio.charset.Charset;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;
import net.lingala.zip4j.util.ZipVersionUtils;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FileHeaderFactory {
    private int a(String str, Charset charset) {
        return HeaderUtil.b(str, charset).length;
    }

    private byte[] b(boolean z2, ZipParameters zipParameters, Charset charset) {
        byte[] bArr = new byte[2];
        bArr[0] = e(z2, zipParameters);
        if (charset == null || InternalZipConstants.f1718b.equals(charset)) {
            bArr[1] = BitUtils.b(bArr[1], 3);
        }
        return bArr;
    }

    private AESExtraDataRecord c(ZipParameters zipParameters) {
        AESExtraDataRecord aESExtraDataRecord = new AESExtraDataRecord();
        if (zipParameters.b() != null) {
            aESExtraDataRecord.i(zipParameters.b());
        }
        AesKeyStrength a2 = zipParameters.a();
        AesKeyStrength aesKeyStrength = AesKeyStrength.KEY_STRENGTH_128;
        if (a2 == aesKeyStrength) {
            aESExtraDataRecord.h(aesKeyStrength);
        } else {
            AesKeyStrength a3 = zipParameters.a();
            AesKeyStrength aesKeyStrength2 = AesKeyStrength.KEY_STRENGTH_192;
            if (a3 == aesKeyStrength2) {
                aESExtraDataRecord.h(aesKeyStrength2);
            } else {
                AesKeyStrength a4 = zipParameters.a();
                AesKeyStrength aesKeyStrength3 = AesKeyStrength.KEY_STRENGTH_256;
                if (a4 == aesKeyStrength3) {
                    aESExtraDataRecord.h(aesKeyStrength3);
                } else {
                    throw new ZipException("invalid AES key strength");
                }
            }
        }
        aESExtraDataRecord.j(zipParameters.d());
        return aESExtraDataRecord;
    }

    private byte e(boolean z2, ZipParameters zipParameters) {
        byte b2 = 0;
        if (z2) {
            b2 = BitUtils.b((byte) 0, 0);
        }
        if (CompressionMethod.DEFLATE.equals(zipParameters.d())) {
            if (CompressionLevel.NORMAL.equals(zipParameters.c())) {
                b2 = BitUtils.c(BitUtils.c(b2, 1), 2);
            } else if (CompressionLevel.MAXIMUM.equals(zipParameters.c())) {
                b2 = BitUtils.c(BitUtils.b(b2, 1), 2);
            } else if (CompressionLevel.FAST.equals(zipParameters.c())) {
                b2 = BitUtils.b(BitUtils.c(b2, 1), 2);
            } else if (CompressionLevel.FASTEST.equals(zipParameters.c()) || CompressionLevel.ULTRA.equals(zipParameters.c())) {
                b2 = BitUtils.b(BitUtils.b(b2, 1), 2);
            }
        }
        if (zipParameters.u()) {
            return BitUtils.b(b2, 3);
        }
        return b2;
    }

    private String g(String str) {
        if (Zip4jUtil.j(str)) {
            return str;
        }
        throw new ZipException("fileNameInZip is null or empty");
    }

    public FileHeader d(ZipParameters zipParameters, boolean z2, int i2, Charset charset, RawIO rawIO) {
        FileHeader fileHeader = new FileHeader();
        fileHeader.b(HeaderSignature.CENTRAL_DIRECTORY);
        fileHeader.Y(ZipVersionUtils.a(zipParameters, rawIO));
        fileHeader.K(ZipVersionUtils.b(zipParameters).a());
        if (zipParameters.o() && zipParameters.f() == EncryptionMethod.AES) {
            fileHeader.w(CompressionMethod.AES_INTERNAL_ONLY);
            fileHeader.u(c(zipParameters));
            fileHeader.D(fileHeader.i() + 11);
        } else {
            fileHeader.w(zipParameters.d());
        }
        if (zipParameters.o()) {
            if (zipParameters.f() != null && zipParameters.f() != EncryptionMethod.NONE) {
                fileHeader.A(true);
                fileHeader.B(zipParameters.f());
            } else {
                throw new ZipException("Encryption method has to be set when encryptFiles flag is set in zip parameters");
            }
        }
        String g2 = g(zipParameters.k());
        fileHeader.E(g2);
        fileHeader.F(a(g2, charset));
        if (!z2) {
            i2 = 0;
        }
        fileHeader.S(i2);
        fileHeader.I(Zip4jUtil.h(zipParameters.l()));
        boolean x2 = FileUtils.x(g2);
        fileHeader.z(x2);
        fileHeader.T(FileUtils.i(x2));
        if (zipParameters.u() && zipParameters.h() == -1) {
            fileHeader.J(0L);
        } else {
            fileHeader.J(zipParameters.h());
        }
        if (zipParameters.o() && zipParameters.f() == EncryptionMethod.ZIP_STANDARD) {
            fileHeader.x(zipParameters.g());
        }
        fileHeader.H(b(fileHeader.s(), zipParameters, charset));
        fileHeader.y(zipParameters.u());
        fileHeader.U(zipParameters.j());
        return fileHeader;
    }

    public LocalFileHeader f(FileHeader fileHeader) {
        LocalFileHeader localFileHeader = new LocalFileHeader();
        localFileHeader.b(HeaderSignature.LOCAL_FILE_HEADER);
        localFileHeader.K(fileHeader.o());
        localFileHeader.w(fileHeader.e());
        localFileHeader.I(fileHeader.m());
        localFileHeader.J(fileHeader.n());
        localFileHeader.F(fileHeader.k());
        localFileHeader.E(fileHeader.j());
        localFileHeader.A(fileHeader.s());
        localFileHeader.B(fileHeader.g());
        localFileHeader.u(fileHeader.c());
        localFileHeader.x(fileHeader.f());
        localFileHeader.v(fileHeader.d());
        localFileHeader.H((byte[]) fileHeader.l().clone());
        localFileHeader.y(fileHeader.q());
        localFileHeader.D(fileHeader.i());
        return localFileHeader;
    }
}
