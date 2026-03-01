package net.lingala.zip4j.tasks;

import androidx.core.graphics.h;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.UnzipUtil;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractExtractFileTask<T> extends AsyncZipTask<T> {

    /* renamed from: d  reason: collision with root package name */
    private final ZipModel f1694d;

    /* renamed from: e  reason: collision with root package name */
    private final UnzipParameters f1695e;

    public AbstractExtractFileTask(ZipModel zipModel, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.f1694d = zipModel;
        this.f1695e = unzipParameters;
    }

    private void k(File file, String str, FileHeader fileHeader) {
        String canonicalPath = file.getCanonicalPath();
        if (file.isDirectory()) {
            String str2 = InternalZipConstants.f1717a;
            if (!canonicalPath.endsWith(str2)) {
                canonicalPath = canonicalPath + str2;
            }
        }
        String canonicalPath2 = new File(str).getCanonicalPath();
        String str3 = InternalZipConstants.f1717a;
        if (!canonicalPath2.endsWith(str3)) {
            canonicalPath2 = canonicalPath2 + str3;
        }
        if (canonicalPath.startsWith(canonicalPath2)) {
            return;
        }
        throw new ZipException("illegal file name that breaks out of the target directory: " + fileHeader.j());
    }

    private void l(File file) {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new ZipException("Unable to create parent directories: " + file.getParentFile());
        }
    }

    private void m(ZipInputStream zipInputStream, FileHeader fileHeader, File file, ProgressMonitor progressMonitor) {
        Path path;
        Path path2;
        String str = new String(s(zipInputStream, fileHeader, progressMonitor));
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new ZipException("Could not create parent directories");
        }
        try {
            path = Paths.get(str, new String[0]);
            if (file.exists() && !file.delete()) {
                throw new ZipException("Could not delete existing symlink " + file);
            }
            path2 = file.toPath();
            Files.createSymbolicLink(path2, path, new FileAttribute[0]);
        } catch (NoSuchMethodError unused) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    h.a(th, th2);
                }
                throw th;
            }
        }
    }

    private File n(FileHeader fileHeader, String str, String str2) {
        String j2 = fileHeader.j();
        if (!Zip4jUtil.j(str2)) {
            str2 = j2;
        }
        return new File(str, p(str2));
    }

    private String p(String str) {
        return str.replaceAll(":\\\\", "_").replaceAll("[/\\\\]", Matcher.quoteReplacement(InternalZipConstants.f1717a));
    }

    private boolean r(FileHeader fileHeader) {
        byte[] O = fileHeader.O();
        if (O != null && O.length >= 4) {
            return BitUtils.a(O[3], 5);
        }
        return false;
    }

    private byte[] s(ZipInputStream zipInputStream, FileHeader fileHeader, ProgressMonitor progressMonitor) {
        int n2 = (int) fileHeader.n();
        byte[] bArr = new byte[n2];
        if (zipInputStream.read(bArr) == n2) {
            progressMonitor.l(n2);
            return bArr;
        }
        throw new ZipException("Could not read complete entry");
    }

    private void t(ZipInputStream zipInputStream, File file, ProgressMonitor progressMonitor, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = zipInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                    progressMonitor.l(read);
                    j();
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e2) {
            if (file.exists()) {
                file.delete();
            }
            throw e2;
        }
    }

    private void u(ZipInputStream zipInputStream, FileHeader fileHeader) {
        if (!BitUtils.a(fileHeader.l()[0], 6)) {
            LocalFileHeader o2 = zipInputStream.o(fileHeader, false);
            if (o2 != null) {
                if (fileHeader.j().equals(o2.j())) {
                    return;
                }
                throw new ZipException("File header and local file header mismatch");
            }
            throw new ZipException("Could not read corresponding local file header for file header: " + fileHeader.j());
        }
        throw new ZipException("Entry with name " + fileHeader.j() + " is encrypted with Strong Encryption. Zip4j does not support Strong Encryption, as this is patented.");
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task g() {
        return ProgressMonitor.Task.EXTRACT_ENTRY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void o(ZipInputStream zipInputStream, FileHeader fileHeader, String str, String str2, ProgressMonitor progressMonitor, byte[] bArr) {
        boolean r2 = r(fileHeader);
        if (r2 && !this.f1695e.a()) {
            return;
        }
        String str3 = InternalZipConstants.f1717a;
        if (!str.endsWith(str3)) {
            str = str + str3;
        }
        File n2 = n(fileHeader, str, str2);
        progressMonitor.h(n2.getAbsolutePath());
        k(n2, str, fileHeader);
        u(zipInputStream, fileHeader);
        if (fileHeader.r()) {
            if (!n2.exists() && !n2.mkdirs()) {
                throw new ZipException("Could not create directory: " + n2);
            }
        } else if (r2) {
            m(zipInputStream, fileHeader, n2, progressMonitor);
        } else {
            l(n2);
            t(zipInputStream, n2, progressMonitor, bArr);
        }
        if (!r2) {
            UnzipUtil.a(fileHeader, n2);
        }
    }

    public ZipModel q() {
        return this.f1694d;
    }
}
