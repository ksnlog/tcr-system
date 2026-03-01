package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface ThreadContextElement<S> extends CoroutineContext.Element {
    S J(CoroutineContext coroutineContext);

    void r(CoroutineContext coroutineContext, S s2);
}
