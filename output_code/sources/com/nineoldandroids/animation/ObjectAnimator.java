package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ObjectAnimator extends ValueAnimator {
    private static final Map<String, Property> K;
    private Object H;
    private String I;
    private Property J;

    static {
        HashMap hashMap = new HashMap();
        K = hashMap;
        hashMap.put("alpha", PreHoneycombCompat.f280a);
        hashMap.put("pivotX", PreHoneycombCompat.f281b);
        hashMap.put("pivotY", PreHoneycombCompat.f282c);
        hashMap.put("translationX", PreHoneycombCompat.f283d);
        hashMap.put("translationY", PreHoneycombCompat.f284e);
        hashMap.put("rotation", PreHoneycombCompat.f285f);
        hashMap.put("rotationX", PreHoneycombCompat.f286g);
        hashMap.put("rotationY", PreHoneycombCompat.f287h);
        hashMap.put("scaleX", PreHoneycombCompat.f288i);
        hashMap.put("scaleY", PreHoneycombCompat.f289j);
        hashMap.put("scrollX", PreHoneycombCompat.f290k);
        hashMap.put("scrollY", PreHoneycombCompat.f291l);
        hashMap.put("x", PreHoneycombCompat.f292m);
        hashMap.put("y", PreHoneycombCompat.f293n);
    }

    public ObjectAnimator() {
    }

    public static ObjectAnimator Q(Object obj, String str, float... fArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.H(fArr);
        return objectAnimator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.nineoldandroids.animation.ValueAnimator
    public void B() {
        if (!this.f327o) {
            if (this.J == null && AnimatorProxy.f379t && (this.H instanceof View)) {
                Map<String, Property> map = K;
                if (map.containsKey(this.I)) {
                    S(map.get(this.I));
                }
            }
            int length = this.f334v.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.f334v[i2].q(this.H);
            }
            super.B();
        }
    }

    @Override // com.nineoldandroids.animation.ValueAnimator
    public void H(float... fArr) {
        PropertyValuesHolder[] propertyValuesHolderArr = this.f334v;
        if (propertyValuesHolderArr != null && propertyValuesHolderArr.length != 0) {
            super.H(fArr);
            return;
        }
        Property property = this.J;
        if (property != null) {
            M(PropertyValuesHolder.h(property, fArr));
        } else {
            M(PropertyValuesHolder.i(this.I, fArr));
        }
    }

    @Override // com.nineoldandroids.animation.ValueAnimator
    /* renamed from: P */
    public ObjectAnimator w() {
        return (ObjectAnimator) super.clone();
    }

    @Override // com.nineoldandroids.animation.ValueAnimator, com.nineoldandroids.animation.Animator
    /* renamed from: R */
    public ObjectAnimator f(long j2) {
        super.f(j2);
        return this;
    }

    public void S(Property property) {
        PropertyValuesHolder[] propertyValuesHolderArr = this.f334v;
        if (propertyValuesHolderArr != null) {
            PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArr[0];
            String f2 = propertyValuesHolder.f();
            propertyValuesHolder.m(property);
            this.f335w.remove(f2);
            this.f335w.put(this.I, propertyValuesHolder);
        }
        if (this.J != null) {
            this.I = property.b();
        }
        this.J = property;
        this.f327o = false;
    }

    public void T(String str) {
        PropertyValuesHolder[] propertyValuesHolderArr = this.f334v;
        if (propertyValuesHolderArr != null) {
            PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArr[0];
            String f2 = propertyValuesHolder.f();
            propertyValuesHolder.n(str);
            this.f335w.remove(f2);
            this.f335w.put(str, propertyValuesHolder);
        }
        this.I = str;
        this.f327o = false;
    }

    public void U(Object obj) {
        Object obj2 = this.H;
        if (obj2 != obj) {
            this.H = obj;
            if (obj2 != null && obj != null && obj2.getClass() == obj.getClass()) {
                return;
            }
            this.f327o = false;
        }
    }

    @Override // com.nineoldandroids.animation.ValueAnimator, com.nineoldandroids.animation.Animator
    public void g() {
        super.g();
    }

    @Override // com.nineoldandroids.animation.ValueAnimator
    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.H;
        if (this.f334v != null) {
            for (int i2 = 0; i2 < this.f334v.length; i2++) {
                str = str + "\n    " + this.f334v[i2].toString();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.nineoldandroids.animation.ValueAnimator
    public void u(float f2) {
        super.u(f2);
        int length = this.f334v.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.f334v[i2].j(this.H);
        }
    }

    private ObjectAnimator(Object obj, String str) {
        this.H = obj;
        T(str);
    }
}
