package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class MainDispatcherLoader {

    /* renamed from: a  reason: collision with root package name */
    public static final MainDispatcherLoader f1138a;

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f1139b;

    /* renamed from: c  reason: collision with root package name */
    public static final MainCoroutineDispatcher f1140c;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        f1138a = mainDispatcherLoader;
        f1139b = SystemPropsKt.f("kotlinx.coroutines.fast.service.loader", true);
        f1140c = mainDispatcherLoader.a();
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher a() {
        Sequence c2;
        List<MainDispatcherFactory> f2;
        Object next;
        MainCoroutineDispatcher e2;
        try {
            if (f1139b) {
                f2 = FastServiceLoader.f1112a.c();
            } else {
                c2 = SequencesKt__SequencesKt.c(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator());
                f2 = SequencesKt___SequencesKt.f(c2);
            }
            Iterator<T> it = f2.iterator();
            if (!it.hasNext()) {
                next = null;
            } else {
                next = it.next();
                if (it.hasNext()) {
                    int c3 = ((MainDispatcherFactory) next).c();
                    do {
                        Object next2 = it.next();
                        int c4 = ((MainDispatcherFactory) next2).c();
                        if (c3 < c4) {
                            next = next2;
                            c3 = c4;
                        }
                    } while (it.hasNext());
                }
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            if (mainDispatcherFactory == null || (e2 = MainDispatchersKt.e(mainDispatcherFactory, f2)) == null) {
                return MainDispatchersKt.b(null, null, 3, null);
            }
            return e2;
        } catch (Throwable th) {
            return MainDispatchersKt.b(th, null, 2, null);
        }
    }
}
