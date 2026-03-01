package kotlin.coroutines;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface CoroutineContext {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class DefaultImpls {
        public static CoroutineContext a(CoroutineContext coroutineContext, CoroutineContext context) {
            Intrinsics.f(context, "context");
            if (context != EmptyCoroutineContext.f802d) {
                return (CoroutineContext) context.B(coroutineContext, CoroutineContext$plus$1.f801d);
            }
            return coroutineContext;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Element extends CoroutineContext {

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public static final class DefaultImpls {
            public static <R> R a(Element element, R r2, Function2<? super R, ? super Element, ? extends R> operation) {
                Intrinsics.f(operation, "operation");
                return operation.invoke(r2, element);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E b(Element element, Key<E> key) {
                Intrinsics.f(key, "key");
                if (Intrinsics.a(element.getKey(), key)) {
                    Intrinsics.d(element, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                    return element;
                }
                return null;
            }

            public static CoroutineContext c(Element element, Key<?> key) {
                Intrinsics.f(key, "key");
                if (Intrinsics.a(element.getKey(), key)) {
                    return EmptyCoroutineContext.f802d;
                }
                return element;
            }

            public static CoroutineContext d(Element element, CoroutineContext context) {
                Intrinsics.f(context, "context");
                return DefaultImpls.a(element, context);
            }
        }

        @Override // kotlin.coroutines.CoroutineContext
        <E extends Element> E b(Key<E> key);

        Key<?> getKey();
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Key<E extends Element> {
    }

    <R> R B(R r2, Function2<? super R, ? super Element, ? extends R> function2);

    CoroutineContext I(Key<?> key);

    <E extends Element> E b(Key<E> key);

    CoroutineContext n(CoroutineContext coroutineContext);
}
