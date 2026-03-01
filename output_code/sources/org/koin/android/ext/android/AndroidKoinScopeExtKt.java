package org.koin.android.ext.android;

import android.content.ComponentCallbacks;
import kotlin.jvm.internal.Intrinsics;
import org.koin.android.scope.AndroidScopeComponent;
import org.koin.core.component.KoinComponent;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.context.GlobalContext;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidKoinScopeExtKt {
    public static final Scope a(ComponentCallbacks componentCallbacks) {
        Intrinsics.f(componentCallbacks, "<this>");
        if (componentCallbacks instanceof AndroidScopeComponent) {
            return ((AndroidScopeComponent) componentCallbacks).a();
        }
        if (componentCallbacks instanceof KoinScopeComponent) {
            return ((KoinScopeComponent) componentCallbacks).a();
        }
        if (componentCallbacks instanceof KoinComponent) {
            return ((KoinComponent) componentCallbacks).getKoin().d().c();
        }
        return GlobalContext.f2528a.get().d().c();
    }
}
