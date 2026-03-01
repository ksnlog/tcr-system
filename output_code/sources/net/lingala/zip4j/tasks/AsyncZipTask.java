package net.lingala.zip4j.tasks;

import java.util.concurrent.ExecutorService;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AsyncZipTask<T> {

    /* renamed from: a  reason: collision with root package name */
    private final ProgressMonitor f1700a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f1701b;

    /* renamed from: c  reason: collision with root package name */
    private final ExecutorService f1702c;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class AsyncTaskParameters {

        /* renamed from: a  reason: collision with root package name */
        private final ProgressMonitor f1705a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f1706b;

        /* renamed from: c  reason: collision with root package name */
        private final ExecutorService f1707c;

        public AsyncTaskParameters(ExecutorService executorService, boolean z2, ProgressMonitor progressMonitor) {
            this.f1707c = executorService;
            this.f1706b = z2;
            this.f1705a = progressMonitor;
        }
    }

    public AsyncZipTask(AsyncTaskParameters asyncTaskParameters) {
        this.f1700a = asyncTaskParameters.f1705a;
        this.f1701b = asyncTaskParameters.f1706b;
        this.f1702c = asyncTaskParameters.f1707c;
    }

    private void h() {
        this.f1700a.c();
        this.f1700a.j(ProgressMonitor.State.BUSY);
        this.f1700a.g(g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(T t2, ProgressMonitor progressMonitor) {
        try {
            f(t2, progressMonitor);
            progressMonitor.a();
        } catch (ZipException e2) {
            progressMonitor.b(e2);
            throw e2;
        } catch (Exception e3) {
            progressMonitor.b(e3);
            throw new ZipException(e3);
        }
    }

    protected abstract long d(T t2);

    public void e(final T t2) {
        if (this.f1701b && ProgressMonitor.State.BUSY.equals(this.f1700a.d())) {
            throw new ZipException("invalid operation - Zip4j is in busy state");
        }
        h();
        if (this.f1701b) {
            this.f1700a.k(d(t2));
            this.f1702c.execute(new Runnable() { // from class: net.lingala.zip4j.tasks.AsyncZipTask.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AsyncZipTask asyncZipTask = AsyncZipTask.this;
                        asyncZipTask.i(t2, asyncZipTask.f1700a);
                    } catch (ZipException unused) {
                    } catch (Throwable th) {
                        AsyncZipTask.this.f1702c.shutdown();
                        throw th;
                    }
                    AsyncZipTask.this.f1702c.shutdown();
                }
            });
            return;
        }
        i(t2, this.f1700a);
    }

    protected abstract void f(T t2, ProgressMonitor progressMonitor);

    protected abstract ProgressMonitor.Task g();

    /* JADX INFO: Access modifiers changed from: protected */
    public void j() {
        if (!this.f1700a.e()) {
            return;
        }
        this.f1700a.i(ProgressMonitor.Result.CANCELLED);
        this.f1700a.j(ProgressMonitor.State.READY);
        throw new ZipException("Task cancelled", ZipException.Type.TASK_CANCELLED_EXCEPTION);
    }
}
