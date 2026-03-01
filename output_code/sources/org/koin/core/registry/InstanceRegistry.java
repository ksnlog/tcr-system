package org.koin.core.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.core.module.ModuleKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InstanceRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final Koin f2575a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, InstanceFactory<?>> f2576b;

    /* renamed from: c  reason: collision with root package name */
    private final HashMap<Integer, SingleInstanceFactory<?>> f2577c;

    public InstanceRegistry(Koin _koin) {
        Intrinsics.f(_koin, "_koin");
        this.f2575a = _koin;
        this.f2576b = KoinPlatformTools.f2602a.f();
        this.f2577c = new HashMap<>();
    }

    private final void a(Module module) {
        for (SingleInstanceFactory<?> singleInstanceFactory : module.a()) {
            this.f2577c.put(Integer.valueOf(singleInstanceFactory.hashCode()), singleInstanceFactory);
        }
    }

    private final void c(Collection<? extends SingleInstanceFactory<?>> collection) {
        if (!collection.isEmpty()) {
            InstanceContext instanceContext = new InstanceContext(this.f2575a.c(), this.f2575a.d().c(), null, 4, null);
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                ((SingleInstanceFactory) it.next()).b(instanceContext);
            }
        }
    }

    private final void e(Module module, boolean z2) {
        for (Map.Entry<String, InstanceFactory<?>> entry : module.c().entrySet()) {
            j(this, z2, entry.getKey(), entry.getValue(), false, 8, null);
        }
    }

    public static /* synthetic */ void j(InstanceRegistry instanceRegistry, boolean z2, String str, InstanceFactory instanceFactory, boolean z3, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z3 = true;
        }
        instanceRegistry.i(z2, str, instanceFactory, z3);
    }

    public final void b() {
        Collection<SingleInstanceFactory<?>> values = this.f2577c.values();
        Intrinsics.e(values, "eagerInstances.values");
        c(values);
        this.f2577c.clear();
    }

    public final void d(Scope scope) {
        Intrinsics.f(scope, "scope");
        Collection<InstanceFactory<?>> values = this.f2576b.values();
        ArrayList<ScopedInstanceFactory> arrayList = new ArrayList();
        for (Object obj : values) {
            if (obj instanceof ScopedInstanceFactory) {
                arrayList.add(obj);
            }
        }
        for (ScopedInstanceFactory scopedInstanceFactory : arrayList) {
            scopedInstanceFactory.e(scope);
        }
    }

    public final void f(Set<Module> modules, boolean z2) {
        Intrinsics.f(modules, "modules");
        for (Module module : modules) {
            e(module, z2);
            a(module);
        }
    }

    public final InstanceFactory<?> g(KClass<?> clazz, Qualifier qualifier, Qualifier scopeQualifier) {
        Intrinsics.f(clazz, "clazz");
        Intrinsics.f(scopeQualifier, "scopeQualifier");
        return this.f2576b.get(BeanDefinitionKt.a(clazz, qualifier, scopeQualifier));
    }

    public final <T> T h(Qualifier qualifier, KClass<?> clazz, Qualifier scopeQualifier, InstanceContext instanceContext) {
        Object obj;
        Intrinsics.f(clazz, "clazz");
        Intrinsics.f(scopeQualifier, "scopeQualifier");
        Intrinsics.f(instanceContext, "instanceContext");
        InstanceFactory<?> g2 = g(clazz, qualifier, scopeQualifier);
        if (g2 != null) {
            obj = g2.b(instanceContext);
        } else {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    public final void i(boolean z2, String mapping, InstanceFactory<?> factory, boolean z3) {
        Intrinsics.f(mapping, "mapping");
        Intrinsics.f(factory, "factory");
        if (this.f2576b.containsKey(mapping)) {
            if (!z2) {
                ModuleKt.c(factory, mapping);
            } else if (z3) {
                Logger c2 = this.f2575a.c();
                String str = "(+) override index '" + mapping + "' -> '" + factory.c() + '\'';
                Level level = Level.WARNING;
                if (c2.b(level)) {
                    c2.a(level, str);
                }
            }
        }
        Logger c3 = this.f2575a.c();
        String str2 = "(+) index '" + mapping + "' -> '" + factory.c() + '\'';
        Level level2 = Level.DEBUG;
        if (c3.b(level2)) {
            c3.a(level2, str2);
        }
        this.f2576b.put(mapping, factory);
    }

    public final int k() {
        return this.f2576b.size();
    }
}
