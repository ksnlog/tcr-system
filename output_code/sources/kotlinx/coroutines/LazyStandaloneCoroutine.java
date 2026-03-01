package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LazyStandaloneCoroutine extends StandaloneCoroutine {

    /* renamed from: g  reason: collision with root package name */
    private final Continuation<Unit> f1010g;

    public LazyStandaloneCoroutine(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        super(coroutineContext, false);
        Continuation<Unit> a2;
        a2 = IntrinsicsKt__IntrinsicsJvmKt.a(function2, this, this);
        this.f1010g = a2;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void i0() {
        CancellableKt.b(this.f1010g, this);
    }
}
