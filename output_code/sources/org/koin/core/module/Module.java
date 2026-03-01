package org.koin.core.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.qualifier.Qualifier;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Module {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f2566a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2567b;

    /* renamed from: c  reason: collision with root package name */
    private HashSet<SingleInstanceFactory<?>> f2568c;

    /* renamed from: d  reason: collision with root package name */
    private final HashMap<String, InstanceFactory<?>> f2569d;

    /* renamed from: e  reason: collision with root package name */
    private final HashSet<Qualifier> f2570e;

    /* renamed from: f  reason: collision with root package name */
    private final List<Module> f2571f;

    public Module() {
        this(false, 1, null);
    }

    public Module(boolean z2) {
        this.f2566a = z2;
        this.f2567b = KoinPlatformTools.f2602a.c();
        this.f2568c = new HashSet<>();
        this.f2569d = new HashMap<>();
        this.f2570e = new HashSet<>();
        this.f2571f = new ArrayList();
    }

    public final HashSet<SingleInstanceFactory<?>> a() {
        return this.f2568c;
    }

    public final List<Module> b() {
        return this.f2571f;
    }

    public final HashMap<String, InstanceFactory<?>> c() {
        return this.f2569d;
    }

    public final HashSet<Qualifier> d() {
        return this.f2570e;
    }

    public final boolean e() {
        return this.f2566a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Module.class == obj.getClass() && Intrinsics.a(this.f2567b, ((Module) obj).f2567b)) {
            return true;
        }
        return false;
    }

    public final void f(InstanceFactory<?> instanceFactory) {
        Intrinsics.f(instanceFactory, "instanceFactory");
        BeanDefinition<?> c2 = instanceFactory.c();
        h(BeanDefinitionKt.a(c2.c(), c2.d(), c2.e()), instanceFactory);
    }

    public final void g(SingleInstanceFactory<?> instanceFactory) {
        Intrinsics.f(instanceFactory, "instanceFactory");
        this.f2568c.add(instanceFactory);
    }

    public final void h(String mapping, InstanceFactory<?> factory) {
        Intrinsics.f(mapping, "mapping");
        Intrinsics.f(factory, "factory");
        this.f2569d.put(mapping, factory);
    }

    public int hashCode() {
        return this.f2567b.hashCode();
    }

    public /* synthetic */ Module(boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z2);
    }
}
