package kotlinx.coroutines;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Empty implements Incomplete {

    /* renamed from: d  reason: collision with root package name */
    private final boolean f963d;

    public Empty(boolean z2) {
        this.f963d = z2;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean c() {
        return this.f963d;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList g() {
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empty{");
        sb.append(c() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
