package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Unconfined extends CoroutineDispatcher {

    /* renamed from: f  reason: collision with root package name */
    public static final Unconfined f1014f = new Unconfined();

    private Unconfined() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        YieldContext yieldContext = (YieldContext) coroutineContext.b(YieldContext.f1017f);
        if (yieldContext != null) {
            yieldContext.f1018e = true;
            return;
        }
        throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean R(CoroutineContext coroutineContext) {
        return false;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
