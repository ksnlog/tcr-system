package org.ligi.kaxt;

import android.graphics.Point;
import android.view.WindowManager;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class WindowManagerExtensionsKt {
    public static final Point a(WindowManager windowManager) {
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    public static final int b(WindowManager windowManager) {
        Point a2 = a(windowManager);
        return Math.min(a2.x, a2.y);
    }
}
