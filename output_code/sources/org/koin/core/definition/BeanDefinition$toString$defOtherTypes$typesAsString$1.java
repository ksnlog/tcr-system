package org.koin.core.definition;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.koin.ext.KClassExtKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class BeanDefinition$toString$defOtherTypes$typesAsString$1 extends Lambda implements Function1<KClass<?>, CharSequence> {

    /* renamed from: d  reason: collision with root package name */
    public static final BeanDefinition$toString$defOtherTypes$typesAsString$1 f2538d = new BeanDefinition$toString$defOtherTypes$typesAsString$1();

    BeanDefinition$toString$defOtherTypes$typesAsString$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: a */
    public final CharSequence f(KClass<?> it) {
        Intrinsics.f(it, "it");
        return KClassExtKt.a(it);
    }
}
