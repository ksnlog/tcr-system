package org.ligi.kaxt;

import android.app.Activity;
import android.content.res.Resources;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ActivityExtensionsKt {
    public static final void a(Activity activity) {
        Resources resources = activity.getResources();
        Intrinsics.b(resources, "this.resources");
        d(activity, resources.getConfiguration().orientation);
    }

    public static final View b(Activity activity, int i2, ViewGroup viewGroup) {
        return LayoutInflater.from(activity).inflate(i2, viewGroup);
    }

    public static /* synthetic */ View c(Activity activity, int i2, ViewGroup viewGroup, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            viewGroup = null;
        }
        return b(activity, i2, viewGroup);
    }

    public static final void d(Activity activity, int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                WindowManager windowManager = activity.getWindowManager();
                Intrinsics.b(windowManager, "this.windowManager");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Intrinsics.b(defaultDisplay, "this.windowManager.defaultDisplay");
                int rotation = defaultDisplay.getRotation();
                if (rotation != 0 && rotation != 1) {
                    activity.setRequestedOrientation(8);
                    return;
                } else {
                    activity.setRequestedOrientation(0);
                    return;
                }
            }
            return;
        }
        WindowManager windowManager2 = activity.getWindowManager();
        Intrinsics.b(windowManager2, "this.windowManager");
        Display defaultDisplay2 = windowManager2.getDefaultDisplay();
        Intrinsics.b(defaultDisplay2, "this.windowManager.defaultDisplay");
        int rotation2 = defaultDisplay2.getRotation();
        if (rotation2 != 1 && rotation2 != 2) {
            activity.setRequestedOrientation(1);
        } else {
            activity.setRequestedOrientation(9);
        }
    }
}
