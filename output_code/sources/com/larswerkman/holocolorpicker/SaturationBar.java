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
public class SaturationBar extends View {

    /* renamed from: d  reason: collision with root package name */
    private int f199d;

    /* renamed from: e  reason: collision with root package name */
    private int f200e;

    /* renamed from: f  reason: collision with root package name */
    private int f201f;

    /* renamed from: g  reason: collision with root package name */
    private int f202g;

    /* renamed from: h  reason: collision with root package name */
    private int f203h;

    /* renamed from: i  reason: collision with root package name */
    private int f204i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f205j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f206k;

    /* renamed from: l  reason: collision with root package name */
    private Paint f207l;

    /* renamed from: m  reason: collision with root package name */
    private RectF f208m;

    /* renamed from: n  reason: collision with root package name */
    private Shader f209n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f210o;

    /* renamed from: p  reason: collision with root package name */
    private int f211p;

    /* renamed from: q  reason: collision with root package name */
    private float[] f212q;

    /* renamed from: r  reason: collision with root package name */
    private float f213r;

    /* renamed from: s  reason: collision with root package name */
    private float f214s;

    /* renamed from: t  reason: collision with root package name */
    private ColorPicker f215t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f216u;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnSaturationChangedListener {
    }

    private void a(int i2) {
        int i3 = i2 - this.f203h;
        if (i3 < 0) {
            i3 = 0;
        } else {
            int i4 = this.f200e;
            if (i3 > i4) {
                i3 = i4;
            }
        }
        this.f211p = Color.HSVToColor(new float[]{this.f212q[0], this.f213r * i3, 1.0f});
    }

    public int getColor() {
        return this.f211p;
    }

    public OnSaturationChangedListener getOnSaturationChangedListener() {
        return null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.f208m, this.f205j);
        if (this.f216u) {
            i2 = this.f204i;
            i3 = this.f203h;
        } else {
            i2 = this.f203h;
            i3 = this.f204i;
        }
        float f2 = i2;
        float f3 = i3;
        canvas.drawCircle(f2, f3, this.f203h, this.f207l);
        canvas.drawCircle(f2, f3, this.f202g, this.f206k);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.f201f + (this.f203h * 2);
        if (!this.f216u) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.f203h * 2;
        int i6 = i4 - i5;
        this.f200e = i6;
        if (!this.f216u) {
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
        setSaturation(bundle.getFloat("saturation"));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.f212q);
        float[] fArr = new float[3];
        Color.colorToHSV(this.f211p, fArr);
        bundle.putFloat("saturation", fArr[1]);
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f216u) {
            int i8 = this.f200e;
            int i9 = this.f203h;
            i6 = i8 + i9;
            i7 = this.f199d;
            int i10 = i2 - (i9 * 2);
            this.f200e = i10;
            this.f208m.set(i9, i9 - (i7 / 2), i10 + i9, i9 + (i7 / 2));
        } else {
            i6 = this.f199d;
            int i11 = this.f200e;
            int i12 = this.f203h;
            int i13 = i3 - (i12 * 2);
            this.f200e = i13;
            this.f208m.set(i12 - (i6 / 2), i12, (i6 / 2) + i12, i13 + i12);
            i7 = i11 + i12;
        }
        if (!isInEditMode()) {
            this.f209n = new LinearGradient(this.f203h, 0.0f, i6, i7, new int[]{-1, Color.HSVToColor(255, this.f212q)}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.f209n = new LinearGradient(this.f203h, 0.0f, i6, i7, new int[]{-1, -8257792}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.f212q);
        }
        this.f205j.setShader(this.f209n);
        int i14 = this.f200e;
        this.f213r = 1.0f / i14;
        this.f214s = i14 / 1.0f;
        float[] fArr = new float[3];
        Color.colorToHSV(this.f211p, fArr);
        if (!isInEditMode()) {
            this.f204i = Math.round((this.f214s * fArr[1]) + this.f203h);
        } else {
            this.f204i = this.f200e + this.f203h;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.f216u) {
            y2 = motionEvent.getX();
        } else {
            y2 = motionEvent.getY();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2 && this.f210o) {
                    int i2 = this.f203h;
                    if (y2 >= i2 && y2 <= this.f200e + i2) {
                        this.f204i = Math.round(y2);
                        a(Math.round(y2));
                        this.f206k.setColor(this.f211p);
                        ColorPicker colorPicker = this.f215t;
                        if (colorPicker != null) {
                            colorPicker.setNewCenterColor(this.f211p);
                            this.f215t.e(this.f211p);
                            this.f215t.d(this.f211p);
                        }
                        invalidate();
                    } else if (y2 < i2) {
                        this.f204i = i2;
                        this.f211p = -1;
                        this.f206k.setColor(-1);
                        ColorPicker colorPicker2 = this.f215t;
                        if (colorPicker2 != null) {
                            colorPicker2.setNewCenterColor(this.f211p);
                            this.f215t.e(this.f211p);
                            this.f215t.d(this.f211p);
                        }
                        invalidate();
                    } else {
                        int i3 = this.f200e;
                        if (y2 > i2 + i3) {
                            this.f204i = i2 + i3;
                            int HSVToColor = Color.HSVToColor(this.f212q);
                            this.f211p = HSVToColor;
                            this.f206k.setColor(HSVToColor);
                            ColorPicker colorPicker3 = this.f215t;
                            if (colorPicker3 != null) {
                                colorPicker3.setNewCenterColor(this.f211p);
                                this.f215t.e(this.f211p);
                                this.f215t.d(this.f211p);
                            }
                            invalidate();
                        }
                    }
                }
            } else {
                this.f210o = false;
            }
        } else {
            this.f210o = true;
            int i4 = this.f203h;
            if (y2 >= i4 && y2 <= i4 + this.f200e) {
                this.f204i = Math.round(y2);
                a(Math.round(y2));
                this.f206k.setColor(this.f211p);
                invalidate();
            }
        }
        return true;
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.f216u) {
            i3 = this.f200e + this.f203h;
            i4 = this.f199d;
        } else {
            i3 = this.f199d;
            i4 = this.f200e + this.f203h;
        }
        Color.colorToHSV(i2, this.f212q);
        LinearGradient linearGradient = new LinearGradient(this.f203h, 0.0f, i3, i4, new int[]{-1, i2}, (float[]) null, Shader.TileMode.CLAMP);
        this.f209n = linearGradient;
        this.f205j.setShader(linearGradient);
        a(this.f204i);
        this.f206k.setColor(this.f211p);
        ColorPicker colorPicker = this.f215t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f211p);
            if (this.f215t.h()) {
                this.f215t.e(this.f211p);
            } else if (this.f215t.g()) {
                this.f215t.d(this.f211p);
            }
        }
        invalidate();
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.f215t = colorPicker;
    }

    public void setOnSaturationChangedListener(OnSaturationChangedListener onSaturationChangedListener) {
    }

    public void setSaturation(float f2) {
        int round = Math.round(this.f214s * f2) + this.f203h;
        this.f204i = round;
        a(round);
        this.f206k.setColor(this.f211p);
        ColorPicker colorPicker = this.f215t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f211p);
            this.f215t.e(this.f211p);
            this.f215t.d(this.f211p);
        }
        invalidate();
    }
}
