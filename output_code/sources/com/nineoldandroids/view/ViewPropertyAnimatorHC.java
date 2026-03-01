package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ViewPropertyAnimatorHC extends ViewPropertyAnimator {

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference<View> f339b;

    /* renamed from: c  reason: collision with root package name */
    private long f340c;

    /* renamed from: g  reason: collision with root package name */
    private Interpolator f344g;

    /* renamed from: d  reason: collision with root package name */
    private boolean f341d = false;

    /* renamed from: e  reason: collision with root package name */
    private long f342e = 0;

    /* renamed from: f  reason: collision with root package name */
    private boolean f343f = false;

    /* renamed from: h  reason: collision with root package name */
    private boolean f345h = false;

    /* renamed from: i  reason: collision with root package name */
    private Animator.AnimatorListener f346i = null;

    /* renamed from: j  reason: collision with root package name */
    private AnimatorEventListener f347j = new AnimatorEventListener();

    /* renamed from: k  reason: collision with root package name */
    ArrayList<NameValuesHolder> f348k = new ArrayList<>();

    /* renamed from: l  reason: collision with root package name */
    private Runnable f349l = new Runnable() { // from class: com.nineoldandroids.view.ViewPropertyAnimatorHC.1
        @Override // java.lang.Runnable
        public void run() {
            ViewPropertyAnimatorHC.this.r();
        }
    };

    /* renamed from: m  reason: collision with root package name */
    private HashMap<Animator, PropertyBundle> f350m = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class AnimatorEventListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private AnimatorEventListener() {
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void a(Animator animator) {
            if (ViewPropertyAnimatorHC.this.f346i != null) {
                ViewPropertyAnimatorHC.this.f346i.a(animator);
            }
            ViewPropertyAnimatorHC.this.f350m.remove(animator);
            if (ViewPropertyAnimatorHC.this.f350m.isEmpty()) {
                ViewPropertyAnimatorHC.this.f346i = null;
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void b(Animator animator) {
            if (ViewPropertyAnimatorHC.this.f346i != null) {
                ViewPropertyAnimatorHC.this.f346i.b(animator);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void c(Animator animator) {
            if (ViewPropertyAnimatorHC.this.f346i != null) {
                ViewPropertyAnimatorHC.this.f346i.c(animator);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void d(Animator animator) {
            if (ViewPropertyAnimatorHC.this.f346i != null) {
                ViewPropertyAnimatorHC.this.f346i.d(animator);
            }
        }

        @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void e(ValueAnimator valueAnimator) {
            View view;
            float z2 = valueAnimator.z();
            PropertyBundle propertyBundle = (PropertyBundle) ViewPropertyAnimatorHC.this.f350m.get(valueAnimator);
            if ((propertyBundle.f356a & 511) != 0 && (view = (View) ViewPropertyAnimatorHC.this.f339b.get()) != null) {
                view.invalidate();
            }
            ArrayList<NameValuesHolder> arrayList = propertyBundle.f357b;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    NameValuesHolder nameValuesHolder = arrayList.get(i2);
                    ViewPropertyAnimatorHC.this.q(nameValuesHolder.f353a, nameValuesHolder.f354b + (nameValuesHolder.f355c * z2));
                }
            }
            View view2 = (View) ViewPropertyAnimatorHC.this.f339b.get();
            if (view2 != null) {
                view2.invalidate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class NameValuesHolder {

        /* renamed from: a  reason: collision with root package name */
        int f353a;

        /* renamed from: b  reason: collision with root package name */
        float f354b;

        /* renamed from: c  reason: collision with root package name */
        float f355c;

        NameValuesHolder(int i2, float f2, float f3) {
            this.f353a = i2;
            this.f354b = f2;
            this.f355c = f3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class PropertyBundle {

        /* renamed from: a  reason: collision with root package name */
        int f356a;

        /* renamed from: b  reason: collision with root package name */
        ArrayList<NameValuesHolder> f357b;

        PropertyBundle(int i2, ArrayList<NameValuesHolder> arrayList) {
            this.f356a = i2;
            this.f357b = arrayList;
        }

        boolean a(int i2) {
            ArrayList<NameValuesHolder> arrayList;
            if ((this.f356a & i2) != 0 && (arrayList = this.f357b) != null) {
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (this.f357b.get(i3).f353a == i2) {
                        this.f357b.remove(i3);
                        this.f356a = (i2 ^ (-1)) & this.f356a;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPropertyAnimatorHC(View view) {
        this.f339b = new WeakReference<>(view);
    }

    private void n(int i2, float f2) {
        float p2 = p(i2);
        o(i2, p2, f2 - p2);
    }

    private void o(int i2, float f2, float f3) {
        Animator animator;
        if (this.f350m.size() > 0) {
            Iterator<Animator> it = this.f350m.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    animator = it.next();
                    PropertyBundle propertyBundle = this.f350m.get(animator);
                    if (propertyBundle.a(i2) && propertyBundle.f356a == 0) {
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
        this.f348k.add(new NameValuesHolder(i2, f2, f3));
        View view = this.f339b.get();
        if (view != null) {
            view.removeCallbacks(this.f349l);
            view.post(this.f349l);
        }
    }

    private float p(int i2) {
        View view = this.f339b.get();
        if (view != null) {
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
                                                    return view.getAlpha();
                                                }
                                                return 0.0f;
                                            }
                                            return view.getY();
                                        }
                                        return view.getX();
                                    }
                                    return view.getRotationY();
                                }
                                return view.getRotationX();
                            }
                            return view.getRotation();
                        }
                        return view.getScaleY();
                    }
                    return view.getScaleX();
                }
                return view.getTranslationY();
            }
            return view.getTranslationX();
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i2, float f2) {
        View view = this.f339b.get();
        if (view != null) {
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
                                                    view.setAlpha(f2);
                                                    return;
                                                }
                                                return;
                                            }
                                            view.setY(f2);
                                            return;
                                        }
                                        view.setX(f2);
                                        return;
                                    }
                                    view.setRotationY(f2);
                                    return;
                                }
                                view.setRotationX(f2);
                                return;
                            }
                            view.setRotation(f2);
                            return;
                        }
                        view.setScaleY(f2);
                        return;
                    }
                    view.setScaleX(f2);
                    return;
                }
                view.setTranslationY(f2);
                return;
            }
            view.setTranslationX(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ValueAnimator D = ValueAnimator.D(1.0f);
        ArrayList arrayList = (ArrayList) this.f348k.clone();
        this.f348k.clear();
        int size = arrayList.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 |= ((NameValuesHolder) arrayList.get(i3)).f353a;
        }
        this.f350m.put(D, new PropertyBundle(i2, arrayList));
        D.t(this.f347j);
        D.a(this.f347j);
        if (this.f343f) {
            D.L(this.f342e);
        }
        if (this.f341d) {
            D.f(this.f340c);
        }
        if (this.f345h) {
            D.I(this.f344g);
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
            this.f341d = true;
            this.f340c = j2;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j2);
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator d(Interpolator interpolator) {
        this.f345h = true;
        this.f344g = interpolator;
        return this;
    }

    @Override // com.nineoldandroids.view.ViewPropertyAnimator
    public ViewPropertyAnimator e(long j2) {
        if (j2 >= 0) {
            this.f343f = true;
            this.f342e = j2;
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
