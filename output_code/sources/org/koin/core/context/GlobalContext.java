package org.koin.core.context;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.error.KoinAppAlreadyStartedException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class GlobalContext implements KoinContext {

    /* renamed from: a  reason: collision with root package name */
    public static final GlobalContext f2528a = new GlobalContext();

    /* renamed from: b  reason: collision with root package name */
    private static Koin f2529b;

    /* renamed from: c  reason: collision with root package name */
    private static KoinApplication f2530c;

    private GlobalContext() {
    }

    private final void b(KoinApplication koinApplication) {
        if (f2529b == null) {
            f2530c = koinApplication;
            f2529b = koinApplication.b();
            return;
        }
        throw new KoinAppAlreadyStartedException("A Koin Application has already been started");
    }

    @Override // org.koin.core.context.KoinContext
    public KoinApplication a(Function1<? super KoinApplication, Unit> appDeclaration) {
        KoinApplication a2;
        Intrinsics.f(appDeclaration, "appDeclaration");
        synchronized (this) {
            a2 = KoinApplication.f2525c.a();
            f2528a.b(a2);
            appDeclaration.f(a2);
            a2.a();
        }
        return a2;
    }

    @Override // org.koin.core.context.KoinContext
    public Koin get() {
        Koin koin = f2529b;
        if (koin != null) {
            return koin;
        }
        throw new IllegalStateException("KoinApplication has not been started".toString());
    }
}
