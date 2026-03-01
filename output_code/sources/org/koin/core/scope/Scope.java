package org.koin.core.scope;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.error.ClosedScopeException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.InstanceContext;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.ext.KClassExtKt;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Scope {

    /* renamed from: a  reason: collision with root package name */
    private final Qualifier f2586a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2587b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f2588c;

    /* renamed from: d  reason: collision with root package name */
    private final Koin f2589d;

    /* renamed from: e  reason: collision with root package name */
    private final ArrayList<Scope> f2590e;

    /* renamed from: f  reason: collision with root package name */
    private Object f2591f;

    /* renamed from: g  reason: collision with root package name */
    private final ArrayList<ScopeCallback> f2592g;

    /* renamed from: h  reason: collision with root package name */
    private final ArrayDeque<ParametersHolder> f2593h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2594i;

    public Scope(Qualifier scopeQualifier, String id, boolean z2, Koin _koin) {
        Intrinsics.f(scopeQualifier, "scopeQualifier");
        Intrinsics.f(id, "id");
        Intrinsics.f(_koin, "_koin");
        this.f2586a = scopeQualifier;
        this.f2587b = id;
        this.f2588c = z2;
        this.f2589d = _koin;
        this.f2590e = new ArrayList<>();
        this.f2592g = new ArrayList<>();
        this.f2593h = new ArrayDeque<>();
    }

    private final <T> T d(KClass<?> kClass, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Iterator<Scope> it = this.f2590e.iterator();
        T t2 = null;
        while (it.hasNext() && (t2 = (T) it.next().i(kClass, qualifier, function0)) == null) {
        }
        return t2;
    }

    private final <T> T n(Qualifier qualifier, KClass<?> kClass, Function0<? extends ParametersHolder> function0) {
        ParametersHolder parametersHolder;
        if (!this.f2594i) {
            if (function0 != null) {
                parametersHolder = function0.mo2invoke();
            } else {
                parametersHolder = null;
            }
            if (parametersHolder != null) {
                Logger c2 = this.f2589d.c();
                Level level = Level.DEBUG;
                if (c2.b(level)) {
                    c2.a(level, "| >> parameters " + parametersHolder + ' ');
                }
                KoinPlatformTools.f2602a.g(this, new Scope$resolveInstance$2(this, parametersHolder));
            }
            T t2 = (T) o(qualifier, kClass, new InstanceContext(this.f2589d.c(), this, parametersHolder), function0);
            if (parametersHolder != null) {
                Logger c3 = this.f2589d.c();
                Level level2 = Level.DEBUG;
                if (c3.b(level2)) {
                    c3.a(level2, "| << parameters");
                }
                KoinPlatformTools.f2602a.g(this, new Scope$resolveInstance$3(this));
            }
            return t2;
        }
        throw new ClosedScopeException("Scope '" + this.f2587b + "' is closed");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    private final <T> T o(Qualifier qualifier, KClass<?> kClass, InstanceContext instanceContext, Function0<? extends ParametersHolder> function0) {
        Object obj;
        T t2 = (T) this.f2589d.b().h(qualifier, kClass, this.f2586a, instanceContext);
        if (t2 == null) {
            Logger c2 = this.f2589d.c();
            String str = "|- ? t:'" + KClassExtKt.a(kClass) + "' - q:'" + qualifier + "' look in injected parameters";
            Level level = Level.DEBUG;
            if (c2.b(level)) {
                c2.a(level, str);
            }
            ParametersHolder g2 = this.f2593h.g();
            Object obj2 = null;
            if (g2 != null) {
                t2 = (T) g2.a(kClass);
            } else {
                t2 = null;
            }
            if (t2 == null) {
                Logger c3 = this.f2589d.c();
                String str2 = "|- ? t:'" + KClassExtKt.a(kClass) + "' - q:'" + qualifier + "' look at scope source";
                if (c3.b(level)) {
                    c3.a(level, str2);
                }
                Object obj3 = this.f2591f;
                if (obj3 != null && Intrinsics.a(Reflection.b(obj3.getClass()), kClass) && qualifier == null && (obj = this.f2591f) != null) {
                    obj2 = obj;
                }
                t2 = obj2;
                if (t2 == null) {
                    Logger c4 = this.f2589d.c();
                    String str3 = "|- ? t:'" + KClassExtKt.a(kClass) + "' - q:'" + qualifier + "' look in other scopes";
                    if (c4.b(level)) {
                        c4.a(level, str3);
                    }
                    t2 = (T) d(kClass, qualifier, function0);
                    if (t2 == null) {
                        KoinPlatformTools.f2602a.g(this, new Scope$resolveValue$4$1(this));
                        Logger c5 = this.f2589d.c();
                        if (c5.b(level)) {
                            c5.a(level, "|- << parameters");
                        }
                        q(qualifier, kClass);
                        throw new KotlinNothingValueException();
                    }
                }
            }
        }
        return t2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
        if (r4 == null) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Void q(org.koin.core.qualifier.Qualifier r4, kotlin.reflect.KClass<?> r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L1a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = " & qualifier:'"
            r0.append(r1)
            r0.append(r4)
            r4 = 39
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            if (r4 != 0) goto L1c
        L1a:
            java.lang.String r4 = ""
        L1c:
            org.koin.core.error.NoBeanDefFoundException r0 = new org.koin.core.error.NoBeanDefFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "No definition found for class:'"
            r1.append(r2)
            java.lang.String r5 = org.koin.ext.KClassExtKt.a(r5)
            r1.append(r5)
            java.lang.String r5 = "' q:'"
            r1.append(r5)
            r1.append(r4)
            java.lang.String r4 = "'. Check your definitions!"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.Scope.q(org.koin.core.qualifier.Qualifier, kotlin.reflect.KClass):java.lang.Void");
    }

    public final void c() {
        KoinPlatformTools.f2602a.g(this, new Scope$close$1(this));
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002b, code lost:
        if (r2 == null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> T e(kotlin.reflect.KClass<?> r9, org.koin.core.qualifier.Qualifier r10, kotlin.jvm.functions.Function0<? extends org.koin.core.parameter.ParametersHolder> r11) {
        /*
            r8 = this;
            java.lang.String r0 = "clazz"
            kotlin.jvm.internal.Intrinsics.f(r9, r0)
            org.koin.core.Koin r0 = r8.f2589d
            org.koin.core.logger.Logger r0 = r0.c()
            org.koin.core.logger.Level r1 = org.koin.core.logger.Level.DEBUG
            boolean r0 = r0.b(r1)
            if (r0 == 0) goto L9b
            r0 = 39
            if (r10 == 0) goto L2d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = " with qualifier '"
            r2.append(r3)
            r2.append(r10)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            if (r2 != 0) goto L2f
        L2d:
            java.lang.String r2 = ""
        L2f:
            org.koin.core.Koin r3 = r8.f2589d
            org.koin.core.logger.Logger r3 = r3.c()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "|- '"
            r4.append(r5)
            java.lang.String r6 = org.koin.ext.KClassExtKt.a(r9)
            r4.append(r6)
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = " ..."
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r3.a(r1, r0)
            org.koin.mp.KoinPlatformTimeTools r0 = org.koin.mp.KoinPlatformTimeTools.f2601a
            long r2 = r0.a()
            java.lang.Object r10 = r8.n(r10, r9, r11)
            long r6 = r0.a()
            long r6 = r6 - r2
            double r2 = (double) r6
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            java.lang.Double.isNaN(r2)
            double r2 = r2 / r6
            org.koin.core.Koin r11 = r8.f2589d
            org.koin.core.logger.Logger r11 = r11.c()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r9 = org.koin.ext.KClassExtKt.a(r9)
            r0.append(r9)
            java.lang.String r9 = "' in "
            r0.append(r9)
            r0.append(r2)
            java.lang.String r9 = " ms"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r11.a(r1, r9)
            goto L9f
        L9b:
            java.lang.Object r10 = r8.n(r10, r9, r11)
        L9f:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.Scope.e(kotlin.reflect.KClass, org.koin.core.qualifier.Qualifier, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        Scope scope = (Scope) obj;
        return Intrinsics.a(this.f2586a, scope.f2586a) && Intrinsics.a(this.f2587b, scope.f2587b) && this.f2588c == scope.f2588c && Intrinsics.a(this.f2589d, scope.f2589d);
    }

    public final boolean f() {
        return this.f2594i;
    }

    public final String g() {
        return this.f2587b;
    }

    public final Logger h() {
        return this.f2589d.c();
    }

    public int hashCode() {
        int hashCode = ((this.f2586a.hashCode() * 31) + this.f2587b.hashCode()) * 31;
        boolean z2 = this.f2588c;
        if (z2) {
            z2 = true;
        }
        int i2 = z2 ? 1 : 0;
        int i3 = z2 ? 1 : 0;
        return ((hashCode + i2) * 31) + this.f2589d.hashCode();
    }

    public final <T> T i(KClass<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Intrinsics.f(clazz, "clazz");
        try {
            return (T) e(clazz, qualifier, function0);
        } catch (ClosedScopeException unused) {
            Logger c2 = this.f2589d.c();
            String str = "* Scope closed - no instance found for " + KClassExtKt.a(clazz) + " on scope " + this;
            Level level = Level.DEBUG;
            if (!c2.b(level)) {
                return null;
            }
            c2.a(level, str);
            return null;
        } catch (NoBeanDefFoundException unused2) {
            Logger c3 = this.f2589d.c();
            String str2 = "* No instance found for " + KClassExtKt.a(clazz) + " on scope " + this;
            Level level2 = Level.DEBUG;
            if (!c3.b(level2)) {
                return null;
            }
            c3.a(level2, str2);
            return null;
        }
    }

    public final Qualifier j() {
        return this.f2586a;
    }

    public final Koin k() {
        return this.f2589d;
    }

    public final ArrayDeque<ParametersHolder> l() {
        return this.f2593h;
    }

    public final boolean m() {
        return !f();
    }

    public final void p(Object obj) {
        this.f2591f = obj;
    }

    public String toString() {
        return "['" + this.f2587b + "']";
    }
}
