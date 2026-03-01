package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class InvokeOnCancelling extends JobCancellingNode {

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f983i = AtomicIntegerFieldUpdater.newUpdater(InvokeOnCancelling.class, "_invoked");
    private volatile int _invoked;

    /* renamed from: h  reason: collision with root package name */
    private final Function1<Throwable, Unit> f984h;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancelling(Function1<? super Throwable, Unit> function1) {
        this.f984h = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        v(th);
        return Unit.f763a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void v(Throwable th) {
        if (f983i.compareAndSet(this, 0, 1)) {
            this.f984h.f(th);
        }
    }
}
