package org.ligi.passandroid.ui.pass_view_holder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.functions.AddToCalendarKt;
import org.ligi.passandroid.model.PassBitmapDefinitions;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassField;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.ui.NavigateToLocationsDialogKt;
import org.ligi.passandroid.ui.pass_view_holder.PassViewHolder;
import org.ligi.passandroid.ui.views.CategoryIndicatorViewWithIcon;
import org.ligi.passandroid.ui.views.TimeAndNavBar;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class PassViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: u  reason: collision with root package name */
    private final CardView f2954u;

    /* renamed from: v  reason: collision with root package name */
    private final TimeAndNavBar f2955v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassViewHolder(CardView view) {
        super(view);
        Intrinsics.f(view, "view");
        this.f2954u = view;
        this.f2955v = (TimeAndNavBar) view.findViewById(2131296772);
    }

    private final PassImpl.TimeSpan R(Pass pass) {
        if (pass.getCalendarTimespan() != null) {
            return pass.getCalendarTimespan();
        }
        List<PassImpl.TimeSpan> validTimespans = pass.getValidTimespans();
        if (validTimespans == null) {
            validTimespans = CollectionsKt__CollectionsKt.f();
        }
        if (!validTimespans.isEmpty()) {
            List<PassImpl.TimeSpan> validTimespans2 = pass.getValidTimespans();
            Intrinsics.c(validTimespans2);
            return validTimespans2.get(0);
        }
        return null;
    }

    private final String T(PassField passField) {
        StringBuilder sb = new StringBuilder();
        if (passField.getLabel() != null) {
            sb.append(passField.getLabel());
            if (passField.getValue() != null) {
                sb.append(": ");
            }
        }
        if (passField.getValue() != null) {
            sb.append(passField.getValue());
        }
        return String.valueOf(sb);
    }

    private final String Y(String str, ZonedDateTime zonedDateTime) {
        CharSequence relativeDateTimeString = DateUtils.getRelativeDateTimeString(this.f2954u.getContext(), zonedDateTime.toEpochSecond() * 1000, 60000L, 604800000L, 0);
        return str + ((Object) relativeDateTimeString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a0(PassViewHolder this$0, Pass pass, View view) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(pass, "$pass");
        PassImpl.TimeSpan R = this$0.R(pass);
        if (R != null) {
            AddToCalendarKt.d(pass, this$0.f2954u, R);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b0(Activity activity, Pass pass, View view) {
        Intrinsics.f(activity, "$activity");
        Intrinsics.f(pass, "$pass");
        NavigateToLocationsDialogKt.d(activity, pass, false);
    }

    public void Q(Pass pass, PassStore passStore, Activity activity) {
        Intrinsics.f(pass, "pass");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(activity, "activity");
        Z(activity, pass);
        X(pass, passStore);
    }

    public final String S(Pass pass) {
        Object w2;
        Intrinsics.f(pass, "pass");
        w2 = CollectionsKt___CollectionsKt.w(pass.getFields());
        PassField passField = (PassField) w2;
        if (passField != null) {
            return T(passField);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String U(Pass pass) {
        ZonedDateTime zonedDateTime;
        String str;
        Intrinsics.f(pass, "pass");
        PassImpl.TimeSpan calendarTimespan = pass.getCalendarTimespan();
        if (calendarTimespan != null) {
            zonedDateTime = calendarTimespan.getFrom();
        } else {
            zonedDateTime = null;
        }
        if (zonedDateTime != null) {
            PassImpl.TimeSpan calendarTimespan2 = pass.getCalendarTimespan();
            Intrinsics.c(calendarTimespan2);
            ZonedDateTime from = calendarTimespan2.getFrom();
            Intrinsics.c(from);
            return Y("", from);
        }
        List<PassImpl.TimeSpan> validTimespans = pass.getValidTimespans();
        if (validTimespans == null) {
            validTimespans = CollectionsKt__CollectionsKt.f();
        }
        if (!(!validTimespans.isEmpty())) {
            return null;
        }
        List<PassImpl.TimeSpan> validTimespans2 = pass.getValidTimespans();
        Intrinsics.c(validTimespans2);
        if (validTimespans2.get(0).getTo() == null) {
            return null;
        }
        List<PassImpl.TimeSpan> validTimespans3 = pass.getValidTimespans();
        Intrinsics.c(validTimespans3);
        ZonedDateTime to = validTimespans3.get(0).getTo();
        Intrinsics.c(to);
        if (to.r(ZonedDateTime.M())) {
            str = "expires ";
        } else {
            str = " expired ";
        }
        return Y(str, to);
    }

    public final CardView V() {
        return this.f2954u;
    }

    protected int W(boolean z2, boolean z3) {
        if (z2) {
            return 8;
        }
        return z3 ? 0 : 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void X(Pass pass, PassStore passStore) {
        boolean z2;
        Intrinsics.f(pass, "pass");
        Intrinsics.f(passStore, "passStore");
        PassImpl.TimeSpan R = R(pass);
        boolean z3 = false;
        if (R == null && pass.getLocations().isEmpty()) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2954u.findViewById(2131296323).setVisibility(W(z2, true));
        ((TextView) this.f2955v.findViewById(2131296514)).setVisibility(W(z2, !pass.getLocations().isEmpty()));
        TextView textView = (TextView) this.f2955v.findViewById(2131296773);
        if (R != null) {
            z3 = true;
        }
        textView.setVisibility(W(z2, z3));
        Bitmap bitmap = pass.getBitmap(passStore, PassBitmapDefinitions.BITMAP_ICON);
        CategoryIndicatorViewWithIcon categoryIndicatorViewWithIcon = (CategoryIndicatorViewWithIcon) this.f2954u.findViewById(2131296363);
        if (bitmap != null) {
            categoryIndicatorViewWithIcon.setIcon(bitmap);
        }
        categoryIndicatorViewWithIcon.setImageByCategory(pass.getType());
        categoryIndicatorViewWithIcon.setAccentColor(pass.getAccentColor());
        ((TextView) this.f2954u.findViewById(2131296623)).setText(pass.getDescription());
    }

    public void Z(final Activity activity, final Pass pass) {
        Intrinsics.f(activity, "activity");
        Intrinsics.f(pass, "pass");
        ((TextView) this.f2955v.findViewById(2131296773)).setText(this.f2954u.getContext().getString(2131755263));
        ((TextView) this.f2955v.findViewById(2131296514)).setText(this.f2954u.getContext().getString(2131755261));
        ((TextView) this.f2955v.findViewById(2131296773)).setOnClickListener(new View.OnClickListener() { // from class: h0.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassViewHolder.a0(PassViewHolder.this, pass, view);
            }
        });
        ((TextView) this.f2955v.findViewById(2131296514)).setOnClickListener(new View.OnClickListener() { // from class: h0.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassViewHolder.b0(activity, pass, view);
            }
        });
    }
}
