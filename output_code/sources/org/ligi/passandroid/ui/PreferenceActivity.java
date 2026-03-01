package org.ligi.passandroid.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PreferenceActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        setContentView(2131493017);
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.t(true);
        }
        ActionBar b03 = b0();
        if (b03 != null) {
            b03.s(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"PrivateResource"})
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super/*android.app.Activity*/.onOptionsItemSelected(item);
    }
}
