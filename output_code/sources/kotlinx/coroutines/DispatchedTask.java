package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class DispatchedTask<T> extends Task {

    /* renamed from: f  reason: collision with root package name */
    public int f957f;

    public DispatchedTask(int i2) {
        this.f957f = i2;
    }

    public void b(Object obj, Throwable th) {
    }

    public abstract Continuation<T> e();

    public Throwable f(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.f935a;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T i(Object obj) {
        return obj;
    }

    public final void j(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            ExceptionsKt__ExceptionsKt.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        Intrinsics.c(th);
        CoroutineExceptionHandlerKt.a(e().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object k();

    @Override // java.lang.Runnable
    public final void run() {
        Object a2;
        UndispatchedCoroutine<?> undispatchedCoroutine;
        Job job;
        Object a3;
        TaskContext taskContext = this.f1207e;
        try {
            Continuation<T> e2 = e();
            Intrinsics.d(e2, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) e2;
            Continuation<T> continuation = dispatchedContinuation.f1107h;
            Object obj = dispatchedContinuation.f1109j;
            CoroutineContext context = continuation.getContext();
            Object c2 = ThreadContextKt.c(context, obj);
            if (c2 != ThreadContextKt.f1156a) {
                undispatchedCoroutine = CoroutineContextKt.g(continuation, context, c2);
            } else {
                undispatchedCoroutine = null;
            }
            CoroutineContext context2 = continuation.getContext();
            Object k2 = k();
            Throwable f2 = f(k2);
            if (f2 == null && DispatchedTaskKt.b(this.f957f)) {
                job = (Job) context2.b(Job.f986c);
            } else {
                job = null;
            }
            if (job != null && !job.c()) {
                CancellationException z2 = job.z();
                b(k2, z2);
                Result.Companion companion = Result.f752d;
                continuation.resumeWith(Result.a(ResultKt.a(z2)));
            } else if (f2 != null) {
                Result.Companion companion2 = Result.f752d;
                continuation.resumeWith(Result.a(ResultKt.a(f2)));
            } else {
                Result.Companion companion3 = Result.f752d;
                continuation.resumeWith(Result.a(i(k2)));
            }
            Unit unit = Unit.f763a;
            if (undispatchedCoroutine == null || undispatchedCoroutine.B0()) {
                ThreadContextKt.a(context, c2);
            }
            try {
                taskContext.a();
                a3 = Result.a(Unit.f763a);
            } catch (Throwable th) {
                Result.Companion companion4 = Result.f752d;
                a3 = Result.a(ResultKt.a(th));
            }
            j(null, Result.b(a3));
        } catch (Throwable th2) {
            try {
                Result.Companion companion5 = Result.f752d;
                taskContext.a();
                a2 = Result.a(Unit.f763a);
            } catch (Throwable th3) {
                Result.Companion companion6 = Result.f752d;
                a2 = Result.a(ResultKt.a(th3));
            }
            j(th2, Result.b(a2));
        }
    }
}
