package permissions.dispatcher.ktx;

import android.content.Context;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.ktx.PermissionRequestFragment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class PermissionRequestType {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Normal extends PermissionRequestType {

        /* renamed from: a  reason: collision with root package name */
        public static final Normal f3461a = new Normal();

        private Normal() {
            super(null);
        }

        @Override // permissions.dispatcher.ktx.PermissionRequestType
        public boolean a(Context context, String[] strArr) {
            return PermissionUtils.b(context, (String[]) Arrays.copyOf(strArr, strArr.length));
        }

        @Override // permissions.dispatcher.ktx.PermissionRequestType
        public PermissionRequestFragment b(String[] strArr) {
            return PermissionRequestFragment.NormalRequestPermissionFragment.f3460i0.a(strArr);
        }
    }

    private PermissionRequestType() {
    }

    public /* synthetic */ PermissionRequestType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean a(Context context, String[] strArr);

    public abstract PermissionRequestFragment b(String[] strArr);
}
