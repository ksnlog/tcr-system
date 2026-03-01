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
public class ValueBar extends View {

    /* renamed from: d  reason: collision with root package name */
    private int f217d;

    /* renamed from: e  reason: collision with root package name */
    private int f218e;

    /* renamed from: f  reason: collision with root package name */
    private int f219f;

    /* renamed from: g  reason: collision with root package name */
    private int f220g;

    /* renamed from: h  reason: collision with root package name */
    private int f221h;

    /* renamed from: i  reason: collision with root package name */
    private int f222i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f223j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f224k;

    /* renamed from: l  reason: collision with root package name */
    private Paint f225l;

    /* renamed from: m  reason: collision with root package name */
    private RectF f226m;

    /* renamed from: n  reason: collision with root package name */
    private Shader f227n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f228o;

    /* renamed from: p  reason: collision with root package name */
    private int f229p;

    /* renamed from: q  reason: collision with root package name */
    private float[] f230q;

    /* renamed from: r  reason: collision with root package name */
    private float f231r;

    /* renamed from: s  reason: collision with root package name */
    private float f232s;

    /* renamed from: t  reason: collision with root package name */
    private ColorPicker f233t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f234u;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnValueChangedListener {
    }

    private void a(int i2) {
        int i3 = i2 - this.f221h;
        if (i3 < 0) {
            i3 = 0;
        } else {
            int i4 = this.f218e;
            if (i3 > i4) {
                i3 = i4;
            }
        }
        float[] fArr = this.f230q;
        this.f229p = Color.HSVToColor(new float[]{fArr[0], fArr[1], 1.0f - (this.f231r * i3)});
    }

    public int getColor() {
        return this.f229p;
    }

    public OnValueChangedListener getOnValueChangedListener() {
        return null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int i3;
        canvas.drawRect(this.f226m, this.f223j);
        if (this.f234u) {
            i2 = this.f222i;
            i3 = this.f221h;
        } else {
            i2 = this.f221h;
            i3 = this.f222i;
        }
        float f2 = i2;
        float f3 = i3;
        canvas.drawCircle(f2, f3, this.f221h, this.f225l);
        canvas.drawCircle(f2, f3, this.f220g, this.f224k);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = this.f219f + (this.f221h * 2);
        if (!this.f234u) {
            i2 = i3;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            i4 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i4 = Math.min(i4, size);
        }
        int i5 = this.f221h * 2;
        int i6 = i4 - i5;
        this.f218e = i6;
        if (!this.f234u) {
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
        setValue(bundle.getFloat("value"));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", onSaveInstanceState);
        bundle.putFloatArray("color", this.f230q);
        float[] fArr = new float[3];
        Color.colorToHSV(this.f229p, fArr);
        bundle.putFloat("value", fArr[2]);
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f234u) {
            int i8 = this.f218e;
            int i9 = this.f221h;
            i6 = i8 + i9;
            i7 = this.f217d;
            int i10 = i2 - (i9 * 2);
            this.f218e = i10;
            this.f226m.set(i9, i9 - (i7 / 2), i10 + i9, i9 + (i7 / 2));
        } else {
            i6 = this.f217d;
            int i11 = this.f218e;
            int i12 = this.f221h;
            int i13 = i3 - (i12 * 2);
            this.f218e = i13;
            this.f226m.set(i12 - (i6 / 2), i12, (i6 / 2) + i12, i13 + i12);
            i7 = i11 + i12;
        }
        if (!isInEditMode()) {
            this.f227n = new LinearGradient(this.f221h, 0.0f, i6, i7, new int[]{Color.HSVToColor(255, this.f230q), -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.f227n = new LinearGradient(this.f221h, 0.0f, i6, i7, new int[]{-8257792, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
            Color.colorToHSV(-8257792, this.f230q);
        }
        this.f223j.setShader(this.f227n);
        int i14 = this.f218e;
        this.f231r = 1.0f / i14;
        this.f232s = i14 / 1.0f;
        float[] fArr = new float[3];
        Color.colorToHSV(this.f229p, fArr);
        if (!isInEditMode()) {
            this.f222i = Math.round((this.f218e - (this.f232s * fArr[2])) + this.f221h);
        } else {
            this.f222i = this.f221h;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y2;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.f234u) {
            y2 = motionEvent.getX();
        } else {
            y2 = motionEvent.getY();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2 && this.f228o) {
                    int i2 = this.f221h;
                    if (y2 >= i2 && y2 <= this.f218e + i2) {
                        this.f222i = Math.round(y2);
                        a(Math.round(y2));
                        this.f224k.setColor(this.f229p);
                        ColorPicker colorPicker = this.f233t;
                        if (colorPicker != null) {
                            colorPicker.setNewCenterColor(this.f229p);
                            this.f233t.d(this.f229p);
                        }
                        invalidate();
                    } else if (y2 < i2) {
                        this.f222i = i2;
                        int HSVToColor = Color.HSVToColor(this.f230q);
                        this.f229p = HSVToColor;
                        this.f224k.setColor(HSVToColor);
                        ColorPicker colorPicker2 = this.f233t;
                        if (colorPicker2 != null) {
                            colorPicker2.setNewCenterColor(this.f229p);
                            this.f233t.d(this.f229p);
                        }
                        invalidate();
                    } else {
                        int i3 = this.f218e;
                        if (y2 > i2 + i3) {
                            this.f222i = i2 + i3;
                            this.f229p = -16777216;
                            this.f224k.setColor(-16777216);
                            ColorPicker colorPicker3 = this.f233t;
                            if (colorPicker3 != null) {
                                colorPicker3.setNewCenterColor(this.f229p);
                                this.f233t.d(this.f229p);
                            }
                            invalidate();
                        }
                    }
                }
            } else {
                this.f228o = false;
            }
        } else {
            this.f228o = true;
            int i4 = this.f221h;
            if (y2 >= i4 && y2 <= i4 + this.f218e) {
                this.f222i = Math.round(y2);
                a(Math.round(y2));
                this.f224k.setColor(this.f229p);
                invalidate();
            }
        }
        return true;
    }

    public void setColor(int i2) {
        int i3;
        int i4;
        if (this.f234u) {
            i3 = this.f218e + this.f221h;
            i4 = this.f217d;
        } else {
            i3 = this.f217d;
            i4 = this.f218e + this.f221h;
        }
        Color.colorToHSV(i2, this.f230q);
        LinearGradient linearGradient = new LinearGradient(this.f221h, 0.0f, i3, i4, new int[]{i2, -16777216}, (float[]) null, Shader.TileMode.CLAMP);
        this.f227n = linearGradient;
        this.f223j.setShader(linearGradient);
        a(this.f222i);
        this.f224k.setColor(this.f229p);
        ColorPicker colorPicker = this.f233t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f229p);
            if (this.f233t.g()) {
                this.f233t.d(this.f229p);
            }
        }
        invalidate();
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.f233t = colorPicker;
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
    }

    public void setValue(float f2) {
        int round = Math.round((this.f218e - (this.f232s * f2)) + this.f221h);
        this.f222i = round;
        a(round);
        this.f224k.setColor(this.f229p);
        ColorPicker colorPicker = this.f233t;
        if (colorPicker != null) {
            colorPicker.setNewCenterColor(this.f229p);
            this.f233t.d(this.f229p);
        }
        invalidate();
    }
}
