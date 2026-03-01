package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DefaultExecutorKt {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f954a = SystemPropsKt.f("kotlinx.coroutines.main.delay", false);

    /* renamed from: b  reason: collision with root package name */
    private static final Delay f955b = b();

    public static final Delay a() {
        return f955b;
    }

    private static final Delay b() {
        if (!f954a) {
            return DefaultExecutor.f952l;
        }
        MainCoroutineDispatcher c2 = Dispatchers.c();
        if (!MainDispatchersKt.c(c2) && (c2 instanceof Delay)) {
            return (Delay) c2;
        }
        return DefaultExecutor.f952l;
    }
}
