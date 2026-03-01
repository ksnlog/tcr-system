package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import w.a;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ChannelSegment<E> extends Segment<ChannelSegment<E>> {

    /* renamed from: h  reason: collision with root package name */
    private final BufferedChannel<E> f1091h;

    /* renamed from: i  reason: collision with root package name */
    private final AtomicReferenceArray f1092i;

    public ChannelSegment(long j2, ChannelSegment<E> channelSegment, BufferedChannel<E> bufferedChannel, int i2) {
        super(j2, channelSegment, i2);
        this.f1091h = bufferedChannel;
        this.f1092i = new AtomicReferenceArray(BufferedChannelKt.f1068b * 2);
    }

    private final void z(int i2, Object obj) {
        this.f1092i.lazySet(i2 * 2, obj);
    }

    public final void A(int i2, Object obj) {
        this.f1092i.set((i2 * 2) + 1, obj);
    }

    public final void B(int i2, E e2) {
        z(i2, e2);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int n() {
        return BufferedChannelKt.f1068b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        s(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0066, code lost:
        if (r0 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0068, code lost:
        r4 = u().f1056b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
        if (r4 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0070, code lost:
        kotlinx.coroutines.internal.OnUndeliveredElementKt.b(r4, r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    @Override // kotlinx.coroutines.internal.Segment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void o(int r4, java.lang.Throwable r5, kotlin.coroutines.CoroutineContext r6) {
        /*
            r3 = this;
            int r5 = kotlinx.coroutines.channels.BufferedChannelKt.f1068b
            if (r4 < r5) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            if (r0 == 0) goto La
            int r4 = r4 - r5
        La:
            java.lang.Object r5 = r3.v(r4)
        Le:
            java.lang.Object r1 = r3.w(r4)
            boolean r2 = r1 instanceof kotlinx.coroutines.Waiter
            if (r2 != 0) goto L74
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r2 == 0) goto L1b
            goto L74
        L1b:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.j()
            if (r1 == r2) goto L63
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.i()
            if (r1 != r2) goto L28
            goto L63
        L28:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.p()
            if (r1 == r2) goto Le
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.q()
            if (r1 != r2) goto L35
            goto Le
        L35:
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.channels.BufferedChannelKt.f()
            if (r1 == r4) goto L62
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.channels.BufferedChannelKt.f1070d
            if (r1 != r4) goto L40
            goto L62
        L40:
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.channels.BufferedChannelKt.z()
            if (r1 != r4) goto L47
            return
        L47:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "unexpected state: "
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L62:
            return
        L63:
            r3.s(r4)
            if (r0 == 0) goto L73
            kotlinx.coroutines.channels.BufferedChannel r4 = r3.u()
            kotlin.jvm.functions.Function1<E, kotlin.Unit> r4 = r4.f1056b
            if (r4 == 0) goto L73
            kotlinx.coroutines.internal.OnUndeliveredElementKt.b(r4, r5, r6)
        L73:
            return
        L74:
            if (r0 == 0) goto L7b
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.j()
            goto L7f
        L7b:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.i()
        L7f:
            boolean r1 = r3.r(r4, r1, r2)
            if (r1 == 0) goto Le
            r3.s(r4)
            r1 = r0 ^ 1
            r3.x(r4, r1)
            if (r0 == 0) goto L9a
            kotlinx.coroutines.channels.BufferedChannel r4 = r3.u()
            kotlin.jvm.functions.Function1<E, kotlin.Unit> r4 = r4.f1056b
            if (r4 == 0) goto L9a
            kotlinx.coroutines.internal.OnUndeliveredElementKt.b(r4, r5, r6)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelSegment.o(int, java.lang.Throwable, kotlin.coroutines.CoroutineContext):void");
    }

    public final boolean r(int i2, Object obj, Object obj2) {
        return a.a(this.f1092i, (i2 * 2) + 1, obj, obj2);
    }

    public final void s(int i2) {
        z(i2, null);
    }

    public final Object t(int i2, Object obj) {
        return this.f1092i.getAndSet((i2 * 2) + 1, obj);
    }

    public final BufferedChannel<E> u() {
        BufferedChannel<E> bufferedChannel = this.f1091h;
        Intrinsics.c(bufferedChannel);
        return bufferedChannel;
    }

    public final E v(int i2) {
        return (E) this.f1092i.get(i2 * 2);
    }

    public final Object w(int i2) {
        return this.f1092i.get((i2 * 2) + 1);
    }

    public final void x(int i2, boolean z2) {
        if (z2) {
            u().v0((this.f1150f * BufferedChannelKt.f1068b) + i2);
        }
        p();
    }

    public final E y(int i2) {
        E v2 = v(i2);
        s(i2);
        return v2;
    }
}
