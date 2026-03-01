package org.ligi.passandroid.ui.pass_view_holder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.ui.pass_view_holder.EditViewHolder;
import org.ligi.passandroid.ui.views.TimeAndNavBar;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.temporal.ChronoUnit;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EditViewHolder extends VerbosePassViewHolder implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    /* renamed from: w  reason: collision with root package name */
    private ZonedDateTime f2951w;

    /* renamed from: x  reason: collision with root package name */
    private PassImpl f2952x;

    /* renamed from: y  reason: collision with root package name */
    private PassStore f2953y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditViewHolder(CardView view) {
        super(view);
        Intrinsics.f(view, "view");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e0(EditViewHolder this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        Context context = this$0.V().getContext();
        ZonedDateTime zonedDateTime = this$0.f2951w;
        ZonedDateTime zonedDateTime2 = null;
        if (zonedDateTime == null) {
            Intrinsics.p("time");
            zonedDateTime = null;
        }
        int J = zonedDateTime.J();
        ZonedDateTime zonedDateTime3 = this$0.f2951w;
        if (zonedDateTime3 == null) {
            Intrinsics.p("time");
            zonedDateTime3 = null;
        }
        int value = zonedDateTime3.H().getValue() - 1;
        ZonedDateTime zonedDateTime4 = this$0.f2951w;
        if (zonedDateTime4 == null) {
            Intrinsics.p("time");
        } else {
            zonedDateTime2 = zonedDateTime4;
        }
        new DatePickerDialog(context, this$0, J, value, zonedDateTime2.E()).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f0(EditViewHolder this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        new AlertDialog.Builder(this$0.V().getContext()).k("Not yet available").r(17039370, (DialogInterface.OnClickListener) null).z();
    }

    @Override // org.ligi.passandroid.ui.pass_view_holder.VerbosePassViewHolder, org.ligi.passandroid.ui.pass_view_holder.PassViewHolder
    public void Q(Pass pass, PassStore passStore, Activity activity) {
        ZonedDateTime zonedDateTime;
        ZonedDateTime X;
        Intrinsics.f(pass, "pass");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(activity, "activity");
        super.Q(pass, passStore, activity);
        this.f2952x = (PassImpl) pass;
        this.f2953y = passStore;
        PassImpl.TimeSpan calendarTimespan = pass.getCalendarTimespan();
        if (calendarTimespan != null) {
            zonedDateTime = calendarTimespan.getFrom();
        } else {
            zonedDateTime = null;
        }
        if (zonedDateTime != null) {
            X = calendarTimespan.getFrom();
        } else {
            ZonedDateTime f02 = ZonedDateTime.M().f0(ChronoUnit.MINUTES);
            X = f02.X(30 - (f02.G() % 30));
            Intrinsics.e(X, "{\n            val rounde…e.minute % 30))\n        }");
        }
        this.f2951w = X;
    }

    @Override // org.ligi.passandroid.ui.pass_view_holder.PassViewHolder
    protected int W(boolean z2, boolean z3) {
        return 0;
    }

    @Override // org.ligi.passandroid.ui.pass_view_holder.PassViewHolder
    public void Z(Activity activity, Pass pass) {
        Intrinsics.f(activity, "activity");
        Intrinsics.f(pass, "pass");
        TimeAndNavBar timeAndNavBar = (TimeAndNavBar) V().findViewById(2131296772);
        ((TextView) timeAndNavBar.findViewById(2131296773)).setText(V().getContext().getString(2131755097));
        ((TextView) timeAndNavBar.findViewById(2131296514)).setText(V().getContext().getString(2131755096));
        ((TextView) timeAndNavBar.findViewById(2131296773)).setOnClickListener(new View.OnClickListener() { // from class: h0.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditViewHolder.e0(EditViewHolder.this, view);
            }
        });
        ((TextView) timeAndNavBar.findViewById(2131296514)).setOnClickListener(new View.OnClickListener() { // from class: h0.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditViewHolder.f0(EditViewHolder.this, view);
            }
        });
    }

    @Override // android.app.DatePickerDialog.OnDateSetListener
    public void onDateSet(DatePicker view, int i2, int i3, int i4) {
        Intrinsics.f(view, "view");
        ZonedDateTime zonedDateTime = this.f2951w;
        ZonedDateTime zonedDateTime2 = null;
        if (zonedDateTime == null) {
            Intrinsics.p("time");
            zonedDateTime = null;
        }
        ZonedDateTime i02 = zonedDateTime.m0(i2).l0(i3 + 1).i0(i4);
        Intrinsics.e(i02, "time.withYear(year).with…ithDayOfMonth(dayOfMonth)");
        this.f2951w = i02;
        PassImpl passImpl = this.f2952x;
        if (passImpl == null) {
            Intrinsics.p("pass");
            passImpl = null;
        }
        ZonedDateTime zonedDateTime3 = this.f2951w;
        if (zonedDateTime3 == null) {
            Intrinsics.p("time");
            zonedDateTime3 = null;
        }
        passImpl.setCalendarTimespan(new PassImpl.TimeSpan(zonedDateTime3, null, null));
        Context context = ((RecyclerView.ViewHolder) this).a.getContext();
        ZonedDateTime zonedDateTime4 = this.f2951w;
        if (zonedDateTime4 == null) {
            Intrinsics.p("time");
            zonedDateTime4 = null;
        }
        int F = zonedDateTime4.F();
        ZonedDateTime zonedDateTime5 = this.f2951w;
        if (zonedDateTime5 == null) {
            Intrinsics.p("time");
        } else {
            zonedDateTime2 = zonedDateTime5;
        }
        new TimePickerDialog(context, this, F, zonedDateTime2.G(), true).show();
    }

    @Override // android.app.TimePickerDialog.OnTimeSetListener
    public void onTimeSet(TimePicker view, int i2, int i3) {
        Intrinsics.f(view, "view");
        ZonedDateTime zonedDateTime = this.f2951w;
        PassStore passStore = null;
        if (zonedDateTime == null) {
            Intrinsics.p("time");
            zonedDateTime = null;
        }
        ZonedDateTime k0 = zonedDateTime.j0(i2).k0(i3);
        Intrinsics.e(k0, "time.withHour(hourOfDay).withMinute(minute)");
        this.f2951w = k0;
        PassImpl passImpl = this.f2952x;
        if (passImpl == null) {
            Intrinsics.p("pass");
            passImpl = null;
        }
        ZonedDateTime zonedDateTime2 = this.f2951w;
        if (zonedDateTime2 == null) {
            Intrinsics.p("time");
            zonedDateTime2 = null;
        }
        passImpl.setCalendarTimespan(new PassImpl.TimeSpan(zonedDateTime2, null, null));
        PassImpl passImpl2 = this.f2952x;
        if (passImpl2 == null) {
            Intrinsics.p("pass");
            passImpl2 = null;
        }
        PassStore passStore2 = this.f2953y;
        if (passStore2 == null) {
            Intrinsics.p("passStore");
        } else {
            passStore = passStore2;
        }
        X(passImpl2, passStore);
    }
}
