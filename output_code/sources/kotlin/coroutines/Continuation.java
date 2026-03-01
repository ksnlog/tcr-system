package kotlin.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Continuation<T> {
    CoroutineContext getContext();

    void resumeWith(Object obj);
}
