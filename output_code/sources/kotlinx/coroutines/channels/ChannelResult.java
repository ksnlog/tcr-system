package kotlinx.coroutines.channels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChannelResult<T> {

    /* renamed from: b  reason: collision with root package name */
    public static final Companion f1087b = new Companion(null);

    /* renamed from: c  reason: collision with root package name */
    private static final Failed f1088c = new Failed();

    /* renamed from: a  reason: collision with root package name */
    private final Object f1089a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Closed extends Failed {

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f1090a;

        public Closed(Throwable th) {
            this.f1090a = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.a(this.f1090a, ((Closed) obj).f1090a);
        }

        public int hashCode() {
            Throwable th = this.f1090a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public String toString() {
            return "Closed(" + this.f1090a + ')';
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <E> Object a(Throwable th) {
            return ChannelResult.c(new Closed(th));
        }

        public final <E> Object b() {
            return ChannelResult.c(ChannelResult.f1088c);
        }

        public final <E> Object c(E e2) {
            return ChannelResult.c(e2);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    private /* synthetic */ ChannelResult(Object obj) {
        this.f1089a = obj;
    }

    public static final /* synthetic */ ChannelResult b(Object obj) {
        return new ChannelResult(obj);
    }

    public static <T> Object c(Object obj) {
        return obj;
    }

    public static boolean d(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.a(obj, ((ChannelResult) obj2).i());
    }

    public static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final boolean f(Object obj) {
        return obj instanceof Closed;
    }

    public static final boolean g(Object obj) {
        return !(obj instanceof Failed);
    }

    public static String h(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return d(this.f1089a, obj);
    }

    public int hashCode() {
        return e(this.f1089a);
    }

    public final /* synthetic */ Object i() {
        return this.f1089a;
    }

    public String toString() {
        return h(this.f1089a);
    }
}
