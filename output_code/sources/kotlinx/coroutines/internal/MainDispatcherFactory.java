package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.MainCoroutineDispatcher;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface MainDispatcherFactory {
    String a();

    MainCoroutineDispatcher b(List<? extends MainDispatcherFactory> list);

    int c();
}
