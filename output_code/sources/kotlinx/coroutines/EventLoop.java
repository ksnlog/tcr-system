package kotlinx.coroutines;

import kotlin.collections.ArrayDeque;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class EventLoop extends CoroutineDispatcher {

    /* renamed from: f  reason: collision with root package name */
    private long f964f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f965g;

    /* renamed from: h  reason: collision with root package name */
    private ArrayDeque<DispatchedTask<?>> f966h;

    private final long U(boolean z2) {
        return z2 ? 4294967296L : 1L;
    }

    public static /* synthetic */ void Y(EventLoop eventLoop, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                z2 = false;
            }
            eventLoop.X(z2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
    }

    public final void T(boolean z2) {
        long U = this.f964f - U(z2);
        this.f964f = U;
        if (U <= 0 && this.f965g) {
            shutdown();
        }
    }

    public final void V(DispatchedTask<?> dispatchedTask) {
        ArrayDeque<DispatchedTask<?>> arrayDeque = this.f966h;
        if (arrayDeque == null) {
            arrayDeque = new ArrayDeque<>();
            this.f966h = arrayDeque;
        }
        arrayDeque.addLast(dispatchedTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long W() {
        ArrayDeque<DispatchedTask<?>> arrayDeque = this.f966h;
        if (arrayDeque == null || arrayDeque.isEmpty()) {
            return Long.MAX_VALUE;
        }
        return 0L;
    }

    public final void X(boolean z2) {
        this.f964f += U(z2);
        if (!z2) {
            this.f965g = true;
        }
    }

    public final boolean Z() {
        return this.f964f >= U(true);
    }

    public final boolean a0() {
        ArrayDeque<DispatchedTask<?>> arrayDeque = this.f966h;
        if (arrayDeque != null) {
            return arrayDeque.isEmpty();
        }
        return true;
    }

    public final boolean b0() {
        DispatchedTask<?> k2;
        ArrayDeque<DispatchedTask<?>> arrayDeque = this.f966h;
        if (arrayDeque == null || (k2 = arrayDeque.k()) == null) {
            return false;
        }
        k2.run();
        return true;
    }

    public void shutdown() {
    }
}
