package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FloatPref extends AbstractPref<Float> {

    /* renamed from: d  reason: collision with root package name */
    private final float f28d;

    /* renamed from: e  reason: collision with root package name */
    private final String f29e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f30f;

    public FloatPref(float f2, String str, boolean z2) {
        this.f28d = f2;
        this.f29e = str;
        this.f30f = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f29e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void h(KProperty kProperty, Float f2, SharedPreferences.Editor editor) {
        l(kProperty, f2.floatValue(), editor);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void i(KProperty kProperty, Float f2, SharedPreferences sharedPreferences) {
        m(kProperty, f2.floatValue(), sharedPreferences);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public Float d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        return Float.valueOf(preference.getFloat(c(), this.f28d));
    }

    public void l(KProperty<?> property, float f2, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(editor, "editor");
        editor.putFloat(c(), f2);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void m(KProperty<?> property, float f2, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putFloat = preference.edit().putFloat(c(), f2);
        Intrinsics.e(putFloat, "preference.edit().putFloat(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putFloat, this.f30f);
    }
}
