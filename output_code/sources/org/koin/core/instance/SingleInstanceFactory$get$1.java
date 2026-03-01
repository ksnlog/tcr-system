package org.koin.core.instance;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SingleInstanceFactory$get$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ SingleInstanceFactory<T> f2557d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ InstanceContext f2558e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleInstanceFactory$get$1(SingleInstanceFactory<T> singleInstanceFactory, InstanceContext instanceContext) {
        super(0);
        this.f2557d = singleInstanceFactory;
        this.f2558e = instanceContext;
    }

    public final void a() {
        if (!this.f2557d.f(this.f2558e)) {
            SingleInstanceFactory<T> singleInstanceFactory = this.f2557d;
            ((SingleInstanceFactory) singleInstanceFactory).f2556c = singleInstanceFactory.a(this.f2558e);
        }
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
