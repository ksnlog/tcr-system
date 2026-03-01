package org.ligi.passandroid.ui.quirk_fix;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import org.ligi.passandroid.ui.AlertFragment;
import org.ligi.passandroid.ui.PassAndroidActivity;
import org.ligi.passandroid.ui.PassImportActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class URLRewriteActivity extends PassAndroidActivity {
    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        String str;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data != null) {
            str = new URLRewriteController(p0()).e(data);
        } else {
            str = null;
        }
        if (str == null) {
            Q().p().e(new AlertFragment(), "AlertFrag").h();
            return;
        }
        Intent intent = new Intent((Context) this, (Class<?>) PassImportActivity.class);
        intent.setData(Uri.parse(str));
        startActivity(intent);
        finish();
    }
}
