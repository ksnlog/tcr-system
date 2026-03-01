package org.ligi.compat;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import androidx.core.text.c;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class HtmlCompat {
    public static Spanned a(String str, Html.ImageGetter imageGetter, Html.TagHandler tagHandler) {
        if (Build.VERSION.SDK_INT >= 24) {
            return c.a(str, 0, imageGetter, tagHandler);
        }
        return Html.fromHtml(str, imageGetter, tagHandler);
    }
}
