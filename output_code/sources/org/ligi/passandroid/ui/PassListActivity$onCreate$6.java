package org.ligi.passandroid.ui;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
@DebugMetadata(c = "org.ligi.passandroid.ui.PassListActivity$onCreate$6", f = "PassListActivity.kt", l = {169}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassListActivity$onCreate$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: d  reason: collision with root package name */
    Object f2809d;

    /* renamed from: e  reason: collision with root package name */
    int f2810e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ PassListActivity f2811f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassListActivity$onCreate$6(PassListActivity passListActivity, Continuation<? super PassListActivity$onCreate$6> continuation) {
        super(2, continuation);
        this.f2811f = passListActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PassListActivity$onCreate$6(this.f2811f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PassListActivity$onCreate$6) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0040 -> B:13:0x0045). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.c()
            int r1 = r5.f2810e
            r2 = 1
            if (r1 == 0) goto L1e
            if (r1 != r2) goto L16
            java.lang.Object r1 = r5.f2809d
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.b(r6)
            r3 = r1
            r1 = r0
            r0 = r5
            goto L45
        L16:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L1e:
            kotlin.ResultKt.b(r6)
            org.ligi.passandroid.ui.PassListActivity r6 = r5.f2811f
            org.ligi.passandroid.model.PassStore r6 = r6.n0()
            kotlinx.coroutines.channels.BroadcastChannel r6 = r6.mo1getUpdateChannel()
            kotlinx.coroutines.channels.ReceiveChannel r6 = r6.a()
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            r1 = r6
            r6 = r5
        L35:
            r6.f2809d = r1
            r6.f2810e = r2
            java.lang.Object r3 = r1.a(r6)
            if (r3 != r0) goto L40
            return r0
        L40:
            r4 = r0
            r0 = r6
            r6 = r3
            r3 = r1
            r1 = r4
        L45:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L6f
            java.lang.Object r6 = r3.next()
            org.ligi.passandroid.model.PassStoreUpdateEvent r6 = (org.ligi.passandroid.model.PassStoreUpdateEvent) r6
            org.ligi.passandroid.ui.PassListActivity r6 = r0.f2811f
            org.ligi.passandroid.databinding.PassListBinding r6 = org.ligi.passandroid.ui.PassListActivity.v0(r6)
            if (r6 != 0) goto L61
            java.lang.String r6 = "binding"
            kotlin.jvm.internal.Intrinsics.p(r6)
            r6 = 0
        L61:
            org.ligi.passandroid.ui.PassNavigationView r6 = r6.f2665j
            r6.t()
            org.ligi.passandroid.ui.PassListActivity r6 = r0.f2811f
            r6.G0()
            r6 = r0
            r0 = r1
            r1 = r3
            goto L35
        L6f:
            kotlin.Unit r6 = kotlin.Unit.f763a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.ui.PassListActivity$onCreate$6.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
