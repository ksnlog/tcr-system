package net.steamcrafted.loadtoast;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.nineoldandroids.animation.ValueAnimator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LoadToastView extends ImageView {
    private boolean A;
    private Path B;
    private MaterialProgressDrawable C;

    /* renamed from: d  reason: collision with root package name */
    private String f1732d;

    /* renamed from: e  reason: collision with root package name */
    private Paint f1733e;

    /* renamed from: f  reason: collision with root package name */
    private Paint f1734f;

    /* renamed from: g  reason: collision with root package name */
    private Paint f1735g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f1736h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f1737i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f1738j;

    /* renamed from: k  reason: collision with root package name */
    private Rect f1739k;

    /* renamed from: l  reason: collision with root package name */
    private Rect f1740l;

    /* renamed from: m  reason: collision with root package name */
    private RectF f1741m;

    /* renamed from: n  reason: collision with root package name */
    private int f1742n;

    /* renamed from: o  reason: collision with root package name */
    private int f1743o;

    /* renamed from: p  reason: collision with root package name */
    private int f1744p;

    /* renamed from: q  reason: collision with root package name */
    private int f1745q;

    /* renamed from: r  reason: collision with root package name */
    private int f1746r;

    /* renamed from: s  reason: collision with root package name */
    private float f1747s;

    /* renamed from: t  reason: collision with root package name */
    private int f1748t;

    /* renamed from: u  reason: collision with root package name */
    private long f1749u;

    /* renamed from: v  reason: collision with root package name */
    private Drawable f1750v;

    /* renamed from: w  reason: collision with root package name */
    private Drawable f1751w;

    /* renamed from: x  reason: collision with root package name */
    private ValueAnimator f1752x;

    /* renamed from: y  reason: collision with root package name */
    private ValueAnimator f1753y;

    /* renamed from: z  reason: collision with root package name */
    private boolean f1754z;

    public LoadToastView(Context context) {
        super(context);
        this.f1732d = "";
        this.f1733e = new Paint();
        this.f1734f = new Paint();
        this.f1735g = new Paint();
        this.f1736h = new Paint();
        this.f1737i = new Paint();
        this.f1738j = new Paint();
        this.f1740l = new Rect();
        this.f1741m = new RectF();
        this.f1742n = 100;
        this.f1743o = 20;
        this.f1744p = 40;
        this.f1745q = 48;
        this.f1746r = 3;
        this.f1747s = 0.0f;
        this.f1748t = 1;
        this.f1749u = 0L;
        this.f1754z = true;
        this.A = false;
        this.B = new Path();
        this.f1733e.setTextSize(15.0f);
        this.f1733e.setColor(-16777216);
        this.f1733e.setAntiAlias(true);
        this.f1734f.setColor(-1);
        this.f1734f.setAntiAlias(true);
        this.f1735g.setColor(-16776961);
        this.f1735g.setAntiAlias(true);
        this.f1736h.setStrokeWidth(d(4));
        this.f1736h.setAntiAlias(true);
        this.f1736h.setColor(f());
        this.f1736h.setStyle(Paint.Style.STROKE);
        this.f1737i.setColor(getResources().getColor(R$color.f1803b));
        this.f1738j.setColor(getResources().getColor(R$color.f1802a));
        this.f1737i.setAntiAlias(true);
        this.f1738j.setAntiAlias(true);
        this.f1742n = d(this.f1742n);
        this.f1743o = d(this.f1743o);
        this.f1744p = d(this.f1744p);
        this.f1745q = d(this.f1745q);
        this.f1746r = d(this.f1746r);
        this.f1748t = d(this.f1748t);
        int i2 = (this.f1745q - this.f1744p) / 2;
        int i3 = this.f1745q;
        int i4 = this.f1742n;
        int i5 = this.f1744p;
        this.f1739k = new Rect((i3 + i4) - i2, i2, ((i3 + i4) - i2) + i5, i5 + i2);
        Drawable e2 = ContextCompat.e(getContext(), R$drawable.f1805b);
        this.f1750v = e2;
        e2.setBounds(this.f1739k);
        Drawable e3 = ContextCompat.e(getContext(), R$drawable.f1804a);
        this.f1751w = e3;
        e3.setBounds(this.f1739k);
        ValueAnimator D = ValueAnimator.D(0.0f, 1.0f);
        this.f1752x = D;
        D.f(6000L);
        this.f1752x.t(new ValueAnimator.AnimatorUpdateListener() { // from class: net.steamcrafted.loadtoast.LoadToastView.1
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void e(ValueAnimator valueAnimator) {
                LoadToastView.this.postInvalidate();
            }
        });
        this.f1752x.K(-1);
        this.f1752x.J(9999999);
        this.f1752x.I(new LinearInterpolator());
        this.f1752x.g();
        g();
        b();
    }

    private void b() {
        this.A = false;
        this.f1749u = 0L;
        this.f1733e.setTextSize(this.f1743o);
        Paint paint = this.f1733e;
        String str = this.f1732d;
        paint.getTextBounds(str, 0, str.length(), this.f1740l);
        if (this.f1740l.width() > this.f1742n) {
            int i2 = this.f1743o;
            while (i2 > d(13) && this.f1740l.width() > this.f1742n) {
                i2--;
                this.f1733e.setTextSize(i2);
                Paint paint2 = this.f1733e;
                String str2 = this.f1732d;
                paint2.getTextBounds(str2, 0, str2.length(), this.f1740l);
            }
            if (this.f1740l.width() > this.f1742n) {
                this.A = true;
            }
        }
    }

    private void c() {
        ValueAnimator D = ValueAnimator.D(0.0f, 1.0f);
        this.f1753y = D;
        D.f(600L);
        this.f1753y.t(new ValueAnimator.AnimatorUpdateListener() { // from class: net.steamcrafted.loadtoast.LoadToastView.2
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void e(ValueAnimator valueAnimator) {
                LoadToastView.this.f1747s = valueAnimator.z() * 2.0f;
                LoadToastView.this.postInvalidate();
            }
        });
        this.f1753y.I(new DecelerateInterpolator());
        this.f1753y.g();
    }

    private int d(int i2) {
        return (int) TypedValue.applyDimension(1, i2, getResources().getDisplayMetrics());
    }

    private int f() {
        int rgb = Color.rgb(155, 155, 155);
        if (Build.VERSION.SDK_INT >= 21) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new TypedValue().data, new int[]{16843829});
            int color = obtainStyledAttributes.getColor(0, rgb);
            obtainStyledAttributes.recycle();
            return color;
        }
        return rgb;
    }

    private void g() {
        int i2;
        if (!h()) {
            MaterialProgressDrawable materialProgressDrawable = new MaterialProgressDrawable(getContext(), this);
            this.C = materialProgressDrawable;
            materialProgressDrawable.l(0.0f, 0.5f);
            this.C.i(0.5f);
            int i3 = this.f1745q;
            int i4 = this.f1746r;
            double d2 = i3;
            this.C.k(d2, d2, (i3 - i2) / 4, i4, i4 * 4, i4 * 2);
            this.C.g(0);
            this.C.h(this.f1736h.getColor());
            this.C.setVisible(true, false);
            this.C.setAlpha(255);
            setImageDrawable(null);
            setImageDrawable(this.C);
            this.C.start();
        }
    }

    private boolean h() {
        try {
            Class.forName("androidx.test.espresso.Espresso");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private int i(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(this.f1745q, size);
        }
        return this.f1745q;
    }

    private int j(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            int i3 = this.f1744p + this.f1742n + this.f1745q;
            if (mode == Integer.MIN_VALUE) {
                return Math.min(i3, size);
            }
            return i3;
        }
        return size;
    }

    public void e() {
        this.f1754z = false;
        c();
    }

    public void k() {
        this.f1747s = 0.0f;
        ValueAnimator valueAnimator = this.f1753y;
        if (valueAnimator != null) {
            valueAnimator.E();
        }
    }

    public void l() {
        this.f1754z = true;
        c();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.f1753y;
        if (valueAnimator != null) {
            valueAnimator.E();
        }
        ValueAnimator valueAnimator2 = this.f1752x;
        if (valueAnimator2 != null) {
            valueAnimator2.E();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3;
        float f2;
        Drawable drawable;
        Paint paint;
        float max = Math.max(1.0f - this.f1747s, 0.0f);
        if (this.f1732d.length() == 0) {
            max = 0.0f;
        }
        float f3 = (1.0f - max) * (this.f1744p + this.f1742n);
        float f4 = f3 / 2.0f;
        this.f1733e.setAlpha((int) (Math.max(0.0f, (10.0f * max) - 9.0f) * 255.0f));
        this.f1741m.set((this.f1739k.left + d(4)) - f4, this.f1739k.top + d(4), (this.f1739k.right - d(4)) - f4, this.f1739k.bottom - d(4));
        double d2 = this.f1745q * 2;
        Double.isNaN(d2);
        int sqrt = (int) ((d2 * (Math.sqrt(2.0d) - 1.0d)) / 3.0d);
        int i4 = this.f1745q;
        int i5 = this.f1744p;
        int i6 = (i4 - i5) / 2;
        double d3 = i5 * 2;
        Double.isNaN(d3);
        int sqrt2 = (int) ((d3 * (Math.sqrt(2.0d) - 1.0d)) / 3.0d);
        int i7 = this.f1744p;
        float f5 = (((f4 * 2.0f) + i4) + ((this.f1742n + i7) * max)) - f3;
        this.B.reset();
        int i8 = i4 / 2;
        float f6 = i8;
        this.B.moveTo(f4 + f6, 0.0f);
        this.B.rLineTo((this.f1744p + this.f1742n) * max, 0.0f);
        float f7 = sqrt;
        this.B.rCubicTo(f7, 0.0f, f6, i8 - sqrt, f6, f6);
        this.B.rLineTo(-i6, 0.0f);
        float f8 = -sqrt2;
        int i9 = (-i7) / 2;
        float f9 = i9 + sqrt2;
        float f10 = i9;
        this.B.rCubicTo(0.0f, f8, f9, f10, f10, f10);
        int i10 = i7 / 2;
        float f11 = i10 - sqrt2;
        float f12 = i10;
        this.B.rCubicTo(f8, 0.0f, f10, f11, f10, f12);
        float f13 = sqrt2;
        this.B.rCubicTo(0.0f, f13, f11, f12, f12, f12);
        this.B.rCubicTo(f13, 0.0f, f12, f9, f12, f10);
        this.B.rLineTo(i6, 0.0f);
        float f14 = (-i4) / 2;
        this.B.rCubicTo(0.0f, f7, sqrt - i8, f6, f14, f6);
        this.B.rLineTo(max * ((-this.f1744p) - this.f1742n), 0.0f);
        float f15 = -sqrt;
        this.B.rCubicTo(f15, 0.0f, f14, i2 + sqrt, f14, f14);
        this.B.rCubicTo(0.0f, f15, i3 + i8, f14, f6, f14);
        canvas.drawCircle(this.f1741m.centerX(), this.f1741m.centerY(), this.f1739k.height() / 1.9f, this.f1734f);
        canvas.drawPath(this.B, this.f1734f);
        this.B.reset();
        canvas.save();
        canvas.translate((f5 - this.f1745q) / 2.0f, 0.0f);
        super.onDraw(canvas);
        canvas.restore();
        float f16 = this.f1747s;
        if (f16 > 1.0f) {
            if (this.f1754z) {
                drawable = this.f1750v;
            } else {
                drawable = this.f1751w;
            }
            float f17 = f16 - 1.0f;
            this.f1733e.setAlpha((int) ((128.0f * f17) + 127.0f));
            float f18 = (0.75f * f17) + 0.25f;
            int i11 = this.f1745q;
            int i12 = (int) (((1.0f - f18) * i11) / 2.0f);
            float f19 = 1.0f - f17;
            int i13 = (int) ((i11 * f19) / 8.0f);
            RectF rectF = this.f1741m;
            drawable.setBounds(((int) rectF.left) + i12, ((int) rectF.top) + i12 + i13, ((int) rectF.right) - i12, (((int) rectF.bottom) - i12) + i13);
            int i14 = this.f1745q;
            float f20 = f4 + (i14 / 2);
            float f21 = ((i14 * f19) / 8.0f) + (i14 / 2);
            float f22 = (f18 * i14) / 2.0f;
            if (this.f1754z) {
                paint = this.f1737i;
            } else {
                paint = this.f1738j;
            }
            canvas.drawCircle(f20, f21, f22, paint);
            canvas.save();
            int i15 = this.f1745q;
            canvas.rotate(f19 * 90.0f, f4 + (i15 / 2), i15 / 2);
            drawable.draw(canvas);
            canvas.restore();
            this.f1749u = 0L;
            return;
        }
        int descent = (int) (f6 - ((this.f1733e.descent() + this.f1733e.ascent()) / 2.0f));
        if (this.A) {
            if (this.f1749u == 0) {
                this.f1749u = System.currentTimeMillis();
                f2 = 0.0f;
            } else {
                float currentTimeMillis = (((float) (System.currentTimeMillis() - this.f1749u)) / 16.0f) * this.f1748t;
                if (currentTimeMillis - this.f1742n > this.f1740l.width()) {
                    this.f1749u = 0L;
                }
                f2 = currentTimeMillis;
            }
            canvas.clipRect(i8, 0, this.f1742n + i8, this.f1745q);
            canvas.drawText(this.f1732d, (f6 - f2) + this.f1742n, descent, this.f1733e);
            return;
        }
        String str = this.f1732d;
        canvas.drawText(str, 0, str.length(), i8 + ((this.f1742n - this.f1740l.width()) / 2), descent, this.f1733e);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(j(i2), i(i3));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.f1734f.setColor(i2);
        this.f1735g.setColor(i2);
    }

    public void setProgressColor(int i2) {
        this.f1736h.setColor(i2);
        MaterialProgressDrawable materialProgressDrawable = this.C;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.h(i2);
        }
    }

    public void setText(String str) {
        this.f1732d = str;
        b();
    }

    public void setTextColor(int i2) {
        this.f1733e.setColor(i2);
    }
}
