package org.koin.core.definition;

import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BeanDefinition<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Qualifier f2531a;

    /* renamed from: b  reason: collision with root package name */
    private final KClass<?> f2532b;

    /* renamed from: c  reason: collision with root package name */
    private Qualifier f2533c;

    /* renamed from: d  reason: collision with root package name */
    private final Function2<Scope, ParametersHolder, T> f2534d;

    /* renamed from: e  reason: collision with root package name */
    private final Kind f2535e;

    /* renamed from: f  reason: collision with root package name */
    private List<? extends KClass<?>> f2536f;

    /* renamed from: g  reason: collision with root package name */
    private Callbacks<T> f2537g;

    /* JADX WARN: Multi-variable type inference failed */
    public BeanDefinition(Qualifier scopeQualifier, KClass<?> primaryType, Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition, Kind kind, List<? extends KClass<?>> secondaryTypes) {
        Intrinsics.f(scopeQualifier, "scopeQualifier");
        Intrinsics.f(primaryType, "primaryType");
        Intrinsics.f(definition, "definition");
        Intrinsics.f(kind, "kind");
        Intrinsics.f(secondaryTypes, "secondaryTypes");
        this.f2531a = scopeQualifier;
        this.f2532b = primaryType;
        this.f2533c = qualifier;
        this.f2534d = definition;
        this.f2535e = kind;
        this.f2536f = secondaryTypes;
        this.f2537g = new Callbacks<>(null, 1, null);
    }

    public final Callbacks<T> a() {
        return this.f2537g;
    }

    public final Function2<Scope, ParametersHolder, T> b() {
        return this.f2534d;
    }

    public final KClass<?> c() {
        return this.f2532b;
    }

    public final Qualifier d() {
        return this.f2533c;
    }

    public final Qualifier e() {
        return this.f2531a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Intrinsics.d(obj, "null cannot be cast to non-null type org.koin.core.definition.BeanDefinition<*>");
        BeanDefinition beanDefinition = (BeanDefinition) obj;
        if (Intrinsics.a(this.f2532b, beanDefinition.f2532b) && Intrinsics.a(this.f2533c, beanDefinition.f2533c) && Intrinsics.a(this.f2531a, beanDefinition.f2531a)) {
            return true;
        }
        return false;
    }

    public final List<KClass<?>> f() {
        return this.f2536f;
    }

    public final void g(List<? extends KClass<?>> list) {
        Intrinsics.f(list, "<set-?>");
        this.f2536f = list;
    }

    public int hashCode() {
        int i2;
        Qualifier qualifier = this.f2533c;
        if (qualifier != null) {
            i2 = qualifier.hashCode();
        } else {
            i2 = 0;
        }
        return (((i2 * 31) + this.f2532b.hashCode()) * 31) + this.f2531a.hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0039, code lost:
        if (r2 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r15 = this;
            org.koin.core.definition.Kind r0 = r15.f2535e
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 39
            r1.append(r2)
            kotlin.reflect.KClass<?> r3 = r15.f2532b
            java.lang.String r3 = org.koin.ext.KClassExtKt.a(r3)
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            org.koin.core.qualifier.Qualifier r2 = r15.f2533c
            java.lang.String r3 = ""
            if (r2 == 0) goto L3b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = ",qualifier:"
            r2.append(r4)
            org.koin.core.qualifier.Qualifier r4 = r15.f2533c
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            if (r2 != 0) goto L3c
        L3b:
            r2 = r3
        L3c:
            org.koin.core.qualifier.Qualifier r4 = r15.f2531a
            org.koin.core.registry.ScopeRegistry$Companion r5 = org.koin.core.registry.ScopeRegistry.f2580e
            org.koin.core.qualifier.StringQualifier r5 = r5.a()
            boolean r4 = kotlin.jvm.internal.Intrinsics.a(r4, r5)
            if (r4 == 0) goto L4c
            r4 = r3
            goto L5f
        L4c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ",scope:"
            r4.append(r5)
            org.koin.core.qualifier.Qualifier r5 = r15.f2531a
            r4.append(r5)
            java.lang.String r4 = r4.toString()
        L5f:
            java.util.List<? extends kotlin.reflect.KClass<?>> r5 = r15.f2536f
            boolean r5 = r5.isEmpty()
            r5 = r5 ^ 1
            if (r5 == 0) goto L8b
            java.util.List<? extends kotlin.reflect.KClass<?>> r6 = r15.f2536f
            java.lang.String r7 = ","
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1 r12 = org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1.f2538d
            r13 = 30
            r14 = 0
            java.lang.String r3 = kotlin.collections.CollectionsKt.B(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = ",binds:"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
        L8b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 91
            r5.append(r6)
            r5.append(r0)
            r0 = 58
            r5.append(r0)
            r5.append(r1)
            r5.append(r2)
            r5.append(r4)
            r5.append(r3)
            r0 = 93
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.BeanDefinition.toString():java.lang.String");
    }
}
