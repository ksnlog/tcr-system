package org.koin.core.definition;

import kotlin.jvm.internal.Intrinsics;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.module.Module;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KoinDefinition<R> {

    /* renamed from: a  reason: collision with root package name */
    private final Module f2544a;

    /* renamed from: b  reason: collision with root package name */
    private final InstanceFactory<R> f2545b;

    public KoinDefinition(Module module, InstanceFactory<R> factory) {
        Intrinsics.f(module, "module");
        Intrinsics.f(factory, "factory");
        this.f2544a = module;
        this.f2545b = factory;
    }

    public final InstanceFactory<R> a() {
        return this.f2545b;
    }

    public final Module b() {
        return this.f2544a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KoinDefinition)) {
            return false;
        }
        KoinDefinition koinDefinition = (KoinDefinition) obj;
        return Intrinsics.a(this.f2544a, koinDefinition.f2544a) && Intrinsics.a(this.f2545b, koinDefinition.f2545b);
    }

    public int hashCode() {
        return (this.f2544a.hashCode() * 31) + this.f2545b.hashCode();
    }

    public String toString() {
        return "KoinDefinition(module=" + this.f2544a + ", factory=" + this.f2545b + ')';
    }
}
