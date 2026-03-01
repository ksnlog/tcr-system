package com.ortiz.touch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class TouchImageView extends ImageView {
    private ScaleGestureDetector A;
    private GestureDetector B;
    private GestureDetector.OnDoubleTapListener C;
    private View.OnTouchListener D;

    /* renamed from: d  reason: collision with root package name */
    private float f397d;

    /* renamed from: e  reason: collision with root package name */
    private Matrix f398e;

    /* renamed from: f  reason: collision with root package name */
    private Matrix f399f;

    /* renamed from: g  reason: collision with root package name */
    private State f400g;

    /* renamed from: h  reason: collision with root package name */
    private float f401h;

    /* renamed from: i  reason: collision with root package name */
    private float f402i;

    /* renamed from: j  reason: collision with root package name */
    private float f403j;

    /* renamed from: k  reason: collision with root package name */
    private float f404k;

    /* renamed from: l  reason: collision with root package name */
    private float[] f405l;

    /* renamed from: m  reason: collision with root package name */
    private Context f406m;

    /* renamed from: n  reason: collision with root package name */
    private Fling f407n;

    /* renamed from: o  reason: collision with root package name */
    private ImageView.ScaleType f408o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f409p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f410q;

    /* renamed from: r  reason: collision with root package name */
    private ZoomVariables f411r;

    /* renamed from: s  reason: collision with root package name */
    private int f412s;

    /* renamed from: t  reason: collision with root package name */
    private int f413t;

    /* renamed from: u  reason: collision with root package name */
    private int f414u;

    /* renamed from: v  reason: collision with root package name */
    private int f415v;

    /* renamed from: w  reason: collision with root package name */
    private float f416w;

    /* renamed from: x  reason: collision with root package name */
    private float f417x;

    /* renamed from: y  reason: collision with root package name */
    private float f418y;

    /* renamed from: z  reason: collision with root package name */
    private float f419z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ortiz.touch.TouchImageView$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f420a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            f420a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f420a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f420a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f420a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f420a[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(R$styleable.f1329e)
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class CompatScroller {

        /* renamed from: a  reason: collision with root package name */
        Scroller f421a;

        /* renamed from: b  reason: collision with root package name */
        OverScroller f422b;

        /* renamed from: c  reason: collision with root package name */
        boolean f423c = false;

        public CompatScroller(Context context) {
            this.f422b = new OverScroller(context);
        }

        public boolean a() {
            if (this.f423c) {
                return this.f421a.computeScrollOffset();
            }
            this.f422b.computeScrollOffset();
            return this.f422b.computeScrollOffset();
        }

        public void b(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (this.f423c) {
                this.f421a.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            } else {
                this.f422b.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            }
        }

        public void c(boolean z2) {
            if (this.f423c) {
                this.f421a.forceFinished(z2);
            } else {
                this.f422b.forceFinished(z2);
            }
        }

        public int d() {
            if (this.f423c) {
                return this.f421a.getCurrX();
            }
            return this.f422b.getCurrX();
        }

        public int e() {
            if (this.f423c) {
                return this.f421a.getCurrY();
            }
            return this.f422b.getCurrY();
        }

        public boolean f() {
            if (this.f423c) {
                return this.f421a.isFinished();
            }
            return this.f422b.isFinished();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private class DoubleTapZoom implements Runnable {

        /* renamed from: d  reason: collision with root package name */
        private long f425d;

        /* renamed from: e  reason: collision with root package name */
        private float f426e;

        /* renamed from: f  reason: collision with root package name */
        private float f427f;

        /* renamed from: g  reason: collision with root package name */
        private float f428g;

        /* renamed from: h  reason: collision with root package name */
        private float f429h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f430i;

        /* renamed from: j  reason: collision with root package name */
        private AccelerateDecelerateInterpolator f431j = new AccelerateDecelerateInterpolator();

        /* renamed from: k  reason: collision with root package name */
        private PointF f432k;

        /* renamed from: l  reason: collision with root package name */
        private PointF f433l;

        DoubleTapZoom(float f2, float f3, float f4, boolean z2) {
            TouchImageView.this.setState(State.ANIMATE_ZOOM);
            this.f425d = System.currentTimeMillis();
            this.f426e = TouchImageView.this.f397d;
            this.f427f = f2;
            this.f430i = z2;
            PointF P = TouchImageView.this.P(f3, f4, false);
            float f5 = P.x;
            this.f428g = f5;
            float f6 = P.y;
            this.f429h = f6;
            this.f432k = TouchImageView.this.O(f5, f6);
            this.f433l = new PointF(TouchImageView.this.f412s / 2, TouchImageView.this.f413t / 2);
        }

        private double a(float f2) {
            float f3 = this.f426e;
            double d2 = f3 + (f2 * (this.f427f - f3));
            double d3 = TouchImageView.this.f397d;
            Double.isNaN(d2);
            Double.isNaN(d3);
            return d2 / d3;
        }

        private float b() {
            return this.f431j.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f425d)) / 500.0f));
        }

        private void c(float f2) {
            PointF pointF = this.f432k;
            float f3 = pointF.x;
            PointF pointF2 = this.f433l;
            float f4 = f3 + ((pointF2.x - f3) * f2);
            float f5 = pointF.y;
            float f6 = f5 + (f2 * (pointF2.y - f5));
            PointF O = TouchImageView.this.O(this.f428g, this.f429h);
            TouchImageView.this.f398e.postTranslate(f4 - O.x, f6 - O.y);
        }

        @Override // java.lang.Runnable
        public void run() {
            float b2 = b();
            TouchImageView.this.J(a(b2), this.f428g, this.f429h, this.f430i);
            c(b2);
            TouchImageView.this.C();
            TouchImageView touchImageView = TouchImageView.this;
            touchImageView.setImageMatrix(touchImageView.f398e);
            TouchImageView.this.getClass();
            if (b2 < 1.0f) {
                TouchImageView.this.A(this);
            } else {
                TouchImageView.this.setState(State.NONE);
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private class Fling implements Runnable {

        /* renamed from: d  reason: collision with root package name */
        CompatScroller f435d;

        /* renamed from: e  reason: collision with root package name */
        int f436e;

        /* renamed from: f  reason: collision with root package name */
        int f437f;

        Fling(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            TouchImageView.this.setState(State.FLING);
            this.f435d = new CompatScroller(TouchImageView.this.f406m);
            TouchImageView.this.f398e.getValues(TouchImageView.this.f405l);
            int i8 = (int) TouchImageView.this.f405l[2];
            int i9 = (int) TouchImageView.this.f405l[5];
            if (TouchImageView.this.getImageWidth() > TouchImageView.this.f412s) {
                i4 = TouchImageView.this.f412s - ((int) TouchImageView.this.getImageWidth());
                i5 = 0;
            } else {
                i4 = i8;
                i5 = i4;
            }
            if (TouchImageView.this.getImageHeight() > TouchImageView.this.f413t) {
                i6 = TouchImageView.this.f413t - ((int) TouchImageView.this.getImageHeight());
                i7 = 0;
            } else {
                i6 = i9;
                i7 = i6;
            }
            this.f435d.b(i8, i9, i2, i3, i4, i5, i6, i7);
            this.f436e = i8;
            this.f437f = i9;
        }

        public void a() {
            if (this.f435d != null) {
                TouchImageView.this.setState(State.NONE);
                this.f435d.c(true);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            TouchImageView.this.getClass();
            if (this.f435d.f()) {
                this.f435d = null;
            } else if (this.f435d.a()) {
                int d2 = this.f435d.d();
                int e2 = this.f435d.e();
                int i2 = d2 - this.f436e;
                int i3 = e2 - this.f437f;
                this.f436e = d2;
                this.f437f = e2;
                TouchImageView.this.f398e.postTranslate(i2, i3);
                TouchImageView.this.D();
                TouchImageView touchImageView = TouchImageView.this;
                touchImageView.setImageMatrix(touchImageView.f398e);
                TouchImageView.this.A(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean z2;
            float f2;
            if (TouchImageView.this.C != null) {
                z2 = TouchImageView.this.C.onDoubleTap(motionEvent);
            } else {
                z2 = false;
            }
            if (TouchImageView.this.f400g == State.NONE) {
                if (TouchImageView.this.f397d == TouchImageView.this.f401h) {
                    f2 = TouchImageView.this.f402i;
                } else {
                    f2 = TouchImageView.this.f401h;
                }
                TouchImageView.this.A(new DoubleTapZoom(f2, motionEvent.getX(), motionEvent.getY(), false));
                return true;
            }
            return z2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (TouchImageView.this.C != null) {
                return TouchImageView.this.C.onDoubleTapEvent(motionEvent);
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (TouchImageView.this.f407n != null) {
                TouchImageView.this.f407n.a();
            }
            TouchImageView touchImageView = TouchImageView.this;
            touchImageView.f407n = new Fling((int) f2, (int) f3);
            TouchImageView touchImageView2 = TouchImageView.this;
            touchImageView2.A(touchImageView2.f407n);
            return super.onFling(motionEvent, motionEvent2, f2, f3);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            TouchImageView.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (TouchImageView.this.C != null) {
                return TouchImageView.this.C.onSingleTapConfirmed(motionEvent);
            }
            return TouchImageView.this.performClick();
        }

        /* synthetic */ GestureListener(TouchImageView touchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnTouchImageViewListener {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.J(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            TouchImageView.this.getClass();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.setState(State.ZOOM);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            TouchImageView.this.setState(State.NONE);
            float f2 = TouchImageView.this.f397d;
            boolean z2 = true;
            if (TouchImageView.this.f397d > TouchImageView.this.f402i) {
                f2 = TouchImageView.this.f402i;
            } else if (TouchImageView.this.f397d < TouchImageView.this.f401h) {
                f2 = TouchImageView.this.f401h;
            } else {
                z2 = false;
            }
            float f3 = f2;
            if (z2) {
                TouchImageView touchImageView = TouchImageView.this;
                TouchImageView.this.A(new DoubleTapZoom(f3, touchImageView.f412s / 2, TouchImageView.this.f413t / 2, true));
            }
        }

        /* synthetic */ ScaleListener(TouchImageView touchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class ZoomVariables {

        /* renamed from: a  reason: collision with root package name */
        public float f449a;

        /* renamed from: b  reason: collision with root package name */
        public float f450b;

        /* renamed from: c  reason: collision with root package name */
        public float f451c;

        /* renamed from: d  reason: collision with root package name */
        public ImageView.ScaleType f452d;

        public ZoomVariables(float f2, float f3, float f4, ImageView.ScaleType scaleType) {
            this.f449a = f2;
            this.f450b = f3;
            this.f451c = f4;
            this.f452d = scaleType;
        }
    }

    public TouchImageView(Context context) {
        super(context);
        this.C = null;
        this.D = null;
        N(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public void A(Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16L);
        }
    }

    private void B() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.f398e != null && this.f399f != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f2 = intrinsicWidth;
            float f3 = this.f412s / f2;
            float f4 = intrinsicHeight;
            float f5 = this.f413t / f4;
            int i2 = AnonymousClass1.f420a[this.f408o.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
                            }
                        }
                    } else {
                        f3 = Math.min(1.0f, Math.min(f3, f5));
                        f5 = f3;
                    }
                    f3 = Math.min(f3, f5);
                } else {
                    f3 = Math.max(f3, f5);
                }
                f5 = f3;
            } else {
                f3 = 1.0f;
                f5 = 1.0f;
            }
            int i3 = this.f412s;
            float f6 = i3 - (f3 * f2);
            int i4 = this.f413t;
            float f7 = i4 - (f5 * f4);
            this.f416w = i3 - f6;
            this.f417x = i4 - f7;
            if (!G() && !this.f409p) {
                this.f398e.setScale(f3, f5);
                this.f398e.postTranslate(f6 / 2.0f, f7 / 2.0f);
                this.f397d = 1.0f;
            } else {
                if (this.f418y == 0.0f || this.f419z == 0.0f) {
                    I();
                }
                this.f399f.getValues(this.f405l);
                float[] fArr = this.f405l;
                float f8 = this.f416w / f2;
                float f9 = this.f397d;
                fArr[0] = f8 * f9;
                fArr[4] = (this.f417x / f4) * f9;
                float f10 = fArr[2];
                float f11 = fArr[5];
                Q(2, f10, this.f418y * f9, getImageWidth(), this.f414u, this.f412s, intrinsicWidth);
                Q(5, f11, this.f419z * this.f397d, getImageHeight(), this.f415v, this.f413t, intrinsicHeight);
                this.f398e.setValues(this.f405l);
            }
            D();
            setImageMatrix(this.f398e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        D();
        this.f398e.getValues(this.f405l);
        float imageWidth = getImageWidth();
        int i2 = this.f412s;
        if (imageWidth < i2) {
            this.f405l[2] = (i2 - getImageWidth()) / 2.0f;
        }
        float imageHeight = getImageHeight();
        int i3 = this.f413t;
        if (imageHeight < i3) {
            this.f405l[5] = (i3 - getImageHeight()) / 2.0f;
        }
        this.f398e.setValues(this.f405l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        this.f398e.getValues(this.f405l);
        float[] fArr = this.f405l;
        float f2 = fArr[2];
        float f3 = fArr[5];
        float F = F(f2, this.f412s, getImageWidth());
        float F2 = F(f3, this.f413t, getImageHeight());
        if (F != 0.0f || F2 != 0.0f) {
            this.f398e.postTranslate(F, F2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float E(float f2, float f3, float f4) {
        if (f4 <= f3) {
            return 0.0f;
        }
        return f2;
    }

    private float F(float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 <= f3) {
            f6 = f3 - f4;
            f5 = 0.0f;
        } else {
            f5 = f3 - f4;
            f6 = 0.0f;
        }
        if (f2 < f5) {
            return (-f2) + f5;
        }
        if (f2 <= f6) {
            return 0.0f;
        }
        return (-f2) + f6;
    }

    private void I() {
        Matrix matrix = this.f398e;
        if (matrix != null && this.f413t != 0 && this.f412s != 0) {
            matrix.getValues(this.f405l);
            this.f399f.setValues(this.f405l);
            this.f419z = this.f417x;
            this.f418y = this.f416w;
            this.f415v = this.f413t;
            this.f414u = this.f412s;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(double d2, float f2, float f3, boolean z2) {
        float f4;
        float f5;
        if (z2) {
            f4 = this.f403j;
            f5 = this.f404k;
        } else {
            f4 = this.f401h;
            f5 = this.f402i;
        }
        float f6 = this.f397d;
        double d3 = f6;
        Double.isNaN(d3);
        float f7 = (float) (d3 * d2);
        this.f397d = f7;
        if (f7 > f5) {
            this.f397d = f5;
            d2 = f5 / f6;
        } else if (f7 < f4) {
            this.f397d = f4;
            d2 = f4 / f6;
        }
        float f8 = (float) d2;
        this.f398e.postScale(f8, f8, f2, f3);
        C();
    }

    private int K(int i2, int i3, int i4) {
        return i2 != Integer.MIN_VALUE ? i2 != 0 ? i3 : i4 : Math.min(i4, i3);
    }

    private void N(Context context) {
        super.setClickable(true);
        this.f406m = context;
        this.A = new ScaleGestureDetector(context, new ScaleListener(this, null));
        this.B = new GestureDetector(context, new GestureListener(this, null));
        this.f398e = new Matrix();
        this.f399f = new Matrix();
        this.f405l = new float[9];
        this.f397d = 1.0f;
        if (this.f408o == null) {
            this.f408o = ImageView.ScaleType.FIT_CENTER;
        }
        this.f401h = 1.0f;
        this.f402i = 3.0f;
        this.f403j = 1.0f * 0.75f;
        this.f404k = 3.0f * 1.25f;
        setImageMatrix(this.f398e);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.NONE);
        this.f410q = false;
        super.setOnTouchListener(new PrivateOnTouchListener(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF O(float f2, float f3) {
        this.f398e.getValues(this.f405l);
        return new PointF(this.f405l[2] + (getImageWidth() * (f2 / getDrawable().getIntrinsicWidth())), this.f405l[5] + (getImageHeight() * (f3 / getDrawable().getIntrinsicHeight())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF P(float f2, float f3, boolean z2) {
        this.f398e.getValues(this.f405l);
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float[] fArr = this.f405l;
        float f4 = fArr[2];
        float f5 = fArr[5];
        float imageWidth = ((f2 - f4) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f3 - f5) * intrinsicHeight) / getImageHeight();
        if (z2) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    private void Q(int i2, float f2, float f3, float f4, int i3, int i4, int i5) {
        float f5 = i4;
        if (f4 < f5) {
            float[] fArr = this.f405l;
            fArr[i2] = (f5 - (i5 * fArr[0])) * 0.5f;
        } else if (f2 > 0.0f) {
            this.f405l[i2] = -((f4 - f5) * 0.5f);
        } else {
            this.f405l[i2] = -((((Math.abs(f2) + (i3 * 0.5f)) / f3) * f4) - (f5 * 0.5f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageHeight() {
        return this.f417x * this.f397d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageWidth() {
        return this.f416w * this.f397d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(State state) {
        this.f400g = state;
    }

    public boolean G() {
        return this.f397d != 1.0f;
    }

    public void H() {
        this.f397d = 1.0f;
        B();
    }

    public void L(float f2, float f3, float f4) {
        M(f2, f3, f4, this.f408o);
    }

    public void M(float f2, float f3, float f4, ImageView.ScaleType scaleType) {
        if (!this.f410q) {
            this.f411r = new ZoomVariables(f2, f3, f4, scaleType);
            return;
        }
        if (scaleType != this.f408o) {
            setScaleType(scaleType);
        }
        H();
        J(f2, this.f412s / 2, this.f413t / 2, true);
        this.f398e.getValues(this.f405l);
        this.f405l[2] = -((f3 * getImageWidth()) - (this.f412s * 0.5f));
        this.f405l[5] = -((f4 * getImageHeight()) - (this.f413t * 0.5f));
        this.f398e.setValues(this.f405l);
        D();
        setImageMatrix(this.f398e);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        this.f398e.getValues(this.f405l);
        float f2 = this.f405l[2];
        if (getImageWidth() < this.f412s) {
            return false;
        }
        if (f2 >= -1.0f && i2 < 0) {
            return false;
        }
        if (Math.abs(f2) + this.f412s + 1.0f >= getImageWidth() && i2 > 0) {
            return false;
        }
        return true;
    }

    public float getCurrentZoom() {
        return this.f397d;
    }

    public float getMaxZoom() {
        return this.f402i;
    }

    public float getMinZoom() {
        return this.f401h;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.f408o;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF P = P(this.f412s / 2, this.f413t / 2, true);
        P.x /= intrinsicWidth;
        P.y /= intrinsicHeight;
        return P;
    }

    public RectF getZoomedRect() {
        if (this.f408o != ImageView.ScaleType.FIT_XY) {
            PointF P = P(0.0f, 0.0f, true);
            PointF P2 = P(this.f412s, this.f413t, true);
            float intrinsicWidth = getDrawable().getIntrinsicWidth();
            float intrinsicHeight = getDrawable().getIntrinsicHeight();
            return new RectF(P.x / intrinsicWidth, P.y / intrinsicHeight, P2.x / intrinsicWidth, P2.y / intrinsicHeight);
        }
        throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        I();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.f410q = true;
        this.f409p = true;
        ZoomVariables zoomVariables = this.f411r;
        if (zoomVariables != null) {
            M(zoomVariables.f449a, zoomVariables.f450b, zoomVariables.f451c, zoomVariables.f452d);
            this.f411r = null;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.f412s = K(mode, size, intrinsicWidth);
            int K = K(mode2, size2, intrinsicHeight);
            this.f413t = K;
            setMeasuredDimension(this.f412s, K);
            B();
            return;
        }
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f397d = bundle.getFloat("saveScale");
            float[] floatArray = bundle.getFloatArray("matrix");
            this.f405l = floatArray;
            this.f399f.setValues(floatArray);
            this.f419z = bundle.getFloat("matchViewHeight");
            this.f418y = bundle.getFloat("matchViewWidth");
            this.f415v = bundle.getInt("viewHeight");
            this.f414u = bundle.getInt("viewWidth");
            this.f409p = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.f397d);
        bundle.putFloat("matchViewHeight", this.f417x);
        bundle.putFloat("matchViewWidth", this.f416w);
        bundle.putInt("viewWidth", this.f412s);
        bundle.putInt("viewHeight", this.f413t);
        this.f398e.getValues(this.f405l);
        bundle.putFloatArray("matrix", this.f405l);
        bundle.putBoolean("imageRendered", this.f409p);
        return bundle;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        I();
        B();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        I();
        B();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        super.setImageResource(i2);
        I();
        B();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        I();
        B();
    }

    public void setMaxZoom(float f2) {
        this.f402i = f2;
        this.f404k = f2 * 1.25f;
    }

    public void setMinZoom(float f2) {
        this.f401h = f2;
        this.f403j = f2 * 0.75f;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.C = onDoubleTapListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.D = onTouchListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.FIT_START && scaleType != ImageView.ScaleType.FIT_END) {
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                super.setScaleType(scaleType2);
                return;
            }
            this.f408o = scaleType;
            if (this.f410q) {
                setZoom(this);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
    }

    public void setZoom(float f2) {
        L(f2, 0.5f, 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class PrivateOnTouchListener implements View.OnTouchListener {

        /* renamed from: d  reason: collision with root package name */
        private PointF f440d;

        private PrivateOnTouchListener() {
            this.f440d = new PointF();
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
            if (r1 != 6) goto L7;
         */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
            /*
                r7 = this;
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                android.view.ScaleGestureDetector r0 = com.ortiz.touch.TouchImageView.a(r0)
                r0.onTouchEvent(r9)
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                android.view.GestureDetector r0 = com.ortiz.touch.TouchImageView.b(r0)
                r0.onTouchEvent(r9)
                android.graphics.PointF r0 = new android.graphics.PointF
                float r1 = r9.getX()
                float r2 = r9.getY()
                r0.<init>(r1, r2)
                com.ortiz.touch.TouchImageView r1 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$State r1 = com.ortiz.touch.TouchImageView.w(r1)
                com.ortiz.touch.TouchImageView$State r2 = com.ortiz.touch.TouchImageView.State.NONE
                r3 = 1
                if (r1 == r2) goto L3e
                com.ortiz.touch.TouchImageView r1 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$State r1 = com.ortiz.touch.TouchImageView.w(r1)
                com.ortiz.touch.TouchImageView$State r4 = com.ortiz.touch.TouchImageView.State.DRAG
                if (r1 == r4) goto L3e
                com.ortiz.touch.TouchImageView r1 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$State r1 = com.ortiz.touch.TouchImageView.w(r1)
                com.ortiz.touch.TouchImageView$State r4 = com.ortiz.touch.TouchImageView.State.FLING
                if (r1 != r4) goto Lc0
            L3e:
                int r1 = r9.getAction()
                if (r1 == 0) goto La3
                if (r1 == r3) goto L9d
                r4 = 2
                if (r1 == r4) goto L4d
                r0 = 6
                if (r1 == r0) goto L9d
                goto Lc0
            L4d:
                com.ortiz.touch.TouchImageView r1 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$State r1 = com.ortiz.touch.TouchImageView.w(r1)
                com.ortiz.touch.TouchImageView$State r2 = com.ortiz.touch.TouchImageView.State.DRAG
                if (r1 != r2) goto Lc0
                float r1 = r0.x
                android.graphics.PointF r2 = r7.f440d
                float r4 = r2.x
                float r1 = r1 - r4
                float r4 = r0.y
                float r2 = r2.y
                float r4 = r4 - r2
                com.ortiz.touch.TouchImageView r2 = com.ortiz.touch.TouchImageView.this
                int r5 = com.ortiz.touch.TouchImageView.d(r2)
                float r5 = (float) r5
                com.ortiz.touch.TouchImageView r6 = com.ortiz.touch.TouchImageView.this
                float r6 = com.ortiz.touch.TouchImageView.e(r6)
                float r1 = com.ortiz.touch.TouchImageView.f(r2, r1, r5, r6)
                com.ortiz.touch.TouchImageView r2 = com.ortiz.touch.TouchImageView.this
                int r5 = com.ortiz.touch.TouchImageView.g(r2)
                float r5 = (float) r5
                com.ortiz.touch.TouchImageView r6 = com.ortiz.touch.TouchImageView.this
                float r6 = com.ortiz.touch.TouchImageView.h(r6)
                float r2 = com.ortiz.touch.TouchImageView.f(r2, r4, r5, r6)
                com.ortiz.touch.TouchImageView r4 = com.ortiz.touch.TouchImageView.this
                android.graphics.Matrix r4 = com.ortiz.touch.TouchImageView.i(r4)
                r4.postTranslate(r1, r2)
                com.ortiz.touch.TouchImageView r1 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView.j(r1)
                android.graphics.PointF r1 = r7.f440d
                float r2 = r0.x
                float r0 = r0.y
                r1.set(r2, r0)
                goto Lc0
            L9d:
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView.c(r0, r2)
                goto Lc0
            La3:
                android.graphics.PointF r1 = r7.f440d
                r1.set(r0)
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$Fling r0 = com.ortiz.touch.TouchImageView.t(r0)
                if (r0 == 0) goto Lb9
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$Fling r0 = com.ortiz.touch.TouchImageView.t(r0)
                r0.a()
            Lb9:
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView$State r1 = com.ortiz.touch.TouchImageView.State.DRAG
                com.ortiz.touch.TouchImageView.c(r0, r1)
            Lc0:
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                android.graphics.Matrix r1 = com.ortiz.touch.TouchImageView.i(r0)
                r0.setImageMatrix(r1)
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                android.view.View$OnTouchListener r0 = com.ortiz.touch.TouchImageView.k(r0)
                if (r0 == 0) goto Lda
                com.ortiz.touch.TouchImageView r0 = com.ortiz.touch.TouchImageView.this
                android.view.View$OnTouchListener r0 = com.ortiz.touch.TouchImageView.k(r0)
                r0.onTouch(r8, r9)
            Lda:
                com.ortiz.touch.TouchImageView r8 = com.ortiz.touch.TouchImageView.this
                com.ortiz.touch.TouchImageView.l(r8)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ortiz.touch.TouchImageView.PrivateOnTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }

        /* synthetic */ PrivateOnTouchListener(TouchImageView touchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        M(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }
}
