package org.ligi.passandroid.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.functions.CategoryHelperKt;
import org.ligi.passandroid.model.pass.PassType;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class BaseCategoryIndicatorView extends LinearLayout {

    /* renamed from: d  reason: collision with root package name */
    private final int f2958d;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseCategoryIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 4, null);
        Intrinsics.f(context, "context");
        Intrinsics.f(attrs, "attrs");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseCategoryIndicatorView(Context context, AttributeSet attrs, int i2) {
        super(context, attrs);
        Intrinsics.f(context, "context");
        Intrinsics.f(attrs, "attrs");
        this.f2958d = i2;
    }

    public final int getLayoutRes() {
        return this.f2958d;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(this.f2958d, (ViewGroup) this, true);
    }

    public final void setAccentColor(int i2) {
        setBackgroundColor(i2);
    }

    public final void setImageByCategory(PassType passType) {
        ImageView imageView = (ImageView) findViewById(2131296780);
        if (passType == null) {
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        imageView.setImageResource(CategoryHelperKt.b(passType));
    }

    public /* synthetic */ BaseCategoryIndicatorView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i3 & 4) != 0 ? 2131492901 : i2);
    }
}
