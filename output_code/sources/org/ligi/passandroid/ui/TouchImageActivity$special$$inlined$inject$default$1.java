package org.ligi.passandroid.ui;

import android.content.ComponentCallbacks;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.ligi.passandroid.model.PassStore;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TouchImageActivity$special$$inlined$inject$default$1 extends Lambda implements Function0<PassStore> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ComponentCallbacks f2879d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Qualifier f2880e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Function0 f2881f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchImageActivity$special$$inlined$inject$default$1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        super(0);
        this.f2879d = componentCallbacks;
        this.f2880e = qualifier;
        this.f2881f = function0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, org.ligi.passandroid.model.PassStore] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final PassStore mo2invoke() {
        ComponentCallbacks componentCallbacks = this.f2879d;
        return AndroidKoinScopeExtKt.a(componentCallbacks).e(Reflection.b(PassStore.class), this.f2880e, this.f2881f);
    }
}
