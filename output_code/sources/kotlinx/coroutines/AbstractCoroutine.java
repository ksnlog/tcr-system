package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Continuation<T>, CoroutineScope {

    /* renamed from: f  reason: collision with root package name */
    private final CoroutineContext f917f;

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z2, boolean z3) {
        super(z3);
        if (z2) {
            W((Job) coroutineContext.b(Job.f986c));
        }
        this.f917f = coroutineContext.n(this);
    }

    public final <R> void A0(CoroutineStart coroutineStart, R r2, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        coroutineStart.b(function2, r2, this);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void V(Throwable th) {
        CoroutineExceptionHandlerKt.a(this.f917f, th);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean c() {
        return super.c();
    }

    @Override // kotlinx.coroutines.JobSupport
    public String c0() {
        String b2 = CoroutineContextKt.b(this.f917f);
        if (b2 == null) {
            return super.c0();
        }
        return '\"' + b2 + "\":" + super.c0();
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext f() {
        return this.f917f;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.f917f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.JobSupport
    protected final void h0(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            y0(completedExceptionally.f935a, completedExceptionally.a());
            return;
        }
        z0(obj);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Object a02 = a0(CompletionStateKt.d(obj, null, 1, null));
        if (a02 == JobSupportKt.f1004b) {
            return;
        }
        x0(a02);
    }

    protected void x0(Object obj) {
        q(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public String y() {
        return DebugStringsKt.a(this) + " was cancelled";
    }

    protected void y0(Throwable th, boolean z2) {
    }

    protected void z0(T t2) {
    }
}
