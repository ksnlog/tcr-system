package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBindings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FullscreenImageBinding {

    /* renamed from: a  reason: collision with root package name */
    private final LinearLayout f2653a;

    /* renamed from: b  reason: collision with root package name */
    public final TextView f2654b;

    /* renamed from: c  reason: collision with root package name */
    public final ImageView f2655c;

    private FullscreenImageBinding(LinearLayout linearLayout, TextView textView, ImageView imageView) {
        this.f2653a = linearLayout;
        this.f2654b = textView;
        this.f2655c = imageView;
    }

    public static FullscreenImageBinding a(View view) {
        int i2 = 2131296334;
        TextView textView = (TextView) ViewBindings.a(view, 2131296334);
        if (textView != null) {
            i2 = 2131296467;
            ImageView imageView = (ImageView) ViewBindings.a(view, 2131296467);
            if (imageView != null) {
                return new FullscreenImageBinding((LinearLayout) view, textView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static FullscreenImageBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static FullscreenImageBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492925, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public LinearLayout b() {
        return this.f2653a;
    }
}
