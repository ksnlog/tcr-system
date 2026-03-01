package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {

    /* renamed from: g  reason: collision with root package name */
    private final int f1201g;

    /* renamed from: h  reason: collision with root package name */
    private final int f1202h;

    /* renamed from: i  reason: collision with root package name */
    private final long f1203i;

    /* renamed from: j  reason: collision with root package name */
    private final String f1204j;

    /* renamed from: k  reason: collision with root package name */
    private CoroutineScheduler f1205k = T();

    public SchedulerCoroutineDispatcher(int i2, int i3, long j2, String str) {
        this.f1201g = i2;
        this.f1202h = i3;
        this.f1203i = j2;
        this.f1204j = str;
    }

    private final CoroutineScheduler T() {
        return new CoroutineScheduler(this.f1201g, this.f1202h, this.f1203i, this.f1204j);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.o(this.f1205k, runnable, null, false, 6, null);
    }

    public final void U(Runnable runnable, TaskContext taskContext, boolean z2) {
        this.f1205k.n(runnable, taskContext, z2);
    }
}
