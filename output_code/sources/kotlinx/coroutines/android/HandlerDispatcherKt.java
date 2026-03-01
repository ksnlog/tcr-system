package kotlinx.coroutines.android;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HandlerDispatcherKt {

    /* renamed from: a  reason: collision with root package name */
    public static final HandlerDispatcher f1027a;
    private static volatile Choreographer choreographer;

    static {
        Object a2;
        HandlerDispatcher handlerDispatcher = null;
        try {
            Result.Companion companion = Result.f752d;
            a2 = Result.a(new HandlerContext(a(Looper.getMainLooper(), true), null, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.f752d;
            a2 = Result.a(ResultKt.a(th));
        }
        if (!Result.c(a2)) {
            handlerDispatcher = a2;
        }
        f1027a = handlerDispatcher;
    }

    public static final Handler a(Looper looper, boolean z2) {
        int i2;
        if (z2 && (i2 = Build.VERSION.SDK_INT) >= 16) {
            if (i2 >= 28) {
                Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
                Intrinsics.d(invoke, "null cannot be cast to non-null type android.os.Handler");
                return (Handler) invoke;
            }
            try {
                return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
            } catch (NoSuchMethodException unused) {
                return new Handler(looper);
            }
        }
        return new Handler(looper);
    }
}
