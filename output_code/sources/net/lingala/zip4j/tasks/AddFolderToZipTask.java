package net.lingala.zip4j.tasks;

import java.io.File;
import java.util.List;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.FileUtils;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AddFolderToZipTask extends AbstractAddFileToZipTask<AddFolderToZipTaskParameters> {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class AddFolderToZipTaskParameters extends AbstractZipTaskParameters {

        /* renamed from: b  reason: collision with root package name */
        private final File f1698b;

        /* renamed from: c  reason: collision with root package name */
        private final ZipParameters f1699c;

        public AddFolderToZipTaskParameters(File file, ZipParameters zipParameters, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.f1698b = file;
            this.f1699c = zipParameters;
        }
    }

    public AddFolderToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, cArr, headerWriter, asyncTaskParameters);
    }

    private void A(AddFolderToZipTaskParameters addFolderToZipTaskParameters) {
        String canonicalPath;
        File file = addFolderToZipTaskParameters.f1698b;
        if (addFolderToZipTaskParameters.f1699c.p()) {
            if (file.getCanonicalFile().getParentFile() == null) {
                canonicalPath = file.getCanonicalPath();
            } else {
                canonicalPath = file.getCanonicalFile().getParentFile().getCanonicalPath();
            }
        } else {
            canonicalPath = file.getCanonicalPath();
        }
        addFolderToZipTaskParameters.f1699c.x(canonicalPath);
    }

    private List<File> z(AddFolderToZipTaskParameters addFolderToZipTaskParameters) {
        List<File> m2 = FileUtils.m(addFolderToZipTaskParameters.f1698b, addFolderToZipTaskParameters.f1699c);
        if (addFolderToZipTaskParameters.f1699c.p()) {
            m2.add(addFolderToZipTaskParameters.f1698b);
        }
        return m2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: x */
    public long d(AddFolderToZipTaskParameters addFolderToZipTaskParameters) {
        List<File> z2 = z(addFolderToZipTaskParameters);
        if (addFolderToZipTaskParameters.f1699c.p()) {
            z2.add(addFolderToZipTaskParameters.f1698b);
        }
        return o(z2, addFolderToZipTaskParameters.f1699c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: y */
    public void f(AddFolderToZipTaskParameters addFolderToZipTaskParameters, ProgressMonitor progressMonitor) {
        List<File> z2 = z(addFolderToZipTaskParameters);
        A(addFolderToZipTaskParameters);
        l(z2, progressMonitor, addFolderToZipTaskParameters.f1699c, addFolderToZipTaskParameters.f1697a);
    }
}
