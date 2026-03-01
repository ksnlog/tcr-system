package net.lingala.zip4j.headers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class HeaderUtil {
    public static String a(byte[] bArr, boolean z2, Charset charset) {
        if (charset != null) {
            return new String(bArr, charset);
        }
        if (z2) {
            return new String(bArr, InternalZipConstants.f1718b);
        }
        try {
            return new String(bArr, "Cp437");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static byte[] b(String str, Charset charset) {
        if (charset == null) {
            return str.getBytes(InternalZipConstants.f1719c);
        }
        return str.getBytes(charset);
    }

    public static FileHeader c(ZipModel zipModel, String str) {
        FileHeader d2 = d(zipModel, str);
        if (d2 == null) {
            String replaceAll = str.replaceAll("\\\\", "/");
            FileHeader d3 = d(zipModel, replaceAll);
            if (d3 == null) {
                return d(zipModel, replaceAll.replaceAll("/", "\\\\"));
            }
            return d3;
        }
        return d2;
    }

    private static FileHeader d(ZipModel zipModel, String str) {
        if (zipModel != null) {
            if (Zip4jUtil.j(str)) {
                if (zipModel.a() != null) {
                    if (zipModel.a().a() != null) {
                        if (zipModel.a().a().size() == 0) {
                            return null;
                        }
                        for (FileHeader fileHeader : zipModel.a().a()) {
                            String j2 = fileHeader.j();
                            if (Zip4jUtil.j(j2) && str.equals(j2)) {
                                return fileHeader;
                            }
                        }
                        return null;
                    }
                    throw new ZipException("file Headers are null, cannot determine file header with exact match for fileName: " + str);
                }
                throw new ZipException("central directory is null, cannot determine file header with exact match for fileName: " + str);
            }
            throw new ZipException("file name is null, cannot determine file header with exact match for fileName: " + str);
        }
        throw new ZipException("zip model is null, cannot determine file header with exact match for fileName: " + str);
    }

    public static long e(ZipModel zipModel) {
        if (zipModel.i()) {
            return zipModel.f().e();
        }
        return zipModel.b().g();
    }

    public static long f(List<FileHeader> list) {
        long n2;
        long j2 = 0;
        for (FileHeader fileHeader : list) {
            if (fileHeader.p() != null && fileHeader.p().f() > 0) {
                n2 = fileHeader.p().f();
            } else {
                n2 = fileHeader.n();
            }
            j2 += n2;
        }
        return j2;
    }
}
