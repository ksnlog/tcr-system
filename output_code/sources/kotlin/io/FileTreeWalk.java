package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FileTreeWalk implements Sequence<File> {

    /* renamed from: a  reason: collision with root package name */
    private final File f832a;

    /* renamed from: b  reason: collision with root package name */
    private final FileWalkDirection f833b;

    /* renamed from: c  reason: collision with root package name */
    private final Function1<File, Boolean> f834c;

    /* renamed from: d  reason: collision with root package name */
    private final Function1<File, Unit> f835d;

    /* renamed from: e  reason: collision with root package name */
    private final Function2<File, IOException, Unit> f836e;

    /* renamed from: f  reason: collision with root package name */
    private final int f837f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class DirectoryState extends WalkState {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectoryState(File rootDir) {
            super(rootDir);
            Intrinsics.f(rootDir, "rootDir");
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class FileTreeWalkIterator extends AbstractIterator<File> {

        /* renamed from: f  reason: collision with root package name */
        private final ArrayDeque<WalkState> f838f;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public final class BottomUpDirectoryState extends DirectoryState {

            /* renamed from: b  reason: collision with root package name */
            private boolean f840b;

            /* renamed from: c  reason: collision with root package name */
            private File[] f841c;

            /* renamed from: d  reason: collision with root package name */
            private int f842d;

            /* renamed from: e  reason: collision with root package name */
            private boolean f843e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f844f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BottomUpDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.f(rootDir, "rootDir");
                this.f844f = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File b() {
                if (!this.f843e && this.f841c == null) {
                    Function1 function1 = FileTreeWalk.this.f834c;
                    boolean z2 = false;
                    if (function1 != null && !((Boolean) function1.f(a())).booleanValue()) {
                        z2 = true;
                    }
                    if (z2) {
                        return null;
                    }
                    File[] listFiles = a().listFiles();
                    this.f841c = listFiles;
                    if (listFiles == null) {
                        Function2 function2 = FileTreeWalk.this.f836e;
                        if (function2 != null) {
                            function2.invoke(a(), new AccessDeniedException(a(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.f843e = true;
                    }
                }
                File[] fileArr = this.f841c;
                if (fileArr != null) {
                    int i2 = this.f842d;
                    Intrinsics.c(fileArr);
                    if (i2 < fileArr.length) {
                        File[] fileArr2 = this.f841c;
                        Intrinsics.c(fileArr2);
                        int i3 = this.f842d;
                        this.f842d = i3 + 1;
                        return fileArr2[i3];
                    }
                }
                if (!this.f840b) {
                    this.f840b = true;
                    return a();
                }
                Function1 function12 = FileTreeWalk.this.f835d;
                if (function12 != null) {
                    function12.f(a());
                }
                return null;
            }
        }

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        private final class SingleFileState extends WalkState {

            /* renamed from: b  reason: collision with root package name */
            private boolean f845b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f846c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleFileState(FileTreeWalkIterator fileTreeWalkIterator, File rootFile) {
                super(rootFile);
                Intrinsics.f(rootFile, "rootFile");
                this.f846c = fileTreeWalkIterator;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File b() {
                if (this.f845b) {
                    return null;
                }
                this.f845b = true;
                return a();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public final class TopDownDirectoryState extends DirectoryState {

            /* renamed from: b  reason: collision with root package name */
            private boolean f847b;

            /* renamed from: c  reason: collision with root package name */
            private File[] f848c;

            /* renamed from: d  reason: collision with root package name */
            private int f849d;

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f850e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public TopDownDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, File rootDir) {
                super(rootDir);
                Intrinsics.f(rootDir, "rootDir");
                this.f850e = fileTreeWalkIterator;
            }

            /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
                if (r0.length == 0) goto L32;
             */
            @Override // kotlin.io.FileTreeWalk.WalkState
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.io.File b() {
                /*
                    r10 = this;
                    boolean r0 = r10.f847b
                    r1 = 0
                    if (r0 != 0) goto L2c
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f850e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.c(r0)
                    r2 = 0
                    r3 = 1
                    if (r0 == 0) goto L22
                    java.io.File r4 = r10.a()
                    java.lang.Object r0 = r0.f(r4)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L22
                    r2 = 1
                L22:
                    if (r2 == 0) goto L25
                    return r1
                L25:
                    r10.f847b = r3
                    java.io.File r0 = r10.a()
                    return r0
                L2c:
                    java.io.File[] r0 = r10.f848c
                    if (r0 == 0) goto L4b
                    int r2 = r10.f849d
                    kotlin.jvm.internal.Intrinsics.c(r0)
                    int r0 = r0.length
                    if (r2 >= r0) goto L39
                    goto L4b
                L39:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f850e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.e(r0)
                    if (r0 == 0) goto L4a
                    java.io.File r2 = r10.a()
                    r0.f(r2)
                L4a:
                    return r1
                L4b:
                    java.io.File[] r0 = r10.f848c
                    if (r0 != 0) goto L97
                    java.io.File r0 = r10.a()
                    java.io.File[] r0 = r0.listFiles()
                    r10.f848c = r0
                    if (r0 != 0) goto L7b
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f850e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function2 r0 = kotlin.io.FileTreeWalk.d(r0)
                    if (r0 == 0) goto L7b
                    java.io.File r2 = r10.a()
                    kotlin.io.AccessDeniedException r9 = new kotlin.io.AccessDeniedException
                    java.io.File r4 = r10.a()
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r7 = 2
                    r8 = 0
                    r3 = r9
                    r3.<init>(r4, r5, r6, r7, r8)
                    r0.invoke(r2, r9)
                L7b:
                    java.io.File[] r0 = r10.f848c
                    if (r0 == 0) goto L85
                    kotlin.jvm.internal.Intrinsics.c(r0)
                    int r0 = r0.length
                    if (r0 != 0) goto L97
                L85:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f850e
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.e(r0)
                    if (r0 == 0) goto L96
                    java.io.File r2 = r10.a()
                    r0.f(r2)
                L96:
                    return r1
                L97:
                    java.io.File[] r0 = r10.f848c
                    kotlin.jvm.internal.Intrinsics.c(r0)
                    int r1 = r10.f849d
                    int r2 = r1 + 1
                    r10.f849d = r2
                    r0 = r0[r1]
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.b():java.io.File");
            }
        }

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public /* synthetic */ class WhenMappings {

            /* renamed from: a  reason: collision with root package name */
            public static final /* synthetic */ int[] f851a;

            static {
                int[] iArr = new int[FileWalkDirection.values().length];
                try {
                    iArr[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                f851a = iArr;
            }
        }

        public FileTreeWalkIterator() {
            ArrayDeque<WalkState> arrayDeque = new ArrayDeque<>();
            this.f838f = arrayDeque;
            if (FileTreeWalk.this.f832a.isDirectory()) {
                arrayDeque.push(e(FileTreeWalk.this.f832a));
            } else if (FileTreeWalk.this.f832a.isFile()) {
                arrayDeque.push(new SingleFileState(this, FileTreeWalk.this.f832a));
            } else {
                b();
            }
        }

        private final DirectoryState e(File file) {
            int i2 = WhenMappings.f851a[FileTreeWalk.this.f833b.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return new BottomUpDirectoryState(this, file);
                }
                throw new NoWhenBranchMatchedException();
            }
            return new TopDownDirectoryState(this, file);
        }

        private final File f() {
            File b2;
            while (true) {
                WalkState peek = this.f838f.peek();
                if (peek == null) {
                    return null;
                }
                b2 = peek.b();
                if (b2 == null) {
                    this.f838f.pop();
                } else if (Intrinsics.a(b2, peek.a()) || !b2.isDirectory() || this.f838f.size() >= FileTreeWalk.this.f837f) {
                    break;
                } else {
                    this.f838f.push(e(b2));
                }
            }
            return b2;
        }

        @Override // kotlin.collections.AbstractIterator
        protected void a() {
            File f2 = f();
            if (f2 != null) {
                c(f2);
            } else {
                b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class WalkState {

        /* renamed from: a  reason: collision with root package name */
        private final File f852a;

        public WalkState(File root) {
            Intrinsics.f(root, "root");
            this.f852a = root;
        }

        public final File a() {
            return this.f852a;
        }

        public abstract File b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int i2) {
        this.f832a = file;
        this.f833b = fileWalkDirection;
        this.f834c = function1;
        this.f835d = function12;
        this.f836e = function2;
        this.f837f = i2;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i3 & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection, function1, function12, function2, (i3 & 32) != 0 ? Integer.MAX_VALUE : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileTreeWalk(File start, FileWalkDirection direction) {
        this(start, direction, null, null, null, 0, 32, null);
        Intrinsics.f(start, "start");
        Intrinsics.f(direction, "direction");
    }
}
