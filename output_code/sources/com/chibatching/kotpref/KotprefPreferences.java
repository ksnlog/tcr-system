package com.chibatching.kotpref;

import android.annotation.Nullable;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import com.chibatching.kotpref.pref.StringSetPref;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KotprefPreferences implements SharedPreferences {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f14a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class KotprefEditor implements SharedPreferences.Editor {

        /* renamed from: a  reason: collision with root package name */
        private final Lazy f15a;

        /* renamed from: b  reason: collision with root package name */
        private final SharedPreferences.Editor f16b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ KotprefPreferences f17c;

        public KotprefEditor(KotprefPreferences kotprefPreferences, SharedPreferences.Editor editor) {
            Lazy b2;
            Intrinsics.f(editor, "editor");
            this.f17c = kotprefPreferences;
            this.f16b = editor;
            b2 = LazyKt__LazyJVMKt.b(KotprefPreferences$KotprefEditor$prefStringSet$2.f18d);
            this.f15a = b2;
        }

        private final Map<String, StringSetPref.PrefMutableSet> a() {
            return (Map) this.f15a.getValue();
        }

        @TargetApi(R$styleable.f1331g)
        private final void c() {
            for (String str : a().keySet()) {
                StringSetPref.PrefMutableSet prefMutableSet = a().get(str);
                if (prefMutableSet != null) {
                    this.f16b.putStringSet(str, prefMutableSet);
                    prefMutableSet.i();
                }
            }
            a().clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            c();
            this.f16b.apply();
        }

        @TargetApi(R$styleable.f1331g)
        public final SharedPreferences.Editor b(String key, StringSetPref.PrefMutableSet prefSet) {
            Intrinsics.f(key, "key");
            Intrinsics.f(prefSet, "prefSet");
            a().put(key, prefSet);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this.f16b.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            c();
            return this.f16b.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z2) {
            return this.f16b.putBoolean(str, z2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            return this.f16b.putFloat(str, f2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            return this.f16b.putInt(str, i2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            return this.f16b.putLong(str, j2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, @Nullable String str2) {
            return this.f16b.putString(str, str2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, @Nullable Set<String> set) {
            return this.f16b.putStringSet(str, set);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            return this.f16b.remove(str);
        }
    }

    public KotprefPreferences(SharedPreferences preferences) {
        Intrinsics.f(preferences, "preferences");
        this.f14a = preferences;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return this.f14a.contains(str);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        SharedPreferences.Editor edit = this.f14a.edit();
        Intrinsics.e(edit, "preferences.edit()");
        return new KotprefEditor(this, edit);
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return this.f14a.getAll();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z2) {
        return this.f14a.getBoolean(str, z2);
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f2) {
        return this.f14a.getFloat(str, f2);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i2) {
        return this.f14a.getInt(str, i2);
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j2) {
        return this.f14a.getLong(str, j2);
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public String getString(String str, @Nullable String str2) {
        return this.f14a.getString(str, str2);
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public Set<String> getStringSet(String str, @Nullable Set<String> set) {
        return this.f14a.getStringSet(str, set);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f14a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f14a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
