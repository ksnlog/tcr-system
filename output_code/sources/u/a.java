package u;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.sidesheet.SideSheetBehavior;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class a implements AccessibilityViewCommand {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SideSheetBehavior f3483a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f3484b;

    public /* synthetic */ a(SideSheetBehavior sideSheetBehavior, int i2) {
        this.f3483a = sideSheetBehavior;
        this.f3484b = i2;
    }

    public final boolean a(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        return SideSheetBehavior.F(this.f3483a, this.f3484b, view, commandArguments);
    }
}
