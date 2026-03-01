package kotlin.coroutines;

import java.io.Serializable;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CombinedContext implements CoroutineContext, Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final CoroutineContext f796d;

    /* renamed from: e  reason: collision with root package name */
    private final CoroutineContext.Element f797e;

    public CombinedContext(CoroutineContext left, CoroutineContext.Element element) {
        Intrinsics.f(left, "left");
        Intrinsics.f(element, "element");
        this.f796d = left;
        this.f797e = element;
    }

    private final boolean a(CoroutineContext.Element element) {
        return Intrinsics.a(b(element.getKey()), element);
    }

    private final boolean d(CombinedContext combinedContext) {
        while (a(combinedContext.f797e)) {
            CoroutineContext coroutineContext = combinedContext.f796d;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else {
                Intrinsics.d(coroutineContext, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                return a((CoroutineContext.Element) coroutineContext);
            }
        }
        return false;
    }

    private final int f() {
        int i2 = 2;
        CombinedContext combinedContext = this;
        while (true) {
            CoroutineContext coroutineContext = combinedContext.f796d;
            combinedContext = coroutineContext instanceof CombinedContext ? (CombinedContext) coroutineContext : null;
            if (combinedContext == null) {
                return i2;
            }
            i2++;
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R B(R r2, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.f(operation, "operation");
        return operation.invoke((Object) this.f796d.B(r2, operation), this.f797e);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext I(CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        if (this.f797e.b(key) != null) {
            return this.f796d;
        }
        CoroutineContext I = this.f796d.I(key);
        if (I == this.f796d) {
            return this;
        }
        if (I == EmptyCoroutineContext.f802d) {
            return this.f797e;
        }
        return new CombinedContext(I, this.f797e);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E b(CoroutineContext.Key<E> key) {
        Intrinsics.f(key, "key");
        CombinedContext combinedContext = this;
        while (true) {
            E e2 = (E) combinedContext.f797e.b(key);
            if (e2 != null) {
                return e2;
            }
            CoroutineContext coroutineContext = combinedContext.f796d;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else {
                return (E) coroutineContext.b(key);
            }
        }
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) obj;
                if (combinedContext.f() != f() || !combinedContext.d(this)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f796d.hashCode() + this.f797e.hashCode();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext n(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.a(this, coroutineContext);
    }

    public String toString() {
        return '[' + ((String) B("", CombinedContext$toString$1.f798d)) + ']';
    }
}
