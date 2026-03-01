package org.ligi.passandroid.scan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.ligi.passandroid.databinding.ActivityScanBinding;
import org.ligi.passandroid.scan.events.PassScanEventChannelProvider;
import org.ligi.passandroid.ui.PassAndroidActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassScanActivity extends PassAndroidActivity {
    private final Lazy H;

    /* JADX WARN: Multi-variable type inference failed */
    public PassScanActivity() {
        Lazy a2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new PassScanActivity$special$$inlined$inject$default$1(this, null, null));
        this.H = a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PassScanEventChannelProvider r0() {
        return (PassScanEventChannelProvider) this.H.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        ActivityScanBinding c2 = ActivityScanBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        setContentView(c2.b());
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.s(true);
        }
        BuildersKt__Builders_commonKt.b(LifecycleOwnerKt.a(this), null, null, new PassScanActivity$onCreate$1(this, c2, null), 3, null);
        startService(new Intent((Context) this, (Class<?>) SearchPassesIntentService.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super/*android.app.Activity*/.onOptionsItemSelected(item);
    }
}
