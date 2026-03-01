package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {

    /* renamed from: d  reason: collision with root package name */
    public static final NonDisposableHandle f1011d = new NonDisposableHandle();

    private NonDisposableHandle() {
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void a() {
    }

    @Override // kotlinx.coroutines.ChildHandle
    public boolean d(Throwable th) {
        return false;
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
