package kotlinx.coroutines.scheduling;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class TaskContextImpl implements TaskContext {

    /* renamed from: a  reason: collision with root package name */
    private final int f1208a;

    public TaskContextImpl(int i2) {
        this.f1208a = i2;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void a() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int b() {
        return this.f1208a;
    }
}
