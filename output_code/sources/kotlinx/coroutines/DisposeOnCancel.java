package kotlinx.coroutines;

import kotlin.Unit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class DisposeOnCancel extends CancelHandler {

    /* renamed from: d  reason: collision with root package name */
    private final DisposableHandle f962d;

    public DisposeOnCancel(DisposableHandle disposableHandle) {
        this.f962d = disposableHandle;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f962d.a();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        d(th);
        return Unit.f763a;
    }

    public String toString() {
        return "DisposeOnCancel[" + this.f962d + ']';
    }
}
