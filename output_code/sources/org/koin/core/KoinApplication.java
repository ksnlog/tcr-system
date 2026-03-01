package org.koin.core;

import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.mp.KoinPlatformTimeTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KoinApplication {

    /* renamed from: c  reason: collision with root package name */
    public static final Companion f2525c = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final Koin f2526a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f2527b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KoinApplication a() {
            return new KoinApplication(null);
        }
    }

    private KoinApplication() {
        this.f2526a = new Koin();
        this.f2527b = true;
    }

    public /* synthetic */ KoinApplication(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final void c(List<Module> list) {
        this.f2526a.e(list, this.f2527b);
    }

    public final void a() {
        this.f2526a.a();
    }

    public final Koin b() {
        return this.f2526a;
    }

    public final KoinApplication d(List<Module> modules) {
        Intrinsics.f(modules, "modules");
        Logger c2 = this.f2526a.c();
        Level level = Level.INFO;
        if (c2.b(level)) {
            KoinPlatformTimeTools koinPlatformTimeTools = KoinPlatformTimeTools.f2601a;
            long a2 = koinPlatformTimeTools.a();
            c(modules);
            Unit unit = Unit.f763a;
            double a3 = koinPlatformTimeTools.a() - a2;
            Double.isNaN(a3);
            double doubleValue = ((Number) new Pair(unit, Double.valueOf(a3 / 1000000.0d)).d()).doubleValue();
            int k2 = this.f2526a.b().k();
            this.f2526a.c().a(level, "loaded " + k2 + " definitions in " + doubleValue + " ms");
        } else {
            c(modules);
        }
        return this;
    }

    public final KoinApplication e(Module modules) {
        List<Module> b2;
        Intrinsics.f(modules, "modules");
        b2 = CollectionsKt__CollectionsJVMKt.b(modules);
        return d(b2);
    }
}
