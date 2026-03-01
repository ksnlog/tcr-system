package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BlockingEventLoop extends EventLoopImplBase {

    /* renamed from: l  reason: collision with root package name */
    private final Thread f919l;

    public BlockingEventLoop(Thread thread) {
        this.f919l = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread c0() {
        return this.f919l;
    }
}
