package org.koin.core.context;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefaultContextExtKt {
    public static final KoinApplication a(Function1<? super KoinApplication, Unit> appDeclaration) {
        Intrinsics.f(appDeclaration, "appDeclaration");
        return KoinPlatformTools.f2602a.a().a(appDeclaration);
    }
}
