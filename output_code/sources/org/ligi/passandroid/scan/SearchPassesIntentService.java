package org.ligi.passandroid.scan;

import android.app.NotificationManager;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.scan.events.PassScanEventChannelProvider;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SearchPassesIntentService extends LifecycleService {

    /* renamed from: n  reason: collision with root package name */
    public static final Companion f2689n = new Companion(null);

    /* renamed from: e  reason: collision with root package name */
    private final Lazy f2690e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2691f;

    /* renamed from: g  reason: collision with root package name */
    private NotificationCompat.Builder f2692g;

    /* renamed from: h  reason: collision with root package name */
    private NotificationCompat.Builder f2693h;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList<Pass> f2694i;

    /* renamed from: j  reason: collision with root package name */
    private long f2695j;

    /* renamed from: k  reason: collision with root package name */
    private final Lazy f2696k;

    /* renamed from: l  reason: collision with root package name */
    private final Lazy f2697l;

    /* renamed from: m  reason: collision with root package name */
    private final Lazy f2698m;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchPassesIntentService() {
        Lazy b2;
        Lazy a2;
        Lazy a3;
        Lazy a4;
        b2 = LazyKt__LazyJVMKt.b(new SearchPassesIntentService$notifyManager$2(this));
        this.f2690e = b2;
        this.f2694i = new ArrayList<>();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        a2 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new SearchPassesIntentService$special$$inlined$inject$default$1(this, null, null));
        this.f2696k = a2;
        a3 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new SearchPassesIntentService$special$$inlined$inject$default$2(this, null, null));
        this.f2697l = a3;
        a4 = LazyKt__LazyJVMKt.a(lazyThreadSafetyMode, new SearchPassesIntentService$special$$inlined$inject$default$3(this, null, null));
        this.f2698m = a4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationManager k() {
        return (NotificationManager) this.f2690e.getValue();
    }

    private final PassStore l() {
        return (PassStore) this.f2696k.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PassScanEventChannelProvider m() {
        return (PassScanEventChannelProvider) this.f2698m.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Tracker n() {
        return (Tracker) this.f2697l.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:26|(1:28)|32|33|34|30|31|12|(2:38|39)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x015a, code lost:
        if (r6 != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01f2, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01f3, code lost:
        r12.n().a("Error in SearchPassesIntentService", r0, false);
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0208  */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [org.ligi.passandroid.scan.SearchPassesIntentService, java.lang.Object, android.content.ContextWrapper] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0116 -> B:45:0x0119). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x01fd -> B:58:0x0200). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object o(java.io.File r28, boolean r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r30) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.scan.SearchPassesIntentService.o(java.io.File, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void onDestroy() {
        this.f2691f = true;
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        BuildersKt__Builders_commonKt.b(LifecycleOwnerKt.a(this), null, null, new SearchPassesIntentService$onStartCommand$1(this, null), 3, null);
        return 1;
    }
}
