package kotlin.collections;

import java.util.Iterator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class IntIterator implements Iterator<Integer> {
    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ Integer next() {
        return Integer.valueOf(nextInt());
    }

    public abstract int nextInt();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
