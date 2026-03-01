package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface CancellableContinuation<T> extends Continuation<T> {
    Object a(T t2, Object obj, Function1<? super Throwable, Unit> function1);

    void d(CoroutineDispatcher coroutineDispatcher, T t2);

    void g(Function1<? super Throwable, Unit> function1);

    void h(Object obj);
}
