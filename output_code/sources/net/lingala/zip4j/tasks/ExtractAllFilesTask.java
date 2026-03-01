package net.lingala.zip4j.tasks;

import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.io.inputstream.SplitFileInputStream;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.UnzipUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ExtractAllFilesTask extends AbstractExtractFileTask<ExtractAllFilesTaskParameters> {

    /* renamed from: f  reason: collision with root package name */
    private final char[] f1708f;

    /* renamed from: g  reason: collision with root package name */
    private SplitFileInputStream f1709g;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class ExtractAllFilesTaskParameters extends AbstractZipTaskParameters {

        /* renamed from: b  reason: collision with root package name */
        private final String f1710b;

        public ExtractAllFilesTaskParameters(String str, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.f1710b = str;
        }
    }

    public ExtractAllFilesTask(ZipModel zipModel, char[] cArr, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, unzipParameters, asyncTaskParameters);
        this.f1708f = cArr;
    }

    private FileHeader x(ZipModel zipModel) {
        if (zipModel.a() != null && zipModel.a().a() != null && zipModel.a().a().size() != 0) {
            return zipModel.a().a().get(0);
        }
        return null;
    }

    private ZipInputStream y(Zip4jConfig zip4jConfig) {
        this.f1709g = UnzipUtil.b(q());
        FileHeader x2 = x(q());
        if (x2 != null) {
            this.f1709g.b(x2);
        }
        return new ZipInputStream(this.f1709g, this.f1708f, zip4jConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: v */
    public long d(ExtractAllFilesTaskParameters extractAllFilesTaskParameters) {
        return HeaderUtil.f(q().a().a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: w */
    public void f(ExtractAllFilesTaskParameters extractAllFilesTaskParameters, ProgressMonitor progressMonitor) {
        try {
            ZipInputStream y2 = y(extractAllFilesTaskParameters.f1697a);
            for (FileHeader fileHeader : q().a().a()) {
                if (fileHeader.j().startsWith("__MACOSX")) {
                    progressMonitor.l(fileHeader.n());
                } else {
                    this.f1709g.b(fileHeader);
                    o(y2, fileHeader, extractAllFilesTaskParameters.f1710b, null, progressMonitor, new byte[extractAllFilesTaskParameters.f1697a.a()]);
                    j();
                }
            }
            if (y2 != null) {
                y2.close();
            }
        } finally {
            SplitFileInputStream splitFileInputStream = this.f1709g;
            if (splitFileInputStream != null) {
                splitFileInputStream.close();
            }
        }
    }
}
