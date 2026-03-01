package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Keyframe;
import java.util.ArrayList;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class KeyframeSet {

    /* renamed from: a  reason: collision with root package name */
    int f274a;

    /* renamed from: b  reason: collision with root package name */
    Keyframe f275b;

    /* renamed from: c  reason: collision with root package name */
    Keyframe f276c;

    /* renamed from: d  reason: collision with root package name */
    Interpolator f277d;

    /* renamed from: e  reason: collision with root package name */
    ArrayList<Keyframe> f278e;

    /* renamed from: f  reason: collision with root package name */
    TypeEvaluator f279f;

    public KeyframeSet(Keyframe... keyframeArr) {
        this.f274a = keyframeArr.length;
        ArrayList<Keyframe> arrayList = new ArrayList<>();
        this.f278e = arrayList;
        arrayList.addAll(Arrays.asList(keyframeArr));
        this.f275b = this.f278e.get(0);
        Keyframe keyframe = this.f278e.get(this.f274a - 1);
        this.f276c = keyframe;
        this.f277d = keyframe.c();
    }

    public static KeyframeSet c(float... fArr) {
        int length = fArr.length;
        Keyframe.FloatKeyframe[] floatKeyframeArr = new Keyframe.FloatKeyframe[Math.max(length, 2)];
        if (length == 1) {
            floatKeyframeArr[0] = (Keyframe.FloatKeyframe) Keyframe.f(0.0f);
            floatKeyframeArr[1] = (Keyframe.FloatKeyframe) Keyframe.g(1.0f, fArr[0]);
        } else {
            floatKeyframeArr[0] = (Keyframe.FloatKeyframe) Keyframe.g(0.0f, fArr[0]);
            for (int i2 = 1; i2 < length; i2++) {
                floatKeyframeArr[i2] = (Keyframe.FloatKeyframe) Keyframe.g(i2 / (length - 1), fArr[i2]);
            }
        }
        return new FloatKeyframeSet(floatKeyframeArr);
    }

    public KeyframeSet a() {
        throw null;
    }

    public Object b(float f2) {
        throw null;
    }

    public void d(TypeEvaluator typeEvaluator) {
        this.f279f = typeEvaluator;
    }

    public String toString() {
        String str = " ";
        for (int i2 = 0; i2 < this.f274a; i2++) {
            str = str + this.f278e.get(i2).d() + "  ";
        }
        return str;
    }
}
