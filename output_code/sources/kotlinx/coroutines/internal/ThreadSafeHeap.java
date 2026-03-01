package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f1163b = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size");
    private volatile int _size;

    /* renamed from: a  reason: collision with root package name */
    private T[] f1164a;

    private final T[] f() {
        T[] tArr = this.f1164a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.f1164a = tArr2;
            return tArr2;
        } else if (c() >= tArr.length) {
            Object[] copyOf = Arrays.copyOf(tArr, c() * 2);
            Intrinsics.e(copyOf, "copyOf(this, newSize)");
            T[] tArr3 = (T[]) ((ThreadSafeHeapNode[]) copyOf);
            this.f1164a = tArr3;
            return tArr3;
        } else {
            return tArr;
        }
    }

    private final void j(int i2) {
        f1163b.set(this, i2);
    }

    private final void k(int i2) {
        while (true) {
            int i3 = (i2 * 2) + 1;
            if (i3 >= c()) {
                return;
            }
            T[] tArr = this.f1164a;
            Intrinsics.c(tArr);
            int i4 = i3 + 1;
            if (i4 < c()) {
                T t2 = tArr[i4];
                Intrinsics.c(t2);
                T t3 = tArr[i3];
                Intrinsics.c(t3);
                if (((Comparable) t2).compareTo(t3) < 0) {
                    i3 = i4;
                }
            }
            T t4 = tArr[i2];
            Intrinsics.c(t4);
            T t5 = tArr[i3];
            Intrinsics.c(t5);
            if (((Comparable) t4).compareTo(t5) <= 0) {
                return;
            }
            m(i2, i3);
            i2 = i3;
        }
    }

    private final void l(int i2) {
        while (i2 > 0) {
            T[] tArr = this.f1164a;
            Intrinsics.c(tArr);
            int i3 = (i2 - 1) / 2;
            T t2 = tArr[i3];
            Intrinsics.c(t2);
            T t3 = tArr[i2];
            Intrinsics.c(t3);
            if (((Comparable) t2).compareTo(t3) <= 0) {
                return;
            }
            m(i2, i3);
            i2 = i3;
        }
    }

    private final void m(int i2, int i3) {
        T[] tArr = this.f1164a;
        Intrinsics.c(tArr);
        T t2 = tArr[i3];
        Intrinsics.c(t2);
        T t3 = tArr[i2];
        Intrinsics.c(t3);
        tArr[i2] = t2;
        tArr[i3] = t3;
        t2.setIndex(i2);
        t3.setIndex(i3);
    }

    public final void a(T t2) {
        t2.b(this);
        T[] f2 = f();
        int c2 = c();
        j(c2 + 1);
        f2[c2] = t2;
        t2.setIndex(c2);
        l(c2);
    }

    public final T b() {
        T[] tArr = this.f1164a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    public final int c() {
        return f1163b.get(this);
    }

    public final boolean d() {
        return c() == 0;
    }

    public final T e() {
        T b2;
        synchronized (this) {
            b2 = b();
        }
        return b2;
    }

    public final boolean g(T t2) {
        boolean z2;
        synchronized (this) {
            if (t2.c() == null) {
                z2 = false;
            } else {
                h(t2.getIndex());
                z2 = true;
            }
        }
        return z2;
    }

    public final T h(int i2) {
        T[] tArr = this.f1164a;
        Intrinsics.c(tArr);
        j(c() - 1);
        if (i2 < c()) {
            m(i2, c());
            int i3 = (i2 - 1) / 2;
            if (i2 > 0) {
                T t2 = tArr[i2];
                Intrinsics.c(t2);
                T t3 = tArr[i3];
                Intrinsics.c(t3);
                if (((Comparable) t2).compareTo(t3) < 0) {
                    m(i2, i3);
                    l(i3);
                }
            }
            k(i2);
        }
        T t4 = tArr[c()];
        Intrinsics.c(t4);
        t4.b(null);
        t4.setIndex(-1);
        tArr[c()] = null;
        return t4;
    }

    public final T i() {
        T t2;
        synchronized (this) {
            if (c() > 0) {
                t2 = h(0);
            } else {
                t2 = null;
            }
        }
        return t2;
    }
}
