package kotlinx.coroutines;

import androidx.concurrent.futures.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class JobSupport implements Job, ChildJob, ParentJob {

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f991d = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f992e = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class ChildCompletion extends JobNode {

        /* renamed from: h  reason: collision with root package name */
        private final JobSupport f995h;

        /* renamed from: i  reason: collision with root package name */
        private final Finishing f996i;

        /* renamed from: j  reason: collision with root package name */
        private final ChildHandleNode f997j;

        /* renamed from: k  reason: collision with root package name */
        private final Object f998k;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.f995h = jobSupport;
            this.f996i = finishing;
            this.f997j = childHandleNode;
            this.f998k = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit f(Throwable th) {
            v(th);
            return Unit.f763a;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void v(Throwable th) {
            this.f995h.F(this.f996i, this.f997j, this.f998k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Finishing implements Incomplete {

        /* renamed from: e  reason: collision with root package name */
        private static final AtomicIntegerFieldUpdater f999e = AtomicIntegerFieldUpdater.newUpdater(Finishing.class, "_isCompleting");

        /* renamed from: f  reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f1000f = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_rootCause");

        /* renamed from: g  reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f1001g = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_exceptionsHolder");
        private volatile Object _exceptionsHolder;
        private volatile int _isCompleting;
        private volatile Object _rootCause;

        /* renamed from: d  reason: collision with root package name */
        private final NodeList f1002d;

        public Finishing(NodeList nodeList, boolean z2, Throwable th) {
            this.f1002d = nodeList;
            this._isCompleting = z2 ? 1 : 0;
            this._rootCause = th;
        }

        private final ArrayList<Throwable> b() {
            return new ArrayList<>(4);
        }

        private final Object d() {
            return f1001g.get(this);
        }

        private final void l(Object obj) {
            f1001g.set(this, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(Throwable th) {
            Throwable e2 = e();
            if (e2 == null) {
                m(th);
            } else if (th == e2) {
            } else {
                Object d2 = d();
                if (d2 == null) {
                    l(th);
                } else if (d2 instanceof Throwable) {
                    if (th == d2) {
                        return;
                    }
                    ArrayList<Throwable> b2 = b();
                    b2.add(d2);
                    b2.add(th);
                    l(b2);
                } else if (d2 instanceof ArrayList) {
                    ((ArrayList) d2).add(th);
                } else {
                    throw new IllegalStateException(("State is " + d2).toString());
                }
            }
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean c() {
            return e() == null;
        }

        public final Throwable e() {
            return (Throwable) f1000f.get(this);
        }

        public final boolean f() {
            return e() != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        public NodeList g() {
            return this.f1002d;
        }

        public final boolean h() {
            if (f999e.get(this) != 0) {
                return true;
            }
            return false;
        }

        public final boolean i() {
            Symbol symbol;
            Object d2 = d();
            symbol = JobSupportKt.f1007e;
            return d2 == symbol;
        }

        public final List<Throwable> j(Throwable th) {
            ArrayList<Throwable> arrayList;
            Symbol symbol;
            Object d2 = d();
            if (d2 == null) {
                arrayList = b();
            } else if (d2 instanceof Throwable) {
                ArrayList<Throwable> b2 = b();
                b2.add(d2);
                arrayList = b2;
            } else if (d2 instanceof ArrayList) {
                arrayList = (ArrayList) d2;
            } else {
                throw new IllegalStateException(("State is " + d2).toString());
            }
            Throwable e2 = e();
            if (e2 != null) {
                arrayList.add(0, e2);
            }
            if (th != null && !Intrinsics.a(th, e2)) {
                arrayList.add(th);
            }
            symbol = JobSupportKt.f1007e;
            l(symbol);
            return arrayList;
        }

        public final void k(boolean z2) {
            f999e.set(this, z2 ? 1 : 0);
        }

        public final void m(Throwable th) {
            f1000f.set(this, th);
        }

        public String toString() {
            return "Finishing[cancelling=" + f() + ", completing=" + h() + ", rootCause=" + e() + ", exceptions=" + d() + ", list=" + g() + ']';
        }
    }

    public JobSupport(boolean z2) {
        Empty empty;
        if (z2) {
            empty = JobSupportKt.f1009g;
        } else {
            empty = JobSupportKt.f1008f;
        }
        this._state = empty;
    }

    private final void C(Incomplete incomplete, Object obj) {
        CompletedExceptionally completedExceptionally;
        ChildHandle S = S();
        if (S != null) {
            S.a();
            m0(NonDisposableHandle.f1011d);
        }
        Throwable th = null;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally != null) {
            th = completedExceptionally.f935a;
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).v(th);
                return;
            } catch (Throwable th2) {
                V(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
                return;
            }
        }
        NodeList g2 = incomplete.g();
        if (g2 != null) {
            f0(g2, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        ChildHandleNode d02 = d0(childHandleNode);
        if (d02 != null && w0(finishing, d02, obj)) {
            return;
        }
        q(K(finishing, obj));
    }

    private final Throwable H(Object obj) {
        boolean z2;
        if (obj == null) {
            z2 = true;
        } else {
            z2 = obj instanceof Throwable;
        }
        if (z2) {
            Throwable th = (Throwable) obj;
            if (th == null) {
                return new JobCancellationException(y(), null, this);
            }
            return th;
        }
        Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((ParentJob) obj).w();
    }

    private final Object K(Finishing finishing, Object obj) {
        CompletedExceptionally completedExceptionally;
        Throwable th;
        boolean f2;
        Throwable N;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally != null) {
            th = completedExceptionally.f935a;
        } else {
            th = null;
        }
        synchronized (finishing) {
            f2 = finishing.f();
            List<Throwable> j2 = finishing.j(th);
            N = N(finishing, j2);
            if (N != null) {
                p(N, j2);
            }
        }
        boolean z2 = false;
        if (N != null && N != th) {
            obj = new CompletedExceptionally(N, false, 2, null);
        }
        if (N != null) {
            if (v(N) || U(N)) {
                z2 = true;
            }
            if (z2) {
                Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((CompletedExceptionally) obj).b();
            }
        }
        if (!f2) {
            g0(N);
        }
        h0(obj);
        a.a(f991d, this, finishing, JobSupportKt.g(obj));
        C(finishing, obj);
        return obj;
    }

    private final ChildHandleNode L(Incomplete incomplete) {
        ChildHandleNode childHandleNode = incomplete instanceof ChildHandleNode ? (ChildHandleNode) incomplete : null;
        if (childHandleNode == null) {
            NodeList g2 = incomplete.g();
            if (g2 == null) {
                return null;
            }
            return d0(g2);
        }
        return childHandleNode;
    }

    private final Throwable M(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.f935a;
        }
        return null;
    }

    private final Throwable N(Finishing finishing, List<? extends Throwable> list) {
        Object obj = null;
        if (list.isEmpty()) {
            if (!finishing.f()) {
                return null;
            }
            return new JobCancellationException(y(), null, this);
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                obj = next;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        return list.get(0);
    }

    private final NodeList R(Incomplete incomplete) {
        NodeList g2 = incomplete.g();
        if (g2 == null) {
            if (incomplete instanceof Empty) {
                return new NodeList();
            }
            if (incomplete instanceof JobNode) {
                k0((JobNode) incomplete);
                return null;
            }
            throw new IllegalStateException(("State should have list: " + incomplete).toString());
        }
        return g2;
    }

    private final Object Z(Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Throwable th = null;
        Throwable th2 = null;
        while (true) {
            Object T = T();
            if (T instanceof Finishing) {
                synchronized (T) {
                    if (((Finishing) T).i()) {
                        symbol2 = JobSupportKt.f1006d;
                        return symbol2;
                    }
                    boolean f2 = ((Finishing) T).f();
                    if (obj != null || !f2) {
                        if (th2 == null) {
                            th2 = H(obj);
                        }
                        ((Finishing) T).a(th2);
                    }
                    Throwable e2 = ((Finishing) T).e();
                    if (!f2) {
                        th = e2;
                    }
                    if (th != null) {
                        e0(((Finishing) T).g(), th);
                    }
                    symbol = JobSupportKt.f1003a;
                    return symbol;
                }
            } else if (T instanceof Incomplete) {
                if (th2 == null) {
                    th2 = H(obj);
                }
                Incomplete incomplete = (Incomplete) T;
                if (incomplete.c()) {
                    if (t0(incomplete, th2)) {
                        symbol4 = JobSupportKt.f1003a;
                        return symbol4;
                    }
                } else {
                    Object u0 = u0(T, new CompletedExceptionally(th2, false, 2, null));
                    symbol5 = JobSupportKt.f1003a;
                    if (u0 != symbol5) {
                        symbol6 = JobSupportKt.f1005c;
                        if (u0 != symbol6) {
                            return u0;
                        }
                    } else {
                        throw new IllegalStateException(("Cannot happen in " + T).toString());
                    }
                }
            } else {
                symbol3 = JobSupportKt.f1006d;
                return symbol3;
            }
        }
    }

    private final JobNode b0(Function1<? super Throwable, Unit> function1, boolean z2) {
        JobNode jobNode = null;
        if (z2) {
            if (function1 instanceof JobCancellingNode) {
                jobNode = (JobCancellingNode) function1;
            }
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            if (function1 instanceof JobNode) {
                jobNode = (JobNode) function1;
            }
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.x(this);
        return jobNode;
    }

    private final ChildHandleNode d0(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.q()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.p();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.o();
            if (!lockFreeLinkedListNode.q()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    private final void e0(NodeList nodeList, Throwable th) {
        g0(th);
        Object n2 = nodeList.n();
        Intrinsics.d(n2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) n2; !Intrinsics.a(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.o()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.v(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.f763a;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            V(completionHandlerException);
        }
        v(th);
    }

    private final void f0(NodeList nodeList, Throwable th) {
        Object n2 = nodeList.n();
        Intrinsics.d(n2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) n2; !Intrinsics.a(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.o()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.v(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.f763a;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            V(completionHandlerException);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.InactiveNodeList] */
    private final void j0(Empty empty) {
        NodeList nodeList = new NodeList();
        if (!empty.c()) {
            nodeList = new InactiveNodeList(nodeList);
        }
        a.a(f991d, this, empty, nodeList);
    }

    private final void k0(JobNode jobNode) {
        jobNode.j(new NodeList());
        a.a(f991d, this, jobNode, jobNode.o());
    }

    private final boolean l(final Object obj, NodeList nodeList, final JobNode jobNode) {
        int u2;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            /* renamed from: f */
            public Object d(LockFreeLinkedListNode lockFreeLinkedListNode) {
                boolean z2;
                if (this.T() == obj) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    return null;
                }
                return LockFreeLinkedListKt.a();
            }
        };
        do {
            u2 = nodeList.p().u(jobNode, nodeList, condAddOp);
            if (u2 == 1) {
                return true;
            }
        } while (u2 != 2);
        return false;
    }

    private final int n0(Object obj) {
        Empty empty;
        if (obj instanceof Empty) {
            if (((Empty) obj).c()) {
                return 0;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f991d;
            empty = JobSupportKt.f1009g;
            if (!a.a(atomicReferenceFieldUpdater, this, obj, empty)) {
                return -1;
            }
            i0();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (!a.a(f991d, this, obj, ((InactiveNodeList) obj).g())) {
                return -1;
            }
            i0();
            return 1;
        }
    }

    private final String o0(Object obj) {
        if (obj instanceof Finishing) {
            Finishing finishing = (Finishing) obj;
            if (finishing.f()) {
                return "Cancelling";
            }
            if (!finishing.h()) {
                return "Active";
            }
            return "Completing";
        } else if (obj instanceof Incomplete) {
            if (((Incomplete) obj).c()) {
                return "Active";
            }
            return "New";
        } else if (obj instanceof CompletedExceptionally) {
            return "Cancelled";
        } else {
            return "Completed";
        }
    }

    private final void p(Throwable th, List<? extends Throwable> list) {
        if (list.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        for (Throwable th2 : list) {
            if (th2 != th && th2 != th && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                ExceptionsKt__ExceptionsKt.a(th, th2);
            }
        }
    }

    public static /* synthetic */ CancellationException q0(JobSupport jobSupport, Throwable th, String str, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            return jobSupport.p0(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    private final boolean s0(Incomplete incomplete, Object obj) {
        if (!a.a(f991d, this, incomplete, JobSupportKt.g(obj))) {
            return false;
        }
        g0(null);
        h0(obj);
        C(incomplete, obj);
        return true;
    }

    private final boolean t0(Incomplete incomplete, Throwable th) {
        NodeList R = R(incomplete);
        if (R == null) {
            return false;
        }
        if (!a.a(f991d, this, incomplete, new Finishing(R, false, th))) {
            return false;
        }
        e0(R, th);
        return true;
    }

    private final Object u(Object obj) {
        Symbol symbol;
        Object u0;
        Symbol symbol2;
        do {
            Object T = T();
            if ((T instanceof Incomplete) && (!(T instanceof Finishing) || !((Finishing) T).h())) {
                u0 = u0(T, new CompletedExceptionally(H(obj), false, 2, null));
                symbol2 = JobSupportKt.f1005c;
            } else {
                symbol = JobSupportKt.f1003a;
                return symbol;
            }
        } while (u0 == symbol2);
        return u0;
    }

    private final Object u0(Object obj, Object obj2) {
        Symbol symbol;
        Symbol symbol2;
        if (!(obj instanceof Incomplete)) {
            symbol2 = JobSupportKt.f1003a;
            return symbol2;
        } else if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            if (s0((Incomplete) obj, obj2)) {
                return obj2;
            }
            symbol = JobSupportKt.f1005c;
            return symbol;
        } else {
            return v0((Incomplete) obj, obj2);
        }
    }

    private final boolean v(Throwable th) {
        if (Y()) {
            return true;
        }
        boolean z2 = th instanceof CancellationException;
        ChildHandle S = S();
        if (S != null && S != NonDisposableHandle.f1011d) {
            if (S.d(th) || z2) {
                return true;
            }
            return false;
        }
        return z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Throwable, T] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private final Object v0(Incomplete incomplete, Object obj) {
        Finishing finishing;
        CompletedExceptionally completedExceptionally;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        NodeList R = R(incomplete);
        if (R == null) {
            symbol3 = JobSupportKt.f1005c;
            return symbol3;
        }
        ?? r2 = 0;
        if (incomplete instanceof Finishing) {
            finishing = (Finishing) incomplete;
        } else {
            finishing = null;
        }
        boolean z2 = false;
        if (finishing == null) {
            finishing = new Finishing(R, false, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (finishing) {
            if (finishing.h()) {
                symbol2 = JobSupportKt.f1003a;
                return symbol2;
            }
            finishing.k(true);
            if (finishing != incomplete && !a.a(f991d, this, incomplete, finishing)) {
                symbol = JobSupportKt.f1005c;
                return symbol;
            }
            boolean f2 = finishing.f();
            if (obj instanceof CompletedExceptionally) {
                completedExceptionally = (CompletedExceptionally) obj;
            } else {
                completedExceptionally = null;
            }
            if (completedExceptionally != null) {
                finishing.a(completedExceptionally.f935a);
            }
            Throwable e2 = finishing.e();
            if (!f2) {
                z2 = true;
            }
            if (Boolean.valueOf(z2).booleanValue()) {
                r2 = e2;
            }
            ref$ObjectRef.f884d = r2;
            Unit unit = Unit.f763a;
            if (r2 != 0) {
                e0(R, r2);
            }
            ChildHandleNode L = L(incomplete);
            if (L != null && w0(finishing, L, obj)) {
                return JobSupportKt.f1004b;
            }
            return K(finishing, obj);
        }
    }

    private final boolean w0(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (Job.DefaultImpls.d(childHandleNode.f928h, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.f1011d) {
            childHandleNode = d0(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    public boolean A(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        if (s(th) && P()) {
            return true;
        }
        return false;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R B(R r2, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.DefaultImpls.b(this, r2, function2);
    }

    @Override // kotlinx.coroutines.Job
    public void D(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(y(), null, this);
        }
        t(cancellationException);
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void E(ParentJob parentJob) {
        s(parentJob);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext I(CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.e(this, key);
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle O(ChildJob childJob) {
        DisposableHandle d2 = Job.DefaultImpls.d(this, true, false, new ChildHandleNode(childJob), 2, null);
        Intrinsics.d(d2, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (ChildHandle) d2;
    }

    public boolean P() {
        return true;
    }

    public boolean Q() {
        return false;
    }

    public final ChildHandle S() {
        return (ChildHandle) f992e.get(this);
    }

    public final Object T() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f991d;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).a(this);
        }
    }

    protected boolean U(Throwable th) {
        return false;
    }

    public void V(Throwable th) {
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void W(Job job) {
        if (job == null) {
            m0(NonDisposableHandle.f1011d);
            return;
        }
        job.start();
        ChildHandle O = job.O(this);
        m0(O);
        if (X()) {
            O.a();
            m0(NonDisposableHandle.f1011d);
        }
    }

    public final boolean X() {
        return !(T() instanceof Incomplete);
    }

    protected boolean Y() {
        return false;
    }

    public final Object a0(Object obj) {
        Object u0;
        Symbol symbol;
        Symbol symbol2;
        do {
            u0 = u0(T(), obj);
            symbol = JobSupportKt.f1003a;
            if (u0 != symbol) {
                symbol2 = JobSupportKt.f1005c;
            } else {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, M(obj));
            }
        } while (u0 == symbol2);
        return u0;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E b(CoroutineContext.Key<E> key) {
        return (E) Job.DefaultImpls.c(this, key);
    }

    @Override // kotlinx.coroutines.Job
    public boolean c() {
        Object T = T();
        if ((T instanceof Incomplete) && ((Incomplete) T).c()) {
            return true;
        }
        return false;
    }

    public String c0() {
        return DebugStringsKt.a(this);
    }

    protected void g0(Throwable th) {
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.f986c;
    }

    protected void h0(Object obj) {
    }

    protected void i0() {
    }

    public final void l0(JobNode jobNode) {
        Object T;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Empty empty;
        do {
            T = T();
            if (T instanceof JobNode) {
                if (T != jobNode) {
                    return;
                }
                atomicReferenceFieldUpdater = f991d;
                empty = JobSupportKt.f1009g;
            } else if ((T instanceof Incomplete) && ((Incomplete) T).g() != null) {
                jobNode.r();
                return;
            } else {
                return;
            }
        } while (!a.a(atomicReferenceFieldUpdater, this, T, empty));
    }

    public final void m0(ChildHandle childHandle) {
        f992e.set(this, childHandle);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext n(CoroutineContext coroutineContext) {
        return Job.DefaultImpls.f(this, coroutineContext);
    }

    protected final CancellationException p0(Throwable th, String str) {
        CancellationException cancellationException;
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        } else {
            cancellationException = null;
        }
        if (cancellationException == null) {
            if (str == null) {
                str = y();
            }
            cancellationException = new JobCancellationException(str, th, this);
        }
        return cancellationException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void q(Object obj) {
    }

    public final String r0() {
        return c0() + '{' + o0(T()) + '}';
    }

    public final boolean s(Object obj) {
        Object obj2;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        obj2 = JobSupportKt.f1003a;
        if (Q() && (obj2 = u(obj)) == JobSupportKt.f1004b) {
            return true;
        }
        symbol = JobSupportKt.f1003a;
        if (obj2 == symbol) {
            obj2 = Z(obj);
        }
        symbol2 = JobSupportKt.f1003a;
        if (obj2 == symbol2 || obj2 == JobSupportKt.f1004b) {
            return true;
        }
        symbol3 = JobSupportKt.f1006d;
        if (obj2 == symbol3) {
            return false;
        }
        q(obj2);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int n0;
        do {
            n0 = n0(T());
            if (n0 == 0) {
                return false;
            }
        } while (n0 != 1);
        return true;
    }

    public void t(Throwable th) {
        s(th);
    }

    public String toString() {
        return r0() + '@' + DebugStringsKt.b(this);
    }

    @Override // kotlinx.coroutines.ParentJob
    public CancellationException w() {
        Throwable th;
        Object T = T();
        CancellationException cancellationException = null;
        if (T instanceof Finishing) {
            th = ((Finishing) T).e();
        } else if (T instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) T).f935a;
        } else if (!(T instanceof Incomplete)) {
            th = null;
        } else {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + T).toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = th;
        }
        if (cancellationException == null) {
            return new JobCancellationException("Parent job is " + o0(T), th, this);
        }
        return cancellationException;
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle x(boolean z2, boolean z3, Function1<? super Throwable, Unit> function1) {
        CompletedExceptionally completedExceptionally;
        JobNode b02 = b0(function1, z2);
        while (true) {
            Object T = T();
            if (T instanceof Empty) {
                Empty empty = (Empty) T;
                if (empty.c()) {
                    if (a.a(f991d, this, T, b02)) {
                        return b02;
                    }
                } else {
                    j0(empty);
                }
            } else {
                Throwable th = null;
                if (T instanceof Incomplete) {
                    NodeList g2 = ((Incomplete) T).g();
                    if (g2 == null) {
                        Intrinsics.d(T, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                        k0((JobNode) T);
                    } else {
                        DisposableHandle disposableHandle = NonDisposableHandle.f1011d;
                        if (z2 && (T instanceof Finishing)) {
                            synchronized (T) {
                                th = ((Finishing) T).e();
                                if (th == null || ((function1 instanceof ChildHandleNode) && !((Finishing) T).h())) {
                                    if (l(T, g2, b02)) {
                                        if (th == null) {
                                            return b02;
                                        }
                                        disposableHandle = b02;
                                    }
                                }
                                Unit unit = Unit.f763a;
                            }
                        }
                        if (th != null) {
                            if (z3) {
                                function1.f(th);
                            }
                            return disposableHandle;
                        } else if (l(T, g2, b02)) {
                            return b02;
                        }
                    }
                } else {
                    if (z3) {
                        if (T instanceof CompletedExceptionally) {
                            completedExceptionally = (CompletedExceptionally) T;
                        } else {
                            completedExceptionally = null;
                        }
                        if (completedExceptionally != null) {
                            th = completedExceptionally.f935a;
                        }
                        function1.f(th);
                    }
                    return NonDisposableHandle.f1011d;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String y() {
        return "Job was cancelled";
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException z() {
        Object T = T();
        if (T instanceof Finishing) {
            Throwable e2 = ((Finishing) T).e();
            if (e2 != null) {
                CancellationException p0 = p0(e2, DebugStringsKt.a(this) + " is cancelling");
                if (p0 != null) {
                    return p0;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (!(T instanceof Incomplete)) {
            if (T instanceof CompletedExceptionally) {
                return q0(this, ((CompletedExceptionally) T).f935a, null, 1, null);
            }
            return new JobCancellationException(DebugStringsKt.a(this) + " has completed normally", null, this);
        } else {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
    }
}
