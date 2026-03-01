package kotlinx.coroutines.scheduling;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Task implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    public long f1206d;

    /* renamed from: e  reason: collision with root package name */
    public TaskContext f1207e;

    public Task(long j2, TaskContext taskContext) {
        this.f1206d = j2;
        this.f1207e = taskContext;
    }

    public Task() {
        this(0L, TasksKt.f1216g);
    }
}
