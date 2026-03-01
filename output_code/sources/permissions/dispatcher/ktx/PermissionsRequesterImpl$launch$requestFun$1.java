package permissions.dispatcher.ktx;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PermissionsRequesterImpl$launch$requestFun$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PermissionsRequesterImpl f3478d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionsRequesterImpl$launch$requestFun$1(PermissionsRequesterImpl permissionsRequesterImpl) {
        super(0);
        this.f3478d = permissionsRequesterImpl;
    }

    public final void a() {
        FragmentActivity fragmentActivity;
        PermissionRequestType permissionRequestType;
        String[] strArr;
        fragmentActivity = this.f3478d.f3472b;
        FragmentTransaction p2 = fragmentActivity.Q().p();
        permissionRequestType = this.f3478d.f3477g;
        strArr = this.f3478d.f3471a;
        p2.p(16908290, permissionRequestType.b(strArr)).k();
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
