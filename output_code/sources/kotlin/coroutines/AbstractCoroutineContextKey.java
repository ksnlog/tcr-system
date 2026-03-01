package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractCoroutineContextKey<B extends CoroutineContext.Element, E extends B> implements CoroutineContext.Key<E> {

    /* renamed from: d  reason: collision with root package name */
    private final Function1<CoroutineContext.Element, E> f794d;

    /* renamed from: e  reason: collision with root package name */
    private final CoroutineContext.Key<?> f795e;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [kotlin.jvm.functions.Function1<? super kotlin.coroutines.CoroutineContext$Element, ? extends E extends B>, java.lang.Object, kotlin.jvm.functions.Function1<kotlin.coroutines.CoroutineContext$Element, E extends B>] */
    public AbstractCoroutineContextKey(CoroutineContext.Key<B> baseKey, Function1<? super CoroutineContext.Element, ? extends E> safeCast) {
        Intrinsics.f(baseKey, "baseKey");
        Intrinsics.f(safeCast, "safeCast");
        this.f794d = safeCast;
        this.f795e = baseKey instanceof AbstractCoroutineContextKey ? (CoroutineContext.Key<B>) ((AbstractCoroutineContextKey) baseKey).f795e : baseKey;
    }

    public final boolean a(CoroutineContext.Key<?> key) {
        Intrinsics.f(key, "key");
        if (key != this && this.f795e != key) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Incorrect return type in method signature: (Lkotlin/coroutines/CoroutineContext$Element;)TE; */
    public final CoroutineContext.Element b(CoroutineContext.Element element) {
        Intrinsics.f(element, "element");
        return (CoroutineContext.Element) this.f794d.f(element);
    }
}
