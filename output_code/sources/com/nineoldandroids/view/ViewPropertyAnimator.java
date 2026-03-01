package com.nineoldandroids.view;

import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.WeakHashMap;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ViewPropertyAnimator {

    /* renamed from: a  reason: collision with root package name */
    private static final WeakHashMap<View, ViewPropertyAnimator> f338a = new WeakHashMap<>(0);

    public static ViewPropertyAnimator b(View view) {
        WeakHashMap<View, ViewPropertyAnimator> weakHashMap = f338a;
        ViewPropertyAnimator viewPropertyAnimator = weakHashMap.get(view);
        if (viewPropertyAnimator == null) {
            int intValue = Integer.valueOf(Build.VERSION.SDK).intValue();
            if (intValue >= 14) {
                viewPropertyAnimator = new ViewPropertyAnimatorICS(view);
            } else if (intValue >= 11) {
                viewPropertyAnimator = new ViewPropertyAnimatorHC(view);
            } else {
                viewPropertyAnimator = new ViewPropertyAnimatorPreHC(view);
            }
            weakHashMap.put(view, viewPropertyAnimator);
        }
        return viewPropertyAnimator;
    }

    public abstract ViewPropertyAnimator a(float f2);

    public abstract ViewPropertyAnimator c(long j2);

    public abstract ViewPropertyAnimator d(Interpolator interpolator);

    public abstract ViewPropertyAnimator e(long j2);

    public abstract void f();

    public abstract ViewPropertyAnimator g(float f2);
}
