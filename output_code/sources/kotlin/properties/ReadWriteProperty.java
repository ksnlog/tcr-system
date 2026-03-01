package kotlin.properties;

import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface ReadWriteProperty<T, V> {
    void a(T t2, KProperty<?> kProperty, V v2);

    V b(T t2, KProperty<?> kProperty);
}
