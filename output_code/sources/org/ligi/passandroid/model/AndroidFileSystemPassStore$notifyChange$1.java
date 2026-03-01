package org.ligi.passandroid.model;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ConflatedBroadcastChannel;
@DebugMetadata(c = "org.ligi.passandroid.model.AndroidFileSystemPassStore$notifyChange$1", f = "AndroidFileSystemPassStore.kt", l = {132}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class AndroidFileSystemPassStore$notifyChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AndroidFileSystemPassStore this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidFileSystemPassStore$notifyChange$1(AndroidFileSystemPassStore androidFileSystemPassStore, Continuation<? super AndroidFileSystemPassStore$notifyChange$1> continuation) {
        super(2, continuation);
        this.this$0 = androidFileSystemPassStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AndroidFileSystemPassStore$notifyChange$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AndroidFileSystemPassStore$notifyChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object c2;
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        int i2 = this.label;
        if (i2 != 0) {
            if (i2 == 1) {
                ResultKt.b(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } else {
            ResultKt.b(obj);
            ConflatedBroadcastChannel<PassStoreUpdateEvent> mo1getUpdateChannel = this.this$0.mo1getUpdateChannel();
            PassStoreUpdateEvent passStoreUpdateEvent = PassStoreUpdateEvent.INSTANCE;
            this.label = 1;
            if (mo1getUpdateChannel.b(passStoreUpdateEvent, this) == c2) {
                return c2;
            }
        }
        return Unit.f763a;
    }
}
