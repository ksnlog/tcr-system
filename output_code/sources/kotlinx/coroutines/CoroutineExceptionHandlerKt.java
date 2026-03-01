package kotlinx.coroutines;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineExceptionHandlerKt {
    public static final void a(CoroutineContext coroutineContext, Throwable th) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.b(CoroutineExceptionHandler.f944b);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.G(coroutineContext, th);
            } else {
                CoroutineExceptionHandlerImpl_commonKt.a(coroutineContext, th);
            }
        } catch (Throwable th2) {
            CoroutineExceptionHandlerImpl_commonKt.a(coroutineContext, b(th, th2));
        }
    }

    public static final Throwable b(Throwable th, Throwable th2) {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        ExceptionsKt__ExceptionsKt.a(runtimeException, th);
        return runtimeException;
    }
}
