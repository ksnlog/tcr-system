package kotlinx.coroutines.scheduling;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefaultScheduler extends SchedulerCoroutineDispatcher {

    /* renamed from: l  reason: collision with root package name */
    public static final DefaultScheduler f1199l = new DefaultScheduler();

    private DefaultScheduler() {
        super(TasksKt.f1212c, TasksKt.f1213d, TasksKt.f1214e, TasksKt.f1210a);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.Default";
    }
}
