package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class OnUndeliveredElementKt {
    public static final <E> Function1<Throwable, Unit> a(Function1<? super E, Unit> function1, E e2, CoroutineContext coroutineContext) {
        return new OnUndeliveredElementKt$bindCancellationFun$1(function1, e2, coroutineContext);
    }

    public static final <E> void b(Function1<? super E, Unit> function1, E e2, CoroutineContext coroutineContext) {
        UndeliveredElementException c2 = c(function1, e2, null);
        if (c2 != null) {
            CoroutineExceptionHandlerKt.a(coroutineContext, c2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> UndeliveredElementException c(Function1<? super E, Unit> function1, E e2, UndeliveredElementException undeliveredElementException) {
        try {
            function1.f(e2);
        } catch (Throwable th) {
            if (undeliveredElementException != null && undeliveredElementException.getCause() != th) {
                ExceptionsKt__ExceptionsKt.a(undeliveredElementException, th);
            } else {
                return new UndeliveredElementException("Exception in undelivered element handler for " + e2, th);
            }
        }
        return undeliveredElementException;
    }

    public static /* synthetic */ UndeliveredElementException d(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            undeliveredElementException = null;
        }
        return c(function1, obj, undeliveredElementException);
    }
}
