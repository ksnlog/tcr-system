package com.chibatching.kotpref;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Kotpref {

    /* renamed from: a  reason: collision with root package name */
    public static final Kotpref f9a = new Kotpref();

    private Kotpref() {
    }

    public final void a(Context context) {
        Intrinsics.f(context, "context");
        StaticContextProvider staticContextProvider = StaticContextProvider.f20b;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.e(applicationContext, "context.applicationContext");
        staticContextProvider.b(applicationContext);
    }
}
