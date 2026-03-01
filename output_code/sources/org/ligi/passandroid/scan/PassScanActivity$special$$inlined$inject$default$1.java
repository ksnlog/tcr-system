package org.ligi.passandroid.scan;

import android.content.ComponentCallbacks;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.ligi.passandroid.scan.events.PassScanEventChannelProvider;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassScanActivity$special$$inlined$inject$default$1 extends Lambda implements Function0<PassScanEventChannelProvider> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ComponentCallbacks f2682d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2683e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2684f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassScanActivity$special$$inlined$inject$default$1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2682d = componentCallbacks;
        this.f2683e = qualifier;
        this.f2684f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, org.ligi.passandroid.scan.events.PassScanEventChannelProvider] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final PassScanEventChannelProvider mo2invoke() {
        ComponentCallbacks componentCallbacks = this.f2682d;
        return AndroidKoinScopeExtKt.a(componentCallbacks).e(Reflection.b(PassScanEventChannelProvider.class), this.f2683e, this.f2684f);
    }
}
