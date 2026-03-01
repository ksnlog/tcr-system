package kotlinx.coroutines.internal;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LockFreeTaskQueueCore<E> {

    /* renamed from: e  reason: collision with root package name */
    public static final Companion f1129e = new Companion(null);

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1130f = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1131g = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");

    /* renamed from: h  reason: collision with root package name */
    public static final Symbol f1132h = new Symbol("REMOVE_FROZEN");
    private volatile Object _next;
    private volatile long _state;

    /* renamed from: a  reason: collision with root package name */
    private final int f1133a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f1134b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1135c;

    /* renamed from: d  reason: collision with root package name */
    private final AtomicReferenceArray f1136d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(long j2) {
            return (j2 & 2305843009213693952L) != 0 ? 2 : 1;
        }

        public final long b(long j2, int i2) {
            return d(j2, 1073741823L) | (i2 << 0);
        }

        public final long c(long j2, int i2) {
            return d(j2, 1152921503533105152L) | (i2 << 30);
        }

        public final long d(long j2, long j3) {
            return j2 & (j3 ^ (-1));
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Placeholder {

        /* renamed from: a  reason: collision with root package name */
        public final int f1137a;

        public Placeholder(int i2) {
            this.f1137a = i2;
        }
    }

    public LockFreeTaskQueueCore(int i2, boolean z2) {
        boolean z3;
        this.f1133a = i2;
        this.f1134b = z2;
        int i3 = i2 - 1;
        this.f1135c = i3;
        this.f1136d = new AtomicReferenceArray(i2);
        boolean z4 = false;
        if (i3 <= 1073741823) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            if ((i2 & i3) == 0 ? true : z4) {
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LockFreeTaskQueueCore<E> b(long j2) {
        LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = new LockFreeTaskQueueCore<>(this.f1133a * 2, this.f1134b);
        int i2 = (int) ((1073741823 & j2) >> 0);
        int i3 = (int) ((1152921503533105152L & j2) >> 30);
        while (true) {
            int i4 = this.f1135c;
            if ((i2 & i4) != (i3 & i4)) {
                Object obj = this.f1136d.get(i4 & i2);
                if (obj == null) {
                    obj = new Placeholder(i2);
                }
                lockFreeTaskQueueCore.f1136d.set(lockFreeTaskQueueCore.f1135c & i2, obj);
                i2++;
            } else {
                f1131g.set(lockFreeTaskQueueCore, f1129e.d(j2, 1152921504606846976L));
                return lockFreeTaskQueueCore;
            }
        }
    }

    private final LockFreeTaskQueueCore<E> c(long j2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1130f;
        while (true) {
            LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicReferenceFieldUpdater.get(this);
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            a.a(f1130f, this, (Object) null, b(j2));
        }
    }

    private final LockFreeTaskQueueCore<E> e(int i2, E e2) {
        Object obj = this.f1136d.get(this.f1135c & i2);
        if ((obj instanceof Placeholder) && ((Placeholder) obj).f1137a == i2) {
            this.f1136d.set(i2 & this.f1135c, e2);
            return this;
        }
        return null;
    }

    private final long h() {
        long j2;
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1131g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 1152921504606846976L) != 0) {
                return j2;
            }
            j3 = j2 | 1152921504606846976L;
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j3));
        return j3;
    }

    private final LockFreeTaskQueueCore<E> k(int i2, int i3) {
        long j2;
        int i4;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1131g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            i4 = (int) ((1073741823 & j2) >> 0);
            if ((1152921504606846976L & j2) != 0) {
                return i();
            }
        } while (!f1131g.compareAndSet(this, j2, f1129e.b(j2, i3)));
        this.f1136d.set(this.f1135c & i4, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(E r14) {
        /*
            r13 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f1131g
        L2:
            long r3 = r0.get(r13)
            r1 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r1 = r1 & r3
            r7 = 0
            int r5 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r5 == 0) goto L16
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r14 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f1129e
            int r14 = r14.a(r3)
            return r14
        L16:
            r1 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r1 = r1 & r3
            r9 = 0
            long r1 = r1 >> r9
            int r2 = (int) r1
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r5 = r5 & r3
            r1 = 30
            long r5 = r5 >> r1
            int r10 = (int) r5
            int r11 = r13.f1135c
            int r1 = r10 + 2
            r1 = r1 & r11
            r5 = r2 & r11
            r6 = 1
            if (r1 != r5) goto L32
            return r6
        L32:
            boolean r1 = r13.f1134b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r1 != 0) goto L51
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r13.f1136d
            r12 = r10 & r11
            java.lang.Object r1 = r1.get(r12)
            if (r1 == 0) goto L51
            int r1 = r13.f1133a
            r3 = 1024(0x400, float:1.435E-42)
            if (r1 < r3) goto L50
            int r10 = r10 - r2
            r2 = r10 & r5
            int r1 = r1 >> 1
            if (r2 <= r1) goto L2
        L50:
            return r6
        L51:
            int r1 = r10 + 1
            r1 = r1 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f1131g
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Companion r5 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f1129e
            long r5 = r5.c(r3, r1)
            r1 = r2
            r2 = r13
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto L2
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r13.f1136d
            r1 = r10 & r11
            r0.set(r1, r14)
            r0 = r13
        L6c:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = kotlinx.coroutines.internal.LockFreeTaskQueueCore.f1131g
            long r1 = r1.get(r0)
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            int r3 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r3 == 0) goto L83
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = r0.i()
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = r0.e(r10, r14)
            if (r0 != 0) goto L6c
        L83:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeTaskQueueCore.a(java.lang.Object):int");
    }

    public final boolean d() {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1131g;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j2 | 2305843009213693952L));
        return true;
    }

    public final int f() {
        long j2 = f1131g.get(this);
        return 1073741823 & (((int) ((j2 & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & j2) >> 0)));
    }

    public final boolean g() {
        long j2 = f1131g.get(this);
        return ((int) ((1073741823 & j2) >> 0)) == ((int) ((j2 & 1152921503533105152L) >> 30));
    }

    public final LockFreeTaskQueueCore<E> i() {
        return c(h());
    }

    public final Object j() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1131g;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            if ((1152921504606846976L & j2) != 0) {
                return f1132h;
            }
            int i2 = (int) ((1073741823 & j2) >> 0);
            int i3 = (int) ((1152921503533105152L & j2) >> 30);
            int i4 = this.f1135c;
            if ((i3 & i4) == (i2 & i4)) {
                return null;
            }
            Object obj = this.f1136d.get(i4 & i2);
            if (obj == null) {
                if (this.f1134b) {
                    return null;
                }
            } else if (obj instanceof Placeholder) {
                return null;
            } else {
                int i5 = (i2 + 1) & 1073741823;
                if (f1131g.compareAndSet(this, j2, f1129e.b(j2, i5))) {
                    this.f1136d.set(this.f1135c & i2, null);
                    return obj;
                } else if (this.f1134b) {
                    LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                    do {
                        lockFreeTaskQueueCore = lockFreeTaskQueueCore.k(i2, i5);
                    } while (lockFreeTaskQueueCore != null);
                    return obj;
                }
            }
        }
    }
}
