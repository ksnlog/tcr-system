package net.steamcrafted.loadtoast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class MaterialProgressDrawable extends Drawable implements Animatable {

    /* renamed from: q  reason: collision with root package name */
    private static final Interpolator f1757q = new LinearInterpolator();

    /* renamed from: r  reason: collision with root package name */
    private static final Interpolator f1758r = new EndCurveInterpolator();

    /* renamed from: s  reason: collision with root package name */
    private static final Interpolator f1759s = new StartCurveInterpolator();

    /* renamed from: t  reason: collision with root package name */
    private static final Interpolator f1760t = new AccelerateDecelerateInterpolator();

    /* renamed from: d  reason: collision with root package name */
    private final int[] f1761d;

    /* renamed from: e  reason: collision with root package name */
    private final ArrayList<Animation> f1762e = new ArrayList<>();

    /* renamed from: f  reason: collision with root package name */
    private final Ring f1763f;

    /* renamed from: g  reason: collision with root package name */
    private final Drawable.Callback f1764g;

    /* renamed from: h  reason: collision with root package name */
    boolean f1765h;

    /* renamed from: i  reason: collision with root package name */
    private float f1766i;

    /* renamed from: j  reason: collision with root package name */
    private Resources f1767j;

    /* renamed from: k  reason: collision with root package name */
    private View f1768k;

    /* renamed from: l  reason: collision with root package name */
    private Animation f1769l;

    /* renamed from: m  reason: collision with root package name */
    private float f1770m;

    /* renamed from: n  reason: collision with root package name */
    private double f1771n;

    /* renamed from: o  reason: collision with root package name */
    private double f1772o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f1773p;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return super.getInterpolation(Math.max(0.0f, (f2 - 0.5f) * 2.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Ring {

        /* renamed from: a  reason: collision with root package name */
        private final RectF f1779a = new RectF();

        /* renamed from: b  reason: collision with root package name */
        private final Paint f1780b;

        /* renamed from: c  reason: collision with root package name */
        private final Paint f1781c;

        /* renamed from: d  reason: collision with root package name */
        private final Drawable.Callback f1782d;

        /* renamed from: e  reason: collision with root package name */
        private final Paint f1783e;

        /* renamed from: f  reason: collision with root package name */
        private float f1784f;

        /* renamed from: g  reason: collision with root package name */
        private float f1785g;

        /* renamed from: h  reason: collision with root package name */
        private float f1786h;

        /* renamed from: i  reason: collision with root package name */
        private float f1787i;

        /* renamed from: j  reason: collision with root package name */
        private float f1788j;

        /* renamed from: k  reason: collision with root package name */
        private int[] f1789k;

        /* renamed from: l  reason: collision with root package name */
        private int f1790l;

        /* renamed from: m  reason: collision with root package name */
        private float f1791m;

        /* renamed from: n  reason: collision with root package name */
        private float f1792n;

        /* renamed from: o  reason: collision with root package name */
        private float f1793o;

        /* renamed from: p  reason: collision with root package name */
        private boolean f1794p;

        /* renamed from: q  reason: collision with root package name */
        private Path f1795q;

        /* renamed from: r  reason: collision with root package name */
        private float f1796r;

        /* renamed from: s  reason: collision with root package name */
        private double f1797s;

        /* renamed from: t  reason: collision with root package name */
        private int f1798t;

        /* renamed from: u  reason: collision with root package name */
        private int f1799u;

        /* renamed from: v  reason: collision with root package name */
        private int f1800v;

        /* renamed from: w  reason: collision with root package name */
        private int f1801w;

        public Ring(Drawable.Callback callback) {
            Paint paint = new Paint();
            this.f1780b = paint;
            Paint paint2 = new Paint();
            this.f1781c = paint2;
            this.f1783e = new Paint();
            this.f1784f = 0.0f;
            this.f1785g = 0.0f;
            this.f1786h = 0.0f;
            this.f1787i = 5.0f;
            this.f1788j = 2.5f;
            this.f1782d = callback;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
        }

        private void b(Canvas canvas, float f2, float f3, Rect rect) {
            if (this.f1794p) {
                Path path = this.f1795q;
                if (path == null) {
                    Path path2 = new Path();
                    this.f1795q = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                double cos = this.f1797s * Math.cos(0.0d);
                double exactCenterX = rect.exactCenterX();
                Double.isNaN(exactCenterX);
                double sin = this.f1797s * Math.sin(0.0d);
                double exactCenterY = rect.exactCenterY();
                Double.isNaN(exactCenterY);
                this.f1795q.moveTo(0.0f, 0.0f);
                this.f1795q.lineTo(this.f1798t * this.f1796r, 0.0f);
                Path path3 = this.f1795q;
                float f4 = this.f1796r;
                path3.lineTo((this.f1798t * f4) / 2.0f, this.f1799u * f4);
                this.f1795q.offset(((float) (cos + exactCenterX)) - ((this.f1798t * this.f1796r) / 2.0f), (float) (sin + exactCenterY));
                this.f1795q.close();
                this.f1781c.setColor(this.f1789k[this.f1790l]);
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                }
                canvas.rotate((f2 + f3) - 0.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.f1795q, this.f1781c);
            }
        }

        private void l() {
            this.f1782d.invalidateDrawable(null);
        }

        public void A() {
            this.f1791m = this.f1784f;
            this.f1792n = this.f1785g;
            this.f1793o = this.f1786h;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f1779a;
            rectF.set(rect);
            float f2 = this.f1788j;
            rectF.inset(f2, f2);
            float f3 = this.f1784f;
            float f4 = this.f1786h;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f1785g + f4) * 360.0f) - f5;
            this.f1780b.setColor(this.f1789k[this.f1790l]);
            canvas.drawArc(rectF, f5, f6, false, this.f1780b);
            b(canvas, f5, f6, rect);
            if (this.f1800v < 255) {
                this.f1783e.setColor(this.f1801w);
                this.f1783e.setAlpha(255 - this.f1800v);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), rect.width() / 2, this.f1783e);
            }
        }

        public int c() {
            return this.f1800v;
        }

        public double d() {
            return this.f1797s;
        }

        public float e() {
            return this.f1785g;
        }

        public float f() {
            return this.f1784f;
        }

        public float g() {
            return this.f1792n;
        }

        public float h() {
            return this.f1793o;
        }

        public float i() {
            return this.f1791m;
        }

        public float j() {
            return this.f1787i;
        }

        public void k() {
            this.f1790l = (this.f1790l + 1) % this.f1789k.length;
        }

        public void m() {
            this.f1791m = 0.0f;
            this.f1792n = 0.0f;
            this.f1793o = 0.0f;
            y(0.0f);
            u(0.0f);
            w(0.0f);
        }

        public void n(int i2) {
            this.f1800v = i2;
        }

        public void o(float f2, float f3) {
            this.f1798t = (int) f2;
            this.f1799u = (int) f3;
        }

        public void p(int i2) {
            this.f1801w = i2;
        }

        public void q(double d2) {
            this.f1797s = d2;
        }

        public void r(ColorFilter colorFilter) {
            this.f1780b.setColorFilter(colorFilter);
            l();
        }

        public void s(int i2) {
            this.f1790l = i2;
        }

        public void t(int[] iArr) {
            this.f1789k = iArr;
            s(0);
        }

        public void u(float f2) {
            this.f1785g = f2;
            l();
        }

        public void v(int i2, int i3) {
            double ceil;
            float min = Math.min(i2, i3);
            double d2 = this.f1797s;
            if (d2 > 0.0d && min >= 0.0f) {
                double d3 = min / 2.0f;
                Double.isNaN(d3);
                ceil = d3 - d2;
            } else {
                ceil = Math.ceil(this.f1787i / 2.0f);
            }
            this.f1788j = (float) ceil;
        }

        public void w(float f2) {
            this.f1786h = f2;
            l();
        }

        public void x(boolean z2) {
            if (this.f1794p != z2) {
                this.f1794p = z2;
                l();
            }
        }

        public void y(float f2) {
            this.f1784f = f2;
            l();
        }

        public void z(float f2) {
            this.f1787i = f2;
            this.f1780b.setStrokeWidth(f2);
            l();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return super.getInterpolation(Math.min(1.0f, f2 * 2.0f));
        }
    }

    public MaterialProgressDrawable(Context context, View view) {
        int[] iArr = {-16777216};
        this.f1761d = iArr;
        Drawable.Callback callback = new Drawable.Callback() { // from class: net.steamcrafted.loadtoast.MaterialProgressDrawable.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                MaterialProgressDrawable.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
                MaterialProgressDrawable.this.scheduleSelf(runnable, j2);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                MaterialProgressDrawable.this.unscheduleSelf(runnable);
            }
        };
        this.f1764g = callback;
        this.f1773p = false;
        this.f1768k = view;
        this.f1767j = context.getResources();
        Ring ring = new Ring(callback);
        this.f1763f = ring;
        ring.t(iArr);
        n(1);
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(float f2, Ring ring) {
        ring.y(ring.i() + ((ring.g() - ring.i()) * f2));
        ring.w(ring.h() + ((((float) (Math.floor(ring.h() / 0.8f) + 1.0d)) - ring.h()) * f2));
    }

    private void m() {
        final Ring ring = this.f1763f;
        Animation animation = new Animation() { // from class: net.steamcrafted.loadtoast.MaterialProgressDrawable.2
            @Override // android.view.animation.Animation
            public void applyTransformation(float f2, Transformation transformation) {
                MaterialProgressDrawable materialProgressDrawable = MaterialProgressDrawable.this;
                if (materialProgressDrawable.f1765h) {
                    materialProgressDrawable.f(f2, ring);
                    return;
                }
                double j2 = ring.j();
                Double.isNaN(j2);
                float radians = (float) Math.toRadians(j2 / (ring.d() * 6.283185307179586d));
                float g2 = ring.g();
                float i2 = ring.i();
                float h2 = ring.h();
                float interpolation = g2 + ((0.8f - radians) * MaterialProgressDrawable.f1759s.getInterpolation(f2));
                float interpolation2 = i2 + (MaterialProgressDrawable.f1758r.getInterpolation(f2) * 0.8f);
                if (Math.abs(interpolation - interpolation2) >= 1.0f) {
                    interpolation = interpolation2 + 0.5f;
                }
                ring.u(interpolation);
                ring.y(interpolation2);
                ring.w(h2 + (0.25f * f2));
                MaterialProgressDrawable.this.j((f2 * 144.0f) + ((MaterialProgressDrawable.this.f1770m / 5.0f) * 720.0f));
            }
        };
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(f1757q);
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: net.steamcrafted.loadtoast.MaterialProgressDrawable.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation2) {
                ring.A();
                ring.k();
                Ring ring2 = ring;
                ring2.y(ring2.e());
                MaterialProgressDrawable materialProgressDrawable = MaterialProgressDrawable.this;
                if (materialProgressDrawable.f1765h) {
                    materialProgressDrawable.f1765h = false;
                    animation2.setDuration(1333L);
                    ring.x(false);
                    return;
                }
                materialProgressDrawable.f1770m = (materialProgressDrawable.f1770m + 1.0f) % 5.0f;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation2) {
                MaterialProgressDrawable.this.f1770m = 0.0f;
            }
        });
        this.f1769l = animation;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.f1766i, bounds.exactCenterX(), bounds.exactCenterY());
        this.f1763f.a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void g(int i2) {
        this.f1763f.p(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1763f.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.f1772o;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.f1771n;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void h(int... iArr) {
        this.f1763f.t(iArr);
        this.f1763f.s(0);
    }

    public void i(float f2) {
        this.f1763f.w(f2);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return !this.f1769l.hasEnded();
    }

    void j(float f2) {
        this.f1766i = f2;
        invalidateSelf();
    }

    public void k(double d2, double d3, double d4, double d5, float f2, float f3) {
        Ring ring = this.f1763f;
        this.f1771n = d2;
        this.f1772o = d3;
        ring.z((float) d5);
        ring.q(d4);
        ring.s(0);
        ring.o(f2, f3);
        ring.v((int) this.f1771n, (int) this.f1772o);
    }

    public void l(float f2, float f3) {
        this.f1763f.y(f2);
        this.f1763f.u(f3);
    }

    public void n(int i2) {
        float f2 = this.f1767j.getDisplayMetrics().density;
        if (i2 == 0) {
            double d2 = 56.0f * f2;
            k(d2, d2, 12.5f * f2, 3.0f * f2, f2 * 12.0f, f2 * 6.0f);
            return;
        }
        double d3 = 40.0f * f2;
        k(d3, d3, 8.75f * f2, 2.5f * f2, f2 * 10.0f, f2 * 5.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1763f.n(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1763f.r(colorFilter);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f1769l.reset();
        this.f1763f.A();
        this.f1763f.x(this.f1773p);
        if (this.f1763f.e() != this.f1763f.f()) {
            this.f1765h = true;
            this.f1769l.setDuration(666L);
            this.f1768k.startAnimation(this.f1769l);
            return;
        }
        this.f1763f.s(0);
        this.f1763f.m();
        this.f1769l.setDuration(1333L);
        this.f1768k.startAnimation(this.f1769l);
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f1768k.clearAnimation();
        j(0.0f);
        this.f1763f.x(false);
        this.f1763f.s(0);
        this.f1763f.m();
    }
}
