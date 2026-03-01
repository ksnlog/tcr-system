package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class JobNode extends CompletionHandlerBase implements DisposableHandle, Incomplete {

    /* renamed from: g  reason: collision with root package name */
    public JobSupport f990g;

    @Override // kotlinx.coroutines.DisposableHandle
    public void a() {
        w().l0(this);
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean c() {
        return true;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList g() {
        return null;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this) + "[job@" + DebugStringsKt.b(w()) + ']';
    }

    public final JobSupport w() {
        JobSupport jobSupport = this.f990g;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.p("job");
        return null;
    }

    public final void x(JobSupport jobSupport) {
        this.f990g = jobSupport;
    }
}
