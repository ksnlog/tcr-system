package org.ligi.passandroid.ui.quirk_fix;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.ligi.passandroid.ui.AlertFragment;
import org.ligi.passandroid.ui.PassAndroidActivity;
import org.ligi.passandroid.ui.PassImportActivity;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class USAirwaysLoadActivity extends PassAndroidActivity {
    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        int z2;
        String F;
        List f2;
        boolean z3;
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data != null) {
            z2 = StringsKt__StringsKt.z(String.valueOf(data), "/", 0, false, 6, null);
            if (z2 != -1) {
                F = StringsKt__StringsKt.F(String.valueOf(data), "/");
                if (F == null) {
                    F = "";
                }
                List<String> c2 = new Regex("/").c(F, 0);
                if (!c2.isEmpty()) {
                    ListIterator<String> listIterator = c2.listIterator(c2.size());
                    while (listIterator.hasPrevious()) {
                        if (listIterator.previous().length() == 0) {
                            z3 = true;
                            continue;
                        } else {
                            z3 = false;
                            continue;
                        }
                        if (!z3) {
                            f2 = CollectionsKt___CollectionsKt.F(c2, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                f2 = CollectionsKt__CollectionsKt.f();
                String[] strArr = (String[]) f2.toArray(new String[0]);
                p0().b("quirk_fix", "redirect", "usairways", null);
                Intent intent = new Intent((Context) this, (Class<?>) PassImportActivity.class);
                Uri parse = Uri.parse("http://prod.wap.ncrwebhost.mobi/mobiqa/wap/" + (strArr[strArr.length - 2] + '/' + strArr[strArr.length - 1]) + "/passbook");
                Intrinsics.e(parse, "parse(this)");
                intent.setData(parse);
                startActivity(intent);
                finish();
                return;
            }
        }
        AlertFragment alertFragment = new AlertFragment();
        FragmentManager supportFragmentManager = Q();
        Intrinsics.e(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction p2 = supportFragmentManager.p();
        Intrinsics.e(p2, "beginTransaction()");
        p2.e(alertFragment, "AlertFrag");
        p2.h();
    }
}
