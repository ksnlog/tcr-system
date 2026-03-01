package org.ligi.passandroid.functions;

import android.app.Activity;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.functions.MoveHelperKt;
import org.ligi.passandroid.model.PassClassifier;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MoveHelperKt {
    public static final void b(final PassClassifier passClassifier, final Pass pass, String topic, Activity activity) {
        Intrinsics.f(passClassifier, "passClassifier");
        Intrinsics.f(pass, "pass");
        Intrinsics.f(topic, "topic");
        Intrinsics.f(activity, "activity");
        final String topic2 = passClassifier.getTopic(pass, "");
        View findViewById = activity.getWindow().getDecorView().findViewById(2131296448);
        Snackbar.n0(findViewById, "Pass moved to " + topic, 0).p0(2131755317, new View.OnClickListener() { // from class: b0.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoveHelperKt.c(PassClassifier.this, pass, topic2, view);
            }
        }).X();
        passClassifier.moveToTopic(pass, topic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(PassClassifier passClassifier, Pass pass, String oldTopic, View view) {
        Intrinsics.f(passClassifier, "$passClassifier");
        Intrinsics.f(pass, "$pass");
        Intrinsics.f(oldTopic, "$oldTopic");
        passClassifier.moveToTopic(pass, oldTopic);
    }
}
