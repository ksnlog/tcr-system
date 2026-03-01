package org.ligi.passandroid.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.core.widget.TextViewCompat;
import kotlin.jvm.internal.Intrinsics;
@SuppressLint({"RestrictedApi"})
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TimeAndNavBar extends FrameLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeAndNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.f(context, "context");
        Intrinsics.f(attrs, "attrs");
        LayoutInflater.from(context).inflate(2131493022, this);
        AppCompatDrawableManager b2 = AppCompatDrawableManager.b();
        TextViewCompat.m((TextView) findViewById(2131296773), (Drawable) null, (Drawable) null, b2.c(context, 2131230872), (Drawable) null);
        TextViewCompat.m((TextView) findViewById(2131296514), b2.c(context, 2131230901), (Drawable) null, (Drawable) null, (Drawable) null);
    }
}
