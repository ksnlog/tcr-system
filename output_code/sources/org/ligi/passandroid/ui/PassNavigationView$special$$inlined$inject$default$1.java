package org.ligi.passandroid.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.component.KoinComponent;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.ligi.passandroid.model.PassStore;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassNavigationView$special$$inlined$inject$default$1 extends Lambda implements Function0<PassStore> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ KoinComponent f2840d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2841e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2842f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassNavigationView$special$$inlined$inject$default$1(KoinComponent koinComponent, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2840d = koinComponent;
        this.f2841e = qualifier;
        this.f2842f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, org.ligi.passandroid.model.PassStore] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final PassStore mo2invoke() {
        Scope c2;
        KoinComponent koinComponent = this.f2840d;
        Qualifier qualifier = this.f2841e;
        Function0<? extends ParametersHolder> function0 = this.f2842f;
        if (koinComponent instanceof KoinScopeComponent) {
            c2 = ((KoinScopeComponent) koinComponent).a();
        } else {
            c2 = koinComponent.getKoin().d().c();
        }
        return c2.e(Reflection.b(PassStore.class), qualifier, function0);
    }
}
