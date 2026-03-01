package net.i2p.android.ext.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AddFloatingActionButton extends FloatingActionButton {

    /* renamed from: p  reason: collision with root package name */
    int f1248p;

    public AddFloatingActionButton(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // net.i2p.android.ext.floatingactionbutton.FloatingActionButton
    public Drawable getIconDrawable() {
        final float h2 = h(R$dimen.f1312b);
        final float f2 = h2 / 2.0f;
        float h3 = h(R$dimen.f1314d);
        final float h4 = h(R$dimen.f1315e) / 2.0f;
        final float f3 = (h2 - h3) / 2.0f;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new Shape() { // from class: net.i2p.android.ext.floatingactionbutton.AddFloatingActionButton.1
            @Override // android.graphics.drawable.shapes.Shape
            public void draw(Canvas canvas, Paint paint) {
                float f4 = f3;
                float f5 = f2;
                float f6 = h4;
                canvas.drawRect(f4, f5 - f6, h2 - f4, f5 + f6, paint);
                float f7 = f2;
                float f8 = h4;
                float f9 = f3;
                canvas.drawRect(f7 - f8, f9, f7 + f8, h2 - f9, paint);
            }
        });
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(this.f1248p);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return shapeDrawable;
    }

    public int getPlusColor() {
        return this.f1248p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // net.i2p.android.ext.floatingactionbutton.FloatingActionButton
    public void j(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.f1325a, 0, 0);
        this.f1248p = obtainStyledAttributes.getColor(R$styleable.f1326b, g(17170443));
        obtainStyledAttributes.recycle();
        super.j(context, attributeSet);
    }

    @Override // net.i2p.android.ext.floatingactionbutton.FloatingActionButton
    public void setIcon(int i2) {
        throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
    }

    public void setPlusColor(int i2) {
        if (this.f1248p != i2) {
            this.f1248p = i2;
            n();
        }
    }

    public void setPlusColorResId(int i2) {
        setPlusColor(g(i2));
    }

    public AddFloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
