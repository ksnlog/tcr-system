package kotlinx.coroutines.internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface ThreadSafeHeapNode {
    void b(ThreadSafeHeap<?> threadSafeHeap);

    ThreadSafeHeap<?> c();

    int getIndex();

    void setIndex(int i2);
}
