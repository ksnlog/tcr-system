package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LongPref extends AbstractPref<Long> {

    /* renamed from: d  reason: collision with root package name */
    private final long f34d;

    /* renamed from: e  reason: collision with root package name */
    private final String f35e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f36f;

    public LongPref(long j2, String str, boolean z2) {
        this.f34d = j2;
        this.f35e = str;
        this.f36f = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public String e() {
        return this.f35e;
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void h(KProperty kProperty, Long l2, SharedPreferences.Editor editor) {
        l(kProperty, l2.longValue(), editor);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    public /* bridge */ /* synthetic */ void i(KProperty kProperty, Long l2, SharedPreferences sharedPreferences) {
        m(kProperty, l2.longValue(), sharedPreferences);
    }

    @Override // com.chibatching.kotpref.pref.AbstractPref
    /* renamed from: k */
    public Long d(KProperty<?> property, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        return Long.valueOf(preference.getLong(c(), this.f34d));
    }

    public void l(KProperty<?> property, long j2, SharedPreferences.Editor editor) {
        Intrinsics.f(property, "property");
        Intrinsics.f(editor, "editor");
        editor.putLong(c(), j2);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void m(KProperty<?> property, long j2, SharedPreferences preference) {
        Intrinsics.f(property, "property");
        Intrinsics.f(preference, "preference");
        SharedPreferences.Editor putLong = preference.edit().putLong(c(), j2);
        Intrinsics.e(putLong, "preference.edit().putLong(preferenceKey, value)");
        SharedPrefExtensionsKt.a(putLong, this.f36f);
    }
}
