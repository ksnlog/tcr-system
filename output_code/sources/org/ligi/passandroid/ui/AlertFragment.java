package org.ligi.passandroid.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.ui.AlertFragment;
import org.ligi.passandroid.ui.quirk_fix.OpenIphoneWebView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AlertFragment extends Fragment {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void U1(Tracker tracker, AlertFragment this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(tracker, "$tracker");
        Intrinsics.f(this$0, "this$0");
        tracker.c(this$0.s1().getLocalClassName() + "with invalid activity", false);
        Intent intent = new Intent((Context) this$0.s1(), (Class<?>) OpenIphoneWebView.class);
        intent.setData(this$0.s1().getIntent().getData());
        this$0.M1(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void V1(AlertFragment this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", "PassAndroid: URLRewrite Problem");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"ligi@ligi.de"});
        if (this$0.s1().getIntent().getData() != null) {
            Uri data = this$0.s1().getIntent().getData();
            Intrinsics.c(data);
            intent.putExtra("android.intent.extra.TEXT", data.toString());
        } else {
            intent.putExtra("android.intent.extra.TEXT", "null");
        }
        intent.setType("text/plain");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void W1(AlertFragment this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this$0, "this$0");
        this$0.s1().finish();
    }

    public void r0(Bundle bundle) {
        super.r0(bundle);
        PassAndroidActivity s1 = s1();
        Intrinsics.d(s1, "null cannot be cast to non-null type org.ligi.passandroid.ui.PassAndroidActivity");
        final Tracker p0 = s1.p0();
        AlertDialog.Builder builder = new AlertDialog.Builder(m());
        builder.setTitle("Workaround failed");
        builder.setMessage("The URL PassAndroid tried to work around failed :-( some companies just send PassBooks to Apple Devices - this was an attempt to workaround this.Unfortunately it failed - perhaps there where changes on the serverside - you can open the site with your browser now - to see it in PassAndroid in future again it would help if you can send me the pass");
        builder.setPositiveButton("Browser", new DialogInterface.OnClickListener() { // from class: e0.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AlertFragment.U1(Tracker.this, this, dialogInterface, i2);
            }
        });
        builder.setNeutralButton("send", new DialogInterface.OnClickListener() { // from class: e0.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AlertFragment.V1(AlertFragment.this, dialogInterface, i2);
            }
        });
        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() { // from class: e0.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AlertFragment.W1(AlertFragment.this, dialogInterface, i2);
            }
        });
        builder.create().show();
    }
}
