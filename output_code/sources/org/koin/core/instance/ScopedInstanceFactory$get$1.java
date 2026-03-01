package org.koin.core.instance;

import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ScopedInstanceFactory$get$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ScopedInstanceFactory<T> f2554d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ InstanceContext f2555e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScopedInstanceFactory$get$1(ScopedInstanceFactory<T> scopedInstanceFactory, InstanceContext instanceContext) {
        super(0);
        this.f2554d = scopedInstanceFactory;
        this.f2555e = instanceContext;
    }

    public final void a() {
        HashMap hashMap;
        if (!this.f2554d.f(this.f2555e)) {
            hashMap = ((ScopedInstanceFactory) this.f2554d).f2553c;
            hashMap.put(this.f2555e.c().g(), this.f2554d.a(this.f2555e));
        }
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
