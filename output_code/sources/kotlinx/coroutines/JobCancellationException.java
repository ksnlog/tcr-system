package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JobCancellationException extends CancellationException {

    /* renamed from: d  reason: collision with root package name */
    public final transient Job f988d;

    public JobCancellationException(String str, Throwable th, Job job) {
        super(str);
        this.f988d = job;
        if (th != null) {
            initCause(th);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof JobCancellationException) {
                JobCancellationException jobCancellationException = (JobCancellationException) obj;
                if (!Intrinsics.a(jobCancellationException.getMessage(), getMessage()) || !Intrinsics.a(jobCancellationException.f988d, this.f988d) || !Intrinsics.a(jobCancellationException.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        Intrinsics.c(message);
        int hashCode = ((message.hashCode() * 31) + this.f988d.hashCode()) * 31;
        Throwable cause = getCause();
        return hashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.f988d;
    }
}
