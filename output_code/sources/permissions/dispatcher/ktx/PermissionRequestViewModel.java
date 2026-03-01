package permissions.dispatcher.ktx;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PermissionRequestViewModel extends ViewModel {

    /* renamed from: d  reason: collision with root package name */
    private final MutableLiveData<Event<PermissionResult>> f3462d = new MutableLiveData<>();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3463a;

        static {
            int[] iArr = new int[PermissionResult.values().length];
            f3463a = iArr;
            iArr[PermissionResult.GRANTED.ordinal()] = 1;
            iArr[PermissionResult.DENIED.ordinal()] = 2;
            iArr[PermissionResult.DENIED_AND_DISABLED.ordinal()] = 3;
        }
    }

    public final void g(PermissionResult permissionResult) {
        this.f3462d.i(new Event(permissionResult));
    }
}
