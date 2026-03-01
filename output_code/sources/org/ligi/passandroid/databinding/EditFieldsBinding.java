package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBindings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EditFieldsBinding {

    /* renamed from: a  reason: collision with root package name */
    private final LinearLayout f2650a;

    /* renamed from: b  reason: collision with root package name */
    public final Button f2651b;

    /* renamed from: c  reason: collision with root package name */
    public final LinearLayout f2652c;

    private EditFieldsBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2) {
        this.f2650a = linearLayout;
        this.f2651b = button;
        this.f2652c = linearLayout2;
    }

    public static EditFieldsBinding a(View view) {
        int i2 = 2131296327;
        Button button = (Button) ViewBindings.a(view, 2131296327);
        if (button != null) {
            i2 = 2131296449;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.a(view, 2131296449);
            if (linearLayout != null) {
                return new EditFieldsBinding((LinearLayout) view, button, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static EditFieldsBinding c(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492923, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public LinearLayout b() {
        return this.f2650a;
    }
}
