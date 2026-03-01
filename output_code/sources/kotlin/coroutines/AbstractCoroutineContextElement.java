package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {

    /* renamed from: d  reason: collision with root package name */
    private final CoroutineContext.Key<?> f793d;

    public AbstractCoroutineContextElement(CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        this.f793d = key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R B(R r2, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) CoroutineContext.Element.DefaultImpls.a(this, r2, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext I(CoroutineContext.Key<?> key) {
        return CoroutineContext.Element.DefaultImpls.c(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E b(CoroutineContext.Key<E> key) {
        return (E) CoroutineContext.Element.DefaultImpls.b(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<?> getKey() {
        return this.f793d;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext n(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.d(this, coroutineContext);
    }
}
