package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class InvokeOnCancel extends CancelHandler {

    /* renamed from: d  reason: collision with root package name */
    private final Function1<Throwable, Unit> f982d;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancel(Function1<? super Throwable, Unit> function1) {
        this.f982d = function1;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f982d.f(th);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        d(th);
        return Unit.f763a;
    }

    public String toString() {
        return "InvokeOnCancel[" + DebugStringsKt.a(this.f982d) + '@' + DebugStringsKt.b(this) + ']';
    }
}
