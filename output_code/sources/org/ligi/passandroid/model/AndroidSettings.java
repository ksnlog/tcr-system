package org.ligi.passandroid.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.comparator.PassSortOrder;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AndroidSettings implements Settings {
    private final Context context;
    private final Lazy sharedPreferences$delegate;

    public AndroidSettings(Context context) {
        Lazy b2;
        Intrinsics.f(context, "context");
        this.context = context;
        b2 = LazyKt__LazyJVMKt.b(new AndroidSettings$sharedPreferences$2(this));
        this.sharedPreferences$delegate = b2;
    }

    private final SharedPreferences getSharedPreferences() {
        return (SharedPreferences) this.sharedPreferences$delegate.getValue();
    }

    @Override // org.ligi.passandroid.model.Settings
    public boolean doTraceDroidEmailSend() {
        return true;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // org.ligi.passandroid.model.Settings
    public int getNightMode() {
        String string = getSharedPreferences().getString(this.context.getString(2131755285), "auto");
        if (string != null) {
            int hashCode = string.hashCode();
            if (hashCode == 99228) {
                return string.equals("day") ? 1 : -1;
            } else if (hashCode != 3005871) {
                return (hashCode == 104817688 && string.equals("night")) ? 2 : -1;
            } else {
                string.equals("auto");
                return -1;
            }
        }
        return -1;
    }

    @Override // org.ligi.passandroid.model.Settings
    public File getPassesDir() {
        return new File(this.context.getFilesDir().getAbsolutePath(), "passes");
    }

    @Override // org.ligi.passandroid.model.Settings
    public PassSortOrder getSortOrder() {
        PassSortOrder[] values;
        boolean z2;
        String string = this.context.getString(2131755286);
        Intrinsics.e(string, "context.getString(R.string.preference_key_sort)");
        String string2 = getSharedPreferences().getString(string, "0");
        Intrinsics.c(string2);
        Integer valueOf = Integer.valueOf(string2);
        for (PassSortOrder passSortOrder : PassSortOrder.values()) {
            int i2 = passSortOrder.getInt();
            if (valueOf != null && i2 == valueOf.intValue()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return passSortOrder;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @Override // org.ligi.passandroid.model.Settings
    public File getStateDir() {
        return new File(this.context.getFilesDir(), "state");
    }

    @Override // org.ligi.passandroid.model.Settings
    public boolean isAutomaticLightEnabled() {
        return getSharedPreferences().getBoolean(this.context.getString(2131755283), true);
    }

    @Override // org.ligi.passandroid.model.Settings
    public boolean isCondensedModeEnabled() {
        return getSharedPreferences().getBoolean(this.context.getString(2131755284), false);
    }
}
