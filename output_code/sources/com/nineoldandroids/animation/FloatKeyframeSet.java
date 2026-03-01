package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Keyframe;
import java.util.ArrayList;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class FloatKeyframeSet extends KeyframeSet {

    /* renamed from: g  reason: collision with root package name */
    private float f265g;

    /* renamed from: h  reason: collision with root package name */
    private float f266h;

    /* renamed from: i  reason: collision with root package name */
    private float f267i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f268j;

    public FloatKeyframeSet(Keyframe.FloatKeyframe... floatKeyframeArr) {
        super(floatKeyframeArr);
        this.f268j = true;
    }

    @Override // com.nineoldandroids.animation.KeyframeSet
    public Object b(float f2) {
        return Float.valueOf(f(f2));
    }

    @Override // com.nineoldandroids.animation.KeyframeSet
    /* renamed from: e */
    public FloatKeyframeSet clone() {
        ArrayList<Keyframe> arrayList = this.f278e;
        int size = arrayList.size();
        Keyframe.FloatKeyframe[] floatKeyframeArr = new Keyframe.FloatKeyframe[size];
        for (int i2 = 0; i2 < size; i2++) {
            floatKeyframeArr[i2] = (Keyframe.FloatKeyframe) arrayList.get(i2).clone();
        }
        return new FloatKeyframeSet(floatKeyframeArr);
    }

    public float f(float f2) {
        int i2 = this.f274a;
        if (i2 == 2) {
            if (this.f268j) {
                this.f268j = false;
                this.f265g = ((Keyframe.FloatKeyframe) this.f278e.get(0)).l();
                float l2 = ((Keyframe.FloatKeyframe) this.f278e.get(1)).l();
                this.f266h = l2;
                this.f267i = l2 - this.f265g;
            }
            Interpolator interpolator = this.f277d;
            if (interpolator != null) {
                f2 = interpolator.getInterpolation(f2);
            }
            TypeEvaluator typeEvaluator = this.f279f;
            if (typeEvaluator == null) {
                return this.f265g + (f2 * this.f267i);
            }
            return ((Number) typeEvaluator.evaluate(f2, Float.valueOf(this.f265g), Float.valueOf(this.f266h))).floatValue();
        } else if (f2 <= 0.0f) {
            Keyframe.FloatKeyframe floatKeyframe = (Keyframe.FloatKeyframe) this.f278e.get(0);
            Keyframe.FloatKeyframe floatKeyframe2 = (Keyframe.FloatKeyframe) this.f278e.get(1);
            float l3 = floatKeyframe.l();
            float l4 = floatKeyframe2.l();
            float b2 = floatKeyframe.b();
            float b3 = floatKeyframe2.b();
            Interpolator c2 = floatKeyframe2.c();
            if (c2 != null) {
                f2 = c2.getInterpolation(f2);
            }
            float f3 = (f2 - b2) / (b3 - b2);
            TypeEvaluator typeEvaluator2 = this.f279f;
            if (typeEvaluator2 == null) {
                return l3 + (f3 * (l4 - l3));
            }
            return ((Number) typeEvaluator2.evaluate(f3, Float.valueOf(l3), Float.valueOf(l4))).floatValue();
        } else if (f2 >= 1.0f) {
            Keyframe.FloatKeyframe floatKeyframe3 = (Keyframe.FloatKeyframe) this.f278e.get(i2 - 2);
            Keyframe.FloatKeyframe floatKeyframe4 = (Keyframe.FloatKeyframe) this.f278e.get(this.f274a - 1);
            float l5 = floatKeyframe3.l();
            float l6 = floatKeyframe4.l();
            float b4 = floatKeyframe3.b();
            float b5 = floatKeyframe4.b();
            Interpolator c3 = floatKeyframe4.c();
            if (c3 != null) {
                f2 = c3.getInterpolation(f2);
            }
            float f4 = (f2 - b4) / (b5 - b4);
            TypeEvaluator typeEvaluator3 = this.f279f;
            if (typeEvaluator3 == null) {
                return l5 + (f4 * (l6 - l5));
            }
            return ((Number) typeEvaluator3.evaluate(f4, Float.valueOf(l5), Float.valueOf(l6))).floatValue();
        } else {
            Keyframe.FloatKeyframe floatKeyframe5 = (Keyframe.FloatKeyframe) this.f278e.get(0);
            int i3 = 1;
            while (true) {
                int i4 = this.f274a;
                if (i3 < i4) {
                    Keyframe.FloatKeyframe floatKeyframe6 = (Keyframe.FloatKeyframe) this.f278e.get(i3);
                    if (f2 < floatKeyframe6.b()) {
                        Interpolator c4 = floatKeyframe6.c();
                        if (c4 != null) {
                            f2 = c4.getInterpolation(f2);
                        }
                        float b6 = (f2 - floatKeyframe5.b()) / (floatKeyframe6.b() - floatKeyframe5.b());
                        float l7 = floatKeyframe5.l();
                        float l8 = floatKeyframe6.l();
                        TypeEvaluator typeEvaluator4 = this.f279f;
                        if (typeEvaluator4 == null) {
                            return l7 + (b6 * (l8 - l7));
                        }
                        return ((Number) typeEvaluator4.evaluate(b6, Float.valueOf(l7), Float.valueOf(l8))).floatValue();
                    }
                    i3++;
                    floatKeyframe5 = floatKeyframe6;
                } else {
                    return ((Number) this.f278e.get(i4 - 1).d()).floatValue();
                }
            }
        }
    }
}
