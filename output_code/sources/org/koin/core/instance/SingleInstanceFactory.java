package org.koin.core.instance;

import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SingleInstanceFactory<T> extends InstanceFactory<T> {

    /* renamed from: c  reason: collision with root package name */
    private T f2556c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleInstanceFactory(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.f(beanDefinition, "beanDefinition");
    }

    private final T e() {
        T t2 = this.f2556c;
        if (t2 != null) {
            return t2;
        }
        throw new IllegalStateException("Single instance created couldn't return value".toString());
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T a(InstanceContext context) {
        Intrinsics.f(context, "context");
        if (this.f2556c == null) {
            return (T) super.a(context);
        }
        return e();
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T b(InstanceContext context) {
        Intrinsics.f(context, "context");
        KoinPlatformTools.f2602a.g(this, new SingleInstanceFactory$get$1(this, context));
        return e();
    }

    public boolean f(InstanceContext instanceContext) {
        return this.f2556c != null;
    }
}
