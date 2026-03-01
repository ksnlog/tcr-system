package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {

    /* renamed from: a  reason: collision with root package name */
    private final BroadcastChannelImpl<E> f1093a;

    private ConflatedBroadcastChannel(BroadcastChannelImpl<E> broadcastChannelImpl) {
        this.f1093a = broadcastChannelImpl;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> a() {
        return this.f1093a.a();
    }

    public Object b(E e2, Continuation<? super Unit> continuation) {
        return this.f1093a.y0(e2, continuation);
    }

    public ConflatedBroadcastChannel() {
        this(new BroadcastChannelImpl(-1));
    }
}
