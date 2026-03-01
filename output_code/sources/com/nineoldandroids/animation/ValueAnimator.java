package com.nineoldandroids.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.Animator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ValueAnimator extends Animator {

    /* renamed from: e  reason: collision with root package name */
    long f317e;

    /* renamed from: k  reason: collision with root package name */
    private long f323k;

    /* renamed from: v  reason: collision with root package name */
    PropertyValuesHolder[] f334v;

    /* renamed from: w  reason: collision with root package name */
    HashMap<String, PropertyValuesHolder> f335w;

    /* renamed from: x  reason: collision with root package name */
    private static ThreadLocal<AnimationHandler> f314x = new ThreadLocal<>();

    /* renamed from: y  reason: collision with root package name */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f315y = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.nineoldandroids.animation.ValueAnimator.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: z  reason: collision with root package name */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f316z = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.nineoldandroids.animation.ValueAnimator.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<ValueAnimator>> A = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.nineoldandroids.animation.ValueAnimator.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<ValueAnimator>> B = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.nineoldandroids.animation.ValueAnimator.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<ValueAnimator>> C = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.nineoldandroids.animation.ValueAnimator.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final Interpolator D = new AccelerateDecelerateInterpolator();
    private static final TypeEvaluator E = new IntEvaluator();
    private static final TypeEvaluator F = new FloatEvaluator();
    private static long G = 10;

    /* renamed from: f  reason: collision with root package name */
    long f318f = -1;

    /* renamed from: g  reason: collision with root package name */
    private boolean f319g = false;

    /* renamed from: h  reason: collision with root package name */
    private int f320h = 0;

    /* renamed from: i  reason: collision with root package name */
    private float f321i = 0.0f;

    /* renamed from: j  reason: collision with root package name */
    private boolean f322j = false;

    /* renamed from: l  reason: collision with root package name */
    int f324l = 0;

    /* renamed from: m  reason: collision with root package name */
    private boolean f325m = false;

    /* renamed from: n  reason: collision with root package name */
    private boolean f326n = false;

    /* renamed from: o  reason: collision with root package name */
    boolean f327o = false;

    /* renamed from: p  reason: collision with root package name */
    private long f328p = 300;

    /* renamed from: q  reason: collision with root package name */
    private long f329q = 0;

    /* renamed from: r  reason: collision with root package name */
    private int f330r = 0;

    /* renamed from: s  reason: collision with root package name */
    private int f331s = 1;

    /* renamed from: t  reason: collision with root package name */
    private Interpolator f332t = D;

    /* renamed from: u  reason: collision with root package name */
    private ArrayList<AnimatorUpdateListener> f333u = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class AnimationHandler extends Handler {
        private AnimationHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z2;
            ArrayList arrayList = (ArrayList) ValueAnimator.f315y.get();
            ArrayList arrayList2 = (ArrayList) ValueAnimator.A.get();
            int i2 = message.what;
            if (i2 != 0) {
                if (i2 == 1) {
                    z2 = true;
                } else {
                    return;
                }
            } else {
                ArrayList arrayList3 = (ArrayList) ValueAnimator.f316z.get();
                if (arrayList.size() <= 0 && arrayList2.size() <= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                while (arrayList3.size() > 0) {
                    ArrayList arrayList4 = (ArrayList) arrayList3.clone();
                    arrayList3.clear();
                    int size = arrayList4.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ValueAnimator valueAnimator = (ValueAnimator) arrayList4.get(i3);
                        if (valueAnimator.f329q == 0) {
                            valueAnimator.O();
                        } else {
                            arrayList2.add(valueAnimator);
                        }
                    }
                }
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            ArrayList arrayList5 = (ArrayList) ValueAnimator.C.get();
            ArrayList arrayList6 = (ArrayList) ValueAnimator.B.get();
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                ValueAnimator valueAnimator2 = (ValueAnimator) arrayList2.get(i4);
                if (valueAnimator2.x(currentAnimationTimeMillis)) {
                    arrayList5.add(valueAnimator2);
                }
            }
            int size3 = arrayList5.size();
            if (size3 > 0) {
                for (int i5 = 0; i5 < size3; i5++) {
                    ValueAnimator valueAnimator3 = (ValueAnimator) arrayList5.get(i5);
                    valueAnimator3.O();
                    valueAnimator3.f325m = true;
                    arrayList2.remove(valueAnimator3);
                }
                arrayList5.clear();
            }
            int size4 = arrayList.size();
            int i6 = 0;
            while (i6 < size4) {
                ValueAnimator valueAnimator4 = (ValueAnimator) arrayList.get(i6);
                if (valueAnimator4.v(currentAnimationTimeMillis)) {
                    arrayList6.add(valueAnimator4);
                }
                if (arrayList.size() == size4) {
                    i6++;
                } else {
                    size4--;
                    arrayList6.remove(valueAnimator4);
                }
            }
            if (arrayList6.size() > 0) {
                for (int i7 = 0; i7 < arrayList6.size(); i7++) {
                    ((ValueAnimator) arrayList6.get(i7)).y();
                }
                arrayList6.clear();
            }
            if (z2) {
                if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
                    sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.G - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
                }
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface AnimatorUpdateListener {
        void e(ValueAnimator valueAnimator);
    }

    public static ValueAnimator D(float... fArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.H(fArr);
        return valueAnimator;
    }

    private void N(boolean z2) {
        if (Looper.myLooper() != null) {
            this.f319g = z2;
            this.f320h = 0;
            this.f324l = 0;
            this.f326n = true;
            this.f322j = false;
            f316z.get().add(this);
            if (this.f329q == 0) {
                F(A());
                this.f324l = 0;
                this.f325m = true;
                ArrayList<Animator.AnimatorListener> arrayList = this.f235d;
                if (arrayList != null) {
                    ArrayList arrayList2 = (ArrayList) arrayList.clone();
                    int size = arrayList2.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((Animator.AnimatorListener) arrayList2.get(i2)).d(this);
                    }
                }
            }
            AnimationHandler animationHandler = f314x.get();
            if (animationHandler == null) {
                animationHandler = new AnimationHandler();
                f314x.set(animationHandler);
            }
            animationHandler.sendEmptyMessage(0);
            return;
        }
        throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        ArrayList<Animator.AnimatorListener> arrayList;
        B();
        f315y.get().add(this);
        if (this.f329q > 0 && (arrayList = this.f235d) != null) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Animator.AnimatorListener) arrayList2.get(i2)).d(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x(long j2) {
        if (!this.f322j) {
            this.f322j = true;
            this.f323k = j2;
            return false;
        }
        long j3 = j2 - this.f323k;
        long j4 = this.f329q;
        if (j3 > j4) {
            this.f317e = j2 - (j3 - j4);
            this.f324l = 1;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        ArrayList<Animator.AnimatorListener> arrayList;
        f315y.get().remove(this);
        f316z.get().remove(this);
        A.get().remove(this);
        this.f324l = 0;
        if (this.f325m && (arrayList = this.f235d) != null) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Animator.AnimatorListener) arrayList2.get(i2)).a(this);
            }
        }
        this.f325m = false;
        this.f326n = false;
    }

    public long A() {
        if (this.f327o && this.f324l != 0) {
            return AnimationUtils.currentAnimationTimeMillis() - this.f317e;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        if (!this.f327o) {
            int length = this.f334v.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.f334v[i2].g();
            }
            this.f327o = true;
        }
    }

    public boolean C() {
        return this.f324l == 1 || this.f325m;
    }

    public void E() {
        ArrayList<AnimatorUpdateListener> arrayList = this.f333u;
        if (arrayList == null) {
            return;
        }
        arrayList.clear();
        this.f333u = null;
    }

    public void F(long j2) {
        B();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.f324l != 1) {
            this.f318f = j2;
            this.f324l = 2;
        }
        this.f317e = currentAnimationTimeMillis - j2;
        v(currentAnimationTimeMillis);
    }

    @Override // com.nineoldandroids.animation.Animator
    /* renamed from: G */
    public ValueAnimator f(long j2) {
        if (j2 >= 0) {
            this.f328p = j2;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j2);
    }

    public void H(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            PropertyValuesHolder[] propertyValuesHolderArr = this.f334v;
            if (propertyValuesHolderArr != null && propertyValuesHolderArr.length != 0) {
                propertyValuesHolderArr[0].l(fArr);
            } else {
                M(PropertyValuesHolder.i("", fArr));
            }
            this.f327o = false;
        }
    }

    public void I(Interpolator interpolator) {
        if (interpolator != null) {
            this.f332t = interpolator;
        } else {
            this.f332t = new LinearInterpolator();
        }
    }

    public void J(int i2) {
        this.f330r = i2;
    }

    public void K(int i2) {
        this.f331s = i2;
    }

    public void L(long j2) {
        this.f329q = j2;
    }

    public void M(PropertyValuesHolder... propertyValuesHolderArr) {
        int length = propertyValuesHolderArr.length;
        this.f334v = propertyValuesHolderArr;
        this.f335w = new HashMap<>(length);
        for (PropertyValuesHolder propertyValuesHolder : propertyValuesHolderArr) {
            this.f335w.put(propertyValuesHolder.f(), propertyValuesHolder);
        }
        this.f327o = false;
    }

    @Override // com.nineoldandroids.animation.Animator
    public void b() {
        ArrayList<Animator.AnimatorListener> arrayList;
        if (this.f324l != 0 || f316z.get().contains(this) || A.get().contains(this)) {
            if (this.f325m && (arrayList = this.f235d) != null) {
                Iterator it = ((ArrayList) arrayList.clone()).iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorListener) it.next()).b(this);
                }
            }
            y();
        }
    }

    @Override // com.nineoldandroids.animation.Animator
    public void g() {
        N(false);
    }

    public void t(AnimatorUpdateListener animatorUpdateListener) {
        if (this.f333u == null) {
            this.f333u = new ArrayList<>();
        }
        this.f333u.add(animatorUpdateListener);
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f334v != null) {
            for (int i2 = 0; i2 < this.f334v.length; i2++) {
                str = str + "\n    " + this.f334v[i2].toString();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(float f2) {
        float interpolation = this.f332t.getInterpolation(f2);
        this.f321i = interpolation;
        int length = this.f334v.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.f334v[i2].a(interpolation);
        }
        ArrayList<AnimatorUpdateListener> arrayList = this.f333u;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f333u.get(i3).e(this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean v(long r10) {
        /*
            r9 = this;
            int r0 = r9.f324l
            r1 = 0
            r3 = 1
            if (r0 != 0) goto L1a
            r9.f324l = r3
            long r4 = r9.f318f
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L12
            r9.f317e = r10
            goto L1a
        L12:
            long r4 = r10 - r4
            r9.f317e = r4
            r4 = -1
            r9.f318f = r4
        L1a:
            int r0 = r9.f324l
            r4 = 2
            r5 = 0
            if (r0 == r3) goto L23
            if (r0 == r4) goto L23
            goto L82
        L23:
            long r6 = r9.f328p
            r0 = 1065353216(0x3f800000, float:1.0)
            int r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r8 <= 0) goto L32
            long r1 = r9.f317e
            long r10 = r10 - r1
            float r10 = (float) r10
            float r11 = (float) r6
            float r10 = r10 / r11
            goto L34
        L32:
            r10 = 1065353216(0x3f800000, float:1.0)
        L34:
            int r11 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r11 < 0) goto L77
            int r11 = r9.f320h
            int r1 = r9.f330r
            if (r11 < r1) goto L47
            r11 = -1
            if (r1 != r11) goto L42
            goto L47
        L42:
            float r10 = java.lang.Math.min(r10, r0)
            goto L78
        L47:
            java.util.ArrayList<com.nineoldandroids.animation.Animator$AnimatorListener> r11 = r9.f235d
            if (r11 == 0) goto L60
            int r11 = r11.size()
            r1 = 0
        L50:
            if (r1 >= r11) goto L60
            java.util.ArrayList<com.nineoldandroids.animation.Animator$AnimatorListener> r2 = r9.f235d
            java.lang.Object r2 = r2.get(r1)
            com.nineoldandroids.animation.Animator$AnimatorListener r2 = (com.nineoldandroids.animation.Animator.AnimatorListener) r2
            r2.c(r9)
            int r1 = r1 + 1
            goto L50
        L60:
            int r11 = r9.f331s
            if (r11 != r4) goto L69
            boolean r11 = r9.f319g
            r11 = r11 ^ r3
            r9.f319g = r11
        L69:
            int r11 = r9.f320h
            int r1 = (int) r10
            int r11 = r11 + r1
            r9.f320h = r11
            float r10 = r10 % r0
            long r1 = r9.f317e
            long r3 = r9.f328p
            long r1 = r1 + r3
            r9.f317e = r1
        L77:
            r3 = 0
        L78:
            boolean r11 = r9.f319g
            if (r11 == 0) goto L7e
            float r10 = r0 - r10
        L7e:
            r9.u(r10)
            r5 = r3
        L82:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nineoldandroids.animation.ValueAnimator.v(long):boolean");
    }

    @Override // com.nineoldandroids.animation.Animator
    /* renamed from: w */
    public ValueAnimator clone() {
        ValueAnimator valueAnimator = (ValueAnimator) super.clone();
        ArrayList<AnimatorUpdateListener> arrayList = this.f333u;
        if (arrayList != null) {
            valueAnimator.f333u = new ArrayList<>();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                valueAnimator.f333u.add(arrayList.get(i2));
            }
        }
        valueAnimator.f318f = -1L;
        valueAnimator.f319g = false;
        valueAnimator.f320h = 0;
        valueAnimator.f327o = false;
        valueAnimator.f324l = 0;
        valueAnimator.f322j = false;
        PropertyValuesHolder[] propertyValuesHolderArr = this.f334v;
        if (propertyValuesHolderArr != null) {
            int length = propertyValuesHolderArr.length;
            valueAnimator.f334v = new PropertyValuesHolder[length];
            valueAnimator.f335w = new HashMap<>(length);
            for (int i3 = 0; i3 < length; i3++) {
                PropertyValuesHolder clone = propertyValuesHolderArr[i3].clone();
                valueAnimator.f334v[i3] = clone;
                valueAnimator.f335w.put(clone.f(), clone);
            }
        }
        return valueAnimator;
    }

    public float z() {
        return this.f321i;
    }
}
