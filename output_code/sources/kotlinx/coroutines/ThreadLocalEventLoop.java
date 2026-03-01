package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadLocalKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ThreadLocalEventLoop {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocalEventLoop f1012a = new ThreadLocalEventLoop();

    /* renamed from: b  reason: collision with root package name */
    private static final ThreadLocal<EventLoop> f1013b = ThreadLocalKt.a(new Symbol("ThreadLocalEventLoop"));

    private ThreadLocalEventLoop() {
    }

    public final EventLoop a() {
        ThreadLocal<EventLoop> threadLocal = f1013b;
        EventLoop eventLoop = threadLocal.get();
        if (eventLoop == null) {
            EventLoop a2 = EventLoopKt.a();
            threadLocal.set(a2);
            return a2;
        }
        return eventLoop;
    }

    public final void b() {
        f1013b.set(null);
    }

    public final void c(EventLoop eventLoop) {
        f1013b.set(eventLoop);
    }
}
