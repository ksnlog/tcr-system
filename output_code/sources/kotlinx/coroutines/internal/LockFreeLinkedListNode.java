package kotlinx.coroutines.internal;

import androidx.concurrent.futures.a;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LockFreeLinkedListNode {

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1123d = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1124e = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1125f = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {

        /* renamed from: b  reason: collision with root package name */
        public final LockFreeLinkedListNode f1126b;

        /* renamed from: c  reason: collision with root package name */
        public LockFreeLinkedListNode f1127c;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.f1126b = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: e */
        public void b(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            boolean z2;
            LockFreeLinkedListNode lockFreeLinkedListNode2;
            if (obj == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                lockFreeLinkedListNode2 = this.f1126b;
            } else {
                lockFreeLinkedListNode2 = this.f1127c;
            }
            if (lockFreeLinkedListNode2 != null && a.a(LockFreeLinkedListNode.f1123d, lockFreeLinkedListNode, this, lockFreeLinkedListNode2) && z2) {
                LockFreeLinkedListNode lockFreeLinkedListNode3 = this.f1126b;
                LockFreeLinkedListNode lockFreeLinkedListNode4 = this.f1127c;
                Intrinsics.c(lockFreeLinkedListNode4);
                lockFreeLinkedListNode3.m(lockFreeLinkedListNode4);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
        if (androidx.concurrent.futures.a.a(r4, r3, r2, ((kotlinx.coroutines.internal.Removed) r5).f1147a) != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlinx.coroutines.internal.LockFreeLinkedListNode k(kotlinx.coroutines.internal.OpDescriptor r9) {
        /*
            r8 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f1124e
            java.lang.Object r0 = r0.get(r8)
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        La:
            r3 = r1
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f1123d
            java.lang.Object r5 = r4.get(r2)
            if (r5 != r8) goto L20
            if (r0 != r2) goto L16
            return r2
        L16:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f1124e
            boolean r0 = androidx.concurrent.futures.a.a(r1, r8, r0, r2)
            if (r0 != 0) goto L1f
            goto L0
        L1f:
            return r2
        L20:
            boolean r6 = r8.q()
            if (r6 == 0) goto L27
            return r1
        L27:
            if (r5 != r9) goto L2a
            return r2
        L2a:
            boolean r6 = r5 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r6 == 0) goto L34
            kotlinx.coroutines.internal.OpDescriptor r5 = (kotlinx.coroutines.internal.OpDescriptor) r5
            r5.a(r2)
            goto L0
        L34:
            boolean r6 = r5 instanceof kotlinx.coroutines.internal.Removed
            if (r6 == 0) goto L50
            if (r3 == 0) goto L47
            kotlinx.coroutines.internal.Removed r5 = (kotlinx.coroutines.internal.Removed) r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = r5.f1147a
            boolean r2 = androidx.concurrent.futures.a.a(r4, r3, r2, r5)
            if (r2 != 0) goto L45
            goto L0
        L45:
            r2 = r3
            goto La
        L47:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f1124e
            java.lang.Object r2 = r4.get(r2)
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto Lb
        L50:
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            kotlin.jvm.internal.Intrinsics.d(r5, r3)
            r3 = r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r7 = r3
            r3 = r2
            r2 = r7
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.k(kotlinx.coroutines.internal.OpDescriptor):kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    private final LockFreeLinkedListNode l(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.q()) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) f1124e.get(lockFreeLinkedListNode);
        }
        return lockFreeLinkedListNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1124e;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) atomicReferenceFieldUpdater.get(lockFreeLinkedListNode);
            if (n() != lockFreeLinkedListNode) {
                return;
            }
        } while (!a.a(f1124e, lockFreeLinkedListNode, lockFreeLinkedListNode2, this));
        if (q()) {
            lockFreeLinkedListNode.k(null);
        }
    }

    private final Removed t() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1125f;
        Removed removed = (Removed) atomicReferenceFieldUpdater.get(this);
        if (removed == null) {
            Removed removed2 = new Removed(this);
            atomicReferenceFieldUpdater.lazySet(this, removed2);
            return removed2;
        }
        return removed;
    }

    public final boolean j(LockFreeLinkedListNode lockFreeLinkedListNode) {
        f1124e.lazySet(lockFreeLinkedListNode, this);
        f1123d.lazySet(lockFreeLinkedListNode, this);
        while (n() == this) {
            if (a.a(f1123d, this, this, lockFreeLinkedListNode)) {
                lockFreeLinkedListNode.m(this);
                return true;
            }
        }
        return false;
    }

    public final Object n() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1123d;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).a(this);
        }
    }

    public final LockFreeLinkedListNode o() {
        return LockFreeLinkedListKt.b(n());
    }

    public final LockFreeLinkedListNode p() {
        LockFreeLinkedListNode k2 = k(null);
        return k2 == null ? l((LockFreeLinkedListNode) f1124e.get(this)) : k2;
    }

    public boolean q() {
        return n() instanceof Removed;
    }

    public boolean r() {
        return s() == null;
    }

    public final LockFreeLinkedListNode s() {
        Object n2;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        do {
            n2 = n();
            if (n2 instanceof Removed) {
                return ((Removed) n2).f1147a;
            }
            if (n2 == this) {
                return (LockFreeLinkedListNode) n2;
            }
            Intrinsics.d(n2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            lockFreeLinkedListNode = (LockFreeLinkedListNode) n2;
        } while (!a.a(f1123d, this, n2, lockFreeLinkedListNode.t()));
        lockFreeLinkedListNode.k(null);
        return null;
    }

    public String toString() {
        return new PropertyReference0Impl(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.reflect.KProperty0
            public Object get() {
                return DebugStringsKt.a(this.f865e);
            }
        } + '@' + DebugStringsKt.b(this);
    }

    public final int u(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, CondAddOp condAddOp) {
        f1124e.lazySet(lockFreeLinkedListNode, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1123d;
        atomicReferenceFieldUpdater.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        condAddOp.f1127c = lockFreeLinkedListNode2;
        if (!a.a(atomicReferenceFieldUpdater, this, lockFreeLinkedListNode2, condAddOp)) {
            return 0;
        }
        if (condAddOp.a(this) == null) {
            return 1;
        }
        return 2;
    }
}
