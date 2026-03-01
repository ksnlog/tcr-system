package net.lingala.zip4j.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Calendar;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AbstractFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Zip4jUtil {
    public static byte[] a(char[] cArr, boolean z2) {
        if (z2) {
            return c(cArr);
        }
        return b(cArr);
    }

    private static byte[] b(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            bArr[i2] = (byte) cArr[i2];
        }
        return bArr;
    }

    private static byte[] c(char[] cArr) {
        try {
            ByteBuffer encode = InternalZipConstants.f1718b.encode(CharBuffer.wrap(cArr));
            byte[] bArr = new byte[encode.limit()];
            encode.get(bArr);
            return bArr;
        } catch (Exception unused) {
            return b(cArr);
        }
    }

    public static boolean d(File file) {
        if (file != null) {
            if (file.exists()) {
                if (!file.isDirectory()) {
                    throw new ZipException("output directory is not valid");
                }
                return true;
            } else if (file.mkdirs()) {
                return true;
            } else {
                throw new ZipException("Cannot create output directories");
            }
        }
        throw new ZipException("output path is null");
    }

    private static long e(long j2) {
        int i2 = (int) ((j2 << 1) & 62);
        int i3 = (int) ((j2 >> 5) & 63);
        int i4 = (int) ((j2 >> 11) & 31);
        int i5 = (int) ((j2 >> 16) & 31);
        int i6 = (int) (((j2 >> 25) & 127) + 1980);
        Calendar calendar = Calendar.getInstance();
        calendar.set(i6, (int) (((j2 >> 21) & 15) - 1), i5, i4, i3, i2);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static long f(long j2) {
        return e(j2) + (j2 >> 32);
    }

    private static long g(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        int i2 = calendar.get(1);
        if (i2 < 1980) {
            return 2162688L;
        }
        return (calendar.get(13) >> 1) | ((i2 - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5);
    }

    public static long h(long j2) {
        if (j2 < 0) {
            return 2162688L;
        }
        long g2 = g(j2);
        if (g2 == 2162688) {
            return 2162688L;
        }
        return g2 + ((j2 % 2000) << 32);
    }

    public static CompressionMethod i(AbstractFileHeader abstractFileHeader) {
        if (abstractFileHeader.e() != CompressionMethod.AES_INTERNAL_ONLY) {
            return abstractFileHeader.e();
        }
        if (abstractFileHeader.c() != null) {
            return abstractFileHeader.c().e();
        }
        throw new ZipException("AesExtraDataRecord not present in local header for aes encrypted data");
    }

    public static boolean j(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean k(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static int l(InputStream inputStream, byte[] bArr) {
        int read = inputStream.read(bArr);
        if (read != -1) {
            if (read != bArr.length && (read = n(inputStream, bArr, read)) != bArr.length) {
                throw new IOException("Cannot read fully into byte buffer");
            }
            return read;
        }
        throw new IOException("Unexpected EOF reached when trying to read stream");
    }

    public static int m(InputStream inputStream, byte[] bArr, int i2, int i3) {
        if (i2 >= 0) {
            if (i3 >= 0) {
                int i4 = 0;
                if (i3 == 0) {
                    return 0;
                }
                if (i2 + i3 <= bArr.length) {
                    while (i4 != i3) {
                        int read = inputStream.read(bArr, i2 + i4, i3 - i4);
                        if (read == -1) {
                            if (i4 == 0) {
                                return -1;
                            }
                            return i4;
                        }
                        i4 += read;
                    }
                    return i4;
                }
                throw new IllegalArgumentException("Length greater than buffer size");
            }
            throw new IllegalArgumentException("Negative length");
        }
        throw new IllegalArgumentException("Negative offset");
    }

    private static int n(InputStream inputStream, byte[] bArr, int i2) {
        if (i2 >= 0) {
            int i3 = 0;
            if (i2 == 0) {
                return 0;
            }
            int length = bArr.length - i2;
            for (int i4 = 1; i2 < bArr.length && i3 != -1 && i4 < 15; i4++) {
                i3 = inputStream.read(bArr, i2, length);
                if (i3 > 0) {
                    i2 += i3;
                    length -= i3;
                }
            }
            return i2;
        }
        throw new IOException("Invalid readLength");
    }
}
