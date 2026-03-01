package i;

import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.Violation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f741d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Violation f742e;

    public /* synthetic */ a(String str, Violation violation) {
        this.f741d = str;
        this.f742e = violation;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FragmentStrictMode.a(this.f741d, this.f742e);
    }
}
