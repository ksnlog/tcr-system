package org.ligi.kaxtui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ContextExtensionsKt {
    public static final void a(Context context, int i2, Integer num, final Function0<Unit> function0, final Function0<Unit> function02) {
        AlertDialog.Builder j2 = new AlertDialog.Builder(context).j(i2);
        if (num != null) {
            j2.v(num.intValue());
        }
        j2.r(17039370, new DialogInterface.OnClickListener() { // from class: org.ligi.kaxtui.ContextExtensionsKt$alert$8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                Function0.this.mo2invoke();
            }
        });
        j2.p(new DialogInterface.OnCancelListener() { // from class: org.ligi.kaxtui.ContextExtensionsKt$alert$9
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                Function0 function03 = Function0.this;
                if (function03 == null) {
                    function03 = function02;
                }
                function03.mo2invoke();
            }
        });
        j2.z();
    }

    public static /* synthetic */ void b(Context context, int i2, Integer num, Function0 function0, Function0 function02, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            num = null;
        }
        if ((i3 & 4) != 0) {
            function0 = ContextExtensionsKt$alert$5.f2605d;
        }
        if ((i3 & 8) != 0) {
            function02 = ContextExtensionsKt$alert$6.f2606d;
        }
        a(context, i2, num, function0, function02);
    }
}
