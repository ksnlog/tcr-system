package com.chibatching.kotpref;

import android.content.SharedPreferences;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SharedPrefExtensionsKt {
    public static final void a(SharedPreferences.Editor execute, boolean z2) {
        Intrinsics.f(execute, "$this$execute");
        if (z2) {
            execute.commit();
        } else {
            execute.apply();
        }
    }
}
