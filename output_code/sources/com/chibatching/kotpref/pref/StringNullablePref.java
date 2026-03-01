package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StringNullablePref extends AbstractPref<String> {

    /* renamed from: d  reason: collision with root package name */
    private final String f37d;

    /* renamed from: e  reason: collision with root package name */
    private final String f38e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f39f;

    public StringNullablePref(String str, String str2, boolean z2) {
        this.f37d = str;
        this.f38e = str2;
        this.f39f = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f38e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public String d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        return preference.getString(c(), this.f37d);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: l */
    public void h(KProperty<?> property, String str, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(editor, "editor");
        editor.putString(c(), str);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: m */
    public void i(KProperty<?> property, String str, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putString = preference.edit().putString(c(), str);
        Intrinsics.e(putString, "preference.edit().putString(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putString, this.f39f);
    }
}
