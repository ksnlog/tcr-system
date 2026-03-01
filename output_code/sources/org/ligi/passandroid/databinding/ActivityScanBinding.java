package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBindings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ActivityScanBinding {

    /* renamed from: a  reason: collision with root package name */
    private final FrameLayout f2626a;

    /* renamed from: b  reason: collision with root package name */
    public final ProgressBar f2627b;

    /* renamed from: c  reason: collision with root package name */
    public final LinearLayoutCompat f2628c;

    /* renamed from: d  reason: collision with root package name */
    public final TextView f2629d;

    private ActivityScanBinding(FrameLayout frameLayout, ProgressBar progressBar, LinearLayoutCompat linearLayoutCompat, TextView textView) {
        this.f2626a = frameLayout;
        this.f2627b = progressBar;
        this.f2628c = linearLayoutCompat;
        this.f2629d = textView;
    }

    public static ActivityScanBinding a(View view) {
        int i2 = 2131296642;
        ProgressBar progressBar = (ProgressBar) ViewBindings.a(view, 2131296642);
        if (progressBar != null) {
            i2 = 2131296644;
            LinearLayoutCompat a2 = ViewBindings.a(view, 2131296644);
            if (a2 != null) {
                i2 = 2131296646;
                TextView textView = (TextView) ViewBindings.a(view, 2131296646);
                if (textView != null) {
                    return new ActivityScanBinding((FrameLayout) view, progressBar, a2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static ActivityScanBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static ActivityScanBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492897, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public FrameLayout b() {
        return this.f2626a;
    }
}
