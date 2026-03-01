package u;

import com.google.android.material.sidesheet.SideSheetBehavior;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class b implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ SideSheetBehavior f3485d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ int f3486e;

    public /* synthetic */ b(SideSheetBehavior sideSheetBehavior, int i2) {
        this.f3485d = sideSheetBehavior;
        this.f3486e = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SideSheetBehavior.E(this.f3485d, this.f3486e);
    }
}
