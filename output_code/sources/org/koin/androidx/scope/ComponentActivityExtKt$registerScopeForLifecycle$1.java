package org.koin.androidx.scope;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.a;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ComponentActivityExtKt$registerScopeForLifecycle$1 implements DefaultLifecycleObserver {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Scope f2517d;

    public /* synthetic */ void a(LifecycleOwner lifecycleOwner) {
        a.d(this, lifecycleOwner);
    }

    public void b(LifecycleOwner owner) {
        Intrinsics.f(owner, "owner");
        a.b(this, owner);
        this.f2517d.c();
    }

    public /* synthetic */ void c(LifecycleOwner lifecycleOwner) {
        a.a(this, lifecycleOwner);
    }

    public /* synthetic */ void e(LifecycleOwner lifecycleOwner) {
        a.c(this, lifecycleOwner);
    }

    public /* synthetic */ void g(LifecycleOwner lifecycleOwner) {
        a.e(this, lifecycleOwner);
    }

    public /* synthetic */ void h(LifecycleOwner lifecycleOwner) {
        a.f(this, lifecycleOwner);
    }
}
