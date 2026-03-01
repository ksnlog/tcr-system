package net.lingala.zip4j.tasks;

import androidx.core.graphics.h;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.io.outputstream.SplitOutputStream;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class RemoveFilesFromZipTask extends AbstractModifyFileTask<RemoveFilesFromZipTaskParameters> {

    /* renamed from: d  reason: collision with root package name */
    private final ZipModel f1711d;

    /* renamed from: e  reason: collision with root package name */
    private final HeaderWriter f1712e;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class RemoveFilesFromZipTaskParameters extends AbstractZipTaskParameters {

        /* renamed from: b  reason: collision with root package name */
        private final List<String> f1713b;

        public RemoveFilesFromZipTaskParameters(List<String> list, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.f1713b = list;
        }
    }

    public RemoveFilesFromZipTask(ZipModel zipModel, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.f1711d = zipModel;
        this.f1712e = headerWriter;
    }

    private List<String> u(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (HeaderUtil.c(this.f1711d, str) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private long v(long j2) {
        if (j2 != Long.MIN_VALUE) {
            return -j2;
        }
        throw new ArithmeticException("long overflow");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean w(net.lingala.zip4j.model.FileHeader r4, java.util.List<java.lang.String> r5) {
        /*
            r3 = this;
            java.util.Iterator r5 = r5.iterator()
        L4:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L2f
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "/"
            boolean r1 = r0.endsWith(r1)
            r2 = 1
            if (r1 == 0) goto L24
            java.lang.String r1 = r4.j()
            boolean r1 = r1.startsWith(r0)
            if (r1 == 0) goto L24
            return r2
        L24:
            java.lang.String r1 = r4.j()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4
            return r2
        L2f:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.tasks.RemoveFilesFromZipTask.w(net.lingala.zip4j.model.FileHeader, java.util.List):boolean");
    }

    private void x(List<FileHeader> list, FileHeader fileHeader, long j2) {
        r(list, this.f1711d, fileHeader, v(j2));
        EndOfCentralDirectoryRecord b2 = this.f1711d.b();
        b2.n(b2.g() - j2);
        b2.p(b2.h() - 1);
        if (b2.i() > 0) {
            b2.q(b2.i() - 1);
        }
        if (this.f1711d.i()) {
            this.f1711d.f().o(this.f1711d.f().e() - j2);
            this.f1711d.f().s(this.f1711d.f().h() - 1);
            this.f1711d.e().g(this.f1711d.e().d() - j2);
        }
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task g() {
        return ProgressMonitor.Task.REMOVE_ENTRY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: s */
    public long d(RemoveFilesFromZipTaskParameters removeFilesFromZipTaskParameters) {
        return this.f1711d.g().length();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: t */
    public void f(RemoveFilesFromZipTaskParameters removeFilesFromZipTaskParameters, ProgressMonitor progressMonitor) {
        List<FileHeader> list;
        if (!this.f1711d.h()) {
            List<String> u2 = u(removeFilesFromZipTaskParameters.f1713b);
            if (u2.isEmpty()) {
                return;
            }
            File p2 = p(this.f1711d.g().getPath());
            try {
                SplitOutputStream splitOutputStream = new SplitOutputStream(p2);
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.f1711d.g(), RandomAccessFileMode.READ.a());
                try {
                    List<FileHeader> l2 = l(this.f1711d.a().a());
                    long j2 = 0;
                    for (FileHeader fileHeader : l2) {
                        long o2 = o(l2, fileHeader, this.f1711d) - splitOutputStream.c();
                        if (w(fileHeader, u2)) {
                            x(l2, fileHeader, o2);
                            if (this.f1711d.a().a().remove(fileHeader)) {
                                j2 += o2;
                                list = l2;
                            } else {
                                throw new ZipException("Could not remove entry from list of central directory headers");
                            }
                        } else {
                            list = l2;
                            j2 += super.m(randomAccessFile, splitOutputStream, j2, o2, progressMonitor, removeFilesFromZipTaskParameters.f1697a.a());
                        }
                        j();
                        l2 = list;
                    }
                    this.f1712e.d(this.f1711d, splitOutputStream, removeFilesFromZipTaskParameters.f1697a.b());
                    randomAccessFile.close();
                    splitOutputStream.close();
                    k(true, this.f1711d.g(), p2);
                    return;
                } catch (Throwable th) {
                    try {
                        randomAccessFile.close();
                    } catch (Throwable th2) {
                        h.a(th, th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                k(false, this.f1711d.g(), p2);
                throw th3;
            }
        }
        throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
    }
}
