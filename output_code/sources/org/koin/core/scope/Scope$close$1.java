package org.koin.core.scope;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Scope$close$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Scope f2595d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scope$close$1(Scope scope) {
        super(0);
        this.f2595d = scope;
    }

    public final void a() {
        ArrayList<ScopeCallback> arrayList;
        ArrayList arrayList2;
        Logger c2 = this.f2595d.k().c();
        String str = "|- (-) Scope - id:'" + this.f2595d.g() + '\'';
        Level level = Level.DEBUG;
        if (c2.b(level)) {
            c2.a(level, str);
        }
        arrayList = this.f2595d.f2592g;
        Scope scope = this.f2595d;
        for (ScopeCallback scopeCallback : arrayList) {
            scopeCallback.a(scope);
        }
        arrayList2 = this.f2595d.f2592g;
        arrayList2.clear();
        this.f2595d.p(null);
        this.f2595d.f2594i = true;
        this.f2595d.k().d().b(this.f2595d);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
