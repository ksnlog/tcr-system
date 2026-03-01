package org.ligi.tracedroid.collecting;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TraceDroidMetaInfoKt {
    public static final TraceDroidMetaInfo a(Context context) {
        PackageInfo packageInfo;
        String str;
        String str2;
        String str3;
        String str4;
        Intrinsics.f(context, "context");
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        String str5 = Build.VERSION.RELEASE;
        Intrinsics.e(str5, "Build.VERSION.RELEASE");
        String str6 = Build.MODEL;
        Intrinsics.e(str6, "Build.MODEL");
        File filesDir = context.getFilesDir();
        Intrinsics.e(filesDir, "context.filesDir");
        String absolutePath = filesDir.getAbsolutePath();
        Intrinsics.e(absolutePath, "context.filesDir.absolutePath");
        if (packageInfo == null || (str4 = packageInfo.versionName) == null) {
            str = "unknown";
        } else {
            str = str4;
        }
        if (packageInfo == null || (str3 = packageInfo.packageName) == null) {
            str2 = "unknown";
        } else {
            str2 = str3;
        }
        return new TraceDroidMetaInfo(str5, str6, absolutePath, str, str2, ".tracedroid", "4.1");
    }
}
