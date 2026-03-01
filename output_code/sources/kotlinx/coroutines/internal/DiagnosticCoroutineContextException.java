package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {

    /* renamed from: d  reason: collision with root package name */
    private final transient CoroutineContext f1104d;

    public DiagnosticCoroutineContextException(CoroutineContext coroutineContext) {
        this.f1104d = coroutineContext;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.f1104d.toString();
    }
}
