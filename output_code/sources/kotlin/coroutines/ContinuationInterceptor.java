package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {

    /* renamed from: a  reason: collision with root package name */
    public static final Key f799a = Key.f800d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class DefaultImpls {
        public static <E extends CoroutineContext.Element> E a(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key<E> key) {
            Intrinsics.f(key, "key");
            if (key instanceof AbstractCoroutineContextKey) {
                AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
                if (!abstractCoroutineContextKey.a(continuationInterceptor.getKey())) {
                    return null;
                }
                E e2 = (E) abstractCoroutineContextKey.b(continuationInterceptor);
                if (!(e2 instanceof CoroutineContext.Element)) {
                    return null;
                }
                return e2;
            } else if (ContinuationInterceptor.f799a != key) {
                return null;
            } else {
                Intrinsics.d(continuationInterceptor, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                return continuationInterceptor;
            }
        }

        public static CoroutineContext b(ContinuationInterceptor continuationInterceptor, CoroutineContext.Key<?> key) {
            Intrinsics.f(key, "key");
            if (key instanceof AbstractCoroutineContextKey) {
                AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
                if (abstractCoroutineContextKey.a(continuationInterceptor.getKey()) && abstractCoroutineContextKey.b(continuationInterceptor) != null) {
                    return EmptyCoroutineContext.f802d;
                }
                return continuationInterceptor;
            } else if (ContinuationInterceptor.f799a == key) {
                return EmptyCoroutineContext.f802d;
            } else {
                return continuationInterceptor;
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Key implements CoroutineContext.Key<ContinuationInterceptor> {

        /* renamed from: d  reason: collision with root package name */
        static final /* synthetic */ Key f800d = new Key();

        private Key() {
        }
    }

    void e(Continuation<?> continuation);

    <T> Continuation<T> k(Continuation<? super T> continuation);
}
