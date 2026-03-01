package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.dsl.ModuleDSLKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KoinExtKt {
    public static final KoinApplication a(KoinApplication koinApplication, Context androidContext) {
        List b2;
        List b3;
        Intrinsics.f(koinApplication, "<this>");
        Intrinsics.f(androidContext, "androidContext");
        Logger c2 = koinApplication.b().c();
        Level level = Level.INFO;
        if (c2.b(level)) {
            Logger c3 = koinApplication.b().c();
            if (c3.b(level)) {
                c3.a(level, "[init] declare Android Context");
            }
        }
        if (androidContext instanceof Application) {
            Koin b4 = koinApplication.b();
            b3 = CollectionsKt__CollectionsJVMKt.b(ModuleDSLKt.b(false, new KoinExtKt$androidContext$1(androidContext), 1, null));
            Koin.f(b4, b3, false, 2, null);
        } else {
            Koin b5 = koinApplication.b();
            b2 = CollectionsKt__CollectionsJVMKt.b(ModuleDSLKt.b(false, new KoinExtKt$androidContext$2(androidContext), 1, null));
            Koin.f(b5, b2, false, 2, null);
        }
        return koinApplication;
    }
}
