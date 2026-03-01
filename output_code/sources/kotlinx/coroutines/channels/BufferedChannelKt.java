package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BufferedChannelKt {

    /* renamed from: a  reason: collision with root package name */
    private static final ChannelSegment<Object> f1067a = new ChannelSegment<>(-1, null, null, 0);

    /* renamed from: b  reason: collision with root package name */
    public static final int f1068b;

    /* renamed from: c  reason: collision with root package name */
    private static final int f1069c;

    /* renamed from: d  reason: collision with root package name */
    public static final Symbol f1070d;

    /* renamed from: e  reason: collision with root package name */
    private static final Symbol f1071e;

    /* renamed from: f  reason: collision with root package name */
    private static final Symbol f1072f;

    /* renamed from: g  reason: collision with root package name */
    private static final Symbol f1073g;

    /* renamed from: h  reason: collision with root package name */
    private static final Symbol f1074h;

    /* renamed from: i  reason: collision with root package name */
    private static final Symbol f1075i;

    /* renamed from: j  reason: collision with root package name */
    private static final Symbol f1076j;

    /* renamed from: k  reason: collision with root package name */
    private static final Symbol f1077k;

    /* renamed from: l  reason: collision with root package name */
    private static final Symbol f1078l;

    /* renamed from: m  reason: collision with root package name */
    private static final Symbol f1079m;

    /* renamed from: n  reason: collision with root package name */
    private static final Symbol f1080n;

    /* renamed from: o  reason: collision with root package name */
    private static final Symbol f1081o;

    /* renamed from: p  reason: collision with root package name */
    private static final Symbol f1082p;

    /* renamed from: q  reason: collision with root package name */
    private static final Symbol f1083q;

    /* renamed from: r  reason: collision with root package name */
    private static final Symbol f1084r;

    /* renamed from: s  reason: collision with root package name */
    private static final Symbol f1085s;

    static {
        int e2;
        int e3;
        e2 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, null);
        f1068b = e2;
        e3 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, null);
        f1069c = e3;
        f1070d = new Symbol("BUFFERED");
        f1071e = new Symbol("SHOULD_BUFFER");
        f1072f = new Symbol("S_RESUMING_BY_RCV");
        f1073g = new Symbol("RESUMING_BY_EB");
        f1074h = new Symbol("POISONED");
        f1075i = new Symbol("DONE_RCV");
        f1076j = new Symbol("INTERRUPTED_SEND");
        f1077k = new Symbol("INTERRUPTED_RCV");
        f1078l = new Symbol("CHANNEL_CLOSED");
        f1079m = new Symbol("SUSPEND");
        f1080n = new Symbol("SUSPEND_NO_WAITER");
        f1081o = new Symbol("FAILED");
        f1082p = new Symbol("NO_RECEIVE_RESULT");
        f1083q = new Symbol("CLOSE_HANDLER_CLOSED");
        f1084r = new Symbol("CLOSE_HANDLER_INVOKED");
        f1085s = new Symbol("NO_CLOSE_CAUSE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long A(int i2) {
        if (i2 != 0) {
            if (i2 == Integer.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            return i2;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> boolean B(CancellableContinuation<? super T> cancellableContinuation, T t2, Function1<? super Throwable, Unit> function1) {
        Object a2 = cancellableContinuation.a(t2, null, function1);
        if (a2 != null) {
            cancellableContinuation.h(a2);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean C(CancellableContinuation cancellableContinuation, Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        return B(cancellableContinuation, obj, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long v(long j2, boolean z2) {
        return (z2 ? 4611686018427387904L : 0L) + j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long w(long j2, int i2) {
        return (i2 << 60) + j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E> ChannelSegment<E> x(long j2, ChannelSegment<E> channelSegment) {
        return new ChannelSegment<>(j2, channelSegment, channelSegment.u(), 0);
    }

    public static final <E> KFunction<ChannelSegment<E>> y() {
        return BufferedChannelKt$createSegmentFunction$1.f1086m;
    }

    public static final Symbol z() {
        return f1078l;
    }
}
