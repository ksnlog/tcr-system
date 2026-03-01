package net.lingala.zip4j.tasks;

import androidx.core.graphics.h;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.io.outputstream.SplitOutputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.tasks.RemoveFilesFromZipTask;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.CrcUtil;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractAddFileToZipTask<T> extends AsyncZipTask<T> {

    /* renamed from: d  reason: collision with root package name */
    private final ZipModel f1691d;

    /* renamed from: e  reason: collision with root package name */
    private final char[] f1692e;

    /* renamed from: f  reason: collision with root package name */
    private final HeaderWriter f1693f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractAddFileToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.f1691d = zipModel;
        this.f1692e = cArr;
        this.f1693f = headerWriter;
    }

    private void k(File file, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream, ProgressMonitor progressMonitor, byte[] bArr) {
        zipOutputStream.r(zipParameters);
        if (file.exists() && !file.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                    progressMonitor.l(read);
                    j();
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        h.a(th, th2);
                    }
                    throw th;
                }
            }
            fileInputStream.close();
        }
        q(zipOutputStream, splitOutputStream, file, false);
    }

    private boolean m(ZipParameters zipParameters) {
        if (!ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(zipParameters.n()) && !ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.n())) {
            return false;
        }
        return true;
    }

    private void n(File file, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream) {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.C(v(zipParameters.k(), file.getName()));
        zipParameters2.y(false);
        zipParameters2.w(CompressionMethod.STORE);
        zipOutputStream.r(zipParameters2);
        zipOutputStream.write(FileUtils.y(file).getBytes());
        q(zipOutputStream, splitOutputStream, file, true);
    }

    private ZipParameters p(ZipParameters zipParameters, File file, ProgressMonitor progressMonitor) {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        if (file.isDirectory()) {
            zipParameters2.B(0L);
        } else {
            zipParameters2.B(file.length());
        }
        if (zipParameters.l() <= 0) {
            zipParameters2.E(file.lastModified());
        }
        zipParameters2.F(false);
        if (!Zip4jUtil.j(zipParameters.k())) {
            zipParameters2.C(FileUtils.p(file, zipParameters));
        }
        if (file.isDirectory()) {
            zipParameters2.w(CompressionMethod.STORE);
            zipParameters2.z(EncryptionMethod.NONE);
            zipParameters2.y(false);
        } else {
            if (zipParameters2.o() && zipParameters2.f() == EncryptionMethod.ZIP_STANDARD) {
                progressMonitor.g(ProgressMonitor.Task.CALCULATE_CRC);
                zipParameters2.A(CrcUtil.a(file, progressMonitor));
                progressMonitor.g(ProgressMonitor.Task.ADD_ENTRY);
            }
            if (file.length() == 0) {
                zipParameters2.w(CompressionMethod.STORE);
            }
        }
        return zipParameters2;
    }

    private void q(ZipOutputStream zipOutputStream, SplitOutputStream splitOutputStream, File file, boolean z2) {
        FileHeader c2 = zipOutputStream.c();
        byte[] j2 = FileUtils.j(file);
        if (!z2) {
            j2[3] = BitUtils.c(j2[3], 5);
        }
        c2.T(j2);
        w(c2, splitOutputStream);
    }

    private List<File> u(List<File> list, ZipParameters zipParameters, ProgressMonitor progressMonitor, Zip4jConfig zip4jConfig) {
        ArrayList arrayList = new ArrayList(list);
        if (!this.f1691d.g().exists()) {
            return arrayList;
        }
        for (File file : list) {
            if (!Zip4jUtil.j(file.getName())) {
                arrayList.remove(file);
            }
            FileHeader c2 = HeaderUtil.c(this.f1691d, FileUtils.p(file, zipParameters));
            if (c2 != null) {
                if (zipParameters.q()) {
                    progressMonitor.g(ProgressMonitor.Task.REMOVE_ENTRY);
                    t(c2, progressMonitor, zip4jConfig);
                    j();
                    progressMonitor.g(ProgressMonitor.Task.ADD_ENTRY);
                } else {
                    arrayList.remove(file);
                }
            }
        }
        return arrayList;
    }

    private String v(String str, String str2) {
        if (str.contains("/")) {
            return str.substring(0, str.lastIndexOf("/") + 1) + str2;
        }
        return str2;
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task g() {
        return ProgressMonitor.Task.ADD_ENTRY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(List<File> list, ProgressMonitor progressMonitor, ZipParameters zipParameters, Zip4jConfig zip4jConfig) {
        FileUtils.e(list, zipParameters.n());
        byte[] bArr = new byte[zip4jConfig.a()];
        List<File> u2 = u(list, zipParameters, progressMonitor, zip4jConfig);
        SplitOutputStream splitOutputStream = new SplitOutputStream(this.f1691d.g(), this.f1691d.d());
        try {
            ZipOutputStream s2 = s(splitOutputStream, zip4jConfig);
            for (File file : u2) {
                j();
                ZipParameters p2 = p(zipParameters, file, progressMonitor);
                progressMonitor.h(file.getAbsolutePath());
                if (FileUtils.u(file) && m(p2)) {
                    n(file, s2, p2, splitOutputStream);
                    if (ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(p2.n())) {
                    }
                }
                k(file, s2, p2, splitOutputStream, progressMonitor, bArr);
            }
            if (s2 != null) {
                s2.close();
            }
            splitOutputStream.close();
        } catch (Throwable th) {
            try {
                splitOutputStream.close();
            } catch (Throwable th2) {
                h.a(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long o(List<File> list, ZipParameters zipParameters) {
        long length;
        long j2 = 0;
        for (File file : list) {
            if (file.exists()) {
                if (zipParameters.o() && zipParameters.f() == EncryptionMethod.ZIP_STANDARD) {
                    length = file.length() * 2;
                } else {
                    length = file.length();
                }
                j2 += length;
                FileHeader c2 = HeaderUtil.c(r(), FileUtils.p(file, zipParameters));
                if (c2 != null) {
                    j2 += r().g().length() - c2.d();
                }
            }
        }
        return j2;
    }

    protected ZipModel r() {
        return this.f1691d;
    }

    ZipOutputStream s(SplitOutputStream splitOutputStream, Zip4jConfig zip4jConfig) {
        if (this.f1691d.g().exists()) {
            splitOutputStream.p(HeaderUtil.e(this.f1691d));
        }
        return new ZipOutputStream(splitOutputStream, this.f1692e, zip4jConfig, this.f1691d);
    }

    void t(FileHeader fileHeader, ProgressMonitor progressMonitor, Zip4jConfig zip4jConfig) {
        new RemoveFilesFromZipTask(this.f1691d, this.f1693f, new AsyncZipTask.AsyncTaskParameters(null, false, progressMonitor)).e(new RemoveFilesFromZipTask.RemoveFilesFromZipTaskParameters(Collections.singletonList(fileHeader.j()), zip4jConfig));
    }

    void w(FileHeader fileHeader, SplitOutputStream splitOutputStream) {
        this.f1693f.k(fileHeader, r(), splitOutputStream);
    }
}
