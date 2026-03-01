package net.lingala.zip4j.io.inputstream;

import java.io.InputStream;
import net.lingala.zip4j.model.FileHeader;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class SplitFileInputStream extends InputStream {
    public abstract void b(FileHeader fileHeader);
}
