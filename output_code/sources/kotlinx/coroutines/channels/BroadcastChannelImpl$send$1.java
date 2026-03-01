package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.BroadcastChannelImpl", f = "BroadcastChannel.kt", l = {230}, m = "send")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BroadcastChannelImpl$send$1 extends ContinuationImpl {

    /* renamed from: d  reason: collision with root package name */
    Object f1035d;

    /* renamed from: e  reason: collision with root package name */
    Object f1036e;

    /* renamed from: f  reason: collision with root package name */
    Object f1037f;

    /* renamed from: g  reason: collision with root package name */
    /* synthetic */ Object f1038g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ BroadcastChannelImpl<E> f1039h;

    /* renamed from: i  reason: collision with root package name */
    int f1040i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastChannelImpl$send$1(BroadcastChannelImpl<E> broadcastChannelImpl, Continuation<? super BroadcastChannelImpl$send$1> continuation) {
        super(continuation);
        this.f1039h = broadcastChannelImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.f1038g = obj;
        this.f1040i |= Integer.MIN_VALUE;
        return this.f1039h.y0(null, this);
    }
}
