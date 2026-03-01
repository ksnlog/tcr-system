package kotlinx.coroutines.internal;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineExceptionHandlerImplKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Collection<CoroutineExceptionHandler> f1103a;

    static {
        Sequence c2;
        List f2;
        c2 = SequencesKt__SequencesKt.c(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator());
        f2 = SequencesKt___SequencesKt.f(c2);
        f1103a = f2;
    }

    public static final Collection<CoroutineExceptionHandler> a() {
        return f1103a;
    }

    public static final void b(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }
}
