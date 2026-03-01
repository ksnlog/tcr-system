package org.ligi.passandroid.ui;

import android.content.ComponentCallbacks;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.ligi.passandroid.model.Settings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PrefsFragment$special$$inlined$inject$default$1 extends Lambda implements Function0<Settings> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ComponentCallbacks f2876d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2877e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2878f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrefsFragment$special$$inlined$inject$default$1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2876d = componentCallbacks;
        this.f2877e = qualifier;
        this.f2878f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, org.ligi.passandroid.model.Settings] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Settings mo2invoke() {
        ComponentCallbacks componentCallbacks = this.f2876d;
        return AndroidKoinScopeExtKt.a(componentCallbacks).e(Reflection.b(Settings.class), this.f2877e, this.f2878f);
    }
}
