package org.ligi.passandroid.scan;

import android.content.DialogInterface;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.ligi.passandroid.databinding.ActivityScanBinding;
@DebugMetadata(c = "org.ligi.passandroid.scan.PassScanActivity$onCreate$1", f = "PassScanActivity.kt", l = {31}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassScanActivity$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: d  reason: collision with root package name */
    Object f2685d;

    /* renamed from: e  reason: collision with root package name */
    int f2686e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ PassScanActivity f2687f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ ActivityScanBinding f2688g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassScanActivity$onCreate$1(PassScanActivity passScanActivity, ActivityScanBinding activityScanBinding, Continuation<? super PassScanActivity$onCreate$1> continuation) {
        super(2, continuation);
        this.f2687f = passScanActivity;
        this.f2688g = activityScanBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void i(PassScanActivity passScanActivity, DialogInterface dialogInterface, int i2) {
        passScanActivity.finish();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PassScanActivity$onCreate$1(this.f2687f, this.f2688g, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PassScanActivity$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00bb  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0040 -> B:13:0x0045). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.c()
            int r1 = r8.f2686e
            r2 = 1
            if (r1 == 0) goto L1e
            if (r1 != r2) goto L16
            java.lang.Object r1 = r8.f2685d
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.b(r9)
            r3 = r1
            r1 = r0
            r0 = r8
            goto L45
        L16:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1e:
            kotlin.ResultKt.b(r9)
            org.ligi.passandroid.scan.PassScanActivity r9 = r8.f2687f
            org.ligi.passandroid.scan.events.PassScanEventChannelProvider r9 = org.ligi.passandroid.scan.PassScanActivity.q0(r9)
            kotlinx.coroutines.channels.ConflatedBroadcastChannel r9 = r9.a()
            kotlinx.coroutines.channels.ReceiveChannel r9 = r9.a()
            kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()
            r1 = r9
            r9 = r8
        L35:
            r9.f2685d = r1
            r9.f2686e = r2
            java.lang.Object r3 = r1.a(r9)
            if (r3 != r0) goto L40
            return r0
        L40:
            r7 = r0
            r0 = r9
            r9 = r3
            r3 = r1
            r1 = r7
        L45:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lbb
            java.lang.Object r9 = r3.next()
            org.ligi.passandroid.scan.events.PassScanEvent r9 = (org.ligi.passandroid.scan.events.PassScanEvent) r9
            boolean r4 = r9 instanceof org.ligi.passandroid.scan.events.DirectoryProcessed
            if (r4 == 0) goto L65
            org.ligi.passandroid.databinding.ActivityScanBinding r4 = r0.f2688g
            android.widget.TextView r4 = r4.f2629d
            org.ligi.passandroid.scan.events.DirectoryProcessed r9 = (org.ligi.passandroid.scan.events.DirectoryProcessed) r9
            java.lang.String r9 = r9.a()
            r4.setText(r9)
            goto Lb6
        L65:
            boolean r4 = r9 instanceof org.ligi.passandroid.scan.events.ScanFinished
            if (r4 == 0) goto Lb6
            org.ligi.passandroid.databinding.ActivityScanBinding r4 = r0.f2688g
            androidx.appcompat.widget.LinearLayoutCompat r4 = r4.f2628c
            r5 = 8
            r4.setVisibility(r5)
            org.ligi.passandroid.scan.PassScanActivity r4 = r0.f2687f
            java.lang.Object[] r5 = new java.lang.Object[r2]
            org.ligi.passandroid.scan.events.ScanFinished r9 = (org.ligi.passandroid.scan.events.ScanFinished) r9
            java.util.List r9 = r9.a()
            int r9 = r9.size()
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.b(r9)
            r6 = 0
            r5[r6] = r9
            r9 = 2131755293(0x7f10011d, float:1.9141461E38)
            java.lang.String r9 = r4.getString(r9, r5)
            java.lang.String r4 = "getString(R.string.scan_…, event.foundPasses.size)"
            kotlin.jvm.internal.Intrinsics.e(r9, r4)
            android.app.AlertDialog$Builder r4 = new android.app.AlertDialog$Builder
            org.ligi.passandroid.scan.PassScanActivity r5 = r0.f2687f
            r4.<init>(r5)
            r5 = 2131755294(0x7f10011e, float:1.9141463E38)
            android.app.AlertDialog$Builder r4 = r4.setTitle(r5)
            android.app.AlertDialog$Builder r9 = r4.setMessage(r9)
            org.ligi.passandroid.scan.PassScanActivity r4 = r0.f2687f
            org.ligi.passandroid.scan.a r5 = new org.ligi.passandroid.scan.a
            r5.<init>()
            r4 = 17039370(0x104000a, float:2.42446E-38)
            android.app.AlertDialog$Builder r9 = r9.setPositiveButton(r4, r5)
            r9.show()
        Lb6:
            r9 = r0
            r0 = r1
            r1 = r3
            goto L35
        Lbb:
            kotlin.Unit r9 = kotlin.Unit.f763a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.scan.PassScanActivity$onCreate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
