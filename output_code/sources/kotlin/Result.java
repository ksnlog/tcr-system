package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Result<T> implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    public static final Companion f752d = new Companion(null);

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Failure implements Serializable {

        /* renamed from: d  reason: collision with root package name */
        public final Throwable f753d;

        public Failure(Throwable exception) {
            Intrinsics.f(exception, "exception");
            this.f753d = exception;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && Intrinsics.a(this.f753d, ((Failure) obj).f753d);
        }

        public int hashCode() {
            return this.f753d.hashCode();
        }

        public String toString() {
            return "Failure(" + this.f753d + ')';
        }
    }

    public static <T> Object a(Object obj) {
        return obj;
    }

    public static final Throwable b(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).f753d;
        }
        return null;
    }

    public static final boolean c(Object obj) {
        return obj instanceof Failure;
    }

    public static final boolean d(Object obj) {
        return !(obj instanceof Failure);
    }
}
