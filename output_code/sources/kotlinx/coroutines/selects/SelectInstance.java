package kotlinx.coroutines.selects;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface SelectInstance<R> {
    boolean a(Object obj, Object obj2);

    CoroutineContext getContext();
}
