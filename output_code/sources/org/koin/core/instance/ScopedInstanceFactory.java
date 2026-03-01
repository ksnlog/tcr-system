package org.koin.core.instance;

import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ScopedInstanceFactory<T> extends InstanceFactory<T> {

    /* renamed from: c  reason: collision with root package name */
    private HashMap<String, T> f2553c;

    @Override // org.koin.core.instance.InstanceFactory
    public T a(InstanceContext context) {
        Intrinsics.f(context, "context");
        if (this.f2553c.get(context.c().g()) == null) {
            return (T) super.a(context);
        }
        T t2 = this.f2553c.get(context.c().g());
        if (t2 != null) {
            return t2;
        }
        throw new IllegalStateException(("Scoped instance not found for " + context.c().g() + " in " + c()).toString());
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T b(InstanceContext context) {
        Intrinsics.f(context, "context");
        if (Intrinsics.a(context.c().j(), c().e())) {
            KoinPlatformTools.f2602a.g(this, new ScopedInstanceFactory$get$1(this, context));
            T t2 = this.f2553c.get(context.c().g());
            if (t2 != null) {
                return t2;
            }
            throw new IllegalStateException(("Scoped instance not found for " + context.c().g() + " in " + c()).toString());
        }
        throw new IllegalStateException(("Wrong Scope: trying to open instance for " + context.c().g() + " in " + c()).toString());
    }

    public void e(Scope scope) {
        if (scope != null) {
            Function1<T, Unit> a2 = c().a().a();
            if (a2 != null) {
                a2.f(this.f2553c.get(scope.g()));
            }
            this.f2553c.remove(scope.g());
        }
    }

    public boolean f(InstanceContext instanceContext) {
        Scope c2;
        return this.f2553c.get((instanceContext == null || (c2 = instanceContext.c()) == null) ? null : c2.g()) != null;
    }
}
