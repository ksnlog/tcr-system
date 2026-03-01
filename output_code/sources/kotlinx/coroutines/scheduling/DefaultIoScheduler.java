package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefaultIoScheduler extends ExecutorCoroutineDispatcher implements Executor {

    /* renamed from: g  reason: collision with root package name */
    public static final DefaultIoScheduler f1197g = new DefaultIoScheduler();

    /* renamed from: h  reason: collision with root package name */
    private static final CoroutineDispatcher f1198h;

    static {
        int a2;
        int e2;
        UnlimitedIoScheduler unlimitedIoScheduler = UnlimitedIoScheduler.f1218f;
        a2 = RangesKt___RangesKt.a(64, SystemPropsKt.a());
        e2 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.io.parallelism", a2, 0, 0, 12, null);
        f1198h = unlimitedIoScheduler.S(e2);
    }

    private DefaultIoScheduler() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        f1198h.Q(coroutineContext, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Q(EmptyCoroutineContext.f802d, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.IO";
    }
}
