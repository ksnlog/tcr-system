package permissions.dispatcher.ktx;

import androidx.fragment.app.FragmentActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import permissions.dispatcher.ktx.PermissionRequestType;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ActivityExtensionsKt {
    public static final PermissionsRequester a(FragmentActivity fragmentActivity, String[] strArr, Function1<Object, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function0<Unit> function03) {
        return new PermissionsRequesterImpl(strArr, fragmentActivity, function1, function0, function03, function02, PermissionRequestType.Normal.f3461a);
    }
}
