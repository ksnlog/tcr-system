package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface ChannelIterator<E> {
    Object a(Continuation<? super Boolean> continuation);

    E next();
}
