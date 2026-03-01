package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {
    CoroutineContext m(CoroutineContext.Element element);

    CopyableThreadContextElement<S> o();
}
