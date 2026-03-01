package com.chibatching.kotpref;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class DefaultPreferencesProvider implements PreferencesProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final DefaultPreferencesProvider f8a = new DefaultPreferencesProvider();

    private DefaultPreferencesProvider() {
    }

    @Override // com.chibatching.kotpref.PreferencesProvider
    public SharedPreferences a(Context context, String name, int i2) {
        Intrinsics.f(context, "context");
        Intrinsics.f(name, "name");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, i2);
        Intrinsics.e(sharedPreferences, "context.getSharedPreferences(name, mode)");
        return sharedPreferences;
    }
}
