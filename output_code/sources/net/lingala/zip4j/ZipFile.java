package net.lingala.zip4j;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderReader;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.io.inputstream.NumberedSplitRandomAccessFile;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AddFolderToZipTask;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.tasks.ExtractAllFilesTask;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipFile implements Closeable {

    /* renamed from: d  reason: collision with root package name */
    private File f1348d;

    /* renamed from: e  reason: collision with root package name */
    private ZipModel f1349e;

    /* renamed from: f  reason: collision with root package name */
    private ProgressMonitor f1350f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1351g;

    /* renamed from: h  reason: collision with root package name */
    private char[] f1352h;

    /* renamed from: i  reason: collision with root package name */
    private HeaderWriter f1353i;

    /* renamed from: j  reason: collision with root package name */
    private Charset f1354j;

    /* renamed from: k  reason: collision with root package name */
    private ThreadFactory f1355k;

    /* renamed from: l  reason: collision with root package name */
    private ExecutorService f1356l;

    /* renamed from: m  reason: collision with root package name */
    private int f1357m;

    /* renamed from: n  reason: collision with root package name */
    private List<InputStream> f1358n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f1359o;

    public ZipFile(String str) {
        this(new File(str), null);
    }

    private void b(File file, ZipParameters zipParameters, boolean z2) {
        r();
        ZipModel zipModel = this.f1349e;
        if (zipModel != null) {
            if (z2 && zipModel.h()) {
                throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
            }
            new AddFolderToZipTask(this.f1349e, this.f1352h, this.f1353i, c()).e(new AddFolderToZipTask.AddFolderToZipTaskParameters(file, zipParameters, e()));
            return;
        }
        throw new ZipException("internal error: zip model is null");
    }

    private AsyncZipTask.AsyncTaskParameters c() {
        if (this.f1351g) {
            if (this.f1355k == null) {
                this.f1355k = Executors.defaultThreadFactory();
            }
            this.f1356l = Executors.newSingleThreadExecutor(this.f1355k);
        }
        return new AsyncZipTask.AsyncTaskParameters(this.f1356l, this.f1351g, this.f1350f);
    }

    private Zip4jConfig e() {
        return new Zip4jConfig(this.f1354j, this.f1357m, this.f1359o);
    }

    private void k() {
        ZipModel zipModel = new ZipModel();
        this.f1349e = zipModel;
        zipModel.r(this.f1348d);
    }

    private RandomAccessFile p() {
        if (FileUtils.t(this.f1348d)) {
            NumberedSplitRandomAccessFile numberedSplitRandomAccessFile = new NumberedSplitRandomAccessFile(this.f1348d, RandomAccessFileMode.READ.a(), FileUtils.h(this.f1348d));
            numberedSplitRandomAccessFile.c();
            return numberedSplitRandomAccessFile;
        }
        return new RandomAccessFile(this.f1348d, RandomAccessFileMode.READ.a());
    }

    private void r() {
        if (this.f1349e != null) {
            return;
        }
        if (!this.f1348d.exists()) {
            k();
        } else if (this.f1348d.canRead()) {
            try {
                RandomAccessFile p2 = p();
                ZipModel h2 = new HeaderReader().h(p2, e());
                this.f1349e = h2;
                h2.r(this.f1348d);
                if (p2 != null) {
                    p2.close();
                }
            } catch (ZipException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new ZipException(e3);
            }
        } else {
            throw new ZipException("no read access for the input zip file");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (InputStream inputStream : this.f1358n) {
            inputStream.close();
        }
        this.f1358n.clear();
    }

    public void m(File file, ZipParameters zipParameters, boolean z2, long j2) {
        if (file != null) {
            if (zipParameters != null) {
                if (!this.f1348d.exists()) {
                    k();
                    this.f1349e.m(z2);
                    if (z2) {
                        this.f1349e.n(j2);
                    }
                    b(file, zipParameters, false);
                    return;
                }
                throw new ZipException("zip file: " + this.f1348d + " already exists. To add files to existing zip file use addFolder method");
            }
            throw new ZipException("input parameters are null, cannot create zip file from folder");
        }
        throw new ZipException("folderToAdd is null, cannot create zip file from folder");
    }

    public void n(String str) {
        o(str, new UnzipParameters());
    }

    public void o(String str, UnzipParameters unzipParameters) {
        if (Zip4jUtil.j(str)) {
            if (Zip4jUtil.d(new File(str))) {
                if (this.f1349e == null) {
                    r();
                }
                ZipModel zipModel = this.f1349e;
                if (zipModel != null) {
                    new ExtractAllFilesTask(zipModel, this.f1352h, unzipParameters, c()).e(new ExtractAllFilesTask.ExtractAllFilesTaskParameters(str, e()));
                    return;
                }
                throw new ZipException("Internal error occurred when extracting zip file");
            }
            throw new ZipException("invalid output path");
        }
        throw new ZipException("output path is null or invalid");
    }

    public String toString() {
        return this.f1348d.toString();
    }

    public ZipFile(File file) {
        this(file, null);
    }

    public ZipFile(File file, char[] cArr) {
        this.f1353i = new HeaderWriter();
        this.f1354j = null;
        this.f1357m = 4096;
        this.f1358n = new ArrayList();
        this.f1359o = true;
        if (file != null) {
            this.f1348d = file;
            this.f1352h = cArr;
            this.f1351g = false;
            this.f1350f = new ProgressMonitor();
            return;
        }
        throw new IllegalArgumentException("input zip file parameter is null");
    }
}
