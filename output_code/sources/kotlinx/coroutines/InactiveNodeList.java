package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InactiveNodeList implements Incomplete {

    /* renamed from: d  reason: collision with root package name */
    private final NodeList f980d;

    public InactiveNodeList(NodeList nodeList) {
        this.f980d = nodeList;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean c() {
        return false;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList g() {
        return this.f980d;
    }

    public String toString() {
        return super.toString();
    }
}
