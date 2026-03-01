package org.koin.dsl;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.module.Module;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ModuleDSLKt {
    public static final Module a(boolean z2, Function1<? super Module, Unit> moduleDeclaration) {
        Intrinsics.f(moduleDeclaration, "moduleDeclaration");
        Module module = new Module(z2);
        moduleDeclaration.f(module);
        return module;
    }

    public static /* synthetic */ Module b(boolean z2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return a(z2, function1);
    }
}
