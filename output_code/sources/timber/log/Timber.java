package timber.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Timber {

    /* renamed from: a  reason: collision with root package name */
    public static final Forest f3479a = new Forest(null);

    /* renamed from: b  reason: collision with root package name */
    private static final ArrayList<Tree> f3480b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private static volatile Tree[] f3481c = new Tree[0];

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Forest extends Tree {
        private Forest() {
        }

        public /* synthetic */ Forest(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // timber.log.Timber.Tree
        public void a(String str, Object... args) {
            Intrinsics.f(args, "args");
            for (Tree tree : Timber.f3481c) {
                tree.a(str, Arrays.copyOf(args, args.length));
            }
        }

        @Override // timber.log.Timber.Tree
        public void b(Throwable th, String str, Object... args) {
            Intrinsics.f(args, "args");
            for (Tree tree : Timber.f3481c) {
                tree.b(th, str, Arrays.copyOf(args, args.length));
            }
        }

        @Override // timber.log.Timber.Tree
        public void f(String str, Object... args) {
            Intrinsics.f(args, "args");
            for (Tree tree : Timber.f3481c) {
                tree.f(str, Arrays.copyOf(args, args.length));
            }
        }

        @Override // timber.log.Timber.Tree
        protected void i(int i2, String str, String message, Throwable th) {
            Intrinsics.f(message, "message");
            throw new AssertionError();
        }

        @Override // timber.log.Timber.Tree
        public void k(String str, Object... args) {
            Intrinsics.f(args, "args");
            for (Tree tree : Timber.f3481c) {
                tree.k(str, Arrays.copyOf(args, args.length));
            }
        }

        @Override // timber.log.Timber.Tree
        public void l(Throwable th, String str, Object... args) {
            Intrinsics.f(args, "args");
            for (Tree tree : Timber.f3481c) {
                tree.l(th, str, Arrays.copyOf(args, args.length));
            }
        }

        public final void m(Tree tree) {
            boolean z2;
            Intrinsics.f(tree, "tree");
            if (tree != this) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                synchronized (Timber.f3480b) {
                    Timber.f3480b.add(tree);
                    Object[] array = Timber.f3480b.toArray(new Tree[0]);
                    if (array != null) {
                        Timber.f3481c = (Tree[]) array;
                        Unit unit = Unit.f763a;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Cannot plant Timber into itself.".toString());
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class Tree {

        /* renamed from: a  reason: collision with root package name */
        private final ThreadLocal<String> f3482a = new ThreadLocal<>();

        private final String d(Throwable th) {
            StringWriter stringWriter = new StringWriter(256);
            PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
            th.printStackTrace(printWriter);
            printWriter.flush();
            String stringWriter2 = stringWriter.toString();
            Intrinsics.e(stringWriter2, "sw.toString()");
            return stringWriter2;
        }

        private final void j(int i2, Throwable th, String str, Object... objArr) {
            boolean z2;
            String e2 = e();
            if (!h(e2, i2)) {
                return;
            }
            boolean z3 = false;
            if (str != null && str.length() != 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2) {
                if (th == null) {
                    return;
                }
                str = d(th);
            } else {
                if (objArr.length == 0) {
                    z3 = true;
                }
                if (!z3) {
                    str = c(str, objArr);
                }
                if (th != null) {
                    str = ((Object) str) + '\n' + d(th);
                }
            }
            i(i2, e2, str, th);
        }

        public void a(String str, Object... args) {
            Intrinsics.f(args, "args");
            j(3, null, str, Arrays.copyOf(args, args.length));
        }

        public void b(Throwable th, String str, Object... args) {
            Intrinsics.f(args, "args");
            j(6, th, str, Arrays.copyOf(args, args.length));
        }

        protected String c(String message, Object[] args) {
            Intrinsics.f(message, "message");
            Intrinsics.f(args, "args");
            Object[] copyOf = Arrays.copyOf(args, args.length);
            String format = String.format(message, Arrays.copyOf(copyOf, copyOf.length));
            Intrinsics.e(format, "java.lang.String.format(this, *args)");
            return format;
        }

        public /* synthetic */ String e() {
            String str = this.f3482a.get();
            if (str != null) {
                this.f3482a.remove();
            }
            return str;
        }

        public void f(String str, Object... args) {
            Intrinsics.f(args, "args");
            j(4, null, str, Arrays.copyOf(args, args.length));
        }

        protected boolean g(int i2) {
            return true;
        }

        protected boolean h(String str, int i2) {
            return g(i2);
        }

        protected abstract void i(int i2, String str, String str2, Throwable th);

        public void k(String str, Object... args) {
            Intrinsics.f(args, "args");
            j(5, null, str, Arrays.copyOf(args, args.length));
        }

        public void l(Throwable th, String str, Object... args) {
            Intrinsics.f(args, "args");
            j(5, th, str, Arrays.copyOf(args, args.length));
        }
    }

    public static void d(String str, Object... objArr) {
        f3479a.a(str, objArr);
    }

    public static void e(Throwable th, String str, Object... objArr) {
        f3479a.b(th, str, objArr);
    }

    public static final void f(Tree tree) {
        f3479a.m(tree);
    }

    public static void g(String str, Object... objArr) {
        f3479a.k(str, objArr);
    }

    public static void h(Throwable th, String str, Object... objArr) {
        f3479a.l(th, str, objArr);
    }
}
