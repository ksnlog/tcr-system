package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StringPref extends AbstractPref<String> {

    /* renamed from: d  reason: collision with root package name */
    private final String f40d;

    /* renamed from: e  reason: collision with root package name */
    private final String f41e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f42f;

    public StringPref(String str, String str2, boolean z2) {
        Intrinsics.f(str, "default");
        this.f40d = str;
        this.f41e = str2;
        this.f42f = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f41e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public String d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        String string = preference.getString(c(), this.f40d);
        if (string != null) {
            return string;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: l */
    public void h(KProperty<?> property, String value, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(value, "value");
        Intrinsics.f(editor, "editor");
        editor.putString(c(), value);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: m */
    public void i(KProperty<?> property, String value, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(value, "value");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putString = preference.edit().putString(c(), value);
        Intrinsics.e(putString, "preference.edit().putString(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putString, this.f42f);
    }
}
