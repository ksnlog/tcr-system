package kotlin.io;

import java.io.File;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class FilesKt__FileTreeWalkKt extends FilesKt__FileReadWriteKt {
    public static final FileTreeWalk a(File file, FileWalkDirection direction) {
        Intrinsics.f(file, "<this>");
        Intrinsics.f(direction, "direction");
        return new FileTreeWalk(file, direction);
    }

    public static final FileTreeWalk b(File file) {
        Intrinsics.f(file, "<this>");
        return a(file, FileWalkDirection.BOTTOM_UP);
    }
}
