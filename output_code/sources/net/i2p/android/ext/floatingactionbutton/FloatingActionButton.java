package net.i2p.android.ext.floatingactionbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.TextView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FloatingActionButton extends ImageButton {

    /* renamed from: d  reason: collision with root package name */
    int f1254d;

    /* renamed from: e  reason: collision with root package name */
    int f1255e;

    /* renamed from: f  reason: collision with root package name */
    int f1256f;

    /* renamed from: g  reason: collision with root package name */
    String f1257g;

    /* renamed from: h  reason: collision with root package name */
    private int f1258h;

    /* renamed from: i  reason: collision with root package name */
    private Drawable f1259i;

    /* renamed from: j  reason: collision with root package name */
    private int f1260j;

    /* renamed from: k  reason: collision with root package name */
    private float f1261k;

    /* renamed from: l  reason: collision with root package name */
    private float f1262l;

    /* renamed from: m  reason: collision with root package name */
    private float f1263m;

    /* renamed from: n  reason: collision with root package name */
    private int f1264n;

    /* renamed from: o  reason: collision with root package name */
    boolean f1265o;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class TranslucentLayerDrawable extends LayerDrawable {

        /* renamed from: d  reason: collision with root package name */
        private final int f1272d;

        public TranslucentLayerDrawable(int i2, Drawable... drawableArr) {
            super(drawableArr);
            this.f1272d = i2;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, this.f1272d, 31);
            super.draw(canvas);
            canvas.restore();
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j(context, attributeSet);
    }

    private int a(int i2, float f2) {
        Color.colorToHSV(i2, r0);
        float[] fArr = {0.0f, 0.0f, Math.min(fArr[2] * f2, 1.0f)};
        return Color.HSVToColor(Color.alpha(i2), fArr);
    }

    private Drawable b(int i2, float f2) {
        LayerDrawable layerDrawable;
        int alpha = Color.alpha(i2);
        int m2 = m(i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setColor(m2);
        Drawable[] drawableArr = {shapeDrawable, d(m2, f2)};
        if (alpha != 255 && this.f1265o) {
            layerDrawable = new TranslucentLayerDrawable(alpha, drawableArr);
        } else {
            layerDrawable = new LayerDrawable(drawableArr);
        }
        int i3 = (int) (f2 / 2.0f);
        layerDrawable.setLayerInset(1, i3, i3, i3, i3);
        return layerDrawable;
    }

    private StateListDrawable c(float f2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842910}, b(this.f1256f, f2));
        stateListDrawable.addState(new int[]{16842919}, b(this.f1255e, f2));
        stateListDrawable.addState(new int[0], b(this.f1254d, f2));
        return stateListDrawable;
    }

    private Drawable d(final int i2, float f2) {
        if (!this.f1265o) {
            return new ColorDrawable(0);
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        final int f3 = f(i2);
        final int i3 = i(f3);
        final int k2 = k(i2);
        final int i4 = i(k2);
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(f2);
        paint.setStyle(Paint.Style.STROKE);
        shapeDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() { // from class: net.i2p.android.ext.floatingactionbutton.FloatingActionButton.1
            @Override // android.graphics.drawable.ShapeDrawable.ShaderFactory
            public Shader resize(int i5, int i6) {
                float f4 = i5 / 2;
                return new LinearGradient(f4, 0.0f, f4, i6, new int[]{k2, i4, i2, i3, f3}, new float[]{0.0f, 0.2f, 0.5f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
            }
        });
        return shapeDrawable;
    }

    private Drawable e(float f2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(f2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(-16777216);
        paint.setAlpha(l(0.02f));
        return shapeDrawable;
    }

    private int f(int i2) {
        return a(i2, 0.9f);
    }

    private int i(int i2) {
        return Color.argb(Color.alpha(i2) / 2, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    private int k(int i2) {
        return a(i2, 1.1f);
    }

    private int l(float f2) {
        return (int) (f2 * 255.0f);
    }

    private int m(int i2) {
        return Color.rgb(Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    private void o() {
        this.f1261k = h(this.f1260j == 0 ? R$dimen.f1319i : R$dimen.f1318h);
    }

    private void p() {
        this.f1264n = (int) (this.f1261k + (this.f1262l * 2.0f));
    }

    @SuppressLint({"NewApi"})
    private void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(int i2) {
        return getResources().getColor(i2);
    }

    public int getColorDisabled() {
        return this.f1256f;
    }

    public int getColorNormal() {
        return this.f1254d;
    }

    public int getColorPressed() {
        return this.f1255e;
    }

    Drawable getIconDrawable() {
        Drawable drawable = this.f1259i;
        if (drawable != null) {
            return drawable;
        }
        if (this.f1258h != 0) {
            return getResources().getDrawable(this.f1258h);
        }
        return new ColorDrawable(0);
    }

    TextView getLabelView() {
        return (TextView) getTag(R$id.f1324b);
    }

    public int getSize() {
        return this.f1260j;
    }

    public String getTitle() {
        return this.f1257g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float h(int i2) {
        return getResources().getDimension(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.f1327c, 0, 0);
        this.f1254d = obtainStyledAttributes.getColor(R$styleable.f1329e, g(R$color.f1309b));
        this.f1255e = obtainStyledAttributes.getColor(R$styleable.f1330f, g(R$color.f1310c));
        this.f1256f = obtainStyledAttributes.getColor(R$styleable.f1328d, g(R$color.f1308a));
        this.f1260j = obtainStyledAttributes.getInt(R$styleable.f1332h, 0);
        this.f1258h = obtainStyledAttributes.getResourceId(R$styleable.f1331g, 0);
        this.f1257g = obtainStyledAttributes.getString(R$styleable.f1334j);
        this.f1265o = obtainStyledAttributes.getBoolean(R$styleable.f1333i, true);
        obtainStyledAttributes.recycle();
        o();
        this.f1262l = h(R$dimen.f1317g);
        this.f1263m = h(R$dimen.f1316f);
        p();
        n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        int i2;
        float h2 = h(R$dimen.f1320j);
        float f2 = h2 / 2.0f;
        Drawable[] drawableArr = new Drawable[4];
        Resources resources = getResources();
        if (this.f1260j == 0) {
            i2 = R$drawable.f1322b;
        } else {
            i2 = R$drawable.f1321a;
        }
        drawableArr[0] = resources.getDrawable(i2);
        drawableArr[1] = c(h2);
        drawableArr[2] = e(h2);
        drawableArr[3] = getIconDrawable();
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        int h3 = ((int) (this.f1261k - h(R$dimen.f1312b))) / 2;
        float f3 = this.f1262l;
        int i3 = (int) f3;
        float f4 = this.f1263m;
        int i4 = (int) (f3 - f4);
        int i5 = (int) (f3 + f4);
        layerDrawable.setLayerInset(1, i3, i4, i3, i5);
        int i6 = (int) (i3 - f2);
        layerDrawable.setLayerInset(2, i6, (int) (i4 - f2), i6, (int) (i5 - f2));
        int i7 = i3 + h3;
        layerDrawable.setLayerInset(3, i7, i4 + h3, i7, i5 + h3);
        setBackgroundCompat(layerDrawable);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int i4 = this.f1264n;
        setMeasuredDimension(i4, i4);
    }

    public void setColorDisabled(int i2) {
        if (this.f1256f != i2) {
            this.f1256f = i2;
            n();
        }
    }

    public void setColorDisabledResId(int i2) {
        setColorDisabled(g(i2));
    }

    public void setColorNormal(int i2) {
        if (this.f1254d != i2) {
            this.f1254d = i2;
            n();
        }
    }

    public void setColorNormalResId(int i2) {
        setColorNormal(g(i2));
    }

    public void setColorPressed(int i2) {
        if (this.f1255e != i2) {
            this.f1255e = i2;
            n();
        }
    }

    public void setColorPressedResId(int i2) {
        setColorPressed(g(i2));
    }

    public void setIcon(int i2) {
        if (this.f1258h != i2) {
            this.f1258h = i2;
            this.f1259i = null;
            n();
        }
    }

    public void setIconDrawable(Drawable drawable) {
        if (this.f1259i != drawable) {
            this.f1258h = 0;
            this.f1259i = drawable;
            n();
        }
    }

    public void setSize(int i2) {
        if (i2 != 1 && i2 != 0) {
            throw new IllegalArgumentException("Use @FAB_SIZE constants only!");
        }
        if (this.f1260j != i2) {
            this.f1260j = i2;
            o();
            p();
            n();
        }
    }

    public void setStrokeVisible(boolean z2) {
        if (this.f1265o != z2) {
            this.f1265o = z2;
            n();
        }
    }

    public void setTitle(String str) {
        this.f1257g = str;
        TextView labelView = getLabelView();
        if (labelView != null) {
            labelView.setText(str);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        TextView labelView = getLabelView();
        if (labelView != null) {
            labelView.setVisibility(i2);
        }
        super.setVisibility(i2);
    }
}
