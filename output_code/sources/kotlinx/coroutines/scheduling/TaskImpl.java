package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.DebugStringsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TaskImpl extends Task {

    /* renamed from: f  reason: collision with root package name */
    public final Runnable f1209f;

    public TaskImpl(Runnable runnable, long j2, TaskContext taskContext) {
        super(j2, taskContext);
        this.f1209f = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f1209f.run();
        } finally {
            this.f1207e.a();
        }
    }

    public String toString() {
        return "Task[" + DebugStringsKt.a(this.f1209f) + '@' + DebugStringsKt.b(this.f1209f) + ", " + this.f1206d + ", " + this.f1207e + ']';
    }
}
