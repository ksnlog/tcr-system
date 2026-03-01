package org.koin.android.ext.koin;

import android.content.Context;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KoinExtKt$androidContext$2 extends Lambda implements Function1<Module, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Context f2515d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.koin.android.ext.koin.KoinExtKt$androidContext$2$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function2<Scope, ParametersHolder, Context> {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ Context f2516d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context) {
            super(2);
            this.f2516d = context;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a */
        public final Context invoke(Scope single, ParametersHolder it) {
            Intrinsics.f(single, "$this$single");
            Intrinsics.f(it, "it");
            return this.f2516d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KoinExtKt$androidContext$2(Context context) {
        super(1);
        this.f2515d = context;
    }

    public final void a(Module module) {
        List f2;
        Intrinsics.f(module, "$this$module");
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f2515d);
        StringQualifier a2 = ScopeRegistry.f2580e.a();
        Kind kind = Kind.Singleton;
        f2 = CollectionsKt__CollectionsKt.f();
        SingleInstanceFactory<?> singleInstanceFactory = new SingleInstanceFactory<>(new BeanDefinition(a2, Reflection.b(Context.class), null, anonymousClass1, kind, f2));
        module.f(singleInstanceFactory);
        if (module.e()) {
            module.g(singleInstanceFactory);
        }
        new KoinDefinition(module, singleInstanceFactory);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Module module) {
        a(module);
        return Unit.f763a;
    }
}
