package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Keyframe implements Cloneable {

    /* renamed from: d  reason: collision with root package name */
    float f269d;

    /* renamed from: e  reason: collision with root package name */
    Class f270e;

    /* renamed from: f  reason: collision with root package name */
    private Interpolator f271f = null;

    /* renamed from: g  reason: collision with root package name */
    boolean f272g = false;

    public static Keyframe f(float f2) {
        return new FloatKeyframe(f2);
    }

    public static Keyframe g(float f2, float f3) {
        return new FloatKeyframe(f2, f3);
    }

    @Override // 
    /* renamed from: a */
    public abstract Keyframe clone();

    public float b() {
        return this.f269d;
    }

    public Interpolator c() {
        return this.f271f;
    }

    public abstract Object d();

    public boolean e() {
        return this.f272g;
    }

    public void h(Interpolator interpolator) {
        this.f271f = interpolator;
    }

    public abstract void i(Object obj);

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class FloatKeyframe extends Keyframe {

        /* renamed from: h  reason: collision with root package name */
        float f273h;

        FloatKeyframe(float f2, float f3) {
            this.f269d = f2;
            this.f273h = f3;
            this.f270e = Float.TYPE;
            this.f272g = true;
        }

        @Override // com.nineoldandroids.animation.Keyframe
        public Object d() {
            return Float.valueOf(this.f273h);
        }

        @Override // com.nineoldandroids.animation.Keyframe
        public void i(Object obj) {
            if (obj != null && obj.getClass() == Float.class) {
                this.f273h = ((Float) obj).floatValue();
                this.f272g = true;
            }
        }

        @Override // com.nineoldandroids.animation.Keyframe
        /* renamed from: j */
        public FloatKeyframe clone() {
            FloatKeyframe floatKeyframe = new FloatKeyframe(b(), this.f273h);
            floatKeyframe.h(c());
            return floatKeyframe;
        }

        public float l() {
            return this.f273h;
        }

        FloatKeyframe(float f2) {
            this.f269d = f2;
            this.f270e = Float.TYPE;
        }
    }
}
