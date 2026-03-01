package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Ref$ObjectRef;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineContextKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    private static final CoroutineContext a(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, boolean z2) {
        boolean c2 = c(coroutineContext);
        boolean c3 = c(coroutineContext2);
        if (!c2 && !c3) {
            return coroutineContext.n(coroutineContext2);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.f884d = coroutineContext2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.f802d;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.B(emptyCoroutineContext, new CoroutineContextKt$foldCopies$folded$1(ref$ObjectRef, z2));
        if (c3) {
            ref$ObjectRef.f884d = ((CoroutineContext) ref$ObjectRef.f884d).B(emptyCoroutineContext, CoroutineContextKt$foldCopies$1.f938d);
        }
        return coroutineContext3.n((CoroutineContext) ref$ObjectRef.f884d);
    }

    public static final String b(CoroutineContext coroutineContext) {
        return null;
    }

    private static final boolean c(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.B(Boolean.FALSE, CoroutineContextKt$hasCopyableElements$1.f941d)).booleanValue();
    }

    public static final CoroutineContext d(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        if (!c(coroutineContext2)) {
            return coroutineContext.n(coroutineContext2);
        }
        return a(coroutineContext, coroutineContext2, false);
    }

    public static final CoroutineContext e(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext a2 = a(coroutineScope.f(), coroutineContext, true);
        if (a2 != Dispatchers.a() && a2.b(ContinuationInterceptor.f799a) == null) {
            return a2.n(Dispatchers.a());
        }
        return a2;
    }

    public static final UndispatchedCoroutine<?> f(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final UndispatchedCoroutine<?> g(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        boolean z2;
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (coroutineContext.b(UndispatchedMarker.f1016d) != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return null;
        }
        UndispatchedCoroutine<?> f2 = f((CoroutineStackFrame) continuation);
        if (f2 != null) {
            f2.C0(coroutineContext, obj);
        }
        return f2;
    }
}
