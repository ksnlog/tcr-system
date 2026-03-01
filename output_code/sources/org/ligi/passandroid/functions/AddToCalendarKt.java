package org.ligi.passandroid.functions;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.snackbar.Snackbar;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.functions.AddToCalendarKt;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.model.pass.PassLocation;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AddToCalendarKt {
    public static final Intent b(Pass pass, PassImpl.TimeSpan timeSpan) {
        Object w2;
        String name;
        Intrinsics.f(pass, "pass");
        Intrinsics.f(timeSpan, "timeSpan");
        Intent intent = new Intent("android.intent.action.EDIT");
        if (timeSpan.getFrom() == null && timeSpan.getTo() == null) {
            throw new IllegalArgumentException("span must have either a to or a from");
        }
        intent.setType("vnd.android.cursor.item/event");
        ZonedDateTime from = timeSpan.getFrom();
        if (from == null) {
            ZonedDateTime to = timeSpan.getTo();
            Intrinsics.c(to);
            from = to.L(8L);
        }
        long j2 = 1000;
        intent.putExtra("beginTime", from.toEpochSecond() * j2);
        ZonedDateTime to2 = timeSpan.getTo();
        if (to2 == null) {
            ZonedDateTime from2 = timeSpan.getFrom();
            Intrinsics.c(from2);
            to2 = from2.W(8L);
        }
        intent.putExtra("endTime", to2.toEpochSecond() * j2);
        intent.putExtra("title", pass.getDescription());
        w2 = CollectionsKt___CollectionsKt.w(pass.getLocations());
        PassLocation passLocation = (PassLocation) w2;
        if (passLocation != null && (name = passLocation.getName()) != null) {
            intent.putExtra("eventLocation", name);
        }
        return intent;
    }

    private static final void c(Pass pass, View view, PassImpl.TimeSpan timeSpan) {
        try {
            view.getContext().startActivity(b(pass, timeSpan));
        } catch (ActivityNotFoundException unused) {
            Snackbar.m0(view, 2131755258, 0).X();
        }
    }

    public static final void d(final Pass pass, final View contextView, final PassImpl.TimeSpan timeSpan) {
        Intrinsics.f(pass, "pass");
        Intrinsics.f(contextView, "contextView");
        Intrinsics.f(timeSpan, "timeSpan");
        if (pass.getCalendarTimespan() == null) {
            new AlertDialog.Builder(contextView.getContext()).j(2131755108).v(2131755109).m(17039360, (DialogInterface.OnClickListener) null).r(17039370, new DialogInterface.OnClickListener() { // from class: b0.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    AddToCalendarKt.e(Pass.this, contextView, timeSpan, dialogInterface, i2);
                }
            }).z();
        } else {
            c(pass, contextView, timeSpan);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(Pass pass, View contextView, PassImpl.TimeSpan timeSpan, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(pass, "$pass");
        Intrinsics.f(contextView, "$contextView");
        Intrinsics.f(timeSpan, "$timeSpan");
        c(pass, contextView, timeSpan);
    }
}
