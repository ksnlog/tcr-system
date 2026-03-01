package org.ligi.snackengage.stats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SnackStats {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2978a;

    public SnackStats(Context context) {
        this.f2978a = context;
    }

    private long a(Snack snack) {
        SharedPreferences d2 = d();
        return d2.getLong("KEY_LAST_SNACK_SHOW" + snack.a(), 0L);
    }

    private long c(Snack snack) {
        SharedPreferences d2 = d();
        return d2.getLong("OPPORTUNITY_COUNTER" + snack.a(), 0L);
    }

    public long b(Snack snack) {
        return c(snack) - a(snack);
    }

    protected SharedPreferences d() {
        return PreferenceManager.getDefaultSharedPreferences(this.f2978a);
    }

    public void e(Snack snack) {
        SharedPreferences.Editor edit = d().edit();
        edit.putLong("OPPORTUNITY_COUNTER" + snack.a(), c(snack) + 1).apply();
    }

    public void f(Snack snack) {
        SharedPreferences.Editor edit = d().edit();
        edit.putLong("KEY_LAST_SNACK_CLICK" + snack.a(), c(snack));
        edit.commit();
    }

    public void g(Snack snack) {
        SharedPreferences.Editor edit = d().edit();
        edit.putLong("KEY_LAST_SNACK_SHOW" + snack.a(), c(snack));
        edit.putInt("KEY_TIMES_SHOWN" + snack.a(), h(snack) + 1);
        edit.commit();
    }

    public int h(Snack snack) {
        SharedPreferences d2 = d();
        return d2.getInt("KEY_TIMES_SHOWN" + snack.a(), 0);
    }

    public boolean i(Snack snack) {
        SharedPreferences d2 = d();
        StringBuilder sb = new StringBuilder();
        sb.append("KEY_LAST_SNACK_CLICK");
        sb.append(snack.a());
        return d2.getLong(sb.toString(), 0L) > 0;
    }
}
