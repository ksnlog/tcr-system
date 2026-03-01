package org.ligi.passandroid;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class App$onCreate$1 extends Lambda implements Function1<KoinApplication, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ App f2617d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public App$onCreate$1(App app) {
        super(1);
        this.f2617d = app;
    }

    public final void a(KoinApplication startKoin) {
        Intrinsics.f(startKoin, "$this$startKoin");
        KoinExtKt.a(startKoin, this.f2617d);
        startKoin.e(this.f2617d.c());
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(KoinApplication koinApplication) {
        a(koinApplication);
        return Unit.f763a;
    }
}
