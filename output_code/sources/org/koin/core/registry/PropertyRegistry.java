package org.koin.core.registry;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PropertyRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final Koin f2578a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, Object> f2579b;

    public PropertyRegistry(Koin _koin) {
        Intrinsics.f(_koin, "_koin");
        this.f2578a = _koin;
        this.f2579b = KoinPlatformTools.f2602a.f();
    }
}
