package kotlinx.coroutines.selects;

import androidx.concurrent.futures.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SelectImplementation<R> extends CancelHandler implements SelectInstance, Waiter {

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1224i = AtomicReferenceFieldUpdater.newUpdater(SelectImplementation.class, Object.class, "state");

    /* renamed from: d  reason: collision with root package name */
    private final CoroutineContext f1225d;

    /* renamed from: e  reason: collision with root package name */
    private List<SelectImplementation<R>.ClauseData> f1226e;

    /* renamed from: f  reason: collision with root package name */
    private Object f1227f;

    /* renamed from: g  reason: collision with root package name */
    private int f1228g;

    /* renamed from: h  reason: collision with root package name */
    private Object f1229h;
    private volatile Object state;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class ClauseData {

        /* renamed from: a  reason: collision with root package name */
        public final Object f1230a;

        /* renamed from: b  reason: collision with root package name */
        private final Object f1231b;

        /* renamed from: c  reason: collision with root package name */
        public final Function3<SelectInstance<?>, Object, Object, Function1<Throwable, Unit>> f1232c;

        /* renamed from: d  reason: collision with root package name */
        public Object f1233d;

        /* renamed from: e  reason: collision with root package name */
        public int f1234e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ SelectImplementation<R> f1235f;

        public final Function1<Throwable, Unit> a(SelectInstance<?> selectInstance, Object obj) {
            Function3<SelectInstance<?>, Object, Object, Function1<Throwable, Unit>> function3 = this.f1232c;
            if (function3 != null) {
                return function3.b(selectInstance, this.f1231b, obj);
            }
            return null;
        }

        public final void b() {
            Object obj = this.f1233d;
            SelectImplementation<R> selectImplementation = this.f1235f;
            DisposableHandle disposableHandle = null;
            if (obj instanceof Segment) {
                ((Segment) obj).o(this.f1234e, null, selectImplementation.getContext());
                return;
            }
            if (obj instanceof DisposableHandle) {
                disposableHandle = (DisposableHandle) obj;
            }
            if (disposableHandle != null) {
                disposableHandle.a();
            }
        }
    }

    private final SelectImplementation<R>.ClauseData g(Object obj) {
        boolean z2;
        List<SelectImplementation<R>.ClauseData> list = this.f1226e;
        Object obj2 = null;
        if (list == null) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((ClauseData) next).f1230a == obj) {
                z2 = true;
                continue;
            } else {
                z2 = false;
                continue;
            }
            if (z2) {
                obj2 = next;
                break;
            }
        }
        SelectImplementation<R>.ClauseData clauseData = (ClauseData) obj2;
        if (clauseData != null) {
            return clauseData;
        }
        throw new IllegalStateException(("Clause with object " + obj + " is not found").toString());
    }

    private final int i(Object obj, Object obj2) {
        boolean h2;
        Symbol symbol;
        boolean z2;
        Symbol symbol2;
        Symbol symbol3;
        List b2;
        List D;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1224i;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj3 instanceof CancellableContinuation) {
                SelectImplementation<R>.ClauseData g2 = g(obj);
                if (g2 == null) {
                    continue;
                } else {
                    Function1<Throwable, Unit> a2 = g2.a(this, obj2);
                    if (a.a(atomicReferenceFieldUpdater, this, obj3, g2)) {
                        this.f1229h = obj2;
                        h2 = SelectKt.h((CancellableContinuation) obj3, a2);
                        if (h2) {
                            return 0;
                        }
                        this.f1229h = null;
                        return 2;
                    }
                }
            } else {
                symbol = SelectKt.f1238c;
                if (Intrinsics.a(obj3, symbol)) {
                    z2 = true;
                } else {
                    z2 = obj3 instanceof ClauseData;
                }
                if (z2) {
                    return 3;
                }
                symbol2 = SelectKt.f1239d;
                if (Intrinsics.a(obj3, symbol2)) {
                    return 2;
                }
                symbol3 = SelectKt.f1237b;
                if (Intrinsics.a(obj3, symbol3)) {
                    b2 = CollectionsKt__CollectionsJVMKt.b(obj);
                    if (a.a(atomicReferenceFieldUpdater, this, obj3, b2)) {
                        return 1;
                    }
                } else if (obj3 instanceof List) {
                    D = CollectionsKt___CollectionsKt.D((Collection) obj3, obj);
                    if (a.a(atomicReferenceFieldUpdater, this, obj3, D)) {
                        return 1;
                    }
                } else {
                    throw new IllegalStateException(("Unexpected state: " + obj3).toString());
                }
            }
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean a(Object obj, Object obj2) {
        return i(obj, obj2) == 0;
    }

    @Override // kotlinx.coroutines.Waiter
    public void c(Segment<?> segment, int i2) {
        this.f1227f = segment;
        this.f1228g = i2;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        Object obj;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1224i;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            symbol = SelectKt.f1238c;
            if (obj == symbol) {
                return;
            }
            symbol2 = SelectKt.f1239d;
        } while (!a.a(atomicReferenceFieldUpdater, this, obj, symbol2));
        List<SelectImplementation<R>.ClauseData> list = this.f1226e;
        if (list == null) {
            return;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((ClauseData) it.next()).b();
        }
        symbol3 = SelectKt.f1240e;
        this.f1229h = symbol3;
        this.f1226e = null;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        d(th);
        return Unit.f763a;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public CoroutineContext getContext() {
        return this.f1225d;
    }

    public final TrySelectDetailedResult h(Object obj, Object obj2) {
        TrySelectDetailedResult a2;
        a2 = SelectKt.a(i(obj, obj2));
        return a2;
    }
}
