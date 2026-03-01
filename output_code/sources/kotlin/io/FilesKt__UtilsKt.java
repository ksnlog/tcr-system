package kotlin.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    public static final File c(File file, File target, boolean z2, int i2) {
        Intrinsics.f(file, "<this>");
        Intrinsics.f(target, "target");
        if (file.exists()) {
            if (target.exists()) {
                if (z2) {
                    if (!target.delete()) {
                        throw new FileAlreadyExistsException(file, target, "Tried to overwrite the destination, but failed to delete it.");
                    }
                } else {
                    throw new FileAlreadyExistsException(file, target, "The destination file already exists.");
                }
            }
            if (file.isDirectory()) {
                if (!target.mkdirs()) {
                    throw new FileSystemException(file, target, "Failed to create target directory.");
                }
            } else {
                File parentFile = target.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(target);
                    ByteStreamsKt.a(fileInputStream, fileOutputStream, i2);
                    CloseableKt.a(fileOutputStream, null);
                    CloseableKt.a(fileInputStream, null);
                } finally {
                }
            }
            return target;
        }
        throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
    }

    public static /* synthetic */ File d(File file, File file2, boolean z2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 8192;
        }
        return c(file, file2, z2, i2);
    }

    public static boolean e(File file) {
        Intrinsics.f(file, "<this>");
        while (true) {
            boolean z2 = true;
            for (File file2 : FilesKt__FileTreeWalkKt.b(file)) {
                if (file2.delete() || !file2.exists()) {
                    if (z2) {
                        break;
                    }
                }
                z2 = false;
            }
            return z2;
        }
    }
}
