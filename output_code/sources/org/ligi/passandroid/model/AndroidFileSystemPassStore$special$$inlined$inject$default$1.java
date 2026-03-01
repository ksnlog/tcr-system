package org.ligi.passandroid.model;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.component.KoinComponent;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidFileSystemPassStore$special$$inlined$inject$default$1 extends Lambda implements Function0<Tracker> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ KoinComponent $this_inject;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidFileSystemPassStore$special$$inlined$inject$default$1(KoinComponent koinComponent, Qualifier qualifier, Function0 function0) {
        super(0);
        this.$this_inject = koinComponent;
        this.$qualifier = qualifier;
        this.$parameters = function0;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, org.ligi.passandroid.Tracker] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Tracker mo2invoke() {
        Scope c2;
        KoinComponent koinComponent = this.$this_inject;
        Qualifier qualifier = this.$qualifier;
        Function0<? extends ParametersHolder> function0 = this.$parameters;
        if (koinComponent instanceof KoinScopeComponent) {
            c2 = ((KoinScopeComponent) koinComponent).a();
        } else {
            c2 = koinComponent.getKoin().d().c();
        }
        return c2.e(Reflection.b(Tracker.class), qualifier, function0);
    }
}
