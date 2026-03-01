package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InvokeOnCompletion extends JobNode {

    /* renamed from: h  reason: collision with root package name */
    private final Function1<Throwable, Unit> f985h;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCompletion(Function1<? super Throwable, Unit> function1) {
        this.f985h = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        v(th);
        return Unit.f763a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void v(Throwable th) {
        this.f985h.f(th);
    }
}
