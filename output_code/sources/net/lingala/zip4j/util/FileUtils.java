package net.lingala.zip4j.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f1714a = {0, 0, -92, -127};

    /* renamed from: b  reason: collision with root package name */
    public static final byte[] f1715b = {0, 0, -19, 65};

    public static void A(Path path, byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            if (w()) {
                c(path, bArr);
            } else if (s() || v()) {
                b(path, bArr);
            }
        }
    }

    public static void B(Path path, long j2) {
        boolean exists;
        FileTime fromMillis;
        if (j2 > 0) {
            exists = Files.exists(path, new LinkOption[0]);
            if (exists) {
                try {
                    fromMillis = FileTime.fromMillis(Zip4jUtil.f(j2));
                    Files.setLastModifiedTime(path, fromMillis);
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void C(File file, long j2) {
        file.setLastModified(Zip4jUtil.f(j2));
    }

    private static void a(byte b2, int i2, Set<PosixFilePermission> set, PosixFilePermission posixFilePermission) {
        if (BitUtils.a(b2, i2)) {
            set.add(posixFilePermission);
        }
    }

    private static void b(Path path, byte[] bArr) {
        PosixFilePermission posixFilePermission;
        PosixFilePermission posixFilePermission2;
        PosixFilePermission posixFilePermission3;
        PosixFilePermission posixFilePermission4;
        PosixFilePermission posixFilePermission5;
        PosixFilePermission posixFilePermission6;
        PosixFilePermission posixFilePermission7;
        PosixFilePermission posixFilePermission8;
        PosixFilePermission posixFilePermission9;
        if (bArr[2] == 0 && bArr[3] == 0) {
            return;
        }
        try {
            HashSet hashSet = new HashSet();
            byte b2 = bArr[3];
            posixFilePermission = PosixFilePermission.OWNER_READ;
            a(b2, 0, hashSet, posixFilePermission);
            byte b3 = bArr[2];
            posixFilePermission2 = PosixFilePermission.OWNER_WRITE;
            a(b3, 7, hashSet, posixFilePermission2);
            byte b4 = bArr[2];
            posixFilePermission3 = PosixFilePermission.OWNER_EXECUTE;
            a(b4, 6, hashSet, posixFilePermission3);
            byte b5 = bArr[2];
            posixFilePermission4 = PosixFilePermission.GROUP_READ;
            a(b5, 5, hashSet, posixFilePermission4);
            byte b6 = bArr[2];
            posixFilePermission5 = PosixFilePermission.GROUP_WRITE;
            a(b6, 4, hashSet, posixFilePermission5);
            byte b7 = bArr[2];
            posixFilePermission6 = PosixFilePermission.GROUP_EXECUTE;
            a(b7, 3, hashSet, posixFilePermission6);
            byte b8 = bArr[2];
            posixFilePermission7 = PosixFilePermission.OTHERS_READ;
            a(b8, 2, hashSet, posixFilePermission7);
            byte b9 = bArr[2];
            posixFilePermission8 = PosixFilePermission.OTHERS_WRITE;
            a(b9, 1, hashSet, posixFilePermission8);
            byte b10 = bArr[2];
            posixFilePermission9 = PosixFilePermission.OTHERS_EXECUTE;
            a(b10, 0, hashSet, posixFilePermission9);
            Files.setPosixFilePermissions(path, hashSet);
        } catch (IOException unused) {
        }
    }

    private static void c(Path path, byte[] bArr) {
        LinkOption linkOption;
        FileAttributeView fileAttributeView;
        if (bArr[0] == 0) {
            return;
        }
        linkOption = LinkOption.NOFOLLOW_LINKS;
        fileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class, linkOption);
        DosFileAttributeView dosFileAttributeView = (DosFileAttributeView) fileAttributeView;
        if (dosFileAttributeView == null) {
            return;
        }
        try {
            dosFileAttributeView.setReadOnly(BitUtils.a(bArr[0], 0));
            dosFileAttributeView.setHidden(BitUtils.a(bArr[0], 1));
            dosFileAttributeView.setSystem(BitUtils.a(bArr[0], 2));
            dosFileAttributeView.setArchive(BitUtils.a(bArr[0], 5));
        } catch (IOException unused) {
        }
    }

    private static void d(File file) {
        if (file.exists()) {
            return;
        }
        throw new ZipException("File does not exist: " + file);
    }

    public static void e(List<File> list, ZipParameters.SymbolicLinkAction symbolicLinkAction) {
        for (File file : list) {
            if (u(file)) {
                if (symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE) || symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY)) {
                    f(file);
                }
            } else {
                d(file);
            }
        }
    }

    private static void f(File file) {
        if (file.exists()) {
            return;
        }
        throw new ZipException("Symlink target '" + y(file) + "' does not exist for link '" + file + "'");
    }

    public static void g(RandomAccessFile randomAccessFile, OutputStream outputStream, long j2, long j3, ProgressMonitor progressMonitor, int i2) {
        byte[] bArr;
        long j4 = 0;
        if (j2 >= 0 && j3 >= 0 && j2 <= j3) {
            if (j2 == j3) {
                return;
            }
            try {
                randomAccessFile.seek(j2);
                long j5 = j3 - j2;
                if (j5 < i2) {
                    bArr = new byte[(int) j5];
                } else {
                    bArr = new byte[i2];
                }
                while (true) {
                    int read = randomAccessFile.read(bArr);
                    if (read != -1) {
                        outputStream.write(bArr, 0, read);
                        long j6 = read;
                        progressMonitor.l(j6);
                        if (progressMonitor.e()) {
                            progressMonitor.i(ProgressMonitor.Result.CANCELLED);
                            return;
                        }
                        j4 += j6;
                        if (j4 != j5) {
                            if (bArr.length + j4 > j5) {
                                bArr = new byte[(int) (j5 - j4)];
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } catch (IOException e2) {
                throw new ZipException(e2);
            }
        } else {
            throw new ZipException("invalid offsets");
        }
    }

    public static File[] h(File file) {
        final String l2 = l(file.getName());
        File[] listFiles = file.getParentFile().listFiles(new FilenameFilter() { // from class: net.lingala.zip4j.util.FileUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.startsWith(l2 + ".");
            }
        });
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles);
        return listFiles;
    }

    public static byte[] i(boolean z2) {
        byte[] bArr = new byte[4];
        if (!v() && !s()) {
            if (w() && z2) {
                bArr[0] = BitUtils.b(bArr[0], 4);
            }
        } else if (z2) {
            System.arraycopy(f1715b, 0, bArr, 0, 4);
        } else {
            System.arraycopy(f1714a, 0, bArr, 0, 4);
        }
        return bArr;
    }

    public static byte[] j(File file) {
        Path path;
        boolean isSymbolicLink;
        Path path2;
        if (file != null) {
            try {
                path = file.toPath();
                isSymbolicLink = Files.isSymbolicLink(path);
                if (isSymbolicLink || file.exists()) {
                    path2 = file.toPath();
                    if (w()) {
                        return q(path2);
                    }
                    if (!s() && !v()) {
                        return new byte[4];
                    }
                    return o(path2);
                }
            } catch (NoSuchMethodError unused) {
                return new byte[4];
            }
        }
        return new byte[4];
    }

    public static String k(File file) {
        String name = file.getName();
        if (!name.contains(".")) {
            return "";
        }
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static String l(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public static List<File> m(File file, ZipParameters zipParameters) {
        if (file != null) {
            ArrayList arrayList = new ArrayList();
            File[] listFiles = file.listFiles();
            if (file.isDirectory() && file.canRead() && listFiles != null) {
                for (File file2 : listFiles) {
                    zipParameters.i();
                    if (!file2.isHidden() || zipParameters.r()) {
                        arrayList.add(file2);
                        ZipParameters.SymbolicLinkAction n2 = zipParameters.n();
                        boolean u2 = u(file2);
                        if ((u2 && !ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(n2)) || (!u2 && file2.isDirectory())) {
                            arrayList.addAll(m(file2, zipParameters));
                        }
                    }
                }
            }
            return arrayList;
        }
        throw new ZipException("input path is null, cannot read files in the directory");
    }

    private static String n(File file, String str) {
        Path path;
        LinkOption linkOption;
        Path realPath;
        Path fileName;
        String path2;
        if (Zip4jUtil.j(str)) {
            return str;
        }
        if (u(file)) {
            path = file.toPath();
            linkOption = LinkOption.NOFOLLOW_LINKS;
            realPath = path.toRealPath(linkOption);
            fileName = realPath.getFileName();
            path2 = fileName.toString();
            return path2;
        }
        return file.getName();
    }

    private static byte[] o(Path path) {
        LinkOption linkOption;
        FileAttributeView fileAttributeView;
        PosixFileAttributes readAttributes;
        Set permissions2;
        boolean isSymbolicLink;
        boolean isRegularFile;
        boolean isDirectory;
        PosixFilePermission posixFilePermission;
        PosixFilePermission posixFilePermission2;
        PosixFilePermission posixFilePermission3;
        PosixFilePermission posixFilePermission4;
        PosixFilePermission posixFilePermission5;
        PosixFilePermission posixFilePermission6;
        PosixFilePermission posixFilePermission7;
        PosixFilePermission posixFilePermission8;
        PosixFilePermission posixFilePermission9;
        byte[] bArr = new byte[4];
        try {
            linkOption = LinkOption.NOFOLLOW_LINKS;
            fileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class, linkOption);
            readAttributes = ((PosixFileAttributeView) fileAttributeView).readAttributes();
            permissions2 = readAttributes.permissions();
            isSymbolicLink = Files.isSymbolicLink(path);
            if (isSymbolicLink) {
                byte b2 = BitUtils.b(bArr[3], 7);
                bArr[3] = b2;
                bArr[3] = BitUtils.c(b2, 6);
            } else {
                isRegularFile = Files.isRegularFile(path, new LinkOption[0]);
                bArr[3] = z(isRegularFile, bArr[3], 7);
                isDirectory = Files.isDirectory(path, new LinkOption[0]);
                bArr[3] = z(isDirectory, bArr[3], 6);
            }
            bArr[3] = z(isSymbolicLink, bArr[3], 5);
            posixFilePermission = PosixFilePermission.OWNER_READ;
            bArr[3] = z(permissions2.contains(posixFilePermission), bArr[3], 0);
            posixFilePermission2 = PosixFilePermission.OWNER_WRITE;
            bArr[2] = z(permissions2.contains(posixFilePermission2), bArr[2], 7);
            posixFilePermission3 = PosixFilePermission.OWNER_EXECUTE;
            bArr[2] = z(permissions2.contains(posixFilePermission3), bArr[2], 6);
            posixFilePermission4 = PosixFilePermission.GROUP_READ;
            bArr[2] = z(permissions2.contains(posixFilePermission4), bArr[2], 5);
            posixFilePermission5 = PosixFilePermission.GROUP_WRITE;
            bArr[2] = z(permissions2.contains(posixFilePermission5), bArr[2], 4);
            posixFilePermission6 = PosixFilePermission.GROUP_EXECUTE;
            bArr[2] = z(permissions2.contains(posixFilePermission6), bArr[2], 3);
            posixFilePermission7 = PosixFilePermission.OTHERS_READ;
            bArr[2] = z(permissions2.contains(posixFilePermission7), bArr[2], 2);
            posixFilePermission8 = PosixFilePermission.OTHERS_WRITE;
            bArr[2] = z(permissions2.contains(posixFilePermission8), bArr[2], 1);
            posixFilePermission9 = PosixFilePermission.OTHERS_EXECUTE;
            bArr[2] = z(permissions2.contains(posixFilePermission9), bArr[2], 0);
        } catch (IOException unused) {
        }
        return bArr;
    }

    public static String p(File file, ZipParameters zipParameters) {
        String n2;
        String substring;
        try {
            String canonicalPath = file.getCanonicalPath();
            if (Zip4jUtil.j(zipParameters.e())) {
                String canonicalPath2 = new File(zipParameters.e()).getCanonicalPath();
                String str = InternalZipConstants.f1717a;
                if (!canonicalPath2.endsWith(str)) {
                    canonicalPath2 = canonicalPath2 + str;
                }
                if (u(file)) {
                    substring = new File(file.getParentFile().getCanonicalFile().getPath() + File.separator + file.getCanonicalFile().getName()).getPath().substring(canonicalPath2.length());
                } else if (!file.getCanonicalFile().getPath().startsWith(canonicalPath2)) {
                    substring = file.getCanonicalFile().getParentFile().getName() + str + file.getCanonicalFile().getName();
                } else {
                    substring = canonicalPath.substring(canonicalPath2.length());
                }
                if (substring.startsWith(System.getProperty("file.separator"))) {
                    substring = substring.substring(1);
                }
                File file2 = new File(canonicalPath);
                if (file2.isDirectory()) {
                    n2 = substring.replaceAll("\\\\", "/") + "/";
                } else {
                    n2 = substring.substring(0, substring.lastIndexOf(file2.getName())).replaceAll("\\\\", "/") + n(file2, zipParameters.k());
                }
            } else {
                File file3 = new File(canonicalPath);
                n2 = n(file3, zipParameters.k());
                if (file3.isDirectory()) {
                    n2 = n2 + "/";
                }
            }
            String m2 = zipParameters.m();
            if (Zip4jUtil.j(m2)) {
                if (!m2.endsWith("\\") && !m2.endsWith("/")) {
                    m2 = m2 + InternalZipConstants.f1717a;
                }
                m2 = m2.replaceAll("\\\\", "/");
                n2 = m2 + n2;
            }
            if (!Zip4jUtil.j(n2)) {
                String str2 = "fileName to add to zip is empty or null. fileName: '" + n2 + "' DefaultFolderPath: '" + zipParameters.e() + "' FileNameInZip: " + zipParameters.k();
                if (u(file)) {
                    str2 = str2 + "isSymlink: true ";
                }
                if (Zip4jUtil.j(m2)) {
                    str2 = "rootFolderNameInZip: '" + m2 + "' ";
                }
                throw new ZipException(str2);
            }
            return n2;
        } catch (IOException e2) {
            throw new ZipException(e2);
        }
    }

    private static byte[] q(Path path) {
        LinkOption linkOption;
        FileAttributeView fileAttributeView;
        DosFileAttributeView dosFileAttributeView;
        DosFileAttributes readAttributes;
        boolean isReadOnly;
        boolean isHidden;
        boolean isSystem;
        boolean isDirectory;
        boolean isArchive;
        byte[] bArr = new byte[4];
        try {
            linkOption = LinkOption.NOFOLLOW_LINKS;
            fileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class, linkOption);
            dosFileAttributeView = (DosFileAttributeView) fileAttributeView;
        } catch (IOException unused) {
        }
        if (dosFileAttributeView == null) {
            return bArr;
        }
        readAttributes = dosFileAttributeView.readAttributes();
        isReadOnly = readAttributes.isReadOnly();
        byte z2 = z(isReadOnly, (byte) 0, 0);
        isHidden = readAttributes.isHidden();
        byte z3 = z(isHidden, z2, 1);
        isSystem = readAttributes.isSystem();
        byte z4 = z(isSystem, z3, 2);
        isDirectory = readAttributes.isDirectory();
        byte z5 = z(isDirectory, z4, 4);
        isArchive = readAttributes.isArchive();
        bArr[0] = z(isArchive, z5, 5);
        return bArr;
    }

    public static String r(String str) {
        if (Zip4jUtil.j(str)) {
            if (str.contains(System.getProperty("file.separator"))) {
                str = str.substring(str.lastIndexOf(System.getProperty("file.separator")) + 1);
            }
            if (str.endsWith(".zip")) {
                return str.substring(0, str.lastIndexOf("."));
            }
            return str;
        }
        throw new ZipException("zip file name is empty or null, cannot determine zip file name");
    }

    public static boolean s() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean t(File file) {
        return file.getName().endsWith(".zip.001");
    }

    public static boolean u(File file) {
        Path path;
        boolean isSymbolicLink;
        try {
            path = file.toPath();
            isSymbolicLink = Files.isSymbolicLink(path);
            return isSymbolicLink;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static boolean v() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    public static boolean w() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean x(String str) {
        if (!str.endsWith("/") && !str.endsWith("\\")) {
            return false;
        }
        return true;
    }

    public static String y(File file) {
        Path path;
        Path readSymbolicLink;
        String path2;
        try {
            path = file.toPath();
            readSymbolicLink = Files.readSymbolicLink(path);
            path2 = readSymbolicLink.toString();
            return path2;
        } catch (Error | Exception unused) {
            return "";
        }
    }

    private static byte z(boolean z2, byte b2, int i2) {
        return z2 ? BitUtils.b(b2, i2) : b2;
    }
}
