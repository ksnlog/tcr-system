package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineExceptionHandlerImpl_commonKt {
    public static final void a(CoroutineContext coroutineContext, Throwable th) {
        for (CoroutineExceptionHandler coroutineExceptionHandler : CoroutineExceptionHandlerImplKt.a()) {
            try {
                coroutineExceptionHandler.G(coroutineContext, th);
            } catch (Throwable th2) {
                CoroutineExceptionHandlerImplKt.b(CoroutineExceptionHandlerKt.b(th, th2));
            }
        }
        try {
            ExceptionsKt__ExceptionsKt.a(th, new DiagnosticCoroutineContextException(coroutineContext));
        } catch (Throwable unused) {
        }
        CoroutineExceptionHandlerImplKt.b(th);
    }
}
