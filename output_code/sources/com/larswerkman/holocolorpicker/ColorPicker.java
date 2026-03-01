package com.larswerkman.holocolorpicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ColorPicker extends View {
    private static final int[] J = {-65536, -65281, -16776961, -16711681, -16711936, -256, -65536};
    private Paint A;
    private Paint B;
    private Paint C;
    private float[] D;
    private SVBar E;
    private OpacityBar F;
    private SaturationBar G;
    private boolean H;
    private ValueBar I;

    /* renamed from: d  reason: collision with root package name */
    private Paint f126d;

    /* renamed from: e  reason: collision with root package name */
    private Paint f127e;

    /* renamed from: f  reason: collision with root package name */
    private Paint f128f;

    /* renamed from: g  reason: collision with root package name */
    private int f129g;

    /* renamed from: h  reason: collision with root package name */
    private int f130h;

    /* renamed from: i  reason: collision with root package name */
    private int f131i;

    /* renamed from: j  reason: collision with root package name */
    private int f132j;

    /* renamed from: k  reason: collision with root package name */
    private int f133k;

    /* renamed from: l  reason: collision with root package name */
    private int f134l;

    /* renamed from: m  reason: collision with root package name */
    private int f135m;

    /* renamed from: n  reason: collision with root package name */
    private int f136n;

    /* renamed from: o  reason: collision with root package name */
    private int f137o;

    /* renamed from: p  reason: collision with root package name */
    private RectF f138p;

    /* renamed from: q  reason: collision with root package name */
    private RectF f139q;

    /* renamed from: r  reason: collision with root package name */
    private boolean f140r;

    /* renamed from: s  reason: collision with root package name */
    private int f141s;

    /* renamed from: t  reason: collision with root package name */
    private int f142t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f143u;

    /* renamed from: v  reason: collision with root package name */
    private int f144v;

    /* renamed from: w  reason: collision with root package name */
    private float f145w;

    /* renamed from: x  reason: collision with root package name */
    private float f146x;

    /* renamed from: y  reason: collision with root package name */
    private float f147y;

    /* renamed from: z  reason: collision with root package name */
    private float f148z;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnColorChangedListener {
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnColorSelectedListener {
    }

    public ColorPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f138p = new RectF();
        this.f139q = new RectF();
        this.f140r = false;
        this.D = new float[3];
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = true;
        this.I = null;
        i(attributeSet, 0);
    }

    private int a(int i2, int i3, float f2) {
        return i2 + Math.round(f2 * (i3 - i2));
    }

    private int b(float f2) {
        double d2 = f2;
        Double.isNaN(d2);
        float f3 = (float) (d2 / 6.283185307179586d);
        if (f3 < 0.0f) {
            f3 += 1.0f;
        }
        if (f3 <= 0.0f) {
            int i2 = J[0];
            this.f141s = i2;
            return i2;
        } else if (f3 >= 1.0f) {
            int[] iArr = J;
            this.f141s = iArr[iArr.length - 1];
            return iArr[iArr.length - 1];
        } else {
            int[] iArr2 = J;
            float length = f3 * (iArr2.length - 1);
            int i3 = (int) length;
            float f4 = length - i3;
            int i4 = iArr2[i3];
            int i5 = iArr2[i3 + 1];
            int a2 = a(Color.alpha(i4), Color.alpha(i5), f4);
            int a3 = a(Color.red(i4), Color.red(i5), f4);
            int a4 = a(Color.green(i4), Color.green(i5), f4);
            int a5 = a(Color.blue(i4), Color.blue(i5), f4);
            this.f141s = Color.argb(a2, a3, a4, a5);
            return Color.argb(a2, a3, a4, a5);
        }
    }

    private float[] c(float f2) {
        double d2 = this.f130h;
        double d3 = f2;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        float f3 = (float) (d2 * cos);
        double d4 = this.f130h;
        double sin = Math.sin(d3);
        Double.isNaN(d4);
        return new float[]{f3, (float) (d4 * sin)};
    }

    private float f(int i2) {
        float[] fArr = new float[3];
        Color.colorToHSV(i2, fArr);
        return (float) Math.toRadians(-fArr[0]);
    }

    private void i(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.f174b, i2, 0);
        Resources resources = getContext().getResources();
        this.f129g = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f180h, resources.getDimensionPixelSize(R$dimen.f172f));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f179g, resources.getDimensionPixelSize(R$dimen.f171e));
        this.f130h = dimensionPixelSize;
        this.f131i = dimensionPixelSize;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f176d, resources.getDimensionPixelSize(R$dimen.f168b));
        this.f132j = dimensionPixelSize2;
        this.f133k = dimensionPixelSize2;
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f175c, resources.getDimensionPixelSize(R$dimen.f167a));
        this.f134l = dimensionPixelSize3;
        this.f135m = dimensionPixelSize3;
        this.f136n = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f178f, resources.getDimensionPixelSize(R$dimen.f170d));
        this.f137o = obtainStyledAttributes.getDimensionPixelSize(R$styleable.f177e, resources.getDimensionPixelSize(R$dimen.f169c));
        obtainStyledAttributes.recycle();
        this.f148z = -1.5707964f;
        SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, J, (float[]) null);
        Paint paint = new Paint(1);
        this.f126d = paint;
        paint.setShader(sweepGradient);
        this.f126d.setStyle(Paint.Style.STROKE);
        this.f126d.setStrokeWidth(this.f129g);
        Paint paint2 = new Paint(1);
        this.f127e = paint2;
        paint2.setColor(-16777216);
        this.f127e.setAlpha(80);
        Paint paint3 = new Paint(1);
        this.f128f = paint3;
        paint3.setColor(b(this.f148z));
        Paint paint4 = new Paint(1);
        this.B = paint4;
        paint4.setColor(b(this.f148z));
        this.B.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint(1);
        this.A = paint5;
        paint5.setColor(b(this.f148z));
        this.A.setStyle(Paint.Style.FILL);
        Paint paint6 = new Paint(1);
        this.C = paint6;
        paint6.setColor(-16777216);
        this.C.setAlpha(0);
        this.f144v = b(this.f148z);
        this.f142t = b(this.f148z);
        this.f143u = true;
    }

    public void d(int i2) {
        OpacityBar opacityBar = this.F;
        if (opacityBar != null) {
            opacityBar.setColor(i2);
        }
    }

    public void e(int i2) {
        ValueBar valueBar = this.I;
        if (valueBar != null) {
            valueBar.setColor(i2);
        }
    }

    public boolean g() {
        return this.F != null;
    }

    public int getColor() {
        return this.f144v;
    }

    public int getOldCenterColor() {
        return this.f142t;
    }

    public OnColorChangedListener getOnColorChangedListener() {
        return null;
    }

    public OnColorSelectedListener getOnColorSelectedListener() {
        return null;
    }

    public boolean getShowOldCenterColor() {
        return this.f143u;
    }

    public boolean getTouchAnywhereOnColorWheel() {
        return this.H;
    }

    public boolean h() {
        return this.I != null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2 = this.f145w;
        canvas.translate(f2, f2);
        canvas.drawOval(this.f138p, this.f126d);
        float[] c2 = c(this.f148z);
        canvas.drawCircle(c2[0], c2[1], this.f137o, this.f127e);
        canvas.drawCircle(c2[0], c2[1], this.f136n, this.f128f);
        canvas.drawCircle(0.0f, 0.0f, this.f134l, this.C);
        if (this.f143u) {
            canvas.drawArc(this.f139q, 90.0f, 180.0f, true, this.A);
            canvas.drawArc(this.f139q, 270.0f, 180.0f, true, this.B);
            return;
        }
        canvas.drawArc(this.f139q, 0.0f, 360.0f, true, this.B);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = (this.f131i + this.f137o) * 2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(i4, size);
            } else {
                size = i4;
            }
        }
        if (mode2 == 1073741824) {
            i4 = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size2);
        }
        int min = Math.min(size, i4);
        setMeasuredDimension(min, min);
        this.f145w = min * 0.5f;
        int i5 = ((min / 2) - this.f129g) - this.f137o;
        this.f130h = i5;
        this.f138p.set(-i5, -i5, i5, i5);
        int i6 = this.f130h;
        int i7 = this.f131i;
        int i8 = (int) (this.f133k * (i6 / i7));
        this.f132j = i8;
        this.f134l = (int) (this.f135m * (i6 / i7));
        this.f139q.set(-i8, -i8, i8, i8);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("parent"));
        this.f148z = bundle.getFloat("angle");
        setOldCenterColor(bundle.getInt("color"));
        this.f143u = bundle.getBoolean("showColor");
        int b2 = b(this.f148z);
        this.f128f.setColor(b2);
        setNewCenterColor(b2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloat("angle", this.f148z);
        bundle.putInt("color", this.f142t);
        bundle.putBoolean("showColor", this.f143u);
        return bundle;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        float x2 = motionEvent.getX() - this.f145w;
        float y2 = motionEvent.getY() - this.f145w;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.f140r) {
                        float atan2 = (float) Math.atan2(y2 - this.f147y, x2 - this.f146x);
                        this.f148z = atan2;
                        this.f128f.setColor(b(atan2));
                        int b2 = b(this.f148z);
                        this.f144v = b2;
                        setNewCenterColor(b2);
                        OpacityBar opacityBar = this.F;
                        if (opacityBar != null) {
                            opacityBar.setColor(this.f141s);
                        }
                        ValueBar valueBar = this.I;
                        if (valueBar != null) {
                            valueBar.setColor(this.f141s);
                        }
                        SaturationBar saturationBar = this.G;
                        if (saturationBar != null) {
                            saturationBar.setColor(this.f141s);
                        }
                        SVBar sVBar = this.E;
                        if (sVBar != null) {
                            sVBar.setColor(this.f141s);
                        }
                        invalidate();
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                }
            } else {
                this.f140r = false;
                this.C.setAlpha(0);
                invalidate();
            }
        } else {
            float[] c2 = c(this.f148z);
            float f2 = c2[0];
            int i2 = this.f137o;
            if (x2 >= f2 - i2 && x2 <= i2 + f2) {
                float f3 = c2[1];
                if (y2 >= f3 - i2 && y2 <= i2 + f3) {
                    this.f146x = x2 - f2;
                    this.f147y = y2 - f3;
                    this.f140r = true;
                    invalidate();
                }
            }
            int i3 = this.f132j;
            if (x2 >= (-i3) && x2 <= i3 && y2 >= (-i3) && y2 <= i3 && this.f143u) {
                this.C.setAlpha(80);
                setColor(getOldCenterColor());
                invalidate();
            } else {
                double d2 = (x2 * x2) + (y2 * y2);
                if (Math.sqrt(d2) <= this.f130h + this.f137o && Math.sqrt(d2) >= this.f130h - this.f137o && this.H) {
                    this.f140r = true;
                    invalidate();
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }
        return true;
    }

    public void setColor(int i2) {
        float f2 = f(i2);
        this.f148z = f2;
        this.f128f.setColor(b(f2));
        OpacityBar opacityBar = this.F;
        if (opacityBar != null) {
            opacityBar.setColor(this.f141s);
            this.F.setOpacity(Color.alpha(i2));
        }
        if (this.E != null) {
            Color.colorToHSV(i2, this.D);
            this.E.setColor(this.f141s);
            float[] fArr = this.D;
            float f3 = fArr[1];
            float f4 = fArr[2];
            if (f3 < f4) {
                this.E.setSaturation(f3);
            } else if (f3 > f4) {
                this.E.setValue(f4);
            }
        }
        if (this.G != null) {
            Color.colorToHSV(i2, this.D);
            this.G.setColor(this.f141s);
            this.G.setSaturation(this.D[1]);
        }
        ValueBar valueBar = this.I;
        if (valueBar != null && this.G == null) {
            Color.colorToHSV(i2, this.D);
            this.I.setColor(this.f141s);
            this.I.setValue(this.D[2]);
        } else if (valueBar != null) {
            Color.colorToHSV(i2, this.D);
            this.I.setValue(this.D[2]);
        }
        setNewCenterColor(i2);
    }

    public void setNewCenterColor(int i2) {
        this.f144v = i2;
        this.B.setColor(i2);
        if (this.f142t == 0) {
            this.f142t = i2;
            this.A.setColor(i2);
        }
        invalidate();
    }

    public void setOldCenterColor(int i2) {
        this.f142t = i2;
        this.A.setColor(i2);
        invalidate();
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
    }

    public void setOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener) {
    }

    public void setShowOldCenterColor(boolean z2) {
        this.f143u = z2;
        invalidate();
    }

    public void setTouchAnywhereOnColorWheelEnabled(boolean z2) {
        this.H = z2;
    }
}
