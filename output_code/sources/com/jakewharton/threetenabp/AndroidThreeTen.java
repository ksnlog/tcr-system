package com.jakewharton.threetenabp;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import org.threeten.bp.zone.ZoneRulesInitializer;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidThreeTen {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicBoolean f123a = new AtomicBoolean();

    public static void a(Application application) {
        b(application);
    }

    public static void b(Context context) {
        c(context, "org/threeten/bp/TZDB.dat");
    }

    public static void c(Context context, String str) {
        if (!f123a.getAndSet(true)) {
            ZoneRulesInitializer.c(new AssetsZoneRulesInitializer(context, str));
        }
    }
}
