package com.larswerkman.holocolorpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class OpacityBar extends View {

    /* renamed from: d  reason: collision with root package name */
    private int f149d;

    /* renamed from: e  reason: collision with root package name */
    private int f150e;

    /* renamed from: f  reason: collision with root package name */
    private int f151f;

    /* renamed from: g  reason: collision with root package name */
    private int f152g;

    /* renamed from: h  reason: collision with root package name */
    private int f153h;

    /* renamed from: i  reason: collision with root package name */
    private int f154i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f155j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f156k;

    /* renamed from: l  reason: collision with root package name */
    private Paint f157l;

    /* renamed from: m  reason: collision with root package name */
    private RectF f158m;

    /* renamed from: n  reason: collision with root package name */
    private Shader f159n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f160o;

    /* renamed from: p  reason: collision with root package name */
    private int f161p;

    /* renamed from: q  reason: collision with root package name */
    private float[] f162q;

    /* renamed from: r  reason: collision with root package name */
    private float f163r;

    /* renamed from: s  reason: collision with root package name */
    private float f164s;

    /* renamed from: t  reason: collision with root package name */
    private ColorPicker f165t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f166u;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnOpacityChangedListener {
    }

    private void a(int i2) {
        int i3 = i2 - this.f153h;
        if (i3 < 0) {
            i3 = 0;
        } else {
            int i4 = this.f150e;
            if (i3 > i4) {
                i3 = i4;
            }
        }
        int HSVToColor = Color.HSVToColor(Math.round(this.f163r * i3), this.f162q);
        this.f161p = HSVToColor;
        if (Color.alpha(HSVToColor) > 250) {
            this.f161p = Color.HSVToColor(this.f162q);
        } else if (Color.alpha(this.f161p) < 5) {
            this.f161p = 0;
        }
    }

    public int getColor() {
        return this.f161p;
    }

    public OnOpacityChangedListener getOnOpacityChangedListener() {
        return null;
    }

    public int getOpacity() {
        int round = Math.round(this.f163r * (this.f154i - this.f153h));
        if (round < 5) {
            return 0;
        }
        if (round > 250) {
            return 255;
        }
        return round;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.f158m, this.f155j);
        if (this.f166u) {
            i2 = this.f154i;
            i3 = this.f153h;
        } else {
            i2 = this.f153h;
            i3 = this.f154i;
        }
        float f2 = i2;
        float f3 = i3;
        canvas.drawCircle(f2, f3, this.f153h, this.f157l);
        canvas.drawCircle(f2, f3, this.f152g, this.f156k);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.f151f + (this.f153h * 2);
        if (!this.f166u) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.f153h * 2;
        int i6 = i4 - i5;
        this.f150e = i6;
        if (!this.f166u) {
            setMeasuredDimension(i5, i6 + i5);
        } else {
            setMeasuredDimension(i6 + i5, i5);
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("parent"));
        setColor(Color.HSVToColor(bundle.getFloatArray("color")));
        setOpacity(bundle.getInt("opacity"));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.f162q);
        bundle.putInt("opacity", getOpacity());
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f166u) {
            int i8 = this.f150e;
            int i9 = this.f153h;
            i6 = i8 + i9;
            i7 = this.f149d;
            int i10 = i2 - (i9 * 2);
            this.f150e = i10;
            this.f158m.set(i9, i9 - (i7 / 2), i10 + i9, i9 + (i7 / 2));
        } else {
            i6 = this.f149d;
            int i11 = this.f150e;
            int i12 = this.f153h;
            int i13 = i3 - (i12 * 2);
            this.f150e = i13;
            this.f158m.set(i12 - (i6 / 2), i12, (i6 / 2) + i12, i13 + i12);
            i7 = i11 + i12;
        }
        if (!isInEditMode()) {
            this.f159n = new LinearGradient(this.f153h, 0.0f, i6, i7, new int[]{Color.HSVToColor(0, this.f162q), Color.HSVToColor(255, this.f162q)}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.f159n = new LinearGradient(this.f153h, 0.0f, i6, i7, new int[]{8519424, -8257792}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.f162q);
        }
        this.f155j.setShader(this.f159n);
        int i14 = this.f150e;
        this.f163r = 255.0f / i14;
        this.f164s = i14 / 255.0f;
        Color.colorToHSV(this.f161p, new float[3]);
        if (!isInEditMode()) {
            this.f154i = Math.round((this.f164s * Color.alpha(this.f161p)) + this.f153h);
        } else {
            this.f154i = this.f150e + this.f153h;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.f166u) {
            y2 = motionEvent.getX();
        } else {
            y2 = motionEvent.getY();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2 && this.f160o) {
                    int i2 = this.f153h;
                    if (y2 >= i2 && y2 <= this.f150e + i2) {
                        this.f154i = Math.round(y2);
                        a(Math.round(y2));
                        this.f156k.setColor(this.f161p);
                        ColorPicker colorPicker = this.f165t;
                        if (colorPicker != null) {
                            colorPicker.setNewCenterColor(this.f161p);
                        }
                        invalidate();
                    } else if (y2 < i2) {
                        this.f154i = i2;
                        this.f161p = 0;
                        this.f156k.setColor(0);
                        ColorPicker colorPicker2 = this.f165t;
                        if (colorPicker2 != null) {
                            colorPicker2.setNewCenterColor(this.f161p);
                        }
                        invalidate();
                    } else {
                        int i3 = this.f150e;
                        if (y2 > i2 + i3) {
                            this.f154i = i2 + i3;
                            int HSVToColor = Color.HSVToColor(this.f162q);
                            this.f161p = HSVToColor;
                            this.f156k.setColor(HSVToColor);
                            ColorPicker colorPicker3 = this.f165t;
                            if (colorPicker3 != null) {
                                colorPicker3.setNewCenterColor(this.f161p);
                            }
                            invalidate();
                        }
                    }
                }
            } else {
                this.f160o = false;
            }
        } else {
            this.f160o = true;
            int i4 = this.f153h;
            if (y2 >= i4 && y2 <= i4 + this.f150e) {
                this.f154i = Math.round(y2);
                a(Math.round(y2));
                this.f156k.setColor(this.f161p);
                invalidate();
            }
        }
        return true;
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.f166u) {
            i3 = this.f150e + this.f153h;
            i4 = this.f149d;
        } else {
            i3 = this.f149d;
            i4 = this.f150e + this.f153h;
        }
        Color.colorToHSV(i2, this.f162q);
        LinearGradient linearGradient = new LinearGradient(this.f153h, 0.0f, i3, i4, new int[]{Color.HSVToColor(0, this.f162q), i2}, (float[]) null, Shader.TileMode.CLAMP);
        this.f159n = linearGradient;
        this.f155j.setShader(linearGradient);
        a(this.f154i);
        this.f156k.setColor(this.f161p);
        ColorPicker colorPicker = this.f165t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f161p);
        }
        invalidate();
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.f165t = colorPicker;
    }

    public void setOnOpacityChangedListener(OnOpacityChangedListener onOpacityChangedListener) {
    }

    public void setOpacity(int i2) {
        int round = Math.round(this.f164s * i2) + this.f153h;
        this.f154i = round;
        a(round);
        this.f156k.setColor(this.f161p);
        ColorPicker colorPicker = this.f165t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f161p);
        }
        invalidate();
    }
}
