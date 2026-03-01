package org.ligi.passandroid.scan;

import android.app.NotificationManager;
import androidx.core.content.ContextCompat;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SearchPassesIntentService$notifyManager$2 extends Lambda implements Function0<NotificationManager> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ SearchPassesIntentService f2708d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchPassesIntentService$notifyManager$2(SearchPassesIntentService searchPassesIntentService) {
        super(0);
        this.f2708d = searchPassesIntentService;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final NotificationManager mo2invoke() {
        Object h2 = ContextCompat.h(this.f2708d, NotificationManager.class);
        Intrinsics.c(h2);
        return (NotificationManager) h2;
    }
}
