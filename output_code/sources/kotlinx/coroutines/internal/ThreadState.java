package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ThreadState {

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineContext f1165a;

    /* renamed from: b  reason: collision with root package name */
    private final Object[] f1166b;

    /* renamed from: c  reason: collision with root package name */
    private final ThreadContextElement<Object>[] f1167c;

    /* renamed from: d  reason: collision with root package name */
    private int f1168d;

    public ThreadState(CoroutineContext coroutineContext, int i2) {
        this.f1165a = coroutineContext;
        this.f1166b = new Object[i2];
        this.f1167c = new ThreadContextElement[i2];
    }

    public final void a(ThreadContextElement<?> threadContextElement, Object obj) {
        Object[] objArr = this.f1166b;
        int i2 = this.f1168d;
        objArr[i2] = obj;
        ThreadContextElement<Object>[] threadContextElementArr = this.f1167c;
        this.f1168d = i2 + 1;
        Intrinsics.d(threadContextElement, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        threadContextElementArr[i2] = threadContextElement;
    }

    public final void b(CoroutineContext coroutineContext) {
        int length = this.f1167c.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                ThreadContextElement<Object> threadContextElement = this.f1167c[length];
                Intrinsics.c(threadContextElement);
                threadContextElement.r(coroutineContext, this.f1166b[length]);
                if (i2 >= 0) {
                    length = i2;
                } else {
                    return;
                }
            }
        }
    }
}
