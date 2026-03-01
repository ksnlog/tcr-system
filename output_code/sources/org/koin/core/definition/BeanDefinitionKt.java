package org.koin.core.definition;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.qualifier.Qualifier;
import org.koin.ext.KClassExtKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BeanDefinitionKt {
    public static final String a(KClass<?> clazz, Qualifier qualifier, Qualifier scopeQualifier) {
        String str;
        Intrinsics.f(clazz, "clazz");
        Intrinsics.f(scopeQualifier, "scopeQualifier");
        if (qualifier == null || (str = qualifier.getValue()) == null) {
            str = "";
        }
        return KClassExtKt.a(clazz) + ':' + str + ':' + scopeQualifier;
    }
}
