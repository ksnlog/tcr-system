package org.ligi.passandroid.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceFragmentCompat;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.Settings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PrefsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Lazy p0;

    /* JADX WARN: Multi-variable type inference failed */
    public PrefsFragment() {
        Lazy a2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new PrefsFragment$special$$inlined$inject$default$1(this, null, null));
        this.p0 = a2;
    }

    public void H0() {
        super/*androidx.fragment.app.Fragment*/.H0();
        SharedPreferences y2 = U1().y();
        if (y2 != null) {
            y2.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    public void M0() {
        super/*androidx.fragment.app.Fragment*/.M0();
        SharedPreferences y2 = U1().y();
        if (y2 != null) {
            y2.registerOnSharedPreferenceChangeListener(this);
        }
    }

    public void Y1(Bundle bundle, String str) {
        g2(2131951617, str);
    }

    public final Settings i2() {
        return (Settings) this.p0.getValue();
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Intrinsics.f(sharedPreferences, "sharedPreferences");
        Intrinsics.f(key, "key");
        if (Intrinsics.a(key, W(2131755285))) {
            AppCompatDelegate.O(i2().getNightMode());
            FragmentActivity m2 = m();
            if (m2 != null) {
                ActivityCompat.o(m2);
            }
        }
    }
}
