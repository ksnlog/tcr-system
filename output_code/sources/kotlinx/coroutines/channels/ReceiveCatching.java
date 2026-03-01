package kotlinx.coroutines.channels;

import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ReceiveCatching<E> implements Waiter {

    /* renamed from: d  reason: collision with root package name */
    public final CancellableContinuationImpl<ChannelResult<? extends E>> f1096d;

    @Override // kotlinx.coroutines.Waiter
    public void c(Segment<?> segment, int i2) {
        this.f1096d.c(segment, i2);
    }
}
