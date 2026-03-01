package net.lingala.zip4j.util;

import androidx.core.graphics.h;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CrcUtil {
    public static long a(File file, ProgressMonitor progressMonitor) {
        if (file != null && file.exists() && file.canRead()) {
            byte[] bArr = new byte[16384];
            CRC32 crc32 = new CRC32();
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        crc32.update(bArr, 0, read);
                        if (progressMonitor != null) {
                            progressMonitor.l(read);
                            if (progressMonitor.e()) {
                                progressMonitor.i(ProgressMonitor.Result.CANCELLED);
                                progressMonitor.j(ProgressMonitor.State.READY);
                                fileInputStream.close();
                                return 0L;
                            }
                        }
                    } else {
                        long value = crc32.getValue();
                        fileInputStream.close();
                        return value;
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        h.a(th, th2);
                    }
                    throw th;
                }
            }
        } else {
            throw new ZipException("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
        }
    }
}
