package net.lingala.zip4j.util;

import net.lingala.zip4j.headers.VersionMadeBy;
import net.lingala.zip4j.headers.VersionNeededToExtract;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipVersionUtils {
    public static int a(ZipParameters zipParameters, RawIO rawIO) {
        byte[] bArr = {VersionMadeBy.SPECIFICATION_VERSION.a(), VersionMadeBy.UNIX.a()};
        if (FileUtils.w() && !zipParameters.t()) {
            bArr[1] = VersionMadeBy.WINDOWS.a();
        }
        return rawIO.m(bArr, 0);
    }

    public static VersionNeededToExtract b(ZipParameters zipParameters) {
        VersionNeededToExtract versionNeededToExtract = VersionNeededToExtract.DEFAULT;
        if (zipParameters.d() == CompressionMethod.DEFLATE) {
            versionNeededToExtract = VersionNeededToExtract.DEFLATE_COMPRESSED;
        }
        if (zipParameters.h() > 4294967295L) {
            versionNeededToExtract = VersionNeededToExtract.ZIP_64_FORMAT;
        }
        if (zipParameters.o() && zipParameters.f().equals(EncryptionMethod.AES)) {
            return VersionNeededToExtract.AES_ENCRYPTED;
        }
        return versionNeededToExtract;
    }
}
