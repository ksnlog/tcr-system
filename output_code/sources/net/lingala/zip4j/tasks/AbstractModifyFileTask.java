package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.FileUtils;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class AbstractModifyFileTask<T> extends AsyncZipTask<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractModifyFileTask(AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
    }

    private int n(List<FileHeader> list, FileHeader fileHeader) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).equals(fileHeader)) {
                return i2;
            }
        }
        throw new ZipException("Could not find file header in list of central directory file headers");
    }

    private void q(File file, File file2) {
        if (file.delete()) {
            if (file2.renameTo(file)) {
                return;
            }
            throw new ZipException("cannot rename modified zip file");
        }
        throw new ZipException("cannot delete old zip file");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(boolean z2, File file, File file2) {
        if (z2) {
            q(file, file2);
        } else if (file2.delete()) {
        } else {
            throw new ZipException("Could not delete temporary file");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<FileHeader> l(List<FileHeader> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new Comparator<FileHeader>() { // from class: net.lingala.zip4j.tasks.AbstractModifyFileTask.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(FileHeader fileHeader, FileHeader fileHeader2) {
                if (fileHeader.j().equals(fileHeader2.j())) {
                    return 0;
                }
                if (fileHeader.Q() < fileHeader2.Q()) {
                    return -1;
                }
                return 1;
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long m(RandomAccessFile randomAccessFile, OutputStream outputStream, long j2, long j3, ProgressMonitor progressMonitor, int i2) {
        FileUtils.g(randomAccessFile, outputStream, j2, j2 + j3, progressMonitor, i2);
        return j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long o(List<FileHeader> list, FileHeader fileHeader, ZipModel zipModel) {
        int n2 = n(list, fileHeader);
        if (n2 == list.size() - 1) {
            return HeaderUtil.e(zipModel);
        }
        return list.get(n2 + 1).Q();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File p(String str) {
        SecureRandom secureRandom = new SecureRandom();
        File file = new File(str + secureRandom.nextInt(10000));
        while (file.exists()) {
            file = new File(str + secureRandom.nextInt(10000));
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(List<FileHeader> list, ZipModel zipModel, FileHeader fileHeader, long j2) {
        int n2 = n(list, fileHeader);
        if (n2 != -1) {
            while (true) {
                n2++;
                if (n2 < list.size()) {
                    FileHeader fileHeader2 = list.get(n2);
                    fileHeader2.X(fileHeader2.Q() + j2);
                    if (zipModel.i() && fileHeader2.p() != null && fileHeader2.p().e() != -1) {
                        fileHeader2.p().i(fileHeader2.p().e() + j2);
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new ZipException("Could not locate modified file header in zipModel");
        }
    }
}
