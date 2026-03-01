package kotlinx.coroutines;

import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {

    /* renamed from: h  reason: collision with root package name */
    private final ThreadLocal<Pair<CoroutineContext, Object>> f1015h;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UndispatchedCoroutine(kotlin.coroutines.CoroutineContext r3, kotlin.coroutines.Continuation<? super T> r4) {
        /*
            r2 = this;
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.f1016d
            kotlin.coroutines.CoroutineContext$Element r1 = r3.b(r0)
            if (r1 != 0) goto Ld
            kotlin.coroutines.CoroutineContext r0 = r3.n(r0)
            goto Le
        Ld:
            r0 = r3
        Le:
            r2.<init>(r0, r4)
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            r2.f1015h = r0
            kotlin.coroutines.CoroutineContext r4 = r4.getContext()
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.f799a
            kotlin.coroutines.CoroutineContext$Element r4 = r4.b(r0)
            boolean r4 = r4 instanceof kotlinx.coroutines.CoroutineDispatcher
            if (r4 != 0) goto L31
            r4 = 0
            java.lang.Object r4 = kotlinx.coroutines.internal.ThreadContextKt.c(r3, r4)
            kotlinx.coroutines.internal.ThreadContextKt.a(r3, r4)
            r2.C0(r3, r4)
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.UndispatchedCoroutine.<init>(kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):void");
    }

    public final boolean B0() {
        boolean z2;
        if (this.threadLocalIsSet && this.f1015h.get() == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f1015h.remove();
        return !z2;
    }

    public final void C0(CoroutineContext coroutineContext, Object obj) {
        this.threadLocalIsSet = true;
        this.f1015h.set(TuplesKt.a(coroutineContext, obj));
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void x0(Object obj) {
        if (this.threadLocalIsSet) {
            Pair<CoroutineContext, Object> pair = this.f1015h.get();
            if (pair != null) {
                ThreadContextKt.a(pair.a(), pair.b());
            }
            this.f1015h.remove();
        }
        Object a2 = CompletionStateKt.a(obj, this.f1148g);
        Continuation<T> continuation = this.f1148g;
        CoroutineContext context = continuation.getContext();
        UndispatchedCoroutine<?> undispatchedCoroutine = null;
        Object c2 = ThreadContextKt.c(context, null);
        if (c2 != ThreadContextKt.f1156a) {
            undispatchedCoroutine = CoroutineContextKt.g(continuation, context, c2);
        }
        try {
            this.f1148g.resumeWith(a2);
            Unit unit = Unit.f763a;
        } finally {
            if (undispatchedCoroutine == null || undispatchedCoroutine.B0()) {
                ThreadContextKt.a(context, c2);
            }
        }
    }
}
