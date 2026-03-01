package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    /* renamed from: d  reason: collision with root package name */
    public static final Companion f770d = new Companion(null);

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(int i2, int i3, int i4) {
            if (i2 >= 0 && i3 <= i4) {
                if (i2 <= i3) {
                    return;
                }
                throw new IllegalArgumentException("startIndex: " + i2 + " > endIndex: " + i3);
            }
            throw new IndexOutOfBoundsException("startIndex: " + i2 + ", endIndex: " + i3 + ", size: " + i4);
        }

        public final void b(int i2, int i3) {
            if (i2 < 0 || i2 >= i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void c(int i2, int i3) {
            if (i2 < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void d(int i2, int i3, int i4) {
            if (i2 >= 0 && i3 <= i4) {
                if (i2 <= i3) {
                    return;
                }
                throw new IllegalArgumentException("fromIndex: " + i2 + " > toIndex: " + i3);
            }
            throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
        }

        public final boolean e(Collection<?> c2, Collection<?> other) {
            Intrinsics.f(c2, "c");
            Intrinsics.f(other, "other");
            if (c2.size() != other.size()) {
                return false;
            }
            Iterator<?> it = other.iterator();
            Iterator<?> it2 = c2.iterator();
            while (it2.hasNext()) {
                if (!Intrinsics.a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int f(Collection<?> c2) {
            int i2;
            Intrinsics.f(c2, "c");
            int i3 = 1;
            for (Object obj : c2) {
                int i4 = i3 * 31;
                if (obj != null) {
                    i2 = obj.hashCode();
                } else {
                    i2 = 0;
                }
                i3 = i4 + i2;
            }
            return i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class IteratorImpl implements Iterator<E> {

        /* renamed from: d  reason: collision with root package name */
        private int f771d;

        public IteratorImpl() {
        }

        protected final int a() {
            return this.f771d;
        }

        protected final void b(int i2) {
            this.f771d = i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f771d < AbstractList.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                AbstractList<E> abstractList = AbstractList.this;
                int i2 = this.f771d;
                this.f771d = i2 + 1;
                return abstractList.get(i2);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private class ListIteratorImpl extends AbstractList<E>.IteratorImpl implements ListIterator<E> {
        public ListIteratorImpl(int i2) {
            super();
            AbstractList.f770d.c(i2, AbstractList.this.size());
            b(i2);
        }

        @Override // java.util.ListIterator
        public void add(E e2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return a() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return a();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (hasPrevious()) {
                AbstractList<E> abstractList = AbstractList.this;
                b(a() - 1);
                return abstractList.get(a());
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return a() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static final class SubList<E> extends AbstractList<E> implements RandomAccess {

        /* renamed from: e  reason: collision with root package name */
        private final AbstractList<E> f774e;

        /* renamed from: f  reason: collision with root package name */
        private final int f775f;

        /* renamed from: g  reason: collision with root package name */
        private int f776g;

        /* JADX WARN: Multi-variable type inference failed */
        public SubList(AbstractList<? extends E> list, int i2, int i3) {
            Intrinsics.f(list, "list");
            this.f774e = list;
            this.f775f = i2;
            AbstractList.f770d.d(i2, i3, list.size());
            this.f776g = i3 - i2;
        }

        @Override // kotlin.collections.AbstractCollection
        public int a() {
            return this.f776g;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public E get(int i2) {
            AbstractList.f770d.b(i2, this.f776g);
            return this.f774e.get(this.f775f + i2);
        }
    }

    @Override // java.util.List
    public void add(int i2, E e2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i2, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        return f770d.e(this, (Collection) obj);
    }

    @Override // java.util.List
    public abstract E get(int i2);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f770d.f(this);
    }

    @Override // java.util.List
    public int indexOf(E e2) {
        int i2 = 0;
        for (E e3 : this) {
            if (!Intrinsics.a(e3, e2)) {
                i2++;
            } else {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    @Override // java.util.List
    public int lastIndexOf(E e2) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Intrinsics.a(listIterator.previous(), e2)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(0);
    }

    @Override // java.util.List
    public E remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i2, E e2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i2, int i3) {
        return new SubList(this, i2, i3);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i2) {
        return new ListIteratorImpl(i2);
    }
}
