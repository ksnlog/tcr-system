package org.ligi.passandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.Settings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PassAndroidActivity extends AppCompatActivity {
    private final Lazy D;
    private final Lazy E;
    private final Lazy F;
    private Integer G;

    /* JADX WARN: Multi-variable type inference failed */
    public PassAndroidActivity() {
        Lazy a2;
        Lazy a3;
        Lazy a4;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        a2 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassAndroidActivity$special$$inlined$inject$default$1(this, null, null));
        this.D = a2;
        a3 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassAndroidActivity$special$$inlined$inject$default$2(this, null, null));
        this.E = a3;
        a4 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new PassAndroidActivity$special$$inlined$inject$default$3(this, null, null));
        this.F = a4;
    }

    public final PassStore n0() {
        return (PassStore) this.D.getValue();
    }

    public final Settings o0() {
        return (Settings) this.E.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        super/*androidx.fragment.app.FragmentActivity*/.onResume();
        Integer num = this.G;
        if (num != null) {
            int nightMode = o0().getNightMode();
            if (num == null || num.intValue() != nightMode) {
                ActivityCompat.o(this);
            }
        }
        this.G = Integer.valueOf(o0().getNightMode());
    }

    public final Tracker p0() {
        return (Tracker) this.F.getValue();
    }
}
