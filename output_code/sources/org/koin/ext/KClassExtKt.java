package org.koin.ext;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KClassExtKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<KClass<?>, String> f2600a = KoinPlatformTools.f2602a.f();

    public static final String a(KClass<?> kClass) {
        Intrinsics.f(kClass, "<this>");
        String str = f2600a.get(kClass);
        if (str == null) {
            return b(kClass);
        }
        return str;
    }

    public static final String b(KClass<?> kClass) {
        Intrinsics.f(kClass, "<this>");
        String d2 = KoinPlatformTools.f2602a.d(kClass);
        f2600a.put(kClass, d2);
        return d2;
    }
}
