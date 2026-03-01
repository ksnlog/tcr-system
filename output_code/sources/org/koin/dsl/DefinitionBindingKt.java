package org.koin.dsl;

import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.KoinDefinition;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefinitionBindingKt {
    public static final KoinDefinition<?> a(KoinDefinition<?> koinDefinition, KClass<?>[] classes) {
        List<? extends KClass<?>> E;
        Intrinsics.f(koinDefinition, "<this>");
        Intrinsics.f(classes, "classes");
        BeanDefinition<?> c2 = koinDefinition.a().c();
        E = CollectionsKt___CollectionsKt.E(c2.f(), classes);
        c2.g(E);
        for (KClass<?> kClass : classes) {
            koinDefinition.b().h(BeanDefinitionKt.a(kClass, koinDefinition.a().c().d(), koinDefinition.a().c().e()), koinDefinition.a());
        }
        return koinDefinition;
    }
}
