package kotlin.collections;

import java.util.List;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractMutableList<E> extends java.util.AbstractList<E> implements List<E> {
    public abstract int a();

    public abstract E b(int i2);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int i2) {
        return b(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return a();
    }
}
