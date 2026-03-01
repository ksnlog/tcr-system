package org.ligi.passandroid.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.ligi.compat.HtmlCompat;
import org.ligi.passandroid.databinding.ActivityHelpBinding;
import org.xml.sax.XMLReader;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HelpActivity extends AppCompatActivity {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class ListTagHandler implements Html.TagHandler {
        public ListTagHandler() {
        }

        @Override // android.text.Html.TagHandler
        public void handleTag(boolean z2, String tag, Editable output, XMLReader xmlReader) {
            boolean h2;
            String str;
            Intrinsics.f(tag, "tag");
            Intrinsics.f(output, "output");
            Intrinsics.f(xmlReader, "xmlReader");
            h2 = StringsKt__StringsJVMKt.h(tag, "li", true);
            if (h2) {
                if (z2) {
                    str = "• ";
                } else {
                    str = "\n";
                }
                output.append((CharSequence) str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        ActivityHelpBinding c2 = ActivityHelpBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        setContentView(c2.b());
        c2.f2621c.setText(HtmlCompat.a(getString(2131755126), null, new ListTagHandler()));
        c2.f2621c.setMovementMethod(new LinkMovementMethod());
        k0(c2.f2622d);
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.s(true);
        }
        ActionBar b03 = b0();
        if (b03 != null) {
            b03.y("v3.7.3");
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
