package org.koin.core.registry;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ScopeRegistry {

    /* renamed from: e  reason: collision with root package name */
    public static final Companion f2580e = new Companion(null);

    /* renamed from: f  reason: collision with root package name */
    private static final StringQualifier f2581f = QualifierKt.a("_root_");

    /* renamed from: a  reason: collision with root package name */
    private final Koin f2582a;

    /* renamed from: b  reason: collision with root package name */
    private final HashSet<Qualifier> f2583b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, Scope> f2584c;

    /* renamed from: d  reason: collision with root package name */
    private final Scope f2585d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final StringQualifier a() {
            return ScopeRegistry.f2581f;
        }
    }

    public ScopeRegistry(Koin _koin) {
        Intrinsics.f(_koin, "_koin");
        this.f2582a = _koin;
        HashSet<Qualifier> hashSet = new HashSet<>();
        this.f2583b = hashSet;
        Map<String, Scope> f2 = KoinPlatformTools.f2602a.f();
        this.f2584c = f2;
        Scope scope = new Scope(f2581f, "_root_", true, _koin);
        this.f2585d = scope;
        hashSet.add(scope.j());
        f2.put(scope.g(), scope);
    }

    private final void d(Module module) {
        this.f2583b.addAll(module.d());
    }

    public final void b(Scope scope) {
        Intrinsics.f(scope, "scope");
        this.f2582a.b().d(scope);
        this.f2584c.remove(scope.g());
    }

    public final Scope c() {
        return this.f2585d;
    }

    public final void e(Set<Module> modules) {
        Intrinsics.f(modules, "modules");
        for (Module module : modules) {
            d(module);
        }
    }
}
