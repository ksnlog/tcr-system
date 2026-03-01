package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EventLoopKt {
    public static final EventLoop a() {
        return new BlockingEventLoop(Thread.currentThread());
    }
}
