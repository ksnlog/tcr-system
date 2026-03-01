package com.nineoldandroids.view;

import android.view.View;
import com.nineoldandroids.view.animation.AnimatorProxy;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ViewHelper {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static final class Honeycomb {
        static void a(View view, float f2) {
            view.setAlpha(f2);
        }

        static void b(View view, float f2) {
            view.setTranslationX(f2);
        }

        static void c(View view, float f2) {
            view.setTranslationY(f2);
        }
    }

    public static void a(View view, float f2) {
        if (AnimatorProxy.f379t) {
            AnimatorProxy.F(view).s(f2);
        } else {
            Honeycomb.a(view, f2);
        }
    }

    public static void b(View view, float f2) {
        if (AnimatorProxy.f379t) {
            AnimatorProxy.F(view).A(f2);
        } else {
            Honeycomb.b(view, f2);
        }
    }

    public static void c(View view, float f2) {
        if (AnimatorProxy.f379t) {
            AnimatorProxy.F(view).B(f2);
        } else {
            Honeycomb.c(view, f2);
        }
    }
}
