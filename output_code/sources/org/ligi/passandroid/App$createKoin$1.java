package org.ligi.passandroid;

import com.squareup.moshi.Moshi;
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
import org.ligi.passandroid.model.AndroidFileSystemPassStore;
import org.ligi.passandroid.model.AndroidSettings;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.Settings;
import org.ligi.passandroid.scan.events.PassScanEventChannelProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class App$createKoin$1 extends Lambda implements Function1<Module, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ App f2612d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ligi.passandroid.App$createKoin$1$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function2<Scope, ParametersHolder, PassStore> {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ App f2613d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(App app) {
            super(2);
            this.f2613d = app;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a */
        public final PassStore invoke(Scope single, ParametersHolder it) {
            Moshi moshi;
            Intrinsics.f(single, "$this$single");
            Intrinsics.f(it, "it");
            moshi = this.f2613d.f2610d;
            Intrinsics.e(moshi, "moshi");
            return new AndroidFileSystemPassStore(this.f2613d, (Settings) single.e(Reflection.b(Settings.class), null, null), moshi);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ligi.passandroid.App$createKoin$1$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass2 extends Lambda implements Function2<Scope, ParametersHolder, Settings> {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ App f2614d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(App app) {
            super(2);
            this.f2614d = app;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a */
        public final Settings invoke(Scope single, ParametersHolder it) {
            AndroidSettings d2;
            Intrinsics.f(single, "$this$single");
            Intrinsics.f(it, "it");
            d2 = this.f2614d.d();
            Intrinsics.d(d2, "null cannot be cast to non-null type org.ligi.passandroid.model.Settings");
            return d2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ligi.passandroid.App$createKoin$1$3  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass3 extends Lambda implements Function2<Scope, ParametersHolder, Tracker> {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ App f2615d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(App app) {
            super(2);
            this.f2615d = app;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a */
        public final Tracker invoke(Scope single, ParametersHolder it) {
            Intrinsics.f(single, "$this$single");
            Intrinsics.f(it, "it");
            return TrackerKodeinModuleKt.a(this.f2615d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ligi.passandroid.App$createKoin$1$4  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass4 extends Lambda implements Function2<Scope, ParametersHolder, PassScanEventChannelProvider> {

        /* renamed from: d  reason: collision with root package name */
        public static final AnonymousClass4 f2616d = new AnonymousClass4();

        AnonymousClass4() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a */
        public final PassScanEventChannelProvider invoke(Scope single, ParametersHolder it) {
            Intrinsics.f(single, "$this$single");
            Intrinsics.f(it, "it");
            return new PassScanEventChannelProvider();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public App$createKoin$1(App app) {
        super(1);
        this.f2612d = app;
    }

    public final void a(Module module) {
        List f2;
        List f3;
        List f4;
        List f5;
        Intrinsics.f(module, "$this$module");
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f2612d);
        ScopeRegistry.Companion companion = ScopeRegistry.f2580e;
        StringQualifier a2 = companion.a();
        Kind kind = Kind.Singleton;
        f2 = CollectionsKt__CollectionsKt.f();
        SingleInstanceFactory<?> singleInstanceFactory = new SingleInstanceFactory<>(new BeanDefinition(a2, Reflection.b(PassStore.class), null, anonymousClass1, kind, f2));
        module.f(singleInstanceFactory);
        if (module.e()) {
            module.g(singleInstanceFactory);
        }
        new KoinDefinition(module, singleInstanceFactory);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.f2612d);
        StringQualifier a3 = companion.a();
        f3 = CollectionsKt__CollectionsKt.f();
        SingleInstanceFactory<?> singleInstanceFactory2 = new SingleInstanceFactory<>(new BeanDefinition(a3, Reflection.b(Settings.class), null, anonymousClass2, kind, f3));
        module.f(singleInstanceFactory2);
        if (module.e()) {
            module.g(singleInstanceFactory2);
        }
        new KoinDefinition(module, singleInstanceFactory2);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.f2612d);
        StringQualifier a4 = companion.a();
        f4 = CollectionsKt__CollectionsKt.f();
        SingleInstanceFactory<?> singleInstanceFactory3 = new SingleInstanceFactory<>(new BeanDefinition(a4, Reflection.b(Tracker.class), null, anonymousClass3, kind, f4));
        module.f(singleInstanceFactory3);
        if (module.e()) {
            module.g(singleInstanceFactory3);
        }
        new KoinDefinition(module, singleInstanceFactory3);
        AnonymousClass4 anonymousClass4 = AnonymousClass4.f2616d;
        StringQualifier a5 = companion.a();
        f5 = CollectionsKt__CollectionsKt.f();
        SingleInstanceFactory<?> singleInstanceFactory4 = new SingleInstanceFactory<>(new BeanDefinition(a5, Reflection.b(PassScanEventChannelProvider.class), null, anonymousClass4, kind, f5));
        module.f(singleInstanceFactory4);
        if (module.e()) {
            module.g(singleInstanceFactory4);
        }
        new KoinDefinition(module, singleInstanceFactory4);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Module module) {
        a(module);
        return Unit.f763a;
    }
}
