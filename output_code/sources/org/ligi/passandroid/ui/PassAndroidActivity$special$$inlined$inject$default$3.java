package org.ligi.passandroid.ui;

import android.content.ComponentCallbacks;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassAndroidActivity$special$$inlined$inject$default$3 extends Lambda implements Function0<Tracker> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ComponentCallbacks f2766d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2767e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2768f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassAndroidActivity$special$$inlined$inject$default$3(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2766d = componentCallbacks;
        this.f2767e = qualifier;
        this.f2768f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, org.ligi.passandroid.Tracker] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Tracker mo2invoke() {
        ComponentCallbacks componentCallbacks = this.f2766d;
        return AndroidKoinScopeExtKt.a(componentCallbacks).e(Reflection.b(Tracker.class), this.f2767e, this.f2768f);
    }
}
