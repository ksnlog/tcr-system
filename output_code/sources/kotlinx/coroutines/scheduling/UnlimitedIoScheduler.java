package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class UnlimitedIoScheduler extends CoroutineDispatcher {

    /* renamed from: f  reason: collision with root package name */
    public static final UnlimitedIoScheduler f1218f = new UnlimitedIoScheduler();

    private UnlimitedIoScheduler() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        DefaultScheduler.f1199l.U(runnable, TasksKt.f1217h, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher S(int i2) {
        LimitedDispatcherKt.a(i2);
        if (i2 >= TasksKt.f1213d) {
            return this;
        }
        return super.S(i2);
    }
}
