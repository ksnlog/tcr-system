package org.ligi.passandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.ligi.passandroid.ui.PassImportActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class InstallListener extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (stringExtra != null) {
            Intent intent2 = new Intent(context, PassImportActivity.class);
            intent2.setFlags(268435456);
            intent2.setData(Uri.parse(stringExtra));
            context.startActivity(intent2);
        }
    }
}
