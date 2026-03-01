package org.ligi.kaxt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ContextExtensionsKt {
    public static final void a(Context context, Class<? extends Activity> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static final void b(Context context, Uri uri) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, "No Browser found", 1).show();
        }
    }

    public static final void c(Context context, String str) {
        Uri parse = Uri.parse(str);
        Intrinsics.b(parse, "Uri.parse(url)");
        b(context, parse);
    }
}
