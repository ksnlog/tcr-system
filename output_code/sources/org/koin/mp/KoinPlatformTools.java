package org.koin.mp;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;
import org.koin.core.context.GlobalContext;
import org.koin.core.context.KoinContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KoinPlatformTools {

    /* renamed from: a  reason: collision with root package name */
    public static final KoinPlatformTools f2602a = new KoinPlatformTools();

    private KoinPlatformTools() {
    }

    public final KoinContext a() {
        return GlobalContext.f2528a;
    }

    public final LazyThreadSafetyMode b() {
        return LazyThreadSafetyMode.SYNCHRONIZED;
    }

    public final String c() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.e(uuid, "randomUUID().toString()");
        return uuid;
    }

    public final String d(KClass<?> kClass) {
        Intrinsics.f(kClass, "kClass");
        String name = JvmClassMappingKt.a(kClass).getName();
        Intrinsics.e(name, "kClass.java.name");
        return name;
    }

    public final String e(Exception e2) {
        String B;
        boolean p2;
        Intrinsics.f(e2, "e");
        StringBuilder sb = new StringBuilder();
        sb.append(e2);
        sb.append("\n\t");
        StackTraceElement[] stackTrace = e2.getStackTrace();
        Intrinsics.e(stackTrace, "e.stackTrace");
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            Intrinsics.e(className, "it.className");
            p2 = StringsKt__StringsKt.p(className, "sun.reflect", false, 2, null);
            if (!(!p2)) {
                break;
            }
            arrayList.add(stackTraceElement);
        }
        B = CollectionsKt___CollectionsKt.B(arrayList, "\n\t", null, null, 0, null, null, 62, null);
        sb.append(B);
        return sb.toString();
    }

    public final <K, V> Map<K, V> f() {
        return new ConcurrentHashMap();
    }

    public final <R> R g(Object lock, Function0<? extends R> block) {
        R mo2invoke;
        Intrinsics.f(lock, "lock");
        Intrinsics.f(block, "block");
        synchronized (lock) {
            mo2invoke = block.mo2invoke();
        }
        return mo2invoke;
    }
}
