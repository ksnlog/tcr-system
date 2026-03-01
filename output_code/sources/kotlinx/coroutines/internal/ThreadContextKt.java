package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ThreadContextKt {

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f1156a = new Symbol("NO_THREAD_ELEMENTS");

    /* renamed from: b  reason: collision with root package name */
    private static final Function2<Object, CoroutineContext.Element, Object> f1157b = ThreadContextKt$countAll$1.f1160d;

    /* renamed from: c  reason: collision with root package name */
    private static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> f1158c = ThreadContextKt$findOne$1.f1161d;

    /* renamed from: d  reason: collision with root package name */
    private static final Function2<ThreadState, CoroutineContext.Element, ThreadState> f1159d = ThreadContextKt$updateState$1.f1162d;

    public static final void a(CoroutineContext coroutineContext, Object obj) {
        if (obj == f1156a) {
            return;
        }
        if (obj instanceof ThreadState) {
            ((ThreadState) obj).b(coroutineContext);
            return;
        }
        Object B = coroutineContext.B(null, f1158c);
        Intrinsics.d(B, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        ((ThreadContextElement) B).r(coroutineContext, obj);
    }

    public static final Object b(CoroutineContext coroutineContext) {
        Object B = coroutineContext.B(0, f1157b);
        Intrinsics.c(B);
        return B;
    }

    public static final Object c(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = b(coroutineContext);
        }
        if (obj == 0) {
            return f1156a;
        }
        if (obj instanceof Integer) {
            return coroutineContext.B(new ThreadState(coroutineContext, ((Number) obj).intValue()), f1159d);
        }
        Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        return ((ThreadContextElement) obj).J(coroutineContext);
    }
}
