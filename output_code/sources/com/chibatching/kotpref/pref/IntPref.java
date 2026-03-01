package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class IntPref extends AbstractPref<Integer> {

    /* renamed from: d  reason: collision with root package name */
    private final int f31d;

    /* renamed from: e  reason: collision with root package name */
    private final String f32e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f33f;

    public IntPref(int i2, String str, boolean z2) {
        this.f31d = i2;
        this.f32e = str;
        this.f33f = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f32e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void h(KProperty kProperty, Integer num, SharedPreferences.Editor editor) {
        l(kProperty, num.intValue(), editor);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void i(KProperty kProperty, Integer num, SharedPreferences sharedPreferences) {
        m(kProperty, num.intValue(), sharedPreferences);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public Integer d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        return Integer.valueOf(preference.getInt(c(), this.f31d));
    }

    public void l(KProperty<?> property, int i2, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(editor, "editor");
        editor.putInt(c(), i2);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void m(KProperty<?> property, int i2, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putInt = preference.edit().putInt(c(), i2);
        Intrinsics.e(putInt, "preference.edit().putInt(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putInt, this.f33f);
    }
}
