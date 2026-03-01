package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Pair<A, B> implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final A f750d;

    /* renamed from: e  reason: collision with root package name */
    private final B f751e;

    public Pair(A a2, B b2) {
        this.f750d = a2;
        this.f751e = b2;
    }

    public final A a() {
        return this.f750d;
    }

    public final B b() {
        return this.f751e;
    }

    public final A c() {
        return this.f750d;
    }

    public final B d() {
        return this.f751e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return Intrinsics.a(this.f750d, pair.f750d) && Intrinsics.a(this.f751e, pair.f751e);
    }

    public int hashCode() {
        A a2 = this.f750d;
        int i2 = 0;
        int hashCode = (a2 == null ? 0 : a2.hashCode()) * 31;
        B b2 = this.f751e;
        if (b2 != null) {
            i2 = b2.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        return '(' + this.f750d + ", " + this.f751e + ')';
    }
}
