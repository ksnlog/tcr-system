package com.nineoldandroids.util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Property<T, V> {

    /* renamed from: a  reason: collision with root package name */
    private final String f336a;

    /* renamed from: b  reason: collision with root package name */
    private final Class<V> f337b;

    public Property(Class<V> cls, String str) {
        this.f336a = str;
        this.f337b = cls;
    }

    public abstract V a(T t2);

    public String b() {
        return this.f336a;
    }

    public void c(T t2, V v2) {
        throw new UnsupportedOperationException("Property " + b() + " is read-only");
    }
}
