package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Dispatchers {

    /* renamed from: a  reason: collision with root package name */
    public static final Dispatchers f958a = new Dispatchers();

    /* renamed from: b  reason: collision with root package name */
    private static final CoroutineDispatcher f959b = DefaultScheduler.f1199l;

    /* renamed from: c  reason: collision with root package name */
    private static final CoroutineDispatcher f960c = Unconfined.f1014f;

    /* renamed from: d  reason: collision with root package name */
    private static final CoroutineDispatcher f961d = DefaultIoScheduler.f1197g;

    private Dispatchers() {
    }

    public static final CoroutineDispatcher a() {
        return f959b;
    }

    public static final CoroutineDispatcher b() {
        return f961d;
    }

    public static final MainCoroutineDispatcher c() {
        return MainDispatcherLoader.f1140c;
    }
}
