package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CompletedWithCancellation {

    /* renamed from: a  reason: collision with root package name */
    public final Object f936a;

    /* renamed from: b  reason: collision with root package name */
    public final Function1<Throwable, Unit> f937b;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedWithCancellation(Object obj, Function1<? super Throwable, Unit> function1) {
        this.f936a = obj;
        this.f937b = function1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedWithCancellation)) {
            return false;
        }
        CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
        return Intrinsics.a(this.f936a, completedWithCancellation.f936a) && Intrinsics.a(this.f937b, completedWithCancellation.f937b);
    }

    public int hashCode() {
        Object obj = this.f936a;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.f937b.hashCode();
    }

    public String toString() {
        return "CompletedWithCancellation(result=" + this.f936a + ", onCancellation=" + this.f937b + ')';
    }
}
