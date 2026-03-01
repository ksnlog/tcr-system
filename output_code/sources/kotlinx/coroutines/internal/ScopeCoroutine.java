package kotlinx.coroutines.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletionStateKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ScopeCoroutine<T> extends AbstractCoroutine<T> implements CoroutineStackFrame {

    /* renamed from: g  reason: collision with root package name */
    public final Continuation<T> f1148g;

    /* JADX WARN: Multi-variable type inference failed */
    public ScopeCoroutine(CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        super(coroutineContext, true, true);
        this.f1148g = continuation;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final boolean Y() {
        return true;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.f1148g;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public void q(Object obj) {
        Continuation b2;
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(this.f1148g);
        DispatchedContinuationKt.c(b2, CompletionStateKt.a(obj, this.f1148g), null, 2, null);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void x0(Object obj) {
        Continuation<T> continuation = this.f1148g;
        continuation.resumeWith(CompletionStateKt.a(obj, continuation));
    }
}
