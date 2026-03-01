package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBindings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ActivityImportBinding {

    /* renamed from: a  reason: collision with root package name */
    private final FrameLayout f2623a;

    /* renamed from: b  reason: collision with root package name */
    public final ProgressBar f2624b;

    /* renamed from: c  reason: collision with root package name */
    public final LinearLayoutCompat f2625c;

    private ActivityImportBinding(FrameLayout frameLayout, ProgressBar progressBar, LinearLayoutCompat linearLayoutCompat) {
        this.f2623a = frameLayout;
        this.f2624b = progressBar;
        this.f2625c = linearLayoutCompat;
    }

    public static ActivityImportBinding a(View view) {
        int i2 = 2131296642;
        ProgressBar progressBar = (ProgressBar) ViewBindings.a(view, 2131296642);
        if (progressBar != null) {
            i2 = 2131296644;
            LinearLayoutCompat a2 = ViewBindings.a(view, 2131296644);
            if (a2 != null) {
                return new ActivityImportBinding((FrameLayout) view, progressBar, a2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static ActivityImportBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static ActivityImportBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492893, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public FrameLayout b() {
        return this.f2623a;
    }
}
