package org.ligi.passandroid.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.MoveToNewTopicUI;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MoveToNewTopicUI {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f2744a;

    /* renamed from: b  reason: collision with root package name */
    private final PassStore f2745b;

    /* renamed from: c  reason: collision with root package name */
    private final Pass f2746c;

    public MoveToNewTopicUI(Activity context, PassStore passStore, Pass pass) {
        Intrinsics.f(context, "context");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(pass, "pass");
        this.f2744a = context;
        this.f2745b = passStore;
        this.f2746c = pass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void i(MoveToNewTopicUI this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this$0, "this$0");
        this$0.f2745b.notifyChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void j(MoveToNewTopicUI this$0, DialogInterface dialogInterface) {
        Intrinsics.f(this$0, "this$0");
        this$0.f2745b.notifyChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void k(EditText editText, MoveToNewTopicUI this$0, Function1 move, View view) {
        boolean z2;
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(move, "$move");
        if (editText.getText().toString().length() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            editText.setError(this$0.f2744a.getString(2131755073));
            editText.requestFocus();
            return;
        }
        move.f(editText.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void l(Function1 move, String topic, View view) {
        Intrinsics.f(move, "$move");
        Intrinsics.f(topic, "$topic");
        move.f(topic);
    }

    public final void h() {
        AlertDialog z2 = new AlertDialog.Builder(this.f2744a).w(this.f2744a.getString(2131755180)).x(2131492919).r(17039370, (DialogInterface.OnClickListener) null).m(17039360, new DialogInterface.OnClickListener() { // from class: e0.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                MoveToNewTopicUI.i(MoveToNewTopicUI.this, dialogInterface, i2);
            }
        }).p(new DialogInterface.OnCancelListener() { // from class: e0.g
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                MoveToNewTopicUI.j(MoveToNewTopicUI.this, dialogInterface);
            }
        }).z();
        final MoveToNewTopicUI$show$move$1 moveToNewTopicUI$show$move$1 = new MoveToNewTopicUI$show$move$1(this, z2);
        final EditText editText = (EditText) z2.findViewById(2131296604);
        ViewGroup viewGroup = (ViewGroup) z2.findViewById(2131296783);
        if (editText != null) {
            z2.n(-1).setOnClickListener(new View.OnClickListener() { // from class: e0.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoveToNewTopicUI.k(editText, this, moveToNewTopicUI$show$move$1, view);
                }
            });
        }
        String topic = this.f2745b.getClassifier().getTopic(this.f2746c, "");
        int[] iArr = {2131755313, 2131755311, 2131755312};
        ArrayList<String> arrayList = new ArrayList(3);
        for (int i2 = 0; i2 < 3; i2++) {
            arrayList.add(this.f2744a.getString(iArr[i2]));
        }
        for (final String str : arrayList) {
            if (!Intrinsics.a(str, topic)) {
                Button button = new Button(this.f2744a);
                button.setText(str);
                if (viewGroup != null) {
                    viewGroup.addView(button);
                }
                button.setOnClickListener(new View.OnClickListener() { // from class: e0.i
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MoveToNewTopicUI.l(Function1.this, str, view);
                    }
                });
            }
        }
    }
}
