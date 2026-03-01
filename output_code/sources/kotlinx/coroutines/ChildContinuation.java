package kotlinx.coroutines;

import kotlin.Unit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChildContinuation extends JobCancellingNode {

    /* renamed from: h  reason: collision with root package name */
    public final CancellableContinuationImpl<?> f927h;

    public ChildContinuation(CancellableContinuationImpl<?> cancellableContinuationImpl) {
        this.f927h = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        v(th);
        return Unit.f763a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void v(Throwable th) {
        CancellableContinuationImpl<?> cancellableContinuationImpl = this.f927h;
        cancellableContinuationImpl.H(cancellableContinuationImpl.u(w()));
    }
}
