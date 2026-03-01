package org.ligi.passandroid;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TrackerKodeinModuleKt {
    public static final Tracker a(Context context) {
        Intrinsics.f(context, "context");
        return new NotTracker();
    }
}
