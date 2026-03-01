package com.chibatching.kotpref;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StaticContextProvider implements ContextProvider {

    /* renamed from: a  reason: collision with root package name */
    private static Context f19a;

    /* renamed from: b  reason: collision with root package name */
    public static final StaticContextProvider f20b = new StaticContextProvider();

    private StaticContextProvider() {
    }

    @Override // com.chibatching.kotpref.ContextProvider
    public Context a() {
        Context context = f19a;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Kotpref has not been initialized.");
    }

    public final void b(Context context) {
        Intrinsics.f(context, "context");
        f19a = context.getApplicationContext();
    }
}
