package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class JobImpl extends JobSupport implements CompletableJob {

    /* renamed from: f  reason: collision with root package name */
    private final boolean f989f;

    public JobImpl(Job job) {
        super(true);
        W(job);
        this.f989f = x0();
    }

    private final boolean x0() {
        ChildHandleNode childHandleNode;
        JobSupport w2;
        ChildHandleNode childHandleNode2;
        ChildHandle S = S();
        if (S instanceof ChildHandleNode) {
            childHandleNode = (ChildHandleNode) S;
        } else {
            childHandleNode = null;
        }
        if (childHandleNode != null && (w2 = childHandleNode.w()) != null) {
            while (!w2.P()) {
                ChildHandle S2 = w2.S();
                if (S2 instanceof ChildHandleNode) {
                    childHandleNode2 = (ChildHandleNode) S2;
                } else {
                    childHandleNode2 = null;
                }
                if (childHandleNode2 != null) {
                    w2 = childHandleNode2.w();
                    if (w2 == null) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean P() {
        return this.f989f;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean Q() {
        return true;
    }
}
