package org.ligi.passandroid;

import javax.annotation.Nullable;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class NotTracker implements Tracker {
    @Override // org.ligi.passandroid.Tracker
    public void a(String str, Throwable th, boolean z2) {
        if (z2) {
            Timber.h(th, "Fatal Exception %s", str);
        } else {
            Timber.h(th, "Not Fatal Exception %s", str);
        }
    }

    @Override // org.ligi.passandroid.Tracker
    public void b(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l2) {
    }

    @Override // org.ligi.passandroid.Tracker
    public void c(String str, boolean z2) {
        if (z2) {
            Timber.g("Fatal Exception %s", str);
        } else {
            Timber.g("Not Fatal Exception %s", str);
        }
    }
}
