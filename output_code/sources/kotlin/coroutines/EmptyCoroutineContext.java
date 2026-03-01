package kotlin.coroutines;

import java.io.Serializable;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EmptyCoroutineContext implements CoroutineContext, Serializable {

    /* renamed from: d  reason: collision with root package name */
    public static final EmptyCoroutineContext f802d = new EmptyCoroutineContext();

    private EmptyCoroutineContext() {
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R B(R r2, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.f(operation, "operation");
        return r2;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext I(CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        return this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E b(CoroutineContext.Key<E> key) {
        Intrinsics.f(key, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext n(CoroutineContext context) {
        Intrinsics.f(context, "context");
        return context;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
