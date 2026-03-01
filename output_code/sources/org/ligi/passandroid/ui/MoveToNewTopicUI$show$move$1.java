package org.ligi.passandroid.ui;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.functions.MoveHelperKt;
import org.ligi.passandroid.model.PassClassifier;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MoveToNewTopicUI$show$move$1 extends Lambda implements Function1<String, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ MoveToNewTopicUI f2747d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ AlertDialog f2748e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveToNewTopicUI$show$move$1(MoveToNewTopicUI moveToNewTopicUI, AlertDialog alertDialog) {
        super(1);
        this.f2747d = moveToNewTopicUI;
        this.f2748e = alertDialog;
    }

    public final void a(String topic) {
        PassStore passStore;
        Pass pass;
        Activity activity;
        Intrinsics.f(topic, "topic");
        passStore = this.f2747d.f2745b;
        PassClassifier classifier = passStore.getClassifier();
        pass = this.f2747d.f2746c;
        activity = this.f2747d.f2744a;
        MoveHelperKt.b(classifier, pass, topic, activity);
        this.f2748e.dismiss();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(String str) {
        a(str);
        return Unit.f763a;
    }
}
