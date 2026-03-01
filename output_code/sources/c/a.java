package c;

import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class a implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        ArchTaskExecutor.e(runnable);
    }
}
