package org.koin.core.module;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.error.DefinitionOverrideException;
import org.koin.core.instance.InstanceFactory;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ModuleKt {
    public static final Set<Module> a(List<Module> modules, Set<Module> newModules) {
        Object v2;
        Intrinsics.f(modules, "modules");
        Intrinsics.f(newModules, "newModules");
        while (!modules.isEmpty()) {
            v2 = CollectionsKt___CollectionsKt.v(modules);
            Module module = (Module) v2;
            if (module != null) {
                modules = modules.subList(1, modules.size());
                if (module.b().isEmpty()) {
                    newModules = SetsKt___SetsKt.d(newModules, module);
                } else {
                    modules = CollectionsKt___CollectionsKt.C(module.b(), modules);
                    newModules = SetsKt___SetsKt.d(newModules, module);
                }
            } else {
                throw new IllegalStateException("Flatten - No head element in list".toString());
            }
        }
        return newModules;
    }

    public static /* synthetic */ Set b(List list, Set set, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            set = SetsKt__SetsKt.b();
        }
        return a(list, set);
    }

    public static final void c(InstanceFactory<?> factory, String mapping) {
        Intrinsics.f(factory, "factory");
        Intrinsics.f(mapping, "mapping");
        throw new DefinitionOverrideException("Already existing definition for " + factory.c() + " at " + mapping);
    }
}
