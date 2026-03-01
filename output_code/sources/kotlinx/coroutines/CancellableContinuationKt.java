package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CancellableContinuationKt {
    public static final void a(CancellableContinuation<?> cancellableContinuation, DisposableHandle disposableHandle) {
        cancellableContinuation.g(new DisposeOnCancel(disposableHandle));
    }

    public static final <T> CancellableContinuationImpl<T> b(Continuation<? super T> continuation) {
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl<>(continuation, 1);
        }
        CancellableContinuationImpl<T> m2 = ((DispatchedContinuation) continuation).m();
        if (m2 != null) {
            if (!m2.J()) {
                m2 = null;
            }
            if (m2 != null) {
                return m2;
            }
        }
        return new CancellableContinuationImpl<>(continuation, 2);
    }
}
