package org.ligi.passandroid.ui;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.ligi.kaxtui.ContextExtensionsKt;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.databinding.ActivityImportBinding;
import org.ligi.passandroid.model.PassStore;
import permissions.dispatcher.ktx.ActivityExtensionsKt;
import permissions.dispatcher.ktx.PermissionsRequester;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImportActivity extends AppCompatActivity {
    private ActivityImportBinding D;
    private final Lazy E;
    private final Lazy F;

    /* JADX WARN: Multi-variable type inference failed */
    public PassImportActivity() {
        Lazy a2;
        Lazy a3;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        a2 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassImportActivity$special$$inlined$inject$default$1(this, null, null));
        this.E = a2;
        a3 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassImportActivity$special$$inlined$inject$default$2(this, null, null));
        this.F = a3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void r0(boolean z2) {
        BuildersKt.b(LifecycleOwnerKt.a(this), Dispatchers.b(), null, new PassImportActivity$doImport$1(this, z2, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s0(boolean z2) {
        PermissionsRequester a2;
        if (!z2) {
            r0(false);
            return;
        }
        a2 = ActivityExtensionsKt.a(this, new String[]{"android.permission.READ_MEDIA_IMAGES"}, (r13 & 2) != 0 ? null : null, (r13 & 4) != 0 ? null : new PassImportActivity$doImportWithPermissionCheck$1(this), (r13 & 8) != 0 ? null : new PassImportActivity$doImportWithPermissionCheck$2(this), new PassImportActivity$doImportWithPermissionCheck$3(this));
        a2.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void v0() {
        ActivityImportBinding activityImportBinding = this.D;
        if (activityImportBinding == null) {
            Intrinsics.p("binding");
            activityImportBinding = null;
        }
        activityImportBinding.f2625c.setVisibility(8);
        ContextExtensionsKt.b(this, 2131755105, 2131755106, null, new PassImportActivity$onExternalStorageDenied$1(this), 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        String str;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        Uri data = getIntent().getData();
        ActivityImportBinding activityImportBinding = null;
        if (data != null) {
            str = data.getScheme();
        } else {
            str = null;
        }
        if (str == null) {
            u0().c("invalid_import_uri", false);
            finish();
            return;
        }
        ActivityImportBinding c2 = ActivityImportBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        this.D = c2;
        if (c2 == null) {
            Intrinsics.p("binding");
        } else {
            activityImportBinding = c2;
        }
        setContentView(activityImportBinding.b());
        s0(false);
    }

    public final PassStore t0() {
        return (PassStore) this.F.getValue();
    }

    public final Tracker u0() {
        return (Tracker) this.E.getValue();
    }
}
