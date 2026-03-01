package org.ligi.passandroid.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassViewHelper {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f2864a;

    /* renamed from: b  reason: collision with root package name */
    private final Lazy f2865b;

    /* renamed from: c  reason: collision with root package name */
    private final Lazy f2866c;

    public PassViewHelper(Activity context) {
        Lazy b2;
        Lazy b3;
        Intrinsics.f(context, "context");
        this.f2864a = context;
        b2 = LazyKt__LazyJVMKt.b(new PassViewHelper$fingerSize$2(this));
        this.f2865b = b2;
        b3 = LazyKt__LazyJVMKt.b(new PassViewHelper$windowWidth$2(this));
        this.f2866c = b3;
    }

    private final ViewGroup.LayoutParams c(ImageView imageView, Bitmap bitmap) {
        int i2;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Intrinsics.c(layoutParams);
        if (bitmap.getHeight() < b()) {
            i2 = b();
        } else {
            i2 = -2;
        }
        layoutParams.height = i2;
        return layoutParams;
    }

    public final int b() {
        return ((Number) this.f2865b.getValue()).intValue();
    }

    public final int d() {
        return ((Number) this.f2866c.getValue()).intValue();
    }

    public final void e(ImageView imageView, Bitmap bitmap) {
        Intrinsics.f(imageView, "imageView");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(0);
            imageView.setLayoutParams(c(imageView, bitmap));
            return;
        }
        imageView.setVisibility(8);
    }
}
