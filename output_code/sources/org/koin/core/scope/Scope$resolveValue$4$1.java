package org.koin.core.scope;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Scope$resolveValue$4$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Scope f2599d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scope$resolveValue$4$1(Scope scope) {
        super(0);
        this.f2599d = scope;
    }

    public final void a() {
        this.f2599d.l().clear();
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
