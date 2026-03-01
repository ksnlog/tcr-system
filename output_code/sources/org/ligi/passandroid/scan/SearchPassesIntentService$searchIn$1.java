package org.ligi.passandroid.scan;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "org.ligi.passandroid.scan.SearchPassesIntentService", f = "SearchPassesIntentService.kt", l = {126, 145}, m = "searchIn")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SearchPassesIntentService$searchIn$1 extends ContinuationImpl {

    /* renamed from: d  reason: collision with root package name */
    Object f2712d;

    /* renamed from: e  reason: collision with root package name */
    Object f2713e;

    /* renamed from: f  reason: collision with root package name */
    Object f2714f;

    /* renamed from: g  reason: collision with root package name */
    boolean f2715g;

    /* renamed from: h  reason: collision with root package name */
    int f2716h;

    /* renamed from: i  reason: collision with root package name */
    int f2717i;

    /* renamed from: j  reason: collision with root package name */
    /* synthetic */ Object f2718j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ SearchPassesIntentService f2719k;

    /* renamed from: l  reason: collision with root package name */
    int f2720l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchPassesIntentService$searchIn$1(SearchPassesIntentService searchPassesIntentService, Continuation<? super SearchPassesIntentService$searchIn$1> continuation) {
        super(continuation);
        this.f2719k = searchPassesIntentService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object o2;
        this.f2718j = obj;
        this.f2720l |= Integer.MIN_VALUE;
        o2 = this.f2719k.o(null, false, this);
        return o2;
    }
}
