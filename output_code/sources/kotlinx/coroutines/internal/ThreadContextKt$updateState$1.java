package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ThreadContextKt$updateState$1 extends Lambda implements Function2<ThreadState, CoroutineContext.Element, ThreadState> {

    /* renamed from: d  reason: collision with root package name */
    public static final ThreadContextKt$updateState$1 f1162d = new ThreadContextKt$updateState$1();

    ThreadContextKt$updateState$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: a */
    public final ThreadState invoke(ThreadState threadState, CoroutineContext.Element element) {
        if (element instanceof ThreadContextElement) {
            ThreadContextElement<?> threadContextElement = (ThreadContextElement) element;
            threadState.a(threadContextElement, threadContextElement.J(threadState.f1165a));
        }
        return threadState;
    }
}
