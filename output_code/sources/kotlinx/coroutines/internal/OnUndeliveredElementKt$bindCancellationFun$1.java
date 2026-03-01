package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class OnUndeliveredElementKt$bindCancellationFun$1 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Function1<E, Unit> f1144d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ E f1145e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ CoroutineContext f1146f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public OnUndeliveredElementKt$bindCancellationFun$1(Function1<? super E, Unit> function1, E e2, CoroutineContext coroutineContext) {
        super(1);
        this.f1144d = function1;
        this.f1145e = e2;
        this.f1146f = coroutineContext;
    }

    public final void a(Throwable th) {
        OnUndeliveredElementKt.b(this.f1144d, this.f1145e, this.f1146f);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        a(th);
        return Unit.f763a;
    }
}
