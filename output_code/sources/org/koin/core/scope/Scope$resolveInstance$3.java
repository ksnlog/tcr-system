package org.koin.core.scope;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Scope$resolveInstance$3 extends Lambda implements Function0<ParametersHolder> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Scope f2598d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scope$resolveInstance$3(Scope scope) {
        super(0);
        this.f2598d = scope;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final ParametersHolder mo2invoke() {
        return this.f2598d.l().k();
    }
}
