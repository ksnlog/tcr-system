package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class JobKt__JobKt {
    public static final void a(CoroutineContext coroutineContext, CancellationException cancellationException) {
        Job job = (Job) coroutineContext.b(Job.f986c);
        if (job != null) {
            job.D(cancellationException);
        }
    }

    public static /* synthetic */ void b(CoroutineContext coroutineContext, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        JobKt.a(coroutineContext, cancellationException);
    }

    public static final void c(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.b(Job.f986c);
        if (job != null) {
            JobKt.d(job);
        }
    }

    public static final void d(Job job) {
        if (job.c()) {
            return;
        }
        throw job.z();
    }
}
