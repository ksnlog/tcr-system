package org.koin.core.instance;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.InstanceCreationException;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.parameter.ParametersHolderKt;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class InstanceFactory<T> {

    /* renamed from: b  reason: collision with root package name */
    public static final Companion f2551b = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final BeanDefinition<T> f2552a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public InstanceFactory(BeanDefinition<T> beanDefinition) {
        Intrinsics.f(beanDefinition, "beanDefinition");
        this.f2552a = beanDefinition;
    }

    public T a(InstanceContext context) {
        Intrinsics.f(context, "context");
        Logger a2 = context.a();
        String str = "| (+) '" + this.f2552a + '\'';
        Level level = Level.DEBUG;
        if (a2.b(level)) {
            a2.a(level, str);
        }
        try {
            ParametersHolder b2 = context.b();
            if (b2 == null) {
                b2 = ParametersHolderKt.a();
            }
            return this.f2552a.b().invoke(context.c(), b2);
        } catch (Exception e2) {
            String e3 = KoinPlatformTools.f2602a.e(e2);
            Logger a3 = context.a();
            String str2 = "* Instance creation error : could not create instance for '" + this.f2552a + "': " + e3;
            Level level2 = Level.ERROR;
            if (a3.b(level2)) {
                a3.a(level2, str2);
            }
            throw new InstanceCreationException("Could not create instance for '" + this.f2552a + '\'', e2);
        }
    }

    public abstract T b(InstanceContext instanceContext);

    public final BeanDefinition<T> c() {
        return this.f2552a;
    }

    public boolean equals(Object obj) {
        InstanceFactory instanceFactory;
        BeanDefinition<T> beanDefinition = null;
        if (obj instanceof InstanceFactory) {
            instanceFactory = (InstanceFactory) obj;
        } else {
            instanceFactory = null;
        }
        if (instanceFactory != null) {
            beanDefinition = instanceFactory.f2552a;
        }
        return Intrinsics.a(this.f2552a, beanDefinition);
    }

    public int hashCode() {
        return this.f2552a.hashCode();
    }
}
