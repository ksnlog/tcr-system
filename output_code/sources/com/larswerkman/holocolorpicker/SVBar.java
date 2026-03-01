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
public class SVBar extends View {

    /* renamed from: d  reason: collision with root package name */
    private int f181d;

    /* renamed from: e  reason: collision with root package name */
    private int f182e;

    /* renamed from: f  reason: collision with root package name */
    private int f183f;

    /* renamed from: g  reason: collision with root package name */
    private int f184g;

    /* renamed from: h  reason: collision with root package name */
    private int f185h;

    /* renamed from: i  reason: collision with root package name */
    private int f186i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f187j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f188k;

    /* renamed from: l  reason: collision with root package name */
    private Paint f189l;

    /* renamed from: m  reason: collision with root package name */
    private RectF f190m;

    /* renamed from: n  reason: collision with root package name */
    private Shader f191n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f192o;

    /* renamed from: p  reason: collision with root package name */
    private int f193p;

    /* renamed from: q  reason: collision with root package name */
    private float[] f194q;

    /* renamed from: r  reason: collision with root package name */
    private float f195r;

    /* renamed from: s  reason: collision with root package name */
    private float f196s;

    /* renamed from: t  reason: collision with root package name */
    private ColorPicker f197t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f198u;

    private void a(int i2) {
        int i3 = i2 - this.f185h;
        int i4 = this.f182e;
        if (i3 > i4 / 2 && i3 < i4) {
            this.f193p = Color.HSVToColor(new float[]{this.f194q[0], 1.0f, 1.0f - (this.f195r * (i3 - (i4 / 2)))});
        } else if (i3 > 0 && i3 < i4) {
            this.f193p = Color.HSVToColor(new float[]{this.f194q[0], this.f195r * i3, 1.0f});
        } else if (i3 == i4 / 2) {
            this.f193p = Color.HSVToColor(new float[]{this.f194q[0], 1.0f, 1.0f});
        } else if (i3 <= 0) {
            this.f193p = -1;
        } else if (i3 >= i4) {
            this.f193p = -16777216;
        }
    }

    public int getColor() {
        return this.f193p;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.f190m, this.f187j);
        if (this.f198u) {
            i2 = this.f186i;
            i3 = this.f185h;
        } else {
            i2 = this.f185h;
            i3 = this.f186i;
        }
        float f2 = i2;
        float f3 = i3;
        canvas.drawCircle(f2, f3, this.f185h, this.f189l);
        canvas.drawCircle(f2, f3, this.f184g, this.f188k);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.f183f + (this.f185h * 2);
        if (!this.f198u) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.f185h * 2;
        int i6 = i4 - i5;
        this.f182e = i6;
        if (!this.f198u) {
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
        if (bundle.containsKey("saturation")) {
            setSaturation(bundle.getFloat("saturation"));
        } else {
            setValue(bundle.getFloat("value"));
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.f194q);
        float[] fArr = new float[3];
        Color.colorToHSV(this.f193p, fArr);
        float f2 = fArr[1];
        float f3 = fArr[2];
        if (f2 < f3) {
            bundle.putFloat("saturation", f2);
        } else {
            bundle.putFloat("value", f3);
        }
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f198u) {
            int i8 = this.f182e;
            int i9 = this.f185h;
            i6 = i8 + i9;
            i7 = this.f181d;
            int i10 = i2 - (i9 * 2);
            this.f182e = i10;
            this.f190m.set(i9, i9 - (i7 / 2), i10 + i9, i9 + (i7 / 2));
        } else {
            i6 = this.f181d;
            int i11 = this.f182e;
            int i12 = this.f185h;
            int i13 = i3 - (i12 * 2);
            this.f182e = i13;
            this.f190m.set(i12 - (i6 / 2), i12, (i6 / 2) + i12, i13 + i12);
            i7 = i11 + i12;
        }
        if (!isInEditMode()) {
            this.f191n = new LinearGradient(this.f185h, 0.0f, i6, i7, new int[]{-1, Color.HSVToColor(this.f194q), -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.f191n = new LinearGradient(this.f185h, 0.0f, i6, i7, new int[]{-1, -8257792, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.f194q);
        }
        this.f187j.setShader(this.f191n);
        int i14 = this.f182e;
        this.f195r = 1.0f / (i14 / 2.0f);
        this.f196s = (i14 / 2.0f) / 1.0f;
        float[] fArr = new float[3];
        Color.colorToHSV(this.f193p, fArr);
        float f2 = fArr[1];
        float f3 = fArr[2];
        if (f2 < f3) {
            this.f186i = Math.round((this.f196s * f2) + this.f185h);
        } else {
            this.f186i = Math.round((this.f196s * (1.0f - f3)) + this.f185h + (this.f182e / 2));
        }
        if (isInEditMode()) {
            this.f186i = (this.f182e / 2) + this.f185h;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.f198u) {
            y2 = motionEvent.getX();
        } else {
            y2 = motionEvent.getY();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2 && this.f192o) {
                    int i2 = this.f185h;
                    if (y2 >= i2 && y2 <= this.f182e + i2) {
                        this.f186i = Math.round(y2);
                        a(Math.round(y2));
                        this.f188k.setColor(this.f193p);
                        ColorPicker colorPicker = this.f197t;
                        if (colorPicker != null) {
                            colorPicker.setNewCenterColor(this.f193p);
                            this.f197t.d(this.f193p);
                        }
                        invalidate();
                    } else if (y2 < i2) {
                        this.f186i = i2;
                        this.f193p = -1;
                        this.f188k.setColor(-1);
                        ColorPicker colorPicker2 = this.f197t;
                        if (colorPicker2 != null) {
                            colorPicker2.setNewCenterColor(this.f193p);
                            this.f197t.d(this.f193p);
                        }
                        invalidate();
                    } else {
                        int i3 = this.f182e;
                        if (y2 > i2 + i3) {
                            this.f186i = i2 + i3;
                            this.f193p = -16777216;
                            this.f188k.setColor(-16777216);
                            ColorPicker colorPicker3 = this.f197t;
                            if (colorPicker3 != null) {
                                colorPicker3.setNewCenterColor(this.f193p);
                                this.f197t.d(this.f193p);
                            }
                            invalidate();
                        }
                    }
                }
            } else {
                this.f192o = false;
            }
        } else {
            this.f192o = true;
            int i4 = this.f185h;
            if (y2 >= i4 && y2 <= i4 + this.f182e) {
                this.f186i = Math.round(y2);
                a(Math.round(y2));
                this.f188k.setColor(this.f193p);
                invalidate();
            }
        }
        return true;
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.f198u) {
            i3 = this.f182e + this.f185h;
            i4 = this.f181d;
        } else {
            i3 = this.f181d;
            i4 = this.f182e + this.f185h;
        }
        Color.colorToHSV(i2, this.f194q);
        LinearGradient linearGradient = new LinearGradient(this.f185h, 0.0f, i3, i4, new int[]{-1, i2, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        this.f191n = linearGradient;
        this.f187j.setShader(linearGradient);
        a(this.f186i);
        this.f188k.setColor(this.f193p);
        ColorPicker colorPicker = this.f197t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f193p);
            if (this.f197t.g()) {
                this.f197t.d(this.f193p);
            }
        }
        invalidate();
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.f197t = colorPicker;
    }

    public void setSaturation(float f2) {
        int round = Math.round((this.f196s * f2) + this.f185h);
        this.f186i = round;
        a(round);
        this.f188k.setColor(this.f193p);
        ColorPicker colorPicker = this.f197t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f193p);
            this.f197t.d(this.f193p);
        }
        invalidate();
    }

    public void setValue(float f2) {
        int round = Math.round((this.f196s * (1.0f - f2)) + this.f185h + (this.f182e / 2));
        this.f186i = round;
        a(round);
        this.f188k.setColor(this.f193p);
        ColorPicker colorPicker = this.f197t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f193p);
            this.f197t.d(this.f193p);
        }
        invalidate();
    }
}
