package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ArrayIterator<T> implements Iterator<T> {

    /* renamed from: d  reason: collision with root package name */
    private final T[] f861d;

    /* renamed from: e  reason: collision with root package name */
    private int f862e;

    public ArrayIterator(T[] array) {
        Intrinsics.f(array, "array");
        this.f861d = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f862e < this.f861d.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f861d;
            int i2 = this.f862e;
            this.f862e = i2 + 1;
            return tArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f862e--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
