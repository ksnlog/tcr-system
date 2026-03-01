package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class ViewPropertyAnimatorICS extends ViewPropertyAnimator {

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference<android.view.ViewPropertyAnimator> f358b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPropertyAnimatorICS(View view) {
        this.f358b = new WeakReference<>(view.animate());
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator a(float f2) {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.alpha(f2);
        }
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator c(long j2) {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setDuration(j2);
        }
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator d(Interpolator interpolator) {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setInterpolator(interpolator);
        }
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator e(long j2) {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setStartDelay(j2);
        }
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public void f() {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.start();
        }
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator g(float f2) {
        android.view.ViewPropertyAnimator viewPropertyAnimator = this.f358b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationY(f2);
        }
        return this;
    }
}
