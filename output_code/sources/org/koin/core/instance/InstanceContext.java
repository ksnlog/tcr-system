package org.koin.core.instance;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InstanceContext {

    /* renamed from: a  reason: collision with root package name */
    private final Logger f2548a;

    /* renamed from: b  reason: collision with root package name */
    private final Scope f2549b;

    /* renamed from: c  reason: collision with root package name */
    private final ParametersHolder f2550c;

    public InstanceContext(Logger logger, Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.f(logger, "logger");
        Intrinsics.f(scope, "scope");
        this.f2548a = logger;
        this.f2549b = scope;
        this.f2550c = parametersHolder;
    }

    public final Logger a() {
        return this.f2548a;
    }

    public final ParametersHolder b() {
        return this.f2550c;
    }

    public final Scope c() {
        return this.f2549b;
    }

    public /* synthetic */ InstanceContext(Logger logger, Scope scope, ParametersHolder parametersHolder, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger, scope, (i2 & 4) != 0 ? null : parametersHolder);
    }
}
