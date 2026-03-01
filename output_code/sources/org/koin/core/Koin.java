package org.koin.core;

import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.extension.ExtensionManager;
import org.koin.core.logger.EmptyLogger;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.core.module.ModuleKt;
import org.koin.core.registry.InstanceRegistry;
import org.koin.core.registry.PropertyRegistry;
import org.koin.core.registry.ScopeRegistry;
import org.koin.mp.KoinPlatformTimeTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Koin {

    /* renamed from: a  reason: collision with root package name */
    private final ScopeRegistry f2520a = new ScopeRegistry(this);

    /* renamed from: b  reason: collision with root package name */
    private final InstanceRegistry f2521b = new InstanceRegistry(this);

    /* renamed from: c  reason: collision with root package name */
    private final PropertyRegistry f2522c = new PropertyRegistry(this);

    /* renamed from: d  reason: collision with root package name */
    private final ExtensionManager f2523d = new ExtensionManager(this);

    /* renamed from: e  reason: collision with root package name */
    private Logger f2524e = new EmptyLogger();

    public static /* synthetic */ void f(Koin koin, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        koin.e(list, z2);
    }

    public final void a() {
        Logger logger = this.f2524e;
        Level level = Level.DEBUG;
        if (logger.b(level)) {
            logger.a(level, "Eager instances ...");
        }
        KoinPlatformTimeTools koinPlatformTimeTools = KoinPlatformTimeTools.f2601a;
        long a2 = koinPlatformTimeTools.a();
        this.f2521b.b();
        Unit unit = Unit.f763a;
        double a3 = koinPlatformTimeTools.a() - a2;
        Double.isNaN(a3);
        double doubleValue = ((Number) new Pair(unit, Double.valueOf(a3 / 1000000.0d)).d()).doubleValue();
        Logger logger2 = this.f2524e;
        String str = "Eager instances created in " + doubleValue + " ms";
        if (logger2.b(level)) {
            logger2.a(level, str);
        }
    }

    public final InstanceRegistry b() {
        return this.f2521b;
    }

    public final Logger c() {
        return this.f2524e;
    }

    public final ScopeRegistry d() {
        return this.f2520a;
    }

    public final void e(List<Module> modules, boolean z2) {
        Intrinsics.f(modules, "modules");
        Set<Module> b2 = ModuleKt.b(modules, null, 2, null);
        this.f2521b.f(b2, z2);
        this.f2520a.e(b2);
    }
}
