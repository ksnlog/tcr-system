package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {

    /* renamed from: g  reason: collision with root package name */
    public static final Companion f779g = new Companion(null);

    /* renamed from: h  reason: collision with root package name */
    private static final Object[] f780h = new Object[0];

    /* renamed from: d  reason: collision with root package name */
    private int f781d;

    /* renamed from: e  reason: collision with root package name */
    private Object[] f782e = f780h;

    /* renamed from: f  reason: collision with root package name */
    private int f783f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(int i2, int i3) {
            int i4 = i2 + (i2 >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            return i4 - 2147483639 > 0 ? i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i4;
        }
    }

    private final void c(int i2, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.f782e.length;
        while (i2 < length && it.hasNext()) {
            this.f782e[i2] = it.next();
            i2++;
        }
        int i3 = this.f781d;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.f782e[i4] = it.next();
        }
        this.f783f = size() + collection.size();
    }

    private final void d(int i2) {
        Object[] objArr = new Object[i2];
        Object[] objArr2 = this.f782e;
        ArraysKt___ArraysJvmKt.e(objArr2, objArr, 0, this.f781d, objArr2.length);
        Object[] objArr3 = this.f782e;
        int length = objArr3.length;
        int i3 = this.f781d;
        ArraysKt___ArraysJvmKt.e(objArr3, objArr, length - i3, 0, i3);
        this.f781d = 0;
        this.f782e = objArr;
    }

    private final int e(int i2) {
        return i2 == 0 ? ArraysKt___ArraysKt.m(this.f782e) : i2 - 1;
    }

    private final void f(int i2) {
        int a2;
        if (i2 >= 0) {
            Object[] objArr = this.f782e;
            if (i2 <= objArr.length) {
                return;
            }
            if (objArr == f780h) {
                a2 = RangesKt___RangesKt.a(i2, 10);
                this.f782e = new Object[a2];
                return;
            }
            d(f779g.a(objArr.length, i2));
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    private final int h(int i2) {
        if (i2 == ArraysKt___ArraysKt.m(this.f782e)) {
            return 0;
        }
        return i2 + 1;
    }

    private final int i(int i2) {
        return i2 < 0 ? i2 + this.f782e.length : i2;
    }

    private final int j(int i2) {
        Object[] objArr = this.f782e;
        return i2 >= objArr.length ? i2 - objArr.length : i2;
    }

    @Override // kotlin.collections.AbstractMutableList
    public int a() {
        return this.f783f;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        addLast(e2);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        Intrinsics.f(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        f(size() + elements.size());
        c(j(this.f781d + size()), elements);
        return true;
    }

    public final void addFirst(E e2) {
        f(size() + 1);
        int e3 = e(this.f781d);
        this.f781d = e3;
        this.f782e[e3] = e2;
        this.f783f = size() + 1;
    }

    public final void addLast(E e2) {
        f(size() + 1);
        this.f782e[j(this.f781d + size())] = e2;
        this.f783f = size() + 1;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E b(int i2) {
        AbstractList.f770d.b(i2, size());
        if (i2 == CollectionsKt.g(this)) {
            return removeLast();
        }
        if (i2 == 0) {
            return removeFirst();
        }
        int j2 = j(this.f781d + i2);
        E e2 = (E) this.f782e[j2];
        if (i2 < (size() >> 1)) {
            int i3 = this.f781d;
            if (j2 >= i3) {
                Object[] objArr = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr, objArr, i3 + 1, i3, j2);
            } else {
                Object[] objArr2 = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr2, objArr2, 1, 0, j2);
                Object[] objArr3 = this.f782e;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i4 = this.f781d;
                ArraysKt___ArraysJvmKt.e(objArr3, objArr3, i4 + 1, i4, objArr3.length - 1);
            }
            Object[] objArr4 = this.f782e;
            int i5 = this.f781d;
            objArr4[i5] = null;
            this.f781d = h(i5);
        } else {
            int j3 = j(this.f781d + CollectionsKt.g(this));
            if (j2 <= j3) {
                Object[] objArr5 = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr5, objArr5, j2, j2 + 1, j3 + 1);
            } else {
                Object[] objArr6 = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr6, objArr6, j2, j2 + 1, objArr6.length);
                Object[] objArr7 = this.f782e;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt___ArraysJvmKt.e(objArr7, objArr7, 0, 1, j3 + 1);
            }
            this.f782e[j3] = null;
        }
        this.f783f = size() - 1;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int j2 = j(this.f781d + size());
        int i2 = this.f781d;
        if (i2 < j2) {
            ArraysKt___ArraysJvmKt.i(this.f782e, null, i2, j2);
        } else if (!isEmpty()) {
            Object[] objArr = this.f782e;
            ArraysKt___ArraysJvmKt.i(objArr, null, this.f781d, objArr.length);
            ArraysKt___ArraysJvmKt.i(this.f782e, null, 0, j2);
        }
        this.f781d = 0;
        this.f783f = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final E g() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.f782e[this.f781d];
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i2) {
        AbstractList.f770d.b(i2, size());
        return (E) this.f782e[j(this.f781d + i2)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i2;
        int j2 = j(this.f781d + size());
        int i3 = this.f781d;
        if (i3 < j2) {
            while (i3 < j2) {
                if (Intrinsics.a(obj, this.f782e[i3])) {
                    i2 = this.f781d;
                } else {
                    i3++;
                }
            }
            return -1;
        } else if (i3 >= j2) {
            int length = this.f782e.length;
            while (true) {
                if (i3 < length) {
                    if (Intrinsics.a(obj, this.f782e[i3])) {
                        i2 = this.f781d;
                        break;
                    }
                    i3++;
                } else {
                    for (int i4 = 0; i4 < j2; i4++) {
                        if (Intrinsics.a(obj, this.f782e[i4])) {
                            i3 = i4 + this.f782e.length;
                            i2 = this.f781d;
                        }
                    }
                    return -1;
                }
            }
        } else {
            return -1;
        }
        return i3 - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E k() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int m2;
        int i2;
        int j2 = j(this.f781d + size());
        int i3 = this.f781d;
        if (i3 < j2) {
            m2 = j2 - 1;
            if (i3 <= m2) {
                while (!Intrinsics.a(obj, this.f782e[m2])) {
                    if (m2 != i3) {
                        m2--;
                    }
                }
                i2 = this.f781d;
                return m2 - i2;
            }
            return -1;
        }
        if (i3 > j2) {
            int i4 = j2 - 1;
            while (true) {
                if (-1 < i4) {
                    if (Intrinsics.a(obj, this.f782e[i4])) {
                        m2 = i4 + this.f782e.length;
                        i2 = this.f781d;
                        break;
                    }
                    i4--;
                } else {
                    m2 = ArraysKt___ArraysKt.m(this.f782e);
                    int i5 = this.f781d;
                    if (i5 <= m2) {
                        while (!Intrinsics.a(obj, this.f782e[m2])) {
                            if (m2 != i5) {
                                m2--;
                            }
                        }
                        i2 = this.f781d;
                    }
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        boolean z2;
        int j2;
        Intrinsics.f(elements, "elements");
        boolean z3 = false;
        z3 = false;
        z3 = false;
        if (!isEmpty()) {
            if (this.f782e.length == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                int j3 = j(this.f781d + size());
                int i2 = this.f781d;
                if (i2 < j3) {
                    j2 = i2;
                    while (i2 < j3) {
                        Object obj = this.f782e[i2];
                        if (!elements.contains(obj)) {
                            this.f782e[j2] = obj;
                            j2++;
                        } else {
                            z3 = true;
                        }
                        i2++;
                    }
                    ArraysKt___ArraysJvmKt.i(this.f782e, null, j2, j3);
                } else {
                    int length = this.f782e.length;
                    int i3 = i2;
                    boolean z4 = false;
                    while (i2 < length) {
                        Object[] objArr = this.f782e;
                        Object obj2 = objArr[i2];
                        objArr[i2] = null;
                        if (!elements.contains(obj2)) {
                            this.f782e[i3] = obj2;
                            i3++;
                        } else {
                            z4 = true;
                        }
                        i2++;
                    }
                    j2 = j(i3);
                    for (int i4 = 0; i4 < j3; i4++) {
                        Object[] objArr2 = this.f782e;
                        Object obj3 = objArr2[i4];
                        objArr2[i4] = null;
                        if (!elements.contains(obj3)) {
                            this.f782e[j2] = obj3;
                            j2 = h(j2);
                        } else {
                            z4 = true;
                        }
                    }
                    z3 = z4;
                }
                if (z3) {
                    this.f783f = i(j2 - this.f781d);
                }
            }
        }
        return z3;
    }

    public final E removeFirst() {
        if (!isEmpty()) {
            Object[] objArr = this.f782e;
            int i2 = this.f781d;
            E e2 = (E) objArr[i2];
            objArr[i2] = null;
            this.f781d = h(i2);
            this.f783f = size() - 1;
            return e2;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final E removeLast() {
        if (!isEmpty()) {
            int j2 = j(this.f781d + CollectionsKt.g(this));
            Object[] objArr = this.f782e;
            E e2 = (E) objArr[j2];
            objArr[j2] = null;
            this.f783f = size() - 1;
            return e2;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> elements) {
        boolean z2;
        int j2;
        Intrinsics.f(elements, "elements");
        boolean z3 = false;
        z3 = false;
        z3 = false;
        if (!isEmpty()) {
            if (this.f782e.length == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                int j3 = j(this.f781d + size());
                int i2 = this.f781d;
                if (i2 < j3) {
                    j2 = i2;
                    while (i2 < j3) {
                        Object obj = this.f782e[i2];
                        if (elements.contains(obj)) {
                            this.f782e[j2] = obj;
                            j2++;
                        } else {
                            z3 = true;
                        }
                        i2++;
                    }
                    ArraysKt___ArraysJvmKt.i(this.f782e, null, j2, j3);
                } else {
                    int length = this.f782e.length;
                    int i3 = i2;
                    boolean z4 = false;
                    while (i2 < length) {
                        Object[] objArr = this.f782e;
                        Object obj2 = objArr[i2];
                        objArr[i2] = null;
                        if (elements.contains(obj2)) {
                            this.f782e[i3] = obj2;
                            i3++;
                        } else {
                            z4 = true;
                        }
                        i2++;
                    }
                    j2 = j(i3);
                    for (int i4 = 0; i4 < j3; i4++) {
                        Object[] objArr2 = this.f782e;
                        Object obj3 = objArr2[i4];
                        objArr2[i4] = null;
                        if (elements.contains(obj3)) {
                            this.f782e[j2] = obj3;
                            j2 = h(j2);
                        } else {
                            z4 = true;
                        }
                    }
                    z3 = z4;
                }
                if (z3) {
                    this.f783f = i(j2 - this.f781d);
                }
            }
        }
        return z3;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i2, E e2) {
        AbstractList.f770d.b(i2, size());
        int j2 = j(this.f781d + i2);
        Object[] objArr = this.f782e;
        E e3 = (E) objArr[j2];
        objArr[j2] = e2;
        return e3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] array) {
        Intrinsics.f(array, "array");
        if (array.length < size()) {
            array = (T[]) ArraysKt__ArraysJVMKt.a(array, size());
        }
        int j2 = j(this.f781d + size());
        int i2 = this.f781d;
        if (i2 < j2) {
            ArraysKt___ArraysJvmKt.g(this.f782e, array, 0, i2, j2, 2, null);
        } else if (!isEmpty()) {
            Object[] objArr = this.f782e;
            ArraysKt___ArraysJvmKt.e(objArr, array, 0, this.f781d, objArr.length);
            Object[] objArr2 = this.f782e;
            ArraysKt___ArraysJvmKt.e(objArr2, array, objArr2.length - this.f781d, 0, j2);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, E e2) {
        AbstractList.f770d.c(i2, size());
        if (i2 == size()) {
            addLast(e2);
        } else if (i2 == 0) {
            addFirst(e2);
        } else {
            f(size() + 1);
            int j2 = j(this.f781d + i2);
            if (i2 < ((size() + 1) >> 1)) {
                int e3 = e(j2);
                int e4 = e(this.f781d);
                int i3 = this.f781d;
                if (e3 >= i3) {
                    Object[] objArr = this.f782e;
                    objArr[e4] = objArr[i3];
                    ArraysKt___ArraysJvmKt.e(objArr, objArr, i3, i3 + 1, e3 + 1);
                } else {
                    Object[] objArr2 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr2, objArr2, i3 - 1, i3, objArr2.length);
                    Object[] objArr3 = this.f782e;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    ArraysKt___ArraysJvmKt.e(objArr3, objArr3, 0, 1, e3 + 1);
                }
                this.f782e[e3] = e2;
                this.f781d = e4;
            } else {
                int j3 = j(this.f781d + size());
                if (j2 < j3) {
                    Object[] objArr4 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr4, objArr4, j2 + 1, j2, j3);
                } else {
                    Object[] objArr5 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr5, objArr5, 1, 0, j3);
                    Object[] objArr6 = this.f782e;
                    objArr6[0] = objArr6[objArr6.length - 1];
                    ArraysKt___ArraysJvmKt.e(objArr6, objArr6, j2 + 1, j2, objArr6.length - 1);
                }
                this.f782e[j2] = e2;
            }
            this.f783f = size() + 1;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection<? extends E> elements) {
        Intrinsics.f(elements, "elements");
        AbstractList.f770d.c(i2, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (i2 == size()) {
            return addAll(elements);
        }
        f(size() + elements.size());
        int j2 = j(this.f781d + size());
        int j3 = j(this.f781d + i2);
        int size = elements.size();
        if (i2 < ((size() + 1) >> 1)) {
            int i3 = this.f781d;
            int i4 = i3 - size;
            if (j3 < i3) {
                Object[] objArr = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr, objArr, i4, i3, objArr.length);
                if (size >= j3) {
                    Object[] objArr2 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr2, objArr2, objArr2.length - size, 0, j3);
                } else {
                    Object[] objArr3 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr4, objArr4, 0, size, j3);
                }
            } else if (i4 >= 0) {
                Object[] objArr5 = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr5, objArr5, i4, i3, j3);
            } else {
                Object[] objArr6 = this.f782e;
                i4 += objArr6.length;
                int i5 = j3 - i3;
                int length = objArr6.length - i4;
                if (length >= i5) {
                    ArraysKt___ArraysJvmKt.e(objArr6, objArr6, i4, i3, j3);
                } else {
                    ArraysKt___ArraysJvmKt.e(objArr6, objArr6, i4, i3, i3 + length);
                    Object[] objArr7 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr7, objArr7, 0, this.f781d + length, j3);
                }
            }
            this.f781d = i4;
            c(i(j3 - size), elements);
        } else {
            int i6 = j3 + size;
            if (j3 < j2) {
                int i7 = size + j2;
                Object[] objArr8 = this.f782e;
                if (i7 <= objArr8.length) {
                    ArraysKt___ArraysJvmKt.e(objArr8, objArr8, i6, j3, j2);
                } else if (i6 >= objArr8.length) {
                    ArraysKt___ArraysJvmKt.e(objArr8, objArr8, i6 - objArr8.length, j3, j2);
                } else {
                    int length2 = j2 - (i7 - objArr8.length);
                    ArraysKt___ArraysJvmKt.e(objArr8, objArr8, 0, length2, j2);
                    Object[] objArr9 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr9, objArr9, i6, j3, length2);
                }
            } else {
                Object[] objArr10 = this.f782e;
                ArraysKt___ArraysJvmKt.e(objArr10, objArr10, size, 0, j2);
                Object[] objArr11 = this.f782e;
                if (i6 >= objArr11.length) {
                    ArraysKt___ArraysJvmKt.e(objArr11, objArr11, i6 - objArr11.length, j3, objArr11.length);
                } else {
                    ArraysKt___ArraysJvmKt.e(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.f782e;
                    ArraysKt___ArraysJvmKt.e(objArr12, objArr12, i6, j3, objArr12.length - size);
                }
            }
            c(j3, elements);
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
