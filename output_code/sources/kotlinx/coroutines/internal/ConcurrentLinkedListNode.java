package kotlinx.coroutines.internal;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1101d = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1102e = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public ConcurrentLinkedListNode(N n2) {
        this._prev = n2;
    }

    private final N c() {
        N g2 = g();
        while (g2 != null && g2.h()) {
            g2 = (N) f1102e.get(g2);
        }
        return g2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    private final N d() {
        N e2 = e();
        Intrinsics.c(e2);
        while (e2.h()) {
            ?? e3 = e2.e();
            if (e3 == 0) {
                return e2;
            }
            e2 = e3;
        }
        return e2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object f() {
        return f1101d.get(this);
    }

    public final void b() {
        f1102e.lazySet(this, null);
    }

    public final N e() {
        Object f2 = f();
        if (f2 == ConcurrentLinkedListKt.a()) {
            return null;
        }
        return (N) f2;
    }

    public final N g() {
        return (N) f1102e.get(this);
    }

    public abstract boolean h();

    public final boolean i() {
        return e() == null;
    }

    public final boolean j() {
        return a.a(f1101d, this, (Object) null, ConcurrentLinkedListKt.a());
    }

    public final void k() {
        Object obj;
        N n2;
        if (i()) {
            return;
        }
        while (true) {
            N c2 = c();
            N d2 = d();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1102e;
            do {
                obj = atomicReferenceFieldUpdater.get(d2);
                if (((ConcurrentLinkedListNode) obj) == null) {
                    n2 = null;
                } else {
                    n2 = c2;
                }
            } while (!a.a(atomicReferenceFieldUpdater, d2, obj, n2));
            if (c2 != null) {
                f1101d.set(c2, d2);
            }
            if (!d2.h() || d2.i()) {
                if (c2 == null || !c2.h()) {
                    return;
                }
            }
        }
    }

    public final boolean l(N n2) {
        return a.a(f1101d, this, (Object) null, n2);
    }
}
