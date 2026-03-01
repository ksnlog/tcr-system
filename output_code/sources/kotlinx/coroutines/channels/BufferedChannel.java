package kotlinx.coroutines.channels;

import androidx.concurrent.futures.a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class BufferedChannel<E> implements ReceiveChannel {

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1046d = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus");

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1047e = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers");

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1048f = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd");

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f1049g = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag");

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1050h = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment");

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1051i = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment");

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1052j = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1053k = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause");

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f1054l = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler");
    private volatile Object _closeCause;

    /* renamed from: a  reason: collision with root package name */
    private final int f1055a;

    /* renamed from: b  reason: collision with root package name */
    public final Function1<E, Unit> f1056b;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;

    /* renamed from: c  reason: collision with root package name */
    private final Function3<SelectInstance<?>, Object, Object, Function1<Throwable, Unit>> f1057c;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class BufferedChannelIterator implements ChannelIterator<E>, Waiter {

        /* renamed from: d  reason: collision with root package name */
        private Object f1058d;

        /* renamed from: e  reason: collision with root package name */
        private CancellableContinuationImpl<? super Boolean> f1059e;

        public BufferedChannelIterator() {
            Symbol symbol;
            symbol = BufferedChannelKt.f1082p;
            this.f1058d = symbol;
        }

        private final Object f(ChannelSegment<E> channelSegment, int i2, long j2, Continuation<? super Boolean> continuation) {
            Continuation b2;
            Symbol symbol;
            Symbol symbol2;
            Boolean a2;
            Symbol symbol3;
            Symbol symbol4;
            Symbol symbol5;
            Object c2;
            BufferedChannel<E> bufferedChannel = BufferedChannel.this;
            b2 = IntrinsicsKt__IntrinsicsJvmKt.b(continuation);
            CancellableContinuationImpl b3 = CancellableContinuationKt.b(b2);
            try {
                this.f1059e = b3;
                Object p0 = bufferedChannel.p0(channelSegment, i2, j2, this);
                symbol = BufferedChannelKt.f1079m;
                if (p0 == symbol) {
                    bufferedChannel.a0(this, channelSegment, i2);
                } else {
                    symbol2 = BufferedChannelKt.f1081o;
                    Function1<Throwable, Unit> function1 = null;
                    if (p0 == symbol2) {
                        if (j2 < bufferedChannel.F()) {
                            channelSegment.b();
                        }
                        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.f1051i.get(bufferedChannel);
                        while (true) {
                            if (bufferedChannel.M()) {
                                h();
                                break;
                            }
                            long andIncrement = BufferedChannel.f1047e.getAndIncrement(bufferedChannel);
                            int i3 = BufferedChannelKt.f1068b;
                            long j3 = andIncrement / i3;
                            int i4 = (int) (andIncrement % i3);
                            if (channelSegment2.f1150f != j3) {
                                ChannelSegment y2 = bufferedChannel.y(j3, channelSegment2);
                                if (y2 != null) {
                                    channelSegment2 = y2;
                                }
                            }
                            Object p02 = bufferedChannel.p0(channelSegment2, i4, andIncrement, this);
                            symbol3 = BufferedChannelKt.f1079m;
                            if (p02 == symbol3) {
                                bufferedChannel.a0(this, channelSegment2, i4);
                                break;
                            }
                            symbol4 = BufferedChannelKt.f1081o;
                            if (p02 == symbol4) {
                                if (andIncrement < bufferedChannel.F()) {
                                    channelSegment2.b();
                                }
                            } else {
                                symbol5 = BufferedChannelKt.f1080n;
                                if (p02 != symbol5) {
                                    channelSegment2.b();
                                    this.f1058d = p02;
                                    this.f1059e = null;
                                    a2 = Boxing.a(true);
                                    Function1<E, Unit> function12 = bufferedChannel.f1056b;
                                    if (function12 != null) {
                                        function1 = OnUndeliveredElementKt.a(function12, p02, b3.getContext());
                                    }
                                } else {
                                    throw new IllegalStateException("unexpected".toString());
                                }
                            }
                        }
                    } else {
                        channelSegment.b();
                        this.f1058d = p0;
                        this.f1059e = null;
                        a2 = Boxing.a(true);
                        Function1<E, Unit> function13 = bufferedChannel.f1056b;
                        if (function13 != null) {
                            function1 = OnUndeliveredElementKt.a(function13, p0, b3.getContext());
                        }
                    }
                    b3.K(a2, function1);
                }
                Object w2 = b3.w();
                c2 = IntrinsicsKt__IntrinsicsKt.c();
                if (w2 == c2) {
                    DebugProbesKt.c(continuation);
                }
                return w2;
            } catch (Throwable th) {
                b3.I();
                throw th;
            }
        }

        private final boolean g() {
            this.f1058d = BufferedChannelKt.z();
            Throwable B = BufferedChannel.this.B();
            if (B == null) {
                return false;
            }
            throw StackTraceRecoveryKt.a(B);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void h() {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.f1059e;
            Intrinsics.c(cancellableContinuationImpl);
            this.f1059e = null;
            this.f1058d = BufferedChannelKt.z();
            Throwable B = BufferedChannel.this.B();
            if (B == null) {
                Result.Companion companion = Result.f752d;
                cancellableContinuationImpl.resumeWith(Result.a(Boolean.FALSE));
                return;
            }
            Result.Companion companion2 = Result.f752d;
            cancellableContinuationImpl.resumeWith(Result.a(ResultKt.a(B)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object a(Continuation<? super Boolean> continuation) {
            ChannelSegment<E> channelSegment;
            Symbol symbol;
            Symbol symbol2;
            Symbol symbol3;
            BufferedChannel<E> bufferedChannel = BufferedChannel.this;
            ChannelSegment<E> channelSegment2 = (ChannelSegment) BufferedChannel.f1051i.get(bufferedChannel);
            while (!bufferedChannel.M()) {
                long andIncrement = BufferedChannel.f1047e.getAndIncrement(bufferedChannel);
                int i2 = BufferedChannelKt.f1068b;
                long j2 = andIncrement / i2;
                int i3 = (int) (andIncrement % i2);
                if (channelSegment2.f1150f != j2) {
                    ChannelSegment<E> y2 = bufferedChannel.y(j2, channelSegment2);
                    if (y2 == null) {
                        continue;
                    } else {
                        channelSegment = y2;
                    }
                } else {
                    channelSegment = channelSegment2;
                }
                Object p0 = bufferedChannel.p0(channelSegment, i3, andIncrement, null);
                symbol = BufferedChannelKt.f1079m;
                if (p0 != symbol) {
                    symbol2 = BufferedChannelKt.f1081o;
                    if (p0 == symbol2) {
                        if (andIncrement < bufferedChannel.F()) {
                            channelSegment.b();
                        }
                        channelSegment2 = channelSegment;
                    } else {
                        symbol3 = BufferedChannelKt.f1080n;
                        if (p0 == symbol3) {
                            return f(channelSegment, i3, andIncrement, continuation);
                        }
                        channelSegment.b();
                        this.f1058d = p0;
                        return Boxing.a(true);
                    }
                } else {
                    throw new IllegalStateException("unreachable".toString());
                }
            }
            return Boxing.a(g());
        }

        @Override // kotlinx.coroutines.Waiter
        public void c(Segment<?> segment, int i2) {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.f1059e;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.c(segment, i2);
            }
        }

        public final boolean i(E e2) {
            boolean B;
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.f1059e;
            Intrinsics.c(cancellableContinuationImpl);
            Function1<Throwable, Unit> function1 = null;
            this.f1059e = null;
            this.f1058d = e2;
            Boolean bool = Boolean.TRUE;
            Function1<E, Unit> function12 = BufferedChannel.this.f1056b;
            if (function12 != null) {
                function1 = OnUndeliveredElementKt.a(function12, e2, cancellableContinuationImpl.getContext());
            }
            B = BufferedChannelKt.B(cancellableContinuationImpl, bool, function1);
            return B;
        }

        public final void j() {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.f1059e;
            Intrinsics.c(cancellableContinuationImpl);
            this.f1059e = null;
            this.f1058d = BufferedChannelKt.z();
            Throwable B = BufferedChannel.this.B();
            if (B == null) {
                Result.Companion companion = Result.f752d;
                cancellableContinuationImpl.resumeWith(Result.a(Boolean.FALSE));
                return;
            }
            Result.Companion companion2 = Result.f752d;
            cancellableContinuationImpl.resumeWith(Result.a(ResultKt.a(B)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            Symbol symbol;
            boolean z2;
            Symbol symbol2;
            E e2 = (E) this.f1058d;
            symbol = BufferedChannelKt.f1082p;
            if (e2 != symbol) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                symbol2 = BufferedChannelKt.f1082p;
                this.f1058d = symbol2;
                if (e2 != BufferedChannelKt.z()) {
                    return e2;
                }
                throw StackTraceRecoveryKt.a(BufferedChannel.this.C());
            }
            throw new IllegalStateException("`hasNext()` has not been invoked".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class SendBroadcast implements Waiter {

        /* renamed from: d  reason: collision with root package name */
        private final CancellableContinuation<Boolean> f1061d;

        /* renamed from: e  reason: collision with root package name */
        private final /* synthetic */ CancellableContinuationImpl<Boolean> f1062e;

        /* JADX WARN: Multi-variable type inference failed */
        public SendBroadcast(CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.f1061d = cancellableContinuation;
            Intrinsics.d(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlin.Boolean>");
            this.f1062e = (CancellableContinuationImpl) cancellableContinuation;
        }

        public final CancellableContinuation<Boolean> a() {
            return this.f1061d;
        }

        @Override // kotlinx.coroutines.Waiter
        public void c(Segment<?> segment, int i2) {
            this.f1062e.c(segment, i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BufferedChannel(int i2, Function1<? super E, Unit> function1) {
        long A;
        Symbol symbol;
        this.f1055a = i2;
        this.f1056b = function1;
        if (i2 >= 0) {
            A = BufferedChannelKt.A(i2);
            this.bufferEnd = A;
            this.completedExpandBuffersAndPauseFlag = A();
            ChannelSegment channelSegment = new ChannelSegment(0L, null, this, 3);
            this.sendSegment = channelSegment;
            this.receiveSegment = channelSegment;
            if (R()) {
                channelSegment = BufferedChannelKt.f1067a;
                Intrinsics.d(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            }
            this.bufferEndSegment = channelSegment;
            this.f1057c = function1 != 0 ? new BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1(this) : null;
            symbol = BufferedChannelKt.f1085s;
            this._closeCause = symbol;
            return;
        }
        throw new IllegalArgumentException(("Invalid channel capacity: " + i2 + ", should be >=0").toString());
    }

    private final long A() {
        return f1048f.get(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable C() {
        Throwable B = B();
        return B == null ? new ClosedReceiveChannelException("Channel was closed") : B;
    }

    private final void H(long j2) {
        boolean z2;
        boolean z3;
        if ((f1049g.addAndGet(this, j2) & 4611686018427387904L) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            do {
                if ((f1049g.get(this) & 4611686018427387904L) != 0) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
            } while (z3);
        }
    }

    static /* synthetic */ void I(BufferedChannel bufferedChannel, long j2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                j2 = 1;
            }
            bufferedChannel.H(j2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
    }

    private final void J() {
        Object obj;
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1054l;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                symbol = BufferedChannelKt.f1083q;
            } else {
                symbol = BufferedChannelKt.f1084r;
            }
        } while (!a.a(atomicReferenceFieldUpdater, this, obj, symbol));
        if (obj == null) {
            return;
        }
        Function1 function1 = (Function1) TypeIntrinsics.a(obj, 1);
        ((Function1) obj).f(B());
    }

    private final boolean K(ChannelSegment<E> channelSegment, int i2, long j2) {
        Object w2;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        do {
            w2 = channelSegment.w(i2);
            if (w2 != null) {
                symbol2 = BufferedChannelKt.f1071e;
                if (w2 != symbol2) {
                    if (w2 == BufferedChannelKt.f1070d) {
                        return true;
                    }
                    symbol3 = BufferedChannelKt.f1076j;
                    if (w2 == symbol3 || w2 == BufferedChannelKt.z()) {
                        return false;
                    }
                    symbol4 = BufferedChannelKt.f1075i;
                    if (w2 == symbol4) {
                        return false;
                    }
                    symbol5 = BufferedChannelKt.f1074h;
                    if (w2 == symbol5) {
                        return false;
                    }
                    symbol6 = BufferedChannelKt.f1073g;
                    if (w2 == symbol6) {
                        return true;
                    }
                    symbol7 = BufferedChannelKt.f1072f;
                    if (w2 == symbol7 || j2 != D()) {
                        return false;
                    }
                    return true;
                }
            }
            symbol = BufferedChannelKt.f1074h;
        } while (!channelSegment.r(i2, w2, symbol));
        w();
        return false;
    }

    private final boolean L(long j2, boolean z2) {
        int i2 = (int) (j2 >> 60);
        if (i2 == 0 || i2 == 1) {
            return false;
        }
        if (i2 != 2) {
            if (i2 == 3) {
                s(j2 & 1152921504606846975L);
            } else {
                throw new IllegalStateException(("unexpected close status: " + i2).toString());
            }
        } else {
            t(j2 & 1152921504606846975L);
            if (z2 && G()) {
                return false;
            }
        }
        return true;
    }

    private final boolean N(long j2) {
        return L(j2, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean P(long j2) {
        return L(j2, false);
    }

    private final boolean R() {
        long A = A();
        return A == 0 || A == Long.MAX_VALUE;
    }

    private final long S(ChannelSegment<E> channelSegment) {
        Symbol symbol;
        do {
            int i2 = BufferedChannelKt.f1068b;
            while (true) {
                i2--;
                if (-1 < i2) {
                    long j2 = (channelSegment.f1150f * BufferedChannelKt.f1068b) + i2;
                    if (j2 >= D()) {
                        while (true) {
                            Object w2 = channelSegment.w(i2);
                            if (w2 != null) {
                                symbol = BufferedChannelKt.f1071e;
                                if (w2 != symbol) {
                                    if (w2 == BufferedChannelKt.f1070d) {
                                        return j2;
                                    }
                                }
                            }
                            if (channelSegment.r(i2, w2, BufferedChannelKt.z())) {
                                channelSegment.p();
                                break;
                            }
                        }
                    } else {
                        return -1L;
                    }
                } else {
                    channelSegment = (ChannelSegment) channelSegment.g();
                }
            }
        } while (channelSegment != null);
        return -1L;
    }

    private final void T() {
        long j2;
        long w2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1046d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if (((int) (j2 >> 60)) == 0) {
                w2 = BufferedChannelKt.w(1152921504606846975L & j2, 1);
            } else {
                return;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, w2));
    }

    private final void U() {
        long j2;
        long w2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1046d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            w2 = BufferedChannelKt.w(1152921504606846975L & j2, 3);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, w2));
    }

    private final void V() {
        long j2;
        long w2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1046d;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            int i2 = (int) (j2 >> 60);
            if (i2 != 0) {
                if (i2 != 1) {
                    return;
                }
                w2 = BufferedChannelKt.w(j2 & 1152921504606846975L, 3);
            } else {
                w2 = BufferedChannelKt.w(j2 & 1152921504606846975L, 2);
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, w2));
    }

    private final void W(long j2, ChannelSegment<E> channelSegment) {
        boolean z2;
        ChannelSegment<E> channelSegment2;
        ChannelSegment<E> channelSegment3;
        while (channelSegment.f1150f < j2 && (channelSegment3 = (ChannelSegment) channelSegment.e()) != null) {
            channelSegment = channelSegment3;
        }
        while (true) {
            if (channelSegment.h() && (channelSegment2 = (ChannelSegment) channelSegment.e()) != null) {
                channelSegment = channelSegment2;
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1052j;
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    z2 = true;
                    if (segment.f1150f >= channelSegment.f1150f) {
                        break;
                    } else if (!channelSegment.q()) {
                        z2 = false;
                        break;
                    } else if (a.a(atomicReferenceFieldUpdater, this, segment, channelSegment)) {
                        if (segment.m()) {
                            segment.k();
                        }
                    } else if (channelSegment.m()) {
                        channelSegment.k();
                    }
                }
                if (z2) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a0(Waiter waiter, ChannelSegment<E> channelSegment, int i2) {
        Z();
        waiter.c(channelSegment, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b0(Waiter waiter, ChannelSegment<E> channelSegment, int i2) {
        waiter.c(channelSegment, i2 + BufferedChannelKt.f1068b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b4, code lost:
        r13 = (kotlinx.coroutines.channels.ChannelSegment) r13.g();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void c0(kotlinx.coroutines.channels.ChannelSegment<E> r13) {
        /*
            r12 = this;
            kotlin.jvm.functions.Function1<E, kotlin.Unit> r0 = r12.f1056b
            r1 = 0
            r2 = 1
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.b(r1, r2, r1)
        L8:
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.f1068b
            int r4 = r4 - r2
        Lb:
            r5 = -1
            if (r5 >= r4) goto Lb4
            long r6 = r13.f1150f
            int r8 = kotlinx.coroutines.channels.BufferedChannelKt.f1068b
            long r8 = (long) r8
            long r6 = r6 * r8
            long r8 = (long) r4
            long r6 = r6 + r8
        L17:
            java.lang.Object r8 = r13.w(r4)
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.f()
            if (r8 == r9) goto Lbc
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.f1070d
            if (r8 != r9) goto L49
            long r9 = r12.D()
            int r11 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r11 < 0) goto Lbc
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.z()
            boolean r8 = r13.r(r4, r8, r9)
            if (r8 == 0) goto L17
            if (r0 == 0) goto L41
            java.lang.Object r5 = r13.v(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.c(r0, r5, r1)
        L41:
            r13.s(r4)
            r13.p()
            goto Lb0
        L49:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.k()
            if (r8 == r9) goto La3
            if (r8 != 0) goto L52
            goto La3
        L52:
            boolean r9 = r8 instanceof kotlinx.coroutines.Waiter
            if (r9 != 0) goto L6f
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L5b
            goto L6f
        L5b:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.p()
            if (r8 == r9) goto Lbc
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.q()
            if (r8 != r9) goto L68
            goto Lbc
        L68:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.p()
            if (r8 == r9) goto L17
            goto Lb0
        L6f:
            long r9 = r12.D()
            int r11 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r11 < 0) goto Lbc
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L81
            r9 = r8
            kotlinx.coroutines.channels.WaiterEB r9 = (kotlinx.coroutines.channels.WaiterEB) r9
            kotlinx.coroutines.Waiter r9 = r9.f1097a
            goto L84
        L81:
            r9 = r8
            kotlinx.coroutines.Waiter r9 = (kotlinx.coroutines.Waiter) r9
        L84:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.channels.BufferedChannelKt.z()
            boolean r8 = r13.r(r4, r8, r10)
            if (r8 == 0) goto L17
            if (r0 == 0) goto L98
            java.lang.Object r5 = r13.v(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.c(r0, r5, r1)
        L98:
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.c(r3, r9)
            r13.s(r4)
            r13.p()
            goto Lb0
        La3:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.z()
            boolean r8 = r13.r(r4, r8, r9)
            if (r8 == 0) goto L17
            r13.p()
        Lb0:
            int r4 = r4 + (-1)
            goto Lb
        Lb4:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r13 = r13.g()
            kotlinx.coroutines.channels.ChannelSegment r13 = (kotlinx.coroutines.channels.ChannelSegment) r13
            if (r13 != 0) goto L8
        Lbc:
            if (r3 == 0) goto Le2
            boolean r13 = r3 instanceof java.util.ArrayList
            if (r13 != 0) goto Lc8
            kotlinx.coroutines.Waiter r3 = (kotlinx.coroutines.Waiter) r3
            r12.e0(r3)
            goto Le2
        Lc8:
            java.lang.String r13 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            kotlin.jvm.internal.Intrinsics.d(r3, r13)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r13 = r3.size()
            int r13 = r13 - r2
        Ld4:
            if (r5 >= r13) goto Le2
            java.lang.Object r0 = r3.get(r13)
            kotlinx.coroutines.Waiter r0 = (kotlinx.coroutines.Waiter) r0
            r12.e0(r0)
            int r13 = r13 + (-1)
            goto Ld4
        Le2:
            if (r1 != 0) goto Le5
            return
        Le5:
            goto Le7
        Le6:
            throw r1
        Le7:
            goto Le6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.c0(kotlinx.coroutines.channels.ChannelSegment):void");
    }

    private final void d0(Waiter waiter) {
        f0(waiter, true);
    }

    private final void e0(Waiter waiter) {
        f0(waiter, false);
    }

    private final void f0(Waiter waiter, boolean z2) {
        Throwable E;
        if (waiter instanceof SendBroadcast) {
            CancellableContinuation<Boolean> a2 = ((SendBroadcast) waiter).a();
            Result.Companion companion = Result.f752d;
            a2.resumeWith(Result.a(Boolean.FALSE));
        } else if (waiter instanceof CancellableContinuation) {
            Continuation continuation = (Continuation) waiter;
            Result.Companion companion2 = Result.f752d;
            if (z2) {
                E = C();
            } else {
                E = E();
            }
            continuation.resumeWith(Result.a(ResultKt.a(E)));
        } else if (waiter instanceof ReceiveCatching) {
            CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) waiter).f1096d;
            Result.Companion companion3 = Result.f752d;
            cancellableContinuationImpl.resumeWith(Result.a(ChannelResult.b(ChannelResult.f1087b.a(B()))));
        } else if (waiter instanceof BufferedChannelIterator) {
            ((BufferedChannelIterator) waiter).j();
        } else if (waiter instanceof SelectInstance) {
            ((SelectInstance) waiter).a(this, BufferedChannelKt.z());
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + waiter).toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b6, code lost:
        r0 = kotlin.Result.f752d;
        r9.resumeWith(kotlin.Result.a(kotlin.coroutines.jvm.internal.Boxing.a(true)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ <E> java.lang.Object h0(kotlinx.coroutines.channels.BufferedChannel<E> r18, E r19, kotlin.coroutines.Continuation<? super java.lang.Boolean> r20) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.h0(kotlinx.coroutines.channels.BufferedChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean i0(long j2) {
        if (P(j2)) {
            return false;
        }
        return !n(j2 & 1152921504606846975L);
    }

    private final boolean k0(Object obj, E e2) {
        boolean B;
        boolean B2;
        if (obj instanceof SelectInstance) {
            return ((SelectInstance) obj).a(this, e2);
        }
        Function1<Throwable, Unit> function1 = null;
        if (obj instanceof ReceiveCatching) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            ReceiveCatching receiveCatching = (ReceiveCatching) obj;
            CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = receiveCatching.f1096d;
            ChannelResult b2 = ChannelResult.b(ChannelResult.f1087b.c(e2));
            Function1<E, Unit> function12 = this.f1056b;
            if (function12 != null) {
                function1 = OnUndeliveredElementKt.a(function12, e2, receiveCatching.f1096d.getContext());
            }
            B2 = BufferedChannelKt.B(cancellableContinuationImpl, b2, function1);
            return B2;
        } else if (obj instanceof BufferedChannelIterator) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator) obj).i(e2);
        } else if (obj instanceof CancellableContinuation) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Function1<E, Unit> function13 = this.f1056b;
            if (function13 != null) {
                function1 = OnUndeliveredElementKt.a(function13, e2, cancellableContinuation.getContext());
            }
            B = BufferedChannelKt.B(cancellableContinuation, e2, function1);
            return B;
        } else {
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
    }

    private final boolean l0(Object obj, ChannelSegment<E> channelSegment, int i2) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.C((CancellableContinuation) obj, Unit.f763a, null, 2, null);
        } else if (obj instanceof SelectInstance) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
            TrySelectDetailedResult h2 = ((SelectImplementation) obj).h(this, Unit.f763a);
            if (h2 == TrySelectDetailedResult.REREGISTER) {
                channelSegment.s(i2);
            }
            if (h2 == TrySelectDetailedResult.SUCCESSFUL) {
                return true;
            }
            return false;
        } else if (obj instanceof SendBroadcast) {
            return BufferedChannelKt.C(((SendBroadcast) obj).a(), Boolean.TRUE, null, 2, null);
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
        }
    }

    private final boolean n(long j2) {
        return j2 < A() || j2 < D() + ((long) this.f1055a);
    }

    private final boolean n0(ChannelSegment<E> channelSegment, int i2, long j2) {
        Symbol symbol;
        Symbol symbol2;
        Object w2 = channelSegment.w(i2);
        if ((w2 instanceof Waiter) && j2 >= f1047e.get(this)) {
            symbol = BufferedChannelKt.f1073g;
            if (channelSegment.r(i2, w2, symbol)) {
                if (l0(w2, channelSegment, i2)) {
                    channelSegment.A(i2, BufferedChannelKt.f1070d);
                    return true;
                }
                symbol2 = BufferedChannelKt.f1076j;
                channelSegment.A(i2, symbol2);
                channelSegment.x(i2, false);
                return false;
            }
        }
        return o0(channelSegment, i2, j2);
    }

    private final void o(ChannelSegment<E> channelSegment, long j2) {
        Symbol symbol;
        Object b2 = InlineList.b(null, 1, null);
        loop0: while (channelSegment != null) {
            for (int i2 = BufferedChannelKt.f1068b - 1; -1 < i2; i2--) {
                if ((channelSegment.f1150f * BufferedChannelKt.f1068b) + i2 < j2) {
                    break loop0;
                }
                while (true) {
                    Object w2 = channelSegment.w(i2);
                    if (w2 != null) {
                        symbol = BufferedChannelKt.f1071e;
                        if (w2 != symbol) {
                            if (w2 instanceof WaiterEB) {
                                if (channelSegment.r(i2, w2, BufferedChannelKt.z())) {
                                    b2 = InlineList.c(b2, ((WaiterEB) w2).f1097a);
                                    channelSegment.x(i2, true);
                                    break;
                                }
                            } else if (!(w2 instanceof Waiter)) {
                                break;
                            } else if (channelSegment.r(i2, w2, BufferedChannelKt.z())) {
                                b2 = InlineList.c(b2, w2);
                                channelSegment.x(i2, true);
                                break;
                            }
                        }
                    }
                    if (channelSegment.r(i2, w2, BufferedChannelKt.z())) {
                        channelSegment.p();
                        break;
                    }
                }
            }
            channelSegment = (ChannelSegment) channelSegment.g();
        }
        if (b2 != null) {
            if (!(b2 instanceof ArrayList)) {
                d0((Waiter) b2);
                return;
            }
            Intrinsics.d(b2, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            ArrayList arrayList = (ArrayList) b2;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                d0((Waiter) arrayList.get(size));
            }
        }
    }

    private final boolean o0(ChannelSegment<E> channelSegment, int i2, long j2) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        while (true) {
            Object w2 = channelSegment.w(i2);
            if (w2 instanceof Waiter) {
                if (j2 < f1047e.get(this)) {
                    if (channelSegment.r(i2, w2, new WaiterEB((Waiter) w2))) {
                        return true;
                    }
                } else {
                    symbol = BufferedChannelKt.f1073g;
                    if (channelSegment.r(i2, w2, symbol)) {
                        if (l0(w2, channelSegment, i2)) {
                            channelSegment.A(i2, BufferedChannelKt.f1070d);
                            return true;
                        }
                        symbol2 = BufferedChannelKt.f1076j;
                        channelSegment.A(i2, symbol2);
                        channelSegment.x(i2, false);
                        return false;
                    }
                }
            } else {
                symbol3 = BufferedChannelKt.f1076j;
                if (w2 == symbol3) {
                    return false;
                }
                if (w2 == null) {
                    symbol4 = BufferedChannelKt.f1071e;
                    if (channelSegment.r(i2, w2, symbol4)) {
                        return true;
                    }
                } else if (w2 == BufferedChannelKt.f1070d) {
                    return true;
                } else {
                    symbol5 = BufferedChannelKt.f1074h;
                    if (w2 == symbol5) {
                        break;
                    }
                    symbol6 = BufferedChannelKt.f1075i;
                    if (w2 == symbol6) {
                        break;
                    }
                    symbol7 = BufferedChannelKt.f1077k;
                    if (w2 == symbol7 || w2 == BufferedChannelKt.z()) {
                        return true;
                    }
                    symbol8 = BufferedChannelKt.f1072f;
                    if (w2 != symbol8) {
                        throw new IllegalStateException(("Unexpected cell state: " + w2).toString());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object p0(ChannelSegment<E> channelSegment, int i2, long j2, Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Object w2 = channelSegment.w(i2);
        if (w2 == null) {
            if (j2 >= (f1046d.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    symbol3 = BufferedChannelKt.f1080n;
                    return symbol3;
                } else if (channelSegment.r(i2, w2, obj)) {
                    w();
                    symbol2 = BufferedChannelKt.f1079m;
                    return symbol2;
                }
            }
        } else if (w2 == BufferedChannelKt.f1070d) {
            symbol = BufferedChannelKt.f1075i;
            if (channelSegment.r(i2, w2, symbol)) {
                w();
                return channelSegment.y(i2);
            }
        }
        return q0(channelSegment, i2, j2, obj);
    }

    private final ChannelSegment<E> q() {
        ChannelSegment channelSegment = f1052j.get(this);
        ChannelSegment channelSegment2 = (ChannelSegment) f1050h.get(this);
        if (channelSegment2.f1150f > ((ChannelSegment) channelSegment).f1150f) {
            channelSegment = channelSegment2;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) f1051i.get(this);
        if (channelSegment3.f1150f > ((ChannelSegment) channelSegment).f1150f) {
            channelSegment = channelSegment3;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.b((ConcurrentLinkedListNode) channelSegment);
    }

    private final Object q0(ChannelSegment<E> channelSegment, int i2, long j2, Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        Symbol symbol9;
        Symbol symbol10;
        Symbol symbol11;
        Symbol symbol12;
        Symbol symbol13;
        Symbol symbol14;
        Symbol symbol15;
        Symbol symbol16;
        while (true) {
            Object w2 = channelSegment.w(i2);
            if (w2 != null) {
                symbol5 = BufferedChannelKt.f1071e;
                if (w2 != symbol5) {
                    if (w2 == BufferedChannelKt.f1070d) {
                        symbol6 = BufferedChannelKt.f1075i;
                        if (channelSegment.r(i2, w2, symbol6)) {
                            w();
                            return channelSegment.y(i2);
                        }
                    } else {
                        symbol7 = BufferedChannelKt.f1076j;
                        if (w2 == symbol7) {
                            symbol8 = BufferedChannelKt.f1081o;
                            return symbol8;
                        }
                        symbol9 = BufferedChannelKt.f1074h;
                        if (w2 == symbol9) {
                            symbol10 = BufferedChannelKt.f1081o;
                            return symbol10;
                        } else if (w2 == BufferedChannelKt.z()) {
                            w();
                            symbol11 = BufferedChannelKt.f1081o;
                            return symbol11;
                        } else {
                            symbol12 = BufferedChannelKt.f1073g;
                            if (w2 != symbol12) {
                                symbol13 = BufferedChannelKt.f1072f;
                                if (channelSegment.r(i2, w2, symbol13)) {
                                    boolean z2 = w2 instanceof WaiterEB;
                                    if (z2) {
                                        w2 = ((WaiterEB) w2).f1097a;
                                    }
                                    if (l0(w2, channelSegment, i2)) {
                                        symbol16 = BufferedChannelKt.f1075i;
                                        channelSegment.A(i2, symbol16);
                                        w();
                                        return channelSegment.y(i2);
                                    }
                                    symbol14 = BufferedChannelKt.f1076j;
                                    channelSegment.A(i2, symbol14);
                                    channelSegment.x(i2, false);
                                    if (z2) {
                                        w();
                                    }
                                    symbol15 = BufferedChannelKt.f1081o;
                                    return symbol15;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
            if (j2 < (f1046d.get(this) & 1152921504606846975L)) {
                symbol = BufferedChannelKt.f1074h;
                if (channelSegment.r(i2, w2, symbol)) {
                    w();
                    symbol2 = BufferedChannelKt.f1081o;
                    return symbol2;
                }
            } else if (obj == null) {
                symbol3 = BufferedChannelKt.f1080n;
                return symbol3;
            } else if (channelSegment.r(i2, w2, obj)) {
                w();
                symbol4 = BufferedChannelKt.f1079m;
                return symbol4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int r0(ChannelSegment<E> channelSegment, int i2, E e2, long j2, Object obj, boolean z2) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        channelSegment.B(i2, e2);
        if (z2) {
            return s0(channelSegment, i2, e2, j2, obj, z2);
        }
        Object w2 = channelSegment.w(i2);
        if (w2 == null) {
            if (n(j2)) {
                if (channelSegment.r(i2, null, BufferedChannelKt.f1070d)) {
                    return 1;
                }
            } else if (obj == null) {
                return 3;
            } else {
                if (channelSegment.r(i2, null, obj)) {
                    return 2;
                }
            }
        } else if (w2 instanceof Waiter) {
            channelSegment.s(i2);
            if (k0(w2, e2)) {
                symbol3 = BufferedChannelKt.f1075i;
                channelSegment.A(i2, symbol3);
                Y();
                return 0;
            }
            symbol = BufferedChannelKt.f1077k;
            Object t2 = channelSegment.t(i2, symbol);
            symbol2 = BufferedChannelKt.f1077k;
            if (t2 != symbol2) {
                channelSegment.x(i2, true);
            }
            return 5;
        }
        return s0(channelSegment, i2, e2, j2, obj, z2);
    }

    private final void s(long j2) {
        c0(t(j2));
    }

    private final int s0(ChannelSegment<E> channelSegment, int i2, E e2, long j2, Object obj, boolean z2) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        while (true) {
            Object w2 = channelSegment.w(i2);
            if (w2 == null) {
                if (n(j2) && !z2) {
                    if (channelSegment.r(i2, null, BufferedChannelKt.f1070d)) {
                        return 1;
                    }
                } else if (z2) {
                    symbol = BufferedChannelKt.f1076j;
                    if (channelSegment.r(i2, null, symbol)) {
                        channelSegment.x(i2, false);
                        return 4;
                    }
                } else if (obj == null) {
                    return 3;
                } else {
                    if (channelSegment.r(i2, null, obj)) {
                        return 2;
                    }
                }
            } else {
                symbol2 = BufferedChannelKt.f1071e;
                if (w2 == symbol2) {
                    if (channelSegment.r(i2, w2, BufferedChannelKt.f1070d)) {
                        return 1;
                    }
                } else {
                    symbol3 = BufferedChannelKt.f1077k;
                    if (w2 == symbol3) {
                        channelSegment.s(i2);
                        return 5;
                    }
                    symbol4 = BufferedChannelKt.f1074h;
                    if (w2 == symbol4) {
                        channelSegment.s(i2);
                        return 5;
                    } else if (w2 == BufferedChannelKt.z()) {
                        channelSegment.s(i2);
                        u();
                        return 4;
                    } else {
                        channelSegment.s(i2);
                        if (w2 instanceof WaiterEB) {
                            w2 = ((WaiterEB) w2).f1097a;
                        }
                        if (k0(w2, e2)) {
                            symbol7 = BufferedChannelKt.f1075i;
                            channelSegment.A(i2, symbol7);
                            Y();
                            return 0;
                        }
                        symbol5 = BufferedChannelKt.f1077k;
                        Object t2 = channelSegment.t(i2, symbol5);
                        symbol6 = BufferedChannelKt.f1077k;
                        if (t2 != symbol6) {
                            channelSegment.x(i2, true);
                        }
                        return 5;
                    }
                }
            }
        }
    }

    private final ChannelSegment<E> t(long j2) {
        ChannelSegment<E> q2 = q();
        if (Q()) {
            long S = S(q2);
            if (S != -1) {
                v(S);
            }
        }
        o(q2, j2);
        return q2;
    }

    private final void t0(long j2) {
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1047e;
        do {
            j3 = atomicLongFieldUpdater.get(this);
            if (j3 >= j2) {
                return;
            }
        } while (!f1047e.compareAndSet(this, j3, j2));
    }

    private final void u() {
        O();
    }

    private final void u0(long j2) {
        long j3;
        long w2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1046d;
        do {
            j3 = atomicLongFieldUpdater.get(this);
            long j4 = 1152921504606846975L & j3;
            if (j4 >= j2) {
                return;
            }
            w2 = BufferedChannelKt.w(j4, (int) (j3 >> 60));
        } while (!f1046d.compareAndSet(this, j3, w2));
    }

    private final void w() {
        if (R()) {
            return;
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) f1052j.get(this);
        while (true) {
            long andIncrement = f1048f.getAndIncrement(this);
            int i2 = BufferedChannelKt.f1068b;
            long j2 = andIncrement / i2;
            if (F() <= andIncrement) {
                if (channelSegment.f1150f < j2 && channelSegment.e() != 0) {
                    W(j2, channelSegment);
                }
                I(this, 0L, 1, null);
                return;
            }
            if (channelSegment.f1150f != j2) {
                ChannelSegment<E> x2 = x(j2, channelSegment, andIncrement);
                if (x2 == null) {
                    continue;
                } else {
                    channelSegment = x2;
                }
            }
            if (n0(channelSegment, (int) (andIncrement % i2), andIncrement)) {
                I(this, 0L, 1, null);
                return;
            }
            I(this, 0L, 1, null);
        }
    }

    private final ChannelSegment<E> x(long j2, ChannelSegment<E> channelSegment, long j3) {
        Object c2;
        boolean z2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1052j;
        Function2 function2 = (Function2) BufferedChannelKt.y();
        do {
            c2 = ConcurrentLinkedListKt.c(channelSegment, j2, function2);
            if (SegmentOrClosed.c(c2)) {
                break;
            }
            Segment b2 = SegmentOrClosed.b(c2);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.f1150f >= b2.f1150f) {
                    break;
                } else if (!b2.q()) {
                    z2 = false;
                    continue;
                    break;
                } else if (a.a(atomicReferenceFieldUpdater, this, segment, b2)) {
                    if (segment.m()) {
                        segment.k();
                    }
                } else if (b2.m()) {
                    b2.k();
                }
            }
            z2 = true;
            continue;
        } while (!z2);
        if (SegmentOrClosed.c(c2)) {
            u();
            W(j2, channelSegment);
            I(this, 0L, 1, null);
            return null;
        }
        ChannelSegment<E> channelSegment2 = (ChannelSegment) SegmentOrClosed.b(c2);
        long j4 = channelSegment2.f1150f;
        if (j4 > j2) {
            int i2 = BufferedChannelKt.f1068b;
            if (f1048f.compareAndSet(this, j3 + 1, i2 * j4)) {
                H((channelSegment2.f1150f * i2) - j3);
                return null;
            }
            I(this, 0L, 1, null);
            return null;
        }
        return channelSegment2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> y(long j2, ChannelSegment<E> channelSegment) {
        Object c2;
        boolean z2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1051i;
        Function2 function2 = (Function2) BufferedChannelKt.y();
        do {
            c2 = ConcurrentLinkedListKt.c(channelSegment, j2, function2);
            if (SegmentOrClosed.c(c2)) {
                break;
            }
            Segment b2 = SegmentOrClosed.b(c2);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                z2 = true;
                if (segment.f1150f < b2.f1150f) {
                    if (!b2.q()) {
                        z2 = false;
                        continue;
                        break;
                    } else if (a.a(atomicReferenceFieldUpdater, this, segment, b2)) {
                        if (segment.m()) {
                            segment.k();
                            continue;
                        } else {
                            continue;
                        }
                    } else if (b2.m()) {
                        b2.k();
                    }
                }
            }
        } while (!z2);
        if (SegmentOrClosed.c(c2)) {
            u();
            if (channelSegment.f1150f * BufferedChannelKt.f1068b >= F()) {
                return null;
            }
            channelSegment.b();
            return null;
        }
        ChannelSegment<E> channelSegment2 = (ChannelSegment) SegmentOrClosed.b(c2);
        if (!R() && j2 <= A() / BufferedChannelKt.f1068b) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1052j;
            while (true) {
                Segment segment2 = (Segment) atomicReferenceFieldUpdater2.get(this);
                if (segment2.f1150f >= channelSegment2.f1150f || !channelSegment2.q()) {
                    break;
                } else if (a.a(atomicReferenceFieldUpdater2, this, segment2, channelSegment2)) {
                    if (segment2.m()) {
                        segment2.k();
                    }
                } else if (channelSegment2.m()) {
                    channelSegment2.k();
                }
            }
        }
        long j3 = channelSegment2.f1150f;
        if (j3 > j2) {
            int i2 = BufferedChannelKt.f1068b;
            t0(j3 * i2);
            if (channelSegment2.f1150f * i2 >= F()) {
                return null;
            }
            channelSegment2.b();
            return null;
        }
        return channelSegment2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> z(long j2, ChannelSegment<E> channelSegment) {
        Object c2;
        boolean z2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1050h;
        Function2 function2 = (Function2) BufferedChannelKt.y();
        do {
            c2 = ConcurrentLinkedListKt.c(channelSegment, j2, function2);
            if (SegmentOrClosed.c(c2)) {
                break;
            }
            Segment b2 = SegmentOrClosed.b(c2);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                z2 = true;
                if (segment.f1150f < b2.f1150f) {
                    if (!b2.q()) {
                        z2 = false;
                        continue;
                        break;
                    } else if (a.a(atomicReferenceFieldUpdater, this, segment, b2)) {
                        if (segment.m()) {
                            segment.k();
                            continue;
                        } else {
                            continue;
                        }
                    } else if (b2.m()) {
                        b2.k();
                    }
                }
            }
        } while (!z2);
        if (SegmentOrClosed.c(c2)) {
            u();
            if (channelSegment.f1150f * BufferedChannelKt.f1068b >= D()) {
                return null;
            }
            channelSegment.b();
            return null;
        }
        ChannelSegment<E> channelSegment2 = (ChannelSegment) SegmentOrClosed.b(c2);
        long j3 = channelSegment2.f1150f;
        if (j3 > j2) {
            int i2 = BufferedChannelKt.f1068b;
            u0(j3 * i2);
            if (channelSegment2.f1150f * i2 >= D()) {
                return null;
            }
            channelSegment2.b();
            return null;
        }
        return channelSegment2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable B() {
        return (Throwable) f1053k.get(this);
    }

    public final long D() {
        return f1047e.get(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable E() {
        Throwable B = B();
        return B == null ? new ClosedSendChannelException("Channel was closed") : B;
    }

    public final long F() {
        return f1046d.get(this) & 1152921504606846975L;
    }

    public final boolean G() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1051i;
            ChannelSegment<E> channelSegment = (ChannelSegment) atomicReferenceFieldUpdater.get(this);
            long D = D();
            if (F() <= D) {
                return false;
            }
            int i2 = BufferedChannelKt.f1068b;
            long j2 = D / i2;
            if (channelSegment.f1150f != j2 && (channelSegment = y(j2, channelSegment)) == null) {
                if (((ChannelSegment) atomicReferenceFieldUpdater.get(this)).f1150f < j2) {
                    return false;
                }
            } else {
                channelSegment.b();
                if (K(channelSegment, (int) (D % i2), D)) {
                    return true;
                }
                f1047e.compareAndSet(this, D, D + 1);
            }
        }
    }

    public boolean M() {
        return N(f1046d.get(this));
    }

    public boolean O() {
        return P(f1046d.get(this));
    }

    protected boolean Q() {
        return false;
    }

    protected void X() {
    }

    protected void Y() {
    }

    protected void Z() {
    }

    public Object g0(E e2, Continuation<? super Boolean> continuation) {
        return h0(this, e2, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return new BufferedChannelIterator();
    }

    public boolean j0() {
        return i0(f1046d.get(this));
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00bb, code lost:
        return kotlinx.coroutines.channels.ChannelResult.f1087b.c(kotlin.Unit.f763a);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object m0(E r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.f1046d
            long r0 = r0.get(r14)
            boolean r0 = r14.i0(r0)
            if (r0 == 0) goto L13
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.f1087b
            java.lang.Object r15 = r15.b()
            return r15
        L13:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.j()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = g()
            java.lang.Object r0 = r0.get(r14)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
        L21:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = h()
            long r1 = r1.getAndIncrement(r14)
            r3 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r1 & r3
            boolean r11 = i(r14, r1)
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.f1068b
            long r2 = (long) r1
            long r2 = r9 / r2
            long r4 = (long) r1
            long r4 = r9 % r4
            int r12 = (int) r4
            long r4 = r0.f1150f
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L4e
            kotlinx.coroutines.channels.ChannelSegment r1 = c(r14, r2, r0)
            if (r1 != 0) goto L4c
            if (r11 == 0) goto L21
            goto L8e
        L4c:
            r13 = r1
            goto L4f
        L4e:
            r13 = r0
        L4f:
            r0 = r14
            r1 = r13
            r2 = r12
            r3 = r15
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = m(r0, r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto Lb0
            r1 = 1
            if (r0 == r1) goto Lb3
            r1 = 2
            if (r0 == r1) goto L89
            r1 = 3
            if (r0 == r1) goto L7d
            r1 = 4
            if (r0 == r1) goto L71
            r1 = 5
            if (r0 == r1) goto L6c
            goto L6f
        L6c:
            r13.b()
        L6f:
            r0 = r13
            goto L21
        L71:
            long r0 = r14.D()
            int r15 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r15 >= 0) goto L8e
            r13.b()
            goto L8e
        L7d:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "unexpected"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L89:
            if (r11 == 0) goto L99
            r13.p()
        L8e:
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.f1087b
            java.lang.Throwable r0 = r14.E()
            java.lang.Object r15 = r15.a(r0)
            goto Lbb
        L99:
            boolean r15 = r8 instanceof kotlinx.coroutines.Waiter
            if (r15 == 0) goto La0
            kotlinx.coroutines.Waiter r8 = (kotlinx.coroutines.Waiter) r8
            goto La1
        La0:
            r8 = 0
        La1:
            if (r8 == 0) goto La6
            k(r14, r8, r13, r12)
        La6:
            r13.p()
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.f1087b
            java.lang.Object r15 = r15.b()
            goto Lbb
        Lb0:
            r13.b()
        Lb3:
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.f1087b
            kotlin.Unit r0 = kotlin.Unit.f763a
            java.lang.Object r15 = r15.c(r0)
        Lbb:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m0(java.lang.Object):java.lang.Object");
    }

    public boolean p(Throwable th) {
        return r(th, false);
    }

    protected boolean r(Throwable th, boolean z2) {
        Symbol symbol;
        if (z2) {
            T();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1053k;
        symbol = BufferedChannelKt.f1085s;
        boolean a2 = a.a(atomicReferenceFieldUpdater, this, symbol, th);
        if (z2) {
            U();
        } else {
            V();
        }
        u();
        X();
        if (a2) {
            J();
        }
        return a2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01eb, code lost:
        r3 = (kotlinx.coroutines.channels.ChannelSegment) r3.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f2, code lost:
        if (r3 != null) goto L115;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01eb A[EDGE_INSN: B:119:0x01eb->B:100:0x01eb ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 541
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void v(long j2) {
        Symbol symbol;
        UndeliveredElementException d2;
        ChannelSegment<E> channelSegment = (ChannelSegment) f1051i.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f1047e;
            long j3 = atomicLongFieldUpdater.get(this);
            if (j2 < Math.max(this.f1055a + j3, A())) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j3, j3 + 1)) {
                int i2 = BufferedChannelKt.f1068b;
                long j4 = j3 / i2;
                int i3 = (int) (j3 % i2);
                if (channelSegment.f1150f != j4) {
                    ChannelSegment<E> y2 = y(j4, channelSegment);
                    if (y2 == null) {
                        continue;
                    } else {
                        channelSegment = y2;
                    }
                }
                Object p0 = p0(channelSegment, i3, j3, null);
                symbol = BufferedChannelKt.f1081o;
                if (p0 == symbol) {
                    if (j3 < F()) {
                        channelSegment.b();
                    }
                } else {
                    channelSegment.b();
                    Function1<E, Unit> function1 = this.f1056b;
                    if (function1 != null && (d2 = OnUndeliveredElementKt.d(function1, p0, null, 2, null)) != null) {
                        throw d2;
                    }
                }
            }
        }
    }

    public final void v0(long j2) {
        int i2;
        long j3;
        long v2;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        boolean z2;
        long v3;
        long j4;
        long v4;
        if (R()) {
            return;
        }
        do {
        } while (A() <= j2);
        i2 = BufferedChannelKt.f1069c;
        for (int i3 = 0; i3 < i2; i3++) {
            long A = A();
            if (A == (f1049g.get(this) & 4611686018427387903L) && A == A()) {
                return;
            }
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f1049g;
        do {
            j3 = atomicLongFieldUpdater2.get(this);
            v2 = BufferedChannelKt.v(j3 & 4611686018427387903L, true);
        } while (!atomicLongFieldUpdater2.compareAndSet(this, j3, v2));
        while (true) {
            long A2 = A();
            atomicLongFieldUpdater = f1049g;
            long j5 = atomicLongFieldUpdater.get(this);
            long j6 = j5 & 4611686018427387903L;
            if ((4611686018427387904L & j5) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (A2 == j6 && A2 == A()) {
                break;
            } else if (!z2) {
                v3 = BufferedChannelKt.v(j6, true);
                atomicLongFieldUpdater.compareAndSet(this, j5, v3);
            }
        }
        do {
            j4 = atomicLongFieldUpdater.get(this);
            v4 = BufferedChannelKt.v(j4 & 4611686018427387903L, false);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j4, v4));
    }

    public /* synthetic */ BufferedChannel(int i2, Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, (i3 & 2) != 0 ? null : function1);
    }
}
