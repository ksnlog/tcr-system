package org.ligi.passandroid.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.ortiz.touch.TouchImageView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TouchImageActivity extends AppCompatActivity {
    private final Lazy D;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchImageActivity() {
        Lazy a2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new TouchImageActivity$special$$inlined$inject$default$1(this, null, null));
        this.D = a2;
    }

    public final PassStore n0() {
        return (PassStore) this.D.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        Pass currentPass;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        TouchImageView touchImageView = new TouchImageView(this);
        setContentView(touchImageView);
        String stringExtra = getIntent().getStringExtra("IMAGE");
        Bitmap bitmap = null;
        if (stringExtra != null && (currentPass = n0().getCurrentPass()) != null) {
            bitmap = currentPass.getBitmap(n0(), stringExtra);
        }
        if (bitmap == null) {
            finish();
            return;
        }
        touchImageView.setImageBitmap(bitmap);
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.s(true);
        }
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
