package permissions.dispatcher.ktx;

import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KtxPermissionRequest {

    /* renamed from: c  reason: collision with root package name */
    public static final Companion f3455c = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Function0<Unit>> f3456a;

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference<Function0<Unit>> f3457b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KtxPermissionRequest a(Function0<Unit> function0, Function0<Unit> function02) {
            WeakReference weakReference;
            WeakReference weakReference2 = new WeakReference(function02);
            if (function0 != null) {
                weakReference = new WeakReference(function0);
            } else {
                weakReference = null;
            }
            return new KtxPermissionRequest(weakReference2, weakReference);
        }
    }

    public KtxPermissionRequest(WeakReference<Function0<Unit>> weakReference, WeakReference<Function0<Unit>> weakReference2) {
        this.f3456a = weakReference;
        this.f3457b = weakReference2;
    }
}
