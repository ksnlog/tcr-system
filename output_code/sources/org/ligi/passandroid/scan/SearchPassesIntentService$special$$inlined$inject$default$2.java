package org.ligi.passandroid.scan;

import android.content.ComponentCallbacks;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SearchPassesIntentService$special$$inlined$inject$default$2 extends Lambda implements Function0<Tracker> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ComponentCallbacks f2702d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2703e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2704f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchPassesIntentService$special$$inlined$inject$default$2(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2702d = componentCallbacks;
        this.f2703e = qualifier;
        this.f2704f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, org.ligi.passandroid.Tracker] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Tracker mo2invoke() {
        ComponentCallbacks componentCallbacks = this.f2702d;
        return AndroidKoinScopeExtKt.a(componentCallbacks).e(Reflection.b(Tracker.class), this.f2703e, this.f2704f);
    }
}
