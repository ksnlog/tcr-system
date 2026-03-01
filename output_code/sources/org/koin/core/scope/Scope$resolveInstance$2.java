package org.koin.core.scope;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Scope$resolveInstance$2 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Scope f2596d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ ParametersHolder f2597e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scope$resolveInstance$2(Scope scope, ParametersHolder parametersHolder) {
        super(0);
        this.f2596d = scope;
        this.f2597e = parametersHolder;
    }

    public final void a() {
        this.f2596d.l().addFirst(this.f2597e);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
