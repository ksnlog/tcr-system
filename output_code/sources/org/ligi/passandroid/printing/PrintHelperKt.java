package org.ligi.passandroid.printing;

import android.annotation.TargetApi;
import android.content.Context;
import android.print.PrintManager;
import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PrintHelperKt {
    @TargetApi(19)
    public static final void a(Context context, Pass pass) {
        Intrinsics.f(context, "context");
        Intrinsics.f(pass, "pass");
        Object h2 = ContextCompat.h(context, PrintManager.class);
        Intrinsics.c(h2);
        String str = context.getString(2131755043) + " print of " + pass.getDescription();
        ((PrintManager) h2).print(str, new PassPrintDocumentAdapter(context, pass, str), null);
    }
}
