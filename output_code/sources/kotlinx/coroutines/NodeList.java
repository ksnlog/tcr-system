package kotlinx.coroutines;

import kotlinx.coroutines.internal.LockFreeLinkedListHead;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @Override // kotlinx.coroutines.Incomplete
    public boolean c() {
        return true;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList g() {
        return this;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return super.toString();
    }
}
