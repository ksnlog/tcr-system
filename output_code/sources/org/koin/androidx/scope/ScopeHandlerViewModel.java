package org.koin.androidx.scope;

import androidx.lifecycle.ViewModel;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ScopeHandlerViewModel extends ViewModel {

    /* renamed from: d  reason: collision with root package name */
    private Scope f2519d;

    protected void d() {
        super.d();
        Scope scope = this.f2519d;
        if (scope != null && scope.m()) {
            Logger h2 = scope.h();
            String str = "Closing scope " + this.f2519d;
            Level level = Level.DEBUG;
            if (h2.b(level)) {
                h2.a(level, str);
            }
            scope.c();
        }
        this.f2519d = null;
    }

    public final Scope f() {
        return this.f2519d;
    }

    public final void g(Scope scope) {
        this.f2519d = scope;
    }
}
