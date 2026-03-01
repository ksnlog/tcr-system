package org.ligi.passandroid.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassLocation;
import org.ligi.passandroid.ui.NavigateToLocationsDialogKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NavigateToLocationsDialogKt {
    private static final void b(Activity activity, boolean z2) {
        if (z2) {
            activity.finish();
        }
    }

    private static final String c(PassLocation passLocation, Pass pass) {
        try {
            return URLEncoder.encode(passLocation.getNameWithFallback(pass), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static final void d(final Activity activity, final Pass pass, final boolean z2) {
        Object v2;
        Intrinsics.f(activity, "<this>");
        Intrinsics.f(pass, "pass");
        final List<PassLocation> locations = pass.getLocations();
        if (locations.isEmpty()) {
            b(activity, z2);
        } else if (locations.size() == 1) {
            v2 = CollectionsKt___CollectionsKt.v(locations);
            f(activity, (PassLocation) v2, pass);
            b(activity, z2);
        } else if (locations.size() > 1) {
            String[] strArr = new String[locations.size()];
            int i2 = 0;
            for (PassLocation passLocation : locations) {
                strArr[i2] = passLocation.getNameWithFallback(pass);
                i2++;
            }
            new AlertDialog.Builder(activity).w(activity.getString(2131755085)).i(strArr, new DialogInterface.OnClickListener() { // from class: e0.j
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    NavigateToLocationsDialogKt.e(activity, locations, pass, z2, dialogInterface, i3);
                }
            }).z();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(Activity this_showNavigateToLocationsDialog, List locations, Pass pass, boolean z2, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this_showNavigateToLocationsDialog, "$this_showNavigateToLocationsDialog");
        Intrinsics.f(locations, "$locations");
        Intrinsics.f(pass, "$pass");
        f(this_showNavigateToLocationsDialog, (PassLocation) locations.get(i2), pass);
        b(this_showNavigateToLocationsDialog, z2);
    }

    private static final void f(Activity activity, PassLocation passLocation, Pass pass) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String c2 = c(passLocation, pass);
        String commaSeparated = passLocation.getCommaSeparated();
        Uri parse = Uri.parse("geo:" + commaSeparated + "?q=" + commaSeparated + '(' + c2 + ')');
        Intrinsics.e(parse, "parse(this)");
        intent.setData(parse);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Uri parse2 = Uri.parse("http://maps.google.com/?q=" + c2 + '@' + commaSeparated);
            Intrinsics.e(parse2, "parse(this)");
            intent.setData(parse2);
            activity.startActivity(intent);
        }
    }
}
