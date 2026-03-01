package org.koin.androidx.scope;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.a;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.scope.Scope;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LifecycleViewModelScopeDelegate {

    /* renamed from: org.koin.androidx.scope.LifecycleViewModelScopeDelegate$2  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class AnonymousClass2 implements DefaultLifecycleObserver {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ ScopeHandlerViewModel f2518d;

        public /* synthetic */ void a(LifecycleOwner lifecycleOwner) {
            a.d(this, lifecycleOwner);
        }

        public /* synthetic */ void b(LifecycleOwner lifecycleOwner) {
            a.b(this, lifecycleOwner);
        }

        public void c(LifecycleOwner owner) {
            Intrinsics.f(owner, "owner");
            if (this.f2518d.f() == null) {
                this.f2518d.g((Scope) LifecycleViewModelScopeDelegate.a(null).f(LifecycleViewModelScopeDelegate.b(null)));
            }
            LifecycleViewModelScopeDelegate.c(null, this.f2518d.f());
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

    public static final /* synthetic */ Function1 a(LifecycleViewModelScopeDelegate lifecycleViewModelScopeDelegate) {
        throw null;
    }

    public static final /* synthetic */ Koin b(LifecycleViewModelScopeDelegate lifecycleViewModelScopeDelegate) {
        throw null;
    }

    public static final /* synthetic */ void c(LifecycleViewModelScopeDelegate lifecycleViewModelScopeDelegate, Scope scope) {
        throw null;
    }
}
