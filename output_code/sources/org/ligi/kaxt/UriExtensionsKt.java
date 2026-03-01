package org.ligi.kaxt;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import org.ligi.kaxt.converter.ImageFromIntentUriToFileConverter;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UriExtensionsKt {
    public static final File a(Uri uri, Context context) {
        String a2 = new ImageFromIntentUriToFileConverter(context).a(uri);
        if (a2 != null) {
            return new File(a2);
        }
        return null;
    }
}
