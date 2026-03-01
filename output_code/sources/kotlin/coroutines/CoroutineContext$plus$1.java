package kotlin.coroutines;

import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineContext$plus$1 extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {

    /* renamed from: d  reason: collision with root package name */
    public static final CoroutineContext$plus$1 f801d = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: a */
    public final CoroutineContext invoke(CoroutineContext acc, CoroutineContext.Element element) {
        CombinedContext combinedContext;
        Intrinsics.f(acc, "acc");
        Intrinsics.f(element, "element");
        CoroutineContext I = acc.I(element.getKey());
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.f802d;
        if (I != emptyCoroutineContext) {
            ContinuationInterceptor.Key key = ContinuationInterceptor.f799a;
            ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) I.b(key);
            if (continuationInterceptor == null) {
                combinedContext = new CombinedContext(I, element);
            } else {
                CoroutineContext I2 = I.I(key);
                if (I2 == emptyCoroutineContext) {
                    return new CombinedContext(element, continuationInterceptor);
                }
                combinedContext = new CombinedContext(new CombinedContext(I2, element), continuationInterceptor);
            }
            return combinedContext;
        }
        return element;
    }
}
