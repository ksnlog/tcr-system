package com.nineoldandroids.view.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AnimatorProxy extends Animation {

    /* renamed from: t  reason: collision with root package name */
    public static final boolean f379t;

    /* renamed from: u  reason: collision with root package name */
    private static final WeakHashMap<View, AnimatorProxy> f380u;

    /* renamed from: d  reason: collision with root package name */
    private final WeakReference<View> f381d;

    /* renamed from: f  reason: collision with root package name */
    private boolean f383f;

    /* renamed from: h  reason: collision with root package name */
    private float f385h;

    /* renamed from: i  reason: collision with root package name */
    private float f386i;

    /* renamed from: j  reason: collision with root package name */
    private float f387j;

    /* renamed from: k  reason: collision with root package name */
    private float f388k;

    /* renamed from: l  reason: collision with root package name */
    private float f389l;

    /* renamed from: o  reason: collision with root package name */
    private float f392o;

    /* renamed from: p  reason: collision with root package name */
    private float f393p;

    /* renamed from: e  reason: collision with root package name */
    private final Camera f382e = new Camera();

    /* renamed from: g  reason: collision with root package name */
    private float f384g = 1.0f;

    /* renamed from: m  reason: collision with root package name */
    private float f390m = 1.0f;

    /* renamed from: n  reason: collision with root package name */
    private float f391n = 1.0f;

    /* renamed from: q  reason: collision with root package name */
    private final RectF f394q = new RectF();

    /* renamed from: r  reason: collision with root package name */
    private final RectF f395r = new RectF();

    /* renamed from: s  reason: collision with root package name */
    private final Matrix f396s = new Matrix();

    static {
        boolean z2;
        if (Integer.valueOf(Build.VERSION.SDK).intValue() < 11) {
            z2 = true;
        } else {
            z2 = false;
        }
        f379t = z2;
        f380u = new WeakHashMap<>();
    }

    private AnimatorProxy(View view) {
        setDuration(0L);
        setFillAfter(true);
        view.setAnimation(this);
        this.f381d = new WeakReference<>(view);
    }

    private void E(Matrix matrix, View view) {
        float f2;
        float f3;
        float width = view.getWidth();
        float height = view.getHeight();
        boolean z2 = this.f383f;
        if (z2) {
            f2 = this.f385h;
        } else {
            f2 = width / 2.0f;
        }
        if (z2) {
            f3 = this.f386i;
        } else {
            f3 = height / 2.0f;
        }
        float f4 = this.f387j;
        float f5 = this.f388k;
        float f6 = this.f389l;
        if (f4 != 0.0f || f5 != 0.0f || f6 != 0.0f) {
            Camera camera = this.f382e;
            camera.save();
            camera.rotateX(f4);
            camera.rotateY(f5);
            camera.rotateZ(-f6);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f2, -f3);
            matrix.postTranslate(f2, f3);
        }
        float f7 = this.f390m;
        float f8 = this.f391n;
        if (f7 != 1.0f || f8 != 1.0f) {
            matrix.postScale(f7, f8);
            matrix.postTranslate((-(f2 / width)) * ((f7 * width) - width), (-(f3 / height)) * ((f8 * height) - height));
        }
        matrix.postTranslate(this.f392o, this.f393p);
    }

    public static AnimatorProxy F(View view) {
        WeakHashMap<View, AnimatorProxy> weakHashMap = f380u;
        AnimatorProxy animatorProxy = weakHashMap.get(view);
        if (animatorProxy == null || animatorProxy != view.getAnimation()) {
            AnimatorProxy animatorProxy2 = new AnimatorProxy(view);
            weakHashMap.put(view, animatorProxy2);
            return animatorProxy2;
        }
        return animatorProxy;
    }

    private void a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        Matrix matrix = this.f396s;
        matrix.reset();
        E(matrix, view);
        this.f396s.mapRect(rectF);
        rectF.offset(view.getLeft(), view.getTop());
        float f2 = rectF.right;
        float f3 = rectF.left;
        if (f2 < f3) {
            rectF.right = f3;
            rectF.left = f2;
        }
        float f4 = rectF.bottom;
        float f5 = rectF.top;
        if (f4 < f5) {
            rectF.top = f4;
            rectF.bottom = f5;
        }
    }

    private void q() {
        View view = this.f381d.get();
        if (view != null && view.getParent() != null) {
            RectF rectF = this.f395r;
            a(rectF, view);
            rectF.union(this.f394q);
            ((View) view.getParent()).invalidate((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
    }

    private void r() {
        View view = this.f381d.get();
        if (view != null) {
            a(this.f394q, view);
        }
    }

    public void A(float f2) {
        if (this.f392o != f2) {
            r();
            this.f392o = f2;
            q();
        }
    }

    public void B(float f2) {
        if (this.f393p != f2) {
            r();
            this.f393p = f2;
            q();
        }
    }

    public void C(float f2) {
        View view = this.f381d.get();
        if (view != null) {
            A(f2 - view.getLeft());
        }
    }

    public void D(float f2) {
        View view = this.f381d.get();
        if (view != null) {
            B(f2 - view.getTop());
        }
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        View view = this.f381d.get();
        if (view != null) {
            transformation.setAlpha(this.f384g);
            E(transformation.getMatrix(), view);
        }
    }

    public float b() {
        return this.f384g;
    }

    public float c() {
        return this.f385h;
    }

    public float d() {
        return this.f386i;
    }

    public float e() {
        return this.f389l;
    }

    public float f() {
        return this.f387j;
    }

    public float g() {
        return this.f388k;
    }

    public float h() {
        return this.f390m;
    }

    public float i() {
        return this.f391n;
    }

    public int j() {
        View view = this.f381d.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollX();
    }

    public int l() {
        View view = this.f381d.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollY();
    }

    public float m() {
        return this.f392o;
    }

    public float n() {
        return this.f393p;
    }

    public float o() {
        View view = this.f381d.get();
        if (view == null) {
            return 0.0f;
        }
        return view.getLeft() + this.f392o;
    }

    public float p() {
        View view = this.f381d.get();
        if (view == null) {
            return 0.0f;
        }
        return view.getTop() + this.f393p;
    }

    public void s(float f2) {
        if (this.f384g != f2) {
            this.f384g = f2;
            View view = this.f381d.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public void t(float f2) {
        if (!this.f383f || this.f385h != f2) {
            r();
            this.f383f = true;
            this.f385h = f2;
            q();
        }
    }

    public void u(float f2) {
        if (!this.f383f || this.f386i != f2) {
            r();
            this.f383f = true;
            this.f386i = f2;
            q();
        }
    }

    public void v(float f2) {
        if (this.f389l != f2) {
            r();
            this.f389l = f2;
            q();
        }
    }

    public void w(float f2) {
        if (this.f387j != f2) {
            r();
            this.f387j = f2;
            q();
        }
    }

    public void x(float f2) {
        if (this.f388k != f2) {
            r();
            this.f388k = f2;
            q();
        }
    }

    public void y(float f2) {
        if (this.f390m != f2) {
            r();
            this.f390m = f2;
            q();
        }
    }

    public void z(float f2) {
        if (this.f391n != f2) {
            r();
            this.f391n = f2;
            q();
        }
    }
}
