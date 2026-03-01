package net.lingala.zip4j.util;

import java.io.File;
import java.nio.file.Path;
import net.lingala.zip4j.io.inputstream.NumberedSplitFileInputStream;
import net.lingala.zip4j.io.inputstream.SplitFileInputStream;
import net.lingala.zip4j.io.inputstream.ZipStandardSplitFileInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class UnzipUtil {
    public static void a(FileHeader fileHeader, File file) {
        Path path;
        try {
            path = file.toPath();
            FileUtils.A(path, fileHeader.O());
            FileUtils.B(path, fileHeader.m());
        } catch (NoSuchMethodError unused) {
            FileUtils.C(file, fileHeader.m());
        }
    }

    public static SplitFileInputStream b(ZipModel zipModel) {
        if (zipModel.g().getName().endsWith(".zip.001")) {
            return new NumberedSplitFileInputStream(zipModel.g());
        }
        return new ZipStandardSplitFileInputStream(zipModel.g(), zipModel.h(), zipModel.b().d());
    }
}
