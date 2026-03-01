package permissions.dispatcher.ktx;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.ktx.PermissionRequestViewModel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PermissionsRequesterImpl implements PermissionsRequester {

    /* renamed from: a  reason: collision with root package name */
    private final String[] f3471a;

    /* renamed from: b  reason: collision with root package name */
    private final FragmentActivity f3472b;

    /* renamed from: c  reason: collision with root package name */
    private final Function1<Object, Unit> f3473c;

    /* renamed from: d  reason: collision with root package name */
    private final Function0<Unit> f3474d;

    /* renamed from: e  reason: collision with root package name */
    private final Function0<Unit> f3475e;

    /* renamed from: f  reason: collision with root package name */
    private final Function0<Unit> f3476f;

    /* renamed from: g  reason: collision with root package name */
    private final PermissionRequestType f3477g;

    public PermissionsRequesterImpl(String[] strArr, FragmentActivity fragmentActivity, Function1<Object, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function0<Unit> function03, PermissionRequestType permissionRequestType) {
        this.f3471a = strArr;
        this.f3472b = fragmentActivity;
        this.f3473c = function1;
        this.f3474d = function0;
        this.f3475e = function02;
        this.f3476f = function03;
        this.f3477g = permissionRequestType;
    }

    @Override // permissions.dispatcher.ktx.PermissionsRequester
    public void a() {
        MutableLiveData mutableLiveData;
        if (this.f3477g.a(this.f3472b, this.f3471a)) {
            this.f3475e.mo2invoke();
            return;
        }
        FragmentActivity fragmentActivity = this.f3472b;
        final Function0<Unit> function0 = this.f3475e;
        final Function0<Unit> function02 = this.f3474d;
        final Function0<Unit> function03 = this.f3476f;
        mutableLiveData = ((PermissionRequestViewModel) new ViewModelProvider(this.f3472b).a(PermissionRequestViewModel.class)).f3462d;
        mutableLiveData.e(fragmentActivity, new Observer<Event<? extends PermissionResult>>() { // from class: permissions.dispatcher.ktx.PermissionRequestViewModel$observe$1
            /* renamed from: b */
            public final void a(Event<? extends PermissionResult> event) {
                Function0 function04;
                PermissionResult a2 = event.a();
                if (a2 != null) {
                    int i2 = PermissionRequestViewModel.WhenMappings.f3463a[a2.ordinal()];
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 == 3 && (function04 = function03) != null) {
                                Unit unit = (Unit) function04.mo2invoke();
                                return;
                            }
                            return;
                        }
                        Function0 function05 = function02;
                        if (function05 != null) {
                            Unit unit2 = (Unit) function05.mo2invoke();
                            return;
                        }
                        return;
                    }
                    Function0.this.mo2invoke();
                }
            }
        });
        PermissionsRequesterImpl$launch$requestFun$1 permissionsRequesterImpl$launch$requestFun$1 = new PermissionsRequesterImpl$launch$requestFun$1(this);
        FragmentActivity fragmentActivity2 = this.f3472b;
        String[] strArr = this.f3471a;
        if (PermissionUtils.d(fragmentActivity2, (String[]) Arrays.copyOf(strArr, strArr.length))) {
            Function1<Object, Unit> function1 = this.f3473c;
            if (function1 != null) {
                function1.f(KtxPermissionRequest.f3455c.a(this.f3474d, permissionsRequesterImpl$launch$requestFun$1));
                return;
            }
            return;
        }
        permissionsRequesterImpl$launch$requestFun$1.mo2invoke();
    }
}
