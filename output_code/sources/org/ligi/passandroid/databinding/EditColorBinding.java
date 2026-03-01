package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBindings;
import com.larswerkman.holocolorpicker.ColorPicker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EditColorBinding {

    /* renamed from: a  reason: collision with root package name */
    private final LinearLayout f2648a;

    /* renamed from: b  reason: collision with root package name */
    public final ColorPicker f2649b;

    private EditColorBinding(LinearLayout linearLayout, ColorPicker colorPicker) {
        this.f2648a = linearLayout;
        this.f2649b = colorPicker;
    }

    public static EditColorBinding a(View view) {
        ColorPicker colorPicker = (ColorPicker) ViewBindings.a(view, 2131296380);
        if (colorPicker != null) {
            return new EditColorBinding((LinearLayout) view, colorPicker);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(2131296380)));
    }

    public static EditColorBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static EditColorBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492921, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public LinearLayout b() {
        return this.f2648a;
    }
}
