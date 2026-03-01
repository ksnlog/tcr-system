package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.EventLoopImplBase;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    protected abstract Thread c0();

    /* JADX INFO: Access modifiers changed from: protected */
    public void d0(long j2, EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.f952l.p0(j2, delayedTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e0() {
        Thread c02 = c0();
        if (Thread.currentThread() != c02) {
            AbstractTimeSourceKt.a();
            LockSupport.unpark(c02);
        }
    }
}
