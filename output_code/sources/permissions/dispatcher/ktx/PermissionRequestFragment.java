package permissions.dispatcher.ktx;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Arrays;
import java.util.Random;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionUtils;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class PermissionRequestFragment extends Fragment {

    /* renamed from: g0  reason: collision with root package name */
    private final int f3458g0;

    /* renamed from: h0  reason: collision with root package name */
    protected PermissionRequestViewModel f3459h0;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class NormalRequestPermissionFragment extends PermissionRequestFragment {

        /* renamed from: i0  reason: collision with root package name */
        public static final Companion f3460i0 = new Companion(null);

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final NormalRequestPermissionFragment a(String[] strArr) {
                NormalRequestPermissionFragment normalRequestPermissionFragment = new NormalRequestPermissionFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("key:permissions", strArr);
                normalRequestPermissionFragment.A1(bundle);
                return normalRequestPermissionFragment;
            }
        }

        public NormalRequestPermissionFragment() {
            super(null);
        }

        public void L0(int i2, String[] strArr, int[] iArr) {
            super.L0(i2, strArr, iArr);
            if (i2 == S1()) {
                if (PermissionUtils.f(Arrays.copyOf(iArr, iArr.length))) {
                    T1().g(PermissionResult.GRANTED);
                } else if (!PermissionUtils.e(this, (String[]) Arrays.copyOf(strArr, strArr.length))) {
                    T1().g(PermissionResult.DENIED_AND_DISABLED);
                } else {
                    T1().g(PermissionResult.DENIED);
                }
            }
            R1();
        }

        public void r0(Bundle bundle) {
            String[] stringArray;
            super.r0(bundle);
            Bundle s2 = s();
            if (s2 != null && (stringArray = s2.getStringArray("key:permissions")) != null) {
                Intrinsics.b(stringArray, "arguments?.getStringArra…ERMISSIONS_KEY) ?: return");
                r1(stringArray, S1());
            }
        }
    }

    private PermissionRequestFragment() {
        this.f3458g0 = new Random().nextInt(1000);
    }

    protected final Unit R1() {
        FragmentTransaction p2;
        FragmentTransaction o2;
        FragmentManager D = D();
        if (D == null || (p2 = D.p()) == null || (o2 = p2.o(this)) == null) {
            return null;
        }
        o2.k();
        return Unit.f763a;
    }

    protected final int S1() {
        return this.f3458g0;
    }

    protected final PermissionRequestViewModel T1() {
        PermissionRequestViewModel permissionRequestViewModel = this.f3459h0;
        if (permissionRequestViewModel == null) {
            Intrinsics.p("viewModel");
        }
        return permissionRequestViewModel;
    }

    public void o0(Context context) {
        super.o0(context);
        H1(true);
        ViewModel a2 = new ViewModelProvider(s1()).a(PermissionRequestViewModel.class);
        Intrinsics.b(a2, "ViewModelProvider(requir…estViewModel::class.java)");
        this.f3459h0 = (PermissionRequestViewModel) a2;
    }

    public /* synthetic */ PermissionRequestFragment(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
