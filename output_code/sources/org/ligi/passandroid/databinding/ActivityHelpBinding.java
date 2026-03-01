package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ActivityHelpBinding {

    /* renamed from: a  reason: collision with root package name */
    private final CoordinatorLayout f2619a;

    /* renamed from: b  reason: collision with root package name */
    public final AppBarLayout f2620b;

    /* renamed from: c  reason: collision with root package name */
    public final TextView f2621c;

    /* renamed from: d  reason: collision with root package name */
    public final Toolbar f2622d;

    private ActivityHelpBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, TextView textView, Toolbar toolbar) {
        this.f2619a = coordinatorLayout;
        this.f2620b = appBarLayout;
        this.f2621c = textView;
        this.f2622d = toolbar;
    }

    public static ActivityHelpBinding a(View view) {
        int i2 = 2131296340;
        AppBarLayout a2 = ViewBindings.a(view, 2131296340);
        if (a2 != null) {
            i2 = 2131296478;
            TextView textView = (TextView) ViewBindings.a(view, 2131296478);
            if (textView != null) {
                i2 = 2131296778;
                Toolbar a3 = ViewBindings.a(view, 2131296778);
                if (a3 != null) {
                    return new ActivityHelpBinding((CoordinatorLayout) view, a2, textView, a3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static ActivityHelpBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static ActivityHelpBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492892, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public CoordinatorLayout b() {
        return this.f2619a;
    }
}
