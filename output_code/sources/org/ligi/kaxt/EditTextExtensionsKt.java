package org.ligi.kaxt;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EditTextExtensionsKt {
    public static final void a(EditText editText, final Function1<? super Editable, Unit> function1) {
        editText.addTextChangedListener(new TextWatcher() { // from class: org.ligi.kaxt.EditTextExtensionsKt$doAfterEdit$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Function1.this.f(editable);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
    }
}
