package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ViewPropertyAnimatorPreHC extends ViewPropertyAnimator {

    /* renamed from: b  reason: collision with root package name */
    private final AnimatorProxy f359b;

    /* renamed from: c  reason: collision with root package name */
    private final WeakReference<View> f360c;

    /* renamed from: d  reason: collision with root package name */
    private long f361d;

    /* renamed from: h  reason: collision with root package name */
    private Interpolator f365h;

    /* renamed from: e  reason: collision with root package name */
    private boolean f362e = false;

    /* renamed from: f  reason: collision with root package name */
    private long f363f = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f364g = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f366i = false;

    /* renamed from: j  reason: collision with root package name */
    private Animator.AnimatorListener f367j = null;

    /* renamed from: k  reason: collision with root package name */
    private AnimatorEventListener f368k = new AnimatorEventListener();

    /* renamed from: l  reason: collision with root package name */
    ArrayList<NameValuesHolder> f369l = new ArrayList<>();

    /* renamed from: m  reason: collision with root package name */
    private Runnable f370m = new Runnable() { // from class: com.nineoldandroids.view.ViewPropertyAnimatorPreHC.1
        @Override // java.lang.Runnable
        public void run() {
            ViewPropertyAnimatorPreHC.this.r();
        }
    };

    /* renamed from: n  reason: collision with root package name */
    private HashMap<Animator, PropertyBundle> f371n = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class AnimatorEventListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private AnimatorEventListener() {
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void a(Animator animator) {
            if (ViewPropertyAnimatorPreHC.this.f367j != null) {
                ViewPropertyAnimatorPreHC.this.f367j.a(animator);
            }
            ViewPropertyAnimatorPreHC.this.f371n.remove(animator);
            if (ViewPropertyAnimatorPreHC.this.f371n.isEmpty()) {
                ViewPropertyAnimatorPreHC.this.f367j = null;
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void b(Animator animator) {
            if (ViewPropertyAnimatorPreHC.this.f367j != null) {
                ViewPropertyAnimatorPreHC.this.f367j.b(animator);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void c(Animator animator) {
            if (ViewPropertyAnimatorPreHC.this.f367j != null) {
                ViewPropertyAnimatorPreHC.this.f367j.c(animator);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void d(Animator animator) {
            if (ViewPropertyAnimatorPreHC.this.f367j != null) {
                ViewPropertyAnimatorPreHC.this.f367j.d(animator);
            }
        }

        @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void e(ValueAnimator valueAnimator) {
            View view;
            float z2 = valueAnimator.z();
            PropertyBundle propertyBundle = (PropertyBundle) ViewPropertyAnimatorPreHC.this.f371n.get(valueAnimator);
            if ((propertyBundle.f377a & 511) != 0 && (view = (View) ViewPropertyAnimatorPreHC.this.f360c.get()) != null) {
                view.invalidate();
            }
            ArrayList<NameValuesHolder> arrayList = propertyBundle.f378b;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    NameValuesHolder nameValuesHolder = arrayList.get(i2);
                    ViewPropertyAnimatorPreHC.this.q(nameValuesHolder.f374a, nameValuesHolder.f375b + (nameValuesHolder.f376c * z2));
                }
            }
            View view2 = (View) ViewPropertyAnimatorPreHC.this.f360c.get();
            if (view2 != null) {
                view2.invalidate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class NameValuesHolder {

        /* renamed from: a  reason: collision with root package name */
        int f374a;

        /* renamed from: b  reason: collision with root package name */
        float f375b;

        /* renamed from: c  reason: collision with root package name */
        float f376c;

        NameValuesHolder(int i2, float f2, float f3) {
            this.f374a = i2;
            this.f375b = f2;
            this.f376c = f3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class PropertyBundle {

        /* renamed from: a  reason: collision with root package name */
        int f377a;

        /* renamed from: b  reason: collision with root package name */
        ArrayList<NameValuesHolder> f378b;

        PropertyBundle(int i2, ArrayList<NameValuesHolder> arrayList) {
            this.f377a = i2;
            this.f378b = arrayList;
        }

        boolean a(int i2) {
            ArrayList<NameValuesHolder> arrayList;
            if ((this.f377a & i2) != 0 && (arrayList = this.f378b) != null) {
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (this.f378b.get(i3).f374a == i2) {
                        this.f378b.remove(i3);
                        this.f377a = (i2 ^ (-1)) & this.f377a;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPropertyAnimatorPreHC(View view) {
        this.f360c = new WeakReference<>(view);
        this.f359b = AnimatorProxy.F(view);
    }

    private void n(int i2, float f2) {
        float p2 = p(i2);
        o(i2, p2, f2 - p2);
    }

    private void o(int i2, float f2, float f3) {
        Animator animator;
        if (this.f371n.size() > 0) {
            Iterator<Animator> it = this.f371n.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    animator = it.next();
                    PropertyBundle propertyBundle = this.f371n.get(animator);
                    if (propertyBundle.a(i2) && propertyBundle.f377a == 0) {
                        break;
                    }
                } else {
                    animator = null;
                    break;
                }
            }
            if (animator != null) {
                animator.b();
            }
        }
        this.f369l.add(new NameValuesHolder(i2, f2, f3));
        View view = this.f360c.get();
        if (view != null) {
            view.removeCallbacks(this.f370m);
            view.post(this.f370m);
        }
    }

    private float p(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 8) {
                        if (i2 != 16) {
                            if (i2 != 32) {
                                if (i2 != 64) {
                                    if (i2 != 128) {
                                        if (i2 != 256) {
                                            if (i2 != 512) {
                                                return 0.0f;
                                            }
                                            return this.f359b.b();
                                        }
                                        return this.f359b.p();
                                    }
                                    return this.f359b.o();
                                }
                                return this.f359b.g();
                            }
                            return this.f359b.f();
                        }
                        return this.f359b.e();
                    }
                    return this.f359b.i();
                }
                return this.f359b.h();
            }
            return this.f359b.n();
        }
        return this.f359b.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i2, float f2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 8) {
                        if (i2 != 16) {
                            if (i2 != 32) {
                                if (i2 != 64) {
                                    if (i2 != 128) {
                                        if (i2 != 256) {
                                            if (i2 == 512) {
                                                this.f359b.s(f2);
                                                return;
                                            }
                                            return;
                                        }
                                        this.f359b.D(f2);
                                        return;
                                    }
                                    this.f359b.C(f2);
                                    return;
                                }
                                this.f359b.x(f2);
                                return;
                            }
                            this.f359b.w(f2);
                            return;
                        }
                        this.f359b.v(f2);
                        return;
                    }
                    this.f359b.z(f2);
                    return;
                }
                this.f359b.y(f2);
                return;
            }
            this.f359b.B(f2);
            return;
        }
        this.f359b.A(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ValueAnimator D = ValueAnimator.D(1.0f);
        ArrayList arrayList = (ArrayList) this.f369l.clone();
        this.f369l.clear();
        int size = arrayList.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 |= ((NameValuesHolder) arrayList.get(i3)).f374a;
        }
        this.f371n.put(D, new PropertyBundle(i2, arrayList));
        D.t(this.f368k);
        D.a(this.f368k);
        if (this.f364g) {
            D.L(this.f363f);
        }
        if (this.f362e) {
            D.f(this.f361d);
        }
        if (this.f366i) {
            D.I(this.f365h);
        }
        D.g();
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator a(float f2) {
        n(512, f2);
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator c(long j2) {
        if (j2 >= 0) {
            this.f362e = true;
            this.f361d = j2;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j2);
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator d(Interpolator interpolator) {
        this.f366i = true;
        this.f365h = interpolator;
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator e(long j2) {
        if (j2 >= 0) {
            this.f364g = true;
            this.f363f = j2;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j2);
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public void f() {
        r();
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator g(float f2) {
        n(2, f2);
        return this;
    }
}
