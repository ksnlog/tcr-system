package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BroadcastChannelImpl<E> extends BufferedChannel<E> implements BroadcastChannel<E> {

    /* renamed from: m  reason: collision with root package name */
    private final int f1028m;

    /* renamed from: n  reason: collision with root package name */
    private final ReentrantLock f1029n;

    /* renamed from: o  reason: collision with root package name */
    private List<? extends BufferedChannel<E>> f1030o;

    /* renamed from: p  reason: collision with root package name */
    private Object f1031p;

    /* renamed from: q  reason: collision with root package name */
    private final HashMap<SelectInstance<?>, Object> f1032q;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class SubscriberBuffered extends BufferedChannel<E> {
        public SubscriberBuffered() {
            super(BroadcastChannelImpl.this.w0(), null, 2, null);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private final class SubscriberConflated extends ConflatedBufferedChannel<E> {
        public SubscriberConflated() {
            super(1, BufferOverflow.DROP_OLDEST, null, 4, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastChannelImpl(int i2) {
        super(0, null);
        Symbol symbol;
        boolean z2 = false;
        this.f1028m = i2;
        if ((i2 >= 1 || i2 == -1) ? true : z2) {
            this.f1029n = new ReentrantLock();
            this.f1030o = CollectionsKt.f();
            symbol = BroadcastChannelKt.f1041a;
            this.f1031p = symbol;
            this.f1032q = new HashMap<>();
            return;
        }
        throw new IllegalArgumentException(("BroadcastChannel capacity must be positive or Channel.CONFLATED, but " + i2 + " was specified").toString());
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean O() {
        ReentrantLock reentrantLock = this.f1029n;
        reentrantLock.lock();
        try {
            return super.O();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> a() {
        BufferedChannel subscriberBuffered;
        Symbol symbol;
        Symbol symbol2;
        ReentrantLock reentrantLock = this.f1029n;
        reentrantLock.lock();
        try {
            if (this.f1028m == -1) {
                subscriberBuffered = new SubscriberConflated();
            } else {
                subscriberBuffered = new SubscriberBuffered();
            }
            if (O()) {
                Object obj = this.f1031p;
                symbol2 = BroadcastChannelKt.f1041a;
                if (obj == symbol2) {
                    subscriberBuffered.p(B());
                    return subscriberBuffered;
                }
            }
            Object obj2 = this.f1031p;
            symbol = BroadcastChannelKt.f1041a;
            if (obj2 != symbol) {
                subscriberBuffered.m0(x0());
            }
            this.f1030o = CollectionsKt.D(this.f1030o, subscriberBuffered);
            return subscriberBuffered;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public Object m0(E e2) {
        ReentrantLock reentrantLock = this.f1029n;
        reentrantLock.lock();
        try {
            if (O()) {
                return super.m0(e2);
            }
            List<? extends BufferedChannel<E>> list = this.f1030o;
            boolean z2 = false;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((BufferedChannel) it.next()).j0()) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z2) {
                return ChannelResult.f1087b.b();
            }
            if (this.f1028m == -1) {
                this.f1031p = e2;
            }
            Iterator<T> it2 = this.f1030o.iterator();
            while (it2.hasNext()) {
                ((BufferedChannel) it2.next()).m0(e2);
            }
            return ChannelResult.f1087b.c(Unit.f763a);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean p(Throwable th) {
        ReentrantLock reentrantLock = this.f1029n;
        reentrantLock.lock();
        try {
            Iterator<T> it = this.f1030o.iterator();
            while (it.hasNext()) {
                ((BufferedChannel) it.next()).p(th);
            }
            List<? extends BufferedChannel<E>> list = this.f1030o;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((BufferedChannel) obj).G()) {
                    arrayList.add(obj);
                }
            }
            this.f1030o = arrayList;
            return super.p(th);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public String toString() {
        Symbol symbol;
        String str;
        StringBuilder sb = new StringBuilder();
        Object obj = this.f1031p;
        symbol = BroadcastChannelKt.f1041a;
        if (obj != symbol) {
            str = "CONFLATED_ELEMENT=" + this.f1031p + "; ";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("BROADCAST=<");
        sb.append(super.toString());
        sb.append(">; SUBSCRIBERS=");
        sb.append(CollectionsKt.B(this.f1030o, ";", "<", ">", 0, null, null, 56, null));
        return sb.toString();
    }

    public final int w0() {
        return this.f1028m;
    }

    public final E x0() {
        Symbol symbol;
        ReentrantLock reentrantLock = this.f1029n;
        reentrantLock.lock();
        try {
            if (O()) {
                Throwable B = B();
                if (B == null) {
                    throw new IllegalStateException("This broadcast channel is closed");
                }
                throw B;
            }
            Object obj = this.f1031p;
            symbol = BroadcastChannelKt.f1041a;
            if (obj != symbol) {
                return (E) this.f1031p;
            }
            throw new IllegalStateException("No value".toString());
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0078 -> B:28:0x007b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object y0(E r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.BroadcastChannelImpl$send$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = (kotlinx.coroutines.channels.BroadcastChannelImpl$send$1) r0
            int r1 = r0.f1040i
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1040i = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = new kotlinx.coroutines.channels.BroadcastChannelImpl$send$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.f1038g
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.c()
            int r2 = r0.f1040i
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r7 = r0.f1037f
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.f1036e
            java.lang.Object r4 = r0.f1035d
            kotlinx.coroutines.channels.BroadcastChannelImpl r4 = (kotlinx.coroutines.channels.BroadcastChannelImpl) r4
            kotlin.ResultKt.b(r8)
            goto L7b
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            kotlin.ResultKt.b(r8)
            java.util.concurrent.locks.ReentrantLock r8 = r6.f1029n
            r8.lock()
            boolean r2 = r6.O()     // Catch: java.lang.Throwable -> L99
            if (r2 != 0) goto L94
            int r2 = r6.f1028m     // Catch: java.lang.Throwable -> L99
            r4 = -1
            if (r2 != r4) goto L50
            r6.f1031p = r7     // Catch: java.lang.Throwable -> L99
        L50:
            java.util.List<? extends kotlinx.coroutines.channels.BufferedChannel<E>> r2 = r6.f1030o     // Catch: java.lang.Throwable -> L99
            r8.unlock()
            java.util.Iterator r8 = r2.iterator()
            r4 = r6
            r5 = r8
            r8 = r7
            r7 = r5
        L5d:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L91
            java.lang.Object r2 = r7.next()
            kotlinx.coroutines.channels.BufferedChannel r2 = (kotlinx.coroutines.channels.BufferedChannel) r2
            r0.f1035d = r4
            r0.f1036e = r8
            r0.f1037f = r7
            r0.f1040i = r3
            java.lang.Object r2 = r2.g0(r8, r0)
            if (r2 != r1) goto L78
            return r1
        L78:
            r5 = r2
            r2 = r8
            r8 = r5
        L7b:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L8f
            boolean r8 = r4.O()
            if (r8 != 0) goto L8a
            goto L8f
        L8a:
            java.lang.Throwable r7 = r4.E()
            throw r7
        L8f:
            r8 = r2
            goto L5d
        L91:
            kotlin.Unit r7 = kotlin.Unit.f763a
            return r7
        L94:
            java.lang.Throwable r7 = r6.E()     // Catch: java.lang.Throwable -> L99
            throw r7     // Catch: java.lang.Throwable -> L99
        L99:
            r7 = move-exception
            r8.unlock()
            goto L9f
        L9e:
            throw r7
        L9f:
            goto L9e
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastChannelImpl.y0(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
