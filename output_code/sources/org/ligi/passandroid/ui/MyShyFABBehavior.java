package org.ligi.passandroid.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu;
import net.i2p.android.ext.floatingactionbutton.R$dimen;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class MyShyFABBehavior extends CoordinatorLayout.Behavior<FloatingActionsMenu> {
    public MyShyFABBehavior() {
    }

    private int E(Context context) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{2130968579});
        int dimension = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        return dimension;
    }

    private void I(FloatingActionsMenu floatingActionsMenu, View view) {
        floatingActionsMenu.setTranslationY(Math.min(0.0f, view.getTranslationY() - view.getHeight()));
    }

    /* renamed from: F */
    public boolean e(CoordinatorLayout coordinatorLayout, FloatingActionsMenu floatingActionsMenu, View view) {
        return (view instanceof Snackbar.SnackbarLayout) || (view instanceof AppBarLayout);
    }

    /* renamed from: G */
    public boolean h(CoordinatorLayout coordinatorLayout, FloatingActionsMenu floatingActionsMenu, View view) {
        int dimension;
        if (view instanceof Snackbar.SnackbarLayout) {
            I(floatingActionsMenu, view);
        }
        if (view instanceof AppBarLayout) {
            int i2 = ((ViewGroup.MarginLayoutParams) floatingActionsMenu.getLayoutParams()).bottomMargin;
            if (floatingActionsMenu.u()) {
                dimension = floatingActionsMenu.getHeight() + i2;
            } else {
                dimension = (int) (floatingActionsMenu.getContext().getResources().getDimension(R$dimen.f1319i) + (i2 * 2));
            }
            floatingActionsMenu.setTranslationY((-dimension) * (view.getY() / E(view.getContext())));
            return false;
        }
        return false;
    }

    /* renamed from: H */
    public void i(CoordinatorLayout coordinatorLayout, FloatingActionsMenu floatingActionsMenu, View view) {
        super.i(coordinatorLayout, floatingActionsMenu, view);
        h(coordinatorLayout, floatingActionsMenu, view);
    }

    public MyShyFABBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
