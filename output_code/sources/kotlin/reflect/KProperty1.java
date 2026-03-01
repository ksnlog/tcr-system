package kotlin.reflect;

import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface KProperty1<T, V> extends KProperty<V>, Function1<T, V> {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Getter<T, V> extends KFunction, Function1<T, V> {
    }

    Getter<T, V> e();

    V get(T t2);
}
