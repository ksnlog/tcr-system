package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class BuildersKt__Builders_commonKt {
    public static final Job a(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        AbstractCoroutine standaloneCoroutine;
        CoroutineContext e2 = CoroutineContextKt.e(coroutineScope, coroutineContext);
        if (coroutineStart.c()) {
            standaloneCoroutine = new LazyStandaloneCoroutine(e2, function2);
        } else {
            standaloneCoroutine = new StandaloneCoroutine(e2, true);
        }
        standaloneCoroutine.A0(coroutineStart, standaloneCoroutine, function2);
        return standaloneCoroutine;
    }

    public static /* synthetic */ Job b(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.f802d;
        }
        if ((i2 & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.a(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final <T> Object c(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        Object B0;
        Object c2;
        CoroutineContext context = continuation.getContext();
        CoroutineContext d2 = CoroutineContextKt.d(context, coroutineContext);
        JobKt.c(d2);
        if (d2 == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(d2, continuation);
            B0 = UndispatchedKt.b(scopeCoroutine, scopeCoroutine, function2);
        } else {
            ContinuationInterceptor.Key key = ContinuationInterceptor.f799a;
            if (Intrinsics.a(d2.b(key), context.b(key))) {
                UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(d2, continuation);
                CoroutineContext context2 = undispatchedCoroutine.getContext();
                Object c3 = ThreadContextKt.c(context2, null);
                try {
                    Object b2 = UndispatchedKt.b(undispatchedCoroutine, undispatchedCoroutine, function2);
                    ThreadContextKt.a(context2, c3);
                    B0 = b2;
                } catch (Throwable th) {
                    ThreadContextKt.a(context2, c3);
                    throw th;
                }
            } else {
                DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(d2, continuation);
                CancellableKt.d(function2, dispatchedCoroutine, dispatchedCoroutine, null, 4, null);
                B0 = dispatchedCoroutine.B0();
            }
        }
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        if (B0 == c2) {
            DebugProbesKt.c(continuation);
        }
        return B0;
    }
}
