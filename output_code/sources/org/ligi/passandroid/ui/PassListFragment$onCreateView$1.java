package org.ligi.passandroid.ui;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
@DebugMetadata(c = "org.ligi.passandroid.ui.PassListFragment$onCreateView$1", f = "PassListFragment.kt", l = {58}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassListFragment$onCreateView$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: d  reason: collision with root package name */
    Object f2822d;

    /* renamed from: e  reason: collision with root package name */
    int f2823e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ PassListFragment f2824f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassListFragment$onCreateView$1(PassListFragment passListFragment, Continuation<? super PassListFragment$onCreateView$1> continuation) {
        super(2, continuation);
        this.f2824f = passListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PassListFragment$onCreateView$1(this.f2824f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PassListFragment$onCreateView$1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0040 -> B:13:0x0045). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.c()
            int r1 = r6.f2823e
            r2 = 1
            if (r1 == 0) goto L1e
            if (r1 != r2) goto L16
            java.lang.Object r1 = r6.f2822d
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.b(r7)
            r3 = r1
            r1 = r0
            r0 = r6
            goto L45
        L16:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1e:
            kotlin.ResultKt.b(r7)
            org.ligi.passandroid.ui.PassListFragment r7 = r6.f2824f
            org.ligi.passandroid.model.PassStore r7 = r7.T1()
            kotlinx.coroutines.channels.BroadcastChannel r7 = r7.mo1getUpdateChannel()
            kotlinx.coroutines.channels.ReceiveChannel r7 = r7.a()
            kotlinx.coroutines.channels.ChannelIterator r7 = r7.iterator()
            r1 = r7
            r7 = r6
        L35:
            r7.f2822d = r1
            r7.f2823e = r2
            java.lang.Object r3 = r1.a(r7)
            if (r3 != r0) goto L40
            return r0
        L40:
            r5 = r0
            r0 = r7
            r7 = r3
            r3 = r1
            r1 = r5
        L45:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L7b
            java.lang.Object r7 = r3.next()
            org.ligi.passandroid.model.PassStoreUpdateEvent r7 = (org.ligi.passandroid.model.PassStoreUpdateEvent) r7
            org.ligi.passandroid.ui.PassListFragment r7 = r0.f2824f
            org.ligi.passandroid.model.PassStoreProjection r7 = org.ligi.passandroid.ui.PassListFragment.S1(r7)
            r4 = 0
            if (r7 != 0) goto L62
            java.lang.String r7 = "passStoreProjection"
            kotlin.jvm.internal.Intrinsics.p(r7)
            r7 = r4
        L62:
            r7.refresh()
            org.ligi.passandroid.ui.PassListFragment r7 = r0.f2824f
            org.ligi.passandroid.ui.PassAdapter r7 = org.ligi.passandroid.ui.PassListFragment.R1(r7)
            if (r7 != 0) goto L73
            java.lang.String r7 = "adapter"
            kotlin.jvm.internal.Intrinsics.p(r7)
            goto L74
        L73:
            r4 = r7
        L74:
            r4.o()
            r7 = r0
            r0 = r1
            r1 = r3
            goto L35
        L7b:
            kotlin.Unit r7 = kotlin.Unit.f763a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.ui.PassListFragment$onCreateView$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
