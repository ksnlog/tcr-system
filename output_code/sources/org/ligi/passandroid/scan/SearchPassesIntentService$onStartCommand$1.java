package org.ligi.passandroid.scan;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
@DebugMetadata(c = "org.ligi.passandroid.scan.SearchPassesIntentService$onStartCommand$1", f = "SearchPassesIntentService.kt", l = {81, 93, 96, 99, 102, 105, 109, 112}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SearchPassesIntentService$onStartCommand$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: d  reason: collision with root package name */
    Object f2709d;

    /* renamed from: e  reason: collision with root package name */
    int f2710e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ SearchPassesIntentService f2711f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchPassesIntentService$onStartCommand$1(SearchPassesIntentService searchPassesIntentService, Continuation<? super SearchPassesIntentService$onStartCommand$1> continuation) {
        super(2, continuation);
        this.f2711f = searchPassesIntentService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchPassesIntentService$onStartCommand$1(this.f2711f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchPassesIntentService$onStartCommand$1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0128 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x013c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0151 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0166 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x017b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0187 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01b0 A[RETURN] */
    /* JADX WARN: Type inference failed for: r10v9, types: [android.content.Context, org.ligi.passandroid.scan.SearchPassesIntentService] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.content.Context, org.ligi.passandroid.scan.SearchPassesIntentService] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.scan.SearchPassesIntentService$onStartCommand$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
