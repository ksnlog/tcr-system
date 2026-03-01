package org.koin.core.extension;

import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ExtensionManager {

    /* renamed from: a  reason: collision with root package name */
    private final Koin f2546a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<String, Object> f2547b;

    public ExtensionManager(Koin _koin) {
        Intrinsics.f(_koin, "_koin");
        this.f2546a = _koin;
        this.f2547b = new HashMap<>();
    }
}
