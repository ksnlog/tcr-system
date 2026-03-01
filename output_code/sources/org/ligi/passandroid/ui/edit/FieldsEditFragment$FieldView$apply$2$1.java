package org.ligi.passandroid.ui.edit;

import android.text.Editable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.model.pass.PassField;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class FieldsEditFragment$FieldView$apply$2$1 extends Lambda implements Function1<Editable, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassField f2945d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldsEditFragment$FieldView$apply$2$1(PassField passField) {
        super(1);
        this.f2945d = passField;
    }

    public final void a(Editable it) {
        Intrinsics.f(it, "it");
        this.f2945d.setValue(String.valueOf(it));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Editable editable) {
        a(editable);
        return Unit.f763a;
    }
}
