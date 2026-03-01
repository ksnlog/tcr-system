package org.ligi.kaxt;

import android.app.Dialog;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DialogExtensionsKt {
    public static final void a(Dialog dialog) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
