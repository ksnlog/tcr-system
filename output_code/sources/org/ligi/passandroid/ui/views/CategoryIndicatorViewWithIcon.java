package org.ligi.passandroid.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CategoryIndicatorViewWithIcon extends BaseCategoryIndicatorView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategoryIndicatorViewWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs, 2131492900);
        Intrinsics.f(context, "context");
        Intrinsics.f(attrs, "attrs");
    }

    public final void setIcon(Bitmap iconBitmap) {
        Intrinsics.f(iconBitmap, "iconBitmap");
        ((ImageView) findViewById(2131296484)).setImageBitmap(iconBitmap);
    }
}
