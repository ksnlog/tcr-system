package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BooleanPref extends AbstractPref<Boolean> {

    /* renamed from: d  reason: collision with root package name */
    private final boolean f25d;

    /* renamed from: e  reason: collision with root package name */
    private final String f26e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f27f;

    public BooleanPref(boolean z2, String str, boolean z3) {
        this.f25d = z2;
        this.f26e = str;
        this.f27f = z3;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f26e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void h(KProperty kProperty, Boolean bool, SharedPreferences.Editor editor) {
        l(kProperty, bool.booleanValue(), editor);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void i(KProperty kProperty, Boolean bool, SharedPreferences sharedPreferences) {
        m(kProperty, bool.booleanValue(), sharedPreferences);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public Boolean d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        return Boolean.valueOf(preference.getBoolean(c(), this.f25d));
    }

    public void l(KProperty<?> property, boolean z2, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(editor, "editor");
        editor.putBoolean(c(), z2);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void m(KProperty<?> property, boolean z2, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putBoolean = preference.edit().putBoolean(c(), z2);
        Intrinsics.e(putBoolean, "preference.edit().putBoolean(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putBoolean, this.f27f);
    }
}
