package org.ligi.tracedroid.sending;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.tracedroid.TraceDroid;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TraceDroidEmailSenderKt {
    public static final boolean a(final String email, final Context context) {
        boolean z2;
        Intrinsics.f(email, "email");
        Intrinsics.f(context, "context");
        if (TraceDroid.f2981c.d().length == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!(!z2)) {
            return false;
        }
        new AlertDialog.Builder(context).w("Problem Report").k("A Problem with this App was detected - do you want to send a Crash-Report to help fix this Problem?").s("Send", new DialogInterface.OnClickListener() { // from class: org.ligi.tracedroid.sending.TraceDroidEmailSenderKt$sendTraceDroidStackTracesIfExist$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{email});
                StringBuilder sb = new StringBuilder();
                sb.append("[TraceDroid Report] ");
                TraceDroid traceDroid = TraceDroid.f2981c;
                sb.append(traceDroid.f().b());
                intent.putExtra("android.intent.extra.SUBJECT", sb.toString());
                intent.putExtra("android.intent.extra.TEXT", traceDroid.e(10));
                context.startActivity(Intent.createChooser(intent, "Send mail..."));
                traceDroid.a();
            }
        }).n("No", TraceDroidEmailSenderKt$sendTraceDroidStackTracesIfExist$2.f2999d).o("Later", TraceDroidEmailSenderKt$sendTraceDroidStackTracesIfExist$3.f3000d).z();
        return true;
    }
}
