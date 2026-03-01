package org.ligi.passandroid.ui.edit.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.functions.CategoryHelperKt;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassType;
import org.ligi.passandroid.ui.edit.dialogs.CategoryPickDialogKt;
import org.ligi.passandroid.ui.views.BaseCategoryIndicatorView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CategoryPickDialogKt {

    /* renamed from: a  reason: collision with root package name */
    private static final PassType[] f2949a = {PassType.BOARDING, PassType.EVENT, PassType.GENERIC, PassType.LOYALTY, PassType.VOUCHER, PassType.COUPON};

    public static final void c(final Context context, final Pass pass, final Function0<Unit> refreshCallback) {
        Intrinsics.f(context, "context");
        Intrinsics.f(pass, "pass");
        Intrinsics.f(refreshCallback, "refreshCallback");
        BaseAdapter baseAdapter = new BaseAdapter() { // from class: org.ligi.passandroid.ui.edit.dialogs.CategoryPickDialogKt$showCategoryPickDialog$adapter$1
            @Override // android.widget.Adapter
            /* renamed from: a */
            public PassType getItem(int i2) {
                PassType[] passTypeArr;
                passTypeArr = CategoryPickDialogKt.f2949a;
                return passTypeArr[i2];
            }

            @Override // android.widget.Adapter
            public int getCount() {
                PassType[] passTypeArr;
                passTypeArr = CategoryPickDialogKt.f2949a;
                return passTypeArr.length;
            }

            @Override // android.widget.Adapter
            public long getItemId(int i2) {
                return i2;
            }

            @Override // android.widget.Adapter
            public View getView(int i2, View view, ViewGroup parent) {
                Intrinsics.f(parent, "parent");
                View inflate = LayoutInflater.from(context).inflate(2131492927, parent, false);
                View findViewById = inflate.findViewById(2131296363);
                Intrinsics.d(findViewById, "null cannot be cast to non-null type org.ligi.passandroid.ui.views.BaseCategoryIndicatorView");
                BaseCategoryIndicatorView baseCategoryIndicatorView = (BaseCategoryIndicatorView) findViewById;
                PassType item = getItem(i2);
                baseCategoryIndicatorView.setImageByCategory(item);
                baseCategoryIndicatorView.setAccentColor(CategoryHelperKt.a(item));
                View findViewById2 = inflate.findViewById(2131296594);
                Intrinsics.d(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById2).setText(CategoryHelperKt.c(item));
                Intrinsics.e(inflate, "inflate");
                return inflate;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.c(baseAdapter, new DialogInterface.OnClickListener() { // from class: g0.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                CategoryPickDialogKt.d(Pass.this, refreshCallback, dialogInterface, i2);
            }
        });
        builder.v(2131755301);
        builder.m(17039360, (DialogInterface.OnClickListener) null);
        builder.z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(Pass pass, Function0 refreshCallback, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(pass, "$pass");
        Intrinsics.f(refreshCallback, "$refreshCallback");
        pass.setType(f2949a[i2]);
        refreshCallback.mo2invoke();
    }
}
