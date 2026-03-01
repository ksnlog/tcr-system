package kotlinx.coroutines;

import kotlin.Unit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChildHandleNode extends JobCancellingNode implements ChildHandle {

    /* renamed from: h  reason: collision with root package name */
    public final ChildJob f928h;

    public ChildHandleNode(ChildJob childJob) {
        this.f928h = childJob;
    }

    @Override // kotlinx.coroutines.ChildHandle
    public boolean d(Throwable th) {
        return w().A(th);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        v(th);
        return Unit.f763a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void v(Throwable th) {
        this.f928h.E(w());
    }
}
